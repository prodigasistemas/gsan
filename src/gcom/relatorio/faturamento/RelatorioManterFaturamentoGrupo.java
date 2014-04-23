package gcom.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterFaturamentoGrupo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterFaturamentoGrupo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_FATURAMENTO_GRUPO);
	}
	
	@Deprecated
	public RelatorioManterFaturamentoGrupo() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param localidades
	 *            Description of the Parameter
	 * @param localidadeParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = (FiltroFaturamentoGrupo) getParametro("filtroFaturamentoGrupo");
		FaturamentoGrupo faturamentoGrupoParametros = (FaturamentoGrupo) getParametro("faturamentoGrupoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterFaturamentoGrupoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroFaturamentoGrupo.setConsultaSemLimites(true);

		Collection<FaturamentoGrupo> colecaoFaturamentoGrupos = fachada.pesquisar(filtroFaturamentoGrupo,
				FaturamentoGrupo.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoFaturamentoGrupos != null && !colecaoFaturamentoGrupos.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (FaturamentoGrupo faturamentoGrupo : colecaoFaturamentoGrupos) {

				
				String ativoInativo = "";

				if ( faturamentoGrupo.getIndicadorUso().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ){
				ativoInativo = "Ativo";
				} else {
				ativoInativo = "Inativo";
				}
				
						
				relatorioBean = new RelatorioManterFaturamentoGrupoBean(
						// Código
						faturamentoGrupo.getId().toString(), 
						
						// Descrição
						faturamentoGrupo.getDescricao(), 
						
						// Descrição Abreviada
						faturamentoGrupo.getDescricaoAbreviada(),
						
						// Ano/Mes Referencia
						Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia().toString()),
						
						// Dia Vencimento
						faturamentoGrupo.getDiaVencimento().toString(),
						
						// Indicador Vencimento
						faturamentoGrupo.getIndicadorVencimentoMesFatura().toString(),
						
						// Indicador de Uso
						ativoInativo);

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

		if (faturamentoGrupoParametros.getId() != null) {
			parametros.put("id",
					faturamentoGrupoParametros.getId().toString());
		} else {
			parametros.put("id", "");
		}
		if (faturamentoGrupoParametros.getAnoMesReferencia() != null){
			parametros.put("anoMesReferencia", Util.formatarAnoMesParaMesAno(faturamentoGrupoParametros.getAnoMesReferencia().toString()));
		}else{
			parametros.put("anoMesReferencia","");
		}
		
		parametros.put("descricao", faturamentoGrupoParametros.getDescricao());
		
		parametros.put("descricaoAbreviada", faturamentoGrupoParametros.getDescricaoAbreviada());
		
		if(faturamentoGrupoParametros.getDiaVencimento() != null){
			parametros.put("diaVencimento", faturamentoGrupoParametros.getDiaVencimento().toString());
		} else {
			parametros.put("diaVencimento", "");
		}
		
		String indicadorUso = "";

		if (faturamentoGrupoParametros.getIndicadorUso() != null
				&& !faturamentoGrupoParametros.getIndicadorUso().equals("")) {
			if (faturamentoGrupoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (faturamentoGrupoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		
		String indicadorVencimentoMesFatura = "";

		if (faturamentoGrupoParametros.getIndicadorVencimentoMesFatura() != null
				&& !faturamentoGrupoParametros.getIndicadorVencimentoMesFatura().equals("")) {
			if (faturamentoGrupoParametros.getIndicadorVencimentoMesFatura().equals(new Short("1"))) {
				indicadorVencimentoMesFatura = "Sim";
			} else if (faturamentoGrupoParametros.getIndicadorVencimentoMesFatura().equals(new Short("2"))) {
				indicadorVencimentoMesFatura = "Não";
			}
		}

		parametros.put("indicadorVencimentoMesFatura", indicadorVencimentoMesFatura);
		
		
		
		
		
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_FATURAMENTO_GRUPO, parametros,
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

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterFaturamentoGrupo", this);
	}

}
