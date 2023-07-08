package kz.arctan.educheck.audio_video_install.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
fun AudioVideoInstall(
    link: String,
    onLinkChange: (String) -> Unit,
    onUploadFile: () -> Unit,
    transcript: String,
    onStartTest: () -> Unit,
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
                text = "Загрузи аудио, видео или PDF",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 40.sp,
                color = Color.White,
                lineHeight = 40.sp
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            TextField(
                value = link,
                onValueChange = onLinkChange,
                placeholder = {
                    Text(
                        text = "Ссылка",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 24.sp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                textStyle = MaterialTheme.typography.labelSmall.copy(fontSize = 24.sp),
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp)
            ) {
                Text(
                    text = "или",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 24.sp),
                    color = Color.White
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onUploadFile,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "Загрузите файл",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 20.sp)
                    )
                }
                Text(
                    text = "макс. 125Mб",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 20.sp)
                )
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {
                Text(
                    text = "Транскрипт",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                    color = Color.White
                )
            }
            SelectionContainer(
                modifier = Modifier
                    .size(340.dp, 280.dp)
                    .padding(bottom = 25.dp)
                    .background(Color.White, RoundedCornerShape(6.dp))
            ) {
                val scrollableFeedbackState = rememberScrollState()
                Text(
                    text = transcript,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 16.sp),
                    modifier = Modifier
                        .padding(12.dp)
                        .verticalScroll(state = scrollableFeedbackState)
                )
            }
            CustomButton(
                textStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                onClick = onStartTest,
                text = "Тестирование",
                width = 200.dp
            )
        }
    }
}

@Composable
fun AudioVideoInstallScreen(navController: NavController, viewModel: AudioVideoInstallViewModel) {
    val state by viewModel.state.collectAsState()
    AudioVideoInstall(
        link = state.link,
        onLinkChange = { viewModel.reduce(AudioVideoInstallIntent.LinkChangeIntent(it)) },
        onUploadFile = { viewModel.reduce(AudioVideoInstallIntent.UploadFileIntent) },
        transcript = state.transcript,
        onStartTest = { viewModel.reduce(AudioVideoInstallIntent.StartTestIntent) }
    )
}

@Preview
@Composable
fun AudioVideoInstallPagePreview() {
    EduCheckTheme() {
        AudioVideoInstall(
            link = "",
            onUploadFile = { /*TODO*/ },
            transcript = "",
            onStartTest = {},
            onLinkChange = {}
        )
    }
}