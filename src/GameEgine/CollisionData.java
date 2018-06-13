package GameEgine;

public class CollisionData {

	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	
	public CollisionData(boolean up, boolean down, boolean left, boolean right) {
		super();
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	public CollisionData() {
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
}
