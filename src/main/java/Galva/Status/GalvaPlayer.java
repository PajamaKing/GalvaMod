package Galva.Status;

import Galva.Leveling.LevelHolder;
import Galva.Packets.AddStatusPacket;
import Galva.Packets.RemoveStatusPacket;
import Galva.Packets.GetStatusPacket;
import Galva.Status.Attributes.*;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.ServerClient;
import necesse.engine.registries.BuffRegistry;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.staticBuffs.Buff;
import necesse.gfx.forms.FormManager;

import java.util.ArrayList;

public class GalvaPlayer extends LevelHolder {

    public String PlayerName;
    public GalvaAttribute Constitution;
    public GalvaAttribute Dexterity;
    public GalvaAttribute Strength;
    public GalvaAttribute Intelligence;
    public GalvaAttribute Wisdom;
    public GalvaPlayer(String name)
    {
        PlayerName = name;
        Constitution = new Constitution();
        Dexterity= new Dexterity();
        Strength= new Strength();
        Intelligence= new Intelligence();
        Wisdom= new Wisdom();
        Level= new GalvaLevel();
    }
    public static void Init()
    {
        //Force param init before game opens
    }

    public void LoadAttributes(PlayerMob player){
        System.out.println("Adding stuff");
        Constitution.value = GetActiveAttributeValue(player, Constitution);
        Strength.value = GetActiveAttributeValue(player, Strength);
        Dexterity.value = GetActiveAttributeValue(player, Dexterity);
        Intelligence.value = GetActiveAttributeValue(player, Intelligence);
        Wisdom.value = GetActiveAttributeValue(player, Wisdom);

        Level.value = GetActiveAttributeValue(player, Level);
    }

    public int GetActiveAttributeValue(PlayerMob playerMob, GalvaAttribute Attribute)
    {
        ArrayList<ActiveBuff> activeBuffs = playerMob.buffManager.getArrayBuffs();
        Buff buff = GetActiveAttributeBuff(playerMob, Attribute);

        if (buff != null)
        {
            return Integer.parseInt(buff.getDisplayName().split("_")[1]);
        }

        if (Attribute.BuffID.equals("Level"))
        {
            GalvaPlayer player = GalvaPlayers.getCurrentPlayer(playerMob);
            player.Experience = 0;
        }

        return 1;
    }

    public Buff GetActiveAttributeBuff(PlayerMob playerMob, GalvaAttribute Attribute)
    {
        return GetActiveAttributeBuff(playerMob, Attribute.BuffID);
    }

    public Buff GetActiveAttributeBuff(PlayerMob playerMob, String BuffID)
    {
        ArrayList<ActiveBuff> activeBuffs = playerMob.buffManager.getArrayBuffs();

        for (ActiveBuff activeBuff : activeBuffs) {
            if (activeBuff.buff.getDisplayName().contains(BuffID)) {
                return activeBuff.buff;
            }
        }
        return null;
    }

    public boolean HasAttributeBuffs(PlayerMob playerMob)
    {
        return GetActiveAttributeBuff(playerMob, "Level") != null;
    }

    public void SendDeleteAttributesPacket(Client client){
        RemoveStatusPacket DSP = new RemoveStatusPacket(client.getPlayer().playerName);
        client.network.sendPacket(DSP);
    }

    public void SendAddAttributesPacket(Client client){
        PrintStats();
        AddStatusPacket ASP = new AddStatusPacket(
                client.getPlayer().playerName
                , Constitution.value
                , Strength.value
                , Dexterity.value
                , Intelligence.value
                , Wisdom.value
                , Level.value);
        client.network.sendPacket(ASP);
    }

    public void SendGetAttributesPacket(ServerClient client, String Name){
        GetStatusPacket ASP = new GetStatusPacket(
                Name
                , Constitution.value
                , Strength.value
                , Dexterity.value
                , Intelligence.value
                , Wisdom.value
                , Level.value
                , Experience);
        client.sendPacket(ASP);
    }

    public void GetAttributesPacket(Client client){
        GetStatusPacket ASP = new GetStatusPacket(
                client.getPlayer().playerName
                , Constitution.value
                , Strength.value
                , Dexterity.value
                , Intelligence.value
                , Wisdom.value
                , Level.value
                , Experience);
        client.network.sendPacket(ASP);
    }

    public void ApplyInternalStats(PlayerMob player)
    {
        ActiveBuff ConBuff = new ActiveBuff(BuffRegistry.getBuff(Constitution.GetBuffValueId()), player, 4147200f, null);
        ActiveBuff StrBuff = new ActiveBuff(BuffRegistry.getBuff(Strength.GetBuffValueId()), player, 4147200f, null);
        ActiveBuff DexBuff = new ActiveBuff(BuffRegistry.getBuff(Dexterity.GetBuffValueId()), player, 4147200f, null);
        ActiveBuff IntBuff = new ActiveBuff(BuffRegistry.getBuff(Intelligence.GetBuffValueId()), player, 4147200f, null);
        ActiveBuff WisBuff = new ActiveBuff(BuffRegistry.getBuff(Wisdom.GetBuffValueId()), player, 4147200f, null);
        ActiveBuff LevBuff = new ActiveBuff(BuffRegistry.getBuff(Level.GetBuffValueId()), player, 4147200f, null);

        player.buffManager.addBuff(ConBuff, true);
        player.buffManager.addBuff(StrBuff, true);
        player.buffManager.addBuff(DexBuff, true);
        player.buffManager.addBuff(IntBuff, true);
        player.buffManager.addBuff(WisBuff, true);
        player.buffManager.addBuff(LevBuff, true);
    }
    //Should only be used in packets or server side commands
    public void ApplyStats(PlayerMob player, int Con, int Str, int Dex, int Int, int Wis, int Lev)
    {
        System.out.println("Adding Stats Internal " + Con + "-" + Str + "-" + Dex + "-" + Int + "-" + Wis + "-" + Lev);
        Constitution.value = Con;
        Strength.value = Str;
        Dexterity.value = Dex;
        Intelligence.value = Int;
        Wisdom.value = Wis;
        Level.value = Lev;

        ApplyInternalStats(player);
    }

    //Should only be used in packets or server side commands
    public void RemoveStats(PlayerMob player){
        if (GetActiveAttributeBuff(player, Constitution) != null)
        {
            player.buffManager.removeBuff(GetActiveAttributeBuff(player, Constitution), true);
            player.buffManager.removeBuff(GetActiveAttributeBuff(player, Strength), true);
            player.buffManager.removeBuff(GetActiveAttributeBuff(player, Dexterity), true);
            player.buffManager.removeBuff(GetActiveAttributeBuff(player, Intelligence), true);
            player.buffManager.removeBuff(GetActiveAttributeBuff(player, Wisdom), true);
            player.buffManager.removeBuff(GetActiveAttributeBuff(player, Level), true);
        }
    }

    public int GetFreeStatusPoints()
    {
        int points = 5 + (Level.value) -
                Wisdom.value -
                Dexterity.value -
                Constitution.value -
                Strength.value -
                Intelligence.value;

        if (points < 0) return 0;
        return points;
    }

    public int GetTotalStatusPoints()
    {
         return GetFreeStatusPoints() +
                Wisdom.value +
                Dexterity.value +
                Constitution.value +
                Strength.value +
                Intelligence.value;
    }

    public void PrintStats()
    {
        System.out.println(PlayerName + "'s Stats are "
                + Constitution.value + "-"
                + Strength.value + "-"
                + Dexterity.value + "-"
                + Intelligence.value + "-"
                + Wisdom.value + "-"
                + Level.value + "-"
                + Experience + "!");
    }
}
