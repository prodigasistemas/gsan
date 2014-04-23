package gcom.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterTipoDebito extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterTipoDebito(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_TIPO_DEBITO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterTipoDebito() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroDebitoTipo filtroDebitoTipo = (FiltroDebitoTipo) getParametro("filtroDebitoTipo");
		
		BigDecimal valorLimiteInicialparametros = (BigDecimal) getParametro("valorLimiteDebitoInicial");
		BigDecimal valorLimiteFinalparametros = (BigDecimal) getParametro("valorLimiteDebitoFinal");
		String valorSugeridoParametros = (String) getParametro("valorSugerido");
		DebitoTipo debitoTipoParametros = (DebitoTipo) getParametro("debitoTipoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterTipoDebitoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
				DebitoTipo.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator debitoTipoIterator = colecaoDebitoTipo.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (debitoTipoIterator.hasNext()) {

				DebitoTipo debitoTipo = (DebitoTipo) debitoTipoIterator.next();

				String indicadorGeracaoAutomatica = "";
				
				if(debitoTipo.getIndicadorGeracaoAutomatica().equals(ConstantesSistema.SIM)){
					indicadorGeracaoAutomatica = "SIM";
				} else {
					indicadorGeracaoAutomatica = "NÃO";
				}
			
				String indicadorGeracaoConta = "";
				
				if(debitoTipo.getIndicadorGeracaoConta().equals(ConstantesSistema.SIM)){
					indicadorGeracaoConta = "SIM";
				} else {
					indicadorGeracaoConta = "NÃO";
				}
				

				String lancamentoItemContabil = "";
				
				if (debitoTipo.getLancamentoItemContabil() != null) {
					lancamentoItemContabil = debitoTipo.getLancamentoItemContabil().getDescricao();
				}
				
				String financiamentoTipo = "";
				
				if (debitoTipo.getFinanciamentoTipo() != null) {
					financiamentoTipo = debitoTipo.getFinanciamentoTipo().getDescricao();
				}
				
				String valorSugerido = "";
				
				if (debitoTipo.getValorSugerido() != null) {
					valorSugerido = debitoTipo.getValorSugerido().toString();
				}
				
				relatorioBean = new RelatorioManterTipoDebitoBean(
						//ID
						debitoTipo.getId().toString(), 
						
						//Descrição
						debitoTipo.getDescricao(), 
						
						//Descrição Abreviada
						debitoTipo.getDescricaoAbreviada(),
						
						//Tipo de Lançamento do Tipo Contabil
						lancamentoItemContabil,

						//Tipo de Financiamento
						financiamentoTipo,
						
						//Indicador Geracao do débito Automatica
						indicadorGeracaoAutomatica,
						
						//Indicador Geracao do Débito em Conta
						indicadorGeracaoConta,
						
						//Indicador de Uso	
						debitoTipo.getIndicadorUso().toString(),
						
						//Valor limite
						debitoTipo.getValorLimite().toString(),
						
						//Valor Sugerido
						valorSugerido);
				
						
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("id",
				debitoTipoParametros.getId() == null ? "" : ""
						+ debitoTipoParametros.getId());

		parametros.put("descricao", debitoTipoParametros.getDescricao());
		
		//Descricao Abreviada
		if (debitoTipoParametros.getDescricaoAbreviada() != null 
				&& !debitoTipoParametros.getDescricaoAbreviada().equals("")){
			parametros.put("descricaoAbreviada", debitoTipoParametros.getDescricaoAbreviada());
		} else {
			parametros.put("descricaoAbreviada", "");
		}
		
		//valor limite debito inicial
		if(valorLimiteInicialparametros != null){
		parametros.put("valorLimiteDebitoInicial", valorLimiteInicialparametros);
		}
		
		//valor limite debito inicial
		if(valorLimiteFinalparametros != null){
		parametros.put("valorLimiteDebitoFinal", valorLimiteFinalparametros);
		}
		String indicadorUso = "";

		if (debitoTipoParametros.getIndicadorUso() != null
				&& !debitoTipoParametros.getIndicadorUso().equals("")) {
			if (debitoTipoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (debitoTipoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);
		
		String indicadorGeracaoDebitoAutomatica = "";

		if (debitoTipoParametros.getIndicadorGeracaoAutomatica() != null
				&& !debitoTipoParametros.getIndicadorGeracaoAutomatica().equals("")) {
			if (debitoTipoParametros.getIndicadorGeracaoAutomatica().equals(new Short("1"))) {
				indicadorGeracaoDebitoAutomatica = "Sim";
			} else if (debitoTipoParametros.getIndicadorGeracaoAutomatica().equals(new Short("2"))) {
				indicadorGeracaoDebitoAutomatica = "Não";
			}
		}
		parametros.put("indicadorGeracaoDebitoAutomatica", indicadorGeracaoDebitoAutomatica);
		
		String indicadorGeracaoDebitoConta = "";

		if (debitoTipoParametros.getIndicadorGeracaoConta() != null
				&& !debitoTipoParametros.getIndicadorGeracaoConta().equals("")) {
			if (debitoTipoParametros.getIndicadorGeracaoConta().equals(new Short("1"))) {
				indicadorGeracaoDebitoConta = "Sim";
			} else if (debitoTipoParametros.getIndicadorGeracaoConta().equals(new Short("2"))) {
				indicadorGeracaoDebitoConta = "Não";
			}
		}
		parametros.put("indicadorGeracaoDebitoConta", indicadorGeracaoDebitoConta);
		
		//Financiamento Tipo
		if (debitoTipoParametros.getFinanciamentoTipo() != null) {
			parametros.put("financiamentoTipo", debitoTipoParametros.getFinanciamentoTipo().getDescricao());
		} else {
			parametros.put("financiamentoTipo", "");	
		}
		
		//Lancamento Item Contabil
		if (debitoTipoParametros.getLancamentoItemContabil() != null) {
			parametros.put("lancamentoItemContabil", debitoTipoParametros.getLancamentoItemContabil().getDescricao());
		} else {
			parametros.put("lancamentoItemContabil", "");	
		}
		//valor sugerido
		String valorSugerido = "";

		if (valorSugeridoParametros!= null
				&& !valorSugeridoParametros.equals("")) {
			if (valorSugeridoParametros.equals("1")) {
				valorSugerido = "Sim";
			} else if (valorSugeridoParametros.equals("2")) {
				valorSugerido = "Não";
			}
		}

		parametros.put("valorSugerido", valorSugerido);
		
		
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_TIPO_DEBITO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLocalidade) getParametro("filtroLocalidade"),
//				Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterTipoDebito", this);
	}

}
