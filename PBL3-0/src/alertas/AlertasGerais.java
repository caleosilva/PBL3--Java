package alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertasGerais {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	
	public void alertaErroLogin() {
		alertaErro.setTitle("Não foi possível realizar o login.");
		alertaErro.setHeaderText("Senha e/ou Login inválidos!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	public void alertaArrayOutOfBounds() {
		alertaErro.setTitle("Não foi possível realizar o login.");
		alertaErro.setHeaderText("Erro ao carregar o banco de dados!");
		alertaErro.setContentText("Tente novamente ou reinicie o sistema!");
		alertaErro.showAndWait();
	}
	
	public void alertaFaltaInformacoesLogin() {
		alertaErro.setTitle("Não foi possível realizar o login.");
		alertaErro.setHeaderText("Insira todos os dados solicitados!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
		
	}
 

}
