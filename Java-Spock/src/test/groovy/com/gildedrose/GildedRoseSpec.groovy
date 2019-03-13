package com.gildedrose

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {



    @Unroll
    def "should update quality correctly"() {

        given: "some items"
        Item[] items = [new Item(name, sellIn, quality)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        List<Item> updates = []
        for (int days: expectedSellIn) {
          app.updateQuality()
          Item updatedItem = app.items[0]
          Item savedItem = new Item()
          updates.add(new Item(app.items[0]))
        }

        then: "the quality is correct"
        app.items[0].name == name
        app.items[0].sellIn == name
        app.items[0].name == name

        where:

        name | sellIn | quality || expectedSellIn | expectedQuality
        "+5 Dexterity Vest" | 5 | 20 || 4..-2 | 19..13
//        new Item("Aged Brie", 2, 0), //
//        new Item("Elixir of the Mongoose", 5, 7), //
//        new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
//        new Item("Sulfuras, Hand of Ragnaros", -1, 80),
//        new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
//        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
//        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
//        // this conjured item does not work properly yet
//        new Item("Conjured Mana Cake", 3, 6) };
    }

}
