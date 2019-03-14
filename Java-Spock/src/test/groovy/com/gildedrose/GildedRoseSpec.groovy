package com.gildedrose


import spock.lang.Specification

import java.nio.charset.StandardCharsets

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
