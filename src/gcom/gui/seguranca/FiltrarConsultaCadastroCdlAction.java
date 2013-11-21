 package gcom.gui.seguranca;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.ConsultaCdl;
import gcom.seguranca.FiltrarConsultaCadastroCdlActionForm;
import gcom.seguranca.FiltroConsultaCadastroCdl;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * FILTRAR 
 * 
 * @author Rodrigo Cabral
 * @date 22/10/2010
 */

public class FiltrarConsultaCadastroCdlAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("resultadoPesquisaConsultaCadastroCdl");

		FiltrarConsultaCadastroCdlActionForm form = (FiltrarConsultaCadastroCdlActionForm) actionForm;
		
		FiltroConsultaCadastroCdl filtroConsultaCadastroCdl = new FiltroConsultaCadastroCdl();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		boolean peloMenosUmParametroInformado = false;
		
		// Verifica se o campo CPF Cliente foi informado

		if (form.getCpfCliente() != null && !form.getCpfCliente().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(
					FiltroConsultaCadastroCdl.CPF_CLIENTE, form.getCpfCliente()));
			
		}
		
		// Verifica se o campo Cod. Cliente Gsan foi informado

		if (form.getCodigoCliente() != null && !form.getCodigoCliente().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(
					FiltroConsultaCadastroCdl.COD_CLIENTE_GSAN, form.getCodigoCliente()));
			
		}
		
		// Verifica se o campo CNPJ Cliente foi informado

		if (form.getCnpjCliente() != null && !form.getCnpjCliente().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(
					FiltroConsultaCadastroCdl.CNPJ_CLIENTE, form.getCnpjCliente()));
			
		}
		
		// Verifica se o campo CNPJ Cliente foi informado

		if (form.getNomeCliente() != null && !form.getNomeCliente().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(
					FiltroConsultaCadastroCdl.NOME_CLIENTE, form.getNomeCliente()));
			
		}
		
		// Verifica se o campo Matrícula do Funcionário foi informado

		if (form.getIdMatriculaFuncionario() != null && !form.getIdMatriculaFuncionario().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(
					FiltroConsultaCadastroCdl.ID_FUNCIONARIO, form.getIdMatriculaFuncionario()));
			
		}
		
		// Verifica se o campo Nome do Usuário foi informado

		if (form.getNomeUsuario() != null && !form.getNomeUsuario().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(
					FiltroConsultaCadastroCdl.NOME_USUARIO, form.getNomeUsuario()));
			
		}
		
		// Verifica se o campo Número do CPF Usuário foi informado
		
		if (form.getCpfUsuario() != null && !form.getCpfUsuario().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(
					FiltroConsultaCadastroCdl.CPF_USUARIO, form.getCpfUsuario()));
			
		}
		
		// Verifica se o campo Login Usuário foi informado
		
		if (form.getLoginUsuario() != null && !form.getLoginUsuario().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(
					FiltroConsultaCadastroCdl.LOGIN_USUARIO, form.getLoginUsuario()));
			
		}
		
		// Verifica se o campo Período Acesso Inicial e Final foi informado
		
		if (form.getPeriodoAcessoFinal() != null && !form.getPeriodoAcessoFinal().trim().equalsIgnoreCase("") &&
				form.getPeriodoAcessoInicial() != null && !form.getPeriodoAcessoInicial().trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			Date dataInicioFormatada = Util.converteStringParaDate(form.getPeriodoAcessoInicial());
			Date dataFinalFormatada = Util.converteStringParaDate(form.getPeriodoAcessoFinal());
			
			filtroConsultaCadastroCdl.adicionarParametro(new Intervalo(
					FiltroConsultaCadastroCdl.DATA_ULTIMA_ALTERACAO, Util.formatarDataInicial(dataInicioFormatada),
					Util.formatarDataFinal(dataFinalFormatada)));
		}
		
		// Verifica se o campo Ação do Operador foi informado
		
		if (form.getAcaoOperador() != null && 
				!form.getAcaoOperador().equals("-1") && 
					!form.getAcaoOperador().equals("4")) {

			peloMenosUmParametroInformado = true;

			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(
					FiltroConsultaCadastroCdl.ACAO_OPERADOR, form.getAcaoOperador()));
			
		}
		
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		Collection<ConsultaCdl> colecaoConsultaCdl = null;
		
		Map resultado = 
			controlarPaginacao( httpServletRequest, 
				retorno,	
				filtroConsultaCadastroCdl, 
				ConsultaCdl.class.getName());
		
		colecaoConsultaCdl = (Collection<ConsultaCdl>) resultado.get("colecaoRetorno");
		
		retorno = (ActionForward) resultado.get("destinoActionForward");

		//Nenhum registro encontrado				
		if (colecaoConsultaCdl == null || colecaoConsultaCdl.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado");
		}
		
		
		httpServletRequest.setAttribute("colecaoConsultaCdl",colecaoConsultaCdl);
		httpServletRequest.setAttribute("periodoAcessoInicial",form.getPeriodoAcessoInicial());
		httpServletRequest.setAttribute("periodoAcessoFinal",form.getPeriodoAcessoFinal());
		httpServletRequest.setAttribute("filtroConsultaCadastroCdl", filtroConsultaCadastroCdl);
		
		sessao.setAttribute("filtroConsultaCadastroCdl", filtroConsultaCadastroCdl);
		

		return retorno;
	
	}	
		
}