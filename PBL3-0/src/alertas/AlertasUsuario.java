package alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertasUsuario {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	
	public void alertaLoginExistente() {
		alertaErro.setTitle("Dado inv�lido.");
		alertaErro.setHeaderText("O Login inserido j� est� presente no sistema!");
		alertaErro.setContentText("Tente novamente com um outro valor!");
		alertaErro.showAndWait();
	}

}
