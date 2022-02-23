package com.gildedrose;

public class GildedRoseExample {

    GildedRose app;

    public GildedRoseExample(String name, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        app = new GildedRose(items);
    }

    public void update() {
        app.updateQuality();
    }

    public Item item() {
        return app.items[0];
    }

    public int quality() {
        return item().quality;
    }

    public int sellIn() {
        return item().sellIn;
    }
}