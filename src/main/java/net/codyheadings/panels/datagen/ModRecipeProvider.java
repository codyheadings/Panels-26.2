package net.codyheadings.panels.datagen;

import net.codyheadings.panels.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.codyheadings.panels.block.ModBlocks.PANELS;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                createPanelRecipes(PANELS);
                createPanelStonecuttingRecipes(PANELS);
                // Special Recipes
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLESTONE_PANEL, Blocks.STONE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_BRICK_PANEL, Blocks.STONE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_DEEPSLATE_PANEL, Blocks.DEEPSLATE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEPSLATE_BRICK_PANEL, Blocks.DEEPSLATE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DEEPSLATE_PANEL, Blocks.DEEPSLATE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEPSLATE_BRICK_PANEL, Blocks.COBBLED_DEEPSLATE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DEEPSLATE_PANEL, Blocks.COBBLED_DEEPSLATE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDSTONE_BRICK_PANEL, Blocks.END_STONE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_BLACKSTONE_BRICK_PANEL, Blocks.BLACKSTONE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_BLACKSTONE_PANEL, Blocks.BLACKSTONE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TUFF_PANEL, Blocks.TUFF, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TUFF_BRICK_PANEL, Blocks.TUFF, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ANDESITE_PANEL, Blocks.ANDESITE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DIORITE_PANEL, Blocks.DIORITE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GRANITE_PANEL, Blocks.GRANITE, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_BASALT_PANEL, Blocks.BASALT, 3);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICK_PANEL, Blocks.QUARTZ_BLOCK, 3);
            }

            private void createPanelRecipes(List<ModBlocks.PanelSet> panels) {
                for(ModBlocks.PanelSet panelSet : panels) {
                    shaped(RecipeCategory.BUILDING_BLOCKS, panelSet.panel(), 9)
                            .pattern("#")
                            .pattern("#")
                            .pattern("#")
                            .define('#', panelSet.source())
                            .unlockedBy(getHasName(panelSet.source()), has(panelSet.source()))
                            .group("panels")
                            .save(output, getItemName(panelSet.panel()) + "_from_" + getItemName(panelSet.source()));
                }
            }

            private void createPanelStonecuttingRecipes(List<ModBlocks.PanelSet> panels) {
                for(ModBlocks.PanelSet panelSet : panels) {
                    stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, panelSet.panel(), panelSet.source(), 3);
                }
            }
        };
    }

    @Override
    public String getName() {
        return "Panels Recipes";
    }
}
