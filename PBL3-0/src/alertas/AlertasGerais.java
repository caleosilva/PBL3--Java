package alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertasGerais {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	private Alert alertaSucesso = new Alert(AlertType.INFORMATION);
	
	public void alertaErroLogin() {
		alertaErro.setTitle("Não foi possível realizar o login.");
		alertaErro.setHeaderText("Senha e/ou Login inválidos!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	public void alertaArrayOutOfBounds() {
		alertaErro.setTitle("Não foi possível realizar o login.");
		alertaErro.setHeaderText("Erro ao carregar o banco de dados!");
		alertaErro.setContentText("Tente novamente ou reinicie o sistema!");
		alertaErro.showAndWait();
	}
	
	public void alertaFaltaInformacoesLogin() {
		alertaErro.setTitle("Não foi possível realizar o login.");
		alertaErro.setHeaderText("Insira todos os dados solicitados!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
		
	}
	
	public void erroNaOperacao() {
		alertaErro.setTitle("Não foi possível realizar essa operação.");
		alertaErro.setHeaderText("Tente novamente. Caso o erro persista reinicie o sistema!");
		alertaErro.showAndWait();
	}
	
	public void faltaDadosOuIncorretos() {
		alertaErro.setTitle("Não foi possível realizar essa operação.");
		alertaErro.setHeaderText("Falta alguma informação ou ao menos um dos dados inseridos é inválido!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	public void itemNaoSelecionado() {
		alertaErro.setTitle("Não foi possível realizar esse procedimento!");
		alertaErro.setHeaderText("Selecione um item da tabela para realizar essa operação!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	public void informarSucessoOperacao() {
		alertaSucesso.setTitle("Sucesso!");
		alertaSucesso.setHeaderText("A operação foi realizada com êxito!");
		alertaSucesso.showAndWait();
	}
 

}
