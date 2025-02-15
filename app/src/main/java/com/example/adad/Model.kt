package com.example.adad

data class JsonResponse(
    val record: Record
)

data class Record(
    val preguntas: List<Question>
)

data class Question(
    val pregunta: String,
    val respuestas: List<String>
)
