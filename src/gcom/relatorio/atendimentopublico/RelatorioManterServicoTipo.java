package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de Tipo de Servico
 * 
 * @author Vinicius Medeiros
 * @created 23 de Marco de 2009
 */
public class RelatorioManterServicoTipo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterServicoTipo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_SERVICO_TIPO);
	}

	@Deprecated
	public RelatorioManterServicoTipo() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param colecaoServicoTipo
	 *            Description of the Parameter
	 * @param servicoTipoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		FiltroTipoServico filtroTipoServico = (FiltroTipoServico) getParametro("filtroTipoServico");
		ServicoTipo servicoTipoParametros = (ServicoTipo) getParametro("servicoTipoParametros");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterServicoTipoBean relatorioBean = null;

		filtroTipoServico.setConsultaSemLimites(true);

		Collection<ServicoTipo> colecaoServicoTipo = fachada.pesquisar(filtroTipoServico, ServicoTipo.class
				.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (ServicoTipo servicoTipo : colecaoServicoTipo) {

				String tempoMedio = "" + servicoTipo.getTempoMedioExecucao();
				
				String indicadorAtualizaComercial = "" + servicoTipo.getIndicadorAtualizaComercial();
				
				if (indicadorAtualizaComercial.equals("1")) {
					indicadorAtualizaComercial = "Sim, no momento da execução";
				
				} else if (indicadorAtualizaComercial.equals("2")) {
					
					indicadorAtualizaComercial = "Não Atualiza";
				
				} else if (indicadorAtualizaComercial.equals("3")) {
					
					indicadorAtualizaComercial = "Sim, no momento posterior";
			    }
				
				String codigoServicoTipo = servicoTipo.getCodigoServicoTipo();
				
				if(codigoServicoTipo.equals("C")){
					codigoServicoTipo = "Comercial";
				} else if (codigoServicoTipo.equals("O")){
					codigoServicoTipo = "Operacional";
				}
				
				relatorioBean = new RelatorioManterServicoTipoBean(
						// Código
						servicoTipo.getId().toString(), 
						
						// Descrição
						servicoTipo.getDescricao(), 
						
						// Codigo Servico Tipo
						codigoServicoTipo,
						
						// Tempo Medio
						tempoMedio,
						
						// Indicador Atualizacao Comercial
						indicadorAtualizaComercial);

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
		
		// Codigo
		if (servicoTipoParametros.getId() != null) {
			parametros.put("codigo",
					servicoTipoParametros.getId().toString());
		} else {
			parametros.put("codigo", "");
		}

		// Descricao
		parametros.put("descricao", servicoTipoParametros.getDescricao());

		// Descricao Abreviada
		parametros.put("abreviacao", servicoTipoParametros.getDescricaoAbreviada());
		
		// Tempo Medio Inicial e Final
		String tempoMedioInicial = (String) getParametro("tempoMedioInicial");
		String tempoMedioFinal = (String) getParametro("tempoMedioFinal");
		parametros.put("tempoMedioInicial", tempoMedioInicial);
		parametros.put("tempoMedioFinal", tempoMedioFinal);
		
		// Valor Inicial e Final
		String valorInicial = (String) getParametro("valorInicial");
		String valorFinal = (String) getParametro("valorFinal");		
		parametros.put("valorInicial", valorInicial);
		parametros.put("valorFinal", valorFinal);
				
		// Codigo Tipo Servico
		String codigoServicoTipo = servicoTipoParametros.getCodigoServicoTipo();
		
		if(codigoServicoTipo != null && !codigoServicoTipo.equals("")){
			if(codigoServicoTipo.equals("C")){
				codigoServicoTipo = "Comercial";
			} else if (codigoServicoTipo.equals("O")){
				codigoServicoTipo = "Operacional";
			}
		}
		
		parametros.put("codigoServicoTipo",codigoServicoTipo);
		
		//indicadorAtualizaComercial
		String indicadorAtualizacaoComercial = "" + servicoTipoParametros.getIndicadorAtualizaComercial();

			if (indicadorAtualizacaoComercial.equals("1")) {
				indicadorAtualizacaoComercial = "Sim, no momento da execução";
			} else if (indicadorAtualizacaoComercial.equals("2")) {
				indicadorAtualizacaoComercial = "Não Atualiza";
			} else if (indicadorAtualizacaoComercial.equals("3")) {
				indicadorAtualizacaoComercial = "Sim, no momento posterior";
		}

		parametros.put("indicadorAtualizacaoComercial", indicadorAtualizacaoComercial);
		
		//Indicador Pavimento Rua
		String indicadorPavimentoRua = "" + servicoTipoParametros.getIndicadorPavimentoRua();
		
		if(indicadorPavimentoRua.equals("1")){
			indicadorPavimentoRua = "Sim";
		} else if(indicadorPavimentoRua.equals("2")){
			indicadorPavimentoRua = "Não";
		} else {
			indicadorPavimentoRua = "Todos";
		}	

		parametros.put("indicadorPavimentoRua",indicadorPavimentoRua);

		//Indicador Pavimento Calcada
		String indicadorPavimentoCalcada = "" + servicoTipoParametros.getIndicadorPavimentoCalcada();
		
		if(indicadorPavimentoCalcada.equals("1")){
			indicadorPavimentoCalcada = "Sim";
		} else if(indicadorPavimentoCalcada.equals("2")){
			indicadorPavimentoCalcada = "Não";
		} else {
			indicadorPavimentoCalcada = "Todos";
		}	

		parametros.put("indicadorPavimentoCalcada",indicadorPavimentoCalcada);
		
		//Indicador Atualizacao Comercial
		String indicadorAtualizaComercial = "" + servicoTipoParametros.getIndicadorAtualizaComercial();
		
		if(indicadorAtualizaComercial.equals("0")){
			indicadorAtualizaComercial = "";
		} else if(indicadorAtualizaComercial.equals("1")){
			indicadorAtualizaComercial = "Sim - Todos";
		} else if(indicadorAtualizaComercial.equals("2")){
			indicadorAtualizaComercial = "Sim - Momento da Execução";
		} else if(indicadorAtualizaComercial.equals("3")){
			indicadorAtualizaComercial = "Sim - Momento Posterior";
		} else if(indicadorAtualizaComercial.equals("4")){
			indicadorAtualizaComercial = "Todos";
		} else if(indicadorAtualizaComercial.equals("5")){
			indicadorAtualizaComercial = "Não";
		}	
		
		parametros.put("indicadorAtualizaComercial",indicadorAtualizaComercial);

		
		// Indicador Servico Terceirizado
		String indicadorServicoTerceirizado = "" + servicoTipoParametros.getIndicadorTerceirizado();
		
		if(indicadorServicoTerceirizado.equals("1")){
			indicadorServicoTerceirizado = "Sim";
		} else if(indicadorServicoTerceirizado.equals("2")){
			indicadorServicoTerceirizado = "Não";
		} else {
			indicadorServicoTerceirizado = "Todos";
		}	

		parametros.put("indicadorServicoTerceirizado",indicadorServicoTerceirizado);
		
		// Indicativo Fiscalizacao Infracao
		String indicativoFiscalizacaoInfracao = "" + servicoTipoParametros.getIndicadorFiscalizacaoInfracao();
		
		if(indicativoFiscalizacaoInfracao.equals("1")){
			indicativoFiscalizacaoInfracao = "Sim";
		} else if(indicativoFiscalizacaoInfracao.equals("2")){
			indicativoFiscalizacaoInfracao = "Não";
		} else {
			indicativoFiscalizacaoInfracao = "Todos";
		}	

		parametros.put("indicativoFiscalizacaoInfracao",indicativoFiscalizacaoInfracao);

		// Indicativo Vistoria
		String indicativoVistoria = "" + servicoTipoParametros.getIndicadorVistoria();
		
		if(indicativoVistoria.equals("1")){
			indicativoVistoria = "Sim";
		} else if(indicativoVistoria.equals("2")){
			indicativoVistoria = "Não";
		} else {
			indicativoVistoria = "Todos";
		}	

		parametros.put("indicativoVistoria",indicativoVistoria);
		
		// Tipo Debito
		if (servicoTipoParametros.getDebitoTipo() != null) {
			parametros.put("debitoTipo", servicoTipoParametros.getDebitoTipo().getDescricao());
		} else {
			parametros.put("debitoTipo", "");	
		}
		
		// Tipo Credito
		if (servicoTipoParametros.getCreditoTipo() != null) {
			parametros.put("creditoTipo", servicoTipoParametros.getCreditoTipo().getDescricao());
		} else {
			parametros.put("creditoTipo", "");	
		}
		
		// Servico Tipo Prioridade
		if (servicoTipoParametros.getServicoTipoPrioridade() != null) {
			parametros.put("servicoTipoPrioridade", servicoTipoParametros.getServicoTipoPrioridade().getDescricao());
		} else {
			parametros.put("servicoTipoPrioridade", "");	
		}
		
		// Servico Tipo Perfil
		if (servicoTipoParametros.getServicoPerfilTipo() != null) {
			parametros.put("servicoPerfilTipo", servicoTipoParametros.getServicoPerfilTipo().getDescricao());
		} else {
			parametros.put("servicoPerfilTipo", "");	
		}
		
		// Servico Tipo Referencia
		if (servicoTipoParametros.getServicoTipoReferencia() != null) {
			parametros.put("servicoTipoReferencia", servicoTipoParametros.getServicoTipoReferencia().getDescricao());
		} else {
			parametros.put("servicoTipoReferencia", "");	
		}		
		
		// Subgrupo
		if (servicoTipoParametros.getServicoTipoSubgrupo() != null) {
			parametros.put("servicoTipoSubgrupo", servicoTipoParametros.getServicoTipoSubgrupo().getDescricao());
		} else {
			parametros.put("servicoTipoSubgrupo", "");	
		}

		//Indicador Atividade Unica
		String indicadorAtividadeUnica = "" + servicoTipoParametros.getIndicadorUso();
		
		if(indicadorAtividadeUnica.equals("1")){
			indicadorAtividadeUnica = "Sim";
		} else if(indicadorAtividadeUnica.equals("2")){
			indicadorAtividadeUnica = "Não";
		} else {
			indicadorAtividadeUnica = "Todos";
		}
		
		parametros.put("indicadorAtividadeUnica", indicadorAtividadeUnica);

		//indicadorUso
		String indicadorUso = "" + servicoTipoParametros.getIndicadorUso();
		
		if(indicadorUso.equals("1")){
			indicadorUso = "Ativo";
		} else if(indicadorUso.equals("2")){
			indicadorUso = "Inativo";
		} else {
			indicadorUso = "Todos";
		}
		
		parametros.put("indicadorUso", indicadorUso);
		
		
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_SERVICO_TIPO, parametros,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterServicoTipo", this);
	}

}
