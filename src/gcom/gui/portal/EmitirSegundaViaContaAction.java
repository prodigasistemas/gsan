package gcom.gui.portal;

import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável pela emissão da segunda via da conta no portal da COMPESA
 * 
 * @author Diogo Peixoto
 * @date 17/05/2011
 */
public class EmitirSegundaViaContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;

		try {
			EmitirSegundaViaContaActionForm form = (EmitirSegundaViaContaActionForm) actionForm;
			HttpSession sessao = httpServletRequest.getSession(true);
			form.setMatricula(String.valueOf((Integer) sessao.getAttribute("matricula")));
			form.setNomeUsuario((String) sessao.getAttribute("nomeUsuario"));
			
			if (form.getMatricula() != null && !form.getMatricula().equals("")) {
				
				BigDecimal totalContas = new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidadeContas = new BigDecimal("0.00");

				Short nDiasVencimentoCobranca = Fachada.getInstancia().pesquisarParametrosDoSistema().getNumeroDiasVencimentoCobranca();
				Date dataDebito = new Date();
				Calendar calendar = Calendar.getInstance();

				// Data Atual - Numero de dias vencimento Cobranca
				calendar.add(Calendar.DAY_OF_MONTH, -nDiasVencimentoCobranca.shortValue());
				dataDebito = calendar.getTime();
				form.setData(Util.formatarData(dataDebito));

				// Ano mes Atual.
				String ano;
				String mes;

				Date dataCorrente = new Date();
				String dataCorrenteTexto = Util.formatarData(dataCorrente);
				ano = dataCorrenteTexto.substring(6, 10);
				mes = dataCorrenteTexto.substring(3, 5);

				String anoMesInicialReferenciaDebito = "198501";
				String anoMesFinalReferenciaDebito = ano + mes;

				// Date aux1 = dataInicioVencimentoDebito.getTime();
				Date aux1 = Util.converteStringParaDate("01/01/1985");

				// Date aux2 = dataFimVencimentoDebito.getTime();
				Date aux2 = Util.converteStringParaDate("31/12/9999");

				String tipoRelacao = "-1";

				ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = (ObterDebitoImovelOuClienteHelper) this
						.getFachada().obterDebitoImovelOuCliente(1,
								form.getMatricula(), null,
								new Short(tipoRelacao),
								anoMesInicialReferenciaDebito,
								anoMesFinalReferenciaDebito, aux1, aux2, 1, 1,
								1, 1, 1, 1, 1, null);

				Collection<ContaValoresHelper> colecaoContasValores = obterDebitoImovelOuClienteHelper.getColecaoContasValores();

				if (colecaoContasValores == null || colecaoContasValores.isEmpty()) {
					retorno = actionMapping.findForward("matriculaSemDebito");
					httpServletRequest.setAttribute("imovelSemDebito", true);
				} else {
					retorno = actionMapping.findForward("emitirSegundaViaContaAction");

					Iterator<ContaValoresHelper> colecaoContasValoresIterator = colecaoContasValores.iterator();
					httpServletRequest.setAttribute("voltarServicos", true);

					while (colecaoContasValoresIterator.hasNext()) {
						ContaValoresHelper contaValoresHelper = (ContaValoresHelper) colecaoContasValoresIterator.next();
						totalContas = totalContas.add(contaValoresHelper.getValorTotalConta());
						valorTotalAcrescimoImpontualidadeContas = 
							valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValores());
					}
					form.setValorDebito(Util.formatarMoedaReal(totalContas));
				}

				ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = this.getFachada().obterDebitoImovelOuCliente(
								1, // Indicador débito imóvel
								form.getMatricula(), // Matrícula do imóvel
								null, // Código do cliente
								null, // Tipo de relação do cliento com o //
										// imóvel
								"000101", // Referência inicial do débito
								"999912", // Referência final do débito
								Util.converteStringParaDate("01/01/0001"), // Inicio
																			// Vencimento
								Util.converteStringParaDate("31/12/9999"), // Final
																			// Vencimento
								1, // Indicador pagamento
								1, // Indicador conta em revisão
								1, // Indicador débito a cobrar
								1, // Indicador crédito a realizar
								1, // Indicador notas promissórias
								1, // Indicador guias de pagamento
								1, // Indicador acréscimos por impontualidade
								null); // Indicador Contas

				Collection<DebitoACobrar> colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();
				BigDecimal valorTotalDebitoACobrar = new BigDecimal("0.00");

				if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
					Iterator<DebitoACobrar> debitoACobrarValores = colecaoDebitoACobrar.iterator();
					while (debitoACobrarValores.hasNext()) {
						DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();
						valorTotalDebitoACobrar = valorTotalDebitoACobrar.add(debitoACobrar.getValorTotalComBonus());
					}
				}

				httpServletRequest.setAttribute("totalContas", totalContas);
				httpServletRequest.setAttribute("colecaoContasValores", colecaoContasValores);
				
				// Acrescimos por Impotualidade
				BigDecimal retornoSoma = new BigDecimal("0.00");

				retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
				valorTotalDebitoACobrar = valorTotalDebitoACobrar.add(retornoSoma);
				form.setValorDebitoCobrado(Util.formatarMoedaReal(valorTotalDebitoACobrar));
			}
		} catch (Exception e) {
			retorno = actionMapping.findForward("erroSistemaPortal");
			httpServletRequest.setAttribute("erroSistema", true);
		}
		
		return retorno;
	}
}