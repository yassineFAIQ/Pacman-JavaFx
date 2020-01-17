package Pacman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Balle extends graphicObject {
	
	
	private Point2D direction = new Point2D(0,0);
	public Balle(Arme arme) {
		

		/////////////////modif
		Image image=null;
		try {
			image = new Image(new FileInputStream("src/photosJeu/fire.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		corps=new ImageView(image) ;
	//	((ImageView)corps).setX(0);
		//((ImageView)corps).setY(0);
		
		///////////////////modif
		
		//corps = new Circle(0,0,4,Color.BLUE);
		corps.setTranslateX(arme.getSortie().getTranslateX());
		corps.setTranslateY(arme.getSortie().getTranslateY());
		updateDirection(arme.getRotate());
	}
	
	
	private void updateDirection(double rotation) {
		Point2D p ;
		double x=Math.cos(Math.toRadians(rotation));
		double y=Math.sin(Math.toRadians(rotation));
		p=new Point2D(x,y);
		direction = p.normalize().multiply(4);
	
	}
	public void update() {
		corps.setTranslateX(corps.getTranslateX()+direction.getX());
		corps.setTranslateY(corps.getTranslateY()+direction.getY());
	}
}
