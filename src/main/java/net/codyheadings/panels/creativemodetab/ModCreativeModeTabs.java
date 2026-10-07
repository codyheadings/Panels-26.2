package net.codyheadings.panels.creativemodetab;

import net.codyheadings.panels.Panels;
import net.codyheadings.panels.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab PANELS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Panels.MOD_ID, "panel_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.POLISHED_DEEPSLATE_PANEL))
                    .title(Component.translatable("creativemodetab.panels.panel_blocks"))
                    .displayItems((parameters, output) -> {
                        for(ModBlocks.PanelSet set : ModBlocks.PANELS) {
                            output.accept(set.panel());
                        }

                    }).build());


    public static void registerModCreativeModeTabs() {
        Panels.LOGGER.info("Registering Creative Mode Tabs for " + Panels.MOD_ID);
    }
}
