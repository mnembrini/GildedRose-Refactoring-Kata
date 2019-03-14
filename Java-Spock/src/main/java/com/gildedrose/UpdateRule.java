package com.gildedrose;

import java.util.function.Function;

public class UpdateRule {

    String namePrefix = "";
    Function<Item, Integer> updateQuality;
    Function<Item, Integer> updateSellIn = (item) -> item.sellIn - 1;
    int maxQuality = 50;
    int minQuality = 0;

    public UpdateRule(Function<Item, Integer> updateQuality) {
        this.updateQuality = updateQuality;
    }

    public UpdateRule(String namePrefix, Function<Item, Integer> updateQuality) {
        this.namePrefix = namePrefix;
        this.updateQuality = updateQuality;
    }

    public UpdateRule(String namePrefix, Function<Item, Integer> updateQuality, Function<Item, Integer> updateSellIn) {
        this.namePrefix = namePrefix;
        this.updateQuality = updateQuality;
        this.updateSellIn = updateSellIn;
    }

    boolean applies(String name) {
        return name.startsWith(namePrefix);
    }

    void apply(Item item) {
        int oldQuality = item.quality;
        int quality = updateQuality.apply(item);

        if (item.sellIn <= 0) {
            // quality changes twice as fast
            quality += (quality - oldQuality);
        }
        item.quality = clamp(quality, minQuality, maxQuality);

        item.sellIn = updateSellIn.apply(item);
    }

    private int clamp(int value, int min, int max) {
        if (value > max) {
            return max;
        }
        if (value < min) {
            return min;
        }
        return value;
    }
}
