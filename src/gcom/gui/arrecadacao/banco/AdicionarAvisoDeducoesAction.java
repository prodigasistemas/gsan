package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.DeducaoTipo;
import gcom.arrecadacao.FiltroAvisoDeducoes;
import gcom.arrecadacao.FiltroDeducaoTipo;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdicionarAvisoDeducoesAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirAvisoBancarioProcessoDoisParaTres");
		
		httpServletRequest.setAttribute("reload","reload");
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		PesquisarAvisoDeducoesActionForm form = (PesquisarAvisoDeducoesActionForm) actionForm; 
		
		
		//Recupera da sessao os aviso deducao que vem do popup
		if(sessao.getAttribute("collectionAvisoDeducao") != null && !sessao.getAttribute("collectionAvisoDeducao").equals("")){
			AvisoDeducoes avisoDeducoes = new AvisoDeducoes();
			
			if(form.getValorDeducaoInclusao() == null)
				throw new ActionServletException("errors.required",null, "valor dedução" );
			
			//Populando aviso deducoes
			String valorDeducaoFormatado = form.getValorDeducaoInclusao().replace(".", "").replace(",", ".");
			avisoDeducoes.setValorDeducao(new BigDecimal(valorDeducaoFormatado));
			DeducaoTipo deducaoTipo = new DeducaoTipo();
			deducaoTipo.setId(new Integer(form.getTipoDeducaoInclusao()));
			//Populando aviso deducoes
			
			//Pesquisar Deducao Tipo
			FiltroDeducaoTipo filtroDeducaoTipo = new FiltroDeducaoTipo();
			filtroDeducaoTipo.adicionarParametro(new ParametroSimples(FiltroAvisoDeducoes.ID, form.getTipoDeducaoInclusao()));
			Collection<DeducaoTipo> collectionDeducaoTipo = fachada.pesquisar(filtroDeducaoTipo, DeducaoTipo.class.getName());
			DeducaoTipo deducaoTemp = (DeducaoTipo)Util.retonarObjetoDeColecao(collectionDeducaoTipo);
			deducaoTipo = deducaoTemp;
			//Pesquisar Deducao Tipo			
			
			avisoDeducoes.setDeducaoTipo(deducaoTipo);
			avisoDeducoes.setUltimaAlteracao(new Date());
			Collection collectionAvisoDeducao = (Collection)sessao.getAttribute("collectionAvisoDeducao");
			collectionAvisoDeducao.add(avisoDeducoes);
			//sessao.setAttribute("collectionAvisoDeducao", collectionAvisoDeducao);
		}
		//Recupera da sessao os aviso deducao que vem do popup
		

		
		return retorno;
	}
	
}
