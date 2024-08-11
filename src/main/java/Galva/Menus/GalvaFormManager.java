package Galva.Menus;

import Galva.Status.GalvaPlayer;
import Galva.Status.GalvaPlayers;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.client.Client;
import necesse.gfx.forms.MainGameFormManager;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = MainGameFormManager.class, name="setup", arguments = {})
public class GalvaFormManager
{
    @Advice.OnMethodExit
    public static void onExit(@Advice.This MainGameFormManager formManager,
                              @Advice.FieldValue(value = "client") Client client) {
        GalvaInventoryButton btn = new GalvaInventoryButton(formManager, client);
        formManager.rightQuickbar.addButton(btn);
        formManager.rightQuickbar.updateButtons(true);

    }
}
