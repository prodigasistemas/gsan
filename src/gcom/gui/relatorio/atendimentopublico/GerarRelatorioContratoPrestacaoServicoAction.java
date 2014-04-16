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
