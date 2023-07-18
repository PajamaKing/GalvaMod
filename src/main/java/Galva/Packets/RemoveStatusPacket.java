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

public class RemoveStatusPacket extends Packet {
    public final String Name;
    // MUST HAVE - Used for construction in registry
    public RemoveStatusPacket(byte[] data) {
        super(data);
        PacketReader reader = new PacketReader(this);
        // Important that it's same order as written in
        Name = reader.getNextString();
    }

    public RemoveStatusPacket(String Name) {
        this.Name = Name;
        PacketWriter writer = new PacketWriter(this);
        writer.putNextString(Name);
    }

    @Override
    public void processClient(NetworkPacket packet, Client client) {
        System.out.println("Removing Stats Client!");
        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(client.getPlayer());
        player.SendDeleteAttributesPacket(client);
    }

    @Override
    public void processServer(NetworkPacket packet, Server server, ServerClient client)
    {
        System.out.println("Removing Stats Server! " + Name);
        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(Name);
        player.RemoveStats(client.playerMob);
    }
}