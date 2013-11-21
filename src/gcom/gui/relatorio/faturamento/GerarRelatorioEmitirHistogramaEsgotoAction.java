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
import gcom.faturamento.ConsumoFaixaLigacao;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.EmissaoHistogramaEsgotoDadosInformarActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioHistogramaEsgotoLigacao;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioEmitirHistogramaEsgotoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirHistogramaEsgoto");
		
		// Form
		EmissaoHistogramaEsgotoDadosInformarActionForm form = 
			(EmissaoHistogramaEsgotoDadosInformarActionForm) actionForm;
		
		FiltrarEmitirHistogramaEsgotoHelper filtro = new FiltrarEmitirHistogramaEsgotoHelper();
		
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
		boolean informouLocalidade = false;
		if (form.getLocalidade() != null && 
			!form.getLocalidade().equals("")) {
			
			Localidade localidade = new Localidade();
			localidade.setId(new Integer(form.getLocalidade()));
			
			filtro.setLocalidade(localidade);
			
			informouLocalidade = true;
			
		}
		
		// Setor Comercial
		boolean informouSetor = false;
		if (form.getSetorComercial() != null && 
			!form.getSetorComercial().equals("")) {
			
			if(informouLocalidade){
				
				informouSetor = true;
				
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
				
			}else{
				filtro.setCodigoSetorComercial(new Integer(form.getSetorComercial()));
			}
			
		}
		
		// Quadra
		if (form.getQuadra() != null && 
			!form.getQuadra().equals("")) {
			
			if(informouSetor){
				
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
				
			}else{
				filtro.setNumeroQuadra(new Integer(form.getQuadra()));
			}
			
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
			
			String[] array = form.getCategoria();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoCategoria(colecao);
			
}
		
		Integer indicadorTarifa = Integer.parseInt( form.getIndicadorTarifaCategoria() );
		filtro.setIndicadorTarifaCategoria( indicadorTarifa.intValue() );
		
		// Sub categoria
		if (form.getSubcategoria() != null && 
			form.getSubcategoria().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getSubcategoria();
			for (int i = 0; i < array.length; i++) {
				
				Integer key = new Integer(array[i]);
				
				if (key.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(key);
				}
			}

			filtro.setColecaoSubcategoria(colecao);
		}
		
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
		
		// Indicador Medicao
		if (form.getMedicao() != null && 
			!form.getMedicao().equals("")) {
			
			filtro.setMedicao(new Short(form.getMedicao()));
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
		
		if(filtro.getConsumo() != null){
			
			if(filtro.getConsumo().shortValue() == ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
				//Faixa Consumo Medido
				if(form.getColecaoConsumoFaixaLigacaoMedido() != null && 
					!form.getColecaoConsumoFaixaLigacaoMedido().isEmpty()){
					
					this.validarColecaoFaixaConsumoLigacao(form.getColecaoConsumoFaixaLigacaoMedido());
					filtro.setColecaoConsumoFaixaLigacaoMedido(form.getColecaoConsumoFaixaLigacaoMedido());
				}
			}
			
			if(filtro.getConsumo().shortValue() == ConstantesSistema.INDICADOR_USO_DESATIVO.shortValue()){
				//Faixa Consumo Não Medido
				if(form.getColecaoConsumoFaixaLigacaoNaoMedido() != null && 
					!form.getColecaoConsumoFaixaLigacaoNaoMedido().isEmpty()){
					
					this.validarColecaoFaixaConsumoLigacao(form.getColecaoConsumoFaixaLigacaoNaoMedido());
					filtro.setColecaoConsumoFaixaLigacaoNaoMedido(form.getColecaoConsumoFaixaLigacaoNaoMedido());
				}
				
			}
			
		}else{

			//Faixa Consumo Medido
			if(form.getColecaoConsumoFaixaLigacaoMedido() != null && 
				!form.getColecaoConsumoFaixaLigacaoMedido().isEmpty()){
				
				this.validarColecaoFaixaConsumoLigacao(form.getColecaoConsumoFaixaLigacaoMedido());
				filtro.setColecaoConsumoFaixaLigacaoMedido(form.getColecaoConsumoFaixaLigacaoMedido());
			}
		
			//Faixa Consumo Não Medido
			if(form.getColecaoConsumoFaixaLigacaoNaoMedido() != null && 
				!form.getColecaoConsumoFaixaLigacaoNaoMedido().isEmpty()){
				
				this.validarColecaoFaixaConsumoLigacao(form.getColecaoConsumoFaixaLigacaoNaoMedido());
				filtro.setColecaoConsumoFaixaLigacaoNaoMedido(form.getColecaoConsumoFaixaLigacaoNaoMedido());
			}
	
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioHistogramaEsgotoLigacao relatorio = 
			new RelatorioHistogramaEsgotoLigacao(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarEmitirHistogramaEsgotoHelper", filtro);
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
	 * @date 05/11/2007
	 *
	 * @param EmissaoHistogramaEsgotoDadosInformarActionForm
	 */
	private void validarColecaoFaixaConsumoLigacao(Collection colecaoFaixaConsumoLigacao){
		
		if(colecaoFaixaConsumoLigacao != null && !colecaoFaixaConsumoLigacao.isEmpty()){
			
			Iterator itera = colecaoFaixaConsumoLigacao.iterator();
			int faixaFimAnterior = 0;
			while (itera.hasNext()) {
				ConsumoFaixaLigacao consumoFaixaLigacao = (ConsumoFaixaLigacao) itera.next();
				int faixaInicio = consumoFaixaLigacao.getNumeroFaixaInicio();
				
				/*if(faixaFimAnterior == 0 && faixaInicio != 0){
					throw new ActionServletException("atencao.consumo_faixa_primeira_zero");
				}*/
				
				if(faixaInicio != faixaFimAnterior){
					String[] msg = new String[3];
					
					msg[0] = ""+faixaInicio;
					msg[1] = ""+consumoFaixaLigacao.getNumeroFaixaFim();
					msg[2] = ""+faixaFimAnterior;
					
					throw new ActionServletException("atencao.consumo_faixa_intervalo_invalido",null,msg);
				}
				faixaFimAnterior = consumoFaixaLigacao.getNumeroFaixaFim()+1;				
			}
		}
	}
}