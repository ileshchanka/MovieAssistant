package com.example.movieassistant

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val IMAGE_ID = "image_id"
        const val TITLE = "title"
        const val OVERVIEW = "overview"
        const val FAVOURITE = "favourite"
        const val COMMENT = "comment"
        const val LOG_TAG = "MainActivity"
        const val THE_ICE_AGE_OPENED = "opened_the_ice_age"
        const val THE_SPIDER_MAN_OPENED = "opened_spider_man"
        const val THE_355_OPENED = "opened_the_355"
    }

    private val showDetails = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.getStringExtra(COMMENT)?.let { Log.d(LOG_TAG, it) }
            Log.d(LOG_TAG, result.data?.getBooleanExtra(FAVOURITE, false).toString())
        }
    }

    private lateinit var titleTheIceAge: TextView
    private lateinit var titleSpiderMan: TextView
    private lateinit var titleThe355: TextView

    private var isTheIceAgeOpened: Boolean = false
    private var isTheSpiderManOpened: Boolean = false
    private var isThe355Opened: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            isTheIceAgeOpened = it.getBoolean(THE_ICE_AGE_OPENED)
            isTheSpiderManOpened = it.getBoolean(THE_SPIDER_MAN_OPENED)
            isThe355Opened = it.getBoolean(THE_355_OPENED)
        }


        // The Ice Age Adventures of Buck Wild (2022)
        titleTheIceAge = findViewById<TextView>(R.id.textViewTheIceAge).apply {
            if (isTheIceAgeOpened) setTextColor(resources.getColor(R.color.purple_200))
        }
        val overviewTheIceAge = findViewById<TextView>(R.id.textViewTheIceAgeOverview)

        findViewById<Button>(R.id.buttonTheIceAge).setOnClickListener {
            isTheIceAgeOpened = true
            titleTheIceAge.setTextColor(resources.getColor(R.color.purple_200))
            val intentTheIceAge = DetailsActivity.build(
                context = this,
                imageId = R.drawable.the_ice_age,
                title = titleTheIceAge.text.toString(),
                overview = overviewTheIceAge.text.toString()
            )
            showDetails.launch(intentTheIceAge)
        }


        // Spider-Man: No Way Home (2021)
        titleSpiderMan = findViewById<TextView>(R.id.textViewSpiderMan).apply {
            if (isTheSpiderManOpened) setTextColor(resources.getColor(R.color.purple_200))
        }
        val overviewSpiderMan = findViewById<TextView>(R.id.textViewSpiderManOverview)

        findViewById<Button>(R.id.buttonSpiderMan).setOnClickListener {
            isTheSpiderManOpened = true
            titleSpiderMan.setTextColor(resources.getColor(R.color.purple_200))
            val intentSpiderMan = DetailsActivity.build(
                context = this,
                imageId = R.drawable.spider_man,
                title = titleSpiderMan.text.toString(),
                overview = overviewSpiderMan.text.toString()
            )
            showDetails.launch(intentSpiderMan)
        }


        // The 355 (2022)
        titleThe355 = findViewById<TextView>(R.id.textViewThe355).apply {
            if (isThe355Opened) setTextColor(resources.getColor(R.color.purple_200))
        }
        val overviewThe355 = findViewById<TextView>(R.id.textViewThe355Overview)

        findViewById<Button>(R.id.buttonThe355).setOnClickListener {
            isThe355Opened = true
            val intentThe355 = DetailsActivity.build(
                context = this,
                imageId = R.drawable.the_355,
                title = titleThe355.text.toString(),
                overview = overviewThe355.text.toString()
            )
            showDetails.launch(intentThe355)
        }


        // Invite Friend
        findViewById<Button>(R.id.inviteFriend).setOnClickListener {
            startActivity(Intent(Intent.ACTION_SEND).apply {
                type = "text/plain";
                putExtra(Intent.EXTRA_TITLE, "Invite")
                putExtra(Intent.EXTRA_TEXT, "Hello, my friend!")
            })
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(THE_ICE_AGE_OPENED, isTheIceAgeOpened)
        outState.putBoolean(THE_SPIDER_MAN_OPENED, isTheSpiderManOpened)
        outState.putBoolean(THE_355_OPENED, isThe355Opened)
    }
}
