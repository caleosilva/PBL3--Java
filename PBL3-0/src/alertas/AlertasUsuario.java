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
	
	public void alertaSenhaAnteriorIncorreta() {
		alertaErro.setTitle("Senha anterior inválida!");
		alertaErro.setHeaderText("A \"senha anterior\" inserida não coincide com a antiga!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	public void alertaSenhasNovasNaoIguais() {
		alertaErro.setTitle("Senhas novas inválida!");
		alertaErro.setHeaderText("As novas senhas inseridas não coincidem uma com a outra!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}

}
