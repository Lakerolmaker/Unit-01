package JackeLibrary;

public class MathClass {

	public static float map(float value, float fromLow, float fromHigh, float toLow, float toHigh) {
		return (value - fromLow) / (fromHigh - fromLow) * (toHigh - toLow) + toLow;
	}
	
	
}
