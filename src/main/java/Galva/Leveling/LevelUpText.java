package Galva.Leveling;

import necesse.engine.util.GameRandom;
import necesse.gfx.gameFont.FontOptions;
import necesse.inventory.InventoryItem;
import necesse.level.maps.hudManager.floatText.FloatTextFade;

import java.awt.*;

public class LevelUpText extends FloatTextFade
{
    public LevelUpText(int x, int y) {
        super(x + (int)(GameRandom.globalRandom.nextGaussian() * 8.0), y + (int)(GameRandom.globalRandom.nextGaussian() * 4.0), (new FontOptions(16)).outline().color(Color.GREEN));
        this.avoidOtherText = true;
        this.hoverTime = 1000;
        this.setText("Level Up!");
    }
}
