package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o Relatorio de Registro de Atendimento 
 * Via Cliente - Especifico para a Cosanpa.
 * 
 * @author Hugo Leonardo
 * @created 08 de Abril de 2010
 */
public class RelatorioConsultarRegistroAtendimentoViaClienteCosanpa extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioConsultarRegistroAtendimentoViaClienteCosanpa(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO_VIA_CLIENTE_COSANPA);
	}
	
	@Deprecated
	public RelatorioConsultarRegistroAtendimentoViaClienteCosanpa() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Integer idRegistroAtendimento = (Integer) getParametro("idRegistroAtendimento");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeFuncionario = (String) getParametro("funcionario");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioConsultarRegistroAtendimentoViaClienteBean relatorioBean = null;

		ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada
				.obterDadosRegistroAtendimento(idRegistroAtendimento);

		// Seta um valor para um indicador que será comparado no relatório para
		// imprimir o título associado do número e da situação da RA
		String indicadorAssociado = "";

		if (registroAtendimentoHelper.getTituloNumeroRAAssociado() != null
				&& registroAtendimentoHelper.getTituloNumeroRAAssociado()
						.equalsIgnoreCase("Número do RA de Referência")) {
			indicadorAssociado = "1";
		} else if (registroAtendimentoHelper.getTituloNumeroRAAssociado() != null
				&& registroAtendimentoHelper.getTituloNumeroRAAssociado()
						.equalsIgnoreCase("Número do RA Atual")) {
			indicadorAssociado = "2";
		} else if (registroAtendimentoHelper.getTituloNumeroRAAssociado() != null
				&& registroAtendimentoHelper.getTituloNumeroRAAssociado()
						.equalsIgnoreCase("Número do RA Anterior")) {
			indicadorAssociado = "3";
		}

		relatorioBean = new RelatorioConsultarRegistroAtendimentoViaClienteBean(

				// Dados Gerais

				// Número RA
				registroAtendimentoHelper.getRegistroAtendimento().getId()
						.toString(),

				// Situação RA
				registroAtendimentoHelper.getDescricaoSituacaoRA(),

				// Indicador RA Associado
				indicadorAssociado,

				// Número RA Associado
				registroAtendimentoHelper.getRAAssociado() == null ? ""
						: registroAtendimentoHelper.getRAAssociado().getId()
								.toString(),

				// Situação RA Associado
				registroAtendimentoHelper.getDescricaoSituacaoRAAssociado(),

				// Tipo Solicitação
				registroAtendimentoHelper.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getSolicitacaoTipoEspecificacao()
								.getSolicitacaoTipo().getDescricao(),

				// Especificação
				registroAtendimentoHelper.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getSolicitacaoTipoEspecificacao()
								.getDescricao(),

				// Data Atendimento
				Util.formatarDataComHora(registroAtendimentoHelper
						.getRegistroAtendimento().getRegistroAtendimento()),

				// Data Prevista
				Util.formatarData(registroAtendimentoHelper
						.getRegistroAtendimento().getDataPrevistaAtual()),

				// Meio Solicitação
				registroAtendimentoHelper.getRegistroAtendimento()
						.getMeioSolicitacao() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getMeioSolicitacao().getDescricao(),

				// Unidade Atendimento
				registroAtendimentoHelper.getUnidadeAtendimento() == null ? ""
						: registroAtendimentoHelper.getUnidadeAtendimento()
								.getDescricao(),

				// Unidade Atual
				registroAtendimentoHelper.getUnidadeAtual() == null ? ""
						: registroAtendimentoHelper.getUnidadeAtual()
								.getDescricao(),

				// Observação
				registroAtendimentoHelper.getRegistroAtendimento()
						.getObservacao(),

				// Dados do Local da Ocorrência

				// Matrícula do Imóvel
				registroAtendimentoHelper.getRegistroAtendimento().getImovel() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getImovel().getId().toString(),

				// Inscrição do Imóvel
				registroAtendimentoHelper.getRegistroAtendimento().getImovel() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getImovel().getInscricaoFormatada(),

				// Endereço da Ocorrência
				registroAtendimentoHelper.getEnderecoOcorrencia(),

				// Ponto de Referência
				registroAtendimentoHelper.getRegistroAtendimento()
						.getPontoReferencia(),

				// Município
				registroAtendimentoHelper.getRegistroAtendimento()
						.getBairroArea() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getBairroArea().getBairro().getMunicipio()
								.getNome(),

				// Bairro
				registroAtendimentoHelper.getRegistroAtendimento()
						.getBairroArea() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getBairroArea().getBairro().getNome(),

				// Área do Bairro
				registroAtendimentoHelper.getRegistroAtendimento()
						.getBairroArea() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getBairroArea().getNome(),

				// Localidade/Setor/Quadra
				(registroAtendimentoHelper.getRegistroAtendimento()
						.getLocalidade() == null ? "---" : Util
						.adicionarZerosEsquedaNumero(3,
								registroAtendimentoHelper
										.getRegistroAtendimento()
										.getLocalidade().getId().toString()))
						+ "/"
						+ (registroAtendimentoHelper.getRegistroAtendimento()
								.getSetorComercial() == null ? "---" : Util
								.adicionarZerosEsquedaNumero(3, ""
										+ registroAtendimentoHelper
												.getRegistroAtendimento()
												.getSetorComercial()
												.getCodigo()))
						+ "/"
						+ (registroAtendimentoHelper.getRegistroAtendimento()
								.getQuadra() == null ? "---" : Util
								.adicionarZerosEsquedaNumero(3, ""
										+ registroAtendimentoHelper
												.getRegistroAtendimento()
												.getQuadra().getNumeroQuadra())),

				// Divisão Esgoto
				registroAtendimentoHelper.getRegistroAtendimento()
						.getDivisaoEsgoto() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getDivisaoEsgoto().getDescricao(),

				// Local da Ocorrência
				registroAtendimentoHelper.getRegistroAtendimento()
						.getLocalOcorrencia() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getLocalOcorrencia().getDescricao(),

				// Pavimento Rua
				registroAtendimentoHelper.getRegistroAtendimento()
						.getPavimentoRua() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getPavimentoRua().getDescricao(),

				// Pavimento Calçada
				registroAtendimentoHelper.getRegistroAtendimento()
						.getPavimentoCalcada() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getPavimentoCalcada().getDescricao(),

				// Descrição do Local da Ocorrência
				registroAtendimentoHelper.getRegistroAtendimento()
						.getDescricaoLocalOcorrencia(),

				// Dados do Solicitante

				// Código do Cliente
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getCliente() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
										.getCliente().getId().toString(),

				// Nome do Cliente
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getCliente() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
										.getCliente().getNome(),
										
				//Protocolo
					registroAtendimentoHelper.getSolicitante() == null ? ""
							: registroAtendimentoHelper.getSolicitante()
								.getNumeroProtocoloAtendimento() == null ? ""
										: registroAtendimentoHelper.getSolicitante()
											.getNumeroProtocoloAtendimento(),						
				
										

				// Unidade Solicitante
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getUnidadeOrganizacional() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
										.getUnidadeOrganizacional()
										.getDescricao(),

				// Código do Funcionário Responsável
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getFuncionario() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
										.getFuncionario().getId().toString(),

				// Nome do Funcionário Responsável
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getFuncionario() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
										.getFuncionario().getNome(),

				// Nome do Solicitante
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getSolicitante(),
								
				// Rota
				registroAtendimentoHelper.getCodigoRota() == null? "": registroAtendimentoHelper.getCodigoRota().toString(),
										
				// Sequencial Rota
				registroAtendimentoHelper.getSequencialRota() == null? "": registroAtendimentoHelper.getSequencialRota().toString());

		//Telefone principal do Solicitante
		String foneSolicitante = fachada.pesquisarClienteFonePrincipal(
				registroAtendimentoHelper.getSolicitante().getCliente().getId());
		relatorioBean.setFoneSolicitante(foneSolicitante);
		
		// adiciona o bean a coleção
		relatorioBeans.add(relatorioBean);

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("funcionario", nomeFuncionario);

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO_VIA_CLIENTE_COSANPA,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.CONSULTAR_REGISTRO_ATENDIMENTO_VIA_CLIENTE_COSANPA,
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
		int retorno = 1;
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarRegistroAtendimentoViaClienteCosanpa", this);
	}
}
