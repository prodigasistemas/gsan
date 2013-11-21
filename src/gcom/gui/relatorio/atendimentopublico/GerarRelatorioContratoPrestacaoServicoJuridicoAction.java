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

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.GerarContratoPrestacaoServicoJuridicoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioContratoPrestacaoServico;
import gcom.relatorio.atendimentopublico.RelatorioContratoPrestacaoServicoJuridico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
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
 * @author Flávio Cordeiro
 * @created 26 de Junho de 2007
 */
public class GerarRelatorioContratoPrestacaoServicoJuridicoAction extends
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
		HttpSession sessao = httpServletRequest.getSession(false);
		
		GerarContratoPrestacaoServicoJuridicoActionForm gerarContratoPrestacaoServicoActionForm = (GerarContratoPrestacaoServicoJuridicoActionForm) actionForm;

		// Inicio da parte que vai mandar os parametros para o relatório
		String idImovel = gerarContratoPrestacaoServicoActionForm.getIdImovel();
		String idCliente = gerarContratoPrestacaoServicoActionForm.getIdCliente();
		
		if (idImovel != null && !idImovel.trim().equals("")) {

			Imovel imovel = fachada.pesquisarImovel(new Integer(idImovel));
			
			if (imovel == null) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Imóvel");
			}
			
		}
		
		if (idCliente != null && !idCliente.trim().equals("")) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,new Integer(idCliente)));
			Collection clientes = fachada.pesquisarCliente(filtroCliente);
			if(clientes==null ||clientes.isEmpty()){
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Cliente");
			}
		}
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
		
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
		Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		if(!colecaoClienteImovel.isEmpty()){
			ClienteImovel clienteImovel = (ClienteImovel)colecaoClienteImovel.iterator().next();
			if(clienteImovel.getCliente() != null && clienteImovel.getCliente().getCnpj() == null 
					&& (idCliente == null || idCliente.trim().equals(""))){

					RelatorioContratoPrestacaoServico relatorioContratoPrestacaoServico = new RelatorioContratoPrestacaoServico((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
					relatorioContratoPrestacaoServico.addParametro("idImovel", new Integer(idImovel));
					
//					 Fim da parte que vai mandar os parametros para o relatório
					String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
					if (tipoRelatorio == null) {
						tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
					}
					relatorioContratoPrestacaoServico.addParametro("tipoFormatoRelatorio", Integer
							.parseInt(tipoRelatorio));
					/*ActionMapping actionMapping2 = actionMapping;
					actionMapping2.setForward("gerarRelatorioContratoPrestacaoServicoAction");
					actionMapping2.setType("gcom.gui.relatorio.atendimentopublico.GerarRelatorioContratoPrestacaoServicoJuridicoAction");
					//actionMapping.set
*/					sessao.setAttribute("relatorioContratoPrestacaoServico", relatorioContratoPrestacaoServico);
					retorno = actionMapping.findForward("gerarRelatorioContratoPrestacao"); 
						
						/*processarExibicaoRelatorio(relatorioContratoPrestacaoServico, tipoRelatorio,
							httpServletRequest, httpServletResponse, actionMapping);*/
			}else{
				RelatorioContratoPrestacaoServicoJuridico relatorioContratoPrestacaoServico = new RelatorioContratoPrestacaoServicoJuridico((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				relatorioContratoPrestacaoServico.addParametro("idImovel", new Integer(idImovel));
				relatorioContratoPrestacaoServico.addParametro("idCliente", new Integer(idCliente));
				
//				 Fim da parte que vai mandar os parametros para o relatório
				String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
				if (tipoRelatorio == null) {
					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				}
				relatorioContratoPrestacaoServico.addParametro("tipoFormatoRelatorio", Integer
						.parseInt(tipoRelatorio));
				retorno = processarExibicaoRelatorio(relatorioContratoPrestacaoServico, tipoRelatorio,
						httpServletRequest, httpServletResponse, actionMapping);
			}
			
		}else{
			RelatorioContratoPrestacaoServicoJuridico relatorioContratoPrestacaoServico = new RelatorioContratoPrestacaoServicoJuridico((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			relatorioContratoPrestacaoServico.addParametro("idImovel", new Integer(idImovel));
			relatorioContratoPrestacaoServico.addParametro("idCliente", new Integer(idCliente));
			
//			 Fim da parte que vai mandar os parametros para o relatório
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			relatorioContratoPrestacaoServico.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			retorno = processarExibicaoRelatorio(relatorioContratoPrestacaoServico, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);
		}
		
		

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
