package LakerLibrary;

public class bitClass {

	public static void main(String[] args) throws Exception{
	
		bitClass c = new bitClass();
		
		int b = 0;
		
		c.print(b);

		
		b = c.set(b, 0);
		b = c.set(b, 1);
			
		c.print(b);

	}
	
	public void print(int b) {
		for(int i = 31; i >= 0; i--) {
			console.log(this.get(b,i) + " ");
		}
		console.logn("");
	}
	
	public int set(int b, int pos) {
		return b |= 1 << pos;
	}
	
	public int clear(int b, int pos) {
		return b &= ~(1 << pos);
	}
	
	public int flip(int b, int pos) {
		return b ^= 1 << pos;
	}
	
	public int get(int b, int pos) {
	    return (b >> pos) & 1;
	}
	
	public boolean check(int b, int pos) {
		b >>= pos;
		b = b & 1;
		boolean bo = (b != 0);
	    return bo;
	}
	
	
}
