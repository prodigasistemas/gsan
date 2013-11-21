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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroZonaPressao;
import gcom.operacional.ZonaPressao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * [UC0797]Filtrar Zona Pressao
 * 
 * @author Vinícius Medeiros
 * @date 20/05/2008
 */

public class FiltrarZonaPressaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterZonaPressao");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarZonaPressaoActionForm filtrarZonaPressaoActionForm = (FiltrarZonaPressaoActionForm) actionForm;

		FiltroZonaPressao filtroZonaPressao = new FiltroZonaPressao();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarZonaPressaoActionForm.getId();
		String descricao = filtrarZonaPressaoActionForm.getDescricao();
		String descricaoAbreviada = filtrarZonaPressaoActionForm.getDescricaoAbreviada();
		String distritoOperacionalID = filtrarZonaPressaoActionForm.getDistritoOperacionalID();
		String indicadorUso = filtrarZonaPressaoActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarZonaPressaoActionForm.getTipoPesquisa();

		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroZonaPressao.adicionarParametro(new ParametroSimples(
						FiltroZonaPressao.ID, id));
			}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroZonaPressao
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroZonaPressao.DESCRICAO, descricao));
			} else {

				filtroZonaPressao.adicionarParametro(new ComparacaoTexto(
						FiltroZonaPressao.DESCRICAO, descricao));
			}
		}
		
		// Descricao Abreviada
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroZonaPressao
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroZonaPressao.DESCRICAO_ABREVIADA,
							descricaoAbreviada));
		} 

		// Distrito Operacional
		if (distritoOperacionalID != null && !distritoOperacionalID.equalsIgnoreCase("")) {
			
			pesquisarDistritoOperacional(filtrarZonaPressaoActionForm,fachada,distritoOperacionalID);
			
			peloMenosUmParametroInformado = true;
			filtroZonaPressao.adicionarParametro(new ParametroSimples(
					FiltroZonaPressao.DISTRITO_OPERACIONAL_ID, new Integer(distritoOperacionalID)));
		}

		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroZonaPressao.adicionarParametro(new ParametroSimples(
					FiltroZonaPressao.INDICADOR_USO, indicadorUso));
		}
		
		Collection colecaoZonaPressao = fachada
				.pesquisar(filtroZonaPressao, ZonaPressao.class
						.getName());

		// Verificar a existencia de uma zona de pressao
		if (colecaoZonaPressao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Zona de Pressão");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoZonaPressao == null
				|| colecaoZonaPressao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Zona de Pressão");
		} else {
			httpServletRequest.setAttribute("colecaoZonaPressao",
					colecaoZonaPressao);
			ZonaPressao zonaPressao = new ZonaPressao();
			zonaPressao = (ZonaPressao) Util
					.retonarObjetoDeColecao(colecaoZonaPressao);
			String idRegistroAtualizacao = zonaPressao.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroZonaPressao", filtroZonaPressao);

		httpServletRequest.setAttribute("filtroZonaPressao",
				filtroZonaPressao);

		return retorno;

	}
	
	private void pesquisarDistritoOperacional(
	    		FiltrarZonaPressaoActionForm filtrarZonaPressaoActionForm,
	            Fachada fachada, String distritoOperacionalID) {
		 
		Collection colecaoPesquisa;
		 
     FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

     filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
    		 FiltroDistritoOperacional.ID, distritoOperacionalID));

     //Retorna localidade
     colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
             DistritoOperacional.class.getName());

     if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
         //DistritoOperacional nao encontrada
         //Limpa o campo distritoOperacionalID do formulário
    	 filtrarZonaPressaoActionForm.setDistritoOperacionalID("");
    	 filtrarZonaPressaoActionForm.setDistritoOperacionalDescricao("Distrito Operacional inexistente.");
     	
     	throw new ActionServletException("atencao.pesquisa_inexistente",
					null,"Distrito Operacional");	

     }else {
         DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
         .retonarObjetoDeColecao(colecaoPesquisa);
         filtrarZonaPressaoActionForm.setDistritoOperacionalID(String
		            .valueOf(objetoDistritoOperacional.getId()));
         filtrarZonaPressaoActionForm
		            .setDistritoOperacionalDescricao(objetoDistritoOperacional.getDescricao());
		
		}
	
	}

}
