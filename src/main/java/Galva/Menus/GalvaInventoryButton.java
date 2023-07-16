package Galva.Menus;

import Galva.Galva;
import necesse.engine.Settings;
import necesse.engine.localization.message.StaticMessage;
import necesse.gfx.forms.MainGameFormManager;
import necesse.gfx.forms.components.FormContentIconButton;
import necesse.gfx.forms.components.FormInputSize;
import necesse.gfx.ui.ButtonColor;
import necesse.engine.network.client.Client;


public class GalvaInventoryButton extends FormContentIconButton {
    public static GalvaForm galvaForm = new GalvaForm();
    public GalvaInventoryButton(MainGameFormManager formManager, Client client) {
        super(0, 0, FormInputSize.SIZE_32, ButtonColor.BASE, Settings.UI.priority_high, new StaticMessage("Attributes Menu"));
        galvaForm.FM = formManager;
        onClicked(e ->
                {
                    if (!galvaForm.IsDisplayed()) {
                        if (galvaForm != null)
                        {
                            galvaForm.dispose();
                        }
                        galvaForm = new GalvaForm();
                        galvaForm.FM = formManager;

                        formManager.addComponent(galvaForm);
                        galvaForm.Open(client);
                    } else {
                        formManager.removeComponent(galvaForm);
                    }
                }
        );
    }
}
