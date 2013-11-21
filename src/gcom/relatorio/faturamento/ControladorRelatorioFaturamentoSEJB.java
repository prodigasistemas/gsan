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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.ConsumoFaixaCategoria;
import gcom.faturamento.ConsumoFaixaLigacao;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.bean.EmitirHistogramaAguaEconomiaDetalheHelper;
import gcom.faturamento.bean.EmitirHistogramaAguaEconomiaHelper;
import gcom.faturamento.bean.EmitirHistogramaEsgotoDetalheHelper;
import gcom.faturamento.bean.EmitirHistogramaEsgotoEconomiaDetalheHelper;
import gcom.faturamento.bean.EmitirHistogramaEsgotoEconomiaHelper;
import gcom.faturamento.bean.EmitirHistogramaEsgotoHelper;
import gcom.faturamento.bean.FaturaClienteResponsavelHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaEconomiaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoEconomiaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.faturamento.conta.Fatura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Foi criador esse controlador para relatorio especificos para faturamento
 * 
 * @author Rafael Pinto
 * @created 16/06/2007
 */

public class ControladorRelatorioFaturamentoSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	SessionContext sessionContext;
	
	private IRepositorioFaturamento repositorioFaturamento;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();

	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * Retorna o valor de ControladorClienteLocal
	 * 
	 * @return O valor de ControladorClienteLocal
	 */
	protected ControladorClienteLocal getControladorCliente() {

		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorClienteLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	protected ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * [UC0604] Emitir Histograma de Água por Economia
	 * 
	 * @author Rafael Pinto
	 * @date 08/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	public Collection<EmitirHistogramaAguaEconomiaHelper> pesquisarEmitirHistogramaAguaEconomia(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {

		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		Collection<Integer> colecao = filtro.getColecaoTarifa();
		
		if(colecao != null && !colecao.isEmpty()){
			
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				Integer tarifa = (Integer) itera.next();
			
				FiltrarEmitirHistogramaAguaEconomiaHelper novoFiltro = 
					new FiltrarEmitirHistogramaAguaEconomiaHelper(filtro);
				
				FiltrarEmitirHistogramaAguaEconomiaHelper novoFiltroClone = 
					new FiltrarEmitirHistogramaAguaEconomiaHelper(filtro);
				
				novoFiltro.setTarifa(tarifa);
				novoFiltroClone.setTarifa(tarifa);
				
				colecaoEmitirHistogramaAguaEconomia.addAll(this.montarSwitchHistogramaAguaEconomia(novoFiltro,novoFiltroClone));
			}
		}
		
		FiltrarEmitirHistogramaAguaEconomiaHelper filtroClone2 = 
			new FiltrarEmitirHistogramaAguaEconomiaHelper(filtro);
		
		colecaoEmitirHistogramaAguaEconomia.addAll(
			this.montarSwitchHistogramaAguaEconomia(filtro,filtroClone2));
		
		
		return colecaoEmitirHistogramaAguaEconomia;
	}
	
	
	/**
	 * [UC0604] Emitir Histograma de Agua Por Economia 
	 * 
	 * Monta switch 
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> montarSwitchHistogramaAguaEconomia(
		FiltrarEmitirHistogramaAguaEconomiaHelper filtro,
		FiltrarEmitirHistogramaAguaEconomiaHelper filtroClone) throws ControladorException {

		int opcaoTotalizacao = filtro.getOpcaoTotalizacao();

		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		switch (opcaoTotalizacao) {
		
			// Estado
			case 1:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEstado(filtro));
				break;
			
			
			// Estado por Gerencia Regional
			case 2:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaGerenciaRegional(filtro));
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEstado(filtroClone));
				break;
				
			// Estado por Unidade Negocio				
			case 3:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocioGerenciaRegional(filtro));
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEstado(filtroClone));
				break;
					
			// Estado por Elo Polo
			case 4:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPoloUnidadeNegocioGerenciaRegional(filtro));
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEstado(filtroClone));
				break;
				
			// Estado por Localidade
			case 5:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidadeEloPoloUnidadeNegocioGerenciaRegional(filtro));
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEstado(filtroClone));
				break;
			
			// Gerência Regional
			case 6:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaGerenciaRegional(filtro));
				break;
				
			// Gerência Regional por Unidade Negocio
			case 7:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocioGerenciaRegional(filtro));
				break;
	
			// Gerência Regional por Elo
			case 8:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPoloUnidadeNegocioGerenciaRegional(filtro));
				break;
	
			// Gerência Regional por Localidade
			case 9:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidadeEloPoloUnidadeNegocioGerenciaRegional(filtro));
				break;
				
			// Unidade de Negocio
			case 10:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocio(filtro));
				break;
				
			// Unidade de Negocio por Elo
			case 11:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPoloUnidadeNegocio(filtro));
				break;
				
			// Unidade de Negocio por Localidade
			case 12:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidadeEloPoloUnidadeNegocio(filtro));
				break;
				
			// Elo Polo
			case 13:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPolo(filtro));
				break;
	
			// Elo Polo Por Localidade
			case 14:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidadeEloPolo(filtro));
				break;
				
			// Elo Polo Por Setor Comercial
			case 15:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaSetorComercialLocalidadeEloPolo(filtro));
				break;
	
			// Localidade
			case 16:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidade(filtro));
				break;
	
			// Localidade Por Setor Comercial
			case 17:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaSetorComercialLocalidade(filtro));
				break;
	
			// Localidade Por Quadra
			case 18:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaQuadraSetorComercialLocalidade(filtro));
				break;
	
			// Setor Comercial
			case 19:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaSetorComercial(filtro));
				break;
	
			// Setor Comercial Por Quadra
			case 20:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaQuadraSetorComercial(filtro));
				break;
				
			// Quadra
			case 21:
				colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaQuadra(filtro));
				break;
				
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
	}
	
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia 
	 * 
	 * Estado
	 * 
	 * @author Rafael Pinto
	 * @date 15/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaEstado(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		

		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){
			

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}
		

		LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
		Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
		Collection colecaoConsumoFaixaCategoria = null;
		EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
		Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
		
		Categoria categoria = null;
		
		FiltroCategoria filtroCategoria = null;
		Collection colecaoConsulta = null;

		Iterator itera = colecaoCategorias.iterator();
		
		String descricaoOpcaoTotalizacao = 
			this.getControladorUtil().pesquisarParametrosDoSistema().getNomeEstado();

		Integer totalGeralEconomiasMedido = 0;
		Integer totalGeralVolumeConsumoMedido = 0;
		Integer totalGeralVolumeFaturadoMedido = 0;
		BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
		Integer totalGeralLigacaoMedido = 0;
		
		Integer totalGeralEconomiasNaoMedido = 0;
		Integer totalGeralVolumeConsumoNaoMedido = 0;
		Integer totalGeralVolumeFaturadoNaoMedido = 0;
		BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
		Integer totalGeralLigacaoNaoMedido = 0;
		
		int quantidadeCategoriaComValores = 0;
		

		while (itera.hasNext()) {
			
			String idCategoria = (String) itera.next(); 
			
			emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
			emitirHistograma.setDescricaoTarifa(descricaoTarifa);
			
			colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
			
			colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);

			int quantidadeFaixaComValores = 0;
			int limiteSuperiorFaixaAnterior = 0;
			
			Integer totalEconomiasMedido = 0;
			Integer totalVolumeConsumoMedido = 0;
			Integer totalVolumeFaturadoMedido = 0;
			Integer totalLigacaoMedido = 0;
			BigDecimal totalReceitaMedido = new BigDecimal(0.0);
			
			Integer totalEconomiasNaoMedido = 0;
			Integer totalVolumeConsumoNaoMedido = 0;
			Integer totalVolumeFaturadoNaoMedido = 0;
			Integer totalLigacaoNaoMedido = 0;
			BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
			
			filtroCategoria = new FiltroCategoria();
			filtroCategoria.adicionarParametro(
				new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
				
			// Recupera Categoria
			colecaoConsulta = 
				this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
		
			categoria = 
				(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

			String descricaoCategoria = categoria.getDescricao();
			
			filtro.setCategoria(categoria);						
			
			if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
				
				Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
				
				while (iteraFaixas.hasNext()) {
					
					ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
					
					EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
						this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
	
					emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
					limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
					
					colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
					
					totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
					totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
					totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
					totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
					totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
					
					totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
					totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
					totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
					totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
					totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					
					if(detalhe.isExisteHistograma()){
						quantidadeFaixaComValores++;
					}
				}				
			} else {
				FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
				filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
				filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
				
				filtroSubcategoria.setCampoOrderBy( "id" );				
				Collection colSubcategoria = new ArrayList();
				
				Subcategoria subcategoria0 = new Subcategoria();
				subcategoria0.setId( new Integer( 0 ) );
				Categoria categoriaX = new Categoria();
				categoriaX.setId( Integer.parseInt( idCategoria ) );
				subcategoria0.setCategoria( categoriaX );
				subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
				
				colSubcategoria.add( subcategoria0 );
				colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
				
				Iterator iteSubcategoria = colSubcategoria.iterator();
				
				emitirHistograma.setDescricaoCategoria(descricaoCategoria);
				
				while ( iteSubcategoria.hasNext() ){			
					
					Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
					filtro.setSubcategoria( subcategoria );					
					
					emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					
					Integer totalEconomiasMedidoSubcategoria = 0;
					Integer totalVolumeConsumoMedidoSubcategoria = 0;
					Integer totalVolumeFaturadoMedidoSubcategoria = 0;
					Integer totalLigacaoMedidoSubcategoria = 0;
					BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedidoSubcategoria = 0;
					Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
					Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
					Integer totalLigacaoNaoMedidoSubcategoria = 0;
					BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
					
					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
						
						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
						
						detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
		
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						// Guardamos os totais por subcategoria
						totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
						totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
						totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						
						if(detalhe.isExisteHistograma()){
							quantidadeFaixaComValores++;
						}
					}
					
					EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
						new EmitirHistogramaAguaEconomiaDetalheHelper ();

					totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getCodigo() );
					
					totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
					totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
					totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
					totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
					totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
					
					totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
					totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
					totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
					totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
					totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
					
					colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
				}				
			}			
			
			if(quantidadeFaixaComValores != 0){
				quantidadeCategoriaComValores++;
			}
				
			emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
			
			emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
			emitirHistograma.setDescricaoFaixa("TOTAL");
			
			emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
			emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
			emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
			emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
			emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
			
			emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
			emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
			emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
			emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
			emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
			
			colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
			
			totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
			totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
			totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
			totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
			totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
			
			totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
			totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
			totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
			totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
			totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
			
		}

		if (quantidadeCategoriaComValores != 0){

			emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
			
			emitirHistograma.setDescricaoTarifa(descricaoTarifa);
			emitirHistograma.setDescricaoCategoria(null);
			emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
			emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
			
			//Medido(COM HIDROMETRO)
			emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
			emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
			emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
			emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
			emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
			
			//Não Medido(SEM HIDROMETRO)
			emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
			emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
			emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
			emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
			emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
			
			// TOTAL GERAL
			colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
		}else{
			colecaoEmitirHistogramaAguaEconomia = 
				new ArrayList<EmitirHistogramaAguaEconomiaHelper>();	
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia - Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 15/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaGerenciaRegional(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		filtro.setEloPolo(null);
		filtro.setUnidadeNegocio(null);
		filtro.setLocalidade(null);
		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();

		filtro.setTipoGroupBy("histograma.gerenciaRegional.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			GerenciaRegional gerencia = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroGerenciaRegional filtroGerencia = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String IdGerencia = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroGerencia = new FiltroGerenciaRegional();
				filtroGerencia.adicionarParametro(
					new ParametroSimples(FiltroGerenciaRegional.ID,IdGerencia));
				
				// Recupera Gerencia Regional
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroGerencia, GerenciaRegional.class.getName());
			
				gerencia = 
					(GerenciaRegional) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = gerencia.getId()+" - "+gerencia.getNome();

				filtro.setGerenciaRegional(gerencia);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;


				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();   
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}			
/*					
					
					

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
*/					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(IdGerencia);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia  
	 * 
	 * Unidade de Negocio e Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaUnidadeNegocioGerenciaRegional(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.unidadeNegocio.id,histograma.gerenciaRegional.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			UnidadeNegocio unidadeNegocio = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroUnidadeNegocio filtroUnidadeNegocio = null;
			Collection colecaoConsulta = null;
			
			//Pai da unidade Negocioa(unidade -> gerencia)
			int gerenciaAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				GerenciaRegional gerencia = new GerenciaRegional();
				gerencia.setId(new Integer(arrayNumeracao[1]));

				if(gerenciaAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					gerenciaAnterior = gerencia.getId();
				}
				
				//Mudou de Gerencia
				if(gerenciaAnterior != gerencia.getId().intValue()){
					
					filtro.setOpcaoTotalizacao(2);
					
					GerenciaRegional gereAnterior = new GerenciaRegional();
					gereAnterior.setId(gerenciaAnterior);
					filtro.setGerenciaRegional(gereAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaGerenciaRegional(filtro));
				}

				filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(
					new ParametroSimples(FiltroUnidadeNegocio.ID,arrayNumeracao[0]));
				filtroUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				
				// Recupera Unidade Negocio
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
				unidadeNegocio = 
					(UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					unidadeNegocio.getGerenciaRegional().getId()+"-"+unidadeNegocio.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					unidadeNegocio.getId()+"-"+unidadeNegocio.getNome();

				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setGerenciaRegional(gerencia);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}*/
					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
				
				gerenciaAnterior = gerencia.getId();
			}
			
			filtro.setOpcaoTotalizacao(2);
			
			GerenciaRegional gereAnterior = new GerenciaRegional();
			gereAnterior.setId(gerenciaAnterior);
			filtro.setGerenciaRegional(gereAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaGerenciaRegional(filtro));
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia  
	 * 
	 * Elo Polo e Unidade de Negocio e Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaEloPoloUnidadeNegocioGerenciaRegional(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();

		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.localidadeElo.id," 
				+ "histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");

		filtro.setTipoOrderBy("2,1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			Localidade eloPolo = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;
			
			//Pai do elo Polo(eloPolo -> unidade -> gerencia)
			int gerenciaAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[1]));
				
				GerenciaRegional gerencia = new GerenciaRegional();
				gerencia.setId(new Integer(arrayNumeracao[2]));
				
				if(unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				if(gerenciaAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					gerenciaAnterior = gerencia.getId();
				}
				
				//Mudou de Unidade
				if(unidadeNegocioAnterior != unidadeNegocio.getId().intValue()){
					
					filtro.setOpcaoTotalizacao(10);
					
					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					filtro.setUnidadeNegocio(uniAnterior);
					
					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocio(filtro));
				}
				
				//Mudou de Gerencia
				if(gerenciaAnterior != gerencia.getId().intValue()){
					
					filtro.setOpcaoTotalizacao(2);
					
					GerenciaRegional gereAnterior = new GerenciaRegional();
					gereAnterior.setId(gerenciaAnterior);
					filtro.setGerenciaRegional(gereAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaGerenciaRegional(filtro));
				}

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,arrayNumeracao[0]));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Localidade
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
			
				eloPolo = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					eloPolo.getGerenciaRegional().getId()+"-"+eloPolo.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					eloPolo.getUnidadeNegocio().getId()+"-"+eloPolo.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					eloPolo.getId()+"-"+eloPolo.getDescricao();

				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setGerenciaRegional(gerencia);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

					/*while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}*/
					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();					
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
				
				gerenciaAnterior = gerencia.getId();
				unidadeNegocioAnterior = unidadeNegocio.getId();
			}
			//Unidade de Negocio
			filtro.setOpcaoTotalizacao(10);
			
			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);
			
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocio(filtro));
			
			//Gerencia Regional
			filtro.setOpcaoTotalizacao(2);
			
			GerenciaRegional gereAnterior = new GerenciaRegional();
			gereAnterior.setId(gerenciaAnterior);
			filtro.setGerenciaRegional(gereAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaGerenciaRegional(filtro));
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia  
	 * 
	 * Elo Polo e Unidade de Negocio e Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaLocalidadeEloPoloUnidadeNegocioGerenciaRegional(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();

		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.localidade.id,histograma.localidadeElo.id," 
				+ "histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");

		filtro.setTipoOrderBy("4,3,2,1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			Localidade localidade = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;
			
			//Pai do elo Polo(eloPolo -> unidade -> gerencia)
			int gerenciaAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				Localidade eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[1]));

				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[2]));
				
				GerenciaRegional gerencia = new GerenciaRegional();
				gerencia.setId(new Integer(arrayNumeracao[3]));
				
				if(unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				if(gerenciaAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					gerenciaAnterior = gerencia.getId();
				}

				if(eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					eloPoloAnterior = eloPolo.getId();
				}
				
				//Mudou de Elo Polo
				if(eloPoloAnterior != eloPolo.getId().intValue()){
					
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					
					filtro.setOpcaoTotalizacao(13);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPolo(filtro));
				}
				
				//Mudou de Unidade
				if(unidadeNegocioAnterior != unidadeNegocio.getId().intValue()){
					
					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					
					filtro.setOpcaoTotalizacao(10);
					filtro.setUnidadeNegocio(uniAnterior);
					
					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocio(filtro));
				}
				
				//Mudou de Gerencia
				if(gerenciaAnterior != gerencia.getId().intValue()){
					
					GerenciaRegional gereAnterior = new GerenciaRegional();
					gereAnterior.setId(gerenciaAnterior);
					
					filtro.setOpcaoTotalizacao(2);
					filtro.setGerenciaRegional(gereAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaGerenciaRegional(filtro));
				}

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,arrayNumeracao[0]));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Localidade
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
				
				localidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					localidade.getGerenciaRegional().getId()+"-"+localidade.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					localidade.getUnidadeNegocio().getId()+"-"+localidade.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					localidade.getLocalidade().getId()+"-"+localidade.getLocalidade().getDescricao()
					+" / "+
					localidade.getId()+"-"+localidade.getDescricao();
				
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setGerenciaRegional(gerencia);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
*/					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
					
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
				
				gerenciaAnterior = gerencia.getId();
				unidadeNegocioAnterior = unidadeNegocio.getId();
				eloPoloAnterior = eloPolo.getId();
			}
			
			//Elo Polo
			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);

			filtro.setOpcaoTotalizacao(13);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPolo(filtro));
			
			//Unidade de Negocio
			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);

			filtro.setOpcaoTotalizacao(10);
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocio(filtro));
			
			//Gerencia Regional
			GerenciaRegional gereAnterior = new GerenciaRegional();
			gereAnterior.setId(gerenciaAnterior);

			filtro.setOpcaoTotalizacao(2);
			filtro.setGerenciaRegional(gereAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaGerenciaRegional(filtro));
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia - Unidade de Negocio
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaUnidadeNegocio(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		filtro.setEloPolo(null);
		filtro.setGerenciaRegional(null);
		filtro.setLocalidade(null);
		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();

		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.unidadeNegocio.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			UnidadeNegocio unidadeNegocio = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroUnidadeNegocio filtroUnidadeNegocio = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String idUnidadeNegocio = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(
					new ParametroSimples(FiltroUnidadeNegocio.ID,idUnidadeNegocio));
				filtroUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				
				// Recupera Unidade Negocio
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
				unidadeNegocio = 
					(UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					unidadeNegocio.getGerenciaRegional().getId()+"-"+unidadeNegocio.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					unidadeNegocio.getId()+"-"+unidadeNegocio.getNome();

				filtro.setUnidadeNegocio(unidadeNegocio);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						Collection colSubcategoria = this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() );
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
*/
					
					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();					
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(idUnidadeNegocio);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia  
	 * 
	 * Elo Polo e Unidade de Negocio
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaEloPoloUnidadeNegocio(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();

		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.localidadeElo.id,histograma.unidadeNegocio.id");
		filtro.setTipoOrderBy("2,1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			Localidade eloPolo = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;
			
			//Pai do elo Polo(eloPolo -> unidade)
			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[1]));
				
				if(unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				//Mudou de Unidade
				if(unidadeNegocioAnterior != unidadeNegocio.getId().intValue()){
					
					filtro.setOpcaoTotalizacao(10);
					
					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					filtro.setUnidadeNegocio(uniAnterior);
					
					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocio(filtro));
				}
				
				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,arrayNumeracao[0]));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Localidade
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
			
				eloPolo = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					eloPolo.getGerenciaRegional().getId()+"-"+eloPolo.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					eloPolo.getUnidadeNegocio().getId()+"-"+eloPolo.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					eloPolo.getId()+"-"+eloPolo.getDescricao();

				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
*/					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
					
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
				
				unidadeNegocioAnterior = unidadeNegocio.getId();
			}
			//Unidade de Negocio
			filtro.setOpcaoTotalizacao(10);
			
			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);
			
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocio(filtro));
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia  
	 * 
	 * Localidade ,Elo Polo e Unidade de Negocio
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaLocalidadeEloPoloUnidadeNegocio(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}
		

		filtro.setTipoGroupBy("histograma.localidade.id,histograma.localidadeElo.id," 
				+ "histograma.unidadeNegocio.id");

		filtro.setTipoOrderBy("3,2,1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			Localidade localidade = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;
			
			//Pai do elo Polo(eloPolo -> unidade -> gerencia)
			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				Localidade eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[1]));

				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[2]));
				
				if(unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				if(eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					eloPoloAnterior = eloPolo.getId();
				}
				
				//Mudou de Elo Polo
				if(eloPoloAnterior != eloPolo.getId().intValue()){
					
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					
					filtro.setOpcaoTotalizacao(13);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPolo(filtro));
				}
				
				//Mudou de Unidade
				if(unidadeNegocioAnterior != unidadeNegocio.getId().intValue()){
					
					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					
					filtro.setOpcaoTotalizacao(10);
					filtro.setUnidadeNegocio(uniAnterior);
					
					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocio(filtro));
				}
				
				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,arrayNumeracao[0]));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Localidade
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
				
				localidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					localidade.getGerenciaRegional().getId()+"-"+localidade.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					localidade.getUnidadeNegocio().getId()+"-"+localidade.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					localidade.getLocalidade().getId()+"-"+localidade.getLocalidade().getDescricao()
					+" / "+
					localidade.getId()+"-"+localidade.getDescricao();
				
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
*/					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
				
				unidadeNegocioAnterior = unidadeNegocio.getId();
				eloPoloAnterior = eloPolo.getId();
			}
			
			//Elo Polo
			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);

			filtro.setOpcaoTotalizacao(13);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPolo(filtro));
			
			
			//Unidade de Negocio
			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);

			filtro.setOpcaoTotalizacao(10);
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaUnidadeNegocio(filtro));
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia 
	 * 
	 * Elo Polo
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaEloPolo(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {

		filtro.setGerenciaRegional(null);
		filtro.setUnidadeNegocio(null);
		filtro.setLocalidade(null);
		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);

		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}
		

		filtro.setTipoGroupBy("histograma.localidadeElo.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			Localidade eloPolo = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String idEloPolo = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,idEloPolo));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Elo Polo
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
			
				eloPolo = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					eloPolo.getGerenciaRegional().getId()+"-"+eloPolo.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					eloPolo.getUnidadeNegocio().getId()+"-"+eloPolo.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					eloPolo.getId()+"-"+eloPolo.getDescricao();

				filtro.setEloPolo(eloPolo);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
*/					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(idEloPolo);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia  
	 * 
	 * Localidade e Elo Polo
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaLocalidadeEloPolo(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();

		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.localidade.id,histograma.localidadeElo.id");
		filtro.setTipoOrderBy("1,2");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			Localidade localidade = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;
			
			//Pai do elo Polo(eloPolo -> unidade -> gerencia)
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				Localidade eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[1]));

				if(eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					eloPoloAnterior = eloPolo.getId();
				}
				
				//Mudou de Elo Polo
				if(eloPoloAnterior != eloPolo.getId().intValue()){
					
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					
					filtro.setOpcaoTotalizacao(13);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPolo(filtro));
				}
				
				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,arrayNumeracao[0]));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Localidade
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
				
				localidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					localidade.getGerenciaRegional().getId()+"-"+localidade.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					localidade.getUnidadeNegocio().getId()+"-"+localidade.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					localidade.getLocalidade().getId()+"-"+localidade.getLocalidade().getDescricao()
					+" / "+
					localidade.getId()+"-"+localidade.getDescricao();
				
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
*/					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();					
					
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
				
				eloPoloAnterior = eloPolo.getId();
			}
			
			//Elo Polo
			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);

			filtro.setOpcaoTotalizacao(13);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPolo(filtro));
			
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia  
	 * 
	 * Setor Comercial ,Localidade e Elo
	 * 
	 * @author Rafael Pinto
	 * @date 20/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaSetorComercialLocalidadeEloPolo(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}
		

		filtro.setTipoGroupBy("histograma.codigoSetorComercial,histograma.localidade.id,histograma.localidadeElo.id");
		filtro.setTipoOrderBy("1,2,3");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			SetorComercial setorComercial = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroSetorComercial filtroSetorComercial = null;
			Collection colecaoConsulta = null;
			
			int localidadeAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;			
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				Localidade localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[1]));
				
				Localidade eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[2]));

				if(localidadeAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					localidadeAnterior = localidade.getId();
				}

				if(eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					eloPoloAnterior = eloPolo.getId();
				}

				//Mudou de Localidade
				if(localidadeAnterior != localidade.getId().intValue()){
					
					Localidade localAnterior = new Localidade();
					localAnterior.setId(localidadeAnterior);
					
					filtro.setOpcaoTotalizacao(16);
					filtro.setLocalidade(localAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidade(filtro));
				}
				
				//Mudou de Elo Polo
				if(eloPoloAnterior != eloPolo.getId().intValue()){
					
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					
					filtro.setOpcaoTotalizacao(13);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPolo(filtro));
				}
				
				
				filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,arrayNumeracao[0]));
				
				filtroSetorComercial.adicionarParametro(
						new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,localidade.getId()));

				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.gerenciaRegional");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.unidadeNegocio");
				
				// Recupera Setor Comercial
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				setorComercial = 
					(SetorComercial) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					setorComercial.getLocalidade().getGerenciaRegional().getId()+"-"+setorComercial.getLocalidade().getGerenciaRegional().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getUnidadeNegocio().getId()+"-"+setorComercial.getLocalidade().getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getLocalidade().getId()+"-"+setorComercial.getLocalidade().getLocalidade().getDescricao()
					+" / "+
					setorComercial.getLocalidade().getId()+"-"+setorComercial.getLocalidade().getDescricao()
					+" / "+
					setorComercial.getCodigo()+"-"+setorComercial.getDescricao();
				
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setSetorComercial(setorComercial);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
*/					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
				
				localidadeAnterior = localidade.getId();
				eloPoloAnterior = eloPolo.getId();
			}
			
			//Localidade
			Localidade localAnterior = new Localidade();
			localAnterior.setId(localidadeAnterior);
			
			filtro.setOpcaoTotalizacao(16);
			filtro.setLocalidade(localAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidade(filtro));
			
			//Elo Polo
			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);

			filtro.setOpcaoTotalizacao(13);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaEloPolo(filtro));
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}

	/**
	 * [UC0605] Emitir Histograma de Água Por Economia  
	 * 
	 * Setor Comercial ,Localidade
	 * 
	 * @author Rafael Pinto
	 * @date 20/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaSetorComercialLocalidade(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.codigoSetorComercial,histograma.localidade.id");
		filtro.setTipoOrderBy("1,2");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			SetorComercial setorComercial = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroSetorComercial filtroSetorComercial = null;
			Collection colecaoConsulta = null;
			
			int localidadeAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;			
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				Localidade localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[1]));
				
				if(localidadeAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					localidadeAnterior = localidade.getId();
				}

				//Mudou de Localidade
				if(localidadeAnterior != localidade.getId().intValue()){
					
					Localidade localAnterior = new Localidade();
					localAnterior.setId(localidadeAnterior);
					
					filtro.setOpcaoTotalizacao(16);
					filtro.setLocalidade(localAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidade(filtro));
				}
				
				filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,arrayNumeracao[0]));
				filtroSetorComercial.adicionarParametro(
						new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,localidade));
				
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.gerenciaRegional");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.unidadeNegocio");

				
				// Recupera Setor Comercial
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				setorComercial = 
					(SetorComercial) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					setorComercial.getLocalidade().getGerenciaRegional().getId()+"-"+setorComercial.getLocalidade().getGerenciaRegional().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getUnidadeNegocio().getId()+"-"+setorComercial.getLocalidade().getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getLocalidade().getId()+"-"+setorComercial.getLocalidade().getLocalidade().getDescricao()
					+" / "+
					setorComercial.getLocalidade().getId()+"-"+setorComercial.getLocalidade().getDescricao()
					+" / "+
					setorComercial.getCodigo()+"-"+setorComercial.getDescricao();
				
				filtro.setLocalidade(localidade);
				filtro.setSetorComercial(setorComercial);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
*/					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
				
				localidadeAnterior = localidade.getId();
			}
			
			//Localidade
			Localidade localAnterior = new Localidade();
			localAnterior.setId(localidadeAnterior);
			
			filtro.setOpcaoTotalizacao(16);
			filtro.setLocalidade(localAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidade(filtro));
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}

	/**
	 * [UC0605] Emitir Histograma de Água Por Economia 
	 * 
	 * Localidade
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaLocalidade(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {

		filtro.setSetorComercial(null);
		filtro.setEloPolo(null);
		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);

		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}
		

		filtro.setTipoGroupBy("histograma.localidade.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			Localidade localidade = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String idLocalidade = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,idLocalidade));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				
				// Recupera Elo Polo
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
			
				localidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					localidade.getGerenciaRegional().getId()+"-"+localidade.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					localidade.getUnidadeNegocio().getId()+"-"+localidade.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					localidade.getLocalidade().getId()+"-"+localidade.getLocalidade().getDescricao()
					+" / "+
					localidade.getId()+"-"+localidade.getDescricao();

				filtro.setLocalidade(localidade);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}*/
					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();					
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(idLocalidade);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia  
	 * 
	 * Quadra e Setor Comercial
	 * 
	 * @author Rafael Pinto
	 * @date 20/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaQuadraSetorComercialLocalidade(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();

		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.quadra.id,histograma.setorComercial.id,histograma.localidade.id");
		filtro.setTipoOrderBy("1,2,3");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			Quadra quadra = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroQuadra filtroQuadra = null;
			Collection colecaoConsulta = null;
			
			int localidadeAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int setorComercialAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setId(new Integer(arrayNumeracao[1]));
				
				Localidade localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[2]));
				
				if(setorComercialAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					setorComercialAnterior = setorComercial.getId();
				}

				if(localidadeAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					localidadeAnterior = localidade.getId();
				}

				//Mudou de Setor Comercial
				if(setorComercialAnterior != setorComercial.getId().intValue()){

					SetorComercial setorAnterior = new SetorComercial();
					setorAnterior.setId(setorComercialAnterior);

					filtro.setOpcaoTotalizacao(19);
					filtro.setSetorComercial(setorAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaSetorComercial(filtro));
				}

				//Mudou de Localidade
				if(localidadeAnterior != localidade.getId().intValue()){
					
					Localidade localAnterior = new Localidade();
					localAnterior.setId(localidadeAnterior);

					filtro.setOpcaoTotalizacao(16);
					filtro.setLocalidade(localAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidade(filtro));
				}
				

				filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(
					new ParametroSimples(FiltroQuadra.ID,arrayNumeracao[0]));
				
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
				
				// Recupera Quadra
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
				
				quadra = 
					(Quadra) Util.retonarObjetoDeColecao(colecaoConsulta);
				
				String descricaoOpcaoTotalizacao = 
					quadra.getSetorComercial().getLocalidade().getId()+"-"+quadra.getSetorComercial().getLocalidade().getDescricao()
					+" / "+
					quadra.getSetorComercial().getCodigo()+"-"+quadra.getSetorComercial().getDescricao()
					+" / "+
					quadra.getNumeroQuadra();
				
				filtro.setQuadra(quadra);
				filtro.setSetorComercial(setorComercial);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);

				localidadeAnterior = localidade.getId();
				setorComercialAnterior = setorComercial.getId();			
			}
			
			//Setor Comercial
			SetorComercial setorAnterior = new SetorComercial();
			setorAnterior.setId(setorComercialAnterior);

			filtro.setOpcaoTotalizacao(19);
			filtro.setSetorComercial(setorAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaSetorComercial(filtro));
			
			//Mudou Localidade
			Localidade localAnterior = new Localidade();
			localAnterior.setId(localidadeAnterior);

			filtro.setOpcaoTotalizacao(16);
			filtro.setLocalidade(localAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaLocalidade(filtro));
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia 
	 * 
	 * Setor Comercial
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaSetorComercial(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {

		filtro.setQuadra(null);
		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.setorComercial.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			SetorComercial setorComercial = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroSetorComercial filtroSetorComercial = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String idSetorComercial = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.ID,idSetorComercial));

				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.gerenciaRegional");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.unidadeNegocio");

				// Recupera Setor Comercial
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
				setorComercial = 
					(SetorComercial) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					setorComercial.getLocalidade().getGerenciaRegional().getId()+"-"+setorComercial.getLocalidade().getGerenciaRegional().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getUnidadeNegocio().getId()+"-"+setorComercial.getLocalidade().getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getLocalidade().getId()+"-"+setorComercial.getLocalidade().getLocalidade().getDescricao()
					+" / "+
					setorComercial.getLocalidade().getId()+"-"+setorComercial.getLocalidade().getDescricao()
					+" / "+
					setorComercial.getCodigo()+"-"+setorComercial.getDescricao();

				filtro.setSetorComercial(setorComercial);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);
					
					if ( filtro.getIndicadorTarifaCategoria().intValue() == 1 ){
						
						while (iteraFaixas.hasNext()) {
							
							ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
							
							EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
								this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
			
							emitirHistograma.setDescricaoCategoria(descricaoCategoria);					
							limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
							
							totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
							totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
							totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
							totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
							totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
							
							totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
							totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
							totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
							totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
							totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
						}				
					} else {
						FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.CATEGORIA_ID, idCategoria ) );
						filtroSubcategoria.adicionarParametro( new ParametroSimples( FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA, ConstantesSistema.SIM ) );
						
						filtroSubcategoria.setCampoOrderBy( "id" );				
						Collection colSubcategoria = new ArrayList();
						
						Subcategoria subcategoria0 = new Subcategoria();
						subcategoria0.setId( new Integer( 0 ) );
						Categoria categoriaX = new Categoria();
						categoriaX.setId( Integer.parseInt( idCategoria ) );
						subcategoria0.setCategoria( categoriaX );
						subcategoria0.setDescricao( "0 - SEM SUBCATEGORIA" );
						
						colSubcategoria.add( subcategoria0 );
						colSubcategoria.addAll( this.getControladorUtil().pesquisar( filtroSubcategoria, Subcategoria.class.getName() ) );
						
						Iterator iteSubcategoria = colSubcategoria.iterator();
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						
						while ( iteSubcategoria.hasNext() ){			
							
							Subcategoria subcategoria = (Subcategoria)iteSubcategoria.next();					
							filtro.setSubcategoria( subcategoria );					
							
							emitirHistograma.setDescricaoSubcategoria( subcategoria.getDescricao() );					
							
							iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
							
							Integer totalEconomiasMedidoSubcategoria = 0;
							Integer totalVolumeConsumoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoMedidoSubcategoria = 0;
							Integer totalLigacaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaMedidoSubcategoria = new BigDecimal(0.0);
							
							Integer totalEconomiasNaoMedidoSubcategoria = 0;
							Integer totalVolumeConsumoNaoMedidoSubcategoria = 0;
							Integer totalVolumeFaturadoNaoMedidoSubcategoria = 0;
							Integer totalLigacaoNaoMedidoSubcategoria = 0;
							BigDecimal totalReceitaNaoMedidoSubcategoria = new BigDecimal(0.0);
							
							while (iteraFaixas.hasNext()) {
								
								ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
								
								EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
									this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
								
								detalhe.setDescricaoSubcategoria( subcategoria.getDescricao() );
				
								limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
								
								colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
								
								// Guardamos os totais por subcategoria
								totalEconomiasMedidoSubcategoria += detalhe.getEconomiasMedido();
								totalVolumeConsumoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedidoSubcategoria = totalReceitaMedidoSubcategoria.add(detalhe.getReceitaMedido());
								totalLigacaoMedidoSubcategoria += detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedidoSubcategoria += detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedidoSubcategoria += detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedidoSubcategoria += detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedidoSubcategoria = totalReceitaNaoMedidoSubcategoria.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedidoSubcategoria += detalhe.getLigacoesNaoMedido();
								
								totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
								totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
								totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
								totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
								totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
								
								totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
								totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
								totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
								totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
								totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
							}
							
							EmitirHistogramaAguaEconomiaDetalheHelper totalSubcategoria =
								new EmitirHistogramaAguaEconomiaDetalheHelper ();

							totalSubcategoria.setDescricaoFaixa("TOTAL " + subcategoria.getDescricao() );
							
							totalSubcategoria.setEconomiasMedido(totalEconomiasMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaMedido(totalVolumeConsumoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaMedido(totalVolumeFaturadoMedidoSubcategoria);
							totalSubcategoria.setReceitaMedido(totalReceitaMedidoSubcategoria);
							totalSubcategoria.setLigacoesMedido(totalLigacaoMedidoSubcategoria);
							
							totalSubcategoria.setEconomiasNaoMedido(totalEconomiasNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeConsumoFaixaNaoMedido(totalVolumeConsumoNaoMedidoSubcategoria);
							totalSubcategoria.setVolumeFaturadoFaixaNaoMedido(totalVolumeFaturadoNaoMedidoSubcategoria);
							totalSubcategoria.setReceitaNaoMedido(totalReceitaNaoMedidoSubcategoria);
							totalSubcategoria.setLigacoesNaoMedido(totalLigacaoNaoMedidoSubcategoria);
							
							colecaoEmitirHistogramaAguaEconomiaDetalhe.add(totalSubcategoria);
						}				
					}
					

/*					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
*/					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(idSetorComercial);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}

	/**
	 * [UC0605] Emitir Histograma de Água Por Economia  
	 * 
	 * Quadra e Setor Comercial
	 * 
	 * @author Rafael Pinto
	 * @date 20/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaQuadraSetorComercial(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.quadra.id,histograma.setorComercial.id");
		filtro.setTipoOrderBy("2,1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			Quadra quadra = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroQuadra filtroQuadra = null;
			Collection colecaoConsulta = null;
			
			int setorComercialAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setId(new Integer(arrayNumeracao[1]));
				
				if(setorComercialAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					setorComercialAnterior = setorComercial.getId();
				}

				//Mudou de Setor Comercial
				if(setorComercialAnterior != setorComercial.getId().intValue()){

					SetorComercial setorAnterior = new SetorComercial();
					setorAnterior.setId(setorComercialAnterior);

					filtro.setOpcaoTotalizacao(19);
					filtro.setSetorComercial(setorAnterior);

					colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaSetorComercial(filtro));
				}

				filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(
					new ParametroSimples(FiltroQuadra.ID,arrayNumeracao[0]));
				
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
				
				// Recupera Quadra
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
				
				quadra = 
					(Quadra) Util.retonarObjetoDeColecao(colecaoConsulta);
				
				String descricaoOpcaoTotalizacao = 
					quadra.getSetorComercial().getLocalidade().getId()+"-"+quadra.getSetorComercial().getLocalidade().getDescricao()
					+" / "+
					quadra.getSetorComercial().getCodigo()+"-"+quadra.getSetorComercial().getDescricao()
					+" / "+
					quadra.getNumeroQuadra();
				
				filtro.setQuadra(quadra);
				filtro.setSetorComercial(setorComercial);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
				
				setorComercialAnterior = setorComercial.getId();			
			}
			
			//Setor Comercial
			SetorComercial setorAnterior = new SetorComercial();
			setorAnterior.setId(setorComercialAnterior);

			filtro.setOpcaoTotalizacao(19);
			filtro.setSetorComercial(setorAnterior);

			colecaoEmitirHistogramaAguaEconomia.addAll(this.emitirHistogramaAguaEconomiaSetorComercial(filtro));
			
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia 
	 * 
	 * Quadra
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaAguaEconomiaHelper> emitirHistogramaAguaEconomiaQuadra(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException {

		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);

		Collection<EmitirHistogramaAguaEconomiaHelper> colecaoEmitirHistogramaAguaEconomia = 
			new ArrayList<EmitirHistogramaAguaEconomiaHelper>();
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}

		filtro.setTipoGroupBy("histograma.quadra.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaAguaEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaAguaEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaAguaEconomiaDetalheHelper> colecaoEmitirHistogramaAguaEconomiaDetalhe = null;
			
			Quadra quadra = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroQuadra filtroQuadra = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String idQuadra = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID,idQuadra));

				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");

				// Recupera Quadra
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
			
				quadra = 
					(Quadra) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					quadra.getSetorComercial().getLocalidade().getId()+"-"+quadra.getSetorComercial().getLocalidade().getDescricao()
					+" / "+
					quadra.getSetorComercial().getCodigo()+"-"+quadra.getSetorComercial().getDescricao()
					+" / "+
					quadra.getNumeroQuadra();

				filtro.setQuadra(quadra);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					
					colecaoEmitirHistogramaAguaEconomiaDetalhe = new ArrayList<EmitirHistogramaAguaEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaAguaEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaAguaEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaAguaEconomiaDetalhe(colecaoEmitirHistogramaAguaEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);

					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaAguaEconomiaHelper) hashMapTotalGeral.get(idQuadra);
				
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaAguaEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaAguaEconomia;
		
	}

	/**
	 * [UC0605] Emitir Histograma de Água Por Economia
	 * 
	 * Monta o objeto EmitirHistogramaAguaEconomiaDetalheHelper
	 * 
	 * @author Rafael Pinto
	 * @date 19/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * @param ConsumoFaixaCategoria
	 * 
	 * @return EmitirHistogramaAguaEconomiaDetalheHelper
	 * @throws ControladorException
	 */
	private EmitirHistogramaAguaEconomiaDetalheHelper montarEmitirHistogramaAguaEconomiaDetalheHelper(
		FiltrarEmitirHistogramaAguaEconomiaHelper filtro,
		ConsumoFaixaCategoria consumoFaixaCategoria,
		int limiteSuperiorFaixaAnterior)
		throws ControladorException {

		EmitirHistogramaAguaEconomiaDetalheHelper detalhe = new EmitirHistogramaAguaEconomiaDetalheHelper();
		
		BigDecimal consumoMedio = null;
		BigDecimal consumoExcedente = null;

		filtro.setConsumoFaixaCategoria(consumoFaixaCategoria);

		detalhe.setDescricaoFaixa(consumoFaixaCategoria.getNumeroFaixaInicio()+" a "+consumoFaixaCategoria.getNumeroFaixaFim()); 
								
		//Pesquisa com indicador de Hidrometro SIM
		filtro.setMedicao(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		Object[] valoresSomatorio = null;
		boolean temMedido = false;
		boolean temNaoMedido = false;
		
		try {						
			valoresSomatorio = 
				this.repositorioFaturamento.pesquisarEmitirHistogramaAguaEconomia(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		if(valoresSomatorio != null){
			
			detalhe.setEconomiasMedido((Integer)valoresSomatorio[2]);
			detalhe.setVolumeConsumoFaixaMedido((Integer)valoresSomatorio[3]);
			detalhe.setVolumeFaturadoFaixaMedido((Integer)valoresSomatorio[4]);
			detalhe.setReceitaMedido((BigDecimal)valoresSomatorio[5]);
			detalhe.setLigacoesMedido((Integer)valoresSomatorio[6]);
			
			consumoMedio = 
				new BigDecimal(detalhe.getVolumeConsumoFaixaMedido()).divide(
					new BigDecimal(detalhe.getEconomiasMedido()),4, BigDecimal.ROUND_HALF_UP);
			
			detalhe.setConsumoMedioMedido(consumoMedio);
			
			if(limiteSuperiorFaixaAnterior > 0){
				consumoExcedente = 
					detalhe.getConsumoMedioMedido().subtract(new BigDecimal(limiteSuperiorFaixaAnterior));
			}
			
			detalhe.setConsumoExcedenteMedido(consumoExcedente);
			temMedido = true;
		}else{
			detalhe.setConsumoMedioMedido(new BigDecimal(0.0));
		}
		
		//Pesquisa com indicador de Hidrometro Nao
		filtro.setMedicao(ConstantesSistema.INDICADOR_USO_DESATIVO);
		
		try {						
			valoresSomatorio = 
				this.repositorioFaturamento.pesquisarEmitirHistogramaAguaEconomia(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		if(valoresSomatorio != null){
			
			detalhe.setEconomiasNaoMedido( (Integer)valoresSomatorio[2]);
			detalhe.setVolumeConsumoFaixaNaoMedido((Integer)valoresSomatorio[3]);
			detalhe.setVolumeFaturadoFaixaNaoMedido((Integer)valoresSomatorio[4]);
			detalhe.setReceitaNaoMedido((BigDecimal)valoresSomatorio[5]);
			detalhe.setLigacoesNaoMedido((Integer)valoresSomatorio[6]);
			
			consumoMedio = 
				new BigDecimal(detalhe.getVolumeConsumoFaixaNaoMedido()).divide(
					new BigDecimal(detalhe.getEconomiasNaoMedido()),4, BigDecimal.ROUND_HALF_UP);
			
			detalhe.setConsumoMedioNaoMedido(consumoMedio);

			if(limiteSuperiorFaixaAnterior > 0){
				consumoExcedente = 
					detalhe.getConsumoMedioNaoMedido().subtract(new BigDecimal(limiteSuperiorFaixaAnterior));
			}
			detalhe.setConsumoExcedenteNaoMedido(consumoExcedente);
			
			temNaoMedido = true;
			
		}else{
			detalhe.setConsumoMedioNaoMedido(new BigDecimal(0.0));
		}
		
		//Caso tenha algum valor então exite histograma para adcionar na colecao
		detalhe.setExisteHistograma(temMedido || temNaoMedido);
		
		return detalhe;
	}
	
	/**
	 * [UC0605] Emitir Histograma de Água Por Economia
	 * 
	 * Agrupa as chaves para totalizacao
	 * 
	 * @author Rafael Pinto
	 * @date 18/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return LinkedHashMap
	 * @throws ControladorException
	 */
	private LinkedHashMap montarEmitirHistogramaAguaEconomiaAgruparChaves(FiltrarEmitirHistogramaAguaEconomiaHelper filtro) 
		throws ControladorException {
		
		LinkedHashMap hashMapTotalizacao = new LinkedHashMap();
		Collection<Object[]> colecao = null;
		
		try {
			
			//filtro.setMedicao(ConstantesSistema.INDICADOR_USO_ATIVO);
			
			colecao =
				this.repositorioFaturamento.pesquisarEmitirHistogramaAguaEconomiaChavesAgrupadas(filtro);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		if (colecao != null && !colecao.isEmpty()) {
			
			Iterator iterator = colecao.iterator();
			
			while (iterator.hasNext()) {

				String tipoTotalizacao = "0";
				
				int chaveQuadra = 0;
				int chaveSetorComercial = 0;
				int chaveLocalidade = 0;
				int chaveElo = 0;
				int chaveUnidade = 0;
				int chaveGerencia = 0;
				
				if(filtro.getOpcaoTotalizacao() == 2 ||
					filtro.getOpcaoTotalizacao() == 6 ){
					
					chaveGerencia = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveGerencia;
				
				}else if(filtro.getOpcaoTotalizacao() == 3 ||
						filtro.getOpcaoTotalizacao() == 7 ){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveUnidade = (Integer) objeto[0];
					chaveGerencia = (Integer) objeto[1];
					
					tipoTotalizacao = chaveUnidade+";"+chaveGerencia;
				
				}else if(filtro.getOpcaoTotalizacao() == 4 ||
						filtro.getOpcaoTotalizacao() == 8){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveElo = (Integer) objeto[0];
					chaveUnidade = (Integer) objeto[1];
					chaveGerencia = (Integer) objeto[2];
					
					tipoTotalizacao = chaveElo+";"+chaveUnidade+";"+chaveGerencia;
					
				}else if(filtro.getOpcaoTotalizacao() == 5 ||
						filtro.getOpcaoTotalizacao() == 9){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveLocalidade = (Integer) objeto[0];
					chaveElo = (Integer) objeto[1];
					chaveUnidade = (Integer) objeto[2];
					chaveGerencia = (Integer) objeto[3];
					
					tipoTotalizacao = 
						chaveLocalidade+";"+chaveElo+";"+chaveUnidade+";"+chaveGerencia;
					
					
				}else if(filtro.getOpcaoTotalizacao() == 10){

					chaveUnidade = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveUnidade;
					
				}else if(filtro.getOpcaoTotalizacao() == 11){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveElo = (Integer) objeto[0];
					chaveUnidade = (Integer) objeto[1];
					
					tipoTotalizacao = chaveElo+";"+chaveUnidade;
					
				}else if(filtro.getOpcaoTotalizacao() == 12){
					
					Object[] objeto = (Object[]) iterator.next();

					chaveLocalidade = (Integer) objeto[0];
					chaveElo = (Integer) objeto[1];
					chaveUnidade = (Integer) objeto[2];
					
					tipoTotalizacao = chaveLocalidade+";"+chaveElo+";"+chaveUnidade;
					
				}else if(filtro.getOpcaoTotalizacao() == 13){
					
					chaveElo = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveElo;
					
				}else if(filtro.getOpcaoTotalizacao() == 14){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveLocalidade = (Integer) objeto[0];
					chaveElo = (Integer) objeto[1];

					tipoTotalizacao = chaveLocalidade+";"+chaveElo;
					
				}else if(filtro.getOpcaoTotalizacao() == 15){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveSetorComercial = (Integer) objeto[0];
					chaveLocalidade = (Integer) objeto[1];
					chaveElo = (Integer) objeto[2];
					
					tipoTotalizacao = 
						chaveSetorComercial+";"+chaveLocalidade+";"+chaveElo;
					
				}else if(filtro.getOpcaoTotalizacao() == 16){
					
					chaveLocalidade = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveLocalidade;

				}else if(filtro.getOpcaoTotalizacao() == 17){
					Object[] objeto = (Object[]) iterator.next();
					
					chaveSetorComercial = (Integer) objeto[0];
					chaveLocalidade = (Integer) objeto[1];
					
					tipoTotalizacao = 
						chaveSetorComercial+";"+chaveLocalidade;

				}else if(filtro.getOpcaoTotalizacao() == 18){
					Object[] objeto = (Object[]) iterator.next();

					chaveQuadra = (Integer) objeto[0];
					chaveSetorComercial = (Integer) objeto[1];
					chaveLocalidade = (Integer) objeto[2];
					
					tipoTotalizacao = 
						chaveQuadra+";"+chaveSetorComercial+";"+chaveLocalidade;
					
				}else if(filtro.getOpcaoTotalizacao() == 19){
					
					chaveSetorComercial = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveSetorComercial;
					
				}else if(filtro.getOpcaoTotalizacao() == 20){

					Object[] objeto = (Object[]) iterator.next();

					chaveQuadra = (Integer) objeto[0];
					chaveSetorComercial = (Integer) objeto[1];
					
					tipoTotalizacao = 
						chaveQuadra+";"+chaveSetorComercial;
					
				
				}else if(filtro.getOpcaoTotalizacao() == 21){
					
					chaveQuadra = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveQuadra;
					
				}

				
				EmitirHistogramaAguaEconomiaHelper emitirHistograma = new EmitirHistogramaAguaEconomiaHelper();
				
				hashMapTotalizacao.put(tipoTotalizacao,emitirHistograma);
			}
		}
		
		return hashMapTotalizacao;
	}
	

	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	public Collection<EmitirHistogramaEsgotoHelper> pesquisarEmitirHistogramaEsgoto(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = 
			new ArrayList<EmitirHistogramaEsgotoHelper>();

		int opcaoTotalizacao = filtro.getOpcaoTotalizacao();

		FiltrarEmitirHistogramaEsgotoHelper filtroClone = 
			new FiltrarEmitirHistogramaEsgotoHelper(filtro);

		switch (opcaoTotalizacao) {

		// Estado
		case 1:

			colecaoEmitirHistogramaEsgoto = this
					.pesquisarEmitirHistogramaEsgotoEstado(filtro);
			break;

		// Estado por Gerencia Regional
		case 2:

			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoGerenciaRegional(filtro);
			colecaoEmitirHistogramaEsgoto.addAll(this.pesquisarEmitirHistogramaEsgotoEstado(filtroClone));
			break;

		
		// Estado por Unidade Negocio
		case 3:

			colecaoEmitirHistogramaEsgoto = 
				this.emitirHistogramaEsgotoUnidadeNegocioGerenciaRegional(filtro);
			colecaoEmitirHistogramaEsgoto.addAll(this.pesquisarEmitirHistogramaEsgotoEstado(filtroClone));
			break;
		
		// Estado por Elo Polo
		case 4:

			colecaoEmitirHistogramaEsgoto = 
				this.emitirHistogramaEsgotoEloUnidadeNegocioGerenciaRegional(filtro);
			colecaoEmitirHistogramaEsgoto.addAll(this.pesquisarEmitirHistogramaEsgotoEstado(filtroClone));
			break;

		// Estado por Localidade
		case 5:

			colecaoEmitirHistogramaEsgoto = 
				this.emitirHistogramaEsgotoLocalidadeEloUnidadeNegocioGerenciaRegional(filtro);
			colecaoEmitirHistogramaEsgoto.addAll(this.pesquisarEmitirHistogramaEsgotoEstado(filtroClone));
			break;

		// Gerência Regional
		case 6:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoGerenciaRegional(filtro);
			break;

		// Gerência Regional por Unidade Negocio
		case 7:
			colecaoEmitirHistogramaEsgoto = 
				this.emitirHistogramaEsgotoUnidadeNegocioGerenciaRegional(filtro);
			break;

		// Gerência Regional por Elo
		case 8:
			colecaoEmitirHistogramaEsgoto = 
				this.emitirHistogramaEsgotoEloUnidadeNegocioGerenciaRegional(filtro);
			break;

		// Gerência Regional por Localidade
		case 9:
			colecaoEmitirHistogramaEsgoto = 
				this.emitirHistogramaEsgotoLocalidadeEloUnidadeNegocioGerenciaRegional(filtro);
			break;

		// Unidade de Negocio
		case 10:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoUnidadeNegocio(filtro);
			break;

		// Unidade de Negocio por Elo
		case 11:

			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoEloUnidadeNegocio(filtro);
			break;

		// Unidade de Negocio por Localidade
		case 12:
			colecaoEmitirHistogramaEsgoto = 
				this.emitirHistogramaEsgotoLocalidadeEloUnidadeNegocio(filtro);
			break;

		// Elo Polo
		case 13:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoElo(filtro);
			break;

		// Elo Polo Por Localidade
		case 14:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoLocalidadeElo(filtro);
			break;

		// Elo Polo Por Setor Comercial
		case 15:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoSetorComercialLocalidadeElo(filtro);
			break;

		// Localidade
		case 16:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoLocalidade(filtro);
			break;

		// Localidade Por Setor Comercial
		case 17:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoSetorComercialLocalidade(filtro);
			break;

		// Localidade Por Quadra
		case 18:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoQuadraLocalidade(filtro);
			break;

		// Setor Comercial
		case 19:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoSetorComercial(filtro);
			break;

		// Setor Comercial Por Quadra
		case 20:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoQuadraSetorComercial(filtro);
			break;

		// Quadra
		case 21:
			colecaoEmitirHistogramaEsgoto = this.emitirHistogramaEsgotoQuadra(filtro);
			break;

		}

		return colecaoEmitirHistogramaEsgoto;
	}
	
	/**
	 * [UC0600] Emitir Histograma de Esgoto - ESTADO
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> pesquisarEmitirHistogramaEsgotoEstado(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();
		
		
		if ( filtro.getIndicadorTarifaCategoria() != null && 
				filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ){
			filtro.setTipoGroupBy(" subcategoria.id, subcategoria.descricao ");
		}

		filtro.setOpcaoTotalizacao(1);

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(
						filtro.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(
						filtro.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro.setConsumoFaixaLigacaoIntervaloMedido(
					filtro.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(
					filtro.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = 
				this.repositorioFaturamento.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(filtro, colecaoTotalGeral);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {
			
			String descricaoOpcaoTotalizacao = 
				this.getControladorUtil().pesquisarParametrosDoSistema().getNomeEstado();

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			while (iter.hasNext()) {

				String estado = (String) iter.next();

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(estado);

				HashMap mapTotalizacaoCategoria = 
					this.montarEmitirHistogramaEsgotoTotalGeralCategoria(
						emitirHistogramaEsgotoHelperTotalGeral.getColecaoEmitirHistogramaEsgotoDetalhe());

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
						colecaoConsumoFaixaLigacaoMedido, 
						filtro,
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeLigacoes(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalValorFaturado(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeVolumeTotal(),
						colecaoEmitirHistogramaEsgoto,
						descricaoOpcaoTotalizacao,
						ConstantesSistema.INDICADOR_USO_ATIVO,
						mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
						colecaoConsumoFaixaLigacaoNaoMedido, 
						filtro,
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeLigacoes(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalValorFaturado(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeVolumeTotal(),
						colecaoEmitirHistogramaEsgoto,
						descricaoOpcaoTotalizacao,
						ConstantesSistema.INDICADOR_USO_DESATIVO,
						mapTotalizacaoCategoria);
				}

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto.add(emitirHistogramaEsgotoHelperTotalGeral);

			}
		}

		return colecaoEmitirHistogramaEsgoto;
	}
	
	/**
	 * [UC0600] Emitir Histograma de Esgoto - Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoGerenciaRegional(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ){
			filtro.setTipoGroupBy(" histograma.gerenciaRegional.id , subcategoria.descricao  ");
		} else {
			filtro.setTipoGroupBy("histograma.gerenciaRegional.id ");
		}
		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		filtro.setEloPolo(null);
		filtro.setUnidadeNegocio(null);
		filtro.setLocalidade(null);
		filtro.setConsumoFaixaLigacao(null);

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			FiltroGerenciaRegional filtroGerencia = null;
			Collection colecaoGerencia = null;
			GerenciaRegional gerenciaRegional = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			while (iter.hasNext()) {

				String gerencia = (String) iter.next();

				gerenciaRegional = new GerenciaRegional();
				gerenciaRegional.setId(new Integer(gerencia));

				filtro.setGerenciaRegional(gerenciaRegional);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(gerencia);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroGerencia = new FiltroGerenciaRegional();
				filtroGerencia.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.ID, gerencia));

				// Recupera Gerencia Regional
				colecaoGerencia = this.getControladorUtil().pesquisar(
						filtroGerencia, GerenciaRegional.class.getName());

				gerenciaRegional = (GerenciaRegional) Util
						.retonarObjetoDeColecao(colecaoGerencia);

				String descricaoOpcaoTotalizacao = gerenciaRegional.getId()
						+ " - " + gerenciaRegional.getNome();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
						colecaoConsumoFaixaLigacaoMedido, 
						filtro,
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeLigacoes(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalValorFaturado(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeVolumeTotal(),
						colecaoEmitirHistogramaEsgoto,
						descricaoOpcaoTotalizacao,
						ConstantesSistema.INDICADOR_USO_ATIVO,
						mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
						colecaoConsumoFaixaLigacaoNaoMedido, 
						filtro,
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeLigacoes(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalValorFaturado(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeVolumeTotal(),
						colecaoEmitirHistogramaEsgoto,
						descricaoOpcaoTotalizacao,
						ConstantesSistema.INDICADOR_USO_DESATIVO,
						mapTotalizacaoCategoria);
				}
				
				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto.add(emitirHistogramaEsgotoHelperTotalGeral);

			}

		}

		return colecaoEmitirHistogramaEsgoto;
	}
	
	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Unidade Negocio e Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoUnidadeNegocioGerenciaRegional(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ) {
			filtro.setTipoGroupBy("histograma.unidadeNegocio.id, subcategoria.descricao , histograma.gerenciaRegional.id ");
		} else {
			filtro.setTipoGroupBy("histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");	
		}
		

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			int gerenciaAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			FiltroUnidadeNegocio filtroUnidade = null;
			Collection colecaoUnidade = null;
			UnidadeNegocio unidade = null;

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				String[] arrayNumeracao = chave.split(";");

				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[0]));

				GerenciaRegional gerencia = new GerenciaRegional();
				gerencia.setId(new Integer(arrayNumeracao[1]));

				filtro.setUnidadeNegocio(unidadeNegocio);

				if (gerenciaAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					gerenciaAnterior = gerencia.getId();
				}

				// Mudou de Gerencia
				if (gerenciaAnterior != gerencia.getId().intValue()) {

					filtro.setOpcaoTotalizacao(2);

					GerenciaRegional gereAnterior = new GerenciaRegional();
					gereAnterior.setId(gerenciaAnterior);
					filtro.setGerenciaRegional(gereAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoGerenciaRegional(filtro));
				}

				filtro.setOpcaoTotalizacao(3);
				filtro.setGerenciaRegional(gerencia);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroUnidade = new FiltroUnidadeNegocio();
				filtroUnidade.adicionarParametro(new ParametroSimples(
						FiltroUnidadeNegocio.ID, unidadeNegocio.getId()));

				filtroUnidade
						.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

				// Recupera Unidade Negocio
				colecaoUnidade = this.getControladorUtil().pesquisar(
						filtroUnidade, UnidadeNegocio.class.getName());

				unidade = (UnidadeNegocio) Util
						.retonarObjetoDeColecao(colecaoUnidade);

				String descricaoOpcaoTotalizacao = unidade
						.getGerenciaRegional().getId()
						+ "-"
						+ unidade.getGerenciaRegional().getNomeAbreviado()
						+ " / " + unidade.getId() + " - " + unidade.getNome();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
						colecaoConsumoFaixaLigacaoMedido, 
						filtro,
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeLigacoes(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalValorFaturado(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeVolumeTotal(),
						colecaoEmitirHistogramaEsgoto,
						descricaoOpcaoTotalizacao,
						ConstantesSistema.INDICADOR_USO_ATIVO,
						mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
						colecaoConsumoFaixaLigacaoNaoMedido, 
						filtro,
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeLigacoes(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalValorFaturado(),
						emitirHistogramaEsgotoHelperTotalGeral.getTotalQuantidadeVolumeTotal(),
						colecaoEmitirHistogramaEsgoto,
						descricaoOpcaoTotalizacao,
						ConstantesSistema.INDICADOR_USO_DESATIVO,
						mapTotalizacaoCategoria);
				}

				filtro.setMedicao(indicadorMedicao);

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto.add(emitirHistogramaEsgotoHelperTotalGeral);

				gerenciaAnterior = gerencia.getId();

			}

			filtro.setOpcaoTotalizacao(2);

			GerenciaRegional gereAnterior = new GerenciaRegional();
			gereAnterior.setId(gerenciaAnterior);
			filtro.setGerenciaRegional(gereAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoGerenciaRegional(filtro));

		}

		return colecaoEmitirHistogramaEsgoto;
	}
	
	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Elo e Unidade Negocio e Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoEloUnidadeNegocioGerenciaRegional(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ) {
			filtro.setTipoGroupBy(" histograma.localidadeEelo.id,"
					+ "subcategoria.descricao, histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");
		
		} else {
		filtro
				.setTipoGroupBy("histograma.localidadeElo.id,"
						+ "histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");
		}
		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			int gerenciaAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoLocalidade = null;

			Localidade eloPolo = null;
			UnidadeNegocio unidadeNegocio = null;
			GerenciaRegional gerencia = null;

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				String[] arrayNumeracao = chave.split(";");

				eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[0]));

				unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[1]));

				gerencia = new GerenciaRegional();
				gerencia.setId(new Integer(arrayNumeracao[2]));

				if (unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				if (gerenciaAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					gerenciaAnterior = gerencia.getId();
				}

				filtro.setMedicao(indicadorMedicao);
				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setGerenciaRegional(gerencia);

				// Mudou de Unidade
				if (unidadeNegocioAnterior != unidadeNegocio.getId().intValue()) {

					filtro.setOpcaoTotalizacao(10);

					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					filtro.setUnidadeNegocio(uniAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoUnidadeNegocio(filtro));
				}

				filtro.setMedicao(indicadorMedicao);

				// Mudou de Gerencia
				if (gerenciaAnterior != gerencia.getId().intValue()) {

					GerenciaRegional gereAnterior = new GerenciaRegional();
					gereAnterior.setId(gerenciaAnterior);
					filtro.setGerenciaRegional(gereAnterior);

					filtro.setOpcaoTotalizacao(2);
					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoGerenciaRegional(filtro));
				}

				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setGerenciaRegional(gerencia);
				filtro.setMedicao(indicadorMedicao);
				filtro.setOpcaoTotalizacao(4);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, eloPolo.getId()));

				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

				// Recupera Localidade
				colecaoLocalidade = this.getControladorUtil().pesquisar(
						filtroLocalidade, Localidade.class.getName());

				eloPolo = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				String descricaoOpcaoTotalizacao = eloPolo
						.getGerenciaRegional().getId()
						+ "-"
						+ eloPolo.getGerenciaRegional().getNomeAbreviado()
						+ " / "
						+ eloPolo.getUnidadeNegocio().getId()
						+ "-"
						+ eloPolo.getUnidadeNegocio().getNomeAbreviado()
						+ " / "
						+ eloPolo.getId()
						+ "-"
						+ eloPolo.getDescricao();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}

				filtro.setMedicao(indicadorMedicao);

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

				gerenciaAnterior = gerencia.getId();
				unidadeNegocioAnterior = unidadeNegocio.getId();

			}

			filtro.setMedicao(indicadorMedicao);

			filtro.setOpcaoTotalizacao(10);
			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this.emitirHistogramaEsgotoUnidadeNegocio(filtro));

			filtro.setMedicao(indicadorMedicao);

			filtro.setOpcaoTotalizacao(2);
			GerenciaRegional gereAnterior = new GerenciaRegional();
			gereAnterior.setId(gerenciaAnterior);
			filtro.setGerenciaRegional(gereAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoGerenciaRegional(filtro));

		}

		return colecaoEmitirHistogramaEsgoto;
	}
	
	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Total Geral
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper,Collection<Object[]>
	 * 
	 * @return LinkedHashMap
	 * @throws ControladorException
	 */
	private LinkedHashMap montarEmitirHistogramaEsgotoTotalGeral(
			FiltrarEmitirHistogramaEsgotoHelper filtro,
			Collection<Object[]> colecao) throws ControladorException {

		LinkedHashMap hashMapTotalizacao = new LinkedHashMap();

		if (colecao != null && !colecao.isEmpty()) {

			Iterator iterator = colecao.iterator();

			Collection<EmitirHistogramaEsgotoDetalheHelper> colecaoEmitirHistogramaEsgotoDetalhe = null;

			while (iterator.hasNext()) {
				Object[] objeto = (Object[]) iterator.next();

				EmitirHistogramaEsgotoDetalheHelper detalhe = new EmitirHistogramaEsgotoDetalheHelper();

				detalhe.setDescricaoCategoria((String) objeto[1]);
				detalhe.setQuantidadeLigacoes((Integer) objeto[2]);
				detalhe.setQuantidadeEconomias((Integer) objeto[3]);
				detalhe.setValorFaturado((BigDecimal) objeto[4]);

				if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ){
					detalhe.setDescricaoSubcategoria((String) objeto[6]);
				}

				String tipoTotalizacao = "0";

				GerenciaRegional gerencia = null;
				UnidadeNegocio unidadeNegocio = null;
				Localidade eloPolo = null;
				Localidade localidade = null;
				SetorComercial setorComercial = null;
				Quadra quadra = null;

				int chaveQuadra = 0;
				int chaveSetorComercial = 0;
				int chaveLocalidade = 0;
				int chaveElo = 0;
				int chaveUnidade = 0;
				int chaveGerencia = 0;

				if (filtro.getOpcaoTotalizacao() == 2
						|| filtro.getOpcaoTotalizacao() == 6) {

					tipoTotalizacao = ((Integer) objeto[5]).toString();

					gerencia = new GerenciaRegional();
					gerencia.setId(new Integer(tipoTotalizacao));

					filtro.setGerenciaRegional(gerencia);

				} else if (filtro.getOpcaoTotalizacao() == 3
						|| filtro.getOpcaoTotalizacao() == 7) {

					chaveUnidade = (Integer) objeto[5];
					if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ) {
						chaveGerencia = (Integer) objeto[7];
					} else { 
						chaveGerencia = (Integer) objeto[6];
					}
					tipoTotalizacao = chaveUnidade + ";" + chaveGerencia;

					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(chaveUnidade);
					filtro.setUnidadeNegocio(unidadeNegocio);

					gerencia = new GerenciaRegional();
					gerencia.setId(chaveGerencia);
					filtro.setGerenciaRegional(gerencia);

				} else if (filtro.getOpcaoTotalizacao() == 4
						|| filtro.getOpcaoTotalizacao() == 8) {

					if( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ) {
						chaveElo = (Integer) objeto[5];
						chaveUnidade = (Integer) objeto[7];
						chaveGerencia = (Integer) objeto[8];
						
					} else {
						chaveElo = (Integer) objeto[5];
						chaveUnidade = (Integer) objeto[6];
						chaveGerencia = (Integer) objeto[7];
					}
					tipoTotalizacao = chaveElo + ";" + chaveUnidade + ";"
							+ chaveGerencia;

					eloPolo = new Localidade();
					eloPolo.setId(chaveElo);
					filtro.setEloPolo(eloPolo);

					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(chaveUnidade);
					filtro.setUnidadeNegocio(unidadeNegocio);

					gerencia = new GerenciaRegional();
					gerencia.setId(chaveGerencia);
					filtro.setGerenciaRegional(gerencia);

				} else if (filtro.getOpcaoTotalizacao() == 5
						|| filtro.getOpcaoTotalizacao() == 9) {

					if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() ==  ConstantesSistema.NAO ) {
						chaveLocalidade = (Integer) objeto[5];
						chaveElo = (Integer) objeto[7];
						chaveUnidade = (Integer) objeto[8];
						chaveGerencia = (Integer) objeto[9];
						
					} else {
						chaveLocalidade = (Integer) objeto[5];
						chaveElo = (Integer) objeto[6];
						chaveUnidade = (Integer) objeto[7];
						chaveGerencia = (Integer) objeto[8];
					}
					tipoTotalizacao = chaveLocalidade + ";" + chaveElo + ";"
							+ chaveUnidade + ";" + chaveGerencia;

					localidade = new Localidade();
					localidade.setId(chaveLocalidade);
					filtro.setLocalidade(localidade);

					eloPolo = new Localidade();
					eloPolo.setId(chaveElo);
					filtro.setEloPolo(eloPolo);

					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(chaveUnidade);
					filtro.setUnidadeNegocio(unidadeNegocio);

					gerencia = new GerenciaRegional();
					gerencia.setId(chaveGerencia);
					filtro.setGerenciaRegional(gerencia);

				} else if (filtro.getOpcaoTotalizacao() == 10) {

					chaveUnidade = (Integer) objeto[5];
					tipoTotalizacao = "" + chaveUnidade;

					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(chaveUnidade);
					filtro.setUnidadeNegocio(unidadeNegocio);

				} else if (filtro.getOpcaoTotalizacao() == 11) {

					chaveElo = (Integer) objeto[5];
					chaveUnidade = (Integer) objeto[6];

					tipoTotalizacao = chaveElo + ";" + chaveUnidade;

					eloPolo = new Localidade();
					eloPolo.setId(chaveElo);
					filtro.setEloPolo(eloPolo);

					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(chaveUnidade);
					filtro.setUnidadeNegocio(unidadeNegocio);

				} else if (filtro.getOpcaoTotalizacao() == 12) {

					chaveLocalidade = (Integer) objeto[5];
					chaveElo = (Integer) objeto[6];
					chaveUnidade = (Integer) objeto[7];

					tipoTotalizacao = chaveLocalidade + ";" + chaveElo + ";"
							+ chaveUnidade;

					localidade = new Localidade();
					localidade.setId(chaveLocalidade);
					filtro.setLocalidade(localidade);

					eloPolo = new Localidade();
					eloPolo.setId(chaveElo);
					filtro.setEloPolo(eloPolo);

					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(chaveUnidade);
					filtro.setUnidadeNegocio(unidadeNegocio);

				} else if (filtro.getOpcaoTotalizacao() == 13) {

					chaveElo = (Integer) objeto[5];
					tipoTotalizacao = "" + chaveElo;

					eloPolo = new Localidade();
					eloPolo.setId(chaveElo);
					filtro.setEloPolo(eloPolo);

				} else if (filtro.getOpcaoTotalizacao() == 14) {

					chaveLocalidade = (Integer) objeto[5];
					chaveElo = (Integer) objeto[6];

					tipoTotalizacao = chaveLocalidade + ";" + chaveElo;

					localidade = new Localidade();
					localidade.setId(chaveLocalidade);
					filtro.setLocalidade(localidade);

					eloPolo = new Localidade();
					eloPolo.setId(chaveElo);
					filtro.setEloPolo(eloPolo);

				} else if (filtro.getOpcaoTotalizacao() == 15) {

					chaveSetorComercial = (Integer) objeto[5];
					chaveLocalidade = (Integer) objeto[6];
					chaveElo = (Integer) objeto[7];

					tipoTotalizacao = chaveSetorComercial + ";"
							+ chaveLocalidade + ";" + chaveElo;

					filtro.setCodigoSetorComercial(chaveSetorComercial);

					localidade = new Localidade();
					localidade.setId(chaveLocalidade);
					filtro.setLocalidade(localidade);

					eloPolo = new Localidade();
					eloPolo.setId(chaveElo);
					filtro.setEloPolo(eloPolo);

				} else if (filtro.getOpcaoTotalizacao() == 16) {

					chaveLocalidade = (Integer) objeto[5];
					tipoTotalizacao = "" + chaveLocalidade;

					localidade = new Localidade();
					localidade.setId(chaveLocalidade);
					filtro.setLocalidade(localidade);

				} else if (filtro.getOpcaoTotalizacao() == 17) {

					chaveSetorComercial = (Integer) objeto[5];
					chaveLocalidade = (Integer) objeto[6];

					tipoTotalizacao = chaveSetorComercial + ";"
							+ chaveLocalidade;

					filtro.setCodigoSetorComercial(chaveSetorComercial);

					localidade = new Localidade();
					localidade.setId(chaveLocalidade);
					filtro.setLocalidade(localidade);

				} else if (filtro.getOpcaoTotalizacao() == 18) {

					chaveQuadra = (Integer) objeto[5];
					chaveSetorComercial = (Integer) objeto[6];
					chaveLocalidade = (Integer) objeto[7];

					tipoTotalizacao = chaveQuadra + ";" + chaveSetorComercial
							+ ";" + chaveLocalidade;

					filtro.setNumeroQuadra(chaveQuadra);

					setorComercial = new SetorComercial();
					setorComercial.setId(chaveSetorComercial);
					filtro.setSetorComercial(setorComercial);

					localidade = new Localidade();
					localidade.setId(chaveLocalidade);
					filtro.setLocalidade(localidade);

				} else if (filtro.getOpcaoTotalizacao() == 19) {

					chaveSetorComercial = (Integer) objeto[5];
					tipoTotalizacao = "" + chaveSetorComercial;

					setorComercial = new SetorComercial();
					setorComercial.setId(chaveSetorComercial);

					filtro.setSetorComercial(setorComercial);

				} else if (filtro.getOpcaoTotalizacao() == 20) {

					chaveQuadra = (Integer) objeto[5];
					chaveSetorComercial = (Integer) objeto[6];

					tipoTotalizacao = chaveQuadra + ";" + chaveSetorComercial;

					filtro.setNumeroQuadra(chaveQuadra);

					setorComercial = new SetorComercial();
					setorComercial.setId(chaveSetorComercial);
					filtro.setSetorComercial(setorComercial);

				} else if (filtro.getOpcaoTotalizacao() == 21) {

					chaveQuadra = (Integer) objeto[5];
					tipoTotalizacao = "" + chaveQuadra;

					quadra = new Quadra();
					quadra.setId(chaveQuadra);

					filtro.setQuadra(quadra);

				}

				try {
					Object[] somatorio = this.repositorioFaturamento
							.pesquisarEmitirHistogramaEsgotoTotalGeral(filtro,
									null);

					Integer quantidadeVolumeMedido;				
					Integer quantidadeVolumeEstimado;					
					
					if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.SIM ){
						
						Categoria categ = new Categoria();
						categ.setId((Integer) objeto[0]);
						
						quantidadeVolumeMedido = this.repositorioFaturamento
							.pesquisarEmitirHistogramaEsgotoVolumeConsumo(filtro,
									ConstantesSistema.INDICADOR_USO_ATIVO,
									categ, null);

					quantidadeVolumeEstimado = this.repositorioFaturamento
							.pesquisarEmitirHistogramaEsgotoVolumeConsumo(filtro,
									ConstantesSistema.INDICADOR_USO_DESATIVO,
									categ, null);
					
					} else {
						
						Subcategoria subcateg = new Subcategoria();
						subcateg.setId((Integer) objeto[5]);
						Categoria categoria = new Categoria();
						categoria.setId( (Integer) objeto[0] );
						
						subcateg.setCategoria( categoria );
						
						if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ) {
							filtro.setTipoGroupBy(" histograma.localidadeEelo.id,"
									+ "subcategoria.descricao, histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");
						} 
						
						quantidadeVolumeMedido = this.repositorioFaturamento
						.pesquisarEmitirHistogramaEsgotoVolumeConsumo(filtro,
								ConstantesSistema.INDICADOR_USO_ATIVO,
								subcateg, null);

						quantidadeVolumeEstimado = this.repositorioFaturamento
						.pesquisarEmitirHistogramaEsgotoVolumeConsumo(filtro,
								ConstantesSistema.INDICADOR_USO_DESATIVO,
								subcateg, null);						
					}	
					if (quantidadeVolumeMedido != null) {
						detalhe
								.setQuantidadeVolumeMedido(quantidadeVolumeMedido);
					}

					if (quantidadeVolumeEstimado != null) {
						detalhe
								.setQuantidadeVolumeEstimado(quantidadeVolumeEstimado);
					}

					BigDecimal percentualParcialLigacao = new BigDecimal(0.0);
					BigDecimal percentualParcialFaturamento = new BigDecimal(
							0.0);
					BigDecimal percentualParcialConsumo = new BigDecimal(0.0);

					if (((Integer) somatorio[0]).intValue() != 0) {

						percentualParcialLigacao = Util
								.calcularPercentualBigDecimal(""
										+ detalhe.getQuantidadeLigacoes(), ""
										+ somatorio[0]);

					}

					if (((Integer) somatorio[1]).intValue() != 0) {

						percentualParcialConsumo = Util
								.calcularPercentualBigDecimal(""
										+ detalhe.getQuantidadeVolumeTotal(),
										"" + somatorio[1]);

					}

					if (((BigDecimal) somatorio[2]).compareTo(new BigDecimal(
							0.0)) > 0) {

						percentualParcialFaturamento = Util
								.calcularPercentualBigDecimal(detalhe
										.getValorFaturado(), new BigDecimal(""
										+ somatorio[2]));
					}

					detalhe
							.setPercentualParcialLigacao(percentualParcialLigacao
									.doubleValue());
					detalhe
							.setPercentualParcialConsumo(percentualParcialConsumo
									.doubleValue());
					detalhe
							.setPercentualParcialFaturamento(percentualParcialFaturamento
									.doubleValue());

				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				if (hashMapTotalizacao.get(tipoTotalizacao) != null) {

					EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelper = (EmitirHistogramaEsgotoHelper) hashMapTotalizacao
							.get(tipoTotalizacao);

					int totalLigacoes = emitirHistogramaEsgotoHelper
							.getTotalQuantidadeLigacoes();
					int totalEconomias = emitirHistogramaEsgotoHelper
							.getTotalQuantidadeEconomias();
					int volumeEstimado = emitirHistogramaEsgotoHelper
							.getTotalQuantidadeVolumeEstimado();
					int volumeMedido = emitirHistogramaEsgotoHelper
							.getTotalQuantidadeVolumeMedido();

					BigDecimal totalValorFaturado = emitirHistogramaEsgotoHelper
							.getTotalValorFaturado();

					emitirHistogramaEsgotoHelper
							.setTotalQuantidadeLigacoes(totalLigacoes
									+ detalhe.getQuantidadeLigacoes());
					emitirHistogramaEsgotoHelper
							.setTotalQuantidadeEconomias(totalEconomias
									+ detalhe.getQuantidadeEconomias());
					emitirHistogramaEsgotoHelper
							.setTotalValorFaturado(totalValorFaturado
									.add(detalhe.getValorFaturado()));

					emitirHistogramaEsgotoHelper
							.setTotalQuantidadeVolumeMedido(volumeMedido
									+ detalhe.getQuantidadeVolumeMedido());
					emitirHistogramaEsgotoHelper
							.setTotalQuantidadeVolumeEstimado(volumeEstimado
									+ detalhe.getQuantidadeVolumeEstimado());

					colecaoEmitirHistogramaEsgotoDetalhe = emitirHistogramaEsgotoHelper
							.getColecaoEmitirHistogramaEsgotoDetalhe();

					colecaoEmitirHistogramaEsgotoDetalhe.add(detalhe);
					emitirHistogramaEsgotoHelper
							.setColecaoEmitirHistogramaEsgotoDetalhe(colecaoEmitirHistogramaEsgotoDetalhe);

					hashMapTotalizacao.put(tipoTotalizacao,
							emitirHistogramaEsgotoHelper);

				} else {
					EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelper = new EmitirHistogramaEsgotoHelper();
					emitirHistogramaEsgotoHelper
							.setDescricaoTitulo("TOTAL GERAL");

					emitirHistogramaEsgotoHelper
							.setTotalQuantidadeLigacoes(detalhe
									.getQuantidadeLigacoes());
					emitirHistogramaEsgotoHelper
							.setTotalQuantidadeEconomias(detalhe
									.getQuantidadeEconomias());
					emitirHistogramaEsgotoHelper
							.setTotalQuantidadeVolumeMedido(detalhe
									.getQuantidadeVolumeMedido());
					emitirHistogramaEsgotoHelper
							.setTotalQuantidadeVolumeEstimado(detalhe
									.getQuantidadeVolumeEstimado());

					emitirHistogramaEsgotoHelper.setTotalValorFaturado(detalhe
							.getValorFaturado());

					colecaoEmitirHistogramaEsgotoDetalhe = new ArrayList();
					colecaoEmitirHistogramaEsgotoDetalhe.add(detalhe);

					emitirHistogramaEsgotoHelper
							.setColecaoEmitirHistogramaEsgotoDetalhe(colecaoEmitirHistogramaEsgotoDetalhe);

					hashMapTotalizacao.put(tipoTotalizacao,
							emitirHistogramaEsgotoHelper);
				}

			}
		}
		return hashMapTotalizacao;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Total Geral Por Categoria
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @param Collection<EmitirHistogramaEsgotoDetalheHelper>
	 * 
	 * @return LinkedHashMap
	 * @throws ControladorException
	 */
	private LinkedHashMap montarEmitirHistogramaEsgotoTotalGeralCategoria(
			Collection<EmitirHistogramaEsgotoDetalheHelper> colecao)
			throws ControladorException {

		LinkedHashMap hashMapTotalizacao = new LinkedHashMap();

		if (colecao != null && !colecao.isEmpty()) {

			Iterator iterator = colecao.iterator();

			while (iterator.hasNext()) {
				EmitirHistogramaEsgotoDetalheHelper detalhe = (EmitirHistogramaEsgotoDetalheHelper) iterator
						.next();

				Object[] objeto = new Object[3];

				objeto[0] = detalhe.getQuantidadeLigacoes();
				objeto[1] = detalhe.getQuantidadeVolumeTotal();
				objeto[2] = detalhe.getValorFaturado();

				hashMapTotalizacao.put(detalhe.getDescricaoCategoria(), objeto);
			}
		}

		return hashMapTotalizacao;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Gera as linhas Por Faixa de Consumo
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @param Collection
	 *            <Object[]>
	 * 
	 * @throws ControladorException
	 */
	private void montarEmitirHistogramaEsgotoFaixaConsumo(
			Collection<ConsumoFaixaLigacao> colecao,
			FiltrarEmitirHistogramaEsgotoHelper filtro,
			int totalQuantidadeLigacoesTotalGeral,
			BigDecimal totalValorFaturado, int totalQuantidadeVolumeTotal,
			Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto,
			String descricaoOpcaoTotalizacao, short indicadorMedicao,
			HashMap mapTotalizacaoGategoria) throws ControladorException {

		// Gera os a linhas com a Faixa (PAI)
		// depois os totalizadores por categoria(FILHO)
		if (colecao != null && !colecao.isEmpty()) {

			Collection<Object[]> colecaoRetorno = null;
			EmitirHistogramaEsgotoHelper emitirTotalizadorIndicador = null;

			String descricaoTitulo = "TOTAL C/HID.";
			if (indicadorMedicao != ConstantesSistema.INDICADOR_USO_ATIVO
					.shortValue()) {
				descricaoTitulo = "TOTAL S/HID.";
				filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
						.retornaOLimiteConsultaTotal(indicadorMedicao));
				filtro.setConsumoFaixaLigacaoIntervaloMedido(null);
			} else {
				filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(null);
				filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
						.retornaOLimiteConsultaTotal(indicadorMedicao));
			}

			try {

				filtro.setConsumoFaixaLigacao(null);

				filtro.setMedicao(indicadorMedicao);

				colecaoRetorno = this.repositorioFaturamento
						.pesquisarEmitirHistogramaEsgoto(filtro);

				filtro.setMedicao(null);

				emitirTotalizadorIndicador = this
						.montarEmitirHistogramaEsgotoDetalhe(colecaoRetorno,
								descricaoTitulo, filtro,
								descricaoOpcaoTotalizacao,
								totalQuantidadeLigacoesTotalGeral,
								totalValorFaturado, totalQuantidadeVolumeTotal,
								indicadorMedicao, mapTotalizacaoGategoria,
								null, null);

			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}

			if (emitirTotalizadorIndicador != null) {

				// chave(Id categoria),valores(BigDecimal[3] percentuais)
				LinkedHashMap mapPercentualParcialPorCategoriaAnterior = new LinkedHashMap();
				LinkedHashMap mapPercentualParcialPorTotalAnterior = new LinkedHashMap();

				filtro.setMedicao(indicadorMedicao);

				Iterator itera = colecao.iterator();
				while (itera.hasNext()) {
					ConsumoFaixaLigacao consumo = (ConsumoFaixaLigacao) itera
							.next();

					filtro.setConsumoFaixaLigacao(consumo);

					try {
						descricaoTitulo = consumo.getNumeroFaixaInicio()
								+ " a " + consumo.getNumeroFaixaFim();

						colecaoRetorno = this.repositorioFaturamento
								.pesquisarEmitirHistogramaEsgoto(filtro);

						EmitirHistogramaEsgotoHelper emitirFaixa = 
							this.montarEmitirHistogramaEsgotoDetalhe(
								colecaoRetorno,
								descricaoTitulo,
								filtro,
								descricaoOpcaoTotalizacao,
								emitirTotalizadorIndicador.getTotalQuantidadeLigacoes(),
								emitirTotalizadorIndicador.getTotalValorFaturado(),
								emitirTotalizadorIndicador.getTotalQuantidadeVolumeTotal(),
								indicadorMedicao,
								null,
								mapPercentualParcialPorCategoriaAnterior,
								mapPercentualParcialPorTotalAnterior);

						if (emitirFaixa != null) {
							colecaoEmitirHistogramaEsgoto.add(emitirFaixa);
						}

					} catch (ErroRepositorioException e) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("erro.sistema", e);
					}
				}
				if (emitirTotalizadorIndicador != null) {
					colecaoEmitirHistogramaEsgoto.add(emitirTotalizadorIndicador);
				}

			}// fim do emitir totalizador
		}
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Linha Faixa
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @return EmitirHistogramaEsgotoHelper
	 * @throws ControladorException
	 */
	private EmitirHistogramaEsgotoHelper montarEmitirHistogramaEsgotoDetalhe(
			Collection<Object[]> colecao, String descricaoTitulo,
			FiltrarEmitirHistogramaEsgotoHelper filtro,
			String descricaoOpcaoTotalizacao, int quantidadeLigacaoIndicador,
			BigDecimal valorFaturado, int totalQuantidadeVolumeTotal,
			short indicadorHidrometro, HashMap mapTotalizacaoGategoria,
			LinkedHashMap mapPercentualParcialPorCategoriaAnterior,
			LinkedHashMap mapPercentualParcialPorTotalAnterior)
			throws ControladorException {

		EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelper = null;
		if (colecao != null && !colecao.isEmpty()) {

			Iterator iterator = colecao.iterator();

			Collection<EmitirHistogramaEsgotoDetalheHelper> colecaoEmitirHistogramaEsgotoDetalhe = new ArrayList();

			EmitirHistogramaEsgotoDetalheHelper detalhe = null;

			int totalLigacoes = 0;
			int totalEconomias = 0;
			int volumeMedido = 0;
			int volumeEstimado = 0;

			BigDecimal totalValorFaturado = new BigDecimal("0.0");

			while (iterator.hasNext()) {
				Object[] objeto = (Object[]) iterator.next();

				detalhe = new EmitirHistogramaEsgotoDetalheHelper();

				detalhe.setDescricaoCategoria((String) objeto[1]);
				detalhe.setQuantidadeLigacoes((Integer) objeto[2]);

				detalhe.setQuantidadeEconomias((Integer) objeto[3]);
				detalhe.setValorFaturado((BigDecimal) objeto[4]);
				
				if ( filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ){
					detalhe.setDescricaoSubcategoria((String) objeto[6]);
				}				

				try {

					Categoria categ = new Categoria();
					categ.setId((Integer) objeto[0]);
					Object[] somatorio = null;

					if (mapTotalizacaoGategoria != null) {
						somatorio = (Object[]) mapTotalizacaoGategoria
								.get(detalhe.getDescricaoCategoria());
					} else {
						somatorio = this.repositorioFaturamento
								.pesquisarEmitirHistogramaEsgotoTotalGeral(
										filtro, categ);
					}

					if (somatorio == null) {
						somatorio = new Object[3];
						somatorio[0] = 0;
						somatorio[1] = 0;
						somatorio[2] = new BigDecimal(0.0);
					}

					BigDecimal percentualParcialLigacao = new BigDecimal(0.0);
					BigDecimal percentualParcialFaturamento = new BigDecimal(
							0.0);
					BigDecimal percentualParcialConsumo = new BigDecimal(0.0);

					if (((Integer) somatorio[0]).intValue() != 0) {

						percentualParcialLigacao = Util
								.calcularPercentualBigDecimal(""
										+ detalhe.getQuantidadeLigacoes(), ""
										+ somatorio[0]);

					}

					detalhe
							.setPercentualParcialLigacao(percentualParcialLigacao
									.doubleValue());

					if (((BigDecimal) somatorio[2]).compareTo(new BigDecimal(
							0.0)) > 0) {

						percentualParcialFaturamento = Util
								.calcularPercentualBigDecimal(detalhe
										.getValorFaturado(), new BigDecimal(""
										+ somatorio[2]));
					}

					detalhe
							.setPercentualParcialFaturamento(percentualParcialFaturamento
									.doubleValue());

					if (indicadorHidrometro == ConstantesSistema.INDICADOR_USO_ATIVO
							.shortValue()) {

						Integer quantidadeVolumeMedido;				
						Integer quantidadeVolumeEstimado;					
						
						if ( filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.SIM ){
							
							categ = new Categoria();
							categ.setId((Integer) objeto[0]);
							
							quantidadeVolumeMedido = this.repositorioFaturamento
								.pesquisarEmitirHistogramaEsgotoVolumeConsumo(filtro,
										ConstantesSistema.INDICADOR_USO_ATIVO,
										categ, null);

							quantidadeVolumeEstimado = this.repositorioFaturamento
								.pesquisarEmitirHistogramaEsgotoVolumeConsumo(filtro,
										ConstantesSistema.INDICADOR_USO_DESATIVO,
										categ, null);
						} else {
							
							Subcategoria subcateg = new Subcategoria();
							subcateg.setId((Integer) objeto[5]);
							Categoria categoria = new Categoria();
							categoria.setId( (Integer) objeto[0] );
							
							subcateg.setCategoria( categoria );
							
							quantidadeVolumeMedido = this.repositorioFaturamento
							.pesquisarEmitirHistogramaEsgotoVolumeConsumo(filtro,
									ConstantesSistema.INDICADOR_USO_ATIVO,
									subcateg, null);

							quantidadeVolumeEstimado = this.repositorioFaturamento
							.pesquisarEmitirHistogramaEsgotoVolumeConsumo(filtro,
									ConstantesSistema.INDICADOR_USO_DESATIVO,
									subcateg, null);						
						}	

						if (quantidadeVolumeMedido != null) {
							detalhe
									.setQuantidadeVolumeMedido(quantidadeVolumeMedido);
						}
						if (quantidadeVolumeEstimado != null) {
							detalhe
									.setQuantidadeVolumeEstimado(quantidadeVolumeEstimado);
						}

					} else {
						Integer quantidadeVolumeEstimado;						
						
						if ( filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.SIM ){
							quantidadeVolumeEstimado = this.repositorioFaturamento
							.pesquisarEmitirHistogramaEsgotoVolumeConsumo(
									filtro,
									ConstantesSistema.INDICADOR_USO_DESATIVO,
									categ, indicadorHidrometro);							
						} else {							
							Subcategoria subcateg = new Subcategoria();
							subcateg.setId((Integer) objeto[5]);	
							
							Categoria categoria = new Categoria();
							categoria.setId( (Integer) objeto[0] );		
							
							subcateg.setCategoria( categoria );
							
							quantidadeVolumeEstimado = this.repositorioFaturamento
							.pesquisarEmitirHistogramaEsgotoVolumeConsumo(
									filtro,
									ConstantesSistema.INDICADOR_USO_DESATIVO,
									subcateg, indicadorHidrometro);
						}	

						if (quantidadeVolumeEstimado != null) {
							detalhe
									.setQuantidadeVolumeEstimado(quantidadeVolumeEstimado);
						}
					}

					if (((Integer) somatorio[1]).intValue() != 0) {

						percentualParcialConsumo = Util
								.calcularPercentualBigDecimal(""
										+ detalhe.getQuantidadeVolumeTotal(),
										"" + somatorio[1]);
					}

					detalhe
							.setPercentualParcialConsumo(percentualParcialConsumo
									.doubleValue());

					if (mapPercentualParcialPorCategoriaAnterior != null) {

						BigDecimal[] valoresPercentual = (BigDecimal[]) mapPercentualParcialPorCategoriaAnterior
								.get(categ.getId());

						BigDecimal percentualAcumuladoLigacao = new BigDecimal(
								0.0);
						BigDecimal percentualAcumuladoConsumo = new BigDecimal(
								0.0);
						BigDecimal percentualAcumuladoFaturamento = new BigDecimal(
								0.0);

						if (valoresPercentual != null) {

							percentualAcumuladoLigacao = percentualParcialLigacao
									.add(valoresPercentual[0]);
							percentualAcumuladoConsumo = percentualParcialConsumo
									.add(valoresPercentual[1]);
							percentualAcumuladoFaturamento = percentualParcialFaturamento
									.add(valoresPercentual[2]);

						} else {

							percentualAcumuladoLigacao = percentualParcialLigacao;
							percentualAcumuladoConsumo = percentualParcialConsumo;
							percentualAcumuladoFaturamento = percentualParcialFaturamento;

						}

						detalhe
								.setPercentualAcumuladoLigacao(percentualAcumuladoLigacao
										.doubleValue());
						detalhe
								.setPercentualAcumuladoConsumo(percentualAcumuladoConsumo
										.doubleValue());
						detalhe
								.setPercentualAcumuladoFaturamento(percentualAcumuladoFaturamento
										.doubleValue());

						valoresPercentual = new BigDecimal[3];

						valoresPercentual[0] = percentualAcumuladoLigacao;
						valoresPercentual[1] = percentualAcumuladoConsumo;
						valoresPercentual[2] = percentualAcumuladoFaturamento;

						mapPercentualParcialPorCategoriaAnterior.put(categ
								.getId(), valoresPercentual);

					}

				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				totalLigacoes += detalhe.getQuantidadeLigacoes();
				totalEconomias += detalhe.getQuantidadeEconomias();
				volumeMedido += detalhe.getQuantidadeVolumeMedido();
				volumeEstimado += detalhe.getQuantidadeVolumeEstimado();
				totalValorFaturado = totalValorFaturado.add(detalhe
						.getValorFaturado());

				colecaoEmitirHistogramaEsgotoDetalhe.add(detalhe);

			}

			emitirHistogramaEsgotoHelper = new EmitirHistogramaEsgotoHelper();
			emitirHistogramaEsgotoHelper
					.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
			emitirHistogramaEsgotoHelper.setDescricaoTitulo(descricaoTitulo);
			emitirHistogramaEsgotoHelper
					.setColecaoEmitirHistogramaEsgotoDetalhe(colecaoEmitirHistogramaEsgotoDetalhe);
			emitirHistogramaEsgotoHelper
					.setTotalQuantidadeLigacoes(totalLigacoes);
			emitirHistogramaEsgotoHelper
					.setTotalQuantidadeEconomias(totalEconomias);
			emitirHistogramaEsgotoHelper
					.setTotalQuantidadeVolumeMedido(volumeMedido);
			emitirHistogramaEsgotoHelper
					.setTotalQuantidadeVolumeEstimado(volumeEstimado);
			emitirHistogramaEsgotoHelper
					.setTotalValorFaturado(totalValorFaturado);

			BigDecimal percentualParcialLigacao = new BigDecimal(0.0);
			BigDecimal percentualParcialFaturamento = new BigDecimal(0.0);
			BigDecimal percentualParcialConsumo = new BigDecimal(0.0);

			if (quantidadeLigacaoIndicador != 0) {

				percentualParcialLigacao = Util.calcularPercentualBigDecimal(""
						+ emitirHistogramaEsgotoHelper
								.getTotalQuantidadeLigacoes(), ""
						+ quantidadeLigacaoIndicador);
			}

			if (totalQuantidadeVolumeTotal != 0) {

				percentualParcialConsumo = Util.calcularPercentualBigDecimal(""
						+ emitirHistogramaEsgotoHelper
								.getTotalQuantidadeVolumeTotal(), ""
						+ totalQuantidadeVolumeTotal);

			}

			if (valorFaturado.compareTo(new BigDecimal(0.0)) > 0) {

				percentualParcialFaturamento = Util
						.calcularPercentualBigDecimal(
								emitirHistogramaEsgotoHelper
										.getTotalValorFaturado(), valorFaturado);
			}

			emitirHistogramaEsgotoHelper
					.setTotalPercentualParcialLigacao(percentualParcialLigacao
							.doubleValue());
			emitirHistogramaEsgotoHelper
					.setTotalPercentualParcialConsumo(percentualParcialConsumo
							.doubleValue());
			emitirHistogramaEsgotoHelper
					.setTotalPercentualParcialFaturamento(percentualParcialFaturamento
							.doubleValue());

			if (mapPercentualParcialPorTotalAnterior != null) {

				BigDecimal[] valoresPercentual = (BigDecimal[]) mapPercentualParcialPorTotalAnterior
						.get("TOTAL");

				BigDecimal percentualAcumuladoLigacao = new BigDecimal(0.0);
				BigDecimal percentualAcumuladoConsumo = new BigDecimal(0.0);
				BigDecimal percentualAcumuladoFaturamento = new BigDecimal(0.0);

				if (valoresPercentual != null) {

					percentualAcumuladoLigacao = percentualParcialLigacao
							.add(valoresPercentual[0]);
					percentualAcumuladoConsumo = percentualParcialConsumo
							.add(valoresPercentual[1]);
					percentualAcumuladoFaturamento = percentualParcialFaturamento
							.add(valoresPercentual[2]);

				} else {

					percentualAcumuladoLigacao = percentualParcialLigacao;
					percentualAcumuladoConsumo = percentualParcialConsumo;
					percentualAcumuladoFaturamento = percentualParcialFaturamento;

				}

				emitirHistogramaEsgotoHelper
						.setTotalPercentualAcumuladoLigacao(percentualAcumuladoLigacao
								.doubleValue());
				emitirHistogramaEsgotoHelper
						.setTotalPercentualAcumuladoConsumo(percentualAcumuladoConsumo
								.doubleValue());
				emitirHistogramaEsgotoHelper
						.setTotalPercentualAcumuladoFaturamento(percentualAcumuladoFaturamento
								.doubleValue());

				valoresPercentual = new BigDecimal[3];

				valoresPercentual[0] = percentualAcumuladoLigacao;
				valoresPercentual[1] = percentualAcumuladoConsumo;
				valoresPercentual[2] = percentualAcumuladoFaturamento;

				mapPercentualParcialPorTotalAnterior.put("TOTAL",
						valoresPercentual);

			}

		}

		return emitirHistogramaEsgotoHelper;
	}
	
	
	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Elo e Unidade Negocio
	 * 
	 * @author Rafael Pinto
	 * @date 12/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoEloUnidadeNegocio(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		filtro
				.setTipoGroupBy("histograma.localidadeElo.id,histograma.unidadeNegocio.id ");

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoLocalidade = null;

			Localidade eloPolo = null;
			UnidadeNegocio unidadeNegocio = null;

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				String[] arrayNumeracao = chave.split(";");

				eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[0]));

				unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[1]));

				if (unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				filtro.setMedicao(indicadorMedicao);
				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);

				// Mudou de Unidade
				if (unidadeNegocioAnterior != unidadeNegocio.getId().intValue()) {

					filtro.setOpcaoTotalizacao(10);

					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					filtro.setUnidadeNegocio(uniAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoUnidadeNegocio(filtro));
				}

				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setMedicao(indicadorMedicao);
				filtro.setOpcaoTotalizacao(4);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, eloPolo.getId()));

				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

				// Recupera Localidade
				colecaoLocalidade = this.getControladorUtil().pesquisar(
						filtroLocalidade, Localidade.class.getName());

				eloPolo = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				String descricaoOpcaoTotalizacao = eloPolo
						.getGerenciaRegional().getId()
						+ "-"
						+ eloPolo.getGerenciaRegional().getNomeAbreviado()
						+ " / "
						+ eloPolo.getUnidadeNegocio().getId()
						+ "-"
						+ eloPolo.getUnidadeNegocio().getNomeAbreviado()
						+ " / "
						+ eloPolo.getId()
						+ "-"
						+ eloPolo.getDescricao();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}

				filtro.setMedicao(indicadorMedicao);

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

				unidadeNegocioAnterior = unidadeNegocio.getId();

			}

			filtro.setMedicao(indicadorMedicao);

			filtro.setOpcaoTotalizacao(10);
			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoUnidadeNegocio(filtro));

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Água
	 * 
	 * Localidade Elo e Unidade Negocio
	 * 
	 * @author Rafael Pinto
	 * @date 12/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoLocalidadeEloUnidadeNegocio(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		filtro
				.setTipoGroupBy("histograma.localidade.id,histograma.localidadeElo.id,"
						+ "histograma.unidadeNegocio.id ");

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoLocalidade = null;

			Localidade localidade = null;
			Localidade eloPolo = null;
			UnidadeNegocio unidadeNegocio = null;

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				String[] arrayNumeracao = chave.split(";");

				localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[0]));

				eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[1]));

				unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[2]));

				if (eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					eloPoloAnterior = eloPolo.getId();
				}

				if (unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				filtro.setMedicao(indicadorMedicao);
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);

				// Mudou de Elo Polo
				if (eloPoloAnterior != eloPolo.getId().intValue()) {

					filtro.setOpcaoTotalizacao(13);
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoElo(filtro));
				}

				filtro.setMedicao(indicadorMedicao);

				// Mudou de Unidade
				if (unidadeNegocioAnterior != unidadeNegocio.getId().intValue()) {

					filtro.setOpcaoTotalizacao(10);
					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					filtro.setUnidadeNegocio(uniAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoUnidadeNegocio(filtro));
				}

				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setMedicao(indicadorMedicao);
				filtro.setOpcaoTotalizacao(4);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, localidade.getId()));

				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

				// Recupera Localidade
				colecaoLocalidade = this.getControladorUtil().pesquisar(
						filtroLocalidade, Localidade.class.getName());

				localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				String descricaoOpcaoTotalizacao = localidade
						.getGerenciaRegional().getId()
						+ "-"
						+ localidade.getGerenciaRegional().getNomeAbreviado()
						+ " / "
						+ localidade.getUnidadeNegocio().getId()
						+ "-"
						+ localidade.getUnidadeNegocio().getNomeAbreviado()
						+ " / "
						+ localidade.getLocalidade().getId()
						+ "-"
						+ localidade.getLocalidade().getDescricao()
						+ " / "
						+ localidade.getId() + "-" + localidade.getDescricao();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

				unidadeNegocioAnterior = unidadeNegocio.getId();
				eloPoloAnterior = eloPolo.getId();

			}

			filtro.setOpcaoTotalizacao(13);
			filtro.setMedicao(indicadorMedicao);

			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoElo(filtro));

			filtro.setOpcaoTotalizacao(10);
			filtro.setMedicao(indicadorMedicao);

			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoUnidadeNegocio(filtro));

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Localidade Elo e Unidade Negocio e Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoLocalidadeEloUnidadeNegocioGerenciaRegional(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ) {
			filtro.setTipoGroupBy("histograma.localidade.id, subcategoria.descricao, histograma.localidadeEelo.id,"
					+ "histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");
		} else {
			filtro.setTipoGroupBy("histograma.localidade.id,histograma.localidadeElo.id,"
							+ "histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");
		}
		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);
			
			if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ) {
				filtro.setTipoGroupBy("histograma.localidade.id, subcategoria.descricao, histograma.localidadeElo.id,"
						+ "histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");
			}

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			int gerenciaAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoLocalidade = null;

			Localidade localidade = null;
			Localidade eloPolo = null;
			UnidadeNegocio unidadeNegocio = null;
			GerenciaRegional gerencia = null;

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				String[] arrayNumeracao = chave.split(";");

				localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[0]));

				eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[1]));

				unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[2]));

				gerencia = new GerenciaRegional();
				gerencia.setId(new Integer(arrayNumeracao[3]));

				if (eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					eloPoloAnterior = eloPolo.getId();
				}

				if (unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				if (gerenciaAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					gerenciaAnterior = gerencia.getId();
				}

				filtro.setMedicao(indicadorMedicao);
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setGerenciaRegional(gerencia);

				// Mudou de Elo Polo
				if (eloPoloAnterior != eloPolo.getId().intValue()) {

					filtro.setOpcaoTotalizacao(13);
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoElo(filtro));
				}

				filtro.setMedicao(indicadorMedicao);

				// Mudou de Unidade
				if (unidadeNegocioAnterior != unidadeNegocio.getId().intValue()) {

					filtro.setOpcaoTotalizacao(10);
					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					filtro.setUnidadeNegocio(uniAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoUnidadeNegocio(filtro));
				}
				filtro.setMedicao(indicadorMedicao);

				// Mudou de Gerencia
				if (gerenciaAnterior != gerencia.getId().intValue()) {

					GerenciaRegional gereAnterior = new GerenciaRegional();
					gereAnterior.setId(gerenciaAnterior);
					filtro.setGerenciaRegional(gereAnterior);

					filtro.setOpcaoTotalizacao(2);
					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoGerenciaRegional(filtro));
				}

				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setGerenciaRegional(gerencia);
				filtro.setMedicao(indicadorMedicao);
				filtro.setOpcaoTotalizacao(4);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, localidade.getId()));

				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

				// Recupera Localidade
				colecaoLocalidade = this.getControladorUtil().pesquisar(
						filtroLocalidade, Localidade.class.getName());

				localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				String descricaoOpcaoTotalizacao = localidade
						.getGerenciaRegional().getId()
						+ "-"
						+ localidade.getGerenciaRegional().getNomeAbreviado()
						+ " / "
						+ localidade.getUnidadeNegocio().getId()
						+ "-"
						+ localidade.getUnidadeNegocio().getNomeAbreviado()
						+ " / "
						+ localidade.getLocalidade().getId()
						+ "-"
						+ localidade.getLocalidade().getDescricao()
						+ " / "
						+ localidade.getId() + "-" + localidade.getDescricao();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

				gerenciaAnterior = gerencia.getId();
				unidadeNegocioAnterior = unidadeNegocio.getId();
				eloPoloAnterior = eloPolo.getId();

			}

			filtro.setOpcaoTotalizacao(13);
			filtro.setMedicao(indicadorMedicao);

			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoElo(filtro));

			filtro.setOpcaoTotalizacao(10);
			filtro.setMedicao(indicadorMedicao);

			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoUnidadeNegocio(filtro));

			filtro.setOpcaoTotalizacao(2);
			filtro.setMedicao(indicadorMedicao);

			GerenciaRegional gereAnterior = new GerenciaRegional();
			gereAnterior.setId(gerenciaAnterior);
			filtro.setGerenciaRegional(gereAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoGerenciaRegional(filtro));

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto -
	 * 
	 * Unidade Negocio
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoUnidadeNegocio(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ) {
			filtro.setTipoGroupBy(" histograma.unidadeNegocio.id , subcategoria.descricao ");
		} else {
			filtro.setTipoGroupBy("histograma.unidadeNegocio.id ");
		}
		
		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		filtro.setEloPolo(null);
		filtro.setGerenciaRegional(null);
		filtro.setLocalidade(null);
		filtro.setConsumoFaixaLigacao(null);

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			FiltroUnidadeNegocio filtroUnidade = null;
			Collection colecaoUnidade = null;
			UnidadeNegocio unidadeNegocio = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(chave));

				filtro.setUnidadeNegocio(unidadeNegocio);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroUnidade = new FiltroUnidadeNegocio();
				filtroUnidade.adicionarParametro(new ParametroSimples(
						FiltroUnidadeNegocio.ID, chave));

				filtroUnidade
						.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

				// Recupera Unidade Negocio
				colecaoUnidade = this.getControladorUtil().pesquisar(
						filtroUnidade, UnidadeNegocio.class.getName());

				unidadeNegocio = (UnidadeNegocio) Util
						.retonarObjetoDeColecao(colecaoUnidade);

				String descricaoOpcaoTotalizacao = unidadeNegocio
						.getGerenciaRegional().getId()
						+ "-"
						+ unidadeNegocio.getGerenciaRegional()
								.getNomeAbreviado()
						+ " / "
						+ unidadeNegocio.getId()
						+ "-"
						+ unidadeNegocio.getNome();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}
				filtro.setMedicao(indicadorMedicao);

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

			}

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto -
	 * 
	 * Elo Polo
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoElo(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		if ( filtro.getIndicadorTarifaCategoria() != null && filtro.getIndicadorTarifaCategoria().intValue() == ConstantesSistema.NAO ) {
			filtro.setTipoGroupBy(" histograma.localidadeEelo.id , subcategoria.descricao ");
		} else {
			filtro.setTipoGroupBy("histograma.localidadeElo.id ");	
		}
		

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		filtro.setLocalidade(null);
		filtro.setUnidadeNegocio(null);
		filtro.setGerenciaRegional(null);
		filtro.setCodigoSetorComercial(null);
		filtro.setConsumoFaixaLigacao(null);

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoLocalidade = null;
			Localidade localidade = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				localidade = new Localidade();
				localidade.setId(new Integer(chave));

				filtro.setEloPolo(localidade);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, chave));

				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

				// Recupera Unidade Negocio
				colecaoLocalidade = this.getControladorUtil().pesquisar(
						filtroLocalidade, Localidade.class.getName());

				localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				String descricaoOpcaoTotalizacao = localidade
						.getGerenciaRegional().getId()
						+ "-"
						+ localidade.getGerenciaRegional().getNomeAbreviado()
						+ " / "
						+ localidade.getUnidadeNegocio().getId()
						+ "-"
						+ localidade.getUnidadeNegocio().getNomeAbreviado()
						+ " / "
						+ localidade.getId()
						+ "-"
						+ localidade.getDescricao();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}
				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

			}

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Localidade, Elo e Unidade Negocio
	 * 
	 * @author Rafael Pinto
	 * @date 12/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoLocalidadeElo(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		filtro
				.setTipoGroupBy("histograma.localidade.id,histograma.localidadeElo.id ");

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoLocalidade = null;

			Localidade localidade = null;
			Localidade eloPolo = null;

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				String[] arrayNumeracao = chave.split(";");

				localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[0]));

				eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[1]));

				if (eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					eloPoloAnterior = eloPolo.getId();
				}

				filtro.setMedicao(indicadorMedicao);
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);

				// Mudou de Elo Polo
				if (eloPoloAnterior != eloPolo.getId().intValue()) {

					filtro.setOpcaoTotalizacao(13);
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoElo(filtro));
				}

				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setMedicao(indicadorMedicao);
				filtro.setOpcaoTotalizacao(4);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, localidade.getId()));

				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade
						.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

				// Recupera Localidade
				colecaoLocalidade = this.getControladorUtil().pesquisar(
						filtroLocalidade, Localidade.class.getName());

				localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				String descricaoOpcaoTotalizacao = localidade
						.getGerenciaRegional().getId()
						+ "-"
						+ localidade.getGerenciaRegional().getNomeAbreviado()
						+ " / "
						+ localidade.getUnidadeNegocio().getId()
						+ "-"
						+ localidade.getUnidadeNegocio().getNomeAbreviado()
						+ " / "
						+ localidade.getLocalidade().getId()
						+ "-"
						+ localidade.getLocalidade().getDescricao()
						+ " / "
						+ localidade.getId() + "-" + localidade.getDescricao();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

				eloPoloAnterior = eloPolo.getId();

			}

			filtro.setOpcaoTotalizacao(13);
			filtro.setMedicao(indicadorMedicao);

			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoElo(filtro));

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Água
	 * 
	 * Setor Comercial ,Localidade e Elo
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoSetorComercialLocalidadeElo(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		filtro
				.setTipoGroupBy("histograma.codigoSetorComercial,histograma.localidade.id,"
						+ "histograma.localidadeElo.id ");

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			int localidadeAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			FiltroSetorComercial filtroSetorComercial = null;
			Collection colecaoSetorComercial = null;

			Integer codigoSetorComercial = null;
			SetorComercial setorComercial = null;
			Localidade localidade = null;
			Localidade eloPolo = null;

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				String[] arrayNumeracao = chave.split(";");

				codigoSetorComercial = new Integer(arrayNumeracao[0]);

				localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[1]));

				eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[2]));

				if (eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					eloPoloAnterior = eloPolo.getId();
				}

				if (localidadeAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					localidadeAnterior = localidade.getId();
				}

				filtro.setMedicao(indicadorMedicao);
				filtro.setCodigoSetorComercial(codigoSetorComercial);
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);

				// Mudou de Localidade
				if (localidadeAnterior != localidade.getId().intValue()) {

					filtro.setOpcaoTotalizacao(16);

					Localidade localAnterior = new Localidade();
					localAnterior.setId(localidadeAnterior);

					filtro.setLocalidade(localAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoLocalidade(filtro));
				}

				filtro.setCodigoSetorComercial(codigoSetorComercial);
				filtro.setLocalidade(localidade);
				filtro.setMedicao(indicadorMedicao);

				// Mudou de Elo Polo
				if (eloPoloAnterior != eloPolo.getId().intValue()) {

					filtro.setOpcaoTotalizacao(13);
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoElo(filtro));
				}

				filtro.setCodigoSetorComercial(codigoSetorComercial);
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);

				filtro.setMedicao(indicadorMedicao);
				filtro.setOpcaoTotalizacao(4);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						codigoSetorComercial));

				filtroSetorComercial
						.adicionarParametro(new ParametroSimples(
								FiltroSetorComercial.ID_LOCALIDADE, localidade
										.getId()));

				filtroSetorComercial
						.adicionarCaminhoParaCarregamentoEntidade("localidade");

				// Recupera Setor Comercial
				colecaoSetorComercial = this.getControladorUtil().pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				setorComercial = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoSetorComercial);

				String descricaoOpcaoTotalizacao = setorComercial
						.getLocalidade().getId()
						+ "-"
						+ setorComercial.getLocalidade().getDescricao()
						+ " / "
						+ setorComercial.getCodigo()
						+ "-"
						+ setorComercial.getDescricao();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

				localidadeAnterior = localidade.getId();
				eloPoloAnterior = eloPolo.getId();

			}

			filtro.setOpcaoTotalizacao(16);
			filtro.setMedicao(indicadorMedicao);

			Localidade localAnterior = new Localidade();
			localAnterior.setId(localidadeAnterior);

			filtro.setLocalidade(localAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoLocalidade(filtro));

			filtro.setOpcaoTotalizacao(13);
			filtro.setMedicao(indicadorMedicao);

			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoElo(filtro));

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Setor Comercial ,Localidade e Elo
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoSetorComercialLocalidade(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		filtro
				.setTipoGroupBy("histograma.codigoSetorComercial,histograma.localidade.id ");

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			int localidadeAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			FiltroSetorComercial filtroSetorComercial = null;
			Collection colecaoSetorComercial = null;

			Integer codigoSetorComercial = null;
			SetorComercial setorComercial = null;
			Localidade localidade = null;

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				String[] arrayNumeracao = chave.split(";");

				codigoSetorComercial = new Integer(arrayNumeracao[0]);

				localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[1]));

				if (localidadeAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					localidadeAnterior = localidade.getId();
				}

				filtro.setMedicao(indicadorMedicao);
				filtro.setCodigoSetorComercial(codigoSetorComercial);
				filtro.setLocalidade(localidade);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						codigoSetorComercial));

				filtroSetorComercial
						.adicionarParametro(new ParametroSimples(
								FiltroSetorComercial.ID_LOCALIDADE, localidade
										.getId()));

				filtroSetorComercial
						.adicionarCaminhoParaCarregamentoEntidade("localidade");

				// Recupera Setor Comercial
				colecaoSetorComercial = this.getControladorUtil().pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				setorComercial = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoSetorComercial);

				String descricaoOpcaoTotalizacao = setorComercial
						.getLocalidade().getId()
						+ "-"
						+ setorComercial.getLocalidade().getDescricao()
						+ " / "
						+ setorComercial.getCodigo()
						+ "-"
						+ setorComercial.getDescricao();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

				localidadeAnterior = localidade.getId();

			}

			filtro.setOpcaoTotalizacao(16);
			filtro.setMedicao(indicadorMedicao);

			Localidade localAnterior = new Localidade();
			localAnterior.setId(localidadeAnterior);

			filtro.setLocalidade(localAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoLocalidade(filtro));
		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Setor Comercial e Quadra
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoQuadraSetorComercial(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		filtro
				.setTipoGroupBy("histograma.numeroQuadra,histograma.setorComercial.id ");

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			int setorComercialAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			FiltroQuadra filtroQuadra = null;
			Collection colecaoQuadra = null;

			Integer numeroQuadra = null;
			SetorComercial setorComercial = null;
			Quadra quadra = null;

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				String[] arrayNumeracao = chave.split(";");

				numeroQuadra = new Integer(arrayNumeracao[0]);

				setorComercial = new SetorComercial();
				setorComercial.setId(new Integer(arrayNumeracao[1]));

				if (setorComercialAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					setorComercialAnterior = setorComercial.getId();
				}

				filtro.setMedicao(indicadorMedicao);
				filtro.setNumeroQuadra(numeroQuadra);
				filtro.setSetorComercial(setorComercial);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, numeroQuadra));

				filtroQuadra
						.adicionarParametro(new ParametroSimples(
								FiltroQuadra.ID_SETORCOMERCIAL, setorComercial
										.getId()));

				filtroQuadra
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroQuadra
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");

				// Recupera Quadra
				colecaoQuadra = this.getControladorUtil().pesquisar(
						filtroQuadra, Quadra.class.getName());

				quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

				String descricaoOpcaoTotalizacao = quadra.getSetorComercial()
						.getLocalidade().getId()
						+ "-"
						+ quadra.getSetorComercial().getLocalidade()
								.getDescricao()
						+ " / "
						+ quadra.getSetorComercial().getCodigo()
						+ "-"
						+ quadra.getSetorComercial().getDescricao()
						+ " / "
						+ quadra.getNumeroQuadra();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

				setorComercialAnterior = setorComercial.getId();

			}

			SetorComercial setorAnterior = new SetorComercial();
			setorAnterior.setId(setorComercialAnterior);

			filtro.setOpcaoTotalizacao(19);
			filtro.setMedicao(indicadorMedicao);
			filtro.setSetorComercial(setorAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoSetorComercial(filtro));
		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * Setor Comercial ,Localidade e Elo
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoQuadraLocalidade(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		filtro
				.setTipoGroupBy("histograma.numeroQuadra,histograma.setorComercial.id,"
						+ "histograma.localidade.id ");

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		Short indicadorMedicao = filtro.getMedicao();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			int localidadeAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int setorComercialAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			FiltroQuadra filtroQuadra = null;
			Collection colecaoQuadra = null;

			Integer numeroQuadra = null;

			Quadra quadra = null;
			SetorComercial setorComercial = null;
			Localidade localidade = null;

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				String[] arrayNumeracao = chave.split(";");

				numeroQuadra = new Integer(arrayNumeracao[0]);

				setorComercial = new SetorComercial();
				setorComercial.setId(new Integer(arrayNumeracao[1]));

				localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[2]));

				if (setorComercialAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					setorComercialAnterior = setorComercial.getId();
				}

				if (localidadeAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					localidadeAnterior = localidade.getId();
				}

				filtro.setMedicao(indicadorMedicao);
				filtro.setSetorComercial(setorComercial);
				filtro.setLocalidade(localidade);

				// Mudou de Setor Comercial
				if (setorComercialAnterior != setorComercial.getId().intValue()) {

					filtro.setOpcaoTotalizacao(19);

					SetorComercial setorAnterior = new SetorComercial();
					setorAnterior.setId(setorComercialAnterior);
					filtro.setSetorComercial(setorAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoSetorComercial(filtro));
				}

				filtro.setMedicao(indicadorMedicao);

				// Mudou de Localidade
				if (localidadeAnterior != localidade.getId().intValue()) {

					filtro.setOpcaoTotalizacao(16);

					Localidade localAnterior = new Localidade();
					localAnterior.setId(localidadeAnterior);

					filtro.setLocalidade(localAnterior);

					colecaoEmitirHistogramaEsgoto.addAll(this
							.emitirHistogramaEsgotoLocalidade(filtro));
				}

				filtro.setNumeroQuadra(numeroQuadra);
				filtro.setSetorComercial(setorComercial);
				filtro.setLocalidade(localidade);
				filtro.setMedicao(indicadorMedicao);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, numeroQuadra));

				filtroQuadra
						.adicionarParametro(new ParametroSimples(
								FiltroQuadra.ID_SETORCOMERCIAL, setorComercial
										.getId()));

				filtroQuadra
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroQuadra
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");

				// Recupera Quadra
				colecaoQuadra = this.getControladorUtil().pesquisar(
						filtroQuadra, Quadra.class.getName());

				quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

				String descricaoOpcaoTotalizacao = quadra.getSetorComercial()
						.getLocalidade().getId()
						+ "-"
						+ quadra.getSetorComercial().getLocalidade()
								.getDescricao()
						+ " / "
						+ quadra.getSetorComercial().getCodigo()
						+ "-"
						+ quadra.getSetorComercial().getDescricao()
						+ " / "
						+ quadra.getNumeroQuadra();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}

				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

				localidadeAnterior = localidade.getId();
				setorComercialAnterior = setorComercial.getId();

			}

			SetorComercial setorAnterior = new SetorComercial();
			setorAnterior.setId(setorComercialAnterior);

			filtro.setOpcaoTotalizacao(19);
			filtro.setMedicao(indicadorMedicao);
			filtro.setSetorComercial(setorAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoSetorComercial(filtro));

			Localidade localAnterior = new Localidade();
			localAnterior.setId(localidadeAnterior);

			filtro.setOpcaoTotalizacao(16);
			filtro.setMedicao(indicadorMedicao);
			filtro.setLocalidade(localAnterior);

			colecaoEmitirHistogramaEsgoto.addAll(this
					.emitirHistogramaEsgotoLocalidade(filtro));

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto -
	 * 
	 * Localidade
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoLocalidade(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		filtro.setTipoGroupBy("histograma.localidade.id ");

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		filtro.setCodigoSetorComercial(null);
		filtro.setSetorComercial(null);
		filtro.setEloPolo(null);
		filtro.setConsumoFaixaLigacao(null);

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoLocalidade = null;
			Localidade localidade = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				localidade = new Localidade();
				localidade.setId(new Integer(chave));

				filtro.setLocalidade(localidade);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, chave));

				// Recupera Localidade
				colecaoLocalidade = this.getControladorUtil().pesquisar(
						filtroLocalidade, Localidade.class.getName());

				localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				String descricaoOpcaoTotalizacao = localidade.getId() + "-"
						+ localidade.getDescricao();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}
				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

			}

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto -
	 * 
	 * Setor Comercial
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoSetorComercial(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		filtro.setTipoGroupBy("histograma.setorComercial.id ");

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();
		
		filtro.setConsumoFaixaLigacao(null);
		
		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			FiltroSetorComercial filtroSetorComercial = null;
			Collection colecaoSetorComercial = null;
			SetorComercial setorComercial = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				setorComercial = new SetorComercial();
				setorComercial.setId(new Integer(chave));

				filtro.setSetorComercial(setorComercial);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID, chave));

				filtroSetorComercial
						.adicionarCaminhoParaCarregamentoEntidade("localidade");

				// Recupera Setor Comercial
				colecaoSetorComercial = this.getControladorUtil().pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				setorComercial = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoSetorComercial);

				String descricaoOpcaoTotalizacao = setorComercial
						.getLocalidade().getId()
						+ "-"
						+ setorComercial.getLocalidade().getDescricao()
						+ " / "
						+ setorComercial.getCodigo()
						+ "-"
						+ setorComercial.getDescricao();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}
				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

			}

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	/**
	 * [UC0600] Emitir Histograma de Esgoto -
	 * 
	 * Quadra
	 * 
	 * @author Rafael Pinto
	 * @date 06/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoHelper> emitirHistogramaEsgotoQuadra(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException {

		Collection<EmitirHistogramaEsgotoHelper> colecaoEmitirHistogramaEsgoto = new ArrayList<EmitirHistogramaEsgotoHelper>();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoMedido();

		Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = filtro
				.getColecaoConsumoFaixaLigacaoNaoMedido();

		filtro.setTipoGroupBy("histograma.quadra.id ");

		LinkedHashMap hashMapTotalGeral = new LinkedHashMap();

		try {

			if (filtro.getMedicao() != null) {
				if (filtro.getMedicao().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					filtro.setConsumoFaixaLigacaoIntervaloMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				} else {
					filtro.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
							.retornaOLimiteConsultaTotal(filtro.getMedicao()));
				}
			} else {
				filtro
						.setConsumoFaixaLigacaoIntervaloMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro
						.setConsumoFaixaLigacaoIntervaloNaoMedido(filtro
								.retornaOLimiteConsultaTotal(ConstantesSistema.INDICADOR_USO_DESATIVO));
			}

			Collection<Object[]> colecaoTotalGeral = this.repositorioFaturamento
					.pesquisarEmitirHistogramaEsgoto(filtro);

			hashMapTotalGeral = this.montarEmitirHistogramaEsgotoTotalGeral(
					filtro, colecaoTotalGeral);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()) {

			FiltroQuadra filtroQuadra = null;
			Collection colecaoQuadra = null;
			Quadra quadra = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();

			while (iter.hasNext()) {

				String chave = (String) iter.next();

				quadra = new Quadra();
				quadra.setId(new Integer(chave));

				filtro.setQuadra(quadra);

				EmitirHistogramaEsgotoHelper emitirHistogramaEsgotoHelperTotalGeral = (EmitirHistogramaEsgotoHelper) hashMapTotalGeral
						.get(chave);

				HashMap mapTotalizacaoCategoria = this
						.montarEmitirHistogramaEsgotoTotalGeralCategoria(emitirHistogramaEsgotoHelperTotalGeral
								.getColecaoEmitirHistogramaEsgotoDetalhe());

				filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID, chave));

				filtroQuadra
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroQuadra
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");

				// Recupera Setor Comercial
				colecaoQuadra = this.getControladorUtil().pesquisar(
						filtroQuadra, Quadra.class.getName());

				quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

				String descricaoOpcaoTotalizacao = quadra.getSetorComercial()
						.getLocalidade().getId()
						+ "-"
						+ quadra.getSetorComercial().getLocalidade()
								.getDescricao()
						+ " / "
						+ quadra.getSetorComercial().getCodigo()
						+ "-"
						+ quadra.getSetorComercial().getDescricao()
						+ " / "
						+ quadra.getNumeroQuadra();

				// Vai gerar Faixa para medido
				if (colecaoConsumoFaixaLigacaoMedido != null
						&& !colecaoConsumoFaixaLigacaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_ATIVO,
							mapTotalizacaoCategoria);

				}

				// Vai gerar Faixa para Não medido
				if (colecaoConsumoFaixaLigacaoNaoMedido != null
						&& !colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()) {

					this.montarEmitirHistogramaEsgotoFaixaConsumo(
							colecaoConsumoFaixaLigacaoNaoMedido, filtro,
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeLigacoes(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalValorFaturado(),
							emitirHistogramaEsgotoHelperTotalGeral
									.getTotalQuantidadeVolumeTotal(),
							colecaoEmitirHistogramaEsgoto,
							descricaoOpcaoTotalizacao,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							mapTotalizacaoCategoria);
				}
				// Setar Total Geral
				emitirHistogramaEsgotoHelperTotalGeral
						.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialLigacao(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialConsumo(100.0);
				emitirHistogramaEsgotoHelperTotalGeral
						.setTotalPercentualParcialFaturamento(100.0);
				colecaoEmitirHistogramaEsgoto
						.add(emitirHistogramaEsgotoHelperTotalGeral);

			}

		}

		return colecaoEmitirHistogramaEsgoto;
	}

	
	/**
	 * [UC0606] Emitir Histograma de Água por Economia
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	public Collection<EmitirHistogramaEsgotoEconomiaHelper> pesquisarEmitirHistogramaEsgotoEconomia(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {

		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();
		
		Collection<BigDecimal> colecao = filtro.getColecaoPercentualLigacaoEsgoto();
		
		if(colecao != null && !colecao.isEmpty()){
			
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				
				BigDecimal percentual = (BigDecimal) itera.next();
			
				FiltrarEmitirHistogramaEsgotoEconomiaHelper novoFiltro = 
					new FiltrarEmitirHistogramaEsgotoEconomiaHelper(filtro);
				
				FiltrarEmitirHistogramaEsgotoEconomiaHelper novoFiltroClone = 
					new FiltrarEmitirHistogramaEsgotoEconomiaHelper(filtro);
				
				novoFiltro.setPercentualLigacaoEsgoto(percentual);
				novoFiltroClone.setPercentualLigacaoEsgoto(percentual);
				
				Collection<Integer> colecaoTarifa = filtro.getColecaoTarifa();
				
				if(colecaoTarifa != null && !colecaoTarifa.isEmpty()){
					
					Iterator iteraTarifa = colecaoTarifa.iterator();
					
					while (iteraTarifa.hasNext()) {
						Integer tarifa = (Integer) iteraTarifa.next();

						novoFiltro.setTarifa(tarifa);
						novoFiltroClone.setTarifa(tarifa);
						
						colecaoEmitirHistogramaEsgotoEconomia.addAll(this.montarSwitchHistogramaEsgotoEconomia(novoFiltro,novoFiltroClone));
					}
				}else{
					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.montarSwitchHistogramaEsgotoEconomia(novoFiltro,novoFiltroClone));
				}
			}
			
		}else{
			
			Collection<Integer> colecaoTarifa = filtro.getColecaoTarifa();
			
			if(colecaoTarifa != null && !colecaoTarifa.isEmpty()){
				
				Iterator iteraTarifa = colecaoTarifa.iterator();
				
				while (iteraTarifa.hasNext()) {
					Integer tarifa = (Integer) iteraTarifa.next();

					FiltrarEmitirHistogramaEsgotoEconomiaHelper novoFiltro = 
						new FiltrarEmitirHistogramaEsgotoEconomiaHelper(filtro);
					
					FiltrarEmitirHistogramaEsgotoEconomiaHelper novoFiltroClone = 
						new FiltrarEmitirHistogramaEsgotoEconomiaHelper(filtro);

					novoFiltro.setTarifa(tarifa);
					novoFiltroClone.setTarifa(tarifa);
					
					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.montarSwitchHistogramaEsgotoEconomia(novoFiltro,novoFiltroClone));
				}
			}			
		}
		
		FiltrarEmitirHistogramaEsgotoEconomiaHelper filtroClone2 = 
			new FiltrarEmitirHistogramaEsgotoEconomiaHelper(filtro);
		
		colecaoEmitirHistogramaEsgotoEconomia.addAll(
			this.montarSwitchHistogramaEsgotoEconomia(filtro,filtroClone2));
		
		
		return colecaoEmitirHistogramaEsgotoEconomia;
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia 
	 * 
	 * Monta switch 
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> montarSwitchHistogramaEsgotoEconomia(
		FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro,
		FiltrarEmitirHistogramaEsgotoEconomiaHelper filtroClone) throws ControladorException {

		int opcaoTotalizacao = filtro.getOpcaoTotalizacao();

		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();
		
		switch (opcaoTotalizacao) {
		
			// Estado
			case 1:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEstado(filtro));
				break;
			
			
			// Estado por Gerencia Regional
			case 2:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaGerenciaRegional(filtro));
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEstado(filtroClone));
				break;
				
			// Estado por Unidade Negocio				
			case 3:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocioGerenciaRegional(filtro));
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEstado(filtroClone));
				break;
					
			// Estado por Elo Polo
			case 4:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPoloUnidadeNegocioGerenciaRegional(filtro));
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEstado(filtroClone));
				break;
				
			// Estado por Localidade
			case 5:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidadeEloPoloUnidadeNegocioGerenciaRegional(filtro));
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEstado(filtroClone));
				break;
			
			// Gerência Regional
			case 6:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaGerenciaRegional(filtro));
				break;
				
			// Gerência Regional por Unidade Negocio
			case 7:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocioGerenciaRegional(filtro));
				break;
	
			// Gerência Regional por Elo
			case 8:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPoloUnidadeNegocioGerenciaRegional(filtro));
				break;
	
			// Gerência Regional por Localidade
			case 9:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidadeEloPoloUnidadeNegocioGerenciaRegional(filtro));
				break;
				
			// Unidade de Negocio
			case 10:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocio(filtro));
				break;
				
			// Unidade de Negocio por Elo
			case 11:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPoloUnidadeNegocio(filtro));
				break;
				
			// Unidade de Negocio por Localidade
			case 12:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidadeEloPoloUnidadeNegocio(filtro));
				break;
				
			// Elo Polo
			case 13:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPolo(filtro));
				break;
	
			// Elo Polo Por Localidade
			case 14:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidadeEloPolo(filtro));
				break;
				
			// Elo Polo Por Setor Comercial
			case 15:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaSetorComercialLocalidadeEloPolo(filtro));
				break;
	
			// Localidade
			case 16:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidade(filtro));
				break;
	
			// Localidade Por Setor Comercial
			case 17:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaSetorComercialLocalidade(filtro));
				break;
	
			// Localidade Por Quadra
			case 18:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaQuadraSetorComercialLocalidade(filtro));
				break;
	
			// Setor Comercial
			case 19:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaSetorComercial(filtro));
				break;
	
			// Setor Comercial Por Quadra
			case 20:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaQuadraSetorComercial(filtro));
				break;
				
			// Quadra
			case 21:
				colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaQuadra(filtro));
				break;
				
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia 
	 * 
	 * Estado
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaEstado(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}
		
		String descricaoTarifa = "TOTALIZADOR";
		if(filtro.getTarifa() != null){

			FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
			
			filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
			filtroConsumoTarifa.adicionarParametro(
				new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
			
			Collection colecaoTarifa = 
				this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
			
			ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
			
			descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
		}
		
		LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
		Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
		Collection colecaoConsumoFaixaCategoria = null;
		EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
		Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
		
		Categoria categoria = null;
		
		FiltroCategoria filtroCategoria = null;
		Collection colecaoConsulta = null;

		Iterator itera = colecaoCategorias.iterator();
		
		String descricaoOpcaoTotalizacao = 
			this.getControladorUtil().pesquisarParametrosDoSistema().getNomeEstado();

		Integer totalGeralEconomiasMedido = 0;
		Integer totalGeralVolumeConsumoMedido = 0;
		Integer totalGeralVolumeFaturadoMedido = 0;
		BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
		Integer totalGeralLigacaoMedido = 0;
		
		Integer totalGeralEconomiasNaoMedido = 0;
		Integer totalGeralVolumeConsumoNaoMedido = 0;
		Integer totalGeralVolumeFaturadoNaoMedido = 0;
		BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
		Integer totalGeralLigacaoNaoMedido = 0;

		int quantidadeCategoriaComValores = 0;
		
		while (itera.hasNext()) {
			
			String idCategoria = (String) itera.next();
			
			emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
			emitirHistograma.setDescricaoPercentual(descricaoPercentual);
			emitirHistograma.setDescricaoTarifa(descricaoTarifa);
			
			colecaoEmitirHistogramaEsgotoEconomiaDetalhe = 
				new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
			
			colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
			
			Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
			
			int quantidadeFaixaComValores = 0;
			int limiteSuperiorFaixaAnterior = 0;
			
			Integer totalEconomiasMedido = 0;
			Integer totalVolumeConsumoMedido = 0;
			Integer totalVolumeFaturadoMedido = 0;
			Integer totalLigacaoMedido = 0;
			BigDecimal totalReceitaMedido = new BigDecimal(0.0);
			
			Integer totalEconomiasNaoMedido = 0;
			Integer totalVolumeConsumoNaoMedido = 0;
			Integer totalVolumeFaturadoNaoMedido = 0;
			Integer totalLigacaoNaoMedido = 0;
			BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
			
			filtroCategoria = new FiltroCategoria();
			filtroCategoria.adicionarParametro(
				new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
				
			// Recupera Categoria
			colecaoConsulta = 
				this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
		
			categoria = 
				(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

			String descricaoCategoria = categoria.getDescricao();
			
			filtro.setCategoria(categoria);

			while (iteraFaixas.hasNext()) {
				
				ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();
				
				EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
					this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,
						consumoFaixaCategoria,limiteSuperiorFaixaAnterior);

				emitirHistograma.setDescricaoCategoria(descricaoCategoria);
				limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
				
				colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
				
				totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
				totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
				totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
				totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
				totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
				
				totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
				totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
				totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
				totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
				totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
				
				if(detalhe.isExisteHistograma()){
					quantidadeFaixaComValores++;
				}

			}

			if(quantidadeFaixaComValores != 0){
				quantidadeCategoriaComValores++;
			}

			emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
			
			emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
			emitirHistograma.setDescricaoFaixa("TOTAL");
			
			emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
			emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
			emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
			emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
			emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
			
			emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
			emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
			emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
			emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
			emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
			
			colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
			
			totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
			totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
			totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
			totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
			totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
			
			totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
			totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
			totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
			totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
			totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
		}

		if (quantidadeCategoriaComValores != 0){
		
			emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
			
			emitirHistograma.setDescricaoPercentual(descricaoPercentual);
			emitirHistograma.setDescricaoTarifa(descricaoTarifa);
			emitirHistograma.setDescricaoCategoria(null);
			emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
			emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
			
			//Medido(COM HIDROMETRO)
			emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
			emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
			emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
			emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
			emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
			
			//Não Medido(SEM HIDROMETRO)
			emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
			emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
			emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
			emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
			emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
			
			// TOTAL GERAL
			colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
		}else{
			colecaoEmitirHistogramaEsgotoEconomia = 
				new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();			
		}
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia 
	 * 
	 * Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaGerenciaRegional(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		filtro.setEloPolo(null);
		filtro.setUnidadeNegocio(null);
		filtro.setLocalidade(null);
		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();
		
		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.gerenciaRegional.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}

			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			GerenciaRegional gerencia = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroGerenciaRegional filtroGerencia = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String IdGerencia = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroGerencia = new FiltroGerenciaRegional();
				filtroGerencia.adicionarParametro(
					new ParametroSimples(FiltroGerenciaRegional.ID,IdGerencia));
				
				// Recupera Gerencia Regional
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroGerencia, GerenciaRegional.class.getName());
			
				gerencia = 
					(GerenciaRegional) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = gerencia.getId()+" - "+gerencia.getNome();

				filtro.setGerenciaRegional(gerencia);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;


				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
						
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(IdGerencia);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia  
	 * 
	 * Unidade de Negocio e Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaUnidadeNegocioGerenciaRegional(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.unidadeNegocio.id,histograma.gerenciaRegional.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			UnidadeNegocio unidadeNegocio = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroUnidadeNegocio filtroUnidadeNegocio = null;
			Collection colecaoConsulta = null;
			
			//Pai da unidade Negocioa(unidade -> gerencia)
			int gerenciaAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				GerenciaRegional gerencia = new GerenciaRegional();
				gerencia.setId(new Integer(arrayNumeracao[1]));

				if(gerenciaAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					gerenciaAnterior = gerencia.getId();
				}
				
				//Mudou de Gerencia
				if(gerenciaAnterior != gerencia.getId().intValue()){
					
					filtro.setOpcaoTotalizacao(2);
					
					GerenciaRegional gereAnterior = new GerenciaRegional();
					gereAnterior.setId(gerenciaAnterior);
					filtro.setGerenciaRegional(gereAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaGerenciaRegional(filtro));
				}

				filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(
					new ParametroSimples(FiltroUnidadeNegocio.ID,arrayNumeracao[0]));
				filtroUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				
				// Recupera Unidade Negocio
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
				unidadeNegocio = 
					(UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					unidadeNegocio.getGerenciaRegional().getId()+"-"+unidadeNegocio.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					unidadeNegocio.getId()+"-"+unidadeNegocio.getNome();

				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setGerenciaRegional(gerencia);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
				
				gerenciaAnterior = gerencia.getId();
			}
			
			filtro.setOpcaoTotalizacao(2);
			
			GerenciaRegional gereAnterior = new GerenciaRegional();
			gereAnterior.setId(gerenciaAnterior);
			filtro.setGerenciaRegional(gereAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaGerenciaRegional(filtro));
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia  
	 * 
	 * Elo Polo e Unidade de Negocio e Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaEloPoloUnidadeNegocioGerenciaRegional(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();
		
		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.localidadeElo.id," 
				+ "histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");

		filtro.setTipoOrderBy("2,1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			Localidade eloPolo = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;
			
			//Pai do elo Polo(eloPolo -> unidade -> gerencia)
			int gerenciaAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[1]));
				
				GerenciaRegional gerencia = new GerenciaRegional();
				gerencia.setId(new Integer(arrayNumeracao[2]));
				
				if(unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				if(gerenciaAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					gerenciaAnterior = gerencia.getId();
				}
				
				//Mudou de Unidade
				if(unidadeNegocioAnterior != unidadeNegocio.getId().intValue()){
					
					filtro.setOpcaoTotalizacao(10);
					
					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					filtro.setUnidadeNegocio(uniAnterior);
					
					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocio(filtro));
				}
				
				//Mudou de Gerencia
				if(gerenciaAnterior != gerencia.getId().intValue()){
					
					filtro.setOpcaoTotalizacao(2);
					
					GerenciaRegional gereAnterior = new GerenciaRegional();
					gereAnterior.setId(gerenciaAnterior);
					filtro.setGerenciaRegional(gereAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaGerenciaRegional(filtro));
				}

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,arrayNumeracao[0]));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Localidade
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
			
				eloPolo = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					eloPolo.getGerenciaRegional().getId()+"-"+eloPolo.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					eloPolo.getUnidadeNegocio().getId()+"-"+eloPolo.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					eloPolo.getId()+"-"+eloPolo.getDescricao();

				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setGerenciaRegional(gerencia);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
				
				gerenciaAnterior = gerencia.getId();
				unidadeNegocioAnterior = unidadeNegocio.getId();
			}
			//Unidade de Negocio
			filtro.setOpcaoTotalizacao(10);
			
			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);
			
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocio(filtro));
			
			//Gerencia Regional
			filtro.setOpcaoTotalizacao(2);
			
			GerenciaRegional gereAnterior = new GerenciaRegional();
			gereAnterior.setId(gerenciaAnterior);
			filtro.setGerenciaRegional(gereAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaGerenciaRegional(filtro));
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia  
	 * 
	 * Elo Polo e Unidade de Negocio e Gerencia Regional
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaLocalidadeEloPoloUnidadeNegocioGerenciaRegional(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();
		
		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.localidade.id,histograma.localidadeElo.id," 
				+ "histograma.unidadeNegocio.id,histograma.gerenciaRegional.id ");

		filtro.setTipoOrderBy("4,3,2,1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){

			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			Localidade localidade = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;
			
			//Pai do elo Polo(eloPolo -> unidade -> gerencia)
			int gerenciaAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				Localidade eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[1]));

				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[2]));
				
				GerenciaRegional gerencia = new GerenciaRegional();
				gerencia.setId(new Integer(arrayNumeracao[3]));
				
				if(unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				if(gerenciaAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					gerenciaAnterior = gerencia.getId();
				}

				if(eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					eloPoloAnterior = eloPolo.getId();
				}
				
				//Mudou de Elo Polo
				if(eloPoloAnterior != eloPolo.getId().intValue()){
					
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					
					filtro.setOpcaoTotalizacao(13);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPolo(filtro));
				}
				
				//Mudou de Unidade
				if(unidadeNegocioAnterior != unidadeNegocio.getId().intValue()){
					
					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					
					filtro.setOpcaoTotalizacao(10);
					filtro.setUnidadeNegocio(uniAnterior);
					
					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocio(filtro));
				}
				
				//Mudou de Gerencia
				if(gerenciaAnterior != gerencia.getId().intValue()){
					
					GerenciaRegional gereAnterior = new GerenciaRegional();
					gereAnterior.setId(gerenciaAnterior);
					
					filtro.setOpcaoTotalizacao(2);
					filtro.setGerenciaRegional(gereAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaGerenciaRegional(filtro));
				}

				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,arrayNumeracao[0]));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Localidade
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
				
				localidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					localidade.getGerenciaRegional().getId()+"-"+localidade.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					localidade.getUnidadeNegocio().getId()+"-"+localidade.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					localidade.getLocalidade().getId()+"-"+localidade.getLocalidade().getDescricao()
					+" / "+
					localidade.getId()+"-"+localidade.getDescricao();
				
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				filtro.setGerenciaRegional(gerencia);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
					
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
				
				gerenciaAnterior = gerencia.getId();
				unidadeNegocioAnterior = unidadeNegocio.getId();
				eloPoloAnterior = eloPolo.getId();
			}
			
			//Elo Polo
			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);

			filtro.setOpcaoTotalizacao(13);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPolo(filtro));
			
			
			//Unidade de Negocio
			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);

			filtro.setOpcaoTotalizacao(10);
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocio(filtro));
			
			//Gerencia Regional
			GerenciaRegional gereAnterior = new GerenciaRegional();
			gereAnterior.setId(gerenciaAnterior);

			filtro.setOpcaoTotalizacao(2);
			filtro.setGerenciaRegional(gereAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaGerenciaRegional(filtro));
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia - Unidade de Negocio
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaUnidadeNegocio(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		filtro.setEloPolo(null);
		filtro.setGerenciaRegional(null);
		filtro.setLocalidade(null);
		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.unidadeNegocio.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){

			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}

			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			UnidadeNegocio unidadeNegocio = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroUnidadeNegocio filtroUnidadeNegocio = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String idUnidadeNegocio = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(
					new ParametroSimples(FiltroUnidadeNegocio.ID,idUnidadeNegocio));
				filtroUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				
				// Recupera Unidade Negocio
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
				unidadeNegocio = 
					(UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					unidadeNegocio.getGerenciaRegional().getId()+"-"+unidadeNegocio.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					unidadeNegocio.getId()+"-"+unidadeNegocio.getNome();

				filtro.setUnidadeNegocio(unidadeNegocio);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(idUnidadeNegocio);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia  
	 * 
	 * Elo Polo e Unidade de Negocio
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaEloPoloUnidadeNegocio(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.localidadeElo.id,histograma.unidadeNegocio.id");
		filtro.setTipoOrderBy("2,1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			Localidade eloPolo = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;
			
			//Pai do elo Polo(eloPolo -> unidade)
			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[1]));
				
				if(unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				//Mudou de Unidade
				if(unidadeNegocioAnterior != unidadeNegocio.getId().intValue()){
					
					filtro.setOpcaoTotalizacao(10);
					
					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					filtro.setUnidadeNegocio(uniAnterior);
					
					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocio(filtro));
				}
				
				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,arrayNumeracao[0]));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Localidade
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
			
				eloPolo = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					eloPolo.getGerenciaRegional().getId()+"-"+eloPolo.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					eloPolo.getUnidadeNegocio().getId()+"-"+eloPolo.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					eloPolo.getId()+"-"+eloPolo.getDescricao();

				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();					
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
				
				unidadeNegocioAnterior = unidadeNegocio.getId();
			}
			//Unidade de Negocio
			filtro.setOpcaoTotalizacao(10);
			
			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);
			
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocio(filtro));
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia  
	 * 
	 * Localidade ,Elo Polo e Unidade de Negocio
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaLocalidadeEloPoloUnidadeNegocio(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.localidade.id,histograma.localidadeElo.id," 
				+ "histograma.unidadeNegocio.id");

		filtro.setTipoOrderBy("3,2,1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			Localidade localidade = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;
			
			//Pai do elo Polo(eloPolo -> unidade -> gerencia)
			int unidadeNegocioAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				Localidade eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[1]));

				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				unidadeNegocio.setId(new Integer(arrayNumeracao[2]));
				
				if(unidadeNegocioAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					unidadeNegocioAnterior = unidadeNegocio.getId();
				}

				if(eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					eloPoloAnterior = eloPolo.getId();
				}
				
				//Mudou de Elo Polo
				if(eloPoloAnterior != eloPolo.getId().intValue()){
					
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					
					filtro.setOpcaoTotalizacao(13);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPolo(filtro));
				}
				
				//Mudou de Unidade
				if(unidadeNegocioAnterior != unidadeNegocio.getId().intValue()){
					
					UnidadeNegocio uniAnterior = new UnidadeNegocio();
					uniAnterior.setId(unidadeNegocioAnterior);
					
					filtro.setOpcaoTotalizacao(10);
					filtro.setUnidadeNegocio(uniAnterior);
					
					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocio(filtro));
				}
				
				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,arrayNumeracao[0]));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Localidade
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
				
				localidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					localidade.getGerenciaRegional().getId()+"-"+localidade.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					localidade.getUnidadeNegocio().getId()+"-"+localidade.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					localidade.getLocalidade().getId()+"-"+localidade.getLocalidade().getDescricao()
					+" / "+
					localidade.getId()+"-"+localidade.getDescricao();
				
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setUnidadeNegocio(unidadeNegocio);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
				
				unidadeNegocioAnterior = unidadeNegocio.getId();
				eloPoloAnterior = eloPolo.getId();
			}
			
			//Elo Polo
			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);

			filtro.setOpcaoTotalizacao(13);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPolo(filtro));
			
			//Unidade de Negocio
			UnidadeNegocio uniAnterior = new UnidadeNegocio();
			uniAnterior.setId(unidadeNegocioAnterior);

			filtro.setOpcaoTotalizacao(10);
			filtro.setUnidadeNegocio(uniAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaUnidadeNegocio(filtro));
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia 
	 * 
	 * Elo Polo
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaEloPolo(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {

		filtro.setGerenciaRegional(null);
		filtro.setUnidadeNegocio(null);
		filtro.setLocalidade(null);
		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.localidadeElo.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			Localidade eloPolo = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String idEloPolo = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,idEloPolo));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Elo Polo
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
			
				eloPolo = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					eloPolo.getGerenciaRegional().getId()+"-"+eloPolo.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					eloPolo.getUnidadeNegocio().getId()+"-"+eloPolo.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					eloPolo.getId()+"-"+eloPolo.getDescricao();

				filtro.setEloPolo(eloPolo);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
					
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(idEloPolo);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia  
	 * 
	 * Localidade e Elo Polo
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaLocalidadeEloPolo(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.localidade.id,histograma.localidadeElo.id");

		filtro.setTipoOrderBy("1,2");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){

			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}

			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			Localidade localidade = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;
			
			//Pai do elo Polo(eloPolo -> unidade -> gerencia)
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				Localidade eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[1]));

				if(eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					eloPoloAnterior = eloPolo.getId();
				}
				
				//Mudou de Elo Polo
				if(eloPoloAnterior != eloPolo.getId().intValue()){
					
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					
					filtro.setOpcaoTotalizacao(13);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPolo(filtro));
				}
				
				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,arrayNumeracao[0]));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				
				// Recupera Localidade
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
				
				localidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					localidade.getGerenciaRegional().getId()+"-"+localidade.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					localidade.getUnidadeNegocio().getId()+"-"+localidade.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					localidade.getLocalidade().getId()+"-"+localidade.getLocalidade().getDescricao()
					+" / "+
					localidade.getId()+"-"+localidade.getDescricao();
				
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
				
				eloPoloAnterior = eloPolo.getId();
			}
			
			//Elo Polo
			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);

			filtro.setOpcaoTotalizacao(13);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPolo(filtro));
			
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia  
	 * 
	 * Setor Comercial ,Localidade e Elo
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaSetorComercialLocalidadeEloPolo(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.codigoSetorComercial,histograma.localidade.id,histograma.localidadeElo.id");
		filtro.setTipoOrderBy("1,2,3");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			SetorComercial setorComercial = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroSetorComercial filtroSetorComercial = null;
			Collection colecaoConsulta = null;
			
			int localidadeAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;			
			int eloPoloAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				Localidade localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[1]));
				
				Localidade eloPolo = new Localidade();
				eloPolo.setId(new Integer(arrayNumeracao[2]));

				if(localidadeAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					localidadeAnterior = localidade.getId();
				}

				if(eloPoloAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					eloPoloAnterior = eloPolo.getId();
				}

				//Mudou de Localidade
				if(localidadeAnterior != localidade.getId().intValue()){
					
					Localidade localAnterior = new Localidade();
					localAnterior.setId(localidadeAnterior);
					
					filtro.setOpcaoTotalizacao(16);
					filtro.setLocalidade(localAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidade(filtro));
				}
				
				//Mudou de Elo Polo
				if(eloPoloAnterior != eloPolo.getId().intValue()){
					
					Localidade eloAnterior = new Localidade();
					eloAnterior.setId(eloPoloAnterior);
					
					filtro.setOpcaoTotalizacao(13);
					filtro.setEloPolo(eloAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPolo(filtro));
				}
				
				
				filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,arrayNumeracao[0]));
				
				filtroSetorComercial.adicionarParametro(
						new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,localidade.getId()));

				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.gerenciaRegional");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.unidadeNegocio");
				
				// Recupera Setor Comercial
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				setorComercial = 
					(SetorComercial) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					setorComercial.getLocalidade().getGerenciaRegional().getId()+"-"+setorComercial.getLocalidade().getGerenciaRegional().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getUnidadeNegocio().getId()+"-"+setorComercial.getLocalidade().getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getLocalidade().getId()+"-"+setorComercial.getLocalidade().getLocalidade().getDescricao()
					+" / "+
					setorComercial.getLocalidade().getId()+"-"+setorComercial.getLocalidade().getDescricao()
					+" / "+
					setorComercial.getCodigo()+"-"+setorComercial.getDescricao();
				
				filtro.setLocalidade(localidade);
				filtro.setEloPolo(eloPolo);
				filtro.setSetorComercial(setorComercial);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
				
				localidadeAnterior = localidade.getId();
				eloPoloAnterior = eloPolo.getId();
			}
			
			//Localidade
			Localidade localAnterior = new Localidade();
			localAnterior.setId(localidadeAnterior);
			
			filtro.setOpcaoTotalizacao(16);
			filtro.setLocalidade(localAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidade(filtro));
			
			//Elo Polo
			Localidade eloAnterior = new Localidade();
			eloAnterior.setId(eloPoloAnterior);

			filtro.setOpcaoTotalizacao(13);
			filtro.setEloPolo(eloAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaEloPolo(filtro));
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}

	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia  
	 * 
	 * Setor Comercial ,Localidade
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaSetorComercialLocalidade(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.codigoSetorComercial,histograma.localidade.id");
		filtro.setTipoOrderBy("1,2");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			SetorComercial setorComercial = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroSetorComercial filtroSetorComercial = null;
			Collection colecaoConsulta = null;
			
			int localidadeAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;			
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				Localidade localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[1]));
				
				if(localidadeAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					localidadeAnterior = localidade.getId();
				}

				//Mudou de Localidade
				if(localidadeAnterior != localidade.getId().intValue()){
					
					Localidade localAnterior = new Localidade();
					localAnterior.setId(localidadeAnterior);
					
					filtro.setOpcaoTotalizacao(16);
					filtro.setLocalidade(localAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidade(filtro));
				}
				
				filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,arrayNumeracao[0]));
				filtroSetorComercial.adicionarParametro(
						new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,localidade));
				
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.gerenciaRegional");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.unidadeNegocio");

				
				// Recupera Setor Comercial
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				setorComercial = 
					(SetorComercial) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					setorComercial.getLocalidade().getGerenciaRegional().getId()+"-"+setorComercial.getLocalidade().getGerenciaRegional().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getUnidadeNegocio().getId()+"-"+setorComercial.getLocalidade().getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getLocalidade().getId()+"-"+setorComercial.getLocalidade().getLocalidade().getDescricao()
					+" / "+
					setorComercial.getLocalidade().getId()+"-"+setorComercial.getLocalidade().getDescricao()
					+" / "+
					setorComercial.getId()+"-"+setorComercial.getDescricao();
				
				filtro.setLocalidade(localidade);
				filtro.setSetorComercial(setorComercial);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
				
				localidadeAnterior = localidade.getId();
			}
			
			//Localidade
			Localidade localAnterior = new Localidade();
			localAnterior.setId(localidadeAnterior);
			
			filtro.setOpcaoTotalizacao(16);
			filtro.setLocalidade(localAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidade(filtro));
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}

	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia 
	 * 
	 * Localidade
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaLocalidade(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {

		filtro.setSetorComercial(null);
		filtro.setEloPolo(null);
		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();
		
		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.localidade.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){

			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}

			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			Localidade localidade = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroLocalidade filtroLocalidade = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String idLocalidade = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,idLocalidade));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				
				// Recupera Elo Polo
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
			
				localidade = 
					(Localidade) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					localidade.getGerenciaRegional().getId()+"-"+localidade.getGerenciaRegional().getNomeAbreviado()
					+" / "+
					localidade.getUnidadeNegocio().getId()+"-"+localidade.getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					localidade.getLocalidade().getId()+"-"+localidade.getLocalidade().getDescricao()
					+" / "+
					localidade.getId()+"-"+localidade.getDescricao();

				filtro.setLocalidade(localidade);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);					
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(idLocalidade);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia  
	 * 
	 * Quadra e Setor Comercial
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaQuadraSetorComercialLocalidade(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.quadra.id,histograma.setorComercial.id,histograma.localidade.id");
		filtro.setTipoOrderBy("1,2,3");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			Quadra quadra = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroQuadra filtroQuadra = null;
			Collection colecaoConsulta = null;
			
			int localidadeAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int setorComercialAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setId(new Integer(arrayNumeracao[1]));
				
				Localidade localidade = new Localidade();
				localidade.setId(new Integer(arrayNumeracao[2]));
				
				if(setorComercialAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					setorComercialAnterior = setorComercial.getId();
				}

				if(localidadeAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					localidadeAnterior = localidade.getId();
				}

				//Mudou de Setor Comercial
				if(setorComercialAnterior != setorComercial.getId().intValue()){

					SetorComercial setorAnterior = new SetorComercial();
					setorAnterior.setId(setorComercialAnterior);

					filtro.setOpcaoTotalizacao(19);
					filtro.setSetorComercial(setorAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaSetorComercial(filtro));
				}

				//Mudou de Localidade
				if(localidadeAnterior != localidade.getId().intValue()){
					
					Localidade localAnterior = new Localidade();
					localAnterior.setId(localidadeAnterior);

					filtro.setOpcaoTotalizacao(16);
					filtro.setLocalidade(localAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidade(filtro));
				}
				

				filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(
					new ParametroSimples(FiltroQuadra.ID,arrayNumeracao[0]));
				
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
				
				// Recupera Quadra
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
				
				quadra = 
					(Quadra) Util.retonarObjetoDeColecao(colecaoConsulta);
				
				String descricaoOpcaoTotalizacao = 
					quadra.getSetorComercial().getLocalidade().getId()+"-"+quadra.getSetorComercial().getLocalidade().getDescricao()
					+" / "+
					quadra.getSetorComercial().getCodigo()+"-"+quadra.getSetorComercial().getDescricao()
					+" / "+
					quadra.getNumeroQuadra();
				
				filtro.setQuadra(quadra);
				filtro.setSetorComercial(setorComercial);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);

				localidadeAnterior = localidade.getId();
				setorComercialAnterior = setorComercial.getId();			
			}
			
			//Setor Comercial
			SetorComercial setorAnterior = new SetorComercial();
			setorAnterior.setId(setorComercialAnterior);

			filtro.setOpcaoTotalizacao(19);
			filtro.setSetorComercial(setorAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaSetorComercial(filtro));
			
			//Mudou Localidade
			Localidade localAnterior = new Localidade();
			localAnterior.setId(localidadeAnterior);

			filtro.setOpcaoTotalizacao(16);
			filtro.setLocalidade(localAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaLocalidade(filtro));
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia 
	 * 
	 * Setor Comercial
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaSetorComercial(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {

		filtro.setQuadra(null);
		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);

		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.setorComercial.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){

			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			SetorComercial setorComercial = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroSetorComercial filtroSetorComercial = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String idSetorComercial = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.ID,idSetorComercial));

				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.gerenciaRegional");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.localidade");
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade.unidadeNegocio");

				// Recupera Setor Comercial
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
				setorComercial = 
					(SetorComercial) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					setorComercial.getLocalidade().getGerenciaRegional().getId()+"-"+setorComercial.getLocalidade().getGerenciaRegional().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getUnidadeNegocio().getId()+"-"+setorComercial.getLocalidade().getUnidadeNegocio().getNomeAbreviado()
					+" / "+
					setorComercial.getLocalidade().getLocalidade().getId()+"-"+setorComercial.getLocalidade().getLocalidade().getDescricao()
					+" / "+
					setorComercial.getLocalidade().getId()+"-"+setorComercial.getLocalidade().getDescricao()
					+" / "+
					setorComercial.getId()+"-"+setorComercial.getDescricao();

				filtro.setSetorComercial(setorComercial);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
					
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(idSetorComercial);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}

	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia  
	 * 
	 * Quadra e Setor Comercial
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaQuadraSetorComercial(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {
		
		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.quadra.id,histograma.setorComercial.id");
		filtro.setTipoOrderBy("2,1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			Quadra quadra = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroQuadra filtroQuadra = null;
			Collection colecaoConsulta = null;
			
			int setorComercialAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {

				String chave = (String) iter.next();
				
				String[] arrayNumeracao = chave.split(";");
				
				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setId(new Integer(arrayNumeracao[1]));
				
				if(setorComercialAnterior == ConstantesSistema.NUMERO_NAO_INFORMADO){
					setorComercialAnterior = setorComercial.getId();
				}

				//Mudou de Setor Comercial
				if(setorComercialAnterior != setorComercial.getId().intValue()){

					SetorComercial setorAnterior = new SetorComercial();
					setorAnterior.setId(setorComercialAnterior);

					filtro.setOpcaoTotalizacao(19);
					filtro.setSetorComercial(setorAnterior);

					colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaSetorComercial(filtro));
				}

				filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(
					new ParametroSimples(FiltroQuadra.ID,arrayNumeracao[0]));
				
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
				
				// Recupera Quadra
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
				
				quadra = 
					(Quadra) Util.retonarObjetoDeColecao(colecaoConsulta);
				
				String descricaoOpcaoTotalizacao = 
					quadra.getSetorComercial().getLocalidade().getId()+"-"+quadra.getSetorComercial().getLocalidade().getDescricao()
					+" / "+
					quadra.getSetorComercial().getCodigo()+"-"+quadra.getSetorComercial().getDescricao()
					+" / "+
					quadra.getNumeroQuadra();
				
				filtro.setQuadra(quadra);
				filtro.setSetorComercial(setorComercial);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;

				Iterator itera = colecaoCategorias.iterator();

				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(chave);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
				
				setorComercialAnterior = setorComercial.getId();			
			}
			
			//Setor Comercial
			SetorComercial setorAnterior = new SetorComercial();
			setorAnterior.setId(setorComercialAnterior);

			filtro.setOpcaoTotalizacao(19);
			filtro.setSetorComercial(setorAnterior);

			colecaoEmitirHistogramaEsgotoEconomia.addAll(this.emitirHistogramaEsgotoEconomiaSetorComercial(filtro));
			
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia 
	 * 
	 * Quadra
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	private Collection<EmitirHistogramaEsgotoEconomiaHelper> emitirHistogramaEsgotoEconomiaQuadra(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException {

		filtro.setMedicao(null);
		filtro.setConsumoFaixaCategoria(null);

		Collection<EmitirHistogramaEsgotoEconomiaHelper> colecaoEmitirHistogramaEsgotoEconomia = 
			new ArrayList<EmitirHistogramaEsgotoEconomiaHelper>();

		String descricaoPercentual = "";
		if(filtro.getPercentualLigacaoEsgoto() != null){
			descricaoPercentual = Util.formataBigDecimal(filtro.getPercentualLigacaoEsgoto(),2,true)+"%";	
		}

		filtro.setTipoGroupBy("histograma.quadra.id");
		filtro.setTipoOrderBy("1");
		
		LinkedHashMap hashMapTotalGeral = 
			this.montarEmitirHistogramaEsgotoEconomiaAgruparChaves(filtro);
		
		if(hashMapTotalGeral != null && !hashMapTotalGeral.isEmpty()){
			
			String descricaoTarifa = "TOTALIZADOR";
			if(filtro.getTarifa() != null){

				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				
				filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
				filtroConsumoTarifa.adicionarParametro(
					new ParametroSimples(FiltroConsumoTarifa.ID,filtro.getTarifa()));
				
				Collection colecaoTarifa = 
					this.getControladorUtil().pesquisar(filtroConsumoTarifa,ConsumoTarifa.class.getName());
				
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifa);
				
				descricaoTarifa = consumoTarifa.getId()+" "+consumoTarifa.getDescricao().trim();	
			}
			
			LinkedHashMap linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
			Collection colecaoCategorias = linkedHashMapConsumoFaixaCategoria.keySet();
			Collection colecaoConsumoFaixaCategoria = null;
			EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = null;
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;
			
			Quadra quadra = null;
			Categoria categoria = null;
			
			FiltroCategoria filtroCategoria = null;
			FiltroQuadra filtroQuadra = null;
			Collection colecaoConsulta = null;

			Iterator iter = hashMapTotalGeral.keySet().iterator();
			
			while (iter.hasNext()) {
				
				String idQuadra = (String) iter.next();
				
				Iterator itera = colecaoCategorias.iterator();
				
				filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID,idQuadra));

				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");

				// Recupera Quadra
				colecaoConsulta = 
					this.getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
			
				quadra = 
					(Quadra) Util.retonarObjetoDeColecao(colecaoConsulta);

				String descricaoOpcaoTotalizacao = 
					quadra.getSetorComercial().getLocalidade().getId()+"-"+quadra.getSetorComercial().getLocalidade().getDescricao()
					+" / "+
					quadra.getSetorComercial().getCodigo()+"-"+quadra.getSetorComercial().getDescricao()
					+" / "+
					quadra.getNumeroQuadra();

				filtro.setQuadra(quadra);
				
				Integer totalGeralEconomiasMedido = 0;
				Integer totalGeralVolumeConsumoMedido = 0;
				Integer totalGeralVolumeFaturadoMedido = 0;
				BigDecimal totalGeralReceitaMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoMedido = 0;
				
				Integer totalGeralEconomiasNaoMedido = 0;
				Integer totalGeralVolumeConsumoNaoMedido = 0;
				Integer totalGeralVolumeFaturadoNaoMedido = 0;
				BigDecimal totalGeralReceitaNaoMedido = new BigDecimal(0.0);
				Integer totalGeralLigacaoNaoMedido = 0;
				
				while (itera.hasNext()) {
					String idCategoria = (String) itera.next();
					
					emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
					emitirHistograma.setDescricaoPercentual(descricaoPercentual);
					emitirHistograma.setDescricaoTarifa(descricaoTarifa);
					colecaoEmitirHistogramaEsgotoEconomiaDetalhe = new ArrayList<EmitirHistogramaEsgotoEconomiaDetalheHelper>();
					
					colecaoConsumoFaixaCategoria = (Collection) linkedHashMapConsumoFaixaCategoria.get(idCategoria);
					
					Iterator iteraFaixas = colecaoConsumoFaixaCategoria.iterator();
					int limiteSuperiorFaixaAnterior = 0;
					
					Integer totalEconomiasMedido = 0;
					Integer totalVolumeConsumoMedido = 0;
					Integer totalVolumeFaturadoMedido = 0;
					Integer totalLigacaoMedido = 0;
					BigDecimal totalReceitaMedido = new BigDecimal(0.0);
					
					Integer totalEconomiasNaoMedido = 0;
					Integer totalVolumeConsumoNaoMedido = 0;
					Integer totalVolumeFaturadoNaoMedido = 0;
					Integer totalLigacaoNaoMedido = 0;
					BigDecimal totalReceitaNaoMedido = new BigDecimal(0.0);
					
					filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO,idCategoria));
					
					// Recupera Categoria
					colecaoConsulta = 
						this.getControladorUtil().pesquisar(filtroCategoria, Categoria.class.getName());
				
					categoria = 
						(Categoria) Util.retonarObjetoDeColecao(colecaoConsulta);

					String descricaoCategoria = categoria.getDescricao();
					
					filtro.setCategoria(categoria);

					while (iteraFaixas.hasNext()) {
						
						ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) iteraFaixas.next();

						EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = 
							this.montarEmitirHistogramaEsgotoEconomiaDetalheHelper(filtro,consumoFaixaCategoria,limiteSuperiorFaixaAnterior);
							
						emitirHistograma.setDescricaoCategoria(descricaoCategoria);
						limiteSuperiorFaixaAnterior = consumoFaixaCategoria.getNumeroFaixaFim();
						
						colecaoEmitirHistogramaEsgotoEconomiaDetalhe.add(detalhe);
						
						totalEconomiasMedido = totalEconomiasMedido + detalhe.getEconomiasMedido();
						totalVolumeConsumoMedido = totalVolumeConsumoMedido + detalhe.getVolumeConsumoFaixaMedido();
						totalVolumeFaturadoMedido = totalVolumeFaturadoMedido + detalhe.getVolumeFaturadoFaixaMedido();
						totalReceitaMedido = totalReceitaMedido.add(detalhe.getReceitaMedido());
						totalLigacaoMedido = totalLigacaoMedido + detalhe.getLigacoesMedido();
						
						totalEconomiasNaoMedido = totalEconomiasNaoMedido + detalhe.getEconomiasNaoMedido();
						totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido + detalhe.getVolumeConsumoFaixaNaoMedido();
						totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido + detalhe.getVolumeFaturadoFaixaNaoMedido();
						totalReceitaNaoMedido = totalReceitaNaoMedido.add(detalhe.getReceitaNaoMedido());
						totalLigacaoNaoMedido = totalLigacaoNaoMedido + detalhe.getLigacoesNaoMedido();
					}
					
					emitirHistograma.setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(colecaoEmitirHistogramaEsgotoEconomiaDetalhe);
					
					emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
					emitirHistograma.setDescricaoFaixa("TOTAL");
					
					emitirHistograma.setTotalEconomiasMedido(totalEconomiasMedido);
					emitirHistograma.setTotalVolumeConsumoMedido(totalVolumeConsumoMedido);
					emitirHistograma.setTotalVolumeFaturadoMedido(totalVolumeFaturadoMedido);
					emitirHistograma.setTotalReceitaMedido(totalReceitaMedido);
					emitirHistograma.setTotalLigacoesMedido(totalLigacaoMedido);
					
					emitirHistograma.setTotalEconomiasNaoMedido(totalEconomiasNaoMedido);
					emitirHistograma.setTotalVolumeConsumoNaoMedido(totalVolumeConsumoNaoMedido);
					emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalVolumeFaturadoNaoMedido);
					emitirHistograma.setTotalReceitaNaoMedido(totalReceitaNaoMedido);
					emitirHistograma.setTotalLigacoesNaoMedido(totalLigacaoNaoMedido);
					
					colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
					
					totalGeralEconomiasMedido = totalGeralEconomiasMedido + emitirHistograma.getTotalEconomiasMedido();
					totalGeralVolumeConsumoMedido = totalGeralVolumeConsumoMedido + emitirHistograma.getTotalVolumeConsumoMedido();
					totalGeralVolumeFaturadoMedido = totalGeralVolumeFaturadoMedido + emitirHistograma.getTotalVolumeFaturadoMedido();
					totalGeralReceitaMedido = totalGeralReceitaMedido.add(emitirHistograma.getTotalReceitaMedido());
					totalGeralLigacaoMedido = totalGeralLigacaoMedido + emitirHistograma.getTotalLigacoesMedido();
					
					totalGeralEconomiasNaoMedido = totalGeralEconomiasNaoMedido + emitirHistograma.getTotalEconomiasNaoMedido();
					totalGeralVolumeConsumoNaoMedido = totalGeralVolumeConsumoNaoMedido + emitirHistograma.getTotalVolumeConsumoNaoMedido();
					totalGeralVolumeFaturadoNaoMedido = totalGeralVolumeFaturadoNaoMedido + emitirHistograma.getTotalVolumeFaturadoNaoMedido();
					totalGeralReceitaNaoMedido = totalGeralReceitaNaoMedido.add(emitirHistograma.getTotalReceitaNaoMedido());						
					totalGeralLigacaoNaoMedido = totalGeralLigacaoNaoMedido + emitirHistograma.getTotalLigacoesNaoMedido();
				}
				
				emitirHistograma = (EmitirHistogramaEsgotoEconomiaHelper) hashMapTotalGeral.get(idQuadra);
				
				emitirHistograma.setDescricaoPercentual(descricaoPercentual);
				emitirHistograma.setDescricaoTarifa(descricaoTarifa);
				emitirHistograma.setDescricaoCategoria(null);
				emitirHistograma.setDescricaoFaixa("TOTAL GERAL");
				emitirHistograma.setOpcaoTotalizacao(descricaoOpcaoTotalizacao);
				
				//Medido(COM HIDROMETRO)
				emitirHistograma.setTotalEconomiasMedido(totalGeralEconomiasMedido);
				emitirHistograma.setTotalVolumeConsumoMedido(totalGeralVolumeConsumoMedido);
				emitirHistograma.setTotalVolumeFaturadoMedido(totalGeralVolumeFaturadoMedido);
				emitirHistograma.setTotalReceitaMedido(totalGeralReceitaMedido);
				emitirHistograma.setTotalLigacoesMedido(totalGeralLigacaoMedido);
				
				//Não Medido(SEM HIDROMETRO)
				emitirHistograma.setTotalEconomiasNaoMedido(totalGeralEconomiasNaoMedido);
				emitirHistograma.setTotalVolumeConsumoNaoMedido(totalGeralVolumeConsumoNaoMedido);
				emitirHistograma.setTotalVolumeFaturadoNaoMedido(totalGeralVolumeFaturadoNaoMedido);
				emitirHistograma.setTotalReceitaNaoMedido(totalGeralReceitaNaoMedido);
				emitirHistograma.setTotalLigacoesNaoMedido(totalGeralLigacaoNaoMedido);
				
				// TOTAL GERAL
				colecaoEmitirHistogramaEsgotoEconomia.add(emitirHistograma);
			}
		}
		
		return colecaoEmitirHistogramaEsgotoEconomia;
		
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia
	 * 
	 * Monta o objeto EmitirHistogramaEsgotoEconomiaDetalheHelper
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * @param ConsumoFaixaCategoria
	 * 
	 * @return EmitirHistogramaEsgotoEconomiaDetalheHelper
	 * @throws ControladorException
	 */
	private EmitirHistogramaEsgotoEconomiaDetalheHelper montarEmitirHistogramaEsgotoEconomiaDetalheHelper(
		FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro,
		ConsumoFaixaCategoria consumoFaixaCategoria,
		int limiteSuperiorFaixaAnterior)
		throws ControladorException {

		EmitirHistogramaEsgotoEconomiaDetalheHelper detalhe = new EmitirHistogramaEsgotoEconomiaDetalheHelper();
		
		BigDecimal consumoMedio = null;
		BigDecimal consumoExcedente = null;

		filtro.setConsumoFaixaCategoria(consumoFaixaCategoria);

		detalhe.setDescricaoFaixa(consumoFaixaCategoria.getNumeroFaixaInicio()+" a "+consumoFaixaCategoria.getNumeroFaixaFim()); 
								
		//Pesquisa com indicador de Hidrometro SIM
		filtro.setMedicao(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		Object[] valoresSomatorio = null;

		boolean temMedido = false;
		boolean temNaoMedido = false;
		
		try {						
			valoresSomatorio = 
				this.repositorioFaturamento.pesquisarEmitirHistogramaEsgotoEconomia(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		if(valoresSomatorio != null){
			
			detalhe.setEconomiasMedido((Integer)valoresSomatorio[2]);
			detalhe.setVolumeConsumoFaixaMedido((Integer)valoresSomatorio[3]);
			detalhe.setVolumeFaturadoFaixaMedido((Integer)valoresSomatorio[4]);
			detalhe.setReceitaMedido((BigDecimal)valoresSomatorio[5]);
			detalhe.setLigacoesMedido((Integer)valoresSomatorio[6]);
			
			consumoMedio = 
				new BigDecimal(detalhe.getVolumeConsumoFaixaMedido()).divide(
					new BigDecimal(detalhe.getEconomiasMedido()),4, BigDecimal.ROUND_HALF_UP);
			
			detalhe.setConsumoMedioMedido(consumoMedio);
			
			if(limiteSuperiorFaixaAnterior > 0){
				consumoExcedente = 
					detalhe.getConsumoMedioMedido().subtract(new BigDecimal(limiteSuperiorFaixaAnterior));
			}
			
			detalhe.setConsumoExcedenteMedido(consumoExcedente);
			temMedido = true;
		}else{
			detalhe.setConsumoMedioMedido(new BigDecimal(0.0));
		}
		
		//Pesquisa com indicador de Hidrometro Nao
		filtro.setMedicao(ConstantesSistema.INDICADOR_USO_DESATIVO);
		
		try {						
			valoresSomatorio = 
				this.repositorioFaturamento.pesquisarEmitirHistogramaEsgotoEconomia(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		if(valoresSomatorio != null){
			
			detalhe.setEconomiasNaoMedido( (Integer)valoresSomatorio[2]);
			detalhe.setVolumeConsumoFaixaNaoMedido((Integer)valoresSomatorio[3]);
			detalhe.setVolumeFaturadoFaixaNaoMedido((Integer)valoresSomatorio[4]);
			detalhe.setReceitaNaoMedido((BigDecimal)valoresSomatorio[5]);
			detalhe.setLigacoesNaoMedido((Integer)valoresSomatorio[6]);
			
			consumoMedio = 
				new BigDecimal(detalhe.getVolumeConsumoFaixaNaoMedido()).divide(
					new BigDecimal(detalhe.getEconomiasNaoMedido()),4, BigDecimal.ROUND_HALF_UP);
			
			detalhe.setConsumoMedioNaoMedido(consumoMedio);

			if(limiteSuperiorFaixaAnterior > 0){
				consumoExcedente = 
					detalhe.getConsumoMedioNaoMedido().subtract(new BigDecimal(limiteSuperiorFaixaAnterior));
			}
			detalhe.setConsumoExcedenteNaoMedido(consumoExcedente);
			temNaoMedido = true;
		}else{
			detalhe.setConsumoMedioNaoMedido(new BigDecimal(0.0));
		}
		
		//Caso tenha algum valor então exite histograma para adcionar na colecao
		detalhe.setExisteHistograma(temMedido || temNaoMedido);

		return detalhe;
	}
	
	/**
	 * [UC0606] Emitir Histograma de Esgoto Por Economia
	 * 
	 * Agrupa as chaves para totalizacao
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return LinkedHashMap
	 * @throws ControladorException
	 */
	private LinkedHashMap montarEmitirHistogramaEsgotoEconomiaAgruparChaves(FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) 
		throws ControladorException {
		
		LinkedHashMap hashMapTotalizacao = new LinkedHashMap();
		Collection<Object[]> colecao = null;
		
		try {
			
			//filtro.setMedicao(ConstantesSistema.INDICADOR_USO_ATIVO);
			
			colecao =
				this.repositorioFaturamento.pesquisarEmitirHistogramaEsgotoEconomiaChavesAgrupadas(filtro);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		if (colecao != null && !colecao.isEmpty()) {
			
			Iterator iterator = colecao.iterator();
			
			while (iterator.hasNext()) {

				String tipoTotalizacao = "0";
				
				int chaveQuadra = 0;
				int chaveSetorComercial = 0;
				int chaveLocalidade = 0;
				int chaveElo = 0;
				int chaveUnidade = 0;
				int chaveGerencia = 0;
				
				if(filtro.getOpcaoTotalizacao() == 2 ||
					filtro.getOpcaoTotalizacao() == 6 ){
					
					chaveGerencia = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveGerencia;
				
				}else if(filtro.getOpcaoTotalizacao() == 3 ||
						filtro.getOpcaoTotalizacao() == 7 ){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveUnidade = (Integer) objeto[0];
					chaveGerencia = (Integer) objeto[1];
					
					tipoTotalizacao = chaveUnidade+";"+chaveGerencia;
				
				}else if(filtro.getOpcaoTotalizacao() == 4 ||
						filtro.getOpcaoTotalizacao() == 8){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveElo = (Integer) objeto[0];
					chaveUnidade = (Integer) objeto[1];
					chaveGerencia = (Integer) objeto[2];
					
					tipoTotalizacao = chaveElo+";"+chaveUnidade+";"+chaveGerencia;
					
				}else if(filtro.getOpcaoTotalizacao() == 5 ||
						filtro.getOpcaoTotalizacao() == 9){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveLocalidade = (Integer) objeto[0];
					chaveElo = (Integer) objeto[1];
					chaveUnidade = (Integer) objeto[2];
					chaveGerencia = (Integer) objeto[3];
					
					tipoTotalizacao = 
						chaveLocalidade+";"+chaveElo+";"+chaveUnidade+";"+chaveGerencia;
					
					
				}else if(filtro.getOpcaoTotalizacao() == 10){

					chaveUnidade = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveUnidade;
					
				}else if(filtro.getOpcaoTotalizacao() == 11){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveElo = (Integer) objeto[0];
					chaveUnidade = (Integer) objeto[1];
					
					tipoTotalizacao = chaveElo+";"+chaveUnidade;
					
				}else if(filtro.getOpcaoTotalizacao() == 12){
					
					Object[] objeto = (Object[]) iterator.next();

					chaveLocalidade = (Integer) objeto[0];
					chaveElo = (Integer) objeto[1];
					chaveUnidade = (Integer) objeto[2];
					
					tipoTotalizacao = chaveLocalidade+";"+chaveElo+";"+chaveUnidade;
					
				}else if(filtro.getOpcaoTotalizacao() == 13){
					
					chaveElo = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveElo;
					
				}else if(filtro.getOpcaoTotalizacao() == 14){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveLocalidade = (Integer) objeto[0];
					chaveElo = (Integer) objeto[1];

					tipoTotalizacao = chaveLocalidade+";"+chaveElo;
					
				}else if(filtro.getOpcaoTotalizacao() == 15){
					
					Object[] objeto = (Object[]) iterator.next();
					
					chaveSetorComercial = (Integer) objeto[0];
					chaveLocalidade = (Integer) objeto[1];
					chaveElo = (Integer) objeto[2];
					
					tipoTotalizacao = 
						chaveSetorComercial+";"+chaveLocalidade+";"+chaveElo;
					
				}else if(filtro.getOpcaoTotalizacao() == 16){
					
					chaveLocalidade = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveLocalidade;

				}else if(filtro.getOpcaoTotalizacao() == 17){
					Object[] objeto = (Object[]) iterator.next();
					
					chaveSetorComercial = (Integer) objeto[0];
					chaveLocalidade = (Integer) objeto[1];
					
					tipoTotalizacao = 
						chaveSetorComercial+";"+chaveLocalidade;

				}else if(filtro.getOpcaoTotalizacao() == 18){
					Object[] objeto = (Object[]) iterator.next();

					chaveQuadra = (Integer) objeto[0];
					chaveSetorComercial = (Integer) objeto[1];
					chaveLocalidade = (Integer) objeto[2];
					
					tipoTotalizacao = 
						chaveQuadra+";"+chaveSetorComercial+";"+chaveLocalidade;
					
				}else if(filtro.getOpcaoTotalizacao() == 19){
					
					chaveSetorComercial = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveSetorComercial;
					
				}else if(filtro.getOpcaoTotalizacao() == 20){

					Object[] objeto = (Object[]) iterator.next();

					chaveQuadra = (Integer) objeto[0];
					chaveSetorComercial = (Integer) objeto[1];
					
					tipoTotalizacao = 
						chaveQuadra+";"+chaveSetorComercial;
					
				
				}else if(filtro.getOpcaoTotalizacao() == 21){
					
					chaveQuadra = (Integer) iterator.next();
					tipoTotalizacao = ""+chaveQuadra;
					
				}

				
				EmitirHistogramaEsgotoEconomiaHelper emitirHistograma = new EmitirHistogramaEsgotoEconomiaHelper();
				
				hashMapTotalizacao.put(tipoTotalizacao,emitirHistograma);
			}
		}
		
		return hashMapTotalizacao;
	}

	/**
	 * [UC0099] Emitir Relação Sintética de Faturas
	 * 
	 * @author Rafael Pinto
	 * @date 19/11/2007
	 * 
	 * @param colecaoFatura
	 * @throws ControladorException
	 */
	public void emitirRelacaoSinteticaFaturas(Collection<Fatura> colecaoFatura,Integer anoMesFaturamento)
			throws ControladorException {

		Collection<FaturaClienteResponsavelHelper> colecaoFaturaHelper = new ArrayList();

		if (colecaoFatura != null) {

			for (Fatura fatura : colecaoFatura) {

				FaturaClienteResponsavelHelper faturaHelper = new FaturaClienteResponsavelHelper();

				Cliente cliente = 
					this.getControladorCliente().pesquisarCliente(fatura.getCliente().getId());

				int anoMes = fatura.getAnoMesReferencia();

				faturaHelper.setCodigoCliente(cliente.getId().toString());
				faturaHelper.setNomeCliente(cliente.getNome());
				faturaHelper.setMesAno(Util.formatarAnoMesParaMesAno(anoMes));

				faturaHelper.setValorTotalAPagar(Util.formatarMoedaReal(fatura.getDebito()));

				colecaoFaturaHelper.add(faturaHelper);
			}

			RelatorioRelacaoSinteticaFaturas relatorio = new RelatorioRelacaoSinteticaFaturas(
					Usuario.USUARIO_BATCH);

			relatorio.addParametro("colecaoFaturas", colecaoFaturaHelper);
			relatorio.addParametro("tipoFormatoRelatorio",
					TarefaRelatorio.TIPO_PDF);

			byte[] relatorioGerado = (byte[]) relatorio.executar();

			try {


				String mesReferencia = anoMesFaturamento
						.toString().substring(4, 6);
				String anoReferencia = anoMesFaturamento
						.toString().substring(0, 4);

				String nomeRelatorio = "RELATORIO_RELACAO_SINTETICA_FATURA_"
						+ mesReferencia + "_" + anoReferencia + ".PDF";

				File leitura = new File(nomeRelatorio);
				FileOutputStream out = new FileOutputStream(leitura
						.getAbsolutePath());
				out.write(relatorioGerado);
				out.flush();
				out.close();

			} catch (IOException e) {
				throw new ControladorException("erro.sistema", e);
			}

		}

	}
	
	
	
}