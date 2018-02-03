package application;

import java.io.IOException;
import java.util.Currency;
import java.util.LinkedList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.css.Match;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration; 

public class MainController {
	
	///create new match
	@FXML
	public Label redName;
	@FXML
	public Label blueName;
	
	private byte numberOfRoundsByte;
	
	
	
	//
	@FXML
	public TextField redId;
	@FXML
	public TextField blueId;
	@FXML
	public TextField numberOfRounds;
	@FXML
	public TextField roundTime;
	@FXML 
	public Button buttonX;
	@FXML
	private Stage stage;

	
	public void newVoid() {
		this.stage = new Stage();
		 Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("NewMatch.fxml"));
		
		    
	       Scene scene = new Scene(root, 300, 275);
	   
	       this.stage.setTitle("FXML Welcome");
	       this.stage.setScene(scene);
	       this.stage.showAndWait();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
//
	
	
	private byte STARTTIME;
	private final byte BREAK_TIME = 5;
	private byte seconds =  STARTTIME;
	private String type = "breaktime";
	private Timeline timeline = new Timeline();

	@FXML
	public Label timer;
	@FXML
	public Label rounds;
	
	
	
	
	
	public void rounds() {
		if (type.equals("breaktime")) {
			type = "";
			endOfRound();
		}else {
			type = "breaktime";
			endOfBreak();
		}
	}
	
	public void endOfRound() {
		if (currentRound >= numberOfRoundsByte) {
			type = "";
			if (currentRound >= numberOfRoundsByte) {
				if (redPoints > bluePoints) {
					System.out.println("red wining");
					redWinner();
				}else if (bluePoints > redPoints) {
					blueWinner();
				} else {
					if (currentRound == numberOfRoundsByte) {
						goldenPoint();
						seconds = BREAK_TIME;
						type = "breaktime";
						currentRound++;
					}  else if (redWrning > blueWrning) {
						blueWinner();
					}else if (redWrning < blueWrning) {
						redWinner();
					}	
				}
			}
		}else {
			seconds = BREAK_TIME;
			currentRound++;
		}		

		rounds.setText(String.format("%s/%s", currentRound, numberOfRoundsByte));
		
	}
	public void endOfBreak() {
		seconds = STARTTIME;
	}
	private boolean doTimeTimes = true;
	 public void doTime() {
		 
		if (doTimeTimes) {
			timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			if (timeline!=null) {
				
			}
			KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
								seconds--;
							
								for (int i = 0; i < redJ1.size(); i++) {
									Point point = redJ1.get(i);
									if (point.time <= 0) {
										redJ1.remove(i);
										i--;
									continue;
									}
									for (int j = 0; j < redJ2.size(); j++) {
										Point point1 = redJ2.get(j);
										if (point1.time <= 0) {
											redJ2.remove(j);
											j--;
										continue;
										}
										if (point.points == point1.points) {
											redPoints += point.points;
											redPints.setText(Byte.valueOf(redPoints).toString());
											redJ1.remove(i);
											redJ1.remove(j);
											i--;
										}
									}
								}
								for (int i = 0; i < blueJ1.size(); i++) {
									Point point = blueJ1.get(i);
									if (point.time <= 0) {
										blueJ1.remove(i);
										i--;
									continue;
									}
									for (int j = 0; j < blueJ2.size(); j++) {
										Point point1 = blueJ2.get(j);
										if (point1.time <= 0) {
											blueJ2.remove(j);
											j--;
										continue;
										}
										if (point.points == point1.points) {
											bluePoints += point.points;
											bluePints.setText(Byte.valueOf(bluePoints).toString());
											blueJ1.remove(i);
											blueJ1.remove(j);
											i--;
										}
									}
								}
								for (Point point : blueJ1) {
									point.points--;
								}
								for (Point point : blueJ2) {
									point.points--;
								}
								for (Point point : redJ1) {
									point.points--;
								}
								for (Point point : redJ2) {
									point.points--;
								}
								
								timer.setText(Integer.valueOf(seconds).toString());
								if (seconds <= 0) {
									timeline.stop();
									rounds();
								}
				}
				
			});
			timeline.getKeyFrames().add(frame);
			timeline.playFromStart();
			doTimeTimes = false;
		} else {
			if (playing) {
				timeline.stop();
				playing = false;
			} else {
				timeline.play();
				playing = true;
			}
		}
	 }
	 LinkedList<Point> redJ1 = new LinkedList<Point>();
	 LinkedList<Point> redJ2 = new LinkedList<Point>();
	 
	 LinkedList<Point> blueJ1 = new LinkedList<Point>();
	 LinkedList<Point> blueJ2 = new LinkedList<Point>();
	 
		boolean playing = true;

	
	 
	 private Stage primaryStage;
	 public void firstScene(Stage primaryStage) {
		 try {
				
				Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
				Scene scene = new Scene(root,1500,1000);
				this.primaryStage = primaryStage;
				this.primaryStage.setTitle("Taewkondo points");
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		 primaryStage.setOnCloseRequest(x-> x.consume());
		}
	 
		 

	 
	 private byte redPoints;
	 private byte bluePoints;
	 
	 @FXML
	 private Label redPints;
	 @FXML
	 private Label bluePints;
	 
	 @FXML
	 private Label redWarning;
	 @FXML
	 private Label blueWarning;
	 
	 private byte redWrning = 0;
	 private byte blueWrning = 0;
	 private byte currentRound = 1;
	 
	 
	@FXML public void getPoints(ActionEvent event) {
		redName.setText(redId.getText());
		blueName.setText(blueId.getText());
		STARTTIME = Byte.valueOf(roundTime.getText());
		timer.setText(Byte.valueOf(STARTTIME).toString());
		seconds = STARTTIME;
		numberOfRoundsByte = Byte.valueOf(numberOfRounds.getText());
		rounds.setText(String.format("%s/%s", currentRound, numberOfRoundsByte));
		
		restart();
	}
	private void restart() {
		redPints.setText("0");
		bluePints.setText("0");
		redWarning.setText("0");
		blueWarning.setText("0");
		redPoints = 0;
		bluePoints = 0;
		redWrning = 0;
		blueWrning = 0;
	}
	private void goldenPoint() {
		redPints.setText("0");
		bluePints.setText("0");
		redPoints = 0;
		bluePoints = 0;
		
	}
	
	@FXML public void blueAddPoint() {
		bluePints.setText(Byte.valueOf(++bluePoints).toString());
		if (bluePoints - redPoints >= 20) {
			blueWinner();
		}
	}

	@FXML public void blueRemovePoint() {
		bluePints.setText(Byte.valueOf(--bluePoints).toString());

	}

	@FXML public void blueAddWarning() {
		blueWarning.setText(Byte.valueOf(++blueWrning).toString());
		redAddPoint();
if (blueWrning == 10) {
	redWinner();
	}
	}

	@FXML public void blueRemoveWarning() {
		blueWarning.setText(Byte.valueOf(--blueWrning).toString());
		redRemovePoint();
	}

	@FXML public void redAddPoint() {
		redPints.setText(Byte.valueOf(++redPoints).toString());
		if (redPoints - bluePoints >= 20) {
			redWinner();
			}
	}

	@FXML public void redRemovePoint() {
		redPints.setText(Byte.valueOf(--redPoints).toString());
	}

	@FXML public void redAddWarnind() {
		redWarning.setText(Byte.valueOf(++redWrning).toString());
		blueAddPoint();
if (redWrning == 10) {
	blueWinner();
}
	}

	@FXML public void redRemoveWarning() {
		redWarning.setText(Byte.valueOf(--redWrning).toString());
		blueRemovePoint();
	}
	
	Stage winnerStage;
	
	private void redWinner() {
		timeline.stop();

		winnerStage = new Stage();
		 Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("redWinner.fxml"));
		
		    
	       Scene scene = new Scene(root, 1800, 1000);
	   
	       winnerStage.setTitle("Winner");
	       winnerStage.setScene(scene);
	       winnerStage.setOnHidden(evt -> Platform.exit());

	       winnerStage.show();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
		private void blueWinner() {
			timeline.stop();

			winnerStage = new Stage();
			 Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("blueWinner.fxml"));
				
			    
		       Scene scene = new Scene(root, 1800, 1000);
		   
		       winnerStage.setTitle("Winner");
		       winnerStage.setScene(scene);
		       winnerStage.setOnHidden(evt -> Platform.exit());
		       winnerStage.show();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			winnerStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					Platform.exit();
					System.exit(0);
				}
			});
			       
		        
		        
		
	}
		
		public void inputJoyStick() {
			
			Byte pByte = null;
			//TODO implemet conection between program and joysticо
			
			if (doTimeTimes) {
				redJ1.add(new Point(pByte));				
			}else if (playing) {
				redJ2.add(new Point(pByte));				

			} else if (doTimeTimes) {
				blueJ1.add(new Point(pByte));				

			} else if (doTimeTimes) {
				blueJ2.add(new Point(pByte));					
			}
		}		
}
