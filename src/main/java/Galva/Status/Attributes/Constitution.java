package Galva.Status.Attributes;

import Galva.Status.GalvaBuff;
import Galva.Status.GalvaModifier;
import necesse.entity.mobs.buffs.BuffModifiers;

public class Constitution extends GalvaAttribute
{
    public Constitution(int value){
        BuffID = "Constitution";
        buffModifiers.add(new GalvaModifier(BuffModifiers.MAX_HEALTH, 0.025f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.INCOMING_DAMAGE_MOD, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.KNOCKBACK_OUT, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.HEALTH_REGEN, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.COMBAT_HEALTH_REGEN, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.STAMINA_CAPACITY, 0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.STAMINA_USAGE, -0.01f));
        buffModifiers.add(new GalvaModifier(BuffModifiers.STAMINA_REGEN, 0.01f));
        buff = new GalvaBuff(buffModifiers, value);
    }

    public Constitution(){
        BuffID = "Constitution";
    }
}