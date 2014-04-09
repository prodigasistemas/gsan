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

public class RelatorioManterNegativadorContratoBean implements RelatorioBean {
	   
	    private String id;    
	
	    private String negativador;
	    
	    private String numeroContrato;
	    
	    private String dataInicio;
	    
	    private String dataFim;
	    
	  
	    /**
	     * 
	     * Construtor de RelatorioManterNegativadorContratoBean 
	     * 
	     * @param id
	     * @param negativador
	     * @param numeroContrato
	     * @param dataInicio
	     * @param dataFim
	     */
		public RelatorioManterNegativadorContratoBean(
			String id, String negativador,String numeroContrato, String dataInicio, String dataFim) {
			super();
			// TODO Auto-generated constructor stub
			this.id = id;		
			this.negativador = negativador;	
			this.numeroContrato = numeroContrato;
			this.dataInicio = dataInicio;
			this.dataFim = dataFim;
		
			
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
		 * @return Retorna o campo numeroContrato.
		 */
		public String getNumeroContrato() {
			return numeroContrato;
		}


		/**
		 * @param numeroContrato O numeroContrato a ser setado.
		 */
		public void setNumeroContrato(String numeroContrato) {
			this.numeroContrato = numeroContrato;
		}


		/**
		 * @return Retorna o campo dataFim.
		 */
		public String getDataFim() {
			return dataFim;
		}


		/**
		 * @param dataFim O dataFim a ser setado.
		 */
		public void setDataFim(String dataFim) {
			this.dataFim = dataFim;
		}


		/**
		 * @return Retorna o campo dataInicio.
		 */
		public String getDataInicio() {
			return dataInicio;
		}


		/**
		 * @param dataInicio O dataInicio a ser setado.
		 */
		public void setDataInicio(String dataInicio) {
			this.dataInicio = dataInicio;
		}


	

		
	
}
