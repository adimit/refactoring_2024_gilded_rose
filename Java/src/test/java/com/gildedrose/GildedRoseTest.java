package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateQualityForNormalItem() {
        // given
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals("foo", app.items[0].name);
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void updateQualityReducesQualityBy1() {
        // given
        Item[] items = new Item[]{new Item("foo", 1, 19)};
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals(18, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void updateQualityReducesQualityBy2AfterSellIn() {
        // given
        Item[] items = new Item[]{new Item("foo", 0, 19)};
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals(17, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void agedBrieIncreasesInQualityQuicklyAfterSellInDate() {
        // given
        Item[] items = new Item[]{new Item("Aged Brie", 0, 2)};
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals(4, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void agedBrieIncreasesInQuality() {
        // given
        Item[] items = new Item[]{new Item("Aged Brie", 1, 2)};
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals(3, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void qualityNeverExceeds50() {
        // given
        Item[] items = new Item[]{new Item("Aged Brie", 1, 50)};
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals(50, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void sulfurasNeverLosesQuality() {
        // given
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 1, 80)};
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals(80, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void backstagePassesIncreaseInQuality() {
        // given
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 15, 10)};
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals(11, app.items[0].quality);
        assertEquals(14, app.items[0].sellIn);
    }

    @Test
    void backstagePassesIncreaseInQualityFasterCloserToSellInDate( ){
        // given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)
        };
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals(12, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);

        assertEquals(13, app.items[1].quality);
        assertEquals(4, app.items[1].sellIn);
    }

    @Test
    void backstagePassesAreWorthlessAfterSellInDate( ){
        // given
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
        };
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    void conjuredArticlesDepreciateFaster( ){
        // given
        Item[] items = new Item[]{
            new Item("conjured article", 5, 10),
        };
        GildedRose app = new GildedRose(items);

        // when
        app.updateQuality();

        // then
        assertEquals(8, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }
}
