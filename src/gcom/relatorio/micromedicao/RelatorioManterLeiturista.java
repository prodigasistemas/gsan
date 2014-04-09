package gcom.relatorio.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.FiltroLeiturista;
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
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Arthur Carvalho
 * @date 29/12/2009
 * @version 1.0
 */

public class RelatorioManterLeiturista extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterLeiturista(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_LEITURISTA);
	}
	
	@Deprecated
	public RelatorioManterLeiturista() {
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

		FiltroLeiturista filtroLeiturista = (FiltroLeiturista) getParametro("filtroLeiturista");
		Leiturista leituristaParametros = (Leiturista) getParametro("leituristaParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String idFuncionario = (String) getParametro("idFuncionario");
		String descricaoFuncionario = (String) getParametro("descricaoFuncionario");
		String descricaoEmpresa = (String) getParametro("descricaoEmpresa");
		String idCliente = (String) getParametro("idCliente");
		String descricaoCliente = (String) getParametro("descricaoCliente");
		
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterLeituristaBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.EMPRESA);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
		
		Collection colecaoLeiturista = fachada.pesquisar(filtroLeiturista,
				Leiturista.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoLeiturista != null && !colecaoLeiturista.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator leituristaIterator = colecaoLeiturista.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (leituristaIterator.hasNext()) {

				Leiturista leiturista = (Leiturista) leituristaIterator.next();

				String indicadorUso = "";
				if ( leiturista.getIndicadorUso().intValue() == ConstantesSistema.SIM.intValue() )  {
					indicadorUso = "ATIVO";
				} else if ( leiturista.getIndicadorUso().intValue() == ConstantesSistema.NAO.intValue() ){
					indicadorUso = "INATIVO";
				} else {
					indicadorUso = "TODOS";
				}
				
				
				
				relatorioBean = new RelatorioManterLeituristaBean(
						//Codigo
						"" + leiturista.getId(),
						
						//Descricao Leiturista
						leiturista.getFuncionario() !=null ? leiturista.getFuncionario().getNome(): leiturista.getCliente().getNome(),
						
						//Codigo DDD
						"" + leiturista.getCodigoDDD(),
						
						//Numero do Telefone
						"" + leiturista.getNumeroFone(),
						
						//Empresa
						leiturista.getEmpresa().getDescricao(),
						
						//IMEI
						"" + leiturista.getNumeroImei(),
						
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

		//Codigo Leiturista
		if ( leituristaParametros.getCodigoDDD() != null &&
				!leituristaParametros.getCodigoDDD().equals("") ) {
			parametros.put("ddd", leituristaParametros.getCodigoDDD());
		}
		
		//IMEI
		if ( leituristaParametros.getNumeroImei() != null &&
				!leituristaParametros.getNumeroImei().toString().equals("") ) {
			parametros.put("imei", leituristaParametros.getNumeroImei().toString());
		}
		
		//Numero do telefone
		if ( leituristaParametros.getNumeroFone() != null &&
				!leituristaParametros.getNumeroFone().toString().equals("") ) {
			parametros.put("fone", leituristaParametros.getNumeroFone());
		}
		
		//Indicador de Uso
		if ( leituristaParametros.getIndicadorUso() != null &&
				!leituristaParametros.getIndicadorUso().toString().equals("") ) {
				String indicadorDeUso = "";
				if ( leituristaParametros.getIndicadorUso().intValue() == ConstantesSistema.SIM.intValue() ) {
					indicadorDeUso = "ATIVO";
				} else if ( leituristaParametros.getIndicadorUso().intValue() == ConstantesSistema.NAO.intValue() ) {
					indicadorDeUso = "INATIVO";
				} else {
					indicadorDeUso = "TODOS";
				}
			parametros.put("indicadorUso", indicadorDeUso);
		}
		
		//ID Funcionario
		if ( idFuncionario != null &&
				!idFuncionario.equals("") ) {
			parametros.put("idFuncionario", idFuncionario);
		} else {
			parametros.put("idFuncionario", "");
		}
		
		//Descricao Funcionario
		if ( descricaoFuncionario != null &&
				!descricaoFuncionario.equals("") ) {
			parametros.put("descricaoFuncionario", descricaoFuncionario);
		} else {
			parametros.put("descricaoFuncionario", "");
		}
		
		//ID Cliente
		if ( idCliente != null &&
				!idCliente.equals("") ) {
			parametros.put("idCliente", idCliente);
		} else {
			parametros.put("idCliente", "");
		}
		
		//Descricao Cliente
		if ( descricaoCliente != null &&
				!descricaoCliente.equals("") ) {
			parametros.put("descricaoCliente", descricaoCliente);
		} else {
			parametros.put("descricaoCliente", descricaoCliente);
		}
		
		//descricao empresa
		if ( descricaoEmpresa != null &&
				!descricaoEmpresa.equals("") ) {
			parametros.put("descricaoEmpresa", descricaoEmpresa);
		} else {
			parametros.put("descricaoEmpresa", "");
		}
		

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_LEITURISTA, parametros,
				ds, tipoFormatoRelatorio);
		

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLeiturista) getParametro("filtroLeiturista"),
//				Leiturista.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterLeiturista", this);
	}

}
