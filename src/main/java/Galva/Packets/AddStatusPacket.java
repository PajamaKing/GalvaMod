package Galva.Packets;

import Galva.Status.GalvaPlayer;
import Galva.Status.GalvaPlayers;
import necesse.engine.network.NetworkPacket;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;

public class AddStatusPacket extends Packet {

    public final String Name;
    public final int Lev;
    public final int Con;
    public final int Str;
    public final int Dex;
    public final int Int;
    public final int Wis;

    // MUST HAVE - Used for construction in registry
    public AddStatusPacket(byte[] data) {
        super(data);
        PacketReader reader = new PacketReader(this);
        // Important that it's same order as written in
        Name = reader.getNextString();
        Con = reader.getNextInt();
        Str = reader.getNextInt();
        Dex = reader.getNextInt();
        Int = reader.getNextInt();
        Wis = reader.getNextInt();
        Lev = reader.getNextInt();
    }

    public AddStatusPacket(String Name, int Con, int Str, int Dex, int Int, int Wis, int Lev) {
        this.Name = Name;
        this.Con = Con;
        this.Str = Str;
        this.Dex = Dex;
        this.Int = Int;
        this.Wis = Wis;
        this.Lev = Lev;

        PacketWriter writer = new PacketWriter(this);

        writer.putNextString(Name);
        writer.putNextInt(Con);
        writer.putNextInt(Str);
        writer.putNextInt(Dex);
        writer.putNextInt(Int);
        writer.putNextInt(Wis);
        writer.putNextInt(Lev);
    }

    @Override
    public void processClient(NetworkPacket packet, Client client) {
        System.out.println("Adding Stats Client!");
        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(client.getPlayer());
        player.PrintStats();
        player.Constitution.value = Con;
        player.Strength.value = Str;
        player.Dexterity.value = Dex;
        player.Intelligence.value = Int;
        player.Wisdom.value = Wis;
        player.Level.value = Lev;
        player.PrintStats();
        player.SendAddAttributesPacket(client);
    }

    @Override
    public void processServer(NetworkPacket packet, Server server,  ServerClient client) {
        System.out.println("Adding Stats Server! " + Name);
        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(Name);
        player.PrintStats();
        player.Constitution.value = Con;
        player.Strength.value = Str;
        player.Dexterity.value = Dex;
        player.Intelligence.value = Int;
        player.Wisdom.value = Wis;
        player.Level.value = Lev;
        player.PrintStats();
        player.ApplyStats(player.playerMob, Con, Str, Dex, Int, Wis, Lev);
    }
}