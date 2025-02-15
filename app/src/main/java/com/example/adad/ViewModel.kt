package com.example.adad

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            try{
                val num = ranNum()
                val response = questionApi.getQuestions()
                _questionsData.value = response.record.preguntas[num]
                usedNumbers.add(num)
            }catch (e: Exception){
                Log.i("quepaso", _questionsData.value.toString())
            }
        }
    }

    private fun ranNum(): Int{
        val random = Random.nextInt(1,12)

        return if (random in usedNumbers){
            ranNum()
        }else{
            random
        }
    }

}