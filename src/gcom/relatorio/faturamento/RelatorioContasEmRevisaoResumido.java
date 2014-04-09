package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ContasEmRevisaoRelatorioHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de volumes faturados
 * 
 * @author Rafael Corrêa
 * @created 12/09/2007
 */
public class RelatorioContasEmRevisaoResumido extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioContasEmRevisaoResumido(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONTAS_EM_REVISAO_RESUMIDO);
	}

	@Deprecated
	public RelatorioContasEmRevisaoResumido() {
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

		Integer idGerenciaRegional = (Integer) getParametro("idGerenciaRegional");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer codigoSetorComercialInicial = (Integer) getParametro("codigoSetorComercialInicial");
		Integer codigoSetorComercialFinal = (Integer) getParametro("codigoSetorComercialFinal");
		Collection<Integer> colecaoIdsMotivoRevisao = (Collection<Integer>) getParametro("colecaoIdsMotivoRevisao");
		Integer idImovelPerfil = (Integer) getParametro("idImovelPerfil");
		Integer referenciaInicial = (Integer) getParametro("referenciaInicial");
		Integer referenciaFinal = (Integer) getParametro("referenciaFinal");
		Integer idCategoria = (Integer) getParametro("idCategoria");
		Integer idEsferaPoder = (Integer) getParametro("idEsferaPoder");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioContasEmRevisaoBean relatorioBean = null;

		Collection colecaoContasEmRevisaoRelatorioHelper = fachada
				.pesquisarDadosRelatorioContasRevisaoResumido(idGerenciaRegional, idUnidadeNegocio, 
						idLocalidadeInicial, idLocalidadeFinal, 
						codigoSetorComercialInicial, codigoSetorComercialFinal,
						colecaoIdsMotivoRevisao, idImovelPerfil, referenciaInicial,
						referenciaFinal, idCategoria, idEsferaPoder);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoContasEmRevisaoRelatorioHelper != null
				&& !colecaoContasEmRevisaoRelatorioHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoContasEmRevisaoRelatorioHelperIterator = colecaoContasEmRevisaoRelatorioHelper
					.iterator();
			
			// Cria as variáveis para verificar se os totalizadores de gerência
			// e elo serão mostrados no relatório
			String imprimeElo = null;
			
			if (idGerenciaRegional != null) {
				imprimeElo = "SIM";
			}
			
			// Cria as variáveis de totalização
			Integer qtdeContasTotalLocalidade = new Integer("0");
			BigDecimal valorContasTotalLocalidade = new BigDecimal("0.00");
			
			Integer qtdeContasTotalElo = new Integer("0");
			BigDecimal valorContasTotalElo = new BigDecimal("0.00");
			
			Integer qtdeContasTotalGerenciaRegional = new Integer("0");
			BigDecimal valorContasTotalGerenciaRegional = new BigDecimal("0.00");

			Integer idLocalidadeAnterior = null;
			Integer idMotivoAnterior = null;
			Integer idEloAnterior = null;
			Integer idGerenciaRegionalAnterior = null;
			boolean zerarLocalidade = false;
			boolean zerarElo = false;
			boolean zerarGerenciaRegional = false;

			boolean primeiraVez = true;

			// laço para criar a coleção de parâmetros da analise
			while (colecaoContasEmRevisaoRelatorioHelperIterator.hasNext()) {
				
				ContasEmRevisaoRelatorioHelper contasEmRevisaoRelatorioHelper = (ContasEmRevisaoRelatorioHelper) colecaoContasEmRevisaoRelatorioHelperIterator
						.next();
				
				// Seta os valores das variáveis de controle de totalização para
				// verificar quando deve ser zerado os totalizadores
				if (idLocalidadeAnterior == null) {
					idLocalidadeAnterior = contasEmRevisaoRelatorioHelper
							.getIdLocalidade();
				}
				
				if (idMotivoAnterior == null) {
					idMotivoAnterior = contasEmRevisaoRelatorioHelper
							.getIdMotivoRevisao();
				}
				
				if (idEloAnterior == null) {
					idEloAnterior = contasEmRevisaoRelatorioHelper
							.getIdElo();
				}

				if (idGerenciaRegionalAnterior == null) {
					idGerenciaRegionalAnterior = contasEmRevisaoRelatorioHelper
							.getIdGerenciaRegional();
				}

				// Cria as variáveis de motivo e localidade atual e anterior
				// para ser verificado no relatório se deve ser mostrado o valor
				// da localidade
				String motivoLocalidadeAnterior = "";
				String motivoLocalidade = "";
				
				if (!primeiraVez) {
					motivoLocalidadeAnterior = motivoLocalidadeAnterior + idMotivoAnterior;
					motivoLocalidadeAnterior = motivoLocalidadeAnterior + idLocalidadeAnterior;
				} else {
					primeiraVez = false;
				}
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// Gerência Regional
				String gerenciaRegional = "";

				if (contasEmRevisaoRelatorioHelper.getIdGerenciaRegional() != null) {
					gerenciaRegional = contasEmRevisaoRelatorioHelper
							.getIdGerenciaRegional()
							+ " - "
							+ contasEmRevisaoRelatorioHelper
									.getNomeGerenciaRegional();
					
					// Caso tenha mudado a gerência regional do imóvel seta a
					// variável para true, para posteriormente zerar todas as
					// variáveis de totalização da gerência regional
					if (!idGerenciaRegionalAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdGerenciaRegional())) {
						zerarGerenciaRegional = true;
					}
					
					// Caso tenha mudado o elo do imóvel seta a variável para
					// true, para posteriormente zerar todas as variáveis de
					// totalização do elo
					if (!idEloAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdElo())) {
						zerarElo = true;
					}
				}

				// Elo
				String elo = "";

				if (contasEmRevisaoRelatorioHelper.getIdElo() != null) {
					elo = contasEmRevisaoRelatorioHelper.getIdElo() + " - "
							+ contasEmRevisaoRelatorioHelper.getNomeElo();
				}

				
				
				// Motivo da Reclamação
				String motivoReclamacao = "";

				if (contasEmRevisaoRelatorioHelper.getIdMotivoRevisao() != null) {
					motivoReclamacao = contasEmRevisaoRelatorioHelper
							.getIdMotivoRevisao()
							+ " - "
							+ contasEmRevisaoRelatorioHelper
									.getDescricaoMotivoRevisao();
					
					motivoLocalidade = motivoLocalidade
							+ contasEmRevisaoRelatorioHelper
									.getIdMotivoRevisao();
					
					// Caso tenha mudado o motivo de revisão seta a variável
					// para true, para posteriormente zerar todas as variáveis
					// de totalização da localidade
					if (!idMotivoAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdMotivoRevisao())) {
						zerarLocalidade = true;
						zerarElo = true;
						zerarGerenciaRegional = true;
					}
				}
				
				// Localidade
				String localidade = "";

				if (contasEmRevisaoRelatorioHelper.getIdLocalidade() != null) {
					localidade = contasEmRevisaoRelatorioHelper
							.getIdLocalidade()
							+ " - "
							+ contasEmRevisaoRelatorioHelper
									.getNomeLocalidade();
					
					motivoLocalidade = motivoLocalidade
							+ contasEmRevisaoRelatorioHelper.getIdLocalidade();

					// Caso tenha mudado a localidade do imóvel seta a variável
					// para true, para posteriormente zerar todas as variáveis
					// de totalização da localidade
					if (!idLocalidadeAnterior
							.equals(contasEmRevisaoRelatorioHelper
									.getIdLocalidade())) {
						zerarLocalidade = true;
					}
				}
				
				// Zera os totalizadores da localidade
				if (zerarLocalidade) {
					qtdeContasTotalLocalidade = new Integer("0");
					valorContasTotalLocalidade = new BigDecimal("0.00");

					zerarLocalidade = false;
					idLocalidadeAnterior = contasEmRevisaoRelatorioHelper
							.getIdLocalidade();
					idMotivoAnterior = contasEmRevisaoRelatorioHelper
							.getIdMotivoRevisao();
				}
				
				// Zera os totalizadores do elo
				if (zerarElo) {
					qtdeContasTotalElo = new Integer("0");
					valorContasTotalElo = new BigDecimal("0.00");

					zerarElo = false;
					idEloAnterior = contasEmRevisaoRelatorioHelper
							.getIdElo();
				}

				// Zera os totalizadores da gerência regional
				if (zerarGerenciaRegional) {
					qtdeContasTotalGerenciaRegional = new Integer("0");
					valorContasTotalGerenciaRegional = new BigDecimal("0.00");

					zerarGerenciaRegional = false;
					idGerenciaRegionalAnterior = contasEmRevisaoRelatorioHelper
							.getIdGerenciaRegional();
				}


				// Mês/Ano da Fatura
				String mesAnoFatura = "";
				
				if (contasEmRevisaoRelatorioHelper.getAnoMesReferenciaConta() != null) {
					mesAnoFatura = Util
							.formatarMesAnoReferencia(contasEmRevisaoRelatorioHelper
									.getAnoMesReferenciaConta());
				}
				
				// Quantidade de Contas
				String qtdeContas = "";
				
				if (contasEmRevisaoRelatorioHelper.getQtdeContas() != null) {
					qtdeContas = contasEmRevisaoRelatorioHelper.getQtdeContas().toString(); 
					qtdeContasTotalLocalidade = qtdeContasTotalLocalidade + contasEmRevisaoRelatorioHelper.getQtdeContas();
					qtdeContasTotalElo = qtdeContasTotalElo + contasEmRevisaoRelatorioHelper.getQtdeContas();
					qtdeContasTotalGerenciaRegional = qtdeContasTotalGerenciaRegional + contasEmRevisaoRelatorioHelper.getQtdeContas();
				}

				// Valor das Contas
				String valorConta = "";

				if (contasEmRevisaoRelatorioHelper.getValorConta() != null) {
					valorConta = Util.formatarMoedaReal(contasEmRevisaoRelatorioHelper.getValorConta());

					// Soma os valores aos totalizadores de cada grupo
					valorContasTotalLocalidade = valorContasTotalLocalidade.add(contasEmRevisaoRelatorioHelper.getValorConta());
					valorContasTotalElo = valorContasTotalElo.add(contasEmRevisaoRelatorioHelper.getValorConta());
					valorContasTotalGerenciaRegional = valorContasTotalGerenciaRegional.add(contasEmRevisaoRelatorioHelper.getValorConta());

				}
				
				relatorioBean = new RelatorioContasEmRevisaoBean();
				relatorioBean.setGerenciaRegional(gerenciaRegional);
				relatorioBean.setElo(elo);
				relatorioBean.setLocalidade(localidade);
				relatorioBean.setMesAnoFatura(mesAnoFatura);
				relatorioBean.setQtdeContas(qtdeContas);
				relatorioBean.setValorConta(valorConta);
				relatorioBean.setMotivoReclamacao(motivoReclamacao);
				
				relatorioBean.setQtdeTotalContasEmRevisaoLocalidade(qtdeContasTotalLocalidade.toString());
				relatorioBean.setValorTotalContasEmRevisaoLocalidade(Util.formatarMoedaReal(valorContasTotalLocalidade));
				
				relatorioBean.setQtdeTotalContasEmRevisaoElo(qtdeContasTotalElo.toString());
				relatorioBean.setValorTotalContasEmRevisaoElo(Util.formatarMoedaReal(valorContasTotalElo));

				relatorioBean.setQtdeTotalContasEmRevisaoGerenciaRegional(qtdeContasTotalGerenciaRegional.toString());
				relatorioBean.setValorTotalContasEmRevisaoGerenciaRegional(Util.formatarMoedaReal(valorContasTotalGerenciaRegional));	
				
				relatorioBean.setImprimeElo(imprimeElo);
				relatorioBean.setImprimeGerenciaRegional(gerenciaRegional);
				
				relatorioBean.setMotivoLocalidadeAnterior(motivoLocalidadeAnterior);
				relatorioBean.setMotivoLocalidade(motivoLocalidade);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);

			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		
		Integer anoMes = sistemaParametro.getAnoMesFaturamento();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(anoMes));

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTAS_EM_REVISAO_RESUMIDO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.CONTAS_EM_REVISAO_RESUMIDO,
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
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContasEmRevisaoResumido",
				this);
	}
}
