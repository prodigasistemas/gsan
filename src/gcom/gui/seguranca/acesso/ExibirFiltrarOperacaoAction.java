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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroOperacaoTipo;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pelo processamento da exibição da página de filtrar operação 
 *
 * @author Pedro Alexandre
 * @date 12/05/2006
 */
public class ExibirFiltrarOperacaoAction  extends GcomAction {
  
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
    	//Seta o mapeamento de retorno para a página de filtrar
        ActionForward retorno = actionMapping.findForward("filtrarOperacao");
 		
        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Recuperao form de filtrar operação
		FiltrarOperacaoActionForm filtrarOperacaoActionForm = (FiltrarOperacaoActionForm) actionForm;

		//Cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //[FS0001] - Verificar existência de dados
        //Pesquisa os tipos de operações cadastradas no sistema
        //Caso não exista nenhum tipo de operação cadastrado levanta uma exceção indicando que 
        //não existem tipos de operações para seleção
		FiltroOperacaoTipo filtroOperacaoTipo = new FiltroOperacaoTipo();
		Collection<OperacaoTipo> colecaoOperacaoTipo = fachada.pesquisar(filtroOperacaoTipo,OperacaoTipo.class.getName());
		if (colecaoOperacaoTipo == null || colecaoOperacaoTipo.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Operação Tipo");
		}

		//Caso exista tipo de operação cadastrada
		//manda a coleção de tipo de operação para a página pela sessão
		sessao.setAttribute("colecaoOperacaoTipo", colecaoOperacaoTipo);

		//Recupera o código da funcionalidade se ela for digitada
		String idFuncionalidadeDigitada = filtrarOperacaoActionForm.getIdFuncionalidade();
		
		//Caso o código da funcionalidade tenha sido informado 
		if (idFuncionalidadeDigitada != null && !idFuncionalidadeDigitada.trim().equalsIgnoreCase("")) {
			
			//Pesquisa a funcionalidade digitada na base de dados
			Funcionalidade funcionalidade = this.pesquisarFuncionalidade(idFuncionalidadeDigitada);
			
			//[FS0002] - Verificar existência da funcionalidade
			//Caso exista a funcionalidade digitada na base de dados 
			//seta as informações da funcionalidade no form 
			//Caso contrário indica que a funcionalidade digitada não existe 
			if(funcionalidade != null){	
				filtrarOperacaoActionForm.setIdFuncionalidade(String.valueOf(funcionalidade.getId()));
				filtrarOperacaoActionForm.setDescricaoFuncionalidade(funcionalidade.getDescricao());
				httpServletRequest.setAttribute("funcionalidadeEncontrada", "true");

			} else {
				filtrarOperacaoActionForm.setIdFuncionalidade("");
				filtrarOperacaoActionForm.setDescricaoFuncionalidade("FUNCIONALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("funcionalidadeNaoEncontrada","exception");
			}
		} 
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
		}
		
		//SETA O TIPO DE PESQUISA DA DESCRICAO
		if (filtrarOperacaoActionForm.getTipoPesquisa() == null || filtrarOperacaoActionForm.getTipoPesquisa().equalsIgnoreCase("")) {
			filtrarOperacaoActionForm.setTipoPesquisa(""+ConstantesSistema.TIPO_PESQUISA_INICIAL);
		}
		
		/*
		 * ESQUEMA DO LIMPAR FORM
		 */
		if (httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
			filtrarOperacaoActionForm.setIdOperacao("");
			filtrarOperacaoActionForm.setDescricaoOperacao("");
			filtrarOperacaoActionForm.setIdTipoOperacao(""+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			filtrarOperacaoActionForm.setIdFuncionalidade("");
			filtrarOperacaoActionForm.setDescricaoFuncionalidade("");
			sessao.setAttribute("indicadorAtualizar", "1");
		}
		
		//Retorna o mapeamento contido na variável retorno
        return retorno;
    }

    
    /**
	 * Pesquisa a funcionalidade digitada na base de dados de acordo com o código passado
	 *
	 * [FS0002 - Pesquisar Funcionalidade]
	 *
	 * @author Pedro Alexandre
	 * @date 11/05/2006
	 *
	 * @param idFuncionalidade
	 * @return
	 */
	private Funcionalidade pesquisarFuncionalidade(String idFuncionalidade){
		//Cria a variável que vai armazenar a funcionalidade pesquisada
		Funcionalidade funcionalidade = null;
		
		//Cria o filtro para pesquisa e seta o código da funcionalidade informada no filtro
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID, idFuncionalidade));
		
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
}
