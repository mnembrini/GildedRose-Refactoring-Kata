package com.gildedrose

import org.w3c.dom.Text
import spock.lang.Specification
import spock.lang.Unroll

import java.nio.charset.StandardCharsets
import java.nio.file.Files

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {


  void "compare text file"() {

    when:
    String progress = TexttestFixture.progressFor(20)

    then:
    InputStream inputStream = getClass().getResourceAsStream("/test20.txt")
    String expected = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8)
    progress == expected

  }



}
