/*
 * EsmerilProgramming.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.esmerilprogramming.g_o_l_d.graphics

import java.io.IOException
import org.jboss.aesh.graphics.Graphics
import org.jboss.aesh.terminal.TerminalColor
import org.jboss.aesh.terminal.Color
import org.jboss.aesh.terminal.Shell
import org.jboss.aesh.graphics.AeshGraphicsConfiguration
import org.jboss.aesh.util.ANSI
import org.esmerilprogramming.g_o_l_d.sprite.Gold
import org.esmerilprogramming.g_o_l_d.sprite.Player

/**
 * @author eprogramming
 */
class GameView {

  private Shell shell
  private Graphics g

  private int centerWidth
  private int centerHeight
  private int screenWidth
  private int screenHeight

  private static final TerminalColor WORLD_COLOR = new TerminalColor(Color.BLUE, Color.DEFAULT)
  private static final TerminalColor GOLD_COLOR = new TerminalColor(Color.DEFAULT, Color.YELLOW)

  GameView(Shell shell) {
    this.shell = shell
    centerWidth = shell.size.centerWidth
    centerHeight = shell.size.centerHeight
    screenWidth = shell.size.width
    screenHeight = shell.size.height
    g = new AeshGraphicsConfiguration(shell).graphics
  }

  void displayInitialScreen() {
    g.setColor(WORLD_COLOR)
    displayAtCenter("Use vim keys: h, j, k, l")
    displayAtCenterPlusY("While playing, press 'q' or 'ESC' to quit", 2)
    displayAtCenterPlusY("Ready ? (y/n)", 4)
  }

  private void displayAtCenter(String s) {
    g.drawString(s, centerWidth, centerHeight)
  }

  private void displayAtCenterPlusY(String s, int y) {
    g.drawString(s, centerWidth, centerHeight + y)
  }

  private void displayAtCenterPlusX(String s, int x) {
    g.drawString(s, centerWidth + x, centerHeight)
  }

  void displayCounter(int counter) {
    if (counter >= 10) {
      g.drawString("" + counter, screenWidth - 2, 1)
    } else {
      g.drawString(" " + counter, screenWidth - 2, 1)
    }
  }

  void displayGold(Gold gold) {
    g.setColor(GOLD_COLOR)
    g.fillRect(gold.positionX, gold.positionY, Gold.WIDTH, Gold.HEIGHT)
    g.setColor(WORLD_COLOR)
  }

  void displayGameOver() {
    g.setColor(GOLD_COLOR)
      for (int i = 0; i < screenWidth; i++) {
        for (int j = 0; j < screenHeight; j++) {
          g.fillRect(i,j,1,1)
            try {
              Thread.sleep(1)
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt()
                break
            }
        }
        g.fillRect(i,i,1,1)
          try {
            Thread.sleep(1)
          } catch(InterruptedException e) {
            Thread.currentThread().interrupt()
              break
          }
      }
  }

  void prepareScreen() {
    clear()
      shell.out().print(ANSI.CURSOR_SAVE)
      shell.out().print(ANSI.CURSOR_HIDE)
      shell.enableAlternateBuffer()
  }

  void clear() {
    try {
      shell.clear()
    } catch(IOException e) {
      e.printStackTrace()
    }
  }

  //void destroyScreen() {
  //  g.clearAndShowCursor()
  //  shell.enableMainBuffer()
  //}

  void displayScenario() {
    g.setColor(WORLD_COLOR)
    g.drawString("GOLD:", 0, 1)
    g.drawString("STEPS:", 13, 1)
    g.drawString("SCORE:", 25, 1)
    g.drawString("TIME REMAINING:", screenWidth - 17, 1)
    g.drawLine(0, 2, screenWidth, 2)
    g.drawLine(0, screenHeight - 1, screenWidth, screenHeight - 1)
    displayGoldPlaces()
  }

  void displayGoldPlaces() {
    g.drawRect(8, 5, 14, 5)
    g.drawRect(60, 5, 14, 5)
    g.drawRect(8, 15, 14, 5)
    g.drawRect(60, 15, 14, 5)
  }

  void displayGold(int gold) {
    g.drawString("" + gold, 6, 1)
  }

  void displayScore(int score) {
    g.drawString("" + score, 31, 1)
  }

  void displayPlayer(Player player) {
    player.positionX = (screenWidth / 2 - 2)
    player.positionY = (screenHeight / 2 - 2)
    g.drawString(player.CHARACTER, player.positionX, player.positionY)
  }

  private void increaseSteps(Player p) {
    g.drawString("" + p.increaseSteps(), 19, 1)
  }

  void playerMoveUp(Player p) {
    int oldStep = p.positionY
    g.drawString(Player.CHARACTER, p.positionX, p.decreasePositionY())
    g.drawString(" ", p.positionX, oldStep)
    increaseSteps(p)
  }

  void playerMoveDown(Player p) {
    int oldStep = p.getPositionY()
    g.drawString(Player.CHARACTER, p.positionX, p.increasePositionY())
    g.drawString(" ", p.positionX, oldStep)
    increaseSteps(p)
  }

  void playerMoveLeft(Player p) {
    int oldStep = p.positionX
    g.drawString(Player.CHARACTER, p.decreasePositionX(), p.positionY)
    g.drawString(" ", oldStep, p.positionY)
    increaseSteps(p)
  }

  void playerMoveRight(Player p) {
    int oldStep = p.positionX
    g.drawString(Player.CHARACTER, p.increasePositionX(), p.positionY)
    g.drawString(" ", oldStep, p.positionY)
    increaseSteps(p)
  }

}

