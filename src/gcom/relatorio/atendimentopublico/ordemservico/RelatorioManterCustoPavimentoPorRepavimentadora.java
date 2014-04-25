package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.atendimentopublico.bean.RelatorioManterCustoPavimentoPorRepavimentadoraBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GSAN
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Hugo Leonardo
 * @version 1.0
 */

public class RelatorioManterCustoPavimentoPorRepavimentadora extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioManterCustoPavimentoPorRepavimentadora(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CUSTO_PAVIMENTO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterCustoPavimentoPorRepavimentadora() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Collection<UnidadeRepavimentadoraCustoPavimentoRua> colecaoCustoPavimentoRua = 
			(Collection<UnidadeRepavimentadoraCustoPavimentoRua>) getParametro("colecaoCustoPavimentoRua");
		
		Collection<UnidadeRepavimentadoraCustoPavimentoCalcada> colecaoCustoPavimentoCalcada = 
			(Collection<UnidadeRepavimentadoraCustoPavimentoCalcada>) getParametro("colecaoCustoPavimentoCalcada");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String unidadeRepavimentadora = (String) getParametro("unidadeRepavimentadora");
		String tipoPavimentoRua = (String) getParametro("pavimentoRua");
		String vigenciaRua = (String) getParametro("vigenciaRua");
		String situacaoVigenciaRua = (String) getParametro("situacaoVigenciaRua");
		String tipoPavimentoCalcada = (String) getParametro("pavimentoCalcada");
		String vigenciaCalcada = (String) getParametro("vigenciaCalcada");
		String situacaoVigenciaCalcada = (String) getParametro("situacaoVigenciaCalcada");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterCustoPavimentoPorRepavimentadoraBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();
		
		String dtInicial = "";
		
		String dtFinal = "";

		//Rua
		if (colecaoCustoPavimentoRua != null && !colecaoCustoPavimentoRua.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator ruaIterator = colecaoCustoPavimentoRua.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (ruaIterator.hasNext()) {

				UnidadeRepavimentadoraCustoPavimentoRua pavimentoRua = 
					(UnidadeRepavimentadoraCustoPavimentoRua) ruaIterator.next();

				dtInicial = Util.formatarData(pavimentoRua.getDataVigenciaInicial());
				
				dtFinal = Util.formatarData(pavimentoRua.getDataVigenciaFinal());
				
				relatorioBean = new RelatorioManterCustoPavimentoPorRepavimentadoraBean(
						pavimentoRua.getPavimentoRua().getId().toString(), 
						pavimentoRua.getPavimentoRua().getDescricao(), 
						pavimentoRua.getValorPavimento(),
						dtInicial, 
						dtFinal);
				
				// Para os Pavimentos de Rua
				relatorioBean.setTipo("1");

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}
		
		// Calçada
		if (colecaoCustoPavimentoCalcada != null && !colecaoCustoPavimentoCalcada.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator calcadaIterator = colecaoCustoPavimentoCalcada.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (calcadaIterator.hasNext()) {

				UnidadeRepavimentadoraCustoPavimentoCalcada pavimentoCalcada = 
					(UnidadeRepavimentadoraCustoPavimentoCalcada) calcadaIterator.next();

				dtInicial = Util.formatarData(pavimentoCalcada.getDataVigenciaInicial());
				
				dtFinal = Util.formatarData(pavimentoCalcada.getDataVigenciaFinal());
				
				relatorioBean = new RelatorioManterCustoPavimentoPorRepavimentadoraBean(
						pavimentoCalcada.getPavimentoCalcada().getId().toString(), 
						pavimentoCalcada.getPavimentoCalcada().getDescricao(), 
						pavimentoCalcada.getValorPavimento(),
						dtInicial, 
						dtFinal);
				
				// Para os Pavimentos de Calçada
				relatorioBean.setTipo("2");

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

		parametros.put("unidadeRepavimentadora", unidadeRepavimentadora);
		parametros.put("tipoPavimentoRua", tipoPavimentoRua);
		parametros.put("vigenciaRua", vigenciaRua);
		parametros.put("situacaoVigenciaRua", situacaoVigenciaRua);
		parametros.put("tipoPavimentoCalcada", tipoPavimentoCalcada);
		parametros.put("vigenciaCalcada", vigenciaCalcada);
		parametros.put("situacaoVigenciaCalcada", situacaoVigenciaCalcada);

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CUSTO_PAVIMENTO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_CUSTO_PAVIMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = 10;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterCustoPavimentoPorRepavimentadora", this);
	}

}
