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
import gcom.atendimentopublico.registroatendimento.FiltroRaDadosAgenciaReguladora;
import gcom.atendimentopublico.registroatendimento.FiltroRaDadosAgenciaReguladoraFone;
import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora;
import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladoraFone;
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
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0537] Consultar Dados da Agencia Reguladora
 *
 * @author Kássia Albuquerque
 * @date 09/05/2007
 */

public class ExibirConsultarRaDadosAgenciaReguladoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("consultarRaDadosAgenciaReguladora");
	
			ConsultarRaDadosAgenciaReguladoraActionForm form = (ConsultarRaDadosAgenciaReguladoraActionForm) actionForm;
	
			Fachada fachada = Fachada.getInstancia();
	
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Integer idRegistroAtendimento;
			
			RaDadosAgenciaReguladora raDadosAgenciaReguladora;
			
			if (httpServletRequest.getParameter("idRa") != null) {
				
				// Vem do Manter
				
				idRegistroAtendimento = new Integer(httpServletRequest.getParameter("idRa"));
				
				RaDadosAgenciaReguladora raDadosAgenciaReguladoraFiltro = new RaDadosAgenciaReguladora();
				
				FiltroRaDadosAgenciaReguladora filtroRaDadosAgenciaReguladora = (FiltroRaDadosAgenciaReguladora)raDadosAgenciaReguladoraFiltro.retornaFiltro();
				
				filtroRaDadosAgenciaReguladora.limparListaParametros();
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples(FiltroRaDadosAgenciaReguladora.REGISTRO_ATENDIMENTO_ID, idRegistroAtendimento));
				
				Collection colecaoRaDadosAgenciaReguladora = fachada.pesquisar(filtroRaDadosAgenciaReguladora,RaDadosAgenciaReguladora.class.getName());
				
				raDadosAgenciaReguladora = (RaDadosAgenciaReguladora) Util.retonarObjetoDeColecao(colecaoRaDadosAgenciaReguladora);
					
			}else{
				
				// Vem do Filtrar
				
				raDadosAgenciaReguladora =  (RaDadosAgenciaReguladora)sessao.getAttribute("raDadosAgenciaReguladora");
				idRegistroAtendimento = raDadosAgenciaReguladora.getRegistroAtendimento().getId();
			}	
				ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(idRegistroAtendimento);
	
				// Dados Gerais do Registro de Atendimento
	
				setDadosRA(form, registroAtendimentoHelper);
				setDadosSolicitante(form, registroAtendimentoHelper);
				setDadosEnderecoOcorrencia(form, registroAtendimentoHelper);
				setUnidades(form, registroAtendimentoHelper);
				
				//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx
				
				//	Pesquisando a coleçao de fones com o valor do Id da Agencia Reguladora
				
				FiltroRaDadosAgenciaReguladoraFone filtroRaDadosAgenciaReguladoraFone = new FiltroRaDadosAgenciaReguladoraFone();
				
				filtroRaDadosAgenciaReguladoraFone.adicionarParametro(new ParametroSimples
						(FiltroRaDadosAgenciaReguladoraFone.AGENCIA_REGULADORA_ID,raDadosAgenciaReguladora.getId()));
				
				filtroRaDadosAgenciaReguladoraFone.adicionarCaminhoParaCarregamentoEntidade("foneTipo");
				
				Collection colecaoRaDadosAgenciaReguladoraFone = fachada.pesquisar
						(filtroRaDadosAgenciaReguladoraFone,RaDadosAgenciaReguladoraFone.class.getName());
				
				if (colecaoRaDadosAgenciaReguladoraFone != null && !colecaoRaDadosAgenciaReguladoraFone.isEmpty()){
					
					sessao.setAttribute("colecaoRaDadosAgenciaReguladoraFone",colecaoRaDadosAgenciaReguladoraFone);
				}

				
				//  Dados da Reclamação na Agência Reguladora
				
				form.setNumReclamacao(""+raDadosAgenciaReguladora.getAgenciaReguladora());
				
				if (raDadosAgenciaReguladora.getCodigoSituacao()!= ConstantesSistema.SITUACAO_AGENCIA_TODOS){
					if (raDadosAgenciaReguladora.getCodigoSituacao()== ConstantesSistema.SITUACAO_AGENCIA_ENCERRADO ){
						form.setSitAgReguladora("ENCERRADO");
					}else{
						form.setSitAgReguladora("PENDENTE");
					}
				}
				
				form.setDtPrevAgRegOriginal(Util.formatarData(raDadosAgenciaReguladora.getDataPrevisaoOriginal())); 
				form.setDtPrevAgRegAtual(Util.formatarData(raDadosAgenciaReguladora.getDataPrevisaoAtual()));
				
				if(raDadosAgenciaReguladora.getAgenciaReguladoraMotReclamacao()!= null ){
					form.setMotivoReclamacaoId(raDadosAgenciaReguladora.getAgenciaReguladoraMotReclamacao().getId().toString());
					form.setMotivoReclamacaoDescricao(raDadosAgenciaReguladora.getAgenciaReguladoraMotReclamacao().getDescricao());
				}
				
				form.setDataReclamacao(Util.formatarData(raDadosAgenciaReguladora.getDataReclamacao()));
				form.setHoraReclamacao(Util.formatarHoraSemData(raDadosAgenciaReguladora.getDataReclamacao()));
				
				if (raDadosAgenciaReguladora.getDescricaoReclamacao() != null 
										&& !raDadosAgenciaReguladora.getDescricaoReclamacao().equals("")){
					
					form.setDescricaoReclamacao(raDadosAgenciaReguladora.getDescricaoReclamacao());
				}
				
				if (raDadosAgenciaReguladora.getAtendimentoMotivoEncerramento()!= null){
					form.setMotEncerramentoId(raDadosAgenciaReguladora.getAtendimentoMotivoEncerramento().getId().toString());
					form.setDescricaoMotEncerramento(raDadosAgenciaReguladora.getAtendimentoMotivoEncerramento().getDescricao());
				}
				
				
				//	Dados do Retorno para Agência
				if(raDadosAgenciaReguladora.getAgenciaReguladoraMotRetorno() != null){
					form.setMotivoRetornoId(raDadosAgenciaReguladora.getAgenciaReguladoraMotRetorno().getId().toString());
					form.setMotivoRetornoDescricao(raDadosAgenciaReguladora.getAgenciaReguladoraMotRetorno().getDescricao());
				}
				
				if (raDadosAgenciaReguladora.getDataRetorno()!= null && 
																!raDadosAgenciaReguladora.getDataRetorno().equals("")){
					form.setDataRetorno(Util.formatarData(raDadosAgenciaReguladora.getDataRetorno()));
					form.setHoraRetorno(Util.formatarHoraSemData(raDadosAgenciaReguladora.getDataRetorno()));
				}
				
				if (raDadosAgenciaReguladora.getDescricaoRetorno() != null 
														&& !raDadosAgenciaReguladora.getDescricaoRetorno().equals("")){
					form.setDescricaoRetorno(raDadosAgenciaReguladora.getDescricaoRetorno());
				}
				
				// Dados do Contato para Agência
				
				if (raDadosAgenciaReguladora.getContato() != null 
																&& !raDadosAgenciaReguladora.getContato().equals("")){
					form.setNomeContato(raDadosAgenciaReguladora.getContato());
				}
				
				if (raDadosAgenciaReguladora.getEmailContato()!= null 
													&& !raDadosAgenciaReguladora.getEmailContato().equals("")){
					form.setMailContato(raDadosAgenciaReguladora.getEmailContato());
				}
				
				if (raDadosAgenciaReguladora.getOrgaoContato()!= null 
													&& !raDadosAgenciaReguladora.getOrgaoContato().equals("")){
					form.setNomeOrgaoContato(raDadosAgenciaReguladora.getOrgaoContato());
				}
				
				if (raDadosAgenciaReguladora.getDddContato()!=null 
													&& !raDadosAgenciaReguladora.getDddContato().equals("")){
					form.setDddContato(Short.toString(raDadosAgenciaReguladora.getDddContato()));
				}
				
				if (raDadosAgenciaReguladora.getFoneContato()!= null 
													&& !raDadosAgenciaReguladora.getFoneContato().equals("")){
					form.setTelefoneContato(raDadosAgenciaReguladora.getFoneContato());
				}
				
				if (raDadosAgenciaReguladora.getRamalContato()!=null 
													&& !raDadosAgenciaReguladora.getFoneContato().equals("")){
					form.setRamalContato(raDadosAgenciaReguladora.getFoneContato());
				}
				
				if (raDadosAgenciaReguladora.getFaxContato()!=null
													&& !raDadosAgenciaReguladora.getFoneContato().equals("")){
					form.setFaxContato(raDadosAgenciaReguladora.getFoneContato());
				}
				
			// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
			
			
			return retorno;
		}

	/**
	 * Carrega Unidades (Atendimento e Atual)
	 * 
	 * @param form
	 * @param registroAtendimentoHelper
	 */
	private void setUnidades(ConsultarRaDadosAgenciaReguladoraActionForm form,
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
	private void setDadosRA(ConsultarRaDadosAgenciaReguladoraActionForm form,
			ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		RegistroAtendimento registroAtendimento = registroAtendimentoHelper.getRegistroAtendimento();
		// Dados Gerais do Registro de Atendimento
		form.setNumeroRADados("" + registroAtendimento.getId());
		form.setSituacaoRA(registroAtendimentoHelper.getDescricaoSituacaoRA());
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
	private void setDadosEncerramento(ConsultarRaDadosAgenciaReguladoraActionForm form,RegistroAtendimento registroAtendimento) {
		
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
	private void setDadosSolicitante(ConsultarRaDadosAgenciaReguladoraActionForm form,ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {

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
	private void setDadosEnderecoOcorrencia(ConsultarRaDadosAgenciaReguladoraActionForm form,ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		
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
