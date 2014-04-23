package gcom.gui.cobranca;

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarResolucaoDiretoria");

		AtualizarResolucaoDiretoriaActionForm atualizarResolucaoDiretoriaActionForm = (AtualizarResolucaoDiretoriaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("inserir") != null
				&& !httpServletRequest.getParameter("inserir").equals("")) {
			String inserir = httpServletRequest.getParameter("inserir");
			httpServletRequest.setAttribute("inserir", inserir);
		}

		if (sessao.getAttribute("resolucaoDiretoria") != null) {

			ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) sessao
					.getAttribute("resolucaoDiretoria");

			atualizarResolucaoDiretoriaActionForm.setNumero(resolucaoDiretoria.getNumeroResolucaoDiretoria());
			atualizarResolucaoDiretoriaActionForm.setAssunto(resolucaoDiretoria.getDescricaoAssunto());
			atualizarResolucaoDiretoriaActionForm.setDataInicio(Util.formatarData(resolucaoDiretoria.getDataVigenciaInicio()));
			atualizarResolucaoDiretoriaActionForm.setDataFim(Util.formatarData(resolucaoDiretoria.getDataVigenciaFim()));
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoUnico(resolucaoDiretoria.getIndicadorParcelamentoUnico().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorUtilizacaoLivre(resolucaoDiretoria.getIndicadorUtilizacaoLivre().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSancoes(resolucaoDiretoria.getIndicadorDescontoSancoes().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoLojaVirtual(resolucaoDiretoria.getIndicadorParcelamentoLojaVirtual().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelasEmAtraso(resolucaoDiretoria.getIndicadorParcelasEmAtraso().toString());
			
			if (resolucaoDiretoria.getRdParcelasEmAtraso()!= null &&
					!resolucaoDiretoria.getRdParcelasEmAtraso().equals("")){
				atualizarResolucaoDiretoriaActionForm.setIdParcelasEmAtraso(resolucaoDiretoria.getRdParcelasEmAtraso().getId().toString());
			}
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoEmAndamento(resolucaoDiretoria.getIndicadorParcelamentoEmAndamento().toString());
			
			if (resolucaoDiretoria.getRdParcelamentoEmAndamento()!= null &&
					!resolucaoDiretoria.getRdParcelamentoEmAndamento().equals("")){
				atualizarResolucaoDiretoriaActionForm.setIdParcelamentoEmAndamento(resolucaoDiretoria.getRdParcelamentoEmAndamento().getId().toString());
			}
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorNegociacaoSoAVista(resolucaoDiretoria.getIndicadorNegociacaoSoAVista().toString());
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSoEmContaAVista(resolucaoDiretoria.getIndicadorDescontoSoEmContaAVista().toString());

			sessao.setAttribute("resolucaoDiretoriaAtualizar",
					resolucaoDiretoria);
			sessao.removeAttribute("resolucaoDiretoria");

		} else {

			String idResolucaoDiretoria = httpServletRequest.getParameter("resolucaoDiretoriaID");

			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, idResolucaoDiretoria));

			Collection<ResolucaoDiretoria> colecaoResolucaoDiretoria = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if (Util.isVazioOrNulo(colecaoResolucaoDiretoria)) {
				throw new ActionServletException("atencao.atualizacao.timestamp");
			}

			ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) colecaoResolucaoDiretoria.iterator().next();

			atualizarResolucaoDiretoriaActionForm.setNumero(resolucaoDiretoria.getNumeroResolucaoDiretoria());
			atualizarResolucaoDiretoriaActionForm.setAssunto(resolucaoDiretoria.getDescricaoAssunto());
			atualizarResolucaoDiretoriaActionForm.setDataInicio(Util.formatarData(resolucaoDiretoria.getDataVigenciaInicio()));
			atualizarResolucaoDiretoriaActionForm.setDataFim(Util.formatarData(resolucaoDiretoria.getDataVigenciaFim()));
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoUnico(resolucaoDiretoria.getIndicadorParcelamentoUnico().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorUtilizacaoLivre(resolucaoDiretoria.getIndicadorUtilizacaoLivre().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSancoes(resolucaoDiretoria.getIndicadorDescontoSancoes().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelasEmAtraso(resolucaoDiretoria.getIndicadorParcelasEmAtraso().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoLojaVirtual(resolucaoDiretoria.getIndicadorParcelamentoLojaVirtual().toString());
			
			if (resolucaoDiretoria.getRdParcelasEmAtraso()!= null &&
					!resolucaoDiretoria.getRdParcelasEmAtraso().equals("")){
				atualizarResolucaoDiretoriaActionForm.setIdParcelasEmAtraso(resolucaoDiretoria.getRdParcelasEmAtraso().getId().toString());
			}
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoEmAndamento(resolucaoDiretoria.getIndicadorParcelamentoEmAndamento().toString());
			
			if (resolucaoDiretoria.getRdParcelamentoEmAndamento()!= null &&
					!resolucaoDiretoria.getRdParcelamentoEmAndamento().equals("")){
				atualizarResolucaoDiretoriaActionForm.setIdParcelamentoEmAndamento(resolucaoDiretoria.getRdParcelamentoEmAndamento().getId().toString());
			}
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorNegociacaoSoAVista(resolucaoDiretoria.getIndicadorNegociacaoSoAVista().toString());
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSoEmContaAVista(resolucaoDiretoria.getIndicadorDescontoSoEmContaAVista().toString());
			
			sessao.setAttribute("resolucaoDiretoriaAtualizar",
					resolucaoDiretoria);

		}

		return retorno;

	}

}
