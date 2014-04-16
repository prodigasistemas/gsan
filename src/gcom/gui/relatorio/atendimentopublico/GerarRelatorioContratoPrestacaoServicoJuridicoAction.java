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
