package gcom.gui.cadastro.funcionario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionarioCargo;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 * @date 04/04/2007
 *
 */
public class ExibirInserirFuncionarioAction extends GcomAction {
	
	/**
	 * Este caso de uso permite a inclusão de um novo Funcionario
	 * 
	 * [UC0842] Inserir Funcionario
	 * 
	 * @author Rômulo Aurélio
	 * @date 04/04/2007
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

		ActionForward retorno = actionMapping
				.findForward("inserirFuncionario");

		Fachada fachada = Fachada.getInstancia();

		InserirFuncionarioActionForm form = (InserirFuncionarioActionForm) actionForm;
		
		httpServletRequest.setAttribute("nomeCampo", "matricula");

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
				Empresa.class.getName());

		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Empresa");
		}

		httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		
		FiltroFuncionarioCargo filtroFuncionarioCargo = new FiltroFuncionarioCargo();
		filtroFuncionarioCargo.setCampoOrderBy(FiltroFuncionarioCargo.DESCRICAO);
		filtroFuncionarioCargo.adicionarParametro(new ParametroSimples(FiltroFuncionarioCargo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<FuncionarioCargo> colecaoFuncionarioCargo = fachada.pesquisar(filtroFuncionarioCargo, FuncionarioCargo.class.getName());
		httpServletRequest.setAttribute("colecaoFuncionarioCargo", colecaoFuncionarioCargo);
		

		//-------Parte que trata do código quando o usuário tecla enter        

        String idDigitadoEnterUnidadeEmpresa = form.getIdUnidade();
        //Verifica se o código da Unidade Empresa foi digitado
        if (idDigitadoEnterUnidadeEmpresa != null
				&& !idDigitadoEnterUnidadeEmpresa.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterUnidadeEmpresa) > 0) {
        	FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

        	filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
        			FiltroUnidadeOrganizacional.ID, idDigitadoEnterUnidadeEmpresa));
			
			Collection<UnidadeOrganizacional> unidadeEmpresaEncontrada = fachada.pesquisar(filtroUnidadeEmpresa,
					UnidadeOrganizacional.class.getName());

			if (unidadeEmpresaEncontrada != null && !unidadeEmpresaEncontrada.isEmpty()) {
				//a unidade de Unidade Empresa foi encontrado
				form.setIdUnidade(""
						+ ((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada).get(0))
								.getId());
				form
						.setNomeUnidade(((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada)
								.get(0)).getDescricao());
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "idUnidade");
				

			} else {

				form.setIdUnidade("");
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada",
						"exception");
				form
						.setNomeUnidade("UNIDADE EMPRESA INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "idUnidade");

			}

        }
        //-------Fim de parte que trata do código quando o usuário tecla enter
        
        //-------------- bt DESFAZER ---------------
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	form.setMatricula("");
        	form.setDescricaoCargo("");
        	form.setEmpresa(""+ConstantesSistema.NUMERO_NAO_INFORMADO);
        	form.setIdUnidade("");
        	form.setNomeUnidade("");
        	form.setNome("");

        }
	// Fim------------Desfazer--------------------
		
        //DATA CORRENTE
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        httpServletRequest.setAttribute("dataAtual", formatoData
        .format(dataCorrente.getTime()));
        
        //IDADE MÍNIMA
        httpServletRequest.setAttribute("idadeMinimaFuncionario", ConstantesSistema.IDADE_MINIMA_FUNCIONARIO);
        
		return retorno;

	}

}
