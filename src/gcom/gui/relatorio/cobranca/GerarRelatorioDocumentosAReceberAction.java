package gcom.gui.relatorio.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioDocumentosAReceber;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * [UC990] Gerar Relatório de Documentos a Receber
 *
 * @author Hugo Amorim
 * @date 22/02/2010
 *
 */
public class GerarRelatorioDocumentosAReceberAction extends
		ExibidorProcessamentoTarefaRelatorio {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Fachada fachada = Fachada.getInstancia();
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();	
		
		RelatorioDocumentosAReceberForm form =
			(RelatorioDocumentosAReceberForm) actionForm;
		
		Integer anoMesInformado = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno());
		
		if(anoMesInformado.compareTo(sistemaParametro.getAnoMesFaturamento())>=0){
			throw new ActionServletException("atencao.mes_ano_resumo_invalido",Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));
		}
	
		FiltroRelatorioDocumentosAReceberHelper 
			helper = new FiltroRelatorioDocumentosAReceberHelper(
					//mesAno
					form.getMesAno()!=null ? 
						Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno()).toString():null,
					//idCategoriaTipo
					form.getIdCategoriaTipo()!=null ? 
						form.getIdCategoriaTipo():null,
					//idsCategoria
					form.getIdsCategoria()!=null ? 
						form.getIdsCategoria():null,
					//idsPerfilImovel
					form.getIdsPerfilImovel()!=null ? 
							form.getIdsPerfilImovel():null,		
					//idsEsferaPoder
					form.getIdsEsferaPoder()!=null ? 
							form.getIdsEsferaPoder():null,	
					//idGerencia
					form.getIdGerencia()!=null ? 
						form.getIdGerencia():null,		
					//idUnidade
					form.getIdUnidade()!=null ? 
						form.getIdUnidade():null,			
					//idLocalidade
					form.getIdLocalidade()!=null ? 
						form.getIdLocalidade():null,
					//idOpcaoTotalizacao
					form.getIdOpcaoTotalizacao()!=null ? 
						form.getIdOpcaoTotalizacao():null,		
					//colecaoFaixas
					form.getColecaoFaixas()!=null ? 
						form.getColecaoFaixas():null,
					sistemaParametro.getNomeEstado(),
					/**TODO:COSANPA
					 * @author Adriana Muniz
					 * @date 29/03/2012
					 * 
					 * Acrescimo de mais um filtro na geração do relatório R0990 
					 * */
					form.getIndicadorGuiaPagamento()!=null ?
							form.getIndicadorGuiaPagamento():null
				);
		
		if(form.getIndicadorInclusaoValorSemParcelas().equals("1")) {
			helper.setExibirDebitoSemParcela(true);
			helper.setExibirCreditoSemParcela(true);
		}
		
		RelatorioDocumentosAReceber relatorio = new RelatorioDocumentosAReceber(usuario);
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		
		/*
		 * TODO - COSANPA - Felipe Santos - 28/03/2012
		 * 
		 * Alteração para pegar o tipo de relatório retornado através da
		 * página conforme selecionado pelo usuário
		 */
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		// fim da alteração
		relatorio.addParametro("tipoFormatoRelatorio", new Integer(tipoRelatorio));
		relatorio.addParametro("filtro", helper);
	
		
		retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);
		
		
		
		return retorno;
	}
}
