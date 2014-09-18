package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
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
 * classe responsável por criar o relatório de contas
 * 
 * @author Rafael Corrêa
 * @created 27/07/2009
 */
public class RelatorioNotificacaoDebito extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioNotificacaoDebito(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO);
	}

	@Deprecated
	public RelatorioNotificacaoDebito() {
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

		Integer idCobrancaAcaoCronograma = (Integer) getParametro("idCobrancaAcaoCronograma");
		Integer idCobrancaAcaoComando = (Integer) getParametro("idCobrancaAcaoComando");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		/**
		 * Enviar documentos de cobrança para endereço de correspondência
		 * 
		 * @author Wellington Rocha
		 * @date 21/06/2013*/
		String tipoEndRelatorio = (String) getParametro ("tipoEndRelatorio");

		//quantidades de relatorio a serem impressos em cada página
		String quantidadeRelatorios = (String) getParametro("quantidadeRelatorios");

		//tamanho máximo de contas a aparecerem no relatório.
		int tamanhoMaximoDebito = 15;

		// valor de retorno
		byte[] retorno = null;


		Fachada fachada = Fachada.getInstancia();

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		//Caso a empresa seja COSANPA,  modificar a quantidade de débitos que serão mostrados no relatorio.
		if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)){
			tamanhoMaximoDebito = 35;
		}
		
		
		// coleção de beans do relatório
		List relatorioBeans = (List) fachada.gerarRelatorioNotificacaoDebito(idCobrancaAcaoCronograma,
				idCobrancaAcaoComando, tamanhoMaximoDebito, quantidadeRelatorios, tipoEndRelatorio);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		
		
		String nomeEmpresa = sistemaParametro.getNomeEmpresa();
		String enderecoEmpresa = sistemaParametro.getEnderecoFormatado();
		String cepEmpresa = "CEP: " + sistemaParametro.getCep().getCepFormatado();
		String cnpjEmpresa = "CNPJ: " + Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		String foneFaxEmpresa = "FONE: " + Util.formatarTelefone(sistemaParametro.getNumeroTelefone()) + "  "
								+ "FAX: " + Util.formatarTelefone(sistemaParametro.getNumeroFax());
		
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("enderecoEmpresa", enderecoEmpresa);
		parametros.put("cepEmpresa", cepEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("foneFaxEmpresa", foneFaxEmpresa);
		
		//Verifica se a empresa é CAERN para alterar a partir de quanto tempo sera 
		//interrompido o fornecimento de agua.
		if ( sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
			SistemaParametro.EMPRESA_CAERN) ) {
			
			parametros.put("quantidadeDias", "30º (trigésimo)");
			
		} else {
			parametros.put("quantidadeDias", "10º (décimo)");
		}
		
		
		if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)){
			
			if(tipoEndRelatorio.equals("1")||
					tipoEndRelatorio.equals("2")){
				//Array list adicionado para executar o relatório principal.
				ArrayList array = new ArrayList();
				array.add("relatorio");				
				RelatorioDataSource dsRelatorio = new RelatorioDataSource(array);
				
				//Data Source real, usado para preencher o subrelatório.
				parametros.put("dataSource", ds);
				
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO_COSANPA_2_PAGINAS,				
						parametros, dsRelatorio, tipoFormatoRelatorio);
			}else{
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO_COSANPA,
						parametros, ds, tipoFormatoRelatorio);
			}
			
		}
		else{
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO,
			parametros, ds, tipoFormatoRelatorio);
		}

		
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.ANORMALIDADE_CONSUMO,
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
		AgendadorTarefas.agendarTarefa("RelatorioNotificacaoDebito",
				this);
	}
}
