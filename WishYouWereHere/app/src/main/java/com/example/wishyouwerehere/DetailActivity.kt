package com.example.wishyouwerehere

import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity 2 – shows full details for a selected Location.
 * Receives the Location as a Parcelable via Intent and sets ALL widgets
 * (including non-TextView widgets) from that data on creation.
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve the Parcelable safely – use the new API on Android 13+ (TIRAMISU)
        val location: Location? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(Location.EXTRA_KEY, Location::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(Location.EXTRA_KEY)
            }

        // 'run' scope function: if location is null, finish() and return in one expression
        location ?: run {
            finish()
            return
        }

        populateViews(location)
    }

    /**
     * Populates every view from the given Location.
     * 'with' scope function: access all location fields without writing "location." every time.
     */
    private fun populateViews(location: Location) = with(location) {

        // ── Image ────────────────────────────────────────────────────────────
        findViewById<ImageView>(R.id.detailImage).apply {
            setImageResource(imageResId)
            contentDescription = name
        }

        // ── Basic text ───────────────────────────────────────────────────────
        findViewById<TextView>(R.id.detailName).text =
            name
        findViewById<TextView>(R.id.detailCity).text =
            city
        findViewById<TextView>(R.id.detailLastVisit).text =
            getString(R.string.label_last_visit_format, lastVisit)
        findViewById<TextView>(R.id.detailDescription).text =
            description

        // ── RatingBar (non-TextView widget #1) ── set from Parcelable ────────
        findViewById<RatingBar>(R.id.detailRating).rating = rating

        // ── Switch (non-TextView widget #2) ── set from Parcelable ───────────
        findViewById<Switch>(R.id.detailVisitedSwitch).isChecked = isVisited

        // ── Spinner (non-TextView widget #3) ── pre-select from Parcelable ───
        val spinner  = findViewById<Spinner>(R.id.detailSeasonSpinner)
        val seasons  = resources.getStringArray(R.array.seasons)
        val adapter  = ArrayAdapter(
            this@DetailActivity,
            android.R.layout.simple_spinner_item,
            seasons
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        spinner.adapter = adapter
        val seasonIndex = seasons.indexOfFirst { it.equals(visitSeason, ignoreCase = true) }
        if (seasonIndex >= 0) spinner.setSelection(seasonIndex)

        // ── RadioGroup (non-TextView widget #4) ── default to Solo ───────────
        findViewById<RadioGroup>(R.id.detailTripTypeGroup).check(R.id.radioSolo)

        // ── Back button ───────────────────────────────────────────────────────
        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }
    }
}
