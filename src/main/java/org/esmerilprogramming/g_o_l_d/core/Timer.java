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
package org.esmerilprogramming.g_o_l_d.core;

import org.esmerilprogramming.g_o_l_d.graphics.GameView;

/**
 * @author eprogramming
 */
public class Timer extends Thread {

  private int counter = 35;
  private GameView view;

  public Timer(GameView view) {
    this.view = view;
  }

  @Override
  public void run() {
    while (counter > 0) {
      view.displayCounter(counter);
      try {
        Thread.sleep(1000);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
      counter = counter - 1;
    }
  }

  public int getCounter() {
    return counter;
  }

  public void shutdown() {
    counter = -1;
  }
}
