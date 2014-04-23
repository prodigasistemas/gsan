package gcom.gui.faturamento.autoinfracao;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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

/**
 * Action que faz a exibição da tela para o usuário setar os campos e permitir
 * que ele gere o relatório de auto de infração 
 * [UC0857] Gerar Relatório de Arrecadação das Multas de Autos de Infração
 * 
 * @author Rafael Corrêa
 * @since 08/09/2008
 */
public class ExibirGerarRelatorioAutoInfracaoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioAutoInfracao");
		
		GerarRelatorioAutoInfracaoActionForm gerarRelatorioAutoInfracaoActionForm = (GerarRelatorioAutoInfracaoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Carrega as coleções que serão exibidas na tela
		
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeNegocio",
						colecaoUnidadeNegocio);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"UNIDADE_NEGOCIO");
			}
		}
		
		String idFuncionario = gerarRelatorioAutoInfracaoActionForm.getIdFuncionario();
		
		// Pesquisa o funcionário
		if (idFuncionario != null && !idFuncionario.trim().equals("")) {
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));
			
			Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
			
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);
				gerarRelatorioAutoInfracaoActionForm.setIdFuncionario(funcionario.getId().toString());
				gerarRelatorioAutoInfracaoActionForm.setNomeFuncionario(funcionario.getNome());
				
				httpServletRequest.setAttribute("nomeCampo", "dataPagamentoInicio");
			} else {
				gerarRelatorioAutoInfracaoActionForm.setIdFuncionario("");
				gerarRelatorioAutoInfracaoActionForm.setNomeFuncionario("FUNCIONÁRIO INEXISTENTE");
				
				httpServletRequest.setAttribute("funcionarioInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
			}
			
		} else {
			gerarRelatorioAutoInfracaoActionForm.setNomeFuncionario("");
		}

		return retorno;

	}

}
