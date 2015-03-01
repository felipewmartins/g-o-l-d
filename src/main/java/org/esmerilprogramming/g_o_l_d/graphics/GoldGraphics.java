/*
 * Copyright 2014 EsmerilProgramming.
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

import org.esmerilprogramming.g_o_l_d.sprite.Gold;
import org.esmerilprogramming.g_o_l_d.sprite.Player;
import org.jboss.aesh.graphics.Graphics;
import org.jboss.aesh.terminal.Color;
import org.jboss.aesh.terminal.Shell;
import org.jboss.aesh.terminal.TerminalColor;

/**
 * @author eprogramming
 */
public class GoldGraphics {

  private Graphics graphics;

  public static final TerminalColor WORLD_COLOR = new TerminalColor(Color.BLUE, Color.DEFAULT);
  private static final TerminalColor GOLD_COLOR = new TerminalColor(Color.DEFAULT, Color.YELLOW);

  public GoldGraphics(Graphics graphics) {
    this.graphics = graphics;
  }

  public void drawScoreScreen(Shell shell) {
    graphics.setColor(WORLD_COLOR);
    graphics.drawString("Best score: 9999.", shell.getSize().getCenterWidth(), shell.getSize().getCenterHeight());
  }

  public void repaintGold(Gold g) {
    graphics.setColor(GOLD_COLOR);
    graphics.fillRect(g.getPositionX(), g.getPositionY(), Gold.WIDTH, Gold.HEIGHT);
    graphics.setColor(WORLD_COLOR);
  }

  public Graphics getGraphics() {
    return graphics;
  }

  public void cleanup() {
    graphics.clearAndShowCursor();
  }

}
