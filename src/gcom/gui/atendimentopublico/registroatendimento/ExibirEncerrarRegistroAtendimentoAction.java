package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * ExibirTramitarRegistroAtendimentoAction
 * 
 * @author Leonardo Regis
 * @date   16/08/2006
 * 
 */
public class ExibirEncerrarRegistroAtendimentoAction extends GcomAction {
	/**
	 * Exibe a Tela para Encerrar o RA
	 * 
	 * @author Leonardo Regis
	 * @date 25/08/2006
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("encerrarRegistroAtendimento");
		HttpSession sessao = httpServletRequest.getSession(false);
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// pega o parametro "confirmado" passado na tela de confirmacao de encerrar RA para o Imovel.
		String confirmarEncerramento = (String) httpServletRequest.getParameter("confirmado");
		
		// Caso o usuario na tela de confirmacao(mostrada depois da tela de atualizar imovel),
		// escolha a opcao NAO, ou seja, nao ecerra o RA, mostra a tela de sucesso da atualizacao
		// do Imovel(fluxo normal).
		if(confirmarEncerramento != null && confirmarEncerramento.equalsIgnoreCase("cancelar")){
			
			retorno = actionMapping.findForward("telaSucesso");

			String idImove = (String) sessao.getAttribute("idImovel");
			sessao.removeAttribute("idImovel");
			
			String linkSucesso = (String)sessao.getAttribute("linkSucesso");
			
			if(linkSucesso != null && !linkSucesso.equals("")){
				
				montarPaginaSucesso(httpServletRequest, "Imóvel de matrícula "
						+ idImove
						+ " atualizado com sucesso.",
						"Realizar outra Manutenção de Imóvel",
						"exibirFiltrarImovelAction.do?menu=sim",
						"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?idImovel="
								+ idImove,
						"Informar Ocorrências / Anormalidades",
						"Retornar ao Consultar Imóvel.",
						linkSucesso);
				
				sessao.removeAttribute("linkSucesso");
				
			}else if(sessao.getAttribute("caminhoVoltarPromais")!=null){
				
				montarPaginaSucesso(httpServletRequest, "Imóvel de matrícula "
						+ idImove
						+ " atualizado com sucesso.",
						"Realizar outra Manutenção de Imóvel",
						"exibirFiltrarImovelAction.do?menu=sim",
						"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?idImovel="
								+ idImove,
						"Informar Ocorrências / Anormalidades",
						"Retornar ao Consultar Imóvel.",
						(String)sessao.getAttribute("caminhoVoltarPromais")+".do?promais=nao");
				
				sessao.setAttribute("promaisExecutado", "sim");
				sessao.setAttribute("idImovelPromaisExecutado", idImove);
				
			}else{
				montarPaginaSucesso(httpServletRequest, "Imóvel de matrícula "
						+ idImove
						+ " atualizado com sucesso.",
						"Realizar outra Manutenção de Imóvel",
						"exibirFiltrarImovelAction.do?menu=sim",
						"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?idImovel="
								+ idImove,
						"Informar Ocorrências / Anormalidades");
			}
			
			
			return retorno;
		}
		
		
		EncerrarRegistroAtendimentoActionForm encerrarRegistroAtendimentoActionForm = (EncerrarRegistroAtendimentoActionForm) actionForm;
		// Reseta Tramitação
		if (encerrarRegistroAtendimentoActionForm.getResetarEncerramento().equalsIgnoreCase("true")) {
			encerrarRegistroAtendimentoActionForm.resetarEncerramento();
		}
		
		Fachada fachada = Fachada.getInstancia();
		
		ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = null;
		
		if (encerrarRegistroAtendimentoActionForm.getNumeroRA() != null) {
			registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(new Integer(encerrarRegistroAtendimentoActionForm.getNumeroRA()));
		} else if(httpServletRequest.getAttribute("numeroRA") != null){
			registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(new Integer(httpServletRequest.getAttribute("numeroRA").toString()));
		} else if(sessao.getAttribute("numeroRA") != null){
			registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(new Integer(sessao.getAttribute("numeroRA").toString()));
		}
		
		setUsuarioRegistro(encerrarRegistroAtendimentoActionForm, usuario);
		setDadosRA(encerrarRegistroAtendimentoActionForm, registroAtendimentoHelper);
		setDadosSolicitante(encerrarRegistroAtendimentoActionForm, registroAtendimentoHelper);
		setDadosEnderecoOcorrencia(encerrarRegistroAtendimentoActionForm, registroAtendimentoHelper);
		setUnidades(encerrarRegistroAtendimentoActionForm, registroAtendimentoHelper);
		encerrarRegistroAtendimentoActionForm.setDataEncerramento(Util.formatarData(new Date()));
		encerrarRegistroAtendimentoActionForm.setHoraEncerramento(Util.formatarHoraSemSegundos(new Date()));
		
		
		
		//Colocado por Raphael Rossiter em 05/02/2008
		this.setDadosDebito(encerrarRegistroAtendimentoActionForm, registroAtendimentoHelper, 
		fachada, sessao, usuario, httpServletRequest);
		
		this.calcularValores(httpServletRequest, registroAtendimentoHelper.getRegistroAtendimento()
		.getSolicitacaoTipoEspecificacao(), encerrarRegistroAtendimentoActionForm);
		
		
		// Valida Pré-Encerramento
		fachada.validarPreEncerramentoRASemTarifaSocial(registroAtendimentoHelper.getRegistroAtendimento(), usuario, new Short(encerrarRegistroAtendimentoActionForm.getIndicadorAutorizacaoManutencaoRA()));
		getMotivoEncerramentoCollection(encerrarRegistroAtendimentoActionForm, sessao);
		// Número do RA de Referência
	    if (encerrarRegistroAtendimentoActionForm.getNumeroRAReferencia() != null &&
				!encerrarRegistroAtendimentoActionForm.getNumeroRAReferencia().equals("")) {
			getNumeroRAReferencia(encerrarRegistroAtendimentoActionForm, sessao);
		}
	    // Valida Indicador Duplicidade
	    if (encerrarRegistroAtendimentoActionForm.getMotivoEncerramentoId() != null &&
				!encerrarRegistroAtendimentoActionForm.getMotivoEncerramentoId().equals("")) {
	    	if (!encerrarRegistroAtendimentoActionForm.getMotivoEncerramentoId().equals("-1")) {
	    		getIndicadorDuplicidade(encerrarRegistroAtendimentoActionForm, sessao);
	    	} else {
		    	encerrarRegistroAtendimentoActionForm.setIndicadorDuplicidade("2");
		    }
	    } else {
	    	encerrarRegistroAtendimentoActionForm.setIndicadorDuplicidade("2");
	    }
		return retorno;
	}

	/**
	 * Carrega Usuário de Registro
	 *
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 *
	 * @param form
	 */		
	private void setUsuarioRegistro(EncerrarRegistroAtendimentoActionForm form, Usuario usuario) {
		form.setUsuarioRegistroId(usuario.getId()+"");
		form.setUsuarioRegistroNome(usuario.getNomeUsuario());
		form.setUsuarioRegistroUnidade(usuario.getUnidadeOrganizacional().getId()+"");
		form.setUsuarioRegistroUnidadeIndicadorTarifaSocial(usuario.getUnidadeOrganizacional().getIndicadorTarifaSocial()+"");
		form.setUsuarioRegistroUnidadeIndicadorCentralAtendimento(usuario.getUnidadeOrganizacional().getIndicadorCentralAtendimento()+"");
	}
	
	/**
	 * Carrega Unidades (Atendimento e Atual)
	 *
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 *
	 * @param form
	 * @param registroAtendimentoHelper
	 */		
	private void setUnidades(EncerrarRegistroAtendimentoActionForm form, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		UnidadeOrganizacional unidadeAtendimento =  registroAtendimentoHelper.getUnidadeAtendimento();
		if(unidadeAtendimento != null){
			form.setUnidadeAtendimentoId(""+unidadeAtendimento.getId());
			form.setUnidadeAtendimentoDescricao(unidadeAtendimento.getDescricao());		
		}
		UnidadeOrganizacional unidadeAtual = registroAtendimentoHelper.getUnidadeAtual();
		Fachada fachada = Fachada.getInstancia();
		if(unidadeAtual != null){
			form.setUnidadeAtualId(""+unidadeAtual.getId());
			form.setIndicadorAutorizacaoManutencaoRA(fachada.obterIndicadorAutorizacaoManutencaoRA(unidadeAtual.getId(), new Integer(form.getUsuarioRegistroId()))+"");
			form.setUnidadeAtualDescricao(unidadeAtual.getDescricao());
			if (unidadeAtual.getUnidadeTipo() != null) {
				form.setUnidadeAtualCodigoTipo(unidadeAtual.getUnidadeTipo().getCodigoTipo());
			}
			if (unidadeAtual.getUnidadeCentralizadora() != null) {
				form.setUnidadeAtualIdCentralizadora(unidadeAtual.getUnidadeCentralizadora().getId()+"");
			}
		}
	}

	/**
	 * Carrega Dados do RA
	 *
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 *
	 * @param form
	 * @param registroAtendimentoHelper
	 */	
	private void setDadosRA(EncerrarRegistroAtendimentoActionForm form, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		RegistroAtendimento registroAtendimento = registroAtendimentoHelper.getRegistroAtendimento();
		//Dados Gerais do Registro de Atendimento
		form.setDataConcorrenciaRA(registroAtendimento.getUltimaAlteracao());
		form.setNumeroRA(""+registroAtendimento.getId());
		form.setSituacaoRA(registroAtendimentoHelper.getDescricaoSituacaoRA());
		form.setCodigoSituacaoRA(registroAtendimento.getCodigoSituacao()+"");
		if(registroAtendimentoHelper.getRAAssociado() != null) {
			form.setNumeroRaAssociado(""+registroAtendimentoHelper.getRAAssociado().getId());
			form.setSituacaoRaAssociado(registroAtendimentoHelper.getDescricaoSituacaoRAAssociado());
		}
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao =	registroAtendimento.getSolicitacaoTipoEspecificacao();
		if(solicitacaoTipoEspecificacao != null){
			if(solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null){
				form.setTipoSolicitacaoId(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId()+"");
				form.setTipoSolicitacaoDescricao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());
				form.setTipoSolicitacaoIndicadorTarifaSocial(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getIndicadorTarifaSocial()+"");
			}
			form.setEspecificacaoId(solicitacaoTipoEspecificacao.getId()+"");
			form.setEspecificacaoDescricao(solicitacaoTipoEspecificacao.getDescricao());
			form.setEspecificacaoIndicadorParecer(solicitacaoTipoEspecificacao.getIndicadorParecerEncerramento()+"");
		}
		if(registroAtendimento.getMeioSolicitacao() != null){
			form.setMeioSolicitacaoId(registroAtendimento.getMeioSolicitacao().getId()+"");
			form.setMeioSolicitacaoDescricao(registroAtendimento.getMeioSolicitacao().getDescricao());	
		}
		//Imovel
		Imovel imovel = registroAtendimento.getImovel();
		if(imovel != null){
			form.setMatriculaImovel(""+imovel.getId());
			form.setInscricaoImovel(imovel.getInscricaoFormatada());
		}
		Date dataAtendimento = registroAtendimento.getRegistroAtendimento();
		form.setDataAtendimento(Util.formatarData(dataAtendimento));		
		form.setHoraAtendimento(Util.formatarHoraSemData(dataAtendimento));
		form.setDataPrevista(Util.formatarData(registroAtendimento.getDataPrevistaAtual()));
		// Encerramento
		setDadosEncerramento(form, registroAtendimento);
	}

	/**
	 * Carrega Dados do RA
	 *
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 *
	 * @param form
	 * @param registroAtendimento
	 */		
	private void setDadosEncerramento(EncerrarRegistroAtendimentoActionForm form, RegistroAtendimento registroAtendimento) {
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = registroAtendimento.getAtendimentoMotivoEncerramento();
		if(atendimentoMotivoEncerramento != null){
			form.setIdMotivoEncerramento(""+atendimentoMotivoEncerramento.getId());	
			form.setMotivoEncerramento(atendimentoMotivoEncerramento.getDescricao());
			Date dataEncerramento = registroAtendimento.getDataEncerramento();
			form.setDataEncerramentoRA(Util.formatarData(dataEncerramento));
			form.setDataEncerramento(Util.formatarData(new Date()));
			form.setHoraEncerramento(Util.formatarHoraSemSegundos(new Date()));
		}
	}

	/**
	 * Carrega Dados do Solicitante
	 *
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 *
	 * @param form
	 * @param registroAtendimentoHelper
	 */	
	private void setDadosSolicitante(EncerrarRegistroAtendimentoActionForm form, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		//Dados do Solicitante
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = registroAtendimentoHelper.getSolicitante();
		if(registroAtendimentoSolicitante != null){
			Cliente cliente = registroAtendimentoSolicitante.getCliente();
			UnidadeOrganizacional unidadeSolicitante = registroAtendimentoSolicitante.getUnidadeOrganizacional();
			//Caso o principal solicitante do registro de atendimento seja um cliente
			//obter os dados do cliente
			if(cliente != null){
				form.setIdClienteSolicitante(""+cliente.getId());
				form.setClienteSolicitante(cliente.getNome());	
			//Caso o principal solicitante do registro de atendimento seja uma unidade
			//obter os dados da unidade
			}else if(unidadeSolicitante != null){
				form.setIdUnidadeSolicitante(""+unidadeSolicitante.getId());
				form.setUnidadeSolicitante(unidadeSolicitante.getDescricao());	
			//Caso o principal solicitante do registro de atendimento não seja um cliente, nem uma unidade
			//obter os dados do solicitante
			}else{
				form.setNomeSolicitante(registroAtendimentoSolicitante.getSolicitante());
			}
		}
	}
	
	/**
	 * Carrega Dados do Endereço de Ocorrência
	 *
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 *
	 * @param form
	 * @param registroAtendimentoHelper
	 */	
	private void setDadosEnderecoOcorrencia(EncerrarRegistroAtendimentoActionForm form, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		String enderecoOcorrencia = registroAtendimentoHelper.getEnderecoOcorrencia();
		form.setEnderecoOcorrencia(enderecoOcorrencia);
		form.setPontoReferencia(registroAtendimentoHelper.getRegistroAtendimento().getPontoReferencia());
		
		//Caso o registro atendimento esteja associado a uma área de bairro,
		//obter os dados da área do bairro
		BairroArea bairroArea = registroAtendimentoHelper.getRegistroAtendimento().getBairroArea();
		if(bairroArea != null){
			form.setBairroId(""+bairroArea.getBairro().getId());
			form.setBairroDescricao(bairroArea.getBairro().getNome());
			form.setAreaBairroId(""+bairroArea.getId());
			form.setAreaBairroDescricao(bairroArea.getNome());
		}
		Localidade localidade = registroAtendimentoHelper.getRegistroAtendimento().getLocalidade();
		if(localidade != null){
			form.setLocalidadeId(""+localidade.getId());
			form.setLocalidadeDescricao(localidade.getDescricao());
		}
		SetorComercial setorComercial = registroAtendimentoHelper.getRegistroAtendimento().getSetorComercial();
		if(setorComercial != null){
			form.setSetorComercialId(""+setorComercial.getId());
			form.setSetorComercialDescricao(setorComercial.getDescricao());
		}
		Quadra quadra = registroAtendimentoHelper.getRegistroAtendimento().getQuadra();
		if(quadra != null){
			form.setQuadraId(""+quadra.getId());
			form.setQuadraDescricao(""+quadra.getNumeroQuadra());
		}
		DivisaoEsgoto divisaoEsgoto = registroAtendimentoHelper.getRegistroAtendimento().getDivisaoEsgoto();
		if(divisaoEsgoto != null){
			form.setDivisaoEsgotoId(""+divisaoEsgoto.getId());
			form.setDivisaoEsgotoDescricao(divisaoEsgoto.getDescricao());			
		}
	}

	/**
	 * Carrega coleção de motivo de encerramento.
	 *
	 * @author Leonardo Regis
	 * @date 25/08/2006
	 * @param sessao
	 */
	private void getMotivoEncerramentoCollection(EncerrarRegistroAtendimentoActionForm form, HttpSession sessao) {
		Fachada fachada = Fachada.getInstancia();
		// Filtra Motivo de Encerramento
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		
		Collection colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
		if (colecaoAtendimentoMotivoEncerramento != null	&& !colecaoAtendimentoMotivoEncerramento.isEmpty()) {
			sessao.setAttribute("colecaoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Motivo do Encerramento");
		}
	}
	
	/**
	 * Recupera RA de Referência
	 *
	 * @author Leonardo Regis
	 * @date 25/08/2006
	 *
	 * @param form
	 * @param sessao
	 */
	private void getNumeroRAReferencia(EncerrarRegistroAtendimentoActionForm form, HttpSession sessao) {
		// [F0003] Valida RA de Referência
		//if (isValidateObject(form)) {
			Fachada fachada = Fachada.getInstancia();
			RegistroAtendimento registroAtendimento = fachada.validarRAReferencia(new Integer(form.getNumeroRA()), new Integer(form.getNumeroRAReferencia()));
			if (registroAtendimento != null) {
				form.setNumeroRAReferenciaRetorno(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
				form.setValidaNumeroRAReferencia("false");
				sessao.setAttribute("raEncontrada","true");
			} else {
				sessao.removeAttribute("raEncontrada");
				form.setNumeroRAReferencia("");
				form.setNumeroRAReferenciaRetorno("Registro de Atendimento Inexistente");
			}
		//}
	}

	/**
	 * Valida Objeto com <Enter> da tela
	 *
	 * @author Leonardo Regis
	 * @date 24/08/2006
	 *
	 * @return está validando algum objeto através do enter?
	 */
	/*private boolean isValidateObject(EncerrarRegistroAtendimentoActionForm form) {
		boolean toReturn = false;
		if (form.getValidaNumeroRAReferencia().equalsIgnoreCase("true")) {
			toReturn = true;
		}
		return toReturn;		
	}*/
	
	/**
	 * Seta indicador de duplicidade.
	 *
	 * @author Leonardo Regis
	 * @date 28/09/2006
	 * 
	 * @param form
	 * @param sessao
	 */
	private void getIndicadorDuplicidade(EncerrarRegistroAtendimentoActionForm form, HttpSession sessao) {
		Fachada fachada = Fachada.getInstancia();
		// Filtra Motivo de Encerramento
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.ID, form.getMotivoEncerramentoId()));
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = null;
		Collection colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
		if (colecaoAtendimentoMotivoEncerramento != null	&& !colecaoAtendimentoMotivoEncerramento.isEmpty()) {
			atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento)Util.retonarObjetoDeColecao(colecaoAtendimentoMotivoEncerramento);
			form.setIndicadorDuplicidade(atendimentoMotivoEncerramento.getIndicadorDuplicidade()+"");
		}
	}
	
	/**
	 * Carrega Dados do Solicitante
	 *
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 *
	 * @param form
	 * @param registroAtendimentoHelper
	 */	
	private void setDadosDebito(EncerrarRegistroAtendimentoActionForm form, 
			ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper, Fachada fachada, HttpSession sessao,
			Usuario usuarioLogado, HttpServletRequest httpServletRequest) {
		
		
		String calculaValores = httpServletRequest.getParameter("calculaValores");
		
		/*
		 * Só permitir informar o débito, se for CONCLUSÃO DO SERVIÇO
		 */
		if (form.getMotivoEncerramentoId() != null &&
			!form.getMotivoEncerramentoId().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)) &&
			form.getMotivoEncerramentoId().equals(String.valueOf(
			AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO.toString()))){
		
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = 
			registroAtendimentoHelper.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
				
			//Tipo do débito
			if (solicitacaoTipoEspecificacao.getDebitoTipo() != null){
					
				form.setIdTipoDebito(solicitacaoTipoEspecificacao.getDebitoTipo().getId().toString());
				form.setDescricaoTipoDebito(solicitacaoTipoEspecificacao.getDebitoTipo().getDescricao());
					
				//Valor do débito
				BigDecimal valorDebito = null;
				if (solicitacaoTipoEspecificacao.getValorDebito() != null){
					valorDebito = solicitacaoTipoEspecificacao.getValorDebito();
					form.setValorDebito(Util.formataBigDecimal(valorDebito,2,true));
				}
					
				//Motivo da não cobrança
				Collection colecaoServicoNaoCobrancaMotivo = (Collection) 
				sessao.getAttribute("colecaoServicoNaoCobrancaMotivo");
					
				if (colecaoServicoNaoCobrancaMotivo == null) {
						
					FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();
					
					filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

					colecaoServicoNaoCobrancaMotivo = fachada.pesquisar(filtroServicoNaoCobrancaMotivo,ServicoNaoCobrancaMotivo.class.getName());

					if (colecaoServicoNaoCobrancaMotivo != null && !colecaoServicoNaoCobrancaMotivo.isEmpty()) {
						sessao.setAttribute("colecaoServicoNaoCobrancaMotivo", colecaoServicoNaoCobrancaMotivo);
					} else {
						throw new ActionServletException("atencao.naocadastrado", null,
							"Motivo da Não Cobrança");
					}
				}
					
					
				//-----------------------------------------------------------
				// Verificar permissão especial
				boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
					
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				}else{
					form.setPercentualCobranca("100");
					form.setQuantidadeParcelas("1");
						
					if (valorDebito != null){
						form.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
					}
				}
				// -----------------------------------------------------------
					
					
				//------------------------------------------------------------
				//[Alteração de Valor]
				this.permitirAlteracaoValor(solicitacaoTipoEspecificacao, form);
				//------------------------------------------------------------
			}
		}
		else if (calculaValores == null || calculaValores.equals("")){
			
			this.limparDadosDebito(form, sessao);
		}
		
	}
	
	/*
	 * [Alteração de Valor]
	 * autor: Raphael Rossiter
	 */
	private void permitirAlteracaoValor(SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao, 
			EncerrarRegistroAtendimentoActionForm form){
		
		if (solicitacaoTipoEspecificacao.getValorDebito() == null ||
			solicitacaoTipoEspecificacao.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}
	
	/*
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Calcular valor da prestação com juros para encerramento de RA
	 * 
	 * autor: Raphael Rossiter
	 * data: 07/03/2008
	 */
	private BigDecimal calcularValores(HttpServletRequest httpServletRequest, 
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao, EncerrarRegistroAtendimentoActionForm form){
		
		
		BigDecimal valorDebito = new BigDecimal(0);
		if (form.getValorDebito() != null && !form.getValorDebito().equals("")){
			valorDebito = new BigDecimal(form.getValorDebito().replace(",","."));
		}
		
		String calculaValores = httpServletRequest.getParameter("calculaValores");
		
		if(calculaValores != null && calculaValores.equals("S")){
			
			BigDecimal[] valorCalculo = this.getFachada().calcularValorPrestacaoAtendimentoPublico(
			solicitacaoTipoEspecificacao.getIndicadorCobrarJuros(), valorDebito, 
			new Integer(form.getQuantidadeParcelas()), form.getPercentualCobranca());
			
			BigDecimal valorPrestacao = valorCalculo[0];
			
			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(valorPrestacao,2,true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}						
			
		}
		
		return valorDebito;
	}
	
	/*
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Limpar as informações relacionadas a geração do débito
	 * 
	 * autor: Raphael Rossiter
	 * data: 13/03/2008
	 */
	private void limparDadosDebito(EncerrarRegistroAtendimentoActionForm form, HttpSession sessao){
		
		form.setIdTipoDebito("");
		form.setDescricaoTipoDebito("");
		form.setValorDebito("");
		form.setValorParcelas("");
		form.setQuantidadeParcelas("");
		form.setPercentualCobranca("-1");
		form.setMotivoNaoCobranca("-1");
		
		sessao.removeAttribute("colecaoServicoNaoCobrancaMotivo");
	}
}
