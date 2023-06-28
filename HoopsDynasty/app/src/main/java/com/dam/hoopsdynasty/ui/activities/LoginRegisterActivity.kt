package com.dam.hoopsdynasty.ui.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.dam.hoopsdynasty.ui.Navigation
import com.dam.hoopsdynasty.ui.theme.HoopsDynastyTheme
import com.dam.hoopsdynasty.ui.view.Background
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel
import com.google.firebase.FirebaseApp


class LoginRegisterActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*  val options = FirebaseOptions.Builder()
              .setDatabaseUrl("https://hoops-dynasty-b9db9-default-rtdb.europe-west1.firebasedatabase.app")
              // Add other Firebase options if applicable
              .build()*/
        FirebaseApp.initializeApp(this)



        setContent {
            HoopsDynastyApp(viewModel = mainViewModel)


        }

    }
}


