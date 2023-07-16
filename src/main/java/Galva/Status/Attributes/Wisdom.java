package Galva.Status.Attributes;

import Galva.Status.GalvaBuff;
import Galva.Status.GalvaModifier;
import necesse.entity.mobs.buffs.BuffModifiers;

public class Wisdom extends GalvaAttribute {
    public Wisdom(int value){
        BuffID = "Wisdom";
        buffModifiers.add(new GalvaModifier(BuffModifiers.SUMMON_ATTACK_SPEED, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.SUMMON_DAMAGE, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.SUMMON_CRIT_CHANCE, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.SUMMONS_SPEED, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.SUMMONS_TARGET_RANGE, 0.01f));
        buff = new GalvaBuff(buffModifiers, value);
    }

    public Wisdom(){
        BuffID = "Wisdom";
    }
}
