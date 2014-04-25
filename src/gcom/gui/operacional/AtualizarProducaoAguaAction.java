package gcom.gui.operacional;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.ProducaoAgua;
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

public class AtualizarProducaoAguaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarProducaoAguaActionForm atualizarProducaoAguaActionForm = (AtualizarProducaoAguaActionForm) actionForm;

		ProducaoAgua producaoAgua = (ProducaoAgua) sessao.getAttribute("atualizarProducaoAgua");
		
		Collection colecaoPesquisa = null;
		String localidadeID = atualizarProducaoAguaActionForm.getLocalidadeID();
		String volumeProduzido = atualizarProducaoAguaActionForm.getVolumeProduzido();
		
		String anoMesReferencia = atualizarProducaoAguaActionForm.getAnoMesReferencia();
        if(anoMesReferencia == null){
        	anoMesReferencia = (String)sessao.getAttribute("mesAno");
        }
		
		
				
		//	Concatena ano mes para insercao
        String mes = anoMesReferencia.substring(0, 2);
        String ano = anoMesReferencia.substring(3, 7);

        anoMesReferencia = ano + "" + mes;
		
        
        if (localidadeID == null || localidadeID.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.required", null, "Localidade");
        }
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, localidadeID));

		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO,
		        ConstantesSistema.INDICADOR_USO_ATIVO));

		//Retorna distrito Operacional
		colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
				Localidade.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		    throw new ActionServletException(
		            "atencao.pesquisa.localidade_inexistente");
		}
        
        
        
        producaoAgua.setVolumeProduzido(Util.formatarMoedaRealparaBigDecimal(volumeProduzido));
        producaoAgua.setAnoMesReferencia(new Integer(anoMesReferencia));
        
        Localidade localidade = new Localidade();
        localidade.setId(new Integer(atualizarProducaoAguaActionForm.getLocalidadeID()));
        
        producaoAgua.setLocalidade(localidade);
    	
		fachada.atualizar(producaoAgua);

		montarPaginaSucesso(httpServletRequest, "Produção de Água "
				+ atualizarProducaoAguaActionForm.getId().toString() + " atualizada com sucesso.",
				"Realizar outra Manutenção de Produção de Água ",
				"exibirFiltrarProducaoAguaAction.do?menu=sim");        
        
		return retorno;
	}
}
