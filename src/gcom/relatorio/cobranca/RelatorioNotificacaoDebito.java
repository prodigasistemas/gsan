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
 * classe respons�vel por criar o relat�rio de contas
 * 
 * @author Rafael Corr�a
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
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Integer idCobrancaAcaoCronograma = (Integer) getParametro("idCobrancaAcaoCronograma");
		Integer idCobrancaAcaoComando = (Integer) getParametro("idCobrancaAcaoComando");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		/**
		 * Enviar documentos de cobran�a para endere�o de correspond�ncia
		 * 
		 * @author Wellington Rocha
		 * @date 21/06/2013*/
		String tipoEndRelatorio = (String) getParametro ("tipoEndRelatorio");

		//quantidades de relatorio a serem impressos em cada p�gina
		String quantidadeRelatorios = (String) getParametro("quantidadeRelatorios");

		//tamanho m�ximo de contas a aparecerem no relat�rio.
		int tamanhoMaximoDebito = 15;

		// valor de retorno
		byte[] retorno = null;


		Fachada fachada = Fachada.getInstancia();

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
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
		
		//Caso a empresa seja COSANPA,  modificar a quantidade de d�bitos que ser�o mostrados no relatorio.
		if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)){
			tamanhoMaximoDebito = 35;
		}
		
		
		// cole��o de beans do relat�rio
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
		
		//Verifica se a empresa � CAERN para alterar a partir de quanto tempo sera 
		//interrompido o fornecimento de agua.
		if ( sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
			SistemaParametro.EMPRESA_CAERN) ) {
			
			parametros.put("quantidadeDias", "30� (trig�simo)");
			
		} else {
			parametros.put("quantidadeDias", "10� (d�cimo)");
		}
		
		
		if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)){
			
			if(tipoEndRelatorio.equals("1")||
					tipoEndRelatorio.equals("2")){
				//Array list adicionado para executar o relat�rio principal.
				ArrayList array = new ArrayList();
				array.add("relatorio");				
				RelatorioDataSource dsRelatorio = new RelatorioDataSource(array);
				
				//Data Source real, usado para preencher o subrelat�rio.
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
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.ANORMALIDADE_CONSUMO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
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
