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
package gcom.gui.relatorio.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cobranca.spcserasa.FiltrarNegativadorRegistroTipoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.spcserasa.RelatorioManterNegativadorRegistroTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de Tipo do Registro do Negativador manter
 * 
 * @author Yara Taciane
 * @created 26 de Fevereiro de 2008
 */
public class GerarRelatorioNegativadorRegistroTipoManterAction extends
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

		FiltrarNegativadorRegistroTipoActionForm form = (FiltrarNegativadorRegistroTipoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroNegativadorRegistroTipo filtro = (FiltroNegativadorRegistroTipo) sessao.getAttribute("filtroNegativadorRegistroTipo");

		// Inicio da parte que vai mandar os parametros para o relatório

		NegativadorRegistroTipo parametros = new NegativadorRegistroTipo();

		String idNegativador = (String) form.getIdNegativador();
		String descricao = (String) form.getDescricaoRegistroTipo();
		String codigoRegistro = (String) form.getCodigoRegistro();

		Negativador negativador = new Negativador();
		Cliente cliente = new Cliente();

		if (idNegativador != null && !idNegativador.equals("")) {
				FiltroNegativador filtroNegativador = new FiltroNegativador();

				filtroNegativador.adicionarParametro(new ParametroSimples(
						FiltroNegativador.ID, idNegativador));
				filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");

				Collection collNegativador = fachada.pesquisar(filtroNegativador,
						Negativador.class.getName());

				if (collNegativador != null && !collNegativador.isEmpty()) {
					// A Negativador foi encontrado
					Iterator it = collNegativador.iterator();

					Negativador negativadorPesquisa = (Negativador) it.next();

					negativador.setId(negativadorPesquisa.getId());
					
					cliente.setId(negativadorPesquisa.getCliente().getId());
					cliente.setNome(negativadorPesquisa.getCliente().getNome());
					
					negativador.setCliente(cliente);

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Negativador");
				}
			

		}

		
		// seta os parametros que serão mostrados no relatório

		negativador.setCliente(cliente);
		parametros.setNegativador(negativador);
		parametros.setDescricaoRegistroTipo(descricao);
		parametros.setCodigoRegistro(codigoRegistro);
		// Fim da parte que vai mandar os parametros para o relatório

			// cria uma instância da classe do relatório
			RelatorioManterNegativadorRegistroTipo relatorioManterNegativadorRegistroTipo = new RelatorioManterNegativadorRegistroTipo(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorioManterNegativadorRegistroTipo.addParametro("filtroNegativadorRegistroTipo", filtro);
			relatorioManterNegativadorRegistroTipo.addParametro("negativadorRegistroTipoParametros",parametros);

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioManterNegativadorRegistroTipo.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorioManterNegativadorRegistroTipo,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}



}
