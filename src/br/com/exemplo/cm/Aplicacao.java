package br.com.exemplo.cm;

import br.com.exemplo.cm.modelo.Tabuleiro;
import br.com.exemplo.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
		new TabuleiroConsole(tabuleiro);
		
		System.out.println(tabuleiro);
	}

}
