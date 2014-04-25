package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.FiltroLigacaoOrigem;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroDiametroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoMaterialEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
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
public class ExibirEfetuarLigacaoEsgotoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = 
			actionMapping.findForward("efetuarLigacaoEsgoto");

		EfetuarLigacaoEsgotoActionForm ligacaoEsgotoActionForm = (EfetuarLigacaoEsgotoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		this.consultaSelectObrigatorio(this.getSessao(httpServletRequest));

		Boolean veioEncerrarOS = null;

		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (ligacaoEsgotoActionForm.getVeioEncerrarOS() != null && 
				ligacaoEsgotoActionForm.getVeioEncerrarOS().equalsIgnoreCase("TRUE")) {
				veioEncerrarOS = Boolean.TRUE;
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		// Permissao Especial Efetuar Ligacao de Esgoto sem RA
		boolean efetuarLigacaoEsgotoSemRA = 
			this.getFachada().verificarPermissaoEspecial(
				PermissaoEspecial.EFETUAR_LIGACAO_DE_ESGOTO_SEM_RA,
					this.getUsuarioLogado(httpServletRequest));

		ligacaoEsgotoActionForm.setPermissaoAlterarLigacaoEsgotosemRA("false");

		if (!veioEncerrarOS) {

			httpServletRequest.setAttribute("efetuarLigacaoEsgotoSemRA",efetuarLigacaoEsgotoSemRA);

			if (efetuarLigacaoEsgotoSemRA) {
				ligacaoEsgotoActionForm.setPermissaoAlterarLigacaoEsgotosemRA("true");
			}
		}

		ligacaoEsgotoActionForm.setPermissaoAlterarLigacaoEsgotosemRA("false");

		if (!veioEncerrarOS) {
			
			httpServletRequest.setAttribute(
				"efetuarLigacaoAguaComInstalacaodeHidrometroSemRA",efetuarLigacaoEsgotoSemRA);

			if (efetuarLigacaoEsgotoSemRA) {
				ligacaoEsgotoActionForm.setPermissaoAlterarLigacaoEsgotosemRA("true");
			}
		}

		String idImovel = ligacaoEsgotoActionForm.getIdImovel();

		if (idImovel != null && !idImovel.trim().equals("")) {
			
			// Pesquisa o imovel na base
			String inscricaoImovelEncontrado = this.getFachada().pesquisarInscricaoImovel(new Integer(idImovel));
			
			if (inscricaoImovelEncontrado != null && !inscricaoImovelEncontrado.equalsIgnoreCase("")) {

				ligacaoEsgotoActionForm.setMatriculaImovel(idImovel);
				ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovelEncontrado);

				Imovel imovel = (Imovel) this.getFachada().pesquisarDadosImovel(new Integer(idImovel));

				// [FS0002] Validar Situação de Água do Imóvel.
				if (imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.POTENCIAL.intValue() && 
					imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.FACTIVEL.intValue() && 
					imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.EM_FISCALIZACAO.intValue()) {

					throw new ActionServletException(
						"atencao.situacao_validar_ligacao_esgoto_invalida_exibir",
						null, 
						imovel.getLigacaoAguaSituacao().getDescricao());
				}

				/*
				 * [FS0008] Verificar Situação Rede de Água na Quadra
				 * 
				 * Integração com o conceito de face da quadra
				 * Raphael Rossiter em 21/05/2009
				 */
				IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
				
				if ((integracao.getIndicadorRedeEsgoto()).equals(Quadra.SEM_REDE)) {
					
					throw new ActionServletException("atencao.percentual_rede_esgoto_quadra", 
					null, imovel.getId() + "");
				}

				// [FS0006] Verificar Situação do Imovel
				if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
					
					throw new ActionServletException(
						"situacao_imovel_indicador_exclusao_esgoto", 
						null,
						imovel.getId() + "");
				}

				// Matricula Imóvel
				ligacaoEsgotoActionForm.setMatriculaImovel(imovel.getId().toString());

				// Situação da Ligação de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
				ligacaoEsgotoActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				// Situação da Ligação de Esgoto
				String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
				ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				this.pesquisarCliente(ligacaoEsgotoActionForm, imovel.getId());

				if (ligacaoEsgotoActionForm.getPerfilLigacao() != null && 
					!ligacaoEsgotoActionForm.getPerfilLigacao().equals("-1")) {

					FiltroLigacaoEsgotoPerfil filtroLigacaoPercentualEsgoto = new FiltroLigacaoEsgotoPerfil();
					filtroLigacaoPercentualEsgoto.adicionarParametro(
						new ParametroSimples(
							FiltroLigacaoEsgotoPerfil.ID,
							ligacaoEsgotoActionForm.getPerfilLigacao()));

					Collection colecaoPercentualEsgoto = 
						this.getFachada().pesquisar(
							filtroLigacaoPercentualEsgoto,
							LigacaoEsgotoPerfil.class.getName());

					if (colecaoPercentualEsgoto != null && 
						!colecaoPercentualEsgoto.isEmpty()) {

						LigacaoEsgotoPerfil percentualEsgotoPerfil = 
							(LigacaoEsgotoPerfil) colecaoPercentualEsgoto.iterator().next();

						String percentualFormatado = 
							percentualEsgotoPerfil.getPercentualEsgotoConsumidaColetada().toString().replace(".", ",");

						ligacaoEsgotoActionForm.setPercentualEsgoto(percentualFormatado);
					}
				}
				
				this.validarPermissaoEspecial(
						ligacaoEsgotoActionForm,null,httpServletRequest,false);

			} else {

				httpServletRequest.setAttribute("corImovel", "exception");
				ligacaoEsgotoActionForm.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}

		}
		
		ligacaoEsgotoActionForm.setIndicadorCaixaGordura("2");
		ligacaoEsgotoActionForm.setVeioEncerrarOS("" + veioEncerrarOS);
		ligacaoEsgotoActionForm.setIndicadorLigacao("1");

		// Variavel responsavél pelo preenchimento do imovel no formulário
		String idOrdemServico = null;
		if (ligacaoEsgotoActionForm.getIdOrdemServico() != null) {
			idOrdemServico = ligacaoEsgotoActionForm.getIdOrdemServico();
		} else {
			
			idOrdemServico = (String) httpServletRequest.getAttribute("veioEncerrarOS");
			ligacaoEsgotoActionForm.setDataLigacao((String) httpServletRequest.getAttribute("dataEncerramento"));
			
			this.getSessao(httpServletRequest).setAttribute("caminhoRetornoIntegracaoComercial",
				httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			this.getSessao(httpServletRequest).setAttribute("semMenu", "SIM");
		} else {
			this.getSessao(httpServletRequest).removeAttribute("semMenu");
		}

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(
				new ParametroSimples(
				FiltroOrdemServico.ID, 
				idOrdemServico));

			OrdemServico ordemServico = 
				this.getFachada().recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {

				this.getFachada().validarLigacaoEsgotoExibir(ordemServico,veioEncerrarOS);
				this.getSessao(httpServletRequest).setAttribute("ordemServico", ordemServico);

				ligacaoEsgotoActionForm.setIdOrdemServico(idOrdemServico);
				ligacaoEsgotoActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());

				Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
				this.getSessao(httpServletRequest).setAttribute("imovel", imovel);

				String matriculaImovel = imovel.getId().toString();

				if (imovel != null) {

					// Matricula Imóvel
					ligacaoEsgotoActionForm.setMatriculaImovel(imovel.getId().toString());

					// Inscrição Imóvel
					String inscricaoImovel = 
						this.getFachada().pesquisarInscricaoImovel(imovel.getId());

					ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovel);

					// Situação da Ligação de Agua
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
					ligacaoEsgotoActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
					ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this.pesquisarCliente(ligacaoEsgotoActionForm, new Integer(matriculaImovel));
					
					this.validarPermissaoEspecial(
						ligacaoEsgotoActionForm,null,httpServletRequest,false);
				}

				if (ordemServico.getServicoTipo() != null && 
					ordemServico.getServicoTipo().getDebitoTipo() != null) {
					
					ligacaoEsgotoActionForm.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId().toString());
					ligacaoEsgotoActionForm.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao());
				} else {
					ligacaoEsgotoActionForm.setIdTipoDebito("");
					ligacaoEsgotoActionForm.setDescricaoTipoDebito("");
				}

				// [FS0013] - Alteração de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(),ligacaoEsgotoActionForm);
				String calculaValores = httpServletRequest.getParameter("calculaValores");

				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
				Integer qtdeParcelas = null;
				BigDecimal valorDebito = new BigDecimal(0);

				if (calculaValores != null && calculaValores.equals("S")) {

					// [UC0186] - Calcular Prestação
					BigDecimal taxaJurosFinanciamento = null;
					qtdeParcelas = new Integer(ligacaoEsgotoActionForm.getQuantidadeParcelas());

					if (ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
						qtdeParcelas.intValue() != 1) {

						taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
					} else {
						taxaJurosFinanciamento = new BigDecimal(0);
					}

					BigDecimal valorPrestacao = null;
					if (taxaJurosFinanciamento != null) {

						valorDebito = 
							new BigDecimal(ligacaoEsgotoActionForm.getValorDebito().replace(",", "."));

						String percentualCobranca = ligacaoEsgotoActionForm.getPercentualCobranca();

						if (percentualCobranca.equals("70")) {
							valorDebito = valorDebito.multiply(new BigDecimal(0.7));
						} else if (percentualCobranca.equals("50")) {
							valorDebito = valorDebito.multiply(new BigDecimal(0.5));
						}

						valorPrestacao = 
							this.getFachada().calcularPrestacao(
								taxaJurosFinanciamento, 
								qtdeParcelas,
								valorDebito, 
								new BigDecimal("0.00"));

						valorPrestacao.setScale(2, BigDecimal.ROUND_HALF_UP);
					}

					if (valorPrestacao != null) {
						String valorPrestacaoComVirgula = 
							Util.formataBigDecimal(valorPrestacao, 2, true);
						
						ligacaoEsgotoActionForm.setValorParcelas(valorPrestacaoComVirgula);
					} else {
						ligacaoEsgotoActionForm.setValorParcelas("0,00");
					}

				} else {

					valorDebito = 
						this.getFachada().obterValorDebito(
							ordemServico.getServicoTipo().getId(), 
							imovel.getId(),
							new Short(LigacaoTipo.LIGACAO_AGUA + ""));
					
					ligacaoEsgotoActionForm.setValorDebito(Util.formataBigDecimal(valorDebito, 2, true));
				}

				if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
					ligacaoEsgotoActionForm.setMotivoNaoCobranca(
						ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
				}

				if (ordemServico.getPercentualCobranca() != null) {
					ligacaoEsgotoActionForm.setPercentualCobranca(
						ordemServico.getPercentualCobranca().toString());
				}

				if (ordemServico.getDataEncerramento() != null) {
					ligacaoEsgotoActionForm.setDataLigacao(
						Util.formatarData(ordemServico.getDataEncerramento()));
				}

				// Inscrição do Imovél
				String inscricaoImovel = imovel.getInscricaoFormatada();

				ligacaoEsgotoActionForm.setMatriculaImovel(matriculaImovel);
				ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovel);

				// Situação da Ligação de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao()
						.getDescricao();
				ligacaoEsgotoActionForm
						.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				this.getSessao(httpServletRequest).setAttribute(
					"ligacaoAguaSituacao", imovel.getLigacaoAguaSituacao());

				// Situação da Ligação de Esgoto
				String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
				ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				this.pesquisarCliente(ligacaoEsgotoActionForm, new Integer(matriculaImovel));

				/*-------------------- Dados da Ligação ----------------------------*/

				this.consultaSelectObrigatorio(this.getSessao(httpServletRequest));

				// Carregando campo Percentual de Esgoto
				// Item 4.6
				if (ligacaoEsgotoActionForm.getPerfilLigacao() != null && 
					!ligacaoEsgotoActionForm.getPerfilLigacao().equals("")) {

					FiltroLigacaoEsgotoPerfil filtroLigacaoPercentualEsgoto = new FiltroLigacaoEsgotoPerfil();

					filtroLigacaoPercentualEsgoto.adicionarParametro(
						new ParametroSimples(
							FiltroLigacaoEsgotoPerfil.ID,
							ligacaoEsgotoActionForm.getPerfilLigacao()));

					Collection colecaoPercentualEsgoto = 
						this.getFachada().pesquisar(
							filtroLigacaoPercentualEsgoto,
							LigacaoEsgotoPerfil.class.getName());

					if (colecaoPercentualEsgoto != null && 
						!colecaoPercentualEsgoto.isEmpty()) {

						LigacaoEsgotoPerfil percentualEsgotoPerfil = 
							(LigacaoEsgotoPerfil) colecaoPercentualEsgoto.iterator().next();

						String percentualFormatado = 
							percentualEsgotoPerfil.getPercentualEsgotoConsumidaColetada().toString().replace(".", ",");

						ligacaoEsgotoActionForm.setPercentualEsgoto(percentualFormatado);
					}
				}

				this.validarPermissaoEspecial(ligacaoEsgotoActionForm,valorDebito,httpServletRequest,true);

			} else {
				ligacaoEsgotoActionForm.setNomeOrdemServico("Ordem de Serviço inexistente");
				ligacaoEsgotoActionForm.setIdOrdemServico("");
				httpServletRequest.setAttribute("OrdemServioInexistente", true);
			}

		} 
		
		return retorno;
	}
	
	/*
	 * Validar os campos apatir da permissão especial
	 * 
	 * autor: Rafael Pinto
	 */
	private void validarPermissaoEspecial(EfetuarLigacaoEsgotoActionForm form,
		BigDecimal valorDebito,HttpServletRequest httpServletRequest,boolean validaMotivoNaoCobranca){

		boolean alterarPercentualColetaEsgoto = 
			this.getFachada().verificarPermissaoEspecial(
				PermissaoEspecial.ALTERAR_PERCENTUAL_COLETA_ESGOTO,
				this.getUsuarioLogado(httpServletRequest));

		if (alterarPercentualColetaEsgoto) {
			httpServletRequest.setAttribute("alterarPercentualColetaEsgoto",alterarPercentualColetaEsgoto);
		} 
			
		if(!alterarPercentualColetaEsgoto || form.getPercentualColeta() == null || form.getPercentualColeta().equals("") ){
			form.setPercentualColeta("100,00");
		}
		
		if(validaMotivoNaoCobranca){
			boolean temPermissaoMotivoNaoCobranca = 
				this.getFachada().verificarPermissaoInformarMotivoNaoCobranca(
					this.getUsuarioLogado(httpServletRequest));

			if (temPermissaoMotivoNaoCobranca) {
				httpServletRequest.setAttribute(
					"permissaoMotivoNaoCobranca",temPermissaoMotivoNaoCobranca);
			} else {
				form.setPercentualCobranca("100");
				form.setQuantidadeParcelas("1");
				form.setValorParcelas(
					Util.formataBigDecimal(valorDebito, 2, true));
			}

			if (temPermissaoMotivoNaoCobranca) {
				form.setPermissaoMotivoNaoCobranca("true");
			}
		}

	}
	

	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo,
			EfetuarLigacaoEsgotoActionForm form) {

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
			EfetuarLigacaoEsgotoActionForm ligacaoEsgotoActionForm,
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
			ligacaoEsgotoActionForm.setClienteUsuario(cliente.getNome());
			ligacaoEsgotoActionForm.setCpfCnpjCliente(documento);

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


		// Filtro para o campo Diametro Ligação Água
		Collection colecaoDiametroLigacao = (Collection) sessao
				.getAttribute("colecaoDiametroLigacaoAgua");

		if (colecaoDiametroLigacao == null) {

			FiltroDiametroLigacaoEsgoto filtroDiametroLigacaoEsgoto = new FiltroDiametroLigacaoEsgoto();

			filtroDiametroLigacaoEsgoto
					.adicionarParametro(new ParametroSimples(
							FiltroDiametroLigacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDiametroLigacaoEsgoto
					.setCampoOrderBy(FiltroDiametroLigacao.DESCRICAO);

			colecaoDiametroLigacao = this.getFachada().pesquisar(
					filtroDiametroLigacaoEsgoto, LigacaoEsgotoDiametro.class
							.getName());

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

			FiltroLigacaoMaterialEsgoto filtroLigacaoMaterialEsgoto = new FiltroLigacaoMaterialEsgoto();
			filtroLigacaoMaterialEsgoto
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoMaterialEsgoto.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoMaterialEsgoto
					.setCampoOrderBy(FiltroLigacaoMaterialEsgoto.DESCRICAO);

			colecaoMaterialLigacao = this.getFachada().pesquisar(
					filtroLigacaoMaterialEsgoto, LigacaoEsgotoMaterial.class
							.getName());

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

			FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();
			filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoPerfil
					.setCampoOrderBy(FiltroLigacaoEsgotoPerfil.DESCRICAO);

			colecaoPerfilLigacao = this.getFachada().pesquisar(filtroLigacaoEsgotoPerfil,
					LigacaoEsgotoPerfil.class.getName());

			if (colecaoPerfilLigacao != null && !colecaoPerfilLigacao.isEmpty()) {
				sessao.setAttribute("colecaoPerfilLigacao",
						colecaoPerfilLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Perfil de Ligação");
			}
		}

		// Filtro para o campo Motivo nao cobranca
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = this.getFachada().pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da Não Cobrança");
			}
		}

		// Filtro para o campo ligação esgotamento
		Collection colecaoLigacaoEsgotoEsgotamento = (Collection) sessao
				.getAttribute("colecaoLigacaoEsgotoEsgotamento");

		if (colecaoLigacaoEsgotoEsgotamento == null) {

			FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
			filtroLigacaoEsgotoEsgotamento
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoEsgotoEsgotamento.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoEsgotamento
					.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.DESCRICAO);

			colecaoLigacaoEsgotoEsgotamento = this.getFachada().pesquisar(
					filtroLigacaoEsgotoEsgotamento,
					LigacaoEsgotoEsgotamento.class.getName());

			if (colecaoLigacaoEsgotoEsgotamento != null
					&& !colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
				sessao.setAttribute("colecaoLigacaoEsgotoEsgotamento",
						colecaoLigacaoEsgotoEsgotamento);
			}
		}

		// Filtro para o campo destino dos dejetos
		Collection colecaoDestinoDejetos = (Collection) sessao
				.getAttribute("colecaoDestinoDejetos");

		if (colecaoDestinoDejetos == null) {

			FiltroLigacaoEsgotoDestinoDejetos filtroDestinoDejetos = new FiltroLigacaoEsgotoDestinoDejetos();
			filtroDestinoDejetos.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoDestinoDejetos.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDestinoDejetos
					.setCampoOrderBy(FiltroLigacaoEsgotoDestinoDejetos.DESCRICAO);

			colecaoDestinoDejetos = this.getFachada().pesquisar(filtroDestinoDejetos,
					LigacaoEsgotoDestinoDejetos.class.getName());

			if (colecaoDestinoDejetos != null
					&& !colecaoDestinoDejetos.isEmpty()) {
				sessao.setAttribute("colecaoDestinoDejetos",
						colecaoDestinoDejetos);
			}
		}

		// Filtro para o campo caixa de inspeção
		Collection colecaoSituacaoCaixaInspecao = (Collection) sessao
				.getAttribute("colecaoSituacaoCaixaInspecao");

		if (colecaoSituacaoCaixaInspecao == null) {

			FiltroLigacaoEsgotoCaixaInspecao filtroSituacaoCaixaInspecao = new FiltroLigacaoEsgotoCaixaInspecao();
			filtroSituacaoCaixaInspecao
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoEsgotoCaixaInspecao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroSituacaoCaixaInspecao
					.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);

			colecaoSituacaoCaixaInspecao = this.getFachada().pesquisar(
					filtroSituacaoCaixaInspecao,
					LigacaoEsgotoCaixaInspecao.class.getName());

			if (colecaoSituacaoCaixaInspecao != null
					&& !colecaoSituacaoCaixaInspecao.isEmpty()) {
				sessao.setAttribute("colecaoSituacaoCaixaInspecao",
						colecaoSituacaoCaixaInspecao);
			}
		}

		// Filtro para o campo destino caixas pluviais
		Collection colecaoDestinoAguasPluviais = (Collection) sessao
				.getAttribute("colecaoDestinoAguasPluviais");

		if (colecaoDestinoAguasPluviais == null) {

			FiltroLigacaoEsgotoDestinoAguasPluviais filtroDestinoAguasPluviais = new FiltroLigacaoEsgotoDestinoAguasPluviais();
			filtroDestinoAguasPluviais.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoDestinoAguasPluviais.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDestinoAguasPluviais
					.setCampoOrderBy(FiltroLigacaoEsgotoDestinoAguasPluviais.DESCRICAO);

			colecaoDestinoAguasPluviais = this.getFachada().pesquisar(
					filtroDestinoAguasPluviais,
					LigacaoEsgotoDestinoAguasPluviais.class.getName());

			if (colecaoDestinoAguasPluviais != null
					&& !colecaoDestinoAguasPluviais.isEmpty()) {
				sessao.setAttribute("colecaoDestinoAguasPluviais",
						colecaoDestinoAguasPluviais);
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

			colecaoLigacaoOrigem = this.getFachada().pesquisar(filtroLigacaoOrigem,
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
