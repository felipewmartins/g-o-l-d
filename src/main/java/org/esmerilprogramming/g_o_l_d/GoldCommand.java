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
package org.esmerilprogramming.g_o_l_d;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.esmerilprogramming.g_o_l_d.graphics.GameView;
import org.esmerilprogramming.g_o_l_d.core.GoldRunner;
import org.esmerilprogramming.g_o_l_d.graphics.GoldGraphics;
import org.esmerilprogramming.g_o_l_d.input.KeyboardInput;
import org.esmerilprogramming.g_o_l_d.core.GoldCore;
import org.esmerilprogramming.g_o_l_d.core.Worker;
import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandOperation;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.graphics.AeshGraphicsConfiguration;
import org.jboss.aesh.graphics.Graphics;
import org.jboss.aesh.graphics.GraphicsConfiguration;
import org.jboss.aesh.terminal.Key;
import org.jboss.aesh.terminal.Shell;
import org.jboss.aesh.util.ANSI;

/**
 * @author eprogramming
 */
@CommandDefinition(name = "gold", description = "")
public class GoldCommand implements Command<CommandInvocation> {

  @Override
  public CommandResult execute(CommandInvocation ci) throws IOException, InterruptedException {

    Shell shell = ci.getShell();

    GameView view = new GameView(shell);
    view.prepareScreen();
    view.displayInitialScreen();

    CommandOperation co = ci.getInput();
    if (co.getInputKey() == Key.y) {

      view.clear();
      view.displayScenario(); 
      GoldCore core = new GoldCore(view);

      Worker worker = new Worker(ci, core);
      worker.start();
      worker.join();

    }
    else {
      view.clear();
      //goldGraphics.drawScoreScreen(shell);
      Thread.sleep(2000);
      //goldGraphics.cleanup();
      //gameModeOff(shell);
      ci.stop();
    }

    //goldGraphics.drawScoreScreen(shell);
    Thread.sleep(5000);
    //goldGraphics.cleanup();
    //gameModeOff(shell);
    ci.stop();
    return CommandResult.SUCCESS;
  }

  private void gameModeOff(Shell shell) throws IOException {
    //goldGraphics.cleanup();
  }

}
