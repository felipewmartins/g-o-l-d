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
package org.esmerilprogramming.g_o_l_d;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.esmerilprogramming.g_o_l_d.core.GoldRunner;
import org.esmerilprogramming.g_o_l_d.graphics.GoldGraphics;
import org.esmerilprogramming.g_o_l_d.input.KeyboardInput;
import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandOperation;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.graphics.AeshGraphicsConfiguration;
import org.jboss.aesh.graphics.GraphicsConfiguration;
import org.jboss.aesh.terminal.Key;
import org.jboss.aesh.util.ANSI;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@CommandDefinition(name = "gold", description = "")
public class GoldCommand implements Command<CommandInvocation> {

    public static ExecutorService executorService;

    @Override
    public CommandResult execute(CommandInvocation commandInvocation) throws IOException, InterruptedException {

        commandInvocation.getShell().out().print(ANSI.saveCursor());
        commandInvocation.getShell().out().print(ANSI.hideCursor());
        commandInvocation.getShell().enableAlternateBuffer();
        commandInvocation.getShell().out().flush();

        GraphicsConfiguration gc = new AeshGraphicsConfiguration(commandInvocation.getShell());
        GoldGraphics goldGraphics = new GoldGraphics(gc.getGraphics());

        goldGraphics.drawReadyScreen(commandInvocation.getShell());

        CommandOperation commandOperation = commandInvocation.getInput();
        if (commandOperation.getInputKey() == Key.y) {
            
            goldGraphics.cleanup();
            commandInvocation.getShell().clear();
            commandInvocation.getShell().out().print(ANSI.saveCursor());
            commandInvocation.getShell().out().print(ANSI.hideCursor());
            commandInvocation.getShell().out().flush();
            
            GoldRunner gr = new GoldRunner(commandInvocation.getShell(), goldGraphics);
            executorService = Executors.newSingleThreadExecutor();
            executorService.execute(gr);

            KeyboardInput ki = new KeyboardInput(commandInvocation, gr, executorService);
            ki.processInput();
        } else {
            goldGraphics.drawScoreScreen(commandInvocation.getShell());
            
            Thread.sleep(5000);
            
            goldGraphics.cleanup();
            commandInvocation.getShell().clear();
            commandInvocation.getShell().out().print(ANSI.restoreCursor());
            commandInvocation.getShell().out().print(ANSI.showCursor());
            commandInvocation.getShell().enableMainBuffer();
            commandInvocation.getShell().out().flush();
        }
        
        goldGraphics.drawScoreScreen(commandInvocation.getShell());
        Thread.sleep(5000);
        goldGraphics.cleanup();
        commandInvocation.getShell().clear();
        commandInvocation.getShell().out().print(ANSI.restoreCursor());
        commandInvocation.getShell().out().print(ANSI.showCursor());
        commandInvocation.getShell().enableMainBuffer();
        commandInvocation.getShell().out().flush();
        
        return CommandResult.SUCCESS;
    }

}