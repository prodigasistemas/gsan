package gcom.cobranca.bean;

import java.io.Serializable;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Thiago Vieira
 * @date 29/01/2008
 */

public class NegativadorMovimentoRetornoResumoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 private String nomeNegativador;
	    
	    private String dataProcessamento;
	   
	    private String horaProcessamento;

	    private String numeroSequencialArquivo;
	    
	    private String numeroRegistros;

		/**
		 * @return Retorna o campo dataProcessamento.
		 */
		public String getDataProcessamento() {
			return dataProcessamento;
		}

		/**
		 * @param dataProcessamento O dataProcessamento a ser setado.
		 */
		public void setDataProcessamento(String dataProcessamento) {
			this.dataProcessamento = dataProcessamento;
		}

		/**
		 * @return Retorna o campo horaProcessamento.
		 */
		public String getHoraProcessamento() {
			return horaProcessamento;
		}

		/**
		 * @param horaProcessamento O horaProcessamento a ser setado.
		 */
		public void setHoraProcessamento(String horaProcessamento) {
			this.horaProcessamento = horaProcessamento;
		}

		/**
		 * @return Retorna o campo nomeNegativador.
		 */
		public String getNomeNegativador() {
			return nomeNegativador;
		}

		/**
		 * @param nomeNegativador O nomeNegativador a ser setado.
		 */
		public void setNomeNegativador(String nomeNegativador) {
			this.nomeNegativador = nomeNegativador;
		}

		/**
		 * @return Retorna o campo numeroRegistros.
		 */
		public String getNumeroRegistros() {
			return numeroRegistros;
		}

		/**
		 * @param numeroRegistros O numeroRegistros a ser setado.
		 */
		public void setNumeroRegistros(String numeroRegistros) {
			this.numeroRegistros = numeroRegistros;
		}

		/**
		 * @return Retorna o campo numeroSequencialArquivo.
		 */
		public String getNumeroSequencialArquivo() {
			return numeroSequencialArquivo;
		}

		/**
		 * @param numeroSequencialArquivo O numeroSequencialArquivo a ser setado.
		 */
		public void setNumeroSequencialArquivo(String numeroSequencialArquivo) {
			this.numeroSequencialArquivo = numeroSequencialArquivo;
		}
	
	

	
	
	
}
