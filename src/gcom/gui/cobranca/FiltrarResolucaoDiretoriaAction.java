package gcom.gui.cobranca;

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarResolucaoDiretoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = actionMapping.findForward("manterResolucaoDiretoria");

		FiltrarResolucaoDiretoriaActionForm form = (FiltrarResolucaoDiretoriaActionForm) actionForm;

		HttpSession sessao = request.getSession(false);

		String numero = "";
		String assunto = "";
		String dataInicio = "";
		String dataFim = "";
		String indicadorParcelamentoUnico = "";
		String indicadorUtilizacaoLivre = "";
		String indicadorDescontoFaixaReferenciaConta = "";
		String indicadorDescontoSancoes = "";
		String indicadorParcelasEmAtraso = "";
		String idParcelasEmAtraso = "";
		String indicadorParcelamentoEmAndamento = "";
		String idParcelamentoEmAndamento = "";
		String indicadorNegociacaoSoAVista = "";
		String indicadorDescontoSoEmContaAVista = "";
		String indicadorParcelamentoLojaVirtual = "";

		if (request.getParameter("page.offset") == null) {

			numero = form.getNumero();
			sessao.setAttribute("numero", numero);

			assunto = form.getAssunto();
			sessao.setAttribute("assunto", assunto);

			dataInicio = form.getDataInicio();
			sessao.setAttribute("dataInicio", dataInicio);

			dataFim = form.getDataFim();
			sessao.setAttribute("dataFim", dataFim);

			indicadorParcelamentoUnico = form.getIndicadorParcelamentoUnico();
			sessao.setAttribute("indicadorParcelamentoUnico", indicadorParcelamentoUnico);

			indicadorUtilizacaoLivre = form.getIndicadorUtilizacaoLivre();
			sessao.setAttribute("indicadorUtilizacaoLivre", indicadorUtilizacaoLivre);

			indicadorDescontoFaixaReferenciaConta = form.getIndicadorDescontoFaixaReferenciaConta();
			sessao.setAttribute("indicadorDescontoFaixaReferenciaConta", indicadorDescontoFaixaReferenciaConta);
			
			indicadorDescontoSancoes = form.getIndicadorDescontoSancoes();
			sessao.setAttribute("indicadorDescontoSancoes", indicadorDescontoSancoes);

			indicadorParcelasEmAtraso = form.getIndicadorParcelasEmAtraso();
			sessao.setAttribute("indicadorParcelasEmAtraso", indicadorParcelasEmAtraso);

			idParcelasEmAtraso = form.getIdParcelasEmAtraso();
			sessao.setAttribute("idParcelasEmAtraso", idParcelasEmAtraso);

			indicadorParcelamentoEmAndamento = form.getIndicadorParcelamentoEmAndamento();
			sessao.setAttribute("indicadorParcelamentoEmAndamento", indicadorParcelamentoEmAndamento);

			idParcelamentoEmAndamento = form.getIdParcelamentoEmAndamento();
			sessao.setAttribute("idParcelamentoEmAndamento", idParcelamentoEmAndamento);

			indicadorNegociacaoSoAVista = form.getIndicadorNegociacaoSoAVista();
			sessao.setAttribute("indicadorNegociacaoSoAVista", indicadorNegociacaoSoAVista);

			indicadorDescontoSoEmContaAVista = form.getIndicadorDescontoSoEmContaAVista();
			sessao.setAttribute("indicadorDescontoSoEmContaAVista", indicadorDescontoSoEmContaAVista);

			indicadorParcelamentoLojaVirtual = form.getIndicadorParcelamentoLojaVirtual();
			sessao.setAttribute("indicadorParcelamentoLojaVirtual", indicadorParcelamentoLojaVirtual);
		} else {
			numero = (String) sessao.getAttribute("numero");
			assunto = (String) sessao.getAttribute("assunto");
			dataInicio = (String) sessao.getAttribute("dataInicio");
			dataFim = (String) sessao.getAttribute("dataFim");
			indicadorParcelamentoUnico = (String) sessao.getAttribute("indicadorParcelamentoUnico");
			indicadorUtilizacaoLivre = (String) sessao.getAttribute("indicadorUtilizacaoLivre");
			indicadorDescontoFaixaReferenciaConta = (String) sessao.getAttribute("indicadorDescontoFaixaReferenciaConta");
			indicadorDescontoSancoes = (String) sessao.getAttribute("indicadorDescontoSancoes");

			indicadorParcelasEmAtraso = (String) sessao.getAttribute("indicadorParcelasEmAtraso");
			if (sessao.getAttribute("idParcelasEmAtraso") != null) {
				idParcelasEmAtraso = (String) sessao.getAttribute("idParcelasEmAtraso");
			}
			indicadorParcelamentoEmAndamento = (String) sessao.getAttribute("indicadorParcelamentoEmAndamento");

			if (sessao.getAttribute("idParcelamentoEmAndamento") != null) {
				idParcelamentoEmAndamento = (String) sessao.getAttribute("idParcelamentoEmAndamento");
			}
			indicadorNegociacaoSoAVista = (String) sessao.getAttribute("indicadorNegociacaoSoAVista");
			indicadorDescontoSoEmContaAVista = (String) sessao.getAttribute("indicadorDescontoSoEmContaAVista");
			indicadorParcelamentoLojaVirtual = (String) sessao.getAttribute("indicadorParcelamentoLojaVirtual");
		}

		FiltroResolucaoDiretoria filtro = new FiltroResolucaoDiretoria();
		filtro.setCampoOrderBy("numeroResolucaoDiretoria");

		if (dataInicio != null && !dataInicio.trim().equals("") && dataFim != null && !dataFim.trim().equals("")) {
			if ((Util.converteStringParaDate(dataInicio)).compareTo(Util.converteStringParaDate(dataFim)) >= 0) {
				throw new ActionServletException("atencao.termino_vigencia.anterior.inicio_vigencia");
			}
		}

		boolean peloMenosUmParametroInformado = false;

		if (numero != null && !numero.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.NUMERO, numero));
		}

		if (assunto != null && !assunto.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtro.adicionarParametro(new ComparacaoTexto(FiltroResolucaoDiretoria.DESCRICAO, assunto));
		}

		if (dataInicio != null && !dataInicio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			Date dataInicioFormatada = Util.converteStringParaDate(dataInicio);

			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.DATA_VIGENCIA_INICIO, dataInicioFormatada));
		}

		if (dataFim != null && !dataFim.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			Date dataFimFormatada = Util.converteStringParaDate(dataFim);
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.DATA_VIGENCIA_FIM, dataFimFormatada));
		}

		if (!form.getIndicadorParcelamentoUnico().equals(ConstantesSistema.TODOS.toString())) {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_UNICO, form.getIndicadorParcelamentoUnico()));
			peloMenosUmParametroInformado = true;
		}

		if (!form.getIndicadorUtilizacaoLivre().equals(ConstantesSistema.TODOS.toString())) {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_UTILIZACAO_LIVRE, form.getIndicadorUtilizacaoLivre()));
			peloMenosUmParametroInformado = true;
		}
		
		if (!form.getIndicadorDescontoFaixaReferenciaConta().equals(ConstantesSistema.TODOS.toString())) {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_DESCONTO_FAIXA_REFERENCIA_CONTA, form.getIndicadorDescontoFaixaReferenciaConta()));
			peloMenosUmParametroInformado = true;
		}

		if (!form.getIndicadorDescontoSancoes().equals(ConstantesSistema.TODOS.toString())) {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_DESCONTOS_SANCOES, form.getIndicadorDescontoSancoes()));
			peloMenosUmParametroInformado = true;
		}

		if (!form.getIndicadorParcelasEmAtraso().equals(ConstantesSistema.TODOS.toString())) {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_PARCELAS_EM_ATRASO, form.getIndicadorParcelasEmAtraso()));
			peloMenosUmParametroInformado = true;

			if (form.getIdParcelasEmAtraso().equals(ConstantesSistema.SIM.toString())) {
				filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.ID_RD_PARCELAS_EM_ATRASO, form.getIdParcelasEmAtraso()));
			}
		}

		if (!form.getIndicadorParcelamentoEmAndamento().equals(ConstantesSistema.TODOS.toString())) {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_EM_ANDAMENTO, form.getIndicadorParcelamentoEmAndamento()));
			peloMenosUmParametroInformado = true;

			if (form.getIdParcelamentoEmAndamento().equals(ConstantesSistema.SIM.toString())) {
				filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.ID_RD_PARCELAMENTO_EM_ANDAMENTO, form.getIdParcelamentoEmAndamento()));
			}
		}

		if (!form.getIndicadorNegociacaoSoAVista().equals(ConstantesSistema.TODOS.toString())) {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_NEGOCIACAO_SO_A_VISTA, form.getIndicadorNegociacaoSoAVista()));
			peloMenosUmParametroInformado = true;
		}

		if (!form.getIndicadorDescontoSoEmContaAVista().equals(ConstantesSistema.TODOS.toString())) {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_DESCONTO_SO_EM_CONTA_A_VISTA, form.getIndicadorDescontoSoEmContaAVista()));
			peloMenosUmParametroInformado = true;
		}

		if (!form.getIndicadorParcelamentoLojaVirtual().equals(ConstantesSistema.TODOS.toString())) {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_LOJA_VIRTUAL, form.getIndicadorParcelamentoLojaVirtual()));
			peloMenosUmParametroInformado = true;
		}

		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		if (form.getAtualizar() != null && form.getAtualizar().equalsIgnoreCase("1")) {
			request.setAttribute("atualizar", form.getAtualizar());
		}

		sessao.setAttribute("filtroResolucaoDiretoria", filtro);

		return retorno;
	}
}
