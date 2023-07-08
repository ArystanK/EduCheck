package kz.arctan.educheck.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kz.arctan.educheck.Screens
import kz.arctan.educheck.audio_video_install.presentation.AudioVideoInstallScreen
import kz.arctan.educheck.auth.presentation.ProfilePage
import kz.arctan.educheck.common.theme.EduCheckTheme
import kz.arctan.educheck.common.theme.PurpleBackground
import kz.arctan.educheck.conspectus.presentation.ConspectusScreen
import kz.arctan.educheck.essay.presentation.EssayCheckScreen
import kz.arctan.educheck.program.presentation.ChooseProgramScreen
import kz.arctan.educheck.program.presentation.FromUsProgramScreen
import kz.arctan.educheck.program.presentation.WithUsProgramScreen
import kz.arctan.educheck.question_answer.presentation.QuestionAnswerScreen

sealed class Screen(val route: String, val icon: ImageVector) {
    object Profile : Screen(Screens.PROFILE.name, Icons.Outlined.Person)
    object Settings : Screen("settings", Icons.Outlined.Settings)
    object Main : Screen(Screens.CHOOSE_PROGRAM.name, Icons.Outlined.Home)
}

val items = listOf(
    Screen.Settings,
    Screen.Main,
    Screen.Profile
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(upperNavController: NavController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF8842D9),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    ),
                backgroundColor = Color(0xFF8842D9)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    val isSelected = screen.route == Screen.Profile.route
//                            currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    BottomNavigationItem(
                        icon = {
                            CustomBottomNavigationItem(
                                icon = screen.icon,
                                type = screen.route
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            if (screen.route in listOf(
                                    Screens.CHOOSE_PROGRAM.name,
                                    Screens.PROFILE.name
                                )
                            )
                                navController.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                        }
                    )
                }
            }
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = Screens.CHOOSE_PROGRAM.name) {
                composable(Screens.CHOOSE_PROGRAM.name) { ChooseProgramScreen(navController = navController) }
                composable(Screens.PROFILE.name) { ProfilePage() }
                composable(Screens.FROM_US.name) { FromUsProgramScreen(navController) }
                composable(Screens.WITH_US.name) { WithUsProgramScreen(navController) }
                composable(Screens.CREATE_CONSPECTUS.name) {
                    ConspectusScreen(
                        navController = navController,
                        viewModel = hiltViewModel()
                    )
                }
                composable(Screens.CHECK_ESSAY.name) {
                    EssayCheckScreen(
                        navController = navController,
                        viewModel = hiltViewModel()
                    )
                }
                composable(Screens.QUESTION_ANSWER.name) {
                    QuestionAnswerScreen(navController = navController, viewModel = hiltViewModel())
                }
                composable(Screens.INSTALL_AUDIO.name) {
                    AudioVideoInstallScreen(navController = navController, hiltViewModel())
                }
            }
        }
    }
}
