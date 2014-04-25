package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
public class ExibirInserirReajusteConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirReajusteConsumoTarifa");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String idRecuperado = httpServletRequest.getParameter("id_r");

		String[] ids = idRecuperado.split(",");
		
		if ( ids == null || ids[0].equals("") ) {
			throw new ActionServletException("atencao.selecione_ao_menos_uma_tarifa_consumo");
		}

		// CRIACOES DE COLECOES....

		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		Collection colecaoCategoria = (Collection) fachada.pesquisar(filtroCategoria, Categoria.class.getName());
		
		Collection colecaoIdsConsumoTarifa = new ArrayList();
		
		//dentre todas as vigencia, ira procurar se existe alguma com o mesmo Consumo TArifa
		for (int i = 0; i < ids.length; i++) {
			String idConsumoTarifaVigencia = ids[i];
			
			FiltroConsumoTarifaVigencia filtroConsumoTarifaVigencia = new FiltroConsumoTarifaVigencia();
			filtroConsumoTarifaVigencia.adicionarParametro(new ParametroSimples(FiltroConsumoTarifaVigencia.ID,idConsumoTarifaVigencia));
			filtroConsumoTarifaVigencia.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaVigencia.CONSUMO_TARIFA);
			
			Collection colecaoConsumoTarifaVigencia = (Collection) fachada.pesquisar(filtroConsumoTarifaVigencia, ConsumoTarifaVigencia.class.getName());	

			ConsumoTarifaVigencia consumoTarifaVigencia = (ConsumoTarifaVigencia) colecaoConsumoTarifaVigencia.iterator().next();
			
			
			adicionarConsumoTarifa(colecaoIdsConsumoTarifa,consumoTarifaVigencia.getConsumoTarifa().getId().toString());
			
			colecaoIdsConsumoTarifa.add(consumoTarifaVigencia.getConsumoTarifa().getId().toString());
		}
		
		
		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
		sessao.setAttribute("ids", ids);
		
		
		return retorno;
	}
	
	/**
	 * 
	 * UC00169 - Manter Tarifa de Consumo
	 *
	 * [FS0010] - Verificar reajuster para mais de uma vigêmcia da mesma tarifa
	 *
	 * @author Rafael Santos
	 * @date 13/07/2006
	 *
	 * @param colecaoIdsConsumoTarifa
	 * @param idConsumoTarifa
	 */
	public void adicionarConsumoTarifa(Collection colecaoIdsConsumoTarifa,String idConsumoTarifa){

		int existe = 0;
		
		if(colecaoIdsConsumoTarifa != null && !colecaoIdsConsumoTarifa.isEmpty()){
			
			Iterator iteratorColecaoIdsConsumoTarifa = colecaoIdsConsumoTarifa.iterator();
			
			
			while (iteratorColecaoIdsConsumoTarifa.hasNext()) {
				String id = (String) iteratorColecaoIdsConsumoTarifa.next();
				
				if(id.equalsIgnoreCase(idConsumoTarifa)){
					existe  = 1 + 1;
		            

				}
			}
			
		}
		
		if(existe > 1){
			throw new ActionServletException("atencao.consumo_tarifa_vigencia.mesma.consumo_tarifa");
		}
		
	
	}
	
}
