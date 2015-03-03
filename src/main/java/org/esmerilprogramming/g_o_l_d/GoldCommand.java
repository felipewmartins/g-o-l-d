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

import org.esmerilprogramming.g_o_l_d.sounds.Sounds;
import org.esmerilprogramming.g_o_l_d.graphics.GameView;
import org.esmerilprogramming.g_o_l_d.core.GoldCore;
import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandOperation;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.terminal.Key;
import java.util.Set;
/**
 * @author eprogramming
 */
@CommandDefinition(name = "gold", description = "")
public class GoldCommand implements Command<CommandInvocation> {

  @Override
  public CommandResult execute(CommandInvocation ci) throws IOException, InterruptedException {

    GameView view = new GameView(ci.getShell());
    view.prepareScreen();
    view.displayInitialScreen();

    GoldCore core = null;
    Sounds sounds = new Sounds();
    CommandOperation co = ci.getInput();
    if (co.getInputKey() == Key.y) {

      view.clear();
      view.displayScenario(); 
      core = new GoldCore(view, sounds, ci);
      core.start();
      core.join();

    }

    sounds.stopMusic();
    //sounds.stopSound();
//    view.destroyScreen();
    core.interrupt();

//    Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//    Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
//    for (Thread t : threadArray) {
//      System.out.println(t.getName());
//    }
    ci.getShell().clear();
    ci.getShell().enableMainBuffer();
    ci.stop();
    return CommandResult.SUCCESS;
  }

}
