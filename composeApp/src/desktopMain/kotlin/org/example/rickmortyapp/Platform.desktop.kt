package org.example.rickmortyapp

class DesktopPlatform : Platform {
    override val name: String = "I am the desktop :)"
}

actual fun getPlatform(): Platform = DesktopPlatform()