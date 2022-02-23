package com.gildedrose;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

    private final GildedRose app = GildedRoseExample.build();

    @Test
    void itemsWithNegativeQualityAreRejected() {
        // initiation with item that has negative quality
        // This is not yet part of the code.
        // Todo: automatically cut off at boundary
    }

    @Test
    void itemsWithLargerThanMaxQualityAreRejected() {
        // initiation with item that has larger than max quality
        // This is not yet part of the code.
        // Todo: automatically cut off at boundary
    }

    @Test
    void sellDateGetsCloserWithEachUpdate() {

    }

    @Test
    void sulfurasDoesNotNeedToBeSold() {
        // Sulfuras has sellIn < 0, which is not updated.
    }

    @Test
    void normalQualityDecreases() {

    }

    @Test
    void normalQualityDecreasesTwiceAsFastOnceSellDateHasPassed() {

    }

    @Test
    void qualityNeverDecreasesBelowZero() {

    }

    @Test
    void sulfurasQualityDoesNotDecrease() {

    }

    @Test
    void conjuredQualityDecreasesTwiceAsFastAsNormalItems() {
        // Test:
        // before sell date has passed
        // after sell date has passed
    }

    @Test
    void brieQualityIncreasesWithAge() {

    }

    @Test
    void qualityNeverIncreasesAbove50() {

    }

    @Test
    void backstagePassQualityIncreasesWithAge() {

    }

    @Test
    void backstagePassQualityIncreasesBy2When10DaysOrLessLeft() {

    }

    @Test
    void backstagePassQualityIncreasesBy3When5DaysOrLessLeft() {

    }

    @Test
    void backstagePassQualityDropsToZeroOnceSellDateHasPassed() {

    }
}
