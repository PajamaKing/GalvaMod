package Galva.SaveData;

import Galva.Compability.DefenseTower.DefenseTowerPlacePatch;
import Galva.Status.GalvaPlayer;
import Galva.Status.GalvaPlayers;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.save.LoadData;
import necesse.entity.mobs.PlayerMob;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = PlayerMob.class, name = "applyLoadData", arguments = {LoadData.class})
public class AttributeLoader
{
    @Advice.OnMethodExit
    static void LoadAttributes(@Advice.This PlayerMob playerMob, @Advice.Argument(0) LoadData loadData)
    {
        GalvaPlayers.RemovePlayer(playerMob.playerName);
        System.out.println("Entering Load Attributes!");
        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(playerMob);
        player.Constitution.value = TryGetSaveData(loadData, "Constitution");
        player.Strength.value = TryGetSaveData(loadData, "Strength");
        player.Dexterity.value = TryGetSaveData(loadData, "Dexterity");
        player.Intelligence.value = TryGetSaveData(loadData, "Intelligence");
        player.Wisdom.value = TryGetSaveData(loadData, "Wisdom");
        player.Level.value = TryGetSaveData(loadData, "Level");
        player.Experience = TryGetSaveData(loadData, "Experience");

        System.out.println(player.PlayerName);

        if (player.Level.value == 0)
        {
            System.out.println("Possible old version character! Loading Attributes by buffs!");
            player.LoadAttributes(playerMob);
        }

        DefenseTowerPlacePatch.DetectMod();
    }

    public static int TryGetSaveData(LoadData loadData, String name)
    {
        if (loadData.hasLoadDataByName(name))
        {
            int value = loadData.getInt(name);
            System.out.println("Loading " + name + value + "!");
            return value;
        }
        else
        {
            return 0;
        }
    }
}
