package Galva;

import Galva.Packets.AddExpPacket;
import Galva.Packets.AddStatusPacket;
import Galva.Packets.GetStatusPacket;
import Galva.Packets.RemoveStatusPacket;
import Galva.Status.Attributes.*;
import Galva.Status.GalvaPlayer;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.BuffRegistry;
import necesse.engine.registries.MusicRegistry;
import necesse.engine.registries.PacketRegistry;
import necesse.gfx.forms.FormManager;

@ModEntry
public class Galva {

    public static int MaxLevel = 100;
    public static int MaxStatLevel = 41;
    public static int BaseStatLevel = 1;
    public void init(){
        System.out.println("Hello world from galva mod!");
        InitItems();
        InitBuffs();
        InitPackets();


    }

    public void InitItems()
    {
        //ItemRegistry.registerItem("Exp", new Exp(), 1, true);
    }

    public void InitBuffs()
    {
        for (int i = 1; i < MaxStatLevel+1; i++){
            Constitution Con = new Constitution(i);
            Dexterity Dex = new Dexterity(i);
            Strength Str = new Strength(i);
            Intelligence Int = new Intelligence(i);
            Wisdom Wis = new Wisdom(i);
            BuffRegistry.registerBuff("Constitution_"+ i, Con.buff);
            BuffRegistry.registerBuff("Dexterity_"+ i, Dex.buff);
            BuffRegistry.registerBuff("Strength_"+ i, Str.buff);
            BuffRegistry.registerBuff("Intelligence_"+ i, Int.buff);
            BuffRegistry.registerBuff("Wisdom_"+ i, Wis.buff);
        }

        for (int i = 1; i < MaxLevel+1; i++)
        {
            GalvaLevel Lev = new GalvaLevel(i);
            BuffRegistry.registerBuff("Level_"+ i, Lev.buff);
        }

        GalvaPlayer.Init();
    }

    public void InitPackets(){
        System.out.println("Registered packets!");
        PacketRegistry.registerPacket(AddStatusPacket.class);
        PacketRegistry.registerPacket(RemoveStatusPacket.class);
        PacketRegistry.registerPacket(AddExpPacket.class);
        PacketRegistry.registerPacket(GetStatusPacket.class);
    }

    public void initResources(){

    }

    public void postInit(){

    }
}
