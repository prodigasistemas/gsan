package gcom.gui.relatorio.cadastro;

import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.gui.cadastro.FiltrarFonteAbastecimentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioManterFonteAbastecimento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
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
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioFonteAbastecimentoManterAction extends
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

		FiltrarFonteAbastecimentoActionForm filtrarFonteAbastecimentoActionForm = (FiltrarFonteAbastecimentoActionForm) actionForm;

		FiltroFonteAbastecimento filtroFonteAbastecimento= (FiltroFonteAbastecimento) sessao
				.getAttribute("filtroFonteAbastecimento");

		// Inicio da parte que vai mandar os parametros para o relatório

		FonteAbastecimento fonteAbastecimentoParametros = new FonteAbastecimento();

		String id = null;

		String idFonteAbastecimentoPesquisar = (String) filtrarFonteAbastecimentoActionForm.getId();

		if (idFonteAbastecimentoPesquisar != null && !idFonteAbastecimentoPesquisar.equals("")) {
			id = idFonteAbastecimentoPesquisar;
		}
		
		Short indicadorUso = null;
		
		if(filtrarFonteAbastecimentoActionForm.getIndicadorUso()!= null && !filtrarFonteAbastecimentoActionForm.getIndicadorUso().equals("")){
			
			indicadorUso = new Short ("" + filtrarFonteAbastecimentoActionForm.getIndicadorUso());
		}
		
		Short indicadorCalcularVolumeFixo = null;
		
		if(filtrarFonteAbastecimentoActionForm.getIndicadorCalcularVolumeFixo() != null && !filtrarFonteAbastecimentoActionForm.getIndicadorCalcularVolumeFixo().equals("")){
			
			indicadorCalcularVolumeFixo = new Short ("" + filtrarFonteAbastecimentoActionForm.getIndicadorCalcularVolumeFixo());
		}
		
		// seta os parametros que serão mostrados no relatório

		fonteAbastecimentoParametros.setId(id == null ? null : new Integer(
				id));
		fonteAbastecimentoParametros.setDescricao(filtrarFonteAbastecimentoActionForm.getDescricao());
		fonteAbastecimentoParametros.setDescricaoAbreviada(filtrarFonteAbastecimentoActionForm.getDescricaoAbreviada());
		fonteAbastecimentoParametros.setIndicadorUso(indicadorUso);
		fonteAbastecimentoParametros.setIndicadorCalcularVolumeFixo(indicadorCalcularVolumeFixo);
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
	
		RelatorioManterFonteAbastecimento relatorioManterFonteAbastecimento = new RelatorioManterFonteAbastecimento(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterFonteAbastecimento.addParametro("filtroFonteAbastecimento",
				filtroFonteAbastecimento);
		relatorioManterFonteAbastecimento.addParametro("fonteAbastecimentoParametros",
				fonteAbastecimentoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterFonteAbastecimento.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterFonteAbastecimento,
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
