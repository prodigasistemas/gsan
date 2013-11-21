package gcom.gui.relatorio.cobranca;

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.FiltroPagamentoHistorico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.EmpresaCobrancaConta;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;



/**
 * 
 * 
 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
 * 
 * @author Hugo Azevedo 
 * @date 02/07/2011
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */


public class RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultado extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultado(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO);
	}
	


	@Deprecated
	public RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultado() {
		super(null, "");
	}
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		
		//Parâmetros vindos do gerar relatório
		Collection colecaoImovelOS = (Collection)getParametro("colecaoImovelOS");
		String tipoServico = (String)getParametro("tipoServico");
		int tipoFormatoRelatorio = (Integer)getParametro("tipoFormatoRelatorio");
		String[] categoriaImovel = (String[])getParametro("categoriaImovel");
		String[] perfilImovel = (String[])getParametro("perfilImovel");
		String[] gerenciaRegional = (String[])getParametro("gerenciaRegional");
		String[] unidadeNegocio = (String[])getParametro("unidadeNegocio");
		String descLocalidadeInicial = (String)getParametro("descLocalidadeInicial");
		String descLocalidadeFinal = (String)getParametro("descLocalidadeFinal");
		String idSetorComercialInicial = (String)getParametro("idSetorComercialInicial");
		String idSetorComercialFinal = (String)getParametro("idSetorComercialFinal");
		String idQuadraInicial =(String)getParametro("idQuadraInicial");
		String idQuadraFinal =(String)getParametro("idQuadraFinal");
		
		
		
		//Variáveis
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Map parametros = new HashMap();

		

		
		//valor de retorno
		byte[] retorno = null;
	
		//Verifica se a filtragem de gerência regional foi realizada
		boolean filtrouGerencia = false;
		if(gerenciaRegional != null && gerenciaRegional.length > 0)
			filtrouGerencia = true;
		
		
		//Montar Cabeçalho
		parametros = montarCabecalho(categoriaImovel,perfilImovel,gerenciaRegional,unidadeNegocio,descLocalidadeInicial,descLocalidadeFinal,
				idSetorComercialInicial,idSetorComercialFinal,idQuadraInicial,idQuadraFinal,tipoServico,sistemaParametro, fachada);
		
		//Montar Beans
		RelatorioDataSource ds = montarBeans(colecaoImovelOS,fachada,tipoServico,filtrouGerencia);
		
		//Montar relatório
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO,
		parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO,
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
	 * 
	 * Método auxiliar para montar o cabeçalho do relatório
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	private Map montarCabecalho(String[] categoriaImovel, String[] perfilImovel, String[] gerenciaRegional, String[] unidadeNegocio, String descLocalidadeInicial, String descLocalidadeFinal, String idSetorComercialInicial, String idSetorComercialFinal, String idQuadraInicial, String idQuadraFinal, String tipoServico,SistemaParametro sistemaParametro, Fachada fachada) {
		
		HashMap parametros = new HashMap();
		
		//Imagem do relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		/*
		 * Verifica se alguma categoria foi selecionada.
		 * Caso não haja, insere 'Todas'
		 */
		if(categoriaImovel != null && categoriaImovel.length > 0){
			for(int i = 0;i < categoriaImovel.length; i ++){
				Integer id = new Integer(categoriaImovel[i]);
				categoriaImovel[i] = this.obterDescricaoCategoria(id,fachada); 
			}
			String categorias = StringUtils.join(categoriaImovel,",");
			parametros.put("categorias",categorias);
		}
		else{
			parametros.put("categorias","Todas");
		}
		
		/*
		 * Verifica se algum perfil de imóvel foi selecionado.
		 * Caso não haja, insere 'Todas'
		 */
		if(perfilImovel != null && perfilImovel.length > 0){
			for(int i = 0; i < perfilImovel.length; i++){
				Integer id = new Integer(perfilImovel[i]);
				perfilImovel[i] = this.obterDescricaoPerfil(id,fachada);
			}
			
			String perfilI = StringUtils.join(perfilImovel,",");
			parametros.put("perfilI",perfilI);
		}
		else{
			parametros.put("perfilI","Todas");
		}
		
		//Insere os parâmetros na hash de retorno
		parametros.put("descLocalidadeInicial",descLocalidadeInicial);
		parametros.put("descLocalidadeFinal",descLocalidadeFinal);
		parametros.put("descLocalidadeFinal",descLocalidadeFinal);
		parametros.put("idSetorComercialInicial",idSetorComercialInicial);
		parametros.put("idSetorComercialFinal",idSetorComercialFinal);
		parametros.put("idQuadraInicial",idQuadraInicial);
		parametros.put("idQuadraFinal",idQuadraFinal);
		parametros.put("tipoServico",obterDescricaoTipoServico(new Integer(tipoServico),fachada));
		//parametros.put("tipoServico",tipoServico);
		
		//retorna ao método principal
		return parametros;
		
	}
	
	/**
	 * 
	 * Método auxiliar para montar os dados do relatório
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	private RelatorioDataSource montarBeans(Collection colecaoImovelOS, Fachada fachada, String tipoServico, boolean filtrouGerencia) {
	
		//lista de retorno
		ArrayList<RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultadoBean> beans = new ArrayList<RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultadoBean>();
		
		//Iteração com cada imóvel retornado pelo filtro
		Iterator it = colecaoImovelOS.iterator();
		while(it.hasNext()){
			HashMap mapa = (HashMap)it.next();
			
			Integer id = (Integer)mapa.get("imovel");
			
			/*FiltroClienteImovel filtroCliIm = new FiltroClienteImovel();
			filtroCliIm.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
			filtroCliIm.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);
			filtroCliIm.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_RELACAO_TIPO);
			filtroCliIm.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
			filtroCliIm.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID,ClienteRelacaoTipo.USUARIO));
			filtroCliIm.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,id));*/
		
			//Busca o cliente do tipo USUARIO do imóvel
			Collection colecaoCliente = fachada.obterClienteImovelporRelacaoTipo(id,new Integer(ClienteRelacaoTipo.USUARIO));
			
			Cliente cliente = null;
			if(colecaoCliente.size() > 0){
				ClienteImovel cliim = (ClienteImovel)Util.retonarObjetoDeColecao(colecaoCliente);
				cliente = cliim.getCliente();
			}else{
				System.out.println("Aqui");
			}
			
			
			
			//Ordem Servico
			/*FiltroOrdemServico filtroOS = new FiltroOrdemServico();
			filtroOS.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.IMOVEL);
			filtroOS.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.SERVICO_TIPO);
			filtroOS.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
			filtroOS.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID_IMOVEL,id));
			if(tipoServico != null && !tipoServico.equals("-1"))
				filtroOS.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID_SERVICO_TIPO,new Integer(tipoServico)));
			
			Collection colecaoOS = fachada.pesquisar(filtroOS,OrdemServico.class.getName());*/
			
			
			//Retorna as Ordens de Serviço do imóvel com o tipo de serviço selecionado
			Collection colecaoOS = fachada.obterOSImovelTipoServico(id,new Integer(tipoServico));
			
			
			//Cobranca Conta
			/*FiltroEmpresaCobrancaConta filtroEmpresaCC = new FiltroEmpresaCobrancaConta();
			filtroEmpresaCC.adicionarCaminhoParaCarregamentoEntidade(FiltroEmpresaCobrancaConta.CONTA);
			filtroEmpresaCC.adicionarCaminhoParaCarregamentoEntidade(FiltroEmpresaCobrancaConta.CONTA_NAO_HISTORICO);
			filtroEmpresaCC.adicionarCaminhoParaCarregamentoEntidade(FiltroEmpresaCobrancaConta.CONTA_HISTORICO);
			filtroEmpresaCC.adicionarCaminhoParaCarregamentoEntidade(FiltroEmpresaCobrancaConta.CONTA_HISTORICO_DC_SITUACAO_ATUAL);
			filtroEmpresaCC.adicionarCaminhoParaCarregamentoEntidade(FiltroEmpresaCobrancaConta.CONTA_NAO_HISTORICO_DC_SITUACAO_ATUAL);
			filtroEmpresaCC.adicionarCaminhoParaCarregamentoEntidade(FiltroEmpresaCobrancaConta.CONTA_HISTORICO);
			filtroEmpresaCC.adicionarParametro(new ParametroSimples(FiltroEmpresaCobrancaConta.IMOVEL_ID,id));
			
			Collection colecaoEmpresaCC = fachada.pesquisar(filtroEmpresaCC,EmpresaCobrancaConta.class.getName());*/
			
			//Retorna a coleção de cobranças do imóvel
			Collection colecaoEmpresaCC = fachada.obterColecaoEmpresaCobrancaContaporImovel(id);
			
			//Iteração com cada ordem de serviço retornada
			Iterator it2 = colecaoOS.iterator();
			while(it2.hasNext()){
				OrdemServico os = (OrdemServico)it2.next();
				
				
				RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultadoBean bean = new RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultadoBean();
				bean.setClienteNome(cliente.getNome());
				//Caso tenha filtrado por gerência, colocar no bean o codigo e descrição da mesma.
				//Caso contrário, colocar 'Todas'
				if(filtrouGerencia){
					bean.setIdGerenciaRegional(((Integer)mapa.get("idGerenciaRegional")).toString());
					bean.setDescGerenciaRegional((String)mapa.get("descGerenciaRegional"));
				}	
				else
					bean.setDescGerenciaRegional("Todas");
				
				//Dados do bean
				bean.setIdUnidadeNegocio(((Integer)mapa.get("idUnidadeNegocio")).toString());
				bean.setDescUnidadeNegocio((String)mapa.get("descUnidadeNegocio"));
				bean.setDataEncerramento(Util.formatarData(os.getDataEncerramento()));
				bean.setDataGeracao(Util.formatarData(os.getDataGeracao()));
				bean.setDescricaoServico(os.getServicoTipo().getDescricao());
				bean.setImovelMatricula(os.getImovel().getId().toString());
		
				if(os.getAtendimentoMotivoEncerramento() != null)
					bean.setMotivoEncerramento(os.getAtendimentoMotivoEncerramento().getDescricao());
				
				bean.setNumeroOS(os.getId().toString());
				bean.setQtdContas(colecaoEmpresaCC.size()+"");
				
				//Utiliza o método auxiliar obterValorEnviado para calcular o valor
				bean.setValorEnviado(this.obterValorEnviado(colecaoEmpresaCC,fachada));
				
				//Utiliza o método auxiliar obterValorPago para calcular o valor
				bean.setValorPago(this.obterValorPago(colecaoEmpresaCC,fachada));
				
				//adiciona o bean a coleção de retorno
				beans.add(bean);
			}	
		}
		
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		return ds;
	}
	
	
	/**
	 * 
	 * Método auxiliar para retornar o valor enviado do imóvel
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	private BigDecimal obterValorEnviado(Collection colecaoEmpresaCC, Fachada fachada){
		
		BigDecimal retorno = new BigDecimal("0");
		
		//Itera entre os comandos retornados
		Iterator it = colecaoEmpresaCC.iterator();
		while(it.hasNext()){
			EmpresaCobrancaConta ecc = (EmpresaCobrancaConta)it.next();
			
			//Contas que foram pra histórico
			if(ecc.getContaGeral().getIndicadorHistorico() == ConstantesSistema.INDICADOR_USO_ATIVO){
				
				/*
				 * Somatório dos valores das contas 
				 * Valor = Consumo água + Consumo Esgoto + Débitos - Créditos    
				 */
				BigDecimal valorConta = ecc.getContaGeral().getContaHistorico().getValorAgua()
																		  .add(ecc.getContaGeral().getContaHistorico().getValorEsgoto()) 
																		  .add(ecc.getContaGeral().getContaHistorico().getValorDebitos())
																		  .subtract(ecc.getContaGeral().getContaHistorico().getValorCreditos());
				
				/*
				 * Caso tenha impostos associado, diminuir do valor final
				 */
				if(ecc.getContaGeral().getContaHistorico().getValorImposto() != null)
					valorConta = valorConta.subtract(ecc.getContaGeral().getContaHistorico().getValorImposto());
				
				//Somar ao valor de retorno e iteragir com a próxima conta
				retorno = retorno.add(valorConta);
				
				
			}
			
			//Contas que não foram pra histórico
			else{
				
				/*
				 * Somatório dos valores das contas 
				 * Valor = Consumo água + Consumo Esgoto + Débitos - Créditos    
				 */
				BigDecimal valorConta = ecc.getContaGeral().getConta().getValorAgua()
																		  .add(ecc.getContaGeral().getConta().getValorEsgoto()) 
																		  .add(ecc.getContaGeral().getConta().getDebitos())
																		  .subtract(ecc.getContaGeral().getConta().getValorCreditos());
				
				/*
				 * Caso tenha impostos associado, diminuir do valor final
				 */
				if(ecc.getContaGeral().getConta().getValorImposto() != null)
					valorConta = valorConta.subtract(ecc.getContaGeral().getConta().getValorImposto());
				
				//Somar ao valor de retorno e iteragir com a próxima conta
				retorno = retorno.add(valorConta);
				
			}
		
		}
		
		return retorno;
	}
	
	
	/**
	 * 
	 * Método auxiliar para retornar o valor pago do imóvel
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	private BigDecimal obterValorPago(Collection colecaoEmpresaCC, Fachada fachada){
		
		BigDecimal retorno = new BigDecimal("0");
		
		//Itera entre os comandos retornados
		Iterator it = colecaoEmpresaCC.iterator();
		while(it.hasNext()){
			BigDecimal valorContaPaga = new BigDecimal("0");
			BigDecimal valorContaParcelada = new BigDecimal("0");
			
			EmpresaCobrancaConta ecc = (EmpresaCobrancaConta)it.next();
			if(ecc.getContaGeral().getIndicadorHistorico() == ConstantesSistema.INDICADOR_USO_ATIVO){
				
				//Conta Paga
				FiltroPagamentoHistorico filtroPag = new FiltroPagamentoHistorico();
				filtroPag.adicionarParametro(new ParametroSimples(FiltroPagamentoHistorico.CONTA_ID,ecc.getContaGeral().getContaHistorico().getId()));
	
				ArrayList pag = new ArrayList(fachada.pesquisar(filtroPag,PagamentoHistorico.class.getName()));
				if(pag.size() > 0){
					PagamentoHistorico p = (PagamentoHistorico) pag.get(0);
					valorContaPaga = valorContaPaga.add(p.getValorPagamento());
				}
				
				
				//Conta Parcelada
				if(ecc.getContaGeral().getContaHistorico().getDebitoCreditoSituacaoAtual().getId().intValue() == DebitoCreditoSituacao.PARCELADA.intValue()){
					valorContaParcelada = valorContaParcelada.add(ecc.getContaGeral().getContaHistorico().getValorAgua()
															 .add(ecc.getContaGeral().getContaHistorico().getValorEsgoto()) 
															 .add(ecc.getContaGeral().getContaHistorico().getValorDebitos())
															 .subtract(ecc.getContaGeral().getContaHistorico().getValorCreditos()));
															 
					
				if(ecc.getContaGeral().getContaHistorico().getValorImposto() != null)
						valorContaParcelada = valorContaParcelada.subtract(ecc.getContaGeral().getContaHistorico().getValorImposto());
					
				}
				
				retorno = retorno.add(valorContaPaga)
				                 .add(valorContaParcelada);
				
			}
			else{
				//Conta Paga
				FiltroPagamento filtroPag = new FiltroPagamento();
				filtroPag.adicionarParametro(new ParametroSimples(FiltroPagamento.CONTA_ID,ecc.getContaGeral().getConta().getId()));
	
				ArrayList pag = new ArrayList(fachada.pesquisar(filtroPag,Pagamento.class.getName()));
				if(pag.size() > 0){
					Pagamento p = (Pagamento) pag.get(0);
					valorContaPaga = valorContaPaga.add(p.getValorPagamento());
				}
				
				
				//Conta Parcelada
				if(ecc.getContaGeral().getConta().getDebitoCreditoSituacaoAtual().getId().intValue() == DebitoCreditoSituacao.PARCELADA.intValue()){
					valorContaParcelada = valorContaParcelada.add(ecc.getContaGeral().getConta().getValorAgua()
															 .add(ecc.getContaGeral().getConta().getValorEsgoto()) 
															 .add(ecc.getContaGeral().getConta().getDebitos())
															 .subtract(ecc.getContaGeral().getConta().getValorCreditos()));
				
					if(ecc.getContaGeral().getConta().getValorImposto() != null)
						valorContaParcelada = valorContaParcelada.subtract(ecc.getContaGeral().getConta().getValorImposto());
															
				}
				
				retorno = retorno.add(valorContaPaga)
				                 .add(valorContaParcelada);

			}
		}
		
		return retorno;
	}
	
	//Método auxiliar para recuperar a descrição do perfil
	private String obterDescricaoPerfil(Integer id,Fachada fachada){
		String retorno = "";
		FiltroImovelPerfil filtroIP = new FiltroImovelPerfil();
		filtroIP.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID,id));
		Collection colecao = fachada.pesquisar(filtroIP,ImovelPerfil.class.getName());
		if(colecao.size() > 0){
			ImovelPerfil ip = (ImovelPerfil)Util.retonarObjetoDeColecao(colecao);
			retorno = ip.getDescricao();
		}
		return retorno;
		
	}
	
	//	Método auxiliar para recuperar a descrição da categoria
	private String obterDescricaoCategoria(Integer id,Fachada fachada){
		String retorno = "";
		FiltroCategoria filtroCat = new FiltroCategoria();
		filtroCat.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,id));
		Collection colecao = fachada.pesquisar(filtroCat,Categoria.class.getName());
		if(colecao.size() > 0){
			Categoria cat = (Categoria)Util.retonarObjetoDeColecao(colecao);
			retorno = cat.getDescricao();
		}
		return retorno;
	}
	
	//Método auxiliar para recuperar a descrição do tipo de serviço
	private String obterDescricaoTipoServico(Integer id, Fachada fachada){
		String retorno = "";
		FiltroServicoTipo filtroST = new FiltroServicoTipo();
		filtroST.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID,id));
		Collection colecao = fachada.pesquisar(filtroST,ServicoTipo.class.getName());
		if(colecao.size() > 0){
			ServicoTipo st = (ServicoTipo)Util.retonarObjetoDeColecao(colecao);
			retorno = st.getDescricao();
		}
		return retorno;
	}
	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		HashMap mapa = this.retornarParametrosContadorOS();
		Collection colecaoImovelOS = (Collection) mapa.get("colecaoImovelOS");
		String tipoServico = (String) mapa.get("tipoServico");
		
		int retorno = Fachada.getInstancia().obterTotalOSColecaoImovelTipoServico(colecaoImovelOS,new Integer(tipoServico));

		//int retorno = 2;
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultado", this);
	}
	
	
	//Método auxiliar para repassar os parâmetros para o avaliador batch
	private HashMap retornarParametrosContadorOS(){
		HashMap mapa = new HashMap();
		
		Collection colecaoImovelOS = (Collection)getParametro("colecaoImovelOS");
		String tipoServico = (String)getParametro("tipoServico");
		
		mapa.put("colecaoImovelOS",colecaoImovelOS);
		mapa.put("tipoServico",tipoServico);
		
		return mapa;	
	}
	
}
