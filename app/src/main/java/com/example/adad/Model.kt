package com.example.adad

data class JsonResponse(
    val record: Record
)

data class Record(
    val questions: List<Question>
)

data class Question(
    val question: String,
    val answers: List<String>
)
