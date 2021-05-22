package com.github.caioreigot.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.caioreigot.nybooks.R
import com.github.caioreigot.nybooks.data.BooksResult
import com.github.caioreigot.nybooks.data.model.Book
import com.github.caioreigot.nybooks.data.repository.BooksRepository
import java.lang.IllegalArgumentException

/*
 * Obs: não receber nenhuma referência de uma activity ou fragment no ViewModel
 * O ViewModel precisa ser independente do framework do Android
 * Assim, quando a atividade for destruida (após rotacionar a tela, por exemplo),
 * o ViewModel não será destruido, ele será "linkado" à uma nova instância daquela
 * activity ou fragment após ela ser reconstruída
 */

class BooksViewModel(val dataSource: BooksRepository) : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

    fun getBooks() {
        // Passando o Callback para a interface
        dataSource.getBooks { result: BooksResult ->
            when (result) {
                is BooksResult.Success -> {
                    booksLiveData.value = result.books
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                }

                is BooksResult.ApiError -> {
                    if (result.statusCode == 401)
                        viewFlipperLiveData.value = Pair(BooksViewModel.VIEW_FLIPPER_ERROR, R.string.books_error_401)
                    else
                        viewFlipperLiveData.value = Pair(BooksViewModel.VIEW_FLIPPER_ERROR, R.string.books_error_400_generic)
                }

                is BooksResult.ServerError -> {
                    viewFlipperLiveData.value = Pair(BooksViewModel.VIEW_FLIPPER_ERROR, R.string.books_error_500_generic)
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class ViewModelFactory(val dataSource: BooksRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BooksViewModel::class.java))
                return BooksViewModel(dataSource) as T

            throw IllegalArgumentException("Unkown ViewModel class")
        }
    }
}