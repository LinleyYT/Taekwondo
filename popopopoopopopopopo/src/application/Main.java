package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		MainController mainController = new MainController();
		mainController.firstScene(primaryStage);
	}
		public static void main(String[] args) {
		launch(args);
	
	}
	
}	
