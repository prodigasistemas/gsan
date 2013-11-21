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
package gcom.gui.cobranca;

import gcom.cobranca.DocumentosReceberFaixaDiasVencidos;
import gcom.cobranca.FiltroDocumentosReceberFaixaDiasVencidos;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Hugo Leonardo
 * 
 */
public class AtualizarFaixaDiasVencidosDocumentosReceberAction extends GcomAction {
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

		// Pega o action form
		AtualizarFaixaDiasVencidosDocumentosReceberActionForm form = (AtualizarFaixaDiasVencidosDocumentosReceberActionForm) actionForm;

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Recupera o ponto de coleta da sessão
		DocumentosReceberFaixaDiasVencidos documentosReceberFaixaDiasVencidos = 
				(DocumentosReceberFaixaDiasVencidos) sessao.getAttribute("documentosReceberFaixaDiasVencidos");

		Date timeStamp = (Date)sessao.getAttribute("timeStamp");
		
		
		// Atualiza a tabela auxiliar com os dados inseridos pelo usuário
		// A data de última alteração não é alterada, pois será usada na
		// verificação de atualização

		String descricaoFaixa = "";
		
		if (!form.getDescricaoFaixa().equals(documentosReceberFaixaDiasVencidos.getDescricaoFaixa())) {
			
			descricaoFaixa = form.getDescricaoFaixa();

			documentosReceberFaixaDiasVencidos.setDescricaoFaixa(descricaoFaixa);
		}
		
		Integer valorInicialFaixa = form.getValorInicialFaixa();
		
		if (!form.getValorInicialFaixa().equals(documentosReceberFaixaDiasVencidos.getValorInicialFaixa())) {
				
			documentosReceberFaixaDiasVencidos.setValorInicialFaixa(valorInicialFaixa);
		}
		
		Integer valorFinalFaixa = form.getValorFinalFaixa();
		if (!form.getValorFinalFaixa().equals(documentosReceberFaixaDiasVencidos.getValorFinalFaixa())) {
			
			documentosReceberFaixaDiasVencidos.setValorFinalFaixa(valorFinalFaixa);
			
			if (valorFinalFaixa != null					
					&& valorFinalFaixa != null) {
				if (valorInicialFaixa.compareTo(valorFinalFaixa) > 0) {
					throw new ActionServletException("atencao.valor_faixa_final_menor_valor_inicial_faixa");
				}
			}
		}
		
		if (form.getIndicadorUso() != null) {
			documentosReceberFaixaDiasVencidos.setIndicadorUso(new Short(form
					.getIndicadorUso()));
		}

		// Filtro da Descrição
		FiltroDocumentosReceberFaixaDiasVencidos filtroDescricao = 
			new FiltroDocumentosReceberFaixaDiasVencidos();

		filtroDescricao
				.adicionarParametro(new ParametroSimples(
						FiltroDocumentosReceberFaixaDiasVencidos.DESCRICAO_FAIXA,
						documentosReceberFaixaDiasVencidos.getDescricaoFaixa()));

		// Collection por descrição
		Collection colecaoDocumentosDescricao = fachada.pesquisar(
				filtroDescricao, DocumentosReceberFaixaDiasVencidos.class.getName());
		
		String auxDescricaoFaixa  = (String)sessao.getAttribute("descricaoFaixa");
		if(!auxDescricaoFaixa.equals(form.getDescricaoFaixa())){
			
			if (colecaoDocumentosDescricao != null && !colecaoDocumentosDescricao.isEmpty()) {
				DocumentosReceberFaixaDiasVencidos doc = (DocumentosReceberFaixaDiasVencidos) Util.retonarObjetoDeColecao(colecaoDocumentosDescricao);
				
				throw new ActionServletException(
						"atencao.verificar_descricao_faixa",doc.getDescricaoFaixa());
			}
		}
		
		Integer auxValorFaixaInicial  = (Integer)sessao.getAttribute("valorFaixaInicial");
		Integer auxValorFaixaFinal  = (Integer)sessao.getAttribute("valorFaixaFinal");
		
		// FS0003 - Verificar pré-existência de faixa contendo o valor
		if(!auxValorFaixaInicial.equals(form.getValorInicialFaixa())){
			fachada.verificarExistenciaFaixaInicial(valorInicialFaixa);
		}
		if(!auxValorFaixaFinal.equals(form.getValorFinalFaixa())){
			fachada.verificarExistenciaFaixaFinal(valorFinalFaixa);
		}
		
		// FS0002 - Atualização realizada por outro usuário.
		if(!timeStamp.equals(documentosReceberFaixaDiasVencidos.getUltimaAlteracao()) ){
			throw new ActionServletException("atencao.atualizacao_realizada_por_outro_usuario");
		}
		
		// Atualiza os dados
		fachada.atualizar(documentosReceberFaixaDiasVencidos);

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			montarPaginaSucesso(
					httpServletRequest,"Faixa atualizada com sucesso.",
					"Realizar outra Manutenção de Faixa de Dias Vencidos para Documentos a Receber."
							+ ((String) sessao.getAttribute("titulo")),"exibirManterFaixaDiasVencidosDocumentosReceberAction.do?menu=sim");

		}

		// devolve o mapeamento de retorno
		return retorno;
	}

}
