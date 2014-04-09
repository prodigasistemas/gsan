package gcom.gui.atendimentopublico;


import java.util.Collection;
import java.util.Date;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarLigacaoEsgotoEsgotamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarLigacaoEsgotoEsgotamentoActionForm atualizarLigacaoEsgotoEsgotamentoActionForm = (AtualizarLigacaoEsgotoEsgotamentoActionForm) actionForm;

		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento= (LigacaoEsgotoEsgotamento) sessao.getAttribute("atualizarLigacaoEsgotoEsgotamento");
		
		Collection colecaoPesquisa = null;
		
		if(atualizarLigacaoEsgotoEsgotamentoActionForm.getCodigo()!= null 
				&& !atualizarLigacaoEsgotoEsgotamentoActionForm.getCodigo().equals("")){
			ligacaoEsgotoEsgotamento.setId(new Integer(atualizarLigacaoEsgotoEsgotamentoActionForm.getCodigo()));
		}else{
			ligacaoEsgotoEsgotamento.setId(null);
		}
		
		String codigo = atualizarLigacaoEsgotoEsgotamentoActionForm.getCodigo();		
        String descricao = atualizarLigacaoEsgotoEsgotamentoActionForm.getDescricao();
        Short indicadorUso = atualizarLigacaoEsgotoEsgotamentoActionForm.getIndicadorUso();
        String quantidadeMesesSituacaoEspecial = atualizarLigacaoEsgotoEsgotamentoActionForm.getQuantidadeMesesSituacaoEspecial();
        String faturamentoSituacaoTipo = atualizarLigacaoEsgotoEsgotamentoActionForm.getFaturamentoSituacaoTipo();
        String faturamentoSituacaoMotivo = atualizarLigacaoEsgotoEsgotamentoActionForm.getFaturamentoSituacaoMotivo();
        
        
        ligacaoEsgotoEsgotamento.setDescricao(descricao);
        
        if(quantidadeMesesSituacaoEspecial != null && !quantidadeMesesSituacaoEspecial.equals("")){
        
        	ligacaoEsgotoEsgotamento.setQuantidadeMesesSituacaoEspecial(new Integer (quantidadeMesesSituacaoEspecial));
       
        } else {
        
        	ligacaoEsgotoEsgotamento.setQuantidadeMesesSituacaoEspecial(null);
       
        }
        
        
        if(codigo != null && !codigo.equals("")){
        	ligacaoEsgotoEsgotamento.setId(new Integer(codigo));
        }else{
        	ligacaoEsgotoEsgotamento.setId(null);
        }
        
        ligacaoEsgotoEsgotamento.setIndicadorUso(new Short(indicadorUso));
        ligacaoEsgotoEsgotamento.setUltimaAlteracao(new Date());

        //Tipo Situação Especial Faturamento
		if(faturamentoSituacaoTipo != null && !faturamentoSituacaoTipo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FaturamentoSituacaoTipo faturamentoTipo = new FaturamentoSituacaoTipo();
			faturamentoTipo.setId(new Integer(atualizarLigacaoEsgotoEsgotamentoActionForm
					.getFaturamentoSituacaoTipo()));
			
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoTipo(faturamentoTipo);
			
		} else {
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoTipo(null);
		}
		
		//Motivo Situacao Especial Faturamento
		if(faturamentoSituacaoMotivo != null && !faturamentoSituacaoMotivo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FaturamentoSituacaoMotivo faturamentoMotivo = new FaturamentoSituacaoMotivo();
			faturamentoMotivo.setId(new Integer(atualizarLigacaoEsgotoEsgotamentoActionForm
					.getFaturamentoSituacaoMotivo()));
			
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoMotivo(faturamentoMotivo);

		} else{
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoMotivo(null);
		}


        FiltroLigacaoEsgotoEsgotamento filtroLigEsgEsgotamento= new FiltroLigacaoEsgotoEsgotamento();
        filtroLigEsgEsgotamento.adicionarParametro(
				new ParametroSimples(FiltroFonteAbastecimento.DESCRICAO, descricao));
        filtroLigEsgEsgotamento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroLigacaoEsgotoEsgotamento.ID, ligacaoEsgotoEsgotamento.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroLigEsgEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		
		FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
		filtroLigacaoEsgotoEsgotamento.adicionarParametro(
					new ParametroSimples(FiltroFonteAbastecimento.ID, codigo));
		filtroLigacaoEsgotoEsgotamento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroLigacaoEsgotoEsgotamento.ID, ligacaoEsgotoEsgotamento.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroLigacaoEsgotoEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigo+"");
		}
		
		fachada.atualizar(ligacaoEsgotoEsgotamento);

		
		montarPaginaSucesso(httpServletRequest, "Ligação de Esgoto Esgotamento "
				+ descricao + " atualizado com sucesso.",
				"Realizar outra Manutenção da Ligação de Esgoto Esgotamento",
				"exibirFiltrarLigacaoEsgotoEsgotamentoAction.do?menu=sim");        
        
		return retorno;
	}
}
