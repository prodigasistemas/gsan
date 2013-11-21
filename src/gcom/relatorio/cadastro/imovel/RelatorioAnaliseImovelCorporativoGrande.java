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
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.bean.GerarRelatorioAnaliseImovelCorporativoGrandeHelper;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Este caso de uso gera relatório de análise do imóvel corporativo ou grande
 * 
 * @author Ana Maria
 * @date 06/01/09
 * 
 */
public class RelatorioAnaliseImovelCorporativoGrande extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioAnaliseImovelCorporativoGrande(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_ANALISE_IMOVEL_CORPORATIVO_GRANDE);
	}

	@Deprecated
	public RelatorioAnaliseImovelCorporativoGrande() {
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

		Integer idGerenciaRegional = (Integer) getParametro("idGerenciaRegional");
		Integer idUnidadeNegocio = (Integer) getParametro("idUnidadeNegocio");
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer idSetorComercialInicial = (Integer) getParametro("idSetorComercialInicial");
		Integer idSetorComercialFinal = (Integer) getParametro("idSetorComercialFinal");
		Integer idImovel = (Integer) getParametro("idImovelPerfil");
		Integer selecionar = (Integer) getParametro("selecionar");
		Integer referencia = (Integer) getParametro("referencia");
		Integer idImovelPerfil = (Integer)getParametro("idImovelPerfil");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");	
		

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAnaliseImovelCorporativoGrandeBean relatorioBean = null;

		Collection colecaoRelatorioAnaliseImovelCorporativoGrandeHelper = fachada.pesquisarDadosRelatorioAnaliseImovelCorporativoGrande(
				idGerenciaRegional,idUnidadeNegocio, idLocalidadeInicial, idLocalidadeFinal, 
				idSetorComercialInicial, idSetorComercialFinal, referencia, idImovel, selecionar);
		

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoRelatorioAnaliseImovelCorporativoGrandeHelper != null
				&& !colecaoRelatorioAnaliseImovelCorporativoGrandeHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoRelatorioAnaliseImovelCorporativoGrandeHelperIterator = colecaoRelatorioAnaliseImovelCorporativoGrandeHelper
					.iterator();
			
			// laço para criar a coleção de parâmetros da analise
			while (colecaoRelatorioAnaliseImovelCorporativoGrandeHelperIterator.hasNext()) {

				GerarRelatorioAnaliseImovelCorporativoGrandeHelper helper = (GerarRelatorioAnaliseImovelCorporativoGrandeHelper) colecaoRelatorioAnaliseImovelCorporativoGrandeHelperIterator
						.next();

				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				// Gerência Regional
				String gerenciaRegional = "";

				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = helper.getIdGerenciaRegional()
							+ " - " + helper.getNomeGerenciaRegional();
				}
				
				// Unidade de Negócio
				String unidadeNegocio = "";

				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = helper.getIdUnidadeNegocio()
							+ " - "+ helper.getNomeUnidadeNegocio();
				}
				
				
				// Localidade
				String localidade = "";

				if (helper.getIdLocalidade() != null) {
					localidade = helper
							.getIdLocalidade()
							+ " - "
							+ helper
									.getNomeLocalidade();
				}
				
				// Setor Comercial
				String setorComercial = "";
				String idSetorComercial = "";

				if (helper.getIdSetorComercial() != null) {
					setorComercial = helper.getCodigoSetorComercial().toString();
					idSetorComercial = helper.getIdSetorComercial().toString();			
				}

				// Imóvel, Endereço e Categoria
				String matriculaImovel = "";
				String endereco = "";
				String inscricao = "";
				
				if (helper.getIdImovel() != null) {
					matriculaImovel = helper.getIdImovel().toString();
					Imovel imovel = new Imovel();
					imovel.setId(helper.getIdImovel());
					endereco = fachada.pesquisarEndereco(helper.getIdImovel());
					inscricao = fachada.pesquisarInscricaoImovel(helper.getIdImovel());
				}
				
				// Capacidade do Hidrômetro
				String capacidadeHidrometro = "";
				
				if (helper.getCapacidadeHidrometro() != null) {
					capacidadeHidrometro = helper.getCapacidadeHidrometro();
				}			
				
				String consumoMedio = "";
				
				if (helper.getConsumoMedio() != null) {
					consumoMedio = helper.getConsumoMedio().toString();
				}
				
				String consumoFaturado = "";
				
				if (helper.getConsumoFaturado() != null) {
					consumoFaturado = helper.getConsumoFaturado().toString();
				}
				
				String tipoLigacao = "";
				
				if(helper.getIdTipoLigacao() != null){
					if(helper.getIdTipoLigacao().equals(1)){
						tipoLigacao = "ÁGUA";
					}else{
						tipoLigacao = "ESGOTO";
					}
				}

				relatorioBean = new RelatorioAnaliseImovelCorporativoGrandeBean(

						// Unidade de Negócio
						unidadeNegocio,
						
						// Gerência Regional
						gerenciaRegional,

						// Localidade
						localidade,
						
						// Id do Setor Comercial
						idSetorComercial,
						
						// Setor Comercial
						setorComercial,
						
						//Inscrição
						inscricao,
						
						// Imóvel
						matriculaImovel,
										
						// Endereço
						endereco,
						
						// Capacidade do Hidrômetro
						capacidadeHidrometro,
						
						//Consumo Médio
						consumoMedio,
						
						//Consumo Faturado
						consumoFaturado,
						
						//Tipo de ligação
						tipoLigacao						
				);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);

			}
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
		
		if(idUnidadeNegocio!=null){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocio));		
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());		
			UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			parametros.put("unidadeNegocio", unidadeNegocio.getNomeAbreviado() + "-" + unidadeNegocio.getNome());
		}else{
			parametros.put("unidadeNegocio", "");
		}
		
		if(idLocalidadeInicial!=null){
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeInicial));		
			Collection colecaoLocalidadeInicial = fachada.pesquisar(filtroLocalidadeInicial, Localidade.class.getName());		
			Localidade localidadeInicial = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeInicial);
			parametros.put("localidadeInicial",  localidadeInicial.getId() + "-" + localidadeInicial.getDescricao()) ;
			
		}else{
			parametros.put("localidadeInicial", "") ;
		}	
		
		if(idLocalidadeInicial!=null){
			FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
			filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeInicial));		
			Collection colecaoLocalidadeFinal = fachada.pesquisar(filtroLocalidadeFinal, Localidade.class.getName());		
			Localidade localidadeFinal = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeFinal);		
			parametros.put("localidadeFinal", localidadeFinal.getId() + "-" + localidadeFinal.getDescricao()) ;		
				
		}else{
			parametros.put("localidadeFinal", "") ;					
		}
		
		if(idGerenciaRegional!=null){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, idGerenciaRegional));		
			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());		
			GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
			parametros.put("gerenciaRegional", gerenciaRegional.getNomeAbreviado() + "-" + gerenciaRegional.getNome());		
		}else{
			parametros.put("gerenciaRegional", "");		
		}
		
		
		if(idSetorComercialInicial!= null){
			FiltroSetorComercial filtroSetorComercialInicial = new FiltroSetorComercial();
			filtroSetorComercialInicial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, idSetorComercialInicial));		
			Collection colecaoSetorComercialInicial = fachada.pesquisar(filtroSetorComercialInicial, SetorComercial.class.getName());		
			SetorComercial setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialInicial);
			parametros.put("setorComercialInicial",  setorComercialInicial.getId() + "-" + setorComercialInicial.getDescricao()) ;				
		}else{
			parametros.put("setorComercialInicial", "") ;				
		}
		
		if(idSetorComercialInicial!=null){
			FiltroSetorComercial filtroSetorComercialFinal = new FiltroSetorComercial();
			filtroSetorComercialFinal.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, idSetorComercialInicial));		
			Collection colecaoSetorComercialFinal = fachada.pesquisar(filtroSetorComercialFinal, SetorComercial.class.getName());		
			SetorComercial setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialFinal);		
			parametros.put("setorComercialFinal",  setorComercialFinal.getId() + "-" + setorComercialFinal.getDescricao()) ;				
		}else{
			parametros.put("setorComercialFinal",  "") ;		
		}		
		
		if(idImovelPerfil!=null){
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, idImovelPerfil));		
			Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());		
			ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(colecaoImovelPerfil);		
			parametros.put("imovelPerfil","ANÁLISE DO IMÓVEIS " +imovelPerfil.getDescricao().toUpperCase()+"S") ;		
		}else{
			parametros.put("imovelPerfil", "") ;	
		}
		
		parametros.put("tipoFormatoRelatorio", "R0887");		
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ANALISE_IMOVEL_CORPORATIVO_GRANDE,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.ANALISE_IMOVEL_CORPORATIVO_GRANDE,
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

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnaliseImovelCorporativoGrande",
				this);
	}
}