package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.bean.RelacaoOrdensServicoEncerradasPendentesHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC0432] - Gerar Relatorio Acompanhamento Ordem de Servico de Hidrometro
 * 
 * Tipo 1 - Analitico - Relatorio Relacao das Ordens de Servico Encerradas/Pendentes
 * 
 * @author Ivan Sérgio
 * @created 12/12/2007, 27/03/2008
 * @alteracao: Adicionado Motivo Encerramento; Setor Comercial;
 */
public class RelatorioRelacaoOrdensServicoEncerradasPendentes extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioRelacaoOrdensServicoEncerradasPendentes(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_RELACAO_ORDENS_SERVICO_ENCERRADAS_PENDENTES);
	}

	@Deprecated
	public RelatorioRelacaoOrdensServicoEncerradasPendentes() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		String tipoOrdem = (String) getParametro("tipoOrdem");
		String situacaoOrdemServico = (String) getParametro("situacaoOrdemServico");
		String firma = (String) getParametro("firma");
		String nomeFirma = (String) getParametro("nomeFirma");
		String idLocalidadeInicial = (String) getParametro("idLocalidadeInicial");
		String idLocalidadeFinal = (String) getParametro("idLocalidadeFinal");		
		String dataEncerramentoInicial = (String) getParametro("dataEncerramentoInicial");
		String dataEncerramentoFinal = (String) getParametro("dataEncerramentoFinal");

		
		String idMotivoEncerramento = (String) getParametro("idMotivoEncerramento");

		String idSetorComercialInicial = (String) getParametro("idSetorComercialInicial");
		String idSetorComercialFinal = (String) getParametro("idSetorComercialFinal");		
		
		String codigoQuadraInicial = (String) getParametro("codigoQuadraInicial");
		String codigoQuadraFinal = (String) getParametro("codigoQuadraFinal");
		String codigoRotaInicial = (String) getParametro("codigoRotaInicial");
		String codigoRotaFinal = (String) getParametro("codigoRotaFinal");
		String sequenciaRotaInicial = (String) getParametro("sequenciaRotaInicial");
		String sequenciaRotaFinal = (String) getParametro("sequenciaRotaFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		Fachada fachada = Fachada.getInstancia();
		
		RelatorioRelacaoOrdensServicoEncerradasPendentesBean relatorioBean = null;
		
		Collection colecaoDadosHelper = fachada
				.pesquisarOrdemServicoGerarRelatorioAcompanhamentoHidrometro(
						firma, tipoOrdem, situacaoOrdemServico, idLocalidadeInicial,
						idLocalidadeFinal, dataEncerramentoInicial, dataEncerramentoFinal,
						idMotivoEncerramento, idSetorComercialInicial, idSetorComercialFinal,
						codigoQuadraInicial, codigoQuadraFinal, codigoRotaInicial,
						codigoRotaFinal, sequenciaRotaInicial, sequenciaRotaFinal);
		
		if (colecaoDadosHelper != null && !colecaoDadosHelper.isEmpty()) {
			Iterator iColecaoDados = colecaoDadosHelper.iterator();

			while (iColecaoDados.hasNext()) {
				//dados = (Object[]) iColecaoDados.next();
				RelacaoOrdensServicoEncerradasPendentesHelper helper = 
					(RelacaoOrdensServicoEncerradasPendentesHelper) iColecaoDados.next(); 
				
				relatorioBean = new RelatorioRelacaoOrdensServicoEncerradasPendentesBean();
				
				// Numero da Ordem
				relatorioBean.setNumeroOrdem(helper.getIdOrdemServico().toString());
				// Imovel Selecionado
				relatorioBean.setImovel(helper.getIdImovel().toString());
				// Localidade
				relatorioBean.setIdLocalidade(helper.getIdLocalidade().toString());
				// Inscricao do Imovel
				Imovel imovel = new Imovel();
				
				Localidade localidade = new Localidade();
				localidade.setId(helper.getIdLocalidade());
				
				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(helper.getCodigoSetorComercial());
				
				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(helper.getNumeroQuadra());
				
				imovel.setId(helper.getIdImovel());
				imovel.setLocalidade(localidade);
				imovel.setSetorComercial(setorComercial);
				imovel.setQuadra(quadra);
				imovel.setLote(helper.getLote());
				imovel.setSubLote(helper.getSubLote());
				
				relatorioBean.setInscricao(imovel.getInscricaoFormatada());
				
				// Data Geracao
				relatorioBean.setDataGeracao(helper.getDataGeracao());
				// Data Encerramento
				relatorioBean.setDataEncerramento(helper.getDataEncerramento());
				// Motivo Encerramento
				relatorioBean.setMotivoEncerramento(helper.getMotivoEncerramento());
				
				// Situacao selecionada
				relatorioBean.setSituacao(situacaoOrdemServico);
				// Tipo do Servico
				relatorioBean.setTipoServico(tipoOrdem);
				// Periodo encerramento
				if (dataEncerramentoInicial != null && !dataEncerramentoInicial.equals("") &&
						dataEncerramentoFinal != null && !dataEncerramentoFinal.equals("")) {
					relatorioBean.setPeriodoEncerramento(dataEncerramentoInicial + " à " + dataEncerramentoFinal);
				}else {
					relatorioBean.setPeriodoEncerramento("");
				}
				// Nome Localidade
				relatorioBean.setNomeLocalidade(helper.getNomeLocalidade());
				// Firma
				relatorioBean.setFirma(firma);
				// Nome Firma
				relatorioBean.setNomeFirma(nomeFirma);
				// Endereco abreviado
				relatorioBean.setEndereco(fachada.obterEnderecoAbreviadoImovel(imovel.getId()));
				
				// Id do Setor Comercial
				relatorioBean.setIdSetorComercial(helper.getIdSetorComercial().toString());
				// Codigo Setor Comercial
				relatorioBean.setCodigoSetorComercial(helper.getCodigoSetorComercial().toString());
				// Nome Setor Comercial
				relatorioBean.setNomeSetorComercial(helper.getNomeSetorComercial());
				
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		
			// Parâmetros do relatório
			Map parametros = new HashMap();
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			parametros.put("imagem", sistemaParametro.getImagemRelatorio());

			// cria uma instância do dataSource do relatório
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_RELACAO_ORDENS_SERVICO_ENCERRADAS_PENDENTES,
					parametros, ds, tipoFormatoRelatorio);

			// Grava o relatório no sistema
			try {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_RELACAO_OS_ENCERRADAS_PENDENTES,
						idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no sistema", e);
			}
			// ------------------------------------
		}else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoOrdensServicoEncerradasPendentes",
				this);
	}
}
