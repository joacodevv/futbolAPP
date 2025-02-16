package com.example.adad

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adad.ui.theme.Correcto
import com.example.adad.ui.theme.Errado
import com.example.adad.ui.theme.Gris
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class QuestionViewModel : ViewModel() {

    private val _questionsData = MutableStateFlow<Question?>(null)
    val questionsData: StateFlow<Question?> = _questionsData

    val usedNumbers = arrayListOf<Int>()


    private val questionApi = ApiClient.getRetrofit()


    fun fetchQuestions() {
        viewModelScope.launch {
            try {
                val num = ranNum()
                val response = questionApi.getQuestions()
                _questionsData.value = response.record.preguntas[num]
                usedNumbers.add(num)
            } catch (e: Exception) {
                Log.i("quepaso", _questionsData.value.toString())
            }
        }
    }

    private fun ranNum(): Int {
        val random = Random.nextInt(1, 12)

        return if (random in usedNumbers) {
            ranNum()
        } else {
            random
        }
    }

    fun checkAnswer1(answer1: String): Boolean{
        return answer1 == questionsData.value?.respuestas?.get(0)
    }

    fun checkAnswer2(answer2: String): Boolean{
        return answer2 == questionsData.value?.respuestas?.get(1)
    }

    fun checkAnswer3(answer3: String): Boolean{
        return answer3 == questionsData.value?.respuestas?.get(2)

    }

    fun checkAnswer4(answer4: String): Boolean{
        return answer4 == questionsData.value?.respuestas?.get(3)

    }

    fun checkAnswer5(answer5: String): Boolean{
        return answer5 == questionsData.value?.respuestas?.get(4)

    }

    fun checkWin(answer1: Boolean, answer2: Boolean, answer3: Boolean, answer4: Boolean, answer5: Boolean): Boolean{
        return if(answer1 && answer2 && answer3 && answer4 && answer5){
            true
        }else{
            false

        }
    }

    fun textFieldBgColor1(answer1: String): Color{
        when (answer1){
            questionsData.value?.respuestas?.get(0) -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor2(answer2: String): Color{
        when (answer2){
            questionsData.value?.respuestas?.get(1) -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor3(answer3: String): Color{
        when (answer3){
            questionsData.value?.respuestas?.get(2) -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor4(answer4: String): Color{
        when (answer4){
            questionsData.value?.respuestas?.get(3) -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }

    fun textFieldBgColor5(answer5: String): Color{
        when (answer5){
            questionsData.value?.respuestas?.get(4) -> return Correcto
            "" -> return Gris
            else -> return Errado
        }
    }





}