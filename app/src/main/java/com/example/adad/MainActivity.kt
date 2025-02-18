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
import androidx.compose.material3.TextFieldDefaults


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.adad.Graph.repository
import com.example.adad.ui.theme.Gris
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Graph.provide(this)
        enableEdgeToEdge()
        //deleteTodo()
        //example()
        setContent {
            Screen(questionViewModel = QuestionViewModel())
        }
    }

    private fun example(){
        lifecycleScope.launch(Dispatchers.IO) {
            repository.insertNumber(4)
        }
    }

    private fun deleteTodo(){
        lifecycleScope.launch(Dispatchers.IO) {
            repository.cleanDB()
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

    var textFieldBgColor1 by remember { mutableStateOf(Gris) }
    var textFieldBgColor2 by remember { mutableStateOf(Gris) }
    var textFieldBgColor3 by remember { mutableStateOf(Gris) }
    var textFieldBgColor4 by remember { mutableStateOf(Gris) }
    var textFieldBgColor5 by remember { mutableStateOf(Gris) }

    var nxtLevelBtn by remember { mutableStateOf(false) }

    var isCorrect1 by remember { mutableStateOf(false) }
    var isCorrect2 by remember { mutableStateOf(false) }
    var isCorrect3 by remember { mutableStateOf(false) }
    var isCorrect4 by remember { mutableStateOf(false) }
    var isCorrect5 by remember { mutableStateOf(false) }



    val maxChar = 20

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

                    TextField(
                        value = answer1.lowercase(),
                        onValueChange = {
                            if (it.length <= maxChar) {
                                answer1 = it
                            }
                        },
                        modifier = Modifier.padding(bottom = 12.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = textFieldBgColor1,
                            focusedContainerColor = textFieldBgColor1,
                        ),
                        singleLine = true,
                        readOnly = isCorrect1
                    )

                    TextField(
                        value = answer2.lowercase(),
                        onValueChange = {
                            if (it.length <= maxChar) {
                                answer2 = it
                            }
                        },
                        modifier = Modifier.padding(bottom = 12.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = textFieldBgColor2,
                            focusedContainerColor = textFieldBgColor2,
                        ),
                        singleLine = true,
                        readOnly = isCorrect2
                    )

                    TextField(
                        value = answer3.lowercase(),
                        onValueChange = {
                            if (it.length <= maxChar) {
                                answer3 = it
                            }
                        },
                        modifier = Modifier.padding(bottom = 12.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = textFieldBgColor3,
                            focusedContainerColor = textFieldBgColor3,
                        ),
                        singleLine = true,
                        readOnly = isCorrect3
                    )
                    TextField(
                        value = answer4.lowercase(),
                        onValueChange = {
                            if (it.length <= maxChar) {
                                answer4 = it
                            }
                        },
                        modifier = Modifier.padding(bottom = 12.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = textFieldBgColor4,
                            focusedContainerColor = textFieldBgColor4,
                        ),
                        singleLine = true,
                        readOnly = isCorrect4
                    )
                    TextField(
                        value = answer5.lowercase(),
                        onValueChange = {
                            if (it.length <= maxChar) {
                                answer5 = it
                            }
                        },
                        modifier = Modifier.padding(bottom = 12.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = textFieldBgColor5,
                            focusedContainerColor = textFieldBgColor5,
                        ),
                        singleLine = true,
                        readOnly = isCorrect5
                    )
                    Button(onClick = {

                        if (questionViewModel.checkAnswer1(answer1)) {
                            isCorrect1 = true
                        }
                        if (questionViewModel.checkAnswer2(answer2)) {
                            isCorrect2 = true
                        }
                        if (questionViewModel.checkAnswer3(answer3)) {
                            isCorrect3 = true
                        }
                        if (questionViewModel.checkAnswer4(answer4)) {
                            isCorrect4 = true
                        }
                        if (questionViewModel.checkAnswer5(answer5)) {
                            isCorrect5 = true
                        }

                        if (questionViewModel.checkWin(
                                questionViewModel.checkAnswer1(answer1),
                                questionViewModel.checkAnswer2(answer2),
                                questionViewModel.checkAnswer3(answer3),
                                questionViewModel.checkAnswer4(answer4),
                                questionViewModel.checkAnswer5(answer5)
                            )
                        ) {
                            //Show next level btn
                            nxtLevelBtn = true
                        }

                        textFieldBgColor1 = questionViewModel.textFieldBgColor1(answer1)
                        textFieldBgColor2 = questionViewModel.textFieldBgColor2(answer2)
                        textFieldBgColor3 = questionViewModel.textFieldBgColor3(answer3)
                        textFieldBgColor4 = questionViewModel.textFieldBgColor4(answer4)
                        textFieldBgColor5 = questionViewModel.textFieldBgColor5(answer5)


                    }) {
                        Text(text = "Check Answers")
                    }

                    AnimatedVisibility(visible = nxtLevelBtn) {
                        Button(onClick = {
                            questionViewModel.fetchQuestions()
                            answer1 = ""
                            answer2 = ""
                            answer3 = ""
                            answer4 = ""
                            answer5 = ""
                            textFieldBgColor1 = Gris
                            textFieldBgColor2 = Gris
                            textFieldBgColor3 = Gris
                            textFieldBgColor4 = Gris
                            textFieldBgColor5 = Gris
                            isCorrect1 = false
                            isCorrect2 = false
                            isCorrect3 = false
                            isCorrect4 = false
                            isCorrect5 = false
                            nxtLevelBtn = false
                        }) {
                            Text(text = "Next Level")
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
}