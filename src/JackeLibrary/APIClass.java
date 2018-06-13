package JackeLibrary;

import java.io.*;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class APIClass {

	//: Requires 
	
	
	//: Text to speach
	private final String voice1 = "kevin";
	
	public void TTS(String text) {
		console.external.print("1");
		Voice voice;
		console.external.print("2");
		VoiceManager vm = VoiceManager.getInstance();
		console.external.print("3");
		voice = vm.getVoice(voice1);
		console.external.print("4");
		try {
			console.external.print(voice.toString());

		voice.allocate();
		console.external.print("5");
		
			
			voice.speak(text);
			console.external.print("6");
		} catch (Exception e) {
			console.external.print(e.toString());
		}
		
	}
	
	
}
