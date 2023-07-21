package com.example.moviescope.util

import android.app.Activity
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviescope.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale


fun ImageView.loadImage(url: String?) {
    val placeholder = R.drawable.gray_placeholder
    url?.let {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions())
            .error(placeholder)
            .into(this)
    } ?: kotlin.run {
        this.setImageResource(placeholder)
    }
}

fun getReformatDate(dateInString: String?): String {
    return if (dateInString != null) {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.US)
        try {
            val date = parser.parse(dateInString)
            formatter.format(date!!)
        } catch (e: ParseException) {
            "-"
        }
    } else "-"
}

fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run {
        navigate(direction)
    }
}

fun Activity.showMotionToast(title : String, description : String, motionStyle : MotionToastStyle) {
    MotionToast.darkColorToast(
        this,
        title,
        description,
        motionStyle,
        MotionToast.GRAVITY_TOP,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}