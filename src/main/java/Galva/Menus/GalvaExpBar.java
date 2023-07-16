//package Galva.Menus;
//
//import Galva.Status.GalvaPlayer;
//import Galva.Status.GalvaPlayers;
//import necesse.engine.Screen;
//import necesse.engine.Settings;
//import necesse.engine.modLoader.annotations.ModMethodPatch;
//import necesse.engine.tickManager.TickManager;
//import necesse.entity.mobs.PlayerMob;
//import necesse.gfx.drawOptions.ProgressBarDrawOptions;
//import necesse.gfx.forms.MainGameFormManager;
//import necesse.gfx.gameTexture.GameTexture;
//import net.bytebuddy.asm.Advice;
//
//import java.awt.*;
//
//@ModMethodPatch(target = MainGameFormManager.class, name="draw", arguments = {TickManager.class, PlayerMob.class})
//public class GalvaExpBar
//{
//    public ProgressBarDrawOptions ProgressBar;
//    public boolean IsDrawn;
//
//    @Advice.OnMethodEnter()
//    public void DrawExpBar(@Advice.Argument(1) PlayerMob playerMob)
//    {
//        System.out.println("This is working!");
//        GalvaPlayer player = GalvaPlayers.getCurrentPlayer(playerMob);
//        float xpPerc = player.Experience / player.GetNeededExp();
//        int posX = Screen.getHudWidth() - (int) (Screen.getHudWidth() / 1.25);
//        int posY = Screen.getHudHeight() - (int) (Screen.getHudHeight() / 1.1);
//        ProgressBarDrawOptions PBDO = new ProgressBarDrawOptions(Settings.UI.healthbar_big_background, Screen.getHudWidth() / 5);
//        ProgressBar = PBDO.addBar(Settings.UI.healthbar_big_fill, xpPerc)
//                .color(Color.yellow)
//                .end();
//        ProgressBar.draw(posX, posY);
//    }
//}
