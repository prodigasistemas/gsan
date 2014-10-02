package gcom.relatorio.cobranca.spcserasa;

import java.math.BigDecimal;

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
 * @created 16 de maio de 2008
 * @version 1.0
 */

public class RelatorioNegativadorResultadoSimulacaoBean implements RelatorioBean {
	   
	    private String idComando;
	    private String descricaoTitulo;
	    private String sequencial;
	    private String idImovel;
	    private BigDecimal valorDebito;
	    private String numeroCpf;
	    private String numeroCnpj;
	    

	    /**
	     * 
	     * Construtor de RelatorioManterNegativadorExclusaoMotivoBean 
	     * 
	     * @param id
	     * @param descricao
	     * @param codigoMotivo
	     * @param negativador	   
	     * @param indicadorUso
	     */
	    
		public RelatorioNegativadorResultadoSimulacaoBean(String idComando,
			String descricaoTitulo,String sequencial,String idImovel,BigDecimal valorDebito,String numeroCpf,String numeroCnpj  ) {
			super();
			
			this.idComando=idComando;
			this.descricaoTitulo=descricaoTitulo;
		    this.sequencial=sequencial;
		    this.idImovel=idImovel;
		    this.valorDebito=valorDebito;
		    this.numeroCpf=numeroCpf;
		    this.numeroCnpj=numeroCnpj;					
		}


		
		/**
		 * @return Retorna o campo idImovel.
		 */
		public String getIdImovel() {
			return idImovel;
		}


		/**
		 * @param idImovel O idImovel a ser setado.
		 */
		public void setIdImovel(String idImovel) {
			this.idImovel = idImovel;
		}


		/**
		 * @return Retorna o campo numeroCnpj.
		 */
		public String getNumeroCnpj() {
			return numeroCnpj;
		}


		/**
		 * @param numeroCnpj O numeroCnpj a ser setado.
		 */
		public void setNumeroCnpj(String numeroCnpj) {
			this.numeroCnpj = numeroCnpj;
		}


		/**
		 * @return Retorna o campo numeroCpf.
		 */
		public String getNumeroCpf() {
			return numeroCpf;
		}


		/**
		 * @param numeroCpf O numeroCpf a ser setado.
		 */
		public void setNumeroCpf(String numeroCpf) {
			this.numeroCpf = numeroCpf;
		}


		/**
		 * @return Retorna o campo valorDebito.
		 */
		public BigDecimal getValorDebito() {
			return valorDebito;
		}


		/**
		 * @param valorDebito O valorDebito a ser setado.
		 */
		public void setValorDebito(BigDecimal valorDebito) {
			this.valorDebito = valorDebito;
		}



		/**
		 * @return Retorna o campo descricaoTitulo.
		 */
		public String getDescricaoTitulo() {
			return descricaoTitulo;
		}



		/**
		 * @param descricaoTitulo O descricaoTitulo a ser setado.
		 */
		public void setDescricaoTitulo(String descricaoTitulo) {
			this.descricaoTitulo = descricaoTitulo;
		}



		/**
		 * @return Retorna o campo sequencial.
		 */
		public String getSequencial() {
			return sequencial;
		}



		/**
		 * @param sequencial O sequencial a ser setado.
		 */
		public void setSequencial(String sequencial) {
			this.sequencial = sequencial;
		}



		/**
		 * @return Retorna o campo idComando.
		 */
		public String getIdComando() {
			return idComando;
		}



		/**
		 * @param idComando O idComando a ser setado.
		 */
		public void setIdComando(String idComando) {
			this.idComando = idComando;
		}

	


		
		
	
}
