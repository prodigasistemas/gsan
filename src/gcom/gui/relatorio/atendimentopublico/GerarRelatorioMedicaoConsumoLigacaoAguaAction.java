package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioMedicaoConsumoLigacaoAgua;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

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
 * action responsável pela exibição do relatório guia de devolução
 * 
 * @author Ana Maria
 * @date 13/02/2007
 */
public class GerarRelatorioMedicaoConsumoLigacaoAguaAction extends
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

		// cria uma instância da classe do relatório
		RelatorioMedicaoConsumoLigacaoAgua relatorioMedicaoConsumoLigacaoAgua = new RelatorioMedicaoConsumoLigacaoAgua((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
		
		// Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoMedicaoHistorico = new ArrayList();
		Collection colecaoimoveisMicromedicao = new ArrayList();
		Collection imovelClientes = new ArrayList();
		 
		 if (sessao.getAttribute("medicoesHistoricos") != null || sessao.getAttribute("imoveisMicromedicao") != null) {
			colecaoMedicaoHistorico = (Collection)sessao.getAttribute("medicoesHistoricos");
			colecaoimoveisMicromedicao = (Collection)sessao.getAttribute("imoveisMicromedicao");
		 }
		
		 String clienteUsuario = "";
		 if(sessao.getAttribute("imovelClientes") != null && !sessao.getAttribute("imovelClientes").equals("")){
			 imovelClientes = (Collection)sessao.getAttribute("imovelClientes"); 
			 Iterator iteratorImovelCliete = imovelClientes.iterator();
			 while (iteratorImovelCliete.hasNext()) {
				ClienteImovel imovelCliente = (ClienteImovel) iteratorImovelCliete.next();
				if(imovelCliente.getClienteRelacaoTipo().getId().equals(new Integer(ClienteRelacaoTipo.USUARIO))){
					clienteUsuario = imovelCliente.getCliente().getNome();
				}				
			}
		 }
		 relatorioMedicaoConsumoLigacaoAgua.addParametro("colecaoMedicaoHistorico", colecaoMedicaoHistorico);
		 relatorioMedicaoConsumoLigacaoAgua.addParametro("colecaoimoveisMicromedicao", colecaoimoveisMicromedicao);
		 relatorioMedicaoConsumoLigacaoAgua.addParametro("matricula", consultarImovelActionForm.getMatriculaImovelAnaliseMedicaoConsumo());
		 relatorioMedicaoConsumoLigacaoAgua.addParametro("sitLigacaoAgua", consultarImovelActionForm.getSituacaoAguaAnaliseMedicaoConsumo());
		 relatorioMedicaoConsumoLigacaoAgua.addParametro("sitLigacaoEsgoto", consultarImovelActionForm.getSituacaoEsgotoAnaliseMedicaoConsumo());
		 relatorioMedicaoConsumoLigacaoAgua.addParametro("endereco", consultarImovelActionForm.getEnderecoImovelDadosCadastrais());
		 relatorioMedicaoConsumoLigacaoAgua.addParametro("clienteUsuario", clienteUsuario);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioMedicaoConsumoLigacaoAgua.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioMedicaoConsumoLigacaoAgua,
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
