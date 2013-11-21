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
package gcom.gui.relatorio.cobranca;

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioManterResolucaoDiretoria;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioResolucaoDiretoriaManterAction extends
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

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroResolucaoDiretoria filtroResolucaoDiretoria = (FiltroResolucaoDiretoria) sessao
				.getAttribute("filtroResolucaoDiretoria");

		// Inicio da parte que vai mandar os parametros para o relatório

		ResolucaoDiretoria resolucaoDiretoriaParametros = new ResolucaoDiretoria();

		String numero = "";

		String numeroPesquisar = (String) sessao.getAttribute("numero");

		if (numeroPesquisar != null && !numeroPesquisar.equals("")) {
			numero = numeroPesquisar;
		}

		String assunto = "";

		String assuntoPesquisar = (String) sessao.getAttribute("assunto");

		if (assuntoPesquisar != null && !assuntoPesquisar.equals("")) {
			assunto = assuntoPesquisar;
		}

		Date dataInicio = null;

		String dataInicioPesquisar = (String) sessao.getAttribute("dataInicio");

		if (dataInicioPesquisar != null && !dataInicioPesquisar.equals("")) {
			dataInicio = Util.converteStringParaDate(dataInicioPesquisar);
		}

		Date dataTermino = null;

		String dataTerminoPesquisar = (String) sessao.getAttribute("dataFim");

		if (dataTerminoPesquisar != null && !dataTerminoPesquisar.equals("")) {
			dataTermino = Util.converteStringParaDate(dataTerminoPesquisar);
		}

		// seta os parametros que serão mostrados no relatório
		resolucaoDiretoriaParametros.setNumeroResolucaoDiretoria(numero);
		resolucaoDiretoriaParametros.setDescricaoAssunto(assunto);
		resolucaoDiretoriaParametros.setDataVigenciaInicio(dataInicio);
		resolucaoDiretoriaParametros.setDataVigenciaFim(dataTermino);

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioManterResolucaoDiretoria relatorioManterResolucaoDiretoria = new RelatorioManterResolucaoDiretoria((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterResolucaoDiretoria.addParametro("filtroResolucaoDiretoria",
				filtroResolucaoDiretoria);
		relatorioManterResolucaoDiretoria.addParametro("resolucaoDiretoriaParametros",
				resolucaoDiretoriaParametros);
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterResolucaoDiretoria.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioManterResolucaoDiretoria, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
