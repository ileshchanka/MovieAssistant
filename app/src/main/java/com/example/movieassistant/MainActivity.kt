package com.example.movieassistant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.movieassistant.models.MovieItem
import com.example.movieassistant.itemmovie.MovieItemAdapter

class MainActivity : AppCompatActivity() {

    companion object {
        const val FAVOURITE_LIST_EXTRA = "favourite_list_extra"
    }

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }

    private var items = listOf(
        MovieItem(
            imageId = R.drawable.the_ice_age,
            title = "The Ice Age Adventures of Buck Wild (2022)",
            overview = "The fearless one-eyed weasel Buck teams up with mischievous possum brothers Crash &amp; Eddie as they head off on a new adventure into Buck\\'s home: The Dinosaur World.",
        ),
        MovieItem(
            imageId = R.drawable.spider_man,
            title = "Spider-Man: No Way Home (2021)",
            overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
        ),
        MovieItem(
            imageId = R.drawable.the_355,
            title = "The 355 (2022)",
            overview = "A group of top female agents from American, British, Chinese, Columbian and German government agencies are drawn together to try and stop an organization from acquiring a deadly weapon to send the world into chaos.",
        ),
        MovieItem(
            imageId = R.drawable.halo,
            title = "Halo (2022)",
            overview = "Depicting an epic 26th-century conflict between humanity and an alien threat known as the Covenant, the series weaves deeply drawn personal stories with action, adventure and a richly imagined vision of the future.",
        ),
        MovieItem(
            imageId = R.drawable.jackass_forever,
            title = "Jackass Forever (2022)",
            overview = "Celebrate the joy of a perfectly executed shot to the groin as Johnny Knoxville, Steve-O and the rest of the gang return alongside some newcomers for one final round of hilarious, wildly absurd and often dangerous displays of stunts and comedy.",
        ),
        MovieItem(
            imageId = R.drawable.deep_water,
            title = "Deep Water (2022)",
            overview = "Vic and Melinda Van Allen are a couple in the small town of Little Wesley. Their loveless marriage is held together only by a precarious arrangement whereby, in order to avoid the messiness of divorce, Melinda is allowed to take any number of lovers as long as she does not desert her family.",
        ),
        MovieItem(
            imageId = R.drawable.parallels,
            title = "Parallels (2022)",
            overview = "Four teenagers' lives are turned upside down when a mysterious event propels them into parallel dimensions.",
        ),
    )

    private var favourites = mutableListOf<MovieItem>()

    private val showFavourites = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_CANCELED) {
            result.data?.getParcelableArrayListExtra<MovieItem>(FAVOURITE_LIST_EXTRA)?.let {
                favourites = it
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        recyclerView.adapter = MovieItemAdapter(items, object : ClickListener {
            override fun onShortClick(movieItem: MovieItem) {
                Toast.makeText(this@MainActivity, "Movie Click", Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(movieItem: MovieItem) {
                if (favourites.contains(movieItem).not()) {
                    favourites.add(movieItem)
                    Toast.makeText(this@MainActivity, resources.getString(R.string.added_to_favorite), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, resources.getString(R.string.not_added_to_favorite), Toast.LENGTH_SHORT).show()
                }
            }
        })
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(this, R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        recyclerView.addItemDecoration(divider)
    }

    private fun initListeners() {
        findViewById<Button>(R.id.btn_favourites).setOnClickListener {
            val intent = Intent(this, FavouriteActivity::class.java).apply {
                putParcelableArrayListExtra(FAVOURITE_LIST_EXTRA, favourites as ArrayList<MovieItem>)
            }
            showFavourites.launch(intent)
        }
    }

    override fun onBackPressed() {
        DialogExit().show(supportFragmentManager, "dialog")
    }
}
