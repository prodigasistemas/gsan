package gcom.gui.relatorio.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.localidade.FiltrarUnidadeNegocioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.localidade.RelatorioManterUnidadeNegocio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * @author Arthur Carvalho
 * @date 01/07/09
 * 
 */

public class GerarRelatorioUnidadeNegocioManterAction extends
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

		FiltrarUnidadeNegocioActionForm filtrarUnidadeNegocioActionForm = (FiltrarUnidadeNegocioActionForm) actionForm;

		FiltroUnidadeNegocio filtroUnidadeNegocio = (FiltroUnidadeNegocio) sessao
				.getAttribute("filtroUnidadeNegocio");

		// Inicio da parte que vai mandar os parametros para o relatório
		UnidadeNegocio unidadeNegocioParametros = new UnidadeNegocio();

		Fachada fachada = Fachada.getInstancia();
		
		//Codigo
		Integer id = null;
		if (filtrarUnidadeNegocioActionForm.getId() != null
				&& !filtrarUnidadeNegocioActionForm.getId().equals("")) {

			id = new Integer (filtrarUnidadeNegocioActionForm.getId() ) ;
		}
		
		//Descricao
		String nome = "";
		if(filtrarUnidadeNegocioActionForm.getNome() != null &&
				!filtrarUnidadeNegocioActionForm.getNome().equals("") ) {
					
			nome = filtrarUnidadeNegocioActionForm.getNome();
		}
		
		//Descricao Abreviada
		String nomeAbreviado = "";
		if(filtrarUnidadeNegocioActionForm.getNomeAbreviado() != null &&
				!filtrarUnidadeNegocioActionForm.getNomeAbreviado().equals("") ) {
					
			nomeAbreviado = filtrarUnidadeNegocioActionForm.getNomeAbreviado();
		}
		
		//CNPJ
		String cnpj = "";
		if( filtrarUnidadeNegocioActionForm.getNumeroCnpj() != null && 
				!filtrarUnidadeNegocioActionForm.getNumeroCnpj().equals("") ) {
			
			cnpj = filtrarUnidadeNegocioActionForm.getNumeroCnpj();
		}
		
		//Gerente da Unidade de Negocio
		Cliente cliente = new Cliente();
		if ( filtrarUnidadeNegocioActionForm.getIdCliente() != null &&
				!filtrarUnidadeNegocioActionForm.getIdCliente().equals("") ) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro( new ParametroSimples (FiltroCliente.ID, 
					filtrarUnidadeNegocioActionForm.getIdCliente() ) );
			
			Collection colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			
			if( colecaoClientes != null && !colecaoClientes.isEmpty() ) {
				cliente = (Cliente) colecaoClientes.iterator().next();
			}
			
		}
	
		//Gerencia Regional 
		GerenciaRegional gerenciaRegional = new GerenciaRegional();
		if ( filtrarUnidadeNegocioActionForm.getIdGerenciaRegional() != null && 
				!filtrarUnidadeNegocioActionForm.getIdGerenciaRegional().equals("") ) {
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, 
					filtrarUnidadeNegocioActionForm.getIdGerenciaRegional() ) ) ;
			
			Collection colecaoGerenciaRegionais = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName() );
			
			if ( colecaoGerenciaRegionais != null && !colecaoGerenciaRegionais.isEmpty() ) {
				gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegionais.iterator().next();
			}
		}

		//Indicador de Uso
		Short indicadorDeUso = null;
		if(filtrarUnidadeNegocioActionForm.getIndicadorUso()!= null && 
				!filtrarUnidadeNegocioActionForm.getIndicadorUso().equals("")){
			
			indicadorDeUso = new Short (filtrarUnidadeNegocioActionForm.getIndicadorUso() ) ;
		}
		
		
		
		// seta os parametros que serão mostrados no relatório
		unidadeNegocioParametros.setId( id ) ;
		unidadeNegocioParametros.setNome( nome );
		unidadeNegocioParametros.setNomeAbreviado( nomeAbreviado );
		unidadeNegocioParametros.setCnpj( cnpj );
		unidadeNegocioParametros.setCliente( cliente );
		unidadeNegocioParametros.setGerenciaRegional( gerenciaRegional );
		unidadeNegocioParametros.setIndicadorUso( indicadorDeUso );
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterUnidadeNegocio relatorioManterUnidadeNegocio = new RelatorioManterUnidadeNegocio (
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterUnidadeNegocio.addParametro("filtroUnidadeNegocio",
				filtroUnidadeNegocio);
		relatorioManterUnidadeNegocio.addParametro("unidadeNegocioParametros",
				unidadeNegocioParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterUnidadeNegocio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterUnidadeNegocio,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
