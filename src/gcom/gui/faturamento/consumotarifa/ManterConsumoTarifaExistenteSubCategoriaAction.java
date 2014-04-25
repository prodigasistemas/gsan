package gcom.gui.faturamento.consumotarifa;

import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.util.Util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ManterConsumoTarifaExistenteSubCategoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirConsumoTarifaActionForm inserirConsumoTarifaActionForm = (InserirConsumoTarifaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		String descTarifa = inserirConsumoTarifaActionForm.getDescTarifa();
		String dataVigencia = inserirConsumoTarifaActionForm.getDataVigencia();

		ConsumoTarifaVigencia consumoTarifaVigencia = (ConsumoTarifaVigencia) gcom.util.Util
				.retonarObjetoDeColecao((Collection) sessao
						.getAttribute("colecaoVigencia"));

		consumoTarifaVigencia.setId(consumoTarifaVigencia.getId());
		consumoTarifaVigencia.setDataVigencia(gcom.util.Util
				.converteStringParaDate(dataVigencia));
		consumoTarifaVigencia.getConsumoTarifa().setDescricao(descTarifa);

		Collection<CategoriaFaixaConsumoTarifaHelper> colecaoCategoriaFaixaConsumoTarifaHelper = (Collection<CategoriaFaixaConsumoTarifaHelper>) sessao
			.getAttribute("colecaoCategoria");
		
		if (colecaoCategoriaFaixaConsumoTarifaHelper == null
				|| colecaoCategoriaFaixaConsumoTarifaHelper.isEmpty()) {
			throw new ActionServletException("atencao.nenhuma_categoria_tarifa");
		}
		
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		
		Iterator iteratorColecaoCategoriaFaixaConsumoTarifaHelper = colecaoCategoriaFaixaConsumoTarifaHelper.iterator();
		
		CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelper= null;
		
		String consumoMinimo= null;
		String tarifaMinima = null;
		
		while (iteratorColecaoCategoriaFaixaConsumoTarifaHelper.hasNext()) {
			categoriaFaixaConsumoTarifaHelper = (CategoriaFaixaConsumoTarifaHelper) iteratorColecaoCategoriaFaixaConsumoTarifaHelper.next();
			
			
			
			//valor minimo
			if (requestMap.get("ValorConMinimo."
					+ categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getSubCategoria().getDescricao()) != null) {

				consumoMinimo = (requestMap.get("ValorConMinimo." + categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getSubCategoria().getDescricao()))[0];

				
				
				if (consumoMinimo == null
						|| consumoMinimo.equalsIgnoreCase("")) {
					throw new ActionServletException(
							"atencao.required", null,
							"Consumo Mínimo");
				}

				categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().setNumeroConsumoMinimo(new Integer(consumoMinimo));
			}

			
			//valor da tarifa minima
			if (requestMap.get("ValorTarMin."
					+ categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getSubCategoria().getDescricao()) != null) {

				tarifaMinima = (requestMap.get("ValorTarMin." + categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getSubCategoria().getDescricao()))[0];

				if (tarifaMinima == null
						|| tarifaMinima.equalsIgnoreCase("")) {
					throw new ActionServletException(
							"atencao.required", null,
							"Tarifa Mínima");
				}

				categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().setValorTarifaMinima(Util.formatarMoedaRealparaBigDecimal(tarifaMinima));
			}
			
			// Atribuindo a colecao faixa valores da categoria
			if ((categoriaFaixaConsumoTarifaHelper.getColecaoFaixas() != null) && (!categoriaFaixaConsumoTarifaHelper.getColecaoFaixas().isEmpty())) {
				Iterator colecaoFaixaIt = categoriaFaixaConsumoTarifaHelper.getColecaoFaixas().iterator();
				while (colecaoFaixaIt.hasNext()) {
					ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIt
							.next();
					if(new Integer(consumoMinimo).intValue() > consumoTarifaFaixa.getNumeroConsumoFaixaFim().intValue() ){
						throw new ActionServletException(
						"atencao.consumo_minimo.maior.faixa_limite_superior_menor_existe");
					}

				}
			}
			
		}
		
		String func =  "informarTarifaConsumoPorSubCategoria";
	
		String [] ids = new String[1];
		ids[0] = consumoTarifaVigencia.getId().toString();
			
		fachada.removerTarifaConsumo(ids);
		
		fachada.atualizarConsumoTarifa(consumoTarifaVigencia,
				(Collection<CategoriaFaixaConsumoTarifaHelper>) sessao
						.getAttribute("colecaoCategoria"), func);


		montarPaginaSucesso(httpServletRequest, consumoTarifaVigencia
				.getConsumoTarifa().getDescricao()
				+ " de vigência "
				+ gcom.util.Util.formatarData(consumoTarifaVigencia
						.getDataVigencia()) + " atualizada com sucesso.",
				"Atualizar outra tarifa de consumo",
				"exibirFiltrarConsumoTarifaSubCategoriaAction.do?menu=sim");

		return retorno;
	}
}
