package net.codyheadings.panels.block;

import net.codyheadings.panels.Panels;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModBlocks {
    public record PanelSet(Block panel, Block source, boolean orientable) {}
    public static final List<PanelSet> PANELS = new ArrayList<>();

    public static final Block ANDESITE_PANEL = registerPanel("andesite_panel", Blocks.ANDESITE, false);
    public static final Block POLISHED_ANDESITE_PANEL = registerPanel("polished_andesite_panel", Blocks.POLISHED_ANDESITE, false);
    public static final Block COBBLESTONE_PANEL = registerPanel("cobblestone_panel", Blocks.COBBLESTONE, false);
    public static final Block MOSSY_COBBLESTONE_PANEL = registerPanel("mossy_cobblestone_panel", Blocks.MOSSY_COBBLESTONE, false);
    public static final Block STONE_PANEL = registerPanel("stone_panel", Blocks.STONE, false);
    public static final Block STONE_BRICK_PANEL = registerPanel("stone_brick_panel", Blocks.STONE_BRICKS, false);
    public static final Block MOSSY_STONE_BRICK_PANEL = registerPanel("mossy_stone_brick_panel", Blocks.MOSSY_STONE_BRICKS, false);
    public static final Block SMOOTH_STONE_PANEL = registerPanel("smooth_stone_panel", Blocks.SMOOTH_STONE, false);
    public static final Block DIORITE_PANEL = registerPanel("diorite_panel", Blocks.DIORITE, true);
    public static final Block POLISHED_DIORITE_PANEL = registerPanel("polished_diorite_panel", Blocks.POLISHED_DIORITE, false);
    public static final Block GRANITE_PANEL = registerPanel("granite_panel", Blocks.GRANITE, false);
    public static final Block POLISHED_GRANITE_PANEL = registerPanel("polished_granite_panel", Blocks.POLISHED_GRANITE, true);
    public static final Block CALCITE_PANEL = registerPanel("calcite_panel", Blocks.CALCITE, false);
    public static final Block SMOOTH_BASALT_PANEL = registerPanel("smooth_basalt_panel", Blocks.SMOOTH_BASALT, false);
    public static final Block BASALT_PANEL = registerPanel("basalt_panel", Blocks.BASALT, false);
    public static final Block POLISHED_BASALT_PANEL = registerPanel("polished_basalt_panel", Blocks.POLISHED_BASALT, false);
    public static final Block DRIPSTONE_PANEL = registerPanel("dripstone_panel", Blocks.DRIPSTONE_BLOCK, false);
    public static final Block DEEPSLATE_PANEL = registerPanel("deepslate_panel", Blocks.DEEPSLATE, false);
    public static final Block COBBLED_DEEPSLATE_PANEL = registerPanel("cobbled_deepslate_panel", Blocks.COBBLED_DEEPSLATE, true);
    public static final Block POLISHED_DEEPSLATE_PANEL = registerPanel("polished_deepslate_panel", Blocks.POLISHED_DEEPSLATE, false);
    public static final Block DEEPSLATE_BRICK_PANEL = registerPanel("deepslate_brick_panel", Blocks.DEEPSLATE_BRICKS, true);
    public static final Block BLACKSTONE_PANEL = registerPanel("blackstone_panel", Blocks.BLACKSTONE, false);
    public static final Block POLISHED_BLACKSTONE_PANEL = registerPanel("polished_blackstone_panel", Blocks.POLISHED_BLACKSTONE, false);
    public static final Block POLISHED_BLACKSTONE_BRICK_PANEL = registerPanel("polished_blackstone_brick_panel", Blocks.POLISHED_BLACKSTONE_BRICKS, true);
    public static final Block GILDED_BLACKSTONE_PANEL = registerPanel("gilded_blackstone_panel", Blocks.GILDED_BLACKSTONE, false);
    public static final Block AMETHYST_PANEL = registerPanel("amethyst_panel", Blocks.AMETHYST_BLOCK, false);
    public static final Block SANDSTONE_PANEL = registerPanel("sandstone_panel", Blocks.SANDSTONE, false);
    public static final Block RED_SANDSTONE_PANEL = registerPanel("red_sandstone_panel", Blocks.RED_SANDSTONE, false);
    public static final Block TUFF_PANEL = registerPanel("tuff_panel", Blocks.TUFF, true);
    public static final Block POLISHED_TUFF_PANEL = registerPanel("polished_tuff_panel", Blocks.POLISHED_TUFF, false);
    public static final Block TUFF_BRICK_PANEL = registerPanel("tuff_brick_panel", Blocks.TUFF_BRICKS, true);
    public static final Block ENDSTONE_BRICK_PANEL = registerPanel("endstone_brick_panel", Blocks.END_STONE_BRICKS, true);
    public static final Block ENDSTONE_PANEL = registerPanel("endstone_panel", Blocks.END_STONE, false);
    public static final Block QUARTZ_PANEL = registerPanel("quartz_panel", Blocks.QUARTZ_BLOCK, false);
    public static final Block QUARTZ_BRICK_PANEL = registerPanel("quartz_brick_panel", Blocks.QUARTZ_BRICKS, true);
    public static final Block COAL_PANEL = registerPanel("coal_panel", Blocks.COAL_BLOCK, true);
    public static final Block DIAMOND_PANEL = registerPanel("diamond_panel", Blocks.DIAMOND_BLOCK, true);
    public static final Block EMERALD_PANEL = registerPanel("emerald_panel", Blocks.EMERALD_BLOCK, false);
    public static final Block GOLD_PANEL = registerPanel("gold_panel", Blocks.GOLD_BLOCK, true);
    public static final Block IRON_PANEL = registerPanel("iron_panel", Blocks.IRON_BLOCK, true);
    public static final Block NETHERITE_PANEL = registerPanel("netherite_panel", Blocks.NETHERITE_BLOCK, true);
    public static final Block REDSTONE_PANEL = registerPoweredPanel("redstone_panel", Blocks.REDSTONE_BLOCK, false);
    public static final Block LAPIS_PANEL = registerPanel("lapis_panel", Blocks.LAPIS_BLOCK, true);


    private static Block registerPanel(String name, Block source, boolean orientable) {
        Block panel = registerBlock(name, PanelBlock::new,
                BlockBehaviour.Properties.ofFullCopy(source));
        PANELS.add(new PanelSet(panel, source, orientable));
        return panel;
    }

    private static Block registerPoweredPanel(String name, Block source, boolean orientable) {
        Block panel = registerBlock(name, PoweredPanelBlock::new,
                BlockBehaviour.Properties.ofFullCopy(source));
        PANELS.add(new PanelSet(panel, source,  orientable));
        return panel;
    }

    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    private static <T extends Block> T registerBlock(String name, Function<BlockBehaviour.Properties, T> factory) { // if I need to change properties
        return registerBlock(name, factory, BlockBehaviour.Properties.of());
    }

    private static <T extends Block> T registerBlock(String name,
                                                     Function<BlockBehaviour.Properties, T> factory,
                                                     BlockBehaviour.Properties base) {
        Identifier id = Identifier.fromNamespaceAndPath(Panels.MOD_ID, name);
        T toRegister = factory.apply(base.setId(ResourceKey.create(Registries.BLOCK, id)));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, id, toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Identifier id = Identifier.fromNamespaceAndPath(Panels.MOD_ID, name);
        BlockItem item = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                .setId(ResourceKey.create(Registries.ITEM, id)));
        item.registerBlocks(Item.BY_BLOCK, item);
        Registry.register(BuiltInRegistries.ITEM, id, item);
    }

    public static void registerModBlocks(){
        Panels.LOGGER.info("Registering Mod Blocks for " + Panels.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
            for(PanelSet panelSet : PANELS) {
                output.accept(panelSet.panel());
            }
        });
    }
}
