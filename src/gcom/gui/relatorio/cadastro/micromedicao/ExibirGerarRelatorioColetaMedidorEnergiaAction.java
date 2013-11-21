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


package gcom.gui.relatorio.cadastro.micromedicao;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
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
 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
 * 
 * @author Hugo Leonardo
 *
 * @date 08/03/2010
 */

public class ExibirGerarRelatorioColetaMedidorEnergiaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioColetaMedidorEnergia");
		
		GerarRelatorioColetaMedidorEnergiaActionForm form = 
			(GerarRelatorioColetaMedidorEnergiaActionForm) actionForm;
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("2"))  ) {

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form,objetoConsulta);
			
		}
		
		//	Pesquisar Setor Comercial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("3")|| objetoConsulta.trim().equals("4"))  ) {
			
			// Faz a consulta do Setor Comercial
			this.pesquisarSetorComercial(form,objetoConsulta);
		}
		
		//this.pesquisarUnidadeNegocio(httpServletRequest,form);
		this.pesquisarGrupoFaturamento(httpServletRequest);
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		
		return retorno;
	}
	
	/**
	 * Pesquisa Setor Comercial
	 *
	 * @author Hugo Leonardo
	 * @date 09/04/2010
	 */
	private void pesquisarSetorComercial(GerarRelatorioColetaMedidorEnergiaActionForm form, 
			String objetoConsulta) {
		
		Object idSetorComercial = null;
		Object local = null;
		
		local = form.getLocalidadeInicial();
		
		if(objetoConsulta.trim().equals("3")){
			idSetorComercial = form.getCodigoSetorComercialInicial();
			
		}else if(objetoConsulta.trim().equals("4")){
			idSetorComercial = form.getCodigoSetorComercialFinal();
			
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
			
			if(objetoConsulta.trim().equals("3")){
				form.setCodigoSetorComercialInicial(""+setorComercial.getCodigo());
				form.setSetorComercialInicialDescricao(setorComercial.getDescricao());
				
				form.setCodigoSetorComercialFinal(""+setorComercial.getCodigo());
				form.setSetorComercialFinalDescricao(setorComercial.getDescricao());
			}
			
			if(objetoConsulta.trim().equals("4")){
				form.setCodigoSetorComercialFinal(""+setorComercial.getCodigo());
				form.setSetorComercialFinalDescricao(setorComercial.getDescricao());
			}

		} else {
			if(objetoConsulta.trim().equals("3")){
				form.setCodigoSetorComercialInicial(null);
				form.setSetorComercialInicialDescricao("Setor Comercial Inexistente");
				
				form.setCodigoSetorComercialFinal(null);
				form.setSetorComercialFinalDescricao(null);
			}else if(objetoConsulta.trim().equals("4")){
				form.setCodigoSetorComercialFinal(null);
				form.setSetorComercialFinalDescricao("Setor Comercial Inexistente");
			}
		}
	}

	/**
	 * Pesquisa Localidade
	 *
	 * @author Hugo Leonardo
	 * @date 08/03/2010
	 */
	private void pesquisarLocalidade(GerarRelatorioColetaMedidorEnergiaActionForm form,
		String objetoConsulta) {

		Object local = null;
		
		if(objetoConsulta.trim().equals("1")){
			local = form.getLocalidadeInicial();
			
		}else if(objetoConsulta.trim().equals("2")){
			local = form.getLocalidadeFinal();
			
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
				form.setLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			
				form.setLocalidadeFinal(localidade.getId().toString());
				form.setNomeLocalidadeFinal(localidade.getDescricao());
			}
			
			if(objetoConsulta.trim().equals("2")){
				form.setLocalidadeFinal(localidade.getId().toString());
				form.setNomeLocalidadeFinal(localidade.getDescricao());
			}

		} else {
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidadeInicial(null);
				form.setNomeLocalidadeInicial("Localidade Inicial inexistente");
				
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal(null);
			}else if(objetoConsulta.trim().equals("2")){
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal("Localidade Final inexistente");
			}
		}
	}
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 02/01/2008
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioColetaMedidorEnergiaActionForm form){
		
		//Localidade Inicial
		if(form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") && 
			form.getNomeLocalidadeInicial() != null && 
			!form.getNomeLocalidadeInicial().equals("")){
					
			httpServletRequest.setAttribute("localidadeInicialEncontrada","true");
			httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
		}else{

			if(form.getLocalidadeFinal() != null && 
				!form.getLocalidadeFinal().equals("") && 
				form.getNomeLocalidadeFinal() != null && 
				!form.getNomeLocalidadeFinal().equals("")){
								
				httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
			}
		}
		
		//Setor Comercial Inicial
		if(form.getCodigoSetorComercialInicial() != null && 
			!form.getCodigoSetorComercialInicial().equals("") && 
			form.getSetorComercialInicialDescricao() != null && 
			!form.getSetorComercialInicialDescricao().equals("")){
					
			httpServletRequest.setAttribute("setorComercialInicialEncontrada","true");
			httpServletRequest.setAttribute("setorComercialFinalEncontrada","true");
		}else{

			if(form.getCodigoSetorComercialFinal() != null && 
				!form.getCodigoSetorComercialFinal().equals("") && 
				form.getSetorComercialFinalDescricao() != null && 
				!form.getSetorComercialFinalDescricao().equals("")){
								
				httpServletRequest.setAttribute("setorComercialFinalEncontrada","true");
			}
		}
		
	}
	
	private void pesquisarGrupoFaturamento(HttpServletRequest httpServletRequest) {

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

		filtroFaturamentoGrupo.setConsultaSemLimites(true);

		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ));
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);

		Collection colecaoFaturamentoGrupo = this.getFachada().pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if (colecaoFaturamentoGrupo == null
				|| colecaoFaturamentoGrupo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo Faturamento");
		} else {
			httpServletRequest.setAttribute("colecaoFaturamentoGrupo",
					colecaoFaturamentoGrupo);
		}
	}
}