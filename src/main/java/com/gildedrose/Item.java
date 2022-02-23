package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

   public static boolean isSulfuras(Item item) {
       return match(item.name, "sulfuras");
   }

   public static boolean isBackstagePass(Item item) {
       return match(item.name, "backstage pass");
   }

   public static boolean isAgedBrie(Item item) {
       return match(item.name, "aged brie");
   }

   public static boolean isConjured(Item item) {
       return match(item.name, "conjured");
   }

   private static boolean match(String name, String pattern) {
       return name.toLowerCase().startsWith(pattern);
   }
}
