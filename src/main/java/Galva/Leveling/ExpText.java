package Galva.Leveling;

import necesse.engine.util.GameRandom;
import necesse.gfx.gameFont.FontOptions;
import necesse.level.maps.hudManager.floatText.FloatTextFade;

import java.awt.*;

public class ExpText extends FloatTextFade
{
    public ExpText(int x, int y, int value) {
        super(x + (int)(GameRandom.globalRandom.nextGaussian() * 8.0), y + (int)(GameRandom.globalRandom.nextGaussian() * 4.0), (new FontOptions(16)).outline().color(Color.white));
        this.avoidOtherText = true;
        this.hoverTime = 1000;
        this.setText(value + " Exp");
    }
}
