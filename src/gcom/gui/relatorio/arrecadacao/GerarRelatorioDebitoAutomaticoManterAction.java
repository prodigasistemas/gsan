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
package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomatico;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.arrecadacao.FiltrarDebitoAutomaticoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioManterDebitoAutomatico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class GerarRelatorioDebitoAutomaticoManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarDebitoAutomaticoActionForm filtrarDebitoAutomaticoActionForm = (FiltrarDebitoAutomaticoActionForm) actionForm;

		FiltroDebitoAutomatico filtroDebitoAutomatico = (FiltroDebitoAutomatico) sessao.getAttribute("filtroDebitoAutomatico");

		DebitoAutomatico debitoAutomaticoParametros = new DebitoAutomatico();
		
		// pega os parâmetros informados na tela de consulta de débitos automáticos
		
		if(Util.verificarNaoVazio(filtrarDebitoAutomaticoActionForm.getMatricula())){
			debitoAutomaticoParametros.setImovel(new Imovel(Integer.parseInt(filtrarDebitoAutomaticoActionForm.getMatricula().trim())));
		}

		if(Util.verificarNaoVazio(filtrarDebitoAutomaticoActionForm.getAgenciaCodigo())){

			FiltroAgencia filtroAgencia = new FiltroAgencia();
			filtroAgencia.adicionarParametro(new ParametroSimples(FiltroAgencia.CODIGO_AGENCIA,filtrarDebitoAutomaticoActionForm.getAgenciaCodigo().trim()));
			filtroAgencia.adicionarParametro(new ParametroSimples(FiltroAgencia.BANCO_ID,Integer.parseInt(filtrarDebitoAutomaticoActionForm.getBancoID().trim())));

			Agencia agencia = (Agencia) Fachada.getInstancia().pesquisar(filtroAgencia,Agencia.class.getName()).iterator().next();
			debitoAutomaticoParametros.setAgencia(agencia);
		}

		if(Util.verificarNaoVazio(filtrarDebitoAutomaticoActionForm.getBancoID())){

			if(debitoAutomaticoParametros.getAgencia() == null){
				debitoAutomaticoParametros.setAgencia(new Agencia());				
			}

			FiltroBanco filtroBanco = new FiltroBanco();
			filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID,Integer.parseInt(filtrarDebitoAutomaticoActionForm.getBancoID().trim())));

			Banco banco = (Banco) Fachada.getInstancia().pesquisar(filtroBanco,Banco.class.getName()).iterator().next();
			debitoAutomaticoParametros.getAgencia().setBanco(banco);
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioManterDebitoAutomatico relatorioManterDebitoAutomatico = new RelatorioManterDebitoAutomatico((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterDebitoAutomatico.addParametro("filtroDebitoAutomatico",filtroDebitoAutomatico);
		relatorioManterDebitoAutomatico.addParametro("debitoAutomaticoParametros",debitoAutomaticoParametros);
		relatorioManterDebitoAutomatico.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(relatorioManterDebitoAutomatico,
					tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

}