package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.FiltroLigacaoOrigem;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.FiltroMaterialLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.FiltroPerfilLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroRamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoagua.FiltroSupressaoTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ordemservico.FiltroSupressaoMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
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

/**
 * Action responsável pela pre-exibição da pagina de atualizar ligação de água
 * 
 * @author Rafael Pinto
 * @created 18/07/2006
 */
public class ExibirAtualizarLigacaoAguaAction extends GcomAction {
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

		ActionForward retorno = actionMapping
				.findForward("atualizarLigacaoAgua");

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		AtualizarLigacaoAguaActionForm atualizarLigacaoAguaActionForm = (AtualizarLigacaoAguaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			veioEncerrarOS = Boolean.FALSE;
		}

		// Permissao Especial Efetuar Ligacao de Agua sem RA

		boolean atualizarLigacaoAguaSemRA = Fachada.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.ATUALIZAR_LIGACAO_DE_AGUA_SEM_RA,
						usuarioLogado);

		atualizarLigacaoAguaActionForm.setPermissaoAlterarOSsemRA("false");

		if (!veioEncerrarOS) {
			httpServletRequest.setAttribute(
					"atualizarLigacaoAguaSemRA",
					atualizarLigacaoAguaSemRA);

			if (atualizarLigacaoAguaSemRA) {
				atualizarLigacaoAguaActionForm
						.setPermissaoAlterarOSsemRA("true");
			}
		}

		String idImovel = null;

		if (httpServletRequest.getParameter("matriculaImovel") != null
				&& !httpServletRequest.getParameter("matriculaImovel")
						.equalsIgnoreCase("")) {

			idImovel = (String) httpServletRequest
					.getParameter("matriculaImovel");
		} else {

			idImovel = atualizarLigacaoAguaActionForm.getIdImovel();
		}

		if (idImovel != null && !idImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			String inscricaoImovelEncontrado = fachada
					.pesquisarInscricaoImovel(new Integer(idImovel));

			if (inscricaoImovelEncontrado != null
					&& !inscricaoImovelEncontrado.equalsIgnoreCase("")) {

				atualizarLigacaoAguaActionForm.setMatriculaImovel(idImovel);

				atualizarLigacaoAguaActionForm
						.setInscricaoImovel(inscricaoImovelEncontrado);

				atualizarLigacaoAguaActionForm.setDataConcorrencia(new Date());

				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));

				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);

				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);

				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
				
				
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

				Collection colecaoImovel = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				Imovel imovel = (Imovel) colecaoImovel.iterator().next();

				sessao.setAttribute("imovel", imovel);

				// [FS0003] Validar Situação de Agua do Imovel
				if (imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.POTENCIAL
						.intValue()
						|| imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.FACTIVEL
								.intValue()) {
					throw new ActionServletException(
							"atencao.atualizar_ligacao_agua_situacao_invalida",
							null, imovel.getLigacaoAguaSituacao()
									.getDescricao()
									+ "");

				}

				/*
				 * [FS0003] Verificar Situação Rede de Água na Quadra
				 * 
				 * Integração com o conceito de face da quadra
				 * Raphael Rossiter em 22/05/2009
				 */
				IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
				
				if ((integracao.getIndicadorRedeAgua()).equals(Quadra.SEM_REDE)) {
					
					throw new ActionServletException("atencao.seituacao_rede_agua_quadra", 
					null, imovel.getId() + "");
				}

				// [FS0006] Verificar Situação do Imovel
				if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
					throw new ActionServletException(
							"atencao.situacao_imovel_indicador_exclusao", null,
							imovel.getId() + "");
				}

				atualizarLigacaoAguaActionForm.setSituacaoLigacaoAgua(imovel
						.getLigacaoAguaSituacao().getDescricao());

				atualizarLigacaoAguaActionForm.setSituacaoLigacaoEsgoto(imovel
						.getLigacaoEsgotoSituacao().getDescricao());

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, idImovel));

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_TIPO);

				Collection colecaoClienteImovel = fachada.pesquisar(
						filtroClienteImovel, ClienteImovel.class.getName());

				if (colecaoClienteImovel != null
						&& !colecaoClienteImovel.isEmpty()) {
					ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
							.iterator().next();

					atualizarLigacaoAguaActionForm
							.setClienteUsuario(clienteImovel.getCliente()
									.getNome());

					if (clienteImovel.getCliente().getClienteTipo().getId()
							.intValue() == ClienteTipo.INDICADOR_PESSOA_FISICA
							.intValue()) {

						atualizarLigacaoAguaActionForm
								.setCpfCnpjCliente(clienteImovel.getCliente()
										.getCpfFormatado());

					} else if (clienteImovel.getCliente().getClienteTipo()
							.getId().intValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA
							.intValue()) {
						atualizarLigacaoAguaActionForm
								.setCpfCnpjCliente(clienteImovel.getCliente()
										.getCnpjFormatado());
					} else {
						atualizarLigacaoAguaActionForm.setCpfCnpjCliente("");
					}

				}

				// ------------------ Dados da Ligacao de
				// Agua--------------------------

				// Validação do select
				boolean habilitaCorte = true;
				boolean habilitaSupressao = true;

				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();

				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAgua.ID, idImovel));
				filtroLigacaoAgua
						.adicionarCaminhoParaCarregamentoEntidade("supressaoTipo");
				filtroLigacaoAgua
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaPerfil");
				filtroLigacaoAgua
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaDiametro");
				filtroLigacaoAgua
						.adicionarCaminhoParaCarregamentoEntidade("motivoCorte");
				filtroLigacaoAgua
						.adicionarCaminhoParaCarregamentoEntidade("supressaoMotivo");
				filtroLigacaoAgua
						.adicionarCaminhoParaCarregamentoEntidade("ramalLocalInstalacao");
				filtroLigacaoAgua
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoOrigem");
				filtroLigacaoAgua
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaPerfil");
				filtroLigacaoAgua
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
				filtroLigacaoAgua
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");

				Collection colecaoLigacaoAgua = fachada.pesquisar(
						filtroLigacaoAgua, LigacaoAgua.class.getName());

				if (colecaoLigacaoAgua != null && !colecaoLigacaoAgua.isEmpty()) {

					LigacaoAgua ligacaoAgua = (LigacaoAgua) colecaoLigacaoAgua
							.iterator().next();

					imovel.setLigacaoAgua(ligacaoAgua);
					
					this.setarDadosLigacaoAgua(atualizarLigacaoAguaActionForm,
							ligacaoAgua, imovel, habilitaCorte,
							habilitaSupressao, httpServletRequest, sessao,
							fachada);

				}

				httpServletRequest.setAttribute("habilitaDataLigacao", true);

			} else {

				httpServletRequest.setAttribute("corImovel", "exception");

				atualizarLigacaoAguaActionForm
						.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}

		}

		String idOrdemServico = atualizarLigacaoAguaActionForm
				.getIdOrdemServico();

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			OrdemServico ordemServico = fachada.recuperaOSPorId(new Integer(
					idOrdemServico));

			if (ordemServico != null) {

				fachada.validarExibirLigacaoAguaImovel(ordemServico,
						veioEncerrarOS);

				atualizarLigacaoAguaActionForm.setVeioEncerrarOS(""
						+ veioEncerrarOS);
				atualizarLigacaoAguaActionForm.setDataConcorrencia(new Date());

				Imovel imovel = ordemServico.getRegistroAtendimento()
						.getImovel();

				LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();

				sessao.setAttribute("ordemServico", ordemServico);

				// Validação do select
				boolean habilitaCorte = true;
				boolean habilitaSupressao = true;

				atualizarLigacaoAguaActionForm
						.setIdOrdemServico(idOrdemServico);
				atualizarLigacaoAguaActionForm.setNomeOrdemServico(ordemServico
						.getServicoTipo().getDescricao());
				atualizarLigacaoAguaActionForm.setServicoTipo(""
						+ ordemServico.getServicoTipo().getId());

				/*-------------- Início dados do Imóvel---------------*/

				String matriculaImovel = "" + imovel.getId();
				String inscricaoImovel = imovel.getInscricaoFormatada();

				atualizarLigacaoAguaActionForm
						.setMatriculaImovel(matriculaImovel);
				atualizarLigacaoAguaActionForm
						.setInscricaoImovel(inscricaoImovel);

				// [FS0003] Validar Situação de Agua do Imovel
				if (imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.POTENCIAL
						.intValue()
						|| imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.FACTIVEL
								.intValue()) {
					throw new ActionServletException(
							"atencao.atualizar_ligacao_agua_situacao_invalida",
							null, imovel.getLigacaoAguaSituacao()
									.getDescricao()
									+ "");

				}

				/*
				 * [FS0003] Verificar Situação Rede de Água na Quadra
				 * 
				 * Integração com o conceito de face da quadra
				 * Raphael Rossiter em 22/05/2009
				 */
				IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
				
				if ((integracao.getIndicadorRedeAgua()).equals(Quadra.SEM_REDE)) {
					
					throw new ActionServletException("atencao.seituacao_rede_agua_quadra", 
					null, imovel.getId() + "");
				}

				// [FS0006] Verificar Situação do Imovel
				if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
					throw new ActionServletException(
							"atencao.situacao_imovel_indicador_exclusao", null,
							imovel.getId() + "");
				}

				// Situação da Ligação de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao()
						.getDescricao();
				atualizarLigacaoAguaActionForm
						.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				// Situação da Ligação de Esgoto
				String situacaoLigacaoEsgoto = imovel
						.getLigacaoEsgotoSituacao().getDescricao();
				atualizarLigacaoAguaActionForm
						.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				/*-------------- Início dados da LigacaoAgua---------------*/

				this.setarDadosLigacaoAgua(atualizarLigacaoAguaActionForm,
						ligacaoAgua, imovel, habilitaCorte, habilitaSupressao,
						httpServletRequest, sessao, fachada);

			} else {
				atualizarLigacaoAguaActionForm.setHabilitaCorte("true");
				atualizarLigacaoAguaActionForm.setHabilitaSupressao("true");

				atualizarLigacaoAguaActionForm
						.setNomeOrdemServico("Ordem de Serviço inexistente");
				atualizarLigacaoAguaActionForm.setIdOrdemServico("");
			}
		}

		this.consultaSelectObrigatorio(sessao);

		this.setaRequest(httpServletRequest, atualizarLigacaoAguaActionForm);

		return retorno;
	}

	private void consultaSelectObrigatorio(HttpSession sessao) {

		Fachada fachada = Fachada.getInstancia();

		// Filtro para o campo Diametro Ligação Água
		Collection colecaoDiametroLigacao = (Collection) sessao
				.getAttribute("colecaoDiametroLigacaoAgua");

		if (colecaoDiametroLigacao == null) {

			FiltroDiametroLigacao filtroDiametroLigacao = new FiltroDiametroLigacao();

			filtroDiametroLigacao.adicionarParametro(new ParametroSimples(
					FiltroDiametroLigacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDiametroLigacao
					.setCampoOrderBy(FiltroDiametroLigacao.DESCRICAO);

			colecaoDiametroLigacao = fachada.pesquisar(filtroDiametroLigacao,
					LigacaoAguaDiametro.class.getName());

			if (colecaoDiametroLigacao != null
					&& !colecaoDiametroLigacao.isEmpty()) {
				sessao.setAttribute("colecaoDiametroLigacaoAgua",
						colecaoDiametroLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Diametro da Ligação");
			}
		}

		// Filtro para o campo Material da Ligação
		Collection colecaoMaterialLigacao = (Collection) sessao
				.getAttribute("colecaoMaterialLigacao");

		if (colecaoMaterialLigacao == null) {

			FiltroMaterialLigacao filtroMaterialLigacao = new FiltroMaterialLigacao();
			filtroMaterialLigacao.adicionarParametro(new ParametroSimples(
					FiltroMaterialLigacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroMaterialLigacao
					.setCampoOrderBy(FiltroMaterialLigacao.DESCRICAO);

			colecaoMaterialLigacao = fachada.pesquisar(filtroMaterialLigacao,
					LigacaoAguaMaterial.class.getName());

			if (colecaoMaterialLigacao != null
					&& !colecaoMaterialLigacao.isEmpty()) {
				sessao.setAttribute("colecaoMaterialLigacao",
						colecaoMaterialLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Material da Ligação");
			}
		}

		// Filtro para o campo Perfil da Ligação
		Collection colecaoPerfilLigacao = (Collection) sessao
				.getAttribute("colecaoPerfilLigacao");

		if (colecaoPerfilLigacao == null) {

			FiltroPerfilLigacao filtroPerfilLigacao = new FiltroPerfilLigacao();
			filtroPerfilLigacao.adicionarParametro(new ParametroSimples(
					FiltroPerfilLigacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroPerfilLigacao.setCampoOrderBy(FiltroPerfilLigacao.DESCRICAO);

			colecaoPerfilLigacao = fachada.pesquisar(filtroPerfilLigacao,
					LigacaoAguaPerfil.class.getName());

			if (colecaoPerfilLigacao != null && !colecaoPerfilLigacao.isEmpty()) {
				sessao.setAttribute("colecaoPerfilLigacao",
						colecaoPerfilLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Material da Ligação");
			}
		}

		// Filtro para o campo Ramal local instalacao
		Collection colecaoRamalLocalInstalacao = (Collection) sessao
				.getAttribute("colecaoRamalLocalInstalacao");

		if (colecaoRamalLocalInstalacao == null) {

			FiltroRamalLocalInstalacao filtroRamalLocalInstalacao = new FiltroRamalLocalInstalacao();
			filtroRamalLocalInstalacao.adicionarParametro(new ParametroSimples(
					FiltroRamalLocalInstalacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroRamalLocalInstalacao
					.setCampoOrderBy(FiltroPerfilLigacao.DESCRICAO);

			colecaoRamalLocalInstalacao = fachada.pesquisar(
					filtroRamalLocalInstalacao, RamalLocalInstalacao.class
							.getName());

			if (colecaoRamalLocalInstalacao != null
					&& !colecaoRamalLocalInstalacao.isEmpty()) {
				sessao.setAttribute("colecaoRamalLocalInstalacao",
						colecaoRamalLocalInstalacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Local de Instalação do Ramal");
			}
		}

		// Filtro para o campo Ligacao origem
		Collection colecaoLigacaoOrigem = (Collection) sessao
				.getAttribute("colecaoLigacaoOrigem");

		if (colecaoLigacaoOrigem == null) {

			FiltroLigacaoOrigem filtroLigacaoOrigem = new FiltroLigacaoOrigem();

			filtroLigacaoOrigem.adicionarParametro(new ParametroSimples(
					FiltroLigacaoOrigem.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoOrigem.setCampoOrderBy(FiltroLigacaoOrigem.DESCRICAO);

			colecaoLigacaoOrigem = fachada.pesquisar(filtroLigacaoOrigem,
					LigacaoOrigem.class.getName());

			if (colecaoLigacaoOrigem != null && !colecaoLigacaoOrigem.isEmpty()) {
				sessao.setAttribute("colecaoLigacaoOrigem",
						colecaoLigacaoOrigem);
			} else {
				sessao.setAttribute("colecaoLigacaoOrigem", new ArrayList());
			}
		}

		// Filtro para o campo Motivo Corte
		Collection colecaoMotivoCorte = (Collection) sessao
				.getAttribute("colecaoMotivoCorte");

		if (colecaoMotivoCorte == null) {

			FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
			filtroMotivoCorte.adicionarParametro(new ParametroSimples(
					FiltroMotivoCorte.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroMotivoCorte.setCampoOrderBy(FiltroMotivoCorte.DESCRICAO);

			colecaoMotivoCorte = fachada.pesquisar(filtroMotivoCorte,
					MotivoCorte.class.getName());

			if (colecaoMotivoCorte != null && !colecaoMotivoCorte.isEmpty()) {
				sessao.setAttribute("colecaoMotivoCorte", colecaoMotivoCorte);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo Corte");
			}
		}

		// Filtro para o campo Tipo Corte
		Collection colecaoTipoCorte = (Collection) sessao
				.getAttribute("colecaoTipoCorte");

		if (colecaoTipoCorte == null) {

			FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();
			filtroCorteTipo.adicionarParametro(new ParametroSimples(
					FiltroCorteTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroCorteTipo.setCampoOrderBy(FiltroCorteTipo.DESCRICAO);

			colecaoTipoCorte = fachada.pesquisar(filtroCorteTipo,
					CorteTipo.class.getName());

			if (colecaoTipoCorte != null && !colecaoTipoCorte.isEmpty()) {
				sessao.setAttribute("colecaoTipoCorte", colecaoTipoCorte);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Tipo Corte");
			}
		}

		// Filtro para o campo Supressao Motivo
		Collection colecaoSupressaoMotivo = (Collection) sessao
				.getAttribute("colecaoSupressaoMotivo");

		if (colecaoSupressaoMotivo == null) {

			FiltroSupressaoMotivo filtroSupressaoMotivo = new FiltroSupressaoMotivo();
			filtroSupressaoMotivo.adicionarParametro(new ParametroSimples(
					FiltroSupressaoMotivo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroSupressaoMotivo
					.setCampoOrderBy(FiltroSupressaoMotivo.DESCRICAO);

			colecaoSupressaoMotivo = fachada.pesquisar(filtroSupressaoMotivo,
					SupressaoMotivo.class.getName());

			if (colecaoSupressaoMotivo != null
					&& !colecaoSupressaoMotivo.isEmpty()) {
				sessao.setAttribute("colecaoSupressaoMotivo",
						colecaoSupressaoMotivo);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo Supressao");
			}
		}
		
		
//		 Filtro para o campo Supressao Motivo
		Collection colecaoSupressaoTipo = (Collection) sessao
				.getAttribute("colecaoSupressaoTipo");

		if (colecaoSupressaoTipo == null) {

			FiltroSupressaoTipo filtroSupressaoTipo = new FiltroSupressaoTipo();
			filtroSupressaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSupressaoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroSupressaoTipo
					.setCampoOrderBy(FiltroSupressaoTipo.DESCRICAO);

			colecaoSupressaoTipo = fachada.pesquisar(filtroSupressaoTipo,
					SupressaoTipo.class.getName());

			if (colecaoSupressaoTipo != null
					&& !colecaoSupressaoMotivo.isEmpty()) {
				sessao.setAttribute("colecaoSupressaoTipo",
						colecaoSupressaoTipo);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Tipo Supressao");
			}
		}


	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 25/08/2006
	 */
	private void pesquisarCliente(
			AtualizarLigacaoAguaActionForm atualizarLigacaoAguaActionForm) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, atualizarLigacaoAguaActionForm
						.getMatriculaImovel()));

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
			atualizarLigacaoAguaActionForm.setClienteUsuario(cliente.getNome());
			atualizarLigacaoAguaActionForm.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	/**
	 * Seta os request com os id encontrados
	 * 
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			AtualizarLigacaoAguaActionForm atualizarLigacaoAguaActionForm) {

		// Imovel
		if (atualizarLigacaoAguaActionForm.getMatriculaImovel() != null
				&& !atualizarLigacaoAguaActionForm.getMatriculaImovel().equals(
						"")
				&& atualizarLigacaoAguaActionForm.getInscricaoImovel() != null
				&& !atualizarLigacaoAguaActionForm.getInscricaoImovel().equals(
						"")) {

			httpServletRequest.setAttribute("numeroOsEncontrada", "true");
		}
	}

	/**
	 * Este metodo seta os dados da ligacao de agua no formulario
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 04/09/2008
	 * 
	 * 
	 * @param atualizarLigacaoAguaActionForm
	 * @param ligacaoAgua
	 * @param imovel
	 * @param habilitaCorte
	 * @param habilitaSupressao
	 * @param httpServletRequest
	 * @param sessao
	 * @param fachada
	 */
	private void setarDadosLigacaoAgua(
			AtualizarLigacaoAguaActionForm atualizarLigacaoAguaActionForm,
			LigacaoAgua ligacaoAgua, Imovel imovel, boolean habilitaCorte,
			boolean habilitaSupressao, HttpServletRequest httpServletRequest,
			HttpSession sessao, Fachada fachada) {

		// Data de Ligacao
		String dataLigacao = Util.formatarData(ligacaoAgua.getDataLigacao());
		atualizarLigacaoAguaActionForm.setDataLigacao(dataLigacao);

		// Diametro da Ligação
		String diametroLigacao = ""
				+ ligacaoAgua.getLigacaoAguaDiametro().getId();
		atualizarLigacaoAguaActionForm.setDiametroLigacao(diametroLigacao);

		// Material da Ligação
		String materialLigacao = ""
				+ ligacaoAgua.getLigacaoAguaMaterial().getId();
		atualizarLigacaoAguaActionForm.setMaterialLigacao(materialLigacao);

		// Perfil da Ligação
		if (ligacaoAgua.getLigacaoAguaPerfil() != null) {
			String perfilLigacao = ""
					+ ligacaoAgua.getLigacaoAguaPerfil().getId();
			atualizarLigacaoAguaActionForm.setPerfilLigacao(perfilLigacao);
		}
		if (ligacaoAgua.getLigacaoOrigem() != null) {
			atualizarLigacaoAguaActionForm.setIdLigacaoOrigem(ligacaoAgua
					.getLigacaoOrigem().getId().toString());
		}

		// Local de Instalação do Ramal
		if (ligacaoAgua.getRamalLocalInstalacao() != null) {
			String ramal = "" + ligacaoAgua.getRamalLocalInstalacao().getId();
			atualizarLigacaoAguaActionForm.setRamalLocalInstalacao(ramal);
		}

		// Tipo Corte
		if (ligacaoAgua.getCorteTipo() != null) {
			String tipoCorte = "" + ligacaoAgua.getCorteTipo().getId();
			atualizarLigacaoAguaActionForm.setTipoCorte(tipoCorte);
		}

		// Motivo Corte
		if (ligacaoAgua.getMotivoCorte() != null) {
			String motivoCorte = "" + ligacaoAgua.getMotivoCorte().getId();
			atualizarLigacaoAguaActionForm.setMotivoCorte(motivoCorte);
		}

		// Selo Corte
		if (ligacaoAgua.getNumeroSeloCorte() != null) {
			String seloCorte = "" + ligacaoAgua.getNumeroSeloCorte();
			atualizarLigacaoAguaActionForm.setNumSeloCorte(seloCorte);
		}

		// Motivo Supressao
		if (ligacaoAgua.getSupressaoMotivo() != null) {
			String supressaoMotivo = ""
					+ ligacaoAgua.getSupressaoMotivo().getId();
			atualizarLigacaoAguaActionForm.setMotivoSupressao(supressaoMotivo);
		}

		// Tipo Supressao
		if (ligacaoAgua.getSupressaoTipo() != null) {
			String tipoSupressao = "" + ligacaoAgua.getSupressaoTipo().getId();
			atualizarLigacaoAguaActionForm.setTipoSupressao(tipoSupressao);
		}

		// Selo Supressao
		if (ligacaoAgua.getNumeroSeloSupressao() != null) {
			String seloSupressao = "" + ligacaoAgua.getNumeroSeloSupressao();
			atualizarLigacaoAguaActionForm.setNumSeloSupressao(seloSupressao);
		}

		// Lacre
		if (ligacaoAgua.getNumeroLacre() != null) {
			atualizarLigacaoAguaActionForm.setAceitaLacre(""
					+ ConstantesSistema.INDICADOR_USO_ATIVO);
			atualizarLigacaoAguaActionForm.setNumeroLacre(ligacaoAgua
					.getNumeroLacre());

		} else {
			atualizarLigacaoAguaActionForm.setAceitaLacre(""
					+ ConstantesSistema.INDICADOR_USO_DESATIVO);
			atualizarLigacaoAguaActionForm.setNumeroLacre("");
		}

		// Cliente
		this.pesquisarCliente(atualizarLigacaoAguaActionForm);

		if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.CORTADO
				.intValue()) {
			habilitaCorte = false;
		}

		if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.SUPRIMIDO
				.intValue()
				&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.SUPR_PARC
						.intValue()) {

			habilitaSupressao = false;
		}

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = imovel
				.getLigacaoAgua().getHidrometroInstalacaoHistorico();

		if (hidrometroInstalacaoHistorico != null) {
			atualizarLigacaoAguaActionForm.setLeituraCorte(Util
					.converterObjetoParaString(hidrometroInstalacaoHistorico
							.getNumeroLeituraCorte()));

			atualizarLigacaoAguaActionForm.setLeituraSupressao(Util
					.converterObjetoParaString(hidrometroInstalacaoHistorico
							.getNumeroLeituraSupressao()));
		}

		httpServletRequest.setAttribute("hidrometroInstalacaoHistorico",
				hidrometroInstalacaoHistorico);

		atualizarLigacaoAguaActionForm.setHabilitaCorte("" + habilitaCorte);
		atualizarLigacaoAguaActionForm.setHabilitaSupressao(""
				+ habilitaSupressao);

		// Filtro para o campo Supressao Tipo
		if (habilitaSupressao) {

			FiltroSupressaoTipo filtroSupressaoTipo = new FiltroSupressaoTipo();

			String indicador = null;

			if (imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPRIMIDO
					.intValue()) {
				indicador = FiltroSupressaoTipo.INDICADOR_TOTAL;
			} else if (imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPR_PARC
					.intValue()) {
				indicador = FiltroSupressaoTipo.INDICADOR_PARCIAL;
			}

			filtroSupressaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSupressaoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroSupressaoTipo.adicionarParametro(new ParametroSimples(
					indicador, ConstantesSistema.SIM));
			filtroSupressaoTipo.setCampoOrderBy(FiltroSupressaoTipo.DESCRICAO);

			Collection colecaoSupressaoTipo = fachada.pesquisar(
					filtroSupressaoTipo, SupressaoTipo.class.getName());

			if (colecaoSupressaoTipo != null && !colecaoSupressaoTipo.isEmpty()) {
				sessao.setAttribute("colecaoSupressaoTipo",
						colecaoSupressaoTipo);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo Supressao");
			}
		}// habilitaSupressao
	}

}
