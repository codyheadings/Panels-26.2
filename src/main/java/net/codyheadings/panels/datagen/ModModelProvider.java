package net.codyheadings.panels.datagen;

import net.codyheadings.panels.Panels;
import net.codyheadings.panels.block.ModBlocks;
import net.codyheadings.panels.block.PanelBlock;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.AttachFace;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        for (ModBlocks.PanelSet panelSet : ModBlocks.PANELS) {
            String name = BuiltInRegistries.BLOCK.getKey(panelSet.panel()).getPath();
            Identifier tex = Identifier.fromNamespaceAndPath(Panels.MOD_ID, "block/" + name);
            if(panelSet.orientable()){
                PanelModelGenerator.registerOrientablePanel(blockModelGenerators, panelSet.panel(),
                        PanelModelGenerator.textureMapping(tex, tex));
            }
            else {
                PanelModelGenerator.registerPanel(blockModelGenerators, panelSet.panel(),
                        PanelModelGenerator.textureMapping(tex, tex));
            }
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {

    }

    public static class PanelModelGenerator {
        public static final ModelTemplate PANEL_CEILING = block("panel_block_ceiling", "_ceiling", TextureSlot.FRONT, TextureSlot.SIDE);
        public static final ModelTemplate PANEL_FLOOR = block("panel_block_floor", "_floor", TextureSlot.FRONT, TextureSlot.SIDE);
        public static final ModelTemplate PANEL_WALL = block("panel_block_wall", "_wall", TextureSlot.FRONT, TextureSlot.SIDE);

        // helper method for creating Models
        private static ModelTemplate block(String parent, String suffix, TextureSlot... requiredTextureKeys) {
            return new ModelTemplate(Optional.of(Panels.id("block/" + parent)), Optional.of(suffix), requiredTextureKeys);
        }


        public static TextureMapping textureMapping(Identifier front, Identifier side) {
            return new TextureMapping()
                    .put(TextureSlot.FRONT, new Material(front))
                    .put(TextureSlot.SIDE, new Material(side));
        }


        private static BlockModelDefinitionGenerator createPanelBlockStates(Block panelBlock, Identifier panelCeilingId, Identifier panelFloorId, Identifier panelWallId) {
            MultiVariant panelCeilingModel = BlockModelGenerators.plainVariant(panelCeilingId);
            MultiVariant panelFloorModel = BlockModelGenerators.plainVariant(panelFloorId);
            MultiVariant panelWallModel = BlockModelGenerators.plainVariant(panelWallId);
            return MultiVariantGenerator.dispatch(panelBlock)
                    .with(PropertyDispatch.initial(PanelBlock.FACING, PanelBlock.FACE)
                            .select(Direction.NORTH, AttachFace.WALL, panelWallModel)
                            .select(Direction.EAST, AttachFace.WALL, panelWallModel.with(BlockModelGenerators.Y_ROT_90))
                            .select(Direction.SOUTH, AttachFace.WALL, panelWallModel.with(BlockModelGenerators.Y_ROT_180))
                            .select(Direction.WEST, AttachFace.WALL, panelWallModel.with(BlockModelGenerators.Y_ROT_270))
                            .select(Direction.NORTH, AttachFace.FLOOR, panelFloorModel)
                            .select(Direction.EAST, AttachFace.FLOOR, panelFloorModel)
                            .select(Direction.SOUTH, AttachFace.FLOOR, panelFloorModel)
                            .select(Direction.WEST, AttachFace.FLOOR, panelFloorModel)
                            .select(Direction.NORTH, AttachFace.CEILING, panelCeilingModel)
                            .select(Direction.EAST, AttachFace.CEILING, panelCeilingModel)
                            .select(Direction.SOUTH, AttachFace.CEILING, panelCeilingModel)
                            .select(Direction.WEST, AttachFace.CEILING, panelCeilingModel)
                    );
        }

        private static BlockModelDefinitionGenerator createOrientablePanelBlockStates(Block panelBlock, Identifier panelCeilingId, Identifier panelFloorId, Identifier panelWallId) {
            MultiVariant panelCeilingModel = BlockModelGenerators.plainVariant(panelCeilingId);
            MultiVariant panelFloorModel = BlockModelGenerators.plainVariant(panelFloorId);
            MultiVariant panelWallModel = BlockModelGenerators.plainVariant(panelWallId);
            return MultiVariantGenerator.dispatch(panelBlock)
                    .with(PropertyDispatch.initial(PanelBlock.FACING, PanelBlock.FACE)
                            .select(Direction.NORTH, AttachFace.WALL, panelWallModel)
                            .select(Direction.EAST, AttachFace.WALL, panelWallModel.with(BlockModelGenerators.Y_ROT_90))
                            .select(Direction.SOUTH, AttachFace.WALL, panelWallModel.with(BlockModelGenerators.Y_ROT_180))
                            .select(Direction.WEST, AttachFace.WALL, panelWallModel.with(BlockModelGenerators.Y_ROT_270))
                            .select(Direction.NORTH, AttachFace.FLOOR, panelFloorModel)
                            .select(Direction.EAST, AttachFace.FLOOR, panelFloorModel.with(BlockModelGenerators.Y_ROT_90))
                            .select(Direction.SOUTH, AttachFace.FLOOR, panelFloorModel.with(BlockModelGenerators.Y_ROT_180))
                            .select(Direction.WEST, AttachFace.FLOOR, panelFloorModel.with(BlockModelGenerators.Y_ROT_270))
                            .select(Direction.NORTH, AttachFace.CEILING, panelCeilingModel)
                            .select(Direction.EAST, AttachFace.CEILING, panelCeilingModel.with(BlockModelGenerators.Y_ROT_90))
                            .select(Direction.SOUTH, AttachFace.CEILING, panelCeilingModel.with(BlockModelGenerators.Y_ROT_180))
                            .select(Direction.WEST, AttachFace.CEILING, panelCeilingModel.with(BlockModelGenerators.Y_ROT_270))
                    );
        }


        public static void registerPanel(BlockModelGenerators generator, Block panelBlock, TextureMapping textures) {
            Identifier panelModelCeiling = PANEL_CEILING.create(panelBlock, textures, generator.modelOutput);
            Identifier panelModelFloor = PANEL_FLOOR.create(panelBlock, textures, generator.modelOutput);
            Identifier panelModelWall = PANEL_WALL.create(panelBlock, textures, generator.modelOutput);
            generator.blockStateOutput.accept(createPanelBlockStates(panelBlock, panelModelCeiling, panelModelFloor, panelModelWall));
            generator.registerSimpleItemModel(panelBlock, panelModelFloor);
        }

        public static void registerOrientablePanel(BlockModelGenerators generator, Block panelBlock, TextureMapping textures) {
            Identifier panelModelCeiling = PANEL_CEILING.create(panelBlock, textures, generator.modelOutput);
            Identifier panelModelFloor = PANEL_FLOOR.create(panelBlock, textures, generator.modelOutput);
            Identifier panelModelWall = PANEL_WALL.create(panelBlock, textures, generator.modelOutput);
            generator.blockStateOutput.accept(createOrientablePanelBlockStates(panelBlock, panelModelCeiling, panelModelFloor, panelModelWall));
            generator.registerSimpleItemModel(panelBlock, panelModelFloor);
        }

    }
}
