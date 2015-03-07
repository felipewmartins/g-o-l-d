/*
 * 2014 EsmerilProgramming.
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
class Main {

  static main(args) {
    def acb = new AeshConsoleBuilder()
    acb.addCommand(new GoldCommand()).executeAtStart("gold\n")
    def ac = acb.create()
    ac.start()
  }
}
