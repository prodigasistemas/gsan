package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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

public class RelatorioManterLigacaoAguaSituacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterLigacaoAguaSituacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_LIGACAO_AGUA_SITUACAO);
	}
	
	@Deprecated
	public RelatorioManterLigacaoAguaSituacao() {
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

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = (FiltroLigacaoAguaSituacao) getParametro("filtroLigacaoAguaSituacao");
		LigacaoAguaSituacao ligacaoAguaSituacaoParametros = (LigacaoAguaSituacao) getParametro("ligacaoAguaSituacaoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterLigacaoAguaSituacaoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroLigacaoAguaSituacao.setConsultaSemLimites(true);

		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao,
				LigacaoAguaSituacao.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoLigacaoAguaSituacao != null && !colecaoLigacaoAguaSituacao.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (LigacaoAguaSituacao ligacaoAguaSituacao : colecaoLigacaoAguaSituacao) {
				
				
				String ativoInativo = "";

				if ( ligacaoAguaSituacao.getIndicadorUso().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ){
					ativoInativo = "Ativo";
				} else {
					ativoInativo = "Inativo";
				}
								
				String indicadorFaturamento = "";
				
				if (ligacaoAguaSituacao.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM)) {
					indicadorFaturamento = "SIM";
				} else {
					indicadorFaturamento = "NÃO";
				}

				String indicadorExistenciaRede = "";
				
				if (ligacaoAguaSituacao.getIndicadorExistenciaRede().equals(ConstantesSistema.SIM)) {
					indicadorExistenciaRede = "SIM";
				} else {
					indicadorExistenciaRede = "NÃO";
				}
				
				String indicadorExistenciaLigacao = "";
				
				if (ligacaoAguaSituacao.getIndicadorExistenciaLigacao().equals(ConstantesSistema.SIM)) {
					indicadorExistenciaLigacao = "SIM";
				} else {
					indicadorExistenciaLigacao = "NÃO";
				}
				
				relatorioBean = new RelatorioManterLigacaoAguaSituacaoBean(
						// Código
						ligacaoAguaSituacao.getId().toString(), 
						
						// Descrição
						ligacaoAguaSituacao.getDescricao(), 
						
						// Descrição Abeviada
						ligacaoAguaSituacao.getDescricaoAbreviado(),
						
						// Consumo Minimo
						(Util.agruparNumeroEmMilhares(ligacaoAguaSituacao.getConsumoMinimoFaturamento())).toString(),
						
						// Indicador Faturamento
						indicadorFaturamento,
						
						// Indicador Existencia Rede
						indicadorExistenciaRede,	
						
						// Indicador Existencia Ligacao
						indicadorExistenciaLigacao,
						
						ligacaoAguaSituacao.getIndicadorAguaAtiva().toString(),
						
						ligacaoAguaSituacao.getIndicadorAguaDesligada().toString(),
						
						ligacaoAguaSituacao.getIndicadorAguaCadastrada().toString(),
						
						ligacaoAguaSituacao.getIndicadorAnalizeAgua().toString(), 
						
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

		if (ligacaoAguaSituacaoParametros.getId() != null) {
			parametros.put("id",
					ligacaoAguaSituacaoParametros.getId().toString());
		} else {
			parametros.put("id", "");
		}

		parametros.put("descricao", ligacaoAguaSituacaoParametros.getDescricao());
		
		parametros.put("descricaoAbreviada", ligacaoAguaSituacaoParametros.getDescricaoAbreviado());
		
		parametros.put("consumoMinimo", ligacaoAguaSituacaoParametros.getConsumoMinimoFaturamento());
		
		String indicadorFaturamentoSituacao = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM)) {
			indicadorFaturamentoSituacao = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.NAO)) {
			indicadorFaturamentoSituacao = "Não";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.TODOS)) {
			indicadorFaturamentoSituacao = "Todos";
		}
		
		parametros.put("indicadorFaturamentoSituacao", indicadorFaturamentoSituacao);
		
		String indicadorExistenciaRede = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaRede().equals(ConstantesSistema.SIM)) {
			indicadorExistenciaRede = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaRede().equals(ConstantesSistema.NAO)) {
			indicadorExistenciaRede = "Não";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaRede().equals(ConstantesSistema.TODOS)) {
			indicadorExistenciaRede = "Todos";
		}
		
		parametros.put("indicadorExistenciaRede", indicadorExistenciaRede);
		
		
		String indicadorExistenciaLigacao = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaLigacao().equals(ConstantesSistema.SIM)) {
			indicadorExistenciaLigacao = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaLigacao().equals(ConstantesSistema.NAO)) {
			indicadorExistenciaLigacao = "Não";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorExistenciaLigacao().equals(ConstantesSistema.TODOS)) {
			indicadorExistenciaLigacao = "Todos";
		}
		
		parametros.put("indicadorExistenciaLigacao", indicadorExistenciaLigacao);
		
		
		String indicadorAguaAtiva = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorAguaAtiva().equals(ConstantesSistema.SIM)) {
			indicadorAguaAtiva = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaAtiva().equals(ConstantesSistema.NAO)) {
			indicadorAguaAtiva = "Não";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaAtiva().equals(ConstantesSistema.TODOS)) {
			indicadorAguaAtiva = "Todos";
		}
		
		parametros.put("indicadorAguaAtiva", indicadorAguaAtiva);
		
		
		String indicadorAguaDesligada = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorAguaDesligada().equals(ConstantesSistema.SIM)) {
			indicadorAguaDesligada = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaDesligada().equals(ConstantesSistema.NAO)) {
			indicadorAguaDesligada = "Não";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaDesligada().equals(ConstantesSistema.TODOS)) {
			indicadorAguaDesligada = "Todos";
		}
		
		parametros.put("indicadorAguaDesligada", indicadorAguaDesligada);
		
		
		String indicadorAguaCadastrada = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.SIM)) {
			indicadorAguaCadastrada = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.NAO)) {
			indicadorAguaCadastrada = "Não";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.TODOS)) {
			indicadorAguaCadastrada = "Todos";
		}
		
		parametros.put("indicadorAguaCadastrada", indicadorAguaCadastrada);
		
		
		String indicadorAnalizeAgua = "";
		
		if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.SIM)) {
			indicadorAnalizeAgua = "Sim";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.NAO)) {
			indicadorAnalizeAgua = "Não";
		} else if (ligacaoAguaSituacaoParametros.getIndicadorAguaCadastrada().equals(ConstantesSistema.TODOS)) {
			indicadorAnalizeAgua = "Todos";
		}
		
		parametros.put("indicadorAnalizeAgua", indicadorAnalizeAgua);
		

		String indicadorUso = "";

		if (ligacaoAguaSituacaoParametros.getIndicadorUso() != null
				&& !ligacaoAguaSituacaoParametros.getIndicadorUso().equals("")) {
			if (ligacaoAguaSituacaoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (ligacaoAguaSituacaoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_LIGACAO_AGUA_SITUACAO, parametros,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterLigacaoAguaSituacao", this);
	}

}
