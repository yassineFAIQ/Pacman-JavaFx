package Pacman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MonsterFinal extends graphicObject {
	int nb_dead ;
	public MonsterFinal(Zone zone) {
		Image image=null;
		try {
			image = new Image(new FileInputStream("Pacman-JavaFx-master/src/photosJeu/monsterFinal.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		corps=new ImageView(image) ;
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		
		//doit appartenir zone
		double x=zone.getX1()+(zone.getX2()-zone.getX1())*Math.random();
		double y=zone.getY1()+(zone.getY2()-zone.getY1())*Math.random();
		corps.setTranslateX(x);
		corps.setTranslateY(y);
	}
}

