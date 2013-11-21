package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.ordemservico.ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioOrdensServicoFiscalizacaoAction extends ExibidorProcessamentoTarefaRelatorio{

	
	/**
	 * 
	 * 
	 * [UC1213] Emitir Relatorio de Ordem de Sercico de Fiscalizacao
	 * 
	 * @author Paulo Diniz
	 * @date 02/08/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obtém a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm relatorioActionForm = (ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm) actionForm;

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");		
		TarefaRelatorio relatorio = null;
		
		
		//Recuperando os dados do formulário
		String relatorioTipoFiscalizacao = relatorioActionForm.getTipoRelatorioFiscalizacao();
		String periodoInicial = relatorioActionForm.getPeriodoInicial();
		String periodoFinal = relatorioActionForm.getPeriodoFinal();
		String idLocalidadeInicial = relatorioActionForm.getLocalidadeInicial();
		String idLocalidadeFinal = relatorioActionForm.getLocalidadeFinal();
		String descLocalidadeInicial = relatorioActionForm.getDescricaoLocalidadeInicial();
		String descLocalidadeFinal = relatorioActionForm.getDescricaoLocalidadeFinal();
		String idGerenciaRegional = relatorioActionForm.getGerenciaRegional();
		String idUnidadeNegocios = relatorioActionForm.getUnidadeNegocio();
		String situacaoOS = relatorioActionForm.getSituacao();
		String idOSReferidaRetornoTipo = relatorioActionForm.getTipoRetorno();
		String aceitacaoDaOS = relatorioActionForm.getAceitacaoOS();
		
		
		//Validar Período Inicial
		if(periodoInicial == null || periodoInicial.equals("")){
			throw new ActionServletException("atencao.periodo_inicial_fiscalizacao.inexistente");
		}
		
		if(periodoFinal == null || periodoFinal.equals("")){
			throw new ActionServletException("atencao.periodo_final_fiscalizacao.inexistente");
		}
		
		if(periodoFinal != null && !periodoFinal.equals("")
				&& periodoInicial != null && !periodoInicial.equals("")){
			String anoMesIni =  periodoInicial.substring(3, 7) + periodoInicial.substring(0, 2);
			String anoMesFin =  periodoFinal.substring(3, 7) + periodoFinal.substring(0, 2);
			boolean compararReferencia = Util.compararAnoMesReferencia(anoMesIni, anoMesFin, ">");
			if(compararReferencia){
				throw new ActionServletException("atencao.periodo_inicial.maior.periodo_final");
			}
		}
		
		
		//Validar Existencia de Localidade Inicial
		if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
			Integer localidadeInicial = fachada.verificarExistenciaLocalidade(new Integer(idLocalidadeInicial));
			if( localidadeInicial == null || localidadeInicial <= 0){
				throw new ActionServletException("atencao.localidade.inexistente");
			}
		}
		
		//Validar Existencia de Localidade Final
		if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
			Integer localidadeFinal = fachada.verificarExistenciaLocalidade(new Integer(idLocalidadeFinal));
			if( localidadeFinal == null || localidadeFinal <= 0){
				throw new ActionServletException("atencao.localidade.inexistente");
			}
		}
		
		//[FS0007] Validar Localidade Pertencente a Gerencia Regional
		if((idGerenciaRegional != null && !idGerenciaRegional.equals(""))){
			
			if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
				FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
				filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idLocalidadeInicial));

				Collection colecaoLocalidadeInicial = fachada.pesquisar(
						filtroLocalidadeInicial, Localidade.class.getName());
				
				if (colecaoLocalidadeInicial != null && !colecaoLocalidadeInicial.isEmpty()) {
					Localidade localidadeInicial = (Localidade) colecaoLocalidadeInicial.iterator().next();
					
					if(localidadeInicial.getGerenciaRegional().getId().intValue() != Integer.parseInt(idGerenciaRegional)){
						FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
						filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, "1"));
						filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, idGerenciaRegional));
						Collection collGerenciaRegional = fachada.pesquisar(filtroGerencia, GerenciaRegional.class.getName());
						GerenciaRegional gerencia = (GerenciaRegional)collGerenciaRegional.iterator().next();
						throw new ActionServletException(
								"atencao.localidade.nao.pertencente.gerencia", null, gerencia.getNome());
					}
				}
			}
			if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
				if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
					if ((new Integer(idLocalidadeInicial)).compareTo(new Integer(idLocalidadeFinal)) > 0) {
						throw new ActionServletException("atencao.localidade.final.maior.localidade.inicial");
					}
				}
				
				FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
				filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idLocalidadeFinal));
				
				Collection colecaoLocalidadeFinal = fachada.pesquisar(
						filtroLocalidadeFinal, Localidade.class.getName());
				
				if (colecaoLocalidadeFinal != null && !colecaoLocalidadeFinal.isEmpty()) {
					Localidade localidadeFinal = (Localidade) colecaoLocalidadeFinal.iterator().next();
				
					if(localidadeFinal.getGerenciaRegional().getId().intValue() != Integer.parseInt(idGerenciaRegional)){
						FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
						filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, "1"));
						filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, idGerenciaRegional));
						Collection collGerenciaRegional = fachada.pesquisar(filtroGerencia, GerenciaRegional.class.getName());
						GerenciaRegional gerencia = (GerenciaRegional)collGerenciaRegional.iterator().next();
						throw new ActionServletException(
								"atencao.localidade.nao.pertencente.gerencia", null, gerencia.getNome());
					}
				}
			}
			
		}
		
		

		//Recuperando as ordem de servico especificadas
        Collection colecaoOrdemServico = fachada.pesquisarOrdensServicoFiscalizacao(
								        		Integer.parseInt(relatorioTipoFiscalizacao), 
								        		periodoInicial, 
								        		periodoFinal,
								        		idGerenciaRegional, 
								        		idUnidadeNegocios, 
								        		idLocalidadeInicial, 
								        		idLocalidadeFinal, 
								        		situacaoOS, 
								        		idOSReferidaRetornoTipo, 
								        		aceitacaoDaOS);
		
		//Nenhum parâmetro retornado
		if(colecaoOrdemServico == null || colecaoOrdemServico.isEmpty()){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "relatório de acompanhamento");
		}
		
		
		if(relatorioTipoFiscalizacao != null && relatorioTipoFiscalizacao.equals("1")){
			relatorio = new RelatorioOrdensServicoFiscalizacaoAction(
					(Usuario) (httpServletRequest.getSession(false))
					.getAttribute("usuarioLogado"), ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_ANALITICO);
		}else{
			relatorio = new RelatorioOrdensServicoFiscalizacaoAction(
					(Usuario) (httpServletRequest.getSession(false))
					.getAttribute("usuarioLogado"), ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_SINTETICO);
		}

		relatorio.addParametro("relatorioTipoFiscalizacao",new Integer(relatorioTipoFiscalizacao));
		relatorio.addParametro("periodoInicial",periodoInicial);
		relatorio.addParametro("periodoFinal",periodoFinal);
		relatorio.addParametro("idLocalidadeInicial",idLocalidadeInicial);
		relatorio.addParametro("idLocalidadeFinal",idLocalidadeFinal);
		relatorio.addParametro("descLocalidadeInicial",descLocalidadeInicial);
		relatorio.addParametro("descLocalidadeFinal",descLocalidadeFinal);
		relatorio.addParametro("idGerenciaRegional",idGerenciaRegional);
		relatorio.addParametro("idUnidadeNegocios",idUnidadeNegocios);
		relatorio.addParametro("situacaoOS",situacaoOS);
		relatorio.addParametro("idOSReferidaRetornoTipo",idOSReferidaRetornoTipo);
		relatorio.addParametro("aceitacaoDaOS",aceitacaoDaOS);
		
		relatorio.addParametro("colecaoOrdemServico",colecaoOrdemServico);
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		return retorno;
	}
}
