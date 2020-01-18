package Pacman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Progam extends Application{

	//les élements de l'interface graphique
private int widthWindow=1500;
private int heightWindow=1000;
private Pane container = new Pane();
Line line = new Line(0,200,widthWindow,200);
Rectangle rectangle = new Rectangle(0,0,widthWindow,50);

Zone zone=new Zone(100, 100,widthWindow-280, heightWindow -100);
Zone Topzone=new Zone(0, 0,200, heightWindow);

int nbLife =5;
VBox vBoxTop = new VBox();

//les objets du jeu

Player player = new Player(zone);
Monster monster=new Monster(zone);
MonsterFinal monsterFinal = new MonsterFinal(zone);
private List<Monster>monsters = new ArrayList<>();
private List<Balle>balles = new ArrayList<>();
private List<Arme>armes = new ArrayList<>();
Arme arme = new Arme(monster);
Score score = new Score();
ScoreIcon scoreIcon = new ScoreIcon();
Life life = new Life();
StageLabel stage = new StageLabel();


static boolean isGameOver = false ;
static boolean isWinner = false ;
static int scoreFinish = 9 ;


String musicFile = "Pacman-JavaFx-master/src/photosJeu/music.mp3";
Media sound = new Media(new File(musicFile).toURI().toString());
MediaPlayer mediaPlayerStart = new MediaPlayer(sound);

String musicFileGameOver = "Pacman-JavaFx-master/src/photosJeu/gameover.mp3";
Media gameover = new Media(new File(musicFileGameOver).toURI().toString());
MediaPlayer mediaPlayerGameOver = new MediaPlayer(gameover);


String musicFileStart = "Pacman-JavaFx-master/src/photosJeu/startMusic.mp3";
Media musicStart = new Media(new File(musicFileStart).toURI().toString());
MediaPlayer StartmediaPlayer = new MediaPlayer(musicStart);


String musicFileWinner = "Pacman-JavaFx-master/src/photosJeu/isWinner.mp3";
Media musicWinner = new Media(new File(musicFileWinner).toURI().toString());
MediaPlayer mediaPlayerWinner = new MediaPlayer(musicWinner);



// timer
public static int heures = 00;
public static int minutes = 00;
public static int seconds = 00;

private static Text time = new Text(" Time (" + heures + ":" + minutes + ":" + seconds + " )               ");


//animationTimer

AnimationTimer animation = new 	AnimationTimer() {
	
	@Override
	public void handle(long now) {
		refeshContent();
		
	}
	
	
	
};

AnimationTimer animationF = new AnimationTimer() {
	@Override
	public void handle(long now) {
		refeshContentFinal();	
	}

};


//event handler

EventHandler<KeyEvent> event = new EventHandler<KeyEvent>() {

	@Override
	public void handle(KeyEvent event) {
		if(event.getCode()==KeyCode.UP) {
			if(player.getCorps().getTranslateY()>-20) {
			player.getCorps().setTranslateY(player.getCorps().getTranslateY()-20);
			player.rotateUp();
			}
			

		}
		
		if(event.getCode()==KeyCode.DOWN) {
			if(player.getCorps().getTranslateY()<heightWindow-220) {
			player.getCorps().setTranslateY(player.getCorps().getTranslateY()+20);
			player.rotateDown();
			}
			
		}
		if(event.getCode()==KeyCode.LEFT) {
			if(player.getCorps().getTranslateX()>-140) {
			player.getCorps().setTranslateX(player.getCorps().getTranslateX()-20);
			player.rotateLeft();
			}
		}
		if(event.getCode()==KeyCode.RIGHT) {
			if(player.getCorps().getTranslateX()<widthWindow-280) {
			player.getCorps().setTranslateX(player.getCorps().getTranslateX()+20);
			player.rotateRight();
			}		
		}	
	}
};

AnimationTimer animation1 = new AnimationTimer() {
    @Override
    public void handle(long now) {
        if (isGameOver==true) {
        	mediaPlayerStart.stop();
        	mediaPlayerGameOver.play();
		       container.getChildren().remove(scoreIcon);
		       container.getChildren().remove(life);
		       container.getChildren().remove(rectangle);
		       container.getChildren().remove(stage);
		       container.getChildren().remove(score);
		       container.getChildren().remove(time);
		       seconds=0;
	    		  minutes=0;
	    		  heures=0;
		       for(Monster monster : monsters) {
			     

		    	   monster.setAlive(false);
			       container.getChildren().remove(monster);

			      

		       }
		       container.getChildren().remove(monsterFinal);
		       monsterFinal.setAlive(false);
				 animation.stop();
				   animation1.stop();	
				   animationStage.stop();
				   animationStageFinal.stop();
				   animationF.stop();
				   
				  
				   
            Image gameOverImage = null;
            try {
                gameOverImage = new Image(new FileInputStream("Pacman-JavaFx-master/src/photosJeu/gameover.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageView gameOver = new ImageView();
            gameOver.setImage(gameOverImage);
            container.getChildren().add(gameOver);
            
            
            score.scoreFianl(score.getScore());
          
          
            gameOver.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
	                container.getChildren().remove(gameOver);
	               
	            	mediaPlayerGameOver.stop();
	      


	           	 balles.clear();
					monsters.clear();
					armes.clear();              	
	                score.scoreInit();	
	      		    life.scoreInit();
	            
      		        
	      		 
	      			
	      			createContent();
	      			
			  	animation.start();
			   //    refeshContent();
			      	animation1.start();
			      	animationStage.start();
			        isGameOver = false;
	                	
                  
                }
            });
            
            
            
    }
    }
};



AnimationTimer animationStage = new AnimationTimer() {
    @Override
    public void handle(long now) {
        if (score.getScore()==3) {
        	
        //
        	
        	mediaPlayerStart.stop();
        	mediaPlayerWinner.play();
      //  	mediaPlayerGameOver.play();
		       container.getChildren().remove(scoreIcon);
		       container.getChildren().remove(life);
		       container.getChildren().remove(rectangle);
		       container.getChildren().remove(score);
		       container.getChildren().remove(stage);
		       container.getChildren().remove(time);
		       seconds=0;
	    		  minutes=0;
	    		  heures=0;
		       
		       for(Monster monster : monsters) {
			     

		    	   monster.setAlive(false);
			      

		       }
		       
				 animation.stop();
				   animation1.stop();	
				   animationStage.stop();
				 
				   
            Image LevelImage = null;
            try {
            	LevelImage = new Image(new FileInputStream("Pacman-JavaFx-master/src/photosJeu/level.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageView Level = new ImageView();
            Level.setImage(LevelImage);
            container.getChildren().add(Level);
            
        //    score.scoreFianl();
          
          
            Level.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
	                container.getChildren().remove(Level);
	               
	            //	mediaPlayerGameOver.stop();
	            	//mediaPlayerS.play();


	           	 balles.clear();
					monsters.clear();
					armes.clear();              	
	                
	      		    life.scoreInit();
	               
	      		  //  player.setAlive(true);
	      		  //  monster.setAlive(true);
      		        
	      		  
	      			
	      			createContent();
	      			stage.stageUpdate2();
	      		//	score.setScore(10);
			  	animation.start();
			   //    refeshContent();
			      	animation1.start();
			      	animationStageFinal.start();
			        isGameOver = false;
			        
			        mediaPlayerStart.play();
		        	mediaPlayerWinner.stop();
	                	
                  
                }
            });
            
            
            
    }
    }
};



AnimationTimer animationStageFinal = new AnimationTimer() {
    @Override
    public void handle(long now) {
        if (score.getScore()==5) {
        	
        	mediaPlayerStart.stop();
        	mediaPlayerWinner.play();
		       container.getChildren().remove(scoreIcon);
		       container.getChildren().remove(score);

		       container.getChildren().remove(life);
		       container.getChildren().remove(rectangle);
		       container.getChildren().remove(stage);
		       container.getChildren().remove(time);
		       seconds=0;
	    		  minutes=0;
	    		  heures=0;
		       for(Monster monster : monsters) {
			     

		    	   monster.setAlive(false);
			      

		       }
		       
				 animation.stop();
				   animation1.stop();	
				   animationStage.stop();
				
				   
            Image LevelImage = null;
            try {
            	LevelImage = new Image(new FileInputStream("Pacman-JavaFx-master/src/photosJeu/level.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageView Level = new ImageView();
            Level.setImage(LevelImage);
            container.getChildren().add(Level);
            
        //    score.scoreFianl();
          
          
            Level.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
	                container.getChildren().remove(Level);
	               
	            //	mediaPlayerGameOver.stop();
	            	//mediaPlayerS.play();


	           	 balles.clear();
					monsters.clear();
					armes.clear();              	
	                
	      		    life.scoreInit();
	               
	      		  //  player.setAlive(true);
	      		  //  monster.setAlive(true);
      		        
	      		
	      			
	      			createContent();
	      			stage.stageUpdate3();
	      			exemple();
	      			animationF.start();
	      		//	score.setScore(10);
			  	animation1.start();
			   //    refeshContent();
			  	animationStageWinner.start();			  
			      	
			   
			        isGameOver = false;
			        
			        mediaPlayerStart.play();
		        	mediaPlayerWinner.stop();
	                	
                  
                }
            });
            
            
            
    }
    }
};


AnimationTimer animationStageWinner = new AnimationTimer() {
    @Override
    public void handle(long now) {
        if (isWinner==true) {
        	mediaPlayerStart.stop();


        	
        	mediaPlayerWinner.play();
        	
		       container.getChildren().remove(scoreIcon);
		       container.getChildren().remove(life);
		       container.getChildren().remove(rectangle);
		       container.getChildren().remove(stage);
		       container.getChildren().remove(time);
		       seconds=0;
	    		  minutes=0;
	    		  heures=0;
		       for(Monster monster : monsters) {
			     

		    	   monster.setAlive(false);
			      

		       }
		       
				 animation.stop();
				   animation1.stop();	
				   animationStage.stop();
				
				   
            Image LevelImage = null;
            try {
            	LevelImage = new Image(new FileInputStream("Pacman-JavaFx-master/src/photosJeu/winner.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageView Level = new ImageView();
            Level.setImage(LevelImage);
            container.getChildren().add(Level);
            
            score.scoreF(scoreFinish);
        //    score.scoreFianl();
          
          
           
            
            
            
    }
    }
};




	private void refeshContent() {
		
	
		
		for (Arme arme : armes) {
			Balle balle = new Balle(arme);			
			for(Monster monster:monsters) {
				if(player.touch(monster)) {
					if((arme.isAttachedTo(monster)) ) {			
					score.scoreUpdate();				
					container.getChildren().removeAll(monster.getCorps(),balle.getCorps(),arme.getCorps());
					monster.setAlive(false);
					}
				monster.setAlive(false);	
				}
				arme.rotateRight();
			}
		}
	
		for (Balle balle : balles) {
				if(balle.touch(player)) {
					container.getChildren().removeAll(balle.getCorps());
					balle.setAlive(false);
					player.setAlive(false);
					life.scoreUpdate();
					int i = life.getLife();
					if(i<=0) {
					isGameOver=true;
					}			
				}	
		}
		
		monsters.removeIf(graphicObject::isDead);
		balles.removeIf(graphicObject::isDead);
		armes.removeIf(graphicObject::isDead);
		
		for (Balle balle : balles) {
			balle.update();
		}
		if(Math.random()<0.009) {
		Monster m = new Monster(zone);
		Arme arme = new Arme(m);
		Balle balle = new Balle(arme);
		balles.add(balle);
		container.getChildren().add(m.getCorps());
		monsters.add(m);
		container.getChildren().add(arme.getCorps());
		armes.add(arme);
		

		new AnimationTimer() {
			
		    private long lastUpdate = 0;

		    @Override
		    public void handle(long now) {
		        if (lastUpdate == 0) {
		            lastUpdate = now;
		            return;
		        }

		        if (now - lastUpdate > 1999999999) {
		        	   Balle balle1 = new Balle(arme);
		        	   
		        	if (m.isAlive()) {    
		               balles.add(balle1);
		               container.getChildren().add(balle1.getCorps());
		                arme.attachToPlayer(m);	                
		            } else
		            	if((arme.isAttachedTo(m)) ) {
		        	  if(m.isDead()) {      		  
		        		  container.getChildren().remove(arme.getCorps());
		        		  container.getChildren().remove(balle1.getCorps());
		        		  balles.remove(balle1);
		        		  armes.remove(arme);			          
		        	  }  
		        	  }
		            	
                  lastUpdate = now;
		        }
		    }
		}.start();
		}
		
	}
	
	
private void exemple() {
	monsterFinal = new MonsterFinal(zone);
	container.getChildren().add(monsterFinal.getCorps());
	
	
}
	
private void refeshContentFinal() {
		
	
	for (Arme arme : armes) {
		Balle balle = new Balle(arme);			
	
			if(player.touch(monsterFinal)) {
				if((arme.isAttachedTo(monsterFinal)) ) {			
				//score.scoreUpdate();				
				container.getChildren().removeAll(monsterFinal.getCorps(),balle.getCorps(),arme.getCorps());
				monsterFinal.setAlive(false);
				animation.stop();
				animation1.stop();
				animationF.stop();
				animationStage.stop();
				animationStageFinal.stop();
				score.scoreUpdate();
				isWinner=true;
				
				
				}
				monsterFinal.setAlive(false);	
			}
			arme.rotateRight();

	}

	for (Balle balle : balles) {
			if(balle.touch(player)) {
				container.getChildren().removeAll(balle.getCorps());
				balle.setAlive(false);
				player.setAlive(false);
				life.scoreUpdate();
				int i = life.getLife();
				if(i<=0) {
				isGameOver=true;
				}			
			}	
	}
	
	
	monsters.removeIf(graphicObject::isDead);
	balles.removeIf(graphicObject::isDead);
	armes.removeIf(graphicObject::isDead);
	
	for (Balle balle : balles) {
		balle.update();
	}
	if(Math.random()<0.05) {
//	Monster m = new Monster(zone);
	Arme arme = new Arme(monsterFinal);
	//Balle balle = new Balle(arme);
//	balles.add(balle);
//	container.getChildren().add(.getCorps());
//	monsters.add(m);
	container.getChildren().add(arme.getCorps());
	armes.add(arme);

		

		new AnimationTimer() {
			
		    private long lastUpdate = 0;

		    @Override
		    public void handle(long now) {
		        if (lastUpdate == 0) {
		            lastUpdate = now;
		            return;
		        }

		        if (now - lastUpdate > 1999999999) {
		        	   Balle balle1 = new Balle(arme);
		        	   
		        	if (monsterFinal.isAlive()) {    
		               balles.add(balle1);
		               container.getChildren().add(balle1.getCorps());
		                arme.attachToPlayer(monsterFinal);	                
		            } else
		            	if((arme.isAttachedTo(monsterFinal)) ) {
		        	  if(monsterFinal.isDead()) {      		  
		        		  container.getChildren().remove(arme.getCorps());
		        		  container.getChildren().remove(balle1.getCorps());
		        		  balles.remove(balle1);
		        		  armes.remove(arme);			          
		        	  }  
		        	  }
		            	
                  lastUpdate = now;
		        }
		    }
		}.start();
		
		}
		
	}
	
	
	
	
	
	
	

    
	private void createContent() {
		
		
    	mediaPlayerStart.play();
    	
		
	
		rectangle.setFill(Color.BLACK);
		
		life = new Life();
		score = new Score();
		scoreIcon = new ScoreIcon();
		stage = new StageLabel();
		stage.stageUpdate();

	
		
		Image image = null;
		try {
			image = new Image(new FileInputStream("Pacman-JavaFx-master/src/photosJeu/background.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView mv=new ImageView(image);
		container.getChildren().add(mv);
		player = new Player(zone);
		container.getChildren().add(player.getCorps());
		container.getChildren().add(rectangle);
		container.getChildren().add(stage);
		container.getChildren().add(score);
		container.getChildren().add(scoreIcon);
		container.getChildren().add(life);
		
		time.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
        time.setFill(Color.WHITE);
        
        time.setTranslateX(700);
		time.setTranslateY(40);
		container.getChildren().add(time);
	
	}
	@Override
	public void start(Stage window) throws Exception {
		BorderPane pane = new BorderPane();
		StackPane stackPane = new StackPane();
		VBox vbox =new VBox();
		
		StartmediaPlayer.play();
		
		Image image = null;
		try {
			image = new Image(new FileInputStream("Pacman-JavaFx-master/src/photosJeu/backgroundF.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImageView mv=new ImageView(image);
		//container.getChildren().add(mv);
		
		
		
		Button buttonStart = new Button("Start");
		buttonStart.setMaxSize(500, 700);
		Scene scene1=new Scene(stackPane,widthWindow,heightWindow);
		scene1.getStylesheets().addAll("css/style.css");
		buttonStart.getStyleClass().add("menu");
		pane.setCenter(vbox);
     	vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(buttonStart);
		buttonStart.getStyleClass().add("buttonB");
		stackPane.getChildren().add(mv);
		stackPane.getChildren().add(vbox);
		
		/*
		String videoFile = "photosJeu/video.mp4";
    	Media sound = new Media(new File(videoFile).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	MediaView mediaView = new MediaView(mediaPlayer); 	
    	
    	
    	mediaPlayer.play();
		
		
	    BackgroundFill background_fill = new BackgroundFill(Color.BLACK, null, null); 
	    Background background = new Background(background_fill); 
	    stackPane.setBackground(background);
	     
	    
	     */
		
		
		
		window.setTitle("Pacman by Yassine FAIQ");
		window.setWidth(widthWindow);
		window.setHeight(heightWindow);
		window.setScene(scene1);
		Scene scene = new Scene(container);
		
		buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
     //      	mediaPlayer.stop();
            	
        		StartmediaPlayer.stop();

            	window.setScene(scene);
            	createContent();
            	animation.start();
            	
	animation1.start();
	animationStage.start();
            }
        });
		
	
    
	//	refeshContent();
    	
		
		scene.setOnKeyPressed(event);
		window.setResizable(false);	
	 	
		window.show();
	}
	
	public static void main(String[] args) {	
		
		final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                time.setText((" Time  " + heures + ":" + minutes + ":" + seconds + "               "));
                seconds++;
                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }
                if (minutes == 60) {
                    heures++;
                    minutes = 0;
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

	
		Application.launch(args);	
	}
		
		
	

}
