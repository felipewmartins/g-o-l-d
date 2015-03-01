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
package org.esmerilprogramming.g_o_l_d.graphics;

import java.io.IOException;
import org.jboss.aesh.graphics.Graphics;
import org.jboss.aesh.terminal.TerminalColor;
import org.jboss.aesh.terminal.Color;
import org.jboss.aesh.terminal.Shell;
import org.jboss.aesh.graphics.AeshGraphicsConfiguration;
import org.jboss.aesh.util.ANSI;

/**
 * @author eprogramming
 */
public class GameView {

  private Shell shell;
  private Graphics g;

  int centerWidth;
  int centerHeight;
  int screenWidth;
  int screenHeight;

  public GameView(Shell shell) {
    this.shell = shell;
    centerWidth = shell.getSize().getCenterWidth();
    centerHeight = shell.getSize().getCenterHeight();
    screenWidth = shell.getSize().getWidth();
    screenHeight = shell.getSize().getHeight();
    g = new AeshGraphicsConfiguration(shell).getGraphics();
  }

  public void displayInitialScreen() {
    g.setColor(new TerminalColor(Color.BLUE, Color.DEFAULT));
    displayAtCenter("Use keyboard: UP,DOWN,LEFT, RIGHT");
    displayAtCenterPlusY("While playing, press 'q' or 'ESC' to quit.", 2);
    displayAtCenterPlusY("Ready ? (y/n)", 4);
  }

  private void displayAtCenter(String s) {
    g.drawString(s, centerWidth, centerHeight);
  }

  private void displayAtCenterPlusY(String s, int y) {
    g.drawString(s, centerWidth, centerHeight + y);
  }

  private void displayAtCenterPlusX(String s, int x) {
    g.drawString(s, centerWidth + x, centerHeight);
  }

  public void displayCounter(int counter) {
    if (counter >= 10) {
      g.drawString("" + counter, 20, 1);
    } else {
      g.drawString(" " + counter, 20, 1);
    }
  }

  public void prepareScreen() {
    clear();
    shell.out().print(ANSI.CURSOR_SAVE);
    shell.out().print(ANSI.CURSOR_HIDE);
    shell.enableAlternateBuffer();
  }

  public void clear() {
    try {
      shell.clear();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public void displayScenario() {
    g.setColor(new TerminalColor(Color.BLUE, Color.DEFAULT));
    g.drawString("SCORE:", 0, 1);
    g.drawString("STEPS:", 13, 1);
    g.drawString("TIME REMAINING:", screenWidth - 17, 1);
    g.drawLine(0, 2, screenWidth, 2);
    g.drawLine(0, screenHeight - 1, screenWidth, screenHeight - 1);
    g.drawRect(8, 5, 14, 5);
    g.drawRect(60, 5, 14, 5);
    g.drawRect(8, 15, 14, 5);
    g.drawRect(60, 15, 14, 5);
  }

}

