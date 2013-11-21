package gcom.gui.arrecadacao;

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


import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * @author Vinicius Medeiros
 * @date 26/05/2006
 */
public class ExibirPesquisarAgenciaBancariaAction extends GcomAction {

	/**
	 * [UC0804] Pesquisar Agencia Bancaria
	 *
	 * @author Vinicius Medeiros
	 * @date 26/05/2006
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
		
		//Seta o mapeamento para a o popup de adicionar banco
		ActionForward retorno = actionMapping.findForward("agenciaBancariaPesquisar");
		
		//Recupera o form de pesquisar agencia bancaria
		PesquisarAgenciaBancariaActionForm pesquisarAgenciaBancariaActionForm = (PesquisarAgenciaBancariaActionForm) actionForm;
		
		//Cria uma instância da fachada 
		Fachada fachada = Fachada.getInstancia();
		
		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o código da agencia se ele foi digitado
		String bancoID = pesquisarAgenciaBancariaActionForm.getBancoID();
		
		// Seta o tipo de pesquisa 
		pesquisarAgenciaBancariaActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		
		//Recupera para onde a tela de popup deve retornar
		//e seta o valor na sessão
		String retornarTela = httpServletRequest.getParameter("retornarTela");
		if(retornarTela != null){
			sessao.setAttribute("retornarTela",retornarTela);
		}

		pesquisarAgenciaBancariaActionForm.setCodigo("");
		
		
		//Recupera a flag que indica se é para limpar o form de adicionar banco
		String limpaForm = httpServletRequest.getParameter("limpaForm");
		
		if (bancoID != null
				&& !bancoID.trim().equalsIgnoreCase("")
				&& httpServletRequest.getParameter("exibirPesquisarBanco") == null
				&& httpServletRequest.getParameter("limparForm") == null) {

			sessao.setAttribute("bancoRecebido", bancoID);
		}
		
		//Caso a flag de limpar o form seja true 
		//Limpa os dados dos campos do banco
		if(limpaForm != null && limpaForm.equalsIgnoreCase("true")){
			pesquisarAgenciaBancariaActionForm.setBancoID("");
			pesquisarAgenciaBancariaActionForm.setBanco("");
		}else{
			//Caso a flag de limpar o form não for true
			//e caso o código do banco tenha sido digitado 
			//pesquisa o banco informada na base de dados
			if (bancoID != null && !bancoID.equals("")){
				
				//Cria o filtro e setao código do banco informado no filtro
				FiltroBanco filtroBanco = new FiltroBanco();
				filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID,bancoID));
				
				//Pesquisa o banco na base de dados
				Collection colecaoBanco = fachada.pesquisar(filtroBanco, Banco.class.getName());
				
				//Caso o banco tenha sido encontrada 
				//Recupera o banco e seta as informações no form de adicionar
				//Caso contrário indica que o banco não existe 
				if (colecaoBanco != null && !colecaoBanco.isEmpty()) {
					Banco banco = (Banco) Util.retonarObjetoDeColecao(colecaoBanco);
					pesquisarAgenciaBancariaActionForm.setBancoID(String.valueOf(banco.getId()));
					pesquisarAgenciaBancariaActionForm.setBanco(banco.getDescricao());
					httpServletRequest.setAttribute("operacaoBancoEncontrado", "true");
	
				} else {
					pesquisarAgenciaBancariaActionForm.setBancoID("");
					pesquisarAgenciaBancariaActionForm.setBanco("BANCO INEXISTENTE");
					httpServletRequest.setAttribute("operacaoBancoNaoEncontrado","exception");
				}
			}
		}
		
		if (httpServletRequest.getParameter("id") != null
				&& !httpServletRequest.getParameter("id").equals("")) {

			String id = httpServletRequest.getParameter("id");
			String descricao = httpServletRequest.getParameter("descricao");
			
			pesquisarAgenciaBancariaActionForm.setBancoID(id);
			pesquisarAgenciaBancariaActionForm.setBanco(descricao);

		}
		
		
		//Seta a flag para indicar que o popup vai ser fechado
		httpServletRequest.setAttribute("fechaPopup", "false");

		//Retorna o mapeamento contido na variável retorno 
		return retorno;

	}

	
}
