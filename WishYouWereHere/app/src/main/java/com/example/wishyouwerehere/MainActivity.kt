package com.example.wishyouwerehere

import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity 1 – shows a 2-column grid of location cards inside a TableLayout.
 * Tapping a card opens DetailActivity, passing the Location as a Parcelable extra.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)

        // Load all locations from the in-memory repository
        val locations = LocationRepository.getLocations()

        // chunked(2) splits the flat list into pairs so we can build one row per pair
        locations.chunked(2).forEach { rowLocations ->

            // Create a new TableRow for this pair of cards
            val tableRow = TableRow(this).apply {
                layoutParams = TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
                )
            }

            // Create a card for each location in this row and add it
            rowLocations.forEach { location ->
                val card = LocationCardView(this).apply {
                    bind(location)
                    setOnClickListener { navigateToDetail(location) }
                }
                tableRow.addView(card)
            }

            tableLayout.addView(tableRow)
        }
    }

    /**
     * Launches DetailActivity with the selected location passed as a Parcelable extra.
     */
    private fun navigateToDetail(location: Location) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(Location.EXTRA_KEY, location)
        }
        startActivity(intent)
    }
}
