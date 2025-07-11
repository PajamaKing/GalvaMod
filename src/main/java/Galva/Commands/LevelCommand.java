package Galva.Commands;

import Galva.Status.GalvaPlayer;
import Galva.Status.GalvaPlayers;
import necesse.engine.commands.CmdParameter;
import necesse.engine.commands.CommandLog;
import necesse.engine.commands.ModularChatCommand;
import necesse.engine.commands.PermissionLevel;
import necesse.engine.commands.parameterHandlers.*;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;
import necesse.engine.commands.parameterHandlers.StoredPlayerParameterHandler.StoredPlayer;

public class LevelCommand extends ModularChatCommand {

    public LevelCommand() {
        super("setLevel", "Command to set player level", PermissionLevel.ADMIN, false,
                // Parameter handlers are used for autocomplete, and will parse the input into their type
                new CmdParameter("player", new StoredPlayerParameterHandler(), false),
                new CmdParameter("level", new IntParameterHandler(), false));
    }

    @Override
    public void runModular(Client client, Server server, ServerClient serverClient, Object[] args, String[] errors, CommandLog commandLog) {

        try
        {
            commandLog.add(args.toString());
            StoredPlayer storedPlayer = (StoredPlayer) args[0]; // Optional parameters are still parsed
            Integer level = (Integer) args[1];

            // Do stuff with the parsed args
            if (storedPlayer == null) {
                commandLog.add("No given player");
                return;
            }

            if (!GalvaPlayers.playerExists(storedPlayer.name))
            {
                commandLog.add("No player matching name");
                return;
            }

            GalvaPlayer player = GalvaPlayers.getPlayerByName(storedPlayer.name);

            player.Level.value = level;
            player.SendGetAttributesPacket(serverClient, storedPlayer.name);
        }catch (Exception e)
        {
            commandLog.add(e.getMessage());
        }
    }
}