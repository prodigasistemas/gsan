package gcom.gui.relatorio.arrecadacao;

import java.util.Collection;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.arrecadacao.banco.FiltrarAgenciaBancariaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioManterAgenciaBancaria;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Fernando Fontelles
 * @version 1.0
 */

public class GerarRelatorioManterAgenciaBancariaAction extends
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

		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarAgenciaBancariaActionForm filtrarAgenciaBancariaActionForm = (FiltrarAgenciaBancariaActionForm) actionForm;

		FiltroAgencia filtroAgenciaBancaria = (FiltroAgencia) sessao
				.getAttribute("filtroAgencia");

		// Inicio da parte que vai mandar os parametros para o relatório

		Agencia agenciaBancariaParametros = new Agencia();		
		
		String id = null;		
		
		// seta os parametros que serão mostrados no relatório

		agenciaBancariaParametros.setId(id == null ? null : new Integer(
				id));
		
		//Banco
		if(filtrarAgenciaBancariaActionForm.getBancoID() != null && !
				filtrarAgenciaBancariaActionForm.getBancoID().equals("")){
			
			FiltroBanco filtroBanco = new FiltroBanco();
			filtroBanco.adicionarParametro( new ParametroSimples( FiltroBanco.ID,
					filtrarAgenciaBancariaActionForm.getBancoID() ) );
			
			Collection colecaoBanco = fachada.pesquisar(filtroBanco, Banco.class.getName());
			
			if( colecaoBanco != null && !colecaoBanco.equals("")){
				
				Banco banco =  (Banco) Util.retonarObjetoDeColecao(colecaoBanco);
				agenciaBancariaParametros.setBanco(banco);
				
			}
		
		}
		
		//Codigo Agencia
		if(filtrarAgenciaBancariaActionForm.getCodigo() != null 
				&& !filtrarAgenciaBancariaActionForm.getCodigo().equals("")){
			
			agenciaBancariaParametros.setCodigoAgencia(filtrarAgenciaBancariaActionForm.getCodigo());
			
		}
		
		//Nome Agencia
		if(filtrarAgenciaBancariaActionForm.getNome() != null
				&& !filtrarAgenciaBancariaActionForm.getNome().equals("")){
			
			agenciaBancariaParametros.setNomeAgencia(filtrarAgenciaBancariaActionForm.getNome());
			
		}
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterAgenciaBancaria relatorioManterAgenciaBancaria = new RelatorioManterAgenciaBancaria(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterAgenciaBancaria.addParametro("filtroAgenciaBancaria",
				filtroAgenciaBancaria	);
		relatorioManterAgenciaBancaria.addParametro("agenciaBancariaParametros",
				agenciaBancariaParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterAgenciaBancaria.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterAgenciaBancaria,
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
