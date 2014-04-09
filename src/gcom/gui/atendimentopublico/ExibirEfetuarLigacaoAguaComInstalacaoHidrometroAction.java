package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.FiltroLigacaoOrigem;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroMaterialLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroPerfilLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroRamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class ExibirEfetuarLigacaoAguaComInstalacaoHidrometroAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		// -----------------------------------------------------------
		// Verificar permissão especial
		boolean temPermissaoMotivoNaoCobranca = fachada
				.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
		// -----------------------------------------------------------

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("efetuarLigacaoAguaComInstalacaoHidrometro");

		EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm = (EfetuarLigacaoAguaComInstalacaoHidrometroActionForm) actionForm;

		if (httpServletRequest.getParameter("desfazer") != null) {
			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setAceitaLacre(null);
			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setSituacaoCavalete(null);
		}

		Boolean veioEncerrarOS = null;

		if (httpServletRequest.getAttribute("veioEncerrarOS") != null
				|| (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getVeioEncerrarOS() != null && efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getVeioEncerrarOS().equals("true"))) {

			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.getVeioEncerrarOS() != null
					&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.getVeioEncerrarOS().equals("")) {
				if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getVeioEncerrarOS().toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		this.consultaSelectObrigatorio(sessao);

		// Variavel responsavél pelo preenchimento do imovel no formulário
		String idOrdemServico = null;

		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdOrdemServico() != null) {
			idOrdemServico = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.getIdOrdemServico();
		} else {
			idOrdemServico = (String) httpServletRequest
					.getAttribute("veioEncerrarOS");

			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setDataLigacao((String) httpServletRequest
							.getAttribute("dataEncerramento"));

			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setDataInstalacao((String) httpServletRequest
							.getAttribute("dataEncerramento"));

			sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest
							.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			sessao.setAttribute("semMenu", "SIM");
		} else {
			sessao.removeAttribute("semMenu");
		}
		OrdemServico ordemServico = null;
		String matriculaImovel = null;
		Imovel imovelComLocalidade = null;

		// Permissao Especial Efetuar Ligacao de Agua com Instalacao de
		// hidrometro sem RA

		boolean efetuarLigacaoAguaComInstalacaodeHidrometroSemRA = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.EFETUAR_LIGACAO_DE_AGUA_COM_INSTALACAO_DE_HIDROMETRO_SEM_RA,
						usuarioLogado);
		
		efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.setPermissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA("false");

		if (!veioEncerrarOS) {
			httpServletRequest.setAttribute(
					"efetuarLigacaoAguaComInstalacaodeHidrometroSemRA",
					efetuarLigacaoAguaComInstalacaodeHidrometroSemRA);

			if (efetuarLigacaoAguaComInstalacaodeHidrometroSemRA) {
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setPermissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA("true");
			}
		}
		String idImovel = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdImovel();

		if (idImovel != null && !idImovel.trim().equals("")) {

			// Pesquisa o imovel na base
			String inscricaoImovelEncontrado = fachada
					.pesquisarInscricaoImovel(new Integer(idImovel));

			if (inscricaoImovelEncontrado != null
					&& !inscricaoImovelEncontrado.equalsIgnoreCase("")) {

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setMatriculaImovel(idImovel);

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setInscricaoImovel(inscricaoImovelEncontrado);

				Imovel imovel = (Imovel) fachada
						.pesquisarDadosImovel(new Integer(idImovel));

				// [FS0002] Validar Situação de Água do Imóvel.
				if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.POTENCIAL
						.intValue()
						&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.FACTIVEL
								.intValue()
						&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.EM_FISCALIZACAO
								.intValue()) {

					throw new ActionServletException(
							"atencao.situacao_validar_ligacao_agua_invalida_exibir",
							null, imovel.getLigacaoAguaSituacao()
									.getDescricao());
				}

				/*
				 * [FS0007] Verificar Situação Rede de Água na Quadra
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

				sessao.setAttribute("imovel", imovel);

				if (imovel != null) {

					/*
					 * FiltroImovel filtroImovel = new FiltroImovel();
					 * filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
					 * filtroImovel.adicionarParametro(new
					 * ParametroSimples(FiltroImovel.ID, imovel.getId()));
					 * 
					 * Collection colecaoImoveis =
					 * fachada.pesquisar(filtroImovel, Imovel.class.getName());
					 * 
					 * imovelComLocalidade = (Imovel)
					 * Util.retonarObjetoDeColecao(colecaoImoveis);
					 */

					if (imovel.getLocalidade().getHidrometroLocalArmazenagem() != null) {
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm
								.setIdLocalArmazenagem(imovel.getLocalidade()
										.getHidrometroLocalArmazenagem()
										.getId().toString());
					}

					// Matricula Imóvel
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setMatriculaImovel(imovel.getId().toString());

					// Situação da Ligação de Agua
					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this
							.pesquisarCliente(
									efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
									new Integer(idImovel));
				}

				httpServletRequest.setAttribute("habilitaDataLigacao", true);

			} else {

				httpServletRequest.setAttribute("corImovel", "exception");

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}

		}

		// [FS0001] Validar Ordem de Serviço.
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setVeioEncerrarOS("" + veioEncerrarOS);

			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {

				fachada.validarLigacaoAguaComInstalacaoHidrometroExibir(
						ordemServico, veioEncerrarOS);

				sessao.setAttribute("ordemServico", ordemServico);

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setIdOrdemServico(idOrdemServico);
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setNomeOrdemServico(ordemServico.getServicoTipo()
								.getDescricao());

				Imovel imovel = ordemServico.getRegistroAtendimento()
						.getImovel();

				ServicoTipo servicoTipo = ordemServico.getServicoTipo();

				BigDecimal valorDebito = new BigDecimal(0.00);

				if (servicoTipo != null && servicoTipo.getDebitoTipo() != null) {

					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setIdTipoDebito(servicoTipo.getDebitoTipo()
									.getId().toString());

					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setDescricaoTipoDebito(servicoTipo.getDebitoTipo()
									.getDescricao());

					// [FS0013] - Alteração de Valor
					this
							.permitirAlteracaoValor(ordemServico
									.getServicoTipo(),
									efetuarLigacaoAguaComInstalacaoHidrometroActionForm);

					// Colocado por Raphael Rossiter em 04/05/2007 (Analista:
					// Rosana Carvalho)
					valorDebito = this
							.calcularValores(httpServletRequest, ordemServico,
									efetuarLigacaoAguaComInstalacaoHidrometroActionForm);

					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm
								.setMotivoNaoCobranca(ordemServico
										.getServicoNaoCobrancaMotivo().getId()
										.toString());
					}

					if (ordemServico.getPercentualCobranca() != null) {
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm
								.setPercentualCobranca(ordemServico
										.getPercentualCobranca().toString());
					}
				}

				if (ordemServico.getDataEncerramento() != null) {
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setDataLigacao(Util.formatarData(ordemServico
									.getDataEncerramento()));
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setDataInstalacao(Util.formatarData(ordemServico
									.getDataEncerramento()));
				}

				sessao.setAttribute("imovel", imovel);

				matriculaImovel = imovel.getId().toString();
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setMatriculaImovel("" + matriculaImovel);

				/*-------------- Início dados do Imóvel---------------*/

				sessao.setAttribute("imovel", ordemServico
						.getRegistroAtendimento().getImovel());

				if (imovel != null) {

					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
					filtroImovel.adicionarParametro(new ParametroSimples(
							FiltroImovel.ID, imovel.getId()));

					Collection colecaoImoveis = fachada.pesquisar(filtroImovel,
							Imovel.class.getName());

					imovelComLocalidade = (Imovel) Util
							.retonarObjetoDeColecao(colecaoImoveis);

					if (imovelComLocalidade.getLocalidade()
							.getHidrometroLocalArmazenagem() != null) {
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm
								.setIdLocalArmazenagem(imovelComLocalidade
										.getLocalidade()
										.getHidrometroLocalArmazenagem()
										.getId().toString());
					}

					// Matricula Imóvel
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setMatriculaImovel(imovel.getId().toString());

					// Inscrição Imóvel
					String inscricaoImovel = fachada
							.pesquisarInscricaoImovel(imovel.getId());
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setInscricaoImovel(inscricaoImovel);

					// Situação da Ligação de Agua
					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this
							.pesquisarCliente(
									efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
									new Integer(matriculaImovel));
				}
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute(
							"permissaoMotivoNaoCobranca",
							temPermissaoMotivoNaoCobranca);
				} else {
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setPercentualCobranca("100");
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setQuantidadeParcelas("1");
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setValorParcelas(Util.formataBigDecimal(
									valorDebito, 2, true));
				}

			} else {
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setNomeOrdemServico("Ordem de Serviço inexistente");
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setIdOrdemServico("");
				httpServletRequest.setAttribute("OrdemServioInexistente", true);
			}
		} /*
			 * else {
			 * 
			 * httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
			 * 
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setIdOrdemServico("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setMatriculaImovel("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setInscricaoImovel("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setClienteUsuario("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setCpfCnpjCliente("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setSituacaoLigacaoAgua("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setSituacaoLigacaoEsgoto("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setDataLigacao("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setDiametroLigacao("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setMaterialLigacao("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setPerfilLigacao("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setIdTipoDebito("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setDescricaoTipoDebito("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setQuantidadeParcelas("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setValorParcelas("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setIdLocalArmazenagem("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setPercentualCobranca("-1");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setMotivoNaoCobranca("-1");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setAceitaLacre("2");
			 *  }
			 */

		String numeroHidrometro = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroHidrometro();

		// Verificar se o número do hidrômetro não está cadastrado

		if (numeroHidrometro != null && !numeroHidrometro.trim().equals("")) {

			// Pesquisa o hidrômetro
			Hidrometro hidrometro = fachada
					.pesquisarHidrometroPeloNumero(numeroHidrometro);

			if (hidrometro == null) {
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setNumeroHidrometro("");
				throw new ActionServletException(
						"atencao.hidrometro_inexistente");
			} else {

				if ( imovelComLocalidade != null
					 && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null
					 && hidrometro.getHidrometroLocalArmazenagem() != null
					 && !hidrometro.getHidrometroLocalArmazenagem()
								.getId()
								.equals(
										imovelComLocalidade
												.getLocalidade()
												.getHidrometroLocalArmazenagem()
												.getId())) {
					throw new ActionServletException(
							"atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
				}

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setNumeroHidrometro(hidrometro.getNumero());

				if (ordemServico != null) {
					// Alterado por Sávio Data:26/12/2007
					// Quando era calculado o valor com o valor do debito
					// alterado, então não pesquisa o valor
					// do debito novamente na base.
					if (httpServletRequest.getParameter("calculaValores") == null
							|| (httpServletRequest
									.getParameter("calculaValores") != null && !httpServletRequest
									.getParameter("calculaValores").equals("S"))) {

						// Valor Débito
						BigDecimal valorDebito = fachada.obterValorDebito(
								ordemServico.getServicoTipo().getId(),
								new Integer(matriculaImovel), hidrometro
										.getHidrometroCapacidade());

						if (valorDebito != null) {
							efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.setValorDebito(Util.formataBigDecimal(
											valorDebito, 2, true));
						} else {
							efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.setValorDebito("0");
						}

						if (temPermissaoMotivoNaoCobranca) {
							httpServletRequest.setAttribute(
									"permissaoMotivoNaoCobranca",
									temPermissaoMotivoNaoCobranca);
						} else {
							efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.setPercentualCobranca("100");
							efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.setQuantidadeParcelas("1");
							efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.setValorParcelas(Util.formataBigDecimal(
											valorDebito, 2, true));
						}
					}
				}

			}
		}

		return retorno;
	}

	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo,
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm form) {

		if (servicoTipo.getIndicadorPermiteAlterarValor() == ConstantesSistema.INDICADOR_USO_ATIVO
				.shortValue()) {

			form.setAlteracaoValor("OK");
		} else {
			form.setAlteracaoValor("");
		}

	}

	/*
	 * Calcular valor da prestação com juros
	 * 
	 * return: Retorna o valor total do débito
	 * 
	 * autor: Raphael Rossiter data: 04/05/2007
	 */
	private BigDecimal calcularValores(HttpServletRequest httpServletRequest,
			OrdemServico ordemServico,
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm form) {

		String calculaValores = httpServletRequest
				.getParameter("calculaValores");

		BigDecimal valorDebito = new BigDecimal(0);
		SistemaParametro sistemaParametro = this.getFachada()
				.pesquisarParametrosDoSistema();
		Integer qtdeParcelas = null;

		if (calculaValores != null && calculaValores.equals("S")) {

			// [UC0186] - Calcular Prestação
			BigDecimal taxaJurosFinanciamento = null;
			qtdeParcelas = new Integer(form.getQuantidadeParcelas());

			if (ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM
					.shortValue()
					&& qtdeParcelas.intValue() > 1) {

				taxaJurosFinanciamento = sistemaParametro
						.getPercentualTaxaJurosFinanciamento();
			} else {
				taxaJurosFinanciamento = new BigDecimal(0);
			}

			BigDecimal valorPrestacao = null;
			if (taxaJurosFinanciamento != null) {

				valorDebito = new BigDecimal(form.getValorDebito().replace(",",
						"."));

				String percentualCobranca = form.getPercentualCobranca();

				if (percentualCobranca.equals("70")) {
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				} else if (percentualCobranca.equals("50")) {
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}

				valorPrestacao = this.getFachada().calcularPrestacao(
						taxaJurosFinanciamento, qtdeParcelas, valorDebito,
						new BigDecimal("0.00"));

				valorPrestacao.setScale(2, BigDecimal.ROUND_HALF_UP);
			}

			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(
						valorPrestacao, 2, true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}

		} else {

			if ( form.getNumeroHidrometro() != null && !form.getNumeroHidrometro().equals("") ) {
	
				HidrometroCapacidade hidrometroCapacidade = 
					Fachada.getInstancia().pesquisarCapacidadeHidrometro(form.getNumeroHidrometro());
			
				valorDebito = Fachada.getInstancia().obterValorDebito(
						ordemServico.getServicoTipo().getId(),
						ordemServico.getRegistroAtendimento().getImovel().getId(),
						hidrometroCapacidade);
			} else {
				valorDebito = Fachada.getInstancia().obterValorDebito(
						ordemServico.getServicoTipo().getId(),
						ordemServico.getRegistroAtendimento().getImovel().getId(),
						new Short(LigacaoTipo.LIGACAO_AGUA + ""));
			}

			form.setValorDebito(Util.formataBigDecimal(valorDebito, 2, true));
		}

		return valorDebito;
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 27/11/2006
	 */
	private void pesquisarCliente(
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
			Integer matriculaImovel) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

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
			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setClienteUsuario(cliente.getNome());
			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	/**
	 * Monta os select´s obrigatorios
	 * 
	 * @author Rafael Corrêa
	 * @date 27/11/2006
	 */
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
				sessao.setAttribute("colecaoDiametroLigacao",
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

		// Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = fachada.pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da Não Cobrança");
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

		// Pesquisando local de instalação
		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();
		filtroHidrometroLocalInstalacao
				.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);
		filtroHidrometroLocalInstalacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroLocalInstalacao.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoHidrometroLocalInstalacao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroLocalInstalacao,
						HidrometroLocalInstalacao.class.getName());

		sessao.setAttribute("colecaoHidrometroLocalInstalacao",
				colecaoHidrometroLocalInstalacao);

		// Pesquisando proteção
		FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();
		filtroHidrometroProtecao
				.setCampoOrderBy(FiltroHidrometroProtecao.DESCRICAO);
		filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(
				FiltroHidrometroProtecao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoHidrometroProtecao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroProtecao,
						HidrometroProtecao.class.getName());

		sessao.setAttribute("colecaoHidrometroProtecao",
				colecaoHidrometroProtecao);

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

	}

}
