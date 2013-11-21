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
package gcom.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.SessionBean;

/**
 * Controlador Micromedicao CAERN
 * 
 * @author Sávio Luiz
 * @date 19/03/2007
 */
public class ControladorMicromedicaoCAERNSEJB extends ControladorMicromedicao
		implements SessionBean {
	private static final long serialVersionUID = 1L;

	// ==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAERN
	// ==============================================================================================================

	// valida anoMes para caso de uso anlise excecoes leituras
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

	// valida anoMes para caso de uso anlise excecoes leituras
	public boolean validaDataFaturamentoIncompativelInferior(
			String anoMesReferencia, String anoMesAnterior) {
		boolean retorno = true;

		String anoMesReferenciaMenosUmMes = ""
				+ Util.subtrairMesDoAnoMes(new Integer(anoMesReferencia), 2);

		// Comparando a data anterior faturada no form com o ano
		// mês
		// referência e com o ano mês anterior
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

	/*public int obterConsumoMinimoLigacao(Imovel imovel,
			Collection colecaoCategorias) throws ControladorException {

		// Criamos um totalizador
		Integer tot = 0;

		// Selecionamos as subcategorias do imovel
		FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
		filtroImovelSubCategoria
				.adicionarCaminhoParaCarregamentoEntidade("comp_id.subcategoria.categoria");
		filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(
				FiltroImovelSubCategoria.IMOVEL_ID, imovel.getId()));
		Iterator iteSub = null;
		try {
			Collection colSub = repositorioUtil.pesquisar(
					filtroImovelSubCategoria, ImovelSubcategoria.class
							.getName());
			filtroImovelSubCategoria = null;
			iteSub = colSub.iterator();
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (iteSub.hasNext()) {
			ImovelSubcategoria sub = (ImovelSubcategoria) iteSub.next();

			// Selecionamos o consumo tarifa da vigencia
			FiltroConsumoTarifaVigencia filtroConsumoTarifaVigencia = new FiltroConsumoTarifaVigencia();
			filtroConsumoTarifaVigencia
					.adicionarParametro(new ParametroSimples(
							FiltroConsumoTarifaVigencia.CONSUMO_TARIFA_ID,
							imovel.getConsumoTarifa().getId(),
							ParametroSimples.CONECTOR_AND));
			filtroConsumoTarifaVigencia.adicionarParametro(new MenorQue(
					FiltroConsumoTarifaVigencia.DATA_VIGENCIA, new Date(),
					ParametroSimples.CONECTOR_OR, 2));
			filtroConsumoTarifaVigencia
					.adicionarParametro(new ParametroSimples(
							FiltroConsumoTarifaVigencia.DATA_VIGENCIA,
							new Date()));

			ConsumoTarifaVigencia consumoTarifaVigenciaPrincipal = null;

			try {
				Iterator iteConsumoTarifaVigencia = repositorioUtil.pesquisar(
						filtroConsumoTarifaVigencia,
						ConsumoTarifaVigencia.class.getName()).iterator();

				while (iteConsumoTarifaVigencia.hasNext()) {
					ConsumoTarifaVigencia consumoTarifaVigencia = (ConsumoTarifaVigencia) iteConsumoTarifaVigencia
							.next();

					if (consumoTarifaVigenciaPrincipal == null
							|| (consumoTarifaVigenciaPrincipal
									.getDataVigencia()
									.before(consumoTarifaVigencia
											.getDataVigencia()))) {
						consumoTarifaVigenciaPrincipal = consumoTarifaVigencia;
					}
				}
			} catch (ErroRepositorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Selecionamos o consumo tarifa da categoria
			FiltroConsumoTarifaCategoria filtroConsumoTarifaCategoria = new FiltroConsumoTarifaCategoria();
			filtroConsumoTarifaCategoria
					.adicionarParametro(new ParametroSimples(
							FiltroConsumoTarifaCategoria.SUBCATEGORIA_ID, sub
									.getComp_id().getSubcategoria().getId()));
			filtroConsumoTarifaCategoria
					.adicionarParametro(new ParametroSimples(
							FiltroConsumoTarifaCategoria.CONSUMO_VIGENCIA_ID,
							consumoTarifaVigenciaPrincipal.getId()));

			try {
				Collection colConsumoTarifaCategoria = repositorioUtil
						.pesquisar(filtroConsumoTarifaCategoria,
								ConsumoTarifaCategoria.class.getName());
				Iterator iteConsumoTarifaCategoria = colConsumoTarifaCategoria
						.iterator();

				// Verificamos se existe tarifa minima para a subcategoria
				// informada
				if (iteConsumoTarifaCategoria != null
						&& iteConsumoTarifaCategoria.hasNext()) {
					ConsumoTarifaCategoria consumo = (ConsumoTarifaCategoria) iteConsumoTarifaCategoria
							.next();

					tot += consumo.getNumeroConsumoMinimo()
							* sub.getQuantidadeEconomias();
				} else {
					// Selecionamos o consumo tarifa da categoria
					filtroConsumoTarifaCategoria = new FiltroConsumoTarifaCategoria();
					filtroConsumoTarifaCategoria
							.adicionarParametro(new ParametroSimples(
									FiltroConsumoTarifaCategoria.CATEGORIA_ID,
									sub.getComp_id().getSubcategoria().getCategoria().getId()));
					filtroConsumoTarifaCategoria
							.adicionarParametro(new ParametroSimples(
									FiltroConsumoTarifaCategoria.CONSUMO_VIGENCIA_ID,
									consumoTarifaVigenciaPrincipal.getId()));

					colConsumoTarifaCategoria = repositorioUtil.pesquisar(
							filtroConsumoTarifaCategoria,
							ConsumoTarifaCategoria.class.getName());
					if (colConsumoTarifaCategoria != null
							&& !colConsumoTarifaCategoria.isEmpty()) {
						iteConsumoTarifaCategoria = colConsumoTarifaCategoria
								.iterator();

						ConsumoTarifaCategoria consumo = (ConsumoTarifaCategoria) iteConsumoTarifaCategoria
								.next();

						tot += consumo.getNumeroConsumoMinimo()
								* sub.getQuantidadeEconomias();
					}
				}

			} catch (ErroRepositorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return tot;
	}*/
	
	
	/**
	 * [UC0105] Obter Consumo Mínimo da Ligação por Subcategoria
	 * 
	 * @author Raphael Rossiter
	 * @date 11/04/2007
	 * 
	 * @return imovel, colecaoSubcategoria
	 * @throws ControladorException
	 */
	/*public int obterConsumoMinimoLigacao(Imovel imovel,
			Collection colecaoSubcategoria) throws ControladorException {

		int consumoMinimoLigacao = 0;

		Collection colecaoDataVigencia = null;
		Integer consumoMinimo = null;

		// Obtém o id do consumo tarifa vigência da maior data de vigência da
		// tarifa do imóvel
		try {
			colecaoDataVigencia = repositorioMicromedicao
					.pesquisarMaiorDataVigenciaConsumoTarifaImovel(new Date(),
							imovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		// Obtém o id do array e atribui na variável
		Object[] consumoTarifaVigenciaIdArray = (Object[]) Util
				.retonarObjetoDeColecaoArray(colecaoDataVigencia);
		Integer consumoTarifaVigenciaId = null;

		if (consumoTarifaVigenciaIdArray == null) {
			throw new ControladorException(
					"erro.nao_cadastrada_consumo_tarifa_vigencia", null, String
							.valueOf(imovel.getId()));
		}

		if (consumoTarifaVigenciaIdArray[0] != null) {
			consumoTarifaVigenciaId = (Integer) consumoTarifaVigenciaIdArray[0];
		}

		// Cria o objeto consumo tarifa vigência e seta o id
		ConsumoTarifaVigencia consumoTarifaVigencia = new ConsumoTarifaVigencia();
		consumoTarifaVigencia.setId(consumoTarifaVigenciaId);

		// Obter Quantidade de Economias por Subcategoria
		if (colecaoSubcategoria == null) {
			colecaoSubcategoria = getControladorImovel()
					.obterQuantidadeEconomiasSubCategoria(imovel.getId());
		}

		Iterator colecaoSubcategoriaIterator = colecaoSubcategoria.iterator();

		while (colecaoSubcategoriaIterator.hasNext()) {

			Subcategoria subcategoria = (Subcategoria) colecaoSubcategoriaIterator
					.next();

			try {
				// Obtém o consumo mínimo da tarifa da categoria do imóvel
				Object consumoMinimoObjeto = repositorioMicromedicao
						.pesquisarConsumoMinimoTarifaSubcategoriaVigencia(
								subcategoria, consumoTarifaVigencia);
				consumoMinimo = (Integer) consumoMinimoObjeto;
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				new ControladorException("erro.sistema", ex);
			}*/

			/*
			 * Caso não exista tarifa cadastrada para a subcategoria em questao,
			 * utilizar a tarifa da categoria
			 */
			/*if (consumoMinimo == null) {

				Categoria categoria = subcategoria.getCategoria();
				categoria.setQuantidadeEconomiasCategoria(subcategoria
						.getQuantidadeEconomias());

				Collection colecaoCategoria = new ArrayList();
				colecaoCategoria.add(categoria);

				consumoMinimo = super.obterConsumoMinimoLigacao(imovel,
				colecaoCategoria); 

				consumoMinimoLigacao = consumoMinimoLigacao + consumoMinimo;
				
			}
			else{
				
				// Multiplica a quantidade de economias da subcategoria pelo consumo
				// mínimo e acumula
				consumoMinimoLigacao = consumoMinimoLigacao
						+ (subcategoria.getQuantidadeEconomias().intValue() * consumoMinimo
								.intValue());
			}

		}

		// Retorna o consumo mínimo da ligação
		return consumoMinimoLigacao;

	}*/
	
	//Colocado por Flávio Cordeiro - 07/03/2008 - pedido por Claudio Lira
	//Modificação nos gerarDadosLeituraConvencionalRegistroUM, gerarDadosLeituraConvencionalRegistroDOIS
	//Caern
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT - Registro 1
	 * 
	 * @author Rafael Francisco Pinto, Raphael Rossiter
	 * @date 22/01/2007, 28/03/2007
	 * 
	 * @param imovel,
	 *            anoMesFaturamento
	 * @throws ControladorException
	 */
	/*protected StringBuilder gerarDadosLeituraConvencionalRegistroUM(
			Imovel imovel, int anoMesFaturamento) throws ControladorException {

		StringBuilder arquivoTxtLinha = new StringBuilder();

		// 1.2.1 - Tipo de Registro 1 (UM)
		arquivoTxtLinha.append("1");

		// 1.2.1.1 - Nome
		String nomeClienteUsuario = null;
		try {
			// Pesquisa o nome do cliente que tem o tipo de relação usuário.
			nomeClienteUsuario = repositorioClienteImovel
					.pesquisarNomeClientePorImovel(imovel.getId());
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		arquivoTxtLinha.append(completaString(nomeClienteUsuario, 40));

		// 1.2.1.2 - Situação (RETIRAR - DEIXAR ESPAÇOS - COM TAMANHO = 06)
		arquivoTxtLinha.append(completaString("", 6));

		// 1.2.1.3
		Collection colecaoSubCategoria = this.getControladorImovel()
				.obterQuantidadeEconomiasSubCategoria(imovel.getId());

		// 1.2.1.3.1 - SubCategoria 01
		// 1.2.1.3.2 - Quantidade 01
		// 1.2.1.3.3 - SubCategoria 02
		// 1.2.1.3.4 - Quantidade 02
		if (colecaoSubCategoria != null && !colecaoSubCategoria.isEmpty()) {

			Iterator itera = colecaoSubCategoria.iterator();
			int tam = 0;
			while (itera.hasNext()) {
				tam++;
				if (tam < 3) {

					Subcategoria subcategoria = (Subcategoria) itera.next();

					// tipoEconomia = categoria_id(1 posição) +
					// subcategoria_codigo(3 posições)
					
					//Retirado por Flávio Cordeiro - 05/05/2008 - pedido por Claudio Lira
					//Alterar a geração do txt do rol de leitura tirando a informação do código da categoria
                    //do arquivo txt, considerando que a 1ª posição do  código da subcategoria corresponde
                    //ao código da categoria.

					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(1,
							subcategoria.getCategoria().getId() + ""));
					
					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(2,
							subcategoria.getCodigo() + ""));

					// qtdeEconomia
					arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(3,
							"" + subcategoria.getQuantidadeEconomias()));

				} else {
					break;
				}
			}

			if (tam < 2) {
				arquivoTxtLinha.append(completaString("", 6));
			}
		} else {
			arquivoTxtLinha.append(completaString("", 12));
		}

		// 1.2.1.4 - Consumo
		Integer numeroConsumoFaturadoMes = null;

		try {

			// 1.2.1.4.1 - Consumo 01
			int anoMesSubtraido = Util
					.subtrairMesDoAnoMes(anoMesFaturamento, 1);

			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovel.getId(), anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);

			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
						+ numeroConsumoFaturadoMes));
			} else {
				arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(
						"", 4));
			}

			// 1.2.1.4.2 - Consumo 02
			anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMesFaturamento, 2);
			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovel.getId(), anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);

			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
						+ numeroConsumoFaturadoMes));
			} else {
				arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(
						"", 4));
			}

			// 1.2.1.4.3 - Consumo 03
			anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMesFaturamento, 3);
			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovel.getId(), anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);

			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
						+ numeroConsumoFaturadoMes));
			} else {
				arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(
						"", 4));
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		// 1.2.1.5 - Condição
		String condicao = null;
		try {
			condicao = this.repositorioMicromedicao.obterDescricaoConsumoTipo(
					imovel.getId(), LigacaoTipo.LIGACAO_AGUA);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		if (condicao != null) {
			arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(""
					+ condicao, 8));
		} else {
			arquivoTxtLinha
					.append(Util.completaStringComEspacoAEsquerda("", 8));
		}

		//Retirado por Flávio Cordeiro - 06/03/2008 - pedido por Claudio Lira
		// 1.2.1.6 - Número do Lacre
//		if (imovel.getLigacaoAgua().getNumeroLacre() != null) {
//			arquivoTxtLinha.append(completaString(imovel.getLigacaoAgua()
//					.getNumeroLacre(), 6));
//		} else {
//			arquivoTxtLinha.append(completaString("", 6));
//		}

		// 1.2.1.7 - Matrícula do Imóvel
		arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(imovel
				.getId().toString(), 9));

		arquivoTxtLinha.append(System.getProperty("line.separator"));

		return arquivoTxtLinha;

	}*/
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT - Registro 2
	 * 
	 * @author Rafael Francisco Pinto, Raphael Rossiter
	 * @date 22/01/2007, 28/03/2007
	 * 
	 * @param imovel,
	 *            anoMesCorrente, sistemaParametro
	 * @throws ControladorException
	 */
	/*protected StringBuilder gerarDadosLeituraConvencionalRegistroDOIS(
			Imovel imovel, Integer anoMesCorrente,
			SistemaParametro sistemaParametro) throws ControladorException {

		StringBuilder arquivoTxtLinha = new StringBuilder();

		boolean ligacaoAgua = false;
		boolean ligacaoPoco = false;

		if (imovel.getLigacaoAgua() != null
				&& imovel.getLigacaoAgua().getId() != null
				&& imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null
				&& imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico()
						.getId() != null) {

			ligacaoAgua = true;
		}

		if (imovel.getHidrometroInstalacaoHistorico() != null
				&& imovel.getHidrometroInstalacaoHistorico().getId() != null) {

			ligacaoPoco = true;
		}

		// Dados do Hidrometro

		Short numeroDigitosHidrometro = null;
		StringBuilder dadosHidrometro = null;

		if (ligacaoAgua && ligacaoPoco) {

			Object[] dadosHidroemtroNumeroLeitura = pesquisarDadosHidrometroTipoLigacaoAgua(imovel);

			dadosHidrometro = (StringBuilder) dadosHidroemtroNumeroLeitura[0];
			numeroDigitosHidrometro = (Short) dadosHidroemtroNumeroLeitura[1];

		} else {
			if (ligacaoAgua) {

				Object[] dadosHidroemtroNumeroLeitura = pesquisarDadosHidrometroTipoLigacaoAgua(imovel);

				dadosHidrometro = (StringBuilder) dadosHidroemtroNumeroLeitura[0];
				numeroDigitosHidrometro = (Short) dadosHidroemtroNumeroLeitura[1];

			} else {

				if (ligacaoPoco) {

					Object[] dadosHidroemtroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovel);

					dadosHidrometro = (StringBuilder) dadosHidroemtroNumeroLeitura[0];
					numeroDigitosHidrometro = (Short) dadosHidroemtroNumeroLeitura[1];

				} else {
					Object[] dadosHidroemtroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovel);

					dadosHidrometro = (StringBuilder) dadosHidroemtroNumeroLeitura[0];
					numeroDigitosHidrometro = (Short) dadosHidroemtroNumeroLeitura[1];

				}
			}
		}

		// 1.2.2 - Tipo de Registro 2 (DOIS)
		arquivoTxtLinha.append("2");

		// 1.2.2.1 - Endereço
		// Pesquisa o endereço do imovel passando o id
		String enderecoImovel = getControladorEndereco()
				.pesquisarEnderecoFormatado(imovel.getId());
		if (enderecoImovel != null && !enderecoImovel.equals("")) {
			// endereço do imóvel
			arquivoTxtLinha.append(completaString(enderecoImovel, 60));
		} else {
			arquivoTxtLinha.append(completaString("", 60));
		}

		

		// 1.2.2.3 - Inscrição do Imovel
		// 1.2.2.3.1 - Quadra
		// 1.2.2.3.2 - Lote
		// 1.2.2.3.3 - SubLote
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(3, ""
				+ imovel.getQuadra().getNumeroQuadra()));
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
				+ imovel.getLote()));
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(3, ""
				+ imovel.getSubLote()));

		// 1.2.2.4 - Sequencial da Rota
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
				+ imovel.getNumeroSequencialRota()));

		// 1.2.2.5 - Leitura Atual (Leitura anterior)
		
		 * Integer anoMesAnterior = Util.subtrairData(anoMesCorrente);
		 * 
		 * String leituraAnterior = null; Integer idMedicaoTipo = null;
		 * 
		 * MedicaoHistorico medicaoHistorico = null;
		 * 
		 * Object[] retorno = pesquisaLeituraAnterior(ligacaoAgua, ligacaoPoco,
		 * anoMesAnterior, imovel); // verifica se a leitura anterior é
		 * diferente de nula if (retorno[0] != null) { leituraAnterior =
		 * retorno[0].toString(); } // verifica se a leitura situação atual é
		 * diferente de // nula if (retorno[1] != null) { medicaoHistorico =
		 * (MedicaoHistorico) retorno[1]; } // verifica se o id da medição tipo
		 * é diferente de nula if (retorno[2] != null) { idMedicaoTipo =
		 * (Integer) retorno[2]; }
		 * 
		 * if (leituraAnterior != null) {
		 * arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(6, "" +
		 * leituraAnterior)); } else {
		 * arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(6, "")); }
		 

		// 1.2.2.6 - Leitura Esperada
		// 1.2.2.7
		// 1.2.2.8
		
		 * Object[] faixaInicialFinal = pesquisarFaixaEsperadaOuFalsa(imovel,
		 * dadosHidrometro, leituraAnterior, medicaoHistorico, idMedicaoTipo,
		 * sistemaParametro, false, numeroDigitosHidrometro);
		 * 
		 * StringBuilder faixaInicialFinalString = (StringBuilder)
		 * faixaInicialFinal[0];
		 * 
		 * arquivoTxtLinha.append(faixaInicialFinalString);
		 

		
		 * Colocado por Raphael Rossiter em 30/03/2007 (Analista: Rosana) Obter
		 * a leitura atual e a leitura esperada da mesma forma que é feito para
		 * o sistema legado.
		 * 
		 * 1.2.2.5 Leitura atual
		 * 
		 * 1.2.2.6 Leitura esperada
		 
		Integer anoMesAnterior = Util.subtrairData(anoMesCorrente);

		String leituraAnterior = null;
		Integer idMedicaoTipo = null;

		MedicaoHistorico medicaoHistorico = null;

		Object[] retorno = pesquisaLeituraAnterior(ligacaoAgua, ligacaoPoco,
				anoMesAnterior, imovel);

		// verifica se a leitura anterior é diferente de nula
		if (retorno[0] != null) {
			leituraAnterior = retorno[0].toString();
		}
		// verifica se a leitura situação atual é diferente de
		// nula
		if (retorno[1] != null) {
			medicaoHistorico = (MedicaoHistorico) retorno[1];
		}
		// verifica se o id da medição tipo é diferente de nula
		if (retorno[2] != null) {
			idMedicaoTipo = (Integer) retorno[2];
		}

		Object[] faixaInicialFinal = pesquisarFaixaEsperadaOuFalsa(imovel,
				dadosHidrometro, leituraAnterior, medicaoHistorico,
				idMedicaoTipo, sistemaParametro, false, numeroDigitosHidrometro);

		StringBuilder faixaInicialFinalString = (StringBuilder) faixaInicialFinal[0];

		arquivoTxtLinha.append(faixaInicialFinalString);

		Integer numeroConsumoFaturadoMes = null;

		try {

			// 1.2.2.9 - Consumo 04
			int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMesCorrente, 4);

			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovel.getId(), anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);

			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
						+ numeroConsumoFaturadoMes));
			} else {
				arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(
						"", 4));
			}

			// 1.2.2.10 - Consumo 05
			anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMesCorrente, 5);
			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovel.getId(), anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);

			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
						+ numeroConsumoFaturadoMes));
			} else {
				arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(
						"", 4));
			}

			// 1.2.2.11 - Consumo 06
			anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMesCorrente, 6);
			numeroConsumoFaturadoMes = repositorioMicromedicao
					.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
							imovel.getId(), anoMesSubtraido,
							LigacaoTipo.LIGACAO_AGUA);

			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
						+ numeroConsumoFaturadoMes));
			} else {
				arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(
						"", 4));
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		// 1.2.2.12 - Consumo Médio
		Integer numeroConsumoMedio = null;
		try {
			numeroConsumoMedio = this.repositorioMicromedicao
					.pesquisarNumeroConsumoMedioImovel(imovel.getId(),
							anoMesCorrente, LigacaoTipo.LIGACAO_AGUA);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (numeroConsumoMedio != null) {
			arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(""
					+ numeroConsumoMedio, 4));
		} else {
			arquivoTxtLinha
					.append(Util.completaStringComEspacoAEsquerda("", 4));
		}
		
		//feito por Flávio Cordeiro - 07/03/2008 pedido por Claudio Lira
//		 1.2.2.2 - Hidrômetro
		if (ligacaoPoco && !ligacaoAgua) {

			String numeroHidrometroFinal = imovel
					.getHidrometroInstalacaoHistorico().getHidrometro()
					.getNumero().trim();

			if (numeroHidrometroFinal.length() > 10) {

				numeroHidrometroFinal = numeroHidrometroFinal.substring(
						numeroHidrometroFinal.length() - 10,
						numeroHidrometroFinal.length());

				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(10,
						numeroHidrometroFinal));
			} else {

				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(10,
						numeroHidrometroFinal));
			}

		} else {

			String numeroHidrometroFinal = imovel.getLigacaoAgua()
					.getHidrometroInstalacaoHistorico().getHidrometro()
					.getNumero().trim();

			if (numeroHidrometroFinal.length() > 10) {

				numeroHidrometroFinal = numeroHidrometroFinal.substring(
						numeroHidrometroFinal.length() - 10,
						numeroHidrometroFinal.length());

				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(10,
						numeroHidrometroFinal));
			} else {

				arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(10,
						numeroHidrometroFinal));
			}
		}

		arquivoTxtLinha.append(System.getProperty("line.separator"));

		return arquivoTxtLinha;
	}
<<<<<<< ControladorMicromedicaoCAERNSEJB.java
*/
	
	// ----------------------novosss-----------------------------------
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT - Registro 1
	 * 
	 * @author Romulo Aurelio
	 * @date 15/07/2008
	 * 
	 * @param movimentoRoteiroEmpresa,
	 *            anoMesFaturamento
	 * @throws ControladorException
	 */
	protected StringBuilder gerarDadosLeituraConvencionalRegistroUM(
			MovimentoRoteiroEmpresa movimentoRoteiroEmpresa,
			int anoMesFaturamento) throws ControladorException {

		StringBuilder arquivoTxtLinha = new StringBuilder();

		// 1.2.1 - Tipo de Registro 1 (UM)
		arquivoTxtLinha.append("1");

		// 1.2.1.1 - Nome

		arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa
				.getNomeCliente(), 40));

		// 1.2.1.2 - Situação (RETIRAR - DEIXAR ESPAÇOS - COM TAMANHO = 06)
		arquivoTxtLinha.append(completaString("", 6));

		// 1.2.1.3
		// Categoria
		if (movimentoRoteiroEmpresa.getCategoriaPrincipal() != null) {

			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(1,
					movimentoRoteiroEmpresa.getCategoriaPrincipal().getId() + ""));
		} else {
			arquivoTxtLinha.append(Util.completaString("", 1));
		}
		// Subcategoria1

		if (movimentoRoteiroEmpresa.getCodigoSubcategoria1() != null && movimentoRoteiroEmpresa.getCodigoSubcategoria1() != 0) {
			arquivoTxtLinha.append(movimentoRoteiroEmpresa.getCodigoSubcategoria1().toString().substring(1,3));
		}else if(movimentoRoteiroEmpresa.getCodigoSubcategoria1() == 0){ 
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(2,"0"));
		}else {
			arquivoTxtLinha.append(Util.completaString("", 2));
		}
		// qtdeEconomia2
		if (movimentoRoteiroEmpresa.getQuantidadeEconomias() != null) {

			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3,
					movimentoRoteiroEmpresa.getQuantidadeEconomias() + ""));
		} else {
			arquivoTxtLinha.append(Util.completaString("", 3));
		}

		// Categoria2
		if (movimentoRoteiroEmpresa.getCategoria2() != null) {

			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(1,
					movimentoRoteiroEmpresa.getCategoria2().getId() + ""));
		} else {
			arquivoTxtLinha.append(Util.completaString("", 1));
		}
		// Subcategoria2

		if (movimentoRoteiroEmpresa.getCodigoSubcategoria2() != null) {

			arquivoTxtLinha.append(movimentoRoteiroEmpresa.getCodigoSubcategoria2().toString().substring(1,3));
		} else {
			arquivoTxtLinha.append(Util.completaString("", 2));
		}

		// qtdeEconomia2
		if (movimentoRoteiroEmpresa.getQuantidadeEconomias2() != null) {

			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(3,
					movimentoRoteiroEmpresa.getQuantidadeEconomias2() + ""));
		} else {
			arquivoTxtLinha.append(Util.completaString("", 3));
		}

		// NumeroConsumoFaturadoMenos1Mes
		if (movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos1Mes() != null) {
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
					+ movimentoRoteiroEmpresa
							.getNumeroConsumoFaturadoMenos1Mes()));
		} else {
			arquivoTxtLinha
					.append(Util.completaStringComEspacoAEsquerda("", 4));
		}
		// NumeroConsumoFaturadoMenos2Mes
		if (movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos2Meses() != null) {
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
					+ movimentoRoteiroEmpresa
							.getNumeroConsumoFaturadoMenos2Meses()));
		} else {
			arquivoTxtLinha
					.append(Util.completaStringComEspacoAEsquerda("", 4));
		}
		// NumeroConsumoFaturadoMenos3Mes
		if (movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos3Meses() != null) {
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
					+ movimentoRoteiroEmpresa
							.getNumeroConsumoFaturadoMenos3Meses()));
		} else {
			arquivoTxtLinha
					.append(Util.completaStringComEspacoAEsquerda("", 4));
		}

		// 1.2.1.5 - Condição

		if (movimentoRoteiroEmpresa.getDescricaoConsumoTipo() != null) {
			arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerdaTruncandoAoTamanhoMaximoInformado(""
					+ movimentoRoteiroEmpresa.getDescricaoConsumoTipo(), 8));
		} else {
			arquivoTxtLinha
					.append(Util.completaStringComEspacoAEsquerda("", 8));
		}

		// Retirado por Flávio Cordeiro - 06/03/2008 - pedido por Claudio Lira
		// 1.2.1.6 - Número do Lacre
		// if (imovel.getLigacaoAgua().getNumeroLacre() != null) {
		// arquivoTxtLinha.append(completaString(imovel.getLigacaoAgua()
		// .getNumeroLacre(), 6));
		// } else {
		// arquivoTxtLinha.append(completaString("", 6));
		// }

		// 1.2.1.7 - Matrícula do Imóvel
		/*
		 * arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(imovel
		 * .getId().toString(), 9));
		 */

		// 1.2.1.7 - Matrícula do Imóvel
		arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(
				movimentoRoteiroEmpresa.getImovel().getId().toString(), 9));

		arquivoTxtLinha.append(System.getProperty("line.separator"));

		return arquivoTxtLinha;

	}
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT - Registro 2
	 * 
	 * @author Rafael Francisco Pinto, Raphael Rossiter
	 * @date 22/01/2007, 28/03/2007
	 * 
	 * @param imovel,
	 *            anoMesCorrente, sistemaParametro
	 * @throws ControladorException
	 */
	protected StringBuilder gerarDadosLeituraConvencionalRegistroDOIS(
			MovimentoRoteiroEmpresa movimentoRoteiroEmpresa, Integer anoMesCorrente,
			SistemaParametro sistemaParametro) throws ControladorException {

		StringBuilder arquivoTxtLinha = new StringBuilder();

		// Dados do Hidrometro
		StringBuilder dadosHidrometro = new StringBuilder();


		String dataInstalacaohidrometro = Util
		.formatarDataAAAAMMDD(movimentoRoteiroEmpresa
				.getDataInstalacaoHidrometro());
		
		dadosHidrometro.append(Util.adicionarZerosEsquedaNumeroTruncando(1, ""
				+ movimentoRoteiroEmpresa.getHidrometroMarca().getCodigoHidrometroMarca()
						.toString())
				+ completaString(""
						+ movimentoRoteiroEmpresa.getNumeroHidrometro(), 10)
				+ Util.adicionarZerosEsquedaNumeroTruncando(1,
						movimentoRoteiroEmpresa.getHidrometroCapacidade()
								.getId().toString())
				+ Util.adicionarZerosEsquedaNumeroTruncando(1,
						movimentoRoteiroEmpresa.getHidrometroLocalInstalacao()
								.getId().toString())
				+ dataInstalacaohidrometro
				+ Util.adicionarZerosEsquedaNumeroTruncando(1,
						movimentoRoteiroEmpresa.getHidrometroProtecao().getId()
								.toString()));

		

		// 1.2.2 - Tipo de Registro 2 (DOIS)
		arquivoTxtLinha.append("2");

		// 1.2.2.1 - Endereço
		if (movimentoRoteiroEmpresa.getEnderecoImovel() != null && !movimentoRoteiroEmpresa.getEnderecoImovel().equals("")) {
			// endereço do imóvel
			arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa.getEnderecoImovel(), 60));
		} else {
			arquivoTxtLinha.append(completaString("", 60));
		}

		// 1.2.2.3 - Inscrição do Imovel
		// 1.2.2.3.1 - Quadra
		// 1.2.2.3.2 - Lote
		// 1.2.2.3.3 - SubLote
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(3, ""
				+ movimentoRoteiroEmpresa.getNumeroQuadra()));
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
				+ movimentoRoteiroEmpresa.getNumeroLoteImovel()));
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(3, ""
				+ movimentoRoteiroEmpresa.getNumeroSubloteImovel()));

		// 1.2.2.4 - Sequencial da Rota
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
				+ movimentoRoteiroEmpresa.getNumeroSequencialRota()));

		if(movimentoRoteiroEmpresa.
				getNumeroFaixaLeituraEsperadaInicial()!=null){
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(6,""+movimentoRoteiroEmpresa.
				getNumeroFaixaLeituraEsperadaInicial()));
		}else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(6,""));
		}
		if(movimentoRoteiroEmpresa.
				getNumeroFaixaLeituraEsperadaFinal()!=null){
		arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(6,""+movimentoRoteiroEmpresa.
				getNumeroFaixaLeituraEsperadaFinal()));
		}else{
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(6,""));
		}
		
		//		 1.2.2.9 - Consumo 04
		if (movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos4Meses() != null) {
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
					+ movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos4Meses()));
		} else {
			arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(
					"", 4));
		}
		
//		 1.2.2.10 - Consumo 05
		
		if (movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos5Meses() != null) {
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
					+ movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos5Meses()));
		} else {
			arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(
					"", 4));
		}
		
		
//		 1.2.2.10 - Consumo 06
		
		if (movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos6Meses() != null) {
			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumero(4, ""
					+ movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos6Meses()));
		} else {
			arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(
					"", 4));
		}
		
		// 1.2.2.12 - Consumo Médio

		if (movimentoRoteiroEmpresa.getNumeroConsumoMedio() != null) {
			arquivoTxtLinha.append(Util.completaStringComEspacoAEsquerda(""
					+ movimentoRoteiroEmpresa.getNumeroConsumoMedio(), 4));
		} else {
			arquivoTxtLinha
					.append(Util.completaStringComEspacoAEsquerda("", 4));
		}
		
		if (movimentoRoteiroEmpresa.getNumeroHidrometro() != null) {

			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(10,
					movimentoRoteiroEmpresa.getNumeroHidrometro()));
		} else {

			arquivoTxtLinha.append(Util.adicionarZerosEsquedaNumeroTruncando(10,
					""));
		}

		arquivoTxtLinha.append(System.getProperty("line.separator"));

		return arquivoTxtLinha;
	}
	
	
	/**
	 * Author: Rômulo Aurélio [UC0083] - Gerar Dados para Leitura
	 * 
	 * @param objetosImoveis
	 * @return
	 */

	public Collection verificarImoveisParaSerGerados(Collection objetosImoveis) {

		// Instancia uma coleção que será usada para gerar o arquivo
		// txt.
		Collection<Imovel> imoveisParaSerGerados = new ArrayList();

		Iterator imovelIterator = objetosImoveis.iterator();
		while (imovelIterator.hasNext()) {
			// Recupera o imovel da coleção
			Imovel imovel = (Imovel) imovelIterator.next();
			// variavel responsável para entrar em uma das 4 condicões
			// abaixo

			/*
			 * if (sistemaParametro.getNomeAbreviadoEmpresa().toUpperCase()
			 * .equals("COMPESA")) {
			 */

			boolean achouImovel = false;

			/**
			 * [SF0002] - Verificar situação especial de faturamento Autor:
			 * Sávio Luiz Data: 21/12/2005
			 */
			// caso no imovel o faturamento situação grupo seja
			// diferente de
			// nulo e igual a leitura
			// não realizada então não seleciona o imovel caso contrario
			// seleciona.
			if (imovel.getFaturamentoSituacaoTipo() == null
					|| !imovel
							.getFaturamentoSituacaoTipo()
							.equals(
									FaturamentoSituacaoTipo.INDICADOR_PARALIZACAO_LEITURA_NAO_REALIZADA)) {
				
				if (!achouImovel) {
					// Verifica se a situação da ligação agua é
					// diferente de
					// nulo
					// Se for verifica se está ligado ou cortado
					/*if (imovel.getLigacaoAguaSituacao() != null
							&& imovel.getLigacaoAguaSituacao()
									.getIndicadorFaturamentoSituacao() != null
							&& (imovel.getLigacaoAguaSituacao()
									.getIndicadorFaturamentoSituacao()
									.equals(LigacaoAguaSituacao.FATURAMENTO_ATIVO))) {*/
						// Se for ligado ou cortado então
						// Verifica se a ligação agua é diferente de
						// nulo
						// se for verifica se o id da ligação agua é
						// igual
						// ao id
						// do
						// imovel e
						// se o id do histórico da instalação do
						// hidrometro
						// é
						// diferente de
						// null

						if (imovel.getLigacaoAgua() != null
								&& imovel.getLigacaoAgua().getId() != null
								&& (imovel.getLigacaoAgua().getId().equals(
										imovel.getId())
										&& imovel
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico() != null && imovel
										.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico()
										.getId() != null)) {

							imoveisParaSerGerados.add(imovel);
							achouImovel = true;

						}
					//}
				}
				if (!achouImovel) {
					// Verifica se a situação da ligação esgoto é
					// diferente
					// de nulo
					// Se for verifica se está ligado
					if (imovel.getLigacaoEsgotoSituacao() != null
							&& imovel.getLigacaoEsgotoSituacao()
									.getIndicadorFaturamentoSituacao() != null
							&& (imovel.getLigacaoEsgotoSituacao()
									.getIndicadorFaturamentoSituacao()
									.equals(LigacaoEsgotoSituacao.FATURAMENTO_ATIVO))) {
						// Verifica se o id do hidrometro historico é
						// diferente de
						// nulo na tabela imovel
						if (imovel.getHidrometroInstalacaoHistorico() != null
								&& imovel.getHidrometroInstalacaoHistorico()
										.getId() != null) {
							imoveisParaSerGerados.add(imovel);
							achouImovel = true;
						}
					}
				}
				if (!achouImovel) {
					// Verifica se a situação da ligação agua é
					// diferente de
					// nulo
					// Se for verifica se está suprimido
					if (imovel.getLigacaoAguaSituacao() != null
							&& imovel.getLigacaoAguaSituacao().getId() != null
							&& imovel.getLigacaoAguaSituacao().getId().equals(
									LigacaoAguaSituacao.SUPRIMIDO)) {
						// verifica se o indicador de fiscalização
						// suprimido
						// é
						// diferente de nulo
						// se for verifica se está ativo
						if (imovel.getQuadra().getRota()
								.getIndicadorFiscalizarSuprimido() != null
								&& imovel.getQuadra().getRota()
										.getIndicadorFiscalizarSuprimido()
										.equals(Rota.INDICADOR_SUPRIMIDO_ATIVO)) {
							imoveisParaSerGerados.add(imovel);
							achouImovel = true;
						}

					}
				}
				if (!achouImovel) {
					// Verifica se a situação da ligação agua é
					// diferente de
					// nulo
					// Se for verifica se está cortado
					if ((imovel.getLigacaoAguaSituacao() != null && imovel
							.getLigacaoAguaSituacao().getId() != null)
							&& (imovel.getLigacaoAguaSituacao().getId()
									.equals(LigacaoAguaSituacao.CORTADO))) {

						// Se for cortado então
						// Verifica se a ligação agua é diferente de
						// nulo
						// se for verifica se o id da ligação agua é
						// igual
						// ao id do
						// imovel e
						// se o id do histórico da instalação do
						// hidrometro
						// é null
						if (imovel.getLigacaoAgua() != null
								&& imovel.getLigacaoAgua().getId() != null
								&& (imovel.getLigacaoAgua().getId().equals(
										imovel.getId()) && (imovel
										.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() == null || imovel
										.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico()
										.getId() == null))) {
							// verifica se o indicador de fiscalização
							// cortado é
							// diferente de nulo
							// se for verifica se está ativo
							if (imovel.getQuadra().getRota()
									.getIndicadorFiscalizarCortado() != null
									&& imovel
											.getQuadra()
											.getRota()
											.getIndicadorFiscalizarCortado()
											.equals(
													Rota.INDICADOR_CORTADO_ATIVO)) {
								imoveisParaSerGerados.add(imovel);
								achouImovel = true;
							}
						}

					}
				}

			}

		}

		return imoveisParaSerGerados;

	}
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Rômulo Aurélio
	 * @date 07/07/2008
	 */
	public Collection retornaImoveisPorRota(Rota rota,
			SistemaParametro sistemaParametro) throws ControladorException {

		

		// inicializa uma coleção de imoveis
		Collection objetosImoveis = new ArrayList();

		// cria uma coleção de imóvel por rota
		Collection imoveisPorRota = null;
		
		

			try {

				imoveisPorRota = repositorioMicromedicao
						.pesquisarImoveisPorRotaCAERN(rota, 
								sistemaParametro.getNomeAbreviadoEmpresa());

			} catch (ErroRepositorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (imoveisPorRota != null && !imoveisPorRota.isEmpty()) {

				

				Iterator imovelporRotaIterator = imoveisPorRota.iterator();
				while (imovelporRotaIterator.hasNext()) {
					// cria um array de objetos para pegar os parametros
					// de
					// retorno da pesquisa
					Object[] arrayImoveisPorRota = (Object[]) imovelporRotaIterator
							.next();

					// instancia um imóvel
					Imovel imovel = new Imovel();
					if (arrayImoveisPorRota[0] != null) {
						// seta o id no imovel
						imovel.setId((Integer) arrayImoveisPorRota[0]);
					}

					if (arrayImoveisPorRota[1] != null) {
						// instancia uma localidade para ser setado no
						// imóvel
						Localidade localidade = new Localidade();
						localidade.setId((Integer) arrayImoveisPorRota[1]);
						imovel.setLocalidade(localidade);
					}

					if (arrayImoveisPorRota[2] != null) {
						// instancia um setor comercial para ser setado
						// no
						// imóvel
						SetorComercial setorComercial = new SetorComercial();
						setorComercial.setCodigo(Integer
								.parseInt(arrayImoveisPorRota[2].toString()));
						imovel.setSetorComercial(setorComercial);
					}
					Quadra quadra = new Quadra();
					if (arrayImoveisPorRota[3] != null) {
						// instancia uma quadra para ser setado no
						// imóvel

						Integer numeroQuadra = (Integer) arrayImoveisPorRota[3];
						quadra.setNumeroQuadra(numeroQuadra);
						imovel.setQuadra(quadra);
					}

					if (arrayImoveisPorRota[4] != null) {
						// seta o lote no imóvel
						imovel.setLote(Short.parseShort(arrayImoveisPorRota[4]
								.toString()));
					}

					if (arrayImoveisPorRota[5] != null) {
						// seta o lote no imóvel
						imovel.setSubLote(Short
								.parseShort(arrayImoveisPorRota[5].toString()));
					}
					if (arrayImoveisPorRota[6] != null) {
						// instancia uma imovel perfil para ser setado
						// no
						// imóvel
						ImovelPerfil imovelPerfil = new ImovelPerfil();
						imovelPerfil.setId((Integer) arrayImoveisPorRota[6]);
						imovel.setImovelPerfil(imovelPerfil);
					}

					LigacaoAgua ligacaoAgua = new LigacaoAgua();
					if (arrayImoveisPorRota[7] != null) {
						// instancia uma ligação agua para ser setado no
						// imóvel

						ligacaoAgua.setId((Integer) arrayImoveisPorRota[7]);
					}
					// instancia um hidrometro instalação historico para
					// ser
					// colocado na ligacao agua

					if (arrayImoveisPorRota[30] != null) {

						HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoLigacaoAgua = (HidrometroInstalacaoHistorico) arrayImoveisPorRota[30];
						MedicaoTipo medicaoTipo = new MedicaoTipo();
						medicaoTipo.setId((Integer) arrayImoveisPorRota[26]);
						hidrometroInstalacaoHistoricoLigacaoAgua
								.setMedicaoTipo(medicaoTipo);
						ligacaoAgua
								.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoLigacaoAgua);

					}
					imovel.setLigacaoAgua(ligacaoAgua);

					// //instancia um hidrometro instalação historico
					// para
					// ser colocado no imovel

					if (arrayImoveisPorRota[31] != null) {

						HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoImovel = (HidrometroInstalacaoHistorico) arrayImoveisPorRota[31];
						MedicaoTipo medicaoTipo = new MedicaoTipo();
						medicaoTipo.setId((Integer) arrayImoveisPorRota[27]);
						hidrometroInstalacaoHistoricoImovel
								.setMedicaoTipo(medicaoTipo);
						imovel
								.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoImovel);

					}
					// instancia a rota
					Rota rotaImovel = new Rota();

					if (arrayImoveisPorRota[10] != null) {
						// seta o id da rota
						rotaImovel.setId((Integer) arrayImoveisPorRota[10]);
					}
					if (arrayImoveisPorRota[11] != null) {
						// seta o indicador fiscalizador suprimido na
						// rota
						rotaImovel
								.setIndicadorFiscalizarSuprimido(Short
										.parseShort(arrayImoveisPorRota[11]
												.toString()));
					}
					if (arrayImoveisPorRota[12] != null) {
						// seta o indicador fiscalizador cortado na rota
						rotaImovel
								.setIndicadorFiscalizarCortado(Short
										.parseShort(arrayImoveisPorRota[12]
												.toString()));
					}
					if (arrayImoveisPorRota[13] != null) {
						// seta o indicador gerar fiscalizacao na rota
						rotaImovel
								.setIndicadorGerarFiscalizacao(Short
										.parseShort(arrayImoveisPorRota[13]
												.toString()));
					}
					if (arrayImoveisPorRota[14] != null) {
						// seta o indicador fgerar falsa faixa na rota
						rotaImovel
								.setIndicadorGerarFalsaFaixa(Short
										.parseShort(arrayImoveisPorRota[14]
												.toString()));
					}
					if (arrayImoveisPorRota[15] != null) {
						// seta o percentual geracao fiscalizacao na
						// rota
						rotaImovel
								.setPercentualGeracaoFiscalizacao((BigDecimal) (arrayImoveisPorRota[15]));
					}
					if (arrayImoveisPorRota[16] != null) {
						// seta o percentual geracao faixa falsa na rota
						rotaImovel
								.setPercentualGeracaoFaixaFalsa((BigDecimal) (arrayImoveisPorRota[16]));
					}
					
					if(rota.getLeiturista() != null ){
						rotaImovel.setLeiturista(rota.getLeiturista());
					}
					
					// instancia a empresa
					Empresa empresa = new Empresa();
					if (arrayImoveisPorRota[17] != null) {

						// seta o id na empresa
						empresa.setId((Integer) arrayImoveisPorRota[17]);

					}
					if (arrayImoveisPorRota[18] != null) {

						// seta a descrição abreviada na empresa
						empresa.setDescricaoAbreviada(arrayImoveisPorRota[18]
								.toString());

					}
					if (arrayImoveisPorRota[19] != null) {

						// seta email da empresa
						empresa.setEmail(arrayImoveisPorRota[19].toString());

					}
					if (arrayImoveisPorRota[28] != null) {

						// seta email da empresa
						empresa
								.setDescricao(arrayImoveisPorRota[28]
										.toString());

					}
					// seta a empresa na rota
					rotaImovel.setEmpresa(empresa);
					// instancia o faturamento
					FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
					if (arrayImoveisPorRota[20] != null) {
						// seta o id no faturamentGrupo
						faturamentoGrupo
								.setId((Integer) arrayImoveisPorRota[20]);

					}
					if (arrayImoveisPorRota[21] != null) {
						// seta o descrição no faturamentGrupo
						faturamentoGrupo
								.setDescricao((String) arrayImoveisPorRota[21]);
					}
					// seta o faturamento na rota
					rotaImovel.setFaturamentoGrupo(faturamentoGrupo);
					if (arrayImoveisPorRota[22] != null) {
						// instancia a ligação esgoto situação
						LeituraTipo leituraTipo = new LeituraTipo();
						// seta o id na ligação esgoto situação
						leituraTipo.setId((Integer) arrayImoveisPorRota[22]);
						// seta a ligação esgoto situação no imovel
						rotaImovel.setLeituraTipo(leituraTipo);
					}

					// seta a rota na quadra
					quadra.setRota(rotaImovel);

					// seta o roteiro empresa na quadra
					quadra
							.setRoteiroEmpresa((RoteiroEmpresa) arrayImoveisPorRota[29]);

					// seta a quadra no imovel
					imovel.setQuadra(quadra);
					if (arrayImoveisPorRota[23] != null) {
						// instancia a ligação agua situação
						LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
						// seta o id na ligação agua situação
						ligacaoAguaSituacao
								.setId((Integer) arrayImoveisPorRota[23]);
						// seta a ligação agua situação no imovel
						imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
					}
					if (arrayImoveisPorRota[24] != null) {
						// instancia a ligação esgoto situação
						LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
						// seta o id na ligação esgoto situação
						ligacaoEsgotoSituacao
								.setId((Integer) arrayImoveisPorRota[24]);
						// seta a ligação esgoto situação no imovel
						imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
					}

					if (arrayImoveisPorRota[25] != null) {
						// instancia o faturamento situacao tipo
						FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
						// seta o id no faturamento situacao tipo
						faturamentoSituacaoTipo
								.setIndicadorParalisacaoLeitura((Short) arrayImoveisPorRota[25]);
						// seta a ligação esgoto situação no imovel
						imovel
								.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
					}

					if (arrayImoveisPorRota[32] != null) {
						imovel.getQuadra().getRota().getEmpresa().setId(
								(Integer) arrayImoveisPorRota[32]);
					}

					if (arrayImoveisPorRota[33] != null) {
						imovel.getLigacaoAguaSituacao()
								.setIndicadorFaturamentoSituacao(
										(Short) arrayImoveisPorRota[33]);
					}

					if (arrayImoveisPorRota[34] != null) {
						imovel.getLigacaoEsgotoSituacao()
								.setIndicadorFaturamentoSituacao(
										(Short) arrayImoveisPorRota[34]);
					}

					if (arrayImoveisPorRota[35] != null) {

						LogradouroBairro logradouroBairro = new LogradouroBairro();

						logradouroBairro
								.setId((Integer) arrayImoveisPorRota[35]);

						imovel.setLogradouroBairro(logradouroBairro);
					}

					if (arrayImoveisPorRota[36] != null) {

						Logradouro logradouro = new Logradouro();

						logradouro.setId((Integer) arrayImoveisPorRota[36]);

						imovel.getLogradouroBairro().setLogradouro(logradouro);
					}

					if (arrayImoveisPorRota[37] != null) {

						imovel.getLogradouroBairro().getLogradouro().setNome(
								(String) arrayImoveisPorRota[37]);
					}

					if (arrayImoveisPorRota[38] != null) {
						Bairro bairro = new Bairro();
						bairro.setNome((String) arrayImoveisPorRota[38]);

						imovel.getLogradouroBairro().setBairro(bairro);
					}
					if (arrayImoveisPorRota[39] != null) {
						imovel.setNumeroImovel(completaString(
								(String) arrayImoveisPorRota[39], 5));
					}

					if (arrayImoveisPorRota[40] != null) {
						imovel
								.setNumeroSequencialRota((Integer) arrayImoveisPorRota[40]);
					}
					if (arrayImoveisPorRota[41] != null) {
						ligacaoAgua.setNumeroLacre(completaString(
								(String) arrayImoveisPorRota[41], 6));
					}
					if (arrayImoveisPorRota[42] != null) {
						imovel.setComplementoEndereco(
								(String) arrayImoveisPorRota[42]);
					}
					if (arrayImoveisPorRota[43] != null) {
						GerenciaRegional gerenciaRegional = new GerenciaRegional();
						gerenciaRegional
								.setId((Integer) arrayImoveisPorRota[43]);
						imovel.getLocalidade().setGerenciaRegional(
								gerenciaRegional);
					}
					if (arrayImoveisPorRota[44] != null) {

						rotaImovel.setCodigo((Short) arrayImoveisPorRota[44]);
					}

					// adiciona na coleção de imoveis
					objetosImoveis.add(imovel);
					arrayImoveisPorRota = null;

				}
			}
		
		imoveisPorRota.clear();
		imoveisPorRota = null;
		return objetosImoveis;
	}
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * @date 07/07/2008
	 * @author Rômulo Aurélio
	 * @param imoveisParaSerGerados
	 * @param anoMesCorrente
	 * @param sistemaParametro
	 * @param idLeituraTipo
	 * @throws ControladorException
	 */
	
	/*public void inserirDadosImoveisMovimentoRoteiroEmpresa(
			Collection imoveisParaSerGerados, int anoMesCorrente,
			SistemaParametro sistemaParametro, Integer idLeituraTipo)
			throws ControladorException {

		Collection colecaoMovimentoRoteiroEmpresa = new ArrayList();
		if (imoveisParaSerGerados != null && !imoveisParaSerGerados.isEmpty()) {
			// Ã© usado para na faixa falsa saber se o hidrometro foi
			// selecionado
			// ou
			// nÃ£o
			boolean hidrometroSelecionado = false;

			Integer quantidadeRegistros = 0;

			Integer quantidadeImoveis = 0;

			ListIterator imovelParaSerGeradoIterator = ((List) imoveisParaSerGerados)
					.listIterator(0);

			Imovel imovelParaSerGerado = null;
			// ListIterator imovelParaSerGeradoIterator = (ListIterator)
			// imoveisParaSerGerados
			// .iterator();

			while (imovelParaSerGeradoIterator.hasNext()) {
				boolean ligacaoAgua = false;
				boolean ligacaoPoco = false;

				imovelParaSerGerado = (Imovel) imovelParaSerGeradoIterator
						.next();

				// incrementa a quantidade de registros
				quantidadeRegistros = quantidadeRegistros + 1;

				quantidadeImoveis = quantidadeImoveis + 1;
				
				if (imovelParaSerGerado.getLigacaoAgua() != null
						&& imovelParaSerGerado.getLigacaoAgua().getId() != null
						&& imovelParaSerGerado.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico() != null
						&& imovelParaSerGerado.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico().getId() != null) {
					ligacaoAgua = true;
				}
				if (imovelParaSerGerado.getHidrometroInstalacaoHistorico() != null
						&& imovelParaSerGerado
								.getHidrometroInstalacaoHistorico().getId() != null) {
					ligacaoPoco = true;
				}

				MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = new MovimentoRoteiroEmpresa();

				LeituraTipo leituraTipo = new LeituraTipo();
				leituraTipo.setId(idLeituraTipo);

				movimentoRoteiroEmpresa.setLeituraTipo(leituraTipo);

				movimentoRoteiroEmpresa.setAnoMesMovimento(anoMesCorrente);

				if (imovelParaSerGerado.getQuadra() != null) {

					movimentoRoteiroEmpresa
							.setRoteiroEmpresa(imovelParaSerGerado.getQuadra()
									.getRoteiroEmpresa());
					movimentoRoteiroEmpresa.setNumeroQuadra(imovelParaSerGerado
							.getQuadra().getNumeroQuadra());

					if (imovelParaSerGerado.getQuadra().getRota() != null) {
						// id do grupo de faturamento
						movimentoRoteiroEmpresa
								.setFaturamentoGrupo(imovelParaSerGerado
										.getQuadra().getRota()
										.getFaturamentoGrupo());
					}

					if (imovelParaSerGerado.getQuadra().getRoteiroEmpresa() != null) {
						movimentoRoteiroEmpresa.setEmpresa(imovelParaSerGerado
								.getQuadra().getRoteiroEmpresa().getEmpresa());
					} else {
						movimentoRoteiroEmpresa.setEmpresa(imovelParaSerGerado
								.getQuadra().getRota().getEmpresa());
					}
				}

				movimentoRoteiroEmpresa.setLocalidade(imovelParaSerGerado
						.getLocalidade());

				movimentoRoteiroEmpresa
						.setCodigoSetorComercial(imovelParaSerGerado
								.getSetorComercial().getCodigo());

				movimentoRoteiroEmpresa.setNumeroLoteImovel(""
						+ imovelParaSerGerado.getLote());

				movimentoRoteiroEmpresa.setNumeroSubloteImovel(""
						+ imovelParaSerGerado.getSubLote());

				movimentoRoteiroEmpresa.setImovelPerfil(imovelParaSerGerado
						.getImovelPerfil());

				// caso seja tipo ligaÃ§Ã£o agua e poÃ§o cria a string
				// primeiro
				// com
				// tipo
				// ligaÃ§Ã£o agua
				if (ligacaoAgua && ligacaoPoco) {

					if (imovelParaSerGerado.getLigacaoAgua() != null
							&& imovelParaSerGerado.getLigacaoAgua()
									.getHidrometroInstalacaoHistorico() != null
							&& imovelParaSerGerado.getLigacaoAgua()
									.getHidrometroInstalacaoHistorico().getId() != null
							&& !imovelParaSerGerado.getLigacaoAgua()
									.getHidrometroInstalacaoHistorico().getId()
									.equals("")) {

						movimentoRoteiroEmpresa
								.setMedicaoTipo(imovelParaSerGerado
										.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico()
										.getMedicaoTipo());
					}
					// caso nÃ£o seja
				} else {
					// caso seja tipo ligaÃ§Ã£o agua cria a string com
					// tipo
					// ligaÃ§Ã£o agua
					if (ligacaoAgua) {
						if (imovelParaSerGerado.getLigacaoAgua() != null
								&& imovelParaSerGerado.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() != null
								&& imovelParaSerGerado.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico()
										.getId() != null
								&& !imovelParaSerGerado.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico()
										.getId().equals("")) {
							movimentoRoteiroEmpresa
									.setMedicaoTipo(imovelParaSerGerado
											.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico()
											.getMedicaoTipo());
						}
					} else {
						// caso seja tipo ligaÃ§Ã£o poÃ§o cria a string
						// com
						// tipo
						// ligaÃ§Ã£o poÃ§o
						if (ligacaoPoco) {
							if (imovelParaSerGerado
									.getHidrometroInstalacaoHistorico() != null
									&& imovelParaSerGerado
											.getHidrometroInstalacaoHistorico()
											.getId() != null
									&& !imovelParaSerGerado
											.getHidrometroInstalacaoHistorico()
											.getId().equals("")) {
								movimentoRoteiroEmpresa
										.setMedicaoTipo(imovelParaSerGerado
												.getHidrometroInstalacaoHistorico()
												.getMedicaoTipo());
							}
						}
					}
				}

				// Matricula do imÃ³vel
				movimentoRoteiroEmpresa.setImovel(imovelParaSerGerado);

				// Perfil do imovel
				movimentoRoteiroEmpresa.setImovelPerfil(imovelParaSerGerado
						.getImovelPerfil());

				String nomeClienteUsuario = null;
				try {
					// Pesquisa o nome do cliente que tem o tipo de
					// relaÃ§Ã£o
					// usuÃ¡rio.

					nomeClienteUsuario = repositorioClienteImovel
							.pesquisarNomeClientePorImovel(imovelParaSerGerado
									.getId());

				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

				// nome do cliente usuÃ¡rio
				movimentoRoteiroEmpresa.setNomeCliente(completaString(
						nomeClienteUsuario, 50));
				// Pesquisa o endereÃ§o do imovel passando o id
				String enderecoImovel = getControladorEndereco()
						.pesquisarEnderecoFormatado(imovelParaSerGerado.getId());

				if (enderecoImovel != null && !enderecoImovel.equals("")) {
					// endereÃ§o do imÃ³vel
					movimentoRoteiroEmpresa.setEnderecoImovel(completaString(
							enderecoImovel, 100));
				} else {
					movimentoRoteiroEmpresa.setEnderecoImovel(completaString(
							"", 100));
				}

				// Dados do Hidrometro

				// caso seja tipo ligaÃ§Ã£o agua e poÃ§o cria a string
				// primeiro
				// com
				// tipo
				// ligaÃ§Ã£o agua
				Short numeroDigitosHidrometro = null;
				StringBuilder dadosHidrometro = null;
				Integer capacidadeHidrometro = null;
				Integer marcaHidrometro = null;

				if (ligacaoAgua && ligacaoPoco) {

					Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoLigacaoAgua(imovelParaSerGerado);

					dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
					numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
					capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
					marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

					HidrometroCapacidade capacidade = new HidrometroCapacidade();
					capacidade.setId(capacidadeHidrometro);
					movimentoRoteiroEmpresa.setHidrometroCapacidade(capacidade);

					HidrometroMarca hidrometroMarca = new HidrometroMarca();
					hidrometroMarca.setId(marcaHidrometro);
					movimentoRoteiroEmpresa.setHidrometroMarca(hidrometroMarca);

					movimentoRoteiroEmpresa.setNumeroHidrometro(completaString(
							(String) dadosHidrometroNumeroLeitura[4], 10));
					// caso nÃ£o seja
				} else {
					// caso seja tipo ligaÃ§Ã£o agua cria a string com
					// tipo
					// ligaÃ§Ã£o agua
					if (ligacaoAgua) {

						Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoLigacaoAgua(imovelParaSerGerado);

						dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
						numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
						capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
						marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

						HidrometroCapacidade capacidade = new HidrometroCapacidade();
						capacidade.setId(capacidadeHidrometro);
						movimentoRoteiroEmpresa
								.setHidrometroCapacidade(capacidade);

						HidrometroMarca hidrometroMarca = new HidrometroMarca();
						hidrometroMarca.setId(marcaHidrometro);
						movimentoRoteiroEmpresa
								.setHidrometroMarca(hidrometroMarca);

						movimentoRoteiroEmpresa
								.setNumeroHidrometro(completaString(
										(String) dadosHidrometroNumeroLeitura[4],
										10));

						// caso nÃ£o seja
					} else {
						// caso seja tipo ligaÃ§Ã£o poÃ§o cria a string
						// com
						// tipo
						// ligaÃ§Ã£o poÃ§o
						if (ligacaoPoco) {

							Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovelParaSerGerado);

							dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
							numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
							capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
							marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

							HidrometroCapacidade capacidade = new HidrometroCapacidade();
							capacidade.setId(capacidadeHidrometro);
							movimentoRoteiroEmpresa
									.setHidrometroCapacidade(capacidade);

							HidrometroMarca hidrometroMarca = new HidrometroMarca();
							hidrometroMarca.setId(marcaHidrometro);
							movimentoRoteiroEmpresa
									.setHidrometroMarca(hidrometroMarca);

							movimentoRoteiroEmpresa
									.setNumeroHidrometro(completaString(
											(String) dadosHidrometroNumeroLeitura[4],
											10));

							// caso nÃ£o seja nem um nem outro entÃ£o
							// pode
							// chamar
							// qualquer um dos mÃ©todos
							// pois os dois fazem a verificaÃ§Ã£o e
							// retorna
							// strings
							// vazia e
							// a data cpm zeros
						} else {
							Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovelParaSerGerado);

							dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
							numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
							capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
							marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

							HidrometroCapacidade capacidade = new HidrometroCapacidade();
							capacidade.setId(capacidadeHidrometro);
							movimentoRoteiroEmpresa
									.setHidrometroCapacidade(capacidade);

							HidrometroMarca hidrometroMarca = new HidrometroMarca();
							hidrometroMarca.setId(marcaHidrometro);
							movimentoRoteiroEmpresa
									.setHidrometroMarca(hidrometroMarca);

							movimentoRoteiroEmpresa
									.setNumeroHidrometro(completaString(
											(String) dadosHidrometroNumeroLeitura[4],
											10));
						}
					}
				}

				if (imovelParaSerGerado.getHidrometroInstalacaoHistorico() != null) {

					movimentoRoteiroEmpresa
							.setHidrometroLocalInstalacao(imovelParaSerGerado
									.getHidrometroInstalacaoHistorico()
									.getHidrometroLocalInstalacao());

					movimentoRoteiroEmpresa
							.setDataInstalacaoHidrometro(imovelParaSerGerado
									.getHidrometroInstalacaoHistorico()
									.getDataInstalacao());

					movimentoRoteiroEmpresa
							.setHidrometroProtecao(imovelParaSerGerado
									.getHidrometroInstalacaoHistorico()
									.getHidrometroProtecao());

				}

				if (imovelParaSerGerado.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico() != null) {

					movimentoRoteiroEmpresa
							.setHidrometroLocalInstalacao(imovelParaSerGerado
									.getLigacaoAgua()
									.getHidrometroInstalacaoHistorico()
									.getHidrometroLocalInstalacao());

					movimentoRoteiroEmpresa
							.setDataInstalacaoHidrometro(imovelParaSerGerado
									.getLigacaoAgua()
									.getHidrometroInstalacaoHistorico()
									.getDataInstalacao());

					movimentoRoteiroEmpresa
							.setHidrometroProtecao(imovelParaSerGerado
									.getLigacaoAgua()
									.getHidrometroInstalacaoHistorico()
									.getHidrometroProtecao());

				}

				// id da ligacao agua situaÃ§Ã£o
				if (imovelParaSerGerado.getLigacaoAguaSituacao() != null
						&& imovelParaSerGerado.getLigacaoAguaSituacao().getId() != null) {
					// SituaÃ§Ã£o da ligaÃ§Ã£o de agua
					movimentoRoteiroEmpresa
							.setLigacaoAguaSituacao(imovelParaSerGerado
									.getLigacaoAguaSituacao());
				}

				// id da ligacao esgoto situaÃ§Ã£o
				if (imovelParaSerGerado.getLigacaoEsgotoSituacao() != null
						&& imovelParaSerGerado.getLigacaoEsgotoSituacao()
								.getId() != null) {
					// SituaÃ§Ã£o de ligaÃ§Ã£o esgoto
					movimentoRoteiroEmpresa
							.setLigacaoEsgotoSituacao(imovelParaSerGerado
									.getLigacaoEsgotoSituacao());
				}

				// pega as descriÃ§Ãµes das categorias do imovel

				Categoria categoria = getControladorImovel()
						.obterDescricoesCategoriaImovel(imovelParaSerGerado);

				// quantidade de economias
				movimentoRoteiroEmpresa
						.setDescricaoAbreviadaCategoriaImovel(categoria
								.getDescricaoAbreviada());

				// [UC0086 - Obter quantidade de economias]
				int quantidadeEconomias = getControladorImovel()
						.obterQuantidadeEconomias(imovelParaSerGerado);
				// quantidade de economias
				movimentoRoteiroEmpresa.setQuantidadeEconomias(new Integer(
						quantidadeEconomias).shortValue());

				// Leitura anterior
				Integer anoMesAnterior = Util.subtrairData(anoMesCorrente);
				String leituraAnterior = null;
				Integer idMedicaoTipo = null;
				MedicaoHistorico medicaoHistorico = null;

				Object[] retorno = pesquisaLeituraAnterior(ligacaoAgua,
						ligacaoPoco, anoMesAnterior, imovelParaSerGerado);

				// verifica se a leitura anterior Ã© diferente de
				// nula
				if (retorno[0] != null) {
					leituraAnterior = retorno[0].toString();
				}
				// verifica se a leitura situaÃ§Ã£o atual Ã© diferente
				// de
				// nula
				if (retorno[1] != null) {
					medicaoHistorico = (MedicaoHistorico) retorno[1];
				}
				// verifica se o id da mediÃ§Ã£o tipo Ã© diferente de
				// nula
				if (retorno[2] != null) {
					idMedicaoTipo = (Integer) retorno[2];
				}

				// verifica se a leitura anterior Ã© diferente de
				// nula
				// para
				// ser
				// jogado no arquivo
				// txt
				if (leituraAnterior != null) {
					movimentoRoteiroEmpresa
							.setNumeroLeituraAnterior(new Integer(
									leituraAnterior));
					// caso contrario coloca a string com zeros
				} else {
					movimentoRoteiroEmpresa
							.setNumeroLeituraAnterior(new Integer(0));
				}

				// Faixa de leitura esperada

				Object[] faixaInicialFinal = pesquisarFaixaEsperadaOuFalsaCelular(
						imovelParaSerGerado, dadosHidrometro, leituraAnterior,
						medicaoHistorico, idMedicaoTipo, sistemaParametro,
						hidrometroSelecionado, numeroDigitosHidrometro);

				hidrometroSelecionado = Boolean
						.parseBoolean(faixaInicialFinal[1].toString());

				boolean faixaFalsaLeitura = Boolean
						.parseBoolean(faixaInicialFinal[2].toString());

				int faixaInicialEsperada = 0;
				int faixaFinalEsperada = 0;

				if (!faixaFalsaLeitura) {
					faixaInicialEsperada = Integer
							.parseInt(faixaInicialFinal[3].toString());

					faixaFinalEsperada = Integer.parseInt(faixaInicialFinal[4]
							.toString());
				}

				movimentoRoteiroEmpresa
						.setNumeroFaixaLeituraEsperadaInicial(faixaInicialEsperada);
				movimentoRoteiroEmpresa
						.setNumeroFaixaLeituraEsperadaFinal(faixaFinalEsperada);

				movimentoRoteiroEmpresa.setUltimaAlteracao(new Date());

				movimentoRoteiroEmpresa.setRota(imovelParaSerGerado.getQuadra()
						.getRota());

				*//**
				 * Obtem a colecao economias por categoria
				 * 
				 *//*

				Collection colecaoSubCategoria = this.getControladorImovel()
						.obterQuantidadeEconomiasSubCategoria(
								imovelParaSerGerado.getId());

				// 1.10.1 - SubCategoria 01
				// 1.10.2 - Quantidade 01
				// 1.10.3 - SubCategoria 02
				// 1.10.4 - Quantidade 02
				if (colecaoSubCategoria != null
						&& !colecaoSubCategoria.isEmpty()) {

					Iterator itera = colecaoSubCategoria.iterator();

					for (int i = 0; i < 2; i++) {

						while (itera.hasNext()) {

							Subcategoria subcategoria = (Subcategoria) itera
									.next();

							// tipoEconomia = categoria_id(1
							// posiÃ§Ã£o) +
							// subcategoria_codigo(3 posiÃ§Ãµes)

							if (i == 0) {
								
								Categoria categoriaPrincipal = new Categoria();
								
								categoriaPrincipal.setId(subcategoria
												.getCategoria().getId());
								
								movimentoRoteiroEmpresa
										.setCategoriaPrincipal(categoriaPrincipal);
								// CÃ³digo da subcategoria do imovel
								movimentoRoteiroEmpresa
										.setCodigoSubcategoria1(subcategoria
												.getCodigo());
								// qtdeEconomia
								movimentoRoteiroEmpresa
										.setQuantidadeEconomias(subcategoria
												.getQuantidadeEconomias()
												.shortValue());
							} else {

								Categoria categoria2 = new Categoria();
								
								categoria2.setId(subcategoria
												.getCategoria().getId());
								
								movimentoRoteiroEmpresa
										.setCategoria2(categoria2);

								// CÃ³digo da 2 subcategoria do
								// imovel
								movimentoRoteiroEmpresa
										.setCodigoSubcategoria2(subcategoria
												.getCodigo());
								// qtdeEconomia
								movimentoRoteiroEmpresa
										.setQuantidadeEconomias2(subcategoria
												.getQuantidadeEconomias()
												.shortValue());
							}

						}
					}
				}
				// 1.11 - Consumo
				Integer numeroConsumoFaturadoMes = null;

				Integer anoMesFaturamento = sistemaParametro
						.getAnoMesFaturamento();

				try {

					// 1.11.1 - Consumo 01
					int anoMesSubtraido = Util.subtrairMesDoAnoMes(
							anoMesFaturamento, 6);

					numeroConsumoFaturadoMes = repositorioMicromedicao
							.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
									imovelParaSerGerado.getId(),
									anoMesSubtraido, LigacaoTipo.LIGACAO_AGUA);

					// caso o numero consumo faturado do mes for
					// diferente de nulo
					if (numeroConsumoFaturadoMes != null) {

						movimentoRoteiroEmpresa
								.setNumeroConsumoFaturadoMenos6Meses(numeroConsumoFaturadoMes);

					}

					// 1.11.2 - Consumo 02
					anoMesSubtraido = Util.subtrairMesDoAnoMes(
							anoMesFaturamento, 5);

					numeroConsumoFaturadoMes = repositorioMicromedicao
							.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
									imovelParaSerGerado.getId(),
									anoMesSubtraido, LigacaoTipo.LIGACAO_AGUA);

					// caso o numero consumo faturado do mes for
					// diferente de nulo
					if (numeroConsumoFaturadoMes != null) {

						movimentoRoteiroEmpresa
								.setNumeroConsumoFaturadoMenos5Meses(numeroConsumoFaturadoMes);

					}

					// 1.11.3 - Consumo 03
					anoMesSubtraido = Util.subtrairMesDoAnoMes(
							anoMesFaturamento, 4);

					numeroConsumoFaturadoMes = repositorioMicromedicao
							.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
									imovelParaSerGerado.getId(),
									anoMesSubtraido, LigacaoTipo.LIGACAO_AGUA);

					// caso o numero consumo faturado do mes for
					// diferente de nulo
					if (numeroConsumoFaturadoMes != null) {

						movimentoRoteiroEmpresa
								.setNumeroConsumoFaturadoMenos4Meses(numeroConsumoFaturadoMes);

					}

				} catch (ErroRepositorioException e) {

					throw new ControladorException("erro.sistema", e);

				}

				// 1.12 - CondiÃ§Ã£o
				String condicao = null;

				try {

					condicao = this.repositorioMicromedicao
							.obterDescricaoConsumoTipo(imovelParaSerGerado
									.getId(), LigacaoTipo.LIGACAO_AGUA);

				} catch (ErroRepositorioException e) {

					throw new ControladorException("erro.sistema", e);

				}

				if (condicao != null) {
					movimentoRoteiroEmpresa.setDescricaoConsumoTipo(""
							+ condicao);
				}

				// 1.13 - NÃºmero do Lacre
				if (imovelParaSerGerado.getLigacaoAgua().getNumeroLacre() != null) {
					movimentoRoteiroEmpresa.setNumeroLacreLigacaoAgua(Util
							.completaStringComEspacoAEsquerda(""
									+ imovelParaSerGerado.getLigacaoAgua()
											.getNumeroLacre(), 6));
				}

				// 1.14 - Sequencial da Rota

				movimentoRoteiroEmpresa
						.setNumeroSequencialRota(imovelParaSerGerado
								.getNumeroSequencialRota());

				// 1.15 - Consumo 04

				int anoMesSubtraido = Util.subtrairMesDoAnoMes(
						anoMesFaturamento, 3);

				try {
					numeroConsumoFaturadoMes = repositorioMicromedicao
							.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
									imovelParaSerGerado.getId(),
									anoMesSubtraido, LigacaoTipo.LIGACAO_AGUA);
				} catch (ErroRepositorioException e3) {
					e3.printStackTrace();
				}

				// caso o numero consumo faturado do mes for
				// diferente
				// de nulo
				if (numeroConsumoFaturadoMes != null) {

					movimentoRoteiroEmpresa
							.setNumeroConsumoFaturadoMenos3Meses(numeroConsumoFaturadoMes);

				}

				// 1.16 - Consumo 05
				anoMesSubtraido = Util
						.subtrairMesDoAnoMes(anoMesFaturamento, 2);

				try {
					numeroConsumoFaturadoMes = repositorioMicromedicao
							.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
									imovelParaSerGerado.getId(),
									anoMesSubtraido, LigacaoTipo.LIGACAO_AGUA);
				} catch (ErroRepositorioException e2) {
					e2.printStackTrace();
				}

				// caso o numero consumo faturado do mes for
				// diferente
				// de nulo
				if (numeroConsumoFaturadoMes != null) {

					movimentoRoteiroEmpresa
							.setNumeroConsumoFaturadoMenos2Meses(numeroConsumoFaturadoMes);

				}

				// 1.17 - Consumo 06
				anoMesSubtraido = Util
						.subtrairMesDoAnoMes(anoMesFaturamento, 1);

				try {
					numeroConsumoFaturadoMes = repositorioMicromedicao
							.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
									imovelParaSerGerado.getId(),
									anoMesSubtraido, LigacaoTipo.LIGACAO_AGUA);
				} catch (ErroRepositorioException e1) {
					e1.printStackTrace();
				}

				// caso o numero consumo faturado do mes for
				// diferente
				// de nulo
				if (numeroConsumoFaturadoMes != null) {

					movimentoRoteiroEmpresa
							.setNumeroConsumoFaturadoMenos1Mes(numeroConsumoFaturadoMes);

				}

				// 1.18 - Consumo Medio

				Integer numeroConsumoMedio = null;
				try {

					numeroConsumoMedio = this.repositorioMicromedicao
							.pesquisarNumeroConsumoMedioImovel(
									imovelParaSerGerado.getId(),
									anoMesCorrente, LigacaoTipo.LIGACAO_AGUA);

				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

				if (numeroConsumoMedio != null) {
					movimentoRoteiroEmpresa
							.setNumeroConsumoMedio(numeroConsumoMedio);
				}

				// 1.19 -Rota

				movimentoRoteiroEmpresa.setCodigoRota(imovelParaSerGerado
						.getQuadra().getRota().getCodigo());

				// 1.20 - Codigo Logradouro
				
				Logradouro logradouro = new Logradouro();
				logradouro.setId(imovelParaSerGerado
						.getLogradouroBairro().getLogradouro().getId());

				movimentoRoteiroEmpresa.setLogradouro(logradouro);

				// 1.21 - Nome do Logradouro

				movimentoRoteiroEmpresa.setNomeLogradouro(imovelParaSerGerado
						.getLogradouroBairro().getLogradouro().getNome());

				// 1.22 - Numero do Imovel

				movimentoRoteiroEmpresa.setNumeroImovel(imovelParaSerGerado
						.getNumeroImovel());

				// 1.23 - Complemento

				movimentoRoteiroEmpresa
						.setComplementoEndereco(imovelParaSerGerado
								.getComplementoEndereco());

				// 1.24 - Nome do Bairro

				movimentoRoteiroEmpresa.setNomeBairro(imovelParaSerGerado
						.getLogradouroBairro().getBairro().getNome());

				// 1.1 Incricao do Imovel
				movimentoRoteiroEmpresa.setInscricaoImovel(imovelParaSerGerado
						.getInscricaoFormatada());

				// Id gerencia Regional
				
				GerenciaRegional gerenciaRegional = new GerenciaRegional();
				
				gerenciaRegional.setId(imovelParaSerGerado
						.getLocalidade().getGerenciaRegional().getId());
				
				movimentoRoteiroEmpresa
						.setGerenciaRegional(gerenciaRegional);
				
				//LEITURISTA
				if(imovelParaSerGerado.getQuadra().getRota().getLeiturista() != null){
					movimentoRoteiroEmpresa.setLeiturista(imovelParaSerGerado.getQuadra().getRota().getLeiturista());
				}

				colecaoMovimentoRoteiroEmpresa.add(movimentoRoteiroEmpresa);
				
				
//				 Caso Imovel tenha Ligacao de Agua e de PoÃ§o
				if(ligacaoAgua && ligacaoPoco){
					
					MovimentoRoteiroEmpresa movimentoRoteiroEmpresaPoco = new MovimentoRoteiroEmpresa();
					
					movimentoRoteiroEmpresaPoco.setAnoMesMovimento(movimentoRoteiroEmpresa.getAnoMesMovimento());
					
					if(movimentoRoteiroEmpresa.getCategoria2()!=null){
						movimentoRoteiroEmpresaPoco.setCategoria2(movimentoRoteiroEmpresa.getCategoria2());
					}
					
					if(movimentoRoteiroEmpresa.getCategoriaPrincipal()!=null){
					movimentoRoteiroEmpresaPoco.setCategoriaPrincipal(movimentoRoteiroEmpresa.getCategoriaPrincipal());
					}
					
					movimentoRoteiroEmpresaPoco.setCodigoRota(movimentoRoteiroEmpresa.getCodigoRota());
					
					
					movimentoRoteiroEmpresaPoco.setCodigoSetorComercial(movimentoRoteiroEmpresa.getCodigoSetorComercial());
					
					
					if(movimentoRoteiroEmpresa.getCodigoSubcategoria1() != null ){
					
					movimentoRoteiroEmpresaPoco.setCodigoSubcategoria1(movimentoRoteiroEmpresa.getCodigoSubcategoria1());
					
					}
					if(movimentoRoteiroEmpresa.getCodigoSubcategoria2() != null ){
					movimentoRoteiroEmpresaPoco.setCodigoSubcategoria2(movimentoRoteiroEmpresa.getCodigoSubcategoria2());
					}
					
					if(movimentoRoteiroEmpresa.getComplementoEndereco() != null ){
						movimentoRoteiroEmpresaPoco.setComplementoEndereco(movimentoRoteiroEmpresa.getComplementoEndereco());
					}
					
					
					if(movimentoRoteiroEmpresa.getDataHoraProcessamento() != null ){
						movimentoRoteiroEmpresaPoco.setDataHoraProcessamento(movimentoRoteiroEmpresa.getDataHoraProcessamento());
					}
					
					if(movimentoRoteiroEmpresa.getDataInstalacaoHidrometro() != null ){
						movimentoRoteiroEmpresaPoco.setDataInstalacaoHidrometro(movimentoRoteiroEmpresa.getDataInstalacaoHidrometro());
					}
					
					if(movimentoRoteiroEmpresa.getDescricaoAbreviadaCategoriaImovel() != null ){
						movimentoRoteiroEmpresaPoco.setDescricaoAbreviadaCategoriaImovel(movimentoRoteiroEmpresa.getDescricaoAbreviadaCategoriaImovel());
					}
					
					
					if(movimentoRoteiroEmpresa.getDescricaoAbreviadaLogradouroTipo() != null ){
						movimentoRoteiroEmpresaPoco.setDescricaoAbreviadaLogradouroTipo(movimentoRoteiroEmpresa.getDescricaoAbreviadaLogradouroTipo());
					}
					
					if(movimentoRoteiroEmpresa.getDescricaoAbreviadaLogradouroTitulo() != null ){
						movimentoRoteiroEmpresaPoco.setDescricaoAbreviadaLogradouroTitulo(movimentoRoteiroEmpresa.getDescricaoAbreviadaLogradouroTitulo());
					}
					
					
					if(movimentoRoteiroEmpresa.getDescricaoConsumoTipo() != null ){
						movimentoRoteiroEmpresaPoco.setDescricaoConsumoTipo(movimentoRoteiroEmpresa.getDescricaoConsumoTipo());
					}
					
					if(movimentoRoteiroEmpresa.getEmpresa() != null ){
						movimentoRoteiroEmpresaPoco.setEmpresa(movimentoRoteiroEmpresa.getEmpresa());
					}
					
					if(movimentoRoteiroEmpresa.getEnderecoImovel() != null ){
						movimentoRoteiroEmpresaPoco.setEnderecoImovel(movimentoRoteiroEmpresa.getEnderecoImovel());
					}
					
					if(movimentoRoteiroEmpresa.getFaturamentoGrupo() != null ){
						movimentoRoteiroEmpresaPoco.setFaturamentoGrupo(movimentoRoteiroEmpresa.getFaturamentoGrupo());
					}
					
					if(movimentoRoteiroEmpresa.getGerenciaRegional() != null ){
						movimentoRoteiroEmpresaPoco.setGerenciaRegional(movimentoRoteiroEmpresa.getGerenciaRegional());
					}
					
					if(movimentoRoteiroEmpresa.getHidrometroProtecao() != null ){
						movimentoRoteiroEmpresaPoco.setHidrometroProtecao(movimentoRoteiroEmpresa.getHidrometroProtecao());
					}
					
					if(movimentoRoteiroEmpresa.getImovel() != null ){
						movimentoRoteiroEmpresaPoco.setImovel(movimentoRoteiroEmpresa.getImovel());
					}
					
					if(movimentoRoteiroEmpresa.getImovelPerfil() != null ){
						movimentoRoteiroEmpresaPoco.setImovelPerfil(movimentoRoteiroEmpresa.getImovelPerfil());
					}
					
					if(movimentoRoteiroEmpresa.getIndicadorAtualizacaoLeitura() != null ){
						movimentoRoteiroEmpresaPoco.setIndicadorAtualizacaoLeitura(movimentoRoteiroEmpresa.getIndicadorAtualizacaoLeitura());
					}
					if(movimentoRoteiroEmpresa.getIndicadorConfirmacaoLeitura() != null ){
						movimentoRoteiroEmpresaPoco.setIndicadorConfirmacaoLeitura(movimentoRoteiroEmpresa.getIndicadorConfirmacaoLeitura());
					}
					
					if(movimentoRoteiroEmpresa.getInscricaoImovel() != null ){
						movimentoRoteiroEmpresaPoco.setInscricaoImovel(movimentoRoteiroEmpresa.getInscricaoImovel());
					}
					
					
					
					if(movimentoRoteiroEmpresa.getLeituraAnormalidade() != null ){
						movimentoRoteiroEmpresaPoco.setLeituraAnormalidade(movimentoRoteiroEmpresa.getLeituraAnormalidade());
					}
					
					if(movimentoRoteiroEmpresa.getLeituraTipo() != null ){
						movimentoRoteiroEmpresaPoco.setLeituraTipo(movimentoRoteiroEmpresa.getLeituraTipo());
					}
					
					if(movimentoRoteiroEmpresa.getLigacaoAguaSituacao() != null ){
						movimentoRoteiroEmpresaPoco.setLigacaoAguaSituacao(movimentoRoteiroEmpresa.getLigacaoAguaSituacao());
					}
					
					if(movimentoRoteiroEmpresa.getLigacaoEsgotoSituacao() != null ){
						movimentoRoteiroEmpresaPoco.setLigacaoEsgotoSituacao(movimentoRoteiroEmpresa.getLigacaoEsgotoSituacao());
					}
					
					if(movimentoRoteiroEmpresa.getLocalidade() != null ){
						movimentoRoteiroEmpresaPoco.setLocalidade(movimentoRoteiroEmpresa.getLocalidade());
					}
					
					if(movimentoRoteiroEmpresa.getLogradouro() != null ){
						movimentoRoteiroEmpresaPoco.setLogradouro(movimentoRoteiroEmpresa.getLogradouro());
					}
					
					if(movimentoRoteiroEmpresa.getNomeBairro() != null ){
						movimentoRoteiroEmpresaPoco.setNomeBairro(movimentoRoteiroEmpresa.getNomeBairro());
					}
					
					if(movimentoRoteiroEmpresa.getNomeCliente() != null ){
						movimentoRoteiroEmpresaPoco.setNomeCliente(movimentoRoteiroEmpresa.getNomeCliente());
					}
					
					if(movimentoRoteiroEmpresa.getNomeLeiturista() != null ){
						movimentoRoteiroEmpresaPoco.setNomeLeiturista(movimentoRoteiroEmpresa.getNomeLeiturista());
					}

					if(movimentoRoteiroEmpresa.getLeiturista() != null ){
						movimentoRoteiroEmpresaPoco.setLeiturista(movimentoRoteiroEmpresa.getLeiturista());
					}
					
					if(movimentoRoteiroEmpresa.getNomeLogradouro() != null ){
						movimentoRoteiroEmpresaPoco.setNomeLogradouro(movimentoRoteiroEmpresa.getNomeLogradouro());
					}	
					
					if(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos1Mes() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroConsumoFaturadoMenos1Mes(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos1Mes());
					}	
					
					if(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos2Meses() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroConsumoFaturadoMenos2Meses(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos2Meses());
					}	
					if(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos3Meses() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroConsumoFaturadoMenos3Meses(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos3Meses());
					}	
					
					if(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos4Meses() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroConsumoFaturadoMenos4Meses(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos4Meses());
					}	
					
					if(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos5Meses() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroConsumoFaturadoMenos5Meses(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos5Meses());
					}	
					
					if(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos6Meses() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroConsumoFaturadoMenos6Meses(movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos6Meses());
					}	
					
					if(movimentoRoteiroEmpresa.getNumeroImovel() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroImovel(movimentoRoteiroEmpresa.getNumeroImovel());
					}
					
					if(movimentoRoteiroEmpresa.getNumeroLacreLigacaoAgua() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroLacreLigacaoAgua(movimentoRoteiroEmpresa.getNumeroLacreLigacaoAgua());
					}
					
					if(movimentoRoteiroEmpresa.getNumeroLeituraAnterior() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroLeituraAnterior(movimentoRoteiroEmpresa.getNumeroLeituraAnterior());
					}
					
					if(movimentoRoteiroEmpresa.getNumeroLoteImovel() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroLoteImovel(movimentoRoteiroEmpresa.getNumeroLoteImovel());
					}
					
					if(movimentoRoteiroEmpresa.getNumeroQuadra() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroQuadra(movimentoRoteiroEmpresa.getNumeroQuadra());
					}
					
					if(movimentoRoteiroEmpresa.getNumeroSequencialRota() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroSequencialRota(movimentoRoteiroEmpresa.getNumeroSequencialRota());
						
					}
					
					if(movimentoRoteiroEmpresa.getNumeroSubloteImovel() != null ){
						movimentoRoteiroEmpresaPoco.setNumeroSubloteImovel(movimentoRoteiroEmpresa.getNumeroSubloteImovel());
						
					}
					

					if(movimentoRoteiroEmpresa.getQuantidadeEconomias() != null ){
						movimentoRoteiroEmpresaPoco.setQuantidadeEconomias(movimentoRoteiroEmpresa.getQuantidadeEconomias());
						
					}
					

					if(movimentoRoteiroEmpresa.getQuantidadeEconomias2() != null ){
						movimentoRoteiroEmpresaPoco.setQuantidadeEconomias2(movimentoRoteiroEmpresa.getQuantidadeEconomias2());
						
					}
					
					if(movimentoRoteiroEmpresa.getRota() != null ){
						movimentoRoteiroEmpresaPoco.setRota(movimentoRoteiroEmpresa.getRota());
						
					}
					
					if(movimentoRoteiroEmpresa.getRoteiroEmpresa() != null ){
						movimentoRoteiroEmpresaPoco.setRoteiroEmpresa(movimentoRoteiroEmpresa.getRoteiroEmpresa());
						
					}
					
					if(movimentoRoteiroEmpresa.getTempoLeitura() != null ){
						movimentoRoteiroEmpresaPoco.setTempoLeitura(movimentoRoteiroEmpresa.getTempoLeitura());
						
					}
					
					
					if(movimentoRoteiroEmpresa.getUltimaAlteracao() != null ){
						movimentoRoteiroEmpresaPoco.setUltimaAlteracao(movimentoRoteiroEmpresa.getUltimaAlteracao());
						
					}
					
					
					
					//Medicao Tipo
					MedicaoTipo medicaoTipo = new MedicaoTipo();
					medicaoTipo.setId(MedicaoTipo.POCO);
					
					movimentoRoteiroEmpresaPoco.setMedicaoTipo(medicaoTipo);
					
					Object[] dadosHidrometroNumeroLeitura = 
						pesquisarDadosHidrometroTipoPoco(imovelParaSerGerado);
					
					dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
					numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
					capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
					marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

					//Capacidade Hidrometro
					HidrometroCapacidade capacidade = new HidrometroCapacidade();
					capacidade.setId(capacidadeHidrometro);
					movimentoRoteiroEmpresaPoco.setHidrometroCapacidade(capacidade);

					//Marca do Hidrometro
					HidrometroMarca hidrometroMarca = new HidrometroMarca();
					hidrometroMarca.setId(marcaHidrometro);
					movimentoRoteiroEmpresaPoco.setHidrometroMarca(hidrometroMarca);

					//Numero Hidrometro
					movimentoRoteiroEmpresaPoco.setNumeroHidrometro(
						(String) dadosHidrometroNumeroLeitura[4]);
					
					if (imovelParaSerGerado
							.getHidrometroInstalacaoHistorico() != null) {


	
						//Local de InstalaÃ§Ã£o
						movimentoRoteiroEmpresaPoco

								.setHidrometroLocalInstalacao(imovelParaSerGerado
										.getHidrometroInstalacaoHistorico()
										.getHidrometroLocalInstalacao());


						//Data de InstalaÃ§Ã£o
						movimentoRoteiroEmpresaPoco
								.setDataInstalacaoHidrometro(imovelParaSerGerado
										.getHidrometroInstalacaoHistorico()
										.getDataInstalacao());


						//ProteÃ§Ã£o do Hidrometro
						movimentoRoteiroEmpresaPoco
								.setHidrometroProtecao(imovelParaSerGerado
										.getHidrometroInstalacaoHistorico()
										.getHidrometroProtecao());

					}
					
					// verifica se a leitura anterior Ã© diferente de
					// nula
					if (retorno[3] != null) {
						leituraAnterior = retorno[3].toString();
					}else{
						leituraAnterior = "0";
					}
					// verifica se a leitura situaÃ§Ã£o atual Ã© diferente
					// de
					// nula
					if (retorno[4] != null) {
						medicaoHistorico = (MedicaoHistorico) retorno[4];
					}else{
						medicaoHistorico = new MedicaoHistorico();
					}
					// verifica se o id da mediÃ§Ã£o tipo Ã© diferente de
					// nula
					if (retorno[5] != null) {
						idMedicaoTipo = (Integer) retorno[5];
					}else{
						idMedicaoTipo = MedicaoTipo.POCO;
					}

					// verifica se a leitura anterior Ã© diferente de
					// nula
					// para
					// ser
					// jogado no arquivo
					// txt
					if (leituraAnterior != null) {
						movimentoRoteiroEmpresaPoco
								.setNumeroLeituraAnterior(new Integer(
										leituraAnterior));
						// caso contrario coloca a string com zeros
					}else{
						movimentoRoteiroEmpresaPoco
						.setNumeroLeituraAnterior(new Integer(0));
					}

					// Faixa de leitura esperada
					faixaInicialFinal = pesquisarFaixaEsperadaOuFalsaCelular(
							imovelParaSerGerado, dadosHidrometro,
							leituraAnterior, medicaoHistorico,
							idMedicaoTipo, sistemaParametro,
							hidrometroSelecionado, numeroDigitosHidrometro);

					
					faixaInicialEsperada = 0;
					faixaFinalEsperada = 0;
					
					faixaInicialEsperada = Integer
							.parseInt(faixaInicialFinal[3].toString());

					faixaFinalEsperada = Integer
							.parseInt(faixaInicialFinal[4].toString());
					

					movimentoRoteiroEmpresaPoco
							.setNumeroFaixaLeituraEsperadaInicial(faixaInicialEsperada);
					movimentoRoteiroEmpresaPoco
							.setNumeroFaixaLeituraEsperadaFinal(faixaFinalEsperada);

					movimentoRoteiroEmpresaPoco.setUltimaAlteracao(new Date());
						
					colecaoMovimentoRoteiroEmpresa.add(movimentoRoteiroEmpresaPoco);
					
				}

				imovelParaSerGerado = null;

			}

			try{
			
				getControladorBatch().inserirColecaoObjetoParaBatch(
					colecaoMovimentoRoteiroEmpresa);

			} catch (ControladorException e) {
				colecaoMovimentoRoteiroEmpresa.isEmpty();
				sessionContext.setRollbackOnly();
				e.printStackTrace();
			}
			colecaoMovimentoRoteiroEmpresa.clear();

			colecaoMovimentoRoteiroEmpresa = null;

		}
	}*/

}
	
	

