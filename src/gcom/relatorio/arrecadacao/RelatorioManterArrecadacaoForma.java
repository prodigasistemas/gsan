package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
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

public class RelatorioManterArrecadacaoForma extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterArrecadacaoForma(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_MOTIVO_CORTE);
	}
	
	@Deprecated
	public RelatorioManterArrecadacaoForma() {
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

		FiltroArrecadacaoForma filtroArrecadacaoForma = (FiltroArrecadacaoForma) getParametro("filtroArrecadacaoForma");
		ArrecadacaoForma arrecadacaoFormaParametros = (ArrecadacaoForma) getParametro("arrecadacaoFormaParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterArrecadacaoFormaBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroArrecadacaoForma.setConsultaSemLimites(true);

		Collection<ArrecadacaoForma> colecaoArrecadacaoFormas = fachada.pesquisar(filtroArrecadacaoForma,
				ArrecadacaoForma.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoArrecadacaoFormas != null && !colecaoArrecadacaoFormas.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (ArrecadacaoForma arrecadacaoForma : colecaoArrecadacaoFormas) {

				relatorioBean = new RelatorioManterArrecadacaoFormaBean(
						// Identificador
						arrecadacaoForma.getId().toString(), 
						
						// Código Arrecadacao Forma
						arrecadacaoForma.getCodigoArrecadacaoForma(), 
						
						// Descrição
						arrecadacaoForma.getDescricao()); 
						
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

		if (arrecadacaoFormaParametros.getId() != null) {
			parametros.put("idArrecadacaoForma",
					arrecadacaoFormaParametros.getId().toString());
		} else {
			parametros.put("idArrecadacaoForma", "");
		}
		
		parametros.put("codigoArrecadacaoForma", arrecadacaoFormaParametros.getCodigoArrecadacaoForma());

		parametros.put("descricao", arrecadacaoFormaParametros.getDescricao());

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_ARRECADACAO_FORMA, parametros,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterArrecadacaoForma", this);
	}

}
