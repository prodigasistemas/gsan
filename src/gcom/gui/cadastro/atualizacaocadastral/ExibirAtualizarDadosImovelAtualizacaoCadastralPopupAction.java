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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Action para exibir a página de Atualizacao Cadastral
 * 
 * @author Ivan Sergio
 * @created 02/06/2009
 */
public class ExibirAtualizarDadosImovelAtualizacaoCadastralPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = 
			actionMapping.findForward("exibirAtualizarDadosImovelAtualizacaoCadastralPopup");
		
		// Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Obtém o action form
		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm atualizacaoCadastralActionForm =
			(ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();
		
		String idImovel = (String) httpServletRequest.getParameter("idImovel");
		String idCliente = (String) httpServletRequest.getParameter("idCliente");
		String idArquivo = (String) httpServletRequest.getParameter("idArquivo");
		Collection colecaoDadosTabelaAtualizacaoCadastral = null;
		String idRegistroAlterado = (String) httpServletRequest.getParameter("idRegistroAlterado");
		String idTipoAlteracao = (String) httpServletRequest.getParameter("idTipoAlteracao");
		
		// Realiza o Filtro para o Imovel
		if ( (idImovel != null && !idImovel.equals("")) || (idCliente != null && !idCliente.equals("")) ) {
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			
			if (idImovel != null && !idImovel.equals("")) {
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
				//idRegistroAlterado = new Integer(idImovel);
			}
			
			if (idCliente != null && !idCliente.equals("")) {
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, idCliente));
				//idRegistroAlterado = new Integer(idCliente);
			}
			
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.LOCALIDADE);
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.SETOR_COMERCIAL);
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.QUADRA);
			
			ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(fachada.pesquisar(
					filtroClienteImovel, ClienteImovel.class.getName()));
			
			if (clienteImovel != null) {
				Imovel imovel = clienteImovel.getImovel();
				Localidade localidade = (Localidade) imovel.getLocalidade();
				SetorComercial setorComercial = (SetorComercial) imovel.getSetorComercial();
				Quadra quadra = (Quadra) imovel.getQuadra();
				
				// Imovel
				atualizacaoCadastralActionForm.setIdImovel(imovel.getId().toString());
				// Localidade
				atualizacaoCadastralActionForm.setIdLocalidade(localidade.getId().toString());
				atualizacaoCadastralActionForm.setDescricaoLocalidade(localidade.getDescricao());
				// Setor Comercial
				atualizacaoCadastralActionForm.setIdSetorComercial(setorComercial.getId().toString());
				atualizacaoCadastralActionForm.setCodigoSetorComercial("" + setorComercial.getCodigo());
				atualizacaoCadastralActionForm.setDescricaoSetorComercial(setorComercial.getDescricao());
				// Quadra
				atualizacaoCadastralActionForm.setIdQuadra(quadra.getId().toString());
				atualizacaoCadastralActionForm.setNumeroQuadra("" + quadra.getNumeroQuadra());
			}else {
				// Imovel
				atualizacaoCadastralActionForm.setIdImovel("Incluido");
				// Localidade
				atualizacaoCadastralActionForm.setIdLocalidade(null);
				atualizacaoCadastralActionForm.setDescricaoLocalidade(null);
				// Setor Comercial
				atualizacaoCadastralActionForm.setIdSetorComercial(null);
				atualizacaoCadastralActionForm.setCodigoSetorComercial(null);
				atualizacaoCadastralActionForm.setDescricaoSetorComercial(null);
				// Quadra
				atualizacaoCadastralActionForm.setIdQuadra(null);
				atualizacaoCadastralActionForm.setNumeroQuadra(null);
/*				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"Dados do Imovel");*/
			}
		}else {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Dados do Imovel e Cliente");
		}
		
		// Consulta os dados da Tabela Atualizacao Cadastral
		Integer arquivo = new Integer(idArquivo);
		if(idCliente != null && !idCliente.equals("")){
			colecaoDadosTabelaAtualizacaoCadastral = fachada.consultarDadosTabelaColunaAtualizacaoCadastral(
					new Integer(idRegistroAlterado), arquivo, new Integer(idImovel), new Integer(idCliente),new Integer(idTipoAlteracao));
		}else{
			colecaoDadosTabelaAtualizacaoCadastral = fachada.consultarDadosTabelaColunaAtualizacaoCadastral(
					new Integer(idRegistroAlterado), arquivo, new Integer(idImovel), null,new Integer(idTipoAlteracao));
		}
		
		if (colecaoDadosTabelaAtualizacaoCadastral != null && !colecaoDadosTabelaAtualizacaoCadastral.isEmpty()) {
			sessao.setAttribute("colecaoDadosTabelaAtualizacaoCadastral", colecaoDadosTabelaAtualizacaoCadastral);
		}else { 
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Dados Tabela Atualizacao Cadastral");
		}

		return retorno;
	}
}
