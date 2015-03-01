package org.esmerilprogramming.g_o_l_d.core;

import org.esmerilprogramming.g_o_l_d.graphics.GameView;

public class GoldCore {

  private GameView view;
  private Timer timer;

  public GoldCore(GameView view) {
    this.view = view;
    timer = new Timer(view);
    timer.start();
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

}
