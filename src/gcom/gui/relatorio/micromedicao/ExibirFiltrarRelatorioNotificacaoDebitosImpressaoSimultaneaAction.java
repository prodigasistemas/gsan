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


package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
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
 * [UC1021] Filtrar Notificação de Débitos para Impressão Simultânea.
 * 
 * @author Daniel Alves
 *
 * @date 14/05/2010
 */

public class ExibirFiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirFiltrarRelatorioNotificacaoDebitosImpressaoSimultanea");
		
		FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form = 
			(FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm) actionForm;
	
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		//Empresa
        FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		Collection colecaoEmpresa = (Collection) this.getFachada().pesquisar(
				filtroEmpresa, Empresa.class.getName());
		
		if (colecaoEmpresa != null	&& !colecaoEmpresa.isEmpty()) {
			httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}else {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tabela Empresa");
		}

		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("1")){

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form,objetoConsulta);
			
		}
		
		//	Pesquisar Setor Comercial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("2")) {
			
			// Faz a consulta do Setor Comercial
			this.pesquisarSetorComercial(form,objetoConsulta);
		}
		
		//this.pesquisarUnidadeNegocio(httpServletRequest,form);
		this.pesquisarGrupoCobranca(httpServletRequest);
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		
		return retorno;
	}
	
	/**
	 * Pesquisa Setor Comercial
	 *
     * @author Daniel Alves
	 * @date 17/05/2010
	 */
	private void pesquisarSetorComercial(FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form, 
			String objetoConsulta) {
		
		Object idSetorComercial = null;
		Object local = null;
		
		local = form.getLocalidade();
		
		if(objetoConsulta.trim().equals("2")){
			idSetorComercial = form.getCodigoSetorComercial();
			
		}
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,idSetorComercial));
		
		filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,local));
		
		// Recupera Setor Comercial
		Collection colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

			SetorComercial setorComercial = 
				(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			if(objetoConsulta.trim().equals("2")){
				form.setCodigoSetorComercial(""+setorComercial.getCodigo());
				form.setSetorComercialDescricao(setorComercial.getDescricao());
								
			}
						

		} else {
			if(objetoConsulta.trim().equals("2")){
				form.setCodigoSetorComercial(null);
				form.setSetorComercialDescricao("Setor Comercial Inexistente");
				
			}
		}
	}

	/**
	 * Pesquisa Localidade
	 *
	 * @author Daniel Alves
	 * @date 17/05/2010
	 */
	private void pesquisarLocalidade(FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form,
		String objetoConsulta) {

		Object local = null;
		
		if(objetoConsulta.trim().equals("1")){
			local = form.getLocalidade();
			
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID,local));
		
		// Recupera Localidade
		Collection colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = 
				(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidade(localidade.getId().toString());
				form.setNomeLocalidade(localidade.getDescricao());		
				
			}
			

		} 
	}
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Daniel Alves 
	 * @date 14/05/2010
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form){
		
		//Localidade
		if(form.getLocalidade() != null && 
			!form.getLocalidade().equals("") && 
			form.getNomeLocalidade() != null && 
			!form.getNomeLocalidade().equals("")){
					
			httpServletRequest.setAttribute("localidadeEncontrada","true");			
		}
		
		//Setor Comercial
		if(form.getCodigoSetorComercial() != null && 
			!form.getCodigoSetorComercial().equals("") && 
			form.getSetorComercialDescricao() != null && 
			!form.getSetorComercialDescricao().equals("")){
					
			httpServletRequest.setAttribute("setorComercialEncontrada","true");			
		}
		
	}
	
	private void pesquisarGrupoCobranca(HttpServletRequest httpServletRequest) {

		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

		filtroCobrancaGrupo.setConsultaSemLimites(true);

		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
				FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ));
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);

		Collection colecaoCobrancaGrupo = this.getFachada().pesquisar(
				filtroCobrancaGrupo, CobrancaGrupo.class.getName());

		if (colecaoCobrancaGrupo == null
				|| colecaoCobrancaGrupo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo Cobranca");
		} else {
			httpServletRequest.setAttribute("colecaoGrupo",
					colecaoCobrancaGrupo);
		}
	}
	
	
	
}