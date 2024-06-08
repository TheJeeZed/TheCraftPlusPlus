package net.thejeezed.craftplusplus;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.thejeezed.craftplusplus.enchant.ModEnchants;
import net.thejeezed.craftplusplus.init.ModBlocks;
import net.thejeezed.craftplusplus.creativetabs.CreativeTabs;
import net.thejeezed.craftplusplus.entity.ModEntities;
import net.thejeezed.craftplusplus.init.ModItems;
import net.thejeezed.craftplusplus.datagen.loot.ModLootModifiers;
import net.thejeezed.craftplusplus.init.ModSounds;
import net.thejeezed.craftplusplus.mob.client.StraferRenderer;
import net.thejeezed.craftplusplus.mob.client.SulphurZombieRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.thejeezed.craftplusplus.util.ModEntitySpawn;
import org.slf4j.Logger;

@Mod(CraftPlusPlus.MOD_ID)
public class CraftPlusPlus {
    public static final String MOD_ID = "craftplusplus";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CraftPlusPlus() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        CreativeTabs.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModEntities.register(modEventBus);
        ModEntitySpawn.SERIALIZER.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModEnchants.ENCHANTMENTS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS)
        {
            event.accept(ModBlocks.CHARCOAL_BLOCK);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
        {
            event.accept(ModBlocks.COMPRESSED_COPPER);
        }

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
        {
            event.accept(ModItems.RAW_TENTACLE);
            event.accept(ModItems.COOKED_TENTACLE);
            event.accept(ModItems.SEA_SOUP);
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            event.accept(ModItems.COPPER_SHOVEL);
            event.accept(ModItems.COPPER_PICKAXE);
            event.accept(ModItems.COPPER_AXE);
            event.accept(ModItems.COPPER_HOE);
            event.accept(ModItems.COPPER_BUCKET);
            event.accept(ModItems.COPPER_MILK_BUCKET);
            event.accept(ModItems.COPPER_WATER_BUCKET);
            event.accept(ModItems.MAGIC_MIRROR);
        }

        if (event.getTabKey() == CreativeModeTabs.COMBAT)
        {
            event.accept(ModItems.COPPER_SWORD);
            event.accept(ModItems.COPPER_AXE);
            event.accept(ModItems.COPPER_HELMET);
            event.accept(ModItems.COPPER_CHESTPLATE);
            event.accept(ModItems.COPPER_LEGGINGS);
            event.accept(ModItems.COPPER_BOOTS);
        }
    }

    @SubscribeEvent
    public void onFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event)
    {
        if (event.getItemStack().getItem() == ModBlocks.CHARCOAL_BLOCK.get().asItem())
        {
            event.setBurnTime(16000);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.STRAFER.get(), StraferRenderer::new);
            EntityRenderers.register(ModEntities.SULPHUR_ZOMBIE.get(), SulphurZombieRenderer::new);
            EntityRenderers.register(ModEntities.DYNAMITE_PROJECTILE.get(), ThrownItemRenderer::new);
        }
    }
}