package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.EloAnormalidade;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroEloAnormalidade;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacaoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ImovelDadosFaturamentoCobrancaFiltrarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarImovelDadosFaturamento");

		Fachada fachada = Fachada.getInstancia();

		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
		Collection<FaturamentoSituacaoTipo> collectionFaturamentoSituacaoTipo = fachada
				.pesquisar(filtroFaturamentoSituacaoTipo,
						FaturamentoSituacaoTipo.class.getName());

		FiltroCobrancaSituacaoTipo filtroCobrancaSituacaoTipo = new FiltroCobrancaSituacaoTipo();
		Collection<CobrancaSituacaoTipo> collectionCobrancaSituacaoTipo = fachada
				.pesquisar(filtroCobrancaSituacaoTipo,
						CobrancaSituacaoTipo.class.getName());

		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
		Collection<CobrancaSituacao> collectionCobrancaSituacao = fachada
				.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class
						.getName());

		FiltroEloAnormalidade filtroEloAnormalidade = new FiltroEloAnormalidade();
		Collection<EloAnormalidade> collectionEloAnormalidade = fachada
				.pesquisar(filtroEloAnormalidade, EloAnormalidade.class
						.getName());

		FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
		Collection<CadastroOcorrencia> collectionCadastroOcorrencia = fachada
				.pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class
						.getName());

		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		Collection<ConsumoTarifa> collectionConsumoTarifa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());

		httpServletRequest.setAttribute("collectionFaturamentoSituacaoTipo",
				collectionFaturamentoSituacaoTipo);
		httpServletRequest.setAttribute("collectionCobrancaSituacaoTipo",
				collectionCobrancaSituacaoTipo);
		httpServletRequest.setAttribute("collectionCobrancaSituacao",
				collectionCobrancaSituacao);
		httpServletRequest.setAttribute("collectionEloAnormalidade",
				collectionEloAnormalidade);
		httpServletRequest.setAttribute("collectionCadastroOcorrencia",
				collectionCadastroOcorrencia);
		httpServletRequest.setAttribute("collectionConsumoTarifa",
				collectionConsumoTarifa);

		return retorno;
	}

}
