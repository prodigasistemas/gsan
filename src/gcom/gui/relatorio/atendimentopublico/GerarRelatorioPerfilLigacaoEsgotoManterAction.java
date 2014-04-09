package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.gui.atendimentopublico.FiltrarPerfilLigacaoEsgotoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioManterPerfilLigacaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

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

public class GerarRelatorioPerfilLigacaoEsgotoManterAction extends
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

		FiltrarPerfilLigacaoEsgotoActionForm filtrarPerfilLigacaoEsgotoActionForm = (FiltrarPerfilLigacaoEsgotoActionForm) actionForm;


		FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil= (FiltroLigacaoEsgotoPerfil) sessao
				.getAttribute("filtroLigacaoEsgotoPerfil");

		// Inicio da parte que vai mandar os parametros para o relatório

		LigacaoEsgotoPerfil ligacaoEsgotoPerfilParametros = new LigacaoEsgotoPerfil();

		String id = null;
			if(filtrarPerfilLigacaoEsgotoActionForm.getId()!= null && !filtrarPerfilLigacaoEsgotoActionForm.getId().equals("")){
			
			id =  filtrarPerfilLigacaoEsgotoActionForm.getId();
		}


		Short indicadordeUso = null;
		
		if(filtrarPerfilLigacaoEsgotoActionForm.getIndicadorUso()!= null && !filtrarPerfilLigacaoEsgotoActionForm.getIndicadorUso().equals("")){
			
			indicadordeUso = new Short ("" + filtrarPerfilLigacaoEsgotoActionForm.getIndicadorUso());
		}
		
		if(filtrarPerfilLigacaoEsgotoActionForm.getPercentualEsgotoConsumidaColetada() != null 
				&& !filtrarPerfilLigacaoEsgotoActionForm.getPercentualEsgotoConsumidaColetada().equals("")){
			ligacaoEsgotoPerfilParametros.setPercentualEsgotoConsumidaColetada(Util.formatarMoedaRealparaBigDecimal(filtrarPerfilLigacaoEsgotoActionForm.getPercentualEsgotoConsumidaColetada()));
		} 
		
		// seta os parametros que serão mostrados no relatório

		ligacaoEsgotoPerfilParametros.setId(id == null ? null : new Integer(
				id));
		ligacaoEsgotoPerfilParametros.setDescricao(""
				+ filtrarPerfilLigacaoEsgotoActionForm.getDescricao());
		
		
		
		ligacaoEsgotoPerfilParametros.setIndicadorUso(indicadordeUso);
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterPerfilLigacaoEsgoto relatorioManterPerfilLigacaoEsgoto = new RelatorioManterPerfilLigacaoEsgoto(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterPerfilLigacaoEsgoto.addParametro("filtroLigacaoEsgotoPerfil",
				filtroLigacaoEsgotoPerfil);
		relatorioManterPerfilLigacaoEsgoto.addParametro("ligacaoEsgotoPerfilParametros",
				ligacaoEsgotoPerfilParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterPerfilLigacaoEsgoto.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterPerfilLigacaoEsgoto,
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
