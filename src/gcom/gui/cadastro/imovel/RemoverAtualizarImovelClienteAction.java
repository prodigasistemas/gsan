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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remover Cliente do Imovel em Manter Imovel
 * 
 * @author Rafael Santos
 * @created 09/02/2006
 */
public class RemoverAtualizarImovelClienteAction extends GcomAction {

    /**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        // obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

        //DynaValidatorActionForm inserirImovelClienteActionForm = (DynaValidatorActionForm) actionForm;

        // Cria variaveis
        Collection imovelClientesNovos = (Collection) sessao
                .getAttribute("imovelClientesNovos");

        // atribui os valores q vem pelo request as variaveis
        String[] clientesImoveis = httpServletRequest
                .getParameterValues("idRemocaoClienteImovel");
        if (clientesImoveis != null) {
        	sessao.setAttribute("arrayClientesImoveis", clientesImoveis);
        } else {
        	clientesImoveis = (String[]) sessao.getAttribute("arrayClientesImoveis");
        }

        Imovel imovel = null;

        ActionForward retorno = actionMapping.findForward("atualizarImovelCliente");
            imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
 
        Collection colecaoClientesImoveisFimRelacao = new ArrayList();
            
        // instancia cliente
        ClienteImovel clienteImovel = null;
        Collection colecaoClientesImoveisRemovidos = null;
        if(sessao.getAttribute("colecaoClientesImoveisRemovidos") == null ){
        	colecaoClientesImoveisRemovidos = new ArrayList();	
        }else{
        	colecaoClientesImoveisRemovidos = (Collection) sessao.getAttribute("colecaoClientesImoveisRemovidos");
        }
        
        
        if (imovelClientesNovos != null && !imovelClientesNovos.equals("")) {

            Iterator clienteImovelIterator = imovelClientesNovos.iterator();

            while (clienteImovelIterator.hasNext()) {
                clienteImovel = (ClienteImovel) clienteImovelIterator.next();
                //Verifica se pode remover o cliente.
                          
                for (int i = 0; i < clientesImoveis.length; i++) {
                    if (obterTimestampIdObjeto(clienteImovel) == new Long(clientesImoveis[i]).longValue()) {
                    	//if(!(colecaoClientesImoveisRemovidos.contains(clienteImovel))){
                    	                 
                    	
                    	
                    			if ((imovel.getImovelPerfil().getId().equals(
	                                    ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) 
                            	&&   (clienteImovel
	                                    .getClienteRelacaoTipo()
	                                    .getId().intValue() ==  
	                                     ConstantesSistema.CLIENTE_IMOVEL_TIPO_USUARIO.intValue()
	                                                            )){
                            		throw new ActionServletException(
                                    "atencao.remover.cliente.atualizar.imovel");
                            	}
                            	if ((imovel.getImovelPerfil().getId().equals(
	                                    ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) 
                            	&&
                            	(clienteImovel
	                                    .getClienteRelacaoTipo()
	                                    .getId().intValue() == ConstantesSistema.CLIENTE_IMOVEL_TIPO_PROPIETARIO.intValue()
	                                                            )){
                            		 throw new ActionServletException(
                                             "atencao.remover.cliente.atualizar.imovel");	                            		
                            	}
                            	
                            	   if (clienteImovel.getId() != null
                                         && !clienteImovel.getId().equals("")) {
        		
                            		
                            	
		                            // caso seja um cliente imóvel da base
	                                // então vai para o
	                                // exibirManterImovelFimRelacaoClienteImovel para
									// colocar
	                                // o motivo do fim da relação
	                                //retorno = actionMapping
	                                        //.findForward("exibirManterImovelFimRelacaoClienteImovelAction");
                            		httpServletRequest.setAttribute("aberturaPopup", "1");
                            		
                            		// Verifica permissão especial para manter cliente 
                        			// responsavel do imovel.
                        			Categoria categoria =
                        					fachada.obterPrincipalCategoriaImovel(clienteImovel.getImovel().getId());
                        	
                        				if(categoria.getId().compareTo(Categoria.PUBLICO)==0
                        						&& clienteImovel.getClienteRelacaoTipo().getId()
                        							.compareTo(ClienteRelacaoTipo.RESPONSAVEL.intValue())==0){
                        		
                        				boolean possuiPermissaoManterClienteResponsavelImoveisPublicos = 
                        						fachada.verificarPermissaoEspecialAtiva(
                        								PermissaoEspecial.ALTERAR_CLIENTE_RESPONSAVEL_PARA_IMOVEIS_PUBLICOS,usuarioLogado);
                        		
                        					if(!possuiPermissaoManterClienteResponsavelImoveisPublicos){
                        						
                                        		httpServletRequest.removeAttribute("aberturaPopup");
                        						throw new ActionServletException(
                        							"atencao.nao_usuario_nao_possui_permissao_alterar_cliente_reponsavel");
                        					}
                        	 	
                        			}
                        			fachada.verificaRestricaoDaTabelaClienteImovel(clienteImovel);
	                                colecaoClientesImoveisFimRelacao
	                                        .add(clienteImovel);
	                                
	                    			// [FS0019] - Verificar existência de negativação para o cliente-imóvel
	                    			// Exibir mensagem de advertência caso o cliente esteja em processo de negativação
	                    			// Adicionado por Victor Cisneiros (12/01/2009)
	                    			// ANALISTA: Fátima Sampaio
	                                
	                				Cliente cliente = clienteImovel.getCliente();
	                				Imovel im = clienteImovel.getImovel();
	                				
	                				if (cliente != null) {
	                					if (Fachada.getInstancia().verificarNegativacaoDoClienteImovel(cliente.getId(), im.getId())) {
	                						String confirmado = httpServletRequest.getParameter("confirmado");
	                						
	                						if (confirmado == null || !confirmado.equals("ok")) {
	                							httpServletRequest.setAttribute("nomeBotao1", "Prosseguir");
//	                							httpServletRequest.setAttribute("caminhoVoltar", "atualizarImovelWizardAction.do?destino=3&action=atualizarImovelEnderecoAction");
	                							
	                							return montarPaginaConfirmacao("atencao.advertencia.imovel.negativado", 
	                									httpServletRequest, actionMapping, new String[] { cliente.getDescricao(), im.getId().toString() });
	                						}
	                					}
	                				}

	                                sessao.setAttribute(
	                                        "colecaoClientesImoveisFimRelacao",
	                                        colecaoClientesImoveisFimRelacao);
		                            
		                            
                            	 }else{
 	                            	// verifica se o tipo do cliente é usuário
 		                            if (clienteImovel
 		                                    .getClienteRelacaoTipo()
 		                                    .getId().shortValue() == 
 		                                    ConstantesSistema.CLIENTE_IMOVEL_TIPO_USUARIO.shortValue()) {
 		                            		if(sessao.getAttribute(
 		                            			"idClienteImovelUsuario") != null){
 		                            				sessao.removeAttribute("idClienteImovelUsuario");	 
 		                            		}
 		                            }
 		                            
 		                            // verifica se o tipo do cliente é responsavel
 		                            if ((clienteImovel.getClienteRelacaoTipo().getId().shortValue()
 		                                     == ConstantesSistema.CLIENTE_IMOVEL_TIPO_RESPONSAVEL.shortValue())) {
 		                               if(sessao.getAttribute(
 		                           			"idClienteImovelResponsavel") != null){
 		                            	   sessao.removeAttribute("idClienteImovelResponsavel");	 
 		                               }
 		                            }
 		                         
 		                            
 	                            	if(!(colecaoClientesImoveisRemovidos.contains(clienteImovel))){
 	                            		fachada.verificaRestricaoDaTabelaClienteImovel(clienteImovel);  
 	                            		colecaoClientesImoveisRemovidos.add(clienteImovel);	
 	                            	}
 		                            clienteImovelIterator.remove();
                            		 
                            	}           	                           	
	                        //}                           	
                        }
                    }
                }
            }       
        	
        	sessao.setAttribute("colecaoClientesImoveisRemovidos", colecaoClientesImoveisRemovidos);	
        	            
        return retorno;
    }
}
