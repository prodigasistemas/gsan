package gcom.relatorio.cobranca.spcserasa;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.NegativadorContrato;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativadorContrato;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane
 * @created 28 de Fevereiro de 2008
 * @version 1.0
 */

public class RelatorioManterNegativadorContrato extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativadorContrato object
	 */
	public RelatorioManterNegativadorContrato(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR_CONTRATO);
	}

	@Deprecated
	public RelatorioManterNegativadorContrato() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param NegativadorContrato Parametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os parâmetros que serão utilizados no relatório
		FiltroNegativadorContrato  filtroNegativadorContrato  = (FiltroNegativadorContrato) getParametro("filtroNegativadorContrato");
		NegativadorContrato negativadorContratoParametros = (NegativadorContrato) getParametro("negativadorContratoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterNegativadorContratoBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necessários para
		// a impressão do relatório
		filtroNegativadorContrato.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
		filtroNegativadorContrato.setConsultaSemLimites(true);

		// Nova consulta para trazer objeto completo
		Collection<NegativadorContrato> colecaoNegativadorContratoNovos = fachada.pesquisar(filtroNegativadorContrato, NegativadorContrato.class
				.getName());

		if (colecaoNegativadorContratoNovos != null && !colecaoNegativadorContratoNovos.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			for (NegativadorContrato negativadorContrato : colecaoNegativadorContratoNovos) {
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// ID
				String id = "";
				
				if (negativadorContrato.getId() != null) {
					id = negativadorContrato.getId().toString();
				}
				
				// número do contrato
				String numeroContrato = "";
				
				if (negativadorContrato.getNumeroContrato() != null
						&& !negativadorContrato.getNumeroContrato()
								.trim().equals("")) {
					numeroContrato = negativadorContrato.getNumeroContrato();
				}
								
				// negativador
				String negativador = "";				
				if (negativadorContrato.getNegativador().getId() != null) {
					negativador = negativadorContrato.getNegativador().getCliente().getNome();
				}
							
				// data início do contrato
				String dataInicio = "";				
				if (negativadorContrato.getDataContratoInicio() != null) {					
					dataInicio = Util.formatarData(negativadorContrato.getDataContratoInicio());
				}
				
				
				// data fim do contrato
				String dataFim = "";				
				if (negativadorContrato.getDataContratoFim() != null) {					
					dataFim = Util.formatarData(negativadorContrato.getDataContratoFim());
				}
				
				
				// Inicializa o construtor constituído dos campos
				// necessários para a impressão do relatorio
				relatorioBean = new RelatorioManterNegativadorContratoBean(						
						// ID
						id,
						
						// Descrição do Negativador
						negativador,	
						
						// Número do Contrato
						numeroContrato,
											
						// Data Início
						dataInicio,
						
						// Data Fim
						dataFim);

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
		
		
		if (negativadorContratoParametros.getNegativador().getId() != null) {
			parametros.put("negativador", negativadorContratoParametros.getNegativador().getCliente().getNome());
		} else {
			parametros.put("negativador", "");
		}
		
		if (negativadorContratoParametros.getNumeroContrato() != null) {
			parametros.put("numeroContrato", negativadorContratoParametros.getNumeroContrato().toString());
		} else {
			parametros.put("numeroContrato", "");
		}
		
		if (negativadorContratoParametros.getDataContratoInicio() != null) {
			parametros.put("dataInicio", Util.formatarData(negativadorContratoParametros.getDataContratoInicio()));
		} else {
			parametros.put("dataInicio", "");
		}

		if (negativadorContratoParametros.getDataContratoFim() != null) {
			parametros.put("dataFim", Util.formatarData(negativadorContratoParametros.getDataContratoFim()));
		} else {
			parametros.put("dataFim", "");
		}


		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR_CONTRATO, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_NEGATIVADOR_CONTRATO,
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
		int retorno = ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA;

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroNegativadorContrato) getParametro("filtroNegativadorContrato"),
				NegativadorContrato.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterNegativadorContrato", this);
	}

}
