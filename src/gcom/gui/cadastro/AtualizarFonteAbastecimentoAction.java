package gcom.gui.cadastro;

import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarFonteAbastecimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarFonteAbastecimentoActionForm atualizarFonteAbastecimentoActionForm = (AtualizarFonteAbastecimentoActionForm) actionForm;

		FonteAbastecimento fonteAbastecimento= (FonteAbastecimento) sessao.getAttribute("atualizarFonteAbastecimento");
		
		Collection colecaoPesquisa = null;
		
		if(atualizarFonteAbastecimentoActionForm.getCodigo()!= null 
				&& !atualizarFonteAbastecimentoActionForm.getCodigo().equals("")){
			fonteAbastecimento.setId(new Integer(atualizarFonteAbastecimentoActionForm.getCodigo()));
		}else{
			fonteAbastecimento.setId(null);
		}
		fonteAbastecimento.setDescricao(atualizarFonteAbastecimentoActionForm.getDescricao());
		fonteAbastecimento.setDescricaoAbreviada(atualizarFonteAbastecimentoActionForm.getDescricaoAbreviada());
		fonteAbastecimento.setIndicadorUso(new Short (atualizarFonteAbastecimentoActionForm.getIndicadorUso()));
		fonteAbastecimento.setIndicadorCalcularVolumeFixo(atualizarFonteAbastecimentoActionForm.getIndicadorCalcularVolumeFixo());
		fonteAbastecimento.setIndicadorPermitePoco(atualizarFonteAbastecimentoActionForm.getIndicadorPermitePoco());
		
		String codigoFonteAbastecimento= atualizarFonteAbastecimentoActionForm.getCodigo();		
        String descricaoFonteAbastecimento = atualizarFonteAbastecimentoActionForm.getDescricao();
        String descricaoAbreviadaFonteAbastecimento = atualizarFonteAbastecimentoActionForm.getDescricaoAbreviada();
        Short indicadorUsoFonteAbastecimento  = atualizarFonteAbastecimentoActionForm.getIndicadorUso();
		
        fonteAbastecimento.setDescricao(descricaoFonteAbastecimento);
        fonteAbastecimento.setDescricaoAbreviada(descricaoAbreviadaFonteAbastecimento);
       
        if(codigoFonteAbastecimento != null && !codigoFonteAbastecimento.equals("")){
        	fonteAbastecimento.setId(new Integer(codigoFonteAbastecimento));
        }else{
        	fonteAbastecimento.setId(null);
        }
        
        fonteAbastecimento.setIndicadorUso(new Short(indicadorUsoFonteAbastecimento));

        fonteAbastecimento.setUltimaAlteracao(new Date());
        

        FiltroFonteAbastecimento filtroFonteAbas= new FiltroFonteAbastecimento();
			filtroFonteAbas.adicionarParametro(
				new ParametroSimples(FiltroFonteAbastecimento.DESCRICAO, descricaoFonteAbastecimento));
			filtroFonteAbas.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroFonteAbastecimento.ID, fonteAbastecimento.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroFonteAbas, FonteAbastecimento.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricaoFonteAbastecimento);
		}
		
		
		FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
			filtroFonteAbastecimento.adicionarParametro(
					new ParametroSimples(FiltroFonteAbastecimento.ID, codigoFonteAbastecimento));
			filtroFonteAbastecimento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroFonteAbastecimento.ID, fonteAbastecimento.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigoFonteAbastecimento+"");
		}
		
		fachada.atualizar(fonteAbastecimento);

		
		montarPaginaSucesso(httpServletRequest, "Fonte de Abastecimento"
				+ descricaoFonteAbastecimento + " atualizado com sucesso.",
				"Realizar outra Manutenção da Fonte de Abastecimento",
				"exibirFiltrarFonteAbastecimentoAction.do?menu=sim");        
        
		return retorno;
	}
}
