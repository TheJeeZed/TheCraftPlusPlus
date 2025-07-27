package net.thejeezed.pandorium.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.thejeezed.pandorium.Pandorium;

public class ModTags {
    public static class Items {
        //public static final TagKey<Item> ITEM_HERE = tag("item_here");
        public static final TagKey<Item> KILN_FUEL = tag("kiln_fuel");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Pandorium.MOD_ID, name));
        }
    }
    public static class Blocks {
        //public static final TagKey<Block> BLOCK_HERE = tag("block_here");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Pandorium.MOD_ID, name));
        }
    }
}
