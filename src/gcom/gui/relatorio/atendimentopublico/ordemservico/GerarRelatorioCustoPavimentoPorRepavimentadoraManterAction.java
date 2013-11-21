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
package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.atendimentopublico.ordemservico.FiltrarCustoPavimentoPorRepavimentadoraActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioManterCustoPavimentoPorRepavimentadora;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 *
 * @date 12/01/2011
 */

public class GerarRelatorioCustoPavimentoPorRepavimentadoraManterAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio", true);
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Form
		FiltrarCustoPavimentoPorRepavimentadoraActionForm form = (FiltrarCustoPavimentoPorRepavimentadoraActionForm) actionForm;
		
		Collection<UnidadeRepavimentadoraCustoPavimentoRua> colecaoCustoPavimentoRua = (Collection<UnidadeRepavimentadoraCustoPavimentoRua>) sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
		
		Collection<UnidadeRepavimentadoraCustoPavimentoCalcada> colecaoCustoPavimentoCalcada = (Collection<UnidadeRepavimentadoraCustoPavimentoCalcada>) sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
		
		// cria uma instância da classe do relatório
		RelatorioManterCustoPavimentoPorRepavimentadora relatorioManterCustoPavimentoPorRepavimentadora = new RelatorioManterCustoPavimentoPorRepavimentadora(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("colecaoCustoPavimentoRua", colecaoCustoPavimentoRua);
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("colecaoCustoPavimentoCalcada", colecaoCustoPavimentoCalcada);
		
		sessao.removeAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
		sessao.removeAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
		
		String unidadeRepavimentadora = " -- ";
		
		if(form.getIdUnidadeRepavimentadora() != null && !form.getIdUnidadeRepavimentadora().equals("-1")){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, form.getIdUnidadeRepavimentadora()));
			
			Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = Fachada.getInstancia().pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeRepavimentadora);
			unidadeRepavimentadora = unidade.getDescricao();
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("unidadeRepavimentadora", unidadeRepavimentadora);
		
		String pavimentoRua = " -- ";
		if(form.getIdTipoPavimentoRua() != null && !form.getIdTipoPavimentoRua().equals("-1")){
			
			FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
			filtroPavimentoRua.adicionarParametro(new ParametroSimples(
					FiltroPavimentoRua.ID, form.getIdTipoPavimentoRua()));
			
			Collection<PavimentoRua> colecaoPavimentoRua = Fachada.getInstancia().pesquisar(
					filtroPavimentoRua, PavimentoRua.class.getName());
			
			PavimentoRua pavimento = (PavimentoRua) Util.retonarObjetoDeColecao(colecaoPavimentoRua);
			pavimentoRua = pavimento.getDescricao();
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("pavimentoRua", pavimentoRua);
		
		String vigenciaRua = " -- ";
		if(form.getDataVigenciaInicialPavimentoRua() != null && !form.getDataVigenciaInicialPavimentoRua().equals("") &&
				form.getDataVigenciaFinalPavimentoRua() != null && !form.getDataVigenciaFinalPavimentoRua().equals("")){
			
			vigenciaRua = form.getDataVigenciaInicialPavimentoRua() + " a " + form.getDataVigenciaFinalPavimentoRua();
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("vigenciaRua", vigenciaRua);
		
		String situacaoVigenciaRua = " -- ";
		if(form.getSituacaoVigenciaRua() != null){
			
			if(form.getSituacaoVigenciaRua().equals("1")){
				
				situacaoVigenciaRua = "Vigente";
			}else if(form.getSituacaoVigenciaRua().equals("2")){
				
				situacaoVigenciaRua = "Não Vigente";
			}else{
				
				situacaoVigenciaRua = "Todos";
			}
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("situacaoVigenciaRua", situacaoVigenciaRua);
		
		String pavimentoCalcada = " -- ";
		if(form.getIdTipoPavimentoCalcada() != null && !form.getIdTipoPavimentoCalcada().equals("-1")){
			
			FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
			filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(
					FiltroPavimentoCalcada.ID, form.getIdTipoPavimentoCalcada()));
			
			Collection<PavimentoCalcada> colecaoPavimentoCalcada = Fachada.getInstancia().pesquisar(
					filtroPavimentoCalcada, PavimentoCalcada.class.getName());
			
			PavimentoCalcada pavimento = (PavimentoCalcada) Util.retonarObjetoDeColecao(colecaoPavimentoCalcada);
			pavimentoCalcada = pavimento.getDescricao();
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("pavimentoCalcada", pavimentoCalcada);
		
		String vigenciaCalcada = " -- ";
		if(form.getDataVigenciaInicialPavimentoCalcada() != null && !form.getDataVigenciaInicialPavimentoCalcada().equals("") &&
				form.getDataVigenciaFinalPavimentoCalcada() != null && !form.getDataVigenciaFinalPavimentoCalcada().equals("")){
			
			vigenciaCalcada = form.getDataVigenciaInicialPavimentoCalcada() + " a " + form.getDataVigenciaFinalPavimentoCalcada();
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("vigenciaCalcada", vigenciaCalcada);
		
		String situacaoVigenciaCalcada = " -- ";
		if(form.getSituacaoVigenciaCalcada() != null){
			
			if(form.getSituacaoVigenciaCalcada().equals("1")){
				
				situacaoVigenciaCalcada = "Vigente";
			}else if(form.getSituacaoVigenciaRua().equals("2")){
				
				situacaoVigenciaCalcada = "Não Vigente";
			}else{
				
				situacaoVigenciaCalcada = "Todos";
			}
		}
		
		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("situacaoVigenciaCalcada", situacaoVigenciaCalcada);
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterCustoPavimentoPorRepavimentadora.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterCustoPavimentoPorRepavimentadora,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;

	}
	
}