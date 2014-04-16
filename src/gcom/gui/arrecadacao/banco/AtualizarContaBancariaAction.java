package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarContaBancariaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarContaBancariaActionForm atualizarContaBancariaActionForm = (AtualizarContaBancariaActionForm) actionForm;

		ContaBancaria contaBancaria = (ContaBancaria) sessao
				.getAttribute("contaBancariaAtualizar");

		String contaContabil = atualizarContaBancariaActionForm
				.getContaContabil();

		// contaBancaria.setId(new Integer(atualizarContaBancariaActionForm
		// .getCodigo()));
		contaBancaria.setNumeroConta(atualizarContaBancariaActionForm
				.getContaBanco());
		// contaBancaria.setNumeroContaContabil(new
		// Integer(atualizarContaBancariaActionForm.getContaContabil()));

		String contaBanco = atualizarContaBancariaActionForm.getContaBanco();

		// String contaContabil = atualizarContaBancariaActionForm
		// .getContaContabil();

		// O numero da Conta Contabil
		if (contaContabil != null && !contaContabil.equalsIgnoreCase("")) {
			contaBancaria.setNumeroContaContabil(new Integer(contaContabil));
		}

		contaBancaria.setNumeroConta(contaBanco);

		if (atualizarContaBancariaActionForm.getAgenciaBancaria() != null) {

			Integer idAgenciaBancaria = new Integer(
					atualizarContaBancariaActionForm.getAgenciaBancaria());

			if (idAgenciaBancaria
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				contaBancaria.setAgencia(null);
			} else {
				FiltroAgencia filtroAgencia = new FiltroAgencia();
				filtroAgencia.adicionarParametro(new ParametroSimples(
						FiltroAgencia.ID, atualizarContaBancariaActionForm
								.getAgenciaBancaria().toString()));
				Collection colecaoAgencia = (Collection) fachada.pesquisar(
						filtroAgencia, Agencia.class.getName());

				// setando
				contaBancaria.setAgencia((Agencia) colecaoAgencia.iterator()
						.next());
			}
		}
		
		if (atualizarContaBancariaActionForm.getBanco() != null) {

			Integer idBanco = new Integer(atualizarContaBancariaActionForm
					.getBanco());

			if (idBanco.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				contaBancaria.getAgencia().setBanco(null);
			} else {
				FiltroBanco filtroBanco = new FiltroBanco();
				filtroBanco.adicionarParametro(new ParametroSimples(
						FiltroBanco.ID, atualizarContaBancariaActionForm
								.getBanco().toString()));
				Collection colecaoBanco = (Collection) fachada.pesquisar(
						filtroBanco, Banco.class.getName());

				// setando
				contaBancaria.getAgencia().setBanco(
						(Banco) colecaoBanco.iterator().next());
			}
		}

		fachada.atualizarContaBancaria(contaBancaria);

		montarPaginaSucesso(httpServletRequest, "Conta Bancária de código "
				+ contaBancaria.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Agência Bancaria",
				"exibirFiltrarContaBancariaAction.do?menu=sim");
		return retorno;
	}
}
