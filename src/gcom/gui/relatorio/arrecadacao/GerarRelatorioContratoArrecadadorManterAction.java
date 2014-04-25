package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.arrecadacao.FiltrarContratoArrecadadorActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioManterContratoArrecadador;
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

public class GerarRelatorioContratoArrecadadorManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarContratoArrecadadorActionForm filtrarContratoArrecadadorActionForm = (FiltrarContratoArrecadadorActionForm) actionForm;

		FiltroArrecadadorContrato filtroContratoArrecadador = (FiltroArrecadadorContrato) sessao.getAttribute("filtroArrecadadorContrato");

		ArrecadadorContrato contratoArrecadadorParametros = new ArrecadadorContrato();
		
		// pega os parâmetros informados na tela de consulta de débitos automáticos
		
		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getIdArrecadador())){
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.ID,Integer.parseInt(filtrarContratoArrecadadorActionForm.getIdArrecadador().trim())));

			Arrecadador arrecadador = (Arrecadador) Fachada.getInstancia().pesquisar(filtroArrecadador,Arrecadador.class.getName()).iterator().next();
			contratoArrecadadorParametros.setArrecadador(arrecadador);
		}

		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getIdCliente())){
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,Integer.parseInt(filtrarContratoArrecadadorActionForm.getIdCliente().trim())));

			Cliente cliente = (Cliente) Fachada.getInstancia().pesquisar(filtroCliente,Cliente.class.getName()).iterator().next();
			contratoArrecadadorParametros.setCliente(cliente);
		}

		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getIdContaBancariaArrecadador())){
			FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
			filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.ID,Integer.parseInt(filtrarContratoArrecadadorActionForm.getIdContaBancariaArrecadador().trim())));
			filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia");
			filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");

			ContaBancaria contaBancariaDeposito = (ContaBancaria) Fachada.getInstancia().pesquisar(filtroContaBancaria,ContaBancaria.class.getName()).iterator().next();
			contratoArrecadadorParametros.setContaBancariaDepositoArrecadacao(contaBancariaDeposito);
		}

		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getIdContaBancariaTarifa())){
			FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
			filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.ID,Integer.parseInt(filtrarContratoArrecadadorActionForm.getIdContaBancariaTarifa().trim())));
			filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia");
			filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");

			ContaBancaria contaBancariaTarifa = (ContaBancaria) Fachada.getInstancia().pesquisar(filtroContaBancaria,ContaBancaria.class.getName()).iterator().next();
			contratoArrecadadorParametros.setContaBancariaDepositoTarifa(contaBancariaTarifa);
		}
		
		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getIndicadorCobranca())){
			contratoArrecadadorParametros.setIndicadorCobrancaIss(Short.parseShort(filtrarContratoArrecadadorActionForm.getIndicadorCobranca().trim()));
		}

		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getDtInicioContrato())){
			contratoArrecadadorParametros.setDataContratoInicio(Util.converteStringParaDate(filtrarContratoArrecadadorActionForm.getDtInicioContrato()));
		}

		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getDtFimContrato())){
			contratoArrecadadorParametros.setDataContratoFim(Util.converteStringParaDate(filtrarContratoArrecadadorActionForm.getDtFimContrato()));
		}
		
		contratoArrecadadorParametros.setDescricaoEmail(filtrarContratoArrecadadorActionForm.getEmailCliente());
		contratoArrecadadorParametros.setNumeroContrato(filtrarContratoArrecadadorActionForm.getNumeroContrato());
		contratoArrecadadorParametros.setCodigoConvenio(filtrarContratoArrecadadorActionForm.getIdConvenio());

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioManterContratoArrecadador relatorioManterContratoArrecadador = new RelatorioManterContratoArrecadador((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterContratoArrecadador.addParametro("filtroArrecadadorContrato",filtroContratoArrecadador);
		relatorioManterContratoArrecadador.addParametro("contratoArrecadadorParametros",contratoArrecadadorParametros);
		relatorioManterContratoArrecadador.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(relatorioManterContratoArrecadador,
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
