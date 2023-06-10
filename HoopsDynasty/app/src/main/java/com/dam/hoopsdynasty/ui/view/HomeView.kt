package com.dam.hoopsdynasty.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel

@Composable
fun HomeView(viewModel: MainViewModel) {
    val managerViewModel = viewModel.managerViewModel
    val managerState by managerViewModel.getManager().collectAsState(initial = null)

    // Display the manager information
    Column() {

        Text(text = "Manager Name: ${managerState?.name }")
        Text(text = "Manager Email: ${managerState?.email }")
        Text(text = "Manager Team: ${managerState?.team?.name }")
    }
    // Add more Text components for other manager properties you want to display
}




