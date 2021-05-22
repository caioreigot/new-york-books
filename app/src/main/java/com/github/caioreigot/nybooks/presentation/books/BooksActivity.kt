package com.github.caioreigot.nybooks.presentation.books

import android.os.Bundle
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.caioreigot.nybooks.R
import com.github.caioreigot.nybooks.data.repository.BooksApiDataSource
import com.github.caioreigot.nybooks.presentation.base.BaseActivity
import com.github.caioreigot.nybooks.presentation.details.BookDetailsActivity
//import androidx.activity.viewModels

class BooksActivity : BaseActivity() {

    //private val viewModel: BooksViewModel by viewModels()

    lateinit var toolbarMain: Toolbar
    lateinit var recyclerViewBooks: RecyclerView
    lateinit var viewFlipperBooks: ViewFlipper
    lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        // Assignments
        toolbarMain = findViewById(R.id.toolbarMain)
        recyclerViewBooks = findViewById(R.id.recycler_books)
        viewFlipperBooks = findViewById(R.id.view_flipper_books)
        textViewError = findViewById(R.id.text_view_error)

        setupToolbar(toolbarMain, R.string.books_title)

        // DI
        val viewModel: BooksViewModel = BooksViewModel.ViewModelFactory(
            BooksApiDataSource()
        )
            .create(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with (recyclerViewBooks) {
                    layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(this@BooksActivity, book.title, book.description)
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperBooks.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageRes ->
                    textViewError.text = getString(errorMessageRes)
                }
            }
        })

        viewModel.getBooks()
    }

}