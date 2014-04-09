package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAgenciaReguladoraMotReclamacao;
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
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0459] Informar Dados da Agencia Reguladora
 * 
 * @author Kássia Albuquerque
 * @date 27/03/2007
 */

public class ExibirInformarRaDadosAgenciaReguladoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			// Seta o mapeamento de retorno
			ActionForward retorno = actionMapping
					.findForward("informarRaDadosAgenciaReguladora");
	
			InformarRaDadosAgenciaReguladoraActionForm form = (InformarRaDadosAgenciaReguladoraActionForm) actionForm;
	
			Fachada fachada = Fachada.getInstancia();
		
			httpServletRequest.setAttribute("nomeCampo", "numeroRA");
	
			if (httpServletRequest.getParameter("pesquisarRa") != null) {
				Integer idRegistroAtendimento = new Integer(form.getNumeroRA());
	
				ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(idRegistroAtendimento);
	
				// Dados Gerais do Registro de Atendimento
	
				setDadosRA(form, registroAtendimentoHelper);
				setDadosSolicitante(form, registroAtendimentoHelper);
				setDadosEnderecoOcorrencia(form, registroAtendimentoHelper);
				setUnidades(form, registroAtendimentoHelper);
				// Data Prevista p Ag. Reg. Original
				Integer prazoDias = fachada.procurarDiasPazo(new Integer(form.getNumeroRA()));
				Date dataOrigem = Util.adicionarNumeroDiasDeUmaData(new Date(), prazoDias);
				form.setDataPrevisaoOriginal(Util.formatarData(dataOrigem));
				
			} else {
				form.reset(actionMapping, httpServletRequest);
			}
	
			// DADOS DA RECLAMAÇÃO
			FiltroAgenciaReguladoraMotReclamacao filtroAgenciaReguladoraMotReclamacao = new FiltroAgenciaReguladoraMotReclamacao();
	
			filtroAgenciaReguladoraMotReclamacao.adicionarParametro(new ParametroSimples(FiltroAgenciaReguladoraMotReclamacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
	
			Collection colecaoMotivoReclamacao = fachada.pesquisar(filtroAgenciaReguladoraMotReclamacao,AgenciaReguladoraMotReclamacao.class.getName());
	
			httpServletRequest.setAttribute("colecaoMotivoReclamacao",colecaoMotivoReclamacao);
			
			
			return retorno;
		}

	/**
	 * Carrega Unidades (Atendimento e Atual)
	 * 
	 * @param form
	 * @param registroAtendimentoHelper
	 */
	private void setUnidades(InformarRaDadosAgenciaReguladoraActionForm form,
			ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		UnidadeOrganizacional unidadeAtendimento = registroAtendimentoHelper.getUnidadeAtendimento();
		if (unidadeAtendimento != null) {
			form.setUnidadeAtendimentoId("" + unidadeAtendimento.getId());
			form.setUnidadeAtendimentoDescricao(unidadeAtendimento.getDescricao());
		}
		UnidadeOrganizacional unidadeAtual = registroAtendimentoHelper.getUnidadeAtual();
		if (unidadeAtual != null) {
			form.setUnidadeAtualId("" + unidadeAtual.getId());
			form.setUnidadeAtualDescricao(unidadeAtual.getDescricao());
		}
	}

	/**
	 * Carrega Dados do RA
	 * 
	 * @param form
	 * @param registroAtendimentoHelper
	 */
	private void setDadosRA(InformarRaDadosAgenciaReguladoraActionForm form,
			ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		RegistroAtendimento registroAtendimento = registroAtendimentoHelper.getRegistroAtendimento();
		// Dados Gerais do Registro de Atendimento
		
		form.setNumeroRADados("" + registroAtendimento.getId());
		form.setSituacaoRA(registroAtendimentoHelper.getDescricaoSituacaoRA());
		form.setNumeroSituacaoRA(""+registroAtendimentoHelper.getRegistroAtendimento().getCodigoSituacao());
		if (registroAtendimentoHelper.getRAAssociado() != null) {
			form.setNumeroRaAssociado(""+ registroAtendimentoHelper.getRAAssociado().getId());
			form.setSituacaoRaAssociado(registroAtendimentoHelper.getDescricaoSituacaoRAAssociado());
		}
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = registroAtendimento.getSolicitacaoTipoEspecificacao();
		if (solicitacaoTipoEspecificacao != null) {
			if (solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null) {
				form.setTipoSolicitacaoId(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId()+ "");
				form.setTipoSolicitacaoDescricao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());
			}
			form.setEspecificacaoId(solicitacaoTipoEspecificacao.getId() + "");
			form.setEspecificacaoDescricao(solicitacaoTipoEspecificacao.getDescricao());
		}
		if (registroAtendimento.getMeioSolicitacao() != null) {
			form.setMeioSolicitacaoId(registroAtendimento.getMeioSolicitacao().getId()+ "");
			form.setMeioSolicitacaoDescricao(registroAtendimento.getMeioSolicitacao().getDescricao());
		}
		// Imovel
		Imovel imovel = registroAtendimento.getImovel();
		if (imovel != null) {
			form.setMatriculaImovel("" + imovel.getId());
			form.setInscricaoImovel(imovel.getInscricaoFormatada());
		}
		Date dataAtendimento = registroAtendimento.getRegistroAtendimento();
		form.setDataAtendimento(Util.formatarData(dataAtendimento));
		form.setHoraAtendimento(Util.formatarHoraSemData(dataAtendimento));
		form.setDataPrevista(Util.formatarData(registroAtendimento.getDataPrevistaAtual()));
		// Encerramento
		setDadosEncerramento(form, registroAtendimento);

		// Dados necessário p/ inserir o novo RA
		if (registroAtendimento.getLogradouroBairro() != null) {
			form.setLogradouroBairro(registroAtendimento.getLogradouroBairro().getId());
		}
		if (registroAtendimento.getLogradouroCep() != null) {
			form.setLogradouroCep(registroAtendimento.getLogradouroCep().getId());
		}
		form.setComplementoEndereco(registroAtendimento.getComplementoEndereco());
		if (registroAtendimento.getLocalOcorrencia() != null) {
			form.setLocalOcorrencia(registroAtendimento.getLocalOcorrencia().getId());
		}
		if (registroAtendimento.getPavimentoRua() != null) {
			form.setPavimentoRua(registroAtendimento.getPavimentoRua().getId());
		}
		if (registroAtendimento.getPavimentoCalcada() != null) {
			form.setPavimentoCalcada(registroAtendimento.getPavimentoCalcada().getId());
		}
		form.setDescricaoLocalOcorrencia(registroAtendimento.getDescricaoLocalOcorrencia());
	}

	/**
	 * Carrega Dados do RA
	 * 
	 * @param form
	 * @param registroAtendimento
	 */
	private void setDadosEncerramento(InformarRaDadosAgenciaReguladoraActionForm form,RegistroAtendimento registroAtendimento) {
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = registroAtendimento.getAtendimentoMotivoEncerramento();
		if (atendimentoMotivoEncerramento != null) {
			form.setIdMotivoEncerramento(""+ atendimentoMotivoEncerramento.getId());
			form.setMotivoEncerramento(atendimentoMotivoEncerramento.getDescricao());
			Date dataEncerramento = registroAtendimento.getDataEncerramento();
			form.setDataEncerramento(Util.formatarData(dataEncerramento));
		}
	}

	/**
	 * Carrega Dados do Solicitante
	 * 
	 * @param form
	 * @param registroAtendimentoHelper
	 */
	private void setDadosSolicitante(InformarRaDadosAgenciaReguladoraActionForm form,ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {

		// Dados do Solicitante
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = registroAtendimentoHelper.getSolicitante();
		if (registroAtendimentoSolicitante != null) {
			form.setIdRaSolicitante(registroAtendimentoSolicitante.getID());
			Cliente cliente = registroAtendimentoSolicitante.getCliente();
			UnidadeOrganizacional unidadeSolicitante = registroAtendimentoSolicitante.getUnidadeOrganizacional();
			// Caso o principal solicitante do registro de atendimento seja um
			// cliente
			// obter os dados do cliente
			if (cliente != null) {
				form.setIdClienteSolicitante("" + cliente.getId());
				form.setClienteSolicitante(cliente.getNome());
				// Caso o principal solicitante do registro de atendimento seja
				// uma unidade
				// obter os dados da unidade
			} else if (unidadeSolicitante != null) {
				form.setIdUnidadeSolicitante("" + unidadeSolicitante.getId());
				form.setUnidadeSolicitante(unidadeSolicitante.getDescricao());
				// Caso o principal solicitante do registro de atendimento não
				// seja um cliente, nem uma unidade
				// obter os dados do solicitante
			} else {
				form.setNomeSolicitante(registroAtendimentoSolicitante.getSolicitante());
			}
		}
	}

	/**
	 * Carrega Dados do Endereço de Ocorrência
	 * 
	 * @param form
	 * @param registroAtendimentoHelper
	 */
	private void setDadosEnderecoOcorrencia(InformarRaDadosAgenciaReguladoraActionForm form,ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		
		String enderecoOcorrencia = registroAtendimentoHelper.getEnderecoOcorrencia();
		form.setEnderecoOcorrencia(enderecoOcorrencia);
		form.setPontoReferencia(registroAtendimentoHelper.getRegistroAtendimento().getPontoReferencia());

		// Caso o registro atendimento esteja associado a uma área de bairro,
		// obter os dados da área do bairro
		BairroArea bairroArea = registroAtendimentoHelper.getRegistroAtendimento().getBairroArea();
		if (bairroArea != null) {
			form.setBairroId("" + bairroArea.getBairro().getId());
			form.setBairroDescricao(bairroArea.getBairro().getNome());
			form.setAreaBairroId("" + bairroArea.getId());
			form.setAreaBairroDescricao(bairroArea.getNome());
		}
		Localidade localidade = registroAtendimentoHelper.getRegistroAtendimento().getLocalidade();
		if (localidade != null) {
			form.setLocalidadeId("" + localidade.getId());
			form.setLocalidadeDescricao(localidade.getDescricao());
		}
		SetorComercial setorComercial = registroAtendimentoHelper.getRegistroAtendimento().getSetorComercial();
		if (setorComercial != null) {
			form.setSetorComercialId("" + setorComercial.getId());
			form.setSetorComercialCodigo("" + setorComercial.getCodigo());
		}
		Quadra quadra = registroAtendimentoHelper.getRegistroAtendimento().getQuadra();
		if (quadra != null) {
			form.setQuadraId("" + quadra.getId());
			form.setQuadraNumero("" + quadra.getNumeroQuadra());
		}
		DivisaoEsgoto divisaoEsgoto = registroAtendimentoHelper.getRegistroAtendimento().getDivisaoEsgoto();
		if (divisaoEsgoto != null) {
			form.setDivisaoEsgotoId("" + divisaoEsgoto.getId());
			form.setDivisaoEsgotoDescricao(divisaoEsgoto.getDescricao());
		}
	}

}
