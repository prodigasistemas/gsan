package gcom.gui.relatorio.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroGuiaPagamentoItem;
import gcom.faturamento.bo.ContaSegundaViaBO;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.cadastro.imovel.CategoriaActionForm;
import gcom.gui.faturamento.ManterGuiaPagamentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.pagamento.RelatorioEmitirGuiaPagamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioEmitirGuiaPagamentoAction extends ExibidorProcessamentoTarefaRelatorio {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = request.getSession(false);

		String[] ids = null;

		if (actionForm instanceof ManterGuiaPagamentoActionForm) {
			ManterGuiaPagamentoActionForm manterGuiaPagamentoActionForm = (ManterGuiaPagamentoActionForm) actionForm;
			ids = manterGuiaPagamentoActionForm.getIdRegistrosRemocao();
		} else if (request.getParameter("idGuiaPagamento") != null) {
			ids = new String[1];
			String idGuiaPagamento = (String) request.getParameter("idGuiaPagamento");
			ids[0] = idGuiaPagamento;

		} else if (actionForm instanceof CategoriaActionForm) {

			ids = (String[]) sessao.getAttribute("idGuiaPagamento");

		} else if (sessao.getAttribute("idGuiaPagamento") != null) {
			ids = (String[]) sessao.getAttribute("idGuiaPagamento");
		} else {
			String idParcelamento = (String) sessao.getAttribute("idParcelamento");

			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.PARCELAMENTO_ID, new Integer(idParcelamento)));

			Collection collectionGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
			GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(collectionGuiaPagamento);
			String idGuiaPagamento = "" + guiaPagamento.getId();
			ids = new String[1];
			ids[0] = idGuiaPagamento;
		}

		GuiaPagamento guia = this.obterGuiaPagamento(new Integer(ids[0]), fachada);
		Parcelamento parcelamento = guia.getParcelamento();
		
		if (this.isEntradaParcelamento(guia)) {
			pesquisarItens(sessao, guia.getId(), guia.getDebitoTipo(), guia.getValorDebito());
			String cpfCnpj = consultarCpfCnpjCliente(guia.getImovel().getId());
			if (!cpfCnpj.equalsIgnoreCase("")) {
				registrarEntradaParcelamento(parcelamento);
			//	retorno = new ActionForward(emitirBoleto());
			} else {
				retorno = new ActionForward(obterLinkBoletoBB(guia.getId()), true);
			}
		} else {
			
			RelatorioEmitirGuiaPagamento relatorioEmitirGuiaPagamento = new RelatorioEmitirGuiaPagamento((Usuario) (request.getSession(false)).getAttribute("usuarioLogado"));
			
			relatorioEmitirGuiaPagamento.addParametro("ids", ids);
			String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			
			relatorioEmitirGuiaPagamento.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			try {
				retorno = processarExibicaoRelatorio(relatorioEmitirGuiaPagamento, tipoRelatorio, request, response, actionMapping);
				
			} catch (RelatorioVazioException ex) {
				reportarErros(request, "atencao.relatorio.vazio");
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
		}

		return retorno;
	}
	
	private void registrarEntradaParcelamento(Parcelamento parcelamento) {
		  getFachada().registrarEntradaParcelamento(parcelamento, false);
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
	
	private boolean isEntradaParcelamento(GuiaPagamento guia) {
		return guia.getParcelamento() != null && guia.getDebitoTipo().getId().intValue() == DebitoTipo.ENTRADA_PARCELAMENTO.intValue();
	}
	
	@SuppressWarnings("unchecked")
	private GuiaPagamento obterGuiaPagamento(Integer idGuia, Fachada fachada) {
		Filtro filtro = new FiltroGuiaPagamento();
		filtro.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, idGuia));
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

		return guia;
	}
	
	@SuppressWarnings("unchecked")
	private String obterLinkBoletoBB(Integer guiaPagamentoId) {
		FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, guiaPagamentoId));
		
		GuiaPagamento guia = (GuiaPagamento) Util.retonarObjetoDeColecao(getFachada().pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName()));
		Parcelamento parcelamento = guia.getParcelamento();
		
		return getFachada().montarLinkBB(parcelamento.getImovel().getId(), parcelamento.getId(), parcelamento.getCliente(), parcelamento.getValorEntrada(), false);
	}
	
	private String consultarCpfCnpjCliente(Integer codigoImovel) {
		return getFachada().consultarCpfCnpjCliente(codigoImovel);
	}
	
	@SuppressWarnings("unchecked")
	public void emitirBoleto () {
		ContaSegundaViaBO parcelamento = new ContaSegundaViaBO(null, null, false, null);
	//	return parcelamento;
	}
	
}
