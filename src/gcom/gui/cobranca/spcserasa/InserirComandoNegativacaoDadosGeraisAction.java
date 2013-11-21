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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativacaoCriterioCpfTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade validar as informações da 1° aba do processo de inserção
 * de um Comando de Negativação
 *
 * @author Ana Maria
 * @date 06/11/2007
 */
public class InserirComandoNegativacaoDadosGeraisAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
		
		//[FS0026] Verificar existência de comando para o negativador na data
		boolean existeComando = fachada.verificarExistenciaComandoNegativador(inserirComandoNegativacaoActionForm.getIdNegativador(),Util.converteStringParaDate(inserirComandoNegativacaoActionForm.getDataPrevista()));
	          
	    if(existeComando){
	    	throw new ActionServletException("atencao.existe_comando_negativado_data", "inserirComandoNegativacaPorCriterioWizardAction.do?voltar=ok&entrou=ok&action=exibirInserirComandoNegativacaoDadosGeraisAction"
    				,new Exception(), inserirComandoNegativacaoActionForm.getDataPrevista(),inserirComandoNegativacaoActionForm.getNomeNegativador());
	    }
		
        //Verificar existência Usuário 
        if(inserirComandoNegativacaoActionForm.getUsuario() != null && !inserirComandoNegativacaoActionForm.getUsuario().equals("")){        	
	        String usuario = inserirComandoNegativacaoActionForm.getUsuario();
	        	
	        	FiltroUsuario filtroUsuario = new FiltroUsuario();  	            
	        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, usuario));
	            
	            Collection colecaoUsuario = fachada.pesquisar(
	                    filtroUsuario,Usuario.class.getName());
	            
	            if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {	            	
	            	inserirComandoNegativacaoActionForm.setUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getId());
	            	inserirComandoNegativacaoActionForm.setNomeUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
				} else {
					throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
				}
        }
        
		//Verificar existência de Titularidades adicionadas
		Collection colecaoNegativacaoCriterioCpfTipo = (Collection)sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo"); 
		if(colecaoNegativacaoCriterioCpfTipo == null || colecaoNegativacaoCriterioCpfTipo.isEmpty()){
			throw new ActionServletException("atencao.campo.informado", null, "Titularidade do CPF/CNPJ da Negativação");
		}else{ //Verificar ordem e coincidente das Titularidade
			Integer tamanhoColecao =colecaoNegativacaoCriterioCpfTipo.size();
			if(tamanhoColecao.equals(1)){
				NegativacaoCriterioCpfTipo ncCpfTipo = (NegativacaoCriterioCpfTipo) Util.retonarObjetoDeColecao(colecaoNegativacaoCriterioCpfTipo);
				ncCpfTipo.setIndicadorCoincidente(new Short("2"));
			}
		}
        
		atualizaColecoesNaSessao( sessao, httpServletRequest);
	
        return retorno;
	}
	
	
	private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	     	
		//colecaoNegativacaoCriterioCpfTipo
		if (sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo") != null
		&& !sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo").equals("")) {
		
			Collection colecaoNegativacaoCriterioCpfTipo = (Collection) sessao
			.getAttribute("colecaoNegativacaoCriterioCpfTipo");
			
			Integer tamanhoColecao = colecaoNegativacaoCriterioCpfTipo.size();
			if(!tamanhoColecao.equals(1)){		
				// cria as variáveis para recuperar os parâmetros do request e jogar
				// no objeto  NegativacaoCriterioCpfTipo
				String ordem = null;
				String coincidente = null;
				
				Integer qtdOrdem = 0;
				Integer qtdCoincidente = 0;
				
				Iterator iteratorNegativacaoCriterioCpfTipo = colecaoNegativacaoCriterioCpfTipo.iterator();
				
				while (iteratorNegativacaoCriterioCpfTipo.hasNext()) {
					NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = 
						(NegativacaoCriterioCpfTipo) iteratorNegativacaoCriterioCpfTipo.next();
					
					Integer idTitularidade = negativacaoCriterioCpfTipo.getCpfTipo().getId();
	
					ordem = (String) httpServletRequest.getParameter("ordem" + idTitularidade);
					coincidente = (String) httpServletRequest.getParameter("coincidente" + idTitularidade);
					
					if((ordem == null || ordem.equals("")) && (coincidente == null || coincidente.equals(""))){
						throw new ActionServletException("atencao.informe_ordem");	
					}
					
					if (ordem != null && !ordem.equals("")) {
						qtdOrdem = qtdOrdem + 1;
						negativacaoCriterioCpfTipo.setNumeroOrdemSelecao(new Short(ordem));
					}
					
					if(coincidente != null && !coincidente.equals("")){
						qtdCoincidente = qtdCoincidente + 1;
						negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("1"));	
					}else{
						negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("2"));			
					}

				}
				
				if(qtdCoincidente.equals(tamanhoColecao) && !qtdOrdem.equals(0)){
					throw new ActionServletException("atencao.nao_informe_oredem");						
				}
				if(!qtdCoincidente.equals(tamanhoColecao) && !qtdOrdem.equals(tamanhoColecao)){
					throw new ActionServletException("atencao.informe_ordem");				
				}
			}			
		}	        	
    }


}
