package com.example.conference.util

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TransferUtilTest {

    @Test
    fun `should be able to transfer String to the Duration`() {
        val string1 = "60min"
        assertEquals(TransferUtil.transferStringToDuration(string1).toMinutes(), 60)
    }
    @Test
    fun `should be able to transfer legal String to the Talk`() {
        val string = "Writing Fast Tests Against Enterprise Rails 60min"
        assertNotNull(TransferUtil.transferStringToTalk(string))
    }
    @Test
    fun `should be able to transfer StringList to the TalkList`() {
        val stringList = mutableListOf("Writing Fast Tests Against Enterprise Rails 60min")
        val talkList = TransferUtil.transferStringListToTalkList(stringList)

        assertEquals(1, talkList.size)
        assertEquals(60, talkList[0].duration.toMinutes())
        assertEquals("Writing Fast Tests Against Enterprise Rails", talkList[0].name)
    }
}
