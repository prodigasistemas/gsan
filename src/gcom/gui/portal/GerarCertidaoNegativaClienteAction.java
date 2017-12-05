package gcom.gui.portal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.atendimentopublico.GerarCertidaoNegativaClienteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativaCliente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * 
 * @author Ewertton Bravo
 *
 * @date 08/11/2017
 */

public class GerarCertidaoNegativaClienteAction extends ExibidorProcessamentoTarefaRelatorio {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		GerarCertidaoNegativaClienteActionForm form = 
			(GerarCertidaoNegativaClienteActionForm) actionForm;
		ActionErrors errors = form.validate(actionMapping, httpServletRequest);
		
		Fachada fachada = Fachada.getInstancia();
		
		
		// Imovel que foi informado
		Collection<Integer> idsTodosClientes = new ArrayList<Integer>();
		Cliente clienteInformado = new Cliente();
		
		if (form.getIdCliente() != null &&  
			!form.getIdCliente().trim().equals("") ) {
			
			Integer idClienteInformado = -1;
			try {
				idClienteInformado = Integer.valueOf( form.getIdCliente() );
			} catch (NumberFormatException nfe) {
				errors.add("idCliente", new ActionError("atencao.pesquisa_inexistente", "Matrícula"));
			}
			
			if (idClienteInformado > -1) {
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idClienteInformado));
				filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo.esferaPoder");
				
				@SuppressWarnings("rawtypes")
				Collection colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
				
				if (colecaoClientes == null || colecaoClientes.isEmpty()) {
					errors.add("idCliente", new ActionError("atencao.pesquisa_inexistente", "Matrícula"));
				} else {
					clienteInformado = (Cliente) Util.retonarObjetoDeColecao(colecaoClientes);
					if (clienteInformado.getClienteTipo().getEsferaPoder().getIndicadorPermiteCertidaoNegativaDebitosParaCliente().equals(ConstantesSistema.NAO)) {
						errors.add("idCliente", new ActionError("atencao.esfera_poder_nao_permite_geracao_certidao_negativa"));
					}
				}

				Integer tipoCliente = null;
				if (form.getResponsavel() != null
						&& form.getResponsavel().equals("1")) {
					tipoCliente = new Integer(3);
				} else if (form.getResponsavel() != null
						&& form.getResponsavel().equals("2")) {
					tipoCliente = new Integer(4);
				} else {
					tipoCliente = new Integer(2);
				}
				
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				Collection<Integer> idsClientes = fachada.pesquisarClientesAssociadosResponsavel(idClienteInformado);
				idsClientes.add(idClienteInformado);
				
				Collection<Integer> idsClientesAdicionados = new ArrayList<Integer>();
				
				// data inicio vencimento debito
				Calendar dataInicioVencimentoDebito = new GregorianCalendar();
				dataInicioVencimentoDebito.set(Calendar.YEAR,
						new Integer("0001").intValue());
				dataInicioVencimentoDebito.set(Calendar.MONTH, 0);
				dataInicioVencimentoDebito.set(Calendar.DATE,
						new Integer("01").intValue());

				// data final de vencimento de debito
				Calendar dataFimVencimentoDebito = new GregorianCalendar();
				dataFimVencimentoDebito.add(Calendar.DATE, -sistemaParametro.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos());
				
				while (idsClientes != null && !idsClientes.isEmpty()) {

					idsClientesAdicionados = new ArrayList<Integer>();

					for (Integer idCliente : idsClientes) {

						if (idsTodosClientes != null && !idsTodosClientes.contains(idCliente)) {
							Collection<Integer> idsClientesNovos = fachada
									.pesquisarClientesAssociadosResponsavel(idCliente);

							ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = fachada
									.obterDebitoImovelOuCliente(tipoCliente, null, idCliente
											.toString(), null, "000101", "999912",
											dataInicioVencimentoDebito.getTime(),
											dataFimVencimentoDebito.getTime(), 1,
											2, 2, 2, 1, 1, 2, null);

							if (obterDebitoImovelOuClienteHelper != null) {
								if ((obterDebitoImovelOuClienteHelper
										.getColecaoContasValores() != null && !obterDebitoImovelOuClienteHelper
										.getColecaoContasValores().isEmpty())
										|| (obterDebitoImovelOuClienteHelper
												.getColecaoDebitoACobrar() != null && !obterDebitoImovelOuClienteHelper
												.getColecaoDebitoACobrar()
												.isEmpty())
										|| (obterDebitoImovelOuClienteHelper
												.getColecaoGuiasPagamentoValores() != null && !obterDebitoImovelOuClienteHelper
												.getColecaoGuiasPagamentoValores()
												.isEmpty())) {
									
									errors.add("idCliente", new ActionError("atencao.cliente_com_debitos"));
								}
							}

							idsClientesAdicionados.addAll(idsClientesNovos);
							idsTodosClientes.add(idCliente);
						}
					}
					idsClientes = idsClientesAdicionados;
				}
			}
		} else {
			errors.add("idCliente", new ActionError("atencao.pesquisa_inexistente", "Matrícula"));
		}
		
		if (!errors.isEmpty() ) {
			saveErrors(httpServletRequest, errors);
			return actionMapping.findForward("exibir");
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");	
		TarefaRelatorio relatorio = 
			new RelatorioCertidaoNegativaCliente( Usuario.USUARIO_TESTE );		
		
		relatorio.addParametro("idsClientes", idsTodosClientes);
		relatorio.addParametro("clienteInformado", clienteInformado);
		relatorio.addParametro("usuarioLogado", Usuario.USUARIO_TESTE);
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
}
