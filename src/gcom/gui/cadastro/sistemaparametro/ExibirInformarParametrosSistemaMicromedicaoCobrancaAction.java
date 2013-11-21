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
package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.util.ConstantesSistema;
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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 09/01/2007
 */
public class ExibirInformarParametrosSistemaMicromedicaoCobrancaAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirInformarParametrosSistemaMicromedicaoCobranca");

		HttpSession sessao = this.getSessao(httpServletRequest);

		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		SistemaParametro sistemaParametro = 
			(SistemaParametro) sessao.getAttribute("sistemaParametro");

		Collection colecaoHidrometroCapacidade = null;

		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		filtroHidrometroCapacidade.setCampoOrderBy(FiltroHidrometroCapacidade.ID);

		filtroHidrometroCapacidade.adicionarParametro(
			new ParametroSimples(FiltroHidrometroCapacidade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		colecaoHidrometroCapacidade = this.getFachada().pesquisar(
				filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());

		httpServletRequest.setAttribute("colecaoHidrometroCapacidade",colecaoHidrometroCapacidade);

		// Verifica se ja entrou nesse action, caso nao coloca no form os dados
		// do objeto sistemaParametro
		Integer cont;

		if (sessao.getAttribute("MicromedicaoCobranca") == null) {

			cont = 1;
			sessao.setAttribute("MicromedicaoCobranca", cont);

			if (sistemaParametro.getHidrometroCapacidade() != null) {
				form.setCodigoMenorCapacidade(sistemaParametro.getHidrometroCapacidade().getId().toString());
			}

			if (sistemaParametro.getIndicadorFaixaFalsa() != null) {
				form.setIndicadorGeracaoFaixaFalsa(sistemaParametro.getIndicadorFaixaFalsa().toString());
			}

			if (sistemaParametro.getIndicadorUsoFaixaFalsa() != null) {
				form.setIndicadorPercentualGeracaoFaixaFalsa(sistemaParametro.getIndicadorUsoFaixaFalsa().toString());
			}

			if (sistemaParametro.getPercentualFaixaFalsa() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getPercentualFaixaFalsa());

				form.setPercentualGeracaoFaixaFalsa(valorAux);
			}

			if (sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura() != null) {
				form.setIndicadorPercentualGeracaoFiscalizacaoLeitura(
					sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura().toString());
			}

			if (sistemaParametro.getIndicadorUsoFiscalizadorLeitura() != null) {
				form.setIndicadorGeracaoFiscalizacaoLeitura(
						sistemaParametro.getIndicadorUsoFiscalizadorLeitura().toString());
			}

			if (sistemaParametro.getPercentualFiscalizacaoLeitura() != null) {

				String valorAux = 
					Util.formatarMoedaReal(sistemaParametro.getPercentualFiscalizacaoLeitura());

				form.setPercentualGeracaoFiscalizacaoLeitura(valorAux);
			}

			if (sistemaParametro.getIncrementoMaximoConsumoRateio() != null) {

				form.setIncrementoMaximoConsumo(sistemaParametro.getIncrementoMaximoConsumoRateio().toString());
			}

			if (sistemaParametro.getDecrementoMaximoConsumoRateio() != null) {
				form.setDecrementoMaximoConsumo(sistemaParametro.getDecrementoMaximoConsumoRateio().toString());
			}
			
			if (sistemaParametro.getPercentualToleranciaRateio() != null) {

				String valorAux = Util.formataBigDecimal(sistemaParametro
						.getPercentualToleranciaRateio(), 1, false);

				form.setPercentualToleranciaRateioConsumo(valorAux);
			}
			
			if (sistemaParametro.getNumeroDiasVencimentoCobranca() != null) {
				form.setDiasVencimentoCobranca(sistemaParametro.getNumeroDiasVencimentoCobranca().toString());
			}

			if (sistemaParametro.getNumeroMaximoMesesSancoes() != null) {
				form.setNumeroMaximoMesesSancoes(sistemaParametro.getNumeroMaximoMesesSancoes().toString());
			}

			form.setValorSegundaVia(Util.formatarMoedaReal(sistemaParametro.getValorSegundaVia()));
			
			form.setIndicadorCobrarTaxaExtrato(""+sistemaParametro.getIndicadorCobrarTaxaExtrato());

			if (sistemaParametro.getCodigoPeriodicidadeNegativacao() != null) {
				form.setCodigoPeriodicidadeNegativacao(sistemaParametro.getCodigoPeriodicidadeNegativacao().toString());
			}

			form.setNumeroDiasCalculoAcrescimos(""+sistemaParametro.getNumeroDiasCalculoAcrescimos());
			
			form.setNumeroDiasValidadeExtrato(sistemaParametro.getNumeroDiasValidadeExtrato().toString());
			
			form.setIndicadorParcelamentoConfirmado(""+ sistemaParametro.getIndicadorParcelamentoConfirmado());
			
			form.setIndicadorTabelaPrice(""+ sistemaParametro.getIndicadorTabelaPrice());
			
			if (sistemaParametro.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo() != null){
				form.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(
						sistemaParametro.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo().toString());
			}
			
			if (sistemaParametro.getNumeroDiasValidadeExtratoPermissaoEspecial() != null) {
				form.setNumeroDiasValidadeExtratoPermissaoEspecial(
						sistemaParametro.getNumeroDiasValidadeExtratoPermissaoEspecial().toString());
			}

			form.setNumeroDiasVencimentoEntradaParcelamento(""+
					sistemaParametro.getNumeroDiasVencimentoEntradaParcelamento());
		
		}
		
		Collection colecaoResolucaoDiretoria = this.getFachada().pesquisarResolucaoDiretoriaMaiorDataVigenciaInicio();
		httpServletRequest.setAttribute("colecaoResolucaoDiretoria",colecaoResolucaoDiretoria);
		
		if (sistemaParametro.getResolucaoDiretoria() != null &&
			sistemaParametro.getResolucaoDiretoria().getId() != null) {
			form.setIdResolucaoDiretoria(sistemaParametro.getResolucaoDiretoria().getId().toString());
		}
		
		if (sistemaParametro.getIndicadorBloqueioContasContratoParcelDebitos() != null){
			form.setIndicadorBloqueioContasContratoParcelDebitos(sistemaParametro.getIndicadorBloqueioContasContratoParcelDebitos().toString());
		}
		
		if (sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() != null){
			form.setIndicadorBloqueioContasContratoParcelManterConta(sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta().toString());
		}
		
		if (sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito() != null){
			form.setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito().toString());
		}
		
		if(sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelDebito() != null){
		 	form.setIndicadorBloqueioDebitoACobrarContratoParcelDebito(sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelDebito().toString());
		}
				
		if (sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() != null){
			form.setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta().toString());
		}
		
		if(sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito() != null){
			form.setIndicadorBloqueioDebitoACobrarContratoParcelManterDebito(sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito().toString());
		}
				
		if (sistemaParametro.getNumeroMaximoParcelasContratosParcelamento() != null){
			form.setNumeroMaximoParcelasContratosParcelamento(sistemaParametro.getNumeroMaximoParcelasContratosParcelamento().toString());
		}
	
		return retorno;
	}
}
