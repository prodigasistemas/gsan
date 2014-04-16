package gcom.relatorio.cobranca.spcserasa;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane 
 * @created 23 de Abril de 2008
 * @version 1.0
 */

public class RelatorioNegativadorMovimentoRetornoResumoBean implements RelatorioBean {
	   
	    private String nomeNegativador;
	    
	    private String dataProcessamento;
	   
	    private String horaProcessamento;

	    private String numeroSequencialArquivo;
	    
	    private String numeroRegistros;

		public RelatorioNegativadorMovimentoRetornoResumoBean(String nomeNegativador, String dataProcessamento, String horaProcessamento, String numeroSequencialArquivo, String numeroRegistros) {
			super();
			// TODO Auto-generated constructor stub
			this.nomeNegativador = nomeNegativador;
			this.dataProcessamento = dataProcessamento;
			this.horaProcessamento = horaProcessamento;
			this.numeroSequencialArquivo = numeroSequencialArquivo;
			this.numeroRegistros = numeroRegistros;
		}

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
