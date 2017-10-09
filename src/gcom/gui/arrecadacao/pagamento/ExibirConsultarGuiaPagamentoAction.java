package gcom.gui.arrecadacao.pagamento;

import java.util.Collection;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroGuiaPagamentoItem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarGuiaPagamentoAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarGuiaPagamento");

		HttpSession sessao = httpServletRequest.getSession(false);

		String guiaPagamentoId = httpServletRequest.getParameter("guiaPagamentoId");
		String guiaPagamentoHistoricoId = httpServletRequest.getParameter("guiaPagamentoHistoricoId");

		// Se chegar na funcionalidade sem o parâmetro indica situação de erro
		if ((guiaPagamentoId == null || guiaPagamentoId.trim().equals(""))
				&& (guiaPagamentoHistoricoId == null || guiaPagamentoHistoricoId.trim().equals(""))) {
			throw new ActionServletException("erro.sistema");

		}

		Fachada fachada = Fachada.getInstancia();
		
		if (guiaPagamentoHistoricoId != null){
			// GUIA PAGAMENTO HISTORICO
			GuiaPagamentoHistorico guiaPagamentoHistorico = this.obterGuiaPagamentoHistorico(guiaPagamentoHistoricoId, fachada);
			
			// Envia o objeto consultado para a página
			httpServletRequest.setAttribute("guiaPagamentoHistorico", guiaPagamentoHistorico);
			
			sessao.removeAttribute("colecaoGuiaDebitoTipoConsulta");
			
			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, guiaPagamentoHistorico.getId()));
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.setCampoOrderBy(new String[]{"guiaPagamentoGeral","debitoTipo"});
			Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItem = fachada.pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			
			if(colecaoGuiaPagamentoItem.isEmpty()){
				GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
				guiaPagamentoItem.setDebitoTipo(guiaPagamentoHistorico.getDebitoTipo());
				guiaPagamentoItem.setValorDebito(guiaPagamentoHistorico.getValorDebito());
				colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
			}
			sessao.setAttribute("colecaoGuiaDebitoTipoConsulta", colecaoGuiaPagamentoItem);
			
			
		}else{	
			
			// GUIA PAGAMENTO
			GuiaPagamento guiaPagamento = this.obterGuiaPagamento(guiaPagamentoId, fachada);
//			 Consulta do GuiaPagamento
			
			// Envia o objeto consultado para a página
			httpServletRequest.setAttribute("guiaPagamento", guiaPagamento);
			
			sessao.removeAttribute("colecaoGuiaDebitoTipoConsulta");
			
			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, guiaPagamento.getId()));
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.setCampoOrderBy(new String[]{"guiaPagamentoGeral","debitoTipo"});
			
			
			Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItem = fachada.pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			
			if(colecaoGuiaPagamentoItem.isEmpty()){
				GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
				guiaPagamentoItem.setDebitoTipo(guiaPagamento.getDebitoTipo());
				guiaPagamentoItem.setValorDebito(guiaPagamento.getValorDebito());
				colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
			}
			sessao.setAttribute("colecaoGuiaDebitoTipoConsulta", colecaoGuiaPagamentoItem);
			
		}
		
		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest.getParameter("caminhoRetornoTelaConsultaGuiaPagamento") != null) {
			sessao.setAttribute("caminhoRetornoTelaConsultaGuiaPagamento",httpServletRequest.getParameter("caminhoRetornoTelaConsultaGuiaPagamento"));
		}

		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	private GuiaPagamento obterGuiaPagamento(String idGuia, Fachada fachada) {
		FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, idGuia));

		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade(FiltroGuiaPagamento.GUIA_PAGAMENTO_GERAL);
		
		// Para a exibição do endereço do imóvel
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("origem.guiaPagamento.imovel");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("usuario");

		return (GuiaPagamento)Util.retonarObjetoDeColecao(fachada.pesquisar(filtroGuiaPagamento,GuiaPagamento.class.getName()));
	}
	
	@SuppressWarnings("unchecked")
	private GuiaPagamentoHistorico obterGuiaPagamentoHistorico(String guiaPagamentoHistoricoId, Fachada fachada) {
		FiltroGuiaPagamentoHistorico filtroGuiaPagamentoHistorico = new FiltroGuiaPagamentoHistorico();
		filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(
				FiltroGuiaPagamentoHistorico.ID, guiaPagamentoHistoricoId));

		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoByDcstIdatual");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroGuiaPagamento.GUIA_PAGAMENTO_GERAL);
		
		// Para a exibição do endereço do imóvel
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("origem.guiaPagamento.imovel");
		filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");

		return (GuiaPagamentoHistorico)Util.retonarObjetoDeColecao
			(fachada.pesquisar(filtroGuiaPagamentoHistorico,GuiaPagamentoHistorico.class.getName()));

	}
}
