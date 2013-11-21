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
package gcom.gui.cobranca;



import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * [UC0930]FILTRAR GRUPO DE COBRANCA
 * 
 * @author Arthur Carvalho
 * @date 14/08/09
 */

public class FiltrarCobrancaGrupoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCobrancaGrupo");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarCobrancaGrupoActionForm form = (FiltrarCobrancaGrupoActionForm) actionForm;

		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

		boolean peloMenosUmParametroInformado = false;

		String id = form.getId();
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String anoMesReferencia = form.getAnoMesReferencia();
		String indicadorUso = form.getIndicadorUso();
		String tipoPesquisa = form.getTipoPesquisa();
		
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		//ID
		if (id != null && !id.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupo.ID, id));
		}
		
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroCobrancaGrupo
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroCobrancaGrupo.DESCRICAO, descricao));
			} else {

				filtroCobrancaGrupo.adicionarParametro(new ComparacaoTexto(
						FiltroCobrancaGrupo.DESCRICAO, descricao));
			}
		}
		
		//Descricao Abreviada
		if ( descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaGrupo.adicionarParametro( new ComparacaoTextoCompleto(
					FiltroCobrancaGrupo.DESCRICAO_ABREVIADA, descricaoAbreviada));
		}
			
		//Ano Mes Referencia
        if(anoMesReferencia != null && !anoMesReferencia.trim().equals("")){
        	String mes = anoMesReferencia.substring(0, 2);
        	String ano = anoMesReferencia.substring(3, 7);
        	String anoMes = ano+mes;
        	peloMenosUmParametroInformado = true;
        	filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
        			FiltroCobrancaGrupo.ANO_MES_REFERENCIA, anoMes));
        	
         }
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupo.INDICADOR_USO, indicadorUso));
		}
		
		Collection<CobrancaGrupo> colecaoCobrancaGrupo = fachada
				.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoCobrancaGrupo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Grupo de Cobrança");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoCobrancaGrupo == null
				|| colecaoCobrancaGrupo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Grupo de Cobrança");
		} else {
			httpServletRequest.setAttribute("colecaoCobrancaGrupo",
					colecaoCobrancaGrupo);
			CobrancaGrupo CobrancaGrupo = new CobrancaGrupo();
			CobrancaGrupo = (CobrancaGrupo) Util
					.retonarObjetoDeColecao(colecaoCobrancaGrupo);
			String idRegistroAtualizar = CobrancaGrupo.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroCobrancaGrupo", filtroCobrancaGrupo);

		httpServletRequest.setAttribute("filtroCobrancaGrupo",
				filtroCobrancaGrupo);

		
		return retorno;
	}
}
