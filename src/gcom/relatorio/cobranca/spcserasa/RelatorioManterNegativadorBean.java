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

public class RelatorioManterNegativadorBean implements RelatorioBean {
	   
	    private String id;    
	
	    private String codigoAgente;
	    
	    private String cliente;
	    
	    private String nome;
	    
	    private String imovel;
	    
	    private String numeroInscricaoEstadual;
	    
	    private String indicadorUso;

		public RelatorioManterNegativadorBean(String id, String codigoAgente, String cliente,String nome, String imovel, String numeroInscricaoEstadual, String indicadorUso) {
			super();
			
			this.id = id;
			this.codigoAgente = codigoAgente;
			this.cliente = cliente;
			this.nome = nome;
			this.imovel = imovel;
			this.numeroInscricaoEstadual = numeroInscricaoEstadual;
			this.indicadorUso = indicadorUso;
		}

		/**
		 * @return Retorna o campo cliente.
		 */
		public String getCliente() {
			return cliente;
		}

		/**
		 * @param cliente O cliente a ser setado.
		 */
		public void setCliente(String cliente) {
			this.cliente = cliente;
		}

		/**
		 * @return Retorna o campo codigoAgente.
		 */
		public String getCodigoAgente() {
			return codigoAgente;
		}

		/**
		 * @param codigoAgente O codigoAgente a ser setado.
		 */
		public void setCodigoAgente(String codigoAgente) {
			this.codigoAgente = codigoAgente;
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
		 * @return Retorna o campo imovel.
		 */
		public String getImovel() {
			return imovel;
		}

		/**
		 * @param imovel O imovel a ser setado.
		 */
		public void setImovel(String imovel) {
			this.imovel = imovel;
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
		 * @return Retorna o campo numeroInscricaoEstadual.
		 */
		public String getNumeroInscricaoEstadual() {
			return numeroInscricaoEstadual;
		}

		/**
		 * @param numeroInscricaoEstadual O numeroInscricaoEstadual a ser setado.
		 */
		public void setNumeroInscricaoEstadual(String numeroInscricaoEstadual) {
			this.numeroInscricaoEstadual = numeroInscricaoEstadual;
		}

		/**
		 * @return Retorna o campo nome.
		 */
		public String getNome() {
			return nome;
		}

		/**
		 * @param nome O nome a ser setado.
		 */
		public void setNome(String nome) {
			this.nome = nome;
		}
	    
	    
	  
	   
		
	
}
