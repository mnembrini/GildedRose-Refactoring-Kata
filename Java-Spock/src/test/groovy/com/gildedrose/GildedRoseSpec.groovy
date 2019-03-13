package com.gildedrose

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {



    @Unroll
    def "should update quality correctly #name "() {

        given: "some items"
        Item[] items = [new Item(name, sellIn, quality)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        List<Item> updates = []
        for (int days: expectedSellIn) {
          app.updateQuality()
          Item updatedItem = app.items[0]
          Item savedItem = new Item(updatedItem.name, updatedItem.sellIn, updatedItem.quality)
          updates.add(savedItem)
        }

        then: "the quality is correct for each day"
        updates*.name.unique() == [name]
        updates*.sellIn == expectedSellIn
        updates*.quality == expectedQuality

        where:

        name | sellIn | quality || expectedSellIn | expectedQuality
        // quality decreases faster after sellIn == 0
        "+5 Dexterity Vest" | 5 | 20 || 4..-2 | [19,18,17,16, 15, 13,11]
        "Aged Brie" | 2 | 0 || 1..-5 | [1,2,4,6,8,10,12]
        "Elixir of the Mongoose" | 5 | 7 || 4..-2 | [6,5,4,3,2,0,0]
        "Sulfuras, Hand of Ragnaros" | 2 | 80 || 1..-4 | []

//        new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
//        new Item("Sulfuras, Hand of Ragnaros", -1, 80),
//        new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
//        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
//        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
//        // this conjured item does not work properly yet
//        new Item("Conjured Mana Cake", 3, 6) };
    }

}
