package com.gildedrose

import org.w3c.dom.Text
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {


  void "compare text file"() {

    when:
    TexttestFixture.main(["20"] as String[])

  }



}
