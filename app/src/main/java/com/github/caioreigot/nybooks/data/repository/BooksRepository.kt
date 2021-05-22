package com.github.caioreigot.nybooks.data.repository

import com.github.caioreigot.nybooks.data.BooksResult

interface BooksRepository {
    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}