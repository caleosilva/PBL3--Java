package controller.views;

import java.net.URL;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;

import alertas.AlertasGerais;
import bancoDeDados.Dados;
import excecoes.InputsIncorretos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Fornecedor;
import model.facade.GerenciadorDeProduto;
import uteis.UteisGeral;
import uteis.UteisProduto;


public class ProdutoTelaCadastrarController implements Initializable {
	
	private String[] unidadesDeMedida = {"Quilograma", "Litro", "Unidade"};
	private ObservableList<String> dadosUnidadesDeMedida;
	private ObservableList<Fornecedor> dadosFornecedores;
	
	private GerenciadorDeProduto gdp = new GerenciadorDeProduto();
	private AlertasGerais alertas = new AlertasGerais();
	
	private UteisProduto uteisProduto = new UteisProduto();
	private UteisGeral uteisGeral = new UteisGeral();
	
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
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoConfirmarCadastro(ActionEvent event) {
    	HashMap<String, Object> data = juntarInformacoes();
    	boolean sucesso = gdp.cadastrarProdutos(data);
    	
    	if(sucesso) {
    		alertas.informarSucessoOperacao();
    		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        	stage.close();
    	}
    	
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarNomeFornecedores();
		carregarUnidadeDeMedidas();
		
		uteisGeral.validarCampoDecimal(campoQuantidade);
		uteisGeral.validarCampoDecimal(campoPreco);
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
	
	public HashMap<String, Object> juntarInformacoes() {
		
		HashMap<String, Object> informacoes = null;
		try {
			String nome = uteisGeral.verificarTextField(campoNome);
			double preco = Double.parseDouble(uteisGeral.verificarTextField(campoPreco));
			double quantidade = Double.parseDouble(uteisGeral.verificarTextField(campoQuantidade));
			int unidadeDeMedida = uteisProduto.transformarUnidadeDeMedida(uteisGeral.verificarChoiceBoxString(campoUnidadeDeMedida));
			Fornecedor fornecedor = uteisProduto.getCampoFornecedor(campoFornecedor.getValue());
			String validade = uteisProduto.validarData(campoValidade);
			
			informacoes = new HashMap<>();
			
			informacoes.put("nome", nome);
			informacoes.put("preco", preco);
			informacoes.put("quantidade", quantidade);
			informacoes.put("unidadeDeMedida", unidadeDeMedida);
			informacoes.put("fornecedor", fornecedor);
			informacoes.put("validade", validade);
		} catch(InputsIncorretos e) {
			alertas.faltaDadosOuIncorretos();
		}
		return informacoes;
	}
	
}
