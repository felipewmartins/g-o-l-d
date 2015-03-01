package org.esmerilprogramming.g_o_l_d.core;

import org.esmerilprogramming.g_o_l_d.sounds.Sounds;
import org.esmerilprogramming.g_o_l_d.graphics.GameView;
import org.esmerilprogramming.g_o_l_d.sprite.Gold;
import org.esmerilprogramming.g_o_l_d.sprite.Player;
import java.util.Random;

public class GoldCore {

  private GameView view;
  private Timer timer;

  private Gold g1 = new Gold(14, 8);
  private Gold g2 = new Gold(66, 8);
  private Gold g3 = new Gold(14, 18);
  private Gold g4 = new Gold(66, 18);

  private Player player;

  private int pastGold;

  public GoldCore(GameView view) {
    this.view = view;
    randomGold();
    player = new Player();
    view.displayPlayer(player);
    timer = new Timer(view);
    timer.start();
    Sounds.playMusic();
  }

  public void moveRight() {
    System.out.println("up");
  }

  public void moveLeft() {
    System.out.println("up");
  }

  public void moveUp() {
    System.out.println("up");
  }

  public void moveDown() {
    System.out.println("up");
  }

  public void gameOver() {
    System.out.println("up");
  }

  private void randomGold() {
    int raffle = new Random().nextInt(4) + 1;
    if (raffle == 1 && raffle != pastGold) {
      view.displayGold(g1);
      pastGold = 1;
    }
    else if (raffle == 2 && raffle != pastGold) {
      view.displayGold(g2);
      pastGold = 2;
    }
    else if (raffle == 3 && raffle != pastGold) {
      view.displayGold(g3);
      pastGold = 3;
    }
    else if (raffle == 4 && raffle != pastGold) {
      view.displayGold(g4);
      pastGold = 4;
    }
    else {
      randomGold();
    }
  }

  private void checkGetGold() {
    Gold currentGold;
    if (pastGold == 1) {
      currentGold = g1;
    }
    else if (pastGold == 2) {
      currentGold = g2;
    }
    else if (pastGold == 3) {
      currentGold = g3;
    }
    else {
      currentGold = g4;
    }

    if (player.getPositionX() == currentGold.getPositionX() && player.getPositionY() == currentGold.getPositionY()) {
      view.displayScore(player.increaseScore());
      //Sounds.playSound();
      // goldGraphics.drawGoldPlaces();
      randomGold();
    }
  }

}
