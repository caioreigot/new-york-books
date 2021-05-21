package com.github.caioreigot.nybooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.caioreigot.nybooks.R
//import androidx.activity.viewModels

class BooksActivity : AppCompatActivity() {

    //private val viewModel: BooksViewModel by viewModels()

    lateinit var toolbarMain: Toolbar
    lateinit var recyclerViewBooks: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        toolbarMain = findViewById(R.id.toolbarMain)
        recyclerViewBooks = findViewById(R.id.recyclerBooks)

        toolbarMain.title = getString(R.string.books_title)
        setSupportActionBar(toolbarMain)

        val viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with (recyclerViewBooks) {
                    layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books)
                }
            }
        })

        viewModel.getBooks()
    }

}