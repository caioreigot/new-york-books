package com.github.caioreigot.nybooks.presentation.base

import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(
        toolbar: Toolbar,
        @StringRes titleRes: Int,
        showBackButton: Boolean = false
    ) {
        toolbar.title = getString(titleRes)
        setSupportActionBar(toolbar)

        // Ativando a seta de voltar na Toolbar
        if (showBackButton)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}