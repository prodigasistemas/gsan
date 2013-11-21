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
package gcom.gui.relatorio.faturamento;

import java.util.ArrayList;
import java.util.Collection;

import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioProtocoloEntregaFatura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @author Fernanda Paiva
 * @version 1.0
 */

public class GerarRelatorioProtocoloEntregaFaturaAction extends
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
		
		GerarRelatorioFaturasAgrupadasActionForm gerarRelatorioFaturasAgrupadasActionForm = (GerarRelatorioFaturasAgrupadasActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		Integer anoMes = null;
		
		if (gerarRelatorioFaturasAgrupadasActionForm.getMesAno() != null && !gerarRelatorioFaturasAgrupadasActionForm.getMesAno().trim().equals("")) {
			anoMes = Util.formatarMesAnoComBarraParaAnoMes(gerarRelatorioFaturasAgrupadasActionForm.getMesAno());
		}
		
		Cliente cliente = new Cliente();
		
		if (gerarRelatorioFaturasAgrupadasActionForm.getIdCliente() != null && !gerarRelatorioFaturasAgrupadasActionForm.getIdCliente().trim().equals("")) {
			cliente = fachada.pesquisarClienteDigitado(new Integer(gerarRelatorioFaturasAgrupadasActionForm.getIdCliente()));
			
			if (cliente != null) {
				cliente.setCliente(null);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Cliente");
			}
		}
		
		Collection<Integer> idsTodosClientes = new ArrayList<Integer>();
		
		if (gerarRelatorioFaturasAgrupadasActionForm.getIdClienteSuperior() != null && !gerarRelatorioFaturasAgrupadasActionForm.getIdClienteSuperior().trim().equals("")) {
		
			Integer idClienteInformado = new Integer(gerarRelatorioFaturasAgrupadasActionForm.getIdClienteSuperior());
			
			Collection<Integer> idsClientes = fachada.pesquisarClientesAssociadosResponsavel(idClienteInformado);
			idsClientes.add(idClienteInformado);
		
			Collection<Integer> idsClientesAdicionados = new ArrayList<Integer>();
		
			while (idsClientes != null && !idsClientes.isEmpty()) {

				idsClientesAdicionados = new ArrayList<Integer>();

				for (Integer idCliente : idsClientes) {

					if (idsTodosClientes != null && !idsTodosClientes.contains(idCliente)) {

						Collection<Integer> idsClientesNovos = fachada
								.pesquisarClientesAssociadosResponsavel(idCliente);

						idsClientesAdicionados.addAll(idsClientesNovos);
						idsTodosClientes.add(idCliente);
					}
				}
			
				idsClientes = idsClientesAdicionados;
			
			}
		}
		
		if (gerarRelatorioFaturasAgrupadasActionForm.getIdEsferaPoder() != null
				&& !gerarRelatorioFaturasAgrupadasActionForm.getIdEsferaPoder()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			String[] clientesSelecionados = gerarRelatorioFaturasAgrupadasActionForm.getIdsClientesAssociados();
			
			for (int i = 0; i < clientesSelecionados.length; i++) {
				String idCliente = clientesSelecionados[i];
				
				if (idCliente != null && !idCliente.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					idsTodosClientes.add(new Integer(idCliente));
				}
			}
			
		}

		// cria uma instância da classe do relatório
		RelatorioProtocoloEntregaFatura relatorioProtocoloEntregaFatura = new RelatorioProtocoloEntregaFatura(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		relatorioProtocoloEntregaFatura.addParametro("anoMes", anoMes);
		relatorioProtocoloEntregaFatura.addParametro("cliente", cliente);
		relatorioProtocoloEntregaFatura.addParametro("idsClientes", idsTodosClientes);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioProtocoloEntregaFatura.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioProtocoloEntregaFatura,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}

}