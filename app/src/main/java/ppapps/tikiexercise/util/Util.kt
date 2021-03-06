package ppapps.tikiexercise.util

import android.graphics.Color
import java.util.*
import org.json.JSONException
import org.json.JSONArray
import ppapps.tikiexercise.model.SrUserModel
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
        //Find space that is near the center of string, then replace this space with \n
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

    /**
     * Function to parse json array without key
     */
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

    fun parseJsonStringToArraySrUser(json : String): ArrayList<SrUserModel>?{
        try {
            val result = ArrayList<SrUserModel>()
            val itemArray = JSONArray(json)
            for (i in 0 until itemArray.length()) {
                val jsonObject = itemArray.getJSONObject(i)
                val title = jsonObject.getString("full_name")
                val avatar: String = jsonObject.getString("html_url")
                val description: String = jsonObject.getString("description")
                val srUser: SrUserModel= SrUserModel(title)
                srUser.avatar = avatar
                srUser.detailDescription = description
                result.add(srUser)
            }
            return result
        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }
    }
}