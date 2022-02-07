package com.example.movieassistant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movieassistant.MainActivity.Companion.COMMENT
import com.example.movieassistant.MainActivity.Companion.FAVOURITE
import com.example.movieassistant.MainActivity.Companion.IMAGE_ID
import com.example.movieassistant.MainActivity.Companion.OVERVIEW
import com.example.movieassistant.MainActivity.Companion.TITLE

class DetailsActivity : AppCompatActivity() {

    private lateinit var result: Intent
    private lateinit var commentEditText: EditText
    private var isChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        findViewById<ImageView>(R.id.imageViewPoster).setImageResource(intent.getIntExtra(IMAGE_ID, 0))
        findViewById<TextView>(R.id.textViewTitle).text = intent.getStringExtra(TITLE)
        findViewById<TextView>(R.id.textViewOverview).text = intent.getStringExtra(OVERVIEW)
        commentEditText = findViewById<EditText>(R.id.editTextComment)

        findViewById<CheckBox>(R.id.checkBoxFavourite).setOnCheckedChangeListener { _, isChecked ->
            this.isChecked = isChecked
            result = Intent().apply {
                putExtra(FAVOURITE, isChecked)
                putExtra(COMMENT, commentEditText.text.toString())
            }
            setResult(RESULT_OK, result)
        }
    }

    companion object {
        fun build(context: Context, imageId: Int, title: String, overview: String): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(IMAGE_ID, imageId)
                putExtra(TITLE, title)
                putExtra(OVERVIEW, overview)
            }
        }
    }
}
