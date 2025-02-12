package com.project.sportify.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.navigation.NavController
import com.project.sportify.data.models.countrycode.CountryCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date

fun String?.alternate(alt: String = Constants.General.DASH_TEXT): String {
    return if (!this?.trim().isNullOrEmpty()) this?.trim().toString() else alt
}

fun List<CountryCode>.searchCountryList(key: String): MutableList<CountryCode> {
    val tempList = mutableListOf<CountryCode>()
    this.forEach {
        if (it.countryName?.lowercase()?.contains(key.lowercase()) == true) {
            tempList.add(it)
        }
    }
    return tempList
}


fun String.isLongEnough() = length >= 8
fun String.hasEnoughDigits() = count(Char::isDigit) > 0
fun String.isMixedCase() = any(Char::isLowerCase) && any(Char::isUpperCase)
fun String.hasSpecialChar() = any { it in "!@#$%^&*()-_=+" }


private val requirements = listOf(
    String::isLongEnough,
    String::hasEnoughDigits,
    String::hasSpecialChar,
    String::isMixedCase
)
val String.meetsRequirements get() = requirements.all { check -> check(this) }


fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}


fun <T1, T2, T3, R> Flow<T1>.zip(
    secondFlow: Flow<T2>,
    thirdFlow: Flow<T3>,
    transform: (T1, T2, T3) -> R
): Flow<R> {
    return this.zip(secondFlow) { flow1, flow2 -> Pair(flow1, flow2) }
        .zip(thirdFlow) { (flow1, flow2), flow3 ->
            transform(flow1, flow2, flow3)
        }
}


fun <T> NavController.navigateWithResult(key: String, value: T) {
    popBackStack()
    currentBackStackEntry
        ?.savedStateHandle
        ?.set(key, value)
}

fun <T> NavController.navigateToWithResult(deepLink: String, key: String, value: T) {
    navigate(deepLink) {
//        popUpTo(NavigationScreen.BottomNavItem.Home.route) {
//            inclusive = true
//        }
        launchSingleTop = true
    }
    currentBackStackEntry
        ?.savedStateHandle
        ?.set(key, value)


}

fun String?.convertToBitmap(): Bitmap? {
    return if (!isNullOrEmpty()) {
        val decodedString: ByteArray = Base64.decode(this, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    } else {
        null
    }
}

fun Double.toTwoDecimalString(): String {
    val value = DecimalFormat("#.##")
    return value.format(this)
}

fun String.isValidDoubleFormat(): Boolean {
    // Ensure it's not empty
    if (this.isEmpty()) return false

    // Ensure proper number format using regex
    val doublePattern = Regex("^-?\\d*(\\.\\d+)?$")

    return this.matches(doublePattern)
}
fun String.formatDoubleWithCommas(): String {
    if(this.endsWith(",")) return this
    if(this.startsWith(",")) return this
    if (this.isEmpty() || this == "." || this == "-" || this == "-.") return this

    if (this.matches(Regex("^-?\\d+\\.$"))) return this

    if (this.matches(Regex("^-?\\d+\\.\\d*$"))) return this
    val cleanString = if (this.startsWith(",")) {
        "," + this.drop(1).replace(",", "").trim()
    } else {
        this.replace(",", "").trim()
    }
    if (cleanString.isEmpty()) return ""
    if (cleanString.count { it == '.' } > 1) return this

    return try {
        val bigDecimal = BigDecimal(cleanString).setScale(4, RoundingMode.HALF_UP) // Round to 4 decimal places
        val formatter = DecimalFormat("#,###.####")
        formatter.isParseBigDecimal = true
        formatter.format(bigDecimal)
    } catch (e: NumberFormatException) {
        this
    }

}

/*fun String.formatDoubleWithCommas(): String {
    val cleanString = this.replace(",", "").trim()
    if (cleanString.isEmpty()) return ""
    if (cleanString.count { it == '.' } > 1) return this

    return try {
        val double = cleanString.toDouble()
        val formatter = DecimalFormat("#,###.###")
        formatter.format(double)
    } catch (e: NumberFormatException) {
        this
    }
}*/

fun String.convertToDouble(): Double {
    if (this.isEmpty()) {
        return 0.0
    }
    val sanitizedInput = this.replace(",", "").replace("-","")
    val finalInput = sanitizedInput.replace(Regex("\\.\\.+"), ".")
    return finalInput.toDoubleOrNull() ?: 0.0
}

fun String.hideIBAN(): String{
    if(this.length == 29){
        return this.replaceRange(10,19,"XXXXXXXXX")
    }
    return this
}

fun String.hideAccountNumber(): String{
    if(this.length > 4){
        val middle = this.length / 2

        return this.replaceRange(middle-1, middle+1, "XXX")
    }
    return this
}

fun String.toBase64Encoded(): String{
    return Base64.encodeToString(this.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
}

@SuppressLint("DefaultLocale")
fun String.toShortenedFormat(): String {
    return try {
        val number = this.toLong()
        when {
            number >= 1_000_000_000 -> String.format("%.1fB", number / 1_000_000_000.0)
            number >= 1_000_000 -> String.format("%.1fM", number / 1_000_000.0)
            number >= 1_000 -> String.format("%.1fK", number / 1_000.0)
            else -> number.toString()
        }
    } catch (e: NumberFormatException) {
        this
    }
}

fun <T> ((T) -> Unit).singleClickWrapper(delay: Long = 1000L): (T) -> Unit {
    var lastClickTime = 0L
    return { param: T ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > delay) {
            lastClickTime = currentTime
            this(param)
        }
    }
}

fun (() -> Unit).singleClickWrapperNonParams(delay: Long = 1000L): () -> Unit {
    var lastClickTime = 0L
    return {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > delay) {
            lastClickTime = currentTime
            this()
        }
    }
}