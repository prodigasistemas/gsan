package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.GerarCertidaoNegativaClienteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativaCliente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00725] Gerar Relatório de Imóveis por Situação da Ligação de Água
 * 
 * @author Rafael Pinto
 *
 * @date 28/11/2007
 */

public class GerarCertidaoNegativaClienteAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		// Form
		GerarCertidaoNegativaClienteActionForm form = 
			(GerarCertidaoNegativaClienteActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuarioLogado = this.getUsuarioLogado( httpServletRequest );
		
		// Imovel que foi informado
		Collection<Integer> idsTodosClientes = new ArrayList<Integer>();
		Cliente clienteInformado = new Cliente();
		
		if (form.getIdCliente() != null &&  
			!form.getIdCliente().trim().equals("") ) {
			
			Integer idClienteInformado = Integer.valueOf( form.getIdCliente() );
			
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idClienteInformado));
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo.esferaPoder");
			
			Collection colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			
			if (colecaoClientes == null || colecaoClientes.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Cliente");
			} else {
				clienteInformado = (Cliente) Util.retonarObjetoDeColecao(colecaoClientes);
				
				if (clienteInformado.getClienteTipo().getEsferaPoder().getIndicadorPermiteCertidaoNegativaDebitosParaCliente().equals(ConstantesSistema.NAO)) {
					throw new ActionServletException("atencao.esfera_poder_nao_permite_geracao_certidao_negativa");
				}
			}
			
			boolean temPermissaoEmitirCertidaoNegativaComClienteSuperior = fachada
					.verificarPermissaoEmitirCertidaoNegativaComClienteSuperior(usuarioLogado);
			
			if (!temPermissaoEmitirCertidaoNegativaComClienteSuperior) {

				// Verifica se o cliente informado tem algum superior, caso
				// tenha informa ao usuário
				filtroCliente
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroCliente.CLIENTE_RESPONSAVEL_ID,
								idClienteInformado));

				colecaoClientes = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());

				if (colecaoClientes != null && !colecaoClientes.isEmpty()) {
					throw new ActionServletException(
							"atencao.existe_cliente_superior");
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
								
								throw new ActionServletException("atencao.cliente_com_debitos");
							}
						}

						idsClientesAdicionados.addAll(idsClientesNovos);
						idsTodosClientes.add(idCliente);
					}
				}
				
//				if (idsTodosClientes == null || idsTodosClientes.isEmpty()) {
//					idsTodosClientes = idsClientes;
//				}
				
//				idsTodosClientes.addAll(idsClientesAdicionados);
				idsClientes = idsClientesAdicionados;
				
			}
		}		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");	
		
		TarefaRelatorio relatorio = 
			new RelatorioCertidaoNegativaCliente( usuarioLogado );		
		
		relatorio.addParametro("idsClientes", idsTodosClientes);
		relatorio.addParametro("clienteInformado", clienteInformado);
		relatorio.addParametro("usuarioLogado", usuarioLogado);
		
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
