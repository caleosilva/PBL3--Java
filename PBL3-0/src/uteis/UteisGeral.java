package uteis;

import static java.lang.Integer.parseInt;

import java.time.format.DateTimeFormatter;

import excecoes.InputsIncorretos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Classe responsavel por conter metodos uteis para que as outras classes utilizem.
 * 
 * @author Caleo Silva e Joao Pedro.
 *
 */
public class UteisGeral {
	
	/**
	 * Classe reponsavel por permitir apenas a entrada de valores numericos inteiros.
	 * 
	 * @param campo Objeto do tipo TextField que sera manipulado.
	 */
	public void validarCampoNumerico(TextField campo) {
		campo.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					try {
						if (!newValue.isBlank()) {
							parseInt(newValue);
						}
					} catch(Exception e) {
						campo.setText(oldValue);
					}
				}
		);
	}
	
	/**
	 * Classe reponsavel por permitir apenas a entrada de valores numericos decimais.
	 * @param textfield campo Objeto do tipo TextField que sera manipulado.
	 */
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
	
	/**
	 * Classe responsavel por validar a selecao de uma informacao em um ChoiceBox.
	 * @param informacao Objeto do tipo ChoiceBox que sera validado.
	 * @return Object que foi validado.
	 * @throws InputsIncorretos Excecao caso o valor inserido nao seja valido.
	 */
	public Object verificarChoiceBoxObjeto(ChoiceBox<Object> informacao) throws InputsIncorretos {
		if (informacao.getValue() == null) {
			throw new InputsIncorretos("Informa��o inv�lida!");
		}
		return informacao.getValue();
	}
	
	/**
	 * lasse responsavel por validar a selecao de uma informacao em um ChoiceBox.
	 * 
	 * @param informacao Objeto do tipo ChoiceBox que sera validado.
	 * @return Objeto do tipo String contendo a informacao validada.
	 * @throws InputsIncorretos Excecao caso o valor inserido nao seja valido.
	 */
	public String verificarChoiceBoxString(ChoiceBox<String> informacao) throws InputsIncorretos {
		if (informacao.getValue() == null) {
			throw new InputsIncorretos("Informa��o inv�lida!");
		}
		return informacao.getValue();
	}
	
	/**
	 * Metodo responsavel por validar  uma informacao inserida em um TextField.
	 * @param informacao Objeto do tipo TextField que sera validado.
	 * @return Objeto do tipo String contendo a informacao validada.
	 * @throws InputsIncorretos Excecao caso o valor inserido nao seja valido.
	 */
	public String verificarTextField(TextField informacao) throws InputsIncorretos {
		if (informacao.getText().isBlank()) {
			throw new InputsIncorretos("Informa��o inv�lida!");
		}
		return informacao.getText();
	}
	
	/**
	 * Metodo responsavel por validar uma informacao inserida em um PassWordField.
	 * 
	 * @param informacao Objeto do tipo PassWordField que sera validado.
	 * @return Objeto do tipo String contendo a informacao validada.
	 * @throws InputsIncorretos Excecao caso o valor inserido nao seja valido.
	 */
	public String verificarPassWordField(PasswordField informacao) throws InputsIncorretos {
		if (informacao.getText().isEmpty()) {
			System.out.println("Joguei a bomba");
			throw new InputsIncorretos("Informa��o inv�lida!");
		}
		return informacao.getText();
	}
	
	public String validarData(DatePicker informacao) throws InputsIncorretos{
		if(informacao.getValue() == null) {
			throw new InputsIncorretos("Data incorreta!");
		}
		DateTimeFormatter fomatoDataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String validade = informacao.getValue().format(fomatoDataBr);
		return validade;
	}
	
}
