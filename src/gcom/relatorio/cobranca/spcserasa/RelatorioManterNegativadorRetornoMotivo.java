package gcom.relatorio.cobranca.spcserasa;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
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
 * @author Rafael Corrêa
 * @created 9 de Setembro de 2005
 * @version 1.0
 */

public class RelatorioManterNegativadorRetornoMotivo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativadorRegistroTipo object
	 */
	public RelatorioManterNegativadorRetornoMotivo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR_RETORNO_MOTIVO);
	}

	@Deprecated
	public RelatorioManterNegativadorRetornoMotivo() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param NegativadorRegistroTipoParametros
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
		FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = (FiltroNegativadorRetornoMotivo) getParametro("filtroNegativadorRetornoMotivo");
		NegativadorRetornoMotivo negativadorRetornoMotivoParametros = (NegativadorRetornoMotivo) getParametro("negativadorRetornoMotivoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterNegativadorRetornoMotivoBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necessários para
		// a impressão do relatório
		filtroNegativadorRetornoMotivo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
		filtroNegativadorRetornoMotivo.setConsultaSemLimites(true);

		// Nova consulta para trazer objeto completo
		Collection<NegativadorRetornoMotivo> colecaoNegativadorRetornoMotivoNovos = fachada.pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class
				.getName());

		if (colecaoNegativadorRetornoMotivoNovos != null && !colecaoNegativadorRetornoMotivoNovos.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			for (NegativadorRetornoMotivo negativadorRetornoMotivo : colecaoNegativadorRetornoMotivoNovos) {
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// ID
				String id = "";
				
				if (negativadorRetornoMotivo.getId() != null) {
					id = negativadorRetornoMotivo.getId().toString();
				}
				
				// Descrição
				String descricao = "";
				
				if (negativadorRetornoMotivo.getDescricaoRetornocodigo() != null
						&& !negativadorRetornoMotivo.getDescricaoRetornocodigo()
								.trim().equals("")) {
					descricao = negativadorRetornoMotivo.getDescricaoRetornocodigo();
				}
				
				// codigoRegistro
				String codigoMotivo = "";				
				if (negativadorRetornoMotivo.getCodigoRetornoMotivo() != null) {
					codigoMotivo = Short.toString(negativadorRetornoMotivo.getCodigoRetornoMotivo()) ;
				}

				
				// negativador
				String negativador = "";				
				if (negativadorRetornoMotivo.getNegativador().getId() != null) {
					negativador = negativadorRetornoMotivo.getNegativador().getCliente().getNome();
				}
				
				// indicadorRegistroAceito
				String indicadorRegistroAceito = "";				
				if (negativadorRetornoMotivo.getIndicadorRegistroAceito() != null) {					
					if(negativadorRetornoMotivo.getIndicadorRegistroAceito().equals(ConstantesSistema.SIM)){
						indicadorRegistroAceito = "Sim";
					}else if(negativadorRetornoMotivo.getIndicadorRegistroAceito().equals(ConstantesSistema.NAO)){
						indicadorRegistroAceito = "Não";
					}
					
					
				}

				
				// indicadorUso
				String indicadorUso = "";				
				if (negativadorRetornoMotivo.getIndicadorUso() != null) {					
					if(negativadorRetornoMotivo.getIndicadorUso().equals(ConstantesSistema.SIM)){
						indicadorUso = "Ativo";
					}else if(negativadorRetornoMotivo.getIndicadorUso().equals(ConstantesSistema.NAO)){
						indicadorUso = "Inativo";
					}
				}
				

				// Inicializa o construtor constituído dos campos
				// necessários para a impressão do relatorio
				relatorioBean = new RelatorioManterNegativadorRetornoMotivoBean(
						
						// ID
						id,

						// Descrição
						descricao,

						// Código Motivo
						codigoMotivo,

						// Descrição do Negativador
						negativador,
						
						// Indicador de Registro Aceito
						indicadorRegistroAceito,
						
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

		parametros.put("negativador", negativadorRetornoMotivoParametros.getNegativador().getId() == -1 ? ""
				: "" + negativadorRetornoMotivoParametros.getNegativador().getCliente().getNome());	

		parametros.put("descricao", negativadorRetornoMotivoParametros.getDescricaoRetornocodigo());

		parametros.put("codigoMotivo", negativadorRetornoMotivoParametros.getCodigoRetornoMotivo());
		
		parametros.put("indicadorRegistroAceito", negativadorRetornoMotivoParametros.getIndicadorRegistroAceito());
	
		parametros.put("indicadorUso", negativadorRetornoMotivoParametros.getIndicadorUso());

	

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR_RETORNO_MOTIVO, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_NEGATIVADOR_RETORNO_MOTIVO,
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
				(FiltroNegativadorRetornoMotivo) getParametro("filtroNegativadorRetornoMotivo"),
				NegativadorRetornoMotivo.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterNegativadorRetornoMotivo", this);
	}

}
