package com.example.adad

import java.text.Normalizer

object Normalizer {

    fun textNormalizer(text: String): String {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
            .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
    }
}