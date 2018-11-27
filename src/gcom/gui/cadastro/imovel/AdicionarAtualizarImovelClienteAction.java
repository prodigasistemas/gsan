package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class AdicionarAtualizarImovelClienteAction extends GcomAction {

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
	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("adicionarAtualizarImovelCliente");

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Collection imovelClientesNovos = null;

		Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");

		if (sessao.getAttribute("imovelClientesNovos") != null) {
			imovelClientesNovos = (Collection) sessao.getAttribute("imovelClientesNovos");
		} else {
			imovelClientesNovos = new ArrayList();
		}

		// instância um cliente

		Cliente cliente = new Cliente();

		// teste se o cliente ja foi pesquisado com enter

		if (inserirImovelActionForm.get("idCliente") != null) {

			// recupera o id do cliente
			String idCliente = (String) inserirImovelActionForm.get("idCliente");
			// instância o filtro do cliente
			FiltroCliente filtroCliente = new FiltroCliente();

			// adiciona o parametro no filtro
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));

			// faz a pesquisa do cliente
			Collection clientesObjs = fachada.pesquisar(filtroCliente, Cliente.class.getName());

			// recupera o cliente da coleção pesquisada
			if (!clientesObjs.isEmpty()) {
				cliente = (Cliente) clientesObjs.iterator().next();
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
			}

		}

		// inicializa o tipo do cliente imovel
		ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();

		// recupera id do tipo do cliente imovel
		clienteRelacaoTipo.setId((Integer) inserirImovelActionForm.get("tipoClienteImovel"));
		// recupera a descricao do tipo do cliente imovel
		clienteRelacaoTipo.setDescricao((String) inserirImovelActionForm.get("textoSelecionado"));

		SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

		Date dataInicioRelacao = null;
		if (inserirImovelActionForm.get("dataInicioClienteImovelRelacao") != null
				&& !((String) inserirImovelActionForm.get("dataInicioClienteImovelRelacao")).equals("")) {
			try {
				dataInicioRelacao = dataFormato.parse((String) inserirImovelActionForm.get("dataInicioClienteImovelRelacao"));

			} catch (ParseException ex) {
				dataInicioRelacao = null;
			}
		}

		Date dataCorrente = null;
		Calendar data = Calendar.getInstance();

		data.set(Calendar.SECOND, 0);
		data.set(Calendar.MILLISECOND, 0);
		data.set(Calendar.HOUR, 0);
		data.set(Calendar.MINUTE, 0);
		dataCorrente = data.getTime();

		// caso a data de inicio da relação seja anterior que a data atual
		if (dataInicioRelacao.before(dataCorrente)) {
			
			if (fachada.verificarPermissaoEspecialAtiva(PermissaoEspecial.MUDANCA_TITULARIDADE_RETROATIVA, usuarioLogado)) {
				
				if (Fachada.getInstancia().isImovelEmCobrancaJudicial(imovel.getId())) {
					throw new ActionServletException("mudanca.titularidade.retroativa.imovel.cobranca.judicial");
				} else if (!Fachada.getInstancia().existeRAAbertaPorSoliticacao(imovel.getId(),SolicitacaoTipoEspecificacao.MUDANCA_TITULARIDADE_CONTA)) {
					throw new ActionServletException("mudanca.titularidade.retroativa.imovel.sem_ra");
				} else {
					Fachada.getInstancia().mudarTitularidaRetroativa(imovel.getId(), cliente.getId(), dataInicioRelacao);
				}
			} else {
				throw new ActionServletException("atencao.data_inicio_relacao_cliente_imovel");
			}
			
		}

		if (dataInicioRelacao == null) {
			dataInicioRelacao = new Date();
		}

		// inicializa o cliente imovel
		ClienteImovel clienteImovel = new ClienteImovel(dataInicioRelacao, null, null, cliente, clienteRelacaoTipo);

		// Verifica permissão especial para manter cliente
		// responsavel do imovel.
		Categoria categoria = fachada.obterPrincipalCategoriaImovel(imovel.getId());

		if (categoria.getId().compareTo(Categoria.PUBLICO) == 0
				&& clienteImovel.getClienteRelacaoTipo().getId().compareTo(ClienteRelacaoTipo.RESPONSAVEL.intValue()) == 0) {

			boolean possuiPermissaoManterClienteResponsavelImoveisPublicos = fachada.verificarPermissaoEspecialAtiva(
					PermissaoEspecial.ALTERAR_CLIENTE_RESPONSAVEL_PARA_IMOVEIS_PUBLICOS, usuarioLogado);

			if (!possuiPermissaoManterClienteResponsavelImoveisPublicos) {
				throw new ActionServletException("atencao.nao_usuario_nao_possui_permissao_alterar_cliente_reponsavel");
			}

		}

		// Adiciona o imovel ao cliente imovel
		clienteImovel.setImovel(imovel);
		// Coloca a data de ultima alteração para identificar o objeto
		clienteImovel.setUltimaAlteracao(new Date());

		if (!imovelClientesNovos.contains(clienteImovel)) {
			// verifica se o tipo do cliente é usuário ou é responsável
			if (clienteImovel.isClienteUsuario()) {

				if (sessao.getAttribute("idClienteImovelUsuario") == null || sessao.getAttribute("idClienteImovelUsuario").equals("")) {
					if (imovel != null && imovel.getImovelPerfil() != null && imovel.getImovelPerfil().getId() != null
							&& imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) {
						
						throw new ActionServletException("atencao.cliente_na_tarifa_social", null, "usuário");
					}
					sessao.setAttribute("idClienteImovelUsuario", cliente.getId().toString());
					httpServletRequest.setAttribute("idClienteImovelUsuario", cliente.getId().toString());

					clienteImovel.setIndicadorNomeConta(new Short((short) 2));

					imovelClientesNovos.add(clienteImovel);

				} else {
					throw new ActionServletException("atencao.ja_cadastradado.cliente_imovel_usuario");
				}
			} else if (clienteImovel.isClienteResponsavel()) {

				if (sessao.getAttribute("idClienteImovelResponsavel") == null || sessao.getAttribute("idClienteImovelResponsavel").equals("")) {

					sessao.setAttribute("idClienteImovelResponsavel", cliente.getId().toString());

					clienteImovel.setIndicadorNomeConta(new Short((short) 2));
					imovelClientesNovos.add(clienteImovel);
				} else {
					throw new ActionServletException("atencao.ja_cadastradado.cliente_imovel_responsavel");
				}
			} else {
				// sever para cliente do tipo proprietario adiciona o cliente imovel na coleção de imovelClientesNovos
				clienteImovel.setIndicadorNomeConta(new Short((short) 2));
				imovelClientesNovos.add(clienteImovel);
			}

			inserirImovelActionForm.set("idCliente", null);
			inserirImovelActionForm.set("nomeCliente", null);

			// manda para a sessão a coleção de imovelClienteNovos
			sessao.setAttribute("imovelClientesNovos", imovelClientesNovos);

		} else {
			throw new ActionServletException("atencao.ja_cadastradado.cliente_imovel");
		}

		return retorno;
	}

}
