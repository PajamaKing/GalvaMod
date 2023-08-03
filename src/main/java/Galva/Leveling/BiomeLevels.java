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
        BiomeLevels.add(new BiomeLevelMultiplier("DesertBiome", 50));
        BiomeLevels.add(new BiomeLevelMultiplier("DesertCaveLevel", 50));
        BiomeLevels.add(new BiomeLevelMultiplier("DesertDeepCaveLevel", 100));
        BiomeLevels.add(new BiomeLevelMultiplier("DesertSurfaceLevel", 50));
        BiomeLevels.add(new BiomeLevelMultiplier("DesertVillageBiome", 50));
        BiomeLevels.add(new BiomeLevelMultiplier("DungeonArenaLevel", 30));
        BiomeLevels.add(new BiomeLevelMultiplier("DungeonBiome", 30));
        BiomeLevels.add(new BiomeLevelMultiplier("DungeonLevel", 30));
        BiomeLevels.add(new BiomeLevelMultiplier("ForestBiome", 10));
        BiomeLevels.add(new BiomeLevelMultiplier("ForestCaveLevel", 10));
        BiomeLevels.add(new BiomeLevelMultiplier("ForestDeepCaveLevel", 70));
        BiomeLevels.add(new BiomeLevelMultiplier("ForestSurfaceLevel", 10));
        BiomeLevels.add(new BiomeLevelMultiplier("ForestVillageBiome", 10));
        BiomeLevels.add(new BiomeLevelMultiplier("PirateVillageBiome", 60));
        BiomeLevels.add(new BiomeLevelMultiplier("PlainsBiome", 10));
        BiomeLevels.add(new BiomeLevelMultiplier("PlainsSurfaceLevel", 10));
        BiomeLevels.add(new BiomeLevelMultiplier("SnowBiome", 20));
        BiomeLevels.add(new BiomeLevelMultiplier("SnowCaveLevel", 20));
        BiomeLevels.add(new BiomeLevelMultiplier("SnowDeepCaveLevel", 80));
        BiomeLevels.add(new BiomeLevelMultiplier("SnowSurfaceLevel", 20));
        BiomeLevels.add(new BiomeLevelMultiplier("SnowVillageBiome", 20));
        BiomeLevels.add(new BiomeLevelMultiplier("SwampBiome", 40));
        BiomeLevels.add(new BiomeLevelMultiplier("SwampCaveLevel", 40));
        BiomeLevels.add(new BiomeLevelMultiplier("SwampDeepCaveLevel", 90));
        BiomeLevels.add(new BiomeLevelMultiplier("SwampSurfaceLevel", 40));
        BiomeLevels.add(new BiomeLevelMultiplier("TempleArenaLevel", 110));
        BiomeLevels.add(new BiomeLevelMultiplier("TempleBiome", 110));
        BiomeLevels.add(new BiomeLevelMultiplier("TempleLevel", 110));
        BiomeLevels.add(new BiomeLevelMultiplier("aoadrydesertsurfacelevel",  50));
        BiomeLevels.add(new BiomeLevelMultiplier("aoaancientplateusurface",  50));
        BiomeLevels.add(new BiomeLevelMultiplier("aoaancientdeepcavelevel",  50));
        BiomeLevels.add(new BiomeLevelMultiplier("aoaoceansurfacelevel",  50));
        BiomeLevels.add(new BiomeLevelMultiplier("aoaoceancavelevel",  70));
        BiomeLevels.add(new BiomeLevelMultiplier("aoadeepoceancavelevel",  140));
        BiomeLevels.add(new BiomeLevelMultiplier("crystalsurfacelevel",  60));
        BiomeLevels.add(new BiomeLevelMultiplier("aoacrystalcavelevel",  70));
        BiomeLevels.add(new BiomeLevelMultiplier("aoacrystaldeepcavelevel",  70));
        BiomeLevels.add(new BiomeLevelMultiplier("aoavolcanicsurfacelevel",  80));
        BiomeLevels.add(new BiomeLevelMultiplier("aoavolcaniccavelevel",  80));
        BiomeLevels.add(new BiomeLevelMultiplier("aoavolcanicdeepcavelevel",  80));
        BiomeLevels.add(new BiomeLevelMultiplier("aoadeadlandsurfacelevel",  85));
        BiomeLevels.add(new BiomeLevelMultiplier("aoadeadlandcavelevel",  85));
        BiomeLevels.add(new BiomeLevelMultiplier("aoabloomingsurfacelevel",  50));
        BiomeLevels.add(new BiomeLevelMultiplier("aoabloomingcavelevel",  90));
        BiomeLevels.add(new BiomeLevelMultiplier("aoabloomingdeepcavelevel",  90));
    }

    public static float GetMultiplier(Biome biome)
    {
        for (BiomeLevelMultiplier biomeMultiplier : BiomeLevels)
        {
            if (biomeMultiplier.biome.toUpperCase().equals(biome.getClass().getSimpleName().toUpperCase()))
            {
                return biomeMultiplier.value;
            }
        }
        return 120;
    }
}
