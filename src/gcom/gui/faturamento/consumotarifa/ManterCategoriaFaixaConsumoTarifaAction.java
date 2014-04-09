package gcom.gui.faturamento.consumotarifa;

import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
public class ManterCategoriaFaixaConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCategoriaFaixaConsumoTarifa");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessaoFaixa = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		InserirCategoriaFaixaConsumoTarifaActionForm inserirCategoriaFaixaConsumoTarifaActionForm = (InserirCategoriaFaixaConsumoTarifaActionForm) actionForm;
		Collection colecaoFaixa = (Collection) sessaoFaixa
				.getAttribute("colecaoFaixa");
		
		Integer consumoMin = new Integer(
				(String) inserirCategoriaFaixaConsumoTarifaActionForm
						.getLimiteSuperiorFaixa());

		if (consumoMin.toString().equalsIgnoreCase("999999")){
			retorno = actionMapping.findForward("manterCategoriaConsumoTarifa");
		}
		
		
		if (colecaoFaixa == null) {
			colecaoFaixa = new ArrayList();
		}
		ConsumoTarifaFaixa consumoTarifaFaixa = new ConsumoTarifaFaixa();

		Integer i = 0;
		Object[] teste = colecaoFaixa.toArray();
		if (teste.length > 0) {
			ConsumoTarifaFaixa consumoTarifaFaixa2 = (ConsumoTarifaFaixa) teste[teste.length - 1];
			if (colecaoFaixa != null) {
				i = consumoTarifaFaixa2.getNumeroConsumoFaixaFim();
			}
		}

		BigDecimal consumoMinimo = new BigDecimal((String) sessaoFaixa
				.getAttribute("consumoMinimo"));

		Integer limiteSuperFaixa =  new Integer(
				inserirCategoriaFaixaConsumoTarifaActionForm
						.getLimiteSuperiorFaixa());
		
		//boleana para saber se a faixa esta entre as faixas ou não
		boolean entre = false;
		if (!colecaoFaixa.isEmpty()) {
			

			Iterator colecaoFaixaIterator = colecaoFaixa.iterator();
			boolean existeUltimaFaixa = false;
			boolean existeFaixa = false;
			while (colecaoFaixaIterator.hasNext()) {
				ConsumoTarifaFaixa consumoTarifaFaixaLista = (ConsumoTarifaFaixa) colecaoFaixaIterator.next();
					
				if ( (consumoTarifaFaixaLista.getNumeroConsumoFaixaFim().toString().equals(limiteSuperFaixa.toString()))  
						&&
					(consumoTarifaFaixaLista.getNumeroConsumoFaixaFim().toString().equals("999999"))){
					existeUltimaFaixa = true;
				}

				if  (consumoTarifaFaixaLista.getNumeroConsumoFaixaFim().toString().equals(limiteSuperFaixa.toString())){
					existeFaixa = true;
				}
				
				
				if (existeUltimaFaixa){
					throw new ActionServletException(
						"atencao.faixa_limite_superior_existe");
				}
				if (existeFaixa){
					throw new ActionServletException(
						"atencao.faixa.existente");
				}					
				
			}
			
			
			ConsumoTarifaFaixa consumoTarifaFaixaAnteiror = null;
			int indice = 0;
			Iterator colecaoFaixaIt = colecaoFaixa.iterator();
			while (colecaoFaixaIt.hasNext()) {
				ConsumoTarifaFaixa consumoTarifaFaixaAtual = (ConsumoTarifaFaixa) colecaoFaixaIt
						.next();
				/*
				 * if(valorM.compareTo(consumoTarifaFaixa.getValorConsumoTarifa()) <
				 * 0){ throw new ActionServletException(
				 * "atencao.valor_m3_menor2"); }
				 */
				
				//Ana Breda pediu a retirada da atualização
				/*
				if (consumoMin.compareTo(consumoTarifaFaixa2
						.getNumeroConsumoFaixaFim()) <= 0) {
					throw new ActionServletException(
							"atencao.valor_consumoMinimo_menor2");
				}*/
				
		///	if (i){
		//		throw new ActionServletException(
		//			"atencao.faixa_limite_superior_existe");
		//	}				
				
				
				if(indice == 0){//isso significa que primeira ira analisar o consumo minimo em relação a faixa informada
					//signifa que o valor a ser inserido esta entre o consumo minimo e a primeira faixa
					if((consumoMin.intValue() > consumoMinimo.intValue())
						&& (consumoTarifaFaixaAtual.getNumeroConsumoFaixaFim().intValue() > limiteSuperFaixa.intValue())){
						entre = true;
						
						consumoTarifaFaixa.setNumeroConsumoFaixaInicio(new Integer(consumoMinimo.intValue()+1));
						consumoTarifaFaixa.setNumeroConsumoFaixaFim(limiteSuperFaixa);
						consumoTarifaFaixa
								.setValorConsumoTarifa(Util
										.formatarMoedaRealparaBigDecimal(inserirCategoriaFaixaConsumoTarifaActionForm
												.getValorM3Faixa()));
						consumoTarifaFaixa.setUltimaAlteracao(new Date());
						
						consumoTarifaFaixaAtual.setNumeroConsumoFaixaInicio(new Integer(limiteSuperFaixa+1));
						
						//colecaoFaixa.add(consumoTarifaFaixa);
						
					}
				}else{
					
					if((consumoTarifaFaixaAnteiror.getNumeroConsumoFaixaFim().intValue()  < limiteSuperFaixa.intValue())
							&& (consumoTarifaFaixaAtual.getNumeroConsumoFaixaFim().intValue() > limiteSuperFaixa.intValue())
							){
						entre = true;

						consumoTarifaFaixa.setNumeroConsumoFaixaInicio(new Integer(consumoTarifaFaixaAnteiror.getNumeroConsumoFaixaFim().intValue()+1));
						consumoTarifaFaixa.setNumeroConsumoFaixaFim(limiteSuperFaixa);
						consumoTarifaFaixa
								.setValorConsumoTarifa(Util
										.formatarMoedaRealparaBigDecimal(inserirCategoriaFaixaConsumoTarifaActionForm
												.getValorM3Faixa()));
						consumoTarifaFaixa.setUltimaAlteracao(new Date());
						
						consumoTarifaFaixaAtual.setNumeroConsumoFaixaInicio(new Integer(limiteSuperFaixa+1));
					
					}
						

					
				}
				indice++;
				consumoTarifaFaixaAnteiror = consumoTarifaFaixaAtual;
			}

		}

		if (consumoMinimo.compareTo(new BigDecimal(
				inserirCategoriaFaixaConsumoTarifaActionForm
						.getLimiteSuperiorFaixa())) >= 0) {
			throw new ActionServletException(
					"atencao.valor_consumoMinimo_menor");
		}

		//coloca a faixa entre as faixas existente
		
		if(!entre){
			consumoTarifaFaixa.setNumeroConsumoFaixaInicio(new Integer(i + 1));
			consumoTarifaFaixa.setNumeroConsumoFaixaFim(new Integer(
					inserirCategoriaFaixaConsumoTarifaActionForm
							.getLimiteSuperiorFaixa()));
			consumoTarifaFaixa
					.setValorConsumoTarifa(Util
							.formatarMoedaRealparaBigDecimal(inserirCategoriaFaixaConsumoTarifaActionForm
									.getValorM3Faixa()));
			consumoTarifaFaixa.setUltimaAlteracao(new Date());
			
			
			
		}

		colecaoFaixa.add(consumoTarifaFaixa);

		CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelperAtualizacao = (CategoriaFaixaConsumoTarifaHelper) sessaoFaixa
				.getAttribute("categoriaFaixaConsumoTarifaHelperAtualizacao");

		// Organizar a coleção

		/*
		 * Collections.sort((List) colecaoFaixa, new Comparator() { public int
		 * compare(Object a, Object b) { Integer consumo1 =
		 * ((ConsumoTarifaFaixa) a) .getNumeroConsumoFaixaFim(); Integer
		 * consumo2 = ((ConsumoTarifaFaixa) b) .getNumeroConsumoFaixaFim();
		 * 
		 * return consumo1.compareTo(consumo2);
		 *  } });
		 */

		if (categoriaFaixaConsumoTarifaHelperAtualizacao != null) {

			categoriaFaixaConsumoTarifaHelperAtualizacao
					.setColecaoFaixas(colecaoFaixa);
		}

		List listColecaoFaixa = new ArrayList();
		listColecaoFaixa.addAll(colecaoFaixa);

		Collections.sort(listColecaoFaixa, new Comparator() {
			public int compare(Object a, Object b) {
				Integer codigo1 = ((ConsumoTarifaFaixa) a)
						.getNumeroConsumoFaixaFim();
				Integer codigo2 = ((ConsumoTarifaFaixa) b)
						.getNumeroConsumoFaixaFim();

				return codigo1.compareTo(codigo2);
			}
		});

		sessaoFaixa.setAttribute("colecaoFaixa", listColecaoFaixa);

		if (httpServletRequest.getParameter("limpaForm") != null){
			inserirCategoriaFaixaConsumoTarifaActionForm.setLimiteSuperiorFaixa("");
			inserirCategoriaFaixaConsumoTarifaActionForm.setValorM3Faixa("");
		}
		
		
		return retorno;

	}
}
