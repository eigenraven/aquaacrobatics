package com.fuzs.aquaacrobatics.config;

import com.fuzs.aquaacrobatics.AquaAcrobatics;
import com.fuzs.aquaacrobatics.biome.BiomeWaterFogColors;
import com.fuzs.aquaacrobatics.client.handler.FogHandler;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AquaAcrobatics.MODID)
@Mod.EventBusSubscriber
public class ConfigHandler {

    @Config.Name("Push Player Out Of Blocks")
    @Config.Comment({"STANDARD - The player will occasionally be pushed out of certain spaces. Collisions are evaluated for full cubes only, non-full cubes are ignored. This is the default behavior up to Minecraft 1.12.", "APPROXIMATE - The player can move into more spaces, but will still be pushed out of some. Collisions are evaluated for full cubes only, non-full cubes are ignored.", "EXACT - The player can move into all spaces as expected. Collisions are evaluated for all types of cubes. This is the default behavior in Minecraft 1.13 and onwards."})
    public static PlayerBlockCollisions playerBlockCollisions = PlayerBlockCollisions.APPROXIMATE;

    @SuppressWarnings("unused")
    @Config.Name("blocks")
    @Config.Comment("Block-related config options (must match server).")
    public static BlocksConfig blocksConfig;
    
    @SuppressWarnings("unused")
    @Config.Name("movement")
    @Config.Comment("Movement related config options.")
    public static MovementConfig movementConfig;

    @SuppressWarnings("unused")
    @Config.Name("miscellaneous")
    @Config.Comment("Config options for various features of the mod.")
    public static MiscellaneousConfig miscellaneousConfig;

    @SuppressWarnings("unused")
    @Config.Name("integration")
    @Config.Comment("Control compatibility settings for individual mods.")
    public static IntegrationConfig integrationConfig;

    public static class MovementConfig {

        @Config.Name("Easy Elytra Takeoff")
        @Config.Comment("Taking off with an elytra from the ground is now far easier like in Minecraft 1.15 and onwards.")
        public static boolean easyElytraTakeoff = true;

        @Config.Name("No Double Tab Sprinting")
        @Config.Comment("Prevent sprinting from being triggered by double tapping the walk forward key.")
        public static boolean noDoubleTapSprinting = false;

        @Config.Name("Sideways Sprinting")
        @Config.Comment("Enables sprinting to the left and right.")
        public static boolean sidewaysSprinting = false;

        @Config.Name("Sideways Swimming")
        @Config.Comment("Enables swimming to the left and right.")
        public static boolean sidewaysSwimming = false;

        @Config.Name("Enable Crawling")
        @Config.Comment("Enables crawling to prevent suffocation. Note that if you disable this there will probably be behavioral differences from 1.13.")
        public static boolean enableCrawling = true;

        @Config.Name("Enable Toggle Crawling")
        @Config.Comment("Enables a keybind to toggle crawling.")
        public static boolean enableToggleCrawling = false;

        @Config.Name("New Projectile Behavior")
        @Config.Comment("Modify projectile behavior to be closer to that of newer versions (fixes MC-73884 and allows bubble columns to work with ender pearls).")
        public static boolean newProjectileBehavior = false;

        @Config.Name("New Climbing Behavior")
        @Config.Comment("Allow climbing vines and climbing by pressing jump.")
        public static boolean newClimbingBehavior = false;

    }

    public static class BlocksConfig {
        @Config.Name("Seagrass")
        @Config.Comment("Allow seagrass to generate in the world.")
        public static boolean seagrass = false;

        @Config.Name("Brighter Water")
        @Config.Comment("Make water only reduce light level by 1 per Y-level, instead of 3.")
        public static boolean brighterWater = true;

        @Config.Name("New Water")
        @Config.Comment("Use the new water rendering in 1.13+.")
        public static boolean newWaterColors = true;

        @Config.Name("New Water Fog")
        @Config.Comment("Use the new fog rendering in 1.13+.")
        public static boolean newWaterFog = true;
    }

    public static class MiscellaneousConfig {

        @Config.Name("Replenish Air Slowly")
        @Config.Comment("Replenish air slowly when out of water instead of immediately.")
        public static boolean slowAirReplenish = false;

        @Config.Name("Sneaking Dismounts Parrots")
        @Config.Comment("Parrots no longer leave the players shoulders as easily, instead the player needs to press the sneak key.")
        public static boolean sneakingForParrots = true;

        @Config.Name("Eating Animation")
        @Config.Comment("Animate eating in third-person view.")
        public static boolean eatingAnimation = true;
        
        @Config.Name("Bubble Columns")
        @Config.Comment("Enable bubble columns.")
        public static boolean bubbleColumns = false;

        @Config.Name("Custom Biome Water Colors")
        @Config.Comment("Allows overriding the water and fog colors for a biome. Specify each entry like this (without quotes) - 'modname:biome,color,fogcolor'")
        public static String[] customBiomeWaterColors = new String[] {};

        @Config.Name("WorldProvider Fog Blacklist")
        @Config.Comment("List of WorldProviders in which fog should be disabled.")
        public static String[] providerFogBlacklist = new String[] { "thebetweenlands.common.world.WorldProviderBetweenlands" };

        @Config.Name("Floating Items")
        @Config.Comment("Whether or not items should float in water like in 1.13+.")
        public static boolean floatingItems = true;
    }

    public static class IntegrationConfig {

        private static final String COMPAT_DESCRIPTION = "Only applies when the mod is installed. Disable when there are issues with the mod.";

        @Config.Name("Applied Energistics 2 Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        @Config.RequiresMcRestart
        public static boolean ae2Integration = true;

        @Config.Name("Betweenlands Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        @Config.RequiresMcRestart
        public static boolean betweenlandsIntegration = true;

        @Config.Name("Chiseled Me Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        @Config.RequiresMcRestart
        public static boolean chiseledMeIntegration = true;

        @Config.Name("Ender IO Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        @Config.RequiresMcRestart
        public static boolean enderIoIntegration = true;
        
        @Config.Name("Random Patches Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        public static boolean randomPatchesIntegration = true;

        @Config.Name("Mo' Bends Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        @Config.RequiresMcRestart
        public static boolean moBendsIntegration = true;

        @Config.Name("Wings Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        public static boolean wingsIntegration = true;

        @Config.Name("ArtemisLib Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        @Config.RequiresMcRestart
        public static boolean artemisLibIntegration = true;

        @Config.Name("Morph Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        public static boolean morphIntegration = true;

        @Config.Name("Hats Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        @Config.RequiresMcRestart
        public static boolean hatsIntegration = true;

        @Config.Name("Thaumic Augmentation Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        public static boolean thaumicAugmentationIntegration = true;

        @Config.Name("Trinkets and Baubles Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        public static boolean trinketsAndBaublesIntegration = true;

        @Config.Name("Witchery: Resurrected Integration")
        @Config.Comment(COMPAT_DESCRIPTION)
        public static boolean witcheryResurrectedIntegration = true;

    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent evt) {

        if (evt.getModID().equals(AquaAcrobatics.MODID)) {

            ConfigManager.sync(AquaAcrobatics.MODID, Config.Type.INSTANCE);
        }
        BiomeWaterFogColors.recomputeColors();
        FogHandler.recomputeBlacklist();
    }

    @SuppressWarnings("unused")
    public enum PlayerBlockCollisions {

        STANDARD, APPROXIMATE, EXACT
    }

}