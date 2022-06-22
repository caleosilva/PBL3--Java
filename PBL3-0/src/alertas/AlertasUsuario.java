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
	
	public void alertaSenhaAnteriorIncorreta() {
		alertaErro.setTitle("Senha anterior inv�lida!");
		alertaErro.setHeaderText("A \"senha anterior\" inserida n�o coincide com a antiga!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	public void alertaSenhasNovasNaoIguais() {
		alertaErro.setTitle("Senhas novas inv�lida!");
		alertaErro.setHeaderText("As novas senhas inseridas n�o coincidem uma com a outra!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}

}
