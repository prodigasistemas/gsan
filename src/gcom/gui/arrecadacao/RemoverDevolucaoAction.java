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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remove as devoluções selecionadas na lista da funcionalidade Manter Devolução
 * 
 * @author Fernanda Paiva
 * @created 09 de Março de 2006
 */
public class RemoverDevolucaoAction extends GcomAction {
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //------------ REGISTRAR TRANSAÇÃO ----------------
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_DEVOLUCOES_REMOVER);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------

        Fachada fachada = Fachada.getInstancia();
        
        // Obtém os ids de remoção
        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        if (ids != null) 
        {
			for (int i = 0; i < ids.length; i++) 
			{
				FiltroDevolucao filtroDevolucao =  new FiltroDevolucao();
				
				filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
				filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
		
				filtroDevolucao.adicionarParametro(new ParametroSimples(
						FiltroDevolucao.ID, ids[i]));
				
				Collection<Devolucao> devolucaoPesquisado = fachada.pesquisar(
						filtroDevolucao, Devolucao.class.getName());
				
				if (devolucaoPesquisado != null && !devolucaoPesquisado.isEmpty()) {
					
					Devolucao dadosDevolucao = (Devolucao) ((List) devolucaoPesquisado).get(0);
					
					//O endereço foi encontrado
					if ((dadosDevolucao.getDevolucaoSituacaoAtual() != null && !dadosDevolucao.getDevolucaoSituacaoAtual().equals("")) && (dadosDevolucao.getDevolucaoSituacaoAnterior() != null && !dadosDevolucao.getDevolucaoSituacaoAnterior().equals(""))) 
					{
						String situacaoAnterior = dadosDevolucao.getDevolucaoSituacaoAnterior().getDescricaoDevolucaoSituacao();
						String situacaoAtual = dadosDevolucao.getDevolucaoSituacaoAtual().getDescricaoDevolucaoSituacao();
						throw new ActionServletException(
	                    "atencao.devolucao.nao_excluir_situacao_alterada", ""
								+ situacaoAnterior, situacaoAtual);
					}
					
					FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();

					filtroAvisoBancario.adicionarParametro(new ParametroSimples(
							FiltroAvisoBancario.ID, dadosDevolucao.getAvisoBancario().getId()));

					Collection avisoBancario = fachada.pesquisar(filtroAvisoBancario,
							AvisoBancario.class.getName());
					
					BigDecimal valorFinal = null;

					if (avisoBancario != null && !avisoBancario.isEmpty()) {

						AvisoBancario dadosAvisoBancario = (AvisoBancario) ((List) avisoBancario).get(0);
						
						BigDecimal valorDevolucao2 = dadosAvisoBancario.getValorDevolucaoCalculado();
						
						valorFinal = (valorDevolucao2.subtract(dadosDevolucao.getValorDevolucao()));
						
					}
					fachada.atualizaValorArrecadacaoAvisoBancaraio(valorFinal, dadosDevolucao.getAvisoBancario().getId());

				}
			}
		}

		//Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a sessão
        //HttpSession sessao = httpServletRequest.getSession(false);
                
        //mensagem de erro quando o usuário tenta excluir sem ter selecionado nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException(
                    "atencao.registros.nao_selecionados");
        }

        //------------ REGISTRAR TRANSAÇÃO ----------------
    	UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
    	colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
        //------------ REGISTRAR TRANSAÇÃO ----------------
    	
        fachada.remover(ids, Devolucao.class.getName(),operacaoEfetuada, colecaoUsuarios);
        
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Devoluçao(ões) removida(s) com sucesso.",
                    "Realizar outra Manutenção de Devolução",
                    "exibirFiltrarDevolucaoAction.do?tela=manterDevolucao&menu=sim");
        }

        return retorno;
    }
}