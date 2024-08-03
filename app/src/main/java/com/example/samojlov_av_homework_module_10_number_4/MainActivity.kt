package com.example.samojlov_av_homework_module_10_number_4

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.samojlov_av_homework_module_10_number_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar
    private lateinit var downloadBT: Button
    private lateinit var bodyEBookTV: TextView
    private val database = Database()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    private fun init() {
        toolbarMain = binding.toolbarMain
        setSupportActionBar(toolbarMain)
        title = getString(R.string.toolbar_title)
        toolbarMain.subtitle = getString(R.string.toolbar_subtitle)
        toolbarMain.setLogo(R.drawable.toolbar_icon)

        downloadBT = binding.downloadBT
        downloadBT.setOnClickListener(this)

        bodyEBookTV = binding.bodyEBookTV
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SetTextI18n", "ShowToast")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.resetMenuMain -> {
                bodyEBookTV.text = ""
                Toast.makeText(
                    applicationContext,
                    getString(R.string.toast_reset),
                    Toast.LENGTH_LONG
                ).show()
            }

            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.toast_exit),
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onClick(v: View?) {
        var result = ""
        when (v?.id) {
            R.id.downloadBT -> {
                result = loadBook(database.textGet).joinToString(
                    separator = "\n"
                )
            }
        }
        bodyEBookTV.text = result
    }
}

fun loadBook(text: String): List<String> {
//функция, которая преобразует строку в список слов
    val list = text.replace("[;,.—«»…!-:–\n ]".toRegex(), "|")
    val listOut = list.split("|").toMutableList()
    listOut.removeAll(listOf(""))
    return listOut
}