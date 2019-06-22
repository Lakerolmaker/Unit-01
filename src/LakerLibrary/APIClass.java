package LakerLibrary;

import java.io.*;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class APIClass {

	//: Requires 
	
	//: Text to speach
	VoiceManager vm = VoiceManager.getInstance();
	public Voice voice = vm.getVoices()[1];
	public long speachRate = 120;
	
	
	public void speak(String text) {
		try {
		voice.allocate();	
		voice.setRate(speachRate);
		voice.speak(text);
		} catch (Exception e) {
			console.logg(e.toString());
		}
		
	}
	
	
}
