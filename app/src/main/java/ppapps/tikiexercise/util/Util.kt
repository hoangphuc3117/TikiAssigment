package ppapps.tikiexercise.util

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.util.*
import com.google.gson.JsonParser
import com.google.gson.JsonElement
import org.json.JSONException
import org.json.JSONArray
import kotlin.collections.ArrayList


/**
 * Function to check keyword contains 1 word or >= 2 words
 */
fun String.isKeywordAWord(): Boolean {
    val kw = this.trim()
    return !kw.contains(" ")
}

/**
 * Function to split a keyword into 2 lines if it has greater than 2 words
 */
fun String.splitKeywordTo2Lines(): String {
    val kw = this.trim()
    //If keyword has a word or it contains "\n", do nothing
    if (kw.isKeywordAWord() || kw.contains("\n")) {
        return kw
    } else {
        val length = kw.length
        val leftPos = length / 2
        val rightPos = kw.length / 2
        var spaceCharacterPos = -1
        for (i in 0..kw.length / 2) {
            val leftCharacter = kw[leftPos - i]
            val rightCharacter = kw[rightPos + i]
            if (rightCharacter.equals(' ')) {
                spaceCharacterPos = rightPos + i
                break

            }
            if (leftCharacter.equals(' ')) {
                spaceCharacterPos = leftPos - i
                break
            }
        }
        val result = kw.replaceRange(spaceCharacterPos, spaceCharacterPos+1, "\n")
        return result
    }
}

object Util{
    fun randomColor():Int{
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    fun parseJsonStringToArray(json : String): ArrayList<String>?{
        try {
            val result = ArrayList<String>()
            val itemArray = JSONArray(json)
            for (i in 0 until itemArray.length()) {
                val value = itemArray.getString(i)
                result.add(value)
            }
            return result
        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }
    }
}