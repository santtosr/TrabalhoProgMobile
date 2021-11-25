 package com.example.trabalho2progmobile.aplicacao

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.trabalho2progmobile.R
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        solicitarPemissoes()
    }

     private fun solicitarPemissoes(){
         val permissions = arrayOf(
             "android.permission.SYSTEM_ALERT_WINDOW",
             "android.permission.CAMERA"
         )
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             requestPermissions(permissions, 200)
         }
     }
}