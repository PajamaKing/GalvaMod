package Galva.SaveData;

import Galva.Status.GalvaPlayer;
import Galva.Status.GalvaPlayers;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.save.SaveData;
import necesse.entity.mobs.PlayerMob;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = PlayerMob.class, name = "addSaveData", arguments = {SaveData.class})
public class AttributeSaver
{
    @Advice.OnMethodExit
    static void SaveAttributes(@Advice.This PlayerMob playerMob, @Advice.Argument(0) SaveData saveData)
    {
        System.out.println("Saving Character Attributes!");
        GalvaPlayer player = GalvaPlayers.getPlayerByName(playerMob);
        saveData.addInt("Constitution", player.Constitution.value);
        saveData.addInt("Strength", player.Strength.value);
        saveData.addInt("Dexterity", player.Dexterity.value);
        saveData.addInt("Intelligence", player.Intelligence.value);
        saveData.addInt("Wisdom", player.Wisdom.value);
        saveData.addInt("Level", player.Level.value);
        saveData.addInt("Experience", player.Experience);
    }
}
