package UILibrary;

public interface sliderInterface {

	public void addOnChange(String ID, Runnable callback);
	public int getvalue(String ID);
	public int getMin(String ID);
	public int getMax(String ID);
	public int getProcentage(String ID);
	
	
}
