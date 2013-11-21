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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.bean.DadosNegativacaoPorImovelHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade validar as informações da segunda aba do
 * processo de inserção de um Comando de Negativação
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class InserirComandoNegativacaoMatriculaImovelAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ControladorException {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
		
        //Pesquisa Usuario 
		String usuario = inserirComandoNegativacaoActionForm.getUsuario();
		
        if(usuario != null && !usuario.equals("")){
        	
        	FiltroUsuario filtroUsuario = new FiltroUsuario();  
            
        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, usuario));
            
            Collection colecaoUsuario = fachada.pesquisar(
                    filtroUsuario,Usuario.class.getName());
            
            if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
            	httpServletRequest.setAttribute("corUsuario", "valor");
            	
            	inserirComandoNegativacaoActionForm.setUsuario(""
						+ ((Usuario) ((List) colecaoUsuario).get(0)).getId());
            	inserirComandoNegativacaoActionForm.setNomeUsuario(""
						+ ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
			} else {
				throw new ActionServletException(
							"atencao.cliente.inexistente");
			}
        }
        
		Collection<DadosNegativacaoPorImovelHelper> colecaoDadosNegativacaoPorImovelHelper = (Collection)sessao.getAttribute("colecaoDadosNegativacaoPorImovelHelper");

		if(colecaoDadosNegativacaoPorImovelHelper == null || colecaoDadosNegativacaoPorImovelHelper.isEmpty()){
			throw new ActionServletException(
					"atencao.informe_imovel_negativacao");
		}
		
		//Verifica o bloqueio de negativação para o cliente
		Iterator iColecaoDadosNegativacao = colecaoDadosNegativacaoPorImovelHelper.iterator();
		
		while (iColecaoDadosNegativacao.hasNext()){
			
			DadosNegativacaoPorImovelHelper dadosNegativacao = (DadosNegativacaoPorImovelHelper)iColecaoDadosNegativacao.next();
			
			Collection colecaoCliente = null;
			if (dadosNegativacao.getIdCliente() != null && !dadosNegativacao.getIdCliente().equals("")){
				
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, dadosNegativacao.getIdCliente()));
				
				colecaoCliente = fachada.pesquisarCliente(filtroCliente);
				
				Iterator icliente = colecaoCliente.iterator();
		        Cliente cliente = (Cliente) icliente.next();
				if (cliente.getIndicadorPermiteNegativacao().equals(ConstantesSistema.NAO)){
		        	  throw new ActionServletException("atencao.cliente_bloqueado_negativacao", cliente.getNome());
		          }
				
			} 
		}
		
		Integer idNegativador = new Integer(inserirComandoNegativacaoActionForm.getIdNegativador());
		String identificacaoCI = inserirComandoNegativacaoActionForm.getIdentificacaoCI();
		Integer idUsuario = new Integer(inserirComandoNegativacaoActionForm.getUsuario());
	
		fachada.gerarMovimentoInclusaoNegativacao(null, 
				ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS, 
				identificacaoCI, 
				idNegativador, 
				idUsuario, 
				colecaoDadosNegativacaoPorImovelHelper, 
				new Date(),
				inserirComandoNegativacaoActionForm.getIndicadorBaixaRenda(),
				inserirComandoNegativacaoActionForm.getIndicadorContaNomeCliente(),
				inserirComandoNegativacaoActionForm.getIndicadorImovelCategoriaPublico());		

		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Foram enviados "+colecaoDadosNegativacaoPorImovelHelper.size()
				+ " imóveis para negativação.", "Efetuar outra Negativação",
				"");
		
		return retorno;
	}
}
