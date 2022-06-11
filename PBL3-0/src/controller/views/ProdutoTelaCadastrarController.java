package controller.views;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import alertas.AlertasGerais;
import bancoDeDados.Dados;
import excecoes.InputsIncorretos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Fornecedor;
import model.ProdutoEspecifico;

import static java.lang.Integer.parseInt;

import java.lang.reflect.InvocationTargetException;

import alertas.AlertasGerais;

public class ProdutoTelaCadastrarController implements Initializable {
	
	private AlertasGerais alertas = new AlertasGerais();
	private String[] unidadesDeMedida = {"Quilograma(s)", "Unidade(s)", "Litro(s)"};
	private ObservableList<String> dadosUnidadesDeMedida;;
	private ObservableList<Fornecedor> dadosFornecedores;
	
    @FXML
    private ChoiceBox<Fornecedor> campoFornecedor;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoPreco;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private ChoiceBox<String> campoUnidadeDeMedida;

    @FXML
    private DatePicker campoValidade;

    @FXML
    void botaoCancelar(ActionEvent event) {

    }

    @FXML
    void botaoConfirmarCadastro(ActionEvent event) {
    	
    	juntarInformacoes();
    	
//    	verificarInformacoes();
    	
//    	System.out.println("Nome: " + campoNome.getText());
//    	System.out.println("Pre�o: "+ campoPreco.getText());
//    	System.out.println("Quantidade: "+ campoQuantidade.getText());
//    	
//    	System.out.println("UnidadeDeMedida: " + campoUnidadeDeMedida.getValue());
//    	System.out.println("Validade: "+ campoValidade.getValue());
//    	System.out.println("Fornecedor1 : "+ campoFornecedor.getValue());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarNomeFornecedores();
		carregarUnidadeDeMedidas();
		
		validarCampoDecimal(campoQuantidade);
		validarCampoDecimal(campoPreco);
		
	}
	
	public void validarCampoNumerico(TextField campo) {
		campo.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					try {
						if (!newValue.isBlank()) {
							parseInt(newValue);
						}
						
					} catch(Exception e) {
						this.campoQuantidade.setText(oldValue);
					}
				}
		);
	}
	
	public void validarCampoDecimal(TextField textfield) {
		textfield.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					try {						
						if (!newValue.matches("\\d*(\\.\\d*)?")) {
							textfield.setText(oldValue);
						}
					} catch(Exception e) {
						textfield.setText(oldValue);
					}
				}
		);
	}

	public void getCampoUnidadeDeMedida(ActionEvent event) {
		this.campoUnidadeDeMedida.getValue();
	}
	
	public void carregarNomeFornecedores() {
		dadosFornecedores = FXCollections.observableArrayList(Dados.getListaFornecedor());
		campoFornecedor.setItems(dadosFornecedores);
	}
	
	public void carregarUnidadeDeMedidas() {
		dadosUnidadesDeMedida = FXCollections.observableArrayList(unidadesDeMedida);
		campoUnidadeDeMedida.setItems(dadosUnidadesDeMedida);
	}
	
	//=============================================================================================
	public Object verificarChoiceBoxObjeto(ChoiceBox<Object> informacao) throws InputsIncorretos {
		if (informacao.getValue() == null) {
			throw new InputsIncorretos("Informa��o inv�lida!");
		}
		return informacao.getValue();
	}
	
	public String verificarChoiceBoxString(ChoiceBox<String> informacao) throws InputsIncorretos {
		if (informacao.getValue() == null) {
			throw new InputsIncorretos("Informa��o inv�lida!");
		}
		return informacao.getValue();
	}
	
	public String verificarTextField(TextField informacao) throws InputsIncorretos {
		if (informacao.getText().isBlank()) {
			throw new InputsIncorretos("Informa��o inv�lida!");
		}
		return informacao.getText();
	}
	
	public Integer transformarUnidadeDeMedida(String informacao) {
		
		int novoValor = 0;
		
		switch(informacao) {
		
			case "Quilograma(s)": {
				novoValor = 1;
				break;
			}
			case "Litro(s)": {
				novoValor = 2;
				break;
			}
			case "Unidade(s)": {
				novoValor = 3;
				break;
			}
			default:
				return null;
			
		}
		return novoValor;
	}	

	public String validarData(DatePicker informacao) throws InputsIncorretos{
		if(informacao.getValue() == null) {
			throw new InputsIncorretos("Data incorreta!");
		}
		DateTimeFormatter fomatoDataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String validade = informacao.getValue().format(fomatoDataBr);
		return validade;
	}
	
	public Fornecedor getCampoFornecedor() throws InputsIncorretos{
		Fornecedor fornecedor = campoFornecedor.getSelectionModel().getSelectedItem();
		if(fornecedor == null) {
			throw new InputsIncorretos("Fornecedor incorreto!");
		}
		return fornecedor;
	}
	
	public HashMap<String, Object> juntarInformacoes() {
		
		HashMap<String, Object> listaDados = null;
		try {
			String nome = verificarTextField(campoNome);
			double preco = Double.parseDouble(verificarTextField(campoPreco));
			double quantidade = Double.parseDouble(verificarTextField(campoQuantidade));
			int unidadeDeMedida = transformarUnidadeDeMedida(verificarChoiceBoxString(campoUnidadeDeMedida));
			Fornecedor fornecedor = getCampoFornecedor();
			String validade = validarData(campoValidade);
			
			listaDados = new HashMap<>();
			
			listaDados.put("nome", nome);
			listaDados.put("preco", preco);
			listaDados.put("quantidade", quantidade);
			listaDados.put("unidadeDeMedida", unidadeDeMedida);
			listaDados.put("fornecedor", fornecedor);
			listaDados.put("validade", validade);
			
		} catch(InputsIncorretos e) {
			alertas.dadosIncorretos();
		}
		
		return listaDados;
	} 
	
}