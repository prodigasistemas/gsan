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
 * Yara Taciane de Souza
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.Arrays;
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

/**
 * Action de Atualizar Ordem de Repavimentacao em Processo de Aceite pelo PopUp.
 * 
 * @author Hugo Leonardo
 * @created 17/05/2010
 */

public class AtualizarOrdemRepavimentacaoProcessoAceitePopUpAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("atualizarOrdemRepavimentacaoProcessoAceitePopUp");
		
		FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form = (FiltrarOrdemRepavimentacaoProcessoAceiteActionForm) actionForm;

		//Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		boolean temPermissao = verificaUnidadeUsuario(usuario, fachada);
		
		if (temPermissao) {
			throw new ActionServletException("atencao.nao_possui_permissao_para_atualizar");
		}
		
		Collection collOrdemServicoPavimento = (Collection) sessao.getAttribute("collOrdemServicoPavimentoAceite");
		
		String dataAceite = null;
		String motivo = null;
		Date dtAceite = null;
		OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();
		
		if (httpServletRequest.getAttribute("ordemServicoPavimentoAtualizar") != null) {
			 ordemServicoPavimento = (OrdemServicoPavimento) httpServletRequest.getAttribute("ordemServicoPavimentoAtualizar");        	
	        
		}else if(sessao.getAttribute("ordemServicoPavimentoAtualizar")!= null ){
			ordemServicoPavimento = (OrdemServicoPavimento) sessao.getAttribute("ordemServicoPavimentoAtualizar");
		}
		
		if (httpServletRequest.getAttribute("ordemServicoPavimentoAceitarOSConvergente") != null ||
        		sessao.getAttribute("ordemServicoPavimentoAceitarOSConvergente") != null ) {
			
			if (sessao.getAttribute("osPavimentoAceiteHelper") != null
					&& !sessao.getAttribute("osPavimentoAceiteHelper").equals("")) {

				if(form.getDataAceite()!= null && !"".equals(form.getDataAceite())){
					
					dataAceite = dataAceite = form.getDataAceite();			
					dtAceite = validarDataAceite(form, dtAceite, ordemServicoPavimento);	
				}
				
				OrdemRepavimentacaoProcessoAceiteHelper osPavimentoAceiteHelper =  (OrdemRepavimentacaoProcessoAceiteHelper) sessao.getAttribute("osPavimentoAceiteHelper");
				
				fachada.aceitarOSRepavimentacaoConvergente(osPavimentoAceiteHelper, usuario,
						dtAceite, new Short(form.getIndicadorSituacaoAceite().toString()));
			}
		}else if (httpServletRequest.getAttribute("ordemServicoPavimentoAtualizar") != null
				|| sessao.getAttribute("ordemServicoPavimentoAtualizar") != null ) {
		
			if(form.getDataAceite()!= null && !"".equals(form.getDataAceite())){
				
				dataAceite = form.getDataAceite();			
				dtAceite = validarDataAceite(form, dtAceite, ordemServicoPavimento);		
			}
			
			if ( form.getMotivo() != null && !"".equals(form.getMotivo())) {
				motivo = form.getMotivo();
			}
			
			if (httpServletRequest.getAttribute("ordemServicoPavimentoAtualizar") != null) {
				 ordemServicoPavimento = (OrdemServicoPavimento) httpServletRequest.getAttribute("ordemServicoPavimentoAtualizar");        	
		        
			}else if(sessao.getAttribute("ordemServicoPavimentoAtualizar")!= null ){
				ordemServicoPavimento = (OrdemServicoPavimento) sessao.getAttribute("ordemServicoPavimentoAtualizar");
			}
			
	    	ordemServicoPavimento.setDataAceite(Util.converteStringParaDate(dataAceite));
			ordemServicoPavimento.setIndicadorAceite(new Short(form.getIndicadorSituacaoAceite().toString()));
			ordemServicoPavimento.setUsuarioAceite(usuario);
			ordemServicoPavimento.setDescricaoMotivoAceite(motivo);
			
			/*
			 * [UC0107] Registrar Transação
			 * 
			 */
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_ATUALIZAR_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE,
					ordemServicoPavimento.getOrdemServico().getId(), ordemServicoPavimento.getOrdemServico().getId(),
					new UsuarioAcaoUsuarioHelper(usuario,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// [UC0107] -Fim- Registrar Transação
			
			registradorOperacao.registrarOperacao(ordemServicoPavimento);
			
			fachada.atualizar(ordemServicoPavimento);
			
			httpServletRequest.removeAttribute("ordemServicoPavimentoAtualizar");
			sessao.removeAttribute("ordemServicoPavimentoAtualizar");

		}else{
			
			if(form.getDataAceite()!= null && !"".equals(form.getDataAceite())){
				
				dataAceite = form.getDataAceite();			
				dtAceite = validarDataAceite(form, dtAceite, ordemServicoPavimento);	
			}
			
			if(form.getIdRegistro() != null){
				String[] idsRegistrosChecados = form.getIdRegistro();

				// obter uma listas das Ordens de repavimentação selecionadas
	        	List listIdsRegistrosChecados = Arrays.asList(idsRegistrosChecados);
	        	 
	        	//Pesquisar a Ordem Servico Selecionados para atualizacao na base
				FiltroOrdemServicoPavimento filtroOSPavimentoSelecionados = new FiltroOrdemServicoPavimento();
				filtroOSPavimentoSelecionados.adicionarParametro( new ParametroSimplesIn(
					FiltroOrdemServicoPavimento.ORDEM_SERVICO_ID, listIdsRegistrosChecados));
				 
				Collection colecaoOSPavimentoSelecionados = fachada.pesquisar(filtroOSPavimentoSelecionados,
					OrdemServicoPavimento.class.getName());
				 
				//Verificar se existe Ordem de Repavimentação em Aceite entre as Ordens selecionadas.
				fachada.verificarExistenciaRepavimentacaoAceite(colecaoOSPavimentoSelecionados);	
			
				OSPavimentoRetornoHelper oSPavimentoRetornoHelper = null;
				Iterator iterator = collOrdemServicoPavimento.iterator();
				String idOSPav = "";
				while (iterator.hasNext()) {
					
					oSPavimentoRetornoHelper = (OSPavimentoRetornoHelper) iterator.next();
					
					for (int i = 0; i < idsRegistrosChecados.length; i++) {
						
						if(oSPavimentoRetornoHelper.getOrdemServico().getId().toString().equals(idsRegistrosChecados[i])){
							
							ordemServicoPavimento = oSPavimentoRetornoHelper.getOrdemServicoPavimento();
							
							if ( ordemServicoPavimento.getDataAceite() != null && 
									!ordemServicoPavimento.getDataAceite().equals("") ) {
								idOSPav = ordemServicoPavimento.getOrdemServico().getId().toString();
							} else if ( ordemServicoPavimento.getDescricaoMotivoAceite() != null &&
									!ordemServicoPavimento.getDescricaoMotivoAceite().equals("") ) {
								idOSPav = ordemServicoPavimento.getOrdemServico().getId().toString();
							}
							
							
							ordemServicoPavimento.setDataAceite(Util.converteStringParaDate(dataAceite));
							ordemServicoPavimento.setIndicadorAceite(new Short(form.getIndicadorSituacaoAceite().toString()));
							ordemServicoPavimento.setUsuarioAceite(usuario);
							ordemServicoPavimento.setDescricaoMotivoAceite(form.getMotivo());
							ordemServicoPavimento.setUltimaAlteracao(new Date());
							if ( !idOSPav.equals("") && sessao.getAttribute("confirmacao") == null ) {
								
								httpServletRequest.setAttribute("caminhoActionConclusao",
								"/gsan/atualizarOrdemRepavimentacaoProcessoAceitePopUpAction.do");
								httpServletRequest.setAttribute("cancelamento", "TRUE");
								httpServletRequest.setAttribute("nomeBotao1", "Sim");
								httpServletRequest.setAttribute("nomeBotao2", "Não");
								sessao.setAttribute("confirmacao" , "sim");
		
								return montarPaginaConfirmacao("atencao.dados_os_sobrescritos",
										httpServletRequest, actionMapping);
								
							} 
							
							fachada.atualizar(ordemServicoPavimento);
						}	
					}
				}
			}
		}
		
		httpServletRequest.setAttribute("fecharPopup", "OK");
		sessao.setAttribute("fecharPopup", "OK");
		
		httpServletRequest.removeAttribute("ordemServicoPavimentoAceitarOSConvergente");
		sessao.removeAttribute("ordemServicoPavimentoAceitarOSConvergente");
		
		httpServletRequest.removeAttribute("ordemServicoPavimentoAtualizar");
		sessao.removeAttribute("ordemServicoPavimentoAtualizar");
		
		return retorno;

	}
	
	/**
	 * Verifica se usuario em Permissao para atualizar o 
	 * retorno das ordens de Serviço atraves do botão confirmar demandas.
	 * 
	 * @author Hugo Leonardo
	 * 
	 * @date 17/05/2010
	 * @param usuario
	 * @param fachada
	 * @return boolean
	 */
    private boolean verificaUnidadeUsuario( Usuario usuario, Fachada fachada) {
    	
    	boolean temPermissao = false;
    	
    	Collection colecaoUnidadesResponsaveis = null;
    	FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
    	
		if ( usuario != null && usuario.getUnidadeOrganizacional() != null && 
				usuario.getUnidadeOrganizacional().getUnidadeTipo() != null && 
				usuario.getUnidadeOrganizacional().getUnidadeTipo().getId() != null &&
				(usuario.getUnidadeOrganizacional().getUnidadeTipo().getId().intValue() == 
					UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID.intValue()) ) { 
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, usuario.getUnidadeOrganizacional().getId()));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO,UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID));
	
			colecaoUnidadesResponsaveis = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if ( colecaoUnidadesResponsaveis != null && !colecaoUnidadesResponsaveis.isEmpty() ) {
				temPermissao = true;
			}
		}
		return temPermissao;
    }
    
    public Date validarDataAceite(FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form, Date dtAceite, 
    		OrdemServicoPavimento ordemServicoPavimento ){
    	
    	String dataAceite = form.getDataAceite();
    	dtAceite = Util.converteStringParaDate(dataAceite);
    	
    	//Verifica se a ordem de servico pavimento foi informada, 
    	//caso não faz a pesquisa de acordo com o id da OS,
    	//armazenado um ou mais de um no campo hidden do form.
    	//Nesse fluxo, caso entre em algum exception exibe o numero da OS com excessão
    	if ( ordemServicoPavimento == null || ordemServicoPavimento.getId() == null ) {

    		String[] idsRegistrosChecados = form.getIdRegistro();

			// obter uma listas das Ordens de repavimentação selecionadas
        	List listIdsRegistrosChecados = Arrays.asList(idsRegistrosChecados);
			FiltroOrdemServicoPavimento filtro = new FiltroOrdemServicoPavimento();
			filtro.adicionarParametro( new ParametroSimplesIn( FiltroOrdemServicoPavimento.ORDEM_SERVICO_ID ,
					listIdsRegistrosChecados));
			
			Collection colecaoPavimento = Fachada.getInstancia().pesquisar( filtro , 
					OrdemServicoPavimento.class.getName());
			Iterator iteratorPavimento = colecaoPavimento.iterator();
			
			while ( iteratorPavimento.hasNext() ) {
				
				ordemServicoPavimento = (OrdemServicoPavimento) iteratorPavimento.next();
				
				if ( ordemServicoPavimento.getIndicadorAceite() != null ) {
					
					if ( dtAceite.before(ordemServicoPavimento.getDataAceite()) ) {
						throw new ActionServletException(
								"atencao.os.data.aceite.menor.anterior.informado",  
									ordemServicoPavimento.getOrdemServico().getId() +"", 
										Util.formatarData(ordemServicoPavimento.getDataExecucao()) );
					}
				} else {
					
					if ( dtAceite.before(ordemServicoPavimento.getDataExecucao()) ) {
						throw new ActionServletException(
								"atencao.os.data.aceite.menor.retorno", 
									ordemServicoPavimento.getOrdemServico().getId() +"",
										Util.formatarData(ordemServicoPavimento.getDataExecucao()) );
					}
				}
				
				Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
				
				if(Util.compararData(dtAceite, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.os.data.superior.data.corrente", 
							ordemServicoPavimento.getOrdemServico().getId() +"", 
							dataAtual);				
				}
			
			}
    		
    	} else {
    	
			if ( ordemServicoPavimento.getIndicadorAceite() != null ) {
		
				if ( dtAceite.before(ordemServicoPavimento.getDataAceite()) ) {
					throw new ActionServletException(
							"atencao.data.aceite.menor.anterior.informado", null, 
								Util.formatarData(ordemServicoPavimento.getDataExecucao()) );
				}
			} else {
				
				if ( dtAceite.before(ordemServicoPavimento.getDataExecucao()) ) {
					throw new ActionServletException(
							"atencao.data.aceite.menor.retorno", null, 
								Util.formatarData(ordemServicoPavimento.getDataExecucao()) );
				}
			}
			
			Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
			
			if(Util.compararData(dtAceite, dataAtualSemHora) == 1){
				String dataAtual = Util.formatarData(new Date());
				throw new ActionServletException(
						"atencao.data.superior.data.corrente", null, dataAtual);				
			}	
    	}
    				
					
		
		
		return dtAceite;
    }

}