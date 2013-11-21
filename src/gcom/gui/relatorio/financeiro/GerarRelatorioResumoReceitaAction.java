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
package gcom.gui.relatorio.financeiro;

import gcom.gui.financeiro.GerarRelatorioResumoReceitaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.financeiro.RelatorioResumoReceita;
import gcom.relatorio.financeiro.RelatorioResumoReceitaAnalitico;
import gcom.relatorio.financeiro.ResumoReceitaHelper;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Flavio Leonardo
 *
 * @date 16/04/2010
 */

public class GerarRelatorioResumoReceitaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		// Form
		GerarRelatorioResumoReceitaActionForm form = 
			(GerarRelatorioResumoReceitaActionForm) actionForm;
		 
//		FiltroResumoReceita filtro = 
//			new FiltroResumoReceita();
		
		ResumoReceitaHelper resumoReceitaHelper = new ResumoReceitaHelper();
		
		//Gerência Regional
		if (form.getMesAnoReferencial() != null && 
			!form.getMesAnoReferencial().equals("") ) {
			
//			filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.ANO_MES_REFERENCIA,
//					Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencial())));
			
			resumoReceitaHelper.setAnoMes(Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencial())+"");
			
			
		}
		
		// Gerência Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			resumoReceitaHelper.setGerenciaRegionalId(new Integer(form.getGerenciaRegional()));
//			filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.GERENCIA_REGIONAL,form.getGerenciaRegional()));
		}
			
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") ) {
			if(form.getLocalidadeFinal() != null && !form.getLocalidadeFinal().equals("")){
				resumoReceitaHelper.setLocalidadeInicial(form.getLocalidadeInicial());
				resumoReceitaHelper.setLocalidadeFinal(form.getLocalidadeFinal());
//				filtro.adicionarParametro(new MaiorQue(FiltroResumoReceita.LOCALIDADE, form.getLocalidadeInicial()));
//				filtro.adicionarParametro(new MenorQue(FiltroResumoReceita.LOCALIDADE, form.getLocalidadeFinal()));
			}else{
//				filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.LOCALIDADE, form.getLocalidadeInicial()));
				resumoReceitaHelper.setLocalidadeInicial(form.getLocalidadeInicial());
			}
		}
		
		//Gerência Regional
		if (form.getCategoria() != null && 
			!form.getCategoria().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			resumoReceitaHelper.setCategoriaId(new Integer(form.getCategoria()));
			
//			filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.CATEGORIA_ID,form.getCategoria()));
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if(form.getTipoRelatorio() != null && form.getTipoRelatorio().equals("1")){
			RelatorioResumoReceitaAnalitico relatorio = 
				new RelatorioResumoReceitaAnalitico(this.getUsuarioLogado(httpServletRequest));
			
			relatorio.addParametro("resumoReceitaHelper", resumoReceitaHelper);
			relatorio.addParametro("mesAno", form.getMesAnoReferencial());
			relatorio.addParametro("gerenciaRegional", form.getGerenciaRegional());
			relatorio.addParametro("localidadeInicial", form.getLocalidadeInicial());
			relatorio.addParametro("localidadeFinal", form.getLocalidadeFinal());
			relatorio.addParametro("categoria", form.getCategoria());
			
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
	
			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			
			retorno = 
				processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
					httpServletResponse, actionMapping);
		}else{
			RelatorioResumoReceita relatorio = 
				new RelatorioResumoReceita(this.getUsuarioLogado(httpServletRequest));
			
			relatorio.addParametro("resumoReceitaHelper", resumoReceitaHelper);
			relatorio.addParametro("mesAno", form.getMesAnoReferencial());
			relatorio.addParametro("gerenciaRegional", form.getGerenciaRegional());
			relatorio.addParametro("localidadeInicial", form.getLocalidadeInicial());
			relatorio.addParametro("localidadeFinal", form.getLocalidadeFinal());
			relatorio.addParametro("categoria", form.getCategoria());
			
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
	
			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			
			retorno = 
				processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
					httpServletResponse, actionMapping);
		}
		
		

		return retorno;
	}
	
}