package gcom.relatorio.seguranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

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

public class RelatorioManterGrupo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterGrupo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_GRUPO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterGrupo() {
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

		//FiltroGrupo filtroGrupo = (FiltroGrupo) getParametro("filtroGrupo");
		Grupo grupoParametros = (Grupo) getParametro("grupoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		FiltroGrupo filtroGrupo = new FiltroGrupo();
		filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterGrupoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoGrupo = fachada.pesquisar(filtroGrupo,
				Grupo.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoGrupo != null && !colecaoGrupo.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator grupoIterator = colecaoGrupo.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (grupoIterator.hasNext()) {

				Grupo grupo = (Grupo) grupoIterator.next();

				
				String indicadorUso = "";
				
				if(grupo.getIndicadorUso().equals(ConstantesSistema.SIM)){
					indicadorUso = "ATIVO";
				} else {
					indicadorUso = "INATIVO";
				}
				
				
				relatorioBean = new RelatorioManterGrupoBean(
						// CODIGO
						grupo.getId().toString(), 
						
						// Descrição
						grupo.getDescricao(), 
						
						//Descricao Abreviada
						grupo.getDescricaoAbreviada(),
						
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
				grupoParametros.getId() == null ? "" : ""
						+ grupoParametros.getId());
		
		
		if (grupoParametros.getDescricao() != null){
			parametros.put("descricao", grupoParametros.getDescricao());
		} else {
			parametros.put("descricao", "");
		}
		
		if (grupoParametros.getDescricaoAbreviada() != null) {
			parametros.put("descricaoAbreviada", grupoParametros.getDescricaoAbreviada());
		} else {
			parametros.put("descricaoAbreviada", "");	
		}
		
				
		String indicadorUso = "";

		if (grupoParametros.getIndicadorUso() != null && !grupoParametros.getIndicadorUso().equals("")) {
			if (grupoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (grupoParametros.getIndicadorUso().equals(new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_GRUPO_MANTER, parametros,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterGrupo", this);
	}

}
