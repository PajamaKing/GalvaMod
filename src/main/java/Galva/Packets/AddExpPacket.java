package Galva.Packets;

import Galva.Leveling.ExpText;
import Galva.Leveling.LevelUpText;
import Galva.Menus.GalvaFormManager;
import Galva.Menus.GalvaInventoryButton;
import Galva.Status.GalvaPlayer;
import Galva.Status.GalvaPlayers;
import necesse.engine.Screen;
import necesse.engine.network.NetworkPacket;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;
import necesse.engine.sound.SoundEffect;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.GameResources;
import necesse.level.maps.Level;

public class AddExpPacket extends Packet {

    public final int value;
    public final String Name;
    public final String mobLocation;

    // MUST HAVE - Used for construction in registry
    public AddExpPacket(byte[] data) {
        super(data);
        PacketReader reader = new PacketReader(this);
        // Important that it's same order as written in
        value = reader.getNextInt();
        mobLocation = reader.getNextString();
        Name = reader.getNextString();
    }

    public AddExpPacket(int value, String mobLocation, String Name) {
        this.value = value;
        this.mobLocation = mobLocation;
        this.Name = Name;
        PacketWriter writer = new PacketWriter(this);

        writer.putNextInt(value);
        writer.putNextString(mobLocation);
        writer.putNextString(Name);
    }

    @Override
    public void processClient(NetworkPacket packet,  Client client) {
        System.out.println("[CLIENT] Adding XP " + value + " to player " + Name);
        PlayerMob playerMob = client.getPlayer();
        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(client.getPlayer());

        //Returns true on level up
        Level level = client.getLevel();
        if (player.AddXP(value)) 
        {
            try
            {
                LevelUpText text = new LevelUpText(playerMob.getX(), playerMob.getY());
                text.hoverTime = 1000;
                if (level != null){
                    level.hudManager.addElement(text);
                }
                Screen.playSound(GameResources.jingle, SoundEffect.effect(playerMob));
            }
            catch (Exception e)
            {

            }

            player.SendDeleteAttributesPacket(client);
            player.Level.value++;
            player.SendAddAttributesPacket(client);
        }
        else
        {
            try
            {
                ExpText expText = new ExpText(playerMob.getX(), playerMob.getY(), value);
                expText.hoverTime = 1000;
                if (level != null){
                    level.hudManager.addElement(expText);
                }
            }
            catch (Exception e)
            {

            }
        }

        if (GalvaInventoryButton.galvaForm.IsDisplayed()) {
            GalvaInventoryButton.galvaForm.Refresh(player);
        }

        //Update on server too! (Shouldn't be done like this, but I can't be fucked to refactor it).
        client.network.sendPacket(new AddExpPacket(value, mobLocation, Name));
    }

    @Override
    public void processServer(NetworkPacket packet, Server server, ServerClient client) {
        System.out.println("[SERVER] Adding XP " + value + " to player " + Name);
        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(Name);

        if (player.AddXP(value)) {
            player.Level.value++;
        }
    }
}