package com.android.dagger2

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.media.ExifInterface
import android.net.Uri
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.gson.JsonArray
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

private val arrayOfSuffixes = arrayOf("k", "M")
private val duration: Long = 300

fun Context.saveToSharedPreference(@StringRes res: Int, value: Any) {
    val editor = getPrefs(this).edit()
    val key = getString(res)
    when (value) {
        is String -> editor.putString(key, value).apply()
        is Int -> editor.putInt(key, value).apply()
        is Boolean -> editor.putBoolean(key, value).apply()
        is Long -> editor.putLong(key, value).apply()
        else -> error("Not supported value set into preferences")
    }
}

fun Context.hideKeyboard(view: View?) {
    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Context.getIntPreference(key: String): Int {
    val prefs = getPrefs(this)
    return prefs.getInt(key, -1)
}

fun Context.getStringPreference(@StringRes key: Int): String? {
    val value = getString(key)
    val prefs = getPrefs(this)
    return prefs.getString(value, null)
}

fun Context.getBooleanPreference(@StringRes key: Int): Boolean {
    val prefs = getPrefs(this)
    return prefs.getBoolean(getString(key), false)
}

fun Context.getBooleanPreference(@StringRes key: Int, defaultValue: Boolean): Boolean {
    val prefs = getPrefs(this)
    return prefs.getBoolean(getString(key), defaultValue)
}

fun Context.getLongPreference(key: String): Long {
    val prefs = getPrefs(this)
    return prefs.getLong(key, 0)
}

fun Context.clearPrefsProperty(key: String) {
    getPrefs(this).edit().remove(key).apply()
}

fun Context.isNetworkAvailable(): Boolean {
    val info = connectivityManager.activeNetworkInfo
    return info != null && info.isConnected && !info.isRoaming
}

private fun getPrefs(context: Context): SharedPreferences {
    val prefsName = context.getString(R.string.preference_file)
    return context.getSharedPreferences(prefsName, MODE_PRIVATE)
}

fun Context.containsPref(key: String): Boolean {
    return getPrefs(this).contains(key)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun EditText.setEnterButtonAction(action: () -> Unit) {
    setOnKeyListener { _, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) action()
        return@setOnKeyListener false
    }
}

fun JsonObject?.getDouble(key: String): Double? {
    return if (this == null || this.get(key) == null || this.get(key) == JsonNull.INSTANCE) null else this.get(key).asDouble
}

fun JsonObject?.getString(key: String): String {
    return if (this == null || this.get(key) == null || this.get(key) == JsonNull.INSTANCE) "" else this.get(key).asString
}

fun JsonObject?.getJsonObject(key: String): JsonObject? {
    return if (this == null || this.get(key) == null || this.get(key) == JsonNull.INSTANCE) null else this.get(key).asJsonObject
}

fun JsonObject?.getJsonArray(key: String): JsonArray? {
    return if (this == null || this.get(key) == null || this.get(key) == JsonNull.INSTANCE) null else this.get(key).asJsonArray
}

fun JsonObject?.getInt(key: String): Int? {
    return if (this == null || this.get(key) == null || this.get(key) == JsonNull.INSTANCE) null else this.get(key).asInt
}

fun JsonObject?.getLong(key: String): Long? {
    return if (this == null || this.get(key) == null || this.get(key) == JsonNull.INSTANCE) null else this.get(key).asLong
}

fun JsonObject?.getBoolean(key: String): Boolean {
    return if (this == null || this.get(key) == null || this.get(key) == JsonNull.INSTANCE) false else this.get(key).asBoolean
}

inline fun <reified T : Activity> Context.enterActivityWithStackClearance() {
    startActivity(intentFor<T>().clearTask().newTask().clearTop())
}

//fun Context.showSnackbar(parent: View, message: Int) {
//    showIntSnackBar(this, parent, message)
//}
//
//fun Context.showSnackbar(parent: View, message: String) {
//    showStringSnackBar(this, parent, message)
//}
//
//fun Fragment.showSnackbar(parent: View, message: Int) {
//    showIntSnackBar(activity, parent, message)
//}
//
//fun Fragment.showSnackbar(parent: View, message: String) {
//    showStringSnackBar(activity, parent, message)
//}

//private fun showStringSnackBar(context: Context, parent: View, message: String) {
//    val color = ContextCompat.getColor(context, android.R.color.white)
//    val snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_LONG)
//    snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.color_pink))
//    val textView = snackbar.view.find<TextView>(android.support.design.R.id.snackbar_text)
//    textView.setTextColor(color)
//    snackbar.show()
//}
//
//private fun showIntSnackBar(context: Context, parent: View, message: Int) {
//    val color = ContextCompat.getColor(context, android.R.color.white)
//    val snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_LONG)
//    snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.color_pink))
//    val textView = snackbar.view.find<TextView>(android.support.design.R.id.snackbar_text)
//    textView.setTextColor(color)
//    snackbar.show()
//}

private fun getOrientation(path: String?): Int {

    val ei = ExifInterface(path)
    val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

    return orientation
}

fun Date?.toBirthdayDate(): String {
    val simpleDateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
}

fun Date.isYesterday(): Boolean {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()
    calendar.add(Calendar.DAY_OF_MONTH, -1)

    val thenYear = calendar.get(Calendar.YEAR)
    val thenMonth = calendar.get(Calendar.MONTH)
    val thenMonthDay = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.timeInMillis = time

    return thenYear == calendar.get(Calendar.YEAR)
            && thenMonth == calendar.get(Calendar.MONTH)
            && thenMonthDay == calendar.get(Calendar.DATE)
}

fun View.dimView(show: Boolean) {
    isEnabled = show
    ViewCompat.animate(this)
            .alpha(if (show) 1F else 0F)
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : ViewPropertyAnimatorListener {
                override fun onAnimationEnd(view: View) {
                    view.visibility = if (show) View.VISIBLE else View.GONE
                }

                override fun onAnimationCancel(view: View) {
                    view.visibility = View.GONE
                }

                override fun onAnimationStart(view: View) {
                    view.visibility = View.VISIBLE
                }

            })
            .withLayer()
            .start()
}

fun Fragment.dialCall(number: String): Boolean = activity.dialCall(number)
fun Context.dialCall(number: String): Boolean {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(intent)
        return true
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
}

inline fun SpannableStringBuilder.withSpan(span: Any, action: SpannableStringBuilder.() -> Unit): SpannableStringBuilder {
    val from = length
    action()
    setSpan(span, from, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

val Activity.app: MyApp
get() = application as MyApp