package Galva.Leveling;

import necesse.level.maps.biomes.Biome;
import necesse.level.maps.biomes.desert.*;
import necesse.level.maps.biomes.dungeon.*;
import necesse.level.maps.biomes.forest.*;
import necesse.level.maps.biomes.pirate.*;
import necesse.level.maps.biomes.plains.*;
import necesse.level.maps.biomes.snow.*;
import necesse.level.maps.biomes.swamp.*;
import necesse.level.maps.biomes.temple.*;

import java.util.ArrayList;

public class BiomeLevels
{
    private static ArrayList<BiomeLevelMultiplier> BiomeLevels = new ArrayList<>();
    static {
        BiomeLevels.add(new BiomeLevelMultiplier(DesertBiome.class, 50));
        BiomeLevels.add(new BiomeLevelMultiplier(DesertCaveLevel.class, 50));
        BiomeLevels.add(new BiomeLevelMultiplier(DesertDeepCaveLevel.class, 100));
        BiomeLevels.add(new BiomeLevelMultiplier(DesertSurfaceLevel.class, 50));
        BiomeLevels.add(new BiomeLevelMultiplier(DesertVillageBiome.class, 50));
        BiomeLevels.add(new BiomeLevelMultiplier(DungeonArenaLevel.class, 30));
        BiomeLevels.add(new BiomeLevelMultiplier(DungeonBiome.class, 30));
        BiomeLevels.add(new BiomeLevelMultiplier(DungeonLevel.class, 30));
        BiomeLevels.add(new BiomeLevelMultiplier(ForestBiome.class, 10));
        BiomeLevels.add(new BiomeLevelMultiplier(ForestCaveLevel.class, 10));
        BiomeLevels.add(new BiomeLevelMultiplier(ForestDeepCaveLevel.class, 70));
        BiomeLevels.add(new BiomeLevelMultiplier(ForestSurfaceLevel.class, 10));
        BiomeLevels.add(new BiomeLevelMultiplier(ForestVillageBiome.class, 10));
        BiomeLevels.add(new BiomeLevelMultiplier(PirateVillageBiome.class, 60));
        BiomeLevels.add(new BiomeLevelMultiplier(PlainsBiome.class, 10));
        BiomeLevels.add(new BiomeLevelMultiplier(PlainsSurfaceLevel.class, 10));
        BiomeLevels.add(new BiomeLevelMultiplier(SnowBiome.class, 20));
        BiomeLevels.add(new BiomeLevelMultiplier(SnowCaveLevel.class, 20));
        BiomeLevels.add(new BiomeLevelMultiplier(SnowDeepCaveLevel.class, 80));
        BiomeLevels.add(new BiomeLevelMultiplier(SnowSurfaceLevel.class, 20));
        BiomeLevels.add(new BiomeLevelMultiplier(SnowVillageBiome.class, 20));
        BiomeLevels.add(new BiomeLevelMultiplier(SwampBiome.class, 40));
        BiomeLevels.add(new BiomeLevelMultiplier(SwampCaveLevel.class, 40));
        BiomeLevels.add(new BiomeLevelMultiplier(SwampDeepCaveLevel.class, 90));
        BiomeLevels.add(new BiomeLevelMultiplier(SwampSurfaceLevel.class, 40));
        BiomeLevels.add(new BiomeLevelMultiplier(TempleArenaLevel.class, 110));
        BiomeLevels.add(new BiomeLevelMultiplier(TempleBiome.class, 110));
        BiomeLevels.add(new BiomeLevelMultiplier(TempleLevel.class, 110));
    }

    public static float GetMultiplier(Biome biome)
    {
        for (BiomeLevelMultiplier biomeMultiplier : BiomeLevels)
        {
            if (biomeMultiplier.biome == biome.getClass())
            {
                return biomeMultiplier.value;
            }
        }
        return 120;
    }
}
