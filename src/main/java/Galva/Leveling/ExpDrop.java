package Galva.Leveling;

import Galva.Packets.AddExpPacket;
import Galva.Status.GalvaPlayer;
import Galva.Status.GalvaPlayers;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.server.ServerClient;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import net.bytebuddy.asm.Advice;
import java.util.HashSet;

import static Galva.Galva.MaxLevel;

@ModMethodPatch(target = Mob.class, name = "onDeath", arguments = {Attacker.class, HashSet.class})
public class ExpDrop
{
    @Advice.OnMethodExit
    public static void onExit(@Advice.This Mob mob, @Advice.Argument(1) HashSet<Attacker> attackers)
    {
        //system.out.println("Running onDeath event!");
        if (mob.getLevel() == null)
        {
            //system.out.println("null mob level?");
            return;
        }
        else
        {
            //system.out.println(mob.getLevel());
        }

        if (mob.isHostile)
        {
            for (Attacker attacker : attackers)
            {
                if (attacker instanceof PlayerMob)
                {
                    PlayerMob playerMob = ((PlayerMob)attacker);
                    ServerClient serverClient = playerMob.getServerClient();
                    GalvaPlayer player = GalvaPlayers.getPlayerByName(playerMob);
                    if (player.Level.value == MaxLevel)
                    {
                        return;
                    }
                    int xpAmount = GetXpAmount(mob, player);
                    serverClient.sendPacket(new AddExpPacket(xpAmount, mob.getLevel().getStringID(), player.PlayerName));
                }
            }
        }
    }
    public static int GetXpAmount(Mob mob, GalvaPlayer player)
    {
        int mobExp = mob.getMaxHealth() / 2 + mob.getArmorFlat() * 2 + Math.round(mob.getSpeed()) * 2;
        //system.out.println(mobExp);
        float multiplier = BiomeLevels.GetMultiplier(mob.getLevel().getBiome(mob.getTileX(), mob.getTileY())) / player.Level.value;
        //system.out.println(multiplier + " * " + mobExp);
        return Math.round(mobExp * multiplier);
    }
}
