package com.gildedrose

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {


  @Unroll
  void "update normal item day #day"() {
    given:
    String name = "+5 Dexterity Vest"
    int initialSellIn = 10
    int initialQuality = 20
    GildedRose app = new GildedRose([new Item(name, initialSellIn, initialQuality)] as Item[])

    when:
    day.times { app.updateQuality() }

    then:
    app.items[0].name == name
    app.items[0].quality == quality
    app.items[0].sellIn == sellIn

    where:
    day | sellIn | quality
    0   | 10     | 20
    1   | 9      | 19
    2   | 8      | 18
    3   | 7      | 17
    4   | 6      | 16
    5   | 5      | 15
    6   | 4      | 14
    7   | 3      | 13
    8   | 2      | 12
    9   | 1      | 11
    10  | 0      | 10
    11  | -1     | 8
    12  | -2     | 6
  }

  @Unroll
  void "update Aged Brie day #day"() {
    given:
    String name = "Aged Brie"
    int initialSellIn = 10
    int initialQuality = 20
    GildedRose app = new GildedRose([new Item(name, initialSellIn, initialQuality)] as Item[])

    when:
    day.times { app.updateQuality() }

    then:
    app.items[0].name == name
    app.items[0].quality == quality
    app.items[0].sellIn == sellIn

    where:
    day | sellIn | quality
    0   | 10     | 20
    1   | 9      | 21
    2   | 8      | 22
    3   | 7      | 23
    4   | 6      | 24
    5   | 5      | 25
    6   | 4      | 26
    7   | 3      | 27
    8   | 2      | 28
    9   | 1      | 29
    10  | 0      | 30
    11  | -1     | 32
    12  | -2     | 34
  }

//    @Unroll
//    def "should update quality correctly #name "() {
//
//        given: "some items"
//        Item[] items = [new Item(name, sellIn, quality)]
//
//        and: "the application with these items"
//        GildedRose app = new GildedRose(items)
//
//        when: "updating quality"
//        List<Item> updates = []
//        for (int days: expectedSellIn) {
//          app.updateQuality()
//          Item updatedItem = app.items[0]
//          Item savedItem = new Item(updatedItem.name, updatedItem.sellIn, updatedItem.quality)
//          updates.add(savedItem)
//        }
//
//        then: "the quality is correct for each day"
//        updates*.name.unique() == [name]
//        updates*.sellIn == expectedSellIn
//        updates*.quality == expectedQuality
//
//        where:
//
//        name | sellIn | quality || expectedSellIn | expectedQuality
//        // quality decreases faster after sellIn == 0
//        "+5 Dexterity Vest" | 5 | 20 || 4..-2 | [19,18,17,16, 15, 13,11]
//        "Aged Brie" | 2 | 0 || 1..-5 | [1,2,4,6,8,10,12]
//        "Elixir of the Mongoose" | 5 | 7 || 4..-2 | [6,5,4,3,2,0,0]
//        "Sulfuras, Hand of Ragnaros" | 2 | 80 || 1..-4 | []

//        new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
//        new Item("Sulfuras, Hand of Ragnaros", -1, 80),
//        new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
//        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
//        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
//        // this conjured item does not work properly yet
//        new Item("Conjured Mana Cake", 3, 6) };
//    }

}
