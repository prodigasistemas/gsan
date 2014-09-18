package gcom.micromedicao;

import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.IoUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.SessionBean;

import org.apache.commons.fileupload.FileItem;
import gcom.cadastro.imovel.Categoria;

/**
 * Controlador Micromedicao COSANPA
 * 
 * @author Raphael Rossiter
 * @date 24/04/2009
 */
public class ControladorMicromedicaoCOSANPASEJB extends ControladorMicromedicao implements SessionBean{
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA COSANPA
	//==============================================================================================================
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Rela��o(ROL) em TXT - COSANPA
	 * 
	 * @author Raphael Rossiter
	 * @date 27/08/2009
	 * 
	 * @param colecaoRota
	 * @param anoMesCorrente
	 * @param idGrupoFaturamentoRota
	 * @param sistemaParametro
	 * @param idFuncionalidadeIniciada
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection gerarDadosPorLeituraMicroColetor(Collection colecaoRota,
			Integer anoMesCorrente, Integer idGrupoFaturamentoRota,
			SistemaParametro sistemaParametro, int idFuncionalidadeIniciada)
			throws ControladorException {

		int idUnidadeIniciada = 0;
		
		try {
			
			Iterator colecaoRotaIterator = colecaoRota.iterator();
		
			StringBuilder arquivoTxt = new StringBuilder();
			
			Integer qtdImoveis = 0;
			
			MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = null;
			
			//PAGINA��O DO ARQUIVO
			Integer numeroPaginacao = 1;
			Localidade localidadeAnterior = null;
			Integer codigoSetorComercialAnterior = null;
			Rota rotaAnterior = null;
			int qtdImoveisLocalidadeSetorRota = 0;

			while (colecaoRotaIterator.hasNext()) {
			
				Rota rota = (Rota) colecaoRotaIterator.next();
				
				//-------------------------
				//
				// Registrar o in�cio do processamento da Unidade de
				// Processamento
				// do Batch
				//
				// -------------------------
				idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, 
				UnidadeProcessamento.ROTA, rota.getId());

				
				//CONTROLE DE PAGINA��O DA PESQUISA
				int numeroIndice = 0;
				int quantidadeRegistrosPesquisa = 1000;
				boolean flagTerminou = false;
				
				//MOVIMENTO ROTEIRO EMPRESA
				Collection<Object[]> objetosMovimentoRoteiroEmpresa = new ArrayList<Object[]>();

				while (!flagTerminou) {

					try {

						//PESQUISANDO MOVIMENTO ROTEIRO EMPRESA
						objetosMovimentoRoteiroEmpresa = repositorioMicromedicao
						.pesquisarImoveisParaLeituraPorColecaoRotaCOSANPA(rota, numeroIndice, anoMesCorrente);

					} catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}

					if (objetosMovimentoRoteiroEmpresa != null && !objetosMovimentoRoteiroEmpresa.isEmpty()) {

						for (Object[] dadosMovimentoRoteiroEmpresa : objetosMovimentoRoteiroEmpresa) {
							
							//QUANTIDADE TOTAL DE IM�VEIS
							qtdImoveis = qtdImoveis + objetosMovimentoRoteiroEmpresa.size();

							//CONTROLE DE PAGINA��O DA PESQUISA
							if (objetosMovimentoRoteiroEmpresa.size() < quantidadeRegistrosPesquisa) {
								flagTerminou = true;
							} 
							else {
								numeroIndice = numeroIndice + quantidadeRegistrosPesquisa;
							}

							boolean ligacaoAgua = false;
							boolean ligacaoPoco = false;

							// cria uma string builder para adicionar no arquivo
							StringBuilder arquivoTxtLinha = new StringBuilder();

							movimentoRoteiroEmpresa = (MovimentoRoteiroEmpresa) dadosMovimentoRoteiroEmpresa[0];
							Integer idQuadra = (Integer) dadosMovimentoRoteiroEmpresa[1];

							Quadra quadra = new Quadra();
							quadra.setId(idQuadra);

							Imovel imovel = new Imovel();
							imovel.setId(movimentoRoteiroEmpresa.getImovel().getId());
							imovel.setQuadra(quadra);
							movimentoRoteiroEmpresa.setImovel(imovel);

							//QUANTIDADE DE IMOVEIS POR LOCALIDADE, SETOR E ROTA
							qtdImoveisLocalidadeSetorRota++; 
							
							//PAGINA��O DO ARQUIVO
							if (localidadeAnterior == null){
								
								//CARREGANDO PELA PRIMEIRA VEZ AS INFORMA��ES DE LOCALIDADE E SETOR ANTERIORES
								localidadeAnterior = movimentoRoteiroEmpresa.getLocalidade();
								codigoSetorComercialAnterior = movimentoRoteiroEmpresa.getCodigoSetorComercial();
								rotaAnterior = rota;
								
							}
							else{
								
								//QUEBRA DE LOCALIDADE
								if (!localidadeAnterior.getId().equals(
									movimentoRoteiroEmpresa.getLocalidade().getId())){
										
									numeroPaginacao = 1;
									qtdImoveisLocalidadeSetorRota = 1;
								}
								//QUEBRA DE SETOR COMERCIAL
								else if (!codigoSetorComercialAnterior.equals(
										movimentoRoteiroEmpresa.getCodigoSetorComercial())){
										
									numeroPaginacao = 1;
									qtdImoveisLocalidadeSetorRota = 1;
								}
								/*
								 * QUEBRA DE ROTA
								 * QUEBRA POR QUANTIDADE DE IM�VEIS COM MESMA: LOCALIDADE, SETOR E ROTA 
								 */
								else if (!rotaAnterior.getId().equals(rota.getId()) ||
										qtdImoveisLocalidadeSetorRota > 12){
										
									numeroPaginacao++;
									qtdImoveisLocalidadeSetorRota = 1;
								}
							}
							
							//GERANDO O ARQUIVO TXT
							adicionarLinhaTxt(arquivoTxt, arquivoTxtLinha,
							null, null, null, null, null,
							movimentoRoteiroEmpresa, ligacaoAgua,
							ligacaoPoco, null, false, sistemaParametro,
							rota.getId(), null, anoMesCorrente, numeroPaginacao);
							
							/*
							 * PAGINA��O DO ARQUIVO
							 * GUARDANDO AS INFORMA��ES DA LOCALIDADE ANTERIOR E DO SETOR COMERCIAL ANTERIOR
							 */
							localidadeAnterior = movimentoRoteiroEmpresa.getLocalidade();
							codigoSetorComercialAnterior = movimentoRoteiroEmpresa.getCodigoSetorComercial();
							rotaAnterior = rota;
						}
						
					}
					else{
						
						flagTerminou = true;
					}
				}


				//	Encerra a unidade de Faturamento
				getControladorBatch().encerrarUnidadeProcessamentoBatch(
				null, idUnidadeIniciada, false);
				
			} //FIM LOOP DE ROTA
			
			/*
			 * Atualiza a data e a hora da realiza��o da atividade com a data e
			 * a hora correntes.
			 */
			try {

				repositorioMicromedicao.atualizarFaturamentoAtividadeCronograma(
				idGrupoFaturamentoRota, anoMesCorrente);
			} 
			catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
			
			//INSERINDO NA BASE O ARQUIVO_TEXTO_ROTEIRO_EMPRESA
			if (arquivoTxt != null && arquivoTxt.length() != 0) {
				
				//GERANDO O NOME DO ARQUIVO
				String anoCom2Digitos = "" + Util.obterAno(movimentoRoteiroEmpresa.getAnoMesMovimento());
				anoCom2Digitos = anoCom2Digitos.substring(2,4);

				String nomeArquivo = "cons" + anoCom2Digitos
				+ Util.adicionarZerosEsquedaNumero(2, 
				"" + Util.obterMes(movimentoRoteiroEmpresa.getAnoMesMovimento()))
				+ "."
				+ Util.adicionarZerosEsquedaNumero(3, 
				  movimentoRoteiroEmpresa.getRota().getFaturamentoGrupo().getId().toString());
				
				//GERANDO O ARQUIVO TXT
				this.inserirArquivoTextoRoteiroEmpresa(anoMesCorrente, movimentoRoteiroEmpresa,
				qtdImoveis, arquivoTxt, nomeArquivo);

			}
			
		} catch (Exception e) { 
			
			/*
			 * Este catch serve para interceptar qualquer exce��o que o processo batch
			 * venha a lan�ar e garantir que a unidade de processamento do batch 
			 * ser� atualizada com o erro ocorrido.
			 */
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
			idUnidadeIniciada, true);
		}
		
		return null;
	}
	
	/**
	 * [UC0083]- Gerar dados para Leitura Author: R�mulo Aur�lio Date:
	 * 08/07/2008
	 * 
	 * @param arquivoTxt
	 * @param arquivoTxtLinha
	 * @param arquivoTxtFiscalizacao
	 * @param arquivoHeaderFiscalizacao
	 * @param quantidadeRegistros
	 * @param quantidadeMovimentoRoteiroEmpresa
	 * @param quantidadeRegistrosFiscalizacao
	 * @param movimentoRoteiroEmpresa
	 * @param ligacaoAgua
	 * @param ligacaoPoco
	 * @param idFaturamentoGrupoOld
	 * @param headerArquivo
	 * @param sistemaParametro
	 * @param idRotaOld
	 * @param dataCalendar
	 * @param anoMesCorrente
	 */
	private void adicionarLinhaTxt(StringBuilder arquivoTxt,
			StringBuilder arquivoTxtLinha,
			StringBuilder arquivoTxtFiscalizacao,
			StringBuilder arquivoHeaderFiscalizacao,
			Integer quantidadeRegistros,
			Integer quantidadeMovimentoRoteiroEmpresa,
			Integer quantidadeRegistrosFiscalizacao,
			MovimentoRoteiroEmpresa movimentoRoteiroEmpresa,
			boolean ligacaoAgua, boolean ligacaoPoco,
			Integer idFaturamentoGrupoOld, boolean headerArquivo,
			SistemaParametro sistemaParametro, Integer idRotaOld,
			Calendar dataCalendar, Integer anoMesCorrente, Integer numeroPagina) throws ControladorException{

		//Matr�cula com D�gito Verificador (IMOV_ID da tabela MOVIMENTO_ROTEIRO_EMPRESA) 
		arquivoTxtLinha.append(Util.completaString(movimentoRoteiroEmpresa.getImovel()
		.getId().toString(), 15));
		
		//Setor (somente cidade planejada) (Num�rico)
		if (movimentoRoteiroEmpresa.getCodigoSetorComercial() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(
			3, movimentoRoteiroEmpresa.getCodigoSetorComercial().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3, ""));
		}
		
		//Rota (Num�rico)
		if (movimentoRoteiroEmpresa.getCodigoRota() != null){
			
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(4,
			movimentoRoteiroEmpresa.getCodigoRota().toString()));
		}
		else{
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
		}
		
		//Setor (somente cidade planejada)
		arquivoTxtLinha.append(Util.completaString("", 3));
		
		//Quadra (somente cidade planejada)
		arquivoTxtLinha.append(Util.completaString("", 3));
		
		//Sub-Quadra (somente cidade planejada)
		arquivoTxtLinha.append(Util.completaString("", 2));
		
		//Lote (somente cidade planejada)
		arquivoTxtLinha.append(Util.completaString("", 3));
		
		//Sub-Lote (somente cidade planejada)
		arquivoTxtLinha.append(Util.completaString("", 2));
		
		//Unidade (Seq��ncia - cidade n�o planejada) Lote (Num�rico)
		if (movimentoRoteiroEmpresa.getNumeroLoteImovel() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(
			6, movimentoRoteiroEmpresa.getNumeroLoteImovel().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(6, ""));
		}
		
		//C�digo da liga��o / Matr�cula (Alfanum�rico)
		arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa.getImovel()
		.getId().toString(), 15));
		
		//D�gito verificador da liga��o (Num�rico)
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(1, ""));
		
		//Hidr�metro / Medidor (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getNumeroHidrometro() != null){
			
			arquivoTxtLinha.append(Util.completaString(
			movimentoRoteiroEmpresa.getNumeroHidrometro().toString(), 12));
		}
		else{
			arquivoTxtLinha.append(Util.completaString("", 12));
		}

		//Situa��o do usu�rio (*) (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getDescricaoLigacaoAguaSituacao() != null){
			
			arquivoTxtLinha.append(Util.completaString(movimentoRoteiroEmpresa.getDescricaoLigacaoAguaSituacao(), 15));
		}
		else{
			arquivoTxtLinha.append(Util.completaString("", 15));
		}
		
		//Servi�o (*) (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getMedicaoTipo() != null){
			
			if (movimentoRoteiroEmpresa.getMedicaoTipo().getId().equals(MedicaoTipo.LIGACAO_AGUA)){
				
				arquivoTxtLinha.append(Util.completaString("SO AGUA", 15));
			}
			else{
				
				arquivoTxtLinha.append(Util.completaString("SO ESGOTO", 15));
			}
			
		}
		else{
			arquivoTxtLinha.append(Util.completaString("", 15));
		}
		
		//Nome do Consumidor (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getNomeCliente() != null){
			
			arquivoTxtLinha.append(Util.completaString(movimentoRoteiroEmpresa.getNomeCliente(), 40));
		}
		else{
			arquivoTxtLinha.append(Util.completaString("", 40));
		}
		
		//Logradouro (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getLogradouro() != null){
			
			arquivoTxtLinha.append(Util.completaString(movimentoRoteiroEmpresa.getLogradouro()
			.getDescricaoFormatada(), 40));
		}
		else{
			arquivoTxtLinha.append(Util.completaString("", 40));
		}
		
		//N�mero do im�vel (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getNumeroImovel() != null){
			
			arquivoTxtLinha.append(Util.completaString(movimentoRoteiroEmpresa.getNumeroImovel(), 6));
		}
		else{
			arquivoTxtLinha.append(Util.completaString("", 6));
		}
		
		//Complemento do endere�o do im�vel (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getComplementoEndereco() != null){
			
			arquivoTxtLinha.append(Util.completaString(movimentoRoteiroEmpresa.getComplementoEndereco(), 15));
		}
		else{
			arquivoTxtLinha.append(Util.completaString("", 15));
		}
		
		//Bairro do im�vel (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getNomeBairro() != null){
			
			arquivoTxtLinha.append(Util.completaString(movimentoRoteiroEmpresa.getNomeBairro(), 20));
		}
		else{
			arquivoTxtLinha.append(Util.completaString("", 20));
		}
		
		/*
		 * N�mero de economias Domiciliar (Num�rico)
		 * N�mero de economias Comercial (Num�rico)
		 * N�mero de economias Industrial (Num�rico)
		 * N�mero de economias P�blica (Num�rico)
		 */
		if (movimentoRoteiroEmpresa.getDescricaoAbreviadaCategoriaImovel() != null){
			
			if (movimentoRoteiroEmpresa.getDescricaoAbreviadaCategoriaImovel().equals(
				Categoria.RESIDENCIAL_DESCRICAO_ABREVIADA)){
				
				if (movimentoRoteiroEmpresa.getQuantidadeEconomias() != null){
					
					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, 
					movimentoRoteiroEmpresa.getQuantidadeEconomias().toString()));
				}
				else{
					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				}
				
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
			}
			else if (movimentoRoteiroEmpresa.getDescricaoAbreviadaCategoriaImovel().equals(
					Categoria.COMERCIAL_DESCRICAO_ABREVIADA)){
				
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				
				if (movimentoRoteiroEmpresa.getQuantidadeEconomias() != null){
					
					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, 
					movimentoRoteiroEmpresa.getQuantidadeEconomias().toString()));
				}
				else{
					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				}
				
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
			}
			else if (movimentoRoteiroEmpresa.getDescricaoAbreviadaCategoriaImovel().equals(
					Categoria.INDUSTRIAL_DESCRICAO_ABREVIADA)){
				
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				
				if (movimentoRoteiroEmpresa.getQuantidadeEconomias() != null){
					
					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, 
					movimentoRoteiroEmpresa.getQuantidadeEconomias().toString()));
				}
				else{
					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				}
				
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
			}
			else{
				
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				
				if (movimentoRoteiroEmpresa.getQuantidadeEconomias() != null){
					
					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, 
					movimentoRoteiroEmpresa.getQuantidadeEconomias().toString()));
				}
				else{
					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
				}
			}
		}
		else{
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
		}
		
		
		//N�mero de economias Outras (Num�rico)
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
		
		//Leitura Anterior (Num�rico)
		Integer numeroLeituraAnterior = 0;
		if (movimentoRoteiroEmpresa.getNumeroLeituraAnterior() != null){
			
			numeroLeituraAnterior = Integer.valueOf(movimentoRoteiroEmpresa.getNumeroLeituraAnterior());
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(8,
			movimentoRoteiroEmpresa.getNumeroLeituraAnterior().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(8, ""));
		}
		
		//Ocorr�ncia principal Anterior (Num�rico)
		if (movimentoRoteiroEmpresa.getCodigoAnormalidadeAnterior() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4,
			movimentoRoteiroEmpresa.getCodigoAnormalidadeAnterior().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
		}
		
		//Consumo m�nimo (m3) (Num�rico)
		if (movimentoRoteiroEmpresa.getNumeroFaixaLeituraEsperadaInicial() != null){
			
			Integer consumoMinimo = movimentoRoteiroEmpresa.getNumeroFaixaLeituraEsperadaInicial().intValue()
			- numeroLeituraAnterior;
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(5,
			consumoMinimo.toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(5, ""));
		}
		
		//Consumo m�ximo (m3) (Num�rico)
		if (movimentoRoteiroEmpresa.getNumeroFaixaLeituraEsperadaFinal() != null){
			
			Integer consumoMaximo = movimentoRoteiroEmpresa.getNumeroFaixaLeituraEsperadaFinal().intValue()
			- numeroLeituraAnterior;
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(5,
			consumoMaximo.toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(5, ""));
		}
		
		//Consumo m�dio (m3) (Num�rico)
		if (movimentoRoteiroEmpresa.getNumeroConsumoMedio() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(5,
			movimentoRoteiroEmpresa.getNumeroConsumoMedio().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(5, ""));
		}
		
		//Observa��es (Alfanum�rico)
		arquivoTxtLinha.append(Util.completaString("", 60));
		
		//N�mero de moradores (Num�rico)
		if (movimentoRoteiroEmpresa.getNumeroMoradores() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(2,
			movimentoRoteiroEmpresa.getNumeroMoradores().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(2, ""));
		}
		
		//Ano/M�s de faturamento (AAAAMM)
		arquivoTxtLinha.append(movimentoRoteiroEmpresa.getAnoMesMovimento());
		
		//Telefone 01
		arquivoTxtLinha.append(Util.completaString("", 16));
		
		//Telefone 02
		arquivoTxtLinha.append(Util.completaString("", 16));
		
		//Categoria 01 (1 - Domiciliar, 2 - Comercial, 3 - Industrial, 4 - P�blica, 5 - Outras)
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(1, ""));
		
		//Subcategoria da categoria 01
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3, ""));
		
		//Categoria 02 (1 - Domiciliar, 2 - Comercial, 3 - Industrial, 4 - P�blica, 5 - Outras)
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(1, ""));
		
		//Subcategoria da categoria 02
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3, ""));
		
		//Categoria 03 (1 - Domiciliar, 2 - Comercial, 3 - Industrial, 4 - P�blica, 5 - Outras)
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(1, ""));
		
		//Subcategoria da categoria 03
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3, ""));
		
		//Categoria 04 (1 - Domiciliar, 2 - Comercial, 3 - Industrial, 4 - P�blica, 5 - Outras)
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(1, ""));
		
		//Subcategoria da categoria 04
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3, ""));
		
		//Categoria 05 (1 - Domiciliar, 2 - Comercial, 3 - Industrial, 4 - P�blica, 5 - Outras)
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(1, ""));
		
		//Subcategoria da categoria 05
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3, ""));
		
		
		/*
		 * Campo livre (ser� enviado e retornado)
		 * 
		 * Identifica��o do sublote (QQQQLLLLSSS)  (Alfanum�rico)
		 * Concatenar Quadra + Lote + Sublote 
		 */
		if (movimentoRoteiroEmpresa.getNumeroQuadra() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(
			4, movimentoRoteiroEmpresa.getNumeroQuadra().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
		}
		
		if (movimentoRoteiroEmpresa.getNumeroLoteImovel() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(
			4, movimentoRoteiroEmpresa.getNumeroLoteImovel().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
		}
		
		if (movimentoRoteiroEmpresa.getNumeroSubloteImovel() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(
			3, movimentoRoteiroEmpresa.getNumeroSubloteImovel().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3, ""));
		}
		
		arquivoTxtLinha.append(Util.completaString("", 19));
		
		/*
		 * Filler (Dever� ser preenchido com espa�os em branco, ser� utilizado quando for(em) 
		 * inclu�do(s) novo(s) campo(s) no READER�. O tamanho deste campo dever� ser diminu�do do 
		 * tamanho total do(s) campo(s) inclu�do(s), ent�o o tamanho da linha (registro) permanecer� o mesmo) 
		 * (Alfanum�rico)
		 */
		arquivoTxtLinha.append(Util.completaString("", 212));
		
		//Fixo A01 (Alfanum�rico)
		arquivoTxtLinha.append("A01");
		
		//M�s e Ano de refer�ncia (MM/AAAA) (Alfanum�rico)
		arquivoTxtLinha.append(Util.formatarAnoMesParaMesAno(movimentoRoteiroEmpresa.getAnoMesMovimento()));
		
		//Grupo (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getFaturamentoGrupo() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4,
			movimentoRoteiroEmpresa.getFaturamentoGrupo().getId().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(4, ""));
		}
		
		//P�gina (Num�rico)
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(5, numeroPagina.toString()));
		
		//Localidade (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getLocalidade() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(10,
			movimentoRoteiroEmpresa.getLocalidade().getId().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(10, ""));
		}
		
		//Distrito (Alfanum�rico)
		arquivoTxtLinha.append(Util.completaString("", 4));
		
		//Face (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getCodigoQuadraFace() != null){
			
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3,
			movimentoRoteiroEmpresa.getCodigoQuadraFace().toString()));
		}
		else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3, ""));
		}

		
		// Registro Detalhe
		arquivoTxt.append(arquivoTxtLinha);

		arquivoTxt.append(System.getProperty("line.separator"));

	}
	
	
	/**
	 * [UC0082] Registrar Leituras e Anormalidades 
	 *
	 * @author Raphael Rossiter
	 * @date 03/09/2009
	 *
	 * @param idFaturamentoGrupo
	 * @param fileItem
	 * @return Collection<MedicaoHistorico>
	 * @throws ControladorException
	 */
	public Collection<MedicaoHistorico> processarArquivoTextoParaRegistrarLeiturasAnormalidades(
			Integer idFaturamentoGrupo, FileItem fileItem) throws ControladorException{
		
		Collection<MedicaoHistorico> colecaoMedicaoHistorico = new ArrayList();
		
		// ABRINDO O ARQUIVO
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(fileItem.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.importacao.nao_concluida");
		}

		// PREPARANDO OBJETO PARA LER O ARQUIVO
		BufferedReader buffer = new BufferedReader(reader);
		
		//VARI�VEl PARA LER O ARQUIVO LINHA A LINHA
		boolean eof = false;
		
		//CARREGANDO OS DADOS DO GRUPO DE FATURAMENTO ONDE SER�O REGISTRADAS AS LEITURAS
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
		FiltroFaturamentoGrupo.ID, idFaturamentoGrupo));
		
		Collection colecaoFaturamentoGrupo = this.getControladorUtil()
		.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		FaturamentoGrupo faturamentoGrupo = null;
		
		if (colecaoFaturamentoGrupo != null && !colecaoFaturamentoGrupo.isEmpty()) {
			
			faturamentoGrupo = (FaturamentoGrupo) Util
			.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
		} 
		else {
			
			throw new ControladorException("atencao.entidade.inexistente",null,
			"Faturamento Grupo");
		}
		
		while (!eof) {
			
			// PEGANDO A LINHA DO ARQUIVO
			String linhaLida;
			try {
				linhaLida = buffer.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				throw new ControladorException("erro.importacao.nao_concluida");
			}

			if (linhaLida == null) {
				
				// �LTIMA LINHA DO ARQUIVO
				eof = true;
			} 
			else if (linhaLida.length() == 420){

				// VARI�VEIS QUE SER�O UTILIZADAS PARA GERA��O DA MEDI��O HIST�RICO
				MedicaoHistorico medicaoHistorico = new MedicaoHistorico();

				MedicaoTipo medicaoTipo = new MedicaoTipo();
				Funcionario funcionario = new Funcionario();
				LeituraAnormalidade leituraAnormalidade = new LeituraAnormalidade();
				Imovel imovel = new Imovel();
				SetorComercial setorComercial = new SetorComercial();
				Quadra quadra = new Quadra();
				
				//M�S E ANO DA LEITURA
				String anoMesLeitura = linhaLida.substring(362, 369);
				
				//VALIDANDO O M�S E ANO DA LEITURA
				boolean anoMesInvalido = Util.validarMesAno(anoMesLeitura);
				
				if (!anoMesInvalido) {
					
					throw new ControladorException("atencao.anomes.faturamento.invalido");
				}
				
				if (!faturamentoGrupo.getAnoMesReferencia().equals(
					Util.formatarMesAnoComBarraParaAnoMes(anoMesLeitura))) {
					
					throw new ControladorException(
					"atencao.anomes.faturamento.nao.corresponde");
				}
				
				//ID DO GRUPO DE FATURAMENTO INFORMADO NO ARQUIVO
				String idFaturamentoHeaderString = linhaLida.substring(369, 373).trim();
				Integer idFaturamentoHeader = null; 
				
				if (!idFaturamentoHeaderString.equals("")) {
					
					idFaturamentoHeader = new Integer(linhaLida.substring(369, 373));
				}
				
				if (idFaturamentoHeader != null && 
					!idFaturamentoGrupo.equals(idFaturamentoHeader)) {
					
					throw new ControladorException("atencao.nao.grupo.faturamento");
				}

				//INICIANDO O PROCESSO DE LEITURA DO ARQUIVO
				
				// ID DO FUNCIONARIO
				String idFuncionario = linhaLida.substring(410, 420).trim();
							
				if (!idFuncionario.equals("")) {
								
					funcionario.setId(new Integer(idFuncionario));
				} 
				else {
								
					funcionario.setId(null);
				}

				medicaoHistorico.setFuncionario(funcionario);

				//DATA DE LEITURA
				String dataColeta = linhaLida.substring(24, 26) + 
				linhaLida.substring(27, 29) + linhaLida.substring(30, 34);
				
				medicaoHistorico.setDataLeituraParaRegistrar(dataColeta);

				//IM�VEL
				String idImovel = linhaLida.substring(0, 15).trim();
				
				System.out.println(idImovel);
							
				if (!idImovel.equals("")) {
								
					FiltroImovel filtroImovel = new FiltroImovel();
					
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
					
					filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, Integer.valueOf(idImovel)));
					
					Collection colecaoImovel = this.getControladorUtil().pesquisar(filtroImovel, 
					Imovel.class.getName());
					
					if (colecaoImovel != null && !colecaoImovel.isEmpty()){
						imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
					}
					else{
						continue;
					}
				} 
				else {
								
					imovel.setId(null);
				}

				//LOCALIDADE JA EST� SENDO CARREGADA NO IM�VEL
								
				//C�DIGO DO SETOR COMERCIAL
				String codigoSetorComercial = linhaLida.substring(392, 395).trim();
							
				if (!codigoSetorComercial.equals("")) {
								
					setorComercial.setCodigo(new Integer(linhaLida.substring(392, 395)));
				} 
				else {
								
					setorComercial.setCodigo(0);
				}
							
				imovel.setSetorComercial(setorComercial);

				//N�MERO DA QUADRA
				String numeroQuadra = linhaLida.substring(62, 66).trim();
							
				if (!numeroQuadra.equals("")) {
								
					quadra.setNumeroQuadra(new Integer(linhaLida.substring(62, 66)));
				} 
				else {
								
					quadra.setNumeroQuadra(0);
				}
							
				imovel.setQuadra(quadra);

				//LOTE
				String lote = linhaLida.substring(66, 70).trim();
							
				if (!lote.equals("")) {
								
					imovel.setLote(new Short(lote));
				} 
				else {
								
					imovel.setLote(new Short("0"));
				}
							
				//SUBLOTE
				String subLote = linhaLida.substring(70, 73).trim();
							
				if (!subLote.equals("")) {
								
					imovel.setSubLote(new Short(subLote));
				} 
				else {
								
					imovel.setSubLote(new Short("0"));
				}

				medicaoHistorico.setImovel(imovel);
							
				// TIPO DE MEDI��O
				String idMedicaoTipo = "1";
				medicaoTipo.setId(new Integer(idMedicaoTipo));
							
				medicaoHistorico.setMedicaoTipo(medicaoTipo);

				// LEITURA DO HIDR�METRO
				String leituraHidrometro = null;

				leituraHidrometro = linhaLida.substring(16, 24).trim();
							
				if (!leituraHidrometro.equals("")) {
								
					try {
									
						medicaoHistorico.setLeituraAtualInformada(new Integer(
						leituraHidrometro));
					} 
					catch (NumberFormatException ex) {
									
						medicaoHistorico.setLeituraAtualInformada(0);
					}
				} 
				else {
								
					medicaoHistorico.setLeituraAtualInformada(null);
				}

				//ANORMALIDADE
				String idLeituraAnormalidade = linhaLida.substring(42, 46).trim();

				if (!idLeituraAnormalidade.equals("")) {
								
					try {
									
						leituraAnormalidade.setId(new Integer(idLeituraAnormalidade));
					} 
					catch (NumberFormatException ex) {
									
						leituraAnormalidade.setId(0);
					}
				} 
				else {
								
					leituraAnormalidade.setId(null);
				}
							
				medicaoHistorico
				.setLeituraAnormalidadeInformada(leituraAnormalidade);

				// INDICADOR DE CONFIRMA��O DA LEITURA
				if (linhaLida.substring(15, 16).equals("S")){
					
					medicaoHistorico.setIndicadorConfirmacaoLeitura("1");
				}
				else{
					
					medicaoHistorico.setIndicadorConfirmacaoLeitura("2");
				}
				
				//INSERINDO A MEDI��O HISTORICO QUE FOI GERADA A PARTIR DO ARQUIVO TXT
				colecaoMedicaoHistorico.add(medicaoHistorico);
			}
			else{
				
				//�LTIMA LINHA DO ARQUIVO
				eof = true;
			}
		}
		
		// FECHANDO O ARQUIVO
		try {
			
			buffer.close();
			reader.close();
			fileItem.getInputStream().close();
		} 
		catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.importacao.nao_concluida");
		}
		
		
		
		return colecaoMedicaoHistorico;
	}
	
	
	/**
	 * [UC0083] - Gerar Dados para Leitura Empresa
	 * 
	 * @author R�mulo Aur�lio
	 * @date 09/07/2008
	 * 
	 * @param anoMesFaturamento
	 * @param movimentoRoteiroEmpresa
	 * @param qtdImoveis
	 * @param arquivoTexto
	 * param nomeArquivo
	 * 
	 */
	public void inserirArquivoTextoRoteiroEmpresa(Integer anoMesFaturamento, 
			MovimentoRoteiroEmpresa movimentoRoteiroEmpresa, Integer qtdImoveis,
			StringBuilder arquivoTexto, String nomeArquivo)
			throws ControladorException {
		
		boolean gerarArquivoTexto = true;
		
		ArquivoTextoRoteiroEmpresa arquivo;
		
		try {
			
			arquivo = this.repositorioMicromedicao
				.pesquisaArquivoTextoParaLeiturista(
						anoMesFaturamento,movimentoRoteiroEmpresa.getFaturamentoGrupo().getId());
			
		} catch (ErroRepositorioException e1) {
			throw new ControladorException("erro.sistema", e1);
		}
		
		/*
		 * 
		 * Altera��o feita para corrigir o problema de n�o gerar
		 * rotas para leitura normal, somente para leitura e impress�o simultanea
		 */
		
		if(arquivo!=null && arquivo.getEmpresa().getId().equals(movimentoRoteiroEmpresa.getEmpresa().getId())){
			if(arquivo.getSituacaoTransmissaoLeitura().getId()
					.compareTo(SituacaoTransmissaoLeitura.LIBERADO)==0){
				
				this.getControladorUtil().remover(arquivo);
				
			}else{
				
				gerarArquivoTexto = false;
				
			}
		}
		
		if(gerarArquivoTexto){
		
		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = new ArquivoTextoRoteiroEmpresa();

		// ANO_MES_REFERENCIA
		arquivoTextoRoteiroEmpresa.setAnoMesReferencia(anoMesFaturamento);

		// FATURAMENTO_GRUPO
		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
		faturamentoGrupo.setId(movimentoRoteiroEmpresa.getFaturamentoGrupo().getId());
		
		arquivoTextoRoteiroEmpresa.setFaturamentoGrupo(faturamentoGrupo);

		// EMPRESA
		arquivoTextoRoteiroEmpresa.setEmpresa(movimentoRoteiroEmpresa
				.getEmpresa());

		// LOCALIDADE
		Localidade localidade = new Localidade();
		localidade.setId(movimentoRoteiroEmpresa.getLocalidade().getId());
		
		arquivoTextoRoteiroEmpresa.setLocalidade(localidade);

		// C�DIGO DO SETOR COMERCIAL
		arquivoTextoRoteiroEmpresa
				.setCodigoSetorComercial1(movimentoRoteiroEmpresa
						.getCodigoSetorComercial());

		// MENOR E MAIOR N�MERO DA QUADRA PARA ROTA
		Object[] intervalorNumeroQuadra = null;
		try {

			intervalorNumeroQuadra = this.repositorioFaturamento
					.pesquisarIntervaloNumeroQuadraPorRota(movimentoRoteiroEmpresa
							.getRota().getId());

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (intervalorNumeroQuadra != null && intervalorNumeroQuadra[0] != null && intervalorNumeroQuadra[1] != null){
			
			arquivoTextoRoteiroEmpresa
			.setNumeroQuadraInicial1((Integer) intervalorNumeroQuadra[0]);
	
			arquivoTextoRoteiroEmpresa
			.setNumeroQuadraFinal1((Integer) intervalorNumeroQuadra[1]);
		}
		else{
			
			// PARA OS CASOS DE ROTA ALTERNATIVA
			arquivoTextoRoteiroEmpresa.setNumeroQuadraInicial1(0);
	
			arquivoTextoRoteiroEmpresa.setNumeroQuadraFinal1(0);
		}
		
		// QUANTIDADE DE IM�VEIS
		arquivoTextoRoteiroEmpresa.setQuantidadeImovel(qtdImoveis);
		
		//NOME DO ARQUIVO
		arquivoTextoRoteiroEmpresa.setNomeArquivo(nomeArquivo);

		
		// ARQUIVO TEMPOR�RIO GERADO PARA ROTA
		byte[] arquivoTextoByte = null;

		try {

			arquivoTextoByte = IoUtil.transformarObjetoParaBytes(arquivoTexto);

		} catch (IOException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		arquivoTextoRoteiroEmpresa.setArquivoTexto(arquivoTextoByte);

		// SITUACAO_TRANSMISSAO_LEITURA
		SituacaoTransmissaoLeitura situacaoTransmissaoLeitura = new SituacaoTransmissaoLeitura();
		situacaoTransmissaoLeitura.setId(SituacaoTransmissaoLeitura.LIBERADO);

		arquivoTextoRoteiroEmpresa
				.setSituacaoTransmissaoLeitura(situacaoTransmissaoLeitura);
		
		//SITUACAO_TRANSMISSAO_LEITURA
		ServicoTipoCelular servicoTipoCelular = new ServicoTipoCelular();
		servicoTipoCelular.setId(ServicoTipoCelular.LEITURA);
		arquivoTextoRoteiroEmpresa
		.setServicoTipoCelular(servicoTipoCelular);


		// ULTIMA ALTERACAO
		arquivoTextoRoteiroEmpresa.setUltimaAlteracao(new Date());

		// INSERINDO NA BASE
		this.getControladorUtil().inserir(arquivoTextoRoteiroEmpresa);
		
		}

	}
	
	//valida anoMes para caso de uso anlise excecoes leituras
	public boolean validaDataFaturamentoIncompativel(String anoMesReferencia,
			String anoMesAtual) {
		boolean retorno = true;

		String anoMesReferenciaMaisUmMes = ""
				+ Util.somarData(new Integer(anoMesReferencia));
		String anoMesReferenciaMenosUmMes = ""
				+ Util.subtrairMesDoAnoMes(new Integer(anoMesReferencia), 1);

		if (!((Util.compararAnoMesReferencia(new Integer(anoMesReferencia),
				new Integer(anoMesAtual), "="))
				|| (Util.compararAnoMesReferencia(new Integer(
						anoMesReferenciaMaisUmMes), new Integer(anoMesAtual),
						"=")) || (Util.compararAnoMesReferencia(new Integer(
				anoMesReferenciaMenosUmMes), new Integer(anoMesAtual), "=")))) {
			retorno = false;
		}

		return retorno;
	}
	
	//valida anoMes para caso de uso anlise excecoes leituras
	public boolean validaDataFaturamentoIncompativelInferior(
			String anoMesReferencia, String anoMesAnterior) {
		boolean retorno = true;

		String anoMesReferenciaMenosUmMes = ""
				+ Util.subtrairMesDoAnoMes(new Integer(anoMesReferencia), 2);

		// Comparando a data anterior faturada no form com o ano
		// m�s
		// refer�ncia e com o ano m�s anterior
		if (!((Util.compararAnoMesReferencia(new Integer(anoMesReferencia),
				new Integer(anoMesAnterior), "="))
				|| (Util.compararAnoMesReferencia(new Integer(
						anoMesReferenciaMenosUmMes),
						new Integer(anoMesAnterior), "=")) || (Util
				.compararAnoMesReferencia(new Integer(anoMesReferencia),
						new Integer(Util.somaMesAnoMesReferencia(new Integer(
								anoMesAnterior), 1)), "=")))) {

			retorno = false;
		}
		return retorno;
	}
}
