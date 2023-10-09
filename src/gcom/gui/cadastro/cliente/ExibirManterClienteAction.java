package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
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
 * Action para a pré-exibição da página de Manter Cliente
 * 
 * @author rodrigo
 */
public class ExibirManterClienteAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("manterCliente");

		Fachada fachada = Fachada.getInstancia();

		Collection<Cliente> clientes = null;

		//Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Parte da verificação do filtro
		FiltroCliente filtroCliente = null;
		
		String codigo  = (String) sessao.getAttribute("codigo");
		String cpf  = (String) sessao.getAttribute("cpf");
		String rg  = (String) sessao.getAttribute("rg");
		String cnpj  = (String) sessao.getAttribute("cnpj");
		String nome  = (String) sessao.getAttribute("nome");
		String nomeMae  = (String) sessao.getAttribute("nomeMae");		
		String cep  = (String) sessao.getAttribute("cep");
		String idMunicipio  = (String) sessao.getAttribute("idMunicipio");
		String codigoBairro  = (String) sessao.getAttribute("codigoBairro");
		String idLogradouro  = (String) sessao.getAttribute("idLogradouro");
		String indicadorUso  = (String) sessao.getAttribute("indicadorUso");
		String tipoPesquisa  = (String) sessao.getAttribute("tipoPesquisa");
		String tipoPesquisaNomeMae  = (String) sessao.getAttribute("tipoPesquisaNomeMae");
		String idEsferaPoder  = (String) sessao.getAttribute("idEsferaPoder");
		String nis = (String) sessao.getAttribute("nis");
		
		
		
		// Verifica se o filtro foi informado pela página de filtragem de
		// cliente
		if (sessao.getAttribute("filtroCliente") != null ) {
			filtroCliente = (FiltroCliente) sessao
					.getAttribute("filtroCliente");
		} else {
			// Caso o exibirManterCliente não tenha passado por algum esquema de
			// filtro, a quantidade de registros é verificada para avaliar a necessidade
			// de filtragem
			filtroCliente = new FiltroCliente();
			
			retorno = actionMapping.findForward("filtrarCliente");
				
			// código para checar ou nao o Atualizar
	        String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
				//pesquisarActionForm.reset();
				//pesquisarActionForm.set("indicadorUso", "");
				sessao.setAttribute("indicadorAtualizar","1");
			}

			if (httpServletRequest.getParameter("desfazer") != null
	                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
		        //Limpando o formulario
				//pesquisarActionForm.reset();
		        sessao.setAttribute("indicadorAtualizar","1");
	        }
	        
			sessao.removeAttribute("voltar");
			sessao.removeAttribute("idRegistroAtualizacao");	
		}

		// A pesquisa de clientes só será feita se o forward estiver direcionado
		// para a página de manterEmpresa
		if (retorno.getName().equalsIgnoreCase("manterCliente")) {
			
			sessao.removeAttribute("atualizar");
			
			// Seta a ordenação desejada do filtro
			filtroCliente.setCampoOrderBy(FiltroCliente.NOME);

			filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			/*filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ORGAO_EXPEDIDOR_RG);*/
			filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.UNIDADE_FEDERACAO);

			// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
			//Integer totalRegistros = fachada
				///	.pesquisarClienteDadosClienteEnderecoCount(filtroCliente);
			Integer totalRegistros = (Integer) fachada.filtrarQuantidadeCliente(codigo,
					cpf,
					rg,
					cnpj,
					nome,
					nomeMae,		
					cep,
					idMunicipio,
					codigoBairro,
					idLogradouro,
					indicadorUso,
					tipoPesquisa,
					tipoPesquisaNomeMae, null,
					idEsferaPoder, nis);

			// 2º Passo - Chamar a função de Paginação passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);

			// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
			// da pesquisa que está no request
			//clientes = fachada
				//	.pesquisarClienteDadosClienteEndereco(filtroCliente, (Integer) httpServletRequest
					//		.getAttribute("numeroPaginasPesquisa"));
			clientes = fachada.filtrarCliente(
					codigo,
					cpf,
					rg,
					cnpj,
					nome,
					nomeMae,		
					cep,
					idMunicipio,
					codigoBairro,
					idLogradouro,
					indicadorUso,
					tipoPesquisa,
					tipoPesquisaNomeMae,
					null, idEsferaPoder,
					(Integer) httpServletRequest
							.getAttribute("numeroPaginasPesquisa"), nis);
		    
			if (clientes == null || clientes.isEmpty()) {
				// Nenhum cliente cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}

			
			if (clientes.size()== 1 && httpServletRequest.getAttribute("atualizar") != null
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarCliente");
				Cliente cliente = (Cliente)clientes.iterator().next();
				httpServletRequest
                	.setAttribute("idRegistroAtualizacao", cliente.getId().toString());
				sessao
                	.setAttribute("atualizar","atualizar");
			}else{
				// A coleção fica na sessão devido ao esquema de paginação
				sessao.setAttribute("clientes", clientes);
			}
		}

		return retorno;
	}
}
