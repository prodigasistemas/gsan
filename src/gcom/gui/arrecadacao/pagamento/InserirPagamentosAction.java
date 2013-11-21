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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;


/**
 * Action responsável por inseri os pagamentos no sistema 
 *
 * @author Pedro Alexandre
 * @date 15/03/2006
 */
public class InserirPagamentosAction extends GcomAction {

   
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * <Breve descrição sobre o subfluxo>
     *
     * <Identificador e nome do subfluxo>	
     *
     * <Breve descrição sobre o fluxo secundário>
     *
     * <Identificador e nome do fluxo secundário> 
     *
     * @author Pedro Alexandre
     * @date 16/02/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Cria a variável de retorno 
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        Integer idPagamento = null;

        //Recupera o form 
        DynaValidatorActionForm pagamentoActionForm = (DynaValidatorActionForm) actionForm;
        
        //Recupera o tipo de inclusão
        String tipoInclusao = (String) pagamentoActionForm.get("tipoInclusao");
                
        //Recupera o aviso bancário e pequisa o objeto no sistema
        String idAvisoBancario = (String)pagamentoActionForm.get("idAvisoBancario");
        FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        AvisoBancario avisoBancario = (AvisoBancario)(fachada.pesquisar(filtroAvisoBancario,AvisoBancario.class.getName())).iterator().next();
        
        //Caso o tipo de inclusão tenha sido manual
        if(tipoInclusao.equalsIgnoreCase("manual")){
        	
        	/*
        	 * Alterado por Raphael Rossiter em 26/09/2007
        	 * OBJ: Inserir manualmente mais de um pagamento por sessao.
        	 */
        	
        	Collection<Pagamento> colecaoPagamento = (Collection) sessao.getAttribute("colecaoPagamento");
        	
        	idPagamento = fachada.inserirPagamentos(colecaoPagamento, usuarioLogado, avisoBancario);
        	
        	
          //Caso o tipo de inclusão seja por caneta óptica 	 
        }else if(tipoInclusao.equalsIgnoreCase("caneta") || tipoInclusao.equalsIgnoreCase("ficha")){
        	
        	/*
        	 * Alterado por Raphael Rossiter em 30/10/2007
        	 */
        	
        	Collection<Pagamento> colecaoPagamentos = new ArrayList();
        	Collection<Devolucao> colecaoDevolucoes = new ArrayList();
        	
        	//Recupera a coleçaõ de documentos da sessão contendo o código de barras e o valor do pagamento
            Collection<InserirPagamentoViaCanetaHelper> colecaoInserirPagamentoViaCanetaHelper = 
            (Collection<InserirPagamentoViaCanetaHelper>) sessao.getAttribute("colecaoInserirPagamentoViaCanetaHelper");
        	
            //[FS0006] - Verificar existência de documento na lista
            //Caso não exista nenhum documento na lista, levanta uma exceção 
            //para o usuário indicando que nenhum documento foi informado
            if(colecaoInserirPagamentoViaCanetaHelper == null || colecaoInserirPagamentoViaCanetaHelper.isEmpty()){
            	throw new ActionServletException("atencao.documento_naoinformado");
            }
            
            //[FS0025] – Verificar valor do aviso bancário
            //Caso o valor calculado do aviso bancário seja maior que valor informado 
            if (avisoBancario.getValorArrecadacaoCalculado()
            		.compareTo(avisoBancario.getValorArrecadacaoInformado()) == 1 ){
            	throw new ActionServletException("atencao.soma_dos_valores_maior_informado");
            }
        	
        	for(InserirPagamentoViaCanetaHelper pagamentoViaCanetaHelper : colecaoInserirPagamentoViaCanetaHelper){
            	
            	colecaoPagamentos.addAll(pagamentoViaCanetaHelper.getColecaoPagamento());
            	
            	if (pagamentoViaCanetaHelper.getColecaoDevolucao() != null &&
            		!pagamentoViaCanetaHelper.getColecaoDevolucao().isEmpty()){
            		
            		colecaoDevolucoes.addAll(pagamentoViaCanetaHelper.getColecaoDevolucao());
            	}
        	}
        	
        	idPagamento = fachada.inserirPagamentosCodigoBarras(colecaoPagamentos, colecaoDevolucoes, 
        	usuarioLogado, avisoBancario);
        	
        }else{
        	////Caso o tipo de inclusão não tenha sido informado, levanta uma exceção para o usuário 
        	//indicando que o tipo de inclusão não foi informado 
        	throw new ActionServletException("atencao.naoinformado",null, "Tipo de Inclusão");
        }
        
        //Caso o retorno seja para a telade sucesso,
        //Monta a tela de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
        	
        	//Limpa a sessão
            sessao.removeAttribute("colecaoFormaArrecadacao");
            sessao.removeAttribute("PagamentoActionForm");
            sessao.removeAttribute("colecaoDocumentoTipo");
            sessao.removeAttribute("colecaoPagamentos");
            sessao.removeAttribute("colecaoInserirPagamentoViaCanetaHelper");
        	
           /* montarPaginaSucesso(httpServletRequest, mensagemSucesso,
            "Inserir outro Pagamento", "exibirInserirPagamentosAction.do");*/
            
            
            montarPaginaSucesso(httpServletRequest, "Pagamento inserido com sucesso."
            		, "Inserir outro Pagamento",
                    "exibirInserirPagamentosAction.do?menu=sim",
                    "exibirAtualizarPagamentosAction.do?idRegistroInseridoAtualizar="
					+ idPagamento,
					"Atualizar Pagamento Inserido");

        
        
            
            
        }

        //Retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
