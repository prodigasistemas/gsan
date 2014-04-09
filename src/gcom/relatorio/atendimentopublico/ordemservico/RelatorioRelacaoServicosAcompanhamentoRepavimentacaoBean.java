package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.OSTipoPavimentoHelper;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

public class RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean implements RelatorioBean {
	
		private JRBeanCollectionDataSource arrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao;
		private ArrayList arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean;

	    private String  numeroOS;
	    private String  matricula;
	    private String  endereco;
	    private String  dataEncerramento;
	    private String  tipoPvtoRua;
	    private BigDecimal  metragem;
	    private String  dataConclusao;
	    private String  tipoPvtoRuaRetorno;
	    private BigDecimal  metragemRetorno;
	    
	    private String  dataAceite;
	    private String  dataRetorno;
	    private String  indicadorAceite;
	    private String indicadorAceiteComPercentualConvergencia;
	    private String motivo;
	    
	    private String dataRejeicao;
	    private String situacaoRetorno;
	    private String motivoRejeicao;
	    
	    private Boolean indicadorPrimeiraOcorrenciaConcluida;
	    private Boolean indicadorPrimeiraOcorrenciaPendente;
	    private Boolean indicadorPrimeiraOcorrenciaRejeitada;
	    private Boolean indicadorUltimaOcorrenciaRejeitada;
	    private String observacao;
	    
	    private JRBeanCollectionDataSource arrayJRSubValoresPorTipoPavimento;
	 
	    
		public RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean(
				String numeroOS, String matricula, String endereco, String dataEncerramento, 
				String tipoPvtoRua, BigDecimal metragem, String dataConclusao, String tipoPvtoRuaRetorno, 
				BigDecimal metragemRetorno, Collection colecaoTipoPavimentoRua,Collection colecaoTipoPavimentoRuaRet) {
			super();
			// TODO Auto-generated constructor stub
			
			this.arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean = new ArrayList();
			this.arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean.addAll(
				this.gerarSubRelatorio(colecaoTipoPavimentoRua,colecaoTipoPavimentoRuaRet));
			
			this.arrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao =
				new JRBeanCollectionDataSource(this.arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean);
			
			this.numeroOS = numeroOS;
			this.matricula = matricula;
			this.endereco = endereco;
			this.dataEncerramento = dataEncerramento;
			this.tipoPvtoRua = tipoPvtoRua;
			this.metragem = metragem;
			this.dataConclusao = dataConclusao;
			this.tipoPvtoRuaRetorno = tipoPvtoRuaRetorno;
			this.metragemRetorno = metragemRetorno;
		}
		
		public RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean(
				String numeroOS, String matricula, String endereco, String dataEncerramento, 
				String tipoPvtoRua, BigDecimal metragem, String dataConclusao, String tipoPvtoRuaRetorno, 
				BigDecimal metragemRetorno, Collection colecaoTipoPavimentoRua,Collection colecaoTipoPavimentoRuaRet,
				String motivo) {
			super();
			// TODO Auto-generated constructor stub
			
			this.arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean = new ArrayList();
			this.arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean.addAll(
				this.gerarSubRelatorio(colecaoTipoPavimentoRua,colecaoTipoPavimentoRuaRet));
			
			this.arrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao =
				new JRBeanCollectionDataSource(this.arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean);
			
			this.numeroOS = numeroOS;
			this.matricula = matricula;
			this.endereco = endereco;
			this.dataEncerramento = dataEncerramento;
			this.tipoPvtoRua = tipoPvtoRua;
			this.metragem = metragem;
			this.dataConclusao = dataConclusao;
			this.tipoPvtoRuaRetorno = tipoPvtoRuaRetorno;
			this.metragemRetorno = metragemRetorno;
			this.motivo = motivo;
		}


		
		  private Collection gerarSubRelatorio(Collection colecaoTipoPavimentoRua,Collection colecaoTipoPavimentoRuaRet){
		    	
				//Metodo que ordena a colecao somando todos os valores por tipo de pavimento.
			  	
			  
			  	Collection colecaoSubRelatorio = new ArrayList();
				
				if(colecaoTipoPavimentoRua != null && !colecaoTipoPavimentoRua.isEmpty()){
					
					String tipoPavimento = "INDICADO";					
			    	Iterator iterator = colecaoTipoPavimentoRua.iterator();		
			    	boolean primeiraVez = true;
					while (iterator.hasNext()) {
						
						if(!primeiraVez){
							tipoPavimento = "";
						}
						
						String descricao = "";
						String valor = "";
						OSTipoPavimentoHelper oSTipoPavimentoHelper1 = (OSTipoPavimentoHelper) iterator.next();
						descricao = oSTipoPavimentoHelper1.getDescricao();
						valor= Util.formatarMoedaReal(oSTipoPavimentoHelper1.getSomatorioArea());
						
//						
					
						Object relatorio =  null;
						
							relatorio = new RelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean(
									tipoPavimento,
									descricao, 
									valor);
					
						colecaoSubRelatorio.add(relatorio);
			    	
						primeiraVez= false;
					}
				}
				
				
				if(colecaoTipoPavimentoRuaRet != null && !colecaoTipoPavimentoRuaRet.isEmpty()){
					
					String tipoPavimento = "RETORNO";
			    	Iterator iteratorRet = colecaoTipoPavimentoRuaRet.iterator();	
			    	boolean primeiraVez = true;
					while (iteratorRet.hasNext()) {
						
						if(!primeiraVez){
							tipoPavimento = "";
						}
						
						String descricao = "";
						String valor = "";
						OSTipoPavimentoHelper oSTipoPavimentoHelper1 = (OSTipoPavimentoHelper) iteratorRet.next();
						descricao = oSTipoPavimentoHelper1.getDescricao();
						valor = Util.formatarMoedaReal(oSTipoPavimentoHelper1.getSomatorioArea());
						
						Object relatorio =  null;
						
						relatorio = new RelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean(
								    tipoPavimento,
									descricao, 
									valor);
					
						colecaoSubRelatorio.add(relatorio);
						

						primeiraVez= false;
			    	
					}
				}
				
				
				
				return colecaoSubRelatorio;
		    }
		    
		
		
		


		/**
		 * @return Retorna o campo dataConclusao.
		 */
		public String getDataConclusao() {
			return dataConclusao;
		}




		/**
		 * @param dataConclusao O dataConclusao a ser setado.
		 */
		public void setDataConclusao(String dataConclusao) {
			this.dataConclusao = dataConclusao;
		}




		/**
		 * @return Retorna o campo dataEncerramento.
		 */
		public String getDataEncerramento() {
			return dataEncerramento;
		}




		/**
		 * @param dataEncerramento O dataEncerramento a ser setado.
		 */
		public void setDataEncerramento(String dataEncerramento) {
			this.dataEncerramento = dataEncerramento;
		}




		/**
		 * @return Retorna o campo endereco.
		 */
		public String getEndereco() {
			return endereco;
		}




		/**
		 * @param endereco O endereco a ser setado.
		 */
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}




		/**
		 * @return Retorna o campo matricula.
		 */
		public String getMatricula() {
			return matricula;
		}




		/**
		 * @param matricula O matricula a ser setado.
		 */
		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}




		/**
		 * @return Retorna o campo metragem.
		 */
		public BigDecimal getMetragem() {
			return metragem;
		}




		/**
		 * @param metragem O metragem a ser setado.
		 */
		public void setMetragem(BigDecimal metragem) {
			this.metragem = metragem;
		}




		/**
		 * @return Retorna o campo metragemRetorno.
		 */
		public BigDecimal getMetragemRetorno() {
			return metragemRetorno;
		}




		/**
		 * @param metragemRetorno O metragemRetorno a ser setado.
		 */
		public void setMetragemRetorno(BigDecimal metragemRetorno) {
			this.metragemRetorno = metragemRetorno;
		}




		/**
		 * @return Retorna o campo numeroOS.
		 */
		public String getNumeroOS() {
			return numeroOS;
		}




		/**
		 * @param numeroOS O numeroOS a ser setado.
		 */
		public void setNumeroOS(String numeroOS) {
			this.numeroOS = numeroOS;
		}




		/**
		 * @return Retorna o campo tipoPvtoRua.
		 */
		public String getTipoPvtoRua() {
			return tipoPvtoRua;
		}




		/**
		 * @param tipoPvtoRua O tipoPvtoRua a ser setado.
		 */
		public void setTipoPvtoRua(String tipoPvtoRua) {
			this.tipoPvtoRua = tipoPvtoRua;
		}




		/**
		 * @return Retorna o campo tipoPvtoRuaRetorno.
		 */
		public String getTipoPvtoRuaRetorno() {
			return tipoPvtoRuaRetorno;
		}




		/**
		 * @param tipoPvtoRuaRetorno O tipoPvtoRuaRetorno a ser setado.
		 */
		public void setTipoPvtoRuaRetorno(String tipoPvtoRuaRetorno) {
			this.tipoPvtoRuaRetorno = tipoPvtoRuaRetorno;
		}



		/**
		 * @return Retorna o campo arrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao.
		 */
		public JRBeanCollectionDataSource getArrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao() {
			return arrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao;
		}



		/**
		 * @param arrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao O arrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao a ser setado.
		 */
		public void setArrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao(
				JRBeanCollectionDataSource arrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao) {
			this.arrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao = arrayJRSubRelatorioRelacaoServicosAcompanhamentoRepavimentacao;
		}



		/**
		 * @return Retorna o campo arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean.
		 */
		public ArrayList getArrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean() {
			return arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean;
		}



		/**
		 * @param arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean O arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean a ser setado.
		 */
		public void setArrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean(
				ArrayList arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean) {
			this.arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean = arrayRelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean;
		}

		public String getDataAceite() {
			return dataAceite;
		}

		public void setDataAceite(String dataAceite) {
			this.dataAceite = dataAceite;
		}

		public String getDataRetorno() {
			return dataRetorno;
		}

		public void setDataRetorno(String dataRetorno) {
			this.dataRetorno = dataRetorno;
		}

		public String getIndicadorAceite() {
			return indicadorAceite;
		}

		public void setIndicadorAceite(String indicadorAceite) {
			this.indicadorAceite = indicadorAceite;
		}



		/**
		 * @return Returns the indicadorAceiteComPercentualConvergencia.
		 */
		public String getIndicadorAceiteComPercentualConvergencia() {
			return indicadorAceiteComPercentualConvergencia;
		}



		/**
		 * @param indicadorAceiteComPercentualConvergencia The indicadorAceiteComPercentualConvergencia to set.
		 */
		public void setIndicadorAceiteComPercentualConvergencia(
				String indicadorAceiteComPercentualConvergencia) {
			this.indicadorAceiteComPercentualConvergencia = indicadorAceiteComPercentualConvergencia;
		}



		/**
		 * @return Returns the motivo.
		 */
		public String getMotivo() {
			return motivo;
		}



		/**
		 * @param motivo The motivo to set.
		 */
		public void setMotivo(String motivo) {
			this.motivo = motivo;
		}

		public JRBeanCollectionDataSource getArrayJRSubValoresPorTipoPavimento() {
			return arrayJRSubValoresPorTipoPavimento;
		}

		public void setArrayJRSubValoresPorTipoPavimento(
				JRBeanCollectionDataSource arrayJRSubValoresPorTipoPavimento) {
			this.arrayJRSubValoresPorTipoPavimento = arrayJRSubValoresPorTipoPavimento;
		}

		public String getDataRejeicao() {
			return dataRejeicao;
		}

		public void setDataRejeicao(String dataRejeicao) {
			this.dataRejeicao = dataRejeicao;
		}

		public String getSituacaoRetorno() {
			return situacaoRetorno;
		}

		public void setSituacaoRetorno(String situacaoRetorno) {
			this.situacaoRetorno = situacaoRetorno;
		}

		public Boolean getIndicadorPrimeiraOcorrenciaConcluida() {
			return indicadorPrimeiraOcorrenciaConcluida;
		}

		public void setIndicadorPrimeiraOcorrenciaConcluida(
				Boolean indicadorPrimeiraOcorrenciaConcluida) {
			this.indicadorPrimeiraOcorrenciaConcluida = indicadorPrimeiraOcorrenciaConcluida;
		}

		public Boolean getIndicadorPrimeiraOcorrenciaPendente() {
			return indicadorPrimeiraOcorrenciaPendente;
		}

		public void setIndicadorPrimeiraOcorrenciaPendente(
				Boolean indicadorPrimeiraOcorrenciaPendente) {
			this.indicadorPrimeiraOcorrenciaPendente = indicadorPrimeiraOcorrenciaPendente;
		}

		public Boolean getIndicadorPrimeiraOcorrenciaRejeitada() {
			return indicadorPrimeiraOcorrenciaRejeitada;
		}

		public void setIndicadorPrimeiraOcorrenciaRejeitada(
				Boolean indicadorPrimeiraOcorrenciaRejeitada) {
			this.indicadorPrimeiraOcorrenciaRejeitada = indicadorPrimeiraOcorrenciaRejeitada;
		}

		public String getMotivoRejeicao() {
			return motivoRejeicao;
		}

		public void setMotivoRejeicao(String motivoRejeicao) {
			this.motivoRejeicao = motivoRejeicao;
		}

		public Boolean getIndicadorUltimaOcorrenciaRejeitada() {
			return indicadorUltimaOcorrenciaRejeitada;
		}

		public void setIndicadorUltimaOcorrenciaRejeitada(
				Boolean indicadorUltimaOcorrenciaRejeitada) {
			this.indicadorUltimaOcorrenciaRejeitada = indicadorUltimaOcorrenciaRejeitada;
		}

		public String getObservacao() {
			return observacao;
		}

		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}

}
