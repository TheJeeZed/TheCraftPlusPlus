package net.thejeezed.craftplusplus.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class ModBlockTags
{

    public static final TagKey<Block> STRAFER_WARM_BLOCKS = create("strafer_warm_blocks");



    private ModBlockTags() {
    }

    private static @NotNull TagKey<Block> create(String pName) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(pName));
    }

    public static @NotNull TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registries.BLOCK, name);
    }
}
