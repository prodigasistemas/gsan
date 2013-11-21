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
package gcom.gui.micromedicao.hidrometro;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltrarHidrometroHelper;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroSituacao;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 5 de Setembro de 2005
 */
public class FiltrarHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		String tela = (String) sessao.getAttribute("tela");
		
		if (tela != null && !tela.equals("")) {
			if (tela.equals("movimentarHidrometro")) {
				retorno = actionMapping.findForward("movimentarHidrometro");
			}
		} else {
			retorno = actionMapping.findForward("retornarFiltroHidrometro");
		}

		// Recupera os parâmetros do form
		String numeroHidrometro = hidrometroActionForm.getNumeroHidrometro();
		String dataAquisicao = hidrometroActionForm.getDataAquisicao();
		String anoFabricacao = hidrometroActionForm.getAnoFabricacao();
		String indicadorMacromedidor = hidrometroActionForm.getIndicadorMacromedidor();
		String idHidrometroClasseMetrologica = hidrometroActionForm.getIdHidrometroClasseMetrologica();
		String idHidrometroMarca = hidrometroActionForm.getIdHidrometroMarca();
		String idHidrometroDiametro = hidrometroActionForm.getIdHidrometroDiametro();
		String idHidrometroCapacidade = hidrometroActionForm.getIdHidrometroCapacidade();
		String idHidrometroTipo = hidrometroActionForm.getIdHidrometroTipo();
		
		String idHidrometroSituacao = hidrometroActionForm.getIdHidrometroSituacao();
		String idLocalArmazenagem = hidrometroActionForm.getIdLocalArmazenagem();
		String fixo = hidrometroActionForm.getFixo();
		String faixaInicial = hidrometroActionForm.getFaixaInicial();
		String faixaFinal = hidrometroActionForm.getFaixaFinal();
		
		String idHidrometroRelojoaria = hidrometroActionForm.getIdHidrometroRelojoaria();
		String idLocalidade = hidrometroActionForm.getIdLocalidade();
		
		String idSetorComercial = hidrometroActionForm.getIdSetorComercial();
		String codigoSetorComercial = hidrometroActionForm.getCodigoSetorComercial();
		
		String idQuadra = hidrometroActionForm.getIdQuadra();
		String numeroQuadra = hidrometroActionForm.getNumeroQuadra();
		
		String vazaoTransicao = hidrometroActionForm.getVazaoTransicao();
		String vazaoNominal = hidrometroActionForm.getVazaoNominal();
		String vazaoMinima = hidrometroActionForm.getVazaoMinima();
		String notaFiscal = hidrometroActionForm.getNotaFiscal();
		String tempoGarantiaAnos = hidrometroActionForm.getTempoGarantiaAnos();
		
		FiltroHidrometro filtroHidrometro = 
			new FiltroHidrometro(FiltroHidrometro.NUMERO_HIDROMETRO);
		
		boolean peloMenosUmParametroInformado = false;
		
		if (idHidrometroSituacao != null && !idHidrometroSituacao.equals("-1") ) { 
			FiltroHidrometroSituacao filtroHidrometroSituacao = new FiltroHidrometroSituacao();
			filtroHidrometroSituacao.adicionarParametro( new ParametroSimples( FiltroHidrometroSituacao.ID, 
					hidrometroActionForm.getIdHidrometroSituacao() ) );
			Collection colecaoHidrometroSituacao =  this.getFachada().pesquisar(filtroHidrometroSituacao, 
					HidrometroSituacao.class.getName() );
			Object hidrometroSituacao = Util.retonarObjetoDeColecao(colecaoHidrometroSituacao);
			
			HidrometroSituacao situacao = (HidrometroSituacao) hidrometroSituacao;
			if (situacao.getDescricao().equals("INSTALADO") && idLocalidade.equals("")) {
				throw new ActionServletException("atencao.required", null, "Localidade");
			}
		}

		// Caso o fixo, a faixa inicial e faixa final seja diferente de null
		// então ignora os outros parametros e faz a pesquisa do filtro por
		// esses 3 parâmetros
		if (fixo != null && !fixo.equalsIgnoreCase("")) {
			
			if (faixaInicial != null && !faixaInicial.equalsIgnoreCase("")) {
				sessao.setAttribute("faixaInicial", faixaInicial);
			}
			
			if (faixaFinal != null && !faixaFinal.equalsIgnoreCase("")) {
				sessao.setAttribute("faixaFinal", faixaFinal);
			}
			
			sessao.setAttribute("fixo", fixo);
			sessao.removeAttribute("instalado");
			
			peloMenosUmParametroInformado = true;
			
		} else if( hidrometroActionForm.getIdLocalidade() != null &&
				!hidrometroActionForm.getIdLocalidade().equals("") ){
			
			
			sessao.setAttribute("instalado", true);
			FiltrarHidrometroHelper helper = new FiltrarHidrometroHelper();
			
			// Insere os parâmetros informados no filtro
			if (numeroHidrometro != null && 
				!numeroHidrometro.trim().equalsIgnoreCase("")) {
				
				peloMenosUmParametroInformado = true;
				helper.setNumeroHidrometro(numeroHidrometro);
				
			}
			
			Date dataAquisicaoDate = Util.converteStringParaDate(dataAquisicao);
			Calendar dataAtual = new GregorianCalendar();
			
			if (dataAquisicao != null && !dataAquisicao.trim().equalsIgnoreCase("")) {
				
				// caso a data de aquisição seja menor que a data atual
				if (dataAquisicaoDate.after(new Date())) {
					throw new ActionServletException("atencao.data.aquisicao.nao.superior.data.corrente");
				}
				
				peloMenosUmParametroInformado = true;
				helper.setDataAquisicao(dataAquisicaoDate);
			}

//			if (anoFabricacao != null && !anoFabricacao.trim().equalsIgnoreCase("")) {
			
			if (anoFabricacao != null && !anoFabricacao.equals("")) {
			
				peloMenosUmParametroInformado = true;
				
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.ANO_FABRICACAO, anoFabricacao));

				helper.setAnoFabricacao(anoFabricacao);
				
				int anoAtual = dataAtual.get(Calendar.YEAR);
				Integer anoFabricacaoInteger = new Integer(anoFabricacao);
				
				// caso o ano de fabricação seja maior que o atual
				if (anoFabricacaoInteger > anoAtual) {
					throw new ActionServletException("atencao.ano.fabricacao.nao.superior.data.corrente");
				}
				if(dataAquisicaoDate != null){
					Integer anoDataAquisicao = Util.getAno(dataAquisicaoDate);
					// caso a data de aquisição seja menor que o ano fabricação
					if (anoDataAquisicao < anoFabricacaoInteger) {
						throw new ActionServletException("atencao.ano.fabricacao.menor.ano.aquisicao");
	
					}
				}
			}

			if (indicadorMacromedidor != null && 
				!indicadorMacromedidor.trim().equalsIgnoreCase("") && 
				!indicadorMacromedidor.trim().equalsIgnoreCase("-1")) {
				
				helper.setIndicadorMacromedidor(indicadorMacromedidor);
			}

			if (idHidrometroClasseMetrologica != null && 
				Integer.parseInt(idHidrometroClasseMetrologica) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroClasseMetrologica(idHidrometroClasseMetrologica);
			}

			if (idHidrometroMarca != null && 
				Integer.parseInt(idHidrometroMarca) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroMarca(idHidrometroMarca);
			}
			
			if (idHidrometroDiametro != null && 
				Integer.parseInt(idHidrometroDiametro) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroDiametro(idHidrometroDiametro);
			}

			if (idHidrometroCapacidade != null && 
				Integer.parseInt(idHidrometroCapacidade) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroCapacidade(idHidrometroCapacidade);
			}
			
			if (idHidrometroTipo != null && 
				Integer.parseInt(idHidrometroTipo) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
							
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroTipo(idHidrometroTipo);
			}
			
			if (idHidrometroRelojoaria != null && 
				Integer.parseInt(idHidrometroRelojoaria) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroRelojoaria(idHidrometroRelojoaria);
			}

			if (idHidrometroSituacao != null && 
				Integer.parseInt(idHidrometroSituacao) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroSituacao(idHidrometroSituacao);
			}

			if (idLocalArmazenagem != null && !idLocalArmazenagem.equals("")) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdLocalArmazenagem(idLocalArmazenagem);
			}
			
			if (idLocalidade != null && !idLocalidade.equals("")) {
				
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.ID, 
						hidrometroActionForm.getIdLocalidade()));

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna localidade
				Collection colecaoPesquisa = 
					this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());
				
				if(colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
					
					Localidade localidade =(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					idLocalidade = localidade.getId().toString();
					peloMenosUmParametroInformado = true;
					helper.setIdLocalidade(idLocalidade);
					helper.setNomeLocalidade(localidade.getDescricao());
					
				}else{
					throw new ActionServletException("atencao.localidade.inexistente");
				}
			}
			
			if (codigoSetorComercial != null && !codigoSetorComercial.equals("")) {
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				
				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, 
						hidrometroActionForm.getIdLocalidade()));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						hidrometroActionForm.getCodigoSetorComercial()));

				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				Collection colecaoPesquisa = 
					this.getFachada().pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());
				
				
				if(colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
					
					SetorComercial setorComercial =(SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					idSetorComercial = setorComercial.getId().toString();
					
					peloMenosUmParametroInformado = true;
					helper.setIdSetorComercial(idSetorComercial);
					helper.setCodigoSetorComercial(""+setorComercial.getCodigo());
					
				}else{
					throw new ActionServletException("atencao.setor_comercial.inexistente");
				}
			}
			
			if ( vazaoTransicao != null && !vazaoTransicao.equals("") ) {
				helper.setVazaoTransicao( vazaoTransicao );
			}
			
			if ( vazaoNominal != null && !vazaoNominal.equals("") ) {
				helper.setVazaoNominal( vazaoNominal );
			}
			
			if ( vazaoMinima != null && !vazaoMinima.equals("") ) {
				helper.setVazaoMinima( vazaoMinima );
			}
			
			if ( notaFiscal != null && !notaFiscal.equals("") ) {
				helper.setNotaFiscal( notaFiscal );
			}

			if ( tempoGarantiaAnos != null && !tempoGarantiaAnos.equals("") ) {
				helper.setTempoGarantiaAnos( tempoGarantiaAnos );
			}
			
			if (numeroQuadra != null && !numeroQuadra.equals("")) {
				
				FiltroQuadra filtroQuadra = new FiltroQuadra();

				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, 
						hidrometroActionForm.getIdSetorComercial()));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, 
						hidrometroActionForm.getNumeroQuadra()));

				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				Collection colecaoPesquisa = 
					this.getFachada().pesquisar(filtroQuadra,
						Quadra.class.getName());
					
				if(colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
						
					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					idQuadra = quadra.getId().toString();
					
					peloMenosUmParametroInformado = true;
					helper.setIdQuadra(idQuadra);
					helper.setNumeroQuadra(""+quadra.getNumeroQuadra());
					
				}else{
					throw new ActionServletException("atencao.quadra.inexistente");
				}
			}

			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}

			sessao.setAttribute("helper",helper);
			sessao.setAttribute("voltarFiltrar","1");
			
			sessao.removeAttribute("fixo");
			sessao.removeAttribute("faixaFinal");
			sessao.removeAttribute("faixaInicial");
			sessao.removeAttribute("filtroHidrometro");
			
		}else{
			// Insere os parâmetros informados no filtro
			if (numeroHidrometro != null && 
				!numeroHidrometro.trim().equalsIgnoreCase("")) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ComparacaoTexto(
						FiltroHidrometro.NUMERO_HIDROMETRO, 
						numeroHidrometro));
			}
			
			Date dataAquisicaoDate = Util.converteStringParaDate(dataAquisicao);
			Calendar dataAtual = new GregorianCalendar();
			
			if (dataAquisicao != null && 
				!dataAquisicao.trim().equalsIgnoreCase("")) {
				
				// caso a data de aquisição seja menor que a data atual
				if (dataAquisicaoDate.after(new Date())) {
					throw new ActionServletException("atencao.data.aquisicao.nao.superior.data.corrente");
				}
				
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.DATA_AQUISICAO, 
						dataAquisicaoDate));
			}

			if (anoFabricacao != null && 
				!anoFabricacao.trim().equalsIgnoreCase("")) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.ANO_FABRICACAO, 
						anoFabricacao));
				
				int anoAtual = dataAtual.get(Calendar.YEAR);
				Integer anoFabricacaoInteger = new Integer(anoFabricacao);
				
				// caso o ano de fabricação seja maior que o atual
				if (anoFabricacaoInteger > anoAtual) {
					throw new ActionServletException("atencao.ano.fabricacao.nao.superior.data.corrente");
				}
				if(dataAquisicaoDate != null){
					
					Integer anoDataAquisicao = Util.getAno(dataAquisicaoDate);
					// caso a data de aquisição seja menor que o ano fabricação
					if (anoDataAquisicao < anoFabricacaoInteger) {
						throw new ActionServletException("atencao.ano.fabricacao.menor.ano.aquisicao");
	
					}
				}
			}

			if (indicadorMacromedidor != null && 
				!indicadorMacromedidor.trim().equalsIgnoreCase("") && 
				!indicadorMacromedidor.trim().equalsIgnoreCase("-1")) {
				
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.INDICADOR_MACROMEDIDOR,
						indicadorMacromedidor));
			}

			if (idHidrometroClasseMetrologica != null && 
				Integer.parseInt(idHidrometroClasseMetrologica) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_CLASSE_METROLOGICA_ID,
						idHidrometroClasseMetrologica));
			}

			if (idHidrometroMarca != null && 
				Integer.parseInt(idHidrometroMarca) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_MARCA_ID,
						idHidrometroMarca));
			}

			if (idHidrometroDiametro != null && 
				Integer.parseInt(idHidrometroDiametro) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_DIAMETRO_ID,
						idHidrometroDiametro));
			}

			if (idHidrometroCapacidade != null && 
				Integer.parseInt(idHidrometroCapacidade) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_CAPACIDADE_ID,
						idHidrometroCapacidade));
			}
			if (idHidrometroTipo != null && 
				Integer.parseInt(idHidrometroTipo) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_TIPO_ID, 
						idHidrometroTipo));
			}
			
			if (idHidrometroRelojoaria != null && 
				Integer.parseInt(idHidrometroRelojoaria) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_RELOJOARIA_ID, 
						idHidrometroRelojoaria));
			}


			if (idHidrometroSituacao != null && 
				Integer.parseInt(idHidrometroSituacao) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_SITUACAO_ID,
						idHidrometroSituacao));
			}

			if (idLocalArmazenagem != null && !idLocalArmazenagem.equals("")) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM_ID,
						idLocalArmazenagem));
			}
			
			if ( vazaoTransicao != null && !vazaoTransicao.equals("") ) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro( 
					new ParametroSimples(
						FiltroHidrometro.VAZAO_TRANSICAO, 
						vazaoTransicao.replace( "," , "." ) ) );
			}
			
			if ( vazaoNominal != null && !vazaoNominal.equals("") ) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro( 
					new ParametroSimples(
						FiltroHidrometro.VAZAO_NOMINAL, 
						vazaoNominal.replace( "," , "." ) ) );
			}

			if ( vazaoMinima != null && !vazaoMinima.equals("") ) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro( 
					new ParametroSimples(
						FiltroHidrometro.VAZAO_MINIMA, 
						vazaoMinima.replace( "," , "." ) ) ) ;
			}
			
			if ( notaFiscal != null && !notaFiscal.equals("") ) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro( 
					new ParametroSimples(
						FiltroHidrometro.NOTA_FISCAL,  
						notaFiscal));
			}
			
			if ( tempoGarantiaAnos != null && !tempoGarantiaAnos.equals("") ) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro( 
					new ParametroSimples(
						FiltroHidrometro.TEMPO_GARANTIA_ANOS, 
						tempoGarantiaAnos));
			}
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
					throw new ActionServletException(
						"atencao.filtro.nenhum_parametro_informado");
			}

			if (retorno.getName().equalsIgnoreCase("movimentarHidrometro")) {
				filtroHidrometro.setConsultaSemLimites(true);
			}

			// Manda o filtro pela sessão para o ExibirManterHidrometroAction
			sessao.setAttribute("filtroHidrometro",filtroHidrometro);
			sessao.setAttribute("voltarFiltrar","1");
			
			sessao.removeAttribute("fixo");
			sessao.removeAttribute("faixaFinal");
			sessao.removeAttribute("faixaInicial");
		}

		sessao.setAttribute("filtrar_manter", "filtrar_manter");
		
		return retorno;
	}
}
