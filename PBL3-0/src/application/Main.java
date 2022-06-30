package application;

import java.io.IOException;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.application.Application;

/***************************
Autor: Georgenes Caleo e João Pedro
Componente Curricular: EXA863 MI Programação
Concluido em: 03/07/2022
Declaro que este código foi elaborado por nós de forma colaborativa e não contém nenhum
trecho de código de outro colega não autorizado ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a nossa está destacado com uma citação para o autor e a fonte
do código, e estamos cientes que estes trechos não serão considerados para fins de avaliação.
******************************/

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * 
 * Classe responsavel por abrir a primeira tela do sistema.
 *
 */
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage){
		
		System.out.println();
		
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
