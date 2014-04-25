package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * [UC0734] Encerrar Ordem de Servico Vencida
 * 
 * @author Ivan Sérgio
 * 
 * @date 11/01/2008
 */
public class ExibirEncerrarOrdemServicoVencidaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirEncerrarOrdemServicoVencidaAction");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		// Form
		EncerrarOrdemServicoVencidaActionForm encerrarOrdemServicoVencida = 
			(EncerrarOrdemServicoVencidaActionForm) actionForm;
		
		// Limpa os Campos
		if (httpServletRequest.getParameter("menu") != null) {
			encerrarOrdemServicoVencida.setTipoOrdem("");
			encerrarOrdemServicoVencida.setQuantidadeDias("");
		}
		
		// Pesquisa o Tipo de Servico
		if(sessao.getAttribute("colecaoTipoServico") == null){
			pesquisarTipoServico(encerrarOrdemServicoVencida, fachada, sessao, httpServletRequest);
		}
		
		return retorno;
	}
	
	/**
	 * 
	 * @param encerrarOrdemServicoVencida
	 * @param fachada
	 * @param sessao
	 * @param httpServletRequest
	 */
	private void pesquisarTipoServico(
			EncerrarOrdemServicoVencidaActionForm encerrarOrdemServicoVencida,
			Fachada fachada,
			HttpSession sessao,
			HttpServletRequest httpServletRequest) {
		
		FiltroTipoServico filtro = new FiltroTipoServico();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTipoServico.ID, ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO, ParametroSimples.CONECTOR_OR));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTipoServico.ID, ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO, ParametroSimples.CONECTOR_OR));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTipoServico.ID, ServicoTipo.TIPO_CORTE_DE_AGUA_POR_DEBITO));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTipoServico.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection<ServicoTipo> colecaoTipoServico = 
			fachada.pesquisar(filtro, ServicoTipo.class.getName());
		
		// [FS0001 - Verificar Existencia de dados]
		if ( (colecaoTipoServico == null) || (colecaoTipoServico.isEmpty()) ) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null, ServicoTipo.class.getName());
		}else {
			sessao.setAttribute("colecaoTipoServico", colecaoTipoServico);
		}
	}
}
