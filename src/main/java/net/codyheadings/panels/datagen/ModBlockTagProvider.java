package net.codyheadings.panels.datagen;

import net.codyheadings.panels.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        for(ModBlocks.PanelSet panelSet : ModBlocks.PANELS) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.getRK(panelSet.panel()));
        }
    }
}
