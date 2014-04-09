package gcom.gui.cadastro.imovel;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Ivan Sérgio
 */
public class ValidarImovelCurvaAbcDebitosAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter*
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = null;
		
		ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm = (ImovelCurvaAbcDebitosActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		// Recupera a data de Referencia da Cobranca informada pelo usuario
		// e converte para o formato padrao do sistema parametros (AAAAMM)
		Integer anoMesReferenciaInicial = Util.converterStringParaInteger(Util
				.formatarMesAnoParaAnoMes(imovelCurvaAbcDebitosActionForm.getReferenciaCobrancaInicial().replace("/", "")));
		
		Integer anoMesReferenciaFinal = Util.converterStringParaInteger(Util
				.formatarMesAnoParaAnoMes(imovelCurvaAbcDebitosActionForm.getReferenciaCobrancaFinal().replace("/", "")));
		
		// Recupera o ano/mes da Arrecadacao atual no sistema parametro
		Integer anoMesReferenciaArrecadacao = fachada.pesquisarParametrosDoSistema().getAnoMesArrecadacao();
		imovelCurvaAbcDebitosActionForm.setAnoMesReferenciaArrecadacaoAtual(""+anoMesReferenciaArrecadacao);
		
		// [FS0004 - Validar Referencia]
		if (anoMesReferenciaInicial > anoMesReferenciaArrecadacao) {
			throw new ActionServletException(
					"atencao.adicionar_debito_ano_mes_referencia_invalido",
					null, "da Cobrança Inicial");
		}
		
		if (anoMesReferenciaFinal > anoMesReferenciaArrecadacao) {
			throw new ActionServletException(
					"atencao.adicionar_debito_ano_mes_referencia_invalido",
					null, "da Cobrança Final");
		}
		
		/**
		 * Classificacao: Caso o usuario selecione a opcao ESTADO inibir todo os
		 * 				  os campos da Aba Localizacao;
		 * 				  Caso o usuario selecione a opcao REGIONAL inibir os
		 * 				  os campos Localidade e Setor Comercial da Aba Localizacao;
		 */
		
		if (imovelCurvaAbcDebitosActionForm.getClassificacao() != null &&
			imovelCurvaAbcDebitosActionForm.getClassificacao().equalsIgnoreCase("ESTADO")) {
			
			imovelCurvaAbcDebitosActionForm.limparCamposPorEstado();
		}else if (imovelCurvaAbcDebitosActionForm.getClassificacao() != null &&
				imovelCurvaAbcDebitosActionForm.getClassificacao().equalsIgnoreCase("REGIONAL")) {
			
			imovelCurvaAbcDebitosActionForm.limparCamposPorRegional();
		}
		
		return retorno;
	}
}
