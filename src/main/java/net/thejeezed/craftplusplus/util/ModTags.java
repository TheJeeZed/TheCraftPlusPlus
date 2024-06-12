package net.thejeezed.craftplusplus.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.thejeezed.craftplusplus.CraftPlusPlus;

public class ModTags {
    public static class Items {
        //public static final TagKey<Item> ITEM_HERE = tag("item_here");
        public static final TagKey<Item> KILN_FUEL = tag("kiln_fuel");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(CraftPlusPlus.MOD_ID, name));
        }
    }
    public static class Blocks {
        //public static final TagKey<Block> BLOCK_HERE = tag("block_here");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(CraftPlusPlus.MOD_ID, name));
        }
    }
}
