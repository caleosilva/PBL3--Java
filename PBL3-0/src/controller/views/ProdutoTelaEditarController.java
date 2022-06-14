package controller.views;

import java.net.URL;
import java.time.LocalDate;
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

public class ProdutoTelaEditarController implements Initializable{
	
	private String[] unidadesDeMedida = {"Quilograma", "Litro", "Unidade"};
	private ObservableList<String> dadosUnidadesDeMedida;
	private ObservableList<Fornecedor> dadosFornecedores;
	
	private AlertasGerais alertas = new AlertasGerais();
	private UteisProduto uteisProduto = new UteisProduto();
	private UteisGeral uteisGeral = new UteisGeral();
	private GerenciadorDeProduto gdp = new GerenciadorDeProduto();
	
	private String id;

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
    void botaoConfirmarEdicao(ActionEvent event) {
    	HashMap<String, Object> dadosAtuais = gdp.encontrarProduto(id);
    	HashMap<String, Object> novosDados = juntarInformacoes();
    	
    	boolean sucesso = false;
    	
    	if (novosDados != null) {
    		sucesso = gdp.editarProdutos(dadosAtuais, novosDados);
    	}
    	
    	if (sucesso) {
    		alertas.informarSucessoOperacao();
    		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        	stage.close();
    	} else {
    		alertas.dadosIncorretos();
    	}    	
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarUnidadeDeMedidas();
		carregarNomeFornecedores();
		
		uteisGeral.validarCampoDecimal(campoPreco);
		uteisGeral.validarCampoDecimal(campoQuantidade);
	}
    
    public HashMap<String, Object> juntarInformacoes() {
		HashMap<String, Object> informacoes = null;
		try {
			String nome = uteisProduto.verificarTextField(campoNome);
			double preco = Double.parseDouble(uteisProduto.verificarTextField(campoPreco));
			double quantidade = Double.parseDouble(uteisProduto.verificarTextField(campoQuantidade));
			int unidadeDeMedida = uteisProduto.transformarUnidadeDeMedida(uteisProduto.verificarChoiceBoxString(campoUnidadeDeMedida));
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
			alertas.dadosIncorretos();
		}
		return informacoes;
	}
    
    public void adicionarInformacoes(String nome, double preco, LocalDate data, double quantidade,
    		Fornecedor fornecedor, String unidadeDeMedida, String idProduto) {
    	
    	this.id = idProduto;
    	
    	String precostr = String.valueOf(preco);
    	String quantidadeStr = String.valueOf(quantidade);
    	
    	campoNome.setText(nome);
    	campoPreco.setText(precostr);
    	campoValidade.setValue(data);
    	campoQuantidade.setText(quantidadeStr);
    	campoUnidadeDeMedida.setValue(unidadeDeMedida);
    	campoFornecedor.setValue(fornecedor);    	
    }

	public void carregarUnidadeDeMedidas() {
		dadosUnidadesDeMedida = FXCollections.observableArrayList(unidadesDeMedida);
		campoUnidadeDeMedida.setItems(dadosUnidadesDeMedida);
	}
	
	public void carregarNomeFornecedores() {
		dadosFornecedores = FXCollections.observableArrayList(Dados.getListaFornecedor());
		campoFornecedor.setItems(dadosFornecedores);
	}

}
