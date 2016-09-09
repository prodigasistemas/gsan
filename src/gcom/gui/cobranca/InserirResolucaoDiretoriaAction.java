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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirResolucaoDiretoriaAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirResolucaoDiretoriaActionForm form = (InserirResolucaoDiretoriaActionForm) actionForm;

		ResolucaoDiretoria resolucaoDiretoria = new ResolucaoDiretoria();
		resolucaoDiretoria.setNumeroResolucaoDiretoria(form.getNumero());
		resolucaoDiretoria.setDescricaoAssunto(form.getAssunto());
		resolucaoDiretoria.setDataVigenciaInicio(Util.converteStringParaDate(form.getDataInicio()));
		resolucaoDiretoria.setIndicadorParcelamentoUnico(new Short(form.getIndicadorParcelamentoUnico()));
		resolucaoDiretoria.setIndicadorUtilizacaoLivre(new Short(form.getIndicadorUtilizacaoLivre()));
		resolucaoDiretoria.setIndicadorDescontoFaixaReferenciaConta(new Short(form.getIndicadorDescontoFaixaReferenciaConta()));
		resolucaoDiretoria.setIndicadorDescontoSancoes(new Short(form.getIndicadorDescontoSancoes()));
		resolucaoDiretoria.setIndicadorParcelasEmAtraso(new Short(form.getIndicadorParcelasEmAtraso()));
		resolucaoDiretoria.setIndicadorNegociacaoSoAVista(new Short(form.getIndicadorNegociacaoSoAVista()));
		resolucaoDiretoria.setIndicadorDescontoSoEmContaAVista(new Short(form.getIndicadorDescontoSoEmContaAVista()));
		resolucaoDiretoria.setIndicadorParcelamentoLojaVirtual(new Short(form.getIndicadorParcelamentoLojaVirtual()));

		if (form.getIdParcelasEmAtraso() != null && !form.getIdParcelasEmAtraso().equals("")) {
			FiltroResolucaoDiretoria filtro = new FiltroResolucaoDiretoria();
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, new Integer(form.getIdParcelasEmAtraso())));

			Collection<ResolucaoDiretoria> colecaoRD = getFachada().pesquisar(filtro, ResolucaoDiretoria.class.getName());

			if (colecaoRD != null && !colecaoRD.isEmpty()) {
				ResolucaoDiretoria rdParcelasEmAtraso = new ResolucaoDiretoria();
				rdParcelasEmAtraso.setId(new Integer(form.getIdParcelasEmAtraso()));
				resolucaoDiretoria.setRdParcelasEmAtraso(rdParcelasEmAtraso);
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "RD Parcelas em Atraso");
			}
		}

		resolucaoDiretoria.setIndicadorParcelamentoEmAndamento(new Short(form.getIndicadorParcelamentoEmAndamento()));

		if (form.getIdParcelamentoEmAndamento() != null && !form.getIdParcelamentoEmAndamento().equals("")) {
			FiltroResolucaoDiretoria filtro = new FiltroResolucaoDiretoria();
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, new Integer(form.getIdParcelamentoEmAndamento())));

			Collection<ResolucaoDiretoria> colecaoRD = getFachada().pesquisar(filtro, ResolucaoDiretoria.class.getName());

			if (colecaoRD != null && !colecaoRD.isEmpty()) {
				ResolucaoDiretoria rdParcelamentoEmAndamento = new ResolucaoDiretoria();
				rdParcelamentoEmAndamento.setId(new Integer(form.getIdParcelamentoEmAndamento()));
				resolucaoDiretoria.setRdParcelamentoEmAndamento(rdParcelamentoEmAndamento);
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "RD Parcelamento em Andamento");
			}

		}

		if (form.getDataFim() != null && !form.getDataFim().equals("")) {
			resolucaoDiretoria.setDataVigenciaFim(Util.converteStringParaDate(form.getDataFim()));
		}

		Integer id = (Integer) getFachada().inserirResolucaoDiretoria(resolucaoDiretoria, this.getUsuarioLogado(request));

		montarPaginaSucesso(request, 
				"Resolução de Diretoria " + resolucaoDiretoria.getNumeroResolucaoDiretoria() + " inserida com sucesso.", 
				"Inserir outra Resolução de Diretoria",
				"exibirInserirResolucaoDiretoriaAction.do?menu=sim", 
				"exibirAtualizarResolucaoDiretoriaAction.do?inserir=sim&resolucaoDiretoriaID=" + id, 
				"Atualizar Resolução de Diretoria inserida");

		return retorno;

	}

}
