package gcom.gui.cadastro;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para informação dos dados
 * para geração do boletim de custo atualizacao cadastral
 * 
 * @author Anderson Italo
 * @date 22/06/2009
 */
public class InformarDadosGeracaoBoletimCustoAtualizacaoCadastralAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("InformarDadosGeracaoBoletimCustoAtualizacaoCadastral");

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//pesquisa as Empresas
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection colecaoEmpresa = fachada.pesquisar(
				filtroEmpresa, Empresa.class.getName());
		if (colecaoEmpresa == null
				|| colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",
					null, "Empresa");
		} else {
			sessao.setAttribute("colecaoEmpresa",
					colecaoEmpresa);
		}
		
		return retorno;
	}

}
