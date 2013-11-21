package gcom.gui.portal;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela exibição dos documentos do parcelamento
 * da loja virtual (termo de pagamento e a guia de pagamento).
 *  
 * @author Diogo Peixoto
 * @since 08/07/2011
 * 
 */
public class GerarRelatorioDocumentosParcelamentoPortalAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, "INTERNET"));
		Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName()));
		
		
		Collection<ContaValoresHelper> colecaoFaturasEmAberto = null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento = null;

		if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoFaturasEmAberto = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
			colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>) sessao.getAttribute("colecaoGuiaPagamentoNegociacao");
		} else {
			colecaoFaturasEmAberto = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValoresImovel");
			colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>) sessao.getAttribute("colecaoGuiaPagamentoValoresImovel");
		}

		// Coleção dos Débitos a Cobrar do Parcelamento
		Collection<DebitoACobrar> colecaoServicosACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
		Collection<CreditoARealizar> colecaoCreditoARealizar = (Collection<CreditoARealizar>) sessao.getAttribute("colecaoCreditoARealizar");
		String idParcelamento = (String) sessao.getAttribute("idParcelamento");

		// Pesquisa a unidade do usuário logado
		UnidadeOrganizacional unidadeUsuario = fachada.pesquisarUnidadeUsuario(usuario.getId());

		//Variáveis da guia de pagamento
		String idGuiaPagamento = (String) sessao.getAttribute("idGuiaPagamento");
		String[] ids = {idGuiaPagamento};
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		TarefaRelatorio relatorioParcelamento = (TarefaRelatorio) fachada.gerarRelatorioDocumentosParcelamentoCobrancaPortal(usuario, idParcelamento, 
				unidadeUsuario, colecaoFaturasEmAberto, colecaoGuiasPagamento, colecaoCreditoARealizar, colecaoServicosACobrar );

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		relatorioParcelamento.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioParcelamento.addParametro("ids",ids);
		retorno = processarExibicaoRelatorio(relatorioParcelamento, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

		/*
		 * Atributo que vai ser utilizado na página de efetuar parcelamentos.
		 * Caso os documentos e o parcelamento já tenham sido gerados/concluído,
		 * quando o usuário apertar pra confirmar ele vai redirecionar a página
		 * para a tela inicial de serviços.
		 */
		sessao.setAttribute("parcelamentoEfetuado", "SIM");
		return retorno;
	}
}