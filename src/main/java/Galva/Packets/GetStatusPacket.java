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
import necesse.entity.mobs.PlayerMob;

public class GetStatusPacket extends Packet {

    public final String Name;
    public final int Lev;
    public final int Con;
    public final int Str;
    public final int Dex;
    public final int Int;
    public final int Wis;
    public final int Exp;

    // MUST HAVE - Used for construction in registry
    public GetStatusPacket(byte[] data) {
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
        Exp = reader.getNextInt();
    }

    public GetStatusPacket(String Name, int Con, int Str, int Dex, int Int, int Wis, int Lev, int Exp) {
        this.Name = Name;
        this.Con = Con;
        this.Str = Str;
        this.Dex = Dex;
        this.Int = Int;
        this.Wis = Wis;
        this.Lev = Lev;
        this.Exp = Exp;

        PacketWriter writer = new PacketWriter(this);

        writer.putNextString(Name);
        writer.putNextInt(Con);
        writer.putNextInt(Str);
        writer.putNextInt(Dex);
        writer.putNextInt(Int);
        writer.putNextInt(Wis);
        writer.putNextInt(Lev);
        writer.putNextInt(Exp);
    }

    @Override
    public void processClient(NetworkPacket packet, Client client) {
        System.out.println("Received Stats " + Con + "-" + Str + "-" + Dex + "-" + Int + "-" + Wis + "-" + Lev
                + "-" + Exp + "!");
        PlayerMob playerMob = client.getPlayer();
        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(playerMob);
        player.Constitution.value = Con;
        player.Strength.value = Str;
        player.Dexterity.value = Dex;
        player.Intelligence.value = Int;
        player.Wisdom.value = Wis;
        player.Level.value = Lev;
        player.Experience = Exp;
        if (!player.HasAttributeBuffs(playerMob))
        {
            player.SendDeleteAttributesPacket(client);
            player.SendAddAttributesPacket(client);
        }
    }

    @Override
    public void processServer(NetworkPacket packet, Server server, ServerClient client) {
        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(Name);
        System.out.println("Sending Stats " + Con + "-" + Str + "-" + Dex + "-" + Int + "-" + Wis + "-" + Lev
                + "-" + Exp + " for player" + Name + "!");
        player.SendGetAttributesPacket(client, Name);
    }
}