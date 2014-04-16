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
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Rômulo Aurélio de Melo
 * @date 29/09/2008
 */
public class AtualizarUnidadeNegocioAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		AtualizarUnidadeNegocioActionForm form = (AtualizarUnidadeNegocioActionForm) actionForm;

		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) sessao
				.getAttribute("atualizarUnidadeNegocio");


		// Nome
		unidadeNegocio.setNome(form.getNome());

		// Nome Abreviado
		unidadeNegocio.setNomeAbreviado(form.getNomeAbreviado());

		// Cliente
		Cliente cliente = new Cliente();
		if (form.getIdCliente() != null && !form.getIdCliente().equalsIgnoreCase("")){
			FiltroCliente filtroCliente = new FiltroCliente();
	
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
					form.getIdCliente()));
	
			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());
	
			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
	
				 cliente = (Cliente) colecaoCliente.iterator().next();
	
				Integer clienteFuncionario = fachada.verificarClienteSelecionadoFuncionario(new Integer(form.getIdCliente()));
				
				if(clienteFuncionario == null){
					throw new ActionServletException("atencao.cliente_selecionado_nao_e_funcionario");
				}
				
			} else {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
		}
		unidadeNegocio.setCliente(cliente);
		
		// Gerencia Regional
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.ID, form.getIdGerenciaRegional()));

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

		// Indicador de uso
		unidadeNegocio.setIndicadorUso(new Integer(form.getIndicadorUso())
				.shortValue());
		
		// Cnpj
		unidadeNegocio.setCnpj(form.getNumeroCnpj());
		
		fachada.atualizarUnidadeNegocio(unidadeNegocio, usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Unidade de Negócio de código "
				+ form.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Unidade de Negócio ",
				"exibirFiltrarUnidadeNegocioAction.do?menu=sim");

		return retorno;
	}
}
