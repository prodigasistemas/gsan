package gcom.relatorio.cobranca;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.GerarCurvaAbcDebitosHelper;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * [UC0622] - Gerar Curva ABC de Debitos
 * 
 * @author Ivan Sérgio
 * @date 07/08/2007
 */
public class RelatorioGerarCurvaAbcDebitos extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioGerarCurvaAbcDebitos
	 */
	public RelatorioGerarCurvaAbcDebitos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_GERAR_CURVA_ABC_DEBITOS);
	}
	
	@Deprecated
	public RelatorioGerarCurvaAbcDebitos() {
		super(null, "");
	}

	/**
	 * 
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Ivan Sérgio
	 * @date 07/08/2007
	 * 
	 * @return
	 * @throws TarefaException
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		

		// Parametros
		String classificacao = (String) getParametro("classificacao");
		String referenciaCobrancaInicial = (String) getParametro("referenciaCobrancaInicial");
		String referenciaCobrancaFinal = (String) getParametro("referenciaCobrancaFinal");
		String indicadorImovelMedicaoIndividualizada = 
			(String) getParametro("indicadorImovelMedicaoIndividualizada");
		String indicadorImovelParalizacaoFaturamentoCobranca = 
			(String) getParametro("indicadorImovelParalizacaoFaturamentoCobranca");
		
		// Localizacao
		String[] gerenciaRegional = (String[]) getParametro("gerenciaRegional");
		String idLocalidadeInicial = (String) getParametro("idLocalidadeInicial");
		String idLocalidadeFinal = (String) getParametro("idLocalidadeFinal");
		String idSetorComercialInicial = (String) getParametro("idSetorComercialInicial");
		String idSetorComercialFinal = (String) getParametro("idSetorComercialFinal");
		String strIdMunicipio = (String) getParametro("idMunicipio");
		Collection<GerenciaRegional> colecaoGerenciasRegionais = (Collection<GerenciaRegional>) 
		getParametro("colecaoGerenciasRegionais");
		String localidadeInicial = (String) getParametro("localidadeInicial");
		String localidadeFinal = (String) getParametro("localidadeFinal");
		String setorComercialInicial = (String) getParametro("setorComercialInicial");
		String setorComercialFinal = (String) getParametro("setorComercialFinal");
		String municipio = (String) getParametro("nomeMunicipio");
		
		// Ligacoes e consumos Agua/Esgoto
		String[] situacaoLigacaoAgua = (String[]) getParametro("situacaoLigacaoAgua");
		String[] situacaoLigacaoEsgoto = (String[]) getParametro("situacaoLigacaoEsgoto");
		String intervaloMesesCortadoSuprimidoInicial = 
			(String) getParametro("intervaloMesesCortadoSuprimidoInicial");
		String intervaloMesesCortadoSuprimidoFinal = 
			(String) getParametro("intervaloMesesCortadoSuprimidoFinal");
		String intervaloConsumoMinimoFixadoEsgotoInicial = 
			(String) getParametro("intervaloConsumoMinimoFixadoEsgotoInicial");
		String intervaloConsumoMinimoFixadoEsgotoFinal = 
			(String) getParametro("intervaloConsumoMinimoFixadoEsgotoFinal");
		String indicadorMedicao = (String) getParametro("indicadorMedicao");
		String idTipoMedicao = (String) getParametro("idTipoMedicao");
		Collection<LigacaoAguaSituacao> colecaoSituacaoLigAgua = (Collection<LigacaoAguaSituacao>) 
		getParametro("collectionsLigacaoAguaSituacao");
		Collection<LigacaoEsgotoSituacao> colecaoSituacaoLigEsgoto = (Collection<LigacaoEsgotoSituacao>) 
		getParametro("collectionLigacaoEsgotoSituacao");
		
		
		
		// Caracteristicas
		String idPerfilImovel = (String) getParametro("idPerfilImovel");
		String idTipoCategoria = (String) getParametro("idTipoCategoria");
		String[] categoria = (String[]) getParametro("categoria");
		String idSubCategoria = (String) getParametro("idSubCategoria");
		Collection<ImovelPerfil> colecaoImovelPerfil = (Collection<ImovelPerfil>) 
		getParametro("collectionImovelPerfil");
		Collection<CategoriaTipo> colecaoCategoriaTipo = (Collection<CategoriaTipo>) 
		getParametro("collectionCategoriaTipo");
		Collection<Categoria> colecaoCategoria = (Collection<Categoria>) 
		getParametro("collectionImovelCategoria");
		Collection<Subcategoria> colecaoSubcategoria = (Collection<Subcategoria>) 
		getParametro("collectionImovelSubcategoria");
		
		// Debito
		String valorMinimoDebito = (String) getParametro("valorMinimoDebito");
		String intervaloQuantidadeDocumentosInicial = (String) getParametro("intervaloQuantidadeDocumentosInicial");
		String intervaloQuantidadeDocumentosFinal = (String) getParametro("intervaloQuantidadeDocumentosFinal");
		String indicadorPagamentosNaoClassificados = (String) getParametro("indicadorPagamentosNaoClassificados");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoDadosRelatorio = fachada.gerarCurvaAbcDebitos(
				classificacao                                , referenciaCobrancaInicial,
				referenciaCobrancaFinal                      , indicadorImovelMedicaoIndividualizada,
				indicadorImovelParalizacaoFaturamentoCobranca, gerenciaRegional,
				idLocalidadeInicial                          , idLocalidadeFinal,
				idSetorComercialInicial                      , idSetorComercialFinal,
				strIdMunicipio,				
				situacaoLigacaoAgua                          , situacaoLigacaoEsgoto,
				intervaloMesesCortadoSuprimidoInicial        , intervaloMesesCortadoSuprimidoFinal,
				intervaloConsumoMinimoFixadoEsgotoInicial    , intervaloConsumoMinimoFixadoEsgotoFinal,
				indicadorMedicao                             , idTipoMedicao,
				idPerfilImovel                               , idTipoCategoria,
				categoria                                    , idSubCategoria,
				valorMinimoDebito                            , intervaloQuantidadeDocumentosInicial,
				intervaloQuantidadeDocumentosFinal           , indicadorPagamentosNaoClassificados);	
		
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		byte[] retorno = null;
		
		// bean do relatorio
		RelatorioGerarCurvaAbcDebitosBean relatorioGerarCurvaAbcDebitosBean = null;
		
		// dados para o relatorio
		if (colecaoDadosRelatorio != null && !colecaoDadosRelatorio.isEmpty()) {
			
			List<GerarCurvaAbcDebitosHelper> listaSimplificada = new ArrayList();
			GerarCurvaAbcDebitosHelper jaCadastrado = null;
			GerarCurvaAbcDebitosHelper gerarCurvaAbcDebitosHelper = null;
			Integer posicao = 0;
			Integer totalLigacoes = 0;
			Integer qtdLigacoesPorClassificacao = 0;
			BigDecimal totalValorLigacoes = new BigDecimal(0);
			
			Iterator iColecaoDadosRelatorio = colecaoDadosRelatorio.iterator();
			
			// Recupera as Faixas de Valores de Debitos
			Collection colecaoDebitoFaixaValores = fachada.pesquisarDebitoFaixaValores(0, null);
			
			if (colecaoDebitoFaixaValores != null & !colecaoDebitoFaixaValores.isEmpty()) {
				Iterator iColecaoDebitoFaixaValores = null;
				Object[] dadosDebitoFaixaValores = null;
				CurvaAbcDebitosHelper dadosRelatorio = null;
				
				// Usado para fazer o controle da classificacao(Quebra)
				Integer idClassificacao = 0;
				Integer idClassificacaoAnterior = 0;
				
				Integer idFaixa = null;
				BigDecimal faixaInicial = null;
				BigDecimal faixaFinal = null;
				
				Integer idGerenciaRegional = 0;
				String nomeGerenciaRegional = null;
				Integer idLocalidade = 0;
				String nomeLocalidade = null;
				Integer idSetorComercial = 0;
				Integer codigoSetorComercial = 0;
				String nomeSetorComercial = null;
				Integer idMunicipio = 0;
				String nomeMunicipio = null;
				
				List listaBeans = new ArrayList();
				
				// Usado para fazer o controle da ultima quebra
				boolean fazQuebra = false;
				
				while (iColecaoDadosRelatorio.hasNext()) {
					dadosRelatorio = (CurvaAbcDebitosHelper) iColecaoDadosRelatorio.next();
					
					// Recupera o id do objeto de acordo com a classificacao selecionada
					// Gerencia; Localidade; Setor
					if (classificacao.equalsIgnoreCase("REGIONAL")) {
						// Id da Gerencia Regional
						idClassificacao = dadosRelatorio.getIdGerencia();
					}else if (classificacao.equalsIgnoreCase("LOCAL")) {
						// Id da Localidade
						idClassificacao = dadosRelatorio.getIdLocalidade();					
					}else if (classificacao.equalsIgnoreCase("SETORCOMERCIAL")) {
						// Id do Setor Comercial
						idClassificacao = dadosRelatorio.getIdSetor();
					}else if(classificacao.equalsIgnoreCase("MUNICIPIO")){
						//Id do município
						idClassificacao = dadosRelatorio.getIdMunicipio();
					}
					
					// Faz a quebra de acordo com classificacao
					if (!idClassificacao.equals(idClassificacaoAnterior) & !idClassificacaoAnterior.equals(0)) {
						// Faz o acumulado
						relatorioGerarCurvaAbcDebitosBean = new RelatorioGerarCurvaAbcDebitosBean();
						listaBeans = quebrarPorClassificacao(
								listaSimplificada                    , totalLigacoes,
								totalValorLigacoes                   , qtdLigacoesPorClassificacao,
								idGerenciaRegional				     , nomeGerenciaRegional,
								idLocalidade                         , nomeLocalidade,
								idSetorComercial                     , codigoSetorComercial,
								nomeSetorComercial                   , referenciaCobrancaInicial,
								referenciaCobrancaFinal              , categoria,
								idTipoCategoria                      , idSubCategoria,
								classificacao                        , intervaloMesesCortadoSuprimidoInicial,
								intervaloMesesCortadoSuprimidoFinal  , situacaoLigacaoAgua,
								idClassificacaoAnterior, idMunicipio, nomeMunicipio);
						
						Iterator iListaBeans = listaBeans.iterator();
						
						while (iListaBeans.hasNext()) {
							relatorioGerarCurvaAbcDebitosBean = 
								(RelatorioGerarCurvaAbcDebitosBean) iListaBeans.next();
						
							relatorioBeans.add(relatorioGerarCurvaAbcDebitosBean);
						}
						
						listaSimplificada.clear();
						totalLigacoes = 0;
						qtdLigacoesPorClassificacao = 0;
						totalValorLigacoes = new BigDecimal(0);
						fazQuebra = false;
					}
					
					if (listaSimplificada.isEmpty()) {
						// Preenche os Helpers com todas as faixas para iniciar os acumulados
						iColecaoDebitoFaixaValores = colecaoDebitoFaixaValores.iterator();
						while (iColecaoDebitoFaixaValores.hasNext()) {
							dadosDebitoFaixaValores = (Object[]) iColecaoDebitoFaixaValores.next();
							
							idFaixa      = (Integer) dadosDebitoFaixaValores[0];
							faixaInicial = (BigDecimal) dadosDebitoFaixaValores[1];
							faixaFinal   = (BigDecimal) dadosDebitoFaixaValores[2];
							
							gerarCurvaAbcDebitosHelper = new GerarCurvaAbcDebitosHelper(
								idFaixa, faixaInicial, faixaFinal);
							
							listaSimplificada.add(gerarCurvaAbcDebitosHelper);
						}
					}
					
					// Preenche o Helper com os outros dados
					gerarCurvaAbcDebitosHelper = new GerarCurvaAbcDebitosHelper(
						dadosRelatorio.getFaixaInicial(),   // Faixa Inicial
						dadosRelatorio.getFaixaFinal(),   // Faixa Final
						dadosRelatorio.getIdFaixa(),   // Id Faixa
						dadosRelatorio.getQuantidadeLigacoes(),   // Quantidade Ligacoes
						dadosRelatorio.getTotal(),   // Valor
						dadosRelatorio.getIdGerencia(),   // Id da Gerencia
						dadosRelatorio.getNomeGerencia(),   // Nome da Gerencia
						dadosRelatorio.getIdLocalidade(),   // Id da Localidade
						dadosRelatorio.getNomeLocalidade(),   // Nome da Localidade
						dadosRelatorio.getIdSetor(),  // Id do Setor Comercial
						dadosRelatorio.getCodigoSetor(),  // Codigo do Setor Comercial
						dadosRelatorio.getNomeSetor(),  // Nome do Setor Comercial
						dadosRelatorio.getIdMunicipio(),  // Id Município
						dadosRelatorio.getNomeMunicipio());  // Nome Município
					
					// Faz os acumulados
					if (listaSimplificada.contains(gerarCurvaAbcDebitosHelper)) {
						posicao = listaSimplificada.indexOf(gerarCurvaAbcDebitosHelper);
						
						jaCadastrado = (GerarCurvaAbcDebitosHelper) listaSimplificada.get(posicao);
						
						// Ligacoes
						jaCadastrado.setQuantidadeLigacoes( (jaCadastrado.getQuantidadeLigacoes() +
								gerarCurvaAbcDebitosHelper.getQuantidadeLigacoes()) );
						
						// Valor
						jaCadastrado.setValor(jaCadastrado.getValor()
								.add(gerarCurvaAbcDebitosHelper.getValor()));
						
						// Id da Gerencia
						if (jaCadastrado.getIdGerenciaRegional() == null) {
							jaCadastrado.setIdGerenciaRegional(
									gerarCurvaAbcDebitosHelper.getIdGerenciaRegional());
							idGerenciaRegional = jaCadastrado.getIdGerenciaRegional();
							
							// Nome da Gerencia
							jaCadastrado.setNomeGerenciaRegional(
									gerarCurvaAbcDebitosHelper.getNomeGerenciaRegional());
							nomeGerenciaRegional = jaCadastrado.getNomeGerenciaRegional();
						}
						
						// Id Localidade
						if (jaCadastrado.getIdLocalidade() == null) {
							jaCadastrado.setIdLocalidade(
									gerarCurvaAbcDebitosHelper.getIdLocalidade());
							idLocalidade = jaCadastrado.getIdLocalidade();
							
							// Nome da Localidade
							jaCadastrado.setNomeLocalidade(
									gerarCurvaAbcDebitosHelper.getNomeLocalidade());
							nomeLocalidade = jaCadastrado.getNomeLocalidade();
						}
						
						// Id Setor Comercial
						if (jaCadastrado.getIdSetorComercial() == null) {
							jaCadastrado.setIdSetorComercial(
									gerarCurvaAbcDebitosHelper.getIdSetorComercial());
							idSetorComercial = jaCadastrado.getIdSetorComercial();
							
							// Codigo Setor Comercial
							jaCadastrado.setCodigoSetorComercial(
									gerarCurvaAbcDebitosHelper.getCodigoSetorComercial());
							codigoSetorComercial = jaCadastrado.getCodigoSetorComercial();
							
							// Nome Setor Comercial
							jaCadastrado.setNomeSetorComercial(
									gerarCurvaAbcDebitosHelper.getNomeSetorComercial());
							nomeSetorComercial = jaCadastrado.getNomeSetorComercial();
						}
						
						//Id Município
						if (jaCadastrado.getIdMunicipio() == null) {
							jaCadastrado.setIdMunicipio(gerarCurvaAbcDebitosHelper.getIdMunicipio());
							idMunicipio = jaCadastrado.getIdMunicipio();
							
							// Nome do Município
							jaCadastrado.setNomeMunicipio(
									gerarCurvaAbcDebitosHelper.getNomeMunicipio());
							nomeMunicipio = jaCadastrado.getNomeMunicipio();
						}
						
						
						// Valor Medio
						BigDecimal qtdLigacoes = new BigDecimal(jaCadastrado.getQuantidadeLigacoes());
						if (!jaCadastrado.getQuantidadeLigacoes().equals(0) &&
								!jaCadastrado.getValor().equals(0)) {
							jaCadastrado.setValorMedio(jaCadastrado.getValor().divide(qtdLigacoes, 2, RoundingMode.HALF_UP));
						}else {
							jaCadastrado.setValorMedio(qtdLigacoes);
						}
						
						// Quantidade de Ligacoes por Classificacao
						qtdLigacoesPorClassificacao += gerarCurvaAbcDebitosHelper.getQuantidadeLigacoes();
						// Total geral das Ligacoes
						totalLigacoes += jaCadastrado.getQuantidadeLigacoes();
						// Total geral dos Valores
						totalValorLigacoes = totalValorLigacoes.add(gerarCurvaAbcDebitosHelper.getValor());
					}
					
					idClassificacaoAnterior = idClassificacao;
					fazQuebra = true;
				}
				
				// Faz o acumulado da ultima quebra
				if (fazQuebra) {
					relatorioGerarCurvaAbcDebitosBean = new RelatorioGerarCurvaAbcDebitosBean();
					listaBeans = quebrarPorClassificacao(
							listaSimplificada                    , totalLigacoes,
							totalValorLigacoes                   , qtdLigacoesPorClassificacao,
							idGerenciaRegional                   , nomeGerenciaRegional,
							idLocalidade                         , nomeLocalidade,
							idSetorComercial                     , codigoSetorComercial,
							nomeSetorComercial                   , referenciaCobrancaInicial,
							referenciaCobrancaFinal              , categoria,
							idTipoCategoria                      , idSubCategoria,
							classificacao                        , intervaloMesesCortadoSuprimidoInicial,
							intervaloMesesCortadoSuprimidoFinal  , situacaoLigacaoAgua,
							idClassificacaoAnterior, idMunicipio, nomeMunicipio);
					
					Iterator iListaBeans = listaBeans.iterator();
					while (iListaBeans.hasNext()) {
						relatorioGerarCurvaAbcDebitosBean = 
							(RelatorioGerarCurvaAbcDebitosBean) iListaBeans.next();
						
						relatorioBeans.add(relatorioGerarCurvaAbcDebitosBean);
					}
				}
			}
			
		}
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("tipoFormatoRelatorio", "R0622");
		
		
        
		/* 
		  Inicio: Adicionando os parametros para serem mostrados no cabecalho do relatorio.
		*/		
		
		// Parametros
		if(classificacao != null){
		parametros.put("classificacao",classificacao);
		}
		else{
			parametros.put("classificacao","");	
		}
		
		if(referenciaCobrancaInicial != null){
		parametros.put("referenciaCobrancaInicial",referenciaCobrancaInicial);	
		}
		else{
			parametros.put("referenciaCobrancaInicial","");	
		}
		
		if(referenciaCobrancaFinal != null){
		parametros.put("referenciaCobrancaFinal",referenciaCobrancaFinal);	
			
		}
		else{
			parametros.put("referenciaCobrancaFinal","");	
		}
		
		if(indicadorImovelMedicaoIndividualizada != null){
			
			if(indicadorImovelMedicaoIndividualizada.equals("1")){
				parametros.put("indicadorImovelMedicaoIndividualizada","Sim");	
			}
			else{
				parametros.put("indicadorImovelMedicaoIndividualizada","Não");
			}
		}
		else{
			parametros.put("indicadorImovelMedicaoIndividualizada","");
		}
		
		if(indicadorImovelParalizacaoFaturamentoCobranca != null){
			
			if(indicadorImovelParalizacaoFaturamentoCobranca.equals("1")){
				parametros.put("indicadorImovelParalizacaoFaturamentoCobranca","Sim");	
			}
			else{
				parametros.put("indicadorImovelParalizacaoFaturamentoCobranca","Não");
			}
		}
		else{
			parametros.put("indicadorImovelParalizacaoFaturamentoCobranca","");
		}
		
		// Ligações e consumos
		if(colecaoSituacaoLigAgua != null && situacaoLigacaoAgua != null){
			String valoresSituacaoLigAgua ="";
			LigacaoAguaSituacao situacaoLigAgua = null;
			
		for (Iterator iter = colecaoSituacaoLigAgua.iterator(); iter.hasNext();) {
				situacaoLigAgua = (LigacaoAguaSituacao) iter.next();
		   
		   for( String idSituacaoLigacaoAgua : situacaoLigacaoAgua){
		   if(situacaoLigAgua.getId() == new Integer(idSituacaoLigacaoAgua)){
				valoresSituacaoLigAgua += situacaoLigAgua.getDescricao()+"\n";
			}
			}
			}
			parametros.put("situacoesLigacaoAgua",valoresSituacaoLigAgua);
		}
		else{
			parametros.put("situacoesLigacaoAgua","");
		}
		
		if(colecaoSituacaoLigEsgoto != null && situacaoLigacaoEsgoto != null){
			String valoresSituacaoLigEsgoto ="";
			LigacaoEsgotoSituacao situacaoLigEsgoto  = null;
			int count = 1;
			for (Iterator iter = colecaoSituacaoLigEsgoto.iterator(); iter.hasNext();) {
				situacaoLigEsgoto = (LigacaoEsgotoSituacao) iter.next();
				
			for( String idSituacaoLigacaoEsgoto : situacaoLigacaoEsgoto){
			if(situacaoLigEsgoto.getId() == new Integer(idSituacaoLigacaoEsgoto)){
				if(count % 2 == 0){
				valoresSituacaoLigEsgoto += situacaoLigEsgoto.getDescricao()+"\n";
				}
				else{
				valoresSituacaoLigEsgoto += situacaoLigEsgoto.getDescricao()+";";
				}
            ++count;
		    }
			}
			}
			parametros.put("situacoesLigacaoEsgoto",valoresSituacaoLigEsgoto);
		}
		else{
			parametros.put("situacoesLigacaoEsgoto","");
		}
		
		
		if(intervaloConsumoMinimoFixadoEsgotoInicial != null){
			parametros.put("intervaloConsumoMinimoFixadoEsgotoInicial",intervaloConsumoMinimoFixadoEsgotoInicial);
		}
		else{
			parametros.put("intervaloConsumoMinimoFixadoEsgotoInicial","");
		}
		
		if(intervaloConsumoMinimoFixadoEsgotoFinal != null){
			parametros.put("intervaloConsumoMinimoFixadoEsgotoFinal",intervaloConsumoMinimoFixadoEsgotoFinal);
		}
		else{
			parametros.put("intervaloConsumoMinimoFixadoEsgotoFinal","");
		}
		
		if(intervaloMesesCortadoSuprimidoInicial != null){
			parametros.put("intervaloMesesCortadoSuprimidoInicial",intervaloMesesCortadoSuprimidoInicial);
		}
		else{
			parametros.put("intervaloMesesCortadoSuprimidoInicial","");
		}
		
		if(intervaloMesesCortadoSuprimidoFinal != null){
			parametros.put("intervaloMesesCortadoSuprimidoFinal",intervaloMesesCortadoSuprimidoFinal);
		}
		else{
			parametros.put("intervaloMesesCortadoSuprimidoFinal","");
		}
		
		if(indicadorMedicao != null){
			
			if(indicadorMedicao.equals("semMedicao")){
				parametros.put("indicadorMedicao","Sem medição");	
			}
			else if(indicadorMedicao.equals("comMedicao")){
				parametros.put("indicadorMedicao","Com medição");
			}
			else{
				parametros.put("indicadorMedicao","Todos");
			}
		}
		else{
			parametros.put("indicadorMedicao","");
		}
		
		if(idTipoMedicao != null){
			if(idTipoMedicao.equals("1")){
				parametros.put("tipoMedicao","Ligação de Água");
			}
			else if(idTipoMedicao.equals("2")){
				parametros.put("tipoMedicao","Poço");
			}
		}
		else{
			parametros.put("tipoMedicao","");
		}
		
		// Caracteristica
		if(colecaoImovelPerfil != null){
			String valoresImovelPerfil ="";
			ImovelPerfil imovelPerfil = null;
			for (Iterator iter = colecaoImovelPerfil.iterator(); iter.hasNext();) {
			    imovelPerfil = (ImovelPerfil) iter.next();
			    if(imovelPerfil.getId() != null){
			    if(imovelPerfil.getId().toString().equals(idPerfilImovel)){
				valoresImovelPerfil = imovelPerfil.getDescricao();
			    }
			    }
			}
			parametros.put("perfilImovel",valoresImovelPerfil);
		}
		else{
			parametros.put("perfilImovel","");
		}
		
		if(colecaoCategoriaTipo != null){
			String valoresCategoriaTipo ="";
			CategoriaTipo categoriaTipo = null;
			for (Iterator iter = colecaoCategoriaTipo.iterator(); iter.hasNext();) {
				categoriaTipo = (CategoriaTipo) iter.next();
				if(categoriaTipo.getId() != null){
				if(categoriaTipo.getId().toString().equals(idTipoCategoria)){
				valoresCategoriaTipo = categoriaTipo.getDescricao();
				break;
				}
				}
			}
			parametros.put("tipoCategoria",valoresCategoriaTipo);
		}	
		else{
			parametros.put("tipoCategoria","");
		}
		
		if(colecaoCategoria != null && categoria != null){
			String valoresCategoria ="";
			Categoria categ = null;
			int count = 1;
			for (Iterator iter = colecaoCategoria.iterator(); iter.hasNext();) {
				categ = (Categoria) iter.next();
			for(String idCategoria : categoria){
				
			if(categ.getId() != null){
			if(categ.getId().toString().equals(idCategoria)){
				if(count % 2 == 0){
				valoresCategoria += categ.getDescricao()+"\n";
				}
				else{
				valoresCategoria += categ.getDescricao()+";";
				}
				++count;
				break;
			}
			}
			}
			}
			parametros.put("categorias",valoresCategoria);
		}
		else{
			parametros.put("categorias","");
		}
		
		if(colecaoSubcategoria != null){
			String valoresSubCategoria ="";
			Subcategoria subCateg = null;
			for (Iterator iter = colecaoSubcategoria.iterator(); iter.hasNext();) {
				subCateg = (Subcategoria) iter.next();
				if(subCateg.getId() != null){
				if(subCateg.getId().toString().equals(idSubCategoria)){
				valoresSubCategoria = subCateg.getDescricao();
				break;
				}
				}
			}
			parametros.put("subCategoria",valoresSubCategoria);
		}
		else{
			parametros.put("subCategoria","");
		}
		
		
		// Debitos
		if(valorMinimoDebito != null){
			parametros.put("valorMinimoDebito",valorMinimoDebito);
		}
		else{
			parametros.put("valorMinimoDebito","");
		}
		
		if(intervaloQuantidadeDocumentosInicial != null){
			parametros.put("intervaloQuantidadeDocumentosInicial",intervaloQuantidadeDocumentosInicial);
		}
		else{
			parametros.put("intervaloQuantidadeDocumentosInicial","");
		}
		
		if(intervaloQuantidadeDocumentosFinal != null){
			parametros.put("intervaloQuantidadeDocumentosFinal",intervaloQuantidadeDocumentosFinal);
		}	
		else{
			parametros.put("intervaloQuantidadeDocumentosFinal","");
		}
		
		if(indicadorPagamentosNaoClassificados != null){
			if(indicadorPagamentosNaoClassificados.equals("1")){
				parametros.put("indicadorPagamentosNaoClassificados","Sim");	
			}
			else {
				parametros.put("indicadorPagamentosNaoClassificados","Não");	
			}			
		}
		else{
			parametros.put("indicadorPagamentosNaoClassificados","");
		}
		
		// Localizacao
		if( colecaoGerenciasRegionais != null && gerenciaRegional != null){
			String valoresGerenciaReg ="";
			int count = 1 ;
			GerenciaRegional gerenciaReg = null;
			
        for (Iterator gerenciaRegionais = colecaoGerenciasRegionais.iterator(); gerenciaRegionais.hasNext();) {
			gerenciaReg = (GerenciaRegional) gerenciaRegionais.next();
         
			for( String idGerenciaRegional : gerenciaRegional){
			if(gerenciaReg.getId() == new Integer(idGerenciaRegional)){
			// arruma para colocar duas gerenciasRegionais por linha.
			if(count % 2 == 0){
				valoresGerenciaReg += gerenciaReg.getNome()+"\n";				
			}
			else{
				valoresGerenciaReg += gerenciaReg.getNome()+";";
			}
			++count;
			}
			}
		}
        parametros.put("gerenciasRegionais",valoresGerenciaReg);
		}
		else{
			parametros.put("gerenciasRegionais","");
		}
		
		if( localidadeInicial != null){
			parametros.put("localidadeInicial",localidadeInicial);	
		}
		else{
			parametros.put("localidadeInicial","");
		}
		
		if( localidadeFinal != null){
			if(localidadeFinal.equals("") && localidadeInicial != null){
				parametros.put("localidadeFinal",localidadeInicial);	
			}
			else{
			parametros.put("localidadeFinal",localidadeFinal);
			}
		}
		else{
			parametros.put("localidadeFinal","");
		}
		
		if( setorComercialInicial != null){
			parametros.put("setorComercialInicial",setorComercialInicial);	
		}
		else{
			parametros.put("setorComercialInicial","");
		}
		
		if( setorComercialFinal != null){
			if(setorComercialFinal.equals("") && setorComercialInicial != null){
				parametros.put("setorComercialFinal",setorComercialInicial);	
			}
			else{
			parametros.put("setorComercialFinal",setorComercialFinal);
			}
		}
		else{
			parametros.put("setorComercialFinal","");
		}
		
		if( municipio != null && !municipio.equals("")){
			parametros.put("municipio",municipio);	
		}else{
			parametros.put("municipio",municipio);
		}
		/* 
		  Fim: Adicionando os parametros para serem mostrados no cabecalho do relatorio.
		*/		
		
		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relatório em pdf e retorna o array de bytes
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_GERAR_CURVA_ABC_DEBITOS,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.GERAR_CURVA_ABC_DEBITOS,
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
	 * Usado para efetivar as quebras do relatorio
	 * 
	 * [UC0621] Gerar Curva ABC de Debitos
	 * 
	 * @author Ivan Sérgio
	 * @date 25/09/2007
	 * 
	 */
	public List quebrarPorClassificacao(
			List listaSimplificada,
			Integer totalLigacoes,
			BigDecimal totalValorLigacoes,
			Integer qtdLigacoesPorClassificacao,
			Integer idGerenciaRegional,
			String nomeGerenciaRegional,
			Integer idLocalidade,
			String nomeLocalidade,
			Integer idSetorComercial,
			Integer codigoSetorComercial,
			String nomeSetorComercial,
			String referenciaCobrancaIncial,
			String referenciaCobrancaFinal,
			String[] categoria,
			String idTipoCategoria,
			String idSubCategoria,
			String classificacao,
			String intervaloMesesCortadoSuprimidoInicial,
			String intervaloMesesCortadoSuprimidoFinal,
			String[] situacaoLigacaoAgua,
			Integer idClassificacao,
			Integer idMunicipio,
			String nomeMunicipio) {
		
		RelatorioGerarCurvaAbcDebitosBean relatorioGerarCurvaAbcDebitosBean = 
			new RelatorioGerarCurvaAbcDebitosBean();
		
		Fachada fachada = Fachada.getInstancia();
		
		// Faz o acumulado
		List listaAcumulada = new ArrayList();
		List listaBeans = new ArrayList();
		
		listaAcumulada = acumularCurvaAbcDebitos(
				listaSimplificada   , totalLigacoes,
				totalValorLigacoes  , qtdLigacoesPorClassificacao,
				idGerenciaRegional  , nomeGerenciaRegional,
				idLocalidade        , nomeLocalidade,
				idSetorComercial    , codigoSetorComercial,
				nomeSetorComercial,
				idMunicipio, nomeMunicipio);
		
		// Recupera a Descricao da Categoria
		String descricaoCategoria = "";
		if (categoria != null) {
			if (categoria.length == 1) {
				if (!categoria[0].trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
					
					FiltroCategoria filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
						 categoria[0]));
					
					Collection collectionImovelCategoria = 
						fachada.pesquisar(filtroCategoria, Categoria.class.getName());
					
					if (collectionImovelCategoria != null && !collectionImovelCategoria.isEmpty()) {
						Iterator icollectionImovelCategoria = collectionImovelCategoria.iterator();
						
						Categoria categoria2 = (Categoria) icollectionImovelCategoria.next(); 
						
						descricaoCategoria = categoria2.getDescricao();
					}
				}
			}else if (idTipoCategoria.equals("1") && categoria.length == 0) {
				descricaoCategoria = "PARTICULAR";
			}else  {
				descricaoCategoria = "GERAL";
			}
		}
		
		// Recupera a Descricao da SubCategoria
		String descricaoSubCategoria = "";
		if (idSubCategoria != null) {
			if (idSubCategoria != null) {
				if (!idSubCategoria.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
					
					FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
					filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
						 idSubCategoria));
					
					Collection collectionImovelSubcategoria = 
						fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
					
					if (collectionImovelSubcategoria != null && !collectionImovelSubcategoria.isEmpty()) {
						Iterator iCollectionImovelSubcategoria = collectionImovelSubcategoria.iterator();
						
						Subcategoria subcategoria = (Subcategoria) iCollectionImovelSubcategoria.next();
						
						descricaoSubCategoria = " - " + subcategoria.getDescricao();
					}
				}
			}
		}
		
		String situacao = "";
		String intervaloMeses = "";
		
		if (intervaloMesesCortadoSuprimidoInicial != null & 
				intervaloMesesCortadoSuprimidoFinal != null) {
			
			// Verifica a Situacao de Agua para Cortado/Suprimido
			if (situacaoLigacaoAgua != null) {
				if (situacaoLigacaoAgua.length >= 1) {
					Integer situacaoAgua = null;
					
					for (int i = 0; i < situacaoLigacaoAgua.length; i++) {
						if (!situacaoLigacaoAgua[i].trim().equalsIgnoreCase(
								new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
							
							situacaoAgua = new Integer(situacaoLigacaoAgua[i]);
							
							// Verifica se a situacao é Cortado/Suprimido e
							// se o intervalo de meses foi informado
							if ( (situacaoAgua.equals(LigacaoAguaSituacao.CORTADO)) ||
									(situacaoAgua.equals(LigacaoAguaSituacao.SUPRIMIDO)) ) {
								
								FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = 
									new FiltroLigacaoAguaSituacao();
								
								filtroLigacaoAguaSituacao.adicionarParametro(
										new ParametroSimples(FiltroLigacaoAguaSituacao.ID, situacaoAgua));
								
								Collection collectionLigacaoAguaSituacao = 
									fachada.pesquisar(filtroLigacaoAguaSituacao, 
											LigacaoAguaSituacao.class.getName());
								
								if (collectionLigacaoAguaSituacao != null &&
										!collectionLigacaoAguaSituacao.isEmpty()) {
									
									Iterator iCollectionLigacaoAguaSituacao = 
										collectionLigacaoAguaSituacao.iterator();
									
									LigacaoAguaSituacao ligacaoAguaSituacao = 
										(LigacaoAguaSituacao) iCollectionLigacaoAguaSituacao.next();
									
									situacao += ligacaoAguaSituacao.getDescricao() + "/";
								}
							}
						}
					}
					
					// Retira o ultimo "/" da situacao
					if (situacao != "") {
						situacao = situacao.substring(0, (situacao.length() - 1));
						
						intervaloMeses = intervaloMesesCortadoSuprimidoInicial + " A " + 
										 intervaloMesesCortadoSuprimidoFinal + " MESES";
					}
				}
			}
		}
		
		// Preenche o Bean com as Faixas já calculadas 
		GerarCurvaAbcDebitosHelper helper = null;
		Iterator iListaAcumulada = listaAcumulada.iterator();
		BigDecimal percentual = new BigDecimal(100.00).setScale(2);
		
		while (iListaAcumulada.hasNext()) {
			helper = (GerarCurvaAbcDebitosHelper) iListaAcumulada.next();
		
			// Passa as informações para o Bean
			relatorioGerarCurvaAbcDebitosBean = new RelatorioGerarCurvaAbcDebitosBean();
			relatorioGerarCurvaAbcDebitosBean.setCategoria(descricaoCategoria);
			relatorioGerarCurvaAbcDebitosBean.setSubCategoria(descricaoSubCategoria);
			relatorioGerarCurvaAbcDebitosBean.setIntervaloMeses(intervaloMeses);
			relatorioGerarCurvaAbcDebitosBean.setSituacaoAgua(situacao);
			
			relatorioGerarCurvaAbcDebitosBean.setReferenciaCobrancaInicial(referenciaCobrancaIncial);
			relatorioGerarCurvaAbcDebitosBean.setReferenciaCobrancaFinal(referenciaCobrancaFinal);
			relatorioGerarCurvaAbcDebitosBean.setClassificacao(classificacao);
			relatorioGerarCurvaAbcDebitosBean.setIdGerenciaRegional(""+idGerenciaRegional);
			relatorioGerarCurvaAbcDebitosBean.setNomeGerenciaRegional(nomeGerenciaRegional);
			relatorioGerarCurvaAbcDebitosBean.setIdLocalidade(""+idLocalidade);
			relatorioGerarCurvaAbcDebitosBean.setNomeLocalidade(nomeLocalidade);
			relatorioGerarCurvaAbcDebitosBean.setIdSetorComercial(""+idSetorComercial);
			relatorioGerarCurvaAbcDebitosBean.setCodigoSetorComercial(""+codigoSetorComercial);
			relatorioGerarCurvaAbcDebitosBean.setNomeSetorComercial(nomeSetorComercial);
			relatorioGerarCurvaAbcDebitosBean.setIdMunicipio(""+idMunicipio);
			relatorioGerarCurvaAbcDebitosBean.setNomeMunicipio(nomeMunicipio);
			
			// Dados das Faixas
			relatorioGerarCurvaAbcDebitosBean.setFaixaInicial(helper.getFaixaInicial());
			relatorioGerarCurvaAbcDebitosBean.setFaixaFinal(helper.getFaixaFinal());
			relatorioGerarCurvaAbcDebitosBean.setIdFaixa(helper.getIdFaixa());
			relatorioGerarCurvaAbcDebitosBean.setQuantidadeLigacoes(helper.getQuantidadeLigacoes());
			
			// Fecha os acumulados em 100%
			if (iListaAcumulada.hasNext()) {
				if (helper.getPercLigacoesAcumulado().compareTo(percentual) > 0) {
					relatorioGerarCurvaAbcDebitosBean.setPercLigacoesAcumulado(percentual);
				}else {
					relatorioGerarCurvaAbcDebitosBean.setPercLigacoesAcumulado(helper.getPercLigacoesAcumulado());
				}
				
				if (helper.getPercValorAcumulado().compareTo(percentual) > 0) {
					relatorioGerarCurvaAbcDebitosBean.setPercValorAcumulado(percentual);
				}else {
					relatorioGerarCurvaAbcDebitosBean.setPercValorAcumulado(helper.getPercValorAcumulado());
				}
			}else {
				relatorioGerarCurvaAbcDebitosBean.setPercLigacoesAcumulado(percentual);
				relatorioGerarCurvaAbcDebitosBean.setPercValorAcumulado(percentual);
			}

			relatorioGerarCurvaAbcDebitosBean.setPercLigacoes(helper.getPercLigacoes());
			relatorioGerarCurvaAbcDebitosBean.setLigacoesAcumuladas(helper.getLigacoesAcumuladas());
			relatorioGerarCurvaAbcDebitosBean.setValor(helper.getValor());
			relatorioGerarCurvaAbcDebitosBean.setPercValor(helper.getPercValor());
			relatorioGerarCurvaAbcDebitosBean.setValorAcumulado(helper.getValorAcumulado());
			relatorioGerarCurvaAbcDebitosBean.setValorMedio(helper.getValorMedio());
			relatorioGerarCurvaAbcDebitosBean.setIdClassificacao(idClassificacao.toString());
			
			listaBeans.add(relatorioGerarCurvaAbcDebitosBean);
		}
		
		return listaBeans;
	}
	
	
	
	
	/**
	 * Faz os acumulados das Faixas Valores
	 * 
	 * [UC0621] Gerar Curva ABC de Debitos
	 * 
	 * @author Ivan Sérgio
	 * @date 24/09/2007
	 * 
	 */
	public List acumularCurvaAbcDebitos(
			List listaCurvaAbcDebito,
			Integer totalLigacoes,
			BigDecimal totalValorLigacoes,
			Integer qtdLigacoesPorClassificacao,
			Integer idGerenciaRegional,
			String nomeGerenciaRegional,
			Integer idLocalidade,
			String nomeLocalidade,
			Integer idSetorComercial,
			Integer codigoSetorComercial,
			String nomeSetorComercial,
			Integer idMunicipio,
			String nomeMunicipio) {
		
		List<GerarCurvaAbcDebitosHelper> listAcumulada = new ArrayList();
		
		if (listaCurvaAbcDebito != null & !listaCurvaAbcDebito.isEmpty()) {
			GerarCurvaAbcDebitosHelper helper = null;
			
			Iterator iListaCurvaAbcDebito = listaCurvaAbcDebito.iterator();
			
			Integer quantidadeLigacoesAnterior = 0;
			BigDecimal percLigacosAcumuladoAnterior = new BigDecimal(0.00);
			BigDecimal valorAnterior = new BigDecimal(0.00);
			BigDecimal percValorAnterior = new BigDecimal(0.00);
			
			while (iListaCurvaAbcDebito.hasNext()) {
				helper = (GerarCurvaAbcDebitosHelper) iListaCurvaAbcDebito.next();
				
				if (!helper.getQuantidadeLigacoes().equals(0) && 
						!totalLigacoes.equals(0)) {
					// Perc (1) - Perc das Ligacoes
					BigDecimal qtdLigacoes = new BigDecimal(helper.getQuantidadeLigacoes());
					BigDecimal porcento = new BigDecimal(100).setScale(2);
					//BigDecimal totalGeralLigacoes = new BigDecimal(totalLigacoes);
					BigDecimal totalGeralLigacoes = new BigDecimal(qtdLigacoesPorClassificacao);
					
					BigDecimal percLigacoes = qtdLigacoes.multiply(porcento);
					percLigacoes = percLigacoes.divide(totalGeralLigacoes, 2, RoundingMode.HALF_UP);
					
					//BigDecimal percLigacoes = new BigDecimal(( (helper.getQuantidadeLigacoes() * 100) /
					//		totalLigacoes ));
					helper.setPercLigacoes(percLigacoes);
				
					// Ligacoes Acumuladas
					helper.setLigacoesAcumuladas(
							(helper.getQuantidadeLigacoes() + quantidadeLigacoesAnterior) );
					//quantidadeLigacoesAnterior = helper.getQuantidadeLigacoes();
					quantidadeLigacoesAnterior = helper.getLigacoesAcumuladas();
					
					// Perc Acum (1) - Perc Acumulado por faixa
					helper.setPercLigacoesAcumulado(
							helper.getPercLigacoes().add(percLigacosAcumuladoAnterior));
					percLigacosAcumuladoAnterior = helper.getPercLigacoesAcumulado();
					
					// Perc (2) - Perc dos Debitos
					if (!helper.getValor().equals(0) &&
							!totalValorLigacoes.equals(0)) {
						BigDecimal percDebitos = (BigDecimal) ( helper.getValor().multiply(porcento) ).setScale(2);
						percDebitos = percDebitos.divide(totalValorLigacoes, 2, RoundingMode.HALF_UP);
						
						helper.setPercValor(percDebitos);
					}
					
					// Valores Acumulados
					helper.setValorAcumulado(
							helper.getValor().add(valorAnterior));
					valorAnterior = helper.getValorAcumulado();
					
					// Perc Acum (2) - Somatorio do Perc (2)
					helper.setPercValorAcumulado(
							helper.getPercValor().add(percValorAnterior));
					percValorAnterior = helper.getPercValorAcumulado();
				
				}else {
					// Perc (1) - Perc das Ligacoes
					BigDecimal percLigacoes = new BigDecimal(0.00).setScale(2);
					helper.setPercLigacoes(percLigacoes);
					
					// Ligacoes Acumuladas
					helper.setLigacoesAcumuladas(quantidadeLigacoesAnterior);
					
					// Perc Acum (1) - Perc Acumulado por faixa
					helper.setPercLigacoesAcumulado(percLigacosAcumuladoAnterior);
					
					// Perc (2) - Perc dos Debitos
					BigDecimal percDebitos = new BigDecimal(0.00).setScale(2);
					helper.setPercValor(percDebitos);
					
					// Valores Acumulados
					helper.setValorAcumulado(valorAnterior);
					
					// Perc Acum (2) - Somatorio do Perc (2)
					helper.setPercValorAcumulado(percValorAnterior);
					
					// Id da Gerencia Regional
					helper.setIdGerenciaRegional(idGerenciaRegional);
					// Nome da Gerencia Regional
					helper.setNomeGerenciaRegional(nomeGerenciaRegional);
					
					// Id da Localidade
					helper.setIdLocalidade(idLocalidade);
					// Nome da Localidade
					helper.setNomeLocalidade(nomeLocalidade);
					
					// Id do Setor Comercial
					helper.setIdSetorComercial(idSetorComercial);
					// Codigo do Setor Comercial
					helper.setCodigoSetorComercial(codigoSetorComercial);
					// Nome do Setor Comercial
					helper.setNomeSetorComercial(nomeSetorComercial);
				
					helper.setIdMunicipio(idMunicipio);
					helper.setNomeMunicipio(nomeMunicipio);
				}
				
				/*
				System.out.print(helper.getFaixaInicial() + "  |  " + 
						helper.getFaixaFinal() + "  |  " +
						helper.getQuantidadeLigacoes() + "  |  " + 
						helper.getPercLigacoes() + "  |  " +
						helper.getLigacoesAcumuladas() + "  |  " +
						helper.getPercLigacoesAcumulado() + "  |  " +
						helper.getValor() + "  |  " + 
						helper.getPercValor() + "  |  " +
						helper.getValorAcumulado() + "  |  " +
						helper.getPercValorAcumulado() + "  |  " +
						helper.getValorMedio());
				*/
				listAcumulada.add(helper);
			}
		}
		
		return listAcumulada;
	}
	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioGerarCurvaAbcDebitos", this);
	}
}
