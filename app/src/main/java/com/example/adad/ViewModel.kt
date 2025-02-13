package com.example.adad

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuestionViewModel : ViewModel() {

    private val _questionsData = MutableStateFlow<Question?>(null)
    val questionsData: StateFlow<Question?> = _questionsData


    private val questionApi = ApiClient.getRetrofit()


    fun fetchQuestions() {
        viewModelScope.launch {
            try{
                val response = questionApi.getQuestions()
                _questionsData.value = response.record.questions[0]
            }catch (e: Exception){
                Log.i("quepaso", _questionsData.value.toString())
            }
        }
    }
}