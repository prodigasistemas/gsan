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
package gcom.gui.gerencial.faturamento;

import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gui.GcomAction;
import gcom.micromedicao.ResumoAnormalidadeLeitura;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Fernanda Paiva
 * @date 30/05/2006
 * 
 */
public class ExibirResultadoConsultaResumoAnaliseFaturamentoAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("resultadoResumoAnaliseFaturamento");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		List colecaoResumoAnormalidade = (List) sessao
				.getAttribute("colecaoResumoAnaliseFaturamento");
		
		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");
		
		List colecaoResumoAnormalidadeAgua = new ArrayList();
		List colecaoResumoAnormalidadeEsgoto = new ArrayList();

		Iterator iterator = colecaoResumoAnormalidade.iterator();
		
		ResumoAnormalidadeLeitura resumoAnormalidadeLeitura = null;
		int somatorioAnormalidadeAgua = 0;
		int somatorioAnormalidadeEsgoto = 0;
		/**
		 * Divide o resultado da coleção em dois subresultados
		 * um por ligação agua e outro por ligação esgoto
		 */
		while(iterator.hasNext()){
			resumoAnormalidadeLeitura = (ResumoAnormalidadeLeitura)iterator.next();
			if(resumoAnormalidadeLeitura.getMedicaoTipo().getId().equals(MedicaoTipo.LIGACAO_AGUA)){
				/**
				 * guarda o somatorio total das quantidades de medicao de ligacao agua 
				 */
				somatorioAnormalidadeAgua += resumoAnormalidadeLeitura.getQuantidadeMedicao().intValue();
				colecaoResumoAnormalidadeAgua.add(resumoAnormalidadeLeitura);
			}else{
				/**
				 * guarda o somatorio total das quantidades de medicao de ligacao esgoto 
				 */
				somatorioAnormalidadeEsgoto += resumoAnormalidadeLeitura.getQuantidadeMedicao().intValue();
				colecaoResumoAnormalidadeEsgoto.add(resumoAnormalidadeLeitura);
			}
		}
		
		/**
		 * Cria coleção de agrupamntos(uma coleção de object[3], agrupamento, id, descricao)
		 */
		Collection colecaoAgrupamento = fachada.criarColecaoAgrupamentoResumos(informarDadosGeracaoRelatorioConsultaHelper);
		httpServletRequest.setAttribute("somatorioAnormalidadeAgua",somatorioAnormalidadeAgua+"");
		sessao.setAttribute("colecaoAgrupamento", colecaoAgrupamento);
		sessao.setAttribute("mesAnoReferencia", Util.formatarAnoMesParaMesAno(informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia()));
		httpServletRequest.setAttribute("colecaoResumoAnormalidadeAgua", colecaoResumoAnormalidadeAgua);
		httpServletRequest.setAttribute("colecaoResumoAnormalidadeEsgoto", colecaoResumoAnormalidadeEsgoto);
		
		return retorno;
	}

}
