package LakerLibrary;

import java.util.Scanner;

import console_external.consoleFX;
import console_external.runnableConsole;
import javafx.scene.input.KeyCode;

public class console {

	public static consoleFX external = new consoleFX();;

	public static void log(Object o) {
		System.out.print(o);
	}

	public static void logg(Object o) {
		System.out.println(o);
	}

	public static void logn(Object o) {
		System.out.println(o);
	}

	public static int readInt() {
		Scanner reader = new Scanner(System.in);
		if (reader.hasNextInt()) {
			return reader.nextInt();
		} else {
			return 0;
		}

	}

	public static String readString() {
		Scanner reader = new Scanner(System.in);
		if (reader.hasNextLine()) {
			return reader.nextLine();
		} else {
			return null;
		}
	}

	public static Byte readByte() {
		Scanner reader = new Scanner(System.in);
		if (reader.hasNextByte()) {
			return reader.nextByte();
		} else {
			return null;
		}
	}

	public static boolean readBool() {
		Scanner reader = new Scanner(System.in);
		if (reader.hasNextBoolean()) {
			return reader.nextBoolean();
		} else {
			new Exception("No input given");
		}
		return false;
	}

}
