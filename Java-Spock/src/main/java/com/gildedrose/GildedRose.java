package com.gildedrose;

import java.util.List;

class GildedRose {
    Item[] items;

    List<UpdateRule> rules;

    public GildedRose(Item[] items) {
        this.items = items;
        UpdateRule defaultRule = new UpdateRule((item -> item.quality - 1));
        UpdateRule agedBrie = new UpdateRule("Aged Brie", item -> item.quality + 1);
        UpdateRule backstagePasses = new UpdateRule("Backstage passes", item -> {
            if (item.sellIn > 10) {
                return item.quality + 1;
            }
            if (item.sellIn > 5) {
                return item.quality + 2;
            }
            if (item.sellIn > 0) {
                return item.quality + 3;
            } else {
                return 0;
            }
        });
        UpdateRule sulfuras = new UpdateRule("Sulfuras", item -> item.quality, item -> item.sellIn);
        sulfuras.maxQuality = Integer.MAX_VALUE;

        this.rules = List.of(
                agedBrie,
                backstagePasses,
                sulfuras,
                defaultRule
        );
    }

    public void updateQuality() {
        for (Item item : items) {
            UpdateRule updateRule = rules.stream().filter(it -> it.applies(item.name))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Could not find rule for item " + item));
            updateRule.apply(item);
        }
    }

    private void updateQuality(Item item) {


        if (item.quality > 50) {
            item.quality = 50;
        }

        if (!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality > 0) {
                if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        }

        if (item.sellIn < 0) {
            if (!item.name.equals("Aged Brie")) {
                if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.quality > 0) {
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }

}
