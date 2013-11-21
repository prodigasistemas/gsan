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
package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.micromedicao.GerarDadosLeituraActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.micromedicao.GerarDadosLeituraHelper;
import gcom.relatorio.micromedicao.RelatorioGerarDadosLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioDadosLeituraAction extends
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

		GerarDadosLeituraActionForm form = (GerarDadosLeituraActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		GerarDadosLeituraHelper gerarDadosLeituraHelper = new GerarDadosLeituraHelper();
		
		if (form.getIdGrupoFaturamento() != null && !form.getIdGrupoFaturamento().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			gerarDadosLeituraHelper.setIdGrupoFaturamento(new Integer(form.getIdGrupoFaturamento()));
		}
		
		if (form.getIdRota() != null && !form.getIdRota().equals("")) {
			gerarDadosLeituraHelper.setIdRota(new Integer(form.getIdRota()));
			gerarDadosLeituraHelper.setCodigoRota(new Integer(form.getCodigoRota()));
		}
		
		if (form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().trim().equals("")) {
			
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer(form.getIdLocalidadeInicial()));
		
			if (localidade != null) {
				gerarDadosLeituraHelper.setIdLocalidadeInicial(localidade.getId());
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade Inicial");
			}
			
		}
		
		if (form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().trim().equals("")) {
			
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer(form.getIdLocalidadeFinal()));
			
			if (localidade != null) {
				gerarDadosLeituraHelper.setIdLocalidadeFinal(localidade.getId());
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade Final");
			}
			
		}
		
		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioGerarDadosLeitura relatorioGerarDadosLeitura = new RelatorioGerarDadosLeitura((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioGerarDadosLeitura.addParametro("gerarDadosLeituraHelper",
				gerarDadosLeituraHelper);
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioGerarDadosLeitura.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioGerarDadosLeitura, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
