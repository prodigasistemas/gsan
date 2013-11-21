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
package gcom.gui.arrecadacao.aviso;

import java.util.Date;

import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0611] Movimentar Pagamentos/ Devoluções entre Avisos Bancários
 * 
 * @author Ana Maria
 *
 * @date 07/06/2007
 */
public class SelecionarPagamentosAvisoBancarioAction extends
		GcomAction {
  
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirMovimentarPagamentosDevolucoesAvisoBancario");	
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		SelecionarPagamentosAvisoBancarioActionForm form = (SelecionarPagamentosAvisoBancarioActionForm) actionForm;
		
		// Recupera os parâmetros do form
		Integer avisoBancarioO = new Integer(form.getAvisoBancarioO());
		Integer avisoBancarioD =  new Integer(form.getAvisoBancarioD());
		String dataDevolucao 		=  form.getDataDevolucao();
		String dataPagamento	=  form.getDataPagamento();
		Integer idArrecadacaoForma =  new Integer(form.getIdArrecadacaoForma());
		
		boolean peloMenosUmParametroInformado = false;
		
		FiltroDevolucao filtroDevolucao = null;
		if(dataDevolucao != null && !dataDevolucao.equals("")){
		  peloMenosUmParametroInformado = true;	
		  Date data = Util.converteStringParaDate(dataDevolucao); 
		  filtroDevolucao = new FiltroDevolucao();
		  filtroDevolucao.adicionarParametro(new ParametroSimples(FiltroDevolucao.AVISO_BANCARIO_ID, avisoBancarioO));
		  filtroDevolucao.adicionarParametro(new ParametroSimples(FiltroDevolucao.DATA_DEVOLUCAO, data));			
		}
		
		FiltroPagamento filtroPagamento = null;
		if((dataPagamento != null && !dataPagamento.equals("")) ||
				(idArrecadacaoForma != null && !idArrecadacaoForma.equals("0"))){
		  peloMenosUmParametroInformado = true;	
		  filtroPagamento = new FiltroPagamento();
		  filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.AVISO_BANCARIO_ID, avisoBancarioO));
		  if(dataPagamento != null && !dataPagamento.equals("")){
			Date data = Util.converteStringParaDate(dataPagamento);  
			filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.DATA_PAGAMENTO, data));   
		  }
		  if(idArrecadacaoForma != null && !idArrecadacaoForma.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.PAGAMENTO_ARRECADACAO_FORMA, idArrecadacaoForma));   
		  }

		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pela sessão 
		sessao.setAttribute("filtroDevolucao", filtroDevolucao);
		sessao.setAttribute("filtroPagamento", filtroPagamento);	
				
		sessao.setAttribute("avisoBancarioO", avisoBancarioO);
		sessao.setAttribute("avisoBancarioD", avisoBancarioD);
		
		String descricaoABOrigem = form.getCodigoAgenteArrecadadorO()+" - "+form.getDataLancamentoAvisoO()+" - "+form.getNumeroSequencialAvisoO();
		String descricaoABDestino = form.getCodigoAgenteArrecadadorD()+" - "+form.getDataLancamentoAvisoD()+" - "+form.getNumeroSequencialAvisoD();
		
		sessao.setAttribute("descricaoABOrigem", descricaoABOrigem);
		sessao.setAttribute("descricaoABDestino", descricaoABDestino);
		
		return retorno;
      
	}
}