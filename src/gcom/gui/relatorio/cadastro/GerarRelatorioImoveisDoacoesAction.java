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
package gcom.gui.relatorio.cadastro;

import java.util.Date;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioImoveisDoacoesEntidade;
import gcom.relatorio.cadastro.RelatorioImoveisDoacoesImovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1076] - Gerar Relatorio Atualizacao Cadastral Via Internet.
 * @author Daniel Alves
 * @date 24/09/2010 
 */

public class GerarRelatorioImoveisDoacoesAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioImoveisDoacoesActionForm form = 
			(GerarRelatorioImoveisDoacoesActionForm) actionForm;
		
		if(form.getOpcaoRelatorio() != null &&
				!form.getOpcaoRelatorio().equals("")){
			
		}else{			
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}	
		
		
		if(Util.verificarNaoVazio(form.getPeriodoAdesaoInicial()) 
				&& Util.verificarNaoVazio(form.getPeriodoAdesaoFinal())){
			
			Date periodoInicial = Util.converteStringParaDate(form.getPeriodoAdesaoInicial());
			Date periodoFinal = Util.converteStringParaDate(form.getPeriodoAdesaoInicial());
			
			if(periodoFinal.compareTo(periodoInicial)<0){
				throw new ActionServletException("atencao.data_final_periodo_adesao.anterior.data_inicial_periodo_adesao");
			}
		}
		
		if(Util.verificarNaoVazio(form.getPeriodoCancelamentoInicial()) 
				&& Util.verificarNaoVazio(form.getPeriodoCancelamentoFinal())){
			
			Date periodoInicial = Util.converteStringParaDate(form.getPeriodoCancelamentoInicial());
			Date periodoFinal = Util.converteStringParaDate(form.getPeriodoCancelamentoFinal());
			
			if(periodoFinal.compareTo(periodoInicial)<0){
				throw new ActionServletException("atencao.data_final_periodo_cancelamento.anterior.data_inicial_periodo_cancelamento");
			}
		}
		
		TarefaRelatorio relatorio = null;
		
		//escolhe o formato do relatorio (Por imovel, por entidade beneficente)
		if(form.getOpcaoRelatorio() != null &&
				!form.getOpcaoRelatorio().equals("")){
			
			if(form.getOpcaoRelatorio().equals("1")){
				relatorio = new RelatorioImoveisDoacoesImovel((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			} else if (form.getOpcaoRelatorio().equals("2")){
				relatorio = new RelatorioImoveisDoacoesEntidade((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			}
			
		}else{
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		if(form.getOpcaoRelatorio().equals("1")){
			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			relatorio.addParametro("filtroImovel", form.getImovel());
			
		}else{
			if(form.getOpcaoRelatorio().equals("2")){
				relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
				relatorio.addParametro("filtroPeriodoAdesaoInicial", form.getPeriodoAdesaoInicial());
				relatorio.addParametro("filtroPeriodoAdesaoFinal", form.getPeriodoAdesaoFinal());
				relatorio.addParametro("filtroPeriodoCancelamentoInicial", form.getPeriodoCancelamentoInicial());
				relatorio.addParametro("filtroPeriodoCancelamentoFinal", form.getPeriodoCancelamentoFinal());
				relatorio.addParametro("filtroRefInicioDoacaoInicial", form.getReferenciaInicioDoacaoInicial());
				relatorio.addParametro("filtroRefInicioDoacaoFinal", form.getReferenciaInicioDoacaoFinal());
				relatorio.addParametro("filtroRefFimDoacaoInicial", form.getReferenciaFimDoacaoInicial());
				relatorio.addParametro("filtroRefFimDoacaoFinal", form.getReferenciaFimDoacaoFinal());
				relatorio.addParametro("filtroUsuarioAdesao", form.getUsuarioAdesao());
				relatorio.addParametro("filtroUsuarioCancelamento", form.getUsuarioCancelamento());
				relatorio.addParametro("filtroEntidadeBeneficente", form.getEntidadeBeneficente());
			}
		}
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			return retorno;
		}
	
}