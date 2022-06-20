package alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertasUsuario {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	
	public void alertaLoginExistente() {
		alertaErro.setTitle("Dado inválido.");
		alertaErro.setHeaderText("O Login inserido já está presente no sistema!");
		alertaErro.setContentText("Tente novamente com um outro valor!");
		alertaErro.showAndWait();
	}

}
