package gcom.gui.relatorio.faturamento;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioAnaliseFaturamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

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
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Fernanda Paiva
 * @version 1.0
 */

public class GerarRelatorioAnaliseFaturamentoAction extends
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

		Collection colecaoAnaliseFaturamento = (Collection) sessao
				.getAttribute("colecaoAnaliseFaturamento");

		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = (InformarDadosGeracaoRelatorioConsultaHelper) sessao
				.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");

		// Inicio da parte que vai mandar os parametros para o relatório

		String mesAnoFaturamento = (String) informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia().toString();
		String descricao = (String) informarDadosGeracaoRelatorioConsultaHelper	.getDescricaoOpcaoTotalizacao();
		Integer tipoAnalise = (Integer) informarDadosGeracaoRelatorioConsultaHelper.getTipoAnaliseFaturamento();
		

		// cria uma instância da classe do relatório
		RelatorioAnaliseFaturamento relatorioAnaliseFaturamento = new RelatorioAnaliseFaturamento(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		relatorioAnaliseFaturamento.addParametro("mesAnoFaturamento", Util.formatarAnoMesParaMesAno(mesAnoFaturamento));
		relatorioAnaliseFaturamento.addParametro("descricao", descricao);		
		relatorioAnaliseFaturamento.addParametro("tipoAnalise", tipoAnalise);		
		relatorioAnaliseFaturamento.addParametro("tipoQuebra", descricao);
		relatorioAnaliseFaturamento.addParametro("colecaoAnaliseFaturamento",colecaoAnaliseFaturamento);
		relatorioAnaliseFaturamento.addParametro("informarDadosGeracaoRelatorioConsultaHelper",informarDadosGeracaoRelatorioConsultaHelper);
		
		
		
		//----------------------------------------------------------------------------------------------------------------
        // Alterado por : Yara T. Souza.
		// Data : 26/08/2008
		//----------------------------------------------------------------------------------------------------------------
	     
		
		String localidade = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getLocalidade()!= null){
			 localidade =  (Integer) informarDadosGeracaoRelatorioConsultaHelper.getLocalidade().getId() + " - " + (String) informarDadosGeracaoRelatorioConsultaHelper.getLocalidade().getDescricao();
		}
			
		String gerenciaRegional = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getGerenciaRegional()!= null){
			gerenciaRegional = (String) informarDadosGeracaoRelatorioConsultaHelper.getGerenciaRegional().getNome();
		}
		String unidadeNegocio = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getUnidadeNegocio() != null){
			unidadeNegocio = (String) informarDadosGeracaoRelatorioConsultaHelper.getUnidadeNegocio().getNome();
		}
		String setorComercial = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial()!= null){
			setorComercial = informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial().getDescricao();
		}
		
		String quadra = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getQuadra()!= null){
			quadra = informarDadosGeracaoRelatorioConsultaHelper.getQuadra().getDescricao();
		}
		
		String grupoFaturamento = "";
		if ( informarDadosGeracaoRelatorioConsultaHelper.getFaturamentoGrupo() != null ) {
			grupoFaturamento = informarDadosGeracaoRelatorioConsultaHelper.getFaturamentoGrupo().getDescricao();
		}
		
		String rota = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getRota()!= null){
			rota = informarDadosGeracaoRelatorioConsultaHelper.getRota().getCodigo().intValue()+"";
		}
		
		Collection colecaoPerfilImovel = (Collection) informarDadosGeracaoRelatorioConsultaHelper.getColecaoImovelPerfil();
		Collection colecaoLigacaoAgua = (Collection) informarDadosGeracaoRelatorioConsultaHelper.getColecaoLigacaoAguaSituacao();
		Collection colecaoLigacaoEsgoto = (Collection) informarDadosGeracaoRelatorioConsultaHelper.getColecaoLigacaoEsgotoSituacao();
		Collection colecaoCategoria = (Collection) informarDadosGeracaoRelatorioConsultaHelper.getColecaoCategoria();
		Collection colecaoEsferaPoder = (Collection) informarDadosGeracaoRelatorioConsultaHelper.getColecaoEsferaPoder();
    			
		relatorioAnaliseFaturamento.addParametro("localidade", localidade);
		relatorioAnaliseFaturamento.addParametro("gerenciaRegional", gerenciaRegional);
		relatorioAnaliseFaturamento.addParametro("unidadeNegocio", unidadeNegocio);
		relatorioAnaliseFaturamento.addParametro("setorComercial", setorComercial);
		relatorioAnaliseFaturamento.addParametro("quadra", quadra);
		relatorioAnaliseFaturamento.addParametro("grupoFaturamento", grupoFaturamento);
		relatorioAnaliseFaturamento.addParametro("rota", rota);

		relatorioAnaliseFaturamento.addParametro("colecaoPerfilImovel", colecaoPerfilImovel);
		relatorioAnaliseFaturamento.addParametro("colecaoLigacaoAgua", colecaoLigacaoAgua);
		relatorioAnaliseFaturamento.addParametro("colecaoLigacaoEsgoto", colecaoLigacaoEsgoto);
		relatorioAnaliseFaturamento.addParametro("colecaoCategoria",colecaoCategoria);
		relatorioAnaliseFaturamento.addParametro("colecaoEsferaPoder",colecaoEsferaPoder);
		
		//----------------------------------------------------------------------------------------------------------------
		

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioAnaliseFaturamento.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioAnaliseFaturamento,
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
