package gcom.relatorio.gerencial.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gerencial.faturamento.bean.FiltrarResumoDadosCasHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioResumoDadosCas extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	//Constantes
    @SuppressWarnings("unused")//adicionado para não aparecer nenhum alert
	private static final Integer TOTALIZACAO_ESTADO = 1;
    private static final Integer TOTALIZACAO_ESTADO_POR_GERENCIA_REGIONAL = 2;
    private static final Integer TOTALIZACAO_ESTADO_POR_UNIDADE_NEGOCIO = 3;
    private static final Integer TOTALIZACAO_ESTADO_POR_MUNICIPIO = 4;
    
    @SuppressWarnings("unused")//adicionado para não aparecer nenhum alert
	private static final Integer TOTALIZACAO_GERENCIA_REGIONAL = 6;
    private static final Integer TOTALIZACAO_UNIDADE_NEGOCIO = 10;
    private static final Integer TOTALIZACAO_LOCALIDADE = 17;
	private static final Integer TOTALIZACAO_MUNICIPIO = 20;
	
	
	public RelatorioResumoDadosCas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_DADOS_CAS);
	}

	@Deprecated
	public RelatorioResumoDadosCas() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarResumoDadosCasHelper filtro = 
			(FiltrarResumoDadosCasHelper) getParametro("filtrarResumoDadosCasHelper");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();


		Collection<Object> colecaoResumoLigacaoEconomia = 
			fachada.pesquisaResumoLigacaoEconomiaResumoDadosCas(filtro);
		
		Collection<Object> colecaoResumoConsumoAgua =
			fachada.pesquisaResumoConsumoAguaResumoDadosCas(filtro);
		
		Collection<Object> colecaoResumoColetaEsgoto =
			fachada.pesquisaResumoColetaEsgotoResumoDadosCas(filtro);
		
		Collection<Object> colecaoResumoArrecadacao =
			fachada.pesquisaResumoArrecadacaoResumoDadosCas(filtro);
		
		Collection<Object> colecaoResumoFaturamento =
			fachada.pesquisaResumoFaturamentoResumoDadosCas(filtro);
		
		Collection<Object> colecaoResumoPendencia =
		    fachada.pesquisaResumoPendenciaResumoDadosCas(filtro);
		
		Collection<Object> colecaoResumoFaturamentoComercial =
			fachada.pesquisaResumoFaturamentoResumoDadosCasComercial(filtro);
		
		Collection<Object> colecaoResumoInstalacaoHidrometro =
			fachada.pesquisaResumoInstalacaoHidrometroResumoDadosCas(filtro);
		
		
		RelatorioResumoDadosCasBean relatorioBean = null;
		
		//cria variaveis para gerar o total por relatorio.
		
		Long sumTotalLigacoesAtivasAgua = new Long(0);		
		Long sumTotalLigacoesInativasAgua = new Long(0);		
		Long sumTotalLigacoesAtvInatAgua = new Long(0);		
		Long sumTotalLigacoesCortadasAgua = new Long(0);		
		Long sumTotalLigacoesAnaliseAgua = new Long(0);		
		Long sumTotalLigacoesRamaisLigAgua = new Long(0);		
		Long sumTotalLigacoesMedidasAgua = new Long(0);		
		Long sumTotalLigacoesNaoMedidasAgua = new Long(0);
		
		Long sumTotalLigacoesAtivasEsgoto = new Long(0);		
		Long sumTotalLigacoesInativasEsgoto = new Long(0);		
		Long sumTotalLigacoesAtivInatEsgoto = new Long(0);		
		Long sumTotalLigacoesTamponatoEsgoto = new Long(0);		
		Long sumTotalLigacoesAnaliseEsgoto = new Long(0);		
		Long sumTotalLigacoesRamaisLigEsgoto = new Long(0);		
		Long sumTotalLigacoesMedidasEsgoto = new Long(0);		
		Long sumTotalLigacoesNaoMedidasEsgoto = new Long(0);
		
		Long sumTotalLigacoesAguaSuprimidas = new Long(0);
		
		Long sumNumeroEconomiasAgua = new Long(0);		
		Long sumNumeroEconomiasEsgoto = new Long(0);
				
		BigDecimal sumTotalArrecadacaoAtual = new BigDecimal(0);		
		BigDecimal sumTotalArrecadacaoAnterior = new BigDecimal(0);		
				
		Long sumContasEmitidas = new Long(0);
		BigDecimal sumTotalVolumeFaturadoAgua = new BigDecimal(0);		
		BigDecimal sumTotalVolumeFaturadoEsgoto = new BigDecimal(0);
		BigDecimal sumTotalValorFaturadoAgua = new BigDecimal(0);
		BigDecimal sumTotalValorFaturadoEsgoto = new BigDecimal(0);
		BigDecimal sumValorPendencia = new BigDecimal(0);
		
		Long sumTotalLigacoesFaturadasAgua = new Long(0);
		Long sumTotalLigacoesFaturadasEsgoto = new Long(0);
		
		Long sumTotalHidrometrosInstaladosAgua = new Long(0);					
		Long sumTotalHidrometrosInstaladosEsgoto = new Long(0);
		Long sumTotalHidrometrosSubstituidosAgua = new Long(0);
		Long sumTotalHidrometrosSubstituidosEsgoto = new Long(0);
		
		BigDecimal sumTotalFaturamentoLiquidoAtual = new BigDecimal(0);
		BigDecimal sumTotalFaturamentoLiquidoAnterior = new BigDecimal(0);
				
 		// variaveis para converter valor long para BigDecimal		
		BigDecimal totalLigacoesAtivasAgua = new BigDecimal(0);
		BigDecimal totalLigacoesMedidasAgua = new BigDecimal(0);		
		
						

		if (colecaoResumoLigacaoEconomia != null && !colecaoResumoLigacaoEconomia.isEmpty()) {
			

		    Iterator iteratorRELE = colecaoResumoLigacaoEconomia.iterator();
		    Iterator iteratorRECA = colecaoResumoConsumoAgua.iterator();
		    Iterator iteratorRECE = colecaoResumoColetaEsgoto.iterator();
		    Iterator iteratorREAR = colecaoResumoArrecadacao.iterator();
		    Iterator iteratorREFA = colecaoResumoFaturamento.iterator();
		    Iterator iteratorRPEN = colecaoResumoPendencia.iterator();
		    Iterator iteratorREFAC = colecaoResumoFaturamentoComercial.iterator();
		    Iterator iteratorREIH = colecaoResumoInstalacaoHidrometro.iterator();

			while (iteratorRELE.hasNext() || iteratorRECA.hasNext() 
					|| iteratorRECE.hasNext() || iteratorREAR.hasNext()
					|| iteratorREFA.hasNext() || iteratorRPEN.hasNext()
					|| iteratorREFAC.hasNext() || iteratorREIH.hasNext()) {
				relatorioBean = new RelatorioResumoDadosCasBean();		
								
				Object[] objRELE = (Object[]) iteratorRELE.next();
				
				
				relatorioBean.setTotalLigacoesAtivasAgua((Long)objRELE[0]);				
				relatorioBean.setTotalLigacoesInativasAgua((Long)objRELE[1]);				
				relatorioBean.setTotalLigacoesAtvInatAgua((Long)objRELE[2]);				
				relatorioBean.setTotalLigacoesCortadasAgua((Long)objRELE[3]);				
				relatorioBean.setTotalLigacoesAnaliseAgua((Long)objRELE[4]);				
				relatorioBean.setTotalLigacoesRamaisLigAgua((Long)objRELE[5]);				
				relatorioBean.setTotalLigacoesMedidasAgua((Long)objRELE[6]);				
				relatorioBean.setTotalLigacoesNaoMedidasAgua((Long)objRELE[7]);
				
				relatorioBean.setTotalLigacoesAtivasEsgoto((Long)objRELE[8]);				
				relatorioBean.setTotalLigacoesInativasEsgoto((Long)objRELE[9]);				
				relatorioBean.setTotalLigacoesAtivInatEsgoto((Long)objRELE[10]);				
				relatorioBean.setTotalLigacoesTamponatoEsgoto((Long)objRELE[11]);				
				relatorioBean.setTotalLigacoesAnaliseEsgoto((Long)objRELE[12]);				
				relatorioBean.setTotalLigacoesRamaisLigEsgoto((Long)objRELE[13]);				
				relatorioBean.setTotalLigacoesMedidasEsgoto((Long)objRELE[14]);				
				relatorioBean.setTotalLigacoesNaoMedidasEsgoto((Long)objRELE[15]);
				
				relatorioBean.setTotalLigacoesAguaSuprimidas((Long)objRELE[16]);
				
				//converter long para BigDecimal e dividir "ligacoes medidas" por "ligacoes ativas" 				
				totalLigacoesAtivasAgua = new BigDecimal( (Long) objRELE[0]);
				totalLigacoesMedidasAgua = new BigDecimal( (Long) objRELE[6]);
				
				if(totalLigacoesAtivasAgua.compareTo(new BigDecimal("0")) != 0 
						&& totalLigacoesMedidasAgua.compareTo(new BigDecimal("0")) != 0){
				    relatorioBean.setIndiceHidrometracaoAgua(Util.dividirArredondando(totalLigacoesMedidasAgua, totalLigacoesAtivasAgua));				
			    }else{
			    	relatorioBean.setIndiceHidrometracaoAgua(new BigDecimal(0));					
			    }
				
				relatorioBean.setGerenciaRegionalID((String)objRELE[17]);
				relatorioBean.setGerenciaRegional((String)objRELE[18]);
				relatorioBean.setUnidadeNegocioID((String)objRELE[19]);
				relatorioBean.setUnidadeNegocio((String)objRELE[20]);
				relatorioBean.setLocalidadeID((String)objRELE[21]);
				relatorioBean.setLocalidade((String)objRELE[22]);				
				relatorioBean.setMunicipioId((String)objRELE[23]);
				relatorioBean.setMunicipio((String)objRELE[24]);
				
				Object[] objRECA = null;				
				if(colecaoResumoConsumoAgua != null && colecaoResumoConsumoAgua.size() != 0 && iteratorRECA.hasNext()){
					objRECA = (Object[]) iteratorRECA.next();					
					relatorioBean.setNumeroEconomiasAgua((Long)objRECA[0]);
					relatorioBean.setTotalVolumeFaturadoAgua((BigDecimal)objRECA[1]);
					relatorioBean.setTotalLigacoesFaturadasAgua((Long)objRECA[2]);
				}else{
					relatorioBean.setNumeroEconomiasAgua(new Long(0));
					relatorioBean.setTotalVolumeFaturadoAgua(new BigDecimal(0));
					relatorioBean.setTotalLigacoesFaturadasAgua(new Long(0));
				}
				
				Object[] objRECE = null;
				if(colecaoResumoColetaEsgoto != null && colecaoResumoColetaEsgoto.size() != 0 && iteratorRECE.hasNext()){
					objRECE = (Object[]) iteratorRECE.next();
					relatorioBean.setNumeroEconomiasEsgoto((Long)objRECE[0]);
					relatorioBean.setTotalVolumeFaturadoEsgoto((BigDecimal)objRECE[1]);
					relatorioBean.setTotalLigacoesFaturadasEsgoto((Long)objRECE[2]);
				}else{
					relatorioBean.setNumeroEconomiasEsgoto(new Long(0));
					relatorioBean.setTotalVolumeFaturadoEsgoto(new BigDecimal(0));
					relatorioBean.setTotalLigacoesFaturadasEsgoto(new Long(0));
				}
				
				Object[] objREAR = null;				
				if(colecaoResumoArrecadacao != null && colecaoResumoArrecadacao.size() != 0 && iteratorREAR.hasNext()){
					objREAR = (Object[]) iteratorREAR.next();
					relatorioBean.setTotalArrecadacaoAtual((BigDecimal)objREAR[0]);
					relatorioBean.setTotalArrecadacaoAnterior((BigDecimal)objREAR[1]);
				}else{
					relatorioBean.setTotalArrecadacaoAtual(new BigDecimal(0));
					relatorioBean.setTotalArrecadacaoAnterior(new BigDecimal(0));
				}
								
				Object[] objREFA = null;				
				if(colecaoResumoFaturamento != null && colecaoResumoFaturamento.size() != 0 && iteratorREFA.hasNext()){
					objREFA = (Object[]) iteratorREFA.next();
					relatorioBean.setContasEmitidas((Long)objREFA[0]);
					relatorioBean.setTotalValorFaturadoAgua((BigDecimal)objREFA[3]);
					relatorioBean.setTotalValorFaturadoEsgoto((BigDecimal)objREFA[4]);
				}else{
					relatorioBean.setContasEmitidas(new Long(0));					
					relatorioBean.setTotalValorFaturadoAgua(new BigDecimal(0));
					relatorioBean.setTotalValorFaturadoEsgoto(new BigDecimal(0));
				}
								
				
				Object[] objRPEN = null;
				if(colecaoResumoPendencia != null && colecaoResumoPendencia.size() != 0 && iteratorRPEN.hasNext()){
					objRPEN = (Object[]) iteratorRPEN.next();
					
					if((BigDecimal)objRPEN[0] == null){
						relatorioBean.setValorPendencia(new BigDecimal(0));
					}else{
						relatorioBean.setValorPendencia((BigDecimal)objRPEN[0]);
					}
				}else{
					relatorioBean.setValorPendencia(new BigDecimal(0));
				}
				
				
				Object[] objREFAC = null;
				if(colecaoResumoFaturamentoComercial != null && colecaoResumoFaturamentoComercial.size() != 0 && iteratorREFAC.hasNext()){
					objREFAC = (Object[]) iteratorREFAC.next();
					relatorioBean.setTotalFaturamentoLiquidoAtual((BigDecimal)objREFAC[0]);					
					relatorioBean.setTotalFaturamentoLiquidoAnterior((BigDecimal)objREFAC[1]);
					relatorioBean.setTotalValorFaturadoAgua((BigDecimal)objREFAC[2]);
					relatorioBean.setTotalValorFaturadoEsgoto((BigDecimal)objREFAC[3]);
				}else{
					relatorioBean.setTotalFaturamentoLiquidoAtual(new BigDecimal(0));					
					relatorioBean.setTotalFaturamentoLiquidoAnterior(new BigDecimal(0));
					relatorioBean.setTotalValorFaturadoAgua(new BigDecimal(0));
					relatorioBean.setTotalValorFaturadoEsgoto(new BigDecimal(0));
				}
				
				Object[] objREIH = null;
				if(colecaoResumoInstalacaoHidrometro != null && colecaoResumoInstalacaoHidrometro.size() != 0 && iteratorREIH.hasNext()){
					objREIH = (Object[]) iteratorREIH.next();
					relatorioBean.setTotalHidrometrosInstaladosAgua((Long)objREIH[0]);					
					relatorioBean.setTotalHidrometrosInstaladosEsgoto((Long)objREIH[1]);
					relatorioBean.setTotalHidrometrosSubstituidosAgua((Long)objREIH[2]);
					relatorioBean.setTotalHidrometrosSubstituidosEsgoto((Long)objREIH[3]);
				}else{
					relatorioBean.setTotalHidrometrosInstaladosAgua(new Long(0));					
					relatorioBean.setTotalHidrometrosInstaladosEsgoto(new Long(0));
					relatorioBean.setTotalHidrometrosSubstituidosAgua(new Long(0));
					relatorioBean.setTotalHidrometrosSubstituidosEsgoto(new Long(0));
				}

				
				if(relatorioBean.getTotalArrecadacaoAtual().compareTo(new BigDecimal("0")) != 0 
						&& relatorioBean.getTotalFaturamentoLiquidoAnterior().compareTo(new BigDecimal("0")) != 0){
				    relatorioBean.setIndiceArrecadacao(Util.dividirArredondando(relatorioBean.getTotalArrecadacaoAtual(), relatorioBean.getTotalFaturamentoLiquidoAnterior()));				
			    }else{
			    	relatorioBean.setIndiceArrecadacao(new BigDecimal(0));					
			    }
				
				
				if(relatorioBean.getTotalFaturamentoLiquidoAtual().compareTo(new BigDecimal("0")) != 0 
						&& relatorioBean.getValorPendencia().compareTo(new BigDecimal("0")) != 0){
				    relatorioBean.setIndiceContasAReceber(Util.dividirArredondando(relatorioBean.getValorPendencia(), relatorioBean.getTotalFaturamentoLiquidoAtual()));				
			    }else{
			    	relatorioBean.setIndiceContasAReceber(new BigDecimal(0));					
			    }
				
				//Se o Total da Arrecadacao atual e o Total do Faturamento Liquido Atual forem = 0,
				//nao adiciona item (seja Gerencia/ Unidade ou Localidade). 
				if(relatorioBean.getTotalArrecadacaoAtual().compareTo(new BigDecimal(0))!=0 
					|| relatorioBean.getTotalFaturamentoLiquidoAtual().compareTo(new BigDecimal(0))!=0){					
				
					relatorioBeans.add(relatorioBean);
					
					//caso a opcao de totalizacao for estado por Gerencia Regional/Unidade de Negocio, adiciona o total do estado,
					//soma os valores
					if (filtro.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_POR_GERENCIA_REGIONAL || filtro.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_POR_UNIDADE_NEGOCIO){
						
						
						sumTotalLigacoesAtivasAgua += (Long)objRELE[0];
						sumTotalLigacoesInativasAgua += (Long)objRELE[1];
						sumTotalLigacoesAtvInatAgua += (Long)objRELE[2];
						sumTotalLigacoesCortadasAgua += (Long)objRELE[3];
						sumTotalLigacoesAnaliseAgua += (Long)objRELE[4];
						sumTotalLigacoesRamaisLigAgua += (Long)objRELE[5];
						sumTotalLigacoesMedidasAgua += (Long)objRELE[6];					
						sumTotalLigacoesNaoMedidasAgua += (Long)objRELE[7];				
					
						sumTotalLigacoesAtivasEsgoto += (Long)objRELE[8];
						sumTotalLigacoesInativasEsgoto += (Long)objRELE[9];
						sumTotalLigacoesAtivInatEsgoto += (Long)objRELE[10];
						sumTotalLigacoesTamponatoEsgoto += (Long)objRELE[11];
						sumTotalLigacoesAnaliseEsgoto += (Long)objRELE[12];
						sumTotalLigacoesRamaisLigEsgoto += (Long)objRELE[13];
						sumTotalLigacoesMedidasEsgoto += (Long)objRELE[14];
						sumTotalLigacoesNaoMedidasEsgoto += (Long)objRELE[15];
						
						sumTotalLigacoesAguaSuprimidas += (Long)objRELE[16];
						
						if(objRECA != null){
							sumNumeroEconomiasAgua += (Long)objRECA[0];
							sumTotalVolumeFaturadoAgua = sumTotalVolumeFaturadoAgua.add((BigDecimal)objRECA[1]);
							sumTotalLigacoesFaturadasAgua += (Long)objRECA[2];
						}
						
						if(objRECE != null){
							sumNumeroEconomiasEsgoto += (Long)objRECE[0];
							sumTotalVolumeFaturadoEsgoto = sumTotalVolumeFaturadoEsgoto.add((BigDecimal)objRECE[1]);
							sumTotalLigacoesFaturadasEsgoto += (Long)objRECE[2];											
						}
						
						if(objREAR != null){
							sumTotalArrecadacaoAtual = sumTotalArrecadacaoAtual.add((BigDecimal)objREAR[0]);
							sumTotalArrecadacaoAnterior = sumTotalArrecadacaoAnterior.add((BigDecimal)objREAR[1]);
						}
						
						if(objREFA != null){
							sumContasEmitidas += (Long)objREFA[0];							
						}
						
						if(objRPEN != null){
							sumValorPendencia = sumValorPendencia.add((BigDecimal)objRPEN[0]);
						}
						
						
						if(objREFAC != null){
							sumTotalFaturamentoLiquidoAtual = sumTotalFaturamentoLiquidoAtual.add((BigDecimal)objREFAC[0]);
							sumTotalFaturamentoLiquidoAnterior = sumTotalFaturamentoLiquidoAnterior.add((BigDecimal)objREFAC[1]);
							sumTotalValorFaturadoAgua = sumTotalValorFaturadoAgua.add((BigDecimal)objREFAC[2]);
							sumTotalValorFaturadoEsgoto = sumTotalValorFaturadoEsgoto.add((BigDecimal)objREFAC[3]);
						}
						
						if(objREIH != null){
							sumTotalHidrometrosInstaladosAgua += (Long)objREIH[0];					
							sumTotalHidrometrosInstaladosEsgoto += (Long)objREIH[1];
							sumTotalHidrometrosSubstituidosAgua += (Long)objREIH[2];
							sumTotalHidrometrosSubstituidosEsgoto += (Long)objREIH[3];
						}
						
					}		
				}
			}
			
			//caso a opcao de totalizacao for estado por Gerencia Regional/Unidade de Negocio, 
			//adiciona o total do estado
			if (filtro.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_POR_GERENCIA_REGIONAL || filtro.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_POR_UNIDADE_NEGOCIO
					|| filtro.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_POR_MUNICIPIO){
				
				relatorioBean = new RelatorioResumoDadosCasBean();
			
				
				relatorioBean.setTotalLigacoesAtivasAgua(Long.valueOf(sumTotalLigacoesAtivasAgua));
				relatorioBean.setTotalLigacoesInativasAgua(Long.valueOf(sumTotalLigacoesInativasAgua));
				relatorioBean.setTotalLigacoesAtvInatAgua(Long.valueOf(sumTotalLigacoesAtvInatAgua));
				relatorioBean.setTotalLigacoesCortadasAgua(Long.valueOf(sumTotalLigacoesCortadasAgua));
				relatorioBean.setTotalLigacoesAnaliseAgua(Long.valueOf(sumTotalLigacoesAnaliseAgua));							
				relatorioBean.setTotalLigacoesRamaisLigAgua(Long.valueOf(sumTotalLigacoesRamaisLigAgua));
				relatorioBean.setTotalLigacoesMedidasAgua(Long.valueOf(sumTotalLigacoesMedidasAgua));
				relatorioBean.setTotalLigacoesNaoMedidasAgua(Long.valueOf(sumTotalLigacoesNaoMedidasAgua));
				
				relatorioBean.setTotalLigacoesAtivasEsgoto(Long.valueOf(sumTotalLigacoesAtivasEsgoto));
				relatorioBean.setTotalLigacoesInativasEsgoto(Long.valueOf(sumTotalLigacoesInativasEsgoto));
				relatorioBean.setTotalLigacoesAtivInatEsgoto(Long.valueOf(sumTotalLigacoesAtivInatEsgoto));
				relatorioBean.setTotalLigacoesTamponatoEsgoto(Long.valueOf(sumTotalLigacoesTamponatoEsgoto));
				relatorioBean.setTotalLigacoesAnaliseEsgoto(Long.valueOf(sumTotalLigacoesAnaliseEsgoto));
				relatorioBean.setTotalLigacoesRamaisLigEsgoto(Long.valueOf(sumTotalLigacoesRamaisLigEsgoto));
				relatorioBean.setTotalLigacoesMedidasEsgoto(Long.valueOf(sumTotalLigacoesMedidasEsgoto));
				relatorioBean.setTotalLigacoesNaoMedidasEsgoto(Long.valueOf(sumTotalLigacoesNaoMedidasEsgoto));
				
				relatorioBean.setTotalLigacoesAguaSuprimidas(sumTotalLigacoesAguaSuprimidas);
				
				
				relatorioBean.setNumeroEconomiasAgua(Long.valueOf(sumNumeroEconomiasAgua));
				relatorioBean.setNumeroEconomiasEsgoto(Long.valueOf(sumNumeroEconomiasEsgoto));
				relatorioBean.setTotalArrecadacaoAtual(sumTotalArrecadacaoAtual);
				relatorioBean.setTotalArrecadacaoAnterior(sumTotalArrecadacaoAnterior);
				
				relatorioBean.setContasEmitidas(Long.valueOf(sumContasEmitidas));
				relatorioBean.setTotalVolumeFaturadoAgua(sumTotalVolumeFaturadoAgua);
				relatorioBean.setTotalVolumeFaturadoEsgoto(sumTotalVolumeFaturadoEsgoto);
				
				relatorioBean.setTotalValorFaturadoAgua(sumTotalValorFaturadoAgua);
				relatorioBean.setTotalValorFaturadoEsgoto(sumTotalValorFaturadoEsgoto);
				
				relatorioBean.setTotalLigacoesFaturadasAgua(Long.valueOf(sumTotalLigacoesFaturadasAgua));
				relatorioBean.setTotalLigacoesFaturadasEsgoto(Long.valueOf(sumTotalLigacoesFaturadasEsgoto));
				
				relatorioBean.setTotalHidrometrosInstaladosAgua(Long.valueOf(sumTotalHidrometrosInstaladosAgua));				
				relatorioBean.setTotalHidrometrosInstaladosEsgoto(Long.valueOf(sumTotalHidrometrosInstaladosEsgoto));
				relatorioBean.setTotalHidrometrosSubstituidosAgua(Long.valueOf(sumTotalHidrometrosSubstituidosAgua));
				relatorioBean.setTotalHidrometrosSubstituidosEsgoto(Long.valueOf(sumTotalHidrometrosSubstituidosEsgoto));
				
				
				//converter long para BigDecimal e dividir "ligacoes medidas" por "ligacoes ativas" 				
				totalLigacoesAtivasAgua = new BigDecimal(sumTotalLigacoesAtivasAgua);
				totalLigacoesMedidasAgua = new BigDecimal(sumTotalLigacoesMedidasAgua);
				
				if(totalLigacoesAtivasAgua.compareTo(new BigDecimal("0")) != 0 
						&& totalLigacoesMedidasAgua.compareTo(new BigDecimal("0")) != 0){
					relatorioBean.setIndiceHidrometracaoAgua(Util.dividirArredondando(totalLigacoesMedidasAgua, totalLigacoesAtivasAgua));					
				}else{
					relatorioBean.setIndiceHidrometracaoAgua(new BigDecimal(0));
				}
				
				relatorioBean.setValorPendencia(sumValorPendencia);
				
				relatorioBean.setTotalFaturamentoLiquidoAtual(sumTotalFaturamentoLiquidoAtual);
				relatorioBean.setTotalFaturamentoLiquidoAnterior(sumTotalFaturamentoLiquidoAnterior);
				
								
				if(sumTotalArrecadacaoAtual.compareTo(new BigDecimal("0")) != 0 
						&& sumTotalFaturamentoLiquidoAnterior.compareTo(new BigDecimal("0")) != 0){					                                                                            
				    relatorioBean.setIndiceArrecadacao(Util.dividirArredondando(sumTotalArrecadacaoAtual, sumTotalFaturamentoLiquidoAnterior));				
			    }else{
			    	relatorioBean.setIndiceArrecadacao(new BigDecimal(0));					
			    }				
				
				if(sumTotalFaturamentoLiquidoAtual.compareTo(new BigDecimal("0")) != 0 
						&& sumValorPendencia.compareTo(new BigDecimal("0")) != 0){
				    relatorioBean.setIndiceContasAReceber(Util.dividirArredondando(sumValorPendencia, sumTotalFaturamentoLiquidoAtual));				
			    }else{
			    	relatorioBean.setIndiceContasAReceber(new BigDecimal(0));					
			    }
				
				//colocar zero para informar que esse bean é o total do estado
				relatorioBean.setGerenciaRegionalID((String)"0");
				relatorioBean.setUnidadeNegocioID((String)"0");
				relatorioBean.setLocalidadeID((String)"0");				
				
				relatorioBeans.add(relatorioBean);
			}
			
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMesReferencia", Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferencia()));
		parametros.put("anoMesReferenciaAnterior", Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferenciaAnterior()));
		parametros.put("codOpcaoTotalizacao", filtro.getOpcaoTotalizacao());
		parametros.put("tipoFormatoRelatorio", "R1017");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_DADOS_CAS,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RESUMO_DADOS_CAS,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		FiltrarResumoDadosCasHelper filtro = 
			(FiltrarResumoDadosCasHelper) getParametro("filtrarResumoDadosCasHelper");

		Fachada fachada = Fachada.getInstancia();

		int retorno = 0;	
			
		retorno = (Integer) fachada.pesquisaResumoLigacaoEconomiaResumoDadosCas(filtro).size();
				
        if(retorno == 0 ){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
			
		// se a opção de totalização for Localidade ou Unidade de Negócio ou Estado ou 1 Gerência
		// não ir pra batch	
		}else if(filtro.getOpcaoTotalizacao() == TOTALIZACAO_LOCALIDADE || filtro.getOpcaoTotalizacao() == TOTALIZACAO_UNIDADE_NEGOCIO
				|| filtro.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO || filtro.getOpcaoTotalizacao() == TOTALIZACAO_GERENCIA_REGIONAL
				|| filtro.getOpcaoTotalizacao() == TOTALIZACAO_MUNICIPIO){
				
			retorno = 0;		
		}
		    
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoDadosCas", this);

	}

}
