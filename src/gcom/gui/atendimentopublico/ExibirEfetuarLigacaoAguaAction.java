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
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
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
public class ExibirEfetuarLigacaoAguaAction extends GcomAction {
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("efetuarLigacaoAgua");

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		EfetuarLigacaoAguaActionForm ligacaoAguaActionForm = (EfetuarLigacaoAguaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		this.consultaSelectObrigatorio(sessao);

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (ligacaoAguaActionForm.getVeioEncerrarOS() != null
					&& !ligacaoAguaActionForm.getVeioEncerrarOS().equals("")) {
				if (ligacaoAguaActionForm.getVeioEncerrarOS().toLowerCase()
						.equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		ligacaoAguaActionForm.setVeioEncerrarOS("" + veioEncerrarOS);

		// Variavel responsavél pelo preenchimento do imovel no formulário
		String idOrdemServico = null;

		if (ligacaoAguaActionForm.getIdOrdemServico() != null) {
			idOrdemServico = ligacaoAguaActionForm.getIdOrdemServico();
		} else {
			idOrdemServico = (String) httpServletRequest
					.getAttribute("veioEncerrarOS");
			ligacaoAguaActionForm.setDataLigacao((String) httpServletRequest
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
		
		
//		 Permissao Especial Efetuar Ligacao de Agua sem RA

		boolean efetuarLigacaoAguaSemRA = Fachada.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.EFETUAR_LIGACAO_DE_AGUA_SEM_RA,
						usuarioLogado);

		ligacaoAguaActionForm.setPermissaoAlterarOSsemRA("false");

		if (!veioEncerrarOS) {
			
			httpServletRequest.setAttribute(
					"efetuarLigacaoAguaSemRA",
					efetuarLigacaoAguaSemRA);

			if (efetuarLigacaoAguaSemRA) {
				ligacaoAguaActionForm.setPermissaoAlterarOSsemRA("true");
			}
		}
		

		String idImovel = ligacaoAguaActionForm.getIdImovel();

		if (idImovel != null && !idImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			String inscricaoImovelEncontrado = fachada
					.pesquisarInscricaoImovel(new Integer(idImovel));

			if (inscricaoImovelEncontrado != null
					&& !inscricaoImovelEncontrado.equalsIgnoreCase("")) {

				ligacaoAguaActionForm.setMatriculaImovel(idImovel);

				ligacaoAguaActionForm
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
							null, imovel.getLigacaoAguaSituacao().getDescricao());
				}

				/*
				 * [FS0007] Verificar Situação Rede de Água na Quadra
				 * 
				 * Integração com o conceito de face da quadra
				 * Raphael Rossiter em 21/05/2009
				 */
				IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
				
				if ((integracao.getIndicadorRedeAgua()).equals(Quadra.SEM_REDE)) {
					
					throw new ActionServletException("atencao.seituacao_rede_agua_quadra", 
					null, imovel.getId() + "");
				}

				// [FS0006] Verificar Situação do Imovel
				if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
					throw new ActionServletException(
							"atencao.situacao_imovel_indicador_exclusao", null, imovel
									.getId()
									+ "");
				}
				

				ligacaoAguaActionForm.setSituacaoLigacaoAgua(imovel
						.getLigacaoAguaSituacao().getDescricao());

				ligacaoAguaActionForm.setSituacaoLigacaoEsgoto(imovel
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

					ligacaoAguaActionForm.setClienteUsuario(clienteImovel
							.getCliente().getNome());

					if (clienteImovel.getCliente().getClienteTipo().getId()
							.intValue() == ClienteTipo.INDICADOR_PESSOA_FISICA
							.intValue()) {

						ligacaoAguaActionForm.setCpfCnpjCliente(clienteImovel
								.getCliente().getCpfFormatado());

					} else if (clienteImovel.getCliente().getClienteTipo()
							.getId().intValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA
							.intValue()) {
						ligacaoAguaActionForm.setCpfCnpjCliente(clienteImovel
								.getCliente().getCnpjFormatado());
					} else {
						ligacaoAguaActionForm.setCpfCnpjCliente("");
					}

				}

				httpServletRequest.setAttribute("habilitaDataLigacao", true);

			} else {

				httpServletRequest.setAttribute("corImovel", "exception");

				ligacaoAguaActionForm
						.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}

		} 
		// [FS0001] Validar Ordem de Serviço.
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			OrdemServico ordemServico = fachada.recuperaOSPorId(new Integer(
					idOrdemServico));

			if (ordemServico != null) {

				BigDecimal valorDebito = new BigDecimal(0);

				fachada.validarLigacaoAguaExibir(ordemServico, veioEncerrarOS);

				sessao.setAttribute("ordemServico", ordemServico);

				ligacaoAguaActionForm.setIdOrdemServico(idOrdemServico);
				ligacaoAguaActionForm.setNomeOrdemServico(ordemServico
						.getServicoTipo().getDescricao());
				
				Imovel imovel = ordemServico.getImovel();
				ServicoTipo servicoTipo = ordemServico.getServicoTipo();

				if (servicoTipo != null && servicoTipo.getDebitoTipo() != null) {

					ligacaoAguaActionForm.setIdTipoDebito(servicoTipo
							.getDebitoTipo().getId().toString());
					ligacaoAguaActionForm.setDescricaoTipoDebito(servicoTipo
							.getDebitoTipo().getDescricao());

					// [FS0013] - Alteração de Valor
					this.permitirAlteracaoValor(servicoTipo,
							ligacaoAguaActionForm);

					String calculaValores = httpServletRequest
							.getParameter("calculaValores");

					SistemaParametro sistemaParametro = this.getFachada()
							.pesquisarParametrosDoSistema();
					Integer qtdeParcelas = null;

					if (calculaValores != null && calculaValores.equals("S")) {

						// [UC0186] - Calcular Prestação
						BigDecimal taxaJurosFinanciamento = null;
						qtdeParcelas = new Integer(ligacaoAguaActionForm
								.getQuantidadeParcelas());

						if (ordemServico.getServicoTipo()
								.getIndicadorCobrarJuros() == ConstantesSistema.SIM
								.shortValue()
								&& qtdeParcelas.intValue() != 1) {

							taxaJurosFinanciamento = sistemaParametro
									.getPercentualTaxaJurosFinanciamento();
						} else {
							taxaJurosFinanciamento = new BigDecimal(0);
						}

						BigDecimal valorPrestacao = null;
						if (taxaJurosFinanciamento != null) {

							valorDebito = new BigDecimal(ligacaoAguaActionForm
									.getValorDebito().replace(",", "."));

							String percentualCobranca = ligacaoAguaActionForm
									.getPercentualCobranca();

							if (percentualCobranca.equals("70")) {
								valorDebito = valorDebito
										.multiply(new BigDecimal(0.7));
							} else if (percentualCobranca.equals("50")) {
								valorDebito = valorDebito
										.multiply(new BigDecimal(0.5));
							}

							valorPrestacao = this.getFachada()
									.calcularPrestacao(taxaJurosFinanciamento,
											qtdeParcelas, valorDebito,
											new BigDecimal("0.00"));

							valorPrestacao
									.setScale(2, BigDecimal.ROUND_HALF_UP);
						}

						if (valorPrestacao != null) {
							String valorPrestacaoComVirgula = Util
									.formataBigDecimal(valorPrestacao, 2, true);
							ligacaoAguaActionForm
									.setValorParcelas(valorPrestacaoComVirgula);
						} else {
							ligacaoAguaActionForm.setValorParcelas("0,00");
						}

					} else {

						valorDebito = fachada.obterValorDebito(servicoTipo
								.getId(), imovel.getId(), new Short(
								LigacaoTipo.LIGACAO_AGUA + ""));

						ligacaoAguaActionForm.setValorDebito(Util
								.formataBigDecimal(valorDebito, 2, true));

					}

					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						ligacaoAguaActionForm.setMotivoNaoCobranca(ordemServico
								.getServicoNaoCobrancaMotivo().getId()
								.toString());
					}

					if (ordemServico.getPercentualCobranca() != null) {
						ligacaoAguaActionForm
								.setPercentualCobranca(ordemServico
										.getPercentualCobranca().toString());
					}
				}

				if (ordemServico.getDataEncerramento() != null) {
					ligacaoAguaActionForm.setDataLigacao(Util
							.formatarData(ordemServico.getDataEncerramento()));
				}

				sessao.setAttribute("imovel", imovel);

				String matriculaImovel = imovel.getId().toString();
				ligacaoAguaActionForm.setMatriculaImovel("" + matriculaImovel);

				/*-------------- Início dados do Imóvel---------------*/

				sessao.setAttribute("imovel", ordemServico.getImovel());

				if (imovel != null) {

					// Matricula Imóvel
					ligacaoAguaActionForm.setMatriculaImovel(imovel.getId()
							.toString());

					// Inscrição Imóvel
					String inscricaoImovel = fachada
							.pesquisarInscricaoImovel(imovel.getId());
					ligacaoAguaActionForm.setInscricaoImovel(inscricaoImovel);

					// Situação da Ligação de Agua
					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					ligacaoAguaActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					ligacaoAguaActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this.pesquisarCliente(ligacaoAguaActionForm, new Integer(
							matriculaImovel));
				}

				this.consultaSelectObrigatorio(sessao);

				// -----------------------------------------------------------
				// Verificar permissão especial
				boolean temPermissaoMotivoNaoCobranca = fachada
						.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------

				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute(
							"permissaoMotivoNaoCobranca",
							temPermissaoMotivoNaoCobranca);
				} else {
					ligacaoAguaActionForm.setPercentualCobranca("100");
					ligacaoAguaActionForm.setQuantidadeParcelas("1");
					ligacaoAguaActionForm.setValorParcelas(Util
							.formataBigDecimal(valorDebito, 2, true));
				}
			} else {
				ligacaoAguaActionForm
						.setNomeOrdemServico("Ordem de Serviço inexistente");
				ligacaoAguaActionForm.setIdOrdemServico("");
				httpServletRequest.setAttribute("OrdemServioInexistente", true);
			}
		}
		
		if(httpServletRequest.getParameter("limpar") !=null){
			if(httpServletRequest.getParameter("limpar").equals("os")){
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");

				ligacaoAguaActionForm.setIdOrdemServico("");
				ligacaoAguaActionForm.setMatriculaImovel("");
				ligacaoAguaActionForm.setInscricaoImovel("");
				ligacaoAguaActionForm.setClienteUsuario("");
				ligacaoAguaActionForm.setCpfCnpjCliente("");
				ligacaoAguaActionForm.setSituacaoLigacaoAgua("");
				ligacaoAguaActionForm.setSituacaoLigacaoEsgoto("");
				ligacaoAguaActionForm.setDataLigacao("");
				ligacaoAguaActionForm.setDiametroLigacao("");
				ligacaoAguaActionForm.setMaterialLigacao("");
				ligacaoAguaActionForm.setPerfilLigacao("");
				ligacaoAguaActionForm.setConsumoMinimo("");
				ligacaoAguaActionForm.setIdTipoDebito("");
				ligacaoAguaActionForm.setDescricaoTipoDebito("");
				ligacaoAguaActionForm.setQuantidadeParcelas("");
				ligacaoAguaActionForm.setValorParcelas("");
				ligacaoAguaActionForm.setPercentualCobranca("-1");
				ligacaoAguaActionForm.setMotivoNaoCobranca("-1");
				ligacaoAguaActionForm.setAceitaLacre("2");
				ligacaoAguaActionForm.setIdLigacaoOrigem("-1");
				ligacaoAguaActionForm.setProfundidadeRamal("");
				ligacaoAguaActionForm.setDistanciaInstalacaoRamal("");
				ligacaoAguaActionForm.setIdPavimentoCalcada("-1");
				ligacaoAguaActionForm.setIdPavimentoRua("-1");
				ligacaoAguaActionForm.setAlteracaoValor("");
			
			}else{
				
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
				
				ligacaoAguaActionForm.setIdOrdemServico("");
				ligacaoAguaActionForm.setIdImovel("");
				ligacaoAguaActionForm.setMatriculaImovel("");
				ligacaoAguaActionForm.setInscricaoImovel("");
				ligacaoAguaActionForm.setClienteUsuario("");
				ligacaoAguaActionForm.setCpfCnpjCliente("");
				ligacaoAguaActionForm.setSituacaoLigacaoAgua("");
				ligacaoAguaActionForm.setSituacaoLigacaoEsgoto("");
				ligacaoAguaActionForm.setDataLigacao("");
				ligacaoAguaActionForm.setDiametroLigacao("");
				ligacaoAguaActionForm.setMaterialLigacao("");
				ligacaoAguaActionForm.setPerfilLigacao("");
				ligacaoAguaActionForm.setConsumoMinimo("");
				ligacaoAguaActionForm.setIdTipoDebito("");
				ligacaoAguaActionForm.setDescricaoTipoDebito("");
				ligacaoAguaActionForm.setQuantidadeParcelas("");
				ligacaoAguaActionForm.setValorParcelas("");
				ligacaoAguaActionForm.setPercentualCobranca("-1");
				ligacaoAguaActionForm.setMotivoNaoCobranca("-1");
				ligacaoAguaActionForm.setAceitaLacre("2");
				ligacaoAguaActionForm.setIdLigacaoOrigem("-1");
				ligacaoAguaActionForm.setProfundidadeRamal("");
				ligacaoAguaActionForm.setDistanciaInstalacaoRamal("");
				ligacaoAguaActionForm.setIdPavimentoCalcada("-1");
				ligacaoAguaActionForm.setIdPavimentoRua("-1");
				ligacaoAguaActionForm.setAlteracaoValor("");
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
			EfetuarLigacaoAguaActionForm form) {

		if (servicoTipo.getIndicadorPermiteAlterarValor() == ConstantesSistema.INDICADOR_USO_ATIVO
				.shortValue()) {

			form.setAlteracaoValor("OK");
		} else {
			form.setAlteracaoValor("");
		}

	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void pesquisarCliente(
			EfetuarLigacaoAguaActionForm ligacaoAguaActionForm,
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
			ligacaoAguaActionForm.setClienteUsuario(cliente.getNome());
			ligacaoAguaActionForm.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	/**
	 * Monta os select´s obrigatorios
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
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
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,
						"Tabela Ligacao Agua Perfil");
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
				sessao.setAttribute("colecaoLigacaoOrigem",
						new ArrayList());
			}
		}

	}

}
