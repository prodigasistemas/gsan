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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroProducaoAgua;
import gcom.operacional.ProducaoAgua;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0813]Filtrar Producao de Agua
 * 
 * @author Vinícius Medeiros
 * @date 10/06/2008
 */

public class FiltrarProducaoAguaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterProducaoAgua");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarProducaoAguaActionForm filtrarProducaoAguaActionForm = (FiltrarProducaoAguaActionForm) actionForm;

		FiltroProducaoAgua filtroProducaoAgua = new FiltroProducaoAgua();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarProducaoAguaActionForm.getId();
		String anoMesReferencia = filtrarProducaoAguaActionForm.getAnoMesReferencia();
		String localidadeID = filtrarProducaoAguaActionForm.getLocalidadeID();
		String volumeProduzido = filtrarProducaoAguaActionForm
				.getVolumeProduzido();
		
		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroProducaoAgua.adicionarParametro(new ParametroSimples(
						FiltroProducaoAgua.ID, id));
			}
		}
		
		// Ano Mes Referencia
	    if(anoMesReferencia != null && !anoMesReferencia.trim().equals("")){
	       	Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(anoMesReferencia);
	       	peloMenosUmParametroInformado = true;	
	       	filtroProducaoAgua.adicionarParametro(new ParametroSimples(
	     			FiltroProducaoAgua.MES_ANO_REFERENCIA, anoMes));
	       	
	    }
		// Localidade
		if (localidadeID != null && !localidadeID.equalsIgnoreCase("")) {
			
			pesquisarLocalidade(filtrarProducaoAguaActionForm,fachada,localidadeID);
			
			peloMenosUmParametroInformado = true;
			filtroProducaoAgua.adicionarParametro(new ParametroSimples(
					FiltroProducaoAgua.ID_LOCALIDADE, new Integer(localidadeID)));
		}
	       
		// Volume Produzido
		if (volumeProduzido != null && !volumeProduzido.equals("") && !volumeProduzido.trim().equals(BigDecimal.ZERO)) {
			BigDecimal volume = Util.formatarMoedaRealparaBigDecimal(volumeProduzido);
			peloMenosUmParametroInformado = true;
			filtroProducaoAgua.adicionarParametro(new ParametroSimples(
								FiltroProducaoAgua.VOLUME_PRODUZIDO, volume));
		} 
		
		
		Collection colecaoProducaoAgua = fachada
				.pesquisar(filtroProducaoAgua, ProducaoAgua.class
						.getName());

		// Verificar a existencia de uma atividade
		if (colecaoProducaoAgua.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Produção de Água");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoProducaoAgua == null
				|| colecaoProducaoAgua.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Produção de Água");
		} else {
			httpServletRequest.setAttribute("colecaoProducaoAgua",
					colecaoProducaoAgua);
			ProducaoAgua producaoAgua = new ProducaoAgua();
			producaoAgua = (ProducaoAgua) Util
					.retonarObjetoDeColecao(colecaoProducaoAgua);
			String idRegistroAtualizacao = producaoAgua.getId().toString();
			
			httpServletRequest.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroProducaoAgua", filtroProducaoAgua);

		httpServletRequest.setAttribute("filtroProducaoAgua",
				filtroProducaoAgua);

		return retorno;

	}
	private void pesquisarLocalidade(
    		FiltrarProducaoAguaActionForm filtrarProducaoAguaActionForm,
            Fachada fachada, String localidadeID) {
	 
		Collection colecaoPesquisa;
	 
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(new ParametroSimples(
		 FiltroLocalidade.ID, localidadeID));

		 //Retorna localidade
		 colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
		         Localidade.class.getName());
		
		 if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	
			 //Localidade nao encontrada
		     //Limpa o campo localidadeID do formulário
			 filtrarProducaoAguaActionForm.setLocalidadeID("");
			 filtrarProducaoAguaActionForm.setLocalidadeDescricao("Localidade inexistente.");
		 	
		 	throw new ActionServletException("atencao.pesquisa_inexistente",
						null,"Localidade");	
		
		 }else {
		     Localidade objetoLocalidade = (Localidade) Util
		     .retonarObjetoDeColecao(colecaoPesquisa);
		     filtrarProducaoAguaActionForm.setLocalidadeID(String
			            .valueOf(objetoLocalidade.getId()));
		     filtrarProducaoAguaActionForm
			            .setLocalidadeDescricao(objetoLocalidade.getDescricao());
			
		}

	}
}
