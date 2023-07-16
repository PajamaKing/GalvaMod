//package Galva.Packets;
//
//import Galva.Status.GalvaPlayer;
//import Galva.Status.GalvaPlayers;
//import necesse.engine.network.NetworkPacket;
//import necesse.engine.network.Packet;
//import necesse.engine.network.PacketReader;
//import necesse.engine.network.PacketWriter;
//import necesse.engine.network.client.Client;
//import necesse.engine.network.server.Server;
//import necesse.engine.network.server.ServerClient;
//import necesse.entity.mobs.PlayerMob;
//
//public class CheckStatusPacket extends Packet {
//
//    public final String Name;
//
//    // MUST HAVE - Used for construction in registry
//    public CheckStatusPacket(byte[] data) {
//        super(data);
//        PacketReader reader = new PacketReader(this);
//        // Important that it's same order as written in
//        Name = reader.getNextString();
//    }
//
//    public CheckStatusPacket(String Name, int Con, int Str, int Dex, int Int, int Wis, int Lev, int Exp) {
//        this.Name = Name;
//        PacketWriter writer = new PacketWriter(this);
//        writer.putNextString(Name);
//    }
//
//    @Override
//    public void processServer(NetworkPacket packet, Server server, ServerClient client) {
//        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(Name);
//        PlayerMob playerMob = client.playerMob;
//
//        if (player.GetActiveAttributeBuff(playerMob, player.Level) == null)
//        {
//            player.ApplyInternalStats(playerMob);
//        }
//    }
//}