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
 * @created 28 de fevereiro de 2008
 * @version 1.0
 */

public class RelatorioManterNegativadorRetornoMotivoBean implements RelatorioBean {
	   
	    private String id;
	    
	    private String descricao;
	   
	    private String codigoMotivo;

	    private String negativador;
	    
	    private String indicadorRegistroAceito;

	    private String indicadorUso;
	    
	    
	    /**
	     * 
	     * Construtor de RelatorioManterNegativadorRetornoMotivoBean 
	     * 
	     * @param id
	     * @param descricao
	     * @param codigoMotivo
	     * @param negativador
	     * @param indicadorRegistroAceito
	     * @param indicadorUso
	     */
	    
		public RelatorioManterNegativadorRetornoMotivoBean(
			String id, String descricao,
			String codigoMotivo, String negativador,String indicadorRegistroAceito,String indicadorUso) {
			super();
			// TODO Auto-generated constructor stub
			this.id = id;
			this.descricao = descricao;
			this.codigoMotivo = codigoMotivo;
			this.negativador = negativador;
			this.indicadorRegistroAceito = indicadorRegistroAceito;
			this.indicadorUso = indicadorUso;
			
		}


		/**
		 * @return Retorna o campo codigoMotivo.
		 */
		public String getCodigoMotivo() {
			return codigoMotivo;
		}


		/**
		 * @param codigoMotivo O codigoMotivo a ser setado.
		 */
		public void setCodigoMotivo(String codigoMotivo) {
			this.codigoMotivo = codigoMotivo;
		}


		/**
		 * @return Retorna o campo descricao.
		 */
		public String getDescricao() {
			return descricao;
		}


		/**
		 * @param descricao O descricao a ser setado.
		 */
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}


		/**
		 * @return Retorna o campo id.
		 */
		public String getId() {
			return id;
		}


		/**
		 * @param id O id a ser setado.
		 */
		public void setId(String id) {
			this.id = id;
		}


		/**
		 * @return Retorna o campo indicadorRegistroAceito.
		 */
		public String getIndicadorRegistroAceito() {
			return indicadorRegistroAceito;
		}


		/**
		 * @param indicadorRegistroAceito O indicadorRegistroAceito a ser setado.
		 */
		public void setIndicadorRegistroAceito(String indicadorRegistroAceito) {
			this.indicadorRegistroAceito = indicadorRegistroAceito;
		}


		/**
		 * @return Retorna o campo indicadorUso.
		 */
		public String getIndicadorUso() {
			return indicadorUso;
		}


		/**
		 * @param indicadorUso O indicadorUso a ser setado.
		 */
		public void setIndicadorUso(String indicadorUso) {
			this.indicadorUso = indicadorUso;
		}


		/**
		 * @return Retorna o campo negativador.
		 */
		public String getNegativador() {
			return negativador;
		}


		/**
		 * @param negativador O negativador a ser setado.
		 */
		public void setNegativador(String negativador) {
			this.negativador = negativador;
		}

		
	
}
