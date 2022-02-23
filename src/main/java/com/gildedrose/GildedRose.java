package com.gildedrose;

class GildedRose {

    static final int MIN_QUALITY = 0;
    static final int MAX_QUALITY = 50;
    static final int QUALITY_CHANGE = 1;

    Item[] items;

    public GildedRose(Item[] items) {
        ensureQualityBoundaries(items);
        ensureSellInStatus(items);
        this.items = items;
    }

    private void ensureQualityBoundaries(Item[] items) {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (item.quality < MIN_QUALITY) item.quality = MIN_QUALITY;
            if (item.quality > MAX_QUALITY) item.quality = MAX_QUALITY;
        }
    }

    private void ensureSellInStatus(Item[] items) {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (isSulfuras(item)) item.sellIn = -1;
        }
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            final Item item = items[i];

            int qualityChange = isConjured(item) ? 2 * QUALITY_CHANGE : QUALITY_CHANGE;

            if (!isAgedBrie(item) && !isBackstagePass(item)) {
                if (item.quality > MIN_QUALITY) {
                    if (!isSulfuras(item)) {
                        item.quality -= qualityChange;
                    }
                }
            } else {
                if (item.quality < MAX_QUALITY) {
                    item.quality += qualityChange;

                    if (isBackstagePass(item)) {
                        if (item.sellIn < 11) {
                            if (item.quality < MAX_QUALITY) {
                                item.quality += qualityChange;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < MAX_QUALITY) {
                                item.quality += qualityChange;
                            }
                        }
                    }
                }
            }

            if (!isSulfuras(item)) {
                item.sellIn -= 1;
            }

            if (item.sellIn < 0) {
                if (!isAgedBrie(item)) {
                    if (!isBackstagePass(item)) {
                        if (item.quality > MIN_QUALITY) {
                            if (!isSulfuras(item)) {
                                item.quality -= qualityChange;
                            }
                        }
                    } else {
                        item.quality -= item.quality;
                    }
                } else {
                    if (item.quality < MAX_QUALITY) {
                        item.quality += qualityChange;
                    }
                }
            }
        }
    }

    private boolean isSulfuras(Item item) {
        return match(item.name, "sulfuras");
    }

    private boolean isBackstagePass(Item item) {
        return match(item.name, "backstage pass");
    }

    private boolean isAgedBrie(Item item) {
        return match(item.name, "aged brie");
    }

    private boolean isConjured(Item item) {
        return match(item.name, "conjured");
    }

    private boolean match(String name, String pattern) {
        return name.toLowerCase().startsWith(pattern);
    }
}