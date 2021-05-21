package com.github.caioreigot.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.caioreigot.nybooks.data.model.Book

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
    }

    fun createFakeBooks(): List<Book> {
        return listOf(
            Book("Title 1", "Author 1"),
            Book("Title 2", "Author 2"),
            Book("Title 3", "Author 3"),
        )
    }

}