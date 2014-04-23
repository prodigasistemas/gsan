package gcom.relatorio.cobranca.spcserasa;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
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

public class RelatorioManterNegativadorExclusaoMotivo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativadorExclusaoMotivo object
	 */
	public RelatorioManterNegativadorExclusaoMotivo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR_EXCLUSAO_MOTIVO);
	}

	@Deprecated
	public RelatorioManterNegativadorExclusaoMotivo() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param NegativadorExclusaoMotivo Parametros
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
		FiltroNegativadorExclusaoMotivo  filtroNegativadorExclusaoMotivo  = (FiltroNegativadorExclusaoMotivo) getParametro("filtroNegativadorExclusaoMotivo");
		NegativadorExclusaoMotivo negativadorExclusaoMotivoParametros = (NegativadorExclusaoMotivo) getParametro("negativadorExclusaoMotivoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterNegativadorExclusaoMotivoBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necessários para
		// a impressão do relatório
		filtroNegativadorExclusaoMotivo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
		filtroNegativadorExclusaoMotivo.setConsultaSemLimites(true);

		// Nova consulta para trazer objeto completo
		Collection<NegativadorExclusaoMotivo> colecaoNegativadorExclusaoMotivoNovos = fachada.pesquisar(filtroNegativadorExclusaoMotivo, NegativadorExclusaoMotivo.class
				.getName());

		if (colecaoNegativadorExclusaoMotivoNovos != null && !colecaoNegativadorExclusaoMotivoNovos.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			for (NegativadorExclusaoMotivo negativadorExclusaoMotivo : colecaoNegativadorExclusaoMotivoNovos) {
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// ID
				String id = "";
				
				if (negativadorExclusaoMotivo.getId() != null) {
					id = negativadorExclusaoMotivo.getId().toString();
				}
				
				// Descrição
				String descricao = "";
				
				if (negativadorExclusaoMotivo.getDescricaoExclusaoMotivo() != null
						&& !negativadorExclusaoMotivo.getDescricaoExclusaoMotivo()
								.trim().equals("")) {
					descricao = negativadorExclusaoMotivo.getDescricaoExclusaoMotivo();
				}
				
				// codigoRegistro
				String codigoMotivo = "";				
				if (negativadorExclusaoMotivo.getCodigoExclusaoMotivo() != null) {
					codigoMotivo = Short.toString(negativadorExclusaoMotivo.getCodigoExclusaoMotivo()) ;
				}

				
				// negativador
				String negativador = "";				
				if (negativadorExclusaoMotivo.getNegativador().getId() != null) {
					negativador = negativadorExclusaoMotivo.getNegativador().getCliente().getNome();
				}
				
			
				// indicadorUso
				String indicadorUso = "";				
				if (negativadorExclusaoMotivo.getIndicadorUso() != null) {					
					if(negativadorExclusaoMotivo.getIndicadorUso().equals(ConstantesSistema.SIM)){
						indicadorUso = "Ativo";
					}else if(negativadorExclusaoMotivo.getIndicadorUso().equals(ConstantesSistema.NAO)){
						indicadorUso = "Inativo";
					}
				}
				

				// Inicializa o construtor constituído dos campos
				// necessários para a impressão do relatorio
				relatorioBean = new RelatorioManterNegativadorExclusaoMotivoBean(
						
						// ID
						id,

						// Descrição
						descricao,

						// Código Motivo
						codigoMotivo,

						// Descrição do Negativador
						negativador,					
					
						// Indicador de Uso
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

		parametros.put("negativador", negativadorExclusaoMotivoParametros.getNegativador().getId() == -1 ? ""
				: "" + negativadorExclusaoMotivoParametros.getNegativador().getCliente().getNome());	

		parametros.put("descricao", negativadorExclusaoMotivoParametros.getDescricaoExclusaoMotivo());

		parametros.put("codigoMotivo", negativadorExclusaoMotivoParametros.getCodigoExclusaoMotivo());		
		
		parametros.put("indicadorUso", negativadorExclusaoMotivoParametros.getIndicadorUso());

	

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR_EXCLUSAO_MOTIVO, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_NEGATIVADOR_EXCLUSAO_MOTIVO,
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
				(FiltroNegativadorExclusaoMotivo) getParametro("filtroNegativadorExclusaoMotivo"),
				NegativadorExclusaoMotivo.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterNegativadorExclusaoMotivo", this);
	}

}
