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

public class AtualizarResolucaoDiretoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarResolucaoDiretoriaActionForm atualizarResolucaoDiretoriaActionForm = (AtualizarResolucaoDiretoriaActionForm) actionForm;

		ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) sessao
				.getAttribute("resolucaoDiretoriaAtualizar");

		resolucaoDiretoria.setNumeroResolucaoDiretoria(atualizarResolucaoDiretoriaActionForm.getNumero());
		resolucaoDiretoria.setDescricaoAssunto(atualizarResolucaoDiretoriaActionForm.getAssunto());
		resolucaoDiretoria.setDataVigenciaInicio(Util.converteStringParaDate(atualizarResolucaoDiretoriaActionForm.getDataInicio()));
		resolucaoDiretoria.setIndicadorParcelamentoUnico(new Short (atualizarResolucaoDiretoriaActionForm.getIndicadorParcelamentoUnico()));
		resolucaoDiretoria.setIndicadorUtilizacaoLivre(new Short (atualizarResolucaoDiretoriaActionForm.getIndicadorUtilizacaoLivre()));
		resolucaoDiretoria.setIndicadorDescontoSancoes(new Short (atualizarResolucaoDiretoriaActionForm.getIndicadorDescontoSancoes()));
		resolucaoDiretoria.setIndicadorParcelasEmAtraso(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorParcelasEmAtraso()));
		resolucaoDiretoria.setIndicadorParcelamentoEmAndamento(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorParcelamentoEmAndamento()));
		resolucaoDiretoria.setIndicadorNegociacaoSoAVista(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorNegociacaoSoAVista()));
		resolucaoDiretoria.setIndicadorDescontoSoEmContaAVista(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorDescontoSoEmContaAVista()));
		resolucaoDiretoria.setIndicadorParcelamentoLojaVirtual(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorParcelamentoLojaVirtual()));
		
		if (atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso()!= null &&
				!atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso().equals("")){
			
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
			FiltroResolucaoDiretoria.CODIGO, new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso())));
			Collection<ResolucaoDiretoria> colecaoRD = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if(!Util.isVazioOrNulo(colecaoRD)){
				ResolucaoDiretoria rdParcelasEmAtraso = new ResolucaoDiretoria();
				rdParcelasEmAtraso.setId(new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso()));
				resolucaoDiretoria.setRdParcelasEmAtraso(rdParcelasEmAtraso);
			}else{
				//RD Parcelas em Atraso inexistente.
				throw new ActionServletException(
				"atencao.pesquisa_inexistente", null, "RD Parcelas em Atraso");
			}
		}

		if (atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento()!= null &&
				!atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento().equals("")){
			
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
			FiltroResolucaoDiretoria.CODIGO, new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento())));

			Collection<ResolucaoDiretoria> colecaoRD = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if(!Util.isVazioOrNulo(colecaoRD)){
				ResolucaoDiretoria rdParcelamentoEmAndamento = new ResolucaoDiretoria();
				rdParcelamentoEmAndamento.setId(new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento()));
				resolucaoDiretoria.setRdParcelamentoEmAndamento(rdParcelamentoEmAndamento);
			}else{
				//RD Parcelamento em Andamento inexistente.
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "RD Parcelamento em Andamento");
			}
			
		}
		if (atualizarResolucaoDiretoriaActionForm.getDataFim() != null 
				&& !atualizarResolucaoDiretoriaActionForm.getDataFim().equals("")) {
			resolucaoDiretoria.setDataVigenciaFim(Util.converteStringParaDate(atualizarResolucaoDiretoriaActionForm.getDataFim()));
		}
		fachada.atualizarResolucaoDiretoria(resolucaoDiretoria, this.getUsuarioLogado(httpServletRequest));

		montarPaginaSucesso(httpServletRequest, "Resolução de Diretoria "
				+ resolucaoDiretoria.getNumeroResolucaoDiretoria()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Resolução de Diretoria",
				"exibirFiltrarResolucaoDiretoriaAction.do?menu=sim");

		return retorno;

	}
}
