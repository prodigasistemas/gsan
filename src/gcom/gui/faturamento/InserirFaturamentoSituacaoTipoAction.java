package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirFaturamentoSituacaoTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão do tipo da situação de faturamento
	 * 
	 * [UC0845] Inserir Tipo da Situação de Faturamento
	 * 
	 * @author Arthur Carvalho
	 * @date 19/08/2008
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

		InserirFaturamentoSituacaoTipoActionForm inserirFaturamentoSituacaoTipoActionForm = (InserirFaturamentoSituacaoTipoActionForm) actionForm;

		
		String descricao = inserirFaturamentoSituacaoTipoActionForm.getDescricao();
		Short indicadorParalisacaoFaturamento = inserirFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoFaturamento();
		Short indicadorParalisacaoLeitura = inserirFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoLeitura();
		Short indicadorValidoAgua = inserirFaturamentoSituacaoTipoActionForm.getIndicadorValidoAgua();
		Short indicadorValidoEsgoto = inserirFaturamentoSituacaoTipoActionForm.getIndicadorValidoEsgoto();
		Short indicadorInformarConsumo = inserirFaturamentoSituacaoTipoActionForm.getIndicadorInformarConsumo();
	    Short indicadorInformarVolume = inserirFaturamentoSituacaoTipoActionForm.getIndicadorInformarVolume();
		String leituraAnormalidadeLeituraComLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeLeituraComLeitura();
		String leituraAnormalidadeLeituraSemLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeLeituraSemLeitura();
		String leituraAnormalidadeConsumoComLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeConsumoComLeitura();
		String leituraAnormalidadeConsumoSemLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeConsumoSemLeitura();
		
		FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
		Collection colecaoPesquisa = null;

		
		// Descrição
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
		filtroFaturamentoSituacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroFaturamentoSituacaoTipo.DESCRICAO, descricao));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		//Indicador de Paralisação do Faturamento
		if (!"".equals(indicadorParalisacaoFaturamento)) {
			faturamentoSituacaoTipo.setIndicadorParalisacaoFaturamento(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorParalisacaoFaturamento());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorParalisacaoFaturamento");
		}
		
		//Indicador de Paralisação de Leitura
		if (!"".equals(indicadorParalisacaoLeitura)) {
			faturamentoSituacaoTipo.setIndicadorParalisacaoLeitura(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorParalisacaoLeitura());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorParalisacaoLeitura");
		}
		
		//Indicador de Valido para Água
		if (!"".equals(indicadorValidoAgua)) {
			faturamentoSituacaoTipo.setIndicadorValidoAgua(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorValidoAgua());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorValidoAgua");
		}
		
		//Indicador de Valido para Esgoto
		if (!"".equals(indicadorValidoEsgoto)) {
			faturamentoSituacaoTipo.setIndicadorValidoEsgoto(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorValidoEsgoto());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorValidoEsgoto");
		}
		
		//Indicador de Informar Consumo Fixo
		if (!"".equals(indicadorInformarConsumo)) {
			faturamentoSituacaoTipo.setIndicadorInformarConsumo(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorInformarConsumo());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorInformarConsumo");
		}	
		
		//Indicador de Informar Volume Fixo
		if (!"".equals(indicadorInformarVolume)) {
			faturamentoSituacaoTipo.setIndicadorInformarVolume(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorInformarVolume());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Indicador de Informar Volume Fixo");
		}	
		
		//Anormalidade de Consumo Cobrar Sem Leitura
        if (leituraAnormalidadeConsumoSemLeitura != null && 
        		!leituraAnormalidadeConsumoSemLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeConsumo leituraConsumoSemLeitura = new LeituraAnormalidadeConsumo();
        	leituraConsumoSemLeitura.setId(new Integer( leituraAnormalidadeConsumoSemLeitura ));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoSemLeitura( leituraConsumoSemLeitura );
        }else {
			throw new ActionServletException("atencao.required", null,
			"Anormalidade de Consumo Cobrar Sem Leitura");
        }	
		
        //Leitura Anormalidade Consumo Com Leitura
		if(leituraAnormalidadeConsumoComLeitura != null &&
				!leituraAnormalidadeConsumoComLeitura.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			LeituraAnormalidadeConsumo leituraConsumoComLeitura = new LeituraAnormalidadeConsumo();
			leituraConsumoComLeitura.setId(new Integer(leituraAnormalidadeConsumoComLeitura));
			faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoComLeitura(leituraConsumoComLeitura);
		}else {
			throw new ActionServletException("atencao.required", null,
			"Leitura Anormalidade Consumo Com Leitura");
        }	
		
		//Leitura Anormalidade Leitura Sem Leitura
		if(leituraAnormalidadeLeituraSemLeitura != null &&
				!leituraAnormalidadeLeituraSemLeitura.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			LeituraAnormalidadeLeitura leituraSemLeitura = new LeituraAnormalidadeLeitura();
			leituraSemLeitura.setId(new Integer(leituraAnormalidadeLeituraSemLeitura));
			faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraSemLeitura(leituraSemLeitura);
		}else {
			throw new ActionServletException("atencao.required", null,
			"Leitura Anormalidade Leitura Sem Leitura");
        }	
		
		//Leitura Anormalidade Leitura Com Leitura
		if(leituraAnormalidadeLeituraComLeitura != null &&
				!leituraAnormalidadeLeituraComLeitura.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			LeituraAnormalidadeLeitura leituraComLeitura = new LeituraAnormalidadeLeitura();
			leituraComLeitura.setId(new Integer(leituraAnormalidadeLeituraComLeitura));
			faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraComLeitura(leituraComLeitura);
		}else {
			throw new ActionServletException("atencao.required", null,
			"Leitura Anormalidade Leitura Com Leitura");
        }	
        
			faturamentoSituacaoTipo.setDescricao(descricao);
			faturamentoSituacaoTipo.setUltimaAlteracao(new Date());
			faturamentoSituacaoTipo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

			Integer idFaturamentoSituacaoTipo = (Integer) this.getFachada().inserir(faturamentoSituacaoTipo);

			montarPaginaSucesso(httpServletRequest,
					"Tipo da Situação de Faturamento " + descricao
							+ " inserida com sucesso.",
					"Inserir outro tipo de situação de faturamento",
					"exibirInserirFaturamentoSituacaoTipoAction.do?menu=sim",
					"exibirAtualizarFaturamentoSituacaoTipoAction.do?idRegistroAtualizacao="
							+ idFaturamentoSituacaoTipo,
					"Atualizar tipo de situação de faturamento Inserida");

			this.getSessao(httpServletRequest).removeAttribute("InserirFaturamentoSituacaoTipoActionForm");

			return retorno;
		
		
	}
}		
