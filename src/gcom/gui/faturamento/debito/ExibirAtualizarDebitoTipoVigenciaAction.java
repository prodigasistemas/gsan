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
package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.FiltroDebitoTipoVigencia;
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
 * Descrição da classe
 * 
 * @author Hugo Leonardo
 * @date 16/04/2010
 */
public class ExibirAtualizarDebitoTipoVigenciaAction extends GcomAction {
	/**
	 * [UC0986] Manter tipo de débito com vigência
	 * [SB0001] – Gerar vigência para o tipo de débito
	 * 
	 * Este caso de uso permite alterar um debito tipo vigencia
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarDebitoTipoVigencia");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarDebitoTipoVigenciaActionForm form = (AtualizarDebitoTipoVigenciaActionForm) actionForm;
		
		DebitoTipoVigencia debitoTipoVigencia = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		String idDebitoTipoVigencia = "";
		
		if ( sessao.getAttribute("objetoDebitoTipoVigencia") != null ) {
			debitoTipoVigencia = (DebitoTipoVigencia) sessao.getAttribute("objetoDebitoTipoVigencia");
		
		} else {
		
			idDebitoTipoVigencia = (String) httpServletRequest.getParameter("idRegistroAtualizar");
			
			
			if( idDebitoTipoVigencia != null ) {
				
				FiltroDebitoTipoVigencia filtroDebitoTipoVigencia = new FiltroDebitoTipoVigencia();
				
				filtroDebitoTipoVigencia.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoTipoVigencia.DEBITO_TIPO);
				filtroDebitoTipoVigencia.adicionarParametro(
							new ParametroSimples(FiltroDebitoTipoVigencia.ID, new Integer(idDebitoTipoVigencia)));
				
				debitoTipoVigencia = (DebitoTipoVigencia) fachada.pesquisar(filtroDebitoTipoVigencia, 
								DebitoTipoVigencia.class.getName()).iterator().next();
			}
		}
		Date timeStamp = debitoTipoVigencia.getUltimaAlteracao();
		
		if(debitoTipoVigencia.getId() != null && !debitoTipoVigencia.getId().equals("")){
			form.setIdDebitoTipoVigencia(debitoTipoVigencia.getId().toString());
		}
		
		if(debitoTipoVigencia.getDebitoTipo() != null && !debitoTipoVigencia.getDebitoTipo().equals("")){
			form.setDebitoTipo(debitoTipoVigencia.getDebitoTipo().getId().toString());
		}
		
		if(debitoTipoVigencia.getDebitoTipo().getDescricao() != null && !debitoTipoVigencia.getDebitoTipo().getDescricao().equals("")){
			form.setNomeDebitoTipo(debitoTipoVigencia.getDebitoTipo().getDescricao().toString());
		}
		
		if(debitoTipoVigencia.getValorDebito() != null && !debitoTipoVigencia.getValorDebito().equals("")){
			form.setValorDebito(Util.formatarBigDecimalParaStringComVirgula(debitoTipoVigencia.getValorDebito()));
		}
		
		if(debitoTipoVigencia.getDataVigenciaInicial() != null && !debitoTipoVigencia.getDataVigenciaInicial().equals("")){
			form.setDataVigenciaInicial(Util.formatarData(debitoTipoVigencia.getDataVigenciaInicial()));
		}
		
		if(debitoTipoVigencia.getDataVigenciaFinal() != null && !debitoTipoVigencia.getDataVigenciaFinal().equals("")){
			form.setDataVigenciaFinal(Util.formatarData(debitoTipoVigencia.getDataVigenciaFinal()));
		}
		
		sessao.setAttribute("idDebitoTipoVigencia", idDebitoTipoVigencia);
		sessao.setAttribute("debitoTipoVigencia", debitoTipoVigencia);
		sessao.setAttribute("timeStamp", timeStamp);
		
		httpServletRequest.setAttribute("idDebitoTipoVigencia", idDebitoTipoVigencia);		
		
		return retorno;
		
	}

}
