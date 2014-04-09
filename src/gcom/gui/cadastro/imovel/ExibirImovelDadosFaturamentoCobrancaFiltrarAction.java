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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirImovelDadosFaturamentoCobrancaFiltrarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarImovelDadosFaturamento");

		Fachada fachada = Fachada.getInstancia();

		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
		filtroFaturamentoSituacaoTipo.setCampoOrderBy(FiltroFaturamentoSituacaoTipo.DESCRICAO);
		Collection<FaturamentoSituacaoTipo> collectionFaturamentoSituacaoTipo = fachada
				.pesquisar(filtroFaturamentoSituacaoTipo,
						FaturamentoSituacaoTipo.class.getName());
		 if(collectionFaturamentoSituacaoTipo == null || collectionFaturamentoSituacaoTipo.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Tipo de Situação Especial de Faturamento");			
		 }	
		
		FiltroCobrancaSituacaoTipo filtroCobrancaSituacaoTipo = new FiltroCobrancaSituacaoTipo();
		filtroCobrancaSituacaoTipo.setCampoOrderBy(FiltroCobrancaSituacaoTipo.DESCRICAO);
		Collection<CobrancaSituacaoTipo> collectionCobrancaSituacaoTipo = fachada
				.pesquisar(filtroCobrancaSituacaoTipo,
						CobrancaSituacaoTipo.class.getName());
		 if(collectionCobrancaSituacaoTipo == null || collectionCobrancaSituacaoTipo.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				" Tipo de Situação Especial de Cobrança");			
		 }	
		
		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
		filtroCobrancaSituacao.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);
		Collection<CobrancaSituacao> collectionCobrancaSituacao = fachada
				.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class
						.getName());
		 if(collectionCobrancaSituacao == null || collectionCobrancaSituacao.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				" Situação de Cobrança");			
		 }	
		
		
		FiltroEloAnormalidade filtroEloAnormalidade = new FiltroEloAnormalidade();
		filtroEloAnormalidade.setCampoOrderBy(FiltroEloAnormalidade.DESCRICAO);
		Collection<EloAnormalidade> collectionEloAnormalidade = fachada
				.pesquisar(filtroEloAnormalidade, EloAnormalidade.class
						.getName());
		 if(collectionEloAnormalidade == null || collectionEloAnormalidade.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Anormalidade de Elo");			
		 }	
		
		
		FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
		filtroCadastroOcorrencia.setCampoOrderBy(FiltroCadastroOcorrencia.DESCRICAO);
		Collection<CadastroOcorrencia> collectionCadastroOcorrencia = fachada
				.pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class
						.getName());
		 if(collectionCadastroOcorrencia == null || collectionCadastroOcorrencia.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Ocorrência de Cadastro");			
		 }	
		
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
		Collection<ConsumoTarifa> collectionConsumoTarifa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());
		 if(collectionConsumoTarifa == null || collectionConsumoTarifa.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Tarifa de Consumo");			
		 }	
		
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
