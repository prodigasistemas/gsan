package gcom.gui.cobranca.cobrancaporresultado;

import gcom.batch.Processo;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.FiltroComandoEmpresaCobrancaConta;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1167] Consultar Comandos de Cobrança por Empresa
 * 
 * @author Mariana Victor
 * @since 03/05/2011
 */
public class ConsultarComandosContasCobrancaEmpresaAction extends ExibidorProcessamentoTarefaRelatorio  {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException  {

		
		ActionForward retorno = actionMapping.findForward("telaSucesso");


		ConsultarComandosContasCobrancaEmpresaActionForm form = (ConsultarComandosContasCobrancaEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Integer idRegistro = new Integer(form.getIdRegistro()) ;
		
		String idEmpresa = form.getIdEmpresa();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		FiltroComandoEmpresaCobrancaConta filtro = new FiltroComandoEmpresaCobrancaConta();
		filtro.adicionarParametro(new ParametroSimples(FiltroComandoEmpresaCobrancaConta.ID, idRegistro));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroComandoEmpresaCobrancaConta.COBRANCA_SITUACAO);
		
		Collection colecao = fachada.pesquisar(filtro, ComandoEmpresaCobrancaConta.class.getName());

		if (colecao == null 
				|| colecao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", 
					null, "Comandos de Contas em Cobrança");
		} 
		
		ComandoEmpresaCobrancaConta comando = (ComandoEmpresaCobrancaConta) Util.retonarObjetoDeColecao(colecao);
		
		if (httpServletRequest.getParameter("confirmado") != null
				&& httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {

			Map parametros = new HashMap();
			parametros.put("idEmpresa", idEmpresa);
			parametros.put("idRegistro", idRegistro);
			parametros.put("idCobrancaSituacao", 
					comando.getCobrancaSituacao().getId());
			
			fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
	          		Processo.ENCERRAR_COMANDO_DE_COBRANCA_POR_EMPRESA, usuarioLogado);
		} else {
		
			if (comando.getDataEncerramento() != null) {
				throw new ActionServletException("atencao.comando.ja_encerrado", 
						null, "Comandos de Contas em Cobrança");
			} 
			if (comando.getIndicadorGeracaoTxt() == null 
					|| comando.getIndicadorGeracaoTxt().equals(new Integer(2))) {
				
				httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/consultarComandosContasCobrancaEmpresaAction.do");
				
				// Monta a página de confirmação do wizard para perguntar se
				// o usuário quer confirmar o encerramento do comando 
				// mesmo sem ter sido enviado para a empresa contratada
				return montarPaginaConfirmacao(
						"atencao.comando.nao_enviado.confirmacao",
						httpServletRequest, actionMapping);
				
			}
			
			Map parametros = new HashMap();
			parametros.put("idEmpresa", idEmpresa);
			parametros.put("idRegistro", idRegistro);
			parametros.put("idCobrancaSituacao", 
					comando.getCobrancaSituacao().getId());
			
			fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
	          		Processo.ENCERRAR_COMANDO_DE_COBRANCA_POR_EMPRESA, usuarioLogado);
			
		}
			
		//montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Encerramento do Comando de Cobrança por Empresa Enviado para Processamento", 
			"Voltar",
			"exibirConsultarComandosContasCobrancaEmpresaAction.do?menu=sim");

	
		return retorno;
	}

}
