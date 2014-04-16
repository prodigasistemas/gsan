package gcom.gui.cadastro;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Arthur Carvalho
 * @date 17/04/2008
 */
public class InserirEmpresaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Empresa
	 * 
	 * [UC0782] Inserir Empresa
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 14/05/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirEmpresaActionForm inserirEmpresaActionForm = (InserirEmpresaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirEmpresaActionForm.getDescricao();

		Empresa empresa = new Empresa();

		// Nome
		if (!"".equals(inserirEmpresaActionForm.getDescricao())) {
			empresa.setDescricao(inserirEmpresaActionForm.getDescricao());
		}
		// Nome Abreviado
		if (!"".equals(inserirEmpresaActionForm.getDescricaoAbreviada())) {
			empresa.setDescricaoAbreviada(inserirEmpresaActionForm
					.getDescricaoAbreviada());
		}
		// E-mail
		if (!"".equals(inserirEmpresaActionForm.getEmail())) {
			empresa.setEmail(inserirEmpresaActionForm.getEmail());
		}
		// Empresa Principal
		if (inserirEmpresaActionForm.getIndicadorEmpresaPrincipal() != null
				&& !"".equals(inserirEmpresaActionForm
						.getIndicadorEmpresaPrincipal())) {
			empresa.setIndicadorEmpresaPrincipal(inserirEmpresaActionForm
					.getIndicadorEmpresaPrincipal());
		}

		// Indicador Empresa Contratada Cobranca
		if (inserirEmpresaActionForm.getIndicadorEmpresaCobranca() != null
				&& !"".equals(inserirEmpresaActionForm
						.getIndicadorEmpresaCobranca())) {
			empresa.setIndicadorEmpresaContratadaCobranca(new Integer(
					inserirEmpresaActionForm.getIndicadorEmpresaCobranca())
					.shortValue());
		}
		
		if ( inserirEmpresaActionForm.getIndicadorLeitura() != null
				&& ! "".equals(inserirEmpresaActionForm.getIndicadorLeitura())){
			
			empresa.setIndicadorLeitura( inserirEmpresaActionForm.getIndicadorLeitura() );
			
		}
		
		// Indicador de Uso
		Short iu = ConstantesSistema.INDICADOR_USO_ATIVO;

		empresa.setIndicadorUso(iu);

		EmpresaContratoCobranca empresaCobranca = null;
		
		// Verifica se a empresa de cobranca
		if (inserirEmpresaActionForm.getIndicadorEmpresaCobranca() != null
				&& inserirEmpresaActionForm.getIndicadorEmpresaCobranca()
						.equals("" + ConstantesSistema.INDICADOR_USO_ATIVO)) {

			empresaCobranca = new EmpresaContratoCobranca();

			// validar Data Inicio Contrato de Cobrança
			if (inserirEmpresaActionForm.getDataInicioContratoCobranca() != null
					&& !inserirEmpresaActionForm
							.getDataInicioContratoCobranca().equals("")) {

				Date data = Util
						.converteStringParaDate(inserirEmpresaActionForm
								.getDataInicioContratoCobranca());
				empresaCobranca.setDataInicioContrato(data);
			} else {
				throw new ActionServletException("atencao.informe_campo", null,
						" Data do Início do Contrato");
			}

			// Percentual de Cobranca
			if (inserirEmpresaActionForm.getPercentualPagamento() != null
					&& !inserirEmpresaActionForm.getPercentualPagamento()
							.equals("")
					&& (inserirEmpresaActionForm.getPercentualDaFaixaInformado() == null
							|| !inserirEmpresaActionForm.getPercentualDaFaixaInformado().equalsIgnoreCase("sim"))) {
				
				BigDecimal percentualPagamentoAtual = null;

				String percentualPagamento = inserirEmpresaActionForm
						.getPercentualPagamento().toString().replace(".", "");

				percentualPagamento = percentualPagamento.replace(",", ".");
				percentualPagamentoAtual = new BigDecimal(percentualPagamento);

				empresaCobranca
						.setPercentualContratoCobranca(percentualPagamentoAtual);
				empresaCobranca
						.setCodigoLayoutTxt(ConstantesSistema.SIM);

			}

		}
		
		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = null;
		
		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixa") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixa").equals("")){

			colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>) sessao.getAttribute("colecaoEmpresaCobrancaFaixa");

			empresaCobranca.setCodigoLayoutTxt(ConstantesSistema.NAO);
			
		}

		Integer idEmpresa = (Integer) fachada.inserirEmpresa(empresa,
				empresaCobranca, usuarioLogado, colecaoEmpresaCobrancaFaixa);

		montarPaginaSucesso(httpServletRequest, "Empresa " + descricao
				+ " inserido com sucesso.", "Inserir outra Empresa",
				"exibirInserirEmpresaAction.do?menu=sim",
				"exibirAtualizarEmpresaAction.do?idRegistroAtualizacao="
						+ idEmpresa, "Atualizar Empresa Inserida");

		sessao.removeAttribute("InserirEmpresaActionForm");

		return retorno;

	}
}
