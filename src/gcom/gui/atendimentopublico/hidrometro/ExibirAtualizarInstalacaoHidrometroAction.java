package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroPocoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
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

public class ExibirAtualizarInstalacaoHidrometroAction extends GcomAction {

	private AtualizarInstalacaoHidrometroActionForm form;
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("atualizarInstalacaoHidrometro");
		form = (AtualizarInstalacaoHidrometroActionForm) actionForm;

		Boolean veioEncerrarOS = null;

		if (request.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			veioEncerrarOS = Boolean.FALSE;
		}

		HttpSession sessao = request.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		if (form.getReset().equals("true")) {
			form.reset();
		} else if (form.getReset().equals("hidrometro")) {
			form.resetHidrometro();
		}

		// Permissao Especial Efetuar Ligacao de Agua sem RA
		boolean atualizarInstalcaoHidrometroSemRA = getFachada().verificarPermissaoEspecial(PermissaoEspecial.ATUALIZAR_INSTALACAO_DO_HIDROMETRO, usuarioLogado);

		request.setAttribute("atualizarInstalcaoHidrometroSemRA", atualizarInstalcaoHidrometroSemRA);

		form.setPermissaoAtualizarInstalacaoHidrometrosemRA("false");

		pesquisarColecaoLocalInstalacao(sessao);
		pesquisarColecaoHidrometroProtecao(sessao);
		pesquisarColecaoPocoTipo(request);

		Integer idOrdemServico = Util.converterStringParaInteger(request.getParameter("idOrdemServico"));
		form.setUsuarioNaoEncontrado("false");


		String idImovel = null;
		if (request.getParameter("matriculaImovel") != null && !request.getParameter("matriculaImovel").equalsIgnoreCase("")) {
			idImovel = (String) request.getParameter("matriculaImovel");
		} else {
			idImovel = form.getIdImovel();
		}

		if (idImovel != null && !idImovel.trim().equals("")) {
			String inscricao = getFachada().pesquisarInscricaoImovel(new Integer(idImovel));

			if (inscricao != null && !inscricao.equalsIgnoreCase("")) {
				form.setMatriculaImovel(idImovel);
				form.setInscricaoImovel(inscricao);

				Imovel imovel = pesquisarImovel(idImovel);

				if (ConstantesSistema.INDICADOR_IMOVEL_EXCLUIDO == imovel.getIndicadorExclusao()) {
					throw new ActionServletException("atencao.atualizar_imovel_situacao_invalida", null, imovel.getId().toString());
				}

				form.setSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
				form.setSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());

				Collection<ClienteImovel> colecaoClienteImovel = pesquisarClienteImovel(idImovel);
				if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
					ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel.iterator().next();

					form.setClienteUsuario(clienteImovel.getCliente().getNome());

					if (clienteImovel.getCliente().getClienteTipo().getId().intValue() == ClienteTipo.INDICADOR_PESSOA_FISICA.intValue()) {
						form.setCpfCnpjCliente(clienteImovel.getCliente().getCpfFormatado());
					} else if (clienteImovel.getCliente().getClienteTipo().getId().intValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA.intValue()) {
						form.setCpfCnpjCliente(clienteImovel.getCliente().getCnpjFormatado());
					} else {
						form.setCpfCnpjCliente("");
					}
				}

				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;

				if (Util.validarStringNumerica(form.getMedicaoTipo())) {
					Integer medicaoTipo = Integer.parseInt(form.getMedicaoTipo());

					getFachada().validarExistenciaHidrometro(imovel, medicaoTipo);

					if (MedicaoTipo.LIGACAO_AGUA.equals(medicaoTipo)) {
						hidrometroInstalacaoHistorico = imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico();
					} else if (MedicaoTipo.POCO.equals(medicaoTipo)) {
						hidrometroInstalacaoHistorico = imovel.getHidrometroInstalacaoHistorico();
					}

					form.setDataInstalacao(Util.formatarData(hidrometroInstalacaoHistorico.getDataInstalacao()));
					form.setHidrometro(hidrometroInstalacaoHistorico.getHidrometro().getNumero());
					form.setLocalInstalacao(hidrometroInstalacaoHistorico.getHidrometroLocalInstalacao().getId().toString());
					form.setIndicadorExistenciaCavalete(hidrometroInstalacaoHistorico.getIndicadorExistenciaCavalete().toString());
					form.setLeituraInstalacao(hidrometroInstalacaoHistorico.getNumeroLeituraInstalacao().toString());
					form.setNumeroSelo(Util.converterObjetoParaString(hidrometroInstalacaoHistorico.getNumeroSelo()));
					form.setLeituraCorte(Util.converterObjetoParaString(hidrometroInstalacaoHistorico.getNumeroLeituraCorte()));
					form.setLeituraSupressao(Util.converterObjetoParaString(hidrometroInstalacaoHistorico.getNumeroLeituraSupressao()));
					form.setLeituraRetirada(Util.converterObjetoParaString(hidrometroInstalacaoHistorico.getNumeroLeituraRetirada()));
					form.setNumeroLacre(Util.converterObjetoParaString(hidrometroInstalacaoHistorico.getNumeroLacre()));
					form.setProtecao(hidrometroInstalacaoHistorico.getHidrometroProtecao().getId().toString());

					request.setAttribute("hidrometroInstalacaoHistorico", hidrometroInstalacaoHistorico);
				}
			}
		}

		if (Util.validarNumeroMaiorQueZERO(idOrdemServico)) {
			OrdemServico ordemServico = getFachada().recuperaOSPorId(idOrdemServico);

			if (ordemServico != null) {
				sessao.setAttribute("ordemServicoEncontrada", "true");
				getFachada().validarExibirAtualizarInstalacaoHidrometro(ordemServico, veioEncerrarOS);

				form.setVeioEncerrarOS("" + veioEncerrarOS);

				Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
				Cliente cliente = pesquisarClienteUsuarioImovel(imovel);

				String cpfCnpjCliente = "";
				if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
					cpfCnpjCliente = cliente.getCpfFormatado();
				} else {
					cpfCnpjCliente = cliente.getCnpjFormatado();
				}

				form.setIdOrdemServico(idOrdemServico.toString());
				form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				form.setMatriculaImovel(imovel.getId().toString());
				form.setInscricaoImovel(getFachada().pesquisarInscricaoImovel(imovel.getId()));
				form.setSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
				form.setSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());
				form.setClienteUsuario(cliente.getNome());

				if ("00.000.000/0000-00".equals(cpfCnpjCliente)) {
					form.setCpfCnpjCliente("");
				} else {
					form.setCpfCnpjCliente(cpfCnpjCliente);
				}

				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
				if (Util.validarStringNumerica(form.getMedicaoTipo())) {

					Integer medicaoTipo = Integer.parseInt(form.getMedicaoTipo());

					getFachada().validarExistenciaHidrometro(imovel, medicaoTipo);

					if (MedicaoTipo.LIGACAO_AGUA.equals(medicaoTipo)) {
						hidrometroInstalacaoHistorico = imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico();
					} else if (MedicaoTipo.POCO.equals(medicaoTipo)) {
						hidrometroInstalacaoHistorico = imovel.getHidrometroInstalacaoHistorico();

						Imovel imovelPoco = getFachada().pesquisarImovel(imovel.getId());
						if (imovelPoco.getPocoTipo() != null) {
							form.setTipoPoco(imovelPoco.getPocoTipo().getId().toString());
						}
					}

					form.setDataInstalacao(Util.formatarData(hidrometroInstalacaoHistorico.getDataInstalacao()));
					form.setHidrometro(hidrometroInstalacaoHistorico.getHidrometro().getNumero());
					form.setLocalInstalacao(hidrometroInstalacaoHistorico.getHidrometroLocalInstalacao().getId().toString());
					form.setProtecao(hidrometroInstalacaoHistorico.getHidrometroProtecao().getId().toString());
					form.setIndicadorExistenciaCavalete(hidrometroInstalacaoHistorico.getIndicadorExistenciaCavalete().toString());
					form.setLeituraInstalacao(hidrometroInstalacaoHistorico.getNumeroLeituraInstalacao().toString());
					form.setNumeroSelo(Util.converterObjetoParaString(hidrometroInstalacaoHistorico.getNumeroSelo()));
					form.setLeituraCorte(Util.converterObjetoParaString(hidrometroInstalacaoHistorico.getNumeroLeituraCorte()));
					form.setLeituraSupressao(Util.converterObjetoParaString(hidrometroInstalacaoHistorico.getNumeroLeituraSupressao()));
					form.setLeituraRetirada(Util.converterObjetoParaString(hidrometroInstalacaoHistorico.getNumeroLeituraRetirada()));
					form.setNumeroLacre(Util.converterObjetoParaString(hidrometroInstalacaoHistorico.getNumeroLacre()));

					request.setAttribute("hidrometroInstalacaoHistorico", hidrometroInstalacaoHistorico);
				}
			} else {
				sessao.removeAttribute("ordemServicoEncontrada");
				form.setIdOrdemServico("");
				form.setNomeOrdemServico("Ordem de Serviço Inexistente");
			}
		} else {
			form.setDataCorrente(new Date());
			request.setAttribute("nomeCampo", "idOrdemServico");
		}

		return retorno;
	}

	private Cliente pesquisarClienteUsuarioImovel(Imovel imovel) {
		Filtro filtro = new FiltroClienteImovel();
		filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovel.getId()));
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
		filtro.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		ClienteImovel clienteImovel = (ClienteImovel) getFachada().pesquisar(filtro, ClienteImovel.class.getName()).iterator().next();
		return clienteImovel.getCliente();
	}

	@SuppressWarnings("unchecked")
	private Collection<ClienteImovel> pesquisarClienteImovel(String idImovel) {
		Filtro filtro = new FiltroClienteImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_TIPO);
		return getFachada().pesquisar(filtro, ClienteImovel.class.getName());
	}

	private Imovel pesquisarImovel(String idImovel) {
		Filtro filtro = new FiltroImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.HIDROMETRO_POCO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.HIDROMETRO_PROTECAO_POCO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO_HIDROMETRO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO_HIDROMETRO_PROTECAO);
		return (Imovel) getFachada().pesquisar(filtro, Imovel.class.getName()).iterator().next();
	}

	private void pesquisarColecaoPocoTipo(HttpServletRequest request) {
		FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();
		filtroPocoTipo.setCampoOrderBy(FiltroPocoTipo.DESCRICAO);
		filtroPocoTipo.adicionarParametro(new ParametroSimples(FiltroPocoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroPocoTipo.adicionarParametro(new ParametroSimples(FiltroPocoTipo.INDICADOR_HIDROMETRO_TIPO_POCO, ConstantesSistema.INDICADOR_USO_ATIVO));
		request.setAttribute("colecaoTipoPoco", getFachada().pesquisar(filtroPocoTipo, PocoTipo.class.getName()));
	}

	private void pesquisarColecaoHidrometroProtecao(HttpSession sessao) {
		Filtro filtro = new FiltroHidrometroProtecao();
		filtro.setCampoOrderBy(FiltroHidrometroProtecao.DESCRICAO);
		filtro.adicionarParametro(new ParametroSimples(FiltroHidrometroProtecao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		sessao.setAttribute("colecaoProtecao", getFachada().pesquisar(filtro, HidrometroProtecao.class.getName()));
	}

	private void pesquisarColecaoLocalInstalacao(HttpSession sessao) {
		Filtro filtro = new FiltroHidrometroLocalInstalacao();
		filtro.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);
		filtro.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalInstalacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		sessao.setAttribute("colecaoLocalInstalacao", getFachada().pesquisar(filtro, HidrometroLocalInstalacao.class.getName()));
	}
}
