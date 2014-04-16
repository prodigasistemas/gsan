package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomatico;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.arrecadacao.FiltrarDebitoAutomaticoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioManterDebitoAutomatico;
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
 * @author Rafael Corrêa
 * @version 1.0
 */

public class GerarRelatorioDebitoAutomaticoManterAction extends
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

		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarDebitoAutomaticoActionForm filtrarDebitoAutomaticoActionForm = (FiltrarDebitoAutomaticoActionForm) actionForm;

		FiltroDebitoAutomatico filtroDebitoAutomatico = (FiltroDebitoAutomatico) sessao.getAttribute("filtroDebitoAutomatico");

		DebitoAutomatico debitoAutomaticoParametros = new DebitoAutomatico();
		
		// pega os parâmetros informados na tela de consulta de débitos automáticos
		
		if(Util.verificarNaoVazio(filtrarDebitoAutomaticoActionForm.getMatricula())){
			debitoAutomaticoParametros.setImovel(new Imovel(Integer.parseInt(filtrarDebitoAutomaticoActionForm.getMatricula().trim())));
		}

		if(Util.verificarNaoVazio(filtrarDebitoAutomaticoActionForm.getAgenciaCodigo())){

			FiltroAgencia filtroAgencia = new FiltroAgencia();
			filtroAgencia.adicionarParametro(new ParametroSimples(FiltroAgencia.CODIGO_AGENCIA,filtrarDebitoAutomaticoActionForm.getAgenciaCodigo().trim()));
			filtroAgencia.adicionarParametro(new ParametroSimples(FiltroAgencia.BANCO_ID,Integer.parseInt(filtrarDebitoAutomaticoActionForm.getBancoID().trim())));

			Agencia agencia = (Agencia) Fachada.getInstancia().pesquisar(filtroAgencia,Agencia.class.getName()).iterator().next();
			debitoAutomaticoParametros.setAgencia(agencia);
		}

		if(Util.verificarNaoVazio(filtrarDebitoAutomaticoActionForm.getBancoID())){

			if(debitoAutomaticoParametros.getAgencia() == null){
				debitoAutomaticoParametros.setAgencia(new Agencia());				
			}

			FiltroBanco filtroBanco = new FiltroBanco();
			filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID,Integer.parseInt(filtrarDebitoAutomaticoActionForm.getBancoID().trim())));

			Banco banco = (Banco) Fachada.getInstancia().pesquisar(filtroBanco,Banco.class.getName()).iterator().next();
			debitoAutomaticoParametros.getAgencia().setBanco(banco);
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioManterDebitoAutomatico relatorioManterDebitoAutomatico = new RelatorioManterDebitoAutomatico((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterDebitoAutomatico.addParametro("filtroDebitoAutomatico",filtroDebitoAutomatico);
		relatorioManterDebitoAutomatico.addParametro("debitoAutomaticoParametros",debitoAutomaticoParametros);
		relatorioManterDebitoAutomatico.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(relatorioManterDebitoAutomatico,
					tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

}
