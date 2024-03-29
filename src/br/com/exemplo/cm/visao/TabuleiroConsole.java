package br.com.exemplo.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.exemplo.cm.excessao.ExplosaoException;
import br.com.exemplo.cm.excessao.SairException;
import br.com.exemplo.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while (continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Tchau!!");
		}finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro.toString());	
				
				String digitado = capturarValorDigitado("Digite (linha, Coluna):");
				Iterator<Integer> xy =Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 => Abrir ou 2 => (Des)Marcar: ");
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}	
			}
			System.out.println(tabuleiro);
			System.out.println("Voc� Ganhou!!!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Voc� Perdeu!");
		}
		
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
	
}
