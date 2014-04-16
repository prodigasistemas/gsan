package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioHistoricoMedicaoPoco;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 * @date 09/10/2009
 */
public class GerarRelatorioHistoricoMedicaoPocoAction extends
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
		
		HttpSession sessao = httpServletRequest.getSession(false);

		// cria uma instância da classe do relatório
		RelatorioHistoricoMedicaoPoco relatorioHistoricoMedicaoPoco = new RelatorioHistoricoMedicaoPoco((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
		
		String endereco = fachada.pesquisarEndereco(new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()));
			
		Cliente cliente = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao(new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()));
		 
		relatorioHistoricoMedicaoPoco.addParametro("matricula", consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo());
		relatorioHistoricoMedicaoPoco.addParametro("inscricao", consultarImovelActionForm.getMatriculaImovelAnaliseMedicaoConsumo());
		relatorioHistoricoMedicaoPoco.addParametro("sitLigacaoAgua", consultarImovelActionForm.getSituacaoAguaAnaliseMedicaoConsumo());
		relatorioHistoricoMedicaoPoco.addParametro("sitLigacaoEsgoto", consultarImovelActionForm.getSituacaoEsgotoAnaliseMedicaoConsumo());
		relatorioHistoricoMedicaoPoco.addParametro("numeroHidrometro",consultarImovelActionForm.getNumeroHidrometroPoco());
		relatorioHistoricoMedicaoPoco.addParametro("endereco", endereco);
		relatorioHistoricoMedicaoPoco.addParametro("imoveisMicromedicaoEsgoto", consultarImovelActionForm.getImoveisMicromedicaoEsgoto());
		relatorioHistoricoMedicaoPoco.addParametro("medicoesHistoricosPoco",  sessao.getAttribute("medicoesHistoricosPoco"));
		
		if (cliente != null) {
			relatorioHistoricoMedicaoPoco.addParametro("clienteUsuario", cliente.getNome());
		} else {
			relatorioHistoricoMedicaoPoco.addParametro("clienteUsuario", "");
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioHistoricoMedicaoPoco.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(relatorioHistoricoMedicaoPoco,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		
		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
