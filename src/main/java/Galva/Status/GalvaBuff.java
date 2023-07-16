package Galva.Status;

import Galva.Status.Attributes.GalvaLevel;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;

import java.util.ArrayList;

import static Galva.Galva.BaseStatLevel;

public class GalvaBuff extends Buff {
    ArrayList<GalvaModifier> buffModifiers;
    int value;
    public GalvaBuff(ArrayList<GalvaModifier> buffModifiers, int value)
    {
        this.buffModifiers = buffModifiers;
        this.value = value;
        canCancel = false;
        //isPassive = true;
        isVisible = false;
        shouldSave = true;
    }

    @Override
    public void init(ActiveBuff activeBuff)
    {
        for (int i = 0; i < buffModifiers.size(); i++) {

            if (buffModifiers.get(i).modifier == BuffModifiers.INCOMING_DAMAGE_MOD)
            {
                activeBuff.setModifier(buffModifiers.get(i).modifier, 1 - buffModifiers.get(i).value * (value - BaseStatLevel));
            }
            else
            {
                activeBuff.setModifier(buffModifiers.get(i).modifier, buffModifiers.get(i).value * (value - BaseStatLevel));
            }
        }
    }

    @Override
    public void serverTick(ActiveBuff buff)
    {
    }

    @Override
    public void clientTick(ActiveBuff buff)
    {
    }
}
