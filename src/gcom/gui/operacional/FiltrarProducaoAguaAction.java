package gcom.gui.operacional;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroProducaoAgua;
import gcom.operacional.ProducaoAgua;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0813]Filtrar Producao de Agua
 * 
 * @author Vinícius Medeiros
 * @date 10/06/2008
 */

public class FiltrarProducaoAguaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterProducaoAgua");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarProducaoAguaActionForm filtrarProducaoAguaActionForm = (FiltrarProducaoAguaActionForm) actionForm;

		FiltroProducaoAgua filtroProducaoAgua = new FiltroProducaoAgua();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarProducaoAguaActionForm.getId();
		String anoMesReferencia = filtrarProducaoAguaActionForm.getAnoMesReferencia();
		String localidadeID = filtrarProducaoAguaActionForm.getLocalidadeID();
		String volumeProduzido = filtrarProducaoAguaActionForm
				.getVolumeProduzido();
		
		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroProducaoAgua.adicionarParametro(new ParametroSimples(
						FiltroProducaoAgua.ID, id));
			}
		}
		
		// Ano Mes Referencia
	    if(anoMesReferencia != null && !anoMesReferencia.trim().equals("")){
	       	Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(anoMesReferencia);
	       	peloMenosUmParametroInformado = true;	
	       	filtroProducaoAgua.adicionarParametro(new ParametroSimples(
	     			FiltroProducaoAgua.MES_ANO_REFERENCIA, anoMes));
	       	
	    }
		// Localidade
		if (localidadeID != null && !localidadeID.equalsIgnoreCase("")) {
			
			pesquisarLocalidade(filtrarProducaoAguaActionForm,fachada,localidadeID);
			
			peloMenosUmParametroInformado = true;
			filtroProducaoAgua.adicionarParametro(new ParametroSimples(
					FiltroProducaoAgua.ID_LOCALIDADE, new Integer(localidadeID)));
		}
	       
		// Volume Produzido
		if (volumeProduzido != null && !volumeProduzido.equals("") && !volumeProduzido.trim().equals(BigDecimal.ZERO)) {
			BigDecimal volume = Util.formatarMoedaRealparaBigDecimal(volumeProduzido);
			peloMenosUmParametroInformado = true;
			filtroProducaoAgua.adicionarParametro(new ParametroSimples(
								FiltroProducaoAgua.VOLUME_PRODUZIDO, volume));
		} 
		
		
		Collection colecaoProducaoAgua = fachada
				.pesquisar(filtroProducaoAgua, ProducaoAgua.class
						.getName());

		// Verificar a existencia de uma atividade
		if (colecaoProducaoAgua.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Produção de Água");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoProducaoAgua == null
				|| colecaoProducaoAgua.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Produção de Água");
		} else {
			httpServletRequest.setAttribute("colecaoProducaoAgua",
					colecaoProducaoAgua);
			ProducaoAgua producaoAgua = new ProducaoAgua();
			producaoAgua = (ProducaoAgua) Util
					.retonarObjetoDeColecao(colecaoProducaoAgua);
			String idRegistroAtualizacao = producaoAgua.getId().toString();
			
			httpServletRequest.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroProducaoAgua", filtroProducaoAgua);

		httpServletRequest.setAttribute("filtroProducaoAgua",
				filtroProducaoAgua);

		return retorno;

	}
	private void pesquisarLocalidade(
    		FiltrarProducaoAguaActionForm filtrarProducaoAguaActionForm,
            Fachada fachada, String localidadeID) {
	 
		Collection colecaoPesquisa;
	 
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(new ParametroSimples(
		 FiltroLocalidade.ID, localidadeID));

		 //Retorna localidade
		 colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
		         Localidade.class.getName());
		
		 if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	
			 //Localidade nao encontrada
		     //Limpa o campo localidadeID do formulário
			 filtrarProducaoAguaActionForm.setLocalidadeID("");
			 filtrarProducaoAguaActionForm.setLocalidadeDescricao("Localidade inexistente.");
		 	
		 	throw new ActionServletException("atencao.pesquisa_inexistente",
						null,"Localidade");	
		
		 }else {
		     Localidade objetoLocalidade = (Localidade) Util
		     .retonarObjetoDeColecao(colecaoPesquisa);
		     filtrarProducaoAguaActionForm.setLocalidadeID(String
			            .valueOf(objetoLocalidade.getId()));
		     filtrarProducaoAguaActionForm
			            .setLocalidadeDescricao(objetoLocalidade.getDescricao());
			
		}

	}
}
