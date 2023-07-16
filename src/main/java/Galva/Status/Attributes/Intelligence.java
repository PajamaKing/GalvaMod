package Galva.Status.Attributes;

import Galva.Status.GalvaBuff;
import Galva.Status.GalvaModifier;
import necesse.entity.mobs.buffs.BuffModifiers;

public class Intelligence extends GalvaAttribute {
    public Intelligence(int value){
        BuffID = "Intelligence";

        buffModifiers.add(new GalvaModifier(BuffModifiers.MAGIC_ATTACK_SPEED, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.MAGIC_DAMAGE, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.MAGIC_CRIT_CHANCE, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.FIRE_DAMAGE, -0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.FROST_DAMAGE, -0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.POISON_DAMAGE, -0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.PROJECTILE_VELOCITY, 0.01f));
        buff = new GalvaBuff(buffModifiers, value);
    }

    public Intelligence(){
        BuffID = "Intelligence";
    }
}
