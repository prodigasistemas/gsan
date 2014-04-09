package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * @author Rômulo Aurélio
 * @created 29/09/2008
 */
public class InserirUnidadeNegocioAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirUnidadeNegocioActionForm form = (InserirUnidadeNegocioActionForm) actionForm;

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		String nome = form.getNome();

		String nomeAbreviado = form.getNomeAbreviado();

		String numeroCnpj = form.getNumeroCnpj();

		String idCliente = form.getIdCliente();

		String idGerenciaRegional = form.getIdGerenciaRegional();

		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();

		// Nome
		if (nome != null && !nome.equals("")) {

			unidadeNegocio.setNome(nome);

		}

		// Nome Abreviado
		if (nomeAbreviado != null && !nomeAbreviado.equals("")) {

			unidadeNegocio.setNomeAbreviado(nomeAbreviado);
		}

		// Cliente
		if (idCliente != null && !idCliente.trim().equals("")) {
			// Pesquisa o cliente na base
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
				Cliente cliente = (Cliente) colecaoCliente.iterator().next();

				Integer clienteFuncionario = fachada.verificarClienteSelecionadoFuncionario(new Integer(idCliente));
				
				if(clienteFuncionario == null){
					throw new ActionServletException("atencao.cliente_selecionado_nao_e_funcionario");
				}
				
				unidadeNegocio.setCliente(cliente);

			} else {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
		
		}


		// Gerencia Regional
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

				unidadeNegocio.setGerenciaRegional(gerenciaRegional);

			} else {
				throw new ActionServletException(
						"atencao.gerenciaRegional_inexistente");
			}

		}

		// Cnpj

		if (numeroCnpj != null && !numeroCnpj.equals("")) {

			unidadeNegocio.setCnpj(numeroCnpj);

		}

		// Indicador de Uso

		unidadeNegocio.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		Integer idUnidadeNegocio = fachada.inserirUnidadeNegocio(
				unidadeNegocio, usuarioLogado);

		montarPaginaSucesso(httpServletRequest,
				"Unidade de Negócio de código  " + idUnidadeNegocio
						+ " inserida com sucesso.",
				"Inserir outra Unidade de Negócio",
				"exibirInserirUnidadeNegócioAction.do?menu=sim",
				"exibirAtualizarUnidadeNegocioAction.do?idRegistroAtualizacao="
						+ idUnidadeNegocio,
				"Atualizar Unidade de Negócio Inserida");

		return retorno;
	}
}
