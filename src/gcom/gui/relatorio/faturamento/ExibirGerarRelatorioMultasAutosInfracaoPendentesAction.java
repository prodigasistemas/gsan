package gcom.gui.relatorio.faturamento;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 
 * @author Hugo Azevedo
 */
public class ExibirGerarRelatorioMultasAutosInfracaoPendentesAction extends GcomAction {

   
	
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	

        ActionForward retorno = actionMapping
                .findForward("exibirFiltroRelatorio");

        Fachada fachada = Fachada.getInstancia();
        GerarRelatorioMultasAutosInfracaoPendentesActionForm form = (GerarRelatorioMultasAutosInfracaoPendentesActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Grupos de faturamento
        Collection colecaoGrupos = fachada.obterColecaoGrupoFaturamento();
        sessao.setAttribute("colecaoGrupos", colecaoGrupos);
        
        
        //Pesquisar funcionÃ¡rio
        String pesquisarFuncionario = (String) httpServletRequest.getParameter("pesquisarFuncionario");
        if(pesquisarFuncionario != null && !"".equals(pesquisarFuncionario)){
        	Integer idFuncionario = new Integer(form.getIdFuncionario());
        	FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
        	filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID,idFuncionario));
        	
        	Collection colecaoFunc = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
        	Funcionario funcionario = null;
        	if(colecaoFunc.size() > 0)
        		funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFunc);
        	
        	if(funcionario != null){
        		form.setIdFuncionario(funcionario.getId().toString());
        		form.setDescricaoFuncionario(funcionario.getNome());
        	}
        	else{
        		form.setIdFuncionario("");
        		form.setDescricaoFuncionario("FUNCIONÁRIO INEXISTENTE");
        		httpServletRequest.setAttribute("funcionarioException", "ok");
        	}
        }
        
        return retorno;
    
    }
}
