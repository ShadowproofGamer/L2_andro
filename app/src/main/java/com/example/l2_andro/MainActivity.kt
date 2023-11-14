package com.example.l2_andro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.graphics.Typeface.ITALIC
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.ButtonBarLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import com.example.l2_andro.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var button1: Button
    lateinit var button2: Button
    //lateinit var button3: Button
    lateinit var toolbar1: Toolbar
    lateinit var buttonRight: Button
    companion object {
        var tsize: Float = 14F
        var tface: Int = Typeface.NORMAL
        var checkedBold: Boolean = false
        var checkedItalic: Boolean = false
    }


    //fun OnCreateOptionsMenu(menu: Menu!):Boolean
    fun applyTheme() {
        var data: SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        var themeNum = data.getInt("theme_color", 0)
        when (themeNum) {
            1 -> setTheme(R.style.AppThemeRed)
            2 -> setTheme(R.style.AppThemeGreen)
            3 -> setTheme(R.style.AppThemeGrey)
            else -> setTheme(R.style.Theme_L2_andro)
        }
            //theme.applyStyle(R.style.Theme_L2_andro, true)
    }

    fun setPrefs(themeNum: Int) {
        val data: SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = data.edit()
        editor.putInt("theme_color", themeNum)
        editor.apply()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show()
        return when (item.itemId) {
            R.id.mi0 -> {
                setPrefs(0)
                recreate()
                true
            }
            R.id.miRes -> {
                tsize = 14F
                tface = Typeface.NORMAL
                checkedBold = false
                checkedItalic = false
                recreate()
                true
            }
            R.id.mi1 -> {
                setPrefs(1)
                recreate()
                true
            }
            R.id.mi2 -> {
                setPrefs(2)
                recreate()
                true
            }
            R.id.mi3 -> {
                setPrefs(3)
                recreate()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(ctxmenu: ContextMenu, myview:View, ctxmi: ContextMenuInfo?){
        when(myview){
            button1 -> menuInflater.inflate(R.menu.cm_fontsize, ctxmenu)
            button2 -> {
                menuInflater.inflate(R.menu.cm_fonttype, ctxmenu)
                ctxmenu.findItem(R.id.ch_type2)?.isChecked = checkedItalic
                ctxmenu.findItem(R.id.ch_type3)?.isChecked = checkedBold
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ch_size1 -> {
                button1.textSize = 20F
                tsize = 20F
                //recreate()
                true
            }
            R.id.ch_size2 -> {
                button1.textSize = 22F
                tsize = 22F
                //recreate()
                true
            }
            R.id.ch_size3 -> {
                button1.textSize = 24F
                tsize = 24F
                //recreate()
                true
            }
            /*
            R.id.ch_type1 -> {
                //
                recreate()
                true
            }
             */
            R.id.ch_type2 -> {
                checkedItalic = !checkedItalic
                item.isChecked = checkedItalic
                //button2.setTypeface(null, Typeface.ITALIC)
                tface = if (checkedItalic && checkedBold) Typeface.BOLD_ITALIC
                else if (checkedItalic) Typeface.ITALIC
                else if (checkedBold) Typeface.BOLD
                else Typeface.NORMAL
                recreate()
                true
            }
            R.id.ch_type3 -> {
                checkedBold = !checkedBold
                item.isChecked = checkedBold
                item.isChecked = !item.isChecked
                //button2.setTypeface(null, Typeface.BOLD)
                tface = if (checkedItalic && checkedBold) Typeface.BOLD_ITALIC
                else if (checkedItalic) Typeface.ITALIC
                else if (checkedBold) Typeface.BOLD
                else Typeface.NORMAL
                recreate()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        toolbar1 = binding.toolbar1
        setSupportActionBar(toolbar1)

        button1 = binding.button1
        button1.textSize = tsize
        registerForContextMenu(button1)

        button2 = binding.button2
        button2.setTypeface(null, tface)
        registerForContextMenu(button2)

        buttonRight = binding.buttonMRight
        buttonRight.setOnClickListener { _ ->
            val myIntent = Intent(this, ActivityRight::class.java)
            startActivity(myIntent)

        }
    }
    override fun onRestart() {
        super.onRestart()
        recreate()
    }
}