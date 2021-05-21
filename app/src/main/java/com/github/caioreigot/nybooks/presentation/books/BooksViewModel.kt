package com.github.caioreigot.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.caioreigot.nybooks.data.ApiService
import com.github.caioreigot.nybooks.data.model.Book
import com.github.caioreigot.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
 * Obs: não receber nenhuma referência de uma activity ou fragment no ViewModel
 * O ViewModel precisa ser independente do framework do Android
 * Assim, quando a atividade for destruida (após rotacionar a tela, por exemplo),
 * o ViewModel não será destruido, ele será "linkado" à uma nova instância daquela
 * activity ou fragment após ela ser reconstruída
 */

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks() {
        //booksLiveData.value = createFakeBooks()

        //Esta chamada à Api poderia estar na camada repository
        ApiService.service.getBooks().enqueue(object: Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val books: MutableList<Book> = mutableListOf()

                    response.body()?.let { bookBodyResponse ->
                        for (result in bookBodyResponse.bookResults) {
                            val book = Book(
                                title = result.bookDetails[0].title,
                                author = result.bookDetails[0].author
                            )

                            books.add(book)
                        }
                    }

                    booksLiveData.value = books
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {

            }
        })
    }

    /*
    fun createFakeBooks(): List<Book> {
        return listOf(
            Book("Title 1", "Author 1"),
            Book("Title 2", "Author 2"),
            Book("Title 3", "Author 3"),
        )
    }
     */

}