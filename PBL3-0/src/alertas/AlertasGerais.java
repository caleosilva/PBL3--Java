package alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * Classe responsavel por conter os alertas gerais para os gerenciamentos.
 *
 */
public class AlertasGerais {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	private Alert alertaSucesso = new Alert(AlertType.INFORMATION);
	
	/**
	 * Alerta do tipo "Error" informando que uma operacao nao foi realizada.
	 */
	public void erroNaOperacao() {
		alertaErro.setTitle("Nao foi possivel realizar essa operacao.");
		alertaErro.setHeaderText("Tente novamente. Caso o erro persista reinicie o sistema!");
		alertaErro.showAndWait();
	}
	
	/**
	 * Alerta do tipo "Error" informando que os dados nao estao corretos.
	 */
	public void faltaDadosOuIncorretos() {
		alertaErro.setTitle("Nao foi possivel realizar essa operaao.");
		alertaErro.setHeaderText("Falta alguma informacao ou ao menos um dos dados inseridos eh invalido!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	/**
	 * Alerta do tipo "Error" informando que nenhum elemento da tabela foi selecionado!.
	 */
	public void itemNaoSelecionado() {
		alertaErro.setTitle("Nao foi possivel realizar esse procedimento!");
		alertaErro.setHeaderText("Selecione um item da tabela para realizar essa operacao!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	/**
	 * Alerta do tipo "INFORMATION" informando que uma operacao foi executada com sucesso.
	 */
	public void informarSucessoOperacao() {
		alertaSucesso.setTitle("Sucesso!");
		alertaSucesso.setHeaderText("A operacao foi realizada com exito!");
		alertaSucesso.showAndWait();
	}
	
	/**
	 * Alerta do tipo "Error" que o login e a senha inserida não pode ser utilizada.
	 */
	public void loginESenhaReservado() {
		alertaErro.setTitle("Erro!");
		alertaErro.setHeaderText("O login e a senha inseridos sao reservados ao sistema!");
		alertaErro.setContentText("Tente novamente com outros dados!");
		alertaErro.showAndWait();
	}
 

}
