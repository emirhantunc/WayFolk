package com.tunc.wayfolk.multilanguage.language

import com.tunc.wayfolk.multilanguage.Strings
import com.tunc.wayfolk.multilanguage.common_thousands_separator
import com.tunc.wayfolk.multilanguage.home_top_bar_title
import com.tunc.wayfolk.multilanguage.home_your_story
import com.tunc.wayfolk.multilanguage.home_cd_notifications
import com.tunc.wayfolk.multilanguage.home_cd_messages
import com.tunc.wayfolk.multilanguage.home_cd_like
import com.tunc.wayfolk.multilanguage.home_cd_unlike
import com.tunc.wayfolk.multilanguage.home_cd_comment
import com.tunc.wayfolk.multilanguage.home_cd_share
import com.tunc.wayfolk.multilanguage.home_cd_save
import com.tunc.wayfolk.multilanguage.home_cd_more_options
import com.tunc.wayfolk.multilanguage.home_cd_story
import com.tunc.wayfolk.multilanguage.home_cd_post_image
import com.tunc.wayfolk.multilanguage.home_time_just_now
import com.tunc.wayfolk.multilanguage.home_post_likes
import com.tunc.wayfolk.multilanguage.home_post_view_comments
import com.tunc.wayfolk.multilanguage.home_time_minutes_ago
import com.tunc.wayfolk.multilanguage.home_time_hours_ago
import com.tunc.wayfolk.multilanguage.home_time_days_ago
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
        val thousandsSeparator = WayFolkStringResource(Strings.string.common_thousands_separator)
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
        val topBarTitle = WayFolkStringResource(Strings.string.home_top_bar_title)
        val yourStory = WayFolkStringResource(Strings.string.home_your_story)
        val notificationsDescription = WayFolkStringResource(Strings.string.home_cd_notifications)
        val messagesDescription = WayFolkStringResource(Strings.string.home_cd_messages)
        val likeDescription = WayFolkStringResource(Strings.string.home_cd_like)
        val unlikeDescription = WayFolkStringResource(Strings.string.home_cd_unlike)
        val commentDescription = WayFolkStringResource(Strings.string.home_cd_comment)
        val shareDescription = WayFolkStringResource(Strings.string.home_cd_share)
        val saveDescription = WayFolkStringResource(Strings.string.home_cd_save)
        val moreOptionsDescription = WayFolkStringResource(Strings.string.home_cd_more_options)
        val storyDescription = WayFolkStringResource(Strings.string.home_cd_story)
        val postImageDescription = WayFolkStringResource(Strings.string.home_cd_post_image)
        val timeJustNow = WayFolkStringResource(Strings.string.home_time_just_now)
        val postLikes = WayFolkPluralResource(Strings.plurals.home_post_likes)
        val postViewComments = WayFolkPluralResource(Strings.plurals.home_post_view_comments)
        val timeMinutesAgo = WayFolkPluralResource(Strings.plurals.home_time_minutes_ago)
        val timeHoursAgo = WayFolkPluralResource(Strings.plurals.home_time_hours_ago)
        val timeDaysAgo = WayFolkPluralResource(Strings.plurals.home_time_days_ago)
    }
}
