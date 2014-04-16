package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirGerenciaRegionalAction extends GcomAction {

	/**
	 * Este caso de uso permite inserir uma Agência Bancária
	 * 
	 * [UC0515] Inserir Agência Bancária
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @author Thiago Tenório
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirGerenciaRegionalActionForm inserirGerenciaRegionalActionForm = (InserirGerenciaRegionalActionForm) actionForm;

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_AGENCIA_BANCARIA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		String nome = inserirGerenciaRegionalActionForm.getNome();
		String nomeAbreviado = inserirGerenciaRegionalActionForm
				.getNomeAbreviado();
		Collection colecaoEnderecos = (Collection) sessao
				.getAttribute("colecaoEnderecos");
		String telefone             = inserirGerenciaRegionalActionForm.getTelefone();
		String ramal                = inserirGerenciaRegionalActionForm.getRamal();
		String fax                  = inserirGerenciaRegionalActionForm.getFax();
		String email                = inserirGerenciaRegionalActionForm.getEmail();
		String idCliente            = inserirGerenciaRegionalActionForm.getIdCliente();
		String cnpjGerenciaRegional = inserirGerenciaRegionalActionForm.getCnpjGerenciaRegional();
		

		GerenciaRegional gerenciaRegionalInserir = new GerenciaRegional();
		Collection colecaoPesquisa = null;

		sessao.removeAttribute("tipoPesquisaRetorno");

		if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
			
			gerenciaRegionalInserir = (GerenciaRegional) Util
			.retonarObjetoDeColecao(colecaoEnderecos);

			gerenciaRegionalInserir.setLogradouroCep(gerenciaRegionalInserir
					.getLogradouroCep());
			gerenciaRegionalInserir.setLogradouroBairro(gerenciaRegionalInserir
					.getLogradouroBairro());
			gerenciaRegionalInserir.setComplementoEndereco(gerenciaRegionalInserir
					.getComplementoEndereco());
			gerenciaRegionalInserir.setNumeroImovel(gerenciaRegionalInserir
					.getNumeroImovel());
			gerenciaRegionalInserir.setEnderecoReferencia(gerenciaRegionalInserir
					.getEnderecoReferencia());
			gerenciaRegionalInserir.setIndicadorUso(gerenciaRegionalInserir
					.getIndicadorUso());


		}

		gerenciaRegionalInserir.setNome(nome);
		gerenciaRegionalInserir.setNomeAbreviado(nomeAbreviado);

		// O Endereço é obrigatório.
		if (colecaoEnderecos == null || colecaoEnderecos.equals("")) {
			throw new ActionServletException("atencao.required", null,
					"Endereço ");
		}
		// O nome da Agência Bancaria é obrigatório.
		if (nomeAbreviado == null || nomeAbreviado.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Nome da Agência Bancária");
		}

		gerenciaRegionalInserir.setNome(nome);
		gerenciaRegionalInserir.setNomeAbreviado(nomeAbreviado);

		// O telefone é obrigatório caso o ramal tenha sido informado.
		if (ramal != null && !ramal.equalsIgnoreCase("")) {
			gerenciaRegionalInserir.setRamalFone(ramal);
			if (telefone == null || telefone.equalsIgnoreCase("")) {
				throw new ActionServletException(
						"atencao.telefone_gerencia_regional_nao_informado");
			} else if (telefone.length() < 7) {
				throw new ActionServletException(
						"atencao.telefone_ou_fax_gerencia_regional_menor_sete_digitos",
						null, "Telefone");
			}
		}

		// O telefone é obrigatório caso o ramal tenha sido informado.
		if (ramal != null && !ramal.equalsIgnoreCase("")) {
			gerenciaRegionalInserir.setRamalFone(ramal);
			if (telefone == null || telefone.equalsIgnoreCase("")) {
				throw new ActionServletException(
						"atencao.telefone_gerencia_regional_nao_informado");
			} else if (telefone.length() < 7) {
				throw new ActionServletException(
						"atencao.telefone_ou_fax_gerencia_regional_menor_sete_digitos",
						null, "Telefone");
			}
		}

		// Telefone.
		if (telefone != null && !telefone.equalsIgnoreCase("")) {
			if (telefone.length() < 7) {
				throw new ActionServletException(
						"atencao.telefone_ou_fax_agencia_bancaria_menor_sete_digitos",
						null, "Telefone");
			} else {
				gerenciaRegionalInserir.setFone(telefone);
			}
		}

		// Fax.
		if (fax != null && !fax.equalsIgnoreCase("")) {
			if (fax.length() < 7) {
				throw new ActionServletException(
						"atencao.telefone_ou_fax_agencia_bancaria_menor_sete_digitos",
						null, "Fax");
			} else {
				gerenciaRegionalInserir.setFax(fax);
			}
		}

		// E-mail.
		if (email != null && !email.equalsIgnoreCase("")) {
			gerenciaRegionalInserir.setEmail(email);
		}
		

		// Indicador de Uso
			gerenciaRegionalInserir.setIndicadorUso(new Short("1"));
			
			
			
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			
			
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.NOME,
					gerenciaRegionalInserir.getNome()));
			

			// Verificar existência da Descrição da Gerencia Regional
			colecaoPesquisa = fachada.pesquisar(filtroGerenciaRegional,
					GerenciaRegional.class.getName());

			if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
				// Descrição da gerencia Regional já existe
				throw new ActionServletException(
						"atencao.pesquisa_descricao_da_gerenciaregional_ja_cadastrada",
						null, nome);
			}
			
			
			// Verificar existência do CNPJ da Gerencia Regional
			if(cnpjGerenciaRegional != null && !cnpjGerenciaRegional.equalsIgnoreCase("")){
				
				//filtroGerenciaRegional = null;
				FiltroGerenciaRegional filtro = new FiltroGerenciaRegional();
				
				filtro.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.CNPJ_GERENCIA_REGIONAL , cnpjGerenciaRegional));
				
				Collection colecaoGerenciaRegional = fachada.pesquisar(filtro,GerenciaRegional.class.getName());
			
				GerenciaRegional gerenciaRegionalEncontrada = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
				
				if(gerenciaRegionalEncontrada != null){
					// CNPJ da gerencia Regional já existe
			
					throw new ActionServletException(
							"atencao.pesquisa_cnpj_da_gerenciaregional_ja_cadastrada",
							null,  "" + gerenciaRegionalEncontrada.getNome() + "");
				}
				gerenciaRegionalInserir.setCnpjGerenciaRegional(cnpjGerenciaRegional);
			}
		

			// Gerente da Gerencia Regional
			if (idCliente != null && !idCliente.trim().equals("")) {
				// Pesquisa o cliente na base
				FiltroCliente filtroCliente = new FiltroCliente();

				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, idCliente));

				Collection colecaoCliente = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());

				if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
					Cliente cliente = (Cliente) colecaoCliente.iterator().next();

					Integer clienteFuncionario = fachada.verificarClienteSelecionadoFuncionario(new Integer(idCliente));
					
					if(clienteFuncionario == null){
						throw new ActionServletException("atencao.cliente_selecionado_nao_e_funcionario");
					}
					
					gerenciaRegionalInserir.setCliente(cliente);

				} else {
					throw new ActionServletException("atencao.cliente.inexistente");
				}
			
			}
			

		// Ultima alteração
		gerenciaRegionalInserir.setUltimaAlteracao(new Date());


		// Verificar existência da Gerência Regional
		colecaoPesquisa = fachada.pesquisar(filtroGerenciaRegional,
				GerenciaRegional.class.getName());

		{
			Integer idGerenciaRegional = null;

			idGerenciaRegional = fachada
					.inserirGerenciaRegional(gerenciaRegionalInserir);

			montarPaginaSucesso(httpServletRequest,
					"Gerência Regional de código  "
							+ gerenciaRegionalInserir.getId()
							+ " inserida com sucesso.",
					"Inserir outra Gerência Regional",
					"exibirInserirGerenciaRegionalAction.do?menu=sim",
					"exibirAtualizarGerenciaRegionalAction.do?idRegistroAtualizacao="
							+ idGerenciaRegional,
					"Atualizar Gerência Regional Inserida");

		}

		sessao.removeAttribute("colecaoEnderecos");

		// devolve o mapeamento de retorno
		return retorno;
	}

}
