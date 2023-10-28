package com.ccb.kmmtranslator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ccb.kmmtranslator.android.core.presentation.Routes
import com.ccb.kmmtranslator.android.translate.presentation.AndroidTranslateViewModel
import com.ccb.kmmtranslator.android.translate.presentation.TranslateScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KmmTranslatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TranslateRoot()
                }
            }
        }
    }
}

@Composable
fun TranslateRoot() {
    val navController = rememberNavController()
    val viewModel: AndroidTranslateViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Routes.TRANSLATE,
    ) {
        composable(route = Routes.TRANSLATE) {
            val state by viewModel.state.collectAsState()
            TranslateScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}
