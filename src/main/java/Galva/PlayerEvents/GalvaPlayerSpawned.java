package Galva.PlayerEvents;

import Galva.Status.GalvaPlayer;
import Galva.Status.GalvaPlayers;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.client.Client;
import necesse.engine.network.client.ClientClient;
import necesse.entity.mobs.PlayerMob;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = ClientClient.class, name = "applySpawned", arguments = {int.class})
public class GalvaPlayerSpawned
{
    public static long World = 0;

    @Advice.OnMethodExit
    public static void onExit(@Advice.FieldValue(value = "client") Client client)
    {
        System.out.println("ClientSpawn");

        if (client.getPlayer() == null){
            return;
        }

        PlayerMob playerMob = client.getPlayer();
        System.out.println(playerMob.playerName);

        if (playerMob != null) {
            GalvaPlayer player = GalvaPlayers.getPlayerByName(playerMob);
            System.out.println("[SPAWN EVENT!]");
            System.out.println("Added spawn attributes to " + player + "!");
            player.GetAttributesPacket(client);
        }
    }
}