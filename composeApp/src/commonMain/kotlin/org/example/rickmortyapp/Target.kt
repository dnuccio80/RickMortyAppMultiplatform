package org.example.rickmortyapp

expect fun getCurrentTarget():Target

enum class Target {
    iOs, Android, Desktop
}

fun isDesktopTarget():Boolean = getCurrentTarget() == Target.Desktop