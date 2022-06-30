package alertas;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * Classe responsavel por conter os alertas relacionados ao gerenciamento dos usuarios.
 *
 */
public class AlertasUsuario {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	
	/**
	 * Alerta do tipo "Error" informando que o login digitado ja esta presente no sistema.
	 */
	public void alertaLoginExistente() {
		alertaErro.setTitle("Dado invalido.");
		alertaErro.setHeaderText("O Login inserido ja esta presente no sistema!");
		alertaErro.setContentText("Tente novamente com um outro valor!");
		alertaErro.showAndWait();
	}
	
	/**
	 * Alerta do tipo "Error" informando que a senha digitada na edicao da mesma esta incorreta.
	 */
	public void alertaSenhaAnteriorIncorreta() {
		alertaErro.setTitle("Senha anterior invalida!");
		alertaErro.setHeaderText("A \"senha anterior\" inserida nao coincide com a antiga!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	/**
	 * Alerta do tipo "Error" informando que as senhas inseridas nao sao iguais.
	 */
	public void alertaSenhasNovasNaoIguais() {
		alertaErro.setTitle("Senhas novas invalida!");
		alertaErro.setHeaderText("As novas senhas inseridas nao coincidem uma com a outra!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}

}
