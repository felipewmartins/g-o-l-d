package org.esmerilprogramming.g_o_l_d;

import org.jboss.aesh.console.AeshConsole;
import org.jboss.aesh.console.AeshConsoleBuilder;
import org.jboss.aesh.console.command.registry.AeshCommandRegistryBuilder;

public class Main {
    
    public static void main(String[] args) {

        AeshCommandRegistryBuilder aeshCommandRegistryBuilder = new AeshCommandRegistryBuilder();
        aeshCommandRegistryBuilder.command(Gold.class);

        AeshConsoleBuilder aeshConsoleBuilder = new AeshConsoleBuilder();
        aeshConsoleBuilder.commandRegistry(aeshCommandRegistryBuilder.create());
        AeshConsole aeshConsole = aeshConsoleBuilder.create();

        aeshConsole.start();
    }
}