package com.github.caioreigot.nybooks.data

import com.github.caioreigot.nybooks.data.model.Book

sealed class BooksResult {
    class Success(val books: List<Book>) : BooksResult()
    class ApiError(val statusCode: Int) : BooksResult()
    object ServerError : BooksResult()
}