package Galva.Status.Attributes;

import Galva.Status.GalvaModifier;
import Galva.Status.GalvaBuff;

import java.util.ArrayList;

public class GalvaAttribute
{
    public ArrayList<GalvaModifier> buffModifiers = new ArrayList<GalvaModifier>();
    public String BuffID;
    public GalvaBuff buff;

    public int value = 1;

    public String GetBuffValueId(int value){
        return BuffID + "_" + value;
    }

    public String GetBuffValueId(){
        return BuffID + "_" + value;
    }
}
