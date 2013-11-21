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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
 *  
 * @author Hugo Leonardo
 * @created 08/06/2010
 *  
 * 
 */
public class LiberarArquivoTextoLeituraDivisaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		Integer situacaoNova = null;	
		String descricaoSituacaoNova = "";

		HttpSession sessao = this.getSessao(httpServletRequest);     
	
		ConsultarArquivoTextoLeituraDivisaoPopupActionForm consultarArquivoTextoLeituraDivisaoPopupActionForm = 
			(ConsultarArquivoTextoLeituraDivisaoPopupActionForm) actionForm;

		// Saber se vai liberar ou nao liberar
		String liberar = (String) httpServletRequest.getParameter("liberar");
		if (consultarArquivoTextoLeituraDivisaoPopupActionForm.getIdsRegistros() != null) {
			Vector<Integer> v = new Vector<Integer>();
			for (int i = 0; i < consultarArquivoTextoLeituraDivisaoPopupActionForm
					.getIdsRegistros().length; i++) {
				v.add(new Integer(consultarArquivoTextoLeituraDivisaoPopupActionForm
						.getIdsRegistros()[i]));

			}
			
			if(liberar == null){
				liberar = (String) sessao.getAttribute("liberar");
			}
			
			if ( liberar.equals( SituacaoTransmissaoLeitura.LIBERADO+ "" ) ){
				situacaoNova = SituacaoTransmissaoLeitura.LIBERADO;
				descricaoSituacaoNova = "LIBERADO";				
			} else if ( liberar.equals( SituacaoTransmissaoLeitura.EM_CAMPO+ "" ) ){
				situacaoNova = SituacaoTransmissaoLeitura.EM_CAMPO;
				descricaoSituacaoNova = "EM CAMPO";
			} else if ( liberar.equals( SituacaoTransmissaoLeitura.DISPONIVEL+"" ) ){
				situacaoNova = SituacaoTransmissaoLeitura.DISPONIVEL;
				descricaoSituacaoNova = "DISPONÍVEL";				
			} else if ( liberar.equals( SituacaoTransmissaoLeitura.FINALIZADO_USUARIO+ "" ) ){
				situacaoNova = SituacaoTransmissaoLeitura.FINALIZADO_USUARIO;
				descricaoSituacaoNova = "FINALIZADO PELO USUÁRIO";				
			}			
			
			fachada.atualizarListaArquivoTextoDivisao(v, situacaoNova);
		}
		
		montarPaginaSucesso(httpServletRequest,
				"Arquivo Texto para Leitura Divisão Alterado para " + descricaoSituacaoNova.toLowerCase() + " com sucesso.",
				"Realizar outra Manutenção de Arquivo Texto para Leitura Divisão",
				"exibirConsultarArquivoTextoLeituraDivisaoPopupAction.do");

		consultarArquivoTextoLeituraDivisaoPopupActionForm.setIdsRegistros(null);

		return retorno;
	}

}
