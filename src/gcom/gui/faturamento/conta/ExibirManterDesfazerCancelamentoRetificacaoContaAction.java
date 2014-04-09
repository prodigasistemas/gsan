package gcom.gui.faturamento.conta;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela exibição dos dados na tela do 
 * 	desfazer cancelamento e/ou retificação da conta. 
 * 
 * [UC0327] Desfazer Cancelamento e/ou Retificação de Conta 
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
public class ExibirManterDesfazerCancelamentoRetificacaoContaAction extends
		GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("desfazerCancelamentoRetificacaoConta");

		DesfazerCancelamentoRetificacaoContaActionForm desfazerCancelamentoRetificacaoContaActionForm = (DesfazerCancelamentoRetificacaoContaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// -------Parte que trata do código quando o usuário tecla enter
		// Matrícula do Imóvel
		String codigoDigitadoImovelEnter = (String) desfazerCancelamentoRetificacaoContaActionForm
				.getIdImovel();

		// Se o código do imóvel tiver sido digitado seta no form os dados do imóvel
		if (codigoDigitadoImovelEnter != null
				&& !codigoDigitadoImovelEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoImovelEnter) > 0) {

			Imovel imovel = fachada.pesquisarImovelRegistroAtendimento(new Integer(codigoDigitadoImovelEnter));
			
			if (imovel != null) {

				// O imovel foi encontrado
				desfazerCancelamentoRetificacaoContaActionForm.setIdImovel(""
						+ imovel.getId());

				desfazerCancelamentoRetificacaoContaActionForm
						.setInscricaoImovel(imovel.getInscricaoFormatada());

				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoAgua(imovel.getLigacaoAguaSituacao()
								.getDescricao());

				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoEsgoto(imovel.getLigacaoEsgotoSituacao()
								.getDescricao());

				
				Cliente cliente = fachada.pesquisarClienteUsuarioImovel(new Integer(codigoDigitadoImovelEnter));

				// Manda os dados do cliente para a página
				if (cliente != null) {
					desfazerCancelamentoRetificacaoContaActionForm.setNomeClienteUsuario(cliente
							.getNome());
				}
				

				Collection contas = fachada.obterContasImovelManter(imovel, DebitoCreditoSituacao.CANCELADA,
						DebitoCreditoSituacao.CANCELADA, DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO);

				/**
				 * Só sera enviada contas que ta na situação retificada ou cancelada
				 * Alterado por Arthur Carvalho
				 * Analista Eduardo Rosa
				 * Data:14/05/2010
				 */
				Iterator iteratorContas = contas.iterator();
				Collection colecaoContas = new ArrayList();
				while ( iteratorContas.hasNext() ) {
					
					Conta conta = (Conta) iteratorContas.next();
					//RETIFICADA
					if ( conta.getDebitoCreditoSituacaoAtual().getId().equals(
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO ) ) {
					
						FiltroConta filtroConta = new FiltroConta();
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.REFERENCIA, 
								conta.getReferencia()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.IMOVEL_ID, 
								conta.getImovel().getId()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL,
								DebitoCreditoSituacao.RETIFICADA ));
						
						Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName() ) ;
						
						if( colecaoConta != null && !colecaoConta.isEmpty() ) {
							
							colecaoContas.add(conta);
						}
						//CANCELADA
					} else if ( conta.getDebitoCreditoSituacaoAtual().getId().equals(
							DebitoCreditoSituacao.CANCELADA ) ) {
						
						FiltroConta filtroConta = new FiltroConta();
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.REFERENCIA, 
								conta.getReferencia()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.IMOVEL_ID, 
								conta.getImovel().getId()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL,
								DebitoCreditoSituacao.CANCELADA ));
						
						Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName() ) ;
						
						if( colecaoConta != null && !colecaoConta.isEmpty() ) {
							
							colecaoContas.add(conta);
						}
					}
					
				}
				// Manda os dados da conta para a página
				if (colecaoContas != null && !colecaoContas.isEmpty()) {
					sessao.setAttribute("contas", colecaoContas);
				}
				else
				{
					throw new ActionServletException(
							"atencao.pesquisa.nenhuma.conta_cancelada_retificada_imovel", null, ""
									+ codigoDigitadoImovelEnter);
				}
			} else {
				httpServletRequest.setAttribute("corImovel", "exception");
				desfazerCancelamentoRetificacaoContaActionForm
						.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
				desfazerCancelamentoRetificacaoContaActionForm
						.setIdImovel("");
				desfazerCancelamentoRetificacaoContaActionForm
						.setNomeClienteUsuario("");
				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoAgua("");
				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoEsgoto("");
				sessao.removeAttribute("contas");
			}
		}
		return retorno;
	}
}
