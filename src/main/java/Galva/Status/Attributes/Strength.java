package Galva.Status.Attributes;

import Galva.Status.GalvaBuff;
import Galva.Status.GalvaModifier;
import necesse.entity.mobs.buffs.BuffModifiers;

public class Strength extends GalvaAttribute {
    public Strength(int value){
        BuffID = "Strength";

        buffModifiers.add(new GalvaModifier(BuffModifiers.MELEE_ATTACK_SPEED, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.MELEE_DAMAGE, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.MELEE_CRIT_CHANCE, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.MINING_SPEED, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.ARMOR, 0.01f));
        buff = new GalvaBuff(buffModifiers, value);
    }

    public Strength(){
        BuffID = "Strength";
    }
}
