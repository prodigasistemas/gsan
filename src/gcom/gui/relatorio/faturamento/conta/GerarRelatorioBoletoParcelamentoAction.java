package gcom.gui.relatorio.faturamento.conta;

import gcom.api.GsanApi;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.bean.ContaSegundaViaHelper;
import gcom.faturamento.bean.EntradaParcelamentoHelper;
import gcom.faturamento.bo.EntradaParcelamentoBO;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioBoletoParcelamentoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		SistemaParametro parametros = getFachada().pesquisarParametrosDoSistema();

		HttpSession sessao = request.getSession(false);

		Integer idParcelamento = null;
		Integer idGuiaPagamento = null;

		if (sessao.getAttribute("idParcelamento") != null && !sessao.getAttribute("idParcelamento").equals("")) {
			idParcelamento = new Integer("" + sessao.getAttribute("idParcelamento"));
		}  
		
		if (sessao.getAttribute("idGuiaPagamento") != null && !sessao.getAttribute("idGuiaPagamento").equals("")) {
			idGuiaPagamento = new Integer("" + sessao.getAttribute("idGuiaPagamento"));
		}

		Imovel imovel = null;
		String situacaoConta = "";

	//	getFachada().verificarClienteSemDocumento(imovel.getId(), (Usuario) (request.getSession(false)).getAttribute("usuarioLogado"));

		try {
			EntradaParcelamentoBO bo = new EntradaParcelamentoBO(idParcelamento);
			EntradaParcelamentoHelper helper = bo.criar(imovel, (Usuario) sessao.getAttribute("usuarioLogado"), situacaoConta);

			if (helper != null) {
				String url = getFachada().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_ENTRADA_PARCELAMENTO.toString());

				GsanApi api = new GsanApi(url);
				api.invoke(helper);
				api.download(helper.getNomeArquivo(), response);
			} else {
				throw new ActionServletException("atencao.entrada_parcelamento_sem_dados");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_entrada_parcelamento");
		}

		return null;
	}
	
}
