package mc.Mitchellbrine.radicalWorlds;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.ChunkEvent;

/**
 * Created by Mitchellbrine on 2015.
 */
public class EventStuff {

    @SubscribeEvent
    public void generate(PopulateChunkEvent.Post event) {
        ChunkCoordinates spawn = event.world.getSpawnPoint();

                //if (event.world.getWorldInfo().isHardcoreModeEnabled()) {
                    if (event.world.getChunkFromBlockCoords(spawn.posX,spawn.posZ).xPosition == event.chunkX && event.world.getChunkFromBlockCoords(spawn.posX,spawn.posZ).zPosition == event.chunkZ) {
                        int highestBlock = getHighestBlock(event.world, spawn.posX, spawn.posZ);

                        generateTheDarkMageCrystal(event.world, spawn.posX, highestBlock, spawn.posZ);
                    }
                //}
    }

    private boolean generateTheDarkMageCrystal(World world,int chunkX, int firstTop, int chunkZ) {

        // Clears the area

        for (int yy = 0; yy < 4;yy++) {
            for (int i = -2; i < 3;i++) {
                for (int ii = -2;ii < 3;ii++) {
                    world.setBlock(chunkX + i, firstTop + yy, chunkZ + ii, Blocks.air);
                }
            }
        }

        for (int i = -2; i < 3;i++) {
            for (int ii = -2; ii < 3;ii++) {
                world.setBlock(chunkX + i, firstTop, chunkZ + ii, Blocks.bedrock);
            }
        }

        for (int i = -2; i < 3;i++) {
            for (int yy = 1; yy < 4;yy++) {
                if (i != 0) {
                    world.setBlock(chunkX + i, firstTop + yy, chunkZ + i,Blocks.bedrock);
                    world.setBlock(chunkX - i, firstTop + yy, chunkZ + i,Blocks.bedrock);
                    world.setBlock(chunkX + i, firstTop + yy, chunkZ - i,Blocks.bedrock);
                    world.setBlock(chunkX - i, firstTop + yy, chunkZ - i, Blocks.bedrock);
                }
            }
        }

        world.setBlock(chunkX,firstTop + 1, chunkZ,Blocks.bedrock);

        return true;
    }

    protected static int getHighestBlock(final World world, final int cx, final int cz)
    {
        int highest = -1;
        for (int x = cx - 3; x <= cx + 3; x++)
        {
            for (int z = cz - 3; z <= cz + 3; z++)
            {
                int y = 255;
                while (!isValidTop(world, x, y, z) && y > highest)
                {
                    y--;
                }
                if ((world.getBlock(x, y, z) != Blocks.air))
                {
                    highest = y;
                }
            }
        }
        return highest;
    }

    /**
     * Returns true if the block is not considered a valid block to gen on. It isnt the ground.
     */
    protected static boolean isValidTop(World world, int x, int y, int z)
    {
        if (world.isAirBlock(x, y, z))
        {
            return false;
        }

        Block block = world.getBlock(x,y,z);

        if (block.isLeaves(world, x, y, z) || block.isWood(world, x, y, z) || block.isFoliage(world, x, y, z))
        {
            return false;
        }

        return block.isBlockSolid(world, x, y, z, ForgeDirection.UP.ordinal());
    }

}
