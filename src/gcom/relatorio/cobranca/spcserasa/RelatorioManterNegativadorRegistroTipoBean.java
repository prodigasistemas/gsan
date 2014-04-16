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
 * @author Rafael Corrêa
 * @created 7 de Novembro de 2005
 * @version 1.0
 */

public class RelatorioManterNegativadorRegistroTipoBean implements RelatorioBean {
	   
	    private String id;
	    
	    private String descricao;
	   
	    private String codigoRegistro;

	    private String negativador;

	    /**
	     * 
	     * Construtor de RelatorioManterNegativadorRegistroTipoBean 
	     * 
	     * @param negativadorRegistroTipo
	     * @param descricaoRegistroTipo
	     * @param codigoRegistro
	     * @param ultimaAlteracao
	     * @param negativador
	     */
	    
		public RelatorioManterNegativadorRegistroTipoBean(
			String id, String descricao,
			String codigoRegistro, String negativador) {
			super();
			// TODO Auto-generated constructor stub
			this.id = id;
			this.descricao = descricao;
			this.codigoRegistro = codigoRegistro;
			this.negativador = negativador;
		}

		/**
		 * @return Retorna o campo codigoRegistro.
		 */
		public String getCodigoRegistro() {
			return codigoRegistro;
		}

		/**
		 * @param codigoRegistro O codigoRegistro a ser setado.
		 */
		public void setCodigoRegistro(String codigoRegistro) {
			this.codigoRegistro = codigoRegistro;
		}

		/**
		 * @return Retorna o campo descricaoRegistroTipo.
		 */
		public String getDescricao() {
			return descricao;
		}

		/**
		 * @param descricaoRegistroTipo O descricaoRegistroTipo a ser setado.
		 */
		public void setDescricao(String descricao) {
			this.descricao = descricao;
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

	
}
