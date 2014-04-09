package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Realiza a pesquisa de imovel de acordo com os parâmetros informados
 * 
 * @author Sávio Luiz
 * @created 21 de Julho de 2005
 */

public class PesquisarImovelAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("listaImovel");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Recupera os parâmetros do form
		String idLocalidade = (String) pesquisarActionForm.get("idLocalidade");
		String codigoSetorComercial = (String) pesquisarActionForm
				.get("codigoSetorComercial");
		String idQuadra = (String) pesquisarActionForm.get("idQuadra");
		String lote = (String) pesquisarActionForm.get("lote");
		String SubLote = (String) pesquisarActionForm.get("subLote");
		String idCliente = (String) pesquisarActionForm.get("idCliente");
		String cep = (String) pesquisarActionForm.get("cep");
		String codigoBairro = (String) pesquisarActionForm
				.get("codigoBairroImovel");
		String idMunicipio = (String) pesquisarActionForm
				.get("idMunicipioImovel");
		String idLogradouro = (String) pesquisarActionForm.get("idLogradouro");
		String numeroImovelInicialFiltro = (String) pesquisarActionForm.get("numeroImovelInicialFiltro");
		String numeroImovelFinalFiltro = (String) pesquisarActionForm.get("numeroImovelFinalFiltro");

		
//		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel(
	//			FiltroClienteImovel.CLIENTE_NOME);

		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		
		
		
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");

		boolean peloMenosUmParametroInformado = false;

		// caso codigo cliente esteja nulo então faz uma pesquisa por cliente
		// imovel
		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_ID, new Integer(idCliente)));
		}

		// Insere os parâmetros informados no filtro
		if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.LOCALIDADE_ID, new Integer(idLocalidade)));
		}

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.SETOR_COMERCIAL_CODIGO,
					new Integer(codigoSetorComercial)));
		}

		if (idQuadra != null && !idQuadra.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.QUADRA_NUMERO, new Integer(idQuadra)));
		}
		if (lote != null && !lote.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.LOTE, new Short(lote)));
		}

		if (SubLote != null && !SubLote.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.SUBLOTE, new Short(SubLote)));
		}

		if (cep != null && !cep.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CEP_CODIGO, cep));
		}

		if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.MUNICIPIO_SETOR_COMERICAL_CODIGO,
					new Integer(idMunicipio)));
		}

		if (codigoBairro != null && !codigoBairro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.BAIRRO_CODIGO, new Integer(codigoBairro)));
		}

		if (idLogradouro != null && !idLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.LOGRADOURO_ID, new Integer(idLogradouro)));
		}
		// adiciona o indicador de uso de clinte(caso ativo)
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// adiciona o indicador de exclusão do imovel
		filtroClienteImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO,
				Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR, 2));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO));

		// inclui o parametro de pesquisa de imóveis os quais não possuem o
		// perfil de
		// tarifa social, cujo o id é 3.
		if (sessao.getAttribute("caminhoRetorno") != null
				&& !sessao.getAttribute("caminhoRetorno").equals("")
				&& sessao.getAttribute("caminhoRetorno").equals(
						"exibirInserirTarifaSocialImovelAction")) {

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_PERFIL_INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroClienteImovel
					.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroClienteImovel.IMOVEL_PERFIL,
							ImovelPerfil.TARIFA_SOCIAL));

		}
		
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO,ClienteRelacaoTipo.USUARIO));

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		boolean pesquisarImovelCondominio = false;
		//verfica se é so para pesquisar os imoveis condominios
		if(sessao.getAttribute("pesquisarImovelCondominio") != null){
			pesquisarImovelCondominio = true;	
		}
		
		Collection clienteImoveis = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
		int totalRegistros = fachada.pesquisarQuantidadeImovelInscricao(
				null, idLocalidade, codigoSetorComercial,
    		     idQuadra, lote, SubLote, idCliente,
    		     idMunicipio, cep, codigoBairro,
    		     idLogradouro, numeroImovelInicialFiltro, numeroImovelFinalFiltro, pesquisarImovelCondominio).intValue();
		
		if (totalRegistros == 0){
			// Nenhuma imovel cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado","imovel");
		}else{
			//2º Passo - Chamar a função de Paginação passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);
	 
			// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
			// da pesquisa que está no request
			clienteImoveis = fachada.pesquisarImovelInscricao(
	    			 null,
	    			 idLocalidade,
	    		     codigoSetorComercial,
	    		     idQuadra,
	    		     lote,
	    		     SubLote,
	    		     idCliente,
	    		     idMunicipio,
	    		     cep,
	    		     codigoBairro,
	    		     idLogradouro, numeroImovelInicialFiltro, numeroImovelFinalFiltro, pesquisarImovelCondominio, ((Integer) httpServletRequest
	 						.getAttribute("numeroPaginasPesquisa")));
			
			//Coloca a coleção na sessão
			sessao.setAttribute("colecaoClientesImoveis", clienteImoveis);
		}

		return retorno;
	}
}
