package alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertasPdf {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	private Alert alertaSucesso = new Alert(AlertType.INFORMATION);

	
	public void alertaPdfErro() {
		alertaErro.setTitle("Erro!");
		alertaErro.setHeaderText("Não foi possível gerar o relatório em questão!");
		alertaErro.setContentText("Tente novamente ou reinicie o sistema!");
		alertaErro.showAndWait();
	}
	
	public void alertaPdfSucesso() {
		alertaSucesso.setTitle("Sucesso!");
		alertaSucesso.setHeaderText("O relatório foi gerado na mesma pasta do projeto em questão!");
		alertaSucesso.showAndWait();
	}
	
	
}
