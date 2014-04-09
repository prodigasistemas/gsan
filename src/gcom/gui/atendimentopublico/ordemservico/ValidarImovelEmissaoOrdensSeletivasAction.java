package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Ivan Sérgio
 */
public class ValidarImovelEmissaoOrdensSeletivasAction extends GcomAction {

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
		
		ImovelEmissaoOrdensSeletivasActionForm form = 
			(ImovelEmissaoOrdensSeletivasActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(form.getTipoRelatorio()!=null){
			if(form.getTipoRelatorio().equals("1")){
				sessao.setAttribute("tipoRelatorio", "SINTETICO");
			}else if(form.getTipoRelatorio().equals("2")){
				sessao.setAttribute("tipoRelatorio", "ANALITICO");
			}else{
				sessao.removeAttribute("tipoRelatorio");
			}
		}

		// [FS0006] - Validar Ano/Mes de Instalacao
		if (form.getMesAnoInstalacaoInicial() != null &&
				!form.getMesAnoInstalacaoInicial().trim().equals("")
				&& form.getMesAnoInstalacaoInicial().contains("/")) {
			Integer anoMesAtual = Util.converterStringParaInteger(
					Util.getAnoMesComoString(new Date()).replace("/", ""));
			
			Integer anoMesInstalacaoInicial = Util.converterStringParaInteger(
					Util.formatarMesAnoParaAnoMes(
							form.getMesAnoInstalacaoInicial().replace("/", "")));
			
			if (anoMesInstalacaoInicial > anoMesAtual) {
				throw new ActionServletException("atencao.mes_ano_instalacao_invalido", null, "");
			}
		}
		
		if (form.getMesAnoInstalacaoFinal() != null &&
				!form.getMesAnoInstalacaoFinal().trim().equals("")
				&& form.getMesAnoInstalacaoFinal().contains("/")) {
			Integer anoMesAtual = Util.converterStringParaInteger(
					Util.getAnoMesComoString(new Date()).replace("/", ""));
			
			Integer anoMesInstalacaoFinal = Util.converterStringParaInteger(
					Util.formatarMesAnoParaAnoMes(
							form.getMesAnoInstalacaoFinal().replace("/", "")));
			
			if (form.getMesAnoInstalacaoInicial() != null &&
					!form.getMesAnoInstalacaoInicial().trim().equals("")
					&& form.getMesAnoInstalacaoInicial().contains("/")){
				
				Integer anoMesInstalacaoInicial = Util.converterStringParaInteger(
						Util.formatarMesAnoParaAnoMes(
								form.getMesAnoInstalacaoInicial().replace("/", "")));
				
				if (anoMesInstalacaoFinal < anoMesInstalacaoInicial){
					
					throw new ActionServletException("atencao.mes_ano_final_instalacao_menor_mes_ano_inicial_instalacao", null, "");
					
				}
				
			}
			
			if (anoMesInstalacaoFinal > anoMesAtual) {
				throw new ActionServletException("atencao.mes_ano_instalacao_invalido", null, "");
			}
		}
		
		if(form.getTipoOrdem() == null || form.getTipoOrdem().equalsIgnoreCase("")
				|| form.getTipoOrdem().equalsIgnoreCase("-1")){
			throw new ActionServletException("atencao.campo.informado", null, "Tipo da Ordem");
		}
		if(form.getSugestao() != null && form.getSugestao().equals("2")){
			if(form.getDescricaoComando() == null || form.getDescricaoComando().equals("")){
				throw new ActionServletException("atencao.campo.informado", null, "Descrição Comando");
			}
		}
		
		/**
		 * Tipo da Ordem: Caso o usuario selecione a opcao INSTALACAO inibir todo os
		 * 				  os campos da Aba Hidrometro;
		 */
		if (form.getTipoOrdem() != null) {
			
			if(form.getTipoOrdem().equalsIgnoreCase(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)){
				
				form.limparCamposHidrometro();
				
			}else if(form.getTipoOrdem().equalsIgnoreCase(ImovelEmissaoOrdensSeletivasActionForm.TIPO_INSPECAO_ANORMALIDADE)){
			
				if(form.getIdImovel() == null ||form.getIdImovel().equals("")){
					String concluir = httpServletRequest.getParameter("concluir");
					
					
					if(form.getAnormalidadeHidrometro() == null){
						if(concluir != null){
//							1.3.3.Anormalidade de Leitura(obrigatório caso o tipo 
							//de ordem selecionado corresponda a ‘INSPEÇÃO DE ANORMALIDADE’
							throw new ActionServletException("atencao.campo.informado", null, "Anormalidade de Leitura");
						}else if(sessao.getAttribute("collectionHidrometroAnormalidade") != null){
//							1.3.3.Anormalidade de Leitura(obrigatório caso o tipo 
							//de ordem selecionado corresponda a ‘INSPEÇÃO DE ANORMALIDADE’
							throw new ActionServletException("atencao.campo.informado", null, "Anormalidade de Leitura");
						}
					}else if(form.getNumeroOcorrenciasConsecutivas() == null || form.getNumeroOcorrenciasConsecutivas().equals("")){
						throw new ActionServletException("atencao.campo.informado", null, "Num. Ocorrências Consecutivas");
					}
					
				}
				
			}
		}
		
		return retorno;
	}
}
