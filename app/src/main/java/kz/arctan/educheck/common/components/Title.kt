package kz.arctan.educheck.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kz.arctan.educheck.R
import kz.arctan.educheck.common.theme.EduCheckTheme

@Composable
fun Title() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 40.sp,
                color = Color.White
            )
            CheckImage()
        }
        Text(
            text = stringResource(id = R.string.self_education_complex),
            style = MaterialTheme.typography.labelSmall,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    EduCheckTheme {
        Title()
    }
}