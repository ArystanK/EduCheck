package kz.arctan.educheck.question_answer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kz.arctan.educheck.common.components.CustomButton
import kz.arctan.educheck.common.theme.EduCheckTheme
import kz.arctan.educheck.common.theme.PurpleBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionAnswerPage(
    isDropDownMenuShown: Boolean,
    onToggleDropDownMenu: (Boolean) -> Unit,
    onSubjectClick: (String) -> Unit,
    subjects: List<String>,
    chosenSubject: String,
    age: String,
    onAgeChange: (String) -> Unit,
    question: String,
    onQuestionChange: (String) -> Unit,
    onSendQuestion: () -> Unit,
    explanation: String,
) {
    val scrollableState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollableState)
            .background(PurpleBackground),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(color = Color(0xDE3D006C), RoundedCornerShape(bottomEnd = 114.dp))
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Вопрос - Ответ",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 40.sp,
                color = Color.White,
                lineHeight = 40.sp
            )
        }
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Категория",
                color = Color.White,
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleLarge
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column {
                    TextField(
                        value = chosenSubject,
                        onValueChange = {},
                        trailingIcon = {
                            IconButton(onClick = { onToggleDropDownMenu(true) }) {
                                Icon(Icons.Default.KeyboardArrowDown, "Show categories")
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onToggleDropDownMenu(true) }
                    )
                    DropdownMenu(
                        modifier = Modifier.width(280.dp),
                        expanded = isDropDownMenuShown,
                        onDismissRequest = { onToggleDropDownMenu(false) }) {
                        subjects.forEach {
                            DropdownMenuItem(onClick = { onSubjectClick(it) }) {
                                Text(text = it)
                            }
                        }
                    }
                }
            }
            Text(
                text = "Возраст",
                color = Color.White,
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleLarge
            )
            TextField(
                value = age,
                onValueChange = onAgeChange,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = question,
                onValueChange = onQuestionChange,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Задай вопрос",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 24.sp)
                    )
                }
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CustomButton(
                    textStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                    text = "Отправить",
                    width = 200.dp,
                    onClick = onSendQuestion
                )
            }
            Text(
                text = "Пояснение",
                color = Color.White,
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleLarge
            )
            SelectionContainer(
                modifier = Modifier
                    .height(230.dp)
                    .fillMaxWidth()
                    .padding(bottom = 25.dp)
                    .background(Color.White, RoundedCornerShape(6.dp))
            ) {
                val scrollableFeedbackState = rememberScrollState()
                Text(
                    text = explanation,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 16.sp),
                    modifier = Modifier
                        .padding(12.dp)
                        .verticalScroll(state = scrollableFeedbackState)
                )
            }
        }
    }
}

@Composable
fun QuestionAnswerScreen(navController: NavController, viewModel: QuestionAnswerViewModel) {
    val state by viewModel.state.collectAsState()
    QuestionAnswerPage(
        isDropDownMenuShown = state.isDropDownMenuShown,
        onToggleDropDownMenu = { viewModel.reduce(QuestionAnswerIntent.ToggleDropDownMenuIntent(it)) },
        onSubjectClick = { viewModel.reduce(QuestionAnswerIntent.SubjectClickIntent(it)) },
        subjects = state.subjects,
        chosenSubject = state.chosenSubject,
        age = state.age,
        onAgeChange = { viewModel.reduce(QuestionAnswerIntent.AgeChangeIntent(it)) },
        question = state.question,
        onQuestionChange = { viewModel.reduce(QuestionAnswerIntent.QuestionChangeIntent(it)) },
        onSendQuestion = { viewModel.reduce(QuestionAnswerIntent.SendQuestionIntent) },
        explanation = state.explanation
    )
}

@Preview
@Composable
fun QuestionAnswerPagePreview() {
    EduCheckTheme {
        var isShow by remember { mutableStateOf(false) }
        var chosenSubject by remember { mutableStateOf("") }
        QuestionAnswerPage(
            isDropDownMenuShown = isShow,
            onToggleDropDownMenu = { isShow = it },
            onSubjectClick = { chosenSubject = it; isShow = false },
            subjects = listOf("Биология", "Алгебра", "Химия", "Физика", "История"),
            chosenSubject = chosenSubject,
            age = "18",
            onAgeChange = {},
            explanation = "",
            onQuestionChange = {},
            onSendQuestion = {},
            question = ""
        )
    }
}