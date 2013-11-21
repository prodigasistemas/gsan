/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que inicializa a primeira página do processo de inserir pagamentos
 * [UC0971] Inserir Pagamentos para Faturas Especiais
 * 
 * @author 	Vivianne Sousa
 * @created	21/12/2009
 */
public class ExibirInserirPagamentosFaturasEspeciaisAvisoBancarioAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //seta o mapeamento de retorno para a página da primeira aba
        ActionForward retorno = actionMapping.findForward("inserirPagamentosFaturasEspeciaisAvisoBancario");
        
        PagamentosFaturasEspeciaisActionForm pagamentoActionForm = (PagamentosFaturasEspeciaisActionForm) actionForm;

        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //cria o filtro de forma de arrecadação
        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        
        //seta a ordenação do resultado da pesquisa
        filtroArrecadacaoForma.setCampoOrderBy(FiltroArrecadacaoForma.DESCRICAO);

        //pesquisa as formas de arrecadação no sistema
        Collection<ArrecadacaoForma> colecaoFormaArrecadacao = fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
        	
        //se a coleção de forma de arrecadação estiver nula ou vazia 
        if(colecaoFormaArrecadacao == null || colecaoFormaArrecadacao.isEmpty()){
        	//[FS0002] levanta exceção
        	throw new ActionServletException("atencao.naocadastrado", null,"Forma de Arrecadação");
        }else{
        	//manda a coleção de forma de arrecadação para a sessão
        	sessao.setAttribute("colecaoFormaArrecadacao", colecaoFormaArrecadacao);	
        }
        
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData.format(dataCorrente.getTime()));
        
        String idAvisoBancario = pagamentoActionForm.getIdAvisoBancario();
        
        if (idAvisoBancario != null && !idAvisoBancario.trim().equals("")) {
        	FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        	filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        	filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        	
        	Collection colecaoAvisoBancario = fachada.pesquisar(filtroAvisoBancario, AvisoBancario.class.getName());
        	
        	if (colecaoAvisoBancario != null && !colecaoAvisoBancario.isEmpty()) {
        		AvisoBancario avisoBancario = (AvisoBancario) Util.retonarObjetoDeColecao(colecaoAvisoBancario);
        		
        		pagamentoActionForm.setCodigoAgenteArrecadador(avisoBancario.getArrecadador().getCodigoAgente().toString());
        		pagamentoActionForm.setDataLancamentoAviso(Util.formatarData(avisoBancario.getDataLancamento()));
        		pagamentoActionForm.setNumeroSequencialAviso(avisoBancario.getNumeroSequencial().toString());
        		
        	} else {
        		
        		pagamentoActionForm.setIdAvisoBancario("");
        		pagamentoActionForm.setCodigoAgenteArrecadador("");
        		pagamentoActionForm.setDataLancamentoAviso("AVISO INEXISTENTE");
        		pagamentoActionForm.setNumeroSequencialAviso("");
        		
        		httpServletRequest.setAttribute("avisoInexistente", true);
        	}
        } else {
        	pagamentoActionForm.setDataLancamentoAviso("");
        }
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
