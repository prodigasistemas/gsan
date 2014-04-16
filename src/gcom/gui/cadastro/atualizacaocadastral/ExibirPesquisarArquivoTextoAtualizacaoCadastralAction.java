package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroSituacaoTransmissaoLeitura;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
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
 * Action que define o pré-processamento da página de pesquisar arquivo texto 
 *
 * @author Ana Maria
 * @date 10/06/2009
 */
public class ExibirPesquisarArquivoTextoAtualizacaoCadastralAction extends GcomAction {
	
	/**
	 * [UC0000] Pesquisar Arquivo Texto para Atualização Cadastral
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
		ActionForward retorno = actionMapping.findForward("pesquisarArquivoTextoAtualizacaoCadastral");
		
		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarArquivoTextoAtualizacaoCadastralActionForm form = (PesquisarArquivoTextoAtualizacaoCadastralActionForm) actionForm;
		
        if (httpServletRequest.getParameter("limpaForm") != null){
        	form.setDescricao("");
        	form.setIdLeiturista("");
        	form.setNomeLeiturista("");
        	form.setIdSituacaoTransmissao("");
        }
		
        //Pesquisando situação da transmissão do arquivo
        if (sessao.getAttribute("colecaoSituacaoTransmissaoLeitura") == null){
        	
        	FiltroSituacaoTransmissaoLeitura filtroSituacaoTransmissaoLeitura = new FiltroSituacaoTransmissaoLeitura();
        	
        	filtroSituacaoTransmissaoLeitura.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoSituacaoTransmissaoLeitura = fachada.pesquisar(filtroSituacaoTransmissaoLeitura, 
        			SituacaoTransmissaoLeitura.class.getName());
        	
        	if (colecaoSituacaoTransmissaoLeitura == null || colecaoSituacaoTransmissaoLeitura.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Situação da Transmissão do Arquivo");
        	}
        	
        	sessao.setAttribute("colecaoSituacaoTransmissaoLeitura", colecaoSituacaoTransmissaoLeitura);
        }
        //Fim de pesquisando empresas
		   
		// -------Parte que trata do código quando o usuário tecla enter
        
        if(httpServletRequest.getParameter("objetoConsulta") != null){

			String idDigitadoAgenteCadastral = form.getIdLeiturista();
	
			//Recupera o leiturista informado pelo usuário
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.ID, idDigitadoAgenteCadastral));
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection leituristaEncontrado = fachada.pesquisar(filtroLeiturista,
					Leiturista.class.getName());
			
			//Caso o leiturista informado pelo usuário esteja cadastrado no sistema
			//Seta os dados do leiturista no form
			//Caso contrário seta as informações de leiturista para vazio 
			//e indica ao usuário que o leiturista não existe 
			
			if (leituristaEncontrado != null && leituristaEncontrado.size() > 0) {
				//leiturista foi encontrado
				Leiturista leiturista = (Leiturista) ((List) leituristaEncontrado).get(0); 
				form.setIdLeiturista("" + leiturista.getId());
				if (leiturista.getFuncionario() != null){
					form.setNomeLeiturista(leiturista.getFuncionario().getNome());					
				} else if (leiturista.getCliente() != null){
					form.setNomeLeiturista(leiturista.getCliente().getNome());
				}
				httpServletRequest.setAttribute("idLeituristaEncontrado","true");
				httpServletRequest.setAttribute("nomeCampo","idSituacaoTransmissao");
			} else {
				//o leiturista não foi encontrado
				form.setIdLeiturista("");
				form.setNomeLeiturista("LEITURISTA INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo","idLeiturista");
			}
        }
        
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			// Verifica se o tipo da consulta de leiturista é de cliente
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina leiturista_pesquisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta") != null){ 
				if (httpServletRequest.getParameter("tipoConsulta").equals(
					"leiturista")) {
					 form.setIdLeiturista(httpServletRequest
						.getParameter("idCampoEnviarDados"));
					 form.setNomeLeiturista(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
					httpServletRequest.setAttribute("idLeituristaEncontrado",
						"true");
					 
				 }
			}
		} else {
	        if (httpServletRequest.getParameter("objetoConsulta") == null
					|| httpServletRequest.getParameter("objetoConsulta")
							.equals("")) {

				form.setDescricao("");
				form.setIdLeiturista("");
				form.setIdSituacaoTransmissao("");
				form.setNomeLeiturista("");
				sessao.removeAttribute("caminhoRetornoTelaPesquisa");
				sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
				sessao.removeAttribute("caminhoRetornoTelaPesquisaFuncionario");

			}		
		}
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaArquivoTextoAtualizacaoCadastral") != null) {
        	
			sessao.setAttribute(
				"caminhoRetornoTelaPesquisaArquivoTextoAtualizacaoCadastral",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaArquivoTextoAtualizacaoCadastral"));
		}
		
        //Retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
