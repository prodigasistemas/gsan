package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * [UC0711] - Emitir Ordem de Servico Seletiva
 * 
 * @author Ivan Sérgio, Raphael Rossiter
 * @date 06/11/2007, 16/04/2009
 */
public class RelatorioEmitirOrdemServicoSeletivaSugestao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioEmitirOrdemServicoSeletivaSugestao
	 */
	public RelatorioEmitirOrdemServicoSeletivaSugestao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO);
	}
	
	@Deprecated
	public RelatorioEmitirOrdemServicoSeletivaSugestao() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		ImovelEmissaoOrdensSeletivasHelper helper = gerarObjetoHelper();
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Fachada fachada = Fachada.getInstancia();
		
		//[UC0711] Filtro para Emissao de Ordens Seletivas
		Collection colecaoDadosRelatorio = fachada.filtrarImovelEmissaoOrdensSeletivas(helper);
		
		String totalSelecionados = "0";
		List relatorioBeans = new ArrayList();
		
		RelatorioEmitirOrdemServicoSeletivaSugestaoBean relatorioEmitirOrdemServicoSeletivaSugestaoBean = 
			new RelatorioEmitirOrdemServicoSeletivaSugestaoBean();
		
		if (colecaoDadosRelatorio != null && !colecaoDadosRelatorio.isEmpty()) {
			totalSelecionados = Util.converterObjetoParaString(colecaoDadosRelatorio.size());
		}
		
		if ( helper.getTipoEmissao().equals("SINTETICO") &&
				(colecaoDadosRelatorio != null && !colecaoDadosRelatorio.isEmpty() ) &&			
				(( helper.getLocalidadeInicial() == null && helper.getLocalidadeFinal() == null ) ||
				( helper.getLocalidadeInicial() != null && !helper.getLocalidadeFinal().equals(helper.getLocalidadeInicial()) ) ||
				( helper.getLocalidadeInicial() != null && helper.getLocalidadeFinal().equals(helper.getLocalidadeInicial()) )) 
			){
			
			//Montando o BEAN agrupados pela localidade ou setor
			relatorioBeans = 
				this.gerarRelatorioBeanAgrupadoLocalidadeOuSetor(helper, colecaoDadosRelatorio);
		}else {
			//Montando o BEAN a partir dos parâmetros passados
			relatorioEmitirOrdemServicoSeletivaSugestaoBean = 
				this.gerarRelatorioBean(helper, totalSelecionados);
			
			relatorioBeans.add(relatorioEmitirOrdemServicoSeletivaSugestaoBean);
		}
		
		
		
		
		// PARÂMETROS PARA O RELATÓRIO
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Map parametros = new HashMap();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		
		// GERANDO O RELATÓRIO
		
		
		
		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);
		
		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO,
		parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------
		// retorna o relatório gerado

		return retorno;
	}

	private ImovelEmissaoOrdensSeletivasHelper gerarObjetoHelper() {
		ImovelEmissaoOrdensSeletivasHelper helper = new ImovelEmissaoOrdensSeletivasHelper();
		
		// PARÂMETROS
		helper.setTipoOrdem((String) getParametro("tipoOrdem"));
		helper.setFirma((String) getParametro("firma"));
		helper.setNomeFirma((String) getParametro("nomeFirma"));
		helper.setQuantidadeMaxima((String) getParametro("quantidadeMaxima"));
		helper.setTipoEmissao((String) getParametro("tipoEmissao"));
		
		
		helper.setIdImovel((String) getParametro("idImovel"));
		helper.setElo((String) getParametro("elo"));
		helper.setNomeElo((String) getParametro("nomeElo"));
		helper.setGerenciaRegional((String) getParametro("gerenciaRegional"));
		helper.setNomeGerenciaRegional((String) getParametro("nomeGerenciaRegional"));
		helper.setUnidadeNegocio((String) getParametro("unidadeNegocio"));
		helper.setNomeUnidadeNegocio((String) getParametro("nomeUnidadeNegocio"));
		
		helper.setLocalidadeInicial((String) getParametro("localidadeInicial"));
		helper.setNomeLocalidadeInicial((String) getParametro("nomeLocalidadeInicial"));
		
		helper.setLocalidadeFinal((String) getParametro("localidadeFinal"));
		helper.setNomeLocalidadeFinal((String) getParametro("nomeLocalidadeFinal"));
		
		helper.setSetorComercialInicial((String) getParametro("setorComercialInicial"));
		helper.setCodigoSetorComercialInicial((String) getParametro("codigoSetorComercialInicial"));
		helper.setSetorComercialFinal((String) getParametro("setorComercialFinal"));
		helper.setCodigoSetorComercialFinal((String) getParametro("codigoSetorComercialFinal"));
		
		helper.setQuadraInicial((String) getParametro("quadraInicial"));
		helper.setQuadraFinal((String) getParametro("quadraFinal"));
		helper.setRotaInicial((String) getParametro("rotaInicial"));
		helper.setRotaFinal((String) getParametro("rotaFinal"));
		helper.setRotaSequenciaInicial((String) getParametro("rotaSequenciaInicial"));
		helper.setRotaSequenciaFinal((String) getParametro("rotaSequenciaFinal"));
		
		helper.setLogradouro((String) getParametro("logradouro"));
		helper.setDescricaoLogradouro((String) getParametro("descricaoLogradouro"));
		
		// CARACTERÍSTICAS
		helper.setPerfilImovel((String) getParametro("perfilImovel"));
		helper.setCategoria((String) getParametro("categoria"));
		helper.setSubCategoria((String) getParametro("subCategoria"));
		
		helper.setQuantidadeEconomiasInicial((String) getParametro("intervaloQuantidadeEconomiasInicial"));
		helper.setQuantidadeEconomiasFinal((String) getParametro("intervaloQuantidadeEconomiasFinal"));
		helper.setQuantidadeDocumentosInicial((String) getParametro("intervaloQuantidadeDocumentosInicial"));
		helper.setQuantidadeDocumentosFinal((String) getParametro("intervaloQuantidadeDocumentosFinal"));
		
		helper.setNumeroMoradoresInicial((String) getParametro("intervaloNumeroMoradoresInicial"));
		helper.setNumeroMoradoresFinal((String) getParametro("intervaloNumeroMoradoresFinal"));
		helper.setAreaConstruidaInicial((String) getParametro("intervaloAreaConstruidaInicial"));
		helper.setAreaConstruidaFinal((String) getParametro("intervaloAreaConstruidaFinal"));
		
		helper.setSituacaoLigacaoAgua((String[])getParametro("situacaoLigacaoAgua"));
		helper.setSituacaoLigacaoAguaDescricao((String)getParametro("situacaoLigacaoAguaDescricao"));
		
		String imovelCondominio = "";
		if(getParametro("imovelCondominio") != null){
			imovelCondominio = (String) getParametro("imovelCondominio");
		}
		helper.setImovelCondominio(imovelCondominio);
		
		helper.setMediaImovel((String) getParametro("mediaImovel"));
		helper.setConsumoPorEconomia((String) getParametro("consumoPorEconomia"));
		helper.setConsumoPorEconomiaFinal((String) getParametro("consumoPorEconomiaFinal"));
		
		String tipoMedicao = "";
		if(getParametro("tipoMedicao") != null){
			tipoMedicao = (String) getParametro("tipoMedicao");
		}
		helper.setTipoMedicao(tipoMedicao);
		
		// HIDRÔMETRO
		helper.setCapacidadeHidrometro((String[]) getParametro("capacidade"));
		helper.setMarcaHidrometro((String) getParametro("marca"));
		helper.setAnormalidadeHidrometro((String[]) getParametro("anormalidadeHidrometro"));
		helper.setNumeroOcorrenciasAnormalidade((String) getParametro("numeroOcorrenciasConsecutivas"));
		//helper.setMesAnoInstalacaoHidrometro((String) getParametro("mesAnoInstalacao"));
		helper.setMesAnoInstalacaoInicialHidrometro((String) getParametro("mesAnoInstalacaoInicial"));
		helper.setMesAnoInstalacaoFinalHidrometro((String) getParametro("mesAnoInstalacaoFinal"));
		
		if(getParametro("localInstalacao") != null){
			helper.setLocalInstalacaoHidrometro((String) getParametro("localInstalacao"));
		}
		
		// SUGESTÃO
		helper.setDescricaoPerfilImovel((String) getParametro("perfilImovelDescricao"));
		helper.setDescricaoCategoria((String) getParametro("categoriaDescricao"));
		helper.setDescricaoSubcategoria((String) getParametro("subCategoriaDescricao"));
		helper.setQuantidadeEconomia((String) getParametro("quantidadeEconomia"));
		helper.setQuantidadeDocumentos((String) getParametro("quantidadeDocumentos"));
		helper.setNumeroMoradores((String) getParametro("numeroMoradores"));
		helper.setAreaConstruida((String) getParametro("areaConstruida"));
		helper.setConsumoEconomia((String) getParametro("consumoEconomia"));
		helper.setDescricaoTipoMedicao((String) getParametro("tipoMedicaoDescricao"));
		helper.setDescricaoCapacidade((String) getParametro("capacidadeDescricao"));
		helper.setDescricaoMarcaHidrometro((String) getParametro("marcaDescricao"));
		helper.setDescricaoLocalInstalacaoHidrometro((String) getParametro("localInstalacaoDescricao"));
		return helper;
	}
	
	/**
	 * Montando o BEAN a partir dos parâmetros passados
	 *
	 * @author Raphael Rossiter
	 * @date 17/04/2009
	 *
	 * @param helper
	 * @param totalSelecionados
	 * @return RelatorioEmitirOrdemServicoSeletivaSugestaoBean
	 */
	private RelatorioEmitirOrdemServicoSeletivaSugestaoBean gerarRelatorioBean(
			ImovelEmissaoOrdensSeletivasHelper helper, String totalSelecionados){
		
		RelatorioEmitirOrdemServicoSeletivaSugestaoBean relatorioEmitirOrdemServicoSeletivaSugestaoBean = 
		new RelatorioEmitirOrdemServicoSeletivaSugestaoBean();
		
		Fachada fachada = Fachada.getInstancia();
		
		// Imprimir pagina com os parametros informados e a quantidade de imoveis selecionados.
		
		//Preenche o BEAN do Relatorio
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setDescricaoTipoServico(helper.getTipoOrdem());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTotalSelecionados(totalSelecionados);
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setFirma(helper.getFirma());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeFirma(helper.getNomeFirma());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeElo(helper.getNomeElo());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTipoOrdem(helper.getTipoOrdem());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeMaxima(helper.getQuantidadeMaxima());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeLocalidadeInicial(helper.getNomeLocalidadeInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeLocalidadeFinal(helper.getNomeLocalidadeFinal());
		
		// Foi Solicitado o Codigo em vez do Nome
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeSetorComercialInicial(helper.getCodigoSetorComercialInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeSetorComercialFinal(helper.getCodigoSetorComercialFinal());
		
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuadraInicial(helper.getQuadraInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuadraFinal(helper.getQuadraFinal());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setPerfilImovelDescricao(helper.getDescricaoPerfilImovel());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCategoriaDescricao(helper.getDescricaoCategoria());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setSubCategoriaDescricao(helper.getDescricaoSubcategoria());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeEconomia(helper.getQuantidadeEconomia());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeDocumentos(helper.getQuantidadeDocumentos());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNumeroMoradores(helper.getNumeroMoradores());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setAreaConstruida(helper.getAreaConstruida());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setImovelCondominio(helper.getImovelCondominio());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMediaImovel(helper.getMediaImovel());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setConsumoEconomia(helper.getConsumoEconomia());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTipoMedicaoDescricao(helper.getDescricaoTipoMedicao());
		//relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCapacidadeDescricao(helper.getDescricaoCapacidade());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMarcaDescricao(helper.getDescricaoMarcaHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setLocalInstalacaoDescricao(helper.getDescricaoLocalInstalacaoHidrometro());
		
		if (helper.getMesAnoInstalacaoInicialHidrometro() != null && !helper.getMesAnoInstalacaoInicialHidrometro().equals("")) {
			
			String mesAnoInstalacaoInicialHidrometro = helper.getMesAnoInstalacaoInicialHidrometro().substring(4, 6)
			+ "/" + helper.getMesAnoInstalacaoInicialHidrometro().substring(0, 4);
			
			helper.setMesAnoInstalacaoInicialHidrometro(mesAnoInstalacaoInicialHidrometro);
		}
		
		if (helper.getMesAnoInstalacaoFinalHidrometro() != null && !helper.getMesAnoInstalacaoFinalHidrometro().equals("")) {
			
			String mesAnoInstalacaoFinalHidrometro = helper.getMesAnoInstalacaoFinalHidrometro().substring(4, 6)
			+ "/" + helper.getMesAnoInstalacaoFinalHidrometro().substring(0, 4);
			
			helper.setMesAnoInstalacaoFinalHidrometro(mesAnoInstalacaoFinalHidrometro);
		}
		
		//relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacao(helper.getMesAnoInstalacaoHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacaoInicial(helper.getMesAnoInstalacaoInicialHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacaoFinal(helper.getMesAnoInstalacaoFinalHidrometro());
		
		String anormalidadeHidrometro = new String();
	
		if (helper.getAnormalidadeHidrometro() != null){
			for ( int i = 0; i < helper.getAnormalidadeHidrometro().length; i++ ){
				
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				
				filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroLeituraAnormalidade.ID, helper.getAnormalidadeHidrometro()[i]));
				
				Collection collectionHidrometroAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade,
						LeituraAnormalidade.class.getName());
				
				LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) Util.retonarObjetoDeColecao(collectionHidrometroAnormalidade);
				
				anormalidadeHidrometro = anormalidadeHidrometro + leituraAnormalidade.getDescricao()+"\n";
				
			}
		}
		//String situacaoLigacaoAguaDescricao = "--";
		String situacaoLigacaoAguaDescricao = new String();
		if (helper.getSituacaoLigacaoAgua()!= null){
			for ( int i = 0; i < helper.getSituacaoLigacaoAgua().length; i++ ){
				
				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAguaSituacao.ID, helper.getSituacaoLigacaoAgua()[i]));
				
				Collection colecaoLigacaoAguaSituacao = Fachada.getInstancia()
							.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
				
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
				
				situacaoLigacaoAguaDescricao = situacaoLigacaoAguaDescricao + ligacaoAguaSituacao.getDescricao()+"\n";
				
			}
		}
		
		String capacidadeDescricao = new String();
		
		if (helper.getCapacidadeHidrometro() != null){
			for ( int i = 0; i < helper.getCapacidadeHidrometro().length; i++ ){
				
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
				
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
						FiltroHidrometroCapacidade.ID, helper.getCapacidadeHidrometro()[i]));
				
				Collection collectionHidrometroCapacidade = fachada.pesquisar(filtroHidrometroCapacidade,
						HidrometroCapacidade.class.getName());
				
				HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) Util.retonarObjetoDeColecao(collectionHidrometroCapacidade);
				
				capacidadeDescricao = capacidadeDescricao + hidrometroCapacidade.getDescricao()+"\n";
				
			}
		}
		
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setSituacaoLigacaoAguaDescricao(helper.getSituacaoLigacaoAguaDescricao());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCapacidadeDescricao(capacidadeDescricao);
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setAnormalidadeHidrometro(anormalidadeHidrometro);
		
		return relatorioEmitirOrdemServicoSeletivaSugestaoBean;
	}
	
	/**
	 * Montando o BEAN agrupados pela localidade
	 *
	 * @author Rodrigo Cabral
	 * @date 30/03/2011
	 *
	 * @param helper
	 * @param colecaoDadosRelatorio
	 * @return RelatorioEmitirOrdemServicoSeletivaSugestaoBean
	 */
	private List gerarRelatorioBeanAgrupadoLocalidadeOuSetor(
			ImovelEmissaoOrdensSeletivasHelper helper, Collection colecaoDadosRelatorio){
		
		
		Fachada fachada = Fachada.getInstancia();
		
		List colecaoBean = new ArrayList();
		Collection colecaoImoveis = new ArrayList();
		String totalSelecionados = "0";
		
		Iterator iColecaoDadosRelatorio = colecaoDadosRelatorio.iterator();
		
		while (iColecaoDadosRelatorio.hasNext()){
		
			RelatorioEmitirOrdemServicoSeletivaSugestaoBean relatorioEmitirOrdemServicoSeletivaSugestaoBean = 
				new RelatorioEmitirOrdemServicoSeletivaSugestaoBean();
			
			Map colecaoDados = (HashMap) iColecaoDadosRelatorio.next();
			
			colecaoImoveis = (Collection) colecaoDados.get("colecaoImoveis");
			totalSelecionados = Util.converterObjetoParaString(colecaoImoveis.size());
			
		// Imprimir pagina com os parametros informados e a quantidade de imoveis selecionados.
		
		//Preenche o BEAN do Relatorio
		
		if (((helper.getLocalidadeInicial() == null && helper.getLocalidadeFinal() == null)) ||
				(helper.getLocalidadeInicial() != null && (!helper.getLocalidadeFinal().equals(helper.getLocalidadeInicial())))){
				
			relatorioEmitirOrdemServicoSeletivaSugestaoBean.setIdLocalidade(colecaoDados.get("idLocalidade").toString());
			relatorioEmitirOrdemServicoSeletivaSugestaoBean.setDesLocalidade(colecaoDados.get("desLocalidade").toString());
		
		} else if (helper.getLocalidadeInicial() != null && (helper.getLocalidadeFinal().equals(helper.getLocalidadeInicial()))) {
			
			relatorioEmitirOrdemServicoSeletivaSugestaoBean.setIdSetorComercial(colecaoDados.get("idSetorComercial").toString());
			relatorioEmitirOrdemServicoSeletivaSugestaoBean.setDesSetorComercial(colecaoDados.get("desSetorComercial").toString());
		}
		
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setDescricaoTipoServico(helper.getTipoOrdem());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTotalSelecionados(totalSelecionados);
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setFirma(helper.getFirma());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeFirma(helper.getNomeFirma());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeElo(helper.getNomeElo());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTipoOrdem(helper.getTipoOrdem());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeMaxima(helper.getQuantidadeMaxima());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeLocalidadeInicial(helper.getNomeLocalidadeInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeLocalidadeFinal(helper.getNomeLocalidadeFinal());
		
		// Foi Solicitado o Codigo em vez do Nome
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeSetorComercialInicial(helper.getCodigoSetorComercialInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeSetorComercialFinal(helper.getCodigoSetorComercialFinal());
		
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuadraInicial(helper.getQuadraInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuadraFinal(helper.getQuadraFinal());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setPerfilImovelDescricao(helper.getDescricaoPerfilImovel());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCategoriaDescricao(helper.getDescricaoCategoria());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setSubCategoriaDescricao(helper.getDescricaoSubcategoria());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeEconomia(helper.getQuantidadeEconomia());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeDocumentos(helper.getQuantidadeDocumentos());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNumeroMoradores(helper.getNumeroMoradores());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setAreaConstruida(helper.getAreaConstruida());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setImovelCondominio(helper.getImovelCondominio());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMediaImovel(helper.getMediaImovel());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setConsumoEconomia(helper.getConsumoEconomia());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTipoMedicaoDescricao(helper.getDescricaoTipoMedicao());
		//relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCapacidadeDescricao(helper.getDescricaoCapacidade());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMarcaDescricao(helper.getDescricaoMarcaHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setLocalInstalacaoDescricao(helper.getDescricaoLocalInstalacaoHidrometro());
		
		if (helper.getMesAnoInstalacaoInicialHidrometro() != null && !helper.getMesAnoInstalacaoInicialHidrometro().equals("")) {
			
			String mesAnoInstalacaoInicialHidrometro = helper.getMesAnoInstalacaoInicialHidrometro().substring(4, 6)
			+ "/" + helper.getMesAnoInstalacaoInicialHidrometro().substring(0, 4);
			
			helper.setMesAnoInstalacaoInicialHidrometro(mesAnoInstalacaoInicialHidrometro);
		}
		
		if (helper.getMesAnoInstalacaoFinalHidrometro() != null && !helper.getMesAnoInstalacaoFinalHidrometro().equals("")) {
			
			String mesAnoInstalacaoFinalHidrometro = helper.getMesAnoInstalacaoFinalHidrometro().substring(4, 6)
			+ "/" + helper.getMesAnoInstalacaoFinalHidrometro().substring(0, 4);
			
			helper.setMesAnoInstalacaoFinalHidrometro(mesAnoInstalacaoFinalHidrometro);
		}
		
		//relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacao(helper.getMesAnoInstalacaoHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacaoInicial(helper.getMesAnoInstalacaoInicialHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacaoFinal(helper.getMesAnoInstalacaoFinalHidrometro());
		
		String anormalidadeHidrometro = new String();
	
		if (helper.getAnormalidadeHidrometro() != null){
			for ( int i = 0; i < helper.getAnormalidadeHidrometro().length; i++ ){
				
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				
				filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroLeituraAnormalidade.ID, helper.getAnormalidadeHidrometro()[i]));
				
				Collection collectionHidrometroAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade,
						LeituraAnormalidade.class.getName());
				
				LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) Util.retonarObjetoDeColecao(collectionHidrometroAnormalidade);
				
				anormalidadeHidrometro = anormalidadeHidrometro + leituraAnormalidade.getDescricao()+"\n";
				
			}
		}
		//String situacaoLigacaoAguaDescricao = "--";
		String situacaoLigacaoAguaDescricao = new String();
		if (helper.getSituacaoLigacaoAgua()!= null){
			for ( int i = 0; i < helper.getSituacaoLigacaoAgua().length; i++ ){
				
				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAguaSituacao.ID, helper.getSituacaoLigacaoAgua()[i]));
				
				Collection colecaoLigacaoAguaSituacao = Fachada.getInstancia()
							.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
				
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
				
				situacaoLigacaoAguaDescricao = situacaoLigacaoAguaDescricao + ligacaoAguaSituacao.getDescricao()+"\n";
				
			}
		}
		
		String capacidadeDescricao = new String();
		
		if (helper.getCapacidadeHidrometro() != null){
			for ( int i = 0; i < helper.getCapacidadeHidrometro().length; i++ ){
				
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
				
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
						FiltroHidrometroCapacidade.ID, helper.getCapacidadeHidrometro()[i]));
				
				Collection collectionHidrometroCapacidade = fachada.pesquisar(filtroHidrometroCapacidade,
						HidrometroCapacidade.class.getName());
				
				HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) Util.retonarObjetoDeColecao(collectionHidrometroCapacidade);
				
				capacidadeDescricao = capacidadeDescricao + hidrometroCapacidade.getDescricao()+"\n";
				
			}
		}
		
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setSituacaoLigacaoAguaDescricao(helper.getSituacaoLigacaoAguaDescricao());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCapacidadeDescricao(capacidadeDescricao);
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setAnormalidadeHidrometro(anormalidadeHidrometro);
		
		colecaoBean.add(relatorioEmitirOrdemServicoSeletivaSugestaoBean);
		
		}
		
		return colecaoBean;
	}
	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = Fachada
				.getInstancia()
				.filtrarImovelEmissaoOrdensSeletivasCount(gerarObjetoHelper());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioEmitirOrdemServicoSeletivaSugestao", this);
	}
}
