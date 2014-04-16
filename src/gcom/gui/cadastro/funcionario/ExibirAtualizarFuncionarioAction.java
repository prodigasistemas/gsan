package gcom.gui.cadastro.funcionario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.FiltroFuncionarioCargo;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 *
 */
public class ExibirAtualizarFuncionarioAction extends GcomAction{
	/**
	 * 
	 * [UC0844] Manter Funcionário
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 16/04/2007
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarFuncionario");

		AtualizarFuncionarioActionForm form = (AtualizarFuncionarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		// Verifica se veio do filtrar ou do manter
		
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}


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
				httpServletRequest.setAttribute("nomeCampo", "descricaoUnidadeEmpresa");
				

			} else {

				form.setIdUnidade("");
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada",
						"exception");
				form
						.setNomeUnidade("UNIDADE EMPRESA INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeEmpresa");

			}

        }
        //-------Fim de parte que trata do código quando o usuário tecla enter
		
        //Verifica se o usuario informou a unidade organizacional e teclou enter
        if(httpServletRequest.getParameter("enter") == null){
        	
		// Verifica se o funcionario já está na sessão, em caso afirmativo
		// significa que o usuário já entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// está entrando pela primeira vez

			if (sessao.getAttribute("objetoFuncionario") != null) {

				Funcionario funcionario = (Funcionario) sessao
						.getAttribute("objetoFuncionario");
				sessao.setAttribute("idFuncionario", funcionario.getId()
						.toString());

				form.setMatricula(funcionario.getId().toString());
				form.setIdFuncionario(funcionario.getId().toString());
				
				form.setFuncionarioCargo(funcionario.getFuncionarioCargo().getId().toString());
				
				form.setNome(funcionario.getNome());
				
				form.setEmpresa(funcionario.getEmpresa().getId().toString());
				
				form.setIdUnidade(funcionario.getUnidadeOrganizacional().getId().toString());
				
				form.setNomeUnidade(funcionario.getUnidadeOrganizacional().getDescricao());
				
				form.setNumeroCpf(funcionario.getNumeroCpf());
				
				form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
                
				sessao.setAttribute("funcionarioAtualizar", funcionario);
				
				sessao.removeAttribute("objetoFuncionario");

			} else {

				String idFuncionario = null;

				if (httpServletRequest.getParameter("idFuncionario") == null
						|| httpServletRequest.getParameter("idFuncionario")
								.equals("")) {
					idFuncionario = (String) sessao
							.getAttribute("idFuncionario");
				} else {
					idFuncionario = (String) httpServletRequest
							.getParameter("idFuncionario");
					sessao.setAttribute("idFuncionario", idFuncionario);
				}

				httpServletRequest.setAttribute("idFuncionario",
						idFuncionario);
				sessao.setAttribute("idFuncionario",idFuncionario);
				
				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");
				filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
				
				filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));

				Collection<Funcionario> colecaoFuncionario = fachada
						.pesquisar(filtroFuncionario, Funcionario.class
								.getName());

				if (colecaoFuncionario == null
						|| colecaoFuncionario.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoFuncionario",
						colecaoFuncionario);

				Funcionario funcionario = (Funcionario) colecaoFuncionario
						.iterator().next();

				form.setMatricula(funcionario.getId().toString());
				
				form.setIdFuncionario(funcionario.getId().toString());
				
				form.setNome(funcionario.getNome());
				
				form.setFuncionarioCargo(funcionario.getFuncionarioCargo().getId().toString());
				
				form.setEmpresa(funcionario.getEmpresa().getId().toString());
				
				form.setIdUnidade(funcionario.getUnidadeOrganizacional().getId().toString());
				
				form.setNomeUnidade(funcionario.getUnidadeOrganizacional().getDescricao());
				
				form.setNumeroCpf(funcionario.getNumeroCpf());
				
				form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
				
				sessao.setAttribute("funcionarioAtualizar", funcionario);
							
			}

        }
		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			String idFuncionario = null;

			if (httpServletRequest.getParameter("idFuncionario") == null
					|| httpServletRequest.getParameter("idFuncionario")
							.equals("")) {
				idFuncionario = (String) sessao
						.getAttribute("idFuncionario");
			} else {
				idFuncionario = (String) httpServletRequest
						.getParameter("idFuncionario");
				sessao.setAttribute("idFuncionario", idFuncionario);
			}

			if (idFuncionario.equalsIgnoreCase("")) {
				idFuncionario = null;
			}

			if (idFuncionario == null) {

				Funcionario funcionario = (Funcionario) sessao
						.getAttribute("objetoFuncionario");

				form.setMatricula(funcionario.getId().toString());

				form.setFuncionarioCargo(funcionario.getFuncionarioCargo().getId().toString());
				
				form.setNome(funcionario.getNome());
				
				form.setEmpresa(funcionario.getEmpresa().getId().toString());
				
				form.setIdUnidade(funcionario.getUnidadeOrganizacional().getId().toString());
				
				form.setNomeUnidade(funcionario.getUnidadeOrganizacional().getDescricao());
				
				form.setNumeroCpf(funcionario.getNumeroCpf());
				
				form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
				
				sessao.setAttribute("funcionarioAtualizar", funcionario);
				sessao.removeAttribute("funcionario");
			}



			if (idFuncionario != null) {

				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				
				filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");
				
				filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
				
				
				filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));

				Collection<Funcionario> colecaoFuncionario = fachada.pesquisar(filtroFuncionario, Funcionario.class
								.getName());

				if (colecaoFuncionario == null
						|| colecaoFuncionario.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoFuncionario",
						colecaoFuncionario);

				Funcionario funcionario = (Funcionario) colecaoFuncionario
						.iterator().next();

				form.setMatricula(funcionario.getId().toString());

				form.setFuncionarioCargo(funcionario.getFuncionarioCargo().getId().toString());
		
				form.setNome(funcionario.getNome());
		
				form.setEmpresa(funcionario.getEmpresa().getId().toString());
		
				form.setIdUnidade(funcionario.getUnidadeOrganizacional().getId().toString());
		
				form.setNomeUnidade(funcionario.getUnidadeOrganizacional().getDescricao());
				
				form.setNumeroCpf(funcionario.getNumeroCpf());
				
				form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
			}
		}
		// -------------- bt DESFAZER ---------------
			
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
