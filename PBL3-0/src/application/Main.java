package application;

import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.application.Application;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage){
		
		PreCadastro.main(null);
		String url = "/view/TelaLogin.fxml";
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
		Parent root;
		
		try {
			root = loader.load();
			Scene scene = new Scene(root);
	        primaryStage.centerOnScreen();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Gerenciador de bares/restaurantes");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
