package gcom.gui.relatorio.cobranca.parcelamento;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
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
 * @author Rafael Correa
 * @created 
 */
public class GerarRelatorioParcelamentoAction extends
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

		//ConsultarRegistroAtendimentoActionForm consultarRegistroAtendimentoActionForm = (ConsultarRegistroAtendimentoActionForm) actionForm;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Collection colecaoFaturasEmAberto = null;
		Collection colecaoGuiasPagamento = null;

		if (sessao.getAttribute("colecaoContaValores") != null) {

			colecaoFaturasEmAberto = (Collection) sessao
					.getAttribute("colecaoContaValores");
			colecaoGuiasPagamento = (Collection) sessao
					.getAttribute("colecaoGuiaPagamentoValores");

		} else {
			colecaoFaturasEmAberto = (Collection) sessao
					.getAttribute("colecaoContaValoresImovel");
			colecaoGuiasPagamento = (Collection) sessao
					.getAttribute("colecaoGuiaPagamentoValoresImovel");
		}

		// Coleção dos Débitos a Cobrar do Parcelamento
		Collection colecaoServicosACobrar = (Collection) sessao
				.getAttribute("colecaoDebitoACobrar");

		Collection colecaoCreditoARealizar = (Collection) sessao
				.getAttribute("colecaoCreditoARealizar");

		String idParcelamento = (String) sessao.getAttribute("idParcelamento");

		// Pesquisa a unidade do usuário logado
		UnidadeOrganizacional unidadeUsuario = fachada
				.pesquisarUnidadeUsuario(usuario.getId());

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		TarefaRelatorio relatorioParcelamento = ( TarefaRelatorio ) fachada.gerarRelatorioParcelamentoCobranca(
				usuario,
				idParcelamento,
				unidadeUsuario,
				colecaoFaturasEmAberto,
				colecaoGuiasPagamento,
				colecaoCreditoARealizar,
				colecaoServicosACobrar );
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioParcelamento.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioParcelamento,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
