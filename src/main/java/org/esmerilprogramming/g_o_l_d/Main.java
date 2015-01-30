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

import org.jboss.aesh.console.AeshConsole;
import org.jboss.aesh.console.AeshConsoleBuilder;


/**
 * @author eprogramming
 */
public class Main {

    public static void main(String[] args) {
        AeshConsoleBuilder aeshConsoleBuilder = new AeshConsoleBuilder();
        aeshConsoleBuilder.addCommand(new GoldCommand()).executeAtStart("gold\n");
        AeshConsole aeshConsole = aeshConsoleBuilder.create();
        aeshConsole.start();
    }
}
