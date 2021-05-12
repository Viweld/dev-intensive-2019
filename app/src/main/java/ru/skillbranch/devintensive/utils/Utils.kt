package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.models.User

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val firstName: String?
        val lastName: String?
        when (fullName) {
            "", " " -> {
                firstName = null
                lastName = null
            }
            else -> {
                val parts: List<String>? = fullName?.split(" ")
                firstName = parts?.getOrNull(0)
                lastName = parts?.getOrNull(1)
            }
        }
        return firstName to lastName
    }

    fun transliteration(payload: String/*, divider: String = " "*/): String {
        var caseTrigger = false
        var string = ""
        payload.forEach {
            var char = it.toString()
            if (it.isUpperCase()) {
                char = char.toLowerCase()
                caseTrigger = true
            }
            char = when (char) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh'"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                else -> it
            }.toString()
            if (caseTrigger) {
                char = char.capitalize()
                caseTrigger = false
            }
            string += char
        }
        return string
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val initials: String?
        if (firstName != null && firstName != "" && firstName != " ") {
            when {
                lastName != null && lastName != "" && lastName != " " -> initials = "${firstName[0].toUpperCase()}${lastName[0].toUpperCase()}"
                else -> initials = "${firstName[0].toUpperCase()}"
            }
        } else initials = null
        return initials
    }
}