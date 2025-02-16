package com.example.adad

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
fun Screen(questionViewModel: QuestionViewModel) {

    val questionData = questionViewModel.questionsData.collectAsState()
    var isVisible by remember { mutableStateOf(false) }

    var answer1 by remember { mutableStateOf("") }
    var answer2 by remember { mutableStateOf("") }
    var answer3 by remember { mutableStateOf("") }
    var answer4 by remember { mutableStateOf("") }
    var answer5 by remember { mutableStateOf("") }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 28.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        Column {
            Button(onClick = {
                questionViewModel.fetchQuestions()
                isVisible = true
                Log.i("Questions", questionViewModel.questionsData.value.toString())
            }) {
                Text(text = "Start Game")
            }

            AnimatedVisibility(visible = isVisible) {
                Column {
                    Text(text = questionData.value?.pregunta ?: "")

                    TextField(value = answer1,
                        onValueChange = { answer1 = it },
                        label = { Text("Answer") })
                    TextField(value = answer2,
                        onValueChange = { answer2 = it },
                        label = { Text("Answer") })
                    TextField(value = answer3,
                        onValueChange = { answer3 = it },
                        label = { Text("Answer") })
                    TextField(value = answer4,
                        onValueChange = { answer4 = it },
                        label = { Text("Answer") })
                    TextField(value = answer5,
                        onValueChange = { answer5 = it },
                        label = { Text("Answer") })
                    Button(onClick = {

                        Log.i(
                            "Answers",
                            questionViewModel.checkAnswer1(answer1)
                                .toString() + questionViewModel.checkAnswer2(answer2)
                                .toString() + questionViewModel.checkAnswer3(answer3)
                                .toString() + questionViewModel.checkAnswer4(answer4)
                                .toString() + questionViewModel.checkAnswer5(answer5)
                        )

                        if (questionViewModel.checkWin(
                                questionViewModel.checkAnswer1(answer1),
                                questionViewModel.checkAnswer2(answer2),
                                questionViewModel.checkAnswer3(answer3),
                                questionViewModel.checkAnswer4(answer4),
                                questionViewModel.checkAnswer5(answer5)
                            )
                        ) {
                            questionViewModel.fetchQuestions()
                            answer1 = ""
                            answer2 = ""
                            answer3 = ""
                            answer4 = ""
                            answer5 = ""
                        }


                    }) {
                        Text(text = "Submit")
                    }
                }
            }
        }


        // AnimatedVisibility(visible = isVisible) {
        //     Column {
        //         questionData.value?.let { Text(it.pregunta) }
        //          questionData.value?.let {
        //              it.respuestas.forEach {
        //                  Text(text = it)
        //             }
        //        }
        //     }
        //}

    }

}

fun callQuestions(viewModel: QuestionViewModel) {
    viewModel.fetchQuestions()
    Log.i("Questions", viewModel.questionsData.value.toString())
}