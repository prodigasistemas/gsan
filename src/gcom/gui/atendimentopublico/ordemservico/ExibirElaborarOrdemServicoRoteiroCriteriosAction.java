package gcom.gui.atendimentopublico.ordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0456] Elaborar Roteiro de Programação de Ordem de Serviço
 * 
 * @author Rafael Pinto
 *
 * @date 04/09/2006
 */
public class ExibirElaborarOrdemServicoRoteiroCriteriosAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = mapping.findForward("exibirElaborarOrdemServico");

		HttpSession sessao = request.getSession(false);

		ElaborarOrdemServicoRoteiroCriteriosActionForm form = (ElaborarOrdemServicoRoteiroCriteriosActionForm) actionForm;

		// Coloca com default a orige serviço como (Solicitado)
		String origemServicos = form.getOrigemServicos();
		if (origemServicos == null || origemServicos.equals("")) {
			origemServicos = "1";
			form.setOrigemServicos(origemServicos);
		}

		// Coloca com default o criterio de seleção como (Tipo de Serviço)
		String criterioSelecao = form.getCriterioSelecao();
		if (criterioSelecao == null || criterioSelecao.equals("")) {
			criterioSelecao = "1";
		}

		// Coloca com default o serviço diagnosticado como (Todos)
		String servicoDiagnosticado = form.getServicoDiagnosticado();
		if (servicoDiagnosticado == null || servicoDiagnosticado.equals("")) {
			form.setServicoDiagnosticado("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
		}

		// Coloca com default os serviços acompanhados como (Todos)
		String servicoAcompanhamento = form.getServicoAcompanhamento();
		if (servicoAcompanhamento == null || servicoAcompanhamento.equals("")) {
			form.setServicoAcompanhamento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
		}

		Date dataRoteiro = Util.converteStringParaDate(form.getDataRoteiro());
		form.setDataRoteiro(Util.formatarData(dataRoteiro));

		// Seta o id da Unidade de Lotacao do Usuario
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		form.setUnidadeLotacao(usuario.getUnidadeOrganizacional().getId().toString());

		this.pesquisarServicoDisponivel(request, new Integer(criterioSelecao), new Integer(origemServicos));

		return retorno;
	}

	/**
	 * Pesquisa Servicos Disponiveis a partir da origem do servico (Solicitados,Seletivos e Ambos) e 
	 * a partir do criterio (Tipo de Servico, Tipo de Equipe, Unidade, Localidade, Setor e Distrito)
	 * 
	 * @param criterio,origemServico
	 * 
	 * @return Tipos de Servico: ServicoTipo ServicoPerfilTipo UnidadeOrganizacional
	 *         Localidade SetorComercial DistritoOperacional
	 */
	private void pesquisarServicoDisponivel(HttpServletRequest request, int criterio, int origemServicos) {

		HttpSession sessao = request.getSession(false);

		Collection<?> colecao = (Collection<?>) sessao.getAttribute("colecaoServicoDisponivel" + criterio + origemServicos);

		if (colecao == null) {

			Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

			colecao = Fachada.getInstancia().pesquisarTipoServicoDisponivelPorCriterio(
					usuario.getUnidadeOrganizacional(), criterio, origemServicos);

			sessao.setAttribute("colecaoServicoDisponivel" + criterio 	+ origemServicos, colecao);
		}

		if (colecao == null || colecao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Serviço Disponível");
		} else {
			request.setAttribute("colecaoTipoServico", colecao);
		}
	}
}
