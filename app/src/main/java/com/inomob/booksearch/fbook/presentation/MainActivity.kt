package com.inomob.booksearch.fbook.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.inomob.booksearch.fbook.presentation.search_book.BookSearchScreen
import com.inomob.booksearch.fbook.presentation.search_book.BooksViewModel
import com.inomob.booksearch.fbook.presentation.ui.theme.BookSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookSearchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    WindowCompat.setDecorFitsSystemWindows(window, false)
                    val systemUiController = rememberSystemUiController()
                    val navController = rememberNavController()
                    val isDarkIcons = MaterialTheme.colors.isLight

                    SideEffect {
                        // System bar colors changed to transparent,
                        // and using dark icons if the application in light mode
                        systemUiController.setSystemBarsColor(
                            color = Color.Transparent,
                            darkIcons = isDarkIcons
                        )
                    }

                    NavHost(
                        navController = navController,
                        startDestination = Route.BookSearchScreen.route)
                    {
                        composable(
                            route = Route.BookSearchScreen.route
                        ){
                            BookSearchScreen()
                        }
                    }
                }
            }
        }
    }
}
