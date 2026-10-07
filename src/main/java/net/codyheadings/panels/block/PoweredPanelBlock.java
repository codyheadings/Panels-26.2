package net.codyheadings.panels.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class PoweredPanelBlock extends PanelBlock {
    public PoweredPanelBlock(Properties properties) {
        super(properties);
    }

    public boolean isSignalSource(BlockState context) {
        return true;
    }

    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 15;
    }
}
