package LakerLibrary;

public class ExampleCode {

	
	public static void main(String[] args) throws Exception{
		//create_one_to_million();
	}
	
	public static void create_one_to_million() {
		String filename = "1 to 1 000 000";
		$.file.createTextFile(filename, $.file.CurrentDir);
		StringBuilder data = new StringBuilder();
		for(long i  = 1; i <= 1000000; i++) {
			String number = $.number.toWord.convert(i);
			data.append(number + "\n");
		}
		$.file.writeToTextFile(filename, data.toString() , $.file.CurrentDir);
	}
	
	
}
