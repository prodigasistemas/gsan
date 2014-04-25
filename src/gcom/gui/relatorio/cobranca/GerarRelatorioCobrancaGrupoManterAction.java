package gcom.gui.relatorio.cobranca;

import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.gui.cobranca.FiltrarCobrancaGrupoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioManterCobrancaGrupo;
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
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioCobrancaGrupoManterAction extends
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

		FiltrarCobrancaGrupoActionForm form = (FiltrarCobrancaGrupoActionForm) actionForm;

		FiltroCobrancaGrupo filtroCobrancaGrupo = (FiltroCobrancaGrupo) sessao
				.getAttribute("filtroCobrancaGrupo");

		// Inicio da parte que vai mandar os parametros para o relatório
		CobrancaGrupo cobrancaGrupoParametros = new CobrancaGrupo();

		//Codigo
		Integer id = null;
		if (form.getId() != null && !form.getId().equals("")) {
			id = new Integer( form.getId() ) ;
			cobrancaGrupoParametros.setId( id );
		}

		//Descrição
		String descricao = "";
		if ( form.getDescricao() != null && !form.getDescricao().equals("")){
			descricao = form.getDescricao();
			cobrancaGrupoParametros.setDescricao(descricao);
		}
		
		//Descrição Abreviada
		String descricaoAbreviada = "";
		if ( form.getDescricaoAbreviada() != null && !form.getDescricaoAbreviada().equals("")){
			descricaoAbreviada = form.getDescricaoAbreviada();
			cobrancaGrupoParametros.setDescricaoAbreviada(descricaoAbreviada);
		}
		
		//Ano Mes Referencia
		String anoMesReferencia = form.getAnoMesReferencia();
		if(anoMesReferencia != null && !anoMesReferencia.trim().equals("")){
        	String mes = anoMesReferencia.substring(0, 2);
        	String ano = anoMesReferencia.substring(3, 7);
        	String anoMes = ano+mes;
        	anoMesReferencia = anoMes;
        	cobrancaGrupoParametros.setAnoMesReferencia(new Integer(anoMesReferencia));
		}
		
		//Indicador de Uso
		Short indicadorUso = null;
		if(form.getIndicadorUso() != null && !form.getIndicadorUso().equals("")){
			indicadorUso = new Short("" + form.getIndicadorUso());
			cobrancaGrupoParametros.setIndicadorUso(indicadorUso);	
		}


		// cria uma instância da classe do relatório
		RelatorioManterCobrancaGrupo relatorioManterCobrancaGrupo = new RelatorioManterCobrancaGrupo(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterCobrancaGrupo.addParametro("filtroCobrancaGrupo",
				filtroCobrancaGrupo);
		relatorioManterCobrancaGrupo.addParametro("cobrancaGrupoParametros",
				cobrancaGrupoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterCobrancaGrupo.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterCobrancaGrupo,
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
