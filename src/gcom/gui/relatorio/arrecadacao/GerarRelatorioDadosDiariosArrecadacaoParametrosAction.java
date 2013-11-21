package gcom.gui.relatorio.arrecadacao;

import java.util.Collection;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioDadosDiariosArrecadacaoParametros;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação por Gerência
 * 
 * @author Mariana Victor
 * @date 01/02/2011
 */
public class GerarRelatorioDadosDiariosArrecadacaoParametrosAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		Collection<ImovelPerfil> colecaoImovelPerfil = null;
		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = null;
		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = null;
		Collection<Categoria> colecaoCategoria = null;
		Collection<EsferaPoder> colecaoEsferaPoder = null;
		Collection colecaoDocumentoTipo = null;
		FiltrarDadosDiariosArrecadacaoActionForm filtrarDadosDiariosArrecadacaoActionForm = new FiltrarDadosDiariosArrecadacaoActionForm();

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		if (sessao.getAttribute("colecaoImovelPerfilResultado") != null) {
			colecaoImovelPerfil = (Collection<ImovelPerfil>)sessao.getAttribute("colecaoImovelPerfilResultado");
		}
		if (sessao.getAttribute("colecaoLigacaoAguaSituacaoResultado") != null) {
			colecaoLigacaoAguaSituacao = (Collection<LigacaoAguaSituacao>)sessao.getAttribute("colecaoLigacaoAguaSituacaoResultado");
		}
		if (sessao.getAttribute("colecaoLigacaoEsgotoSituacaoResultado") != null) {
			colecaoLigacaoEsgotoSituacao = (Collection<LigacaoEsgotoSituacao>)sessao.getAttribute("colecaoLigacaoEsgotoSituacaoResultado");
		}
		if (sessao.getAttribute("colecaoCategoriaResultado") != null) {
			colecaoCategoria = (Collection<Categoria>)sessao.getAttribute("colecaoCategoriaResultado");
		}
		if (sessao.getAttribute("colecaoEsferaPoderResultado") != null) {
			colecaoEsferaPoder = (Collection<EsferaPoder>)sessao.getAttribute("colecaoEsferaPoderResultado");
		}
		if (sessao.getAttribute("colecaoDocumentoTipoResultado") != null) {
			colecaoDocumentoTipo = (Collection)sessao.getAttribute("colecaoDocumentoTipoResultado");
		}
		if (sessao.getAttribute("filtrarDadosDiariosArrecadacaoActionForm") != null) {
			filtrarDadosDiariosArrecadacaoActionForm = (FiltrarDadosDiariosArrecadacaoActionForm)sessao.getAttribute("filtrarDadosDiariosArrecadacaoActionForm");
		}
		
		RelatorioDadosDiariosArrecadacaoParametros relatorioDadosDiariosArrecadacaoParametros = new RelatorioDadosDiariosArrecadacaoParametros(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioDadosDiariosArrecadacaoParametros.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioDadosDiariosArrecadacaoParametros.addParametro("colecaoImovelPerfil", 
				colecaoImovelPerfil);
		relatorioDadosDiariosArrecadacaoParametros.addParametro("colecaoLigacaoAguaSituacao", 
				colecaoLigacaoAguaSituacao);
		relatorioDadosDiariosArrecadacaoParametros.addParametro("colecaoLigacaoEsgotoSituacao", 
				colecaoLigacaoEsgotoSituacao);
		relatorioDadosDiariosArrecadacaoParametros.addParametro("colecaoCategoria", 
				colecaoCategoria);
		relatorioDadosDiariosArrecadacaoParametros.addParametro("colecaoEsferaPoder", 
				colecaoEsferaPoder);
		relatorioDadosDiariosArrecadacaoParametros.addParametro("colecaoDocumentoTipo", 
				colecaoDocumentoTipo);
		relatorioDadosDiariosArrecadacaoParametros.addParametro("filtrarDadosDiariosArrecadacaoActionForm", 
				filtrarDadosDiariosArrecadacaoActionForm);
		
		retorno = processarExibicaoRelatorio(
				relatorioDadosDiariosArrecadacaoParametros, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
