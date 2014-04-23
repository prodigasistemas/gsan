package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 * @date 27/12/2010
 */
public class ExibirAtualizarCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarCustoPavimentoPorRepavimentadoraAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCustoPavimentoPorRepavimentadoraActionForm form = (AtualizarCustoPavimentoPorRepavimentadoraActionForm) actionForm;
		
		UnidadeRepavimentadoraCustoPavimentoRua unidadeRepavimentadoraCustoPavimentoRua = null;
		
		UnidadeRepavimentadoraCustoPavimentoCalcada unidadeRepavimentadoraCustoPavimentoCalcada = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		String idUnidadeRepavimentadoraCustoPavimentoRua = "";
		
		String idUnidadeRepavimentadoraCustoPavimentoCalcada = "";
		
		// Atualiza Custo Pavimento Rua
		if(httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("atualizarRua") ){
			
			if ( sessao.getAttribute("objetoUnidadeCustoPavimentoRua") != null ) {
				unidadeRepavimentadoraCustoPavimentoRua = (UnidadeRepavimentadoraCustoPavimentoRua) sessao.getAttribute("objetoUnidadeCustoPavimentoRua");
			
			} else {
			
				idUnidadeRepavimentadoraCustoPavimentoRua = (String) httpServletRequest.getParameter("idAtualizacao");
				
				
				if( idUnidadeRepavimentadoraCustoPavimentoRua != null ) {
					
					FiltroUnidadeRepavimentadoraCustoPavimentoRua filtroUnidadeRepavimentadoraCustoPavimentoRua = new FiltroUnidadeRepavimentadoraCustoPavimentoRua();
					
					filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA);
					filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoRua.UNIDADE_REPAVIMENTADORA);
					
					filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarParametro(
								new ParametroSimples(FiltroUnidadeRepavimentadoraCustoPavimentoRua.ID, new Integer(idUnidadeRepavimentadoraCustoPavimentoRua)));
					
					unidadeRepavimentadoraCustoPavimentoRua = (UnidadeRepavimentadoraCustoPavimentoRua) fachada.pesquisar(filtroUnidadeRepavimentadoraCustoPavimentoRua, 
							UnidadeRepavimentadoraCustoPavimentoRua.class.getName()).iterator().next();
				}
			}
			
			Date timeStamp = unidadeRepavimentadoraCustoPavimentoRua.getUltimaAlteracao();
			
			// Descrição Unidade Repavimentadora Rua
			if(unidadeRepavimentadoraCustoPavimentoRua.getUnidadeRepavimentadora() != null && !unidadeRepavimentadoraCustoPavimentoRua.getUnidadeRepavimentadora().getDescricao().equals("")){
				form.setDescricaoUnidadeRepavimentadora(unidadeRepavimentadoraCustoPavimentoRua.getUnidadeRepavimentadora().getDescricao());
			}
			
			// Descrição Tipo Pavimento Rua
			if(unidadeRepavimentadoraCustoPavimentoRua.getPavimentoRua() != null && !unidadeRepavimentadoraCustoPavimentoRua.getPavimentoRua().getDescricao().equals("")){
				form.setDescricaoTipoPavimentoRua(unidadeRepavimentadoraCustoPavimentoRua.getPavimentoRua().getDescricao());
			}
			
			// Valor Pavimento Rua
			if(unidadeRepavimentadoraCustoPavimentoRua.getValorPavimento() != null && !unidadeRepavimentadoraCustoPavimentoRua.getValorPavimento().equals("")){
				form.setValorPavimentoRua(Util.formatarBigDecimalParaStringComVirgula(unidadeRepavimentadoraCustoPavimentoRua.getValorPavimento()));
			}
			
			// Data Inicio Vigencia Pavimento Rua
			if(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial() != null && !unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial().equals("")){
				form.setDataVigenciaInicialPavimentoRua(Util.formatarData(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial()));
			}
			
			// Data Fim Vigencia Pavimento Rua
			if(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaFinal() != null && !unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaFinal().equals("")){
				form.setDataVigenciaFinalPavimentoRua(Util.formatarData(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaFinal()));
			}else{
				form.setDataVigenciaFinalPavimentoRua("");
			}
			
			sessao.setAttribute("idUnidadeRepavimentadoraCustoPavimentoRua", idUnidadeRepavimentadoraCustoPavimentoRua);
			sessao.setAttribute("unidadeRepavimentadoraCustoPavimentoRua", unidadeRepavimentadoraCustoPavimentoRua);
			sessao.setAttribute("timeStamp", timeStamp);
			
			httpServletRequest.setAttribute("idUnidadeRepavimentadoraCustoPavimentoRua", idUnidadeRepavimentadoraCustoPavimentoRua);		
			
		}else {
			
			// Atualiza Custo Pavimento Calçada
			if ( sessao.getAttribute("objetoUnidadeCustoPavimentoCalcada") != null ) {
				unidadeRepavimentadoraCustoPavimentoCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) sessao.getAttribute("objetoUnidadeCustoPavimentoCalcada");
			
			} else {
			
				idUnidadeRepavimentadoraCustoPavimentoCalcada = (String) httpServletRequest.getParameter("idAtualizacao");
				
				if( idUnidadeRepavimentadoraCustoPavimentoCalcada != null ) {
					
					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada filtroUnidadeRepavimentadoraCustoPavimentoCalcada = new FiltroUnidadeRepavimentadoraCustoPavimentoCalcada();
					
					filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.PAVIMENTO_CALCADA);
					filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.UNIDADE_REPAVIMENTADORA);
					
					filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarParametro(
								new ParametroSimples(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.ID, new Integer(idUnidadeRepavimentadoraCustoPavimentoCalcada)));
					
					unidadeRepavimentadoraCustoPavimentoCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) fachada.pesquisar(filtroUnidadeRepavimentadoraCustoPavimentoCalcada, 
							UnidadeRepavimentadoraCustoPavimentoCalcada.class.getName()).iterator().next();
				}
			}
			
			Date timeStamp = unidadeRepavimentadoraCustoPavimentoCalcada.getUltimaAlteracao();
			
			// Descrição Unidade Repavimentadora Rua
			if(unidadeRepavimentadoraCustoPavimentoCalcada.getUnidadeRepavimentadora() != null && !unidadeRepavimentadoraCustoPavimentoCalcada.getUnidadeRepavimentadora().getDescricao().equals("")){
				form.setDescricaoUnidadeRepavimentadora(unidadeRepavimentadoraCustoPavimentoCalcada.getUnidadeRepavimentadora().getDescricao());
			}
			
			// Descrição Tipo Pavimento Calçada
			if(unidadeRepavimentadoraCustoPavimentoCalcada.getPavimentoCalcada() != null && !unidadeRepavimentadoraCustoPavimentoCalcada.getPavimentoCalcada().getDescricao().equals("")){
				form.setDescricaoTipoPavimentoCalcada(unidadeRepavimentadoraCustoPavimentoCalcada.getPavimentoCalcada().getDescricao());
			}
			
			// Valor Pavimento Calçada
			if(unidadeRepavimentadoraCustoPavimentoCalcada.getValorPavimento() != null && !unidadeRepavimentadoraCustoPavimentoCalcada.getValorPavimento().equals("")){
				form.setValorPavimentoCalcada(Util.formatarBigDecimalParaStringComVirgula(unidadeRepavimentadoraCustoPavimentoCalcada.getValorPavimento()));
			}
			
			// Data Inicio Vigencia Pavimento Calçada
			if(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial() != null && !unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial().equals("")){
				form.setDataVigenciaInicialPavimentoCalcada(Util.formatarData(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial()));
			}
			
			// Data Fim Vigencia Pavimento Calçada
			if(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaFinal() != null && !unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaFinal().equals("")){
				form.setDataVigenciaFinalPavimentoCalcada(Util.formatarData(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaFinal()));
			}else{
				form.setDataVigenciaFinalPavimentoCalcada("");
			}
			
			sessao.setAttribute("idUnidadeRepavimentadoraCustoPavimentoCalcada", idUnidadeRepavimentadoraCustoPavimentoCalcada);
			sessao.setAttribute("unidadeRepavimentadoraCustoPavimentoCalcada", unidadeRepavimentadoraCustoPavimentoCalcada);
			sessao.setAttribute("timeStamp", timeStamp);
			
			httpServletRequest.setAttribute("idUnidadeRepavimentadoraCustoPavimentoCalcada", idUnidadeRepavimentadoraCustoPavimentoCalcada);		
			
		}

		return retorno;
	}
}
