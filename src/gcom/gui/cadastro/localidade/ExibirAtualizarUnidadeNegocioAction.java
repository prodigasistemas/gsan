package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0???]Manter Unidade Negocio
 * 
 * @author Rômulo Aurélio
 * @date 30/09/2008
 */

public class ExibirAtualizarUnidadeNegocioAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("unidadeNegocioAtualizar");

		AtualizarUnidadeNegocioActionForm form = (AtualizarUnidadeNegocioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;

		String idCliente = form.getIdCliente();
		
		if (idCliente == null ) {
			idCliente = "";
		}

		String idGerenciaRegional = form.getIdGerenciaRegional();
		if (idGerenciaRegional == null ) {
			idGerenciaRegional = "";
		}
		
		if (idCliente != null && !idCliente.trim().equals("")) {
			// Pesquisa o cliente na base
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
				Cliente cliente = (Cliente) colecaoCliente.iterator().next();

				form.setIdCliente(cliente.getId().toString());

				form.setNomeCliente(cliente.getNome());
			} else {
				form.setIdCliente("");
				form.setNomeCliente("Cliente inexistente");

				httpServletRequest.setAttribute("corCliente", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}
		}

		if (idGerenciaRegional != null && !idGerenciaRegional.trim().equals("")) {
			// Pesquisa o cliente na base
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, idGerenciaRegional));

			Collection colecaoGerenciaRegional = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional != null
					&& !colecaoGerenciaRegional.isEmpty()) {

				GerenciaRegional gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional
						.iterator().next();

				form.setIdGerenciaRegional(gerenciaRegional.getId().toString());

				form.setNomeGerenciaRegional(gerenciaRegional.getNome());
			} else {
				form.setIdGerenciaRegional("");
				form.setNomeGerenciaRegional("Gerência Regional inexistente");

				httpServletRequest.setAttribute("corGerenciaRegional",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"idGerenciaRegional");
			}

		}

		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
			UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
			unidadeNegocio.setId(new Integer(id));
			sessao.setAttribute("unidadeNegocio", unidadeNegocio);
		} else {
			id = ((UnidadeNegocio) sessao.getAttribute("unidadeNegocio"))
					.getId().toString();
		}

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
		
		
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, id));
			filtroUnidadeNegocio
					.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.CLIENTE);
			filtroUnidadeNegocio
					.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.GERENCIA);
			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				unidadeNegocio = (UnidadeNegocio) Util
						.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			}
			
			
			if ( idCliente.equals("") || unidadeNegocio.getCliente() != null  && idCliente == ""+ unidadeNegocio.getCliente().getId()) {
				
				if (  idGerenciaRegional.equals("") || unidadeNegocio.getCliente() != null && idGerenciaRegional == ""+ unidadeNegocio.getGerenciaRegional().getId()) {
			
					if (id == null || id.trim().equals("")) {
		
						form.setId(unidadeNegocio.getId().toString());
		
						form.setNome(unidadeNegocio.getNome());
		
						form.setNomeAbreviado(unidadeNegocio.getNomeAbreviado());
		
						if (unidadeNegocio.getCliente() != null) {
		
							form.setIdCliente(unidadeNegocio.getCliente().getId()
									.toString());
		
							form.setNomeCliente(unidadeNegocio.getCliente().getNome());
		
						}
		
						form.setIdGerenciaRegional(unidadeNegocio.getGerenciaRegional()
								.getId().toString());
		
						form.setNomeGerenciaRegional(unidadeNegocio
								.getGerenciaRegional().getNome());
		
						form.setIndicadorUso(unidadeNegocio.getIndicadorUso()
								.toString());
		
						if (unidadeNegocio.getCnpj() != null) {
							form.setNumeroCnpj(unidadeNegocio.getCnpj());
						} else {
							form.setNumeroCnpj("");
						}
		
					}
		
					form.setId(unidadeNegocio.getId().toString());
		
					form.setNome(unidadeNegocio.getNome());
		
					form.setNomeAbreviado(unidadeNegocio.getNomeAbreviado());
		
					if (unidadeNegocio.getCliente() != null) {
						form.setIdCliente(unidadeNegocio.getCliente().getId()
								.toString());
		
						form.setNomeCliente(unidadeNegocio.getCliente().getNome());
					}
		
					form.setIdGerenciaRegional(unidadeNegocio.getGerenciaRegional()
							.getId().toString());
		
					form.setNomeGerenciaRegional(unidadeNegocio.getGerenciaRegional()
							.getNome());
		
					form.setIndicadorUso(unidadeNegocio.getIndicadorUso().toString());
					
					if (unidadeNegocio.getCnpj() != null) {
						form.setNumeroCnpj(unidadeNegocio.getCnpj());
					} else {
						form.setNumeroCnpj("");
					}
					sessao.setAttribute("atualizarUnidadeNegocio", unidadeNegocio);
		
					if (sessao.getAttribute("colecaoUnidadeNegocio") != null) {
						sessao.setAttribute("caminhoRetornoVoltar",
								"/gsan/filtrarUnidadeNegocioAction.do");
					} else {
						sessao.setAttribute("caminhoRetornoVoltar",
								"/gsan/exibirFiltrarUnidadeNegocioAction.do");
					}
				}
			}
		}
		
		
		if ( httpServletRequest.getParameter("desfazer") != null &&
        		httpServletRequest.getParameter("desfazer").equals("S") ) {

			form.setId(unidadeNegocio.getId().toString());

			form.setNome(unidadeNegocio.getNome());

			form.setNomeAbreviado(unidadeNegocio.getNomeAbreviado());

			if (unidadeNegocio.getCliente() != null) {
				form.setIdCliente(unidadeNegocio.getCliente().getId()
						.toString());

				form.setNomeCliente(unidadeNegocio.getCliente().getNome());
			}

			form.setIdGerenciaRegional(unidadeNegocio.getGerenciaRegional()
					.getId().toString());

			form.setNomeGerenciaRegional(unidadeNegocio.getGerenciaRegional()
					.getNome());

			form.setIndicadorUso(unidadeNegocio.getIndicadorUso().toString());
			
			if (unidadeNegocio.getCnpj() != null) {
				form.setNumeroCnpj(unidadeNegocio.getCnpj());
			} else {
				form.setNumeroCnpj("");
			}
		}

		return retorno;
	}
		

}
