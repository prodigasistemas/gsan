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


package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
 * 
 * @author Hugo Leonardo, Ivan Sergio
 * @date 14/01/2010
 * 		 14/09/2010 - CRC4734 - Retirar do filtro o perfil do imóvel e obter as contas a partir
 * 								de fatura item da fatura selecionada;
 */

public class ExibirGerarRelatorioImoveisProgramasEspeciaisAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioImoveisProgramasEspeciaisAction");
		
		GerarRelatorioImoveisProgramasEspeciaisActionForm form = 
			(GerarRelatorioImoveisProgramasEspeciaisActionForm) actionForm;

		//Primeira vez que carrega a pagina
		if ( httpServletRequest.getParameter("menu") != null && 
				httpServletRequest.getParameter("menu").equals("sim")) {
			
			form.setTipo("0");
			
		}
		
		this.pesquisarRegiaoDesenvolvimento(httpServletRequest, form);
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		
		//  Pega os codigos que o usuario digitou para a pesquisa direta de
		// localidade

		if (form.getIdLocalidade() != null && !form.getIdLocalidade().trim().equals("")) {
			pesquisarLocalidade( httpServletRequest, form);

		}
		return retorno;
	}

	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioImoveisProgramasEspeciaisActionForm form){
		
		//Localidade Inicial
		if(form.getIdLocalidade() != null && 
			!form.getIdLocalidade().equals("") && 
			form.getNomeLocalidade() != null && 
			!form.getNomeLocalidade().equals("")){
					
			httpServletRequest.setAttribute("localidadeEncontrada","true");
		}
		
	}
	
	/**
	 * [CRC_3895] Filtrar por Região de Desenvolvimento.
	 * 
	 * @author Hugo Leonardo
	 *
	 * @date 17/03/2010
	 */
	
	private void pesquisarRegiaoDesenvolvimento(HttpServletRequest httpServletRequest,
			GerarRelatorioImoveisProgramasEspeciaisActionForm form){
		
		FiltroRegiaoDesenvolvimento filtroRegiaoDesenvolvimento = new FiltroRegiaoDesenvolvimento();
		
		filtroRegiaoDesenvolvimento.setConsultaSemLimites(true);
		filtroRegiaoDesenvolvimento.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);

		filtroRegiaoDesenvolvimento.adicionarParametro(
				new ParametroSimples(FiltroRegiaoDesenvolvimento.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoRegiaoDesenvolvimento = 
			this.getFachada().pesquisar(filtroRegiaoDesenvolvimento,RegiaoDesenvolvimento.class.getName());

		if (colecaoRegiaoDesenvolvimento == null || colecaoRegiaoDesenvolvimento.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Região de Desenvolvimento");
		} else {
			httpServletRequest.setAttribute("colecaoRegiaoDesenvolvimento",colecaoRegiaoDesenvolvimento);
		}
	}

	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, 
						GerarRelatorioImoveisProgramasEspeciaisActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidade()));

		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhuma localidade for encontrada a mensagem é enviada para a
		// página
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			form.setIdLocalidade(""+localidade.getId());
			form.setNomeLocalidade(localidade.getDescricao());

		} else {
			form.setIdLocalidade("");
			form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
			httpServletRequest.setAttribute("localidadeInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
		}
	}
	
}
