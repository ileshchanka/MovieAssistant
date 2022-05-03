package com.example.movieassistant.repositories

import com.example.movieassistant.R
import com.example.movieassistant.models.Movie

interface Repository {
    fun getMovies(): List<Movie>
}

class RepositoryImpl : Repository {

    override fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                imageId = R.drawable.the_ice_age,
                title = "The Ice Age Adventures of Buck Wild (2022)",
                overview = "The fearless one-eyed weasel Buck teams up with mischievous possum brothers Crash & Eddie as they head off on a new adventure into Buck's home: The Dinosaur World.",
            ),
            Movie(
                imageId = R.drawable.spider_man,
                title = "Spider-Man: No Way Home (2021)",
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            ),
            Movie(
                imageId = R.drawable.the_355,
                title = "The 355 (2022)",
                overview = "A group of top female agents from American, British, Chinese, Columbian and German government agencies are drawn together to try and stop an organization from acquiring a deadly weapon to send the world into chaos.",
            ),
            Movie(
                imageId = R.drawable.halo,
                title = "Halo (2022)",
                overview = "Depicting an epic 26th-century conflict between humanity and an alien threat known as the Covenant, the series weaves deeply drawn personal stories with action, adventure and a richly imagined vision of the future.",
            ),
            Movie(
                imageId = R.drawable.jackass_forever,
                title = "Jackass Forever (2022)",
                overview = "Celebrate the joy of a perfectly executed shot to the groin as Johnny Knoxville, Steve-O and the rest of the gang return alongside some newcomers for one final round of hilarious, wildly absurd and often dangerous displays of stunts and comedy.",
            ),
            Movie(
                imageId = R.drawable.deep_water,
                title = "Deep Water (2022)",
                overview = "Vic and Melinda Van Allen are a couple in the small town of Little Wesley. Their loveless marriage is held together only by a precarious arrangement whereby, in order to avoid the messiness of divorce, Melinda is allowed to take any number of lovers as long as she does not desert her family.",
            ),
            Movie(
                imageId = R.drawable.parallels,
                title = "Parallels (2022)",
                overview = "Four teenagers' lives are turned upside down when a mysterious event propels them into parallel dimensions.",
            ),
        )
    }
}
