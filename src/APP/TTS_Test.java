package APP;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import LakerLibrary.*;

public class TTS_Test {

	public static void main(String[] args) throws Exception{
		Voice[] voices = VoiceManager.getInstance().getVoices();
		
		Voice voice = voices[0];
		voice.allocate();
		voice.speak("hello there");
		voice.deallocate();

		
	}
	
}
