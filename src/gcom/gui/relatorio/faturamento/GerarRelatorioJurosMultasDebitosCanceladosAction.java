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

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.FiltrarRelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioJurosMultasDebitosCancelados;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesInterfaceGSAN;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *[UC0958] Gerar Relatório Juras, Multas e Débitos Cancelados
 *
 * @author Marlon Patrick
 * @since 22/10/2009
 */
public class GerarRelatorioJurosMultasDebitosCanceladosAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		GerarRelatorioJurosMultasDebitosCanceladosActionForm form = 
			(GerarRelatorioJurosMultasDebitosCanceladosActionForm) actionForm;
		
		validarPeriodoCancelamento(form);
		
		FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro = criarFiltro(form);
		
		TarefaRelatorio relatorio = new RelatorioJurosMultasDebitosCancelados(getUsuarioLogado(httpServletRequest));

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
				
		configurarParametrosRelatorio(relatorio, form,filtro,tipoRelatorio);

		return 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);
	}


	/**
	 * Este método cria e configura o filtro necessário a geração do relatório.
	 * 
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private FiltrarRelatorioJurosMultasDebitosCanceladosHelper criarFiltro(
			GerarRelatorioJurosMultasDebitosCanceladosActionForm form) {
		
		FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro = 
			new FiltrarRelatorioJurosMultasDebitosCanceladosHelper();

		if (Util.verificarNaoVazio(form.getMesAnoFaturamento())) {
			filtro.setMesAnoFaturamento(new Integer(
					Util.formatarMesAnoParaAnoMesSemBarra(form.getMesAnoFaturamento())));
		}

		if (Util.verificarNaoVazio(form.getDataCancelamentoInicial())
				&& Util.verificarNaoVazio(form.getDataCancelamentoFinal()) ) {
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				filtro.setDataCancelamentoInicial(df.parse(form.getDataCancelamentoInicial()));
			} catch (ParseException e) {
				throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO_FORMATO_INVALIDO,
						ConstantesInterfaceGSAN.LABEL_GSAN_DATA_CANCELAMENTO_INICIAL);
			}

			try {
				filtro.setDataCancelamentoFinal(df.parse(form.getDataCancelamentoFinal()));
			} catch (ParseException e) {
				throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO_FORMATO_INVALIDO,
						ConstantesInterfaceGSAN.LABEL_GSAN_DATA_CANCELAMENTO_FINAL);
			}
		}
		
		if (Util.isCampoComboboxInformado(form.getIdUnidadeNegocio())) {
			
			filtro.setUnidadeNegocio(new Integer(form.getIdUnidadeNegocio()));
		}
			
		if (Util.verificarNaoVazio(form.getIdLocalidade())) {
			filtro.setLocalidade(new Integer(form.getIdLocalidade()));
		}

		if ( !Util.isVazioOrNulo(form.getIdsTiposDebito())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getIdsTiposDebito();
			
			for(String atual: array){
				if( !atual.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(atual));					
				}
			}
			
			filtro.setColecaoTiposDebito(colecao);
		}

		if ( !Util.isVazioOrNulo(form.getIdsCategoria())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getIdsCategoria();
			
			for(String atual: array){
				if( !atual.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(atual));					
				}
			}
			
			filtro.setColecaoCategorias(colecao);
		}

		if ( !Util.isVazioOrNulo(form.getIdsPerfilImovel())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getIdsPerfilImovel();
			
			for(String atual: array){
				if( !atual.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(atual));					
				}
			}
			
			filtro.setColecaoPerfilImovel(colecao);
		}

		if ( !Util.isVazioOrNulo(form.getIdsEsferaPoder())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getIdsEsferaPoder();
			
			for(String atual: array){
				if( !atual.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(atual));					
				}
			}
			
			filtro.setColecaoEsferaPoder(colecao);
		}

		if (Util.verificarNaoVazio(form.getIdUsuarioCancelamento())) {
			
			filtro.setUsuarioCancelamento(new Integer(form.getIdUsuarioCancelamento()));
		}


		return filtro;
	}
	
	/**
	 * Esse método adiciona os parametros no objeto TarefaRelatorio
	 * para que os mesmos sejam exibidos quando o relatório for gerado.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void configurarParametrosRelatorio(TarefaRelatorio relatorio, GerarRelatorioJurosMultasDebitosCanceladosActionForm form,
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro, String tipoRelatorio){

		if (Util.verificarNaoVazio(form.getMesAnoFaturamento())) {

			relatorio.addParametro("filtroMesAnoFaturamento", form.getMesAnoFaturamento());
		}

		if (Util.verificarNaoVazio(form.getDataCancelamentoInicial())
				&& Util.verificarNaoVazio(form.getDataCancelamentoFinal()) ) {

			relatorio.addParametro("filtroDataCancelamento", form.getDataCancelamentoInicial() + " a " + form.getDataCancelamentoFinal());
		}
		
		if (Util.isCampoComboboxInformado(form.getIdUnidadeNegocio())) {
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(
					new ParametroSimples(FiltroUnidadeNegocio.ID,form.getIdUnidadeNegocio()));
			filtroUnidadeNegocio.adicionarParametro(
					new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));		

			Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
				this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());

			relatorio.addParametro("filtroUnidadeNegocio", colecaoUnidadeNegocio.iterator().next().getNome());
		}
			
		if (Util.verificarNaoVazio(form.getIdLocalidade())) {

			relatorio.addParametro("filtroLocalidade", form.getIdLocalidade() 
					+ " - " + form.getNomeLocalidade());
		}

		if ( !Util.isVazioOrNulo(form.getIdsTiposDebito())) {
			
			Collection<String> colecao = new ArrayList<String>();
			
			String[] array = form.getIdsTiposDebito();


			for(String atual: array){
				if( !atual.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
					filtroDebitoTipo.adicionarParametro(
							new ParametroSimples(FiltroDebitoTipo.ID, atual));		
					filtroDebitoTipo.adicionarParametro(
							new ParametroSimples(FiltroDebitoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));		

					Collection<DebitoTipo> colecaoDebitoTipo = 
						this.getFachada().pesquisar(filtroDebitoTipo,DebitoTipo.class.getName());
					
					colecao.add(colecaoDebitoTipo.iterator().next().getDescricaoAbreviada());
				}
			}
			
			relatorio.addParametro("filtroTipoDebito", colecao.toString().replace("[", "").replace("]", ""));
		}

		if ( !Util.isVazioOrNulo(form.getIdsCategoria())) {
			
			Collection<String> colecao = new ArrayList<String>();
			
			String[] array = form.getIdsCategoria();
			
			for(String atual: array){
				if( !atual.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					FiltroCategoria filtroCategoria = new FiltroCategoria();
					filtroCategoria.adicionarParametro(
							new ParametroSimples(FiltroCategoria.CODIGO,atual));
					filtroCategoria.adicionarParametro(
							new ParametroSimples(FiltroCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection<Categoria> colecaoCategoria = 
						this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());

					colecao.add(colecaoCategoria.iterator().next().getDescricaoAbreviada());
				}
			}
			
			relatorio.addParametro("filtroCategoria", colecao.toString().replace("[", "").replace("]", ""));
		}

		if ( !Util.isVazioOrNulo(form.getIdsPerfilImovel())) {
			
			Collection<String> colecao = new ArrayList<String>();
			
			String[] array = form.getIdsPerfilImovel();
			
			for(String atual: array){
				if( !atual.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					
					FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
					filtroImovelPerfil.adicionarParametro(new ParametroSimples(
							FiltroImovelPerfil.ID,atual));
					filtroImovelPerfil.adicionarParametro(new ParametroSimples(
							FiltroImovelPerfil.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection<ImovelPerfil> colecaoImoveisPerfis = this.getFachada().pesquisar(
							filtroImovelPerfil, ImovelPerfil.class.getName());

					colecao.add(colecaoImoveisPerfis.iterator().next().getDescricao());
				}
			}
			
			relatorio.addParametro("filtroPerfilImovel", colecao.toString().replace("[", "").replace("]", ""));
		}

		if ( !Util.isVazioOrNulo(form.getIdsEsferaPoder())) {
			
			Collection<String> colecao = new ArrayList<String>();
			
			String[] array = form.getIdsEsferaPoder();
			
			for(String atual: array){
				if( !atual.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
					filtroEsferaPoder.adicionarParametro(
							new ParametroSimples(FiltroEsferaPoder.ID, atual));
					filtroEsferaPoder.adicionarParametro(
							new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection<EsferaPoder> colecaoEsferaPoder = 
						this.getFachada().pesquisar(filtroEsferaPoder,EsferaPoder.class.getName());

					colecao.add(colecaoEsferaPoder.iterator().next().getDescricao());
				}
			}
			
			relatorio.addParametro("filtroEsferaPoder", colecao.toString().replace("[", "").replace("]", ""));
		}

		if (Util.verificarNaoVazio(form.getIdUsuarioCancelamento())) {
			
			relatorio.addParametro("filtroUsuarioCancelamento", form.getIdUsuarioCancelamento()
					+ " - " + form.getNomeUsuarioCancelamento());
			
		}

		relatorio.addParametro("filtrarRelatorioJurosMultasDebitosCanceladosHelper", filtro);
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

	}
	
	/**
	 * Esse método faz validações em cima dos campos
	 * de data de cancelamento.
	 *
	 *@since 09/10/2009
	 *@author Marlon Patrick
	 */
	private void validarPeriodoCancelamento(GerarRelatorioJurosMultasDebitosCanceladosActionForm form) {

		Date dataCancelamentoInicial = Util
				.converteStringParaDate(form.getDataCancelamentoInicial());
		
		Date dataCancelamentoFinal = Util
				.converteStringParaDate(form.getDataCancelamentoFinal());

		if (dataCancelamentoInicial.compareTo(dataCancelamentoFinal) > 0) {
			throw new ActionServletException(
					ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO_FINAL_MENOR_CAMPO_INICIAL,
					ConstantesInterfaceGSAN.LABEL_GSAN_DATA_CANCELAMENTO);
		}
	}

	
}