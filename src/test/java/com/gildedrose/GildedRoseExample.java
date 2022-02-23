package com.gildedrose;

public class GildedRoseExample {

    public static GildedRose build() {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Crap with zero quality", 10, 0),
            new Item("Aged Brie", 10, 20),
            new Item("Aged Brie with min quality", 2, 0),
            new Item("Aged Brie with max quality", 2, 50),
            new Item("Sulfuras, Hand of Ragnaros", -1, 50),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)
        };

        return new GildedRose(items);
    }
}