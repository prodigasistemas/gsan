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
package gcom.gui.relatorio.faturamento;

import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.ConsumoFaixaCategoria;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoEconomiaHelper;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.EmissaoHistogramaEsgotoEconomiaDadosInformarActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioHistogramaEsgotoEconomia;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe montar o filtro para pesquisa de 
 * emitir histograma de esgoto por economia
 *
 * @author Rafael Pinto
 * 
 * @date 07/11/2007
 */

public class GerarRelatorioEmitirHistogramaEsgotoEconomiaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirHistogramaEsgoto");
		
		// Form
		EmissaoHistogramaEsgotoEconomiaDadosInformarActionForm form = 
			(EmissaoHistogramaEsgotoEconomiaDadosInformarActionForm) actionForm;
		
		FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro = new FiltrarEmitirHistogramaEsgotoEconomiaHelper();
		
		// Opção de Totalização
		filtro.setOpcaoTotalizacao(Integer.parseInt(form.getOpcaoTotalizacao()));
		
		// Mês/Ano do Faturamento
		Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoFaturamento());
		
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		if(sistemaParametro.getAnoMesFaturamento().intValue() < anoMes.intValue()){
			throw new ActionServletException("atencao.mes_ano.faturamento.inferior", 
				null,""+sistemaParametro.getAnoMesFaturamento());
		}
		
		filtro.setMesAnoFaturamento(anoMes);
		
		// Gerência Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			GerenciaRegional gerencia = new GerenciaRegional();
			gerencia.setId(new Integer(form.getGerenciaRegional()));
			
			filtro.setGerenciaRegional(gerencia);
			
		}

		// Unidade de Negocio
		if (form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			UnidadeNegocio unidade = new UnidadeNegocio();
			unidade.setId(new Integer(form.getUnidadeNegocio()));
			
			filtro.setUnidadeNegocio(unidade);
			
		}
		
		// Elo Pólo
		if (form.getEloPolo() != null && 
			!form.getEloPolo().equals("")) {
			
			Localidade eloPolo = new Localidade();
			eloPolo.setId(new Integer(form.getEloPolo()));
			
			filtro.setEloPolo(eloPolo);
			
		}
		
		// Localidade
		if (form.getLocalidade() != null && 
			!form.getLocalidade().equals("")) {
			
			Localidade localidade = new Localidade();
			localidade.setId(new Integer(form.getLocalidade()));
			
			filtro.setLocalidade(localidade);
			
		}
		
		// Setor Comercial
		if (form.getSetorComercial() != null && 
			!form.getSetorComercial().equals("")) {
			
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
					form.getSetorComercial()));
			
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.LOCALIDADE, 
					form.getLocalidade()));
			
			// Recupera Setor Comercial
			Collection colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
			SetorComercial setorComercial = 
				(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			filtro.setSetorComercial(setorComercial);
		}
		
		// Quadra
		if (form.getQuadra() != null && 
			!form.getQuadra().equals("")) {
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(
				new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, 
				form.getQuadra()));
			
			filtroQuadra.adicionarParametro(
				new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, 
				filtro.getSetorComercial().getId()));		
			
			// Recupera Quadra
			Collection colecaoQuadra = 
				this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
		
			Quadra quadra = 
				(Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
			
			filtro.setQuadra(quadra);
				
		}		
		
		// Tipo Categoria
		if (form.getTipoCategoria() != null && 
			!form.getTipoCategoria().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			CategoriaTipo tipoCategoria = new CategoriaTipo();
			tipoCategoria.setId(new Integer(form.getTipoCategoria()));
			
			filtro.setTipoCategoria(tipoCategoria);
			
		}	
		
		// Categoria
		if (form.getCategoria() != null && 
			form.getCategoria().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			LinkedHashMap<String,Collection<ConsumoFaixaCategoria>> linkedHashMapConsumoFaixaCategoria = 
				new LinkedHashMap<String,Collection<ConsumoFaixaCategoria>>();
			
			String[] array = form.getCategoria();
			for (int i = 0; i < array.length; i++) {
				
				Integer key = new Integer(array[i]);
				
				if (key.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					
					colecao.add(key);
					
					Collection<ConsumoFaixaCategoria> colecaoFaixa = (Collection<ConsumoFaixaCategoria>)
						form.getLinkedHashMapConsumoFaixaCategoria().get(""+key);
					
					linkedHashMapConsumoFaixaCategoria.put(key+"",colecaoFaixa);
				}
			}
			
			form.setLinkedHashMapConsumoFaixaCategoria(linkedHashMapConsumoFaixaCategoria);
			filtro.setColecaoCategoria(colecao);
			
		}
		
		//Faixa de Consumo por Categoria
		this.validarColecaoFaixaConsumoCategoria(form);
		filtro.setLinkedHashMapConsumoFaixaCategoria(form.getLinkedHashMapConsumoFaixaCategoria());
		
		// Tarifa
		if (form.getTarifa() != null && 
			form.getTarifa().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getTarifa();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoTarifa(colecao);
			
		}
		
		// Perfil do Imovel
		if (form.getPerfilImovel() != null && 
			form.getPerfilImovel().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getPerfilImovel();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoPerfilImovel(colecao);
			
		}	
		
		// Esfera de Poder
		if (form.getEsferaPoder() != null && 
			form.getEsferaPoder().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getEsferaPoder();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoEsferaPoder(colecao);
			
		}	
		
		// Situacao da Ligacao de Esgoto
		if (form.getSituacaoLigacaoEsgoto() != null && 
			form.getSituacaoLigacaoEsgoto().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getSituacaoLigacaoEsgoto();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoSituacaoLigacaoEsgoto(colecao);
			
		}		
		
		
		// Perfil da  Ligacao de Esgoto
		if (form.getPerfilLigacaoEsgoto() != null && 
			form.getPerfilLigacaoEsgoto().length > 0) {
			
			Collection<BigDecimal> colecao = new ArrayList();
			
			String[] array = form.getPerfilLigacaoEsgoto();
			for (int i = 0; i < array.length; i++) {
				if (new BigDecimal(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new BigDecimal(array[i]));
				}
			}

			filtro.setColecaoPercentualLigacaoEsgoto(colecao);
			
		}		
		
		// Indicador Consumo
		if (form.getConsumo() != null && 
			!form.getConsumo().equals("")) {
			
			filtro.setConsumo(new Short(form.getConsumo()));
		}

		// Indicador Poco
		if (form.getPoco() != null && 
			!form.getPoco().equals("")) {
			
			filtro.setPoco(new Short(form.getPoco()));
		}
		
		// Indicador Volume
		if (form.getVolumoFixoEsgoto() != null && 
			!form.getVolumoFixoEsgoto().equals("")) {
			
			filtro.setVolumoFixoEsgoto(new Short(form.getVolumoFixoEsgoto()));
		}		
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioHistogramaEsgotoEconomia relatorio = 
			new RelatorioHistogramaEsgotoEconomia(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarEmitirHistogramaEsgotoEconomiaHelper", filtro);
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
	/**
	 * Valida se a colecao possui faixa inicial(faixaInicio = 0)
	 * Verifica se as faixa estão sequenciadas
	 * Obs:A colecao já vem ordenada pela faixaInicio
	 * 
	 * @author Rafael Pinto
	 * @date 21/06/2007
	 *
	 * @param EmissaoHistogramaEsgotoEconomiaDadosInformarActionForm
	 */
	private void validarColecaoFaixaConsumoCategoria(EmissaoHistogramaEsgotoEconomiaDadosInformarActionForm form){
		
		LinkedHashMap<String,Collection<ConsumoFaixaCategoria>> linkedHashMapConsumoFaixaCategoria = 
			form.getLinkedHashMapConsumoFaixaCategoria();
		
		Iterator iteraChaves = linkedHashMapConsumoFaixaCategoria.keySet().iterator();
		while (iteraChaves.hasNext()) {
			
			String chave = (String) iteraChaves.next();
			
			Collection<ConsumoFaixaCategoria> colecaoFaixaConsumoCategoria = 
				(Collection<ConsumoFaixaCategoria>) linkedHashMapConsumoFaixaCategoria.get(chave);
		
			if(colecaoFaixaConsumoCategoria != null && !colecaoFaixaConsumoCategoria.isEmpty()){

				int faixaFimAnterior = 0;
				
				Iterator itera = colecaoFaixaConsumoCategoria.iterator();
				while (itera.hasNext()) {
					ConsumoFaixaCategoria consumoFaixaCategoria = (ConsumoFaixaCategoria) itera.next();
					int faixaInicio = consumoFaixaCategoria.getNumeroFaixaInicio();
					
					/*if(faixaFimAnterior == 0 && faixaInicio != 0){
						throw new ActionServletException("atencao.consumo_faixa_primeira_zero");
					}*/
					
					if(faixaInicio != faixaFimAnterior){
						String[] msg = new String[3];
						
						msg[0] = ""+faixaInicio;
						msg[1] = ""+consumoFaixaCategoria.getNumeroFaixaFim();
						msg[2] = ""+faixaFimAnterior;
						
						throw new ActionServletException("atencao.consumo_faixa_intervalo_invalido",null,msg);
					}
					faixaFimAnterior = consumoFaixaCategoria.getNumeroFaixaFim()+1;				
				}
			}

		}
		
		

		
		
	}	
}