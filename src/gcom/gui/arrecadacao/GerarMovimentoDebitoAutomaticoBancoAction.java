package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroArrecadadorMovimento;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * processamento para gerar movimento de débito automático para o banco
 * 
 * @author Sávio Luiz
 * @date 20/04/2006
 */
public class GerarMovimentoDebitoAutomaticoBancoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarMovimentoDebitoAutomaticoBancoActionForm gerarMovimentoDebitoAutomaticoBancoActionForm = (GerarMovimentoDebitoAutomaticoBancoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// caso a opção do movimento de debito automatico seja diferente de
		// nulo
		if (gerarMovimentoDebitoAutomaticoBancoActionForm.getOpcaoMovimentoDebitoAutomatico() != null
				&& !gerarMovimentoDebitoAutomaticoBancoActionForm.getOpcaoMovimentoDebitoAutomatico().equals("")) {
			// Se a opção seja Gerar Movimento de Débito Automático
			if (gerarMovimentoDebitoAutomaticoBancoActionForm.getOpcaoMovimentoDebitoAutomatico().equals("1")) {
				// recupera o Map<Banco, Collection<DebitoAutomaticoMovimento>>
				// da sessao
				Map<Banco, Collection<DebitoAutomaticoMovimento>> debitosAutomaticoBancosMap = (Map<Banco, Collection<DebitoAutomaticoMovimento>>) sessao
						.getAttribute("debitosAutomaticoBancosMap");
				String[] idsBancos = gerarMovimentoDebitoAutomaticoBancoActionForm.getIdsCodigosBancos();
				// cria um Map<Banco, Collection<DebitoAutomaticoMovimento>>
				// novo para inserir só os que foram escolhidos
				if (debitosAutomaticoBancosMap != null && debitosAutomaticoBancosMap.size() != idsBancos.length) {
					Map<Banco, Collection<DebitoAutomaticoMovimento>> debitosAutomaticoBancosMapNovo = new HashMap();

					for (int i = 0; i < idsBancos.length; i++) {
						Integer idBanco = new Integer(idsBancos[i]);
						Iterator debitosAutomaticoBancosIterator = debitosAutomaticoBancosMap.keySet().iterator();
						while (debitosAutomaticoBancosIterator.hasNext()) {
							Banco banco = (Banco) debitosAutomaticoBancosIterator.next();
							if (banco.getId().equals(idBanco)) {
								Collection<DebitoAutomaticoMovimento> debitoAutomaticoMovimento = debitosAutomaticoBancosMap.get(banco);
								debitosAutomaticoBancosMapNovo.put(banco, debitoAutomaticoMovimento);
							}

						}
					}
					// manda os debitos automáticos selecionados
					fachada.gerarMovimentoDebitoAutomaticoBanco(debitosAutomaticoBancosMapNovo,
							(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

				} else {
					// caso todos os debitos automáticos forem selecionados
					fachada.gerarMovimentoDebitoAutomaticoBanco(debitosAutomaticoBancosMap,
							(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				}
			} else {
				// no caso da opção ser Regerar Arquivo TXT do Movimento de
				// Débito Automático
				String codigoRemessa = gerarMovimentoDebitoAutomaticoBancoActionForm.getCodigoRemessaMovimento();
				String identificacaoServicoMovimento = gerarMovimentoDebitoAutomaticoBancoActionForm.getIdentificacaoServicoMovimento();
				if (codigoRemessa.equals("1") && identificacaoServicoMovimento.equals(ConstantesSistema.DEBITO_AUTOMATICO)) {
					Integer idArrecadadorMovimento = new Integer(gerarMovimentoDebitoAutomaticoBancoActionForm.getIdArrecadadorMovimento());
					FiltroArrecadadorMovimento filtroArrecadadorMovimento = new FiltroArrecadadorMovimento();
					filtroArrecadadorMovimento.adicionarParametro(new ParametroSimples(FiltroArrecadadorMovimento.ID, idArrecadadorMovimento));
					Collection colecaoArrecadadorMovimento = fachada.pesquisar(filtroArrecadadorMovimento, ArrecadadorMovimento.class.getName());
					if (colecaoArrecadadorMovimento != null && !colecaoArrecadadorMovimento.isEmpty()) {
						ArrecadadorMovimento arrecadadorMovimento = (ArrecadadorMovimento) Util.retonarObjetoDeColecao(colecaoArrecadadorMovimento);

						String enviarBanco = gerarMovimentoDebitoAutomaticoBancoActionForm.getOpcaoEnvioParaBanco();

						fachada.regerarArquivoTxtMovimentoDebitoAutomatico(arrecadadorMovimento, enviarBanco,
								(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

					}
				} else {
					throw new ActionServletException("atencao.movimento.nao.envio");
				}
			}

		}
		gerarMovimentoDebitoAutomaticoBancoActionForm.setDataAtual(new Date());
		sessao.removeAttribute("colecaogerarMovimentoDebitoAutomatico");

		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest, "Movimento Débito Automatico Enviado para Processamento", "Voltar",
				"/exibirGerarMovimentoDebitoAutomaticoBancoAction.do");

		return retorno;
	}
}
