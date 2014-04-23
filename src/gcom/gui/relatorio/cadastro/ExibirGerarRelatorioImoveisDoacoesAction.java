package gcom.gui.relatorio.cadastro;

import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
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
 * [UC1174] - Gerar Relatorio Imoveis com Doações.
 * 
 * @author Erivan Sousa
 * 
 * @date 01/06/2011,17/06/2011
 */

public class ExibirGerarRelatorioImoveisDoacoesAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioImoveisDoacoes");

		HttpSession sessao = httpServletRequest.getSession(false);

		GerarRelatorioImoveisDoacoesActionForm form = (GerarRelatorioImoveisDoacoesActionForm) actionForm;

		// Verifica se entrou apartir
		// do menu

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").toString()
						.equalsIgnoreCase("sim")) {

			this.limpar(form, sessao);

		}

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest
				.getParameter("objetoConsulta");
		// Pesquisar Imovel
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")) {
			if (objetoConsulta.trim().equals("1")) {
				// Faz a consulta do Imovel
				this.pesquisarImovel(form);
			}
			if (objetoConsulta.trim().equals("2")) {
				// Faz a consulta do Imovel
				this.pesquisarUsuarioAdesao(form);
			}
			if (objetoConsulta.trim().equals("3")) {
				// Faz a consulta do Imovel
				this.pesquisarUsuarioCancelamento(form);
			}
		}
		this.pesquisarEntidadeBeneficente(httpServletRequest);
		this.setaRequest(httpServletRequest, form);

		return retorno;
	}

	private void limpar(GerarRelatorioImoveisDoacoesActionForm form,
			HttpSession sessao) {

		form.setEntidadeBeneficente("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
		form.setImovel("");
		form.setPeriodoAdesaoFinal("");
		form.setPeriodoAdesaoInicial("");
		form.setPeriodoCancelamentoFinal("");
		form.setPeriodoCancelamentoInicial("");
		form.setReferenciaFimDoacaoInicial("");
		form.setReferenciaFimDoacaoFinal("");
		form.setReferenciaInicioDoacaoInicial("");
		form.setReferenciaInicioDoacaoFinal("");
		form.setOpcaoRelatorio("1");

		sessao.removeAttribute("imovelEncontrado");
		sessao.removeAttribute("usuarioAdesaoEncontrado");
		sessao.removeAttribute("usuarioCancelamentoEncontrado");
	}

	/**
	 * Pesquisa Entidade Beneficente
	 * 
	 * @author Erivan Sousa
	 * @date 01/06/2011
	 */
	private void pesquisarEntidadeBeneficente(
			HttpServletRequest httpServletRequest) {

		FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();

		filtroEntidadeBeneficente.setConsultaSemLimites(true);
		filtroEntidadeBeneficente.setCampoOrderBy(FiltroEntidadeBeneficente.ID);
		filtroEntidadeBeneficente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroEntidadeBeneficente.CLIENTE);

		Collection colecaoEntidadeBeneficente = this.getFachada().pesquisar(
				filtroEntidadeBeneficente, EntidadeBeneficente.class.getName());

		if (colecaoEntidadeBeneficente == null
				|| colecaoEntidadeBeneficente.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Entidade Beneficente");
		} else {
			httpServletRequest.setAttribute("colecaoEntidadeBeneficente",
					colecaoEntidadeBeneficente);
		}
	}

	/**
	 * Pesquisa Imovel
	 * 
	 * @author Erivan Sousa
	 * @date 24/05/2011
	 */
	private void pesquisarImovel(GerarRelatorioImoveisDoacoesActionForm form) {

		Object local = form.getImovel();

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, local));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		// Recupera Localidade
		Collection colecaoImovel = this.getFachada().pesquisar(filtroImovel,
				Imovel.class.getName());

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

			form.setImovel(imovel.getId().toString());
			form.setNomeImovel(imovel.getInscricaoFormatada());

		} else {
			form.setImovel("");
			form.setNomeImovel("Imóvel inexistente");
		}
	}

	/**
	 * Pesquisa Usuario Adesão
	 * 
	 * @author Erivan Sousa
	 * @date 24/05/2011
	 */
	private void pesquisarUsuarioAdesao(
			GerarRelatorioImoveisDoacoesActionForm form) {

		Object local = form.getUsuarioAdesao();

		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, local));

		// Recupera Localidade
		Collection colecaoUsuario = this.getFachada().pesquisar(filtroUsuario,
				Usuario.class.getName());

		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {

			Usuario usuario = (Usuario) Util
					.retonarObjetoDeColecao(colecaoUsuario);

			form.setUsuarioAdesao(usuario.getLogin().toString());
			form.setNomeUsuarioAdesao(usuario.getNomeUsuario());

		} else {
			form.setUsuarioAdesao("");
			form.setNomeUsuarioAdesao("Usuário inexistente");
		}
	}

	/**
	 * Pesquisa Usuario Cancelamento
	 * 
	 * @author Erivan Sousa
	 * @date 24/05/2011
	 */
	private void pesquisarUsuarioCancelamento(
			GerarRelatorioImoveisDoacoesActionForm form) {

		Object local = form.getUsuarioCancelamento();

		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, local));

		// Recupera Localidade
		Collection colecaoUsuario = this.getFachada().pesquisar(filtroUsuario,
				Usuario.class.getName());

		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {

			Usuario usuario = (Usuario) Util
					.retonarObjetoDeColecao(colecaoUsuario);

			form.setUsuarioCancelamento(usuario.getLogin().toString());
			form.setNomeUsuarioCancelamento(usuario.getNomeUsuario());

		} else {
			form.setUsuarioCancelamento("");
			form.setNomeUsuarioCancelamento("Usuário inexistente");
		}
	}

	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioImoveisDoacoesActionForm form) {

		// Imovel
		if (form.getImovel() != null && !form.getImovel().equals("")) {
			httpServletRequest.setAttribute("imovelEncontrado", "true");
		}
		if (form.getUsuarioAdesao() != null
				&& !form.getUsuarioAdesao().equals("")) {
			httpServletRequest.setAttribute("usuarioAdesaoEncontrado", "true");
		}
		if (form.getUsuarioCancelamento() != null
				&& !form.getUsuarioCancelamento().equals("")) {
			httpServletRequest.setAttribute("usuarioCancelamentoEncontrado",
					"true");
		}
	}
}
