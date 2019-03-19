package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {
	private final int NMAX = 100;
	private final int TMAX = 8;

	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;

	public NumeroModel() {
		inGioco = false;
	}

	/**
	 * Avvia nuova partita
	 */
	public void newGame() {
		inGioco = true;
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;
	}

	public int tentativo(int t) {
		// controllo se la partita è in corso
		if (!inGioco) {
			throw new IllegalStateException("La partita è terminata");

		}
		// controllo se l'input è nel range corretto
		if (!tentativoValido(t)) {
			throw new InvalidParameterException(String.format("devi inserire un numero "+ "tra %d e %d", 1, NMAX));

		}
		/*if (t < 1 || t > NMAX) {
			throw new InvalidParameterException(String.format("devi inserire un numero" + "tra %d e %d", 1, NMAX));
		}*/
		//gestiamo il tentativo 
		this.tentativiFatti++;
		if(this.tentativiFatti==this.TMAX){
			this.inGioco=false;
		}

		if(t==this.segreto) {
			this.inGioco=false; // ho indovinato il gioco 
			return 0;
		}
		if(t>this.segreto) {
			return 1; //il tentativo è troppo alto
		}
		

		return -1; // il tentativo sarà troppo basso
	}



	public boolean tentativoValido(int t) {
		if (t < 1 || t > NMAX) {
			return false;

		} else
			return true;
	}

}
