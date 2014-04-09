package gcom.gui.cadastro;


import gcom.cadastro.funcionario.FiltroFuncionarioCargo;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirCargoFuncionarioAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um cargo funcionário
	 * 
	 * [UC0834] Inserir Cargo Funcionário
	 * 
	 * @author Arthur Carvalho
	 * @date 11/08/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirCargoFuncionarioActionForm inserirCargoFuncionarioActionForm = (InserirCargoFuncionarioActionForm) actionForm;

		Integer codigo = null;
		if(inserirCargoFuncionarioActionForm.getCodigo() != null && 
				!inserirCargoFuncionarioActionForm.getCodigo().equals("")){
		  codigo = Util.converterStringParaInteger(inserirCargoFuncionarioActionForm.getCodigo());
		}
		String descricao = inserirCargoFuncionarioActionForm.getDescricao();
		String descricaoAbreviada = inserirCargoFuncionarioActionForm.getDescricaoAbreviada();
		Collection colecaoPesquisa = null;

		
		// Descrição
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		FiltroFuncionarioCargo filtroFuncCargo = new FiltroFuncionarioCargo();
		filtroFuncCargo.adicionarParametro(
				new ParametroSimples(FiltroFuncionarioCargo.DESCRICAO,descricao));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroFuncCargo, FuncionarioCargo.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		
		FiltroFuncionarioCargo filtroFuncionarioCargo = new FiltroFuncionarioCargo();
			filtroFuncionarioCargo.adicionarParametro(
					new ParametroSimples(FiltroFuncionarioCargo.CODIGO,codigo));
					
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroFuncionarioCargo, FuncionarioCargo.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigo+"");
			
		}
		

			FuncionarioCargo funcionarioCargo= new FuncionarioCargo();
			funcionarioCargo.setCodigo(codigo);
			funcionarioCargo.setDescricao(descricao);
			funcionarioCargo.setDescricaoAbreviada(descricaoAbreviada);
			funcionarioCargo.setUltimaAlteracao(new Date());
			funcionarioCargo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

			Integer idFuncionarioCargo = (Integer) this.getFachada().inserir(funcionarioCargo);

			montarPaginaSucesso(httpServletRequest,
					"Cargo do Funcionário " + descricao
							+ " inserido com sucesso.",
					"Inserir outro Cargo do Funcionário",
					"exibirInserirCargoFuncionarioAction.do?menu=sim",
					"exibirAtualizarCargoFuncionarioAction.do?idRegistroAtualizacao="
							+ idFuncionarioCargo,
					"Atualizar Cargo do Funcionário Inserido");

			this.getSessao(httpServletRequest).removeAttribute("InserirCargoFuncionarioActionForm");

			return retorno;
		
		
	}
}		
