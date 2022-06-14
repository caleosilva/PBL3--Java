package uteisProduto;

import java.time.format.DateTimeFormatter;

import excecoes.InputsIncorretos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Fornecedor;

public class UteisProduto {
	
	public Object verificarChoiceBoxObjeto(ChoiceBox<Object> informacao) throws InputsIncorretos {
		if (informacao.getValue() == null) {
			throw new InputsIncorretos("Informação inválida!");
		}
		return informacao.getValue();
	}
	
	public String verificarChoiceBoxString(ChoiceBox<String> informacao) throws InputsIncorretos {
		if (informacao.getValue() == null) {
			throw new InputsIncorretos("Informação inválida!");
		}
		return informacao.getValue();
	}
	
	public String verificarTextField(TextField informacao) throws InputsIncorretos {
		if (informacao.getText().isBlank()) {
			throw new InputsIncorretos("Informação inválida!");
		}
		return informacao.getText();
	}
	
	public Integer transformarUnidadeDeMedida(String informacao) {
		
		int novoValor = 0;
		
		switch(informacao) {
		
			case "Quilograma": {
				novoValor = 1;
				break;
			}
			case "Litro": {
				novoValor = 2;
				break;
			}
			case "Unidade": {
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
	
	public Fornecedor getCampoFornecedor(Fornecedor fornecedor) throws InputsIncorretos{
		if(fornecedor == null) {
			throw new InputsIncorretos("Fornecedor incorreto!");
		}
		return fornecedor;
	}

}
