package gcom.gui.operacional;


import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSetorAbastecimento;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0521]	INSERIR Distrito Operacional
 * 
 * @author Eduardo Bianchi
 * @date 29/01/2007
 */

public class ExibirInserirDistritoOperacionalAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		
		ActionForward retorno = actionMapping.findForward("inserirDistritoOperacional");	
		Fachada fachada = Fachada.getInstancia();
		
		InserirDistritoOperacionalActionForm inserirDistritoOperacionalActionForm = (InserirDistritoOperacionalActionForm) actionForm;
		
		// Sistema Abastecimento		
		FiltroSistemaAbastecimento  filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
		filtroSistemaAbastecimento.adicionarParametro( new ParametroSimples
				(FiltroSistemaAbastecimento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO) );		
		
		Collection colecaoSistemaAbastecimento = fachada.pesquisar(
				filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
				
		if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Sistema Abastecimento");
		}
		
		httpServletRequest.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
		
		Collection colecaoSetorAbastecimento = new ArrayList();
		
		String sistemaAbastecimento = inserirDistritoOperacionalActionForm.getSistemaAbastecimento();
		
		if (sistemaAbastecimento != null && !sistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
		
			// Setor Abastecimento				
			FiltroSetorAbastecimento  filtroSetorAbastecimento = new FiltroSetorAbastecimento();
			filtroSetorAbastecimento.adicionarParametro(new ParametroSimples
					(FiltroSetorAbastecimento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSetorAbastecimento.adicionarParametro(new ParametroSimples
					(FiltroSetorAbastecimento.SISTEMA_ABASTECIMENTO_ID, sistemaAbastecimento));
			
			colecaoSetorAbastecimento = fachada.pesquisar(
					filtroSetorAbastecimento, SetorAbastecimento.class.getName());
			
		} 
		
		httpServletRequest.setAttribute("colecaoSetorAbastecimento", colecaoSetorAbastecimento);
		
		return retorno;
	}
}
