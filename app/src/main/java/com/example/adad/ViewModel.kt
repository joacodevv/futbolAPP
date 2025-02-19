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
        val random = Random.nextInt(0, 12)

        return if (random in usedNumbers) {
            ranNum()
        } else {
            usedNumbers.add(random)
            return random

        }
    }

    fun checkAnswer1(answer1: String): Boolean {
        return answer1 == questionsData.value?.respuestas?.get(0)?.lowercase()
    }

    fun checkAnswer2(answer2: String): Boolean {
        return answer2 == questionsData.value?.respuestas?.get(1)?.lowercase()
    }

    fun checkAnswer3(answer3: String): Boolean {
        return answer3 == questionsData.value?.respuestas?.get(2)?.lowercase()

    }

    fun checkAnswer4(answer4: String): Boolean {
        return answer4 == questionsData.value?.respuestas?.get(3)?.lowercase()

    }

    fun checkAnswer5(answer5: String): Boolean {
        return answer5 == questionsData.value?.respuestas?.get(4)?.lowercase()

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

    fun textFieldBgColor1(answer1: String): Color {
        when (answer1) {
            questionsData.value?.respuestas?.get(0)?.lowercase() -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor2(answer2: String): Color {
        when (answer2) {
            questionsData.value?.respuestas?.get(1)?.lowercase() -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor3(answer3: String): Color {
        when (answer3) {
            questionsData.value?.respuestas?.get(2)?.lowercase() -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor4(answer4: String): Color {
        when (answer4) {
            questionsData.value?.respuestas?.get(3)?.lowercase() -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor5(answer5: String): Color {
        when (answer5) {
            questionsData.value?.respuestas?.get(4)?.lowercase() -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }


}