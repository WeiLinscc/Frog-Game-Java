
public class Frog extends GameObject{
	private Boolean live;
	
	public Boolean getLive() {
		return live;
	}

	public void setLive(Boolean live) {
		this.live = live;
	}

	public Frog() {
		super(40, 40, "frog.png");
		this.live = true;
	}
}
