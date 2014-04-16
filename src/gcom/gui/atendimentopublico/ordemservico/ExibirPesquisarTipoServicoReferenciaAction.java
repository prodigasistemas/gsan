package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 07/08/2006
 */
public class ExibirPesquisarTipoServicoReferenciaAction extends GcomAction {

	/**
	 * Este caso de uso efetua pesquisa do Material
	 * 
	 * [UC0381] Inserir Material
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 07/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		// HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarTipoServicoReferencia");

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarTipoServicoReferenciaActionForm pesquisarTipoServicoReferenciaActionForm = (PesquisarTipoServicoReferenciaActionForm) actionForm;

		if (httpServletRequest.getParameter("enter") == null) {

			pesquisarTipoServicoReferenciaActionForm.setDescricao("");
			pesquisarTipoServicoReferenciaActionForm.setDescricaoAbreviada("");
			pesquisarTipoServicoReferenciaActionForm
					.setDescricaoTipoServico("");
			pesquisarTipoServicoReferenciaActionForm.setIdTipoServico("");
			pesquisarTipoServicoReferenciaActionForm
					.setIndicadorExistenciaOsReferencia("");

		} else {

			String idTipoServico = pesquisarTipoServicoReferenciaActionForm
					.getIdTipoServico();

			if (idTipoServico != null && !idTipoServico.trim().equals("")) {

				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, idTipoServico));

				Collection colecaoTipoServico = fachada.pesquisar(
						filtroServicoTipo, ServicoTipo.class.getName());

				if (colecaoTipoServico != null && !colecaoTipoServico.isEmpty()) {

					ServicoTipo servicoTipo = (ServicoTipo) colecaoTipoServico
							.iterator().next();
					pesquisarTipoServicoReferenciaActionForm
							.setDescricaoTipoServico(servicoTipo.getDescricao());

				} else {
					pesquisarTipoServicoReferenciaActionForm
							.setIdTipoServico("");
					httpServletRequest.setAttribute("nomeCampo",
							"idTipoServico");
					httpServletRequest.setAttribute(
							"idTipoServicoNaoEncontrado", "exception");
					pesquisarTipoServicoReferenciaActionForm
							.setDescricaoTipoServico("Tipo de Servico Inexistente");
				}
			}
		}
		
		if(httpServletRequest.getParameter("tipoConsulta") != null){
			String tipoConsulta = (String) httpServletRequest.getParameter("tipoConsulta");
			
			if(tipoConsulta.equalsIgnoreCase("tipoServico")){
			pesquisarTipoServicoReferenciaActionForm.
							setIdTipoServico(httpServletRequest.getParameter("idCampoEnviarDados"));
			pesquisarTipoServicoReferenciaActionForm.
							setDescricaoTipoServico(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			
		}
		
//		 envia uma flag que será verificado no material_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros

		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaServicoTipoReferencia") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaServicoTipoReferencia",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaServicoTipoReferencia"));
		}else if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaServicoTipoReferencia") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaServicoTipoReferencia",
							httpServletRequest.getParameter("caminhoRetornoTelaPesquisaServicoTipoReferencia"));
		}	

		return retorno;
	}

}
