/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
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
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
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
 * ExibirTramitarRegistroAtendimentoAction
 * 
 * @author Leonardo Regis
 * @date   16/08/2006
 * 
 */
public class ExibirTramitarRegistroAtendimentoAction extends GcomAction {
	/**
	 * Exibe a Tela para Tramitar o RA
	 * 
	 * @author Leonardo Regis
	 * @date 16/06/2006
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("tramitarRegistroAtendimento");
		TramitarRegistroAtendimentoActionForm tramitarRegistroAtendimentoActionForm = (TramitarRegistroAtendimentoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");		

		//Verifica se o usuário deseja desfezar as alterações efetuadas
		if (httpServletRequest.getParameter("desfazer") != null) {
			tramitarRegistroAtendimentoActionForm.resetarTramitacao();
		}
		
		// Reseta Tramitação
		if (tramitarRegistroAtendimentoActionForm.getResetarTramitacao().equalsIgnoreCase("true")) {
			tramitarRegistroAtendimentoActionForm.resetarTramitacao();
		}
		
		if (tramitarRegistroAtendimentoActionForm.getValidaUnidadeDestino().equalsIgnoreCase("false") &&
				tramitarRegistroAtendimentoActionForm.getValidaUsuarioResponsavel().equalsIgnoreCase("false")) {
			Fachada fachada = Fachada.getInstancia();
			
			ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = null;
			
			if (tramitarRegistroAtendimentoActionForm.getNumeroRA() != null) {
				registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(new Integer(tramitarRegistroAtendimentoActionForm.getNumeroRA()));
			} else {
				registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(new Integer(httpServletRequest.getAttribute("numeroRA").toString()));
			}
			
			setUsuarioRegistro(tramitarRegistroAtendimentoActionForm, usuario);
			if (tramitarRegistroAtendimentoActionForm.getUsuarioResponsavelId() == null ||
					tramitarRegistroAtendimentoActionForm.getUsuarioResponsavelId().equals("")) {
				tramitarRegistroAtendimentoActionForm.setUsuarioResponsavelId(usuario.getId().toString());
				getUsuarioResponsavel(tramitarRegistroAtendimentoActionForm, sessao);
			}
			setDadosRA(tramitarRegistroAtendimentoActionForm, registroAtendimentoHelper);
			setDadosSolicitante(tramitarRegistroAtendimentoActionForm, registroAtendimentoHelper);
			setDadosEnderecoOcorrencia(tramitarRegistroAtendimentoActionForm, registroAtendimentoHelper);
			setUnidades(tramitarRegistroAtendimentoActionForm, registroAtendimentoHelper);
			tramitarRegistroAtendimentoActionForm.setDataTramite(Util.formatarData(new Date()));
			tramitarRegistroAtendimentoActionForm.setHoraTramite(Util.formatarHoraSemSegundos(new Date()));
		} else {
			// Unidade de Destino
		    if (tramitarRegistroAtendimentoActionForm.getUnidadeDestinoId() != null &&
					!tramitarRegistroAtendimentoActionForm.getUnidadeDestinoId().equals("")) {
				getUnidadeDestino(tramitarRegistroAtendimentoActionForm, sessao);
			}
		    // Usuário Responsável
			if (tramitarRegistroAtendimentoActionForm.getUsuarioResponsavelId() != null &&
					!tramitarRegistroAtendimentoActionForm.getUsuarioResponsavelId().equals("")) {
				getUsuarioResponsavel(tramitarRegistroAtendimentoActionForm, sessao);
			}else{
				tramitarRegistroAtendimentoActionForm.setUsuarioResponsavelId(usuario.getId().toString());
				getUsuarioResponsavel(tramitarRegistroAtendimentoActionForm, sessao);
			}
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
	private void setUsuarioRegistro(TramitarRegistroAtendimentoActionForm tramitarRegistroAtendimentoActionForm, Usuario usuario) {
		tramitarRegistroAtendimentoActionForm.setUsuarioRegistroId(usuario.getId()+"");
		tramitarRegistroAtendimentoActionForm.setUsuarioRegistroNome(usuario.getNomeUsuario());
		tramitarRegistroAtendimentoActionForm.setUsuarioRegistroUnidade(usuario.getUnidadeOrganizacional().getId()+"");
		tramitarRegistroAtendimentoActionForm.setUsuarioRegistroUnidadeIndicadorTarifaSocial(usuario.getUnidadeOrganizacional().getIndicadorTarifaSocial()+"");
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
	private void setUnidades(TramitarRegistroAtendimentoActionForm tramitarRegistroAtendimentoActionForm, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		UnidadeOrganizacional unidadeAtendimento =  registroAtendimentoHelper.getUnidadeAtendimento();
		if(unidadeAtendimento != null){
			tramitarRegistroAtendimentoActionForm.setUnidadeAtendimentoId(""+unidadeAtendimento.getId());
			tramitarRegistroAtendimentoActionForm.setUnidadeAtendimentoDescricao(unidadeAtendimento.getDescricao());		
		}
		UnidadeOrganizacional unidadeAtual = registroAtendimentoHelper.getUnidadeAtual();
		if(unidadeAtual != null){
			tramitarRegistroAtendimentoActionForm.setUnidadeAtualId(""+unidadeAtual.getId());
			tramitarRegistroAtendimentoActionForm.setUnidadeAtualDescricao(unidadeAtual.getDescricao());
			if (unidadeAtual.getUnidadeTipo() != null) {
			tramitarRegistroAtendimentoActionForm.setUnidadeAtualCodigoTipo(unidadeAtual.getUnidadeTipo().getCodigoTipo());
			}
			if (unidadeAtual.getUnidadeCentralizadora() != null) {
				tramitarRegistroAtendimentoActionForm.setUnidadeAtualIdCentralizadora(unidadeAtual.getUnidadeCentralizadora().getId()+"");
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
	private void setDadosRA(TramitarRegistroAtendimentoActionForm tramitarRegistroAtendimentoActionForm, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		RegistroAtendimento registroAtendimento = registroAtendimentoHelper.getRegistroAtendimento();
		//Dados Gerais do Registro de Atendimento
		tramitarRegistroAtendimentoActionForm.setDataConcorrenciaRA(registroAtendimento.getUltimaAlteracao());
		tramitarRegistroAtendimentoActionForm.setNumeroRA(""+registroAtendimento.getId());
		tramitarRegistroAtendimentoActionForm.setSituacaoRA(registroAtendimentoHelper.getDescricaoSituacaoRA());
		tramitarRegistroAtendimentoActionForm.setCodigoSituacaoRA(registroAtendimento.getCodigoSituacao()+"");
		if(registroAtendimentoHelper.getRAAssociado() != null) {
			tramitarRegistroAtendimentoActionForm.setNumeroRaAssociado(""+registroAtendimentoHelper.getRAAssociado().getId());
			tramitarRegistroAtendimentoActionForm.setSituacaoRaAssociado(registroAtendimentoHelper.getDescricaoSituacaoRAAssociado());
		}
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao =	registroAtendimento.getSolicitacaoTipoEspecificacao();
		if(solicitacaoTipoEspecificacao != null){
			if(solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null){
				tramitarRegistroAtendimentoActionForm.setTipoSolicitacaoId(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId()+"");
				tramitarRegistroAtendimentoActionForm.setTipoSolicitacaoDescricao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());
				tramitarRegistroAtendimentoActionForm.setTipoSolicitacaoIndicadorTarifaSocial(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getIndicadorTarifaSocial()+"");
			}
			tramitarRegistroAtendimentoActionForm.setEspecificacaoId(solicitacaoTipoEspecificacao.getId()+"");
			tramitarRegistroAtendimentoActionForm.setEspecificacaoDescricao(solicitacaoTipoEspecificacao.getDescricao());		
		}
		if(registroAtendimento.getMeioSolicitacao() != null){
			tramitarRegistroAtendimentoActionForm.setMeioSolicitacaoId(registroAtendimento.getMeioSolicitacao().getId()+"");
			tramitarRegistroAtendimentoActionForm.setMeioSolicitacaoDescricao(registroAtendimento.getMeioSolicitacao().getDescricao());	
		}
		//Imovel
		Imovel imovel = registroAtendimento.getImovel();
		if(imovel != null){
			tramitarRegistroAtendimentoActionForm.setMatriculaImovel(""+imovel.getId());
			tramitarRegistroAtendimentoActionForm.setInscricaoImovel(imovel.getInscricaoFormatada());
		}
		Date dataAtendimento = registroAtendimento.getRegistroAtendimento();
		tramitarRegistroAtendimentoActionForm.setDataAtendimento(Util.formatarData(dataAtendimento));		
		tramitarRegistroAtendimentoActionForm.setHoraAtendimento(Util.formatarHoraSemData(dataAtendimento));
		tramitarRegistroAtendimentoActionForm.setDataPrevista(Util.formatarData(registroAtendimento.getDataPrevistaAtual()));
		// Encerramento
		setDadosEncerramento(tramitarRegistroAtendimentoActionForm, registroAtendimento);
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
	private void setDadosEncerramento(TramitarRegistroAtendimentoActionForm tramitarRegistroAtendimentoActionForm, RegistroAtendimento registroAtendimento) {
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = registroAtendimento.getAtendimentoMotivoEncerramento();
		if(atendimentoMotivoEncerramento != null){
			tramitarRegistroAtendimentoActionForm.setIdMotivoEncerramento(""+atendimentoMotivoEncerramento.getId());	
			tramitarRegistroAtendimentoActionForm.setMotivoEncerramento(atendimentoMotivoEncerramento.getDescricao());
			Date dataEncerramento = registroAtendimento.getDataEncerramento();
			tramitarRegistroAtendimentoActionForm.setDataEncerramento(Util.formatarData(dataEncerramento));
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
	private void setDadosSolicitante(TramitarRegistroAtendimentoActionForm tramitarRegistroAtendimentoActionForm, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		//Dados do Solicitante
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = registroAtendimentoHelper.getSolicitante();
		if(registroAtendimentoSolicitante != null){
			Cliente cliente = registroAtendimentoSolicitante.getCliente();
			UnidadeOrganizacional unidadeSolicitante = registroAtendimentoSolicitante.getUnidadeOrganizacional();
			//Caso o principal solicitante do registro de atendimento seja um cliente
			//obter os dados do cliente
			if(cliente != null){
				tramitarRegistroAtendimentoActionForm.setIdClienteSolicitante(""+cliente.getId());
				tramitarRegistroAtendimentoActionForm.setClienteSolicitante(cliente.getNome());	
			//Caso o principal solicitante do registro de atendimento seja uma unidade
			//obter os dados da unidade
			}else if(unidadeSolicitante != null){
				tramitarRegistroAtendimentoActionForm.setIdUnidadeSolicitante(""+unidadeSolicitante.getId());
				tramitarRegistroAtendimentoActionForm.setUnidadeSolicitante(unidadeSolicitante.getDescricao());	
			//Caso o principal solicitante do registro de atendimento não seja um cliente, nem uma unidade
			//obter os dados do solicitante
			}else{
				tramitarRegistroAtendimentoActionForm.setNomeSolicitante(registroAtendimentoSolicitante.getSolicitante());
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
	private void setDadosEnderecoOcorrencia(TramitarRegistroAtendimentoActionForm tramitarRegistroAtendimentoActionForm, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		String enderecoOcorrencia = registroAtendimentoHelper.getEnderecoOcorrencia();
		tramitarRegistroAtendimentoActionForm.setEnderecoOcorrencia(enderecoOcorrencia);
		tramitarRegistroAtendimentoActionForm.setPontoReferencia(registroAtendimentoHelper.getRegistroAtendimento().getPontoReferencia());
		
		//Caso o registro atendimento esteja associado a uma área de bairro,
		//obter os dados da área do bairro
		BairroArea bairroArea = registroAtendimentoHelper.getRegistroAtendimento().getBairroArea();
		if(bairroArea != null){
			tramitarRegistroAtendimentoActionForm.setBairroId(""+bairroArea.getBairro().getId());
			tramitarRegistroAtendimentoActionForm.setBairroDescricao(bairroArea.getBairro().getNome());
			tramitarRegistroAtendimentoActionForm.setAreaBairroId(""+bairroArea.getId());
			tramitarRegistroAtendimentoActionForm.setAreaBairroDescricao(bairroArea.getNome());
		}
		Localidade localidade = registroAtendimentoHelper.getRegistroAtendimento().getLocalidade();
		if(localidade != null){
			tramitarRegistroAtendimentoActionForm.setLocalidadeId(""+localidade.getId());
			tramitarRegistroAtendimentoActionForm.setLocalidadeDescricao(localidade.getDescricao());
		}
		SetorComercial setorComercial = registroAtendimentoHelper.getRegistroAtendimento().getSetorComercial();
		if(setorComercial != null){
			tramitarRegistroAtendimentoActionForm.setSetorComercialId(""+setorComercial.getId());
			tramitarRegistroAtendimentoActionForm.setSetorComercialDescricao(setorComercial.getDescricao());
		}
		Quadra quadra = registroAtendimentoHelper.getRegistroAtendimento().getQuadra();
		if(quadra != null){
			tramitarRegistroAtendimentoActionForm.setQuadraId(""+quadra.getId());
			tramitarRegistroAtendimentoActionForm.setQuadraDescricao(""+quadra.getNumeroQuadra());
		}
		DivisaoEsgoto divisaoEsgoto = registroAtendimentoHelper.getRegistroAtendimento().getDivisaoEsgoto();
		if(divisaoEsgoto != null){
			tramitarRegistroAtendimentoActionForm.setDivisaoEsgotoId(""+divisaoEsgoto.getId());
			tramitarRegistroAtendimentoActionForm.setDivisaoEsgotoDescricao(divisaoEsgoto.getDescricao());			
		}
	}

	/**
	 * Recupera Unidade de Destino
	 *
	 * @author Leonardo Regis
	 * @date 17/08/2006
	 *
	 * @param form
	 */
	private void getUnidadeDestino(TramitarRegistroAtendimentoActionForm form, HttpSession sessao) {
		// [F0004] Valida Unidade de Destino
		Fachada fachada = Fachada.getInstancia();
		// Filtra Unidade de Destino
		FiltroUnidadeOrganizacional filtroUnidadeDestino = new FiltroUnidadeOrganizacional();
		filtroUnidadeDestino.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getUnidadeDestinoId()));
		filtroUnidadeDestino.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		//filtroUnidadeDestino.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo.codigoTipo");
		// Recupera Unidade de Destino
		Collection colecaoUnidadeDestino = fachada.pesquisar(filtroUnidadeDestino, UnidadeOrganizacional.class.getName());
		if (colecaoUnidadeDestino != null && !colecaoUnidadeDestino.isEmpty()) {
			UnidadeOrganizacional unidadeDestino = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeDestino);
			form.setUnidadeDestinoDescricao(unidadeDestino.getDescricao());
			form.setUnidadeDestinoIndicadorTramite(unidadeDestino.getIndicadorTramite()+"");
			form.setUnidadeDestinoCodigoTipo(unidadeDestino.getUnidadeTipo().getCodigoTipo());
			sessao.setAttribute("unidadeDestinoEncontrada","true");
		} else {
			sessao.removeAttribute("unidadeDestinoEncontrada");
			form.setUnidadeDestinoDescricao("Unidade Organizacional inexistente");
			form.setUnidadeDestinoId("");
		}
		form.setValidaUnidadeDestino("false");
	}

	/**
	 * Recupera Usuário Responsável
	 *
	 * @author Leonardo Regis
	 * @date 17/08/2006
	 *
	 * @param form
	 */
	private void getUsuarioResponsavel(TramitarRegistroAtendimentoActionForm form, HttpSession sessao) {
		// [F0005] Valida Usuário Responsável
		Fachada fachada = Fachada.getInstancia();
		// Filtra usuário
		FiltroUsuario filtroUsuarioResponsavel = new FiltroUsuario();
		filtroUsuarioResponsavel.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, form.getUsuarioResponsavelId()));
		// Recupera usuário
		Collection colecaoUsuarioResponsavel = fachada.pesquisar(filtroUsuarioResponsavel, Usuario.class.getName());
		if (colecaoUsuarioResponsavel != null && !colecaoUsuarioResponsavel.isEmpty()) {
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarioResponsavel);
			form.setUsuarioResponsavelNome(usuario.getNomeUsuario());
			sessao.setAttribute("usuarioResponsavelEncontrada", "true");
		} else {
			sessao.removeAttribute("usuarioResponsavelEncontrada");
			form.setUsuarioResponsavelNome("Usuário inexistente");
			form.setUsuarioResponsavelId("");
		}
		form.setValidaUsuarioResponsavel("false");
	}	
}