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
package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoRD;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de Filtrar Resolucao de Diretoria para Contrato pro Cliente
 * 
 * @author Paulo Diniz
 * @created 16/03/2011
 */
public class FiltrarResolucaoDiretoriaContratoParcelamentoAction extends
		GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma resolução de diretoria
	 * 
	 * [UC1134] Manter Resolução de Diretoria (RD) para Contratos de Parcelamento por cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obtém a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirManter");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarResolucaoDiretoriaContratoParcelamentoActionForm form = (FiltrarResolucaoDiretoriaContratoParcelamentoActionForm) actionForm;
		FiltroContratoParcelamentoRD filtroContratoParcelamento = new FiltroContratoParcelamentoRD(FiltroContratoParcelamentoRD.NUMERO);
		
		String numero = form.getNumero();
		String assunto = form.getAssunto();
		String dataVigenciaInicial = form.getDataVigenciaInicial();
		String dataVigenciaFinal = form.getDataVigenciaFinal();
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		boolean peloMenosUmParametroInformado = false;
		
		if(numero != null && !numero.equals("")){
			peloMenosUmParametroInformado = true;
		}
		
		if(assunto != null && !assunto.equals("")){
			peloMenosUmParametroInformado = true;
		}
		
		if(dataVigenciaInicial != null && !"".equals(dataVigenciaInicial)){
			peloMenosUmParametroInformado = true;
			
			if(dataVigenciaFinal == null || "".equals(dataVigenciaFinal)){
				dataVigenciaFinal = dataVigenciaInicial;
			}
		}
		
		//atencao.data.invalida
		if (!"".equals(dataVigenciaInicial) && !"".equals(dataVigenciaFinal)) {
			Date dataInicial = Util
					.converteStringParaDate(dataVigenciaInicial);
			Date dataFinal = Util.converteStringParaDate(dataVigenciaFinal);
			
			if(dataInicial == null || dataFinal == null){
				throw new ActionServletException(
						"atencao.data.invalida", null, "Data");
			}
			
			peloMenosUmParametroInformado = true;

			if (dataFinal.getTime() < dataInicial.getTime()) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido", null, Util
								.formatarData(new Date()));
			}
		}
		
		//Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		if (numero != null && !numero.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroContratoParcelamento.adicionarParametro(new ComparacaoTexto(
					FiltroContratoParcelamentoRD.NUMERO, numero.toUpperCase()));
		}
		
		if (assunto != null && !assunto.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroContratoParcelamento.adicionarParametro(new ComparacaoTexto(
					FiltroContratoParcelamentoRD.ASSUNTO, assunto.toUpperCase()));
		}
		
		if (dataVigenciaInicial != null
				&& !dataVigenciaInicial.trim().equalsIgnoreCase("")) {
			Date dataVigenciaInicialConvertida = Util
					.converteStringParaDate(dataVigenciaInicial);
			peloMenosUmParametroInformado = true;
			filtroContratoParcelamento.adicionarParametro(new MaiorQue(
					FiltroContratoParcelamentoRD.DATA_VIGENCIA_INICIO,
					dataVigenciaInicialConvertida));
		}
		if (dataVigenciaFinal != null
				&& !dataVigenciaFinal.trim().equalsIgnoreCase("")) {
			Date dataVigenciaFinalConvertida = Util
					.converteStringParaDate(dataVigenciaFinal);
			peloMenosUmParametroInformado = true;
			filtroContratoParcelamento.adicionarParametro(new MenorQue(
					FiltroContratoParcelamentoRD.DATA_VIGENCIA_FIM,
					dataVigenciaFinalConvertida));
		}
		
		sessao.setAttribute("filtroContratoParcelamento", filtroContratoParcelamento);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar);
		
		return retorno;
	}
		
}
