package gcom.gui.gerencial.consumo;

import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.micromedicao.ResumoAnormalidadeConsultaHelper;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Administrador
 * @date 07/03/2006
 * 
 */
public class ExibirResultadoConsultaResumoAnormalidadeConsumoAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Administrador
	 * @date 07/03/2006
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

		ActionForward retorno = actionMapping.findForward("resultadoResumoAnormalidadeConsumo");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		List colecaoResumoAnormalidade = (List) sessao
				.getAttribute("colecaoResumoAnormalidadeConsumo");
		
		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");
		
		if(informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao().intValue() ==
				ConstantesSistema.CODIGO_ELO_POLO_LOCALIDADE){
			retorno = actionMapping.findForward("resultadoResumoAnormalidadeEloPoloLocalidade");
		}
		
		//List colecaoResumoAnormalidadeAgua = new ArrayList();
		List colecaoResumoAnormalidadeEsgoto = new ArrayList();
		List colecaoResumoAnormalidadeRelatorio = new ArrayList();

		Iterator iterator = colecaoResumoAnormalidade.iterator();
		
		Object[] resumoAnormalidade = null;

		ResumoAnormalidadeConsultaHelper resumoAnormalidadeConsultaHelper = null;

		int somatorioAnormalidadeAgua = 0;
		/**
		 * Divide o resultado da coleção em dois subresultados
		 * um por ligação agua e outro por ligação esgoto
		 */
		boolean primeiraVez = true;
		while(iterator.hasNext()){
			resumoAnormalidadeConsultaHelper = new ResumoAnormalidadeConsultaHelper();
			
			resumoAnormalidade = (Object[])iterator.next();
			if(primeiraVez){
				primeiraVez = false;
			}else{
				resumoAnormalidadeConsultaHelper.setDescricaoConsumoAnormalidade((String)resumoAnormalidade[0]);
				resumoAnormalidadeConsultaHelper.setQuantidadeMedicao((Integer)resumoAnormalidade[1]+"");
				
				
				
	//			resumoAnormalidadeConsumo = (ResumoAnormalidadeConsumo)resumoAnormalidade[0];
	//			resumoAnormalidadeConsumo.setGerenciaRegional((GerenciaRegional)resumoAnormalidade[1]);
	//			localidade = (Localidade)resumoAnormalidade[3];
	//			localidade.setLocalidade((Localidade)resumoAnormalidade[2]);
	//			resumoAnormalidadeConsumo.setSetorComercial((SetorComercial)resumoAnormalidade[4]);
	//			resumoAnormalidadeConsumo.setQuadra((Quadra)resumoAnormalidade[5]);
	//			resumoAnormalidadeConsumo.setLigacaoTipo((LigacaoTipo)resumoAnormalidade[6]);
	//			resumoAnormalidadeConsumo.setConsumoAnormalidade((ConsumoAnormalidade)resumoAnormalidade[7]);
			
				somatorioAnormalidadeAgua += Integer.parseInt(resumoAnormalidadeConsultaHelper.getQuantidadeMedicao());
				colecaoResumoAnormalidadeEsgoto.add(resumoAnormalidadeConsultaHelper);
	
				colecaoResumoAnormalidadeRelatorio.add(resumoAnormalidadeConsultaHelper);
			}
		}
		
		/**
		 * Cria coleção de agrupamntos(uma coleção de object[3], agrupamento, id, descricao)
		 */
		Collection colecaoAgrupamento = fachada.criarColecaoAgrupamentoResumos(informarDadosGeracaoRelatorioConsultaHelper);
		
		sessao.setAttribute("colecaoResumoAnormalidadeRelatorio", colecaoResumoAnormalidadeRelatorio);
		httpServletRequest.setAttribute("somatorioAnormalidadeAgua",somatorioAnormalidadeAgua+"");
		sessao.setAttribute("colecaoAgrupamento", colecaoAgrupamento);
		sessao.setAttribute("mesAnoReferencia", Util.formatarAnoMesParaMesAno(informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia()));
		
		return retorno;
	}

}
