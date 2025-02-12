package com.project.sportify.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.project.sportify.ui.base.composables.IBaseBottomNavigationBar
import com.project.sportify.ui.main.contract.MainContract
import com.project.sportify.ui.theme.SportifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , IBaseBottomNavigationBar {

    private  val viewModel :MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    AppNavHost(
                        viewModel = viewModel,
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
    override fun showBottomNavigation() {
        viewModel.setEvent(MainContract.Event.ShowBottomNavigationBar)
    }

    override fun hideBottomNavigation() {
        viewModel.setEvent(MainContract.Event.HideBottomNavigationBar)
    }
}
