package Galva.Status;

import necesse.entity.mobs.PlayerMob;

import java.util.ArrayList;

public class GalvaPlayers

{
    public static ArrayList<GalvaPlayer> Players;
    static
    {
          Players = new ArrayList<>();
    }

    public static GalvaPlayer getPlayerByName(PlayerMob playerMob)
    {
        return getPlayerByName(playerMob.playerName);
    }

    //caution on usage
    public static GalvaPlayer getPlayerByName(String name)
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
        GalvaPlayer player = getPlayerByName(name);
        Players.remove(player);
    }

    public static boolean playerExists(String name)
    {
        for (GalvaPlayer player : Players)
        {
            if (player.PlayerName.equals(name)) return true;
        }
        return false;
    }
}
