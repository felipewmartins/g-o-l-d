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
package org.esmerilprogramming.g_o_l_d.sounds

import java.io.IOException

import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.LineUnavailableException
import javax.sound.sampled.UnsupportedAudioFileException
import java.io.InputStream
import java.io.BufferedInputStream

/**
 * @author eprogramming
 */
class Sounds {

  private static final String MUSIC = "music_gold.wav"
  private static final String SOUND = "sound_gold.wav"

  private Clip music
  private Clip sound
  private AudioInputStream musicStream
  private AudioInputStream soundStream

  Sounds() {
    try {
      InputStream mis = Sounds.class.getResourceAsStream(MUSIC)
      InputStream musicBuffered = new BufferedInputStream(mis)
      musicStream = AudioSystem.getAudioInputStream(musicBuffered)
      music = AudioSystem.getClip()
      music.open(musicStream)
      InputStream sis = Sounds.class.getResourceAsStream(SOUND)
      InputStream soundBuffered = new BufferedInputStream(sis)
      soundStream = AudioSystem.getAudioInputStream(soundBuffered)
      sound = AudioSystem.getClip()
      sound.open(soundStream)
    }
    catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
      e.printStackTrace()
    }
  }

  void playMusic() {
    music.start()
    music.loop(3)
  }

  void playSound() {
    sound.start()
  }

  void stopMusic() {
    music.loop(-1)
    music.stop()
    music.close()
    try {
      musicStream.close()
    } catch(IOException e) {
      e.printStackTrace()
    }
  }

  void stopSound() {
    sound.loop(-1)
    sound.stop()
    sound.close()
    try {
      soundStream.close()
    } catch(IOException e) {
      e.printStackTrace()
    }
  }

}
