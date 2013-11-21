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

import gcom.cobranca.CobrancaForma;
import gcom.cobranca.FiltroCobrancaForma;
import gcom.cobranca.contratoparcelamento.ComparatorParcela;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroQuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
public class ExibirAtualizarResolucaoDiretoriaContratoParcelamentoAction extends
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		ActionForward retorno = actionMapping.findForward("exibirAtualizar");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String numeroContratoParcelamentoRD =(String) httpServletRequest.getParameter("numeroContratoParcelamentoRD");
		
		//Para no caso da Action seja ativada diretamente pelo filtro
		if(numeroContratoParcelamentoRD == null){
			numeroContratoParcelamentoRD = (String) sessao.getAttribute("numeroContratoParcelamentoRD");
		}
		
		FiltroCobrancaForma filtroCobranca = new FiltroCobrancaForma();
        filtroCobranca.adicionarParametro(new ParametroSimples(FiltroCobrancaForma.IC_USO_CONTRATO_PARCEL_CLIENTE, "1"));
		Collection coll = this.getFachada().pesquisar(filtroCobranca, CobrancaForma.class.getName()); 
		sessao.setAttribute("collFormaPgto", coll);
		
		ContratoParcelamentoRD contratoRDAtualizar = (ContratoParcelamentoRD) sessao.getAttribute("contratoAtualizar");
		List<QuantidadePrestacoes> parcelas = (List<QuantidadePrestacoes>) sessao.getAttribute("parcelas");

		if(numeroContratoParcelamentoRD != null && !numeroContratoParcelamentoRD.equals("")){
			contratoRDAtualizar = this.getFachada().pesquisarContratoParcelamentoRDPorNumero(numeroContratoParcelamentoRD);

			boolean verificaContratoAssociado = fachada.verificaResolucaoDiretoriaAssociadaContratoParcelamentoNaoEncerrado(contratoRDAtualizar.getId());
			if(verificaContratoAssociado){
				throw new ActionServletException("atencao.existe.contrato.parcelamento.associado.atualizar", null, "");
			}
			
			FiltroQuantidadePrestacoes filtro = new FiltroQuantidadePrestacoes(FiltroQuantidadePrestacoes.QTD_FATURAS_PARCELADAS);
			filtro.adicionarParametro(new ComparacaoTexto(
					FiltroQuantidadePrestacoes.CONTRATO_PARCELAMENTO_RD_NUMERO, numeroContratoParcelamentoRD.toUpperCase()));
			parcelas = new ArrayList<QuantidadePrestacoes>(this.getFachada().pesquisar(filtro,QuantidadePrestacoes.class.getName()));
			
			sessao.setAttribute("contratoAntes",contratoRDAtualizar);
		}
		
		if(parcelas == null){
			parcelas = new ArrayList<QuantidadePrestacoes>();
		}
		if (contratoRDAtualizar == null) {
			contratoRDAtualizar = new ContratoParcelamentoRD();
		}
		
		Collections.sort(parcelas, new ComparatorParcela());
		sessao.setAttribute("parcelas", parcelas);
		sessao.setAttribute("contratoAtualizar", contratoRDAtualizar);
        
        
		return retorno;
	}
		
}
