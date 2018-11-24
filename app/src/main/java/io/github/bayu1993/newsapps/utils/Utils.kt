package io.github.bayu1993.newsapps.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Bayu teguh pamuji on 11/23/18.
 * email : bayuteguhpamuji@gmail.com.
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun dateFormat(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MM yyyy", Locale.ENGLISH).format(this)
}