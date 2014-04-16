package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
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
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * classe responsável por criar o relatório de regitro atendimento manter de
 * água
 * 
 * @author Rafael Corrêa
 * @created 11 de Julho de 2005
 */
public class RelatorioConsultarRegistroAtendimento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioConsultarRegistroAtendimento(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO);
	}
	
	@Deprecated
	public RelatorioConsultarRegistroAtendimento() {
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

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioConsultarRegistroAtendimentoBean relatorioBean = null;
		RelatorioConsultarRegistroAtendimentoDetailBean relatorioDetailBean = null;

		ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada
				.obterDadosRegistroAtendimento(idRegistroAtendimento);

		// Seta um valor para um indicador que será comparado no relatório para
		// imprimir o título associado do número e da situação da RA
		String indicadorAssociado = "";

		if (registroAtendimentoHelper.getTituloNumeroRAAssociado() != null
				&& registroAtendimentoHelper.getTituloNumeroRAAssociado()
						.equalsIgnoreCase("Número do RA de Referência:")) {
			indicadorAssociado = "1";
		} else if (registroAtendimentoHelper.getTituloNumeroRAAssociado() != null
				&& registroAtendimentoHelper.getTituloNumeroRAAssociado()
						.equalsIgnoreCase("Número do RA Atual:")) {
			indicadorAssociado = "2";
		} else if (registroAtendimentoHelper.getTituloNumeroRAAssociado() != null
				&& registroAtendimentoHelper.getTituloNumeroRAAssociado()
						.equalsIgnoreCase("Número do RA Anterior:")) {
			indicadorAssociado = "3";
		}
		
		String unidadeEncerramento = "";
		String usuarioEncerramento = "";
		
		UnidadeOrganizacional unidadeEncerramentoRA = 
			fachada.obterUnidadeEncerramentoRA(registroAtendimentoHelper.getRegistroAtendimento().getId());
		
		if(unidadeEncerramentoRA != null){
			
			unidadeEncerramento = unidadeEncerramentoRA.getId() + " - " + unidadeEncerramentoRA.getDescricao();
			
			RegistroAtendimentoUnidade registroAtendimentoUnidade = 
				this.consultarRegistroAtendimentoUnidade(registroAtendimentoHelper.getRegistroAtendimento().getId(),unidadeEncerramentoRA.getId());
			
			Usuario usuario = registroAtendimentoUnidade.getUsuario();
			if(usuario != null){
				usuarioEncerramento = usuario.getId() + " - " + usuario.getNomeUsuario();
			}
		}
		
		// Montando Bean Detail
		Collection<RelatorioConsultarRegistroAtendimentoDetailBean> colecaoDetail = new ArrayList<RelatorioConsultarRegistroAtendimentoDetailBean>();
		
		Collection colecaoDadosReiteracao = new ArrayList();
		
		colecaoDadosReiteracao = fachada.pesquisarDadosReiteracao(registroAtendimentoHelper.getRegistroAtendimento().getId());
		
		Iterator iColecaoDadosReiteracao = colecaoDadosReiteracao.iterator();
		
		while (iColecaoDadosReiteracao.hasNext()){
			Object[] dadosReiteracao = (Object[]) iColecaoDadosReiteracao.next();
			
			Date dt = (Date) dadosReiteracao[0];
			String dataHora = Util.formatarDataComHora(dt);
			
			String nomeSolicitante = "";
			
			if (dadosReiteracao[1] != null){
				nomeSolicitante = dadosReiteracao[1].toString();
			} else if (dadosReiteracao[2] != null){
				nomeSolicitante = dadosReiteracao[2].toString();
			} else if (dadosReiteracao[3] != null){
				nomeSolicitante = dadosReiteracao[3].toString();
			}
			
			String fone = "";

			if (dadosReiteracao[8] == null && dadosReiteracao[6] != null && dadosReiteracao[7] != null){
				fone = "(" + dadosReiteracao[6] + ")" + " - " + dadosReiteracao[7];
			} else if (dadosReiteracao[6] == null && dadosReiteracao[7] != null && dadosReiteracao[8] != null){
				fone = dadosReiteracao[7] + " - " + dadosReiteracao[8];
			} else if (dadosReiteracao[6] == null && dadosReiteracao[8] == null && dadosReiteracao[7] != null){
				fone = dadosReiteracao[7].toString();
			} else if (dadosReiteracao[8] != null && dadosReiteracao[6] != null && dadosReiteracao[7] != null){
				fone = "(" + dadosReiteracao[6] + ")" + " - " + dadosReiteracao[7] + " - " + dadosReiteracao[8];
			}
			
				relatorioDetailBean = new RelatorioConsultarRegistroAtendimentoDetailBean(
				
					// Data Hora
					dataHora,
					
					// Nome do Solicidante
					nomeSolicitante,
					
					// Cliente
					dadosReiteracao[4] == null ? ""
							: dadosReiteracao[4]
									.toString(),
									
					// Unidade
					dadosReiteracao[5] == null ? ""
							: dadosReiteracao[5]
									.toString(),
									
					// Telefone
					fone
				);
			
			colecaoDetail.add(relatorioDetailBean);
		}
		
		

		relatorioBean = new RelatorioConsultarRegistroAtendimentoBean(

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

				// Dados da Reiteração

				colecaoDetail,

				// Dados do Encerramento

				// Motivo do Encerramento
				registroAtendimentoHelper.getRegistroAtendimento()
						.getAtendimentoMotivoEncerramento() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getAtendimentoMotivoEncerramento()
								.getDescricao(),

				// Número RA Referência
				registroAtendimentoHelper.getRAAssociado() == null ? ""
						: registroAtendimentoHelper.getRAAssociado().getId()
								.toString(),

				// Situação RA Referência
				registroAtendimentoHelper.getDescricaoSituacaoRAAssociado() == null ? ""
						: registroAtendimentoHelper
								.getDescricaoSituacaoRAAssociado(),

				// Data Encerramento
				registroAtendimentoHelper.getRegistroAtendimento()
						.getDataEncerramento() == null ? ""
						: Util
								.formatarDataComHora(registroAtendimentoHelper
										.getRegistroAtendimento()
										.getDataEncerramento()),
//				registroAtendimentoHelper.getRegistroAtendimento()
//						.getAtendimentoMotivoEncerramento() == null ? ""
//						: registroAtendimentoHelper.getRegistroAtendimento()
//								.getAtendimentoMotivoEncerramento()
//								.getUltimaAlteracao() == null ? "" : Util
//								.formatarDataComHora(registroAtendimentoHelper
//										.getRegistroAtendimento()
//										.getAtendimentoMotivoEncerramento()
//										.getUltimaAlteracao()),

				// Unidade Encerramento
				unidadeEncerramento,

				// Usuário Encerramento
				usuarioEncerramento,

				// Parecer Encerramento
				registroAtendimentoHelper.getRegistroAtendimento()
						.getParecerEncerramento() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getParecerEncerramento(),
				
				// Rota
				registroAtendimentoHelper.getCodigoRota() == null? "": registroAtendimentoHelper.getCodigoRota().toString(),
						
				// Sequencial Rota
				registroAtendimentoHelper.getSequencialRota() == null? "": registroAtendimentoHelper.getSequencialRota().toString() 
				
		);

		// adiciona o bean a coleção
		relatorioBeans.add(relatorioBean);
		
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.CONSULTAR_REGISTRO_ATENDIMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	/**
	 * Consulta o Registro Atendimento Unidade pelo id da RA
	 * 
	 * @author Rafael Pinto
	 * @created 09/08/2006
	 */
	private RegistroAtendimentoUnidade consultarRegistroAtendimentoUnidade(Integer idRA,Integer idUnidade){

		RegistroAtendimentoUnidade retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimentoUnidade = null; 

		FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();

		filtroRegistroAtendimentoUnidade.adicionarParametro(
			new ParametroSimples(FiltroRegistroAtendimentoUnidade.REGISTRO_ATENDIMENTO_ID,idRA));

		filtroRegistroAtendimentoUnidade.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoUnidade.UNIDADE_ORGANIZACIONAL_ID,idUnidade));
		
		filtroRegistroAtendimentoUnidade.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoUnidade.ATENDIMENTO_RELACAO_TIPO, AtendimentoRelacaoTipo.ENCERRAR));

		filtroRegistroAtendimentoUnidade.adicionarCaminhoParaCarregamentoEntidade("usuario");
		
		colecaoRegistroAtendimentoUnidade = 
			fachada.pesquisar(filtroRegistroAtendimentoUnidade,RegistroAtendimentoUnidade.class.getName());

		if (colecaoRegistroAtendimentoUnidade != null && !colecaoRegistroAtendimentoUnidade.isEmpty()) {
			retorno = (RegistroAtendimentoUnidade) Util.retonarObjetoDeColecao(colecaoRegistroAtendimentoUnidade);
			
		} 
		
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarRegistroAtendimento", this);
	}
}
