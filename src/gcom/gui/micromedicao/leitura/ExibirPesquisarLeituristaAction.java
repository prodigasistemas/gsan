package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisar leiturista 
 *
 * @author Francisco Nascimento
 * @date 03/08/2007
 */
public class ExibirPesquisarLeituristaAction extends GcomAction {
	
	/**
	 * [UC0628] Pesquisar Leiturista
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno para a tela de pesquisar leiturista
		ActionForward retorno = actionMapping.findForward("pesquisarLeiturista");
		
		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarLeituristaActionForm form = (PesquisarLeituristaActionForm) actionForm;
		
        //Pesquisando empresas
        if (sessao.getAttribute("colecaoEmpresa") == null){
        	
        	FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEsferaPoder.DESCRICAO);
        	filtroEmpresa.setConsultaSemLimites(true);
        	
        	filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, 
        			Empresa.class.getName());
        	
        	if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "EMPRESA");
        	}
        	
        	sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
        }
        //Fim de pesquisando empresas
		   
		// -------Parte que trata do código quando o usuário tecla enter
		// caso seja o id do cliente
		String idDigitadoEnterCliente = form.getIdCliente();
		String idDigitadoFuncionario = form.getIdFuncionario();
        
		// Verifica se o código foi digitado
		if (idDigitadoEnterCliente != null
				&& !idDigitadoEnterCliente.trim().equals("")) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idDigitadoEnterCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O cliente foi encontrado
				form.setIdCliente("" + ((Cliente) ((List) clienteEncontrado).get(0))
					.getId());
				form.setNomeCliente(((Cliente) ((List) clienteEncontrado).get(0))
					.getNome());
				// idClienteEncontrado no jsp, será verificada a presença deste campo para
				// identificar se algum objeto foi encontrado
				httpServletRequest.setAttribute("idClienteEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo","idCliente");

			} else {

				form.setIdCliente("");
				//httpServletRequest.setAttribute("idClienteNaoEncontrado",
					//	"exception");
				form.setNomeCliente("CLIENTE INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo","DDDTelefone");

			}

		}

		// Verifica se o código foi digitado
		if (idDigitadoFuncionario != null
				&& !idDigitadoFuncionario.trim().equals("")) {
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idDigitadoFuncionario));

			Collection funcionarioEncontrado = fachada.pesquisar(filtroFuncionario,
					Funcionario.class.getName());

			if (funcionarioEncontrado != null && !funcionarioEncontrado.isEmpty()) {
				// O cliente foi encontrado
				form.setIdFuncionario("" + ((Funcionario) ((List) funcionarioEncontrado).get(0))
					.getId());
				form.setNomeFuncionario(((Funcionario) ((List) funcionarioEncontrado).get(0))
					.getNome());
				// idFuncionarioEncontrado no jsp, será verificada a presença deste campo para
				// identificar se algum objeto foi encontrado
				httpServletRequest.setAttribute("idFuncionarioEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo","idCliente");

			} else {

				form.setIdFuncionario("");
				form.setNomeFuncionario("FUNCIONÁRIO INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo","idFuncionario");

			}

		}

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			// Verifica se o tipo da consulta de leiturista é de cliente
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina leiturista_pesquisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta") != null){ 
				 if (httpServletRequest.getParameter("tipoConsulta").equals(
					"cliente")) {
					 form.setIdCliente(httpServletRequest
							 .getParameter("idCampoEnviarDados"));
					 form.setNomeCliente(httpServletRequest
							 .getParameter("descricaoCampoEnviarDados"));
					httpServletRequest.setAttribute("idClienteEncontrado",
						"true");
					 
				 } else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"funcionario")) {
					 form.setIdFuncionario(httpServletRequest
						.getParameter("idCampoEnviarDados"));
					 form.setNomeFuncionario(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
					httpServletRequest.setAttribute("idFuncionarioEncontrado",
						"true");
					 
				 }
			}
		} else {
	        if (httpServletRequest.getParameter("objetoConsulta") == null
					|| httpServletRequest.getParameter("objetoConsulta")
							.equals("")) {

				form.setEmpresa("");
				form.setIdFuncionario("");
				form.setNomeFuncionario("");
				form.setIdCliente("");
				form.setNomeCliente("");
				form.setDDDTelefone("");
				form.setNumeroTelefone("");
				sessao.removeAttribute("caminhoRetornoTelaPesquisa");
				sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
				sessao.removeAttribute("caminhoRetornoTelaPesquisaFuncionario");

			}		
		}
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaLeiturista") != null) {
        	
			sessao.setAttribute(
				"caminhoRetornoTelaPesquisaLeiturista",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaLeiturista"));
		}
		
        //Retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
