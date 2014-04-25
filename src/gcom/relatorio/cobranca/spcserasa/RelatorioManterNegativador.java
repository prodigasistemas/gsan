package gcom.relatorio.cobranca.spcserasa;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
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

public class RelatorioManterNegativador extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativador object
	 */
	public RelatorioManterNegativador(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR);
	}

	@Deprecated
	public RelatorioManterNegativador() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param Negativador Parametros
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
		FiltroNegativador  filtroNegativador  = (FiltroNegativador) getParametro("filtroNegativador");
		Negativador negativadorParametros = (Negativador) getParametro("negativadorParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterNegativadorBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necessários para
		// a impressão do relatório
		filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);
		filtroNegativador.setConsultaSemLimites(true);

		// Nova consulta para trazer objeto completo
		Collection<Negativador> colecaoNegativadorNovos = fachada.pesquisar(filtroNegativador, Negativador.class
				.getName());

		if (colecaoNegativadorNovos != null && !colecaoNegativadorNovos.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			for (Negativador negativador : colecaoNegativadorNovos) {
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// ID
				String id = "";
				
				if (negativador.getId() != null) {
					id = negativador.getId().toString();
				}
				
				// código agente
				String codigoAgente = null;
				
				if (negativador.getCodigoAgente() != null) {
					codigoAgente = negativador.getCodigoAgente().toString();
				}
								
				// código do cliente
				String cliente = "";				
				if (negativador.getCliente().getId() != null) {
					cliente = negativador.getCliente().getId().toString();
				}
							
				// nome do negativador 
				String nome = "";				
				if (negativador.getCliente() != null) {					
					nome = negativador.getCliente().getNome();
				}
				
				// imóvel
				String imovel = "";				
				if (negativador.getImovel()!= null && negativador.getImovel().getId() != null) {
					imovel = negativador.getImovel().getId().toString();
				}

				// numeroInscricaoEstadual
				String numeroInscricaoEstadual = "";				
//				if (negativador.getImovel()!= null && negativador.getImovel().getInscricaoFormatada()!= null) {
//					numeroInscricaoEstadual = negativador.getImovel().getInscricaoFormatada();
//				}
				
				// indicadorUso
				String indicadorUso = "";				
				if (negativador.getIndicadorUso()!= null ) {
					if(negativador.getIndicadorUso().equals(ConstantesSistema.SIM)){
						indicadorUso = "Ativo";
					}else if(negativador.getIndicadorUso().equals(ConstantesSistema.NAO)){
						indicadorUso = "Inativo";
					}
				}
				
				// Inicializa o construtor constituído dos campos
				// necessários para a impressão do relatorio
				relatorioBean = new RelatorioManterNegativadorBean(						
						// ID
						id,
						
						// Descrição do Negativador
						codigoAgente,	
						
						// Número do Contrato
						cliente,
											
						// Data Início
						nome,
						
						// Data Fim
						imovel,
						
						numeroInscricaoEstadual,
						
						indicadorUso);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (negativadorParametros.getCodigoAgente() != null) {
			parametros.put("codigoAgente", negativadorParametros.getCodigoAgente().toString());
		} else {
			parametros.put("codigoAgente", "");
		}
			
		if (negativadorParametros.getCliente().getId() != null) {
			parametros.put("codigoCliente", negativadorParametros.getCliente().getId().toString());
		} else {
			parametros.put("codigoCliente", "");
		}
		
		if (negativadorParametros.getImovel().getId() != null) {
			parametros.put("imovel", negativadorParametros.getImovel().getId().toString());
			parametros.put("numeroInscricaoEstadual", negativadorParametros.getImovel().getInscricaoFormatada());
		} else {
			parametros.put("imovel", "");
			parametros.put("numeroInscricaoEstadual", "");
		}

		if (negativadorParametros.getIndicadorUso() != null) {
			parametros.put("indicadorUso", negativadorParametros.getIndicadorUso().toString());
		} else {
			parametros.put("indicadorUso", "");
		}
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_NEGATIVADOR,
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
				(FiltroNegativador) getParametro("filtroNegativador"),
				Negativador.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterNegativador", this);
	}

}
