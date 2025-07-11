package org.example.rickmortyapp.core

actual object Logger {
    actual fun i(tag: String, message: String) {
        android.util.Log.i(tag, message)
    }
}