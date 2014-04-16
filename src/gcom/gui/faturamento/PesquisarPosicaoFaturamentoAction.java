package gcom.gui.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarPosicaoFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("posicaoFaturamento");
		
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = request.getSession(false);
		
		sessao.removeAttribute("mapPagina");

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo
				.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);

		Collection colecaoFaturamentoGrupo = fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		/*Iterator iteratorFaturamentoGrupo =*/ colecaoFaturamentoGrupo.iterator();

		Map<FaturamentoGrupo, Collection<FaturamentoAtividadeCronograma>> mapPagina = new TreeMap(
				new Comparator() {
					public int compare(Object a, Object b) {
						String descricao1 = ((FaturamentoGrupo) a)
								.getDescricao();
						String descricao2 = ((FaturamentoGrupo) b)
								.getDescricao();

						return descricao1.compareTo(descricao2);
					}
				});

		Iterator iteratorFaturamentoGrupo2 = colecaoFaturamentoGrupo.iterator();
		
		while (iteratorFaturamentoGrupo2.hasNext()) {
			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) iteratorFaturamentoGrupo2
					.next();

			Collection<FaturamentoAtividadeCronograma> colecaoFaturamentoAtividadeCronograma = Fachada
					.getInstancia().pesquisarRelacaoAtividadesGrupo(
							faturamentoGrupo.getId());

			if (colecaoFaturamentoAtividadeCronograma != null
					&& !colecaoFaturamentoAtividadeCronograma.isEmpty()) {
				mapPagina.put(faturamentoGrupo,
						colecaoFaturamentoAtividadeCronograma);
			}
		}
		
		if(mapPagina.size() == 0)
		{
			throw new ActionServletException(
                    "atencao.nao.ha.cronograma.faturamento", null, "");
		}
		
		sessao.setAttribute("mapPagina", mapPagina);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		request.setAttribute("mesAno", Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));

		return retorno;
	}
}
