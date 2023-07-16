package Galva.Leveling;

import Galva.Status.Attributes.GalvaAttribute;

public class LevelHolder
{
    public int Experience;
    public GalvaAttribute Level;

    public boolean AddXP(int exp)
    {
        Experience += exp;
        System.out.println(Experience);
        int neededExp = GetNeededExp();
        boolean shouldLevel = Level.value < Level.value + (int)Math.floor(Experience / neededExp);

        if (shouldLevel)
        {
            System.out.println("Level up!");
            Experience = 0;
            return true;
        }
        return false;
    }

    public int GetNeededExp()
    {
        return (int)Math.floor(1000 + (Level.value - 1) * 5000);
    }
}
