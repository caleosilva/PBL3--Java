package uteis;

import static java.lang.Integer.parseInt;

import excecoes.InputsIncorretos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class UteisGeral {
	
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
	
	
}
