package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoSomatorioPorTipoPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Relatorio de Relacao das Ordem de Repavimentacao em Processo de Aceite.
 * 
 * @author Hugo Leonardo
 * @created 20/05/2010 
 */

public class RelatorioRelacaoOrdemRepavimentacaoProcessoAceite extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativadorExclusaoMotivo object
	 */
	public RelatorioRelacaoOrdemRepavimentacaoProcessoAceite(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELACAO_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE);
	}

	@Deprecated
	public RelatorioRelacaoOrdemRepavimentacaoProcessoAceite() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param NegativadorExclusaoMotivo Parametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os parâmetros que serão utilizados no relatório
		OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoHelperParametros = (OrdemRepavimentacaoProcessoAceiteHelper) getParametro("osPavimentoAceiteHelper");
		
		Collection<OSPavimentoRetornoHelper> collOrdemServicoPavimentoHelper = null;

		String escolhaRelatorio = (String) getParametro("escolhaRelatorio");
		String periodoAceiteServicoInicial = (String) getParametro("periodoAceiteServicoInicial");
		String periodoAceiteServicoFinal = (String) getParametro("periodoAceiteServicoFinal");
		
		String retornoServicoInicial = (String) getParametro("retornoServicoInicial");
		String retornoServicoFinal = (String) getParametro("retornoServicoFinal");
		
		String situacaoAceiteDescricao = (String) getParametro("situacaoAceiteDescricao");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String descricaoUnidadeOrganizacional = (String) getParametro("descricaoUnidadeOrganizacional");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();
		
		collOrdemServicoPavimentoHelper = fachada.pesquisarOrdemRepavimentacaoProcessoAceite(oSPavimentoHelperParametros, null);

		RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean relatorioBean = null;
		
		if (collOrdemServicoPavimentoHelper != null && !collOrdemServicoPavimentoHelper.isEmpty()) {
			// totaliza metragem por tipo de pavimento
			
			// Variaveis totalização
			String descricaoPvtRua = "Total:";
			BigDecimal valorPvtRua = BigDecimal.ZERO;
			String descricaoPvtRuaRetorno = "Total:";
			BigDecimal valorPvtRuaRetorno = BigDecimal.ZERO;
			
			Collection<OSPavimentoSomatorioPorTipoPavimentoHelper> 
				colecaoSomatorioPorPavimento = new ArrayList<OSPavimentoSomatorioPorTipoPavimentoHelper>();
			
			Collection<OSPavimentoSomatorioPorTipoPavimentoHelper> 
			colecaoSomatorioPorPavimentoRua = new ArrayList<OSPavimentoSomatorioPorTipoPavimentoHelper>();
			
			Collection<OSPavimentoSomatorioPorTipoPavimentoHelper> 
			colecaoSomatorioPorPavimentoRetorno = new ArrayList<OSPavimentoSomatorioPorTipoPavimentoHelper>();
			/**
			 * Codigo comentado quebra o total por ocorrencia
			for (OSPavimentoRetornoHelper oSPavimentoRetornoHelper : collOrdemServicoPavimentoHelper) {
				
				OSPavimentoSomatorioPorTipoPavimentoHelper helper = 
					new OSPavimentoSomatorioPorTipoPavimentoHelper();
				
				// tipo pvto rua			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada()!= null) {
					
					helper.setDescricaoPvtRua(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada());
				}
				// Metr. (m²) indicada.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua()!= null) {
					
					helper.setValorPvtRua(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua());
				}
				//tipo pvto rua retorno.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada()!= null) {
					helper.setDescricaoPvtRuaRetorno(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada());
				}
				// Metr. (m²) indicada retorno.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno()!= null) {
					helper.setValorPvtRuaRetorno(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno());
				}
				if(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()!=null &&
						oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()
							.compareTo(ConstantesSistema.SIM)==0){
					if(colecaoSomatorioPorPavimento.contains(helper)){
						
						for (OSPavimentoSomatorioPorTipoPavimentoHelper 
								iteracao : colecaoSomatorioPorPavimento) {
							
							if(iteracao.equals(helper)){
								
								iteracao.setValorPvtRua(
										Util.somaBigDecimal(
												iteracao.getValorPvtRua(), helper.getValorPvtRua()));
								iteracao.setValorPvtRuaRetorno(
										Util.somaBigDecimal(
												iteracao.getValorPvtRuaRetorno(), helper.getValorPvtRuaRetorno()));
								
								valorPvtRua = Util.somaBigDecimal(valorPvtRua,helper.getValorPvtRua());
								valorPvtRuaRetorno = Util.somaBigDecimal(valorPvtRuaRetorno,helper.getValorPvtRuaRetorno());
								
							}
						}
						
					}else{					
						valorPvtRua = Util.somaBigDecimal(valorPvtRua,helper.getValorPvtRua());
						valorPvtRuaRetorno = Util.somaBigDecimal(valorPvtRuaRetorno,helper.getValorPvtRuaRetorno());
						colecaoSomatorioPorPavimento.add(helper);
					}
				}
				
			}
			//Cria objeto com totalização
			OSPavimentoSomatorioPorTipoPavimentoHelper helperTotalizacao = 
				new OSPavimentoSomatorioPorTipoPavimentoHelper(descricaoPvtRua,
						valorPvtRua,descricaoPvtRuaRetorno,valorPvtRuaRetorno);
			
			colecaoSomatorioPorPavimento.add(helperTotalizacao);
			**/
			////////////////////////
			
			Collection colecaoPavimentoRuaExiste = new ArrayList();
			Collection colecaoPavimentoRetornoExiste = new ArrayList();
			
			for (OSPavimentoRetornoHelper oSPavimentoRetornoHelper : collOrdemServicoPavimentoHelper) {
				
				OSPavimentoSomatorioPorTipoPavimentoHelper helper = 
					new OSPavimentoSomatorioPorTipoPavimentoHelper();
				
				// tipo pvto rua			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada()!= null) {
					
					helper.setDescricaoPvtRua(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada());
				}
				// Metr. (m²) indicada.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua()!= null) {
					
					helper.setValorPvtRua(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua());
				}
				
				if(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()!=null &&
						oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()
							.compareTo(ConstantesSistema.SIM)==0){
					if(colecaoPavimentoRuaExiste.contains(
							oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada())){
						
						for (OSPavimentoSomatorioPorTipoPavimentoHelper 
								iteracao : colecaoSomatorioPorPavimentoRua) {
							
							if(iteracao.getDescricaoPvtRua().equals(helper.getDescricaoPvtRua())){
								
								iteracao.setValorPvtRua(
										Util.somaBigDecimal(
												iteracao.getValorPvtRua(), helper.getValorPvtRua()));
								
								valorPvtRua = Util.somaBigDecimal(valorPvtRua,helper.getValorPvtRua());
								
							}
						}
						
					}else{					
						valorPvtRua = Util.somaBigDecimal(valorPvtRua,helper.getValorPvtRua());
						colecaoPavimentoRuaExiste.add(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada());
						colecaoSomatorioPorPavimentoRua.add(helper);
					}
					
				}
				
			}
			
			for (OSPavimentoRetornoHelper oSPavimentoRetornoHelper : collOrdemServicoPavimentoHelper) {
				
				OSPavimentoSomatorioPorTipoPavimentoHelper helper = 
					new OSPavimentoSomatorioPorTipoPavimentoHelper();
				
//				tipo pvto rua retorno.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada()!= null) {
					helper.setDescricaoPvtRuaRetorno(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada());
				}
				// Metr. (m²) indicada retorno.			
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno()!= null) {
					helper.setValorPvtRuaRetorno(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno());
				}
				
				if(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()!=null &&
						oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite()
							.compareTo(ConstantesSistema.SIM)==0){
					if(colecaoPavimentoRetornoExiste.contains(
							oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada())){
						
						for (OSPavimentoSomatorioPorTipoPavimentoHelper 
								iteracao : colecaoSomatorioPorPavimentoRetorno) {
							
							if(iteracao.getDescricaoPvtRuaRetorno().equals(helper.getDescricaoPvtRuaRetorno())){
								
								iteracao.setValorPvtRuaRetorno(
										Util.somaBigDecimal(
												iteracao.getValorPvtRuaRetorno(), helper.getValorPvtRuaRetorno()));
								
								valorPvtRuaRetorno = Util.somaBigDecimal(valorPvtRuaRetorno,helper.getValorPvtRuaRetorno());
								
							}
						}
						
					}else{					
						valorPvtRuaRetorno = Util.somaBigDecimal(valorPvtRuaRetorno,helper.getValorPvtRuaRetorno());
						colecaoPavimentoRetornoExiste.add(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada());
						colecaoSomatorioPorPavimentoRetorno.add(helper);
					}
					
				}
				
			}
			
			
			int size = 0;
			if (colecaoSomatorioPorPavimentoRua.size() > colecaoSomatorioPorPavimentoRetorno.size()){
				size = colecaoSomatorioPorPavimentoRua.size();
			} else {
				size = colecaoSomatorioPorPavimentoRetorno.size();
			}
			
			Iterator iColecaoSomatorioPorPavimentoRua = colecaoSomatorioPorPavimentoRua.iterator();
			Iterator iColecaoSomatorioPorPavimentoRetorno = colecaoSomatorioPorPavimentoRetorno.iterator();
			
			for (int i = 0; size > i; i++){
				
				OSPavimentoSomatorioPorTipoPavimentoHelper pavRua = null; 
				OSPavimentoSomatorioPorTipoPavimentoHelper pavRetorno = null;
				
				if (iColecaoSomatorioPorPavimentoRua.hasNext()){
					pavRua = (OSPavimentoSomatorioPorTipoPavimentoHelper) iColecaoSomatorioPorPavimentoRua.next();
				}
				
				if (iColecaoSomatorioPorPavimentoRetorno.hasNext()){
					pavRetorno = (OSPavimentoSomatorioPorTipoPavimentoHelper) iColecaoSomatorioPorPavimentoRetorno.next();
				}
				
				OSPavimentoSomatorioPorTipoPavimentoHelper helper = 
					new OSPavimentoSomatorioPorTipoPavimentoHelper();
				if (pavRua.getDescricaoPvtRua() != null){
					helper.setDescricaoPvtRua(pavRua.getDescricaoPvtRua());
				}
				if (pavRua.getValorPvtRua() != null){
					helper.setValorPvtRua(pavRua.getValorPvtRua());
				}
				if (pavRetorno.getDescricaoPvtRuaRetorno() != null){
					helper.setDescricaoPvtRuaRetorno(pavRetorno.getDescricaoPvtRuaRetorno());
				}
				if (pavRetorno.getValorPvtRuaRetorno() != null){
					helper.setValorPvtRuaRetorno(pavRetorno.getValorPvtRuaRetorno());
				}
				
				
				colecaoSomatorioPorPavimento.add(helper);
				
				
			}
				
				
				
				
			
			
			OSPavimentoSomatorioPorTipoPavimentoHelper helperTotalizacao = 
				new OSPavimentoSomatorioPorTipoPavimentoHelper(descricaoPvtRua,
						valorPvtRua,descricaoPvtRuaRetorno,valorPvtRuaRetorno);
			
			colecaoSomatorioPorPavimento.add(helperTotalizacao);
			
			////////////////////////
			
			
			// coloca a coleção de parâmetros da analise no iterator
			for (OSPavimentoRetornoHelper oSPavimentoRetornoHelper : collOrdemServicoPavimentoHelper) {
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// numeroOS
				String numeroOS = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico() != null) {
					
					numeroOS = oSPavimentoRetornoHelper.getOrdemServico().getId().toString();
				}
				
				// matricula do imóvel
				String matricula = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico()!= null 
						&& oSPavimentoRetornoHelper.getOrdemServico().getImovel() != null) {
					
					matricula = oSPavimentoRetornoHelper.getOrdemServico().getImovel().getId().toString();
				}
				
				// endereco
				String endereco = "";				
				if (oSPavimentoRetornoHelper.getEndereco() != null) {
					
					endereco = oSPavimentoRetornoHelper.getEndereco();
				}
									
				 // data encerramento da OS
				String dataEncerramento = "";				
				if (oSPavimentoRetornoHelper.getOrdemServico() != null && oSPavimentoRetornoHelper.getOrdemServico().getDataEncerramento()!= null) {
					dataEncerramento = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServico().getDataEncerramento());
				}
				
				// tipo pvto rua
				String tipoPvtoRua = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada()!= null) {
					
					tipoPvtoRua = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada();
				}
				
				// Metr. (m²) indicada.
				BigDecimal metragem = null;				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua()!= null) {
					
					metragem =oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRua();
				}
				
				// data de Retorno
				String dataRetorno = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento()!= null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataExecucao()!= null) {
					
					dataRetorno = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataExecucao());
				}

				//  data de Aceite
				String dataAceite = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento()!= null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataAceite()!= null) {
					
					dataAceite = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataAceite());
				}
				

				//  data da Rejeição
				String dataRejeicao = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento()!= null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataRejeicao()!= null) {
					
					dataRejeicao = Util.formatarData(oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDataRejeicao());
				}
				
				//tipo pvto rua retorno.
				String tipoPvtoRuaRetorno = "";				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno()!= null &&
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada()!= null) {
					tipoPvtoRuaRetorno = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId() + "-" +
					oSPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada();
				}
				
				// Metr. (m²) indicada retorno.
				BigDecimal metragemRetorno = null;				
				if (oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null && oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno()!= null) {
					metragemRetorno = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno();
				}
				
				//Indicador de Aceite
				String indicadorAceite = "";
				if(oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null 
						&& oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite() != null){
					
					indicadorAceite = oSPavimentoRetornoHelper.getOrdemServicoPavimento().getIndicadorAceite().toString();
				}
				
				String motivo = "";
				if ( oSPavimentoRetornoHelper.getOrdemServicoPavimento() != null  &&
						oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDescricaoMotivoAceite() != null ) {
					motivo = "MOTIVO: " +oSPavimentoRetornoHelper.getOrdemServicoPavimento().getDescricaoMotivoAceite();
					
					if ( motivo.length() > 200 ) {
						motivo = motivo.substring(0, 200);
					}
				}
				
			

				//Inicializa o construtor constituído dos campos
				// necessários para a impressão do relatorio
				relatorioBean = new RelatorioRelacaoServicosAcompanhamentoRepavimentacaoBean(
																					numeroOS,
																					matricula,
																					endereco,
																					dataEncerramento,
																					tipoPvtoRua,
																					metragem,
																					null,
																					tipoPvtoRuaRetorno,
																					metragemRetorno,
																					null,
																					null,
																					motivo );
				relatorioBean.setDataAceite(dataAceite);
				relatorioBean.setDataRetorno(dataRetorno);
				relatorioBean.setIndicadorAceite(indicadorAceite);
				relatorioBean.setArrayJRSubValoresPorTipoPavimento(
					new JRBeanCollectionDataSource(colecaoSomatorioPorPavimento));
				
				relatorioBean.setDataRejeicao(dataRejeicao);
				
				//O indicador pode mudar, caso o retorno não seja igual, mas a diferenca esteja 
				//entre o percentual permitido
				Integer indicadorAceiteComPercentualConvergencia = 
					calculaPercentualMetragemEValidaRetorno(metragem, metragemRetorno, indicadorAceite);
				
				if ( indicadorAceiteComPercentualConvergencia != null ) { 
					relatorioBean.setIndicadorAceiteComPercentualConvergencia(indicadorAceiteComPercentualConvergencia.toString());
				}
				//adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);

			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		UnidadeOrganizacional unidadeOrganizacional = null;
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
	    filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,oSPavimentoHelperParametros.getIdUnidadeResponsavel()));		    
	    filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeSuperior");	
	    
		Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		Iterator it = colecaoUnidadeOrganizacional.iterator();
		while(it.hasNext()){
			unidadeOrganizacional = (UnidadeOrganizacional)it.next();
		}		
		if(unidadeOrganizacional != null){
			parametros.put("unidadeResponsavel", unidadeOrganizacional.getDescricao());				

		}
		
		if(unidadeOrganizacional != null && unidadeOrganizacional.getUnidadeSuperior() != null){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacionalSup = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacionalSup.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,unidadeOrganizacional.getUnidadeSuperior().getId()));		 
			filtroUnidadeOrganizacionalSup.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			UnidadeOrganizacional unidadeOrganizacionalSup = null;
			Collection colecaoUnidadeOrganizacionalSup = fachada.pesquisar(filtroUnidadeOrganizacionalSup, UnidadeOrganizacional.class.getName());
			Iterator itt = colecaoUnidadeOrganizacionalSup.iterator();
			while(itt.hasNext()){
				unidadeOrganizacionalSup = (UnidadeOrganizacional)itt.next();
			}
			
			if(unidadeOrganizacionalSup!= null && unidadeOrganizacionalSup.getGerenciaRegional()!= null){
				parametros.put("gerenciaRegional", unidadeOrganizacionalSup.getGerenciaRegional().getId() + "-" + unidadeOrganizacionalSup.getGerenciaRegional().getNomeAbreviado());	
				
			}
		}
		
		parametros.put("situacao", situacaoAceiteDescricao);
		
		parametros.put("escolhaRelatorio", escolhaRelatorio);
		
		parametros.put("unidadeOrganizacional", descricaoUnidadeOrganizacional);
		
		String periodoAceiteServico = "";
		if(periodoAceiteServicoInicial != null && !periodoAceiteServicoInicial.equals("") 
				&& periodoAceiteServicoFinal != null && !periodoAceiteServicoFinal.equals("")){
			
			periodoAceiteServico = periodoAceiteServicoInicial + " a " + periodoAceiteServicoFinal;
			parametros.put("periodoAceiteServico", periodoAceiteServico) ;
		}
		
		String periodoRetornoServico = "";
		if(retornoServicoInicial !=null && !retornoServicoInicial.equals("") 
				&& retornoServicoFinal != null && !retornoServicoFinal.equals("")){
			
			periodoRetornoServico = retornoServicoInicial + " a " + retornoServicoFinal;
			parametros.put("periodoRetornoServico", periodoRetornoServico);
		}
	
		// cria uma instância do dataSource do relatório
		if ( relatorioBeans.size() > 0) {
			RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);
			
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_RELACAO_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE, parametros, ds,
					tipoFormatoRelatorio);
		} else {

			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			
		}

		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RELACAO_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		
		OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoHelperParametros = (OrdemRepavimentacaoProcessoAceiteHelper) getParametro("osPavimentoAceiteHelper");
		
		retorno = Fachada.getInstancia().pesquisarOrdemRepavimentacaoProcessoAceiteCount(oSPavimentoHelperParametros);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoOrdemRepavimentacaoProcessoAceite", this);
	}
	
	/**
	 *  [SB0003] - Imprimir relação das ordens
	 *  1.331
	 * Metodo responsavel por verificar se a metragem informada no retorno esta compreendida
	 * entre o percentual de variação permitido.
	 * 
	 * @author Arthur Carvalho
	 * @date 26/07/2010
	 * @param metragem
	 * @param metragemRetono
	 * @return
	 */
	public Integer calculaPercentualMetragemEValidaRetorno(BigDecimal metragem, BigDecimal metragemRetono, String indicadorAceite) {
		Integer indicadorAceiteComCalculoPercentualConvergencia = null;
		BigDecimal percentualConvergenciaRepavimentacao = new BigDecimal(0);
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		percentualConvergenciaRepavimentacao = sistemaParametro.getPercentualConvergenciaRepavimentacao();
		
		//1.3.3.    Total Ordens Aceitas 
		if ( indicadorAceite.equals("1") ) {
			if ( percentualConvergenciaRepavimentacao != null ) { 
				 
				if ( metragem.add(metragem.multiply(percentualConvergenciaRepavimentacao).divide(
								new BigDecimal(100))).compareTo(metragemRetono) >= 0 &&
									metragem.subtract(metragem.multiply(
										percentualConvergenciaRepavimentacao).divide(
												new BigDecimal(100))).compareTo(metragemRetono) <= 0 ) {
					
					indicadorAceiteComCalculoPercentualConvergencia = 1;
				} else {
					indicadorAceiteComCalculoPercentualConvergencia = 2;
				}
			}
		//1.3.4.	Total Ordens Não Aceitas 
		} else if ( indicadorAceite.equals("2") ) {
			
			if ( percentualConvergenciaRepavimentacao != null ) {
				if ( metragem.add(metragem.multiply(percentualConvergenciaRepavimentacao).divide(
						new BigDecimal(100))).compareTo(metragemRetono) <= 0 &&
							metragem.subtract(metragem.multiply(
								percentualConvergenciaRepavimentacao).divide(
										new BigDecimal(100))).compareTo(metragemRetono) >= 0 ) {
			
					indicadorAceiteComCalculoPercentualConvergencia = 1;
				} else {
					indicadorAceiteComCalculoPercentualConvergencia = 2;
				}
			}
		}
		return indicadorAceiteComCalculoPercentualConvergencia;
	}

}
