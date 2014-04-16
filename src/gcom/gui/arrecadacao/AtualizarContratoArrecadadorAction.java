package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Kássia Albuquerque
 * @date 08/01/2007
 * 
 * Alterado por Vinicius Medeiros
 * @date 06/06/2008
 */

public class AtualizarContratoArrecadadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarContratoArrecadadorActionForm atualizarContratoArrecadadorActionForm = (AtualizarContratoArrecadadorActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) sessao
				.getAttribute("arrecadadorContrato");

		String[] idArrecadador = new String[1];

		idArrecadador[0] = arrecadadorContrato.getArrecadador().getId()
				.toString();

		// Arrecadador
		if (atualizarContratoArrecadadorActionForm.getIdClienteCombo() != null
				&& !atualizarContratoArrecadadorActionForm.getIdClienteCombo()
						.equals("")) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			// Inclui a obejeto de cliente no filtro de arrecadador
			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");
			// filtra arrecadador pelo cliente
			filtroArrecadador
					.adicionarParametro(new ParametroSimples(
							FiltroArrecadador.CLIENTE_ID,
							atualizarContratoArrecadadorActionForm
									.getIdClienteCombo()));
			// Preenche colecao de arrecadador baseado no cliente escolhido
			Collection<Arrecadador> colecaoArrecadador = fachada.pesquisar(
					filtroArrecadador, Arrecadador.class.getName());

			if (colecaoArrecadador != null && !colecaoArrecadador.isEmpty()) {
				Iterator iteratorColecaoArrecadador = colecaoArrecadador
						.iterator();
				while (iteratorColecaoArrecadador.hasNext()) {
					Arrecadador arrecadador = (Arrecadador) iteratorColecaoArrecadador
							.next();
					arrecadadorContrato.setArrecadador(arrecadador);
				}
			} else {
				arrecadadorContrato.setArrecadador(null);
			}
		}

		arrecadadorContrato
				.setNumeroContrato(atualizarContratoArrecadadorActionForm
						.getNumeroContrato());
		// Conta Deposito Arrecadador
		FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
		if (atualizarContratoArrecadadorActionForm
				.getIdContaBancariaArrecadador() != null
				&& !atualizarContratoArrecadadorActionForm
						.getIdContaBancariaArrecadador().equals("")) {
			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.ID,
					atualizarContratoArrecadadorActionForm
							.getIdContaBancariaArrecadador()));
			Collection<ContaBancaria> colecaoContaBancariaArrecadador = fachada
					.pesquisar(filtroContaBancaria, ContaBancaria.class
							.getName());
			if (colecaoContaBancariaArrecadador != null
					&& !colecaoContaBancariaArrecadador.isEmpty()) {
				Iterator iteratorColecaoContaBancariaArrecadador = colecaoContaBancariaArrecadador
						.iterator();
				while (iteratorColecaoContaBancariaArrecadador.hasNext()) {
					ContaBancaria contaBancariaArrecadador = (ContaBancaria) iteratorColecaoContaBancariaArrecadador
							.next();
					arrecadadorContrato
							.setContaBancariaDepositoArrecadacao(contaBancariaArrecadador);
				}
			} else {
				arrecadadorContrato.setContaBancariaDepositoArrecadacao(null);
			}
		}

		// Conta Deposito Tarifa
		filtroContaBancaria = new FiltroContaBancaria();
		if (atualizarContratoArrecadadorActionForm.getIdContaBancariaTarifa() != null
				&& !atualizarContratoArrecadadorActionForm
						.getIdContaBancariaTarifa().equals("")) {
			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.ID,
					atualizarContratoArrecadadorActionForm
							.getIdContaBancariaTarifa()));
			Collection<ContaBancaria> colecaoContaBancariaTarifa = fachada
					.pesquisar(filtroContaBancaria, ContaBancaria.class
							.getName());
			if (colecaoContaBancariaTarifa != null
					&& !colecaoContaBancariaTarifa.isEmpty()) {

				Iterator iteratorColecaoContaBancariaTarifa = colecaoContaBancariaTarifa
						.iterator();
				while (iteratorColecaoContaBancariaTarifa.hasNext()) {
					ContaBancaria contaBancariaTarifa = (ContaBancaria) iteratorColecaoContaBancariaTarifa
							.next();
					arrecadadorContrato
							.setContaBancariaDepositoTarifa(contaBancariaTarifa);
				}
			} else {
				arrecadadorContrato.setContaBancariaDepositoTarifa(null);
			}
		}
		// Cliente
		Cliente cliente = new Cliente();
		cliente.setId(new Integer(atualizarContratoArrecadadorActionForm
				.getIdCliente()));

		// [FS0004]-Verificar se pessoa física
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				cliente.getId()));
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
		Collection colecaoCliente = fachada.pesquisar(filtroCliente,
				Cliente.class.getName());
		Cliente clientePesq = (Cliente) Util
				.retonarObjetoDeColecao(colecaoCliente);

		if (clientePesq.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null
				&& clientePesq.getClienteTipo()
						.getIndicadorPessoaFisicaJuridica().equals(
								new Short("2"))) {
			throw new ActionServletException(
					"atencao.cliente_arrecadador_pessoa_fisica");
		}

		arrecadadorContrato.setCliente(cliente);

		// Código Convenio
		arrecadadorContrato
				.setCodigoConvenio(atualizarContratoArrecadadorActionForm
						.getIdConvenio());

		// IndicadorCobrancaISS
		if (atualizarContratoArrecadadorActionForm.getIndicadorCobranca() != null) {
			arrecadadorContrato.setIndicadorCobrancaIss(new Short(
					atualizarContratoArrecadadorActionForm
							.getIndicadorCobranca()));
		} else {
			arrecadadorContrato.setIndicadorCobrancaIss(null);
		}
		// Intervalo de Datas do Contrato
		arrecadadorContrato.setDataContratoInicio(Util
				.converteStringParaDate(atualizarContratoArrecadadorActionForm
						.getDtInicioContrato()));
		arrecadadorContrato.setDataContratoFim(Util
				.converteStringParaDate(atualizarContratoArrecadadorActionForm
						.getDtFimContrato()));

		String emailCliente = atualizarContratoArrecadadorActionForm
				.getEmailCliente();

		// email
		if (emailCliente != null && !emailCliente.trim().equals("")) {
			arrecadadorContrato.setDescricaoEmail(emailCliente);
		} else {
			arrecadadorContrato.setDescricaoEmail(null);
		}

		arrecadadorContrato.setTamanhoMaximoIdentificacaoImovel(new Integer(
				atualizarContratoArrecadadorActionForm
						.getTamanhoMaximoIdentificacaoImovel()).shortValue());

		//Contrato Tarifa
		Collection colecaoArrecadadorContratoTarifa = (Collection) 
		sessao.getAttribute("colecaoArrecadadorContratoTarifaSelecionados");
		sessao.setAttribute("colecaoArrecadadorContratoTarifaSelecionados", null);
		
		if (colecaoArrecadadorContratoTarifa == null || colecaoArrecadadorContratoTarifa.isEmpty()) {
			throw new ActionServletException("atencao.required", null,
					"pelo menos uma Tarifa de Contrato");
		}
		
		
		// atualiza na base de dados de Arrecadador
		fachada
				.atualizarContratoArrecadador(arrecadadorContrato, colecaoArrecadadorContratoTarifa,
						usuarioLogado);

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Contrato de Arrecadador "
				+ atualizarContratoArrecadadorActionForm.getNumeroContrato()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Contrato de Arrecadador",
				"exibirFiltrarContratoArrecadadorAction.do?menu=sim");

		return retorno;
	}

}
