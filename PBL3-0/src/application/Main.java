package application;
	
import java.io.IOException;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage){
		
		// Largura e Comprimento deve ser setado em outro lugar
		int altura = 500;
		int comprimento = 700;
		
		String url = "/view/Menu.fxml";
		FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
	        
	        primaryStage.setResizable(false);
	        primaryStage.centerOnScreen();
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
