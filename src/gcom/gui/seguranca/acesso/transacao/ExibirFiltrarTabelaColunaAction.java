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
package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pelo processamento da exibição da página de filtrar tabela coluna 
 *
 * @author Francisco do Nascimento
 * @date 20/02/08
 */
public class ExibirFiltrarTabelaColunaAction  extends GcomAction {
  
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
    	//Seta o mapeamento de retorno para a página de filtrar
        ActionForward retorno = actionMapping.findForward("filtrarTabelaColuna");
 		
       
        //Recuperao form de filtrar operação
		FiltrarTabelaColunaActionForm filtrarForm = (FiltrarTabelaColunaActionForm) actionForm;

		//Cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o código da funcionalidade se ela for digitada
		String idFuncionalidadeDigitada = filtrarForm.getIdFuncionalidade();
		
		//Caso o código da funcionalidade tenha sido informado 
		if (idFuncionalidadeDigitada != null && !idFuncionalidadeDigitada.trim().equalsIgnoreCase("")) {
			
			//Pesquisa a funcionalidade digitada na base de dados
			Funcionalidade funcionalidade = this.pesquisarFuncionalidade(idFuncionalidadeDigitada);
			
			//[FS0002] - Verificar existência da funcionalidade
			//Caso exista a funcionalidade digitada na base de dados 
			//seta as informações da funcionalidade no form 
			//Caso contrário indica que a funcionalidade digitada não existe 
			if(funcionalidade != null){	
				filtrarForm.setIdFuncionalidade(String.valueOf(funcionalidade.getId()));
				filtrarForm.setNomeFuncionalidade(funcionalidade.getDescricao());
				httpServletRequest.setAttribute("funcionalidadeEncontrada", "true");

				Vector operacoes = new Vector();
				
				// verificando se tem operacao de pesquisa
				if (funcionalidade.getOperacoes() != null){
					for (Iterator iterator = funcionalidade.getOperacoes().iterator(); 
						iterator.hasNext();) {
						Operacao operacao = (Operacao) iterator.next();
						operacao.setDescricao(operacao.getDescricao().toUpperCase());
						operacoes.add(operacao);
					}
				}											
				sessao.setAttribute("operacoes", operacoes);
			} else {
				filtrarForm.setIdFuncionalidade("");
				filtrarForm.setNomeFuncionalidade("FUNCIONALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("funcionalidadeNaoEncontrada","exception");
			}
		} 

		//Recupera o código da funcionalidade se ela for digitada
		String idTabelaDigitada = filtrarForm.getIdTabela();
		
		//Caso o código da funcionalidade tenha sido informado 
		if (idTabelaDigitada != null && !idTabelaDigitada.trim().equalsIgnoreCase("")) {
			
			//Pesquisa a tabela digitada na base de dados
			Tabela tabela = this.pesquisarTabela(idTabelaDigitada);
			
			sessao.setAttribute("tabela", tabela);
			
			//Verificar existência da tabela
			//Caso exista a tabela digitada na base de dados 
			//seta as informações da tabela no form 
			//Caso contrário indica que a tabela digitada não existe 
			if(tabela != null){	
				filtrarForm.setIdTabela(String.valueOf(tabela.getId()));
				filtrarForm.setNomeTabela(tabela.getNomeTabela());
				httpServletRequest.setAttribute("tabelaNaoEncontrada", "false");

			} else {
				filtrarForm.setIdTabela("");
				filtrarForm.setNomeTabela("TABELA INEXISTENTE");
				httpServletRequest.setAttribute("tabelaNaoEncontrada","true");
			}
		} 		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
		}
		
		/*
		 * ESQUEMA DO LIMPAR FORM
		 */
		if (httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
			filtrarForm.setIdFuncionalidade("");
			filtrarForm.setNomeFuncionalidade("");
			filtrarForm.setIdTabela("");
			filtrarForm.setNomeTabela("");			
			sessao.setAttribute("indicadorAtualizar", "1");
		}
		
		//Retorna o mapeamento contido na variável retorno
        return retorno;
    }

    
    /**
	 * Pesquisa a funcionalidade digitada na base de dados de acordo com o código passado
	 * [FS0002 - Pesquisar Funcionalidade]
	 * @param idFuncionalidade
	 * @return
	 */
	private Funcionalidade pesquisarFuncionalidade(String idFuncionalidade){
		//Cria a variável que vai armazenar a funcionalidade pesquisada
		Funcionalidade funcionalidade = null;
		
		//Cria o filtro para pesquisa e seta o código da funcionalidade informada no filtro
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID, idFuncionalidade));
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.OPERACOES);
		//Pesquisa a funcionalidade na base de dados
		Collection colecaoFuncionalidade = Fachada.getInstancia().pesquisar(filtroFuncionalidade,Funcionalidade.class.getName());
		
		//Caso exista a funcionalidade cadastrada na base de dados 
		//recupera a funcionalidade da coleção
		if(colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()){
			funcionalidade = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
		}
		
		//Retorna a funcionalidade pesquisa ou nulo se a funcionalidade não for encontrada
		return funcionalidade;
		
	}
	
    /**
	 * Pesquisa a tabela digitada na base de dados de acordo com o código passado
	 * @param idTabela
	 * @return
	 */
	private Tabela pesquisarTabela(String idTabela){
		//Cria a variável que vai armazenar a tabela pesquisada
		Tabela tabela = null;
		
		//Cria o filtro para pesquisa e seta o código da tabela informada no filtro
		FiltroTabela filtro = new FiltroTabela();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTabela.ID, idTabela));
		
		//Pesquisa a tabela na base de dados
		Collection colecaoTabela = Fachada.getInstancia().pesquisar(filtro, Tabela.class.getName());
		
		//Caso exista a tabela cadastrada na base de dados 
		//recupera a tabela da coleção
		if(colecaoTabela != null && !colecaoTabela.isEmpty()){
			tabela = (Tabela) Util.retonarObjetoDeColecao(colecaoTabela);
		}
		
		//Retorna a tabela pesquisa ou nulo se a tabela não for encontrada
		return tabela;		
	}
	
}
