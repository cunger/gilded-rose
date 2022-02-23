package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.GildedRose.MAX_QUALITY;
import static com.gildedrose.GildedRose.MIN_QUALITY;
import static org.assertj.core.api.Assertions.*;

class GildedRoseTest {

    @Test
    void itemsWithNegativeQualityAreSetToMinQuality() {
        GildedRoseExample example = new GildedRoseExample("Junk", 2, MIN_QUALITY - 1);

        assertThat(example.quality()).isEqualTo(MIN_QUALITY);
    }

    @Test
    void itemsWithTooHighQualityAreSetToMaxQuality() {
        GildedRoseExample example = new GildedRoseExample("All-in-one problem solver", 2, MAX_QUALITY + 1);

        assertThat(example.quality()).isEqualTo(MAX_QUALITY);
    }

    @Test
    void sellDateGetsCloserWithEachUpdate() {
        GildedRoseExample example = new GildedRoseExample("Cookbook", 2, 5);
        int initialSellIn = example.sellIn();

        example.update();

        assertThat(example.sellIn()).isLessThan(initialSellIn);
    }

    @Test
    void sulfurasDoesNotNeedToBeSold() {
        // Sulfuras has sellIn < 0, which is not updated.
        // Does this need a test if Sulfuras starts with sellIn < 0 anyway?
        // Rather test that Sulfuras always has sellIn < 0?
    }

    @Test
    void normalQualityDecreases() {
        GildedRoseExample example = new GildedRoseExample("+5 Dexterity Vest", 10, 20);
        int initialQuality = example.quality();

        example.update();

        assertThat(example.quality()).isLessThan(initialQuality);
    }

    @Test
    void normalQualityDecreasesTwiceAsFastOnceSellDateHasPassed() {
        GildedRoseExample example = new GildedRoseExample("Elixir of the Mongoose", 1, 20);
        int initialQuality = example.quality();

        example.update();

        int nextQuality = example.quality();
        int qualityDifference = initialQuality - nextQuality;

        example.update();

        assertThat(example.quality()).isEqualTo(nextQuality - 2 * qualityDifference);
    }

    @Test
    void qualityDoesNotDecreaseBelowMinQuality() {
        GildedRoseExample example = new GildedRoseExample("Crap", 10, MIN_QUALITY);

        example.update();

        assertThat(example.quality()).isEqualTo(MIN_QUALITY);
    }

    @Test
    void sulfurasQualityDoesNotDecrease() {
        GildedRoseExample example = new GildedRoseExample("Sulfuras, Hand of Ragnaros", -1, 10);
        int initialQuality = example.quality();

        example.update();

        assertThat(example.quality()).isEqualTo(initialQuality);
    }

    @Test
    void conjuredQualityDecreasesTwiceAsFastAsNormalItems() {
        // "Conjured Mana Cake", 3, 6

        // Test:
        // before sell date has passed
        // after sell date has passed
    }

    @Test
    void brieQualityIncreasesWithAge() {
        GildedRoseExample example = new GildedRoseExample("Aged Brie", 10, 20);
        int initialQuality = example.quality();

        example.update();

        assertThat(example.quality()).isGreaterThan(initialQuality);
    }

    @Test
    void qualityNeverIncreasesAboveMaxQuality() {
        GildedRoseExample example = new GildedRoseExample("Aged Brie", 10, MAX_QUALITY);

        example.update();

        assertThat(example.quality()).isEqualTo(MAX_QUALITY);
    }

    @Test
    void backstagePassQualityIncreasesWithAge() {
        GildedRoseExample example = new GildedRoseExample("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        int initialQuality = example.quality();

        example.update();

        assertThat(example.quality()).isGreaterThan(initialQuality);
    }

    @Test
    void backstagePassQualityIncreasesBy2When10DaysOrLessLeft() {
        GildedRoseExample example = new GildedRoseExample("Backstage passes to a TAFKAL80ETC concert", 10, 5);
        int initialQuality = example.quality();

        example.update();

        assertThat(example.quality()).isEqualTo(initialQuality + 2);
    }

    @Test
    void backstagePassQualityIncreasesBy3When5DaysOrLessLeft() {
        GildedRoseExample example = new GildedRoseExample("Backstage passes to a TAFKAL80ETC concert", 5, 5);
        int initialQuality = example.quality();

        example.update();

        assertThat(example.quality()).isEqualTo(initialQuality + 3);
    }

    @Test
    void backstagePassQualityDropsToZeroOnceSellDateHasPassed() {
        GildedRoseExample example = new GildedRoseExample("Backstage passes to a TAFKAL80ETC concert", 0, 20);

        example.update();

        assertThat(example.quality()).isEqualTo(0);
    }
}
