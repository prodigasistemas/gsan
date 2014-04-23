package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o Imóveis com Alteração de Inscrição Via Batch
 * 
 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
 * 
 * @author Hugo Leonardo
 *
 * @date 19/01/2011
 */
public class RelatorioImoveisAlteracaoInscricaoViaBatch extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisAlteracaoInscricaoViaBatch(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH);
	}

	@Deprecated
	public RelatorioImoveisAlteracaoInscricaoViaBatch() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper = 
			(FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper) getParametro("filtrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String periodo = (String) getParametro("periodo");
		
		String localidadeInicial = (String) getParametro("localidadeInicial");
		String localidadeFinal = (String) getParametro("localidadeFinal");
		
		String setorComercialInicial = (String) getParametro("setorComercialInicial");
		String setorComercialFinal = (String) getParametro("setorComercialFinal");
		
		String quadraIncial = (String) getParametro("quadraIncial");
		String quadraFinal = (String) getParametro("quadraFinal");
		
		String loteInicial = (String) getParametro("loteInicial");
		String loteFinal = (String) getParametro("loteFinal");
		
		String subLoteInicial = (String) getParametro("subLoteInicial");
		String subLoteFinal = (String) getParametro("subLoteFinal");
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioImoveisAlteracaoInscricaoViaBatchBean relatorioBean = null;

		Collection<ImovelInscricaoAlterada> colecao = fachada.pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch(relatorioHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			Imovel imovelAnterior = null;
			Imovel imovelAtual = null;
			
			// laço para criar a coleção de parâmetros da analise
			for (ImovelInscricaoAlterada helper : colecao) {
				
				// Inscrição Anterior
				imovelAnterior = new Imovel();
				imovelAnterior.setLocalidade(helper.getLocalidadeAnterior());
				imovelAnterior.setSetorComercial(helper.getSetorComercialAnterior());
				imovelAnterior.setQuadra(helper.getQuadraAnterior());
				imovelAnterior.setLote(helper.getLoteAnterior());
				imovelAnterior.setSubLote(helper.getSubLoteAnterior());
				
				String inscricaoAnterior = imovelAnterior.getInscricaoFormatada();
				
				// Inscrição Atual
				imovelAtual = new Imovel();
				imovelAtual.setLocalidade(helper.getLocalidadeAtual());
				imovelAtual.setSetorComercial(helper.getSetorComercialAtual());
				imovelAtual.setQuadra(helper.getQuadraAtual());
				imovelAtual.setLote(helper.getLoteAtual());
				imovelAtual.setSubLote(helper.getSubLoteAtual());
				
				String inscricaoAtual = imovelAtual.getInscricaoFormatada();
				
				// Data Alteração
				String dataAlteracao = "";
				if (helper.getDataAlteracaoBatch() != null ){
					
					dataAlteracao = Util.formatarData(helper.getDataAlteracaoBatch());
				}
				
				// Matricula
				String matricula = "";
				if(helper.getImovel() != null){
					
					matricula = helper.getImovel().getMatriculaFormatada();
				}
				
				// Cliente Usuário
				String clienteUsuario = "";
				if(helper.getImovel().getClienteImoveis() != null){
					
					ClienteImovel clienteImovel = (ClienteImovel) helper.getImovel().getClienteImoveis().iterator().next();
					
					clienteUsuario = clienteImovel.getCliente().getNome();
				}
				
				// Indicador Autorizado
				Short indicadorAutorizado = null;
				String autorizado = "";
				if(helper.getIndicadorAutorizado() != null){
					
					indicadorAutorizado = helper.getIndicadorAutorizado();
					if (indicadorAutorizado == 1){
						autorizado = "SIM";
					} else {
						autorizado = "NÃO";
					}
					
				}
				
				relatorioBean = 
					new RelatorioImoveisAlteracaoInscricaoViaBatchBean(
							inscricaoAnterior,
							inscricaoAtual,
							dataAlteracao,
							matricula, 
							clienteUsuario,
							autorizado);	
				
				relatorioBeans.add(relatorioBean);
			}
		}else{
			
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
	
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	
		
		String titulo = "";
		if(relatorioHelper.getEscolhaRelatorio().intValue() == 1){
			
			titulo = "Imóveis alterados com sucesso.";
		}else if(relatorioHelper.getEscolhaRelatorio().intValue() == 2){
			
			titulo = "Imóveis sem alteração devido a erro.";
		}else if(relatorioHelper.getEscolhaRelatorio().intValue() == 3){
			
			titulo = "Imóveis pendentes de alteração.";
		}
		
		parametros.put("titulo", titulo);
		
		parametros.put("periodo", periodo);
		
		parametros.put("localidadeInicial", localidadeInicial);
		parametros.put("localidadeFinal", localidadeFinal);
		
		parametros.put("setorComercialInicial", setorComercialInicial);
		parametros.put("setorComercialFinal", setorComercialFinal);
		
		parametros.put("quadraIncial", quadraIncial);
		parametros.put("quadraFinal", quadraFinal);
		
		parametros.put("loteInicial", loteInicial);
		parametros.put("loteFinal", loteFinal);
		
		parametros.put("subLoteInicial", subLoteInicial);
		parametros.put("subLoteFinal", subLoteFinal);
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH,
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
   
		retorno = Fachada.getInstancia().countTotalRelatorioImoveisAlteracaoInscricaoViaBatch(
				(FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper) 
					getParametro("filtrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper"));
		
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisAlteracaoInscricaoViaBatch", this);
	}

}
