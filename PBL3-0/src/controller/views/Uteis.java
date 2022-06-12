package controller.views;

import static java.lang.Integer.parseInt;

import javafx.scene.control.TextField;

public class Uteis {
	
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

}
