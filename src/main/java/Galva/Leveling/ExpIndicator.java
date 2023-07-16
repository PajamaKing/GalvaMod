package Galva.Leveling;

import necesse.engine.util.GameRandom;
import necesse.gfx.gameFont.FontOptions;
import necesse.level.maps.hudManager.floatText.FloatTextFade;

import java.awt.*;

public class ExpIndicator extends FloatTextFade
{
    public ExpIndicator(int x, int y, int value) {
        super(x + (int)(GameRandom.globalRandom.nextGaussian() * 8.0), y + (int)(GameRandom.globalRandom.nextGaussian() * 4.0), (new FontOptions(10)).outline().color(Color.white));
        this.avoidOtherText = true;
        this.hoverTime = 1000;
        this.setText(value + " Exp");
    }
}
