package com.tunc.wayfolk.multilanguage.language

import com.tunc.wayfolk.multilanguage.Strings
import com.tunc.wayfolk.multilanguage.common_no_internet
import com.tunc.wayfolk.multilanguage.home_empty_description
import com.tunc.wayfolk.multilanguage.home_empty_title
import com.tunc.wayfolk.multilanguage.home_greeting
import com.tunc.wayfolk.multilanguage.home_new_posts
import com.tunc.wayfolk.multilanguage.home_stories_title
import com.tunc.wayfolk.multilanguage.home_story_image_description
import com.tunc.wayfolk.multilanguage.home_tab_title
import com.tunc.wayfolk.multilanguage.home_title
import com.tunc.wayfolk.multilanguage.language_english
import com.tunc.wayfolk.multilanguage.language_system
import com.tunc.wayfolk.multilanguage.language_turkish

object WayFolkStrings {

    object Common {
        val noInternet = WayFolkStringResource(Strings.string.common_no_internet)
    }

    object Language {
        val system = WayFolkStringResource(Strings.string.language_system)
        val english = WayFolkStringResource(Strings.string.language_english)
        val turkish = WayFolkStringResource(Strings.string.language_turkish)
    }

    object Home {
        val tabTitle = WayFolkStringResource(Strings.string.home_tab_title)
        val title = WayFolkStringResource(Strings.string.home_title)
        val greeting = WayFolkStringResource(Strings.string.home_greeting)
        val storiesTitle = WayFolkStringResource(Strings.string.home_stories_title)
        val storyImageDescription = WayFolkStringResource(Strings.string.home_story_image_description)
        val emptyTitle = WayFolkStringResource(Strings.string.home_empty_title)
        val emptyDescription = WayFolkStringResource(Strings.string.home_empty_description)
        val newPosts = WayFolkPluralResource(Strings.plurals.home_new_posts)
    }
}
