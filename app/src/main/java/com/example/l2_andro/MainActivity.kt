package com.example.l2_andro

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.l2_andro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_main)

        val toolbar1 = binding.toolbar1
        setSupportActionBar(toolbar1)

        val button1 = binding.button1
        button1.setOnClickListener { _ ->
            setPrefs(1)
            recreate()
        }

        val button2 = binding.button2
        button2.setOnClickListener { _ ->
            setPrefs(2)
            recreate()
        }

        val button3 = binding.button3
        button3.setOnClickListener { _ ->
            setPrefs(3)
            recreate()
        }
    }

    private fun applyTheme(){
        val data: SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        var themeNum = data.getInt("theme_color", 0)
        when (themeNum){
            1 -> setTheme(R.style.AppThemeGreen)
            2 -> setTheme(R.style.AppThemeRed)
            3 -> setTheme(R.style.Theme_L2_andro)
            else -> setTheme(R.style.Theme_L2_andro)
        }
    }
    private fun setPrefs(themeNum:Int){
        val data: SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = data.edit()
        editor.putInt("theme_color", themeNum)
        editor.apply()

    }
}