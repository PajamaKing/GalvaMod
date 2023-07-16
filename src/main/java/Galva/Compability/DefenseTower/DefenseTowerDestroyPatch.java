package Galva.Compability.DefenseTower;

import Galva.Status.GalvaPlayer;
import Galva.Status.GalvaPlayers;
import necesse.engine.modLoader.LoadedMod;
import necesse.engine.modLoader.ModLoader;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.ServerClient;
import necesse.entity.pickup.ItemPickupEntity;
import necesse.level.gameObject.GameObject;
import necesse.level.maps.Level;
import net.bytebuddy.asm.Advice;

import java.util.ArrayList;

@ModMethodPatch(target = GameObject.class, name="onDestroyed", arguments = {Level.class, int.class, int.class,
        ServerClient.class, ArrayList.class})
public class DefenseTowerDestroyPatch
{
    @Advice.OnMethodExit
    public static void CheckYourStatus(@Advice.Argument(0) Level level)
    {
        DefenseTowerPlacePatch.CheckYourStatus(level);
    }
}
