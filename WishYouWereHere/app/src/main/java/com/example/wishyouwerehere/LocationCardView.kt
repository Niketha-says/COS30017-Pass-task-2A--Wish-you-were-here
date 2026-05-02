package com.example.wishyouwerehere

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView

/**
 * Compound view for a single location card in the grid.
 * Wraps card_location.xml and provides a bind() method to avoid
 * repeating the same binding code for each card in the Activity.
 */
class LocationCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : CardView(context, attrs, defStyle) {

    private val imageView: ImageView
    private val nameText:  TextView
    private val cityText:  TextView
    private val ratingBar: RatingBar

    init {
        // Inflate the card layout into this ViewGroup
        LayoutInflater.from(context).inflate(R.layout.card_location, this, true)
        imageView = findViewById(R.id.cardImage)
        nameText  = findViewById(R.id.cardName)
        cityText  = findViewById(R.id.cardCity)
        ratingBar = findViewById(R.id.cardRating)
    }

    /**
     * Binds a Location to this card's views.
     * 'apply' scope function lets us configure imageView without repeating "imageView."
     */
    fun bind(location: Location) {
        imageView.apply {
            setImageResource(location.imageResId)
            contentDescription = location.name
        }
        nameText.text    = location.name
        cityText.text    = location.city
        ratingBar.rating = location.rating
    }
}
