package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.ZonaPressao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarZonaPressaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarZonaPressaoActionForm atualizarZonaPressaoActionForm = (AtualizarZonaPressaoActionForm) actionForm;

		ZonaPressao zonaPressao = (ZonaPressao) sessao.getAttribute("atualizarZonaPressao");
		
		Collection colecaoPesquisa = null;
		
        String descricaoZonaPressao = atualizarZonaPressaoActionForm.getDescricao();
        String descricaoAbreviadaZonaPressao = atualizarZonaPressaoActionForm.getDescricaoAbreviada();    
        String indicadordeUso = atualizarZonaPressaoActionForm.getIndicadorUso();
        String distritoOperacionalID = atualizarZonaPressaoActionForm.getDistritoOperacionalID();
        
        //Distrito Operacional é obrigatório.

        if (distritoOperacionalID == null || distritoOperacionalID.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.required", null, "Distrito Operacional");
        }
		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.ID, distritoOperacionalID));

		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.INDICADORUSO,
		        ConstantesSistema.INDICADOR_USO_ATIVO));

		//Retorna distrito Operacional
		colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
				DistritoOperacional.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		    throw new ActionServletException(
		            "atencao.pesquisa.distrito_operacional_inexistente");
		}

        
    zonaPressao.setDescricaoZonaPressao(atualizarZonaPressaoActionForm.getDescricao());
	zonaPressao.setDescricaoAbreviada(atualizarZonaPressaoActionForm.getDescricaoAbreviada());
	zonaPressao.setIndicadorUso(new Short (atualizarZonaPressaoActionForm.getIndicadorUso()));
	
    DistritoOperacional distritoOperacional = new DistritoOperacional();
    	distritoOperacional.setId(new Integer(atualizarZonaPressaoActionForm.getDistritoOperacionalID()));
    zonaPressao.setDistritoOperacional(distritoOperacional);
    
    zonaPressao.setDescricaoZonaPressao(descricaoZonaPressao);
        
	// Caso não tenha sido preenchida a Descrição Abreviada, no banco ficará null
    if(atualizarZonaPressaoActionForm.getDescricaoAbreviada() != null 
			&& !atualizarZonaPressaoActionForm.getDescricaoAbreviada().equals("")){
        
		zonaPressao.setDescricaoAbreviada(descricaoAbreviadaZonaPressao);
	
	} else {
	
		zonaPressao.setDescricaoAbreviada(null);
	
	}
        
   	// Seta a data da alteração
    zonaPressao.setUltimaAlteracao( new Date() );	
    
    // Seta o Indicador de Uso
    zonaPressao.setIndicadorUso( new Short(indicadordeUso));
	
	fachada.atualizar(zonaPressao);

	montarPaginaSucesso(httpServletRequest, "Zona de Pressão "
			+ atualizarZonaPressaoActionForm.getId().toString() + " atualizado com sucesso.",
			"Realizar outra Manutenção de Zona de Pressão ",
			"exibirFiltrarZonaPressaoAction.do?menu=sim");        
    
	return retorno;
	}
}
