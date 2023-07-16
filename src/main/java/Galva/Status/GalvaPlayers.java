package Galva.Status;

import necesse.engine.network.client.Client;
import necesse.engine.network.server.ServerClient;
import necesse.entity.mobs.PlayerMob;

import java.util.ArrayList;

public class GalvaPlayers

{
    public static ArrayList<GalvaPlayer> Players;
    static
    {
          Players = new ArrayList<>();
    }

    public static GalvaPlayer getCurrentPlayer(PlayerMob playerMob)
    {
        for (GalvaPlayer player : Players)
        {
            if (player.PlayerName.equals(playerMob.playerName))
            {
                player.playerMob = playerMob;
                return player;
            }
        }
        GalvaPlayer newPlayer = new GalvaPlayer(playerMob);
        newPlayer.PrintStats();
        Players.add(newPlayer);
        return newPlayer;
    }

    //caution on usage
    public static GalvaPlayer getCurrentPlayer(String name)
    {
        for (GalvaPlayer player : Players)
        {
            if (player.PlayerName.equals(name)) return player;
        }
        return null;
    }
}
