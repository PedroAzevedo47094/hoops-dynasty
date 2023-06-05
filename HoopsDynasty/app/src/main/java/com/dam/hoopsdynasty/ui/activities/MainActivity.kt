package com.dam.hoopsdynasty.ui.activities


import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dam.hoopsdynasty.ui.theme.HoopsDynastyTheme
import com.dam.hoopsdynasty.ui.view.Background

import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           HoopsDynastyApp(viewModel = mainViewModel)

          //  HoopsDynastyApp()
        }

    }
}

@Composable
//fun HoopsDynastyApp(){
fun HoopsDynastyApp(viewModel: MainViewModel){
    val viewModel : MainViewModel = viewModel()
    HoopsDynastyTheme {

        MaterialTheme {
            Background()
            //ManagerInfo(viewModel)
            //ManagerInfo()
        }

    }
}


