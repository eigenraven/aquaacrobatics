package com.fuzs.aquaacrobatics.world.structure;

import com.fuzs.aquaacrobatics.AquaAcrobatics;
import com.fuzs.aquaacrobatics.world.gen.RandomHelper;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.WoodlandMansion;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuriedTreasureStructure extends MapGenStructure {
    public final List<Biome> allowedBiomes;

    public BuriedTreasureStructure() {
        allowedBiomes = new ArrayList<>(BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN));
    }

    public String getStructureName()
    {
        return "Buried_Treasure";
    }

    protected boolean canSpawnStructureAtCoords(int chunkPosX, int chunkPosZ)
    {
        Biome biome = world.getBiomeProvider().getBiome(new BlockPos((chunkPosX << 4) + 9, 0, (chunkPosZ << 4) + 9), (Biome)null);
        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN)) {
            Random random = this.world.setRandomSeed(chunkPosX, chunkPosZ, 10387320);
            return random.nextFloat() < 0.5f;
        } else {
            return false;
        }
    }

    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored)
    {
        return findNearestStructurePosBySpacing(worldIn,
                this,
                pos,
                16,
                1,
                10387320,
                false,
                1000,
                findUnexplored);
    }

    public static class Start extends StructureStart {
        public Start() {
        }

        public Start(World world, Random rand, int chunkX, int chunkZ) {
            super(chunkX, chunkZ);
            int i = chunkX * 16;
            int j = chunkZ * 16;
            BlockPos blockpos = new BlockPos(i + 9, 90, j + 9);
            this.components.add(new BuriedTreasurePieces.Piece(blockpos));
            this.updateBoundingBox();
        }

        public BlockPos getPos() {
            return new BlockPos((this.getChunkPosX() << 4) + 9, 0, (this.getChunkPosZ() << 4) + 9);
        }
    }

    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new BuriedTreasureStructure.Start(this.world, this.rand, chunkX, chunkZ);
    }
}
