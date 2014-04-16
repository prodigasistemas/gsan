package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.util.ConstantesSistema;
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

public class InserirContaBancariaAction extends GcomAction {

	/**
	 * Este caso de uso permite inserir uma Agência Bancária
	 * 
	 * [UC0518] Inserir Conta Bancária
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
		
		InserirContaBancariaActionForm inserirContaBancariaActionForm = (InserirContaBancariaActionForm) actionForm;

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_AGENCIA_BANCARIA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		String banco = inserirContaBancariaActionForm.getBanco();
		String agenciaBancaria = inserirContaBancariaActionForm
				.getAgenciaBancaria();
		String contaBanco = inserirContaBancariaActionForm.getContaBanco();
		String contaContabil = inserirContaBancariaActionForm
				.getContaContabil();

		ContaBancaria contaBancariaInserir = new ContaBancaria();
		Collection colecaoPesquisa = null;

		sessao.removeAttribute("tipoPesquisaRetorno");

		if (Util.validarNumeroMaiorQueZERO(inserirContaBancariaActionForm
				.getBanco())) {
			// Constrói o filtro para pesquisa do Banco
			FiltroBanco filtroBanco = new FiltroBanco();
			filtroBanco.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.AGENCIA_BANCO_ID,
					inserirContaBancariaActionForm.getBanco()));
		}

		if (Util.validarNumeroMaiorQueZERO(inserirContaBancariaActionForm
				.getAgenciaBancaria())) {
			// Constrói o filtro para pesquisa da Agencia
			FiltroAgencia filtroAgencia = new FiltroAgencia();
			filtroAgencia.adicionarParametro(new ParametroSimples(
					FiltroAgencia.ID, inserirContaBancariaActionForm
							.getAgenciaBancaria()));
			filtroAgencia.adicionarCaminhoParaCarregamentoEntidade("banco");

			// Realiza a pesquisa serviço Agência
			Agencia agencia = (Agencia) fachada.pesquisar(filtroAgencia,
					Agencia.class.getName()).iterator().next();
			contaBancariaInserir.setAgencia(agencia);
		}

		// O numero da Conta é obrigatório.
		if (contaBanco == null || contaBanco.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Conta Bancária");
		}
		
		contaBancariaInserir.setNumeroConta(contaBanco);
		
		if (contaContabil != null && !contaContabil.trim().equals("")) {
			contaBancariaInserir.setNumeroContaContabil(new Integer(contaContabil));
		}
		

		// O numero da Conta Contabil é obrigatório.
//		if (contaContabil == null || contaContabil.equalsIgnoreCase("")) {
//			throw new ActionServletException("atencao.required", null,
//					"Nome da Agência Bancária");
//		}

		// Banco.
		if (banco != null
				&& !banco.equalsIgnoreCase(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			FiltroBanco filtroBanco = new FiltroBanco();

			filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID,
					banco));

			filtroBanco.adicionarParametro(new ParametroSimples(
					FiltroBanco.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Agencia.
			if (agenciaBancaria != null
					&& !agenciaBancaria.equalsIgnoreCase(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				FiltroAgencia filtroAgencia = new FiltroAgencia();

				filtroAgencia.adicionarParametro(new ParametroSimples(
						FiltroAgencia.ID, agenciaBancaria));

				// Ultima alteração
				contaBancariaInserir.setUltimaAlteracao(new Date());

				FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();

				filtroContaBancaria.adicionarParametro(new ParametroSimples(
						FiltroContaBancaria.ID, contaBancariaInserir.getId()));

				if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
					// Conta Bancária já existe
					throw new ActionServletException(
							"atencao.pesquisa_agencia_ja_cadastrada", null,
							contaBanco);
				} else {
					Integer idContaBancaria = null;

					idContaBancaria = fachada
							.inserirContaBancaria(contaBancariaInserir);

					montarPaginaSucesso(httpServletRequest,
							"Conta Bancaria de código  "
									+ contaBancariaInserir.getId()
									+ " inserida com sucesso.",
							"Inserir outra Conta Bancaria",
							"exibirInserirContaBancariaAction.do?menu=sim",
							"exibirAtualizarContaBancariaAction.do?inserir=sim&idRegistroAtualizacao="
									+ idContaBancaria,
							"Atualizar Agencia Conta Inserida");

				}

			}
		}
		return retorno;

	}
}
