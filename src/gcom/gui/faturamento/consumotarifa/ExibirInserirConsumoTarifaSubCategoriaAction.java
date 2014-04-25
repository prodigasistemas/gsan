package gcom.gui.faturamento.consumotarifa;

import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaVigencia;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.criterion.Order;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirConsumoTarifaSubCategoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirConsumoTarifaSubCategoria");

		Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String limparForm = (String) httpServletRequest.getParameter("limparForm");
		
		InserirConsumoTarifaSubCategoriaActionForm inserirConsumoTarifaSubCategoriaActionForm = (InserirConsumoTarifaSubCategoriaActionForm) actionForm;
		
		if ((sessao.getAttribute("Vigencia") != null)
				&& (! sessao.getAttribute("Vigencia").equals(""))) {
			inserirConsumoTarifaSubCategoriaActionForm.setDataVigencia((String) sessao.getAttribute("Vigencia"));
		}
		
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples(FiltroConsumoTarifa.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
		
		Collection colecaoConsumoTarifa = fachada.pesquisar(filtroConsumoTarifa, ConsumoTarifa.class.getName()); 
		sessao.setAttribute("colecaoConsumoTarifa", colecaoConsumoTarifa);
		/*		
				if (colecaoConsumoTarifa == null || colecaoConsumoTarifa.isEmpty()){
					//...
				}
		*/		
		//sessao.setAttribute("colecaoConsumoTarifa", colecaoConsumoTarifa);
		
		sessao.setAttribute("inserirConsumoTarifaSubCategoriaActionForm", inserirConsumoTarifaSubCategoriaActionForm);
		
		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {
			inserirConsumoTarifaSubCategoriaActionForm.reset(actionMapping,httpServletRequest);
		}	

		if (inserirConsumoTarifaSubCategoriaActionForm.getSlcDescTarifa() != null
				&& !inserirConsumoTarifaSubCategoriaActionForm.getSlcDescTarifa().equalsIgnoreCase("-1")){
			FiltroConsumoTarifaVigencia filtroConsumoTarifaVigencia = new FiltroConsumoTarifaVigencia();
			filtroConsumoTarifaVigencia.adicionarParametro(new ParametroSimples(
					FiltroConsumoTarifaVigencia.CONSUMO_TARIFA, 
					inserirConsumoTarifaSubCategoriaActionForm.getSlcDescTarifa()));
			filtroConsumoTarifaVigencia.setCampoOrderBy(Order.desc("dataVigencia").toString());
			filtroConsumoTarifaVigencia.adicionarCaminhoParaCarregamentoEntidade("consumoTarifa");
			Collection vigencias = fachada.pesquisar(
					filtroConsumoTarifaVigencia, ConsumoTarifaVigencia.class.getName());
			ConsumoTarifaVigencia vigencia = (ConsumoTarifaVigencia) vigencias.iterator().next();
			String dataVigencia = Util.formatarData(vigencia.getDataVigencia());
			inserirConsumoTarifaSubCategoriaActionForm.setDataVigencia(dataVigencia);
			sessao.setAttribute("vigencia", dataVigencia);
			sessao.setAttribute("select", inserirConsumoTarifaSubCategoriaActionForm.getSlcDescTarifa());
		} else {
			sessao.removeAttribute("vigencia");
			sessao.removeAttribute("select");
			inserirConsumoTarifaSubCategoriaActionForm.setDataVigencia("");
		}
		
		
		
		
		return retorno;

	}

}
