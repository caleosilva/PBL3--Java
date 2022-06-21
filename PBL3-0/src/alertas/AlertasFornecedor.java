package alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertasFornecedor {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	
	public void alertaNomeJaExistente() {
		alertaErro.setTitle("Item Repetido.");
		alertaErro.setHeaderText("O produto digitado já foi selecionado anteriormente");
		alertaErro.setContentText("Tente novamente com um outro valor ou finalize o cadastro");
		alertaErro.showAndWait();
	}

}
