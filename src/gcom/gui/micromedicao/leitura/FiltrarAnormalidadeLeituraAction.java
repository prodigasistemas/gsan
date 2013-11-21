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
package gcom.gui.micromedicao.leitura;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 05/08/2006
 */
public class FiltrarAnormalidadeLeituraAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servicço
	 * 
	 * [UC0437] Pesquisar Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 31/07/2006
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterAnormalidadeLeitura");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarAnormalidadeLeituraActionForm filtrarAnormalidadeLeituraActionForm = (FiltrarAnormalidadeLeituraActionForm) actionForm;

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = filtrarAnormalidadeLeituraActionForm.getDescricao();

		String indicadorRelativoHidrometro = filtrarAnormalidadeLeituraActionForm
				.getIndicadorRelativoHidrometro();

		String indicadorImovelSemHidrometro = filtrarAnormalidadeLeituraActionForm
				.getIndicadorImovelSemHidrometro();

		String usoRestritoSistema = filtrarAnormalidadeLeituraActionForm
				.getUsoRestritoSistema();

		String perdaTarifaSocial = filtrarAnormalidadeLeituraActionForm
				.getPerdaTarifaSocial();

		String osAutomatico = filtrarAnormalidadeLeituraActionForm
				.getOsAutomatico();

		String tipoServico = filtrarAnormalidadeLeituraActionForm
				.getTipoServico();

		String consumoLeituraNaoInformado = filtrarAnormalidadeLeituraActionForm
				.getConsumoLeituraNaoInformado();

		String consumoLeituraInformado = filtrarAnormalidadeLeituraActionForm
				.getConsumoLeituraInformado();

		String leituraLeituraNaoturaInformado = filtrarAnormalidadeLeituraActionForm
				.getLeituraLeituraNaoturaInformado();

		String leituraLeituraInformado = filtrarAnormalidadeLeituraActionForm
				.getLeituraLeituraInformado();

		String tipoPesquisa = filtrarAnormalidadeLeituraActionForm
				.getTipoPesquisa();

		String indicadorUso = filtrarAnormalidadeLeituraActionForm
				.getIndicadorUso();

		/**
		 * TODO : COSANPA
		 * Pamela Gatinho - 13/03/2012
		 * Campo que identifica se a anormalidade será usada ou
		 * não no sistema de leitura e impressão simultanea.
		 */
		String indicadorImpressaoSimultanea = filtrarAnormalidadeLeituraActionForm.getIndicadorImpressaoSimultanea();
		
		// Verifica se o campo Descrição da Anormalidade de Leitura foi
		// informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroLeituraAnormalidade
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLeituraAnormalidade.DESCRICAO, descricao));
			} else {
				filtroLeituraAnormalidade
						.adicionarParametro(new ComparacaoTexto(
								FiltroLeituraAnormalidade.DESCRICAO, descricao));
			}

		}

		// Verifica se o campo Anormalidade Relativa a Hidrômetro foi informado

		if (indicadorRelativoHidrometro != null
				&& !indicadorRelativoHidrometro.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_RELATIVO_HIDROMETRO,
					indicadorRelativoHidrometro));

		}

		// Verifica se o campo Anormalidade Aceita para Ligação sem Hidrômetro
		// foi informado

		if (indicadorImovelSemHidrometro != null
				&& !indicadorImovelSemHidrometro.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_IMOVEL_SEM_HIDROMETRO,
					indicadorImovelSemHidrometro));

		}

		// Verifica se o campo Anormalidade de Uso Restrito do Sistema foi
		// informado

		if (usoRestritoSistema != null
				&& !usoRestritoSistema.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_USO_SISTEMA,
					usoRestritoSistema));

		}

		// Verifica se o campo Anormalidade Acarreta Perda Tarifa Social foi
		// informado

		if (perdaTarifaSocial != null
				&& !perdaTarifaSocial.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_PERDA_TARIFA_SOCIAL,
					perdaTarifaSocial));

		}

		// Verifica se o campo Anormalidade Emite OS Automática foi informado

		if (perdaTarifaSocial != null
				&& !perdaTarifaSocial.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_PERDA_TARIFA_SOCIAL,
					perdaTarifaSocial));

		}

		// Verifica se o campo Tipo de Serviço da OS Automática foi informado

		if (osAutomatico != null
				&& !osAutomatico.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_EMISSAO_ORDEM_SERVICO,
					osAutomatico));

		}

		// Verifica se o campo Consumo a Ser Cobrado (leitura não informada) foi
		// informado

		if (tipoServico != null
				&& !tipoServico.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_TIPO_SERVICO, tipoServico));

		}

		// Verifica se o campo Consumo a Ser Cobrado (leitura informada) foi
		// informado

		if (consumoLeituraNaoInformado != null
				&& !consumoLeituraNaoInformado.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_CONSUMO_A_COBRAR_SEM_LEITURA,
					consumoLeituraNaoInformado));

		}

		// Verifica se o campo Leitura para faturamento (leitura não informada)
		// foi informado

		if (consumoLeituraInformado != null
				&& !consumoLeituraInformado.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_CONSUMO_A_COBRAR_COM_LEITURA,
					consumoLeituraInformado));

		}

		// Verifica se o campo Referência do Tipo de Serviço foi informado

		if (leituraLeituraNaoturaInformado != null
				&& !leituraLeituraNaoturaInformado.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_LEITURA_A_FATURAR_SEM_LEITURA,
					leituraLeituraNaoturaInformado));

		}

		// Verifica se o campo Leitura para faturamento ( leitura informada) foi
		// informado

		if (leituraLeituraInformado != null
				&& !leituraLeituraInformado.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_LEITURA_A_FATURAR_COM_LEITURA,
					leituraLeituraInformado));

		}

		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_USO, indicadorUso));
		}

		if ( indicadorImpressaoSimultanea != null &&
				 !indicadorImpressaoSimultanea.trim().equalsIgnoreCase( "" )){
				peloMenosUmParametroInformado = true;

				filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroLeituraAnormalidade.INDICADOR_IMPRESSAO_SIMULTANEA,
						indicadorImpressaoSimultanea));			
		}
		
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

		sessao.setAttribute("filtroLeituraAnormalidade",
				filtroLeituraAnormalidade);

		return retorno;
	}

}