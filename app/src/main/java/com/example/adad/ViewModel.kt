package com.example.adad


import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adad.ui.theme.Correcto
import com.example.adad.ui.theme.Errado
import com.example.adad.ui.theme.Gris
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class QuestionViewModel(private val repository: Repository = Graph.repository) : ViewModel() {

    private val _questionsData = MutableStateFlow<Question?>(null)
    val questionsData: StateFlow<Question?> = _questionsData

    private val usedNumbers = mutableListOf<Int>()

    private val questionApi = ApiClient.getRetrofit()

    private lateinit var correctAnswer1: String
    private lateinit var correctAnswer2: String
    private lateinit var correctAnswer3: String
    private lateinit var correctAnswer4: String
    private lateinit var correctAnswer5: String




    init {
        uploadNums()
    }



    fun fetchQuestions() {
        viewModelScope.launch {
            try {
                val num = ranNum()
                repository.insertNumber(num)
                Log.i("used", num.toString())
                val response = questionApi.getQuestions()
                _questionsData.value = response.record.preguntas[num]
                repository.getNumbers().observeForever {
                    Log.i("usedNumbers", it.joinToString { it.usedNumber.toString() })
                }
                getCorrectAnswers()
            } catch (e: Exception) {
                Log.i("quepaso", _questionsData.value.toString())

            }
        }
    }

    private fun uploadNums(){
        viewModelScope.launch(Dispatchers.IO) {
            val numsFromRoom = repository.getNumsML()
            usedNumbers.addAll(numsFromRoom)
        }
    }

    private fun ranNum(): Int {
        val random = Random.nextInt(0, 68)

        return if (random in usedNumbers) {
            ranNum()
        } else {
            usedNumbers.add(random)
            return random

        }
    }

    private fun getCorrectAnswers(){
        correctAnswer1 = questionsData.value?.respuestas?.get(0)?.lowercase() ?: ""
        correctAnswer2 = questionsData.value?.respuestas?.get(1)?.lowercase() ?: ""
        correctAnswer3 = questionsData.value?.respuestas?.get(2)?.lowercase() ?: ""
        correctAnswer4 = questionsData.value?.respuestas?.get(3)?.lowercase() ?: ""
        correctAnswer5 = questionsData.value?.respuestas?.get(4)?.lowercase() ?: ""
        normalizeCorrectAnswers()
    }

    private fun normalizeCorrectAnswers(){
        correctAnswer1 = Normalizer.textNormalizer(correctAnswer1)
        correctAnswer2 = Normalizer.textNormalizer(correctAnswer2)
        correctAnswer3 = Normalizer.textNormalizer(correctAnswer3)
        correctAnswer4 = Normalizer.textNormalizer(correctAnswer4)
        correctAnswer5 = Normalizer.textNormalizer(correctAnswer5)

    }

    fun checkAnswer1(_answer1: String): Boolean {
        return _answer1 == correctAnswer1
    }

    fun checkAnswer2(_answer2: String): Boolean {
        return _answer2 == correctAnswer2
    }

    fun checkAnswer3(_answer3: String): Boolean {
        return _answer3 == correctAnswer3

    }

    fun checkAnswer4(_answer4: String): Boolean {
        return _answer4 == correctAnswer4

    }

    fun checkAnswer5(_answer5: String): Boolean {
        return _answer5 == correctAnswer5

    }

    fun checkWin(
        answer1: Boolean, answer2: Boolean, answer3: Boolean, answer4: Boolean, answer5: Boolean
    ): Boolean {
        return if (answer1 && answer2 && answer3 && answer4 && answer5) {
            true
        } else {
            false

        }
    }

    fun textFieldBgColor1(_answer1: String): Color {
        when (_answer1) {
            correctAnswer1 -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor2(answer2: String): Color {
        when (answer2) {
            correctAnswer2 -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor3(answer3: String): Color {
        when (answer3) {
            correctAnswer3 -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor4(answer4: String): Color {
        when (answer4) {
            correctAnswer4 -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor5(answer5: String): Color {
        when (answer5) {
            correctAnswer5 -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }


}