package alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertasGerais {
	
	private Alert alertaErro = new Alert(AlertType.ERROR);
	private Alert alertaSucesso = new Alert(AlertType.INFORMATION);
	
	public void alertaErroLogin() {
		alertaErro.setTitle("N�o foi poss�vel realizar o login.");
		alertaErro.setHeaderText("Senha e/ou Login inv�lidos!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	public void alertaArrayOutOfBounds() {
		alertaErro.setTitle("N�o foi poss�vel realizar o login.");
		alertaErro.setHeaderText("Erro ao carregar o banco de dados!");
		alertaErro.setContentText("Tente novamente ou reinicie o sistema!");
		alertaErro.showAndWait();
	}
	
	public void alertaFaltaInformacoesLogin() {
		alertaErro.setTitle("N�o foi poss�vel realizar o login.");
		alertaErro.setHeaderText("Insira todos os dados solicitados!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
		
	}
	
	public void erroNaOperacao() {
		alertaErro.setTitle("N�o foi poss�vel realizar essa opera��o.");
		alertaErro.setHeaderText("Tente novamente. Caso o erro persista reinicie o sistema!");
		alertaErro.showAndWait();
	}
	
	public void faltaDadosOuIncorretos() {
		alertaErro.setTitle("N�o foi poss�vel realizar essa opera��o.");
		alertaErro.setHeaderText("Falta alguma informa��o ou ao menos um dos dados inseridos � inv�lido!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	public void itemNaoSelecionado() {
		alertaErro.setTitle("N�o foi poss�vel realizar esse procedimento!");
		alertaErro.setHeaderText("Selecione um item da tabela para realizar essa opera��o!");
		alertaErro.setContentText("Tente novamente!");
		alertaErro.showAndWait();
	}
	
	public void informarSucessoOperacao() {
		alertaSucesso.setTitle("Sucesso!");
		alertaSucesso.setHeaderText("A opera��o foi realizada com �xito!");
		alertaSucesso.showAndWait();
	}
 

}
