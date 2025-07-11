package Galva.Compability.DefenseTower;

import Galva.Status.GalvaPlayer;
import Galva.Status.GalvaPlayers;
import necesse.engine.modLoader.LoadedMod;
import necesse.engine.modLoader.ModLoader;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.client.Client;
import necesse.level.gameObject.GameObject;
import necesse.level.maps.Level;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = GameObject.class, name="placeObject", arguments = {Level.class, int.class, int.class, int.class})
public class DefenseTowerPlacePatch
{
    public static boolean IsDTEnabled;
    @Advice.OnMethodExit
    public static void CheckYourStatus(@Advice.Argument(0) Level level)
    {
        if (level == null)
        {
            return;
        }
        if (IsDTEnabled)
        {
            Client client = level.getClient();
            if (client == null)
            {
                return;
            }
            GalvaPlayer player = GalvaPlayers.getPlayerByName(client.getPlayer());
            player.SendDeleteAttributesPacket(client);
            player.SendAddAttributesPacket(client);
        }
    }

    public static boolean DetectMod()
    {
        for (LoadedMod mod : ModLoader.getEnabledMods())
        {
            if (mod.name.equals("Defense Tower"))
            {
                IsDTEnabled = true;
                return true;
            }
        }
        return false;
    }
}
