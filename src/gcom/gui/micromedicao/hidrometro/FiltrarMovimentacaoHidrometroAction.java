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
package gcom.gui.micromedicao.hidrometro;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.sql.Time;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0000] Filtrar Movimentação de Hidrômetro
 * 
 * @author Fernanda Paiva, Roberta Costa
 * @created 23 de Janeiro de 2006, 03/08/2006
 */
public class FiltrarMovimentacaoHidrometroAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;
		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		retorno = actionMapping.findForward("consultarMovimentacaoHidrometro");
		
		// Recupera os parâmetros do form
		String fixo = hidrometroActionForm.getFixo();
		
		String faixaInicial = hidrometroActionForm.getFaixaInicial();
		
		String faixaFinal = hidrometroActionForm.getFaixaFinal();
		
		String dataMovimentacaoInicial = hidrometroActionForm
				.getDataMovimentacaoInicial();
		
		sessao.setAttribute("dataMovimentacaoInicial", dataMovimentacaoInicial);
		
		String dataMovimentacaoFinal = hidrometroActionForm
				.getDataMovimentacaoFinal();
		
		sessao.setAttribute("dataMovimentacaoFinal", dataMovimentacaoFinal);
		
		String horaMovimentacaoInicial = hidrometroActionForm
				.getHoraMovimentacaoInicial();
		
		sessao.setAttribute("horaMovimentacaoInicial", horaMovimentacaoInicial);
		
		String horaMovimentacaoFinal = hidrometroActionForm
				.getHoraMovimentacaoFinal();
		
		sessao.setAttribute("horaMovimentacaoFinal", horaMovimentacaoFinal);
		
		String localArmazenagemDestino = hidrometroActionForm
				.getLocalArmazenagemDestino();
		
		sessao.setAttribute("localArmazenagemDestino", localArmazenagemDestino);
		
		String localArmazenagemOrigem = hidrometroActionForm
				.getLocalArmazenagemOrigem();
		
		sessao.setAttribute("localArmazenagemOrigem", localArmazenagemOrigem);
		
		Integer motivoMovimentacao = new Integer(hidrometroActionForm.getMotivoMovimentacao());
		
		sessao.setAttribute("motivoMovimentacao", motivoMovimentacao);
		
		String usuario = hidrometroActionForm.getUsuario();
		
		sessao.setAttribute("usuario", usuario);

		FiltroHidrometroMovimentacao filtroHidrometroMovimentacao = new FiltroHidrometroMovimentacao();
		
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroMotivoMovimentacao");
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemOrigem");
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemDestino");
		
		filtroHidrometroMovimentacao.adicionarCaminhoParaCarregamentoEntidade("usuario");
		
		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		// Caso o fixo, a faixa inicial e faixa final seja diferente de null
		// então ignora os outros parametros e faz a pesquisa do filtro por
		// esses 3 parâmetros
		if (fixo != null && !fixo.equalsIgnoreCase("")) {
			
			if (faixaInicial != null && !faixaInicial.equalsIgnoreCase("")) {
				sessao.setAttribute("faixaInicial", faixaInicial);
			}
			
			if (faixaFinal != null && !faixaFinal.equalsIgnoreCase("")) {
				sessao.setAttribute("faixaFinal", faixaFinal);
			}
			
			sessao.setAttribute("fixo", fixo);
			
			peloMenosUmParametroInformado = true;
			
			sessao.removeAttribute("dataMovimentacaoInicial");
			sessao.removeAttribute("dataMovimentacaoFinal");
			sessao.removeAttribute("horaMovimentacaoInicial");
			sessao.removeAttribute("horaMovimentacaoFinal");
			sessao.removeAttribute("localArmazenagemDestino");
			sessao.removeAttribute("localArmazenagemOrigem");
			sessao.removeAttribute("motivoMovimentacao");
			sessao.removeAttribute("usuario");
			
		} else{
			if (dataMovimentacaoInicial != null
					&& !dataMovimentacaoInicial.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new MaiorQue(
						FiltroHidrometroMovimentacao.DATA,
						Util.converteStringParaDate(dataMovimentacaoInicial)));
			}
			if (dataMovimentacaoInicial != null
					&& !dataMovimentacaoInicial.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new MaiorQue(
						FiltroHidrometroMovimentacao.DATA,
						Util.converteStringParaDate(dataMovimentacaoInicial)));
			}
			
			if (dataMovimentacaoInicial != null
					&& !dataMovimentacaoInicial.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new MaiorQue(
						FiltroHidrometroMovimentacao.DATA,
						Util.converteStringParaDate(dataMovimentacaoInicial)));
			}
			if (dataMovimentacaoFinal != null
					&& !dataMovimentacaoFinal.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao.adicionarParametro(new MenorQue(
						FiltroHidrometroMovimentacao.DATA, Util.converteStringParaDate(dataMovimentacaoFinal)));
			}
			if (horaMovimentacaoInicial != null
					&& !horaMovimentacaoInicial.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new MaiorQue(
						FiltroHidrometroMovimentacao.HORA,
						new Time(Util.converterStringParaHoraMinuto(horaMovimentacaoInicial).getTime())));
			}
			if (horaMovimentacaoFinal != null
					&& !horaMovimentacaoFinal.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao.adicionarParametro(new MenorQue(
						FiltroHidrometroMovimentacao.HORA, Util.converterStringParaHoraMinuto(horaMovimentacaoFinal)));
			}
			if (motivoMovimentacao != null 
					&& motivoMovimentacao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMovimentacao.HIDROMETRO_MOTIVO,
						motivoMovimentacao));
			}
			if (localArmazenagemOrigem != null
					&& !localArmazenagemOrigem.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMovimentacao.HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_ORIGEM_ID,
						localArmazenagemOrigem));
			}
			if (localArmazenagemDestino != null
					&& !localArmazenagemDestino.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMovimentacao.HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_DESTINO_ID,
						localArmazenagemDestino));
			}
			
			if (usuario != null && !usuario.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao.adicionarCaminhoParaCarregamentoEntidade("usuario"); 
				filtroHidrometroMovimentacao.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMovimentacao.USUARIO, usuario)); 
			}
			
			sessao.removeAttribute("fixo");
			sessao.removeAttribute("faixaFinal");
			sessao.removeAttribute("faixaInicial");
		}
		

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroHidrometroMovimentacao.setConsultaSemLimites(true);
		
		// Manda o filtro pelo request
		httpServletRequest.setAttribute("filtroMovimentacaoHidrometro",
				filtroHidrometroMovimentacao);
		
		sessao.setAttribute("filtroMovimentacaoHidrometro", filtroHidrometroMovimentacao);				

		return retorno;
	}
}
