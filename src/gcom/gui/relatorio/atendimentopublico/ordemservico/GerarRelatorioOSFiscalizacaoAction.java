package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOSFiscalizacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
 * [SB0005] – Gerar Formulário em formato pdf
 * @author Vivianne Sousa
 * @date 02/06/2011
 */
public class GerarRelatorioOSFiscalizacaoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoOSFiscalizacaoGeradas = null;
		if (sessao.getAttribute("colecaoOSFiscalizacao") != null 
				&& !sessao.getAttribute("colecaoOSFiscalizacao").equals("")){

			colecaoOSFiscalizacaoGeradas = (Collection)sessao.getAttribute("colecaoOSFiscalizacao");
		}
		
		if(colecaoOSFiscalizacaoGeradas == null || colecaoOSFiscalizacaoGeradas.isEmpty()){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		Integer idGrupoCobranca = null;
		if(httpServletRequest.getParameter("idGrupoCobranca") != null
				&& !httpServletRequest.getParameter("idGrupoCobranca").equals("null")
				&& !httpServletRequest.getParameter("idGrupoCobranca").equals("")){
			idGrupoCobranca = new Integer((String)httpServletRequest.getParameter("idGrupoCobranca"));
		}
		
		FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = 
			new FiltroOSReferidaRetornoTipo(FiltroOSReferidaRetornoTipo.DESCRICAO);
		filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
			FiltroOSReferidaRetornoTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
			FiltroOSReferidaRetornoTipo.ID_SERVICO_TIPO_REFERENCIA,new Integer(2)));

		Collection colecaoOSReferidaRetornoTipo = Fachada.getInstancia().pesquisar(
				filtroOSReferidaRetornoTipo, OsReferidaRetornoTipo.class.getName());
		
		RelatorioOSFiscalizacao relatorioOSFiscalizacao = new RelatorioOSFiscalizacao(
			(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
		// Parte que vai mandar o relatório para a tela
		// cria uma instância da classe do relatório
		
		relatorioOSFiscalizacao.addParametro("colecaoOSFiscalizacaoGeradas", colecaoOSFiscalizacaoGeradas);
		relatorioOSFiscalizacao.addParametro("qtdeOSFiscalizacaoGeradas", colecaoOSFiscalizacaoGeradas.size());
		relatorioOSFiscalizacao.addParametro("idGrupoCobranca", idGrupoCobranca);
		relatorioOSFiscalizacao.addParametro("colecaoOSReferidaRetornoTipo", colecaoOSReferidaRetornoTipo);
		
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		relatorioOSFiscalizacao.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		
		try {
			retorno = processarExibicaoRelatorio(relatorioOSFiscalizacao,
				tipoRelatorio, httpServletRequest, httpServletResponse,	actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
	
		return retorno;
	}

}
