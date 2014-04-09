package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de atualizar volume mínimo de
 * ligação de esgoto.
 * 
 * @author Leonardo Regis
 * @date 22/09/2006
 */
public class ExibirAtualizarVolumeMinimoLigacaoEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarVolumeMinimoLigacaoEsgotoActionExibir");
		HttpSession sessao = httpServletRequest.getSession(false);
		AtualizarVolumeMinimoLigacaoEsgotoActionForm form = (AtualizarVolumeMinimoLigacaoEsgotoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (form.getVeioEncerrarOS() != null
					&& !form
							.getVeioEncerrarOS().equals("")) {
				if (form.getVeioEncerrarOS()
						.toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}
		form.setVeioEncerrarOS("" + veioEncerrarOS);

		// Variavel responsavél pelo preenchimento do imovel no formulário
		String idOrdemServico = null;

		if (form.getIdOrdemServico() != null) {
			idOrdemServico = form.getIdOrdemServico();
		} else {
			idOrdemServico = (String) httpServletRequest
					.getAttribute("veioEncerrarOS");

			sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest
							.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			sessao.setAttribute("semMenu", "SIM");
		} else {
			sessao.removeAttribute("semMenu");
		}
		if (idOrdemServico != null && !idOrdemServico.equals("")) {
			OrdemServico ordemServico = fachada.recuperaOSPorId(new Integer(
					idOrdemServico));
			if (ordemServico != null && !ordemServico.equals("")) {
				// Validações
				fachada.validarExibirAtualizarVolumeMinimoLigacaoEsgoto(
						ordemServico, veioEncerrarOS);
				form.setIdOrdemServico(idOrdemServico);
				// Preenchar dados da ordem de servico
				form.setNomeOrdemServico(ordemServico.getServicoTipo()
						.getDescricao());
				// Preencher dados do imovel
				this.preencherDadosImovel(form, ordemServico);
				// Preencher dados do Cliente
				this.pesquisarCliente(form, ordemServico);
				sessao.setAttribute("osEncontrada", "true");
			} else {
				sessao.removeAttribute("osEncontrada");
				form.setNomeOrdemServico("Ordem de Serviço inexistente");
			}
		} else {
			httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
			form.setDataConcorrencia(new Date());
		}
		return retorno;
	}

	/**
	 * Preencher dados do imóvel
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 */
	private void preencherDadosImovel(
			AtualizarVolumeMinimoLigacaoEsgotoActionForm form,
			OrdemServico ordemServico) {
		Fachada fachada = Fachada.getInstancia();
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		// Matricula Imóvel
		form.setMatriculaImovel(imovel.getId().toString());
		// Inscrição Imóvel
		String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel
				.getId());
		form.setInscricaoImovel(inscricaoImovel);
		// Situação da Ligação de Agua
		String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao()
				.getDescricao();
		form.setSituacaoLigacaoAgua(situacaoLigacaoAgua);
		// Situação da Ligação de Esgoto
		String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao()
				.getDescricao();
		form.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
		// Categoria do Imovel
		Categoria categoria = fachada.obterDescricoesCategoriaImovel(imovel);
		form.setCategoriaImovel(categoria.getDescricao());
		// Quatidade de Economias
		int qtdeconomias = fachada.obterQuantidadeEconomias(imovel);
		form.setQtdeEconomia(qtdeconomias + "");
		// Volume Mínimo
		if (imovel.getLigacaoEsgoto().getConsumoMinimo() != null
				&& !imovel.getLigacaoEsgoto().getConsumoMinimo()
						.equals("")) {
			form.setConsumoMinimoFixado(imovel.getLigacaoEsgoto()
					.getConsumoMinimo()
					+ "");
		} else {
			form.setConsumoMinimoFixado("");
		}
		// Valor Obtido
		String valorObtido = fachada.obterConsumoMinimoLigacao(imovel, null)
				+ "";
		form.setValorObtido(valorObtido);
		// Consumo Tarifa
		form.setConsumoTarifaId(imovel.getConsumoTarifa().getId() + "");
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 */
	private void pesquisarCliente(
			AtualizarVolumeMinimoLigacaoEsgotoActionForm form,
			OrdemServico ordemServico) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, ordemServico
						.getRegistroAtendimento().getImovel().getId()));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
					.iterator().next();
			Cliente cliente = clienteImovel.getCliente();

			String documento = "";
			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			form.setClienteUsuario(cliente.getNome());
			form.setCpfCnpjCliente(documento);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}

	}
}
