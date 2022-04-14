import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.*;

public class WeiGameFrame extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = -4795362984523672023L;
	
	private Frog myFrog;
	private Vehicle myVehicle;
	private JLabel FrogLabel, VehicleLabel;
	private ImageIcon FrogIMG, VehicleIMG;
	//vehicle array
	private Vehicle arr1[];
	private JLabel[] arr1Label = new JLabel[3];
	private ImageIcon[] arr1IMG= new ImageIcon[3];
	
	private Vehicle arr2[];
	private JLabel[] arr2Label = new JLabel[3];
	private ImageIcon[] arr2IMG= new ImageIcon[3];
	
	private Vehicle arr3[];
	private JLabel[] arr3Label = new JLabel[2];
	private ImageIcon[] arr3IMG= new ImageIcon[2];
		
	private int specing = 0;

	private Date startTime, endTime;
	private double score;
	
	private String name = "";
	
	private JLabel resultLbl;
	private Font font = new Font("Helvetica", Font.PLAIN, 18);
	
	private JButton AnimateBtn;
	private Container content;
	
	public WeiGameFrame () {
		
		super("Froger Game - Project 1");
		setSize(GameProperties.SCREEN_WIDTH,GameProperties.SCREEN_HEIGHT);
		setResizable(false);
		
		//background image
		ImageIcon bgIcon = new ImageIcon(getClass().getResource("bg.jpg"));
		JLabel bgLabel = new JLabel(bgIcon);
		bgLabel.setBounds(0, 0, 540, 340);
		JPanel bgPanel = (JPanel)this.getContentPane();
		bgPanel.setOpaque(false);		
		this.getLayeredPane().add(bgLabel, Integer.valueOf(Integer.MIN_VALUE));
				
		myFrog = new Frog();
		FrogLabel = new JLabel();
		FrogIMG = new ImageIcon(getClass().getResource(myFrog.getFilename()));
		FrogLabel.setIcon(FrogIMG);
		FrogLabel.setSize(myFrog.getWidth(), myFrog.getHeight());
		
		myVehicle = new Vehicle();
		myVehicle.setFilename("car2.png");
		myVehicle.setSpeed(6);
		VehicleLabel = new JLabel();
		VehicleIMG = new ImageIcon(getClass().getResource(myVehicle.getFilename()));
		VehicleLabel.setIcon(VehicleIMG);
		VehicleLabel.setSize(myVehicle.getWidth(),myVehicle.getHeight());		
		
		myVehicle.setVehicleLabel(VehicleLabel);
		myVehicle.setFrog(myFrog);
		myVehicle.setFrogLabel(FrogLabel);
					
		content = getContentPane();
//		content.setBackground(Color.white);
		setLayout(null);
		
		myFrog.setX(250);
		myFrog.setY(250);
		myVehicle.setX(50);
		myVehicle.setY(50);

		//arr1
		arr1 = new Vehicle[3];
		specing = 0;
		for (int i=0; i<arr1.length; i++) {
			arr1[i] = new Vehicle();
			arr1Label[i] = new JLabel();
			arr1IMG[i] = new ImageIcon(getClass().getResource(arr1[i].getFilename()));
			arr1Label[i].setIcon(arr1IMG[i]);
			arr1Label[i].setSize(arr1[i].getWidth(), arr1[i].getHeight());
			
			arr1[i].setVehicleLabel(arr1Label[i]);
			arr1[i].setFrog(myFrog);
			arr1[i].setFrogLabel(FrogLabel);
			
			arr1[i].setX(specing);
			arr1[i].setY(100);	
			specing += 150;
			add(arr1Label[i]);
			
		}
		
		arr2 = new Vehicle[3];
		specing = 20;
		for (int i=0; i<arr2.length; i++) {
			arr2[i] = new Vehicle();
			arr2Label[i] = new JLabel();
			arr2[i].setFilename("car3.png");
			arr2[i].setSpeed(6);
			arr2IMG[i] = new ImageIcon(getClass().getResource(arr2[i].getFilename()));
			arr2Label[i].setIcon(arr2IMG[i]);
			arr2Label[i].setSize(arr2[i].getWidth(), arr2[i].getHeight());
			
			arr2[i].setVehicleLabel(arr2Label[i]);
			arr2[i].setFrog(myFrog);
			arr2[i].setFrogLabel(FrogLabel);
			
			arr2[i].setX(specing);
			arr2[i].setY(150);	
			specing += 180;
			add(arr2Label[i]);
			
		}
		
		arr3 = new Vehicle[2];
		specing = 40;
		for (int i=0; i<arr3.length; i++) {
			arr3[i] = new Vehicle();
			arr3Label[i] = new JLabel();
			arr3[i].setFilename("car4.png");
			arr3[i].setSpeed(5);
			arr3IMG[i] = new ImageIcon(getClass().getResource(arr3[i].getFilename()));
			arr3Label[i].setIcon(arr3IMG[i]);
			arr3Label[i].setSize(arr3[i].getWidth(), arr3[i].getHeight());
			
			arr3[i].setVehicleLabel(arr3Label[i]);
			arr3[i].setFrog(myFrog);
			arr3[i].setFrogLabel(FrogLabel);
			
			arr3[i].setX(specing);
			arr3[i].setY(200);	
			specing += 180;
			add(arr3Label[i]);
			
		}
		
					
		add(FrogLabel);
		add(VehicleLabel);		
		//Update the label positions to match the stored values
		FrogLabel.setLocation(myFrog.getX(), myFrog.getY());
		VehicleLabel.setLocation(myVehicle.getX(), myVehicle.getY());
				
		AnimateBtn = new JButton("Start Game");
		AnimateBtn.setSize(100,50);
		AnimateBtn.setLocation(220, 290 );
		
		resultLbl = new JLabel();
		resultLbl.setOpaque(true);
		resultLbl.setBackground(Color.black);
		resultLbl.setBounds(70, 100, 400, 100);
		resultLbl.setFont(font);
		resultLbl.setForeground(Color.white);
		resultLbl.setVisible(false);		
		add(resultLbl);
		
		name = JOptionPane.showInputDialog("Please enter your name!");

		add(AnimateBtn);
		AnimateBtn.addActionListener(this);
		AnimateBtn.setFocusable(false);
		myVehicle.setAnimationBtn(AnimateBtn);		

		for (int i=0;i<arr1.length;i++) {
			arr1Label[i].setLocation(arr1[i].getX(), arr1[i].getY());
			arr1[i].setAnimationBtn(AnimateBtn);
		}
		
		for (int i=0;i<arr2.length;i++) {
			arr2Label[i].setLocation(arr2[i].getX(), arr2[i].getY());
			arr2[i].setAnimationBtn(AnimateBtn);
		}
		
		for (int i=0;i<arr3.length;i++) {
			arr3Label[i].setLocation(arr3[i].getX(), arr3[i].getY());
			arr3[i].setAnimationBtn(AnimateBtn);
		}
			
		content.addKeyListener(this);
		content.setFocusable(true);	
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}

	public static void main(String[] args) {
		WeiGameFrame wei = new WeiGameFrame();
		wei.setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int dx = myFrog.getX();
		int dy = myFrog.getY();
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
			dx -= GameProperties.CHARACTER_STEP;
			if (dx <= 0) dx = 0;
		}else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			dx += GameProperties.CHARACTER_STEP;
			if (dx >= 500) dx = 500;
		}else if (e.getKeyCode() == KeyEvent.VK_UP) {
			dy -= GameProperties.CHARACTER_STEP;
			if (dy <= 0 )  dy = 0; 
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
			dy += GameProperties.CHARACTER_STEP;
			if (dy >= 250)  dy = 250; 
		}
		myFrog.setX(dx);
		myFrog.setY(dy);
		FrogLabel.setLocation(myFrog.getX(), myFrog.getY());
		
		if (myFrog.getY() == 0 && this.startTime != null) {
			if (endTime == null) {
				Hide();
				AnimateBtn.setText("Start Again");
				AnimateBtn.setVisible(true);

				String text = null;
				this.endTime = new Date();
				this.score = (double)(endTime.getTime() - startTime.getTime()) / 1000 ;
				System.out.println("Cool! Your Score is " + this.score + " seconds.");
				text = "Cool! " + this.name + ", your score is " + this.score + " seconds!";			
				
				//send to DB
				ToDB();
				
				//Game status display in the middle of the page	
				resultLbl.setText(text);;
				resultLbl.setVerticalAlignment(JLabel.CENTER);
				resultLbl.setHorizontalAlignment(JLabel.CENTER);
				resultLbl.setVisible(true);	
			}		
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == AnimateBtn) {
			
//			if (myVehicle.getMoving()) {
//				myVehicle.setMoving(false);
//				AnimateBtn.setText("Try Again");
//			} else {
			this.startTime = new Date();
			this.endTime = null;
			AnimateBtn.setVisible(false);
			resultLbl.setVisible(false);
			Show();
			Location();
			
			myVehicle.moveVehicle();
			myFrog.setLive(true);
			
//			arr1
			for (int i=0;i<arr1.length;i++) {
				arr1Label[i].setLocation(arr1[i].getX(), arr1[i].getY());
				arr1[i].moveVehicle();
			}
			for (int i=0;i<arr2.length;i++) {
				arr2Label[i].setLocation(arr2[i].getX(), arr2[i].getY());
				arr2[i].moveVehicle();
			}
			for (int i=0;i<arr3.length;i++) {
				arr3Label[i].setLocation(arr3[i].getX(), arr3[i].getY());
				arr3[i].moveVehicle();
			}

		}		
	}
	public void Location() {
		//Frog label location
		myFrog.setX(250);
		myFrog.setY(250);
		FrogLabel.setLocation(myFrog.getX(), myFrog.getY());
		//Vehicle 
		myVehicle.setX(50);
		myVehicle.setY(50);
		VehicleLabel.setLocation(myVehicle.getX(), myVehicle.getY());
		//arr1
		specing = 0;
		for (int i=0; i<arr1.length; i++) {
			arr1[i].setX(specing);
			arr1[i].setY(100);
			specing += 150;
		}
		specing = 20;
		for (int i=0; i<arr2.length; i++) {
			arr2[i].setX(specing);
			arr2[i].setY(150);
			specing += 180;
		}
		specing = 40;
		for (int i=0; i<arr3.length; i++) {
			arr3[i].setX(specing);
			arr3[i].setY(200);
			specing += 180;
		}
		
	}
	
	public void Hide () {
		VehicleLabel.setVisible(false);	
		for (int i=0; i<arr1.length; i++) {
			arr1Label[i].setVisible(false);
		}
		for (int i=0; i<arr2.length; i++) {
			arr2Label[i].setVisible(false);
		}
		for (int i=0; i<arr3.length; i++) {
			arr3Label[i].setVisible(false);
		}
	}
	
	public void Show () {
		VehicleLabel.setVisible(true);	
		for (int i=0; i<arr1.length; i++) {
			arr1Label[i].setVisible(true);
		}
		for (int i=0; i<arr2.length; i++) {
			arr2Label[i].setVisible(true);
		}
		for (int i=0; i<arr3.length; i++) {
			arr3Label[i].setVisible(true);
		}
	}
	
	public  void  ToDB() {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			System.out.println("Database Driver Loaded");
			
			String dbURL = "jdbc:sqlite:playerScore.db";
			conn = DriverManager.getConnection(dbURL); 
			
			if (conn != null) {
				System.out.println("Connected to database");
				conn.setAutoCommit(false);
				
				stmt = conn.createStatement();
				String sql = "CREATE TABLE IF NOT EXISTS PLAYER " +
							 "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
							 " NAME TEXT, " +
							 " SCORE DOUBLE NOT NULL)";
				stmt.executeUpdate(sql);
				conn.commit();
				
				System.out.println("Table Created Successfully");
				
				sql = "INSERT INTO PLAYER (NAME, SCORE) VALUES " +
					  "(?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setDouble(2, score);
				ps.executeUpdate();
				conn.commit();
				
				System.out.println("Display:");
				ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER");
				DisplayRecords(rs);
				rs.close();
								
				conn.close();
			}
			
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void DisplayRecords(ResultSet rs) throws SQLException {
		while ( rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			double score = rs.getDouble("score");
			
			System.out.println("ID = " + id);
			System.out.println("Name = " + name);
			System.out.println("Score = " + score);
			System.out.println();
		}
	}
	
	
}
