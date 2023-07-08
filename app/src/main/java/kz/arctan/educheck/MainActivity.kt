package kz.arctan.educheck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kz.arctan.educheck.auth.presentation.WelcomeScreen
import kz.arctan.educheck.common.components.MainPage
import kz.arctan.educheck.common.theme.EduCheckTheme
import kz.arctan.educheck.program.presentation.ChooseProgramPage
import kz.arctan.educheck.program.presentation.ChooseProgramScreen
import kz.arctan.educheck.program.presentation.FromUsProgramScreen
import kz.arctan.educheck.program.presentation.WithUsProgramOption
import kz.arctan.educheck.program.presentation.WithUsProgramScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EduCheckTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.WELCOME.name
                    ) {
                        composable(Screens.WELCOME.name) { WelcomeScreen(navController) }
                        composable(Screens.MAIN.name) { MainPage(navController) }
                    }
                }
            }
        }
    }
}
