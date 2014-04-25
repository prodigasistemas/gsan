package gcom.relatorio.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
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

public class RelatorioManterConsumoAnormalidade extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterConsumoAnormalidade(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_ANORMALIDADE_CONSUMO);
	}
	
	@Deprecated
	public RelatorioManterConsumoAnormalidade() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param atividades
	 *            Description of the Parameter
	 * @param atividadeParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroConsumoAnormalidade filtroConsumoAnormalidade = (FiltroConsumoAnormalidade) getParametro("filtroConsumoAnormalidade");
		ConsumoAnormalidade consumoAnormalidadeParametros = (ConsumoAnormalidade) getParametro("consumoAnormalidadeParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterConsumoAnormalidadeBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroConsumoAnormalidade.setConsultaSemLimites(true);

		Collection<ConsumoAnormalidade> colecaoConsumoAnormalidades = fachada.pesquisar(filtroConsumoAnormalidade,
				ConsumoAnormalidade.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoConsumoAnormalidades != null && !colecaoConsumoAnormalidades.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (ConsumoAnormalidade consumoAnormalidade : colecaoConsumoAnormalidades) {
				
				String ativoInativo = "";

				if ( consumoAnormalidade.getIndicadorUso().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ){
				ativoInativo = "Ativo";
				} else {
				ativoInativo = "Inativo";
				}

				relatorioBean = new RelatorioManterConsumoAnormalidadeBean(
						// Código
						consumoAnormalidade.getId().toString(), 
						
						// Descrição
						consumoAnormalidade.getDescricao(),
						
						// Descrição Abreviada
						consumoAnormalidade.getDescricaoAbreviada(),
						
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

		if (consumoAnormalidadeParametros.getId() != null) {
			parametros.put("id",
					consumoAnormalidadeParametros.getId().toString());
		} else {
			parametros.put("id", "");
		}

		parametros.put("descricao", consumoAnormalidadeParametros.getDescricao());

		parametros.put("descricaoAbreviada", consumoAnormalidadeParametros.getDescricaoAbreviada());
		
		String indicadorUso = "";

		if (consumoAnormalidadeParametros.getIndicadorUso() != null
				&& !consumoAnormalidadeParametros.getIndicadorUso().equals("")) {
			if (consumoAnormalidadeParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (consumoAnormalidadeParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_ANORMALIDADE_CONSUMO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_ATIVIDADE,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterConsumoAnormalidade", this);
	}

}
