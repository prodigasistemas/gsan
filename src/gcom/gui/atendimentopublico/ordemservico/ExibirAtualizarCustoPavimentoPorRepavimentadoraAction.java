/**
 * 
 */
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

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
 * @date 27/12/2010
 */
public class ExibirAtualizarCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarCustoPavimentoPorRepavimentadoraAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCustoPavimentoPorRepavimentadoraActionForm form = (AtualizarCustoPavimentoPorRepavimentadoraActionForm) actionForm;
		
		UnidadeRepavimentadoraCustoPavimentoRua unidadeRepavimentadoraCustoPavimentoRua = null;
		
		UnidadeRepavimentadoraCustoPavimentoCalcada unidadeRepavimentadoraCustoPavimentoCalcada = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		String idUnidadeRepavimentadoraCustoPavimentoRua = "";
		
		String idUnidadeRepavimentadoraCustoPavimentoCalcada = "";
		
		// Atualiza Custo Pavimento Rua
		if(httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("atualizarRua") ){
			
			if ( sessao.getAttribute("objetoUnidadeCustoPavimentoRua") != null ) {
				unidadeRepavimentadoraCustoPavimentoRua = (UnidadeRepavimentadoraCustoPavimentoRua) sessao.getAttribute("objetoUnidadeCustoPavimentoRua");
			
			} else {
			
				idUnidadeRepavimentadoraCustoPavimentoRua = (String) httpServletRequest.getParameter("idAtualizacao");
				
				
				if( idUnidadeRepavimentadoraCustoPavimentoRua != null ) {
					
					FiltroUnidadeRepavimentadoraCustoPavimentoRua filtroUnidadeRepavimentadoraCustoPavimentoRua = new FiltroUnidadeRepavimentadoraCustoPavimentoRua();
					
					filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA);
					filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoRua.UNIDADE_REPAVIMENTADORA);
					
					filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarParametro(
								new ParametroSimples(FiltroUnidadeRepavimentadoraCustoPavimentoRua.ID, new Integer(idUnidadeRepavimentadoraCustoPavimentoRua)));
					
					unidadeRepavimentadoraCustoPavimentoRua = (UnidadeRepavimentadoraCustoPavimentoRua) fachada.pesquisar(filtroUnidadeRepavimentadoraCustoPavimentoRua, 
							UnidadeRepavimentadoraCustoPavimentoRua.class.getName()).iterator().next();
				}
			}
			
			Date timeStamp = unidadeRepavimentadoraCustoPavimentoRua.getUltimaAlteracao();
			
			// Descrição Unidade Repavimentadora Rua
			if(unidadeRepavimentadoraCustoPavimentoRua.getUnidadeRepavimentadora() != null && !unidadeRepavimentadoraCustoPavimentoRua.getUnidadeRepavimentadora().getDescricao().equals("")){
				form.setDescricaoUnidadeRepavimentadora(unidadeRepavimentadoraCustoPavimentoRua.getUnidadeRepavimentadora().getDescricao());
			}
			
			// Descrição Tipo Pavimento Rua
			if(unidadeRepavimentadoraCustoPavimentoRua.getPavimentoRua() != null && !unidadeRepavimentadoraCustoPavimentoRua.getPavimentoRua().getDescricao().equals("")){
				form.setDescricaoTipoPavimentoRua(unidadeRepavimentadoraCustoPavimentoRua.getPavimentoRua().getDescricao());
			}
			
			// Valor Pavimento Rua
			if(unidadeRepavimentadoraCustoPavimentoRua.getValorPavimento() != null && !unidadeRepavimentadoraCustoPavimentoRua.getValorPavimento().equals("")){
				form.setValorPavimentoRua(Util.formatarBigDecimalParaStringComVirgula(unidadeRepavimentadoraCustoPavimentoRua.getValorPavimento()));
			}
			
			// Data Inicio Vigencia Pavimento Rua
			if(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial() != null && !unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial().equals("")){
				form.setDataVigenciaInicialPavimentoRua(Util.formatarData(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial()));
			}
			
			// Data Fim Vigencia Pavimento Rua
			if(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaFinal() != null && !unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaFinal().equals("")){
				form.setDataVigenciaFinalPavimentoRua(Util.formatarData(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaFinal()));
			}else{
				form.setDataVigenciaFinalPavimentoRua("");
			}
			
			sessao.setAttribute("idUnidadeRepavimentadoraCustoPavimentoRua", idUnidadeRepavimentadoraCustoPavimentoRua);
			sessao.setAttribute("unidadeRepavimentadoraCustoPavimentoRua", unidadeRepavimentadoraCustoPavimentoRua);
			sessao.setAttribute("timeStamp", timeStamp);
			
			httpServletRequest.setAttribute("idUnidadeRepavimentadoraCustoPavimentoRua", idUnidadeRepavimentadoraCustoPavimentoRua);		
			
		}else {
			
			// Atualiza Custo Pavimento Calçada
			if ( sessao.getAttribute("objetoUnidadeCustoPavimentoCalcada") != null ) {
				unidadeRepavimentadoraCustoPavimentoCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) sessao.getAttribute("objetoUnidadeCustoPavimentoCalcada");
			
			} else {
			
				idUnidadeRepavimentadoraCustoPavimentoCalcada = (String) httpServletRequest.getParameter("idAtualizacao");
				
				if( idUnidadeRepavimentadoraCustoPavimentoCalcada != null ) {
					
					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada filtroUnidadeRepavimentadoraCustoPavimentoCalcada = new FiltroUnidadeRepavimentadoraCustoPavimentoCalcada();
					
					filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.PAVIMENTO_CALCADA);
					filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.UNIDADE_REPAVIMENTADORA);
					
					filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarParametro(
								new ParametroSimples(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.ID, new Integer(idUnidadeRepavimentadoraCustoPavimentoCalcada)));
					
					unidadeRepavimentadoraCustoPavimentoCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) fachada.pesquisar(filtroUnidadeRepavimentadoraCustoPavimentoCalcada, 
							UnidadeRepavimentadoraCustoPavimentoCalcada.class.getName()).iterator().next();
				}
			}
			
			Date timeStamp = unidadeRepavimentadoraCustoPavimentoCalcada.getUltimaAlteracao();
			
			// Descrição Unidade Repavimentadora Rua
			if(unidadeRepavimentadoraCustoPavimentoCalcada.getUnidadeRepavimentadora() != null && !unidadeRepavimentadoraCustoPavimentoCalcada.getUnidadeRepavimentadora().getDescricao().equals("")){
				form.setDescricaoUnidadeRepavimentadora(unidadeRepavimentadoraCustoPavimentoCalcada.getUnidadeRepavimentadora().getDescricao());
			}
			
			// Descrição Tipo Pavimento Calçada
			if(unidadeRepavimentadoraCustoPavimentoCalcada.getPavimentoCalcada() != null && !unidadeRepavimentadoraCustoPavimentoCalcada.getPavimentoCalcada().getDescricao().equals("")){
				form.setDescricaoTipoPavimentoCalcada(unidadeRepavimentadoraCustoPavimentoCalcada.getPavimentoCalcada().getDescricao());
			}
			
			// Valor Pavimento Calçada
			if(unidadeRepavimentadoraCustoPavimentoCalcada.getValorPavimento() != null && !unidadeRepavimentadoraCustoPavimentoCalcada.getValorPavimento().equals("")){
				form.setValorPavimentoCalcada(Util.formatarBigDecimalParaStringComVirgula(unidadeRepavimentadoraCustoPavimentoCalcada.getValorPavimento()));
			}
			
			// Data Inicio Vigencia Pavimento Calçada
			if(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial() != null && !unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial().equals("")){
				form.setDataVigenciaInicialPavimentoCalcada(Util.formatarData(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial()));
			}
			
			// Data Fim Vigencia Pavimento Calçada
			if(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaFinal() != null && !unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaFinal().equals("")){
				form.setDataVigenciaFinalPavimentoCalcada(Util.formatarData(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaFinal()));
			}else{
				form.setDataVigenciaFinalPavimentoCalcada("");
			}
			
			sessao.setAttribute("idUnidadeRepavimentadoraCustoPavimentoCalcada", idUnidadeRepavimentadoraCustoPavimentoCalcada);
			sessao.setAttribute("unidadeRepavimentadoraCustoPavimentoCalcada", unidadeRepavimentadoraCustoPavimentoCalcada);
			sessao.setAttribute("timeStamp", timeStamp);
			
			httpServletRequest.setAttribute("idUnidadeRepavimentadoraCustoPavimentoCalcada", idUnidadeRepavimentadoraCustoPavimentoCalcada);		
			
		}

		return retorno;
	}
}
