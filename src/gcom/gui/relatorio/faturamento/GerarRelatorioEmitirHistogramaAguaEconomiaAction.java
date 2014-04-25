package gcom.gui.relatorio.faturamento;

import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.ConsumoFaixaCategoria;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaEconomiaHelper;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.EmissaoHistogramaAguaEconomiaDadosInformarActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioHistogramaAguaEconomia;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe montar o filtro para pesquisa de 
 * emitir histograma de agua por economia
 *
 * @author Rafael Pinto
 * 
 * @date 16/06/2007
 */

public class GerarRelatorioEmitirHistogramaAguaEconomiaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirHistogramaAgua");
		
		// Form
		EmissaoHistogramaAguaEconomiaDadosInformarActionForm form = 
			(EmissaoHistogramaAguaEconomiaDadosInformarActionForm) actionForm;
		
		FiltrarEmitirHistogramaAguaEconomiaHelper filtro = new FiltrarEmitirHistogramaAguaEconomiaHelper();
		
		// Opção de Totalização
		filtro.setOpcaoTotalizacao(Integer.parseInt(form.getOpcaoTotalizacao()));
		
		// Mês/Ano do Faturamento
		Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoFaturamento());
		
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		if(sistemaParametro.getAnoMesFaturamento().intValue() < anoMes.intValue()){
			throw new ActionServletException("atencao.mes_ano.faturamento.inferior", 
				null,""+sistemaParametro.getAnoMesFaturamento());
		}
		
		filtro.setMesAnoFaturamento(anoMes);
		
		// Gerência Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			GerenciaRegional gerencia = new GerenciaRegional();
			gerencia.setId(new Integer(form.getGerenciaRegional()));
			
			filtro.setGerenciaRegional(gerencia);
			
		}

		// Unidade de Negocio
		if (form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			UnidadeNegocio unidade = new UnidadeNegocio();
			unidade.setId(new Integer(form.getUnidadeNegocio()));
			
			filtro.setUnidadeNegocio(unidade);
			
		}
		
		// Elo Pólo
		if (form.getEloPolo() != null && 
			!form.getEloPolo().equals("")) {
			
			Localidade eloPolo = new Localidade();
			eloPolo.setId(new Integer(form.getEloPolo()));
			
			filtro.setEloPolo(eloPolo);
			
		}
		
		// Localidade
		if (form.getLocalidade() != null && 
			!form.getLocalidade().equals("")) {
			
			Localidade localidade = new Localidade();
			localidade.setId(new Integer(form.getLocalidade()));
			
			filtro.setLocalidade(localidade);
			
		}
		
		// Setor Comercial
		if (form.getSetorComercial() != null && 
			!form.getSetorComercial().equals("")) {
			
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
					form.getSetorComercial()));
			
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.LOCALIDADE, 
					form.getLocalidade()));
			
			// Recupera Setor Comercial
			Collection colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
			SetorComercial setorComercial = 
				(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			filtro.setSetorComercial(setorComercial);
		}
		
		// Quadra
		if (form.getQuadra() != null && 
			!form.getQuadra().equals("")) {
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(
				new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, 
				form.getQuadra()));
			
			filtroQuadra.adicionarParametro(
				new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, 
				filtro.getSetorComercial().getId()));		
			
			// Recupera Quadra
			Collection colecaoQuadra = 
				this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
		
			Quadra quadra = 
				(Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
			
			filtro.setQuadra(quadra);
				
		}		
		
		// Tipo Categoria
		if (form.getTipoCategoria() != null && 
			!form.getTipoCategoria().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			CategoriaTipo tipoCategoria = new CategoriaTipo();
			tipoCategoria.setId(new Integer(form.getTipoCategoria()));
			
			filtro.setTipoCategoria(tipoCategoria);
			
		}	
		
		// Categoria
		if (form.getCategoria() != null && 
			form.getCategoria().length > 0 && 
			Fachada.getInstancia().pesquisarParametrosDoSistema().getIndicadorTarifaCategoria() == 1 ) {
			
			Collection<Integer> colecao = new ArrayList();
			
			LinkedHashMap<String,Collection<ConsumoFaixaCategoria>> linkedHashMapConsumoFaixaCategoria = 
				new LinkedHashMap<String,Collection<ConsumoFaixaCategoria>>();
			
			String[] array = form.getCategoria();
			for (int i = 0; i < array.length; i++) {
				
				Integer key = new Integer(array[i]);
				
				if (key.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					
					colecao.add(key);
					
					Collection<ConsumoFaixaCategoria> colecaoFaixa = (Collection<ConsumoFaixaCategoria>)
						form.getLinkedHashMapConsumoFaixaCategoria().get(""+key);
					
					linkedHashMapConsumoFaixaCategoria.put(key+"",colecaoFaixa);
				}
			}
			
			form.setLinkedHashMapConsumoFaixaCategoria(linkedHashMapConsumoFaixaCategoria);
			filtro.setColecaoCategoria(colecao);
			
		}
		
		Integer indicadorTarifa = Integer.parseInt( form.getIndicadorTarifaCategoria() );
		filtro.setIndicadorTarifaCategoria( indicadorTarifa.intValue() );
		
		// Sub categoria
		if (form.getSubcategoria() != null && 
			form.getSubcategoria().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getSubcategoria();
			for (int i = 0; i < array.length; i++) {
				
				Integer key = new Integer(array[i]);
				
				if (key.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(key);
				}
			}

			filtro.setColecaoSubcategoria(colecao);
		}
		
		//Faixa de Consumo por Categoria
		this.validarColecaoFaixaConsumoCategoria(form);
		filtro.setLinkedHashMapConsumoFaixaCategoria(form.getLinkedHashMapConsumoFaixaCategoria());
		
		// Tarifa
		if (form.getTarifa() != null && 
			form.getTarifa().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getTarifa();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoTarifa(colecao);
			
		}
		
		// Perfil do Imovel
		if (form.getPerfilImovel() != null && 
			form.getPerfilImovel().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getPerfilImovel();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoPerfilImovel(colecao);
			
		}	
		
		// Esfera de Poder
		if (form.getEsferaPoder() != null && 
			form.getEsferaPoder().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getEsferaPoder();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoEsferaPoder(colecao);
			
		}	
		
		// Situacao da Ligacao de agua
		if (form.getSituacaoLigacaoAgua() != null && 
			form.getSituacaoLigacaoAgua().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getSituacaoLigacaoAgua();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoSituacaoLigacaoAgua(colecao);
			
		}		
		
		// Indicador Consumo
		if (form.getConsumo() != null && 
			!form.getConsumo().equals("")) {
			
			filtro.setConsumo(new Short(form.getConsumo()));
		}

		// Indicador Poco
		if (form.getPoco() != null && 
			!form.getPoco().equals("")) {
			
			filtro.setPoco(new Short(form.getPoco()));
		}
		
		// Indicador Volume
		if (form.getVolumoFixoAgua() != null && 
			!form.getVolumoFixoAgua().equals("")) {
			
			filtro.setVolumoFixoAgua(new Short(form.getVolumoFixoAgua()));
		}		
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioHistogramaAguaEconomia relatorio = 
			new RelatorioHistogramaAguaEconomia(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarEmitirHistogramaAguaEconomiaHelper", filtro);
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
	/**
	 * Valida se a colecao possui faixa inicial(faixaInicio = 0)
	 * Verifica se as faixa estão sequenciadas
	 * Obs:A colecao já vem ordenada pela faixaInicio
	 * 
	 * @author Rafael Pinto
	 * @date 21/06/2007
	 *
	 * @param EmissaoHistogramaAguaEconomiaDadosInformarActionForm
	 */
	private void validarColecaoFaixaConsumoCategoria(EmissaoHistogramaAguaEconomiaDadosInformarActionForm form){
		
		LinkedHashMap<String,Collection<ConsumoFaixaCategoria>> linkedHashMapConsumoFaixaCategoria = 
			form.getLinkedHashMapConsumoFaixaCategoria();
		
		Iterator iteraChaves = linkedHashMapConsumoFaixaCategoria.keySet().iterator();
		while (iteraChaves.hasNext()) {
			
			String chave = (String) iteraChaves.next();
			
			Collection<ConsumoFaixaCategoria> colecaoFaixaConsumoCategoria = 
				(Collection<ConsumoFaixaCategoria>) linkedHashMapConsumoFaixaCategoria.get(chave);
		
			if(colecaoFaixaConsumoCategoria != null && !colecaoFaixaConsumoCategoria.isEmpty()){

				int faixaFimAnterior = 0;
				
				Iterator itera = colecaoFaixaConsumoCategoria.iterator();
				while (itera.hasNext()) {
					ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) itera.next();
					int faixaInicio = consumoFaixaCategoria.getNumeroFaixaInicio();
					
					/*if(faixaFimAnterior == 0 && faixaInicio != 0){
						throw new ActionServletException("atencao.consumo_faixa_primeira_zero");
					}*/
					
					if(faixaInicio != faixaFimAnterior){
						String[] msg = new String[3];
						
						msg[0] = ""+faixaInicio;
						msg[1] = ""+consumoFaixaCategoria.getNumeroFaixaFim();
						msg[2] = ""+faixaFimAnterior;
						
						throw new ActionServletException("atencao.consumo_faixa_intervalo_invalido",null,msg);
					}
					faixaFimAnterior = consumoFaixaCategoria.getNumeroFaixaFim()+1;				
				}
			}

		}
		
		

		
		
	}	
}
