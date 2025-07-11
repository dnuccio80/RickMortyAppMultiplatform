package org.example.rickmortyapp.core

expect object Logger {
    fun i(tag:String, message:String)
}