package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action responsável por adicionar na coleção a relação entre o cliente imovel,
 * o cliente e a data de inicio da relação
 * 
 * @author Sávio Luiz
 * @created 16 de Maio de 2004
 */
public class AdicionarInserirImovelClienteAction extends GcomAction {

	/**
	 * < <Descrição do método>>
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("adicionarInserirImovelCliente");

		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Collection imovelClientesNovos = null;

		if (sessao.getAttribute("imovelClientesNovos") != null) {
			imovelClientesNovos = (Collection) sessao.getAttribute("imovelClientesNovos");
		} else {
			imovelClientesNovos = new ArrayList();
		}

		Cliente cliente = new Cliente();

		if (inserirImovelActionForm.get("idCliente") != null) {

			String idCliente = (String) inserirImovelActionForm.get("idCliente");

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));

			Collection clientesObjs = fachada.pesquisar(filtroCliente, Cliente.class.getName());

			if (!clientesObjs.isEmpty()) {
				cliente = (Cliente) clientesObjs.iterator().next();
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
			}

		}

		ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();

		clienteRelacaoTipo.setId((Integer) inserirImovelActionForm.get("tipoClienteImovel"));
		clienteRelacaoTipo.setDescricao((String) inserirImovelActionForm.get("textoSelecionado"));

		ClienteImovel clienteImovel = new ClienteImovel(new Date(), null, null, cliente, clienteRelacaoTipo);

		clienteImovel.setUltimaAlteracao(new Date());

		if (!imovelClientesNovos.contains(clienteImovel)) {

			if (clienteImovel.isClienteUsuario()) {

				if (inserirImovelActionForm.get("idClienteImovelUsuario") == null || inserirImovelActionForm.get("idClienteImovelUsuario").equals("")) {
					sessao.setAttribute("idClienteImovelUsuario", cliente.getId().toString());
					inserirImovelActionForm.set("idClienteImovelUsuario", cliente.getId().toString());

					imovelClientesNovos.add(clienteImovel);
				} else {
					throw new ActionServletException("atencao.ja_cadastradado.cliente_imovel_usuario");
				}
			} else if (clienteImovel.isClienteResponsavel()) {
				if (inserirImovelActionForm.get("idClienteImovelResponsavel") == null || inserirImovelActionForm.get("idClienteImovelResponsavel").equals("")) {
					inserirImovelActionForm.set("idClienteImovelResponsavel", cliente.getId().toString());

					sessao.setAttribute("idClienteImovelResponsavel", cliente.getId().toString());

					imovelClientesNovos.add(clienteImovel);
				} else {
					throw new ActionServletException("atencao.ja_cadastradado.cliente_imovel_responsavel");
				}
			} else {
				imovelClientesNovos.add(clienteImovel);
			}

			inserirImovelActionForm.set("idCliente", null);
			inserirImovelActionForm.set("nomeCliente", null);

			sessao.setAttribute("imovelClientesNovos", imovelClientesNovos);

		} else {
			throw new ActionServletException("atencao.ja_cadastradado.cliente_imovel");
		}

		return retorno;
	}

}
