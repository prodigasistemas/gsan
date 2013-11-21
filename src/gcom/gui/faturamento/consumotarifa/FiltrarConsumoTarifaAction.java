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
package gcom.gui.faturamento.consumotarifa;

import gcom.faturamento.consumotarifa.FiltroConsumoTarifaVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Filtra as tarifas de consumo
 * 
 * @author Tiago Moreno,Rafael Santos
 */
public class FiltrarConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterConsumoTarifa");

		FiltrarConsumoTarifaActionForm filtrarConsumoTarifaActionForm = (FiltrarConsumoTarifaActionForm) actionForm;

		//Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		String descTarifa = filtrarConsumoTarifaActionForm.getDescTarifa();
		String dataVigenciaInicial = filtrarConsumoTarifaActionForm.getDataVigencia();
		String dataVigenciaFinal = filtrarConsumoTarifaActionForm
				.getDataVigenciaFim();
		String atualizar = (String)httpServletRequest.getParameter("atualizarFiltro");
		
		//valida o intervalo de datas
		if ((dataVigenciaInicial.trim().length() == 10)
				&& (dataVigenciaFinal.trim().length() == 10)) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();

			calendarInicio.set(Calendar.DAY_OF_MONTH, new Integer(dataVigenciaInicial
					.substring(0, 2)).intValue());
			calendarInicio.set(Calendar.MONTH, new Integer(dataVigenciaInicial
					.substring(3, 5)).intValue());
			calendarInicio.set(Calendar.YEAR, new Integer(dataVigenciaInicial
					.substring(6, 10)).intValue());

			calendarFim.set(Calendar.DAY_OF_MONTH, new Integer(dataVigenciaFinal
					.substring(0, 2)).intValue());
			calendarFim.set(Calendar.MONTH, new Integer(dataVigenciaFinal
					.substring(3, 5)).intValue());
			calendarFim.set(Calendar.YEAR, new Integer(dataVigenciaFinal
					.substring(6, 10)).intValue());
			// joga exessão
			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_vigencia_final.menor.data_vigencia_inicial");
			}
		}

		FiltroConsumoTarifaVigencia filtroConsumoTarifaVigencia = new FiltroConsumoTarifaVigencia();
		/*filtroConsumoTarifaVigencia
				.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaVigencia.CONSUMO_TARIFA_DESCRICAO);*/
        filtroConsumoTarifaVigencia.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaVigencia.CONSUMO_TARIFA);
        
		boolean parametroPesquisaInformando = false;
		
		//Descricao da Tarifa 
		if (descTarifa != null
			&& !descTarifa.trim().equalsIgnoreCase("")) {
				
				filtroConsumoTarifaVigencia.adicionarParametro(new ComparacaoTexto(
				FiltroConsumoTarifaVigencia.CONSUMO_TARIFA_DESCRICAO,
				descTarifa));
				parametroPesquisaInformando = true;
		}
		
		//data de Vigencia Inicial
		if (dataVigenciaInicial != null
			&& !dataVigenciaInicial.trim().equalsIgnoreCase("")) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataInicial = null;
		
			try {
				dataInicial = formatoData.parse(dataVigenciaInicial);
			} catch (ParseException e) {
				dataInicial = null;
			}
		
			filtroConsumoTarifaVigencia.adicionarParametro(new MaiorQue(
				FiltroConsumoTarifaVigencia.DATA_VIGENCIA, dataInicial));
			parametroPesquisaInformando = true;
		}
		
		//data de Vigencia final
		if (dataVigenciaFinal != null
			&& !dataVigenciaFinal.trim().equalsIgnoreCase("")) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFinal = null;
		
			try {
				dataFinal = formatoData.parse(dataVigenciaFinal);
			} catch (ParseException e) {
				dataFinal = null;
			}
		
			filtroConsumoTarifaVigencia.adicionarParametro(new MenorQue(
				FiltroConsumoTarifaVigencia.DATA_VIGENCIA, dataFinal));
			parametroPesquisaInformando = true;
		}
		
		if(!parametroPesquisaInformando){
			// Exception que nao foi digitado nada!!!
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}


		filtroConsumoTarifaVigencia.setCampoOrderBy(
				FiltroConsumoTarifaVigencia.CONSUMO_TARIFA_DESCRICAO,
				FiltroConsumoTarifaVigencia.DATA_VIGENCIA);
		
		sessao.setAttribute("filtroConsumoTarifaVigencia", filtroConsumoTarifaVigencia);
		sessao.setAttribute("indicadorAtualizar", atualizar);	
		
		return retorno;
	}
}