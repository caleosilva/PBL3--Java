package alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 *
 */
public class AlertasPdf {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	private Alert alertaSucesso = new Alert(AlertType.INFORMATION);

	/**
	 * Alerta do tipo "Error" informando que nao foi possivel gerar o relatorio.
	 */
	public void alertaPdfErro() {
		alertaErro.setTitle("Erro!");
		alertaErro.setHeaderText("Nao foi possivel gerar o relatorio em questao!");
		alertaErro.setContentText("Tente novamente ou reinicie o sistema!");
		alertaErro.showAndWait();
	}
	
	/**
	 * Alerta do tipo "Information" informando que o relatorio foi gerado com sucesso.
	 */
	public void alertaPdfSucesso() {
		alertaSucesso.setTitle("Sucesso!");
		alertaSucesso.setHeaderText("O relatório foi gerado na mesma pasta do projeto em questão!");
		alertaSucesso.showAndWait();
	}
	
	
}
