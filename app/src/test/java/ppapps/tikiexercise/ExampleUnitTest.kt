package ppapps.tikiexercise

import org.junit.Test

import org.junit.Assert.*
import ppapps.tikiexercise.util.isKeywordAWord
import ppapps.tikiexercise.util.splitKeywordTo2Lines

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilTest {
    @Test
    fun testMethodSplitKeyword_1() {
        val result = "kem chống nắng".splitKeywordTo2Lines()
        assertEquals("kem chống\nnắng", result)
    }

    @Test
    fun testMethodSplitKeyword_2() {
        val result = "Balo".splitKeywordTo2Lines()
        assertEquals("Balo", result)
    }

    @Test
    fun testMethodIsKeywordAWord_1(){
        val result = "Keyword".isKeywordAWord()
        assertEquals(true, result)
    }

    @Test
    fun testMethodIsKeywordAWord_2(){
        val result = "Bitis   Hunter".isKeywordAWord()
        assertEquals(false, result)
    }

    @Test
    fun testMethodIsKeywordAWord_3(){
        val result = "Bitis Hunter   x".isKeywordAWord()
        assertEquals(false, result)
    }
}
