package com.dam.hoopsdynasty.ui.activities


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.dam.hoopsdynasty.ui.theme.HoopsDynastyTheme
import com.dam.hoopsdynasty.ui.view.Background
import com.dam.hoopsdynasty.ui.view.Login


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoopsDynastyTheme {

                MaterialTheme {
                    Background()
                    Login()
                }

            }
        }

    }
}



