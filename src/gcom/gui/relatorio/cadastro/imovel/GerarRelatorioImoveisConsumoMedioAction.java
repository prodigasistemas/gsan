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
package gcom.gui.relatorio.cadastro.imovel;

import java.util.ArrayList;
import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedio;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00727] Gerar Relatório de Imóveis por Consumo Medio
 * 
 * @author Bruno Barros
 *
 * @date 17/12/2007
 */

public class GerarRelatorioImoveisConsumoMedioAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		Fachada fachada = Fachada.getInstancia();
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirRelatorioImoveisConsumoMedio");
		
		// Form
		GerarRelatorioImoveisConsumoMedioActionForm form = 
			(GerarRelatorioImoveisConsumoMedioActionForm) actionForm;
		
		FiltrarRelatorioImoveisConsumoMedioHelper filtro = 
			new FiltrarRelatorioImoveisConsumoMedioHelper();
		
		// Gerência Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setGerenciaRegional(new Integer(form.getGerenciaRegional()));
		}
		
		// Unidade de Negocio
		if (form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setUnidadeNegocio(new Integer(form.getUnidadeNegocio()));
		}
			
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") ) {
				
			filtro.setLocalidadeInicial(new Integer(form.getLocalidadeInicial()));
		}
		
		// Setor Comercial Inicial
		if (form.getSetorComercialInicial() != null && 
			!form.getSetorComercialInicial().equals("") ) {
				
			filtro.setSetorComercialInicial(new Integer(form.getSetorComercialInicial()));
		}

		// Rota Inicial
		if (form.getRotaInicial() != null && 
			!form.getRotaInicial().equals("") ) {
				
			filtro.setRotaInicial(new Integer(form.getRotaInicial()));
		}

		// Sequencial Rota Inicial
		if (form.getSequencialRotaInicial() != null && 
			!form.getSequencialRotaInicial().equals("") ) {
				
			filtro.setSequencialRotalInicial(new Integer(form.getSequencialRotaInicial()));
		}

		// Localidade Final
		if (form.getLocalidadeFinal() != null && 
			!form.getLocalidadeFinal().equals("") ) {
				
			filtro.setLocalidadeFinal(new Integer(form.getLocalidadeFinal()));
		}

		// Setor Comercial Final
		if (form.getSetorComercialFinal() != null && 
			!form.getSetorComercialFinal().equals("") ) {
				
			filtro.setSetorComercialFinal(new Integer(form.getSetorComercialFinal()));
		}
		
		// Rota Final
		if (form.getRotaFinal() != null && 
			!form.getRotaFinal().equals("") ) {
				
			filtro.setRotaFinal(new Integer(form.getRotaFinal()));
		}
		
		// Sequencial Rota Final
		if (form.getSequencialRotaFinal() != null && 
			!form.getSequencialRotaFinal().equals("") ) {
				
			filtro.setSequencialRotalFinal(new Integer(form.getSequencialRotaFinal()));
		}
		
		// Consumo Medio Agua Inicial
		if (form.getConsumoMedioAguaInicial() != null && 
			!form.getConsumoMedioAguaInicial().equals("") ) {
				
			filtro.setConsumoMedioAguaInicial( new Integer( form.getConsumoMedioAguaInicial() ) );
		}
		
		// Consumo Medio Agua Final
		if (form.getConsumoMedioAguaFinal() != null && 
			!form.getConsumoMedioAguaFinal().equals("") ) {
				
			filtro.setConsumoMedioAguaFinal( new Integer( form.getConsumoMedioAguaFinal() ) );
		}
		
		Collection<Integer> colecao = new ArrayList();
		// Perfil do Imóvel
		if (form.getPerfisImovel() != null && 
			form.getPerfisImovel().length > 0) {
			
			String[] array = form.getPerfisImovel();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoPerfisImovel(colecao);
			
		}
		
		// Consumo Medio Esgoto Inicial
		if (form.getConsumoMedioEsgotoInicial() != null && 
			!form.getConsumoMedioEsgotoInicial().equals("") ) {
				
			filtro.setConsumoMedioEsgotoInicial( new Integer( form.getConsumoMedioEsgotoInicial() ) );
		}
		
		// Consumo Medio Esgoto Final
		if (form.getConsumoMedioEsgotoFinal() != null && 
			!form.getConsumoMedioEsgotoFinal().equals("") ) {
				
			filtro.setConsumoMedioEsgotoFinal( new Integer( form.getConsumoMedioEsgotoFinal() ) );
		}
		
		// Indicador Medicao Com Hidrometro
		if (form.getIndicadorMedicaoComHidrometro() != null 
			&& !form.getIndicadorMedicaoComHidrometro().equals("") ) {
			
			filtro.setIndicadorMedicaoComHidrometro(new Integer(form.getIndicadorMedicaoComHidrometro()));
		}
		
		//Mes ano de Referência
		if (form.getAnoMesReferencia()!= null 
			&& !form.getAnoMesReferencia().equals("")) {
			
			filtro.setAnoMesReferencia(new Integer (Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferencia())));
			
		}

		//verifica se contem algum imovel para gerar o relatorio.
		Integer imovelConsumoMedioCount = fachada.pesquisarTotalRegistroRelatorioImoveisConsumoMedio(filtro);
		
		if ( imovelConsumoMedioCount == null || imovelConsumoMedioCount == 0 ) {
			//Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioImoveisConsumoMedio relatorio = 
			new RelatorioImoveisConsumoMedio(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarRelatorioImoveisConsumoMedioHelper", filtro);
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
}