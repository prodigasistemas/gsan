package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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

public class RelatorioManterAtividade extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterAtividade(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_ATIVIDADE);
	}
	
	@Deprecated
	public RelatorioManterAtividade() {
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

		FiltroAtividade filtroAtividade = (FiltroAtividade) getParametro("filtroAtividade");
		Atividade atividadeParametros = (Atividade) getParametro("atividadeParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterAtividadeBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroAtividade.setConsultaSemLimites(true);

		Collection<Atividade> colecaoAtividades = fachada.pesquisar(filtroAtividade,
				Atividade.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoAtividades != null && !colecaoAtividades.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (Atividade atividade : colecaoAtividades) {
				
				String ativoInativo = "";

				if ( atividade.getIndicadorUso().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ){
				ativoInativo = "Ativo";
				} else {
				ativoInativo = "Inativo";
				}

				relatorioBean = new RelatorioManterAtividadeBean(
						// Código
						atividade.getId().toString(), 
						
						// Descrição
						atividade.getDescricao(),
						
						// Descrição Abreviada
						atividade.getDescricaoAbreviada(),
						
						// Indicador Atividade Unica
						atividade.getIndicadorAtividadeUnica().intValue()== 1 ? "Sim":"Não" ,
						
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

		if (atividadeParametros.getId() != null) {
			parametros.put("id",
					atividadeParametros.getId().toString());
		} else {
			parametros.put("id", "");
		}

		parametros.put("descricao", atividadeParametros.getDescricao());

		parametros.put("descricaoAbreviada", atividadeParametros.getDescricaoAbreviada());
		
		String indicadorAtividadeUnica = "";
		
		if(atividadeParametros.getIndicadorAtividadeUnica() != null && !atividadeParametros.getIndicadorAtividadeUnica().equals("")){
			if (atividadeParametros.getIndicadorAtividadeUnica().equals(ConstantesSistema.SIM)) {
				indicadorAtividadeUnica = "Sim";
			} else if (atividadeParametros.getIndicadorAtividadeUnica().equals(ConstantesSistema.NAO)) {
				indicadorAtividadeUnica = "Não";
			} else if (atividadeParametros.getIndicadorAtividadeUnica().equals("")) {
				indicadorAtividadeUnica = "Todos";
			}
		}
	
		parametros.put("indicadorAtividadeUnica", indicadorAtividadeUnica);
		
		String indicadorUso = "";

		if (atividadeParametros.getIndicadorUso() != null
				&& !atividadeParametros.getIndicadorUso().equals("")) {
			if (atividadeParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (atividadeParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_ATIVIDADE, parametros,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterAtividade", this);
	}

}
