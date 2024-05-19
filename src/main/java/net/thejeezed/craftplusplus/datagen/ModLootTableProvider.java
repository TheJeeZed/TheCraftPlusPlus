package net.thejeezed.craftplusplus.datagen;

import net.minecraft.data.PackOutput;
import net.thejeezed.craftplusplus.datagen.loot.ModBlockLootTables;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider {
    @Contract("_ -> new")
    public static @NotNull LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}