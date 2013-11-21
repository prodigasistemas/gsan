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
package gcom.relatorio.micromedicao.hidrometro;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.FiltrarHidrometroHelper;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @created 23 de Setembro de 2005
 * @version 1.0
 */

public class RelatorioManterHidrometro extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterHidrometro object
	 */
	public RelatorioManterHidrometro(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_HIDROMETRO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterHidrometro() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param hidrometros
	 *            Description of the Parameter
	 * @param HidrometroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroHidrometro filtroHidrometro = (FiltroHidrometro) getParametro("filtroHidrometro");
		Hidrometro hidrometroParametros = (Hidrometro) getParametro("hidrometroParametros");
		String fixo = (String) getParametro("fixo");
		String faixaInicial = (String) getParametro("faixaInicial");
		String faixaFinal = (String) getParametro("faixaFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		FiltrarHidrometroHelper helper = (FiltrarHidrometroHelper) getParametro("helper");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterHidrometroBean relatorioBean = null;

		Collection hidrometrosNovos = null;

		if (filtroHidrometro != null) {

			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroClasseMetrologica");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroDiametro");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroTipo");
			filtroHidrometro
			.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
			
			filtroHidrometro.setConsultaSemLimites(true);

			// consulta para trazer objeto completo
			hidrometrosNovos = fachada.pesquisar(filtroHidrometro,
					Hidrometro.class.getName());
			
			helper = null;
			
		} else if(helper != null){
			hidrometrosNovos = fachada.pesquisarNumeroHidrometroSituacaoInstaladoRelatorio(helper);
			
		}else {
			hidrometrosNovos = fachada.pesquisarNumeroHidrometroFaixaRelatorio(fixo, faixaInicial, faixaFinal);
		}

		if (hidrometrosNovos != null && !hidrometrosNovos.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator hidrometroNovoIterator = hidrometrosNovos.iterator();

			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

			// laço para criar a coleção de parâmetros da analise
			while (hidrometroNovoIterator.hasNext()) {

				Hidrometro hidrometroNovo = (Hidrometro) hidrometroNovoIterator
						.next();
				
				String situacao = hidrometroNovo.getHidrometroSituacao() == null ? ""
						: hidrometroNovo.getHidrometroSituacao()
						.getDescricao();
				
				String matricula = "";
				
				String dataInstalacao = "";
				//alterado por Rômulo Aurélio CRC 1671 Analista:Rosana Carvalho
				// Caso a situaçao do hidrometro seja INSTALADO
				//colocar a matrícula do imóvel junto com a Data de Intalacao do hidrometro
				if(hidrometroNovo.getHidrometroSituacao().getId().equals(Hidrometro.SITUACAO_INSTALADO)){
					Integer idImovel = fachada.pesquisarImovelPeloHidrometro(hidrometroNovo.getId());
					Date dataInstalacaoHidrometro = fachada.pesquisarDataInstalacaoHidrometroAgua(idImovel);
					matricula = idImovel.toString() ;
					
					dataInstalacao = Util.formatarData(dataInstalacaoHidrometro);
					
				}
				relatorioBean = new RelatorioManterHidrometroBean(

						// Número
						hidrometroNovo.getNumero(),

						// Data de Aquisição
						dataFormatada.format(hidrometroNovo.getDataAquisicao()),

						// Ano de Fabricação
						hidrometroNovo.getAnoFabricacao().toString(),

						// Finalidade
						hidrometroNovo.getIndicadorMacromedidor() == 1 ? "COMERCIAL"
								: "OPERACIONAL",

						// Classe Metrológicao
						hidrometroNovo.getHidrometroClasseMetrologica()
								.getDescricao(),

						// Marca
						hidrometroNovo.getHidrometroMarca().getDescricao(),

						// Diâmetro
						hidrometroNovo.getHidrometroDiametro().getDescricao(),

						// Capacidade
						hidrometroNovo.getHidrometroCapacidade().getDescricao(),

						// Número de Digitos
						hidrometroNovo.getNumeroDigitosLeitura().toString(),

						// Tipo
						hidrometroNovo.getHidrometroTipo().getDescricao(),
						
						//Situação
						situacao,
						//matricula
						matricula,
						//DataInstalacao
						dataInstalacao);
							

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}

		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dataAquisicao = "";
		if (hidrometroParametros.getDataAquisicao() != null
				&& !hidrometroParametros.getDataAquisicao().equals("")) {
			dataAquisicao = format.format(hidrometroParametros
					.getDataAquisicao());
		}

		String numeroQuadra = "";
		
		String descricao = "";
		
		String codigo = "";
		
		if( helper!=null ){
			
			descricao = helper.getIdLocalidade() + "-" + helper.getNomeLocalidade() + "";
			
			
			codigo = helper.getCodigoSetorComercial();
			
			
			numeroQuadra = helper.getNumeroQuadra();
			
			
		}
		parametros.put("codigoSetorComercial", codigo);
		parametros.put("descricaoLocalidade",descricao);
		parametros.put("numeroQuadra", numeroQuadra);
		parametros.put("numero", hidrometroParametros.getNumero());

		parametros.put("dataAquisicao", dataAquisicao);

		parametros.put("anoFabricacao",
				hidrometroParametros.getAnoFabricacao() == null ? "" : ""
						+ hidrometroParametros.getAnoFabricacao());

		parametros.put("classeMetrologica", hidrometroParametros
				.getHidrometroClasseMetrologica() == null ? ""
				: hidrometroParametros.getHidrometroClasseMetrologica()
						.getDescricao());

		parametros.put("marca",
				hidrometroParametros.getHidrometroMarca() == null ? ""
						: hidrometroParametros.getHidrometroMarca()
								.getDescricao());

		parametros.put("diametro",
				hidrometroParametros.getHidrometroDiametro() == null ? ""
						: hidrometroParametros.getHidrometroDiametro()
								.getDescricao());

		parametros.put("capacidade", hidrometroParametros
				.getHidrometroCapacidade() == null ? "" : hidrometroParametros
				.getHidrometroCapacidade().getDescricao());

		parametros.put("tipo",
				hidrometroParametros.getHidrometroTipo() == null ? ""
						: hidrometroParametros.getHidrometroTipo()
								.getDescricao());

		parametros.put("idLocalArmazenagem", hidrometroParametros
				.getHidrometroLocalArmazenagem().getId() == null ? "" : ""
				+ hidrometroParametros.getHidrometroLocalArmazenagem().getId());

		parametros.put("nomeLocalArmazenagem", hidrometroParametros
				.getHidrometroLocalArmazenagem().getDescricao());

		parametros.put("fixo", fixo);

		parametros.put("faixaInicial", faixaInicial);

		parametros.put("faixaFinal", faixaFinal);

		String finalidade = "";

		if (hidrometroParametros.getIndicadorMacromedidor() != null
				&& !hidrometroParametros.getIndicadorMacromedidor().equals("")) {
			if (hidrometroParametros.getIndicadorMacromedidor().equals(
					new Short("1"))) {
				finalidade = "Comercial";
			} else {
				finalidade = "Operacional";
			}
		}

		parametros.put("finalidade", finalidade);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_HIDROMETRO_MANTER,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_HIDROMETRO,
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

		if (getParametro("filtroHidrometro") != null) {

			retorno = Fachada.getInstancia().totalRegistrosPesquisa(
					(FiltroHidrometro) getParametro("filtroHidrometro"),
					Hidrometro.class.getName());

		}else if(getParametro("helper")!= null){
			retorno = Fachada.getInstancia().pesquisarNumeroHidrometroSituacaoInstaladoPaginacaoCount((FiltrarHidrometroHelper) getParametro("helper"));
			
		} 
		else {

			String faixaInicial = (String) getParametro("faixaInicial");
			String faixaFinal = (String) getParametro("faixaFinal");
			String fixo = (String) getParametro("fixo");

			String numeroFormatadoInicial = "";
			String numeroFormatadoFinal = "";

			numeroFormatadoInicial = Util.adicionarZerosEsquedaNumero(6,
					faixaInicial);
			numeroFormatadoFinal = Util.adicionarZerosEsquedaNumero(6,
					faixaFinal);

			Integer totalRegistros = Fachada.getInstancia()
					.pesquisarNumeroHidrometroFaixaCount(fixo,
							fixo + numeroFormatadoInicial,
							fixo + numeroFormatadoFinal);

			retorno = totalRegistros.intValue();
		}

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterHidrometro", this);
	}

}
