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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
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
 * @author Rômulo Aurélio
 * @date 21/08/2006
 */
public class ConsultarProgramacaoAbastecimentoManutencaoAction extends
		GcomAction {
	/**
	 * Este caso de uso permite a programaçao de abastecimento e manutencao de
	 * uma determinada área de bairro
	 * 
	 * [UC0440] Consultar Programação de Abastecimento e Manutenção
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 21/08/2006
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
				.findForward("exibirResultadoConsultarProgramacaoAbastecimentoManutencaoAction");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarProgramacaoAbastecimentoManutencaoActionForm consultarProgramacaoAbastecimentoManutencaoActionForm = (ConsultarProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		String idMunicipio = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getMunicipio();

		sessao.setAttribute("idMunicipio", idMunicipio);

		String idBairro = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getBairro();

		sessao.setAttribute("idBairro", idBairro);

		String areaBairro = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getAreaBairro();

		sessao.setAttribute("areaBairro", areaBairro);

		String mesAnoReferencia = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getMesAnoReferencia();

		Collection colecaoProgramacaoAbastecimento = fachada
				.consultarProgramacaoAbastecimento(idMunicipio, idBairro,
						areaBairro, mesAnoReferencia);

		// Seleciona todas as programações de abastecimento de acordo da área de
		// bairro e o mês e o ano de referencia informado. Ordenando de forma
		// crescente por Data de Inicio de Abastecimento e Hora de Inicio de
		// abastecimento
		Collection colecaoProgramacaoManutencao = fachada
				.consultarProgramacaoManutencao(idMunicipio, idBairro,
						areaBairro, mesAnoReferencia);

		if ((colecaoProgramacaoAbastecimento == null || colecaoProgramacaoAbastecimento
				.isEmpty())
				&& ((colecaoProgramacaoManutencao == null || colecaoProgramacaoManutencao
						.isEmpty()))) {
			throw new ActionServletException(
					"atencao.abastecimento_manutencao_sem_registro");

		}

		// retorna ano e mes

		String ano = null;

		String mes = null;

		if (mesAnoReferencia == null || mesAnoReferencia.equals("")) {

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);
			sessao.setAttribute("ano", ano);
			sessao.setAttribute("mes", mes);

		} else {

			boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);
			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			mes = mesAnoReferencia.substring(0, 2);
			ano = mesAnoReferencia.substring(3, 7);
			sessao.setAttribute("ano", ano);
			sessao.setAttribute("mes", mes);
		}

		return retorno;
	}
}
