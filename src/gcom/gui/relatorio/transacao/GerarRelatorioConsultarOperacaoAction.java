package gcom.gui.relatorio.transacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.transacao.RelatorioConsultarOperacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAcao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de operação consultar
 * 
 * @author Rafael Corrêa
 * @created 06/04/2006
 */
public class GerarRelatorioConsultarOperacaoAction extends
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
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		//ConsultarOperacaoEfetuadaActionForm form = (ConsultarOperacaoEfetuadaActionForm) actionForm; 

//		Collection colecaoOperacoesEfetuadas = (Collection) sessao
//				.getAttribute("operacaoEfetuada");

		// Inicio da parte que vai mandar os parametros para o relatório
 		String[] idOperacoes          = null;
		String unidadeNegocio         = "";
		String idUsuario              = "";
		Usuario usuario               = null;
		String idUsuarioAcao          = "";
		UsuarioAcao usuarioAcao       = null;
		Date periodoRealizacaoInicial = null;
		Date periodoRealizacaoFinal   = null;
		Date horarioRealizacaoInicial = null;
		Date horarioRealizacaoFinal   = null;
		Integer idTabela              = null;
		Integer[] idTabelaColuna      = null;
		Integer id1                   = null;
		Integer numeroPaginasPesquisa = null;
		Integer idFuncionalidade      = null;
		Hashtable<String,String> argumentos = new Hashtable<String, String>();
		

		if (sessao.getAttribute("idOperacoes") != null) {
			idOperacoes = (String[]) sessao.getAttribute("idOperacoes");
		}

//		Operacao operacao = null;
//
//		if (idOperacao != null && !idOperacao.equals("")) {
//			FiltroOperacao filtroOperacao = new FiltroOperacao();
//
//			filtroOperacao.adicionarParametro(new ParametroSimples(
//					FiltroOperacao.ID, idOperacao));
//
//			Collection colecaoOperacoes = fachada.pesquisar(filtroOperacao,
//					Operacao.class.getName());
//
//			if (colecaoOperacoes != null && !colecaoOperacoes.isEmpty()) {
//				// A operação foi encontrada
//
//				operacao = (Operacao) colecaoOperacoes.iterator().next();
//
//			} else {
//				throw new ActionServletException(
//						"atencao.pesquisa_inexistente", null, "Operação");
//			}
//
//		} else {
//			operacao = new Operacao();
//		}

		

		if (sessao.getAttribute("idUsuario") != null) {
			idUsuario = (String) sessao.getAttribute("idUsuario");
		}


		if (idUsuario != null && !idUsuario.equals("")) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();

  filtroUsuario.adicionarParametro( new ParametroSimples( FiltroUsuario.LOGIN, idUsuario ) );

  Collection colecaoUsuarios = fachada.pesquisar( filtroUsuario,Usuario.class.getName() );

			if (colecaoUsuarios != null && !colecaoUsuarios.isEmpty()) {
				// O usuário foi encontrado
				usuario = (Usuario) colecaoUsuarios.iterator().next();
			} 
			else {
	throw new ActionServletException("atencao.pesquisa_inexistente", null, "Usuário");
			}

		}
		else {
			usuario = new Usuario();
		}


		if (sessao.getAttribute("idUsuarioAcao") != null) {
	idUsuarioAcao = ((Integer) sessao.getAttribute("idUsuarioAcao")).toString();
		}
		if( sessao.getAttribute("numeroPaginasPesquisa") != null ){
	numeroPaginasPesquisa = (Integer) sessao.getAttribute("numeroPaginasPesquisa");
		}

		

		if ( idUsuarioAcao != null && !idUsuarioAcao.equals("") ) {
			
			FiltroUsuarioAcao filtroUsuarioAcao = new FiltroUsuarioAcao();

	filtroUsuarioAcao.adicionarParametro( new ParametroSimples( FiltroUsuarioAcao.ID, idUsuario) );

	Collection colecaoUsuariosAcoes = fachada.pesquisar( filtroUsuarioAcao, UsuarioAcao.class.getName() );

			if (colecaoUsuariosAcoes != null && !colecaoUsuariosAcoes.isEmpty()) {
				// A ação do usuário foi encontrado
	usuarioAcao = (UsuarioAcao) colecaoUsuariosAcoes.iterator().next();
			}
			else {
	throw new ActionServletException( "atencao.pesquisa_inexistente", null, "Ação do Usuário" );
			}

		}
		else {
			usuarioAcao = new UsuarioAcao();
		}



		if (sessao.getAttribute("dataInicial") != null) {
	periodoRealizacaoInicial =(Date)  sessao.getAttribute("dataInicial");
		}
		if( sessao.getAttribute("unidadeNegocio") != null ){
			unidadeNegocio = (String) sessao.getAttribute("unidadeNegocio");
		}

		if (sessao.getAttribute("dataFinal") != null) {
	periodoRealizacaoFinal = (Date) sessao.getAttribute("dataFinal");
		}



		if (sessao.getAttribute("horaInicial") != null) {
	horarioRealizacaoInicial = (Date) sessao.getAttribute("horaInicial");
		}

		if (sessao.getAttribute("horaFinal") != null) {
	horarioRealizacaoFinal =(Date) sessao.getAttribute("horaFinal");
		}
		
		
		
		if(sessao.getAttribute("idTabela") != null){
	idTabela = (Integer) sessao.getAttribute("idTabela");
		}
		
		
		
		if(sessao.getAttribute("idTabelaColuna") != null){
			idTabelaColuna = (Integer[]) sessao.getAttribute("idTabelaColuna");
		}
		
		if(sessao.getAttribute("id1") != null){
			id1 = (Integer) sessao.getAttribute("id1");
		}
		if(sessao.getAttribute("idFuncionalidade") != null){
			idFuncionalidade = (Integer) sessao.getAttribute("idFuncionalidade");
		}
		
		if (sessao.getAttribute("argumentos") != null) {
			argumentos = (Hashtable<String, String>) sessao.getAttribute("argumentos");
		}

		// cria uma instância da classe do relatório
		RelatorioConsultarOperacao relatorioConsultarOperacao = new RelatorioConsultarOperacao(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

//		relatorioConsultarOperacao.addParametro("colecaoOperacoesEfetuadas",
//				colecaoOperacoesEfetuadas);
relatorioConsultarOperacao.addParametro("idOperacoes"              , idOperacoes              );
relatorioConsultarOperacao.addParametro("usuario"                  , usuario                  );
relatorioConsultarOperacao.addParametro("usuarioAcao"              , usuarioAcao              );
relatorioConsultarOperacao.addParametro("periodoRealizacaoInicial" , periodoRealizacaoInicial );
relatorioConsultarOperacao.addParametro("periodoRealizacaoFinal"   , periodoRealizacaoFinal   );
relatorioConsultarOperacao.addParametro("horarioRealizacaoInicial" , horarioRealizacaoInicial );
relatorioConsultarOperacao.addParametro("horarioRealizacaoFinal"   , horarioRealizacaoFinal   );
relatorioConsultarOperacao.addParametro("idTabela"                 , idTabela                 );
relatorioConsultarOperacao.addParametro("idTabelaColuna"           , idTabelaColuna           );
relatorioConsultarOperacao.addParametro("id1"                      , id1                      );
relatorioConsultarOperacao.addParametro("argumentos"               , argumentos               );
relatorioConsultarOperacao.addParametro("unidadeNegocio"           , unidadeNegocio           );
relatorioConsultarOperacao.addParametro("numeroPaginasPesquisa"    , numeroPaginasPesquisa    );
relatorioConsultarOperacao.addParametro("idFuncionalidade"         , idFuncionalidade         );
		
		
		
		String tipo = httpServletRequest.getParameter("tipo");
		
		relatorioConsultarOperacao.addParametro("tipoRelatorio", tipo);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioConsultarOperacao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioConsultarOperacao,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}

}
