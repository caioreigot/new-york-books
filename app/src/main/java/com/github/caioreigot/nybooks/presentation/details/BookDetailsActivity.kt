package com.github.caioreigot.nybooks.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.github.caioreigot.nybooks.R
import com.github.caioreigot.nybooks.presentation.base.BaseActivity

class BookDetailsActivity : BaseActivity() {

    lateinit var toolbarMain: Toolbar
    lateinit var tv_book_details_title: TextView
    lateinit var tv_book_details_description: TextView

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent(context: Context, title: String, description: String): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        toolbarMain = findViewById(R.id.toolbarMain)
        setupToolbar(toolbarMain, R.string.books_details_title, true)

        // Assignments
        tv_book_details_title = findViewById(R.id.book_details_title)
        tv_book_details_description = findViewById(R.id.book_details_description)

        tv_book_details_title.text = intent.getStringExtra(EXTRA_TITLE)
        tv_book_details_description.text = intent.getStringExtra(EXTRA_DESCRIPTION)
    }
}