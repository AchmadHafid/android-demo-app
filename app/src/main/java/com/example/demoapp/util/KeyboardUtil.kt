package com.example.demoapp.util

import android.widget.EditText
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

fun Fragment.showKeyboardOn(input: EditText) {
    if (input.hasFocus().not()) {
        input.requestFocus()
    }

    activity?.let {
        WindowCompat.getInsetsController(it.window, input)
            .show(WindowInsetsCompat.Type.ime())
    }
}