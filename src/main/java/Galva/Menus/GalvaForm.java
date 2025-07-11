package Galva.Menus;

import Galva.Status.Attributes.GalvaAttribute;
import Galva.Status.GalvaPlayers;
import Galva.Status.GalvaPlayer;
import necesse.engine.input.InputEvent;
import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.engine.network.client.Client;
import necesse.engine.window.GameWindow;
import necesse.engine.window.WindowManager;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.fairType.FairType;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.FormManager;
import necesse.gfx.forms.components.*;
import necesse.gfx.gameFont.FontOptions;

import java.awt.*;

import static Galva.Galva.MaxLevel;
import static Galva.Galva.MaxStatLevel;

public class GalvaForm extends Form {
    FormTextButton Con;
    FormTextButton Str;
    FormTextButton Dex;
    FormTextButton Int;
    FormTextButton Wis;
    FormTextBox RP;
    FormTextBox Lev;
    FormTextBox Exp;
    public FormManager FM;
    Client client;
    public GalvaForm() {
        super("Attributes", 500, 330);

        GameWindow window = WindowManager.getWindow();
        setPosition(window.getHudWidth()/2 - 250,150);

    }

    @Override
    public void handleInputEvent(InputEvent event, TickManager tickManager, PlayerMob perspective)
    {
        super.handleInputEvent(event, tickManager, perspective);
        if (event.isKeyboardEvent())
        {
            if (event.getID() == 256)
            {
                FM.removeComponent(this);
            }
        }
    }

    public void Open(Client client){
        //Needed to send packets
        this.client = client;

        GalvaPlayer player = GalvaPlayers.getPlayerByName(client.getPlayer().playerName);
        //Load text if not loaded.

        //Text
        Con = new FormTextButton ("Constitution (" + player.Constitution.value + ")",
                "Everything related to Survivability." , 10, 10, 360);
        Str = new FormTextButton ("Strength (" + player.Strength.value + ")",
                "Everything related to Close Combat." , 10, 70, 360);
        Dex = new FormTextButton ("Dexterity (" + player.Dexterity.value + ")",
                "Everything related to Ranged and Armor Pen." , 10, 130, 360);
        Int = new FormTextButton ("Intelligence (" + player.Intelligence.value + ")",
                "Everything related to Magic and Elemental Resistance." , 10, 190, 360);
        Wis = new FormTextButton ("Wisdom (" + player.Wisdom.value + ")",
                "Everything related to Summons." , 10, 250, 360);

        RP = new FormTextBox(new FontOptions(12), FairType.TextAlign.LEFT, Color.BLACK, 10, 310, 200);
        Exp = new FormTextBox(new FontOptions(12), FairType.TextAlign.LEFT, Color.BLACK, 200, 310, 200);
        Lev = new FormTextBox(new FontOptions(12), FairType.TextAlign.LEFT, Color.BLACK, 430, 310, 200);
        RP.allowTyping = false;
        Exp.allowTyping = false;
        Lev.allowTyping = false;
        RP.setText("Remaining Points: " + player.GetFreeStatusPoints());
        if (player.Level.value > 50)
        {
            Exp.setText("Experience: MAX/MAX");
        }
        else
        {
            Exp.setText("Experience: " + player.Experience + "/" + player.GetNeededExp());
        }
        Lev.setText("Level: " + player.Level.value);

        addComponent(Con);
        addComponent(Str);
        addComponent(Dex);
        addComponent(Int);
        addComponent(Wis);
        addComponent(RP);
        addComponent(Exp);
        addComponent(Lev);

        //-+Buttons
        FormTextButton PlusCon = new FormTextButton ("+", null , 380, 10, 50);
        FormTextButton MinusCon = new FormTextButton ("-", null , 440, 10, 50);
        FormTextButton PlusStr = new FormTextButton ("+", null , 380, 70, 50);
        FormTextButton MinusStr = new FormTextButton ("-", null , 440, 70, 50);
        FormTextButton PlusDex = new FormTextButton ("+", null , 380, 130, 50);
        FormTextButton MinusDex = new FormTextButton ("-", null , 440, 130, 50);
        FormTextButton PlusInt = new FormTextButton ("+", null , 380, 190, 50);
        FormTextButton MinusInt = new FormTextButton ("-", null , 440, 190, 50);
        FormTextButton PlusWis = new FormTextButton ("+", null , 380, 250, 50);
        FormTextButton MinusWis = new FormTextButton ("-", null , 440, 250, 50);

        PlusCon.onClicked(e -> Add(player.Constitution, player));
        MinusCon.onClicked(e -> Subtract(player.Constitution, player));
        PlusStr.onClicked(e -> Add(player.Strength, player));
        MinusStr.onClicked(e -> Subtract(player.Strength, player));
        PlusDex.onClicked(e -> Add(player.Dexterity, player));
        MinusDex.onClicked(e -> Subtract(player.Dexterity, player));
        PlusInt.onClicked(e -> Add(player.Intelligence, player));
        MinusInt.onClicked(e -> Subtract(player.Intelligence, player));
        PlusWis.onClicked(e -> Add(player.Wisdom, player));
        MinusWis.onClicked(e -> Subtract(player.Wisdom, player));

        addComponent(PlusCon);
        addComponent(MinusCon);
        addComponent(PlusStr);
        addComponent(MinusStr);
        addComponent(PlusDex);
        addComponent(MinusDex);
        addComponent(PlusInt);
        addComponent(MinusInt);
        addComponent(PlusWis);
        addComponent(MinusWis);
    }

    public void Add(GalvaAttribute status, GalvaPlayer player)
    {
        if (player.GetFreeStatusPoints() > 0 && status.value < MaxStatLevel){
            System.out.println(client.getPlayer().playerName);
            player.SendDeleteAttributesPacket(client);
            status.value++;
            player.SendAddAttributesPacket(client);
            Refresh(player);
        }
    }

    public void Subtract(GalvaAttribute status, GalvaPlayer player)
    {
        if (status.value > 1){
            System.out.println(client.getPlayer().playerName);
            player.SendDeleteAttributesPacket(client);
            status.value--;
            player.SendAddAttributesPacket(client);
            Refresh(player);
        }
    }

    public void Refresh(GalvaPlayer player)
    {
        Con.setText("Constitution (" + player.Constitution.value + ")");
        Str.setText("Strength (" + player.Strength.value + ")");
        Dex.setText("Dexterity (" + player.Dexterity.value + ")");
        Int.setText("Intelligence (" + player.Intelligence.value + ")");
        Wis.setText("Wisdom (" + player.Wisdom.value + ")");
        RP.setText("Remaining Points: " + player.GetFreeStatusPoints());
        if (player.Level.value == MaxLevel)
        {
            Exp.setText("Experience: MAX/MAX");
        }
        else
        {
            Exp.setText("Experience: " + player.Experience + "/" + player.GetNeededExp());
        }
        Lev.setText("Level: " + player.Level.value);
    }

    public boolean IsDisplayed()
    {
        return FM.hasComponent(this);
    }
}
