package com.example.conference.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TransferUtilTest {

    @Test
    fun `should be able to transfer String to the Duration`() {
        val string1 = "60min"
        Assertions.assertEquals(TransferUtil.transferStringToDuration(string1).toMinutes(), 60)
    }
    @Test
    fun `should be able to transfer legal String to the Talk`() {
        val string = "Writing Fast Tests Against Enterprise Rails 60min"
        Assertions.assertNotNull(TransferUtil.transferStringToTalk(string))
    }
    @Test
    fun `should be able to transfer StringList to the TalkList`() {
        val stringList = mutableListOf("Writing Fast Tests Against Enterprise Rails 60min")
        val talkList = TransferUtil.transferStringListToTalkList(stringList)

        Assertions.assertEquals(1, talkList.size)
        Assertions.assertEquals(60, talkList[0].duration.toMinutes())
        Assertions.assertEquals("Writing Fast Tests Against Enterprise Rails", talkList[0].name)
    }
}
