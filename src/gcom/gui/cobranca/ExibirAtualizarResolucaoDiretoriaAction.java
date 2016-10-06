package gcom.gui.cobranca;

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarResolucaoDiretoriaAction extends GcomAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirAtualizarResolucaoDiretoria");

		AtualizarResolucaoDiretoriaActionForm form = (AtualizarResolucaoDiretoriaActionForm) actionForm;

		HttpSession sessao = request.getSession(false);

		if (request.getParameter("inserir") != null && !request.getParameter("inserir").equals("")) {
			String inserir = request.getParameter("inserir");
			request.setAttribute("inserir", inserir);
		}

		if (sessao.getAttribute("resolucaoDiretoria") != null) {

			ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) sessao.getAttribute("resolucaoDiretoria");

			form.setNumero(resolucaoDiretoria.getNumeroResolucaoDiretoria());
			form.setAssunto(resolucaoDiretoria.getDescricaoAssunto());
			form.setDataInicio(Util.formatarData(resolucaoDiretoria.getDataVigenciaInicio()));
			form.setDataFim(Util.formatarData(resolucaoDiretoria.getDataVigenciaFim()));
			form.setIndicadorParcelamentoUnico(resolucaoDiretoria.getIndicadorParcelamentoUnico().toString());
			form.setIndicadorUtilizacaoLivre(resolucaoDiretoria.getIndicadorUtilizacaoLivre().toString());
			form.setIndicadorDescontoFaixaReferenciaConta(resolucaoDiretoria.getIndicadorDescontoFaixaReferenciaConta().toString());
			form.setIndicadorDescontoSancoes(resolucaoDiretoria.getIndicadorDescontoSancoes().toString());
			form.setIndicadorParcelamentoLojaVirtual(resolucaoDiretoria.getIndicadorParcelamentoLojaVirtual().toString());
			form.setIndicadorParcelasEmAtraso(resolucaoDiretoria.getIndicadorParcelasEmAtraso().toString());

			if (resolucaoDiretoria.getRdParcelasEmAtraso() != null && !resolucaoDiretoria.getRdParcelasEmAtraso().equals("")) {
				form.setIdParcelasEmAtraso(resolucaoDiretoria.getRdParcelasEmAtraso().getId().toString());
			}

			form.setIndicadorParcelamentoEmAndamento(resolucaoDiretoria.getIndicadorParcelamentoEmAndamento().toString());

			if (resolucaoDiretoria.getRdParcelamentoEmAndamento() != null && !resolucaoDiretoria.getRdParcelamentoEmAndamento().equals("")) {
				form.setIdParcelamentoEmAndamento(resolucaoDiretoria.getRdParcelamentoEmAndamento().getId().toString());
			}

			form.setIndicadorNegociacaoSoAVista(resolucaoDiretoria.getIndicadorNegociacaoSoAVista().toString());

			form.setIndicadorDescontoSoEmContaAVista(resolucaoDiretoria.getIndicadorDescontoSoEmContaAVista().toString());

			sessao.setAttribute("resolucaoDiretoriaAtualizar", resolucaoDiretoria);
			sessao.removeAttribute("resolucaoDiretoria");

		} else {
			String idResolucaoDiretoria = request.getParameter("resolucaoDiretoriaID");

			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, idResolucaoDiretoria));

			Collection<ResolucaoDiretoria> colecaoResolucaoDiretoria = getFachada().pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if (Util.isVazioOrNulo(colecaoResolucaoDiretoria)) {
				throw new ActionServletException("atencao.atualizacao.timestamp");
			}

			ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) colecaoResolucaoDiretoria.iterator().next();

			form.setNumero(resolucaoDiretoria.getNumeroResolucaoDiretoria());
			form.setAssunto(resolucaoDiretoria.getDescricaoAssunto());
			form.setDataInicio(Util.formatarData(resolucaoDiretoria.getDataVigenciaInicio()));
			form.setDataFim(Util.formatarData(resolucaoDiretoria.getDataVigenciaFim()));
			form.setIndicadorParcelamentoUnico(resolucaoDiretoria.getIndicadorParcelamentoUnico().toString());
			form.setIndicadorUtilizacaoLivre(resolucaoDiretoria.getIndicadorUtilizacaoLivre().toString());
			form.setIndicadorDescontoFaixaReferenciaConta(resolucaoDiretoria.getIndicadorDescontoFaixaReferenciaConta().toString());
			form.setIndicadorDescontoSancoes(resolucaoDiretoria.getIndicadorDescontoSancoes().toString());
			form.setIndicadorParcelasEmAtraso(resolucaoDiretoria.getIndicadorParcelasEmAtraso().toString());
			form.setIndicadorParcelamentoLojaVirtual(resolucaoDiretoria.getIndicadorParcelamentoLojaVirtual().toString());

			if (resolucaoDiretoria.getRdParcelasEmAtraso() != null && !resolucaoDiretoria.getRdParcelasEmAtraso().equals("")) {
				form.setIdParcelasEmAtraso(resolucaoDiretoria.getRdParcelasEmAtraso().getId().toString());
			}

			form.setIndicadorParcelamentoEmAndamento(resolucaoDiretoria.getIndicadorParcelamentoEmAndamento().toString());

			if (resolucaoDiretoria.getRdParcelamentoEmAndamento() != null && !resolucaoDiretoria.getRdParcelamentoEmAndamento().equals("")) {
				form.setIdParcelamentoEmAndamento(resolucaoDiretoria.getRdParcelamentoEmAndamento().getId().toString());
			}

			form.setIndicadorNegociacaoSoAVista(resolucaoDiretoria.getIndicadorNegociacaoSoAVista().toString());

			form.setIndicadorDescontoSoEmContaAVista(resolucaoDiretoria.getIndicadorDescontoSoEmContaAVista().toString());

			sessao.setAttribute("resolucaoDiretoriaAtualizar", resolucaoDiretoria);

		}

		return retorno;

	}

}
