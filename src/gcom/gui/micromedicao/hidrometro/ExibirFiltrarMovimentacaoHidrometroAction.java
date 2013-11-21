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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroMotivoMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 *
 * @author Fernanda Paiva
 * @created 23 de Janeiro de 2006
 */
public class ExibirFiltrarMovimentacaoHidrometroAction extends GcomAction {
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

        // Obtém o action form
        HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;

        String tela = (String) httpServletRequest.getParameter("tela");
        
        String limparCampos = (String) httpServletRequest.getParameter("limparCampos");

        // Seta a ação de retorno
        ActionForward retorno = actionMapping
                .findForward("filtrarMovimentacaoHidrometro");

        // Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        String localArmazenagemOrigem = hidrometroActionForm
        		.getLocalArmazenagemOrigem();

        String localArmazenagemDestino = hidrometroActionForm
        		.getLocalArmazenagemDestino();

        String usuario = hidrometroActionForm
				.getUsuario();

        // Obtém a facahda
        Fachada fachada = Fachada.getInstancia();

        // Obtém o objetoCosulta vindo no request
        String objetoConsulta = (String) httpServletRequest
                .getParameter("objetoConsulta");

        // Obtém o objetoCosulta vindo no request 
        String tipo = (String) httpServletRequest
        		.getParameter("tipo");

        // Carregar a data corrente do sistema
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData.format(dataCorrente.getTime()));
        
        // Verifica se o objeto é diferente de nulo
        if (objetoConsulta != null
                && !objetoConsulta.trim().equalsIgnoreCase("")
                && (Integer.parseInt(objetoConsulta)) == 1) {

            // Filtro para obter o local de armazenagem ativo de id informado
            FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

            if (localArmazenagemOrigem != null  && Integer.parseInt(tipo) == 1) {
                filtroHidrometroLocalArmazenagem
                        .adicionarParametro(new ParametroSimples(
                                FiltroHidrometroLocalArmazenagem.ID ,
                                new Integer(hidrometroActionForm
                                        .getLocalArmazenagemOrigem()),
                                ParametroSimples.CONECTOR_AND));
            } else if (localArmazenagemDestino != null  && Integer.parseInt(tipo) == 2) {
                filtroHidrometroLocalArmazenagem
                        .adicionarParametro(new ParametroSimples(
                                FiltroHidrometroLocalArmazenagem.ID,
                                new Integer(hidrometroActionForm
                                        .getLocalArmazenagemDestino()),
                                ParametroSimples.CONECTOR_AND));
            }
            filtroHidrometroLocalArmazenagem
                    .adicionarParametro(new ParametroSimples(
                            FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
                            ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroLocalArmazenagem
                    .setCampoOrderBy( FiltroHidrometroLocalArmazenagem.DESCRICAO);

            // Pesquisa de acordo com os parâmetros informados no filtro
            Collection colecaoHidrometroLocalArmazenagem = fachada.pesquisar(
                    filtroHidrometroLocalArmazenagem,
                    HidrometroLocalArmazenagem.class.getName());

            // Verifica se a pesquisa retornou algum objeto para a coleção
            if (colecaoHidrometroLocalArmazenagem != null
                    && !colecaoHidrometroLocalArmazenagem.isEmpty()) {

                // Obtém o objeto da coleção pesquisada
                HidrometroLocalArmazenagem hidrometroLocalArmazenagem = (HidrometroLocalArmazenagem) Util
                        .retonarObjetoDeColecao(colecaoHidrometroLocalArmazenagem);

                if(localArmazenagemOrigem != null  && Integer.parseInt(tipo) == 1){
                	 // Exibe o código e a descrição pesquisa na página
                    httpServletRequest.setAttribute("corLocalArmazenagemOrigem", "valor");
                }else{
                	if(localArmazenagemDestino != null  && Integer.parseInt(tipo) == 2){
                		 // Exibe o código e a descrição pesquisa na página
                        httpServletRequest.setAttribute("corLocalArmazenagemDestino", "valor");
                	}
                }
               
                if (localArmazenagemOrigem != null && Integer.parseInt(tipo) == 1) {
                    hidrometroActionForm
                            .setLocalArmazenagemOrigem(hidrometroLocalArmazenagem
                                    .getId().toString());

                    hidrometroActionForm
                            .setLocalArmazenagemDescricaoOrigem(hidrometroLocalArmazenagem
                                    .getDescricao());

                }
                if (localArmazenagemDestino != null && Integer.parseInt(tipo) == 2) {
                    hidrometroActionForm
                            .setLocalArmazenagemDestino(hidrometroLocalArmazenagem
                                    .getId().toString());

                    hidrometroActionForm
                            .setLocalArmazenagemDescricaoDestino(hidrometroLocalArmazenagem
                                    .getDescricao());
                }
                
            } else {
                if (localArmazenagemOrigem != null
						&& !localArmazenagemOrigem.equals("") && Integer.parseInt(tipo) == 1) {
					hidrometroActionForm
							.setLocalArmazenagemDescricaoOrigem(ConstantesSistema.CODIGO_LOCAL_ARMAZENAGEM_INEXISTENTE);
					
					hidrometroActionForm
					.setLocalArmazenagemOrigem("");

					// Exibe mensagem de código inexiste e limpa o campo de
					// código
					httpServletRequest.setAttribute("corLocalArmazenagemOrigem",
							"exception");
				}
				if (localArmazenagemDestino != null
						&& !localArmazenagemDestino.equals("") && Integer.parseInt(tipo) == 2) {
					hidrometroActionForm
							.setLocalArmazenagemDescricaoDestino(ConstantesSistema.CODIGO_LOCAL_ARMAZENAGEM_INEXISTENTE);
					
					hidrometroActionForm
					.setLocalArmazenagemDestino("");
					// Exibe mensagem de código inexiste e limpa o campo de
					// código
					httpServletRequest.setAttribute("corLocalArmazenagemDestino",
							"exception");
                }
            }
        } 
        
        // Pesquisa Usuario
        if(usuario != null && !usuario.equals("")){
        	
        	FiltroUsuario filtroUsuario = new FiltroUsuario();  
            
        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, usuario));
            
            Collection colecaoUsuario = fachada.pesquisar(
                    filtroUsuario,Usuario.class.getName());
            
            if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
            	httpServletRequest.setAttribute("corUsuario", "valor");
            	
				// O imovel foi encontrado
				hidrometroActionForm.setUsuario(""
						+ ((Usuario) ((List) colecaoUsuario).get(0)).getId());
				hidrometroActionForm.setNomeUsuario(""
						+ ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
			} else {
				httpServletRequest.setAttribute("corUsuario","exception");
            	hidrometroActionForm
            		.setNomeUsuario(ConstantesSistema.USUARIO_INEXISTENTE);
			}
        }
        if (sessao.getAttribute("colecaoHidrometroMotivoMovimentacao") == null) {
            // Filtro de hidrômetro motivo movimentacao para obter todas os
            // motivo ativas
            FiltroHidrometroMotivoMovimentacao filtroHidrometroMotivoMovimentacao = new FiltroHidrometroMotivoMovimentacao();

            filtroHidrometroMotivoMovimentacao
                    .adicionarParametro(new ParametroSimples(
                            FiltroHidrometroMotivoMovimentacao.INDICADOR_USO,
                             ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroMotivoMovimentacao
                    .setCampoOrderBy(FiltroHidrometroMotivoMovimentacao.DESCRICAO);

            // Pesquisa a coleção de classe metrológica
            Collection colecaoHidrometroMotivoMovimentacao = fachada.pesquisar(
                    filtroHidrometroMotivoMovimentacao,
                    HidrometroMotivoMovimentacao.class.getName());
            // Envia as coleções na sessão
            sessao.setAttribute("colecaoHidrometroMotivoMovimentacao",
                    colecaoHidrometroMotivoMovimentacao);

        }
        if(limparCampos != null)
        {
        	hidrometroActionForm.reset(actionMapping, httpServletRequest);
        	limparCampos = null;
        }
        sessao.setAttribute("tela", tela);
        return retorno;
    }

}