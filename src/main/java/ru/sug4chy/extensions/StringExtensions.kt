package ru.sug4chy.extensions

fun String.capitalizeFirstLetter(): String {
    if (!this[0].isLetter()) {
        return this
    }

    return replaceFirstChar {
        it.uppercaseChar()
    }
}