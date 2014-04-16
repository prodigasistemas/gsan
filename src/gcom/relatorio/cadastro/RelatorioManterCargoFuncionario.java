package gcom.relatorio.cadastro;

import gcom.cadastro.funcionario.FiltroFuncionarioCargo;
import gcom.cadastro.funcionario.FuncionarioCargo;
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

public class RelatorioManterCargoFuncionario extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterCargoFuncionario(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CARGO_FUNCIONARIO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterCargoFuncionario() {
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

		FiltroFuncionarioCargo filtroFuncionarioCargo = (FiltroFuncionarioCargo) getParametro("filtroFuncionarioCargo");
		FuncionarioCargo funcionarioCargoParametros = (FuncionarioCargo) getParametro("funcionarioCargoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterCargoFuncionarioBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoFuncionarioCargo = fachada.pesquisar(filtroFuncionarioCargo,
				FuncionarioCargo.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoFuncionarioCargo != null && !colecaoFuncionarioCargo.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator funcionarioCargoIterator = colecaoFuncionarioCargo.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (funcionarioCargoIterator.hasNext()) {

				FuncionarioCargo funcionarioCargo = (FuncionarioCargo) funcionarioCargoIterator.next();

				
				String indicadorUso = "";
				
				if(funcionarioCargo.getIndicadorUso().equals(ConstantesSistema.SIM)){
					indicadorUso = "SIM";
				} else {
					indicadorUso = "NÃO";
				}
				
				relatorioBean = new RelatorioManterCargoFuncionarioBean(
						// CODIGO
						funcionarioCargo.getCodigo().toString(), 
						
						// Descrição
						funcionarioCargo.getDescricao(), 
						
						//Descrição Abreviada
						funcionarioCargo.getDescricaoAbreviada(),
						
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

		parametros.put("codigo",
				funcionarioCargoParametros.getCodigo() == null ? "" : ""
						+ funcionarioCargoParametros.getCodigo());
		
		
		if (funcionarioCargoParametros.getDescricao() != null){
			parametros.put("descricao", funcionarioCargoParametros.getDescricao());
		} else {
			parametros.put("descricao", "");
		}
		
		
		if (funcionarioCargoParametros.getDescricaoAbreviada() != null) {
			parametros.put("descricaoAbreviada", funcionarioCargoParametros.getDescricaoAbreviada());
		} else {
			parametros.put("descricaoAbreviada", "");
		}
		
		
		String indicadorUso = "";

		if (funcionarioCargoParametros.getIndicadorUso() != null
				&& !funcionarioCargoParametros.getIndicadorUso().equals("")) {
			if (funcionarioCargoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (funcionarioCargoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CARGO_FUNCIONARIO_MANTER, parametros,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterCargoFuncionario", this);
	}

}
