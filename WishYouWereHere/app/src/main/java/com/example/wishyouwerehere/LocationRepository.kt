package com.example.wishyouwerehere

/**
 * In-memory data source. No disk storage is used – all data lives here at runtime.
 * Using an object (singleton) keeps data concerns separate from the UI layer.
 */
object LocationRepository {

    fun getLocations(): List<Location> = listOf(

        Location(
            name        = "12 Apostles",
            city        = "Great Ocean Road, Victoria",
            lastVisit   = "March 2024",
            rating      = 5.0f,
            description = "The Twelve Apostles are a collection of limestone stacks " +
                          "off the shore of Port Campbell National Park. Carved by erosion " +
                          "over millions of years, the golden towers rise dramatically from " +
                          "the Southern Ocean. Sunrise here is genuinely breathtaking – " +
                          "arrive early to beat the crowds and catch the golden light.",
            imageResId  = R.drawable.location_apostles,
            isVisited   = true,
            visitSeason = "Autumn"
        ),

        Location(
            name        = "Royal Botanic Gardens",
            city        = "Melbourne, Victoria",
            lastVisit   = "January 2025",
            rating      = 4.5f,
            description = "Spanning 38 hectares along the Yarra River, the Royal Botanic " +
                          "Gardens Melbourne is home to over 8,500 plant species from " +
                          "around the world. Perfect for a relaxed afternoon stroll, " +
                          "a lakeside picnic, or exploring the indigenous plant trail. " +
                          "The spring flower displays are absolutely stunning.",
            imageResId  = R.drawable.location_botanic,
            isVisited   = true,
            visitSeason = "Spring"
        ),

        Location(
            name        = "Grampians National Park",
            city        = "Halls Gap, Victoria",
            lastVisit   = "July 2023",
            rating      = 4.0f,
            description = "Known to the Djab wurrung and Jardwadjali people as Gariwerd, " +
                          "the Grampians features rugged sandstone peaks, ancient Aboriginal " +
                          "rock art sites, and diverse wildlife. The MacKenzie Falls hike is " +
                          "unmissable, and the Pinnacle lookout rewards you with panoramic " +
                          "views across the western plains.",
            imageResId  = R.drawable.location_grampians,
            isVisited   = true,
            visitSeason = "Winter"
        ),

        Location(
            name        = "Phillip Island",
            city        = "Phillip Island, Victoria",
            lastVisit   = "November 2024",
            rating      = 4.5f,
            description = "Phillip Island is world-famous for its nightly Penguin Parade, " +
                          "where hundreds of little penguins waddle ashore after a day at " +
                          "sea. The island also offers surf beaches, koala conservation " +
                          "centres, and a MotoGP racing circuit. A perfect getaway for " +
                          "families and nature lovers just 90 minutes from Melbourne.",
            imageResId  = R.drawable.location_phillip,
            isVisited   = false,
            visitSeason = "Summer"
        )
    )
}
