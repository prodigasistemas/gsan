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
package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.GerarContratoPrestacaoServicoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioContratoPrestacaoServico;
import gcom.relatorio.atendimentopublico.RelatorioContratoPrestacaoServicoJuridico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

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
public class GerarRelatorioContratoPrestacaoServicoAction extends
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

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		// HttpSession sessao = httpServletRequest.getSession(false);

		GerarContratoPrestacaoServicoActionForm gerarContratoPrestacaoServicoActionForm = (GerarContratoPrestacaoServicoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		// Inicio da parte que vai mandar os parametros para o relatório
		String idImovel = gerarContratoPrestacaoServicoActionForm.getIdImovel();

		String idCliente = gerarContratoPrestacaoServicoActionForm
				.getIdCliente();

		if (idImovel != null && !idImovel.trim().equals("")) {

			Imovel imovel = fachada.pesquisarImovel(new Integer(idImovel));

			if (imovel == null) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Imóvel");
			}

		}

		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				idCliente));
		
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);

		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoCliente = fachada.pesquisar(filtroCliente,
				Cliente.class.getName());

		if (!colecaoCliente.isEmpty()) {
			
			Cliente cliente = (Cliente) colecaoCliente.iterator().next();
			
			if (cliente != null && cliente.getClienteTipo() != null && cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().intValue() == ClienteTipo.INDICADOR_PESSOA_FISICA.intValue()) {

				RelatorioContratoPrestacaoServico relatorioContratoPrestacaoServico = new RelatorioContratoPrestacaoServico(
						(Usuario) (httpServletRequest.getSession(false))
								.getAttribute("usuarioLogado"));
				relatorioContratoPrestacaoServico.addParametro("idImovel",
						new Integer(idImovel));
				relatorioContratoPrestacaoServico.addParametro("idCliente",
						new Integer(idCliente));

				// Fim da parte que vai mandar os parametros para o relatório
				String tipoRelatorio = httpServletRequest
						.getParameter("tipoRelatorio");
				if (tipoRelatorio == null) {
					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				}
				relatorioContratoPrestacaoServico
						.addParametro("tipoFormatoRelatorio", Integer
								.parseInt(tipoRelatorio));

				sessao.setAttribute("relatorioContratoPrestacaoServico",
						relatorioContratoPrestacaoServico);
				retorno = actionMapping
						.findForward("gerarRelatorioContratoPrestacao");

			} else {
				
				RelatorioContratoPrestacaoServicoJuridico relatorioContratoPrestacaoServico = new RelatorioContratoPrestacaoServicoJuridico(
						(Usuario) (httpServletRequest.getSession(false))
								.getAttribute("usuarioLogado"));
				relatorioContratoPrestacaoServico.addParametro("idImovel",
						new Integer(idImovel));
				relatorioContratoPrestacaoServico.addParametro("idCliente",
						new Integer(idCliente));

				// Fim da parte que vai mandar os parametros para o relatório
				String tipoRelatorio = httpServletRequest
						.getParameter("tipoRelatorio");
				if (tipoRelatorio == null) {
					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				}
				relatorioContratoPrestacaoServico
						.addParametro("tipoFormatoRelatorio", Integer
								.parseInt(tipoRelatorio));
				retorno = processarExibicaoRelatorio(
						relatorioContratoPrestacaoServico, tipoRelatorio,
						httpServletRequest, httpServletResponse, actionMapping);
			}
		}

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioContratoPrestacaoServico relatorioContratoPrestacaoServico = new RelatorioContratoPrestacaoServico(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		relatorioContratoPrestacaoServico.addParametro("idImovel", new Integer(
				idImovel));
		relatorioContratoPrestacaoServico.addParametro("idCliente",
				new Integer(idCliente));
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioContratoPrestacaoServico.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioContratoPrestacaoServico,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
