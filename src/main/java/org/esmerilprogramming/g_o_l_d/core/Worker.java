package org.esmerilprogramming.g_o_l_d.core;

import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.console.command.CommandOperation;
import org.jboss.aesh.terminal.Key;

public class Worker extends Thread {

  private volatile  boolean running = true;

  private CommandInvocation ci;
  private GoldCore core;

  public Worker(CommandInvocation ci, GoldCore core) {
    this.ci = ci;
    this.core = core;
  }

  public void run() {

    while(running) {
      CommandOperation co = null;
      try {
        co = ci.getInput();
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
      if (co.getInputKey() == Key.UP) {
        core.moveUp();
      } else if (co.getInputKey() == Key.DOWN) {
        core.moveDown();
      } else if (co.getInputKey() == Key.LEFT) {
        core.moveLeft();
      } else if (co.getInputKey() == Key.RIGHT) {
        core.moveRight();
      } else if (co.getInputKey() == Key.q || co.getInputKey() == Key.ESC) {
        core.gameOver();
      }
      try {
        Thread.sleep(59);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void shutdown() {
    running = false;
  }

}
