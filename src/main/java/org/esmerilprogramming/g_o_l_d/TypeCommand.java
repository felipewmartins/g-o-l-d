package org.esmerilprogramming.g_o_l_d;

import java.io.IOException;

import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandOperation;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.terminal.Key;
import org.jboss.aesh.terminal.Shell;
import org.jboss.aesh.util.ANSI;
/**
 * @author eprogramming
 */
@CommandDefinition(name = "type", description = "")
public class TypeCommand implements Command<CommandInvocation> {

  @Override
  public CommandResult execute(final CommandInvocation ci) throws IOException, InterruptedException {


    final Shell shell = ci.getShell();
    shell.clear();
    shell.out().print(ANSI.CURSOR_SAVE);
    shell.out().print(ANSI.CURSOR_HIDE);
    shell.enableAlternateBuffer();

    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          CommandOperation co = null;
          try {
            co = ci.getInput();
          } catch(InterruptedException e) {
            e.printStackTrace();
          }
          if (co.getInputKey() == Key.k) {
            shell.out().print("k");
          } else if (co.getInputKey() == Key.j) {
            shell.out().print("j");
          } else if (co.getInputKey() == Key.h) {
            shell.out().print("h");
          } else if (co.getInputKey() == Key.l) {
            shell.out().print("l");
          } else if (co.getInputKey() == Key.q || co.getInputKey() == Key.ESC) {
            break;
          }
        }
    }});
    t.start();
    t.join();

    t.interrupt();

    shell.clear();
    shell.enableMainBuffer();
    ci.stop();
    return CommandResult.SUCCESS;
  }

}
