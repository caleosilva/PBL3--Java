package alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 *
 */
public class AlertasLogin {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	
	/**
	 * Alerta do tipo "Error" informando que que nao foi possivel realizar o login
	 */
	public void alertaErroLogin() {
		alertaErro.setTitle("Nao foi possivel realizar o login.");
		alertaErro.setHeaderText("Senha e/ou Login invalidos!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	/**
	 * Alerta do tipo "Error" para a excecao "ArrayOutOfBounds".
	 */
	public void alertaArrayOutOfBounds() {
		alertaErro.setTitle("Nao foi possivel realizar o login.");
		alertaErro.setHeaderText("Erro ao carregar o banco de dados!");
		alertaErro.setContentText("Tente novamente ou reinicie o sistema!");
		alertaErro.showAndWait();
	}
	
	/**
	 *  Alerta do tipo "Error" informando que as informacoes para realizar o login estao incompletas.
	 */
	public void alertaFaltaInformacoesLogin() {
		alertaErro.setTitle("Nao foi possivel realizar o login.");
		alertaErro.setHeaderText("Insira todos os dados solicitados!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}

}
