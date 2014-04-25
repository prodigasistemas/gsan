package gcom.relatorio.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterCobrancaGrupo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterCobrancaGrupo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COBRANCA_GRUPO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterCobrancaGrupo() {
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

		FiltroCobrancaGrupo filtroCobrancaGrupo = (FiltroCobrancaGrupo) getParametro("filtroCobrancaGrupo");
		CobrancaGrupo cobrancaGrupoParametros = (CobrancaGrupo) getParametro("cobrancaGrupoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterCobrancaGrupoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoCobrancaGrupo = fachada.pesquisar(filtroCobrancaGrupo,
				CobrancaGrupo.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoCobrancaGrupo != null && !colecaoCobrancaGrupo.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator cobrancaGrupoIterator = colecaoCobrancaGrupo.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (cobrancaGrupoIterator.hasNext()) {
				CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) cobrancaGrupoIterator.next();
				
				//ID
				String id = "";
				if ( cobrancaGrupo.getId() != null && !cobrancaGrupo.getId().equals("") ) {
					id = "" + cobrancaGrupo.getId();
				}
				
				//DESCRICAO
				String descricao = "";
				if ( cobrancaGrupo.getDescricao() != null && !cobrancaGrupo.getDescricao().equals("") ) {
					descricao = cobrancaGrupo.getDescricao();
				}
				
				//DESCRICAO ABREVIADA
				String descricaoAbreviada = "";
				if ( cobrancaGrupo.getDescricaoAbreviada() != null && !cobrancaGrupo.getDescricaoAbreviada().equals("") ) {
					descricaoAbreviada = cobrancaGrupo.getDescricaoAbreviada();
				}
				
				//ANO MES REFERENCIA
				String anoMesReferencia = "";
				if ( cobrancaGrupo.getAnoMesReferencia() != null && !cobrancaGrupo.getAnoMesReferencia().equals("") ) {
					anoMesReferencia = "" + cobrancaGrupo.getAnoMesReferencia();
					String ano = anoMesReferencia.substring(0, 4);
		        	String mes = anoMesReferencia.substring(4, 6);
		        	anoMesReferencia = mes +"/"+ano;
				}
				
				//INDICADOR USO
				String indicadorUso = "";
				if ( cobrancaGrupo.getIndicadorUso() != null && !cobrancaGrupo.getIndicadorUso().equals("") ) {
					if (cobrancaGrupo.getIndicadorUso().equals(new Short("1") ) ) {
						indicadorUso = "Ativo";	
					} else if (cobrancaGrupo.getIndicadorUso().equals(new Short("2") )) {
						indicadorUso = "Inativo";
					}
				}
				
				relatorioBean = new RelatorioManterCobrancaGrupoBean(
						//ID
						id, 
						
						//Descrição
						descricao, 
						
						// Descrição Abreviada
						descricaoAbreviada,
						
						//Ano Mes Referencia
						anoMesReferencia,
						
						//Indicador de Uso
						indicadorUso);
						
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
				cobrancaGrupoParametros.getId() == null ? "" : ""
						+ cobrancaGrupoParametros.getId());

		//Descricao
		String descricao = "";
		if ( cobrancaGrupoParametros.getDescricao() != null &&
				!cobrancaGrupoParametros.getDescricao().equals("") ) {
			descricao = cobrancaGrupoParametros.getDescricao();
			parametros.put("descricao", descricao);
		}
		
		//Descricao Abreviada
		String descricaoAbreviada = "";
		if ( cobrancaGrupoParametros.getDescricaoAbreviada() != null &&
				!cobrancaGrupoParametros.getDescricaoAbreviada().equals("") ) {
			descricaoAbreviada = cobrancaGrupoParametros.getDescricaoAbreviada();
			parametros.put("descricaoAbreviada", descricaoAbreviada);
		}
		
		//Ano Mes Referencia 
		String anoMesReferencia = "";
		if ( cobrancaGrupoParametros.getAnoMesReferencia() != null &&
				!cobrancaGrupoParametros.getAnoMesReferencia().equals("") ) {
			anoMesReferencia = "" + cobrancaGrupoParametros.getAnoMesReferencia();
			String ano = anoMesReferencia.substring(0, 4);
        	String mes = anoMesReferencia.substring(4, 6);
        	String anoMes = mes +"/"+ano;
			parametros.put("anoMesReferencia", anoMes);
		}
		
		//Indicador de Uso
		String indicadorUso = "";
		if (cobrancaGrupoParametros.getIndicadorUso() != null
				&& !cobrancaGrupoParametros.getIndicadorUso().equals("")) {
			if (cobrancaGrupoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (cobrancaGrupoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_COBRANCA_GRUPO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterCobrancaGrupo", this);
	}

}
