package org.example.rickmortyapp.core

import platform.Foundation.NSLog

actual object Logger {
    actual fun i(tag: String, message: String) {
        NSLog("[$tag] $message")
    }
}