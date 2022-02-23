package com.gildedrose;

class GildedRose {

    static final int MIN_QUALITY = 0;
    static final int MAX_QUALITY = 50;
    static final int QUALITY_CHANGE = 1;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = ensureQualityBoundaries(items);
    }

    private Item[] ensureQualityBoundaries(Item[] items) {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (item.quality < MIN_QUALITY) item.quality = MIN_QUALITY;
            if (item.quality > MAX_QUALITY) item.quality = MAX_QUALITY;
        }

        return items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > MIN_QUALITY) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - QUALITY_CHANGE;
                    }
                }
            } else {
                if (items[i].quality < MAX_QUALITY) {
                    items[i].quality = items[i].quality + QUALITY_CHANGE;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < MAX_QUALITY) {
                                items[i].quality = items[i].quality + QUALITY_CHANGE;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < MAX_QUALITY) {
                                items[i].quality = items[i].quality + QUALITY_CHANGE;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > MIN_QUALITY) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - QUALITY_CHANGE;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < MAX_QUALITY) {
                        items[i].quality = items[i].quality + QUALITY_CHANGE;
                    }
                }
            }
        }
    }
}