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
        return getCurrentPlayer(playerMob.playerName);
    }

    //caution on usage
    public static GalvaPlayer getCurrentPlayer(String name)
    {
        for (GalvaPlayer player : Players)
        {
            if (player.PlayerName.equals(name)) return player;
        }
        GalvaPlayer newPlayer = new GalvaPlayer(name);
        newPlayer.PrintStats();
        Players.add(newPlayer);
        return newPlayer;
    }

    public static void RemovePlayer(String name)
    {
        GalvaPlayer player = getCurrentPlayer(name);
        Players.remove(player);
    }
}
