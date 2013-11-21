
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
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por gerar um filtro  de operação para pesquisa de acordo 
 * com os parâmetros informados pelo usuário
 *
 * @author Pedro Alexandre 
 * @date 12/05/2006
 */
public class FiltrarOperacaoAction   extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
		
		//Cria a variavel de retorno e seta o mapeamento para o exibirManterOperacaoAction
    	ActionForward retorno = actionMapping.findForward("retornarFiltroOperacao");
    	
    	//Recupera o form de filtrar operação
        FiltrarOperacaoActionForm filtrarOperacaoActionForm = (FiltrarOperacaoActionForm) actionForm;
        
        //Cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Cria uma flag que vai indicar se o usuário informou ao menos um campo para a filtragem
        Boolean peloMenosUmParametroInformado = false;
        
        //Recupera os campos informados pelo usuário na página para filtrar as operações 
        String idOperacao = filtrarOperacaoActionForm.getIdOperacao();
		String descricaoOperacao = filtrarOperacaoActionForm.getDescricaoOperacao();
		Integer idTipoOperacao = new Integer(filtrarOperacaoActionForm.getIdTipoOperacao());
		String idFuncionalidade = filtrarOperacaoActionForm.getIdFuncionalidade();	
		String tipoPesquisa = filtrarOperacaoActionForm.getTipoPesquisa();
		
		//Recupera o indicador de atualização do request
		// 1 check   --- null uncheck 
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");

		//Cria o filtro e carrega os objetos necessários no filtro para o exibirManterOperacaoAction
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("idOperacaoPesquisa");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("tabelaColuna");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA);

		//Caso o usário tenha informado o código da operação
		//seta o código da operação no filtro e indica que o usuário 
		//selecionou um parâmetro para a filtragem
		if (idOperacao != null && !idOperacao.equals("")){
			filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID,idOperacao)); 
			peloMenosUmParametroInformado = true;
		}		

		//Caso o usário tenha informado descrição da operação
		//seta a descrição da operação no filtro e indica que o usuário 
		//selecionou um parâmetro para a filtragem
		if ((descricaoOperacao != null)&& (!descricaoOperacao.trim().equals(""))) {
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroOperacao.adicionarParametro(new ComparacaoTextoCompleto(FiltroOperacao.DESCRICAO, descricaoOperacao));
			} else {
				filtroOperacao.adicionarParametro(new ComparacaoTexto(FiltroOperacao.DESCRICAO,descricaoOperacao));
			}
			peloMenosUmParametroInformado = true;
		}
		
		//Caso o usário tenha informado o tipo da operação
		//seta o código do tipo da operação no filtro e indica que o usuário 
		//selecionou um parâmetro para a filtragem
		if (idTipoOperacao != null && idTipoOperacao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
    		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.OPERACAO_TIPO_ID,idTipoOperacao.toString()));
			peloMenosUmParametroInformado = true;
		}
		

		//Caso o usário tenha informado o código da funcionalidade
		//seta o código da funcionalidade no filtro e indica que o usuário 
		//selecionou um parâmetro para a filtragem
		if (idFuncionalidade != null && !idFuncionalidade.trim().equals("")) {

			//[FS0002] - Verificar existência da funcionalidade 
			//Pesquisa a funcionalidade informada pelo usuário no sistema 
			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
			filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID,idFuncionalidade));
			Collection colecaoFuncionalidade = fachada.pesquisar(filtroFuncionalidade,Funcionalidade.class.getName());
			
			//Caso a funcionalidade informada não exista no sistema 
			//levanta uma execeção indicanf]do que a funcionalidade não existe
			//Caso contrário seta ocódigo da funcionalidade no filtro 
			if(colecaoFuncionalidade == null || colecaoFuncionalidade.isEmpty()){
				throw new ActionServletException("atencao.funcionalidade.inexistente");
			}else{
				filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.FUNCIONALIDADE_ID,idFuncionalidade));
				peloMenosUmParametroInformado = true;
			}
		}

		//[FS0003] - Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		//Seta na sessão o filtro criado para pesquisa de operação e a flag 
		//indicando que o usuário que ir diretopara a página de atualizar
		sessao.setAttribute("filtroOperacao",filtroOperacao);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
	   //Retorna o mapeamento contido na variável retorno 	
       return retorno;
    }
}
