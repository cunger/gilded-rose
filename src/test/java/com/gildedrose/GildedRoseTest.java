package com.gildedrose;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.gildedrose.GildedRose.*;
import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GildedRoseTest {

    @Test
    void items_with_negative_quality_are_set_to_minimal_quality() {
        GildedRoseExample example = new GildedRoseExample("Junk", 2, MIN_QUALITY - 1);

        assertThat(example.quality()).isEqualTo(MIN_QUALITY);
    }

    @Test
    void items_with_too_high_quality_are_set_to_max_quality() {
        GildedRoseExample example = new GildedRoseExample("All-in-one problem solver", 2, MAX_QUALITY + 1);

        assertThat(example.quality()).isEqualTo(MAX_QUALITY);
    }

    @Test
    void sell_date_gets_closer_with_each_update() {
        GildedRoseExample example = new GildedRoseExample("Cookbook", 2, 5);
        int initialSellIn = example.sellIn();

        example.update();

        assertThat(example.sellIn()).isLessThan(initialSellIn);
    }

    @Nested
    class normal_quality {
        @Test
        void decreases() {
            GildedRoseExample example = new GildedRoseExample("+5 Dexterity Vest", 10, 20);
            int initialQuality = example.quality();

            example.update();

            assertThat(example.quality()).isLessThan(initialQuality);
        }

        @Test
        void decreases_twice_as_fast_once_expired() {
            GildedRoseExample example = new GildedRoseExample("Elixir of the Mongoose", 0, 20);
            int initialQuality = example.quality();

            example.update();

            assertThat(example.quality()).isEqualTo(initialQuality - 2 * QUALITY_CHANGE);
        }
    }

    @Test
    void quality_does_not_decrease_below_minimum() {
        GildedRoseExample example = new GildedRoseExample("Crap", 10, MIN_QUALITY);

        example.update();

        assertThat(example.quality()).isEqualTo(MIN_QUALITY);
    }

    @Test
    void quality_does_not_increase_above_maximum() {
        GildedRoseExample example = new GildedRoseExample("Aged Brie", 10, MAX_QUALITY);

        example.update();

        assertThat(example.quality()).isEqualTo(MAX_QUALITY);
    }

    @Nested
    class sulfuras {
        @Test
        void does_not_need_to_be_sold() {
            GildedRoseExample example = new GildedRoseExample("Sulfuras, Hand of Ragnaros", 2, 5);
            int initialSellIn = example.sellIn();

            assertThat(initialSellIn).isLessThan(0);

            example.update();

            assertThat(example.sellIn()).isEqualTo(initialSellIn);
        }

        @Test
        void quality_does_not_decrease() {
            GildedRoseExample example = new GildedRoseExample("Sulfuras, Hand of Ragnaros", -1, 10);
            int initialQuality = example.quality();

            example.update();

            assertThat(example.quality()).isEqualTo(initialQuality);
        }
    }

    @Test
    void conjured_quality_decreases_twice_as_fast_as_normal_items() {
        GildedRoseExample example = new GildedRoseExample("Conjured Mana Cake", 1, 6);
        int initialQuality = example.quality();

        // Before sell date has passed:

        example.update();

        assertThat(example.quality()).isEqualTo(initialQuality - 2 * QUALITY_CHANGE);

        // After sell date has passed:

        int currentQuality = example.quality();

        example.update();

        assertThat(example.quality()).isEqualTo(currentQuality - 4 * QUALITY_CHANGE);
    }

    @Test
    void brie_quality_increases_with_age() {
        GildedRoseExample example = new GildedRoseExample("Aged Brie", 10, 20);
        int initialQuality = example.quality();

        example.update();

        assertThat(example.quality()).isGreaterThan(initialQuality);
    }

    @Nested
    class backstage_pass_quality {
        @Test
        void increases_with_age() {
            GildedRoseExample example = new GildedRoseExample("Backstage passes to a TAFKAL80ETC concert", 15, 20);
            int initialQuality = example.quality();

            example.update();

            assertThat(example.quality()).isGreaterThan(initialQuality);
        }

        @Test
        void increases_by_2_when_10_days_or_less_left() {
            GildedRoseExample example = new GildedRoseExample("Backstage passes to a TAFKAL80ETC concert", 11, 5);
            int initialQuality = example.quality();
            int nextQuality;

            example.update();

            assertThat(example.quality()).isEqualTo(initialQuality + 1 * QUALITY_CHANGE);

            nextQuality = example.quality();

            example.update();

            assertThat(example.quality()).isEqualTo(nextQuality + 2 * QUALITY_CHANGE);

            nextQuality = example.quality();

            example.update();

            assertThat(example.quality()).isEqualTo(nextQuality + 2 * QUALITY_CHANGE);
        }

        @Test
        void increases_by_3_when_5_days_or_less_left() {
            GildedRoseExample example = new GildedRoseExample("Backstage passes to a TAFKAL80ETC concert", 6, 5);
            int initialQuality = example.quality();
            int nextQuality;

            example.update();

            assertThat(example.quality()).isEqualTo(initialQuality + 2 * QUALITY_CHANGE);

            nextQuality = example.quality();

            example.update();

            assertThat(example.quality()).isEqualTo(nextQuality + 3 * QUALITY_CHANGE);

            nextQuality = example.quality();

            example.update();

            assertThat(example.quality()).isEqualTo(nextQuality + 3 * QUALITY_CHANGE);
        }

        @Test
        void drops_to_zero_once_expired() {
            GildedRoseExample example = new GildedRoseExample("Backstage passes to a TAFKAL80ETC concert", 0, 20);

            example.update();

            assertThat(example.quality()).isEqualTo(0);
        }
    }
}
