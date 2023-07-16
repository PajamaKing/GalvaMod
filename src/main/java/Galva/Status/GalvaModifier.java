package Galva.Status;

import necesse.engine.modifiers.Modifier;

public class GalvaModifier
{
    public Modifier<Float> modifier;
    public Float value;

    public GalvaModifier(Modifier<Float> M, Float V)
    {
        modifier = M;
        value = V;
    }
}
