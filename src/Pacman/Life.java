package Pacman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
public class Life extends Label {
	private static final String font="";
	private int life ;
	
	
	public Life() {
		Image image = null;
		try {
			image = new Image(new FileInputStream("src/photosJeu/life.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		    setGraphic(new ImageView(image));
		
		
		this.getStylesheets().addAll("css/style.css");
		this.getStyleClass().add("labelText");
		life = 5;
		setText(""+life);
		setFont(new Font("Arial", 30));
		
		setTranslateX(150);
		setTranslateY(2);
	}
	
	void scoreUpdate() {
		life--;
		setText("" + life);
		
	}
	void scoreInit() {
		life=5;
	}

	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
}