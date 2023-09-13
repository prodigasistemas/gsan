package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.IoUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SimularCalculoContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		ActionForward retorno = actionMapping
				.findForward("simularCalculoConta");

		
		SimularCalculoContaActionForm simularCalculoContaActionForm = (SimularCalculoContaActionForm) actionForm;

		
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		

		// Declaração de objetos
		String anoMesReferencia = null;
		Integer ligacaoAguaSituacaoID = null;
		Integer ligacaoEsgotoSituacaoID = null;
		Integer consumoTarifaID = null;
		BigDecimal percentualEsgoto = null;
		Short indicadorFaturamentoAgua = new Short("1");
		Short indicadorFaturamentoEsgoto = new Short("1");
		Integer consumoFaturadoAgua = null;
		Integer consumoFaturadoEsgoto = null;
		Integer faturamentoGrupoID = null;
		Collection colecaoFaturamentoAtividadeCronograma = null;
		FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = null;
		Date dataLeituraAnterior = null;
		Date dataLeituraAtual = null;

		// Verifica qual atributo do action form foi informado no JSP
		if (simularCalculoContaActionForm.getMesAnoReferencia() != null
				&& !simularCalculoContaActionForm.getMesAnoReferencia()
						.equalsIgnoreCase("")) {

			String mes = simularCalculoContaActionForm.getMesAnoReferencia()
					.substring(0, 2);
			String ano = simularCalculoContaActionForm.getMesAnoReferencia()
					.substring(3, 7);

			anoMesReferencia = Util.formatarMesAnoParaAnoMes(mes + ano);
		}

		if (simularCalculoContaActionForm.getLigacaoAguaSituacaoID() != null
				&& !simularCalculoContaActionForm.getLigacaoAguaSituacaoID()
						.equalsIgnoreCase("")) {
			ligacaoAguaSituacaoID = new Integer(simularCalculoContaActionForm
					.getLigacaoAguaSituacaoID());
		}

		if (simularCalculoContaActionForm.getLigacaoEsgotoSituacaoID() != null
				&& !simularCalculoContaActionForm.getLigacaoEsgotoSituacaoID()
						.equalsIgnoreCase("")) {
			ligacaoEsgotoSituacaoID = new Integer(simularCalculoContaActionForm
					.getLigacaoEsgotoSituacaoID());
		}

		if (simularCalculoContaActionForm.getConsumoFaturadoAgua() != null
				&& !simularCalculoContaActionForm.getConsumoFaturadoAgua()
						.equalsIgnoreCase("")) {
			consumoFaturadoAgua = new Integer(simularCalculoContaActionForm
					.getConsumoFaturadoAgua());
		}

		if (simularCalculoContaActionForm.getConsumoFaturadoEsgoto() != null
				&& !simularCalculoContaActionForm.getConsumoFaturadoEsgoto()
						.equalsIgnoreCase("")) {
			consumoFaturadoEsgoto = new Integer(simularCalculoContaActionForm
					.getConsumoFaturadoEsgoto());
		}

		if (simularCalculoContaActionForm.getConsumoTarifaID() != null
				&& !simularCalculoContaActionForm.getConsumoTarifaID()
						.equalsIgnoreCase("")) {
			consumoTarifaID = new Integer(simularCalculoContaActionForm
					.getConsumoTarifaID());
		}

		if (simularCalculoContaActionForm.getFaturamentoGrupoID() != null
				&& !simularCalculoContaActionForm.getFaturamentoGrupoID()
						.equalsIgnoreCase("")) {
			faturamentoGrupoID = new Integer(simularCalculoContaActionForm
					.getFaturamentoGrupoID());
		}

		if (simularCalculoContaActionForm.getPercentualEsgoto() != null
				&& !simularCalculoContaActionForm.getPercentualEsgoto()
						.equalsIgnoreCase("")) {
			percentualEsgoto = Util.formatarMoedaRealparaBigDecimal((simularCalculoContaActionForm
					.getPercentualEsgoto()));
		}

		// Criação do consumo tarifa
		Imovel imovel = new Imovel();
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		consumoTarifa.setId(consumoTarifaID);
		imovel.setConsumoTarifa(consumoTarifa);
		
		
		Collection colecaoCategoriaOUSubcategoria = null;
		Integer qtdEconnomia = null;
		int consumoMinimoLigacao = 0;
		
		if (sessao.getAttribute("colecaoCategoria") != null){
			
			colecaoCategoriaOUSubcategoria = (Collection) sessao.getAttribute("colecaoCategoria");
			
			qtdEconnomia = this.atualizarQuantidadeEconomiasCategoria(colecaoCategoriaOUSubcategoria, 
			httpServletRequest);
			
			//Obtém o consumo mínimo ligação por categoria
			consumoMinimoLigacao = fachada.obterConsumoMinimoLigacao(imovel,
			colecaoCategoriaOUSubcategoria);
		}
		else{
			
			colecaoCategoriaOUSubcategoria = (Collection) sessao.getAttribute("colecaoSubcategoria");
			
			qtdEconnomia = this.atualizarQuantidadeEconomiasSubcategoria(colecaoCategoriaOUSubcategoria, 
			httpServletRequest);
			
			//Obtém o consumo mínimo ligação por subcategoria
			consumoMinimoLigacao = fachada.obterConsumoMinimoLigacao(imovel,
			colecaoCategoriaOUSubcategoria);
		}


		//BUSCANDO O CRONOGRAMA ANTERIOR
		colecaoFaturamentoAtividadeCronograma = this.buscarFaturamentoAtividadeCronograma(
		Util.subtrairData(new Integer(anoMesReferencia)), faturamentoGrupoID, fachada);
		
		// Criação do filtro para faturamento atividade cronograma
		/*FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();

		// Seta o parâmetros do filtro de faturamento atividade cronograma
		filtroFaturamentoAtividadeCronograma
				.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_FATURAMENTO_GRUPO_ID,
						faturamentoGrupoID, ParametroSimples.CONECTOR_AND));
		filtroFaturamentoAtividadeCronograma
				.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA,
						Util.subtrairData(new Integer(anoMesReferencia)),
						ParametroSimples.CONECTOR_AND));
		filtroFaturamentoAtividadeCronograma
				.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID,
						FaturamentoAtividade.EFETUAR_LEITURA));

		// Pesquisa o faturamento atividade cronograma
		colecaoFaturamentoAtividadeCronograma = fachada.pesquisar(
				filtroFaturamentoAtividadeCronograma,
				FaturamentoAtividadeCronograma.class.getName());*/

		boolean dataHoraRealizacaoValidoAnterior = false;
		boolean dataHoraRealizacaoValidoAtual = false;

		// Verifica se a pesquisa retornou uma coleção de objetos
		if (colecaoFaturamentoAtividadeCronograma != null
				&& colecaoFaturamentoAtividadeCronograma.size() > 0) {

			faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util.retonarObjetoDeColecao(colecaoFaturamentoAtividadeCronograma);

			if (faturamentoAtividadeCronograma.getDataRealizacao() != null) {

				dataHoraRealizacaoValidoAnterior = true;
			}

			
		}

		if (!dataHoraRealizacaoValidoAnterior) {

			// Obtém ano e mês
			int ano = Util.obterAno(new Integer(anoMesReferencia));
			int mes = Util.obterMes(new Integer(anoMesReferencia));

			// Define a data de leitura anterior no primeiro dia do mês e ano de
			// referência
			dataLeituraAnterior = IoUtil.criarData(1, mes, ano);

			/*Calendar dataLeitura = GregorianCalendar.getInstance();
			dataLeitura.setTime(dataLeituraAnterior);
			// Define a data de leitura anterior no último dia do mês e ano de
			// referência
			int ultimoDia = dataLeitura.getActualMaximum(Calendar.DAY_OF_MONTH);

			dataLeitura.set(Calendar.DAY_OF_MONTH, ultimoDia);

			dataLeituraAtual = dataLeitura.getTime();*/

		} else {

			dataLeituraAnterior = faturamentoAtividadeCronograma
					.getDataRealizacao();
			/*Calendar dataAnterior = GregorianCalendar.getInstance();
			
			dataLeituraAtual = faturamentoAtividadeCronograma
					.getDataRealizacao();*/
		}
		

		//BUSCANDO O CRONOGRAMA ATUAL
		colecaoFaturamentoAtividadeCronograma = this.buscarFaturamentoAtividadeCronograma(
		new Integer(anoMesReferencia), faturamentoGrupoID, fachada);
		
		
		//	Seta o parâmetros do filtro de faturamento atividade cronograma
		/*filtroFaturamentoAtividadeCronograma.limparListaParametros();
		filtroFaturamentoAtividadeCronograma
				.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_FATURAMENTO_GRUPO_ID,
						faturamentoGrupoID, ParametroSimples.CONECTOR_AND));
		filtroFaturamentoAtividadeCronograma
				.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA,
						anoMesReferencia,
						ParametroSimples.CONECTOR_AND));
		filtroFaturamentoAtividadeCronograma
				.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID,
						FaturamentoAtividade.EFETUAR_LEITURA));

		// Pesquisa o faturamento atividade cronograma
		colecaoFaturamentoAtividadeCronograma = fachada.pesquisar(
				filtroFaturamentoAtividadeCronograma,
				FaturamentoAtividadeCronograma.class.getName());*/

		// Verifica se a pesquisa retornou uma coleção de objetos
		if (colecaoFaturamentoAtividadeCronograma != null
				&& colecaoFaturamentoAtividadeCronograma.size() > 0) {

			faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util.retonarObjetoDeColecao(colecaoFaturamentoAtividadeCronograma);

			if (faturamentoAtividadeCronograma.getDataRealizacao() != null) {

				dataHoraRealizacaoValidoAtual = true;
			}

			
		}

		if (!dataHoraRealizacaoValidoAtual) {

			// Obtém ano e mês
			int ano = Util.obterAno(new Integer(anoMesReferencia));
			int mes = Util.obterMes(new Integer(anoMesReferencia));

			// Define a data de leitura anterior no primeiro dia do mês e ano de
			// referência
			dataLeituraAtual = IoUtil.criarData(1, mes, ano);

			Calendar dataLeitura = GregorianCalendar.getInstance();
			dataLeitura.setTime(dataLeituraAtual);
			// Define a data de leitura anterior no último dia do mês e ano de
			// referência
			int ultimoDia = dataLeitura.getActualMaximum(Calendar.DAY_OF_MONTH);

			dataLeitura.set(Calendar.DAY_OF_MONTH, ultimoDia);

			dataLeituraAtual = dataLeitura.getTime();

		} else {

			dataLeituraAtual = faturamentoAtividadeCronograma
					.getDataRealizacao();
		}

		
		/*
		 * Colocado por Raphael Rossiter em 02/04/2007
		 * 
		 * [UC0157] - Simular Cálculo da Conta
		 * [FS0003] - Verificar Consumo Mínimo
		 */
		fachada.verificarConsumoFaturadoAgua(ligacaoAguaSituacaoID, consumoFaturadoAgua);
		
		/*
		 * Colocado por Raphael Rossiter em 02/04/2007
		 * 
		 * [UC0157] - Simular Cálculo da Conta
		 * [FS0004] - Verificar Volume Mínimo
		 */
		fachada.verificarConsumoFaturadoEsgoto(ligacaoEsgotoSituacaoID, consumoFaturadoEsgoto);


		/*
		 * Alterado por Raphael Rossiter em 15/01/2007
		 * OBJ - Validar os consumos de água, esgoto e o percentual de esgoto a partir
		 * do indicador de faturamento que está na situação das ligações de água e esgoto.
		 */
		
		//CONSUMO DE ÁGUA
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.ID, ligacaoAguaSituacaoID));

		Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao,
		LigacaoAguaSituacao.class.getName());
		
		LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util
		.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
		
		if (ligacaoAguaSituacao.getIndicadorFaturamentoSituacao() != null &&
			ligacaoAguaSituacao.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)){
		
			consumoFaturadoAgua = new Integer(0);
		}
		
		//CONSUMO DE ESGOTO
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
		FiltroLigacaoEsgotoSituacao.ID, ligacaoEsgotoSituacaoID));

		Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao,
		LigacaoEsgotoSituacao.class.getName());
		
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util
		.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);

		if (ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() != null &&
			ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)){
			
			consumoFaturadoEsgoto = new Integer(0);
		}
		
		//PERCENTUAL DE ESGOTO
		if ((ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() != null &&
			ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.INDICADOR_USO_ATIVO))
			&& percentualEsgoto == null) {
			
			throw new ActionServletException("atencao.informe.percentualEsgoto");
		}

		
		//==============================================================================================
		//SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Collection colecaoCalcularValoresAguaEsgotoHelper = null;
		
		//[UC0120] - Calcular Valores de Água e/ou Esgoto
			colecaoCalcularValoresAguaEsgotoHelper = fachada
					.calcularValoresAguaEsgoto(new Integer(anoMesReferencia),
							ligacaoAguaSituacaoID, ligacaoEsgotoSituacaoID,
							indicadorFaturamentoAgua, indicadorFaturamentoEsgoto,
							colecaoCategoriaOUSubcategoria, consumoFaturadoAgua,
							consumoFaturadoEsgoto, consumoMinimoLigacao,
							dataLeituraAnterior, dataLeituraAtual,
							percentualEsgoto, consumoTarifa.getId(), null, null);
			
		
		
		// Método reponsável pela totalização dos valores de água e esgoto por
		// categoria, a partir da coleção
		// retornada pelo [UC0120] - Calcular Valores de Água e/ou Esgoto
		Collection colecaoCalcularValoresAguaEsgotoHelperPorCategoria = fachada
				.calcularValoresAguaEsgotoTotalizandoPorCategoria(colecaoCalcularValoresAguaEsgotoHelper);

		httpServletRequest.setAttribute(
				"colecaoCalcularValoresAguaEsgotoHelper",
				colecaoCalcularValoresAguaEsgotoHelperPorCategoria);
		
		httpServletRequest.setAttribute(
				"tamanhoColecaoValores",
				colecaoCalcularValoresAguaEsgotoHelperPorCategoria.size());

		BigDecimal valorTotalCategorias = 
		this.obterValorTotalCategorias(colecaoCalcularValoresAguaEsgotoHelperPorCategoria, null);
		
		BigDecimal valorTotalAgua = 
		this.obterValorTotalCategorias(colecaoCalcularValoresAguaEsgotoHelperPorCategoria, 
		ConstantesSistema.CALCULAR_AGUA);
		
		BigDecimal valorTotalEsgoto = 
		this.obterValorTotalCategorias(colecaoCalcularValoresAguaEsgotoHelperPorCategoria, 
		ConstantesSistema.CALCULAR_ESGOTO);
		
		
		httpServletRequest.setAttribute("valorTotalAgua", valorTotalAgua);
		httpServletRequest.setAttribute("valorTotalEsgoto", valorTotalEsgoto);
		httpServletRequest.setAttribute("valorTotalCategorias",valorTotalCategorias);
		
		Integer consumoAgua = 0;
		Integer consumoEsgoto = 0;
		
		Iterator iteratorCalcularValoresAguaEsgotoHelperPorCategoria = 
				colecaoCalcularValoresAguaEsgotoHelperPorCategoria.iterator();
		while (iteratorCalcularValoresAguaEsgotoHelperPorCategoria.hasNext()) {
			CalcularValoresAguaEsgotoHelper objeto = (CalcularValoresAguaEsgotoHelper) 
					iteratorCalcularValoresAguaEsgotoHelperPorCategoria.next();
			if(objeto!=null) {
				
			if (objeto.getConsumoFaturadoAguaCategoria()!=null) {
                consumoAgua = consumoAgua + objeto.getConsumoFaturadoAguaCategoria();
                simularCalculoContaActionForm.setConsumoFaturadoAgua(consumoAgua.toString());
			} else if (qtdEconnomia != 0 && consumoFaturadoAgua != null) {
				consumoAgua = consumoFaturadoAgua / qtdEconnomia;
				consumoAgua = consumoAgua * qtdEconnomia;
				simularCalculoContaActionForm.setConsumoFaturadoAgua(consumoAgua.toString());
			}
			if (objeto.getConsumoFaturadoAguaCategoria()!=null) {
				consumoEsgoto = consumoEsgoto + objeto.getConsumoFaturadoAguaCategoria();
                simularCalculoContaActionForm.setConsumoFaturadoEsgoto(consumoEsgoto.toString());
			} else if (qtdEconnomia != 0 && consumoFaturadoEsgoto != null) {
				consumoEsgoto = consumoFaturadoEsgoto / qtdEconnomia;
				consumoEsgoto = consumoEsgoto * qtdEconnomia;
				simularCalculoContaActionForm.setConsumoFaturadoEsgoto(consumoEsgoto.toString());
			}
			}

		}
		
		/*
		 * Colocado por Raphael Rossiter em 14/03/2007
		 * Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
//		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
		
		httpServletRequest.setAttribute("indicadorTarifaCategoria", sistemaParametro.getIndicadorTarifaCategoria().toString());
		
		
		return retorno;

	}
	
	
	/**
	 * Obtém o valor total para todas as categorias
	 *
	 * @author Raphael Rossiter
	 * @date 27/03/2006
	 *
	 * @param colecaoCalcularValoresAguaEsgotoHelperPorCategoria
	 */
	private BigDecimal obterValorTotalCategorias(Collection<CalcularValoresAguaEsgotoHelper> 
		colecaoCalcularValoresAguaEsgotoHelperPorCategoria, String totalizacao){
		
		BigDecimal retorno = new BigDecimal("0");
		
		Iterator iteratorCalcularValoresAguaEsgotoHelperPorCategoria = 
		colecaoCalcularValoresAguaEsgotoHelperPorCategoria.iterator();
		
		while (iteratorCalcularValoresAguaEsgotoHelperPorCategoria.hasNext()){
			
			CalcularValoresAguaEsgotoHelper objeto = (CalcularValoresAguaEsgotoHelper) 
			iteratorCalcularValoresAguaEsgotoHelperPorCategoria.next();
			
			if (totalizacao == null && objeto.getValorTotalCategoria() != null){
				retorno = retorno.add(objeto.getValorTotalCategoria());
			}
			else if ((totalizacao != null && totalizacao.equals(ConstantesSistema.CALCULAR_AGUA)) &&
					objeto.getValorFaturadoAguaCategoria() != null){
				retorno = retorno.add(objeto.getValorFaturadoAguaCategoria());
			}
			else if ((totalizacao != null && totalizacao.equals(ConstantesSistema.CALCULAR_ESGOTO)) &&
					objeto.getValorFaturadoEsgotoCategoria() != null){
				retorno = retorno.add(objeto.getValorFaturadoEsgotoCategoria());
			}
		}
		
		
		return retorno;
	}
	
	
	
	private Integer atualizarQuantidadeEconomiasCategoria(Collection colecaoCategorias, HttpServletRequest httpServletRequest){
		
		/*
		 * Atualizando a quantidade de economias por categoria de acordo com o
		 * informado pelo usuário
		 */ 
		
		Integer qtdEconnomia = 0;
		
		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()){
			
			Iterator colecaoCategoriaIt = colecaoCategorias.iterator();
			Categoria categoria;
			Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
			String qtdPorEconomia;
			
			while (colecaoCategoriaIt.hasNext()) {
				categoria = (Categoria) colecaoCategoriaIt.next();

				if (requestMap.get("categoria" + categoria.getId().intValue()) != null) {

					qtdPorEconomia = (requestMap.get("categoria"
							+ categoria.getId().intValue()))[0];

					if (qtdPorEconomia == null
							|| qtdPorEconomia.equalsIgnoreCase("")) {

						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"Quantidade de economias");
					}

					categoria.setQuantidadeEconomiasCategoria(new Integer(
							qtdPorEconomia));
					
					qtdEconnomia = Util.somaInteiros(qtdEconnomia,new Integer(qtdPorEconomia));
				}
			}
		}
		
		return qtdEconnomia;
	}
	
	
	
	private Integer atualizarQuantidadeEconomiasSubcategoria(Collection colecaoSubcategorias, HttpServletRequest httpServletRequest){
		
		/*
		 * Atualizando a quantidade de economias por subcategoria de acordo com o
		 * informado pelo usuário
		 */ 
		
		Integer qtdEconnomia = 0;
		
		if (colecaoSubcategorias != null && !colecaoSubcategorias.isEmpty()){
			
			Iterator colecaoSubcategoriaIt = colecaoSubcategorias.iterator();
			Subcategoria subcategoria;
			Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
			String qtdPorEconomia;
			
			while (colecaoSubcategoriaIt.hasNext()) {
				subcategoria = (Subcategoria) colecaoSubcategoriaIt.next();

				if (requestMap.get("subcategoria" + subcategoria.getId().intValue()) != null) {

					qtdPorEconomia = (requestMap.get("subcategoria"
							+ subcategoria.getId().intValue()))[0];

					if (qtdPorEconomia == null
							|| qtdPorEconomia.equalsIgnoreCase("")) {

						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"Quantidade de economias");
					}

					subcategoria.setQuantidadeEconomias(new Integer(
							qtdPorEconomia));
					
					qtdEconnomia = Util.somaInteiros(qtdEconnomia,new Integer(qtdPorEconomia));
				}
			}
		}
		
		return qtdEconnomia;
	}
	
	
	public Collection buscarFaturamentoAtividadeCronograma(Integer anoMesReferencia, Integer 
			faturamentoGrupoID, Fachada fachada){
		
		Collection colecaoFaturamentoAtividadeCronograma = null;
		
		if (faturamentoGrupoID != null && 
			!faturamentoGrupoID.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			//Criação do filtro para faturamento atividade cronograma
			FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();

			// Seta o parâmetros do filtro de faturamento atividade cronograma
			filtroFaturamentoAtividadeCronograma
			.adicionarParametro(new ParametroSimples(
			FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_FATURAMENTO_GRUPO_ID,
			faturamentoGrupoID, ParametroSimples.CONECTOR_AND));
			
			filtroFaturamentoAtividadeCronograma.adicionarParametro(new ParametroSimples(
			FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA,
			anoMesReferencia, ParametroSimples.CONECTOR_AND));
			
			filtroFaturamentoAtividadeCronograma.adicionarParametro(new ParametroSimples(
			FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID,
			FaturamentoAtividade.EFETUAR_LEITURA));

			// Pesquisa o faturamento atividade cronograma
			colecaoFaturamentoAtividadeCronograma = fachada.pesquisar(
					filtroFaturamentoAtividadeCronograma,
					FaturamentoAtividadeCronograma.class.getName());
		}
		
		return colecaoFaturamentoAtividadeCronograma;
	}
	
}
