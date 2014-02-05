package gcom.gui.cadastro.atualizacaocadastral;

import java.util.Collection;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarMovimentoAtualizacaoCadastralAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirConsultarMovimentoAtualizacaoCadastral");

        Fachada fachada = Fachada.getInstancia();

        FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;
        
        Integer idEmpresa = null;
        if (form.getIdEmpresa() != null 
        		&& !form.getIdEmpresa().trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
        	idEmpresa = new Integer(form.getIdEmpresa());
        	
        	FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
        	filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
        	Collection colecaoPesquisa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
        	Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoPesquisa);
        	form.setNomeEmpresa(empresa.getDescricao());
        }
      
        return retorno;
    }
    
}
