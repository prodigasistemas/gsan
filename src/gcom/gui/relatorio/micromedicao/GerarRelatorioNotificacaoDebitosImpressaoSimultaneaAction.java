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
package gcom.gui.relatorio.micromedicao;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.micromedicao.RelatorioNotificacaoDebitosImpressaoSimultanea;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1022] Relatório de Notificação de Débitos para Impressão Simultânea 
 * 
 * @author Daniel Alves
 * @date 17/05/2010
 */
public class GerarRelatorioNotificacaoDebitosImpressaoSimultaneaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		// Form
		FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form = 
			(FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm) actionForm;

		RelatorioNotificacaoDebitosImpressaoSimultaneaHelper helper = new
			RelatorioNotificacaoDebitosImpressaoSimultaneaHelper();

		//Trecho que converte o form para um helper
		
		//linha que vai modificar o formato da data, de mm/aaaa para aaaamm.
		helper.setAnoMesReferencia(
				String.valueOf(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferencia()))
		);
		
		helper.setEmpresa(form.getEmpresa());
		helper.setGrupo(form.getGrupo());
		helper.setLocalidade(form.getLocalidade());
		helper.setCodigoSetorComercial(form.getCodigoSetorComercial());
		helper.setRota(form.getRota());

		//Modifica o cabeçalho do relatorio tem
		//localidade, setor e rota		
		helper.setCabecalhoTipo(0);

		//caso o Localidade for informada
	    //adicionar Localidade
		if(form.getLocalidade() != null &&
       		 !form.getLocalidade().equalsIgnoreCase("")){			

			helper.setCabecalhoTipo(1);       	 
			
			//caso o Setor for informada
   	    	//adicionar Localidade e Setor
       	    if(form.getCodigoSetorComercial() != null &&
           		 !form.getCodigoSetorComercial().equalsIgnoreCase("")){

       	    	helper.setCabecalhoTipo(2);        

       	    	//caso a Rota for informada
       	    	//adicionar Localidade, Setor e Rota
           	    if(form.getRota() != null &&
	            		 !form.getRota().equalsIgnoreCase("")){
           	    	helper.setCabecalhoTipo(3);
	            }
            }
        }


		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioNotificacaoDebitosImpressaoSimultanea relatorio =
			new RelatorioNotificacaoDebitosImpressaoSimultanea(this.getUsuarioLogado(httpServletRequest));

		relatorio.addParametro("relatorioNotificacaoDebitosImpressaoSimultaneaHelper", helper);
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