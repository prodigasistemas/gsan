/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelRelatorioHelper;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
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
public class RelatorioCadastroConsumidoresInscricao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioCadastroConsumidoresInscricao(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CADASTRO_CONSUMIDORES_INSCRICAO);
	}
	
	@Deprecated
	public RelatorioCadastroConsumidoresInscricao() {
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

		Imovel imovelParametrosInicial = (Imovel) getParametro("imovelParametrosInicial");
		Imovel imovelParametrosFinal = (Imovel) getParametro("imovelParametrosFinal");
		ClienteImovel clienteImovelParametros = (ClienteImovel) getParametro("clienteImovelParametros");
		Municipio municipio = (Municipio) getParametro("municipio");
		Bairro bairro = (Bairro) getParametro("bairro");
		MedicaoHistorico medicaoHistoricoParametrosInicial = (MedicaoHistorico) getParametro("medicaoHistoricoParametrosInicial");
		MedicaoHistorico medicaoHistoricoParametrosFinal = (MedicaoHistorico) getParametro("medicaoHistoricoParametrosFinal");
		ConsumoHistorico consumoHistoricoParametrosInicial = (ConsumoHistorico) getParametro("consumoHistoricoParametrosInicial");
		ConsumoHistorico consumoHistoricoParametrosFinal = (ConsumoHistorico) getParametro("consumoHistoricoParametrosFinal");
		GerenciaRegional gerenciaRegional = (GerenciaRegional) getParametro("gerenciaRegionalParametro");
		Categoria categoria = (Categoria) getParametro("categoria");
		Subcategoria subcategoria = (Subcategoria) getParametro("subcategoria");
		CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) getParametro("cobrancaSituacao");
		String indicadorMedicao = (String) getParametro("indicadorMedicaoParametro");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// Recupera os parâmetros utilizados na formação da query

		// id da genrencia regional
		String gerenciaRegionalPesquisa = (String) getParametro("gerenciaRegional");
		// id da unidade negocio
		String idUnidadeNegoio = (String) getParametro("unidadeNegocio");

		// numero da quadra origem
		String qudraOrigem = (String) getParametro("quadraOrigem");
		// numero quadra destino
		String quadraDestino = (String) getParametro("quadraDestino");
		// lote origem
		String loteOrigem = (String) getParametro("loteOrigem");
		// lote destino
		String loteDestino = (String) getParametro("loteDestino");
		// cep
		String cep = (String) getParametro("cep");
		// id localidade origem
		String localidadeOrigem = (String) getParametro("localidadeOrigem");
		// id localidade destino
		String localidadeDestino = (String) getParametro("localidadeDestino");
		// setor comercial origem CD
		String setorComercialOrigemCD = (String) getParametro("setorComercialOrigemCD");
		// setor comercial destino CD
		String setorComercialDestinoCD = (String) getParametro("setorComercialDestinoCD");
		// cliente ID
		String clienteID = (String) getParametro("clienteID");
		// municipio ID
		String municipioID = (String) getParametro("municipioID");
		// bairro ID
		String bairroID = (String) getParametro("bairroID");
		// logradouro ID
		String logradouroID = (String) getParametro("logradouroID");
		// cliente relacao tipo ID
		String clienteRelacaoTipoID = (String) getParametro("clienteRelacaoTipoID");
		// cliente tipo ID
		String clienteTipoID = (String) getParametro("clienteTipoID");
		// imovel condominio ID
		String imovelCondominioID = (String) getParametro("imovelCondominioID");
		// imovel Principal ID
		String imovelPrincipalID = (String) getParametro("imovelPrincipalID");
		// nome Conta ID
		// String nomeContaID = (String) getParametro("nomeContaID");
		// situacao Agua
		String situacaoAgua = (String) getParametro("situacaoAgua");
		// situacao Ligacao Esgoto
		String situacaoLigacaoEsgoto = (String) getParametro("situacaoLigacaoEsgoto");
		// consumo Minimo Inicial
		String consumoMinimoInicial = (String) getParametro("consumoMinimoInicial");
		// consumo Minimo Final
		String consumoMinimoFinal = (String) getParametro("consumoMinimoFinal");
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = (String) getParametro("consumoMinimoFixadoEsgotoInicial");
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = (String) getParametro("consumoMinimoFixadoEsgotoFinal");
		// intervalo Percentual Esgoto Inicial
		String intervaloPercentualEsgotoInicial = (String) getParametro("intervaloPercentualEsgotoInicial");
		// intervalor Percentual Esgoto Final
		String intervaloPercentualEsgotoFinal = (String) getParametro("intervaloPercentualEsgotoFinal");
		// indicador Medicao
		String indicadorMedicaoPesquisa = (String) getParametro("indicadorMedicao");
		// tipo Medicao ID
		String tipoMedicaoID = (String) getParametro("tipoMedicaoID");
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = (String) getParametro("intervaloMediaMinimaImovelInicial");
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImoveFinal = (String) getParametro("intervaloMediaMinimaImoveFinal");
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = (String) getParametro("intervaloMediaMinimaHidrometroInicial");
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = (String) getParametro("intervaloMediaMinimaHidrometroFinal");
		// perfil Imovel ID
		String perfilImovelID = (String) getParametro("perfilImovelID");
		// categoria Imovel ID
		String categoriaImovelID = (String) getParametro("categoriaImovelID");
		// sub categoria ID
		String subCategoriaID = (String) getParametro("subCategoriaID");
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = (String) getParametro("quantidadeEconomiasInicial");
		// quantidade Economias Final
		String quantidadeEconomiasFinal = (String) getParametro("quantidadeEconomiasFinal");
		// numero Pontos Inicial
		String numeroPontosInicial = (String) getParametro("numeroPontosInicial");
		// numero Pontos Final
		String numeroPontosFinal = (String) getParametro("numeroPontosFinal");
		// numero Moradores Inicial
		String numeroMoradoresInicial = (String) getParametro("numeroMoradoresInicial");
		// numero Moradoras Final
		String numeroMoradoresFinal = (String) getParametro("numeroMoradoresFinal");
		// area Construida Inicial
		String areaConstruidaInicial = (String) getParametro("areaConstruidaInicial");
		// area Construida Final
		String areaConstruidaFinal = (String) getParametro("areaConstruidaFinal");
		// area Construida Faixa
		String areaConstruidaFaixa = (String) getParametro("areaConstruidaFaixa");
		// poco Tipo ID
		String pocoTipoID = (String) getParametro("pocoTipoID");
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = (String) getParametro("tipoSituacaoFaturamentoID");
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = (String) getParametro("tipoSituacaoEspecialCobrancaID");
		// situacao Cobranca ID
		String situacaoCobrancaID = (String) getParametro("situacaoCobrancaID");
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = (String) getParametro("diaVencimentoAlternativo");
		// ocorrencia Cadastro
		String ocorrenciaCadastro = (String) getParametro("ocorrenciaCadastro");
		// tarifa Consumo
		String tarifaConsumo = (String) getParametro("tarifaConsumo");
		// anormalidade Elo
		String anormalidadeElo = (String) getParametro("anormalidadeElo");
		
		// codigo da rota inicial
		String cdRotaInicial = (String) getParametro("cdRotaInicial");
		
		// codigo da rota final
		String cdRotaFinal = (String) getParametro("cdRotaFinal");
		
		// sequencial rota inicial
		String sequencialRotaInicial = (String) getParametro("sequencialRotaInicial");

		// sequencial rota final
		String sequencialRotaFinal = (String) getParametro("sequencialRotaFinal");
		
		String[] pocoTipoListIds = (String[]) getParametro("pocoTipoListIds");
		
		// Ordenação
		String ordenacao = (String) getParametro("ordenacao");
		
		Fachada fachada = Fachada.getInstancia();

		Collection imoveisRelatoriosHelper = fachada
				.gerarRelatorioCadastroConsumidoresInscricao(imovelCondominioID,
						imovelPrincipalID, situacaoAgua, consumoMinimoInicial,
						consumoMinimoFinal, situacaoLigacaoEsgoto,
						consumoMinimoFixadoEsgotoInicial,
						consumoMinimoFixadoEsgotoFinal,
						intervaloPercentualEsgotoInicial,
						intervaloPercentualEsgotoFinal,
						intervaloMediaMinimaImovelInicial,
						intervaloMediaMinimaImoveFinal,
						intervaloMediaMinimaHidrometroInicial,
						intervaloMediaMinimaHidrometroFinal, perfilImovelID,
						pocoTipoID, tipoSituacaoFaturamentoID,
						situacaoCobrancaID, tipoSituacaoEspecialCobrancaID,
						anormalidadeElo, areaConstruidaInicial,
						areaConstruidaFinal, ocorrenciaCadastro, tarifaConsumo,
						gerenciaRegionalPesquisa, localidadeOrigem,
						localidadeDestino, setorComercialOrigemCD,
						setorComercialDestinoCD, qudraOrigem, quadraDestino,
						loteOrigem, loteDestino, cep, logradouroID, bairroID,
						municipioID, tipoMedicaoID, indicadorMedicaoPesquisa,
						subCategoriaID, categoriaImovelID,
						quantidadeEconomiasInicial, quantidadeEconomiasFinal,
						diaVencimentoAlternativo, clienteID, clienteTipoID,
						clienteRelacaoTipoID, numeroPontosInicial,
						numeroPontosFinal, numeroMoradoresInicial,
						numeroMoradoresFinal, areaConstruidaFaixa,
						idUnidadeNegoio, cdRotaInicial, cdRotaFinal,
						sequencialRotaInicial, sequencialRotaFinal, ordenacao, pocoTipoListIds);

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioCadastroConsumidoresInscricaoBean relatorioBean = null;

		// se a coleção de parâmetros da analise não for vazia
		if (imoveisRelatoriosHelper != null
				&& !imoveisRelatoriosHelper.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator imovelRelatorioIterator = imoveisRelatoriosHelper
					.iterator();

			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

			// laço para criar a coleção de parâmetros da analise
			while (imovelRelatorioIterator.hasNext()) {

				ImovelRelatorioHelper imovelRelatorioHelper = (ImovelRelatorioHelper) imovelRelatorioIterator
						.next();

				String imovelSubcategoriaImprimir = "";

				if (imovelRelatorioHelper.getSubcategoriaQtdEconomia() != null) {

					String[] subCategoriaQtdEconomia = imovelRelatorioHelper
							.getSubcategoriaQtdEconomia();
					int i = 0;
					while (i < imovelRelatorioHelper
							.getSubcategoriaQtdEconomia().length) {

						// Concatenar as categorias com suas
						// respectivas
						// quantidades de economias

						imovelSubcategoriaImprimir = imovelSubcategoriaImprimir
								+ subCategoriaQtdEconomia[i];

						i = i + 1;
					}
				}

				// Início da Construção do objeto
				// RelatorioManterImovelBean
				// para ser colocado no relatório
				relatorioBean = new RelatorioCadastroConsumidoresInscricaoBean(

						// Código da Gerência Regional
						imovelRelatorioHelper.getIdGerenciaRegional() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getIdGerenciaRegional(),

						// Descrição da Gerência Regional
						imovelRelatorioHelper.getDescricaoGerenciaRegional() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoGerenciaRegional(),

						// Código Unidade de Negócio
						imovelRelatorioHelper.getIdUnidadeNegocio() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getIdUnidadeNegocio(),

						// Nome Unidade de Negócio
						imovelRelatorioHelper.getNomeUnidadeNegocio() == null ? ""
								: imovelRelatorioHelper.getNomeUnidadeNegocio(),

						// Código da Localidade
						imovelRelatorioHelper.getIdLocalidade() == null ? ""
								: "" + imovelRelatorioHelper.getIdLocalidade(),

						// Descrição da Localidade
						imovelRelatorioHelper.getDescricaoLocalidade() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoLocalidade(),

						// Código do Setor Comercial
						imovelRelatorioHelper.getCodigoSetorComercial() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getCodigoSetorComercial(),

						// Descrição do Setor Comercial
						imovelRelatorioHelper.getDescricaoSetorComercial() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoSetorComercial(),

						// Inscrição do Imóvel composto do código da
						// localidade, código do setor comercial, número
						// da
						// quadra, lote e sublote
						imovelRelatorioHelper.getInscricaoImovel() == null ? ""
								: imovelRelatorioHelper.getInscricaoImovel(),

						// Matrícula do Imóvel
						"" + imovelRelatorioHelper.getMatriculaImovel(),

						// Código do Cliente Usuário
						imovelRelatorioHelper.getClienteUsuarioId() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getClienteUsuarioId(),

						// Nome do Cliente Usuário
						imovelRelatorioHelper.getClienteUsuarioNome() == null ? ""
								: imovelRelatorioHelper.getClienteUsuarioNome(),

						// Código do Cliente Responsável
						imovelRelatorioHelper.getClienteResponsavelId() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getClienteResponsavelId(),

						// Nome do Cliente Responsável
						imovelRelatorioHelper.getClienteResponsavelNome() == null ? ""
								: imovelRelatorioHelper
										.getClienteResponsavelNome(),

						// Endereço
						imovelRelatorioHelper.getEndereco(),

						// Indicador Imóvel Condomínio
						imovelRelatorioHelper.getIndicadorImovelCondominio() == 1 ? "SIM"
								: "NÃO",

						// Matrícula Imóvel Condomínio
						imovelRelatorioHelper.getMatriculaImovelCondominio() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getMatriculaImovelCondominio(),

						// Matrícula Imóvel Principal
						imovelRelatorioHelper.getMatriculaImovelPrincipal() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getMatriculaImovelPrincipal(),

						// Perfil Imóvel
						imovelRelatorioHelper.getPerfilImovel() == null ? ""
								: imovelRelatorioHelper.getPerfilImovel(),

						// Subcategorias / Quantidade de Economias
						imovelSubcategoriaImprimir,

						// Situação da Ligação de Água
						imovelRelatorioHelper.getLigacaoAguaSituacao(),

						// Situação da Ligação de Esgoto
						imovelRelatorioHelper.getLigacaoEsgotoSituacao(),

						// Tipo Pavimento Calçada
						imovelRelatorioHelper.getTipoPavimentoCalcada(),

						// Tipo Pavimento Rua
						imovelRelatorioHelper.getTipoPavimentoRua(),

						// Tipo de Despejo
						imovelRelatorioHelper.getTipoDespejo(),

						// Volume do Reservatório Superior
						imovelRelatorioHelper.getVolumeReservatorioSuperior() == null ?  "0" : imovelRelatorioHelper.getVolumeReservatorioSuperior().toString(),

						// Volume do Reservatório Inferior
						imovelRelatorioHelper.getVolumeReservatorioInferior() == null ? "0" : imovelRelatorioHelper.getVolumeReservatorioInferior().toString(),

						// Volume da Piscina
						imovelRelatorioHelper.getVolumePiscina() == null ? "0" : imovelRelatorioHelper.getVolumePiscina().toString(),  

						// Média de Consumo do Imóvel
						imovelRelatorioHelper.getConsumoMedioImovel() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getConsumoMedioImovel(),

						// Área Construída
						imovelRelatorioHelper.getAreaConstruidaImovel() == null ? "" : imovelRelatorioHelper.getAreaConstruidaImovel() + "",

						// Número de Moradores
						imovelRelatorioHelper.getNumeroMoradores() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getNumeroMoradores(),

						// Pontos de Utilização
						imovelRelatorioHelper.getNumeroPontosUtilzacao() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getNumeroPontosUtilzacao(),

						// Tipo do Poço
						imovelRelatorioHelper.getDescricaoTipoPoco() == null ? ""
								: imovelRelatorioHelper.getDescricaoTipoPoco(),

						// Jardim
						imovelRelatorioHelper.getJardim(),

						// Data da Ligação de Água
						imovelRelatorioHelper.getDataLigacaoAgua() == null ? ""
								: dataFormatada.format(imovelRelatorioHelper
										.getDataLigacaoAgua()),

						// Diâmetro da Ligação de Água
						imovelRelatorioHelper.getDiametroLigacaoAgua() == null ? ""
								: imovelRelatorioHelper
										.getDiametroLigacaoAgua(),

						// Material da Ligação de Água
						imovelRelatorioHelper.getMaterialLigacaoAgua() == null ? ""
								: imovelRelatorioHelper
										.getMaterialLigacaoAgua(),

						// Consumo Mínimo Fixado de Água
						imovelRelatorioHelper.getConsumoMinimoFixadoAgua() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getConsumoMinimoFixadoAgua(),

						// Data da Ligação de Esgoto
						imovelRelatorioHelper.getDataLigacaoEsgoto() == null ? ""
								: dataFormatada.format(imovelRelatorioHelper
										.getDataLigacaoEsgoto()),

						// Diâmetro da Ligação de Esgoto
						imovelRelatorioHelper.getDiametroLigacaoEsgoto() == null ? ""
								: imovelRelatorioHelper
										.getDiametroLigacaoEsgoto(),

						// Material da Ligação de Esgoto
						imovelRelatorioHelper.getMaterialLigacaoEsgoto() == null ? ""
								: imovelRelatorioHelper
										.getMaterialLigacaoEsgoto(),

						// Percentual da Coleta de Água
						imovelRelatorioHelper
								.getPercentualAguaConsumidaColetada() == null ? ""
								: imovelRelatorioHelper
										.getPercentualAguaConsumidaColetada()
										.toString(),

						// Percentual de Esgoto
						imovelRelatorioHelper.getPercentual() == null ? ""
								: imovelRelatorioHelper.getPercentual()
										.toString(),

						// Consumo Mínimo Fixado de Esgoto
						imovelRelatorioHelper
								.getConsumoMinimoFixadoLigacaoEsgoto() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getConsumoMinimoFixadoLigacaoEsgoto(),

						// Início dos Dados do Hidrômetro Instalado na
						// Ligação de Água
						// Número
						imovelRelatorioHelper.getNumeroHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getNumeroHidrometroAgua(),

						// Ano de Fabricação
						imovelRelatorioHelper.getAnoFabricaocaHidrometroAgua() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getAnoFabricaocaHidrometroAgua(),

						// Capacidade
						imovelRelatorioHelper.getCapacidadeHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getCapacidadeHidrometroAgua(),

						// Marca
						imovelRelatorioHelper.getMarcaHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getMarcaHidrometroAgua(),

						// Diâmetro
						imovelRelatorioHelper.getDiametroHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getDiametroHidrometroAgua(),

						// Tipo
						imovelRelatorioHelper.getTipoHidrometroAgua() == null ? ""
								: imovelRelatorioHelper.getTipoHidrometroAgua(),

						// Data de Instalação
						imovelRelatorioHelper.getDataInstalacaoHidrometroAgua() == null ? ""
								: dataFormatada.format(imovelRelatorioHelper
										.getDataInstalacaoHidrometroAgua()),

						// Local de Instalação
						imovelRelatorioHelper
								.getLocalInstalacaoHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getLocalInstalacaoHidrometroAgua(),

						// Tipo de Proteção
						imovelRelatorioHelper.getTipoProtecaoHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getTipoProtecaoHidrometroAgua(),

						// Indicador da Existência de Cavalete
						imovelRelatorioHelper
								.getIndicadorExistenciaCavaleteAgua() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getIndicadorExistenciaCavaleteAgua(),

						// Fim dos Dados do Hidrômetro Instalado na
						// Ligação
						// de Água

						// Início dos Dados do Hidrômetro Instalado na
						// Saída
						// do Poço
						// Número
						imovelRelatorioHelper.getNumeroHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getNumeroHidrometroPoco(),

						// Ano de Fabricação
						imovelRelatorioHelper.getAnoFabricacaoHidrometroPoco() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getAnoFabricacaoHidrometroPoco(),

						// Capacidade
						imovelRelatorioHelper.getCapacidadeHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getCapacidadeHidrometroPoco(),

						// Marca
						imovelRelatorioHelper.getMarcaHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getMarcaHidrometroPoco(),

						// Diâmetro
						imovelRelatorioHelper.getDiametroHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getDiametroHidrometroPoco(),

						// Tipo
						imovelRelatorioHelper.getTipoHidrometroPoco() == null ? ""
								: imovelRelatorioHelper.getTipoHidrometroPoco(),

						// Data de Instalação
						imovelRelatorioHelper.getDataInstalacaoHidrometroPoco() == null ? ""
								: dataFormatada.format(imovelRelatorioHelper
										.getDataInstalacaoHidrometroPoco()),

						// Local de Instalação
						imovelRelatorioHelper
								.getLocalInstalacaoHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getLocalInstalacaoHidrometroPoco(),

						// Tipo de Proteção
						imovelRelatorioHelper.getTipoProtecaoHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getTipoProtecaoHidrometroPoco(),

						// Indicador da Existência de Cavalete
						imovelRelatorioHelper
								.getIndicadorExistenciaCavaletePoco() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getIndicadorExistenciaCavaletePoco(),
				
						// Rota
						imovelRelatorioHelper.getCodigoRota().toString(),
						
						// Número Sequencial Rota
						imovelRelatorioHelper.getNumeroSequencialRota() == null ? ""
								: imovelRelatorioHelper
										.getNumeroSequencialRota().toString(),
										
						// Testada Lote
						imovelRelatorioHelper.getTestadaLote() == null ? ""
								: imovelRelatorioHelper.getTestadaLote().toString()
								
				);
				
				relatorioBean.setTipoFaturamento(imovelRelatorioHelper.getTipoFaturamento() == null ? "" : imovelRelatorioHelper.getTipoFaturamento());
				
				relatorioBean.setQuantidadeEconomia(imovelRelatorioHelper.getQuantidadeEconomia() == null ? "" : imovelRelatorioHelper.getQuantidadeEconomia() + "");				
				
				relatorioBean.setDescricaoAbreviadaCategoria(imovelRelatorioHelper.getDescricaoAbreviadaCategoria() == null ? "" : imovelRelatorioHelper.getDescricaoAbreviadaCategoria());
				
				relatorioBean.setIdLogradouro(imovelRelatorioHelper.getIdLogradouro() == null ? "" : imovelRelatorioHelper.getIdLogradouro());
				
				relatorioBean.setFone(imovelRelatorioHelper.getFone() == null ? "" : imovelRelatorioHelper.getFone());	
				
				// Fim dos Dados do Hidrômetro Instalado na Saída do
				// Poço

				// Fim da Construção do objeto RelatorioManterImovelBean
				// para ser colocado no relatório

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
			
			// Organizar a coleção
//			Collections.sort((List) relatorioBeans, new Comparator() {
//				public int compare(Object a, Object b) {
//					
//					String chave1 = ((RelatorioCadastroConsumidoresInscricaoBean) a)
//							.getIdLocalidade() == null ? ""
//							+ ((RelatorioCadastroConsumidoresInscricaoBean) a)
//									.getEndereco()
//							: Util
//									.adicionarZerosEsquedaNumero(
//											3,
//											((RelatorioCadastroConsumidoresInscricaoBean) a)
//													.getIdLocalidade())
//									+ ((RelatorioCadastroConsumidoresInscricaoBean) a)
//											.getEndereco();
//					String chave2 = ((RelatorioCadastroConsumidoresInscricaoBean) b)
//							.getIdLocalidade() == null ? ""
//							+ ((RelatorioCadastroConsumidoresInscricaoBean) b)
//									.getEndereco()
//							: Util
//									.adicionarZerosEsquedaNumero(
//											3,
//											((RelatorioCadastroConsumidoresInscricaoBean) b)
//													.getIdLocalidade())
//									+ ((RelatorioCadastroConsumidoresInscricaoBean) b)
//											.getEndereco();
//
//					return chave1.compareTo(chave2);
//
//				}
//			});
//			
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("gerenciaRegional", gerenciaRegional == null ? ""
				: gerenciaRegional.getNomeAbreviado());
		parametros.put("idLocalidadeOrigem", imovelParametrosInicial
				.getLocalidade().getId() == null ? "" : ""
				+ imovelParametrosInicial.getLocalidade().getId());
		parametros.put("idLocalidadeDestino", imovelParametrosFinal
				.getLocalidade().getId() == null ? "" : ""
				+ imovelParametrosFinal.getLocalidade().getId());
		parametros.put("nomeLocalidadeOrigem", imovelParametrosInicial
				.getLocalidade().getDescricao());
		parametros.put("nomeLocalidadeDestino", imovelParametrosFinal
				.getLocalidade().getDescricao());
		parametros.put("idSetorComercialOrigem", imovelParametrosInicial
				.getSetorComercial().getId() == null ? "" : ""
				+ imovelParametrosInicial.getSetorComercial().getCodigo());
		parametros.put("idSetorComercialDestino", imovelParametrosFinal
				.getSetorComercial().getId() == null ? "" : ""
				+ imovelParametrosFinal.getSetorComercial().getCodigo());
		parametros.put("nomeSetorComercialOrigem", imovelParametrosInicial
				.getSetorComercial().getDescricao());
		parametros.put("nomeSetorComercialDestino", imovelParametrosFinal
				.getSetorComercial().getDescricao());
		parametros.put("numeroQuadraOrigem", imovelParametrosInicial
				.getQuadra().getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametrosInicial.getQuadra().getNumeroQuadra());
		parametros.put("numeroQuadraDestino", imovelParametrosFinal.getQuadra()
				.getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametrosFinal.getQuadra().getNumeroQuadra());
		parametros.put("loteOrigem",
				imovelParametrosInicial.getLote() == 0 ? "" : ""
						+ imovelParametrosInicial.getLote());
		parametros.put("loteDestino", imovelParametrosFinal.getLote() == 0 ? ""
				: "" + imovelParametrosFinal.getLote());
		parametros.put("idMunicipio", municipio.getId() == null ? "" : ""
				+ municipio.getId());
		parametros.put("nomeMunicipio", municipio.getNome());
		parametros.put("idBairro", bairro.getCodigo() == 0 ? "" : ""
				+ bairro.getCodigo());
		parametros.put("nomeBairro", bairro.getNome());
		parametros.put("cep", imovelParametrosInicial.getLogradouroCep()
				.getCep() == null ? "" : imovelParametrosInicial
				.getLogradouroCep().getCep().getCodigo() == null ? "" : ""
				+ imovelParametrosInicial.getLogradouroCep().getCep()
						.getCodigo());

		parametros.put("idLogradouro", imovelParametrosInicial
				.getLogradouroCep().getLogradouro() == null ? ""
				: imovelParametrosInicial.getLogradouroCep().getLogradouro()
						.getId() == null ? "" : ""
						+ imovelParametrosInicial.getLogradouroCep()
								.getLogradouro().getId());

		parametros.put("nomeLogradouro", imovelParametrosInicial
				.getLogradouroCep().getLogradouro() == null ? ""
				: imovelParametrosInicial.getLogradouroCep().getLogradouro()
						.getNome());

		parametros
				.put(
						"idCliente",
						clienteImovelParametros.getCliente() == null ? ""
								: clienteImovelParametros.getCliente().getId() == null ? ""
										: ""
												+ clienteImovelParametros
														.getCliente().getId());

		parametros.put("nomeCliente",
				clienteImovelParametros.getCliente() == null ? ""
						: clienteImovelParametros.getCliente().getNome());
		parametros.put("tipoRelacao", clienteImovelParametros
				.getClienteRelacaoTipo().getDescricao());
		parametros.put("tipoCliente", clienteImovelParametros.getCliente()
				.getClienteTipo().getDescricao());
		parametros.put("imovelCondominio", imovelParametrosInicial
				.getImovelCondominio().getId() == null ? "" : ""
				+ imovelParametrosInicial.getImovelCondominio().getId());
		parametros.put("imovelPrincipal", imovelParametrosInicial
				.getImovelPrincipal().getId() == null ? "" : ""
				+ imovelParametrosInicial.getImovelPrincipal().getId());
		// parametros.put("nomeConta", imovelParametrosInicial.getNomeConta()
		// .getNomeConta());
		parametros.put("situacaoLigacaoAgua", imovelParametrosInicial
				.getLigacaoAguaSituacao().getDescricao());
		parametros.put("situacaoLigacaoEsgoto", imovelParametrosInicial
				.getLigacaoEsgotoSituacao().getDescricao());
		parametros.put("consumoMinimoFixadoAguaInicial",
				imovelParametrosInicial.getLigacaoAgua()
						.getNumeroConsumoMinimoAgua() == null ? null : ""
						+ imovelParametrosInicial.getLigacaoAgua()
								.getNumeroConsumoMinimoAgua());
		parametros.put("consumoMinimoFixadoAguaFinal", imovelParametrosFinal
				.getLigacaoAgua().getNumeroConsumoMinimoAgua() == null ? null
				: ""
						+ imovelParametrosFinal.getLigacaoAgua()
								.getNumeroConsumoMinimoAgua());
		parametros.put("percentualEsgotoInicial", imovelParametrosInicial
				.getLigacaoEsgoto().getPercentual() == null ? null
				: imovelParametrosInicial.getLigacaoEsgoto().getPercentual()
						.toString());
		parametros.put("percentualEsgotoFinal", imovelParametrosFinal
				.getLigacaoEsgoto().getPercentual() == null ? null
				: imovelParametrosFinal.getLigacaoEsgoto().getPercentual()
						.toString());
		parametros
				.put("consumoMinimoFixadoEsgotoInicial",
						imovelParametrosInicial.getLigacaoEsgoto()
								.getConsumoMinimo() == null ? null : ""
								+ imovelParametrosInicial.getLigacaoEsgoto()
										.getConsumoMinimo());
		parametros.put("consumoMinimoFixadoEsgotoFinal", imovelParametrosFinal
				.getLigacaoEsgoto().getConsumoMinimo() == null ? null : ""
				+ imovelParametrosFinal.getLigacaoEsgoto().getConsumoMinimo());
		parametros.put("indicadorMedicao", indicadorMedicao == null ? ""
				: indicadorMedicao.equals("comMedicao") ? "COM MEDIÇÃO"
						: "SEM MEDIÇÃO");
		parametros.put("tipoMedicao", medicaoHistoricoParametrosInicial
				.getMedicaoTipo().getDescricao());
		parametros
				.put(
						"mediaMinimaImovelInicial",
						consumoHistoricoParametrosInicial.getConsumoMedio() == null ? null
								: ""
										+ consumoHistoricoParametrosInicial
												.getConsumoMedio());
		parametros
				.put("mediaMinimaImovelFinal", consumoHistoricoParametrosFinal
						.getConsumoMedio() == null ? null : ""
						+ consumoHistoricoParametrosFinal.getConsumoMedio());
		parametros
				.put("mediaMinimaHidrometroInicial",
						medicaoHistoricoParametrosInicial
								.getConsumoMedioHidrometro() == null ? null
								: ""
										+ medicaoHistoricoParametrosInicial
												.getConsumoMedioHidrometro());
		parametros
				.put("mediaMinimaHidrometroFinal",
						medicaoHistoricoParametrosFinal
								.getConsumoMedioHidrometro() == null ? null
								: ""
										+ medicaoHistoricoParametrosFinal
												.getConsumoMedioHidrometro());
		parametros.put("perfilImovel", imovelParametrosInicial
				.getImovelPerfil().getDescricao());
		parametros.put("categoria", categoria.getDescricao());
		parametros.put("subCategoria", subcategoria.getDescricao());
		parametros.put("qtdeEconomiaInicial", imovelParametrosInicial
				.getQuantidadeEconomias().equals(new Short("0")) ? null : ""
				+ imovelParametrosInicial.getQuantidadeEconomias());
		parametros.put("qtdeEconomiaFinal", imovelParametrosFinal
				.getQuantidadeEconomias().equals(new Short("0")) ? null : ""
				+ imovelParametrosFinal.getQuantidadeEconomias());
		parametros.put("numeroPontosInicial", imovelParametrosInicial
				.getNumeroPontosUtilizacao() == 0 ? null : ""
				+ imovelParametrosInicial.getNumeroPontosUtilizacao());
		parametros.put("numeroPontosFinal", imovelParametrosFinal
				.getNumeroPontosUtilizacao() == 0 ? null : ""
				+ imovelParametrosFinal.getNumeroPontosUtilizacao());
		parametros.put("numeroMoradoresInicial", imovelParametrosInicial
				.getNumeroMorador() == 0 ? null : ""
				+ imovelParametrosInicial.getNumeroMorador());
		parametros.put("numeroMoradoresFinal", imovelParametrosFinal
				.getNumeroMorador() == 0 ? null : ""
				+ imovelParametrosFinal.getNumeroMorador());
		parametros.put("areaConstruidaInicial", imovelParametrosInicial
				.getAreaConstruida().equals(new BigDecimal("0")) ? null : ""
				+ imovelParametrosInicial.getAreaConstruida());
		parametros.put("areaConstruidaFinal", imovelParametrosFinal
				.getAreaConstruida().equals(new BigDecimal("0")) ? null : ""
				+ imovelParametrosFinal.getAreaConstruida());
		parametros.put("tipoPoco", imovelParametrosInicial.getPocoTipo()
				.getDescricao());
		parametros.put("tipoSituacaoEspecialFaturamento",
				imovelParametrosInicial.getFaturamentoSituacaoTipo()
						.getDescricao());
		parametros.put("tipoSituacaoEspecialCobranca", imovelParametrosInicial
				.getCobrancaSituacaoTipo().getDescricao());
		parametros.put("situacaoCobranca", cobrancaSituacao == null ? ""
				: cobrancaSituacao.getDescricao());
		parametros.put("diaVencimentoAlternativo", imovelParametrosInicial
				.getDiaVencimento() == null ? "" : ""
				+ imovelParametrosInicial.getDiaVencimento());
		parametros.put("anormalidadeElo", imovelParametrosInicial
				.getEloAnormalidade() == null ? "" : imovelParametrosInicial
				.getEloAnormalidade().getDescricao());
		parametros.put("ocorrenciaCadastro", imovelParametrosInicial
				.getCadastroOcorrencia() == null ? "" : imovelParametrosInicial
				.getCadastroOcorrencia().getDescricao());
		parametros.put("tarifaConsumo", imovelParametrosInicial
				.getConsumoTarifa() == null ? "" : imovelParametrosInicial
				.getConsumoTarifa().getDescricao());
		parametros.put("tipoFormatoRelatorio", "R0612");

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relatório em pdf e retorna o array de bytes
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CADASTRO_CONSUMIDORES_INSCRICAO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.CADASTRO_CONSUMIDORES_INSCRICAO,
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
		
		// Recupera os parâmetros utilizados na formação da query
		// id da genrencia regional
		String gerenciaRegionalPesquisa = (String) getParametro("gerenciaRegional");
		// id da unidade negocio
		String idUnidadeNegoio = (String) getParametro("unidadeNegocio");
		// numero da quadra origem
		String qudraOrigem = (String) getParametro("quadraOrigem");
		// numero quadra destino
		String quadraDestino = (String) getParametro("quadraDestino");
		// lote origem
		String loteOrigem = (String) getParametro("loteOrigem");
		// lote destino
		String loteDestino = (String) getParametro("loteDestino");
		// cep
		String cep = (String) getParametro("cep");
		// id localidade origem
		String localidadeOrigem = (String) getParametro("localidadeOrigem");
		// id localidade destino
		String localidadeDestino = (String) getParametro("localidadeDestino");
		// setor comercial origem CD
		String setorComercialOrigemCD = (String) getParametro("setorComercialOrigemCD");
		// setor comercial destino CD
		String setorComercialDestinoCD = (String) getParametro("setorComercialDestinoCD");
		// cliente ID
		String clienteID = (String) getParametro("clienteID");
		// municipio ID
		String municipioID = (String) getParametro("municipioID");
		// bairro ID
		String bairroID = (String) getParametro("bairroID");
		// logradouro ID
		String logradouroID = (String) getParametro("logradouroID");
		// cliente relacao tipo ID
		String clienteRelacaoTipoID = (String) getParametro("clienteRelacaoTipoID");
		// cliente tipo ID
		String clienteTipoID = (String) getParametro("clienteTipoID");
		// imovel condominio ID
		String imovelCondominioID = (String) getParametro("imovelCondominioID");
		// imovel Principal ID
		String imovelPrincipalID = (String) getParametro("imovelPrincipalID");
		// nome Conta ID
		// String nomeContaID = (String) getParametro("nomeContaID");
		// situacao Agua
		String situacaoAgua = (String) getParametro("situacaoAgua");
		// situacao Ligacao Esgoto
		String situacaoLigacaoEsgoto = (String) getParametro("situacaoLigacaoEsgoto");
		// consumo Minimo Inicial
		String consumoMinimoInicial = (String) getParametro("consumoMinimoInicial");
		// consumo Minimo Final
		String consumoMinimoFinal = (String) getParametro("consumoMinimoFinal");
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = (String) getParametro("consumoMinimoFixadoEsgotoInicial");
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = (String) getParametro("consumoMinimoFixadoEsgotoFinal");
		// intervalo Percentual Esgoto Inicial
		String intervaloPercentualEsgotoInicial = (String) getParametro("intervaloPercentualEsgotoInicial");
		// intervalor Percentual Esgoto Final
		String intervaloPercentualEsgotoFinal = (String) getParametro("intervaloPercentualEsgotoFinal");
		// indicador Medicao
		String indicadorMedicaoPesquisa = (String) getParametro("indicadorMedicao");
		// tipo Medicao ID
		String tipoMedicaoID = (String) getParametro("tipoMedicaoID");
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = (String) getParametro("intervaloMediaMinimaImovelInicial");
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImoveFinal = (String) getParametro("intervaloMediaMinimaImoveFinal");
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = (String) getParametro("intervaloMediaMinimaHidrometroInicial");
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = (String) getParametro("intervaloMediaMinimaHidrometroFinal");
		// perfil Imovel ID
		String perfilImovelID = (String) getParametro("perfilImovelID");
		// categoria Imovel ID
		String categoriaImovelID = (String) getParametro("categoriaImovelID");
		// sub categoria ID
		String subCategoriaID = (String) getParametro("subCategoriaID");
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = (String) getParametro("quantidadeEconomiasInicial");
		// quantidade Economias Final
		String quantidadeEconomiasFinal = (String) getParametro("quantidadeEconomiasFinal");
		// numero Pontos Inicial
		String numeroPontosInicial = (String) getParametro("numeroPontosInicial");
		// numero Pontos Final
		String numeroPontosFinal = (String) getParametro("numeroPontosFinal");
		// numero Moradores Inicial
		String numeroMoradoresInicial = (String) getParametro("numeroMoradoresInicial");
		// numero Moradoras Final
		String numeroMoradoresFinal = (String) getParametro("numeroMoradoresFinal");
		// area Construida Inicial
		String areaConstruidaInicial = (String) getParametro("areaConstruidaInicial");
		// area Construida Final
		String areaConstruidaFinal = (String) getParametro("areaConstruidaFinal");
		// area Construida Faixa
		String areaConstruidaFaixa = (String) getParametro("areaConstruidaFaixa");
		// poco Tipo ID
		String pocoTipoID = (String) getParametro("pocoTipoID");
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = (String) getParametro("tipoSituacaoFaturamentoID");
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = (String) getParametro("tipoSituacaoEspecialCobrancaID");
		// situacao Cobranca ID
		String situacaoCobrancaID = (String) getParametro("situacaoCobrancaID");
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = (String) getParametro("diaVencimentoAlternativo");
		// ocorrencia Cadastro
		String ocorrenciaCadastro = (String) getParametro("ocorrenciaCadastro");
		// tarifa Consumo
		String tarifaConsumo = (String) getParametro("tarifaConsumo");
		// anormalidade Elo
		String anormalidadeElo = (String) getParametro("anormalidadeElo");
		// codigo da rota inicial
		String cdRotaInicial = (String) getParametro("cdRotaInicial");
		// codigo da rota final
		String cdRotaFinal = (String) getParametro("cdRotaFinal");
		// sequencial rota inicial
		String sequencialRotaInicial = (String) getParametro("sequencialRotaInicial");
		// sequencial rota final
		String sequencialRotaFinal = (String) getParametro("sequencialRotaFinal");
		
		String[] pocoTipoListIds = (String[]) getParametro("pocoTipoListIds");

		retorno = Fachada
				.getInstancia()
				.gerarRelatorioCadastroConsumidoresInscricaoCount(imovelCondominioID,
						imovelPrincipalID, situacaoAgua, consumoMinimoInicial,
						consumoMinimoFinal, situacaoLigacaoEsgoto,
						consumoMinimoFixadoEsgotoInicial,
						consumoMinimoFixadoEsgotoFinal,
						intervaloPercentualEsgotoInicial,
						intervaloPercentualEsgotoFinal,
						intervaloMediaMinimaImovelInicial,
						intervaloMediaMinimaImoveFinal,
						intervaloMediaMinimaHidrometroInicial,
						intervaloMediaMinimaHidrometroFinal, perfilImovelID,
						pocoTipoID, tipoSituacaoFaturamentoID,
						situacaoCobrancaID, tipoSituacaoEspecialCobrancaID,
						anormalidadeElo, areaConstruidaInicial,
						areaConstruidaFinal, ocorrenciaCadastro, tarifaConsumo,
						gerenciaRegionalPesquisa, localidadeOrigem,
						localidadeDestino, setorComercialOrigemCD,
						setorComercialDestinoCD, qudraOrigem, quadraDestino,
						loteOrigem, loteDestino, cep, logradouroID, bairroID,
						municipioID, tipoMedicaoID, indicadorMedicaoPesquisa,
						subCategoriaID, categoriaImovelID,
						quantidadeEconomiasInicial, quantidadeEconomiasFinal,
						diaVencimentoAlternativo, clienteID, clienteTipoID,
						clienteRelacaoTipoID, numeroPontosInicial,
						numeroPontosFinal, numeroMoradoresInicial,
						numeroMoradoresFinal, areaConstruidaFaixa,
						idUnidadeNegoio, cdRotaInicial, cdRotaFinal,
						sequencialRotaInicial, sequencialRotaFinal, pocoTipoListIds);

		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

		}
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioCadastroConsumidoresInscricao", this);
	}

}
