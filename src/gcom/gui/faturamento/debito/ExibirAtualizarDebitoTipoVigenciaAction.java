package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.FiltroDebitoTipoVigencia;
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
 * Descrição da classe
 * 
 * @author Hugo Leonardo
 * @date 16/04/2010
 */
public class ExibirAtualizarDebitoTipoVigenciaAction extends GcomAction {
	/**
	 * [UC0986] Manter tipo de débito com vigência
	 * [SB0001] – Gerar vigência para o tipo de débito
	 * 
	 * Este caso de uso permite alterar um debito tipo vigencia
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarDebitoTipoVigencia");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarDebitoTipoVigenciaActionForm form = (AtualizarDebitoTipoVigenciaActionForm) actionForm;
		
		DebitoTipoVigencia debitoTipoVigencia = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		String idDebitoTipoVigencia = "";
		
		if ( sessao.getAttribute("objetoDebitoTipoVigencia") != null ) {
			debitoTipoVigencia = (DebitoTipoVigencia) sessao.getAttribute("objetoDebitoTipoVigencia");
		
		} else {
		
			idDebitoTipoVigencia = (String) httpServletRequest.getParameter("idRegistroAtualizar");
			
			
			if( idDebitoTipoVigencia != null ) {
				
				FiltroDebitoTipoVigencia filtroDebitoTipoVigencia = new FiltroDebitoTipoVigencia();
				
				filtroDebitoTipoVigencia.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoTipoVigencia.DEBITO_TIPO);
				filtroDebitoTipoVigencia.adicionarParametro(
							new ParametroSimples(FiltroDebitoTipoVigencia.ID, new Integer(idDebitoTipoVigencia)));
				
				debitoTipoVigencia = (DebitoTipoVigencia) fachada.pesquisar(filtroDebitoTipoVigencia, 
								DebitoTipoVigencia.class.getName()).iterator().next();
			}
		}
		Date timeStamp = debitoTipoVigencia.getUltimaAlteracao();
		
		if(debitoTipoVigencia.getId() != null && !debitoTipoVigencia.getId().equals("")){
			form.setIdDebitoTipoVigencia(debitoTipoVigencia.getId().toString());
		}
		
		if(debitoTipoVigencia.getDebitoTipo() != null && !debitoTipoVigencia.getDebitoTipo().equals("")){
			form.setDebitoTipo(debitoTipoVigencia.getDebitoTipo().getId().toString());
		}
		
		if(debitoTipoVigencia.getDebitoTipo().getDescricao() != null && !debitoTipoVigencia.getDebitoTipo().getDescricao().equals("")){
			form.setNomeDebitoTipo(debitoTipoVigencia.getDebitoTipo().getDescricao().toString());
		}
		
		if(debitoTipoVigencia.getValorDebito() != null && !debitoTipoVigencia.getValorDebito().equals("")){
			form.setValorDebito(Util.formatarBigDecimalParaStringComVirgula(debitoTipoVigencia.getValorDebito()));
		}
		
		if(debitoTipoVigencia.getDataVigenciaInicial() != null && !debitoTipoVigencia.getDataVigenciaInicial().equals("")){
			form.setDataVigenciaInicial(Util.formatarData(debitoTipoVigencia.getDataVigenciaInicial()));
		}
		
		if(debitoTipoVigencia.getDataVigenciaFinal() != null && !debitoTipoVigencia.getDataVigenciaFinal().equals("")){
			form.setDataVigenciaFinal(Util.formatarData(debitoTipoVigencia.getDataVigenciaFinal()));
		}
		
		sessao.setAttribute("idDebitoTipoVigencia", idDebitoTipoVigencia);
		sessao.setAttribute("debitoTipoVigencia", debitoTipoVigencia);
		sessao.setAttribute("timeStamp", timeStamp);
		
		httpServletRequest.setAttribute("idDebitoTipoVigencia", idDebitoTipoVigencia);		
		
		return retorno;
		
	}

}
