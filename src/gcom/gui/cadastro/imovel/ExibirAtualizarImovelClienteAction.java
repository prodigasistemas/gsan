package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que inicializa a primeira página do processo de inserir imovel cliente
 * 
 * @author Sávio Luiz
 * @created 24 de Maio de 2004
 */
public class ExibirAtualizarImovelClienteAction extends GcomAction {
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarImovelCliente");

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.removeAttribute("gis");
		
		DynaValidatorForm inserirImovelClienteActionForm = (DynaValidatorForm) actionForm;

		// cria variaveis
		String idCliente = null;

		// atribui os valores q vem pelo request as variaveis
		idCliente = (String) inserirImovelClienteActionForm.get("idCliente");

		// cria filtro
		FiltroCliente filtroCliente = new FiltroCliente();
		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();

		// limpa o cliente da sessao
		sessao.removeAttribute("clienteObj");

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("confirmado")!= null && httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			sessao.setAttribute("confirmou", "sim");
		}

		Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");

		Collection tipoClientesImoveis = null;

		SimpleDateFormat dataFormatoAtual = new SimpleDateFormat("dd/MM/yyyy");
		// joga em dataInicial a parte da data
		String dataAtual = dataFormatoAtual.format(new Date());

		inserirImovelClienteActionForm.set("dataInicioClienteImovelRelacao",
				dataAtual);
		

		String nomeDoImovel = "";
		
		if(imovel.getNomeImovel()!= null){
			nomeDoImovel = imovel.getNomeImovel();
		}
		
		inserirImovelClienteActionForm.set("nomeDoImovel", nomeDoImovel);

		if (imovel.getImovelPerfil() != null
				&& imovel.getImovelPerfil().getId().equals(
						ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) {
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, ConstantesSistema.INDICADOR_TARIFA_SOCIAL));
			ImovelPerfil imovelPerfil = (ImovelPerfil) 
				Util.retonarObjetoDeColecao(fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName()));
			if (imovelPerfil.getIndicadorBloqueaDadosSocial().equals(ConstantesSistema.SIM)){
			
				filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
						ConstantesSistema.CLIENTE_IMOVEL_TIPO_RESPONSAVEL));
			}
		}
		filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteRelacaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		tipoClientesImoveis = fachada.pesquisar(filtroClienteRelacaoTipo,
				ClienteRelacaoTipo.class.getName());
		// a coleção de clientesImoveisTipos é obrigatório
		if (tipoClientesImoveis == null || tipoClientesImoveis.equals("")) {

			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"tipo cliente imovel");

		} else {

			sessao.setAttribute("tipoClientesImoveis", tipoClientesImoveis);
			if (inserirImovelClienteActionForm.get("textoSelecionado") == null
					|| inserirImovelClienteActionForm.get("textoSelecionado")
							.equals("")) {
				inserirImovelClienteActionForm.set("textoSelecionado",
						((ClienteRelacaoTipo) ((List) tipoClientesImoveis)
								.get(0)).getDescricao());
			}
		}

		// informar o cliente conta para o imovel
		String indicadorNomeConta = httpServletRequest
				.getParameter("indicadorNomeConta");

		if (indicadorNomeConta != null && !indicadorNomeConta.equals("")) {

			// String[] ids =
			// (String[])inserirImovelClienteActionForm.get("idNomeConta");

			if (sessao.getAttribute("imovelClientesNovos") != null) {
				Collection clientesImoveis = (Collection) sessao
						.getAttribute("imovelClientesNovos");

				if (!clientesImoveis.isEmpty()) {
					Iterator iteratorClientesImoveis = clientesImoveis
							.iterator();
					// int i = 0;

					String nomeContaSelecionado = (String) inserirImovelClienteActionForm
							.get("nomeContaSelecionado");
					while (iteratorClientesImoveis.hasNext()) {

						// String nomeConta = ids[i];
						ClienteImovel clienteImovel = (ClienteImovel) iteratorClientesImoveis
								.next();

						// if(nomeConta.substring(0,7).equals("USUARIO"))
						if ((clienteImovel.getClienteRelacaoTipo()
								.getDescricao()
								+ "-" + clienteImovel.getCliente().getId()
								.toString()).equals(nomeContaSelecionado)) {
							clienteImovel.setIndicadorNomeConta(new Short(
									(short) 1));
						} else {
							clienteImovel.setIndicadorNomeConta(new Short(
									(short) 2));
						}
						
						// i++;
					}
				}

			}

		} else {

			// -------Parte que trata do código quando o usuário tecla enter
			// se o id do cliente for diferente de nulo
			if (idCliente != null
					&& !idCliente.toString().trim().equalsIgnoreCase("")) {
				Collection clientes = null;
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, new Integer(idCliente)));
				clientes = fachada.pesquisar(filtroCliente, Cliente.class
						.getName());
				if (clientes != null && !clientes.isEmpty()) {
					// O cliente foi encontrado
					inserirImovelClienteActionForm.set("idCliente",
							((Cliente) ((List) clientes).get(0)).getId()
									.toString());
					inserirImovelClienteActionForm.set("nomeCliente",
							((Cliente) ((List) clientes).get(0)).getNome());

					Cliente cliente = new Cliente();

					cliente = (Cliente) clientes.iterator().next();
					sessao.setAttribute("clienteObj", cliente);
				} else {
					httpServletRequest.setAttribute(
							"codigoClienteNaoEncontrado", "true");
					inserirImovelClienteActionForm.set("nomeCliente", "");
				}

			}
			
			//**************************************************************
			// Autor: Ivan Sergio
			// Data: 21/07/2009
			// CRC2103
			// Esse filtro se faz necessario por conta do usuario ter a
			// opcao de atualizar o cliente.
			//**************************************************************
			Collection clientesImoveis = (Collection) sessao.getAttribute("imovelClientesNovos");
			if (clientesImoveis != null && !clientesImoveis.isEmpty()) {
				Iterator iteratorClientesImoveis = clientesImoveis.iterator();
				
				while (iteratorClientesImoveis.hasNext()) {
					ClienteImovel clienteImovel = (ClienteImovel) iteratorClientesImoveis.next();
					if (clienteImovel.getIndicadorNomeConta() == null) {
						clienteImovel.setIndicadorNomeConta(new Short((short) 2));
					}
			
					if (sessao.getAttribute("codigoCliente") != null
							&& !sessao.getAttribute("codigoCliente").equals("")) {
						FiltroCliente filtro = new FiltroCliente();
						filtro.adicionarParametro(new ParametroSimples(
								FiltroCliente.ID, clienteImovel.getCliente().getId()));
						Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(
								fachada.pesquisar(filtro, Cliente.class.getName()));
						
						clienteImovel.setCliente(cliente);
					}
					// fim da parte que trata do codigo do usuario que tecla enter
				}
			}
		}
		
		//************************************************************
		// Autor: Ivan Sergio
		// Data: 06/08/2009
		// CRC2103
		// Adicionado para o caso do Inserir Cliente no manter Imovel
		//************************************************************
		//httpServletRequest.setAttribute("idImovel", sessao.getAttribute("idImovel"));
		//************************************************************
		
		return retorno;
	}

}
