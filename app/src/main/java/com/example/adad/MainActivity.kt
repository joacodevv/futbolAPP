package com.example.adad

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.adad.ui.theme.AdadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                Screen(questionViewModel = QuestionViewModel())
        }
    }
}

@Composable
fun Screen(questionViewModel: QuestionViewModel ){

    val questionData = questionViewModel.questionsData.collectAsState()
    var isVisible by remember { mutableStateOf(false) }



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Button(onClick = {
                questionViewModel.fetchQuestions()
                isVisible = true
            }) {
                Text(text = "Questions")
            }
            AnimatedVisibility(visible = isVisible) {
                questionData.value?.let { Text(it.pregunta) }
            }

        }

    }

fun callQuestions(viewModel: QuestionViewModel){
    viewModel.fetchQuestions()
    Log.i("Questions", viewModel.questionsData.value.toString())
}