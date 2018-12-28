package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoPagamentoDuplicidade;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitanteFone;
import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.RaMotivoReativacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidade;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitanteFone;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterRAAssociadoHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAFiltroHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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

public class ExibirConsultarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimento");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarRegistroAtendimentoActionForm form = (ConsultarRegistroAtendimentoActionForm) actionForm;

		Integer idRA = null;
		if (form.getNumeroRA() != null && !form.getNumeroRA().equalsIgnoreCase("")) {

			idRA = new Integer(form.getNumeroRA());

			if (httpServletRequest.getParameter("pesquisaUnitaria") != null) {
				form.reset(actionMapping, httpServletRequest);
				sessao.removeAttribute("colecaoCompleta");
				sessao.setAttribute("naoHabilitarNavegacao", "OK");
			} else if (sessao.getAttribute("colecaoRAHelper") != null) {
				sessao.removeAttribute("naoHabilitarNavegacao");
			} else {
				sessao.setAttribute("naoHabilitarNavegacao", "OK");
			}
		} else {
			idRA = (Integer) sessao.getAttribute("numeroOS");
			sessao.removeAttribute("colecaoCompleta");

			sessao.setAttribute("naoHabilitarNavegacao", "OK");
		}

		System.out.println(sessao.getAttribute("colecaoCompleta"));

		if (sessao.getAttribute("colecaoRAHelper") != null) {
			FiltrarRegistroAtendimentoHelper filtro = (FiltrarRegistroAtendimentoHelper) sessao.getAttribute("filtroRA");
			List<RAFiltroHelper> colecao = (List<RAFiltroHelper>) sessao.getAttribute("colecaoRAHelper");
			System.out.println("colecaoRAHelper: " + colecao.size());

			Integer totalRegistros = (Integer) sessao.getAttribute("totalRegistros");
			System.out.println("totalRegistros: " + totalRegistros);
			Integer numeroPaginasPesquisa = (int) Math.ceil((double) totalRegistros / 10.0);
			System.out.println("numeropaginas: " + numeroPaginasPesquisa);
			Integer page = (Integer) sessao.getAttribute("page.offset");
			System.out.println("page: " + page);

			boolean anterior = (httpServletRequest.getParameter("raAnterior") != null) ? true : false;
			boolean proximo = (httpServletRequest.getParameter("proximoRA") != null) ? true : false;

			int index = obterIndexRAColecao(idRA, colecao);
			if (index != -1) {

				if (anterior)
					index--;
				if (proximo)
					index++;
				System.out.println("Index: " + index);
				boolean mudaPagina = false;
				if (index > 9 && page < numeroPaginasPesquisa) {
					index = 0;
					page = page + 1;
					mudaPagina = true;
				} else if (index < 0 && page > 1) {
					index = 9;
					page = page - 1;
					mudaPagina = true;
				}
				if (mudaPagina) {
					System.out.println("mudaPagina");
					filtro.setNumeroPagina(page - 1);
					sessao.setAttribute("page.offset", page);

					Collection<RegistroAtendimento> colecaoRA = fachada.filtrarRegistroAtendimento(filtro);
					if (colecaoRA != null && colecaoRA.size() > 0) {
						colecao = (List<RAFiltroHelper>) loadColecaoRAHelper(colecaoRA);
						sessao.setAttribute("colecaoRAHelper", colecao);
						idRA = colecao.get(index).getRegistroAtendimento().getId();
					} else {
						httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
					}
				}

				if (page == 1 && index == 0) {
					httpServletRequest.setAttribute("desabilitaBotaoAnterior", "true");
				}
				if ((page >= numeroPaginasPesquisa) && index >= colecao.size() - 1) {
					System.out.println("EEK!");
					httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
				}

				if (index >= 0 && index <= 9) {
					if (index > colecao.size() - 1) {
						httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
					}
					idRA = colecao.get(index).getRegistroAtendimento().getId();

				}

			} else {
				httpServletRequest.setAttribute("desabilitaBotaoAnterior", "true");
				httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
			}
		} else {
			httpServletRequest.setAttribute("naoHabilitarNavegacao", "OK");
		}

		ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(new Integer(idRA));

		if (obterDadosRegistroAtendimentoHelper == null || obterDadosRegistroAtendimentoHelper.getRegistroAtendimento() == null) {

			throw new ActionServletException("atencao.naocadastrado", null, "Registro Atendimento");
		}

		form = limparCampos(form);
		
		RegistroAtendimento registroAtendimento = obterDadosRegistroAtendimentoHelper.getRegistroAtendimento();

		this.validarRAAtualizacaoCadastral(registroAtendimento, sessao);
		
		// Dados Gerais do Registro de Atendimento
		form.setNumeroRAPesquisado("" + registroAtendimento.getId());

		if (registroAtendimento.getManual() != null) {
			int tamanhoNumeracao = registroAtendimento.getManual().toString().length();
			String numeracao = registroAtendimento.getManual().toString().substring(0, tamanhoNumeracao - 1);
			form.setNumeroRAManual(Util.formatarNumeracaoRAManual(new Integer(numeracao)));
		} else {
			form.setNumeroRAManual("");
		}

		form.setCodigoSituacao("" + registroAtendimento.getCodigoSituacao());

		// Caso de Uso [UC0420]
		ObterDescricaoSituacaoRAHelper situacaoRA = fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());

		form.setSituacaoRA(situacaoRA.getDescricaoSituacao());

		// Caso de Uso [UC0433]
		ObterRAAssociadoHelper obterRAAssociadoHelper = fachada.obterRAAssociadoConsultarRA(registroAtendimento.getId());

		if (obterRAAssociadoHelper != null && obterRAAssociadoHelper.getRegistroAtendimentoAssociado() != null) {
			form.setNumeroRaAssociado("" + obterRAAssociadoHelper.getRegistroAtendimentoAssociado().getId());

			ObterDescricaoSituacaoRAHelper situacaoRAssociado = fachada.obterDescricaoSituacaoRA(
					obterRAAssociadoHelper.getRegistroAtendimentoAssociado().getId());

			form.setSituacaoRaAssociado(situacaoRAssociado.getDescricaoSituacao());

			if (obterRAAssociadoHelper.getCodigoExistenciaRAAssociado() == RegistroAtendimento.CODIGO_ASSOCIADO_RA_REFERENCIA) {
				form.setDescricaoRAAssociada("Número do RA de Referência:");
				form.setDescricaoSituacaoRAAssociada("Situação do RA de Referência:");
			} else if (obterRAAssociadoHelper.getCodigoExistenciaRAAssociado() == RegistroAtendimento.CODIGO_ASSOCIADO_RA_ATUAL) {
				form.setDescricaoRAAssociada("Número do RA Atual:");
				form.setDescricaoSituacaoRAAssociada("Situação do RA Atual:");
			} else if (obterRAAssociadoHelper.getCodigoExistenciaRAAssociado() == RegistroAtendimento.CODIGO_ASSOCIADO_RA_ANTERIOR) {
				form.setDescricaoRAAssociada("Número do RA Anterior:");
				form.setDescricaoSituacaoRAAssociada("Situação do RA Anterior:");
			}

			httpServletRequest.setAttribute("existeRaAssociado", true);
		}

		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = registroAtendimento.getSolicitacaoTipoEspecificacao();
		if (solicitacaoTipoEspecificacao != null) {
			if (solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null) {
				form.setIdTipoSolicitacao("" + solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId());
				form.setTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());
			}

			if (solicitacaoTipoEspecificacao.getServicoTipo() != null) {
				String valorPrevisto = Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getServicoTipo().getValor());
				form.setValorSugerido(valorPrevisto);
			}

			form.setIdEspecificacao("" + solicitacaoTipoEspecificacao.getId());
			form.setEspecificacao(solicitacaoTipoEspecificacao.getDescricao());
		}

		// Perfil do Imovel
		if (registroAtendimento.getImovel() != null) {
			ImovelPerfil imovelPerfil = registroAtendimento.getImovel().getImovelPerfil();

			if (imovelPerfil != null) {
				form.setPerfilImovel(imovelPerfil.getDescricao());
			}
		}

		form.setTipoAtendimento("" + registroAtendimento.getIndicadorAtendimentoOnline());

		Date dataAtendimento = registroAtendimento.getRegistroAtendimento();

		form.setDataAtendimento(Util.formatarData(dataAtendimento));
		form.setHoraAtendimento(Util.formatarHoraSemSegundos(dataAtendimento));

		form.setTempoEsperaInicio(Util.formatarHoraSemSegundos(registroAtendimento.getDataInicioEspera()));
		form.setTempoEsperaTermino(Util.formatarHoraSemSegundos(registroAtendimento.getDataFimEspera()));

		form.setDataPrevista(Util.formatarData(registroAtendimento.getDataPrevistaAtual()));

		if (registroAtendimento.getMeioSolicitacao() != null) {
			form.setIdMeioSolicitacao("" + registroAtendimento.getMeioSolicitacao().getId());
			form.setMeioSolicitacao(registroAtendimento.getMeioSolicitacao().getDescricao());
		}

		// Caso de Uso [UC0421]
		UnidadeOrganizacional unidadeAtendimento = fachada.obterUnidadeAtendimentoRA(registroAtendimento.getId());

		if (unidadeAtendimento != null) {

			form.setIdUnidadeAtendimento("" + unidadeAtendimento.getId());
			form.setUnidadeAtendimento(unidadeAtendimento.getDescricao());

			RegistroAtendimentoUnidade registroAtendimentoUnidade = this.consultarRegistroAtendimentoUnidade(registroAtendimento.getId(),
					unidadeAtendimento.getId(), AtendimentoRelacaoTipo.ABRIR_REGISTRAR);

			Usuario usuario = registroAtendimentoUnidade.getUsuario();

			if (usuario != null) {
				form.setIdUsuario("" + usuario.getId());
				form.setUsuario(usuario.getNomeUsuario());
			}

		}

		// Caso de Uso [UC0418]
		UnidadeOrganizacional unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());

		if (unidadeAtual != null) {
			form.setIdUnidadeAtual("" + unidadeAtual.getId());
			form.setUnidadeAtual(unidadeAtual.getDescricao());
		}

		UnidadeOrganizacional unidadeAnterior = fachada.verificaUnidadeAnteriorRA(registroAtendimento.getId());
		if (unidadeAnterior != null) {
			form.setIdUnidadeAnterior("" + unidadeAnterior.getId());
			form.setUnidadeAnterior(unidadeAnterior.getDescricao());
		} else {
			form.setIdUnidadeAnterior("");
			form.setUnidadeAnterior("");
		}

		form.setObservacao(registroAtendimento.getObservacao());

		// Dados do Local da Ocorrencia
		Imovel imovel = registroAtendimento.getImovel();
		if (imovel != null) {

			form.setMatriculaImovel("" + imovel.getId());
			form.setInscricaoImovel(imovel.getInscricaoFormatada());
			form.setRota(obterDadosRegistroAtendimentoHelper.getCodigoRota().toString());

			if (obterDadosRegistroAtendimentoHelper.getSequencialRota() != null) {
				form.setSequencialRota(obterDadosRegistroAtendimentoHelper.getSequencialRota().toString());
			}
		}

		// Caso de Uso [UC0422]
		String enderecoOcorrencia = fachada.obterEnderecoOcorrenciaRA(registroAtendimento.getId());

		form.setEnderecoOcorrencia(enderecoOcorrencia);
		form.setPontoReferencia(registroAtendimento.getPontoReferencia());

		if (registroAtendimento.getNnCoordenadaNorte() != null) {
			form.setNumeroCoordenadaNorte("" + registroAtendimento.getNnCoordenadaNorte());
		} else {
			form.setNumeroCoordenadaNorte("");
		}
		if (registroAtendimento.getNnCoordenadaLeste() != null) {
			form.setNumeroCoordenadaLeste("" + registroAtendimento.getNnCoordenadaLeste());
		} else {
			form.setNumeroCoordenadaLeste("");
		}

		// Caso o registro atendimento esteja associado a uma área de bairro, obter os dados da área do bairro
		BairroArea bairroArea = registroAtendimento.getBairroArea();
		if (bairroArea != null) {

			form.setIdMunicipio("" + bairroArea.getBairro().getMunicipio().getId());
			form.setMunicipio(bairroArea.getBairro().getMunicipio().getNome());

			form.setIdBairro("" + bairroArea.getBairro().getId());
			form.setBairro(bairroArea.getBairro().getNome());

			form.setIdAreaBairro("" + bairroArea.getId());
			form.setAreaBairro(bairroArea.getNome());
		}

		Localidade localidade = registroAtendimento.getLocalidade();

		if (localidade != null) {
			form.setIdLocalidade("" + localidade.getId());
			form.setLocalidade(localidade.getDescricao());
		}

		SetorComercial setorComercial = registroAtendimento.getSetorComercial();

		if (setorComercial != null) {
			form.setIdSetorComercial("" + setorComercial.getCodigo());
			form.setSetorComercial(setorComercial.getDescricao());
		}

		Quadra quadra = registroAtendimento.getQuadra();

		if (quadra != null) {
			form.setIdQuadra("" + quadra.getNumeroQuadra());
		}

		DivisaoEsgoto divisaoEsgoto = registroAtendimento.getDivisaoEsgoto();

		if (divisaoEsgoto != null) {

			form.setIdDivisaoEsgoto("" + divisaoEsgoto.getId());
			form.setDivisaoEsgoto(divisaoEsgoto.getDescricao());
		}

		LocalOcorrencia localOcorrencia = registroAtendimento.getLocalOcorrencia();

		if (localOcorrencia != null) {
			form.setLocalOcorrencia(localOcorrencia.getDescricao());
		}

		PavimentoRua pavimentoRua = registroAtendimento.getPavimentoRua();

		if (pavimentoRua != null) {
			form.setPavimentoRua(pavimentoRua.getDescricao());
		}

		PavimentoCalcada pavimentoCalcada = registroAtendimento.getPavimentoCalcada();

		if (pavimentoCalcada != null) {
			form.setPavimentoCalcada(pavimentoCalcada.getDescricao());
		}

		form.setDescricaoLocalOcorrencia(registroAtendimento.getDescricaoLocalOcorrencia());

		// Dados do Solicitante
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = this.consultarRegistroAtendimentoSolicitante(registroAtendimento.getId());

		if (registroAtendimentoSolicitante != null) {

			Cliente cliente = registroAtendimentoSolicitante.getCliente();
			UnidadeOrganizacional unidadeSolicitante = registroAtendimentoSolicitante.getUnidadeOrganizacional();

			// PROTOCOLO DE ATENDIMENTO
			if (registroAtendimentoSolicitante.getNumeroProtocoloAtendimento() != null
					&& !registroAtendimentoSolicitante.getNumeroProtocoloAtendimento().equals("")) {

				form.setNumeroProtocolo(registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
			}

			// Caso o principal solicitante do registro de atendimento seja um cliente obter os dados do cliente
			if (cliente != null) {

				form.setIdClienteSolicitante("" + cliente.getId());
				form.setClienteSolicitante(cliente.getNome());

				// Caso o principal solicitante do registro de atendimento seja uma unidade obter os dados da unidade
			} else if (unidadeSolicitante != null) {

				form.setIdUnidadeSolicitante("" + unidadeSolicitante.getId());
				form.setUnidadeSolicitante(unidadeSolicitante.getDescricao());

				// Caso o principal solicitante do registro de atendimento não seja um cliente, nem uma unidade obter os dados do solicitante
			} else {
				form.setNomeSolicitante(registroAtendimentoSolicitante.getSolicitante());
			}

			Funcionario funcionario = registroAtendimentoSolicitante.getFuncionario();
			if (funcionario != null) {
				form.setIdFuncionarioResponsavel("" + funcionario.getId());
				form.setFuncionarioResponsavel(funcionario.getNome());
			}

			// Caso de Uso [UC0423]
			String enderecoSolicitante = fachada.obterEnderecoSolicitanteRA(registroAtendimentoSolicitante.getID());

			form.setEnderecoSolicitante(enderecoSolicitante);
			form.setPontoReferenciaSolicitante(registroAtendimentoSolicitante.getPontoReferencia());

			SolicitanteFone solicitanteFone = consultarSolicitanteFone(registroAtendimentoSolicitante.getID());

			if (solicitanteFone != null) {
				form.setFoneDDD("" + solicitanteFone.getDdd());
				form.setFone(solicitanteFone.getFone());
				form.setFoneRamal(solicitanteFone.getRamal());

			}

			// [RM1094] Questionario de Satisfacao do Cliente
			if (registroAtendimentoSolicitante != null && registroAtendimentoSolicitante.getIndicadorEnvioEmailPesquisa() != null) {
				sessao.setAttribute("habilitarCampoSatisfacaoEmail", true);
				form
						.setEnviarEmailSatisfacao(registroAtendimentoSolicitante.getIndicadorEnvioEmailPesquisa().intValue() + "");
				form.setEnderecoEmail(registroAtendimentoSolicitante.getEnderecoEmail());
			} else {
				sessao.setAttribute("habilitarCampoSatisfacaoEmail", false);
			}

		}

		/*
		 * ANEXOS
		 * ----------------------------------------------------------------
		 * -------------------------------------------
		 */
		// CARREGANDO OS ANEXOS QUE ESTÃO CADASTRADOS NA BASE
		String visualizar = httpServletRequest.getParameter("visualizar");

		FiltroRegistroAtendimentoAnexo filtroRegistroAtendimentoAnexo = new FiltroRegistroAtendimentoAnexo();

		filtroRegistroAtendimentoAnexo.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoAnexo.REGISTRO_ATENDIMENTO_ID, registroAtendimento
				.getId()));

		Collection colecaoRegistroAtendimentoAnexo = fachada.pesquisar(filtroRegistroAtendimentoAnexo, RegistroAtendimentoAnexo.class.getName());

		httpServletRequest.setAttribute("colecaoRegistroAtendimentoAnexo", colecaoRegistroAtendimentoAnexo);

		// OBTENDO ARQUIVO PARA VISUALIZAÇÃO
		RegistroAtendimentoAnexo registroAtendimentoAnexo = this.obterArquivoParaVisualizacao(visualizar, colecaoRegistroAtendimentoAnexo);

		// PREPARANDO VISUALIZAÇÃO DO ARQUIVO
		if (registroAtendimentoAnexo != null) {

			OutputStream out = null;

			String mimeType = ConstantesSistema.CONTENT_TYPE_GENERICO;

			if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_DOC)) {
				mimeType = ConstantesSistema.CONTENT_TYPE_MSWORD;
			} else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_PDF)) {
				mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
			} else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_JPG)) {
				mimeType = ConstantesSistema.CONTENT_TYPE_JPEG;
			}

			try {
				httpServletResponse.setContentType(mimeType);
				out = httpServletResponse.getOutputStream();

				out.write(registroAtendimentoAnexo.getImagemDocumento());
				out.flush();
				out.close();
			} catch (IOException e) {
				throw new ActionServletException("erro.sistema", e);
			}
		}
		/*
		 * FIM DOS ANEXOS
		 * --------------------------------------------------------
		 * ---------------------------------------------------
		 */

		// Dados da Ultima Tramitação

		Tramite tramite = fachada.recuperarTramiteMaisAtualPorRA(registroAtendimento.getId());

		if (tramite != null) {

			UnidadeOrganizacional unidadeOrigem = tramite.getUnidadeOrganizacionalOrigem();

			if (unidadeOrigem != null) {

				form.setIdUnidadeOrigem("" + unidadeOrigem.getId());
				form.setUnidadeOrigem(unidadeOrigem.getDescricao());
			}

			UnidadeOrganizacional unidadeDestino = tramite.getUnidadeOrganizacionalDestino();

			if (unidadeDestino != null) {

				form.setIdUnidadeAtualTramitacao("" + unidadeDestino.getId());
				form.setUnidadeAtualTramitacao(unidadeDestino.getDescricao());

			}

			Date dataTramite = tramite.getDataTramite();

			form.setDataTramite(Util.formatarData(dataTramite));
			form.setHoraTramite(Util.formatarHoraSemSegundos(dataTramite));

			form.setParecerTramite(tramite.getParecerTramite());

			Usuario usuarioResponsavel = tramite.getUsuarioResponsavel();

			if (usuarioResponsavel != null) {
				form.setIdUsuarioResponsavel("" + usuarioResponsavel.getId());
				form.setUsuarioResponsavel(usuarioResponsavel.getNomeUsuario());
			}
		}

		// Dados da Reiteração

		// Caso o registro atendimento tenha sido reiterado,
		// exibir os dados da reiteração
		if (registroAtendimento.getQuantidadeReiteracao() != null) {

			Date dataUltimaReiteracao = registroAtendimento.getUltimaReiteracao();

			form.setQuantidade("" + registroAtendimento.getQuantidadeReiteracao());
			form.setDataUltimaReiteracao(Util.formatarData(dataUltimaReiteracao));
			form.setHoraUltimaReiteracao(Util.formatarHoraSemSegundos(dataUltimaReiteracao));

		}

		obterDadosReiteracaoRa(registroAtendimento.getId(), fachada, sessao);

		// Dados da Reativação

		// Caso o registro atendimento tenha sido reativado
		// exibir os dados da reativação
		Short codigoAssociado = obterRAAssociadoHelper.getCodigoExistenciaRAAssociado();

		RegistroAtendimento registroAtendimentoAssociado = obterRAAssociadoHelper.getRegistroAtendimentoAssociado();

		// Caso de Uso [UC0420]
		ObterDescricaoSituacaoRAHelper situacaoRAAssociado = null;
		if (registroAtendimentoAssociado != null) {
			situacaoRAAssociado = fachada.obterDescricaoSituacaoRA(registroAtendimentoAssociado.getId());
		}

		if (codigoAssociado == RegistroAtendimento.CODIGO_ASSOCIADO_RA_ATUAL && registroAtendimentoAssociado != null) {

			form.setNumeroRaAtual("" + registroAtendimentoAssociado.getId());
			form.setSituacaoRaAtual(situacaoRAAssociado.getDescricaoSituacao());

			RaMotivoReativacao raMotivoReativacao = registroAtendimentoAssociado.getRaMotivoReativacao();
			if (raMotivoReativacao != null) {
				form.setIdMotivoReativacao("" + raMotivoReativacao.getId());
				form.setMotivoReativacao(raMotivoReativacao.getDescricao());
			}

			Date dataRegistro = registroAtendimentoAssociado.getRegistroAtendimento();
			Date dataPrevista = registroAtendimentoAssociado.getDataPrevistaAtual();

			form.setDataReativacao(Util.formatarData(dataRegistro));
			form.setHoraReativacao(Util.formatarHoraSemSegundos(dataRegistro));

			form.setDataPrevistaRaAtual(Util.formatarData(dataPrevista));

			// Caso de Uso [UC0421]
			UnidadeOrganizacional unidadeReativacao = fachada.obterUnidadeAtendimentoRA(registroAtendimentoAssociado.getId());

			if (unidadeReativacao != null) {
				form.setIdUnidadeReativacao("" + unidadeReativacao.getId());
				form.setUnidadeReativacao(unidadeReativacao.getDescricao());
			}

			// Caso de Uso [UC0418]
			UnidadeOrganizacional unidadeRAAtual = fachada.obterUnidadeAtualRA(registroAtendimentoAssociado.getId());

			if (unidadeRAAtual != null) {
				form.setIdUnidadeRaAtual("" + unidadeRAAtual.getId());
				form.setUnidadeRaAtual(unidadeRAAtual.getDescricao());
			}

			form.setObservacaoReativacao(registroAtendimentoAssociado.getObservacao());
		}

		// Dados do encerramento

		// Caso o registro atendimento seja encerrado,
		// exibir os dados do encerramento
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = registroAtendimento.getAtendimentoMotivoEncerramento();

		if (atendimentoMotivoEncerramento != null) {

			form.setIdMotivoEncerramento("" + atendimentoMotivoEncerramento.getId());
			form.setMotivoEncerramento(atendimentoMotivoEncerramento.getDescricao());

			if (codigoAssociado == RegistroAtendimento.CODIGO_ASSOCIADO_RA_REFERENCIA && registroAtendimentoAssociado != null) {

				form.setNumeroRaReferencia("" + registroAtendimentoAssociado.getId());

				// Caso de Uso [UC0420]
				form.setSituacaoRaReferencia(situacaoRAAssociado.getDescricaoSituacao());

			}

			// Caso de Uso [UC0434]
			UnidadeOrganizacional unidadeEncerramento = fachada.obterUnidadeEncerramentoRA(registroAtendimento.getId());

			if (unidadeEncerramento != null) {

				form.setIdUnidadeEncerramento("" + unidadeEncerramento.getId());
				form.setUnidadeEncerramento(unidadeEncerramento.getDescricao());

				RegistroAtendimentoUnidade registroAtendimentoUnidade = this.consultarRegistroAtendimentoUnidade(registroAtendimento.getId(),
						unidadeEncerramento.getId(), AtendimentoRelacaoTipo.ENCERRAR);

				Usuario usuario = registroAtendimentoUnidade.getUsuario();
				if (usuario != null) {

					form.setIdUsuarioEncerramento("" + usuario.getId());
					form.setUsuarioEncerramento(usuario.getNomeUsuario());
				}
			}

			Date dataEncerramento = registroAtendimento.getDataEncerramento();

			form.setDataEncerramento(Util.formatarData(dataEncerramento));
			form.setHoraEncerramento(Util.formatarHoraSemSegundos(dataEncerramento));

			form.setDataPrevistaEncerramento(Util.formatarData(registroAtendimento.getDataPrevistaAtual()));

			form.setParecerEncerramento(registroAtendimento.getParecerEncerramento());

			if (registroAtendimento.getServicoNaoCobrancaMotivo() != null) {
				form.setMotivoNaoCobranca(registroAtendimento.getServicoNaoCobrancaMotivo().getDescricao());
			}
		}

		// Dados das Contas relacionados
		// Mariana Victor em 28/01/2011
		FiltroRegistroAtendimentoConta filtroRegistroAtendimentoConta = new FiltroRegistroAtendimentoConta();
		filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimentoConta.CONTA);
		filtroRegistroAtendimentoConta.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO);
		filtroRegistroAtendimentoConta.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoConta.REGISTRO_ATENDIMENTO_ID, registroAtendimento
				.getId()));

		Collection colecaoRAContas = fachada.pesquisar(filtroRegistroAtendimentoConta, RegistroAtendimentoConta.class.getName());

		if (colecaoRAContas != null && !colecaoRAContas.isEmpty()) {
			sessao.setAttribute("colecaoRAContas", colecaoRAContas);
		} else {
			sessao.removeAttribute("colecaoRAContas");
		}

		// Colocado por Raphael Rossiter em 26/10/2006
		form.setNumeroRA("");
		httpServletRequest.setAttribute("nomeCampo", "numeroRA");

		// Pagamentos Duplicidade
		FiltroRegistroAtendimentoPagamentoDuplicidade filtroRegistroAtendimentoPagamentoDuplicidade = new FiltroRegistroAtendimentoPagamentoDuplicidade();
		filtroRegistroAtendimentoPagamentoDuplicidade.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimentoPagamentoDuplicidade.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));

		Collection<RegistroAtendimentoPagamentoDuplicidade> colecaoRAPagamentoDuplicidade = this.getFachada().pesquisar(
				filtroRegistroAtendimentoPagamentoDuplicidade, RegistroAtendimentoPagamentoDuplicidade.class.getName());

		if (colecaoRAPagamentoDuplicidade != null && !colecaoRAPagamentoDuplicidade.isEmpty()) {
			sessao.setAttribute("colecaoRAPagamentoDuplicidade", colecaoRAPagamentoDuplicidade);
		} else {
			sessao.removeAttribute("colecaoRAPagamentoDuplicidade");
		}

		return retorno;
	}

	/**
	 * Consulta o registro atendimento solicitante pelo id do registro
	 * atendimento
	 */
	private RegistroAtendimentoSolicitante consultarRegistroAtendimentoSolicitante(Integer idRegistroAtendimento) {

		RegistroAtendimentoSolicitante retorno = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimento = null;

		FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimento = new FiltroRegistroAtendimentoSolicitante();

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, idRegistroAtendimento));

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.INDICADOR_SOLICITANTE_PRINCIPAL,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("funcionario");

		colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimentoSolicitante.class.getName());

		if (colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()) {
			retorno = (RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);

		}

		return retorno;
	}

	/**
	 * Consulta o solicitante fone pelo id do registro atendimentoSolicitante
	 */
	private SolicitanteFone consultarSolicitanteFone(Integer idRegistroAtendimentoSolicitante) {

		SolicitanteFone retorno = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoSolicitanteFone = null;

		FiltroSolicitanteFone filtroSolicitanteFone = new FiltroSolicitanteFone();

		filtroSolicitanteFone.adicionarParametro(new ParametroSimples(FiltroSolicitanteFone.REGISTRO_ATENDIMENTO_SOLICITANTE_ID,
				idRegistroAtendimentoSolicitante));

		colecaoSolicitanteFone = fachada.pesquisar(filtroSolicitanteFone, SolicitanteFone.class.getName());

		if (colecaoSolicitanteFone != null && !colecaoSolicitanteFone.isEmpty()) {
			retorno = (SolicitanteFone) Util.retonarObjetoDeColecao(colecaoSolicitanteFone);

		}

		return retorno;
	}

	/**
	 * Consulta o Registro Atendimento Unidade pelo id da RA
	 */
	private RegistroAtendimentoUnidade consultarRegistroAtendimentoUnidade(Integer idRA, Integer idUnidade, Integer atendimentoRelacaoTipoId) {

		RegistroAtendimentoUnidade retorno = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimentoUnidade = null;

		FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();

		filtroRegistroAtendimentoUnidade.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoUnidade.REGISTRO_ATENDIMENTO_ID, idRA));

		filtroRegistroAtendimentoUnidade.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoUnidade.UNIDADE_ORGANIZACIONAL_ID, idUnidade));

		if (atendimentoRelacaoTipoId != null) {
			filtroRegistroAtendimentoUnidade.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoUnidade.ATENDIMENTO_RELACAO_TIPO,
					atendimentoRelacaoTipoId));
		}

		filtroRegistroAtendimentoUnidade.adicionarCaminhoParaCarregamentoEntidade("usuario");

		colecaoRegistroAtendimentoUnidade = fachada.pesquisar(filtroRegistroAtendimentoUnidade, RegistroAtendimentoUnidade.class.getName());

		if (colecaoRegistroAtendimentoUnidade != null && !colecaoRegistroAtendimentoUnidade.isEmpty()) {
			retorno = (RegistroAtendimentoUnidade) Util.retonarObjetoDeColecao(colecaoRegistroAtendimentoUnidade);

		}

		return retorno;
	}

	private int obterIndexRAColecao(Integer idRA, Collection<RAFiltroHelper> colecao) {
		int index = 0;
		for (RAFiltroHelper helper : colecao) {
			if (helper.getRegistroAtendimento().getId().equals(idRA)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	private Collection loadColecaoRAHelper(Collection<RegistroAtendimento> colecaoRegistroAtendimento) {
		Fachada fachada = Fachada.getInstancia();
		Collection colecaoRAHelper = new ArrayList();
		UnidadeOrganizacional unidadeAtual = null;
		ObterDescricaoSituacaoRAHelper situacao = null;
		RAFiltroHelper helper = null;
		for (Iterator iter = colecaoRegistroAtendimento.iterator(); iter.hasNext();) {
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) iter.next();

			situacao = fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
			unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());
			helper = new RAFiltroHelper();
			helper.setRegistroAtendimento(registroAtendimento);
			helper.setUnidadeAtual(unidadeAtual);
			helper.setSituacao(situacao.getDescricaoAbreviadaSituacao());
			colecaoRAHelper.add(helper);
		}
		return colecaoRAHelper;
	}

	/**
	 * Removendo um arquivo da coleção
	 */
	private RegistroAtendimentoAnexo obterArquivoParaVisualizacao(String identificacao, Collection colecaoRegistroAtendimentoAnexo) {

		RegistroAtendimentoAnexo registroAtendimentoAnexo = null;

		if (identificacao != null && !identificacao.equals("")) {

			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;

			while (it.hasNext()) {

				anexoColecao = (RegistroAtendimentoAnexo) it.next();

				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)) {
					registroAtendimentoAnexo = anexoColecao;
					break;
				}
			}
		}

		return registroAtendimentoAnexo;
	}

	private void obterDadosReiteracaoRa(Integer numeroRA, Fachada fachada, HttpSession sessao) {

		sessao.removeAttribute("colecaoDadosReiteracao");

		if (numeroRA != null) {
			Collection colecaoDadosReiteracao = fachada.pesquisarDadosReiteracaoRA(numeroRA);

			if (colecaoDadosReiteracao != null && !colecaoDadosReiteracao.isEmpty()) {
				sessao.setAttribute("colecaoDadosReiteracao", colecaoDadosReiteracao);
			}
		}
	}
	
	private ConsultarRegistroAtendimentoActionForm limparCampos(ConsultarRegistroAtendimentoActionForm form) {
		form.setPerfilImovel(null);
		form.setSituacaoRA(null);
		form.setNumeroRaAssociado(null);
		form.setSituacaoRaAssociado(null);
		form.setNumeroRAManual(null);
		form.setIdTipoSolicitacao(null);
		form.setTipoSolicitacao(null);
		form.setIdEspecificacao(null);
		form.setEspecificacao(null);
		form.setTipoAtendimento(null);
		form.setDataAtendimento(null);
		form.setHoraAtendimento(null);
		form.setTempoEsperaInicio(null);
		form.setTempoEsperaTermino(null);
		form.setDataPrevista(null);
		form.setValorSugerido(null);
		form.setIdMeioSolicitacao(null);
		form.setMeioSolicitacao(null);
		form.setIdUnidadeAtendimento(null);
		form.setUnidadeAtendimento(null);
		form.setIdUsuario(null);
		form.setUsuario(null);
		form.setIdUnidadeAtual(null);
		form.setUnidadeAtual(null);
		form.setIdUnidadeAnterior(null);
		form.setUnidadeAnterior(null);
		form.setObservacao(null);
		
		form.setMatriculaImovel(null);
		form.setInscricaoImovel(null);
		form.setRota(null);
		form.setSequencialRota(null);
		
		form.setEnderecoOcorrencia(null);
		form.setPontoReferencia(null);
		form.setNumeroCoordenadaNorte(null);
		form.setNumeroCoordenadaLeste(null);
		form.setIdMunicipio(null);
		form.setMunicipio(null);
		form.setIdBairro(null);
		form.setBairro(null);
		form.setIdAreaBairro(null);
		form.setAreaBairro(null);
		form.setIdLocalidade(null);
		form.setLocalidade(null);
		form.setIdSetorComercial(null);
		form.setSetorComercial(null);
		form.setIdQuadra(null);
		form.setIdDivisaoEsgoto(null);
		form.setDivisaoEsgoto(null);
		form.setLocalOcorrencia(null);
		form.setPavimentoRua(null);
		form.setPavimentoCalcada(null);
		form.setDescricaoLocalOcorrencia(null);
		
		form.setNumeroProtocolo(null);
		form.setIdClienteSolicitante(null);
		form.setClienteSolicitante(null);
		form.setIdUnidadeSolicitante(null);
		form.setUnidadeSolicitante(null);
		form.setIdFuncionarioResponsavel(null);
		form.setFuncionarioResponsavel(null);
		form.setNomeSolicitante(null);
		form.setEnderecoEmail(null);
		form.setEnderecoSolicitante(null);
		form.setPontoReferencia(null);
		form.setFoneDDD(null);
		form.setFone(null);
		form.setFoneRamal(null);
		
		form.setIdUnidadeOrigem(null);
		form.setUnidadeOrigem(null);
		form.setIdUnidadeAtualTramitacao(null);
		form.setUnidadeAtualTramitacao(null);
		form.setDataTramite(null);
		form.setHoraTramite(null);
		form.setIdUsuarioResponsavel(null);
		form.setUsuarioResponsavel(null);
		form.setParecerTramite(null);
		
		form.setNumeroRaAtual(null);
		form.setSituacaoRaAtual(null);
		form.setIdMotivoReativacao(null);
		form.setMotivoReativacao(null);
		form.setDataReativacao(null);
		form.setHoraReativacao(null);
		form.setDataPrevistaRaAtual(null);
		form.setIdUnidadeReativacao(null);
		form.setUnidadeReativacao(null);
		form.setIdUnidadeRaAtual(null);
		form.setUnidadeRaAtual(null);
		form.setObservacaoReativacao(null);
		
		form.setIdMotivoEncerramento(null);
		form.setMotivoEncerramento(null);
		form.setNumeroRaReferencia(null);
		form.setSituacaoRaReferencia(null);
		form.setDataEncerramento(null);
		form.setHoraEncerramento(null);
		form.setDataPrevistaEncerramento(null);
		form.setIdUnidadeEncerramento(null);
		form.setUnidadeEncerramento(null);
		form.setIdUsuarioEncerramento(null);
		form.setUsuarioEncerramento(null);
		form.setParecerEncerramento(null);
		form.setMotivoNaoCobranca(null);
		
		return form;
	}
	
	private void validarRAAtualizacaoCadastral(RegistroAtendimento ra, HttpSession sessao) {
		if (ra.obterNumeroImovelRetorno() != null)
			sessao.setAttribute("permiteImprimirFIC", true);
		else
			sessao.setAttribute("permiteImprimirFIC", false);
	}
}
