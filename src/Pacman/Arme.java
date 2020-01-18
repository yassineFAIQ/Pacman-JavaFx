package Pacman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Arme extends graphicObject {
	private Rectangle corps = new Rectangle(-6,0,0,0);
	//private Circle sortie = new Circle(0,0,5);
	
	
	//////modif
	protected Node sortie;
	////modif
	
	public Arme(graphicObject monster) {
		corps.setTranslateX(monster.getCorps().getTranslateX());
		corps.setTranslateY(monster.getCorps().getTranslateY());
		corps.setFill(Color.YELLOW);
		
		////modif
		Image image=null;
		try {
			image = new Image(new FileInputStream("Pacman-JavaFx-master/src/photosJeu/fire.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sortie=new ImageView(image) ;
		
		////modif
		
	//	sortie.setFill(Color.BROWN);

		updateSortie();
		
	}
	
	
	public void attachToPlayer(Monster monster) {
		corps.setTranslateX(monster.getCorps().getTranslateX());
		corps.setTranslateY(monster.getCorps().getTranslateY());
		updateSortie();
	}
	
	public void attachToPlayer(MonsterFinal monsterFinal) {
		corps.setTranslateX(monsterFinal.getCorps().getTranslateX());
		corps.setTranslateY(monsterFinal.getCorps().getTranslateY());
		updateSortie();
	}
	
	
	public Rectangle getCorps() {
		return corps;
	}
	public void setCorps(Rectangle corps) {
		this.corps = corps;
	}
	
	//public Circle getSortie() {
		//return sortie;
	//}
	/////modif
	public Node getSortie() {
			return sortie;
	}
	////modif
	public void setSortie(Circle sortie) {
		this.sortie = sortie;
	}
	public void updateSortie() {
		sortie.setTranslateX(corps.getTranslateX());
		sortie.setTranslateY(corps.getTranslateY()+25);

	}
	
	
	
	public boolean isAttachedTo(Monster monster){
        if(corps.getTranslateX() == monster.corps.getTranslateX() && corps.getTranslateY() == monster.corps.getTranslateY()) {
        	return true;
        }
        return false;
    }
	public boolean isAttachedTo(MonsterFinal monsterFinal){
        if(corps.getTranslateX() == monsterFinal.corps.getTranslateX() && corps.getTranslateY() == monsterFinal.corps.getTranslateY()) {
        	return true;
        }
        return false;
    }
	public boolean isAttachedToBalle(Balle balle){
        if(corps.getTranslateX() == balle.corps.getTranslateX() && corps.getTranslateY() == balle.corps.getTranslateY()) {
        	return true;
        }
        return false;
    }
	//sur x :  tourner vers la droite
	//w : la gauche
	
	public void rotate(){
        corps.setRotate(corps.getRotate()+5);
	}
	public void rotateRight() {
		corps.setRotate(corps.getRotate()-5);
	}
	public void rotateLeft() {
		corps.setRotate(corps.getRotate()+5);
	}
	public double getRotate() {
		return corps.getRotate()+Math.random();
	}
}
