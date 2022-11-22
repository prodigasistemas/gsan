package gcom.gui.arrecadacao.pagamento;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.emissaoboleto.EmissaoAPIBB;
import gcom.cobranca.parcelamento.emissaoboleto.EmissaoECommerceBB;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroGuiaPagamentoItem;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class ExibirConsultarGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirConsultarGuiaPagamento");
		HttpSession sessao = request.getSession(false);

		SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
		
		String guiaId = request.getParameter("guiaPagamentoId");
		String guiaHistoricoId = request.getParameter("guiaPagamentoHistoricoId");
		if ((guiaId == null || guiaId.trim().equals("")) && (guiaHistoricoId == null || guiaHistoricoId.trim().equals(""))) {
			throw new ActionServletException("erro.sistema");
		}
		
		String boleto = null;
		
		if (guiaHistoricoId != null) {
			GuiaPagamentoHistorico guia = pesquisarGuiaHistorico(request, sessao, guiaHistoricoId);
			pesquisarItens(sessao, guia.getId(), guia.getDebitoTipo(), guia.getValorDebito());
		} else {
			GuiaPagamento guia = pesquisarGuia(request, sessao, guiaId);
			pesquisarItens(sessao, guia.getId(), guia.getDebitoTipo(), guia.getValorDebito());
			request.setAttribute("geracaoBoletoBB", sistemaParametro.getIndicadorGeracaoBoletoBB());
			
			boleto = new EmissaoAPIBB(
					new EmissaoECommerceBB(null))
					.emitirBoleto(guia.getParcelamento().getId(), guia.getImovel().getId(), Fachada.getInstancia(), false);
			
			if (boleto.contains("gerarRelatorioBoletoParcelamentoAction")) {
				request.setAttribute("boletoParcelamento", guiaId);
			} else {
				request.setAttribute("linkBoletoBB", boleto);
			}
		}

		if (request.getParameter("caminhoRetornoTelaConsultaGuiaPagamento") != null) {
			sessao.setAttribute("caminhoRetornoTelaConsultaGuiaPagamento", request.getParameter("caminhoRetornoTelaConsultaGuiaPagamento"));
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarItens(HttpSession sessao, Integer id, DebitoTipo debitoTipo, BigDecimal valor) {
		FiltroGuiaPagamentoItem filtro = new FiltroGuiaPagamentoItem();
		filtro.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, id));
		filtro.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtro.setCampoOrderBy(new String[] { "guiaPagamentoGeral", "debitoTipo" });

		Collection<GuiaPagamentoItem> colecao = getFachada().pesquisar(filtro, GuiaPagamentoItem.class.getName());

		if (colecao.isEmpty()) {
			GuiaPagamentoItem item = new GuiaPagamentoItem();
			item.setDebitoTipo(debitoTipo);
			item.setValorDebito(valor);
			colecao.add(item);
		}
		
		sessao.setAttribute("colecaoGuiaDebitoTipoConsulta", colecao);
	}

	@SuppressWarnings("unchecked")
	private GuiaPagamento pesquisarGuia(HttpServletRequest request, HttpSession sessao, String id) {
		Filtro filtro = new FiltroGuiaPagamento();
		filtro.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, id));
		filtro.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtro.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
		filtro.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtro.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("origem.guiaPagamento.imovel");
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamento.imovel");
		filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamento.cliente");

		GuiaPagamento guia = (GuiaPagamento) Util.retonarObjetoDeColecao(getFachada().pesquisar(filtro, GuiaPagamento.class.getName()));

		request.setAttribute("guiaPagamento", guia);
		sessao.removeAttribute("colecaoGuiaDebitoTipoConsulta");
		return guia;
	}

	@SuppressWarnings("unchecked")
	private GuiaPagamentoHistorico pesquisarGuiaHistorico(HttpServletRequest request, HttpSession sessao, String id) {
		Filtro filtro = new FiltroGuiaPagamentoHistorico();
		filtro.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.ID, id));
		filtro.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtro.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoByDcstIdatual");
		filtro.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtro.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("origem.guiaPagamento.imovel");
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamento.imovel");
		filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamento.cliente");

		GuiaPagamentoHistorico guia = (GuiaPagamentoHistorico) Util.retonarObjetoDeColecao(getFachada().pesquisar(filtro, GuiaPagamentoHistorico.class.getName()));

		request.setAttribute("guiaPagamentoHistorico", guia);
		sessao.removeAttribute("colecaoGuiaDebitoTipoConsulta");

		return guia;
	}
	
}
