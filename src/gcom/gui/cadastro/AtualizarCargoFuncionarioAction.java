package gcom.gui.cadastro;

import gcom.cadastro.funcionario.FiltroFuncionarioCargo;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarCargoFuncionarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCargoFuncionarioActionForm atualizarCargoFuncionarioActionForm = (AtualizarCargoFuncionarioActionForm) actionForm;

		FuncionarioCargo funcionarioCargo= (FuncionarioCargo) sessao.getAttribute("atualizarCargoFuncionario");
		
		Collection colecaoPesquisa = null;
		
		if(atualizarCargoFuncionarioActionForm.getCodigo()!= null 
				&& !atualizarCargoFuncionarioActionForm.getCodigo().equals("")){
			funcionarioCargo.setCodigo(new Integer(atualizarCargoFuncionarioActionForm.getCodigo()));
		}else{
			funcionarioCargo.setCodigo(null);
		}
		funcionarioCargo.setDescricao(atualizarCargoFuncionarioActionForm.getDescricao());
		funcionarioCargo.setDescricaoAbreviada(atualizarCargoFuncionarioActionForm.getDescricaoAbreviada());
		funcionarioCargo.setIndicadorUso(new Short (atualizarCargoFuncionarioActionForm.getIndicadorUso()));
		
		String codigoFuncionarioCargo= atualizarCargoFuncionarioActionForm.getCodigo();		
        String descricaoFuncionarioCargo = atualizarCargoFuncionarioActionForm.getDescricao();
        String descricaoAbreviadaFuncionarioCargo = atualizarCargoFuncionarioActionForm.getDescricaoAbreviada();
        Short indicadorUsoFuncionarioCargo  = atualizarCargoFuncionarioActionForm.getIndicadorUso();
        
		
        funcionarioCargo.setDescricao(descricaoFuncionarioCargo);
        
        funcionarioCargo.setDescricaoAbreviada(descricaoAbreviadaFuncionarioCargo);
        if(codigoFuncionarioCargo != null && !codigoFuncionarioCargo.equals("")){
        	funcionarioCargo.setCodigo(new Integer(codigoFuncionarioCargo));
        }else{
        	funcionarioCargo.setCodigo(null);
        }
        
        funcionarioCargo.setIndicadorUso(new Short(indicadorUsoFuncionarioCargo));

        funcionarioCargo.setUltimaAlteracao(new Date());
        

		FiltroFuncionarioCargo filtroFuncCargo = new FiltroFuncionarioCargo();
			filtroFuncCargo.adicionarParametro(
				new ParametroSimples(FiltroFuncionarioCargo.DESCRICAO,descricaoFuncionarioCargo));
			filtroFuncCargo.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroFuncionarioCargo.ID, funcionarioCargo.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroFuncCargo, FuncionarioCargo.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricaoFuncionarioCargo);
		}
		
		
		FiltroFuncionarioCargo filtroFuncionarioCargo = new FiltroFuncionarioCargo();
			filtroFuncionarioCargo.adicionarParametro(
					new ParametroSimples(FiltroFuncionarioCargo.CODIGO,codigoFuncionarioCargo));
			filtroFuncionarioCargo.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroFuncionarioCargo.ID, funcionarioCargo.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroFuncionarioCargo, FuncionarioCargo.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigoFuncionarioCargo+"");
		}
		
		fachada.atualizar(funcionarioCargo);

		
		montarPaginaSucesso(httpServletRequest, "Cargo do Funcionário "
				+ descricaoFuncionarioCargo + " atualizado com sucesso.",
				"Realizar outra Manutenção de Cargo do Funcionário ",
				"exibirFiltrarCargoFuncionarioAction.do?menu=sim");        
        
		return retorno;
	}
}
