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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.GerarRelatorioAnormalidadePorAmostragemHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * Este caso de uso gera relatorio de anormalidade Informadas - Amostragem
 * 
 * [UC1051] Gerar Relatório de Amostragem das Anormalidades Informadas
 * 
 * @author Hugo Leonardo
 * @date 06/08/2010
 * 
 */
public class RelatorioAnormalidadePorAmostragem extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	public RelatorioAnormalidadePorAmostragem(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_ANORMALIDADE_POR_AMOSTRAGEM);
	}

	@Deprecated
	public RelatorioAnormalidadePorAmostragem() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
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

		Integer idGrupo = (Integer) getParametro("idGrupo");
		Short cdRota = (Short) getParametro("cdRota");
		Integer idGerenciaRegional = (Integer) getParametro("idGerenciaRegional");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer idSetorComercialInicial = (Integer) getParametro("idSetorComercialInicial");
		Integer idSetorComercialFinal = (Integer) getParametro("idSetorComercialFinal");
		Collection<Integer> colecaoIdsEmpresa = (Collection<Integer>) getParametro("colecaoIdsEmpresa");
		Collection<Integer> colecaoIdsAnormalidadeLeituraInformada = (Collection<Integer>) getParametro("colecaoIdsAnormalidadeLeituraInformada");
		Integer numeroOcorrencias = (Integer) getParametro("numeroOcorrencias");
		String indicadorOcorrenciasIguais = (String) getParametro("ocorrenciasIguais");
		Integer idImovelPerfil = (Integer) getParametro("idImovelPerfil");
		Integer referencia = (Integer) getParametro("referencia");
		Integer mediaConsumoInicial = (Integer) getParametro("mediaConsumoInicial");
		Integer mediaConsumoFinal = (Integer) getParametro("mediaConsumoFinal");
		Integer tipoMedicao = (Integer) getParametro("tipoMedicao");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer idCategoria = (Integer)getParametro("idCategoria");
		Integer numeroQuadraInicial = (Integer)getParametro("numeroQuadraInicial");
		Integer numeroQuadraFinal = (Integer)getParametro("numeroQuadraFinal");
		Integer amostragem = (Integer)getParametro("amostragem");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAnormalidadePorAmostragemBean relatorioBean = null;
		
		Collection colecaoHelper = fachada.pesquisarDadosRelatorioAnormalidadeConsumoPorAmostragem(idGrupo, cdRota,
				idGerenciaRegional, idUnidadeNegocio, idLocalidadeInicial, idLocalidadeFinal, 
				idSetorComercialInicial, idSetorComercialFinal ,referencia,
				idImovelPerfil, numeroOcorrencias, indicadorOcorrenciasIguais,
				mediaConsumoInicial, mediaConsumoFinal, null, 
				null, colecaoIdsAnormalidadeLeituraInformada, tipoMedicao, colecaoIdsEmpresa,
				numeroQuadraInicial, numeroQuadraFinal, idCategoria, amostragem);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoHelper != null && !colecaoHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoHelperIterator = colecaoHelper.iterator();
			
			Integer totalGrupo = new Integer("0");
			Integer totalGerenciaRegional = new Integer("0");
			Integer totalUnidadeNegocio = new Integer("0");
			Integer totalElo = new Integer("0");
			Integer totalLocalidade = new Integer("0");
			Integer totalSetorComercial = new Integer("0");
			
			Integer idGrupoAnterior = null;
			Integer idGerenciaRegionalAnterior = null;
			Integer idUnidadeNegocioAnterior = null;
			Integer idEloAnterior = null;
			Integer idLocalidadeAnterior = null;
			Integer idSetorComercialAnterior = null;
			
			boolean zerarGrupo = false;
			boolean zerarGerenciaRegional = false;
			boolean zerarUnidadeNegocio = false;
			boolean zerarElo = false;
			boolean zerarLocalidade = false;
			boolean zerarSetorComercial = false;

			// laço para criar a coleção de parâmetros da analise
			while (colecaoHelperIterator.hasNext()) {

				GerarRelatorioAnormalidadePorAmostragemHelper relatorioHelper = (GerarRelatorioAnormalidadePorAmostragemHelper) colecaoHelperIterator
						.next();

				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório

				// Grupo
				String grupo = "";
				if (relatorioHelper.getIdGrupo() != null) {
					grupo = relatorioHelper.getIdGrupo()
							+ " - "
							+ relatorioHelper.getNomeGrupo();
					
					if (idGrupoAnterior == null) {
						idGrupoAnterior = relatorioHelper.getIdGrupo();
					} else {
						if (!idGrupoAnterior.equals(relatorioHelper.getIdGrupo())) {
							zerarGrupo = true;
						}
					}
				}
				
				// Gerência Regional
				String gerenciaRegional = "";
				if (relatorioHelper.getIdGerenciaRegional() != null) {
					gerenciaRegional = relatorioHelper.getIdGerenciaRegional()
							+ " - "
							+ relatorioHelper.getNomeGerenciaRegional();
					
					if (idGerenciaRegionalAnterior == null) {
						idGerenciaRegionalAnterior = relatorioHelper.getIdGerenciaRegional();
					} else {
						if (!idGerenciaRegionalAnterior.equals(relatorioHelper.getIdGerenciaRegional())) {
							zerarGerenciaRegional = true;
						}
					}
				}
				
				// Unidade de Negócio
				String unidadeNegocio = "";
				if (relatorioHelper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = relatorioHelper.getIdUnidadeNegocio()
							+ " - "
							+ relatorioHelper.getNomeUnidadeNegocio();
					
					if (idUnidadeNegocioAnterior == null) {
						idUnidadeNegocioAnterior = relatorioHelper.getIdUnidadeNegocio();
					} else {
						if (!idUnidadeNegocioAnterior.equals(relatorioHelper.getIdUnidadeNegocio())) {
							zerarUnidadeNegocio = true;
						}
					}
				}
				
				// Elo
				String elo = "";
				if (relatorioHelper.getIdElo() != null) {
					elo = relatorioHelper.getIdElo()
							+ " - "
							+ relatorioHelper.getNomeElo();
					
					if (idEloAnterior == null) {
						idEloAnterior = relatorioHelper.getIdElo();
					} else {
						if (!idEloAnterior.equals(relatorioHelper.getIdElo())) {
							zerarElo = true;
						}
					}
				}
				
				// Localidade
				String localidade = "";
				if (relatorioHelper.getIdLocalidade() != null) {
					localidade = relatorioHelper.getIdLocalidade()
							+ " - "
							+ relatorioHelper.getNomeLocalidade();
					
					if (idLocalidadeAnterior == null) {
						idLocalidadeAnterior = relatorioHelper.getIdLocalidade();
					} else {
						if (!idLocalidadeAnterior.equals(relatorioHelper.getIdLocalidade())) {
							zerarLocalidade = true;
						}
					}
				}
				
				// Setor Comercial
				String setorComercial = "";
				String idSetorComercial = "";
				if (relatorioHelper.getIdSetorComercial() != null) {
					setorComercial = relatorioHelper.getCodigoSetorComercial().toString();
					idSetorComercial = relatorioHelper.getIdSetorComercial().toString();
					
					if (idSetorComercialAnterior == null) {
						idSetorComercialAnterior = relatorioHelper.getIdSetorComercial();
					} else {
						if (!idSetorComercialAnterior.equals(relatorioHelper.getIdSetorComercial())) {
							zerarSetorComercial = true;
						}
					}
				}
				
				// Caso tenha mudado de Grupo zera seus totalizadores
				if (zerarGrupo) {
					totalGrupo = new Integer("0");
					idGrupoAnterior = relatorioHelper.getIdGrupo();
					zerarGrupo = false;
				}

				// Caso tenha mudado de Gerência Regional zera seus totalizadores
				if (zerarGerenciaRegional) {
					totalGerenciaRegional = new Integer("0");
					idGerenciaRegionalAnterior = relatorioHelper.getIdGerenciaRegional();
					zerarGerenciaRegional = false;
				}

				// Caso tenha mudado de Unidade de Negócio zera seus totalizadores
				if (zerarUnidadeNegocio) {
					totalUnidadeNegocio = new Integer("0");
					idUnidadeNegocioAnterior = relatorioHelper.getIdUnidadeNegocio();
					zerarUnidadeNegocio = false;
				}
				
				// Caso tenha mudado de Elo zera seus totalizadores
				if (zerarElo) {
					totalElo = new Integer("0");
					idEloAnterior = relatorioHelper.getIdElo();
					zerarElo = false;
				}
				
				// Caso tenha mudado de Localidade zera seus totalizadores
				if (zerarLocalidade) {
					totalLocalidade = new Integer("0");
					idLocalidadeAnterior = relatorioHelper.getIdLocalidade();
					zerarLocalidade = false;
				}
				
				// Caso tenha mudado de setor comercial zera seus totalizadores
				if (zerarSetorComercial) {
					totalSetorComercial = new Integer("0");
					idSetorComercialAnterior = relatorioHelper.getIdSetorComercial();
					zerarSetorComercial = false;
				}
				
				totalGrupo = totalGrupo + 1;
				totalGerenciaRegional = totalGerenciaRegional + 1;
				totalUnidadeNegocio = totalUnidadeNegocio + 1;
				totalElo = totalElo + 1;
				totalLocalidade = totalLocalidade + 1;
				totalSetorComercial = totalSetorComercial + 1;

				// Imóvel, Endereço e Categoria
				String idImovel = "";
				String endereco = "";
				String descricaoCategoria = "";

				if (relatorioHelper.getIdImovel() != null) {
					idImovel = relatorioHelper.getIdImovel()
							.toString();
					Imovel imovel = new Imovel();
					imovel.setId(relatorioHelper.getIdImovel());
					Categoria categoria = fachada.obterDescricoesCategoriaImovel(imovel);
					
					if (categoria.getDescricaoAbreviada() != null) {
						descricaoCategoria = categoria.getDescricaoAbreviada();
					}
				
				}
				
				if(relatorioHelper.getEnderecoImovel() != null){
					endereco = relatorioHelper.getEnderecoImovel() ;
				}

				// Nome do Usuário
				String nomeUsuario = "";
				if (relatorioHelper.getNomeUsuario() != null) {
					nomeUsuario = relatorioHelper.getNomeUsuario();
				}
				
				// Situação da Ligação de Água
				String situacaoAgua = "";
				if (relatorioHelper.getSituacaoLigacaoAgua() != null) {
					situacaoAgua = relatorioHelper.getSituacaoLigacaoAgua().toString();
				}

				// Situação de Esgoto
				String situacaoEsgoto = "";
				if (relatorioHelper.getSituacaoLigacaoEsgoto() != null) {
					situacaoEsgoto = relatorioHelper.getSituacaoLigacaoEsgoto().toString();
				}
				
				// Débito Automático
				String debitoAutomatico = "";
				if (relatorioHelper.getIndicadorDebito() != null) {
					if (relatorioHelper.getIndicadorDebito().equals(ConstantesSistema.SIM)) {
						debitoAutomatico = "SIM";
					} else {
						debitoAutomatico = "NÃO";
					}
				}
				
				// Média de Consumo
				String media = "";
				if (relatorioHelper.getConsumoMedio() != null) {
					media = relatorioHelper.getConsumoMedio().toString();
				}

				// Consumo
				String consumo = "";
				if (relatorioHelper.getConsumoMes() != null) {
					consumo = relatorioHelper.getConsumoMes().toString();
				}

				// Anormalidade de Consumo
				String anormalidadeConsumo = "";
				if (relatorioHelper.getDescricaoAbrevConsumoAnormalidade() != null) {
					anormalidadeConsumo = relatorioHelper.getDescricaoAbrevConsumoAnormalidade();
				}
				
				// Anormalidade de Leitura
				String anormalidadeLeitura = "";
				if (relatorioHelper.getIdLeituraAnormalidade() != null) {
					anormalidadeLeitura = relatorioHelper.getIdLeituraAnormalidade().toString();
				}
				
				String nnLeituraAtualInformada = "";
				if (relatorioHelper.getNnLeituraAtualInformada() != null) {
					nnLeituraAtualInformada = relatorioHelper.getNnLeituraAtualInformada().toString();
				}
				
				// Quantidade de Economias
				String qtdeEconomias = "";
				if (relatorioHelper.getQuantidadeEconomias() != null) {
					qtdeEconomias = relatorioHelper.getQuantidadeEconomias().toString();
				}
				
				// Capacidade do Hidrômetro
				String capacidadeHidrometro = "";
				if (relatorioHelper.getCapacidadeHidrometro() != null) {
					capacidadeHidrometro = relatorioHelper.getCapacidadeHidrometro();
				}
				
				// Local de Instalação do Hidrômetro
				String localInstalacaoHidrometro = "";
				if (relatorioHelper.getLocalInstalacaoHidrometro() != null) {
					localInstalacaoHidrometro = relatorioHelper.getLocalInstalacaoHidrometro();
				}
				
				// Setor Comercial
				String idEmpresa = "";
				String nomeEmpresa = "";

				if (relatorioHelper.getIdEmpresa() != null) {
				    idEmpresa = relatorioHelper.getIdEmpresa().toString();
					nomeEmpresa = idEmpresa + " - " +relatorioHelper.getNomeEmpresa();
				}

				String inscricaoImovel = relatorioHelper.getInscricaoImovel();
				if(relatorioHelper.getInscricaoImovel() != null){
					inscricaoImovel = relatorioHelper.getInscricaoImovel();
				}
				
				relatorioBean = new RelatorioAnormalidadePorAmostragemBean(
						
						// Grupo
						grupo,
						// Gerência Regional
						gerenciaRegional,
						// Unidade de Negócio
						unidadeNegocio,
						// Elo
						elo,
						// Localidade
						localidade,
						// Id do Setor Comercial
						idSetorComercial,
						// Setor Comercial
						setorComercial,
						// Imóvel
						idImovel,
						// Usuário
						nomeUsuario,
						// Endereço
						endereco,
						// Situação de Água
						situacaoAgua,
						// Situação de Esgoto
						situacaoEsgoto,
						// Débito Automático
						debitoAutomatico,
						// Média de Consumo
						media,
						// Consumo
						consumo,
						// Anormalidade de Consumo
						anormalidadeConsumo,
						// Anormalidade de Leitura
						anormalidadeLeitura,
						// Número leitura atual informada 
						nnLeituraAtualInformada,
						// Categoria
						descricaoCategoria,
						// Quantidade de Economias
						qtdeEconomias,
						// Tipo de Medição
						relatorioHelper.getTipoMedicao(),
						// Capacidade do Hidrômetro
						capacidadeHidrometro,
						// Local de Instalação do Hidrômetro
						localInstalacaoHidrometro,
						// Total do Grupo
						totalGrupo.toString(),
						// Total da Gerência Regional
						totalGerenciaRegional.toString(),
						// Total da Unidade de Negócio
						totalUnidadeNegocio.toString(),
						// Total do Elo
						totalElo.toString(),
						// Total da Localidade
						totalLocalidade.toString(),
						// Total do Setor Comercial
						totalSetorComercial.toString(),
						//Id Empresa
						idEmpresa,
						//Nome Empresa
						nomeEmpresa,
						inscricaoImovel 			
				);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);

			}
		}else{
			
			relatorioBean = new RelatorioAnormalidadePorAmostragemBean();
			relatorioBeans.add(relatorioBean);
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(referencia));
		
		parametros.put("tipoFormatoRelatorio", "R1051");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		if(colecaoHelper != null && colecaoHelper.size() > 0){
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_ANORMALIDADE_POR_AMOSTRAGEM,
					parametros, ds, tipoFormatoRelatorio);
		}else{
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_ANORMALIDADE_POR_AMOSTRAGEM,
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
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnormalidadePorAmostragem", this);
	}
	
}