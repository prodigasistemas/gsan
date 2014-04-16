package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de imóvel manter
 * 
 * @author Rafael Corrêa
 * @created 11 de Julho de 2005
 */
public class RelatorioManterImovel extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioManterImovel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEL_MANTER);
	}
	
	@Deprecated
	public RelatorioManterImovel() {
		super(null, "");
	}

	/**
	 * <<Descrição do método>>
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

		FiltroClienteImovel filtroClienteImovelPesquisa = (FiltroClienteImovel) getParametro("filtroClienteImovel");
		Imovel imovelParametros = (Imovel) getParametro("imovelParametros");
		Cliente cliente = (Cliente) getParametro("cliente");
		Municipio municipio = (Municipio) getParametro("municipio");
		Bairro bairro = (Bairro) getParametro("bairro");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterImovelBean relatorioBean = null;

		filtroClienteImovelPesquisa.setConsultaSemLimites(true);

		/*
		 * TODO - COSANPA
		 *  
		 * Adição dos filtros para os atributos: perimetroInicial e perimetroFinal
		 * Correção para o método getEnderecoFormatado()
		 */		
		filtroClienteImovelPesquisa
			.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
		filtroClienteImovelPesquisa
			.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
		filtroClienteImovelPesquisa
			.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
		filtroClienteImovelPesquisa
			.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
		
		/*filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade.gerenciaRegional");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade.unidadeNegocio");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPrincipal");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.pavimentoCalcada");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.pavimentoRua");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.despejo");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.pocoTipo");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua.ligacaoAguaDiametro");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua.ligacaoAguaMaterial");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgoto.ligacaoEsgotoDiametro");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgoto.ligacaoEsgotoMaterial");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro.hidrometroCapacidade");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro.hidrometroMarca");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro.hidrometroDiametro");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro.hidrometroTipo");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometroLocalInstalacao");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometroProtecao");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico.hidrometro.hidrometroCapacidade");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico.hidrometro.hidrometroMarca");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico.hidrometro.hidrometroDiametro");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico.hidrometro.hidrometroTipo");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico.hidrometroLocalInstalacao");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico.hidrometroProtecao");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.municipio");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.reservatorioVolumeFaixaSuperior");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.reservatorioVolumeFaixaInferior");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.piscinaVolumeFaixa");
		filtroClienteImovelPesquisa
				.adicionarCaminhoParaCarregamentoEntidade("imovel.areaConstruidaFaixa");*/

		Collection imoveis = fachada
				.pesquisarClienteImovelRelatorio(filtroClienteImovelPesquisa);

		// se a coleção de parâmetros da analise não for vazia
		if (imoveis != null && !imoveis.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator imovelIterator = imoveis.iterator();

			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

			while (imovelIterator.hasNext()) {

				Imovel imovelNovo = (Imovel) imovelIterator.next();

				ClienteImovel clienteImovel = null;
				ClienteImovel clienteUsuario = null;
				ClienteImovel clienteResponsavel = null;
				ImovelSubcategoria imovelSubcategoria = null;
				String imovelSubcategoriaImprimir = "";
				String mediaConsumo = "";
				short quantidadeEconomias = 0;

				if (imovelNovo.getId() != null
						&& !imovelNovo.getId().equals("")) {
					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

					filtroClienteImovel
							.adicionarParametro(new ParametroSimples(
									FiltroClienteImovel.IMOVEL_ID, imovelNovo
											.getId()));
					filtroClienteImovel
							.adicionarCaminhoParaCarregamentoEntidade("cliente");
					filtroClienteImovel
							.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

					Collection clientesImoveis = fachada.pesquisar(
							filtroClienteImovel, ClienteImovel.class.getName());

					if (clientesImoveis != null && !clientesImoveis.isEmpty()) {
						// O Cliente Imovel foi encontrado
						Iterator clienteImovelIterator = clientesImoveis
								.iterator();

						while (clienteImovelIterator.hasNext()) {
							clienteImovel = (ClienteImovel) clienteImovelIterator
									.next();

							// Trazer o cliente usuário do imóvel
							if (clienteImovel.getClienteRelacaoTipo().getId()
									.toString().equals(
											ClienteRelacaoTipo.USUARIO
													.toString())) {
								clienteUsuario = clienteImovel;
							}

							// Trazer o cliente responsável do imóvel
							if (clienteImovel.getClienteRelacaoTipo().getId()
									.toString().equals(
											ClienteRelacaoTipo.RESPONSAVEL
													.toString())) {
								clienteResponsavel = clienteImovel;
							}
						}

					}

					FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();

					filtroImovelSubCategoria
							.adicionarParametro(new ParametroSimples(
									FiltroImovelSubCategoria.IMOVEL_ID,
									imovelNovo.getId()));

					filtroImovelSubCategoria
							.adicionarCaminhoParaCarregamentoEntidade("comp_id.subcategoria");

					Collection imoveisSubCategorias = fachada.pesquisar(
							filtroImovelSubCategoria, ImovelSubcategoria.class
									.getName());

					if (imoveisSubCategorias != null
							&& !imoveisSubCategorias.isEmpty()) {
						// O Cliente Imovel foi encontrado
						Iterator imovelSubCategoriaIterator = imoveisSubCategorias
								.iterator();

						while (imovelSubCategoriaIterator.hasNext()) {

							// Concatenar as categorias com suas respectivas
							// quantidades de economias
							imovelSubcategoria = (ImovelSubcategoria) imovelSubCategoriaIterator
									.next();
							quantidadeEconomias = imovelSubcategoria
									.getQuantidadeEconomias();

							if (imovelSubcategoriaImprimir != null
									&& !imovelSubcategoriaImprimir.equals("")) {

								imovelSubcategoriaImprimir = imovelSubcategoriaImprimir
										+ (imovelSubcategoria.getComp_id()
												.getSubcategoria()
												.getDescricao() == null ? ""
												: "; "
														+ imovelSubcategoria
																.getComp_id()
																.getSubcategoria()
																.getDescricao()
														+ "/" + ""
														+ quantidadeEconomias);
							} else {
								imovelSubcategoriaImprimir = imovelSubcategoriaImprimir
										+ (imovelSubcategoria.getComp_id()
												.getSubcategoria()
												.getDescricao() == null ? ""
												: imovelSubcategoria
														.getComp_id()
														.getSubcategoria()
														.getDescricao()
														+ "/"
														+ ""
														+ quantidadeEconomias);
							}
						}
					}

					FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
					filtroConsumoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroConsumoHistorico.IMOVEL_ID,
									imovelNovo.getId()));

					SistemaParametro sistemaParametro = fachada
							.pesquisarParametrosDoSistema();

					if (sistemaParametro != null) {

						filtroConsumoHistorico
								.adicionarParametro(new ParametroSimples(
										FiltroConsumoHistorico.ANO_MES_FATURAMENTO,
										Util.subtrairData(sistemaParametro
												.getAnoMesFaturamento())));

					}

					Collection consumosHistorico = fachada.pesquisar(
							filtroConsumoHistorico, ConsumoHistorico.class
									.getName());

					if (consumosHistorico != null
							&& !consumosHistorico.isEmpty()) {
						Iterator consumoHistoricoIterator = consumosHistorico
								.iterator();

						ConsumoHistorico consumoHistorico = (ConsumoHistorico) consumoHistoricoIterator
								.next();

						mediaConsumo = "" + consumoHistorico.getConsumoMedio();
					}

				}
				String indicadorJardim = "";
				if ( imovelNovo.getIndicadorJardim() != null && !imovelNovo.getIndicadorJardim().equals("") ) {
					if ( imovelNovo.getIndicadorJardim().equals(
							Imovel.INDICADOR_JARDIM_SIM)) {
						indicadorJardim = "SIM";
					} else {
						indicadorJardim = "NÃO";
					}
					
				}
				// Início da Construção do objeto RelatorioManterImovelBean
				// para ser colocado no relatório
				relatorioBean = new RelatorioManterImovelBean(

						// Código da Gerência Regional
						imovelNovo.getLocalidade() == null ? "" : ""
								+ imovelNovo.getLocalidade()
										.getGerenciaRegional().getId(),

						// Descrição da Gerência Regional
						imovelNovo.getLocalidade() == null ? "" : imovelNovo
								.getLocalidade().getGerenciaRegional()
								.getNomeAbreviado(),

						// Código da Unidade de Negócio
						imovelNovo.getLocalidade() == null ? ""
								: imovelNovo.getLocalidade()
										.getUnidadeNegocio() == null ? "" : ""
										+ imovelNovo.getLocalidade()
												.getUnidadeNegocio().getId(),

						// Descrição da Unidade de Negócio
						imovelNovo.getLocalidade() == null ? ""
								: imovelNovo.getLocalidade()
										.getUnidadeNegocio() == null ? ""
										: imovelNovo.getLocalidade()
												.getUnidadeNegocio().getNome(),

						// Código da Localidade
						imovelNovo.getLocalidade() == null ? "" : ""
								+ imovelNovo.getLocalidade().getId(),

						// Descrição da Localidade
						imovelNovo.getLocalidade() == null ? "" : imovelNovo
								.getLocalidade().getDescricao(),

						// Código do Setor Comercial
						imovelNovo.getSetorComercial() == null ? "" : ""
								+ imovelNovo.getSetorComercial().getCodigo(),

						// Descrição do Setor Comercial
						imovelNovo.getSetorComercial() == null ? ""
								: imovelNovo.getSetorComercial().getDescricao(),

						// Inscrição do Imóvel composto do código da
						// localidade, código do setor comercial, número da
						// quadra, lote e sublote
						imovelNovo.getInscricaoFormatada(),

						// Matrícula do Imóvel
						imovelNovo.getId().toString(),

						// Código do Cliente Usuário
						clienteUsuario == null ? "" : ""
								+ clienteUsuario.getCliente().getId(),

						// Nome do Cliente Usuário
						clienteUsuario == null ? "" : clienteUsuario
								.getCliente().getNome(),

						// Código do Cliente Responsável
						clienteResponsavel == null ? "" : ""
								+ clienteResponsavel.getCliente().getId(),

						// Nome do Cliente Responsável
						clienteResponsavel == null ? "" : clienteResponsavel
								.getCliente().getNome(),

						// Endereço
						imovelNovo.getEnderecoFormatado(),

						// Indicador Imóvel Condomínio
						imovelNovo.getIndicadorImovelCondominio() == null ? ""
								: imovelNovo.getIndicadorImovelCondominio()
										.equals(Imovel.IMOVEL_CONDOMINIO) ? "SIM"
										: "NÃO",

						// Matrícula Imóvel Condomínio
						imovelNovo.getImovelCondominio() == null ? ""
								: imovelNovo.getImovelCondominio().getId() == null ? ""
										: ""
												+ imovelNovo
														.getImovelCondominio()
														.getId(),

						// Matrícula Imóvel Principal
						imovelNovo.getImovelPrincipal() == null ? "" : ""
								+ imovelNovo.getImovelPrincipal().getId(),

						// Perfil Imóvel
						imovelNovo.getImovelPerfil() == null ? "" : imovelNovo
								.getImovelPerfil().getDescricao(),

						// Subcategorias / Quantidade de Economias
						imovelSubcategoriaImprimir,

						// Situação da Ligação de Água
						imovelNovo.getLigacaoAguaSituacao() == null ? ""
								: imovelNovo.getLigacaoAguaSituacao()
										.getDescricao(),

						// Situação da Ligação de Esgoto
						imovelNovo.getLigacaoEsgotoSituacao() == null ? ""
								: imovelNovo.getLigacaoEsgotoSituacao()
										.getDescricao(),

						// Tipo Pavimento Calçada
						imovelNovo.getPavimentoCalcada() == null ? ""
								: imovelNovo.getPavimentoCalcada()
										.getDescricao(),

						// Tipo Pavimento Rua
						imovelNovo.getPavimentoRua() == null ? "" : imovelNovo
								.getPavimentoRua().getDescricao(),

						// Tipo de Despejo
						imovelNovo.getDespejo() == null ? "" : imovelNovo
								.getDespejo().getDescricao(),

						// Volume do Reservatório Superior
						imovelNovo.getVolumeReservatorioSuperior() == null ? imovelNovo
								.getReservatorioVolumeFaixaSuperior() == null ? ""
								: imovelNovo
										.getReservatorioVolumeFaixaSuperior()
										.getFaixaCompleta()
								: Util.formatarMoedaReal(imovelNovo
										.getVolumeReservatorioSuperior()),

						// Volume do Reservatório Inferior
						imovelNovo.getVolumeReservatorioInferior() == null ? imovelNovo
								.getReservatorioVolumeFaixaInferior() == null ? ""
								: imovelNovo
										.getReservatorioVolumeFaixaInferior()
										.getFaixaCompleta()
								: Util.formatarMoedaReal(imovelNovo
										.getVolumeReservatorioInferior()),

						// Volume da Piscina
						imovelNovo.getVolumePiscina() == null ? imovelNovo
								.getPiscinaVolumeFaixa() == null ? ""
								: imovelNovo.getPiscinaVolumeFaixa()
										.getFaixaCompleta() : Util
								.formatarMoedaReal(imovelNovo
										.getVolumePiscina()),

						// Média de Consumo do Imóvel
						mediaConsumo,

						// Área Construída
						imovelNovo.getAreaConstruida() == null ? imovelNovo
								.getAreaConstruidaFaixa() == null ? ""
								: imovelNovo.getAreaConstruidaFaixa()
										.getFaixaCompleta() : Util
								.formatarMoedaReal(imovelNovo
										.getAreaConstruida()),

						// Número de Moradores
						imovelNovo.getNumeroMorador() == null ? "" : ""
								+ imovelNovo.getNumeroMorador(),

						// Pontos de Utilização
						imovelNovo.getNumeroPontosUtilizacao() == null ? ""
								: "" + imovelNovo.getNumeroPontosUtilizacao(),

						// Tipo do Poço
						imovelNovo.getPocoTipo() == null ? "" : imovelNovo
								.getPocoTipo().getDescricao(),

						// Jardim
						indicadorJardim,

						// Data da Ligação de Água
						imovelNovo.getLigacaoAgua() == null ? "" : imovelNovo
								.getLigacaoAgua().getDataLigacao() == null ? ""
								: dataFormatada.format(imovelNovo
										.getLigacaoAgua().getDataLigacao()),

						// Diâmetro da Ligação de Água
						imovelNovo.getLigacaoAgua() == null ? "" : imovelNovo
								.getLigacaoAgua().getLigacaoAguaDiametro()
								.getDescricao(),

						// Material da Ligação de Água
						imovelNovo.getLigacaoAgua() == null ? "" : imovelNovo
								.getLigacaoAgua().getLigacaoAguaMaterial()
								.getDescricao(),

						// Consumo Mínimo Fixado de Água
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getNumeroConsumoMinimoAgua() == null ? ""
										: ""
												+ imovelNovo
														.getLigacaoAgua()
														.getNumeroConsumoMinimoAgua(),

						// Data da Ligação de Esgoto
						imovelNovo.getLigacaoEsgoto() == null ? ""
								: imovelNovo.getLigacaoEsgoto()
										.getDataLigacao() == null ? ""
										: dataFormatada.format(imovelNovo
												.getLigacaoEsgoto()
												.getDataLigacao()),

						// Diâmetro da Ligação de Esgoto
						imovelNovo.getLigacaoEsgoto() == null ? "" : imovelNovo
								.getLigacaoEsgoto().getLigacaoEsgotoDiametro()
								.getDescricao(),

						// Material da Ligação de Esgoto
						imovelNovo.getLigacaoEsgoto() == null ? "" : imovelNovo
								.getLigacaoEsgoto().getLigacaoEsgotoMaterial()
								.getDescricao(),

						// Percentual da Coleta de Água
						imovelNovo.getLigacaoEsgoto() == null ? "" : Util
								.formatarMoedaReal(imovelNovo
										.getLigacaoEsgoto()
										.getPercentualAguaConsumidaColetada()),

						// Percentual de Esgoto
						imovelNovo.getLigacaoEsgoto() == null ? "" : Util
								.formatarMoedaReal(imovelNovo
										.getLigacaoEsgoto().getPercentual()),

						// Consumo Mínimo Fixado de Esgoto
						imovelNovo.getLigacaoEsgoto() == null ? ""
								: imovelNovo.getLigacaoEsgoto()
										.getConsumoMinimo() == null ? "" : ""
										+ imovelNovo.getLigacaoEsgoto()
												.getConsumoMinimo(),

						// Início dos Dados do Hidrômetro Instalado na
						// Ligação de Água
						// Número
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null ? ""
										: imovelNovo
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometro().getNumero(),

						// Ano de Fabricação
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null ? ""
										: imovelNovo
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometro() == null ? ""
												: ""
														+ imovelNovo
																.getLigacaoAgua()
																.getHidrometroInstalacaoHistorico()
																.getHidrometro()
																.getAnoFabricacao(),

						// Capacidade
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null ? ""
										: imovelNovo
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometro() == null ? ""
												: imovelNovo
														.getLigacaoAgua()
														.getHidrometroInstalacaoHistorico()
														.getHidrometro()
														.getHidrometroCapacidade()
														.getDescricao(),

						// Marca
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null ? ""
										: imovelNovo
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometro() == null ? ""
												: imovelNovo
														.getLigacaoAgua()
														.getHidrometroInstalacaoHistorico()
														.getHidrometro()
														.getHidrometroMarca()
														.getDescricao(),

						// Diâmetro
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null ? ""
										: imovelNovo
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometro() == null ? ""
												: imovelNovo
														.getLigacaoAgua()
														.getHidrometroInstalacaoHistorico()
														.getHidrometro()
														.getHidrometroDiametro()
														.getDescricao(),

						// Tipo
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null ? ""
										: imovelNovo
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometro() == null ? ""
												: imovelNovo
														.getLigacaoAgua()
														.getHidrometroInstalacaoHistorico()
														.getHidrometro()
														.getHidrometroTipo()
														.getDescricao(),

						// Data de Instalação
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null ? ""
										: dataFormatada
												.format(imovelNovo
														.getLigacaoAgua()
														.getHidrometroInstalacaoHistorico()
														.getDataInstalacao()),

						// Local de Instalação
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null ? ""
										: imovelNovo
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometroLocalInstalacao()
												.getDescricao(),

						// Tipo de Proteção
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null ? ""
										: imovelNovo
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometroProtecao()
												.getDescricao(),

						// Indicador da Existência de Cavalete
						imovelNovo.getLigacaoAgua() == null ? ""
								: imovelNovo.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null ? ""
										: imovelNovo
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getIndicadorExistenciaCavalete()
												.equals(
														HidrometroInstalacaoHistorico.INDICADOR_CAVALETE_SIM) ? "SIM"
												: "NÃO",
						// Fim dos Dados do Hidrômetro Instalado na Ligação
						// de Água

						// Início dos Dados do Hidrômetro Instalado na Saída
						// do Poço
						// Número
						imovelNovo.getHidrometroInstalacaoHistorico() == null ? ""
								: imovelNovo.getHidrometroInstalacaoHistorico()
										.getHidrometro().getNumero(),

						// Ano de Fabricação
						imovelNovo.getHidrometroInstalacaoHistorico() == null ? ""
								: ""
										+ imovelNovo
												.getHidrometroInstalacaoHistorico() == null ? ""
										: imovelNovo
												.getHidrometroInstalacaoHistorico()
												.getHidrometro() == null ? ""
												: ""
														+ imovelNovo
																.getHidrometroInstalacaoHistorico()
																.getHidrometro()
																.getAnoFabricacao(),

						// Capacidade
						imovelNovo.getHidrometroInstalacaoHistorico() == null ? ""
								: imovelNovo.getHidrometroInstalacaoHistorico()
										.getHidrometro() == null ? ""
										: imovelNovo
												.getHidrometroInstalacaoHistorico()
												.getHidrometro()
												.getHidrometroCapacidade()
												.getDescricao(),

						// Marca
						imovelNovo.getHidrometroInstalacaoHistorico() == null ? ""
								: imovelNovo.getHidrometroInstalacaoHistorico()
										.getHidrometro() == null ? ""
										: imovelNovo
												.getHidrometroInstalacaoHistorico()
												.getHidrometro()
												.getHidrometroMarca()
												.getDescricao(),

						// Diâmetro
						imovelNovo.getHidrometroInstalacaoHistorico() == null ? ""
								: imovelNovo.getHidrometroInstalacaoHistorico()
										.getHidrometro() == null ? ""
										: imovelNovo
												.getHidrometroInstalacaoHistorico()
												.getHidrometro()
												.getHidrometroDiametro()
												.getDescricao(),

						// Tipo
						imovelNovo.getHidrometroInstalacaoHistorico() == null ? ""
								: imovelNovo.getHidrometroInstalacaoHistorico()
										.getHidrometro() == null ? ""
										: imovelNovo
												.getHidrometroInstalacaoHistorico()
												.getHidrometro()
												.getHidrometroTipo()
												.getDescricao(),

						// Data de Instalação
						imovelNovo.getHidrometroInstalacaoHistorico() == null ? ""
								: dataFormatada.format(imovelNovo
										.getHidrometroInstalacaoHistorico()
										.getDataInstalacao()),

						// Local de Instalação
						imovelNovo.getHidrometroInstalacaoHistorico() == null ? ""
								: imovelNovo.getHidrometroInstalacaoHistorico()
										.getHidrometroLocalInstalacao()
										.getDescricao(),

						// Tipo de Proteção
						imovelNovo.getHidrometroInstalacaoHistorico() == null ? ""
								: imovelNovo.getHidrometroInstalacaoHistorico()
										.getHidrometroProtecao().getDescricao(),

						// Indicador da Existência de Cavalete
						imovelNovo.getHidrometroInstalacaoHistorico() == null ? ""
								: imovelNovo
										.getHidrometroInstalacaoHistorico()
										.getIndicadorExistenciaCavalete()
										.equals(
												HidrometroInstalacaoHistorico.INDICADOR_CAVALETE_SIM) ? "SIM"
										: "NÃO",
				
						// Rota
						imovelNovo.getQuadra().getRota().getCodigo().toString(),
						
						// Sequencial Rota
						imovelNovo.getNumeroSequencialRota() == null? "": imovelNovo.getNumeroSequencialRota().toString(),
						
						// Logradouro	
							imovelNovo.getLogradouroCep() == null ? ""
							: imovelNovo.getLogradouroCep().getLogradouro() == null ? ""
								:imovelNovo.getLogradouroCep().getLogradouro().getId().toString()	
						
				);
				// Fim dos Dados do Hidrômetro Instalado na Saída do
				// Poço

				// Fim da Construção do objeto RelatorioManterImovelBean
				// para ser colocado no relatório

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("matricula", imovelParametros.getId() == null ? "" : ""
				+ imovelParametros.getId());
		parametros.put("idLocalidade",
				imovelParametros.getLocalidade().getId() == null ? "" : ""
						+ imovelParametros.getLocalidade().getId());
		parametros.put("nomeLocalidade", imovelParametros.getLocalidade()
				.getDescricao());
		parametros.put("idSetorComercial", imovelParametros.getSetorComercial()
				.getId() == null ? "" : ""
				+ imovelParametros.getSetorComercial().getCodigo());
		parametros.put("nomeSetorComercial", imovelParametros
				.getSetorComercial().getDescricao());
		parametros.put("numeroQuadra", imovelParametros.getQuadra()
				.getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametros.getQuadra().getNumeroQuadra());
		parametros.put("lote", imovelParametros.getLote() == 0 ? "" : ""
				+ imovelParametros.getLote());
		parametros.put("subLote", imovelParametros.getSubLote() == 0 ? "" : ""
				+ imovelParametros.getSubLote());
		parametros.put("idCliente", cliente.getId() == null ? "" : cliente
				.getId().toString());
		parametros.put("nomeCliente", cliente.getNome());
		parametros.put("cep",
				imovelParametros.getLogradouroCep().getCep() == null ? ""
						: imovelParametros.getLogradouroCep().getCep()
								.getCodigo() == null ? "" : ""
								+ imovelParametros.getLogradouroCep().getCep()
										.getCodigo());
		parametros.put("idMunicipio", municipio.getId() == null ? "" : ""
				+ municipio.getId());
		parametros.put("nomeMunicipio", municipio.getNome());
		parametros.put("idBairro", bairro.getId() == null ? "" : ""
				+ bairro.getCodigo());
		parametros.put("nomeBairro", bairro.getNome());
		parametros.put("nomeLogradouro", imovelParametros.getLogradouroCep()
				.getLogradouro() == null ? "" : imovelParametros
				.getLogradouroCep().getLogradouro().getNome());

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relatório em pdf e retorna o array de bytes
		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_IMOVEL_MANTER, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_IMOVEL,
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

		int retorno = Fachada.getInstancia().pesquisarQuantidadeClienteImovel(
				(FiltroClienteImovel) getParametro("filtroClienteImovel"));

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterImovel", this);
	}
}
