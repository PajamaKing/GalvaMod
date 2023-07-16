package Galva.Status.Attributes;

import Galva.Status.GalvaBuff;
import Galva.Status.GalvaModifier;
import necesse.entity.mobs.buffs.BuffModifiers;

public class Dexterity extends GalvaAttribute {
    public Dexterity(int value){
        BuffID = "Dexterity";
        buffModifiers.add(new GalvaModifier(BuffModifiers.RANGED_ATTACK_SPEED, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.RANGED_DAMAGE, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.RANGED_CRIT_CHANCE, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.PROJECTILE_VELOCITY, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.ARMOR_PEN, 0.01f));
        buff = new GalvaBuff(buffModifiers, value);
    }

    public Dexterity(){
        BuffID = "Dexterity";
    }
}
