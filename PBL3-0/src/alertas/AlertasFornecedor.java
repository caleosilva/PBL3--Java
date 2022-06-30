package alertas;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * 
 * Classe responsavel por conter os alertas relacionados ao gerenciamento de fornecedores.
 *
 */
public class AlertasFornecedor {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	
	/**
	 * Alerta de tipo "Error" informando sobre a existencia de um nome.
	 */
	public void alertaNomeJaExistente() {
		alertaErro.setTitle("Item Repetido.");
		alertaErro.setHeaderText("O produto digitado ja foi selecionado anteriormente");
		alertaErro.setContentText("Tente novamente com um outro valor ou finalize o cadastro");
		alertaErro.showAndWait();
	}

}
