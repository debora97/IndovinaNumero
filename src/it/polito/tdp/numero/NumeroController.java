package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.numero.model.NumeroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	private NumeroModel model;
	
	
	private final int NMAX = 100;
	private final int TMAX = 8;

	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private HBox boxcontrollopartita;

	@FXML
	private TextField txtRimasti;

	@FXML
	private HBox boxControlloTentativi;

	@FXML
	private TextField txtTentativo;

	@FXML
	private TextArea txtMessaggi;

	@FXML
	void handleNuovaPartita(ActionEvent event) {
		// gestire una nuova partita (dati ed interfaccia)
		// GIOCO
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;
		this.inGioco = true;

		// GRAFICA
		boxcontrollopartita.setDisable(true);
		boxControlloTentativi.setDisable(false);
		txtMessaggi.clear();
		txtRimasti.setText(Integer.toString(this.TMAX));

	}

	@FXML
	void hendleProvaTentativo(ActionEvent event) {
		// leggi il valore inserito
		String ts = txtTentativo.getText();
		
		int tentativo;
		try {
			tentativo = Integer.parseInt(ts);
		} catch (NumberFormatException e) {
			// la string ainserita non è valida
			txtMessaggi.appendText("Non è un numero valido\n");
			return;
		}
		tentativiFatti++;

		if (tentativo == segreto) {
			txtMessaggi.appendText("Complimenti hai indovinato in " + tentativiFatti + " tentativi\n");
			boxcontrollopartita.setDisable(false);
			boxControlloTentativi.setDisable(true);
			this.inGioco = false;
			return;
		}
		// controlla se indovinato
		// verifica i tentativi
		// --> fine partita
		if (tentativiFatti == TMAX) {
			txtMessaggi.appendText("Hai perso il numero segreto era" + segreto + "\n");
			boxcontrollopartita.setDisable(false);
			boxControlloTentativi.setDisable(true);
			this.inGioco = false;
			return;
		}
		// --> informa se basso o alto
		if (tentativo < segreto) {
			txtMessaggi.appendText("tentativo troppo BASSO\n");
		} else {
			txtMessaggi.appendText("tentativo troppo ALTO\n");
		}
		// aggiorna interfaccia
		txtRimasti.setText(Integer.toString(TMAX - tentativiFatti));

	}

	@FXML
	void initialize() {
		assert boxcontrollopartita != null : "fx:id=\"boxcontrollopartita\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Numero.fxml'.";
		assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'Numero.fxml'.";

	}
	public void setModel(NumeroModel model) {
		this.model = model;
	}

}
