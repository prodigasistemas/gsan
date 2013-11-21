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
package gcom.gui.relatorio.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.faturamento.debito.FiltrarTipoDebitoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioManterTipoDebito;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
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
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioTipoDebitoManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarTipoDebitoActionForm filtrarTipoDebitoActionForm = (FiltrarTipoDebitoActionForm) actionForm;


		FiltroDebitoTipo filtroDebitoTipo = (FiltroDebitoTipo) sessao
				.getAttribute("filtroDebitoTipo");

		// Inicio da parte que vai mandar os parametros para o relatório
		Fachada fachada = Fachada.getInstancia();

		DebitoTipo debitoTipoParametros = new DebitoTipo();

		String id = null;
		

		Short indicadorGeracaoDebitoAuto = null;
		
		if(filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoAutomatica()!= null
				&& !filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoAutomatica().equals("")){
			
			indicadorGeracaoDebitoAuto = new Short ("" + filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoAutomatica());
		}
		
		Short indicadorGeracaoDebitoConta = null;
		
		if(filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoConta()!= null
				&& !filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoConta().equals("")){
			
			indicadorGeracaoDebitoConta = new Short ("" + filtrarTipoDebitoActionForm.getIndicadorGeracaoDebitoConta());
		}
		
		if (filtrarTipoDebitoActionForm.getFinanciamentoTipo() != null && !filtrarTipoDebitoActionForm.getFinanciamentoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
			filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(FiltroFinanciamentoTipo.ID, filtrarTipoDebitoActionForm.getFinanciamentoTipo()));
			
			Collection colecaoFinanciamentoTipo = fachada.pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());
			
			if (colecaoFinanciamentoTipo != null && !colecaoFinanciamentoTipo.isEmpty()) {
				FinanciamentoTipo financiamentoTipo = (FinanciamentoTipo) Util.retonarObjetoDeColecao(colecaoFinanciamentoTipo);
				debitoTipoParametros.setFinanciamentoTipo(financiamentoTipo);
			}
			
		}
		if (filtrarTipoDebitoActionForm.getLancamentoItemContabil() != null && !filtrarTipoDebitoActionForm.getLancamentoItemContabil().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroLancamentoItemContabil filtroLancamentoItemContabil = new FiltroLancamentoItemContabil();
			filtroLancamentoItemContabil.adicionarParametro(new ParametroSimples(FiltroLancamentoItemContabil.ID, filtrarTipoDebitoActionForm.getLancamentoItemContabil()));
			
			Collection colecaoLancamentoItemContabil = fachada.pesquisar(filtroLancamentoItemContabil, LancamentoItemContabil.class.getName());
			
			if (colecaoLancamentoItemContabil != null && !colecaoLancamentoItemContabil.isEmpty()) {
				LancamentoItemContabil lancamentoItemContabil = (LancamentoItemContabil) Util.retonarObjetoDeColecao(colecaoLancamentoItemContabil);
				debitoTipoParametros.setLancamentoItemContabil(lancamentoItemContabil);
			}
			
		}
		Short indicadorUso = null;
		
		if(filtrarTipoDebitoActionForm.getIndicadorUso()!= null && !filtrarTipoDebitoActionForm.getIndicadorUso().equals("")){
			
			indicadorUso = new Short ("" + filtrarTipoDebitoActionForm.getIndicadorUso());
		}
	
		BigDecimal valorLimiteDebitoInicial = null;
		
		if(filtrarTipoDebitoActionForm.getValorLimiteDebitoInicial() != null && !filtrarTipoDebitoActionForm.getValorLimiteDebitoInicial().equals("")){
			valorLimiteDebitoInicial = new BigDecimal (filtrarTipoDebitoActionForm.getValorLimiteDebitoInicial());
		}
		
		BigDecimal valorLimiteDebitoFinal = null;
		
		if(filtrarTipoDebitoActionForm.getValorLimiteDebitoFinal() != null && !filtrarTipoDebitoActionForm.getValorLimiteDebitoFinal().equals("")){
			valorLimiteDebitoFinal = new BigDecimal (filtrarTipoDebitoActionForm.getValorLimiteDebitoFinal());
		}
		
		String valorSugerido = null;
		
		if(filtrarTipoDebitoActionForm.getValorSugerido()!= null && !filtrarTipoDebitoActionForm.getValorSugerido().equals("")){
			
			valorSugerido = new String (filtrarTipoDebitoActionForm.getValorSugerido());
		}
		
		
		// seta os parametros que serão mostrados no relatório

		debitoTipoParametros.setId(id == null ? null : new Integer(
				id));
		debitoTipoParametros.setDescricao(""
				+ filtrarTipoDebitoActionForm.getDescricao());
		debitoTipoParametros.setDescricaoAbreviada(""+ filtrarTipoDebitoActionForm.getDescricaoAbreviada());
		debitoTipoParametros.setIndicadorGeracaoAutomatica( indicadorGeracaoDebitoAuto );
		debitoTipoParametros.setIndicadorGeracaoConta( indicadorGeracaoDebitoConta );
		debitoTipoParametros.setIndicadorUso(indicadorUso);
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterTipoDebito relatorioManterTipoDebito= new RelatorioManterTipoDebito(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterTipoDebito.addParametro("filtroDebitoTipo",
				filtroDebitoTipo);
		relatorioManterTipoDebito.addParametro("valorLimiteDebitoInicial", valorLimiteDebitoInicial);
		relatorioManterTipoDebito.addParametro("valorLimiteDebitoFinal", valorLimiteDebitoFinal);
		relatorioManterTipoDebito.addParametro("valorSugerido", valorSugerido);
		relatorioManterTipoDebito.addParametro("debitoTipoParametros",
				debitoTipoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterTipoDebito.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterTipoDebito,
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