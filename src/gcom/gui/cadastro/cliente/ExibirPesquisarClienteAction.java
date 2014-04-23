package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
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
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Sávio Luiz, Fernanda Paiva
 * @date 25/04/2005, 29/08/2006
 */
public class ExibirPesquisarClienteAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarCliente");
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Verifica se o pesquisar cliente foi chamado a partir do inserir cliente
 		// e em caso afirmativo recebe o parâmetro e manda-o pela sessão para
 		// ser verificado no cliente_resultado_pesquisa e depois retirado da
 		// sessão no ExibirFiltrarClienteAction
 		if (httpServletRequest.getParameter("consultaCliente") != null) {
 			sessao.setAttribute("consultaCliente", httpServletRequest.getParameter("consultaCliente"));
 		}

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		String limparForm = httpServletRequest.getParameter("limparForm");
		if(limparForm == null || limparForm.equals("")){
			if(httpServletRequest.getAttribute("limparForm") != null){
				limparForm = (String)httpServletRequest.getAttribute("limparForm");
			}
		}
		
		// Verifica se esta entrando a primeira vez no pesquisar e limpa o form e a sessao caso isso aconteça.
		if (httpServletRequest.getParameter("objetoConsulta") == null
				&& httpServletRequest.getParameter("tipoConsulta") == null
				&& httpServletRequest.getParameter("voltarPesquisa") == null) 
		{

			pesquisarActionForm.set("idTipoCliente", null);
			pesquisarActionForm.set("nomeCliente", "");
			pesquisarActionForm.set("cepClienteEndereco", "");
			pesquisarActionForm.set("cpf", "");
			pesquisarActionForm.set("rg", "");
			pesquisarActionForm.set("cnpj", "");
			pesquisarActionForm.set("idMunicipioCliente", "");
			pesquisarActionForm.set("descricaoMunicipioCliente", "");
			pesquisarActionForm.set("codigoBairroCliente", "");
			pesquisarActionForm.set("descricaoBairroCliente", "");
			pesquisarActionForm.set("idLogradouroCliente", "");
			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

			sessao.removeAttribute("caminhoRetornoTelaPesquisaMunicipio");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaBairro");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaLogradouro");
			sessao.removeAttribute("idMunicipio");
			if (httpServletRequest.getParameter("novaPesquisa") == null
					|| httpServletRequest.getParameter("novaPesquisa").equals(
							"")) {
				sessao.removeAttribute("caminhoRetorno");
			}
		}

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {
			pesquisarActionForm.set("idTipoCliente", null);
			pesquisarActionForm.set("nomeCliente", "");
			pesquisarActionForm.set("cepClienteEndereco", "");
			pesquisarActionForm.set("cpf", "");
			pesquisarActionForm.set("rg", "");
			pesquisarActionForm.set("cnpj", "");
			pesquisarActionForm.set("idMunicipioCliente", "");
			pesquisarActionForm.set("descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte", "");
			pesquisarActionForm.set("codigoBairroCliente", "");
			pesquisarActionForm.set("descricaoBairroCliente", "");
			pesquisarActionForm.set("idLogradouroCliente", "");
			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

			sessao.removeAttribute("caminhoRetornoTelaPesquisaMunicipio");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaBairro");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaLogradouro");
			sessao.removeAttribute("idMunicipio");
		}

		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo(
				FiltroClienteTipo.DESCRICAO);
		if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
        	sessao.removeAttribute("indicadorUsoTodos");
        	filtroClienteTipo.adicionarParametro(new ParametroSimples(
    				FiltroClienteTipo.INDICADOR_USO,
    				ConstantesSistema.INDICADOR_USO_ATIVO));
        }
        else
        {
        	sessao.setAttribute("indicadorUsoTodos",
				httpServletRequest.getParameter("indicadorUsoTodos"));
        }
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(
				FiltroEsferaPoder.DESCRICAO);
		
		Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
		httpServletRequest.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
		
		// inicializa a coleção
		Collection tiposClientes = null;

		// -------Parte que trata do código quando o usuário tecla enter
		// caso seja o id do municipio
		String idDigitadoEnterMunicipio = (String) pesquisarActionForm
				.get("idMunicipioCliente");
		// caso seja o codigo do bairro
		String codigoDigitadoEnterBairro = (String) pesquisarActionForm
				.get("codigoBairroCliente");

		// caso seja o codigo do logradouro
		String codigoDigitadoEnterLogradouro = (String) pesquisarActionForm
				.get("idLogradouroCliente");

		// Verifica se o código foi digitado
		if (idDigitadoEnterMunicipio != null
				&& !idDigitadoEnterMunicipio.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idDigitadoEnterMunicipio));
			
			if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
	        	sessao.removeAttribute("indicadorUsoTodos");
	        	filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
	        }
	        else
	        {
	        	sessao.setAttribute("indicadorUsoTodos",
					httpServletRequest.getParameter("indicadorUsoTodos"));
	        }

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O municipio foi encontrado
				pesquisarActionForm.set("idMunicipioCliente", ""
						+ ((Municipio) ((List) municipioEncontrado).get(0))
								.getId());
				pesquisarActionForm.set("descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte",
						((Municipio) ((List) municipioEncontrado).get(0))
								.getNome());
				httpServletRequest.setAttribute(
						"idMunicipioClienteNaoEncontrado", "true");

				httpServletRequest.setAttribute("nomeCampo",
						"codigoBairroCliente");

			} else {

				pesquisarActionForm.set("idMunicipioCliente", "");
				httpServletRequest.setAttribute("nomeCampo",
						"idMunicipioCliente");
				httpServletRequest.setAttribute(
						"idMunicipioClienteNaoEncontrado", "exception");
				pesquisarActionForm.set("descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte",
						"Município inexistente");

			}

		}

		// Verifica se o código foi digitado
		if (codigoDigitadoEnterBairro != null
				&& !codigoDigitadoEnterBairro.trim().equals("")
				&& Integer.parseInt(codigoDigitadoEnterBairro) > 0) {
			FiltroBairro filtroBairro = new FiltroBairro();

			if (idDigitadoEnterMunicipio != null
					&& !idDigitadoEnterMunicipio.trim().equals("")
					&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID, idDigitadoEnterMunicipio));
			}

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoDigitadoEnterBairro));
			if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
	        	sessao.removeAttribute("indicadorUsoTodos");
	        	filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
	        }
	        else
	        {
	        	sessao.setAttribute("indicadorUsoTodos",
					httpServletRequest.getParameter("indicadorUsoTodos"));
	        }

			// verifica se o bairro pesquisado é de um municipio existente
			if (idDigitadoEnterMunicipio != null
					&& !idDigitadoEnterMunicipio.trim().equals("")
					&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID, idDigitadoEnterMunicipio));
			}

			Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
				// O municipio foi encontrado
				pesquisarActionForm.set("codigoBairroCliente", ""
						+ ((Bairro) ((List) bairroEncontrado).get(0))
								.getCodigo());
				pesquisarActionForm.set("descricaoBairroCliente",
						((Bairro) ((List) bairroEncontrado).get(0)).getNome());
				httpServletRequest.setAttribute(
						"codigoBairroClienteNaoEncontrado", "true");

				httpServletRequest.setAttribute("nomeCampo",
						"logradouroCliente");

			} else {

				pesquisarActionForm.set("codigoBairroCliente", "");
				httpServletRequest.setAttribute(
						"codigoBairroClienteNaoEncontrado", "exception");
				pesquisarActionForm.set("descricaoBairroCliente",
						"Bairro inexistente");

				httpServletRequest.setAttribute("nomeCampo",
						"codigoBairroCliente");

			}

		}

		// Verifica se o código do logradouro foi digitado
		if (codigoDigitadoEnterLogradouro != null
				&& !codigoDigitadoEnterLogradouro.trim().equals("")
				&& Integer.parseInt(codigoDigitadoEnterLogradouro) > 0) {
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID, codigoDigitadoEnterLogradouro));
			if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
	        	sessao.removeAttribute("indicadorUsoTodos");
	        	filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
	        }
	        

			Collection logradouroEncontrado = fachada.pesquisar(
					filtroLogradouro, Logradouro.class.getName());

			if (logradouroEncontrado != null && !logradouroEncontrado.isEmpty()) {
				// O municipio foi encontrado
				Logradouro logradouro = (Logradouro) ((List) logradouroEncontrado)
						.get(0);
				String logradouroFormatado = logradouro.getDescricaoFormatada();
				pesquisarActionForm.set("idLogradouroCliente", ""
						+ logradouro.getId());
				pesquisarActionForm.set("nomeLogradouroCliente",
						logradouroFormatado);
				httpServletRequest.setAttribute("idLogradouroNaoEncontrado",
						"true");

				httpServletRequest.setAttribute("nomeCampo", "Button");

			} else {
				pesquisarActionForm.set("idLogradouroCliente", "");
				httpServletRequest.setAttribute("idLogradouroNaoEncontrado",
						"exception");
				pesquisarActionForm.set("nomeLogradouroCliente",
						"Logradouro inexistente");

				httpServletRequest.setAttribute("nomeCampo", "idLogradouro");

			}

		}

		// -------Fim da Parte que trata do código quando o usuário tecla enter
		tiposClientes = fachada.pesquisar(filtroClienteTipo, ClienteTipo.class
				.getName());

		if (tiposClientes == null || tiposClientes.isEmpty()) {
			// Nenhuma empresa cadastrada
			new ActionServletException("atencao.pesquisa.nenhumresultado",
					null, "tipo cliente");
		} else {
			sessao.setAttribute("tiposClientes", tiposClientes);
		}

		// Verifica se o tipoConsulta é diferente de nulo ou vazio esse tipo
		// consulta vem do
		// municipio_resultado_pesquisa.jsp ou do bairro_resultado_pesquisa.jsp.
		// É feita essa verificação pois pode ser que ainda não tenha
		// feito a pesquisa de municipio ou bairro.
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// Verifica se o tipo da consulta de cliente é de municipio
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"municipio")) {

				pesquisarActionForm.set("idMunicipioCliente",
						httpServletRequest.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("descricaoMunicipioCliente",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}
			// Verifica se o tipo da consulta de cliente é de bairro
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta")
					.equals("bairro")) {

				pesquisarActionForm.set("codigoBairroCliente",
						httpServletRequest.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("descricaoBairroCliente",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}
			// Verifica se o tipo da consulta de imovel é de logradouro
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"logradouro")) {

				pesquisarActionForm.set("idLogradouroCliente",
						httpServletRequest.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("nomeLogradouroCliente",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}
		}

		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaCliente") != null && 
				!"".equals(httpServletRequest.getParameter("caminhoRetornoTelaPesquisaCliente"))) {

			sessao.setAttribute("caminhoRetornoTelaPesquisaCliente",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaCliente"));
		} else {
			
			if ((httpServletRequest
					.getParameter("objetoConsulta") == null || 
					"".equals(httpServletRequest.getParameter("objetoConsulta"))) && (httpServletRequest
							.getParameter("voltarPesquisa") == null || 
							"".equals(httpServletRequest.getParameter("voltarPesquisa"))) && 
							(httpServletRequest.getParameter("tipoConsulta") == null 
									|| httpServletRequest.getParameter("tipoConsulta").equals(""))) {
				
				sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
				
			}

		}

		if (pesquisarActionForm.get("tipoPesquisa") == null
				|| pesquisarActionForm.get("tipoPesquisa").equals("")) {

			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}

		return retorno;
	}
}
