package gcom.gui.portal;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.gui.atendimentopublico.GerarCertidaoNegativaClienteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativaCliente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarCertidaoNegativaClienteAction extends ExibidorProcessamentoTarefaRelatorio {

	GerarCertidaoNegativaClienteActionForm form;
	private ActionErrors errors;

	private Cliente cliente;
	private Collection<Integer> idsTodosClientes;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		this.form = (GerarCertidaoNegativaClienteActionForm) actionForm;
		this.errors = form.validate();
		this.idsTodosClientes = new ArrayList<Integer>();

		if (errors.isEmpty()) {
			verificarCliente();

			if (cliente != null) {
				Collection<Integer> idsClientes = getFachada().pesquisarClientesAssociadosResponsavel(cliente.getId());
				idsClientes.add(cliente.getId());

				Collection<Integer> idsClientesAdicionados = new ArrayList<Integer>();

				while (idsClientes != null && !idsClientes.isEmpty()) {

					idsClientesAdicionados = new ArrayList<Integer>();

					for (Integer idCliente : idsClientes) {

						if (idsTodosClientes != null && !idsTodosClientes.contains(idCliente)) {
							Collection<Integer> idsClientesNovos = getFachada().pesquisarClientesAssociadosResponsavel(idCliente);

							ObterDebitoImovelOuClienteHelper debitos = getFachada().obterDebitoImovelOuCliente(2, null, idCliente.toString(), null, "000101", "999912", 
									getDataInicioVencimento(), getDataFimVencimento(), 1, 2, 2, 2, 1, 1, 2, null);

							if (debitos != null) {
								if ((debitos.getColecaoContasValores() != null && !debitos.getColecaoContasValores().isEmpty())
										|| (debitos.getColecaoDebitoACobrar() != null && !debitos.getColecaoDebitoACobrar().isEmpty())
										|| (debitos.getColecaoGuiasPagamentoValores() != null && !debitos.getColecaoGuiasPagamentoValores().isEmpty())) {

									adicionarErro(new ActionError("errors.portal.possui_debitos", "Cliente"));
								}
							}

							idsClientesAdicionados.addAll(idsClientesNovos);
							idsTodosClientes.add(idCliente);
						}
					}
					idsClientes = idsClientesAdicionados;
				}
			}
		}

		form.setIdCliente(null);

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("erro-certidao-cliente", true);
			return mapping.findForward("exibir");
		} else {
			request.removeAttribute("erro-certidao-cliente");
		}

		return processar(mapping, request, response);
	}

	private ActionForward processar(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) {
		TarefaRelatorio dados = new RelatorioCertidaoNegativaCliente(Usuario.USUARIO_TESTE);
		dados.addParametro("idsClientes", idsTodosClientes);
		dados.addParametro("clienteInformado", cliente);
		dados.addParametro("usuarioLogado", Usuario.USUARIO_TESTE);
		dados.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);

		return processarExibicaoRelatorio(dados, String.valueOf(TarefaRelatorio.TIPO_PDF), request, response, mapping);
	}

	private Date getDataFimVencimento() {
		SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
		Calendar dataFim = new GregorianCalendar();
		dataFim.add(Calendar.DATE, -sistemaParametro.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos());
		return dataFim.getTime();
	}

	private Date getDataInicioVencimento() {
		Calendar dataInicio = new GregorianCalendar();
		dataInicio.set(Calendar.YEAR, new Integer("0001").intValue());
		dataInicio.set(Calendar.MONTH, 0);
		dataInicio.set(Calendar.DATE, new Integer("01").intValue());
		return dataInicio.getTime();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void verificarCliente() {
		Filtro filtro = new FiltroCliente();
		filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getIdCliente()));
		filtro.adicionarCaminhoParaCarregamentoEntidade("clienteTipo.esferaPoder");

		Collection colecao = getFachada().pesquisar(filtro, Cliente.class.getName());
		if (colecao == null || colecao.isEmpty()) {
			adicionarErro(new ActionError("errors.portal.invalido", "Código do Cliente"));
		} else {
			cliente = (Cliente) Util.retonarObjetoDeColecao(colecao);
			if (cliente.getClienteTipo().getEsferaPoder().getIndicadorPermiteCertidaoNegativaDebitosParaCliente().equals(ConstantesSistema.NAO)) {
				adicionarErro(new ActionError("atencao.esfera_poder_nao_permite_geracao_certidao_negativa"));
			}
		}
	}
	
	private void adicionarErro(ActionError erro) {
		if (errors.isEmpty())
			errors.add("erro-certidao-cliente", erro);
	}
}
