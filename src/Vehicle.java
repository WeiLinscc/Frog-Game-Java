

import javax.swing.*;

public class Vehicle extends GameObject implements Runnable{
	
	private Boolean moving;
	private Thread t;
	private JLabel VehicleLabel, FrogLabel;
	private Frog myFrog;
	private int speed = 3;
	private JButton animationBtn;
	
	public Boolean getMoving() {return moving;}
	
	public void setMoving(Boolean temp) {this.moving = temp;}
	public void setSpeed(int temp) {this.speed = temp;}	
	
	public void setFrog(Frog temp) {this.myFrog = temp;};
	public void setVehicleLabel(JLabel temp) {this.VehicleLabel = temp;}
	public void setFrogLabel(JLabel temp) {this.FrogLabel = temp;}
	public void setAnimationBtn(JButton temp) {this.animationBtn = temp;}


	
	public Vehicle() {
		super(50, 50, "car1.png");
		this.moving = false;
	}
	
	public Vehicle(JLabel temp) {
		super(50, 50, "car1.png");
		this.moving = false;
		this.VehicleLabel = temp;
		
	}
	
	public void moveVehicle () {
		t = new Thread(this, "Vehicle Thread");
		t.start();		
	}
	
	@Override
	public void run() {
		
//		this.startTime = new Date();
//		this.endTime = null;
		
		this.moving = true;
		FrogLabel.setIcon(new ImageIcon(getClass().getResource("frog.png")));
		VehicleLabel.setIcon(new ImageIcon(getClass().getResource(this.getFilename())));
		
		
		while (moving) {
			int tx = this.x;
			int ty = this.y;			

			tx += speed ;
			
			if (tx > GameProperties.SCREEN_WIDTH) {
				tx = -1 * this.width;
			}
			
			this.setX(tx);
			this.setY(ty);
			
			VehicleLabel.setLocation(this.x, this.y);
			this.collision();
			
			try {
				Thread.sleep(30);
			
			} catch (Exception e) { }
			
			if (myFrog.y == 0) {
				this.moving = false;				
			}
			if(!myFrog.getLive()) {
				this.moving = false;
			}
						
		}

	}
	
	private void collision() {
		if (this.r.intersects(myFrog.getR())) {
			this.moving = false;
			myFrog.setLive(false);
			animationBtn.setText("Try Again");
			animationBtn.setVisible(true);
			System.out.println("Fail！"); 
		}
		
	}
}

