package Galva.Status.Attributes;

import Galva.Status.GalvaBuff;
import Galva.Status.GalvaModifier;
import necesse.entity.mobs.buffs.BuffModifiers;

public class GalvaLevel extends GalvaAttribute {

    public GalvaLevel(int value){
        BuffID = "Level";
        buffModifiers.add(new GalvaModifier(BuffModifiers.SPEED, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.ACCELERATION, 0.01f));
        buff = new GalvaBuff(buffModifiers, value);
    }

    public GalvaLevel(){
        BuffID = "Level";
        value = 1;
    }
}
