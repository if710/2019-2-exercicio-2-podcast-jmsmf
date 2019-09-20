package br.ufpe.cin.android.podcast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.doAsync
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resourceLink = "https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml" // rss feed xml file url string

        doAsync { loadRssFeed(resourceLink) }

    }

    // function that gets the contents on the giver URL, parse the URL object as a XML string,
    // and then parses the XML string as an List<ItemFeed>
    private fun loadRssFeed(resourceLink: String): List<ItemFeed> {

        val xmlURL = URL(resourceLink)
        val xmlAsString = xmlURL.readText()
        val publications = Parser.parse(xmlAsString)

        return publications

    }
}
