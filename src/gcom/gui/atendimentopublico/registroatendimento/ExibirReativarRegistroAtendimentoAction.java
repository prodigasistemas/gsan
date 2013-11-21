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

import java.util.Collection;
import java.util.Date;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.FiltroRAMotivoReativacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.RaMotivoReativacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0426] Este caso de uso permite reativar um RA
 * 
 * 
 * @author Ana Maria
 * @created 17/08/2006
 */
public class ExibirReativarRegistroAtendimentoAction extends GcomAction {
	/**
	 * Exibe a Tela para Reativar o RA
	 * 
	 * @author Ana Maria
	 * @date 17/06/2006
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("reativarRegistroAtendimento");
		ReativarRegistroAtendimentoActionForm form = (ReativarRegistroAtendimentoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		 //Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");	
		// Reseta Tramitação
		if (form.getResetarReativar().equalsIgnoreCase("true")) {
			form.resetarReativar();
		}
		
		Integer idRegistroAtendimento = new Integer(httpServletRequest.getParameter("numeroRA"));
		
		if (form.getValidaUnidadeAtendimento().equalsIgnoreCase("false")
						 && form.getValidaUnidadeDestino().equalsIgnoreCase("false")) {
			
			if (form.getEspecificacaoId() == null || form.getEspecificacaoId().equals("")) {
				
				// [FS0001] Verificar possibilidade de reativação do registro atendimento
				fachada.validaPossibilidadeReativacaoRA(idRegistroAtendimento, usuario.getId());

				ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada
						.obterDadosRegistroAtendimento(idRegistroAtendimento);

				// [FS0002] Verificar existência de registro de atendimento para o imóvel com a mesma especificação
				// [SB0004] Verificar situação do imóvel e especificação
				if (registroAtendimentoHelper.getRegistroAtendimento().getImovel() != null) {
					Integer idSolicitacaoTipoEspecificacao = validaRegistroAtendimento(fachada, registroAtendimentoHelper);
					fachada.verificarSituacaoImovelEspecificacao(registroAtendimentoHelper.getRegistroAtendimento()
									.getImovel(),idSolicitacaoTipoEspecificacao);
				}

				// Dados Gerais do Registro de Atendimento
				setDadosRA(form, registroAtendimentoHelper);
				setDadosSolicitante(form, registroAtendimentoHelper);
				setDadosEnderecoOcorrencia(form, registroAtendimentoHelper);
				setUnidades(form, registroAtendimentoHelper);

				// Dados da reativação
				// Informa a Unidade Atendimento e o Meio de solicitação
				setUnidadeAtendimentoMeioSolicitacao(form, fachada, usuario);
				// Informa o tipo de Atendimento(on-line)
				form.setTipoAtendimento("1");

			    //Carrega Meio de Solicitação
				carregarMeioSolicitacao(httpServletRequest, fachada, sessao);	
				 			
				// [SB0001], [SB0002], [SB0003] Definir Unidade Destino
				setUnidadeDestino(form, fachada, registroAtendimentoHelper);
						
				//Carrega Motivo da Reativação
				carregaMotivoReativacao(httpServletRequest, fachada, sessao);
				
				// Identificar tipo de geração da ordem de serviço (AUTOMÁTICA, OPCIONAL ou NÃO GERAR)
				/* if (fachada.gerarOrdemServicoAutomatica(Util
						.converterStringParaInteger(form.getEspecificacaoId()))) {
					sessao.setAttribute("gerarOSAutomativa", "OK");
					FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
					filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
					
					filtroSolicitacaoTipoEspecificacao
							.adicionarParametro(new ParametroSimples(
									FiltroSolicitacaoTipoEspecificacao.ID,
									form.getEspecificacaoId()));

					filtroSolicitacaoTipoEspecificacao
							.adicionarParametro(new ParametroNaoNulo(
									FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO_ID));
					
					filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
									filtroSolicitacaoTipoEspecificacao,
									SolicitacaoTipoEspecificacao.class.getName());

					Integer idServicoTipo = ((SolicitacaoTipoEspecificacao)
					colecaoSolicitacaoTipoEspecificacao.iterator().next()).getServicoTipo().getId();
					
					sessao.setAttribute("servicoTipo", idServicoTipo);

				} else {
					sessao.removeAttribute("gerarOSAutomativa");
					sessao.removeAttribute("servicoTipo");					
				}*/
			}

			String definirDataPrevista = httpServletRequest.getParameter("definirDataPrevista");
			String tempoEsperaFinalDesabilitado = httpServletRequest.getParameter("tempoEsperaFinalDesabilitado");

			// [FS003] Verifica data atendimento - Caso a data do atendimento esteja habilitada para preenchimento
			if (definirDataPrevista != null && !definirDataPrevista.equalsIgnoreCase("")) {
			  Date dataAtendimento = Util.converteStringParaDate(form.getDataAtendimentoReativado());

			  DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper dataPrevistaUnidadeDestino = fachada
				.definirDataPrevistaUnidadeDestinoEspecificacao(dataAtendimento, new Integer(form.getEspecificacaoId()));

			  if (dataPrevistaUnidadeDestino.getDataPrevista() != null) {
				 form.setDataPrevistaReativado(Util.formatarData(dataPrevistaUnidadeDestino.getDataPrevista()));
			  }	
		    //[FS0006] Verificar tempo de espera final para atendimento
			}else if (tempoEsperaFinalDesabilitado != null && !tempoEsperaFinalDesabilitado.equalsIgnoreCase("")){
				 /* 
				  * Caso o Tempo de Espera Final esteja desabilitado e o Tempo de
				  * Espera Inicial para Atendimento esteja preenchido, atribuir o
				  * valor correspondente à hora corrente e não permitir alteração
				  */
				 this.atribuirHoraCorrenteTempoEsperaFinal(form);
				 httpServletRequest.setAttribute("nomeCampo", "unidade");
			}else{	
				// [SB0005 - Habilita/Desabilita Dados do Momento do Atendimento]
				habilitacaoDadosMomentoAtendimento(form, httpServletRequest);
				
	 			if (form.getTipoAtendimento().equals("1")) {
					Date dataAtendimento = Util.converteStringParaDate(form.getDataAtendimentoReativado());
	 				/* 
	 				 * [SB0001] - Define Data Prevista - (exibir a data prevista calculada no SB0001 e não
	 				 * permitir alteração).
	 				 */
					Date dataPrevista = fachada.definirDataPrevistaRA(dataAtendimento, new Integer(
							form.getEspecificacaoId()));
					if (dataPrevista != null) {
						form.setDataPrevistaReativado(Util.formatarData(dataPrevista));
					}
				}
			}
			
		} else {
			// Unidade de Atendimento
			if (form.getIdUnidadeAtendimento() != null && !form.getIdUnidadeAtendimento().equals("")) {
				getUnidadeAtendimentoEnter(form, fachada, httpServletRequest);
			}
			// Unidade Destino
			if (form.getIdUnidadeDestino() != null && !form.getIdUnidadeDestino().equals("")) {
				getUnidadeDestinoEnter(form, fachada, httpServletRequest);
			}
		}		
	 
	  return retorno;
	}
	
	
	/**
	 * Recupera Unidade de Atendimento
	 *
	 * @param form
	 */	

	private void getUnidadeDestinoEnter(ReativarRegistroAtendimentoActionForm form, Fachada fachada,
			HttpServletRequest httpServletRequest) {
		if (form.getValidaUnidadeDestino().equalsIgnoreCase("true")) {
			// Filtro para descobrir a unidade destino
			FiltroUnidadeOrganizacional filtroUnidadeDestino = new FiltroUnidadeOrganizacional();

			filtroUnidadeDestino.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getIdUnidadeDestino()));

			Collection colecaoUnidadesDestino = fachada.pesquisar(filtroUnidadeDestino, UnidadeOrganizacional.class.getName());
	
            if (colecaoUnidadesDestino != null && !colecaoUnidadesDestino.isEmpty()){
				UnidadeOrganizacional unidadeDestinoIterator = (UnidadeOrganizacional) colecaoUnidadesDestino.iterator().next();
				// [FS0013] - Verificar possibilidade de encaminhamento para a unidade destino
				fachada.verificaPossibilidadeEncaminhamentoUnidadeDestino(unidadeDestinoIterator);
				form.setIdUnidadeDestino(unidadeDestinoIterator.getId().toString());
				form.setUnidadeDestino(unidadeDestinoIterator.getDescricao());
				httpServletRequest.setAttribute("unidadeDestinoEncontrado", "true");
			} else {
				// Exibe mensagem de código inexiste e limpa o campo de código
				httpServletRequest.setAttribute("unidadeDestinoEncontrado", "exception");
				form.setIdUnidadeDestino("");
				form.setUnidadeDestino("Unidade Destino inexistente");				
			}
		}
		form.setValidaUnidadeDestino("false");	
	}
	
	/**
	 * Recupera Unidade Destino
	 *
	 * @param form
	 */	
	
	private void getUnidadeAtendimentoEnter(ReativarRegistroAtendimentoActionForm form, Fachada fachada,
			HttpServletRequest httpServletRequest) {
		if (form.getValidaUnidadeAtendimento().equalsIgnoreCase("true")) {
			// Filtro para descobrir a unidade atendimento
			FiltroUnidadeOrganizacional filtroUnidadeAtendimento = new FiltroUnidadeOrganizacional();

			filtroUnidadeAtendimento.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getIdUnidadeAtendimento()));

			Collection colecaoUnidadesAtendimento = fachada.pesquisar(filtroUnidadeAtendimento, UnidadeOrganizacional.class.getName());
	
            if (colecaoUnidadesAtendimento != null && !colecaoUnidadesAtendimento.isEmpty()){
				UnidadeOrganizacional unidadeAtendimentoIterator = (UnidadeOrganizacional) colecaoUnidadesAtendimento.iterator().next();
				fachada.verificarAutorizacaoUnidadeAberturaRA(unidadeAtendimentoIterator.getId(), false);
				form.setIdUnidadeAtendimento(unidadeAtendimentoIterator.getId().toString());
				form.setUnidadeAtendimento(unidadeAtendimentoIterator.getDescricao());
				httpServletRequest.setAttribute("unidadeAtendimentoEncontrado", "true");
			} else {
				// Exibe mensagem de código inexiste e limpa o campo de código
				httpServletRequest.setAttribute("unidadeAtendimentoEncontrado", "exception");
				form.setIdUnidadeAtendimento("");
				form.setUnidadeAtendimento("Unidade Atendimento inexistente");
			}
		}
		form.setValidaUnidadeAtendimento("false");			
	}

	/**
	 * Unidade de Atendimento (exibir a tela com a unidade associada ao
	 * usuário que estiver efetuando a reativação. 
	 * 
	 * Meio de Solicitação (exibir na tela com o meio de solicitação
	 * associado à unidade de atendimento)
	 * 
	 * [FS0009] Verificar autorização da unidade de atendimento para abertura de 
	 * registro de atendimento
	 */
	private void setUnidadeAtendimentoMeioSolicitacao(ReativarRegistroAtendimentoActionForm form, Fachada fachada, Usuario usuario) {	

			UnidadeOrganizacional unidadeOrganizacionalUsuario = fachada
					.obterUnidadeOrganizacionalAberturaRAAtivoUsuario(usuario
							.getLogin());

			if (unidadeOrganizacionalUsuario != null) {

				form.setIdUnidadeAtendimento(unidadeOrganizacionalUsuario.getId()
								.toString());
				form.setUnidadeAtendimento(unidadeOrganizacionalUsuario
								.getDescricao());

				if (unidadeOrganizacionalUsuario.getMeioSolicitacao() != null) {

					form.setMeioSolicitacao(unidadeOrganizacionalUsuario
									.getMeioSolicitacao().getId().toString());
				}
			}
	}
	
	/**
	 * Motivo da Reativação - Carregando a coleção que irá ficar disponível
	 * para escolha do usuário 
	 * 
	 * [FS0003] - Verificar existência de dados
	 */
	private void carregaMotivoReativacao(HttpServletRequest httpServletRequest, Fachada fachada, HttpSession sessao) {
		FiltroRAMotivoReativacao filtroRAMotivoReativacao = new FiltroRAMotivoReativacao();
		   filtroRAMotivoReativacao.setCampoOrderBy(FiltroRAMotivoReativacao.DESCRICAO);
		   filtroRAMotivoReativacao.adicionarParametro(new ParametroSimples(FiltroRAMotivoReativacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		   Collection colecaoMotivoReativacao = fachada.pesquisar(filtroRAMotivoReativacao, RaMotivoReativacao.class.getName());       
				
		   if (colecaoMotivoReativacao == null || colecaoMotivoReativacao.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",
							null, "Motide da Reativação");
			}
				
		  sessao.setAttribute("colecaoMotivoReativacao", colecaoMotivoReativacao);
	}

	/**
	 * Meio de Solicitação - Carregando a coleção que irá ficar disponível
	 * para escolha do usuário 
	 * 
	 * [FS0003] - Verificar existência de dados
	 */
	private void carregarMeioSolicitacao(HttpServletRequest httpServletRequest, Fachada fachada, HttpSession sessao) {

 		Collection colecaoMeioSolicitacao = (Collection)httpServletRequest.getAttribute("colecaoMeioSolicitacao");

		if (colecaoMeioSolicitacao == null) {

			FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao(FiltroMeioSolicitacao.DESCRICAO);
		
			filtroMeioSolicitacao.setConsultaSemLimites(true);
			
			filtroMeioSolicitacao.adicionarParametro(new ParametroSimples(
			FiltroMeioSolicitacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
					
			colecaoMeioSolicitacao = fachada.pesquisar(filtroMeioSolicitacao, 
			MeioSolicitacao.class.getName());
			
			if (colecaoMeioSolicitacao == null || colecaoMeioSolicitacao.isEmpty()){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "MEIO_SOLICITACAO");
			}
			else{
				sessao.setAttribute("colecaoMeioSolicitacao", colecaoMeioSolicitacao);
			}
		}
	}
	
	/**
	 * [SB0001], [SB0002], [SB0003] Definir Unidade Destino
	 *
	 * @param reativarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param registroAtendimentoHelper
	 */
	private void setUnidadeDestino(ReativarRegistroAtendimentoActionForm form, Fachada fachada, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		Integer idDivisaoEsgoto = null;
		if(registroAtendimentoHelper.getRegistroAtendimento().getDivisaoEsgoto() != null){
		    idDivisaoEsgoto = registroAtendimentoHelper.getRegistroAtendimento().getDivisaoEsgoto().getId();
		}
		ObterDadosIdentificacaoLocalOcorrenciaHelper localOcorrencia = fachada.habilitarGeograficoDivisaoEsgoto(new Integer(
				form.getTipoSolicitacaoId()));
		boolean solicitacaoTipoRelativoAreaEsgoto = localOcorrencia.isSolicitacaoTipoRelativoAreaEsgoto();
		
		UnidadeOrganizacional unidadeDestino = fachada.definirUnidadeDestino(new Integer(
				form.getEspecificacaoId()), new Integer(
				form.getLocalidadeId()), new Integer(
				form.getTipoSolicitacaoId()),
			    solicitacaoTipoRelativoAreaEsgoto, idDivisaoEsgoto);
		if(unidadeDestino != null){
			form.setIdUnidadeDestino(unidadeDestino.getId().toString());
			form.setUnidadeDestino(unidadeDestino.getDescricao());
		}
	}

	/**
	 * [FS0002] Verificar existência de registro de atendimento para o imóvel com a mesma especificação
	 *
	 * @param fachada
	 * @param registroAtendimentoHelper
	 */		
	private Integer validaRegistroAtendimento(Fachada fachada, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		Integer idImovel = registroAtendimentoHelper.getRegistroAtendimento().getImovel().getId();
		  Integer idSolicitacaoTipoEspecificacao = registroAtendimentoHelper.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getId();
		  fachada.verificarExistenciaRAImovelMesmaEspecificacao(idImovel, idSolicitacaoTipoEspecificacao);
		return idSolicitacaoTipoEspecificacao;
	}

	/**
	 * Carrega Unidades (Atendimento e Atual)
	 *
	 * @param form
	 * @param registroAtendimentoHelper
	 */		
	private void setUnidades(ReativarRegistroAtendimentoActionForm form, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		UnidadeOrganizacional unidadeAtendimento =  registroAtendimentoHelper.getUnidadeAtendimento();
		if(unidadeAtendimento != null){
			form.setUnidadeAtendimentoId(""+unidadeAtendimento.getId());
			form.setUnidadeAtendimentoDescricao(unidadeAtendimento.getDescricao());		
		}
		UnidadeOrganizacional unidadeAtual = registroAtendimentoHelper.getUnidadeAtual();
		if(unidadeAtual != null){
			form.setUnidadeAtualId(""+unidadeAtual.getId());
			form.setUnidadeAtualDescricao(unidadeAtual.getDescricao());
		}
	}

	/**
	 * Carrega Dados do RA
	 *
	 * @param form
	 * @param registroAtendimentoHelper
	 */	
	private void setDadosRA(ReativarRegistroAtendimentoActionForm form, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		RegistroAtendimento registroAtendimento = registroAtendimentoHelper.getRegistroAtendimento();
		//Dados Gerais do Registro de Atendimento
		form.setNumeroRA(""+registroAtendimento.getId());
		form.setSituacaoRA(registroAtendimentoHelper.getDescricaoSituacaoRA());		
		if(registroAtendimentoHelper.getRAAssociado() != null) {
			form.setNumeroRaAssociado(""+registroAtendimentoHelper.getRAAssociado().getId());
			form.setSituacaoRaAssociado(registroAtendimentoHelper.getDescricaoSituacaoRAAssociado());
		}
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao =	registroAtendimento.getSolicitacaoTipoEspecificacao();
		if(solicitacaoTipoEspecificacao != null){
			if(solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null){
				form.setTipoSolicitacaoId(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId()+"");
				form.setTipoSolicitacaoDescricao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());	
			}
			form.setEspecificacaoId(solicitacaoTipoEspecificacao.getId()+"");
			form.setEspecificacaoDescricao(solicitacaoTipoEspecificacao.getDescricao());	
		}
		if(registroAtendimento.getMeioSolicitacao() != null){
			form.setMeioSolicitacaoId(registroAtendimento.getMeioSolicitacao().getId()+"");
			//reativarRegistroAtendimentoActionForm.setMeioSolicitacao(registroAtendimento.getMeioSolicitacao().getId()+"");			
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
		
		//Dados necessário p/ inserir o novo RA
		if(registroAtendimento.getLogradouroBairro() != null){
			form.setLogradouroBairro(registroAtendimento.getLogradouroBairro().getId());
		}
		if(registroAtendimento.getLogradouroCep() != null){
			form.setLogradouroCep(registroAtendimento.getLogradouroCep().getId());
		}
		form.setComplementoEndereco(registroAtendimento.getComplementoEndereco());
		if(registroAtendimento.getLocalOcorrencia() != null){
			form.setLocalOcorrencia(registroAtendimento.getLocalOcorrencia().getId());
		}
		if(registroAtendimento.getPavimentoRua() != null){
			form.setPavimentoRua(registroAtendimento.getPavimentoRua().getId());
		}
		if(registroAtendimento.getPavimentoCalcada() != null){
			form.setPavimentoCalcada(registroAtendimento.getPavimentoCalcada().getId());
		}
		if(registroAtendimento.getNumeroImovel() != null ) {
			form.setNumeroImovel(registroAtendimento.getNumeroImovel() );
		}
		form.setDescricaoLocalOcorrencia(registroAtendimento.getDescricaoLocalOcorrencia());		
	}

	/**
	 * Carrega Dados do RA
	 *
	 * @param form
	 * @param registroAtendimento
	 */		
	private void setDadosEncerramento(ReativarRegistroAtendimentoActionForm form, RegistroAtendimento registroAtendimento) {
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = registroAtendimento.getAtendimentoMotivoEncerramento();
		if(atendimentoMotivoEncerramento != null){
			form.setIdMotivoEncerramento(""+atendimentoMotivoEncerramento.getId());	
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
	private void setDadosSolicitante(ReativarRegistroAtendimentoActionForm form, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
		//Dados do Solicitante
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = registroAtendimentoHelper.getSolicitante();
		if(registroAtendimentoSolicitante != null){
			form.setIdRaSolicitante(registroAtendimentoSolicitante.getID());
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
			
			//PROTOCOLO DE ATENDIMENTO
			if (registroAtendimentoSolicitante.getNumeroProtocoloAtendimento() != null){
				form.setProtocoloAtendimento(registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
			}
		}
	}
	
	/**
	 * Carrega Dados do Endereço de Ocorrência
	 *
	 * @param form
	 * @param registroAtendimentoHelper
	 */	
	private void setDadosEnderecoOcorrencia(ReativarRegistroAtendimentoActionForm form, ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper) {
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
			form.setSetorComercialCodigo(""+setorComercial.getCodigo());
		}
		Quadra quadra = registroAtendimentoHelper.getRegistroAtendimento().getQuadra();
		if(quadra != null){
			form.setQuadraId(""+quadra.getId());
			form.setQuadraNumero(""+quadra.getNumeroQuadra());
		}
		DivisaoEsgoto divisaoEsgoto = registroAtendimentoHelper.getRegistroAtendimento().getDivisaoEsgoto();
		if(divisaoEsgoto != null){
			form.setDivisaoEsgotoId(""+divisaoEsgoto.getId());
			form.setDivisaoEsgotoDescricao(divisaoEsgoto.getDescricao());			
		}
	}
	/**
	 * Habilitar ou desabilitar os campos Tempo de Espera para Atendimento, Data
	 * do Atendimento e Hora do Atendimento
	 * 
	 * [SB0005] Habilita/Desabilita Dados do Momento da Reativação
	 * 
	 * @param reativarRegistroAtendimentoActionForm
	 * @return void
	 */
	private void habilitacaoDadosMomentoAtendimento(
			ReativarRegistroAtendimentoActionForm form,
			HttpServletRequest httpServletRequest) {

		// On-line
		if (form.getTipoAtendimento()
				.equalsIgnoreCase("1")) {
			Date dataCorrente = new Date();

			form.setDataAtendimentoReativado(Util
					.formatarData(dataCorrente));
			form.setHoraAtendimentoReativado(Util
					.formatarHoraSemSegundos(dataCorrente));
			form.setTempoEsperaInicial("");
			form.setTempoEsperaFinal("");
			
			httpServletRequest.setAttribute("nomeCampo", "tempoEsperaInicial");
		}
		//Manual
		else{
			form.setDataAtendimentoReativado("");
			form.setHoraAtendimentoReativado("");
			form.setTempoEsperaInicial("");
			form.setTempoEsperaFinal("");
			form.setDataPrevistaReativado("");
		
			httpServletRequest.setAttribute("nomeCampo", "dataAtendimentoReativado");
		}
	}
	
	/**
	 * Atribui o valor correspondente à hora corrente
	 * 
	 * @param InserirRegistroAtendimentoActionForm
	 * @return void
	 */
	private void atribuirHoraCorrenteTempoEsperaFinal(
			ReativarRegistroAtendimentoActionForm form) {

		Date dataCorrente = new Date();

		form.setTempoEsperaFinal(Util.formatarHoraSemSegundos(dataCorrente));
	}
}
