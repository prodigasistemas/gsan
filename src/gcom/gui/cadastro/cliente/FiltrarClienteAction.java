package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.FiltroCliente;
import gcom.gui.ActionServletException;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesColecao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que filtra os clientes do sistema de acordo com os parâmentros informados
 * pelo usuário.
 *
 * @author Rodrigo
 * @date 23/07/2005
 */
public class FiltrarClienteAction extends Action {
	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Rodrigo
	 * @date 23/07/2005
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornarFiltroCliente");
		
		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarClienteActionForm filtrarClienteActionForm = (FiltrarClienteActionForm) actionForm;

		// Recupera os parâmetros do form
		String id = (String) filtrarClienteActionForm.getCodigoClienteFiltro();
		String cpf = (String) filtrarClienteActionForm.getCpfClienteFiltro();
		String rg = (String) filtrarClienteActionForm.getRgClienteFiltro();
		String cnpj = (String) filtrarClienteActionForm.getCnpjClienteFiltro();
		String nome = (String) filtrarClienteActionForm.getNomeClienteFiltro();
		String nomeMae = (String) filtrarClienteActionForm.getNomeMaeClienteFiltro();		
		String cep = (String) filtrarClienteActionForm.getCepClienteFiltro();
		String idMunicipioCliente = (String) filtrarClienteActionForm.getMunicipioClienteFiltro();
		String codigoBairroCliente = (String) filtrarClienteActionForm.getBairroClienteFiltro();
		String idLogradouro = (String) filtrarClienteActionForm.getLogradouroClienteFiltro();
		String indicadorUso = (String) filtrarClienteActionForm.getIndicadorUsoClienteFiltro();
		String tipoPesquisa = (String) filtrarClienteActionForm.getTipoPesquisa();
		String tipoPesquisaNomeMae = (String) filtrarClienteActionForm.getTipoPesquisaNomeMae();
		String idEsferaPoder = (String) filtrarClienteActionForm.getIdEsferaPoder();
		String atualizar = (String)httpServletRequest.getParameter("atualizarFiltro");
		
		sessao.setAttribute("atualizar", atualizar);
		
		if (atualizar != null && atualizar.equals("1")) {
			httpServletRequest.setAttribute("atualizar", atualizar);
		}else{
			filtrarClienteActionForm.setAtualizarFiltro("");
		}
		
		// cria o filtro para Tabela Auxiliar
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.setCampoOrderBy(FiltroCliente.NOME);
		/*filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ORGAO_EXPEDIDOR_RG);*/
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.UNIDADE_FEDERACAO);
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, id));
		}
		if (cpf != null && !cpf.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CPF, cpf));
		}
		if (rg != null && !rg.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.RG, rg));
		}
		if (cnpj != null && !cnpj.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CNPJ, cnpj));
		}
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroCliente.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroCliente.NOME, nome));
			} else {
				filtroCliente.adicionarParametro(new ComparacaoTexto(
						FiltroCliente.NOME, nome));
			}

		}
		//Nome da Mãe
		if (nomeMae  != null && !nomeMae.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisaNomeMae != null
					&& tipoPesquisaNomeMae
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroCliente.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroCliente.NOME_MAE, nomeMae));
			} else {
				filtroCliente.adicionarParametro(new ComparacaoTexto(
						FiltroCliente.NOME_MAE, nomeMae));
			}

		}
		
		if (codigoBairroCliente != null
				&& !codigoBairroCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.BAIRRO_CODIGO, codigoBairroCliente));
		}
		if (idLogradouro != null && !idLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.LOGRADOURO, idLogradouro));
		}
		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO, indicadorUso));
		}
		if (cep != null && !cep.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.CEP, cep));
		}

		if (idMunicipioCliente != null
				&& !idMunicipioCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.MUNICIPIO_ID, idMunicipioCliente));
		}
		
		if (idEsferaPoder != null && !idEsferaPoder.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ESFERA_PODER_ID, idEsferaPoder));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		filtroCliente.setCampoDistinct(FiltroCliente.NOME);

		// Manda o filtro pela sessão para o ExibirManterClienteAction
		sessao.setAttribute("filtroCliente", filtroCliente);

		
		sessao.setAttribute("codigo",(String) filtrarClienteActionForm.getCodigoClienteFiltro());
		sessao.setAttribute("cpf",(String) filtrarClienteActionForm.getCpfClienteFiltro());
		sessao.setAttribute("rg",(String) filtrarClienteActionForm.getRgClienteFiltro());
		sessao.setAttribute("cnpj",(String) filtrarClienteActionForm.getCnpjClienteFiltro());
		sessao.setAttribute("nome",(String) filtrarClienteActionForm.getNomeClienteFiltro());
		sessao.setAttribute("nomeMae",(String) filtrarClienteActionForm.getNomeMaeClienteFiltro());		
		sessao.setAttribute("cep",(String) filtrarClienteActionForm.getCepClienteFiltro());
		sessao.setAttribute("idMunicipio",(String) filtrarClienteActionForm.getMunicipioClienteFiltro());
		sessao.setAttribute("codigoBairro",(String) filtrarClienteActionForm.getBairroClienteFiltro());
		sessao.setAttribute("idLogradouro",(String) filtrarClienteActionForm.getLogradouroClienteFiltro());
		sessao.setAttribute("indicadorUso",(String) filtrarClienteActionForm.getIndicadorUsoClienteFiltro());
		sessao.setAttribute("tipoPesquisa",(String) filtrarClienteActionForm.getTipoPesquisa());
		sessao.setAttribute("tipoPesquisaNomeMae",(String) filtrarClienteActionForm.getTipoPesquisaNomeMae());
		sessao.setAttribute("idEsferaPoder",(String) filtrarClienteActionForm.getIdEsferaPoder());

		
		// Devolve o mapeamento de retorno
		return retorno;
	}
}
