package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean;
import gcom.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
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
 * 		Relatório Ordem Seletiva Analítico
 * 
 * @author Hugo Amorim
 * @date 03/03/2010
 */
public class RelatorioEmitirOrdemServicoSeletivaAnalitico extends
		TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RelatorioEmitirOrdemServicoSeletivaAnalitico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA);
	}
	
	@Deprecated
	public RelatorioEmitirOrdemServicoSeletivaAnalitico() {
		super(null, "");
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = Fachada
				.getInstancia()
				.filtrarImovelEmissaoOrdensSeletivasCount(gerarObjetoHelper());
		
		if(retorno == 0){
			 throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		 }

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioEmitirOrdemServicoSeletivaAnalitico", this);

	}

	@Override
	public Object executar() throws TarefaException {
		
		byte[] retorno = null;
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		ImovelEmissaoOrdensSeletivasHelper helper = gerarObjetoHelper();
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		List relatorioBeans = new ArrayList();
		
		//[UC0711] Filtro para Emissao de Ordens Seletivas
		Collection<Integer> colecaoIdsImoveis = fachada.filtrarImovelEmissaoOrdensSeletivas(helper);
		/*
		  
		String anoMesFaturamento = 
			Util.subtraiAteSeisMesesAnoMesReferencia(
					sistemaParametro.getAnoMesFaturamento(), 1).toString();  
		 
		String tipoOrdem = helper.getTipoOrdem();
		String idElo = helper.getElo();
	    String idLocalidadeIncial = helper.getLocalidadeInicial();
		String idLocalidadeFinal = helper.getLocalidadeFinal();
		String codigoSetorComercialInicial = helper.getCodigoSetorComercialInicial();
		String codigoSetorComercialFinal =helper.getCodigoSetorComercialFinal();
			
		//CRIAÇÃO DO ARQUIVO
		String nomeZip = "ORDEM_DE_" + tipoOrdem + "_DE_HIDROMETRO_";
		
		if (idElo != null && !idElo.equals("")) {
			
			nomeZip += idElo + "_" + Util.formatarDataComHora(new Date()).replace("/", "_").replace(":", "_");
			
		}else if (idLocalidadeIncial != null && !idLocalidadeIncial.equals("") 
				&& idLocalidadeFinal != null && !idLocalidadeFinal.equals("") 
				&& codigoSetorComercialInicial != null && !codigoSetorComercialInicial.equals("") 
				&& codigoSetorComercialFinal != null && !codigoSetorComercialFinal.equals("")){
			
			nomeZip += idLocalidadeIncial + "_" + idLocalidadeFinal + "_" +
					   codigoSetorComercialInicial + "_" + codigoSetorComercialFinal + "_" +
					   Util.formatarDataComHora(new Date()).replace("/", "_").replace(":", "_");
			
		}else if (idLocalidadeIncial != null && !idLocalidadeIncial.equals("") 
				&& idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			
			nomeZip += idLocalidadeIncial + "_" + idLocalidadeFinal + "_" +
						Util.formatarDataComHora(new Date()).replace("/", "_").replace(":", "_");
			
		}else {
			
			nomeZip += Util.formatarDataComHora(new Date()).replace("/", "_").replace(":", "_");
			
		}
		
		nomeZip = nomeZip.replace(" ", "_");
		
		File compactado = null;
		ZipOutputStream zos  = null;
		File leitura  = null;
		BufferedWriter out  = null;
		
		try{

			compactado = new File(nomeZip + ".zip"); // nomeZip
			zos = new ZipOutputStream(new FileOutputStream(compactado));
	
			leitura = new File(nomeZip + ".txt");
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(leitura.getAbsolutePath())));
			
			 
			//FIM CRIAÇÃO DO ARQUIVO
			
			String idProjeto = null;
			Integer idTipoServico = null;
			Integer idEmpresa = null;
			Integer idOrdemServico = null;
			StringBuilder documentoTxt = new StringBuilder();
			
		*/	
			Collection<RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean> 
					colecaoSubcategorias = this.pesquisarSubcategorias(fachada);
		
			RelatorioEmitirOrdemServicoSeletivaAnaliticoBean bean = null;
			
			for (Integer idImovel : colecaoIdsImoveis) {
				
				/*
				if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)) {
					idTipoServico = ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO;
				}else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_SUBSTITUICAO)){
					idTipoServico = ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO;
				}else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_REMOCAO)){
					idTipoServico = ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO;
				}
				
				idEmpresa = Util.converterStringParaInteger(helper.getFirma());
				
				idProjeto = (String) getParametro("idProjeto");
				
				// [UC0430] - Gerar Ordem de Servico
				idOrdemServico = geraOrdemServico(idTipoServico, idEmpresa, idImovel, idProjeto);
				
				documentoTxt = this.gerarLinhaTxt(idImovel,idOrdemServico,helper,fachada,anoMesFaturamento,idTipoServico);
			
				out.write(documentoTxt.toString());
				*/
				bean = fachada
					.pesquisarDadosRelatorioRelatorioEmitirOsSeletivaAnalitico(
							idImovel,colecaoSubcategorias,sistemaParametro);
				
				//bean.setNumeroOs(idOrdemServico.toString());
				
				if(bean!=null){
					relatorioBeans.add(bean);
				} 
				
				
			}
		/*	
			//Finaliza estrutura do arquivo
			documentoTxt.append("\u0004");
			out.write(documentoTxt.toString());
			out.close();
			ZipUtil.adicionarArquivo(zos, leitura);
			zos.close();
			leitura.delete();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gerar arquivo.");
		}
	    */
					
		Map parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);
		// ------------------------------------
		// Gera o relatório 
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_ANALITICO,
				parametros, ds, tipoFormatoRelatorio);
		// 			  Fim 
		// ------------------------------------
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_ANALITICO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// 			  Fim 
		// ------------------------------------
		
		return retorno;
	}
	
	private Collection<RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean> 
		pesquisarSubcategorias(Fachada fachada) {
		
		Collection<RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean> retorno = 
			new ArrayList<RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean>();

		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		filtroCategoria.adicionarParametro(
				new ParametroSimples(FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Categoria> colecaoCategoria = 
			fachada.pesquisar(filtroCategoria, Categoria.class.getName());
		
		for (Categoria categoria : colecaoCategoria) {
			
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
			
			filtroSubCategoria.setCampoOrderBy(FiltroSubCategoria.CATEGORIA_ID);
			
			filtroSubCategoria.adicionarParametro(
					new ParametroSimples(FiltroSubCategoria.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroSubCategoria.adicionarParametro(
					new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID,
							categoria.getId()));
			
			filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);

			Collection<Subcategoria> colecaoSubcategorias = fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean catBean = 
				new RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean(
						categoria.getDescricao(),
						null
						);
			
			retorno.add(catBean);
			
			String subs = "";
			int count = 0;
			for (Iterator iterator = colecaoSubcategorias.iterator(); iterator.hasNext();) {
				
				Subcategoria subcategoria = (Subcategoria) iterator.next();
			
				count++;
				subs += "  (    ) " + subcategoria.getDescricao()+" ";
				if(subs.length()>50){
					
					RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean subBean = 
						new RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean(
								subs,
								null
								);
					subs="";
					count =0;	
					retorno.add(subBean);
				
				}else if(count==5){
					
					RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean subBean = 
						new RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean(
								subs,
								null
								);
					subs="";
					count =0;	
					retorno.add(subBean);
					
				}else if(!iterator.hasNext()){
					
					RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean subBean = 
						new RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean(
								subs,
								null
								);
					
					retorno.add(subBean);
					
				}
			}
		}
		
		return retorno;
	}
	/*
	private StringBuilder gerarLinhaTxt(
			Integer idImovel,Integer idOrdemServico,
			ImovelEmissaoOrdensSeletivasHelper helper,
			Fachada fachada, String anoMesFaturamento, Integer idTipoServico) {
		
		Imovel imovel = null;
		Cliente cliente = null;
		ClienteFone clienteFone = null;
		boolean achou = false;
		Collection colecaoEconomiasCategoria = null;
		Categoria entCategoria = null;
		Hidrometro hidrometro = null;
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
		
		StringBuilder documentoTxt = new StringBuilder();

		// Numero de Ordem
		documentoTxt.append(Util.completaStringComEspacoAEsquerda(
				Util.adicionarZerosEsquedaNumero(9, idOrdemServico.toString()), 9));
		
		// Data de Emissao
		String dataAtual = Util.formatarData(new Date());
		documentoTxt.append(Util.completaString(dataAtual, 10));
		
		// Data de Validade
		String dataValidade = "";
		Date data = Util.adicionarNumeroDiasDeUmaData(new Date(), 90);
		// Adiciona 0 ao dia se necessario
		if (Util.getDiaMes(data) < 10) {
			dataValidade = "0" + Util.getDiaMes(data) + "/";
		}else {
			dataValidade = Util.getDiaMes(data) + "/";
		}
		
		// Adiciona 0 ao mes se necessario
		if (Util.getMes(data) < 10) {
			dataValidade += "0" + Util.getMes(data) + "/" + Util.getAno(data);
		}else {
			dataValidade += Util.getMes(data) + "/" + Util.getAno(data);
		}
		
		documentoTxt.append(Util.completaString(dataValidade, 10));
		
		// Matricula do Imovel
		documentoTxt.append(Util.completaStringComEspacoAEsquerda(
				Util.adicionarZerosEsquedaNumero(9, idImovel.toString()), 9));
		
		// Inscricao
		//imovel = new Imovel();
		imovel = fachada.pesquisarImovel(idImovel);
		documentoTxt.append(Util.completaString(imovel.getInscricaoFormatada(), 20));
		
		*//**
		 *  Dados do Cliente
		 *//*
		cliente = new Cliente();
		cliente = fachada.consultarClienteUsuarioImovel(imovel);
		
		// Numero Telefone
		String telefone = "";
		if (!cliente.getClienteFones().isEmpty()) {
			Iterator iClienteFone = cliente.getClienteFones().iterator();
			clienteFone = new ClienteFone();
			
			while (iClienteFone.hasNext() & !achou) {
				clienteFone = (ClienteFone) iClienteFone.next();
				
				if (clienteFone.getIndicadorTelefonePadrao().equals(ClienteFone.INDICADOR_FONE_PADRAO)) {
					telefone = clienteFone.getTelefone();
					achou = true;
				}
			}
		}
		documentoTxt.append(Util.completaString(telefone, 9));
		
		// Nome do Cliente
		documentoTxt.append(Util.completaString(cliente.getNome(), 40));
		
		// CPF/CNPJ do Cliente
		String cpfCnpj = "";
		if (cliente.getCpf() != null) {
			cpfCnpj = Util.completaStringComEspacoAEsquerda(cliente.getCpfFormatado(), 19);
		}else if (cliente.getCnpj() != null) {
			cpfCnpj = Util.completaStringComEspacoAEsquerda(cliente.getCnpjFormatado(), 19);
		}else {
			cpfCnpj = Util.completaStringComEspacoAEsquerda("", 19);
		}
		
		documentoTxt.append(cpfCnpj);
		
		// Quantidade de Economias por Categoria
		colecaoEconomiasCategoria = fachada.obterQuantidadeEconomiasCategoria(imovel);
		
		if (colecaoEconomiasCategoria != null && !colecaoEconomiasCategoria.isEmpty()) {
			Iterator icolecaoEconomiasCategoria = colecaoEconomiasCategoria.iterator();
			entCategoria = new Categoria();
			int x = 0;
			
			while ( (icolecaoEconomiasCategoria.hasNext()) || (x < 4)) {
				if (icolecaoEconomiasCategoria.hasNext()) {
					entCategoria = (Categoria) icolecaoEconomiasCategoria.next();
					documentoTxt.append(Util.completaStringComEspacoAEsquerda(
							Util.adicionarZerosEsquedaNumero(4, entCategoria.
									getQuantidadeEconomiasCategoria().toString()), 4));
					
					//quantidadeEconomias += entCategoria.getQuantidadeEconomiasCategoria();
				}else {
					documentoTxt.append("0000");
				}
				
				x++;
			}
			
			//documentoTxt.append(Util.completaStringComEspacoAEsquerda(""+quantidadeEconomias, 4));
			//quantidadeEconomias = 0;
		}else {
			documentoTxt.append("0000000000000000");
		}
		
		// Perfil do Imovel
		documentoTxt.append(Util.completaString(imovel.getImovelPerfil().getDescricao(), 15));
		
		// Endereco Abreviado do Imovel
		String enderecoAbreviado = "";
		enderecoAbreviado = fachada.pesquisarEndereco(idImovel);
		documentoTxt.append(Util.completaString(enderecoAbreviado, 55));
		
		// Situacao de Esgoto
		documentoTxt.append(Util.completaString(imovel.getLigacaoEsgotoSituacao().getDescricao(), 10));
		
		// Consumo Minimo
		String consumoMinimo = "";
		if (imovel.getLigacaoEsgoto() != null) {
			if (imovel.getLigacaoEsgoto().getConsumoMinimo() != null) {
				consumoMinimo = ""+imovel.getLigacaoEsgoto().getConsumoMinimo();
			}
		}
		documentoTxt.append(Util.completaString(
				Util.adicionarZerosEsquedaNumero(5, consumoMinimo), 5));
		
		// Situacao de Agua
		documentoTxt.append(Util.completaString(imovel.getLigacaoAguaSituacao().getDescricao(), 15));
		
		*//**
		 * Caso o Tipo de Ordem seja SUBSTITUICAO
		 *//*
		if (idTipoServico.equals(ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO)) {
			LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();
			if (ligacaoAgua.getHidrometroInstalacaoHistorico() != null) {
				//hidrometro = new Hidrometro();
				hidrometro = ligacaoAgua.getHidrometroInstalacaoHistorico().getHidrometro();
				
				Integer idHidrometro = hidrometro.getId();
				
				// Cria o Filtro para Hidrometro
				FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.ID, idHidrometro));
				filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
				filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
				filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroDiametro");
				
				Collection<Hidrometro> colecaoHidrometro = 
					fachada.pesquisar(filtroHidrometro, Hidrometro.class.getName());
				
				Iterator iColecaoHidrometro = colecaoHidrometro.iterator();
				Hidrometro dadosHidrometro = (Hidrometro) iColecaoHidrometro.next();
				
				// Marca Hidrometro
				documentoTxt.append(Util.completaString(dadosHidrometro.getHidrometroMarca().
						getDescricao(), 15));
				
				// Capacidade Hidrometro
				documentoTxt.append(Util.completaString(dadosHidrometro.getHidrometroCapacidade().
						getDescricaoAbreviada(), 6));
				
				// Numero do Hidrometro
				documentoTxt.append(Util.completaString(hidrometro.getNumero(), 10));
				
				// Diametro do Hidrometro
				documentoTxt.append(Util.completaString(dadosHidrometro.getHidrometroDiametro().
						getDescricaoAbreviada(), 5));
				
				// Local de Instalacao do Hidrometro
				hidrometroInstalacaoHistorico = ligacaoAgua.getHidrometroInstalacaoHistorico();
				documentoTxt.append(Util.completaString(hidrometroInstalacaoHistorico.
						getHidrometroLocalInstalacao().getDescricaoAbreviada(), 5));
				
				// Leitura Hidrometro
				FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
				filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
						FiltroMedicaoHistorico.LIGACAO_AGUA_ID, ligacaoAgua.getId()));
				
				if (helper.getTipoMedicao().equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
					filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
							FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.LIGACAO_AGUA));
				}else if (helper.getTipoMedicao().equals(MedicaoTipo.POCO.toString())) {
					filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
							FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.POCO));
				}
				
				filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
						FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO, anoMesFaturamento));
				
				filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
				
				Collection<MedicaoHistorico> colecaoMedicaoHistorico = 
					fachada.pesquisar(filtroMedicaoHistorico, MedicaoHistorico.class.getName());
				
				MedicaoHistorico dadosMedicaoHistorico = null;
				if (colecaoMedicaoHistorico != null && !colecaoMedicaoHistorico.isEmpty()) {
					Iterator iColecaoMedicaoHistorico = colecaoMedicaoHistorico.iterator();
					dadosMedicaoHistorico = (MedicaoHistorico) iColecaoMedicaoHistorico.next();
					
					documentoTxt.append(Util.completaStringComEspacoAEsquerda(
							Util.adicionarZerosEsquedaNumero(7, ""+dadosMedicaoHistorico.
							getLeituraAtualFaturamento()), 7));
				}else {
					documentoTxt.append(Util.completaStringComEspacoAEsquerda(
							Util.adicionarZerosEsquedaNumero(7, ""), 7));
				}
				
				// Data de Instalacao do Hidrometro
				String dataInstalacao = "";
				if (hidrometroInstalacaoHistorico.getDataInstalacao() != null) {
					dataInstalacao = hidrometroInstalacaoHistorico.getDataInstalacao().toString();
					dataInstalacao = dataInstalacao.substring(8, 10) + "/" +
									 dataInstalacao.substring(5, 7) + "/" +
									 dataInstalacao.substring(0, 4);
				}
				documentoTxt.append(Util.completaString(dataInstalacao, 10));
				
				// Protecao Hidrometro
				String protecao = "";
				HidrometroProtecao hidrometroProtecao = 
					hidrometroInstalacaoHistorico.getHidrometroProtecao();
				if (hidrometroProtecao.getId() != null) {
					protecao = hidrometroProtecao.getDescricaoAbreviada();
				}
				documentoTxt.append(Util.completaString(protecao, 6));
				
				// Cavalete Hidrometro
				if (hidrometroInstalacaoHistorico.getIndicadorExistenciaCavalete().
						equals(HidrometroInstalacaoHistorico.INDICADOR_CAVALETE_SIM.shortValue())) {
					documentoTxt.append("SIM");
				}else if (hidrometroInstalacaoHistorico.getIndicadorExistenciaCavalete().
						equals(HidrometroInstalacaoHistorico.INDICADOR_CAVALETE_NAO.shortValue())) {
					documentoTxt.append("NAO");
				}
				
				// Anormalidade
				String descricaoAnormalidade = "";
				if (dadosMedicaoHistorico != null) {
					if (dadosMedicaoHistorico.getLeituraAnormalidadeFaturamento() != null) {
						LeituraAnormalidade leitura = dadosMedicaoHistorico.getLeituraAnormalidadeFaturamento();
						descricaoAnormalidade = leitura.getDescricao();
					}
				}
				documentoTxt.append(Util.completaString(descricaoAnormalidade, 25));
			}
		}
		
		// Nome da Firma
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, helper.getFirma()));
		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		Iterator iColecaoEmpresa = colecaoEmpresa.iterator();
		Empresa empresa = (Empresa) iColecaoEmpresa.next();
		
		documentoTxt.append(Util.completaString(empresa.getDescricaoAbreviada(), 10));
		
		// Adiciona o Numero da Pagina
		//documentoTxt.append("|" + (indice + 1) + "/" + quantidadePaginas);
		
		documentoTxt.append(System.getProperty("line.separator"));
		
		
		return documentoTxt;
	}

	
	
	private Integer geraOrdemServico(Integer idTipoServico, Integer idEmpresa, Integer idImovel, String idProjeto) {
		OrdemServico ordemServico = null;
		ServicoTipo servicoTipo = null;
		Empresa empresa = null;
		Integer retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		// [UC0430] - Gerar Ordem de Servico
		servicoTipo = new ServicoTipo();
		servicoTipo.setId(idTipoServico);
		
		ordemServico = new OrdemServico();
		ordemServico.setServicoTipo(servicoTipo);
		
		Imovel imovel = new Imovel();
		imovel.setId(idImovel);
		ordemServico.setImovel(imovel);
		
		if(idProjeto!=null && !idProjeto.equals("")){
			Projeto projeto = new Projeto();
			
			Integer id = new Integer(idProjeto);
			
			projeto.setId(id);
			
			ordemServico.setProjeto(projeto);
		}
		
		// Alterado por Victor Cisneiros - 05/11/2008
		// Verificar se a empresa é a principal
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new TarefaException("Empresa com id " + idEmpresa + " não encontrada.");
		}
		
		empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
		
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		
		// Caso a empresa seja principal (indicadorEmpresaPrincipal = 1) 
		// obter a unidade a partir da unidade que representa a localidade do imovel
		if (empresa.getIndicadorEmpresaPrincipal() != null && 
			empresa.getIndicadorEmpresaPrincipal().equals(Empresa.INDICADOR_EMPRESA_PRINCIPAL)) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			Collection pesquisaImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			if (pesquisaImovel == null || pesquisaImovel.isEmpty()) {
				throw new TarefaException("Imovel com id " + idImovel + "nao encontrado");
			}
			
			Imovel im = (Imovel) Util.retonarObjetoDeColecao(pesquisaImovel);
			
			Integer idLocalidade = im.getLocalidade().getId();
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.EMPRESA, idEmpresa));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_LOCALIDADE, idLocalidade));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoUnidadeOrganizacional = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
				
				//[FS0011]-Verificar existência de mais de uma unidade correspondente à empresa
				if(colecaoUnidadeOrganizacional.size() > 1){
					throw new TarefaException("Existe mais de uma unidade correspondente à empresa da ordem de serviço seletiva.Erro grave.Entre em contato com o analista responsável pelo sistema.");
				}
				
				unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				
			}else {
				throw new TarefaException("Unidade Organizacional não encontrada para a Empresa: " + empresa + ".");
			}
			
		} else {
			
			// Recupera a Unidade Organizacional da Empresa
			unidadeOrganizacional.setEmpresa(empresa);
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.EMPRESA, empresa));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = 
				fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
				
				//[FS0011]-Verificar existência de mais de uma unidade correspondente à empresa
				if(colecaoUnidadeOrganizacional.size() > 1){
					throw new TarefaException("Existe mais de uma unidade correspondente à empresa da ordem de serviço seletiva.Erro grave.Entre em contato com o analista responsável pelo sistema.");
				}
				
				Iterator iColecaoUnidadeOrganizacional = colecaoUnidadeOrganizacional.iterator();
				unidadeOrganizacional = (UnidadeOrganizacional) iColecaoUnidadeOrganizacional.next();
			}else {
				throw new TarefaException("Unidade Organizacional não encontrada para a Empresa: " + empresa + ".");
			}
		}
		
		retorno = fachada.gerarOrdemServicoSeletiva(
				ordemServico, unidadeOrganizacional, imovel, Usuario.USUARIO_BATCH);
		
		return retorno;
	}
	*/
	private ImovelEmissaoOrdensSeletivasHelper gerarObjetoHelper() {
		ImovelEmissaoOrdensSeletivasHelper helper = new ImovelEmissaoOrdensSeletivasHelper();
		
		// PARÂMETROS
		helper.setTipoEmissao((String) getParametro("tipoEmissao"));
		helper.setTipoOrdem((String) getParametro("tipoOrdem"));
		helper.setFirma((String) getParametro("firma"));
		helper.setNomeFirma((String) getParametro("nomeFirma"));
		helper.setQuantidadeMaxima((String) getParametro("quantidadeMaxima"));
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
		helper.setDescricaoComando((String)getParametro("descricaoComando"));

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
		helper.setImovelCondominio((String) getParametro("imovelCondominio"));
		helper.setMediaImovel((String) getParametro("mediaImovel"));
		helper.setConsumoPorEconomia((String) getParametro("consumoPorEconomia"));
		helper.setConsumoPorEconomiaFinal((String) getParametro("consumoPorEconomiaFinal"));
		helper.setTipoMedicao((String) getParametro("tipoMedicao"));
		helper.setSituacaoLigacaoAgua((String[])getParametro("situacaoLigacaoAgua"));
		
		// HIDRÔMETRO
		helper.setCapacidadeHidrometro((String[]) getParametro("capacidade"));
		helper.setMarcaHidrometro((String) getParametro("marca"));
		helper.setAnormalidadeHidrometro((String[]) getParametro("anormalidadeHidrometro"));
		helper.setNumeroOcorrenciasAnormalidade((String) getParametro("numeroOcorrenciasConsecutivas"));
		//helper.setMesAnoInstalacaoInicialHidrometro((String) getParametro("mesAnoInstalacao"));
		helper.setMesAnoInstalacaoInicialHidrometro((String) getParametro("mesAnoInstalacaoInicial"));
		helper.setMesAnoInstalacaoFinalHidrometro((String) getParametro("mesAnoInstalacaoFinal"));
		
		
		return helper;
	}

}
