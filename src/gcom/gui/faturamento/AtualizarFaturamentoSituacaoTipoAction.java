package gcom.gui.faturamento;





import java.util.Collection;
import java.util.Date;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarFaturamentoSituacaoTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarFaturamentoSituacaoTipoActionForm atualizarFaturamentoSituacaoTipoActionForm = (AtualizarFaturamentoSituacaoTipoActionForm) actionForm;

		FaturamentoSituacaoTipo faturamentoSituacaoTipo= (FaturamentoSituacaoTipo) sessao.getAttribute("atualizarFaturamentoSituacaoTipo");
		
		if(atualizarFaturamentoSituacaoTipoActionForm.getCodigo()!= null 
				&& !atualizarFaturamentoSituacaoTipoActionForm.getCodigo().equals("")){
			faturamentoSituacaoTipo.setId(new Integer(atualizarFaturamentoSituacaoTipoActionForm.getCodigo()));
		}else{
			faturamentoSituacaoTipo.setId(null);
		}
		
		String codigo = atualizarFaturamentoSituacaoTipoActionForm.getCodigo();
		String descricao = atualizarFaturamentoSituacaoTipoActionForm.getDescricao();
		String indicadorParalisacaoFaturamento = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoFaturamento();
		String indicadorParalisacaoLeitura = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoLeitura();
		String indicadorValidoAgua = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorValidoAgua();
		String indicadorValidoEsgoto = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorValidoEsgoto();
		String indicadorInformarConsumo = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorInformarConsumo();
		String indicadorInformarVolume = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorInformarVolume();
		String indicadorUso = atualizarFaturamentoSituacaoTipoActionForm.getIndicadorUso();
		String leituraAnormalidadeLeituraComLeitura = atualizarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeLeituraComLeitura();
		String leituraAnormalidadeLeituraSemLeitura = atualizarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeLeituraSemLeitura();
		String leituraAnormalidadeConsumoComLeitura = atualizarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeConsumoComLeitura();
		String leituraAnormalidadeConsumoSemLeitura = atualizarFaturamentoSituacaoTipoActionForm.getLeituraAnormalidadeConsumoSemLeitura();
		
       
        Collection colecaoPesquisa = null;
		
        faturamentoSituacaoTipo.setDescricao(descricao);
        faturamentoSituacaoTipo.setUltimaAlteracao(new Date());
        
        //Indicador de Uso
        faturamentoSituacaoTipo.setIndicadorUso(new Short(indicadorUso));

        //Indicador Paralisacao Faturamento
        faturamentoSituacaoTipo.setIndicadorParalisacaoFaturamento(new Short(indicadorParalisacaoFaturamento));
        
        //Indicador Paralisacao Leitura
        faturamentoSituacaoTipo.setIndicadorParalisacaoLeitura(new Short(indicadorParalisacaoLeitura));
        
        //Indicador Valido Água
        faturamentoSituacaoTipo.setIndicadorValidoAgua(new Short(indicadorValidoAgua));
        
        //Indicador Valido Esgoto
        faturamentoSituacaoTipo.setIndicadorValidoEsgoto(new Short(indicadorValidoEsgoto));
        
        //Indicador Informar Consumo
        faturamentoSituacaoTipo.setIndicadorInformarConsumo(new Short (indicadorInformarConsumo));
        
        //Indicador Informar Volume
        faturamentoSituacaoTipo.setIndicadorInformarVolume(new Short (indicadorInformarVolume));
        
        //Leitura Anormalidade Leitura Com Leitura
        if (leituraAnormalidadeLeituraComLeitura != null 
        		&& !leituraAnormalidadeLeituraComLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeLeitura leituraAnormalidadeComLeitura = new LeituraAnormalidadeLeitura();
        	leituraAnormalidadeComLeitura.setId(new Integer(leituraAnormalidadeLeituraComLeitura));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraComLeitura(leituraAnormalidadeComLeitura);
        }else{
        	faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraComLeitura(null);
        }

        //Leitura Anormalidade Leitura Sem Leitura
        if (leituraAnormalidadeLeituraSemLeitura != null 
        		&& !leituraAnormalidadeLeituraSemLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeLeitura leituraAnormalidadeSemLeitura = new LeituraAnormalidadeLeitura();
        	leituraAnormalidadeSemLeitura.setId(new Integer(leituraAnormalidadeLeituraSemLeitura));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraSemLeitura(leituraAnormalidadeSemLeitura);
        }else{
        	faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraSemLeitura(null);
        }

        //Leitura Anormalidade Consumo Com Leitura
        if (leituraAnormalidadeConsumoComLeitura != null 
        		&& !leituraAnormalidadeConsumoComLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeConsumo leituraAnormalidadeConsComLeitura = new LeituraAnormalidadeConsumo();
        	leituraAnormalidadeConsComLeitura.setId(new Integer(leituraAnormalidadeConsumoComLeitura));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoComLeitura(leituraAnormalidadeConsComLeitura);
        }else{
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoSemLeitura(null);
        }
        
        //Leitura Anormalidade Consumo Sem Leitura
        if (leituraAnormalidadeConsumoSemLeitura != null 
        		&& !leituraAnormalidadeConsumoSemLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeConsumo leituraAnormalidadeConsSemLeitura = new LeituraAnormalidadeConsumo();
        	leituraAnormalidadeConsSemLeitura.setId(new Integer(leituraAnormalidadeConsumoSemLeitura));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoSemLeitura(leituraAnormalidadeConsSemLeitura);
        }else{
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoSemLeitura(null);
        }
        
        FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo= new FiltroFaturamentoSituacaoTipo();
        filtroFaturamentoSituacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroFaturamentoSituacaoTipo.DESCRICAO, descricao));
        filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroFaturamentoSituacaoTipo.ID, faturamentoSituacaoTipo.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		
		filtroFaturamentoSituacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoSituacaoTipo.ID, codigo));
		filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroFaturamentoSituacaoTipo.ID, faturamentoSituacaoTipo.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigo+"");
		}
		
		fachada.atualizar(faturamentoSituacaoTipo);

		
		montarPaginaSucesso(httpServletRequest, "Tipo de Situacao de Faturamento"
				+ descricao + " atualizado com sucesso.",
				"Realizar outra Manutenção do Tipo de Situação de Faturamento",
				"exibirFiltrarFaturamentoSituacaoTipoAction.do?menu=sim");        
        
		return retorno;
	}
}
