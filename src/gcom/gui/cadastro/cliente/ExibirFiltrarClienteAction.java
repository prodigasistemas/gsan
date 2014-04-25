package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
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
 * [UC0000] Filtrar Cliente
 * Pré-processamento para a página de filtro de cliente
 * 
 * @author Rodrigo Silveira, Roberta Costa
 * @created 23/07/2005, 12/07/2006
 */
public class ExibirFiltrarClienteAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("filtrarCliente");

		FiltrarClienteActionForm filtrarClienteActionForm = (FiltrarClienteActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		if(filtrarClienteActionForm.getAtualizarFiltro()== null
				|| filtrarClienteActionForm.getAtualizarFiltro().equals("") ){
			filtrarClienteActionForm.setAtualizarFiltro("1");
			httpServletRequest.setAttribute("nomeCampo", "cpfClienteFiltro");
		}

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (sessao.getAttribute("consultaCliente") != null) {
			sessao.removeAttribute("consultaCliente");
		}
		
		if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").trim().equals("")) {
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
			filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
		
			Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
		
			sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
			
		}

		// Código do Município
		String codigoDigitadoMunicipioEnter = (String) filtrarClienteActionForm
			.getMunicipioClienteFiltro();

		// Verifica se o código foi digitado
		if (codigoDigitadoMunicipioEnter != null
				&& !codigoDigitadoMunicipioEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoMunicipioEnter) > 0) {

			// Manda para a página a informação do campo para que seja
			// focado no retorno da pesquisa
			httpServletRequest.setAttribute("nomeCampo", "municipioClienteFiltro");

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, codigoDigitadoMunicipioEnter));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O cliente foi encontrado
				filtrarClienteActionForm.setMunicipioClienteFiltro( 
						""+ ((Municipio) ((List) municipioEncontrado).get(0)).getId());
				filtrarClienteActionForm.setDescricaoMunicipioClienteFiltro(
						((Municipio) ((List) municipioEncontrado).get(0)).getNome());
				httpServletRequest.setAttribute("codigoMunicipioNaoEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "bairroClienteFiltro");

			} else {
				filtrarClienteActionForm.setMunicipioClienteFiltro("");
				httpServletRequest.setAttribute("codigoMunicipioNaoEncontrado",
						"exception");
				filtrarClienteActionForm.setDescricaoMunicipioClienteFiltro("Município Inexistente");

			}
		}

		// Código do Município
		String codigoDigitadoLogradouroEnter = (String) filtrarClienteActionForm
				.getLogradouroClienteFiltro();

		// Código do Bairro
		String codigoDigitadoBairroEnter = (String) filtrarClienteActionForm
				.getBairroClienteFiltro();

		// Verifica se o código foi digitado
		if (codigoDigitadoBairroEnter != null
				&& !codigoDigitadoBairroEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoBairroEnter) > 0) {

			// Manda para a página a informação do campo para que seja
			// focado no retorno da pesquisa
			httpServletRequest.setAttribute("nomeCampo", "bairroClienteFiltro");

			FiltroBairro filtroBairro = new FiltroBairro();

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoDigitadoBairroEnter));

			// Adiciona a busca por município se ele foi digitado na página
			if (codigoDigitadoMunicipioEnter != null
					&& !codigoDigitadoMunicipioEnter.equalsIgnoreCase("")) {
				filtroBairro
						.adicionarParametro(new ParametroSimples(
								FiltroBairro.MUNICIPIO_ID,
								codigoDigitadoMunicipioEnter));
			}

			Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
				// O cliente foi encontrado
				filtrarClienteActionForm.setBairroClienteFiltro(
						""+ ((Bairro) ((List) bairroEncontrado).get(0)).getCodigo());
				filtrarClienteActionForm.setDescricaoBairroClienteFiltro(
						((Bairro) ((List) bairroEncontrado).get(0)).getNome());
				httpServletRequest.setAttribute("codigoBairroNaoEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "logradouroClienteFiltro");

			} else {
				filtrarClienteActionForm.setBairroClienteFiltro("");
				httpServletRequest.setAttribute("codigoBairroNaoEncontrado",
						"exception");
				filtrarClienteActionForm.setDescricaoBairroClienteFiltro(
						"Bairro Inexistente");

			}
		}

		// Verifica se o código foi digitado
		if (codigoDigitadoLogradouroEnter != null
				&& !codigoDigitadoLogradouroEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoLogradouroEnter) > 0) {

			// Manda para a página a informação do campo para que seja
			// focado no retorno da pesquisa
			httpServletRequest.setAttribute("nomeCampo", "logradouroClienteFiltro");

			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID, codigoDigitadoLogradouroEnter));

			Collection logradouroEncontrado = fachada.pesquisar(
					filtroLogradouro, Logradouro.class.getName());

			if (logradouroEncontrado != null && !logradouroEncontrado.isEmpty()) {
				// O logradouro foi encontrado
				filtrarClienteActionForm.setLogradouroClienteFiltro(
						""+ ((Logradouro) ((List) logradouroEncontrado).get(0)).getId());
				filtrarClienteActionForm.setDescricaoLogradouroClienteFiltro(
						((Logradouro) ((List) logradouroEncontrado).get(0)).getDescricaoFormatada());
				httpServletRequest.setAttribute("idLogradouroNaoEncontrado",
						"true");

			} else {
				filtrarClienteActionForm.setLogradouroClienteFiltro("");
				httpServletRequest.setAttribute("idLogradouroNaoEncontrado",
						"exception");
				filtrarClienteActionForm.setDescricaoLogradouroClienteFiltro(
						"Logradouro Inexistente");

			}
		}

		// Código do Município
		String idCep = (String) filtrarClienteActionForm.getCepClienteFiltro();
		
		// PESQUISAR CEP
		if (idCep != null
				&& !idCep.toString().trim().equalsIgnoreCase("")) {
			this.pesquisarCep(idCep, filtrarClienteActionForm, fachada, httpServletRequest);
		} 

		
		if (filtrarClienteActionForm.getTipoPesquisa() == null
				|| filtrarClienteActionForm.getTipoPesquisa().equals("")) {

			filtrarClienteActionForm.setTipoPesquisa(
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}
		
		if (filtrarClienteActionForm.getTipoPesquisaNomeMae() == null
				|| filtrarClienteActionForm.getTipoPesquisaNomeMae().equals("")) {

			filtrarClienteActionForm.setTipoPesquisaNomeMae(
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}
		
		
		return retorno;
	}
	
	/**
	 * Pesquisar Cep
	 * @param filtroMunicipio
	 * @param idMunicipioFiltro
	 * @param codigoSetorComercial
	 * @param municipios
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCep(
			String idCepFiltro, 
			FiltrarClienteActionForm filtrarClienteActionForm,
			Fachada fachada, 
			HttpServletRequest httpServletRequest) {
		FiltroCep filtroCep = new FiltroCep();

		filtroCep.adicionarParametro(new ParametroSimples(
				FiltroCep.CODIGO, idCepFiltro));
		filtroCep.adicionarParametro(new ParametroSimples(
				FiltroCep.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection cepEncontrado = null;

       //pesquisa
       	cepEncontrado = fachada.pesquisar(filtroCep, Cep.class
                    .getName());
		
		if (cepEncontrado != null && !cepEncontrado.isEmpty()) {
			// O municipio foi encontrado
			filtrarClienteActionForm.setCepClienteFiltro(""
					+ ((Cep) ((List) cepEncontrado).get(0))
							.getCodigo());
			filtrarClienteActionForm.setCepDescricaoClienteFiltro(
					((Cep) ((List) cepEncontrado).get(0))
							.getDescricaoLogradouroFormatada());
			
			httpServletRequest.setAttribute(
					"cepImovelNaoEncontrado", "true");

			httpServletRequest.setAttribute("nomeCampo",
					"idMunicipioFiltro");

		} else {
			filtrarClienteActionForm.setCepClienteFiltro("");
			httpServletRequest.setAttribute(
					"cepImovelNaoEncontrado", "exception");
			
			filtrarClienteActionForm.setCepDescricaoClienteFiltro(
					"Cep inexistente");

			httpServletRequest.setAttribute("nomeCampo",
					"cepFiltro");

		}
	}
}
