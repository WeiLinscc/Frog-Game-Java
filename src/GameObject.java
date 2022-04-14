import java.awt.Rectangle;

//The parent class of game objects

public class GameObject {
	protected int  x,y;
	protected int  width, height;
	protected String filename;
	protected Rectangle r;	
	
	public int getX() { return x; }
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public String getFilename() {return filename;}
	public Rectangle getR() {return r;}
	
	public void setX(int x) { this.x = x; this.r.setLocation(this.x, this.y); }
	public void setY(int y) {this.y = y; this.r.setLocation(this.x, this.y);}
	public void setWidth(int width) {this.width = width;this.r.setSize(this.width,this.height);}
	public void setHeight(int height) {this.height = height;this.r.setSize(this.width,this.height);}
	public void setFilename(String filename) {this.filename = filename;}
	//public void setR(Rectangle r) {this.r = r;}

	public GameObject() {
		super();
		this.x = 0; this.y = 0; this.width = 0; this.height = 0;
		this.filename = "";
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
	}
	public GameObject(int x, int y, int width, int height, String filename) {
		super();
		this.x = 0; this.y = 0; this.width = 0; this.height = 0;
		this.filename = "";
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public GameObject(int width, int height, String filename) {
		super();
		this.x = 0; this.y = 0;
		this.width = width;
		this.height = height;
		this.filename = filename;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
	}

	
	
}