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
package gcom.gui.relatorio.cobranca.parcelamento;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.bean.FiltrarRelacaoParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cobranca.parcelamento.GerarRelatorioRelacaoParcelamentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.parcelamento.RelacaoParcelamentoRelatorioHelper;
import gcom.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamento;
import gcom.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoAnalitico;
import gcom.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoAnaliticoBean;
import gcom.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoCartaoCredito;
import gcom.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoCartaoCreditoBean;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0594] Gerar Relação de Parcelamento
 * 
 * @author Ana Maria, Diogo Peixoto
 *
 * @date 30/05/2007, 29/04/2011
 */
public class GerarRelatorioRelacaoParcelamentoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
        ActionForward retorno = null;

		Fachada fachada =  Fachada.getInstancia();
        
        httpServletRequest.setAttribute( "telaSucessoRelatorio", "sim" );
		
		 //HttpSession sessao = httpServletRequest.getSession(false);    
		
		// cria uma instância da classe do relatório
		RelatorioRelacaoParcelamento relatorioRelacaoParcelamento = 
			new RelatorioRelacaoParcelamento((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));		
		RelatorioRelacaoParcelamentoAnalitico relatorioRelacaoParcelamentoAnalitico = 
			new RelatorioRelacaoParcelamentoAnalitico((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		RelatorioRelacaoParcelamentoCartaoCredito relatorioRelacaoParcelamentoCartaoCredito = 
			new RelatorioRelacaoParcelamentoCartaoCredito((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		
		GerarRelatorioRelacaoParcelamentoActionForm form = (GerarRelatorioRelacaoParcelamentoActionForm) actionForm;

		String idLocalidade     = form.getIdLocalidade();
		String codigoSetorComercial = form.getIdSetorComercial();
		String nnQuadra 		= form.getIdQuadra();	
		String idSituacaoParcelamento =  form.getIdSituacaoParcelamento();
		 
		Parcelamento parcelamento = new Parcelamento();
		FiltrarRelacaoParcelamentoHelper filtroParcelamento = new FiltrarRelacaoParcelamentoHelper();
		
		boolean peloMenosUmParametroInformado = false;

		// Unidade Organizacional
		if (form.getIdUnidadeOrganizacional() != null && !form.getIdUnidadeOrganizacional().trim().equals("") ){

			peloMenosUmParametroInformado = true;
			filtroParcelamento.setIdUnidadeOrganizacional(
					new Integer(form.getIdUnidadeOrganizacional()));
		}
		
		
		// Insere os parâmetros informados no filtro
		Localidade localidade = new Localidade();
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
				peloMenosUmParametroInformado = true;
				localidade.setId(new Integer(idLocalidade));
				parcelamento.setLocalidade(localidade);
			}
			
			if(codigoSetorComercial != null && !codigoSetorComercial.trim().equals("")){
			  parcelamento.setCodigoSetorComercial(new Integer(codigoSetorComercial));
			}
			if(nnQuadra != null && !nnQuadra.trim().equals("")){
			  parcelamento.setNumeroQuadra(new Integer(nnQuadra));
			}
		}

		// Municípios Associados à Localidade
		if (form.getMunicipiosAssociados() != null && form.getMunicipiosAssociados().length > 0) {
			Collection<Integer> colecao = new ArrayList();
			String[] array = form.getMunicipiosAssociados();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					peloMenosUmParametroInformado = true;
					colecao.add(new Integer(array[i]));
				}
			}
			filtroParcelamento.setColecaoMunicipiosAssociados(colecao);
		}
		
		// Perfil do Imovel
		if (form.getPerfilImovel() != null && form.getPerfilImovel().length > 0) {

			Collection<Integer> colecao = new ArrayList();

			String[] array = form.getPerfilImovel();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					peloMenosUmParametroInformado = true;
					colecao.add(new Integer(array[i]));
				}
			}
			filtroParcelamento.setColecaoPerfilImovel(colecao);
		}
		
		Integer idGerencia   = null;
		if(form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			idGerencia = new Integer(form.getIdGerenciaRegional());
			
		}
		
		Integer idUnidadeNegocio   = null;
		if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			idUnidadeNegocio = new Integer(form.getIdUnidadeNegocio());
			
		}

		if(idSituacaoParcelamento != null && !idSituacaoParcelamento.trim().equals("")){
			ParcelamentoSituacao parcelamentoSituacao = new ParcelamentoSituacao();
			parcelamentoSituacao.setId(new Integer(idSituacaoParcelamento));
			parcelamento.setParcelamentoSituacao(parcelamentoSituacao);
			
			
		}
		
		Collection<Integer> idsMotivoDesfazimento = new ArrayList();
		if (form.getIdsMotivoDesfazimento() != null && form.getIdsMotivoDesfazimento().length > 0) {
			String[] motivoDesfazimento = form.getIdsMotivoDesfazimento();
			for (int i = 0; i < motivoDesfazimento.length; i++) {
				if (new Integer(motivoDesfazimento[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					idsMotivoDesfazimento.add(new Integer(motivoDesfazimento[i]));
					// passar a coleção de especificação por parâmetro		
					peloMenosUmParametroInformado = true;
				}
			}
		}
		
		Date dataParcelamentoInicial	=  null;
		Date dataParcelamentoFinal	=  null;
		
		if (form.getDataParcelamentoInicial() != null && !form.getDataParcelamentoInicial().equals("")) {
			
			dataParcelamentoInicial = Util.converteStringParaDate(form.getDataParcelamentoInicial());
			dataParcelamentoInicial = Util.formatarDataInicial(dataParcelamentoInicial);
			
			dataParcelamentoFinal = null;
			if (form.getDataParcelamentoFinal() != null && !form.getDataParcelamentoFinal().equals("")) {
				dataParcelamentoFinal = Util.converteStringParaDate(form.getDataParcelamentoFinal());
				dataParcelamentoFinal = Util.adaptarDataFinalComparacaoBetween(dataParcelamentoFinal);
			} else {
				dataParcelamentoFinal = new Date();
				dataParcelamentoFinal = Util.formatarDataFinal(dataParcelamentoFinal);
			}
			//[FS005] Verificar data final menor que data inicial
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataParcelamentoInicial, dataParcelamentoFinal);
			if (qtdeDias < 0) {
				throw new ActionServletException("atencao.filtrar_data_final_maior_que_inicial");
			}
			
			relatorioRelacaoParcelamento.addParametro("dataParcelamentoInicial", form.getDataParcelamentoInicial());
			relatorioRelacaoParcelamento.addParametro("dataParcelamentoFinal", form.getDataParcelamentoFinal());
			// passar as datas de atendimento por parâmetro
			peloMenosUmParametroInformado = true;
		}
		
		String valorDebitoInicial	=  form.getValorDebitoInicial();
		String valorDebitoFinal	=  form.getValorDebitoFinal();
		BigDecimal valorInicial = null;
		BigDecimal valorFinal = null;
			
		// Verifica se o campo valorDebitoInicial e valorDebitoFinal foram informados
		if (valorDebitoInicial != null && !valorDebitoInicial.trim().equalsIgnoreCase("") &&
				valorDebitoFinal != null && !valorDebitoFinal.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;	
			
			String valorSemPontosInicial = valorDebitoInicial.replace(".", "");
			valorDebitoInicial = valorSemPontosInicial.replace(",", ".");	

			String valorSemPontosFinal = valorDebitoFinal.replace(".", "");
			valorDebitoFinal = valorSemPontosFinal.replace(",", ".");

		   valorInicial = new BigDecimal(valorDebitoInicial);
		   valorFinal = new BigDecimal(valorDebitoFinal);
		   Integer resultado = valorInicial.compareTo(valorFinal);

		   if (resultado == 1) {
			throw new ActionServletException(
					"atencao.valor_servico_final_menor_valor_servico_inicial");
		   }
		   
		}
		
		// Verificamos se o elo foi informado
		Integer idElo = null;
		
		if ( form.getIdEloPolo() != null && 
			 !form.getIdEloPolo().equals( "" ) ){
			peloMenosUmParametroInformado = true;
			
			FiltroLocalidade filtro = new FiltroLocalidade();
			filtro.adicionarParametro( 
					new ParametroSimples( FiltroLocalidade.ID, form.getIdLocalidade() ) );			
			Collection<Localidade> colLocalidade = fachada.pesquisar( filtro, Usuario.class.getName() );
			
			if ( colLocalidade != null && colLocalidade.size() == 0 ){
				throw new ActionServletException(
				"atencao.elo_invalido", null, form.getIdEloPolo() );				
			}
			
			idElo = Integer.parseInt( form.getIdEloPolo() );
			
		}		
		
		// Verificamos se o usuário responsavel foi informado
		Integer idUsuarioResponsavel = null;
		
		if ( form.getIdUsuarioResponsavel() != null && 
			 !form.getIdUsuarioResponsavel().equals( "" ) ){
			peloMenosUmParametroInformado = true;
			
			FiltroUsuario filtro = new FiltroUsuario();
			filtro.adicionarParametro( 
					new ParametroSimples( FiltroUsuario.ID, form.getIdUsuarioResponsavel() ) );			
			Collection<Usuario> colUsuario = fachada.pesquisar( filtro, Usuario.class.getName() );
			
			if ( colUsuario != null && colUsuario.size() == 0 ){
				throw new ActionServletException(
				"atencao.usuario_responsavel_invalido", null, form.getIdUsuarioResponsavel() );				
			}
			
			idUsuarioResponsavel = Integer.parseInt( form.getIdUsuarioResponsavel() );
		}
		
		// Verificamos data de confirmação parcelamento
		Date dataConfirmacaoInicial	=  null;
		Date dataConfirmacaoFinal	=  null;
		
		if (form.getDataConfirmacaoInicial() != null && !form.getDataConfirmacaoInicial().equals("")
				&& form.getDataConfirmacaoFinal() != null && !form.getDataConfirmacaoFinal().equals("")) {
			
			dataConfirmacaoInicial = Util.converteStringParaDate(form.getDataConfirmacaoInicial());
			dataConfirmacaoInicial = Util.formatarDataInicial(dataConfirmacaoInicial);
			dataConfirmacaoFinal = Util.converteStringParaDate(form.getDataConfirmacaoFinal());
			dataConfirmacaoFinal = Util.adaptarDataFinalComparacaoBetween(dataConfirmacaoFinal);

			//[FS005] Verificar data final menor que data inicial
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataConfirmacaoInicial, dataConfirmacaoFinal);
			if (qtdeDias < 0) {
				throw new ActionServletException("atencao.filtrar_data_final_maior_que_inicial");
			}
			
			// passar as datas de atendimento por parâmetro
			peloMenosUmParametroInformado = true;
		}
		
		// Verificamos se o usuário confirmação foi informado
		Integer idUsuarioConfirmacao = null;
		
		if ( form.getIdUsuarioConfirmacao() != null && 
			 !form.getIdUsuarioConfirmacao().equals( "" )){
			peloMenosUmParametroInformado = true;
			
			FiltroUsuario filtro = new FiltroUsuario();
		    filtro.adicionarParametro( 
					new ParametroSimples( FiltroUsuario.ID, form.getIdUsuarioConfirmacao() ) );			
			Collection<Usuario> colUsuario = fachada.pesquisar( filtro, Usuario.class.getName() );
			
			if ( colUsuario != null && colUsuario.size() == 0 ){
				throw new ActionServletException(
				"atencao.usuario_confirmacao_invalido", null, form.getIdUsuarioConfirmacao() );				
			}
			
			idUsuarioConfirmacao = Integer.parseInt(form.getIdUsuarioConfirmacao());
		}
		
		// Verificamos indicador de confirmação da operadora
		Short indicadorConfirmacaoOperadora = null;
		Date dataConfirmacaoOperadoraInicial	=  null;
		Date dataConfirmacaoOperadoraFinal	=  null;
		if(form.getIndicadorConfirmacaoOperadora()!=null
				&& !form.getIndicadorConfirmacaoOperadora().equals("")){
			
			peloMenosUmParametroInformado = true;
			
			indicadorConfirmacaoOperadora = new Short(form.getIndicadorConfirmacaoOperadora());
			
			if(form.getIndicadorConfirmacaoOperadora().equals(ConstantesSistema.SIM.toString())){
				if(form.getDataConfirmacaoOperadoraInicial()!=null && !form.getDataConfirmacaoOperadoraInicial().equals("")){
					dataConfirmacaoOperadoraInicial = Util.converteStringParaDate(form.getDataConfirmacaoOperadoraInicial());
					dataConfirmacaoOperadoraInicial = Util.formatarDataInicial(dataConfirmacaoOperadoraInicial);
				}
				if(form.getDataConfirmacaoOperadoraFinal()!=null && !form.getDataConfirmacaoOperadoraFinal().equals("")){
					dataConfirmacaoOperadoraFinal = Util.converteStringParaDate(form.getDataConfirmacaoOperadoraFinal());
					dataConfirmacaoOperadoraFinal = Util.formatarDataInicial(dataConfirmacaoOperadoraFinal);
				}
			}		
		}
		
		
		
		Collection<RelacaoParcelamentoRelatorioHelper> colecaoRelacaoParcelamento = null;
		Collection<RelatorioRelacaoParcelamentoAnaliticoBean> colecaoRelacaoParcelamentoAnalitico = null;
		Collection<RelatorioRelacaoParcelamentoCartaoCreditoBean> colecaoRelacaoParcelamentoCartaoCredito = null;

		if (peloMenosUmParametroInformado) {
			
			colecaoRelacaoParcelamento = new ArrayList();
			
			filtroParcelamento.setParcelamento(parcelamento);
			filtroParcelamento.setDataParcelamentoInicial(dataParcelamentoInicial);
			filtroParcelamento.setDataParcelamentoFinal(dataParcelamentoFinal);
			filtroParcelamento.setIdsMotivoDesfazimento(idsMotivoDesfazimento);
			filtroParcelamento.setValorDebitoInicial(valorInicial);
			filtroParcelamento.setValorDebitoFinal(valorFinal);
			filtroParcelamento.setIdGerencia(idGerencia);
			filtroParcelamento.setIdUnidadeNegocio(idUnidadeNegocio);
			filtroParcelamento.setIdUsuarioResponsavel( idUsuarioResponsavel );
			filtroParcelamento.setIdElo( idElo );
			filtroParcelamento.setDataConfirmacaoInicial(dataConfirmacaoInicial);
			filtroParcelamento.setDataConfirmacaoFinal(dataConfirmacaoFinal);
			filtroParcelamento.setIdUsuarioConfirmacao(idUsuarioConfirmacao);
			filtroParcelamento.setIndicadorConfirmacaoOperadora(indicadorConfirmacaoOperadora);
			filtroParcelamento.setDataConfirmacaoOperadoraInicial(dataConfirmacaoOperadoraInicial);
			filtroParcelamento.setDataConfirmacaoOperadoraFinal(dataConfirmacaoOperadoraFinal);
			
			
			//MARKETING ATIVO
			if ( form.getIdVisaoRelatorio().equals( "1" ) ) {			
				colecaoRelacaoParcelamento = fachada.filtrarRelacaoParcelamento(filtroParcelamento);
			//ANALITICO
			} else 	if ( form.getIdVisaoRelatorio().equals( "2" ) ) {
				colecaoRelacaoParcelamentoAnalitico = fachada.filtrarRelacaoParcelamentoAnalitico(filtroParcelamento);
			//CARTAO CREDITO
			} else 	if ( form.getIdVisaoRelatorio().equals( "3" ) ) {
				colecaoRelacaoParcelamentoCartaoCredito = fachada.filtrarRelacaoParcelamentoCartaoCredito(filtroParcelamento);
			}
			
		} else {
			throw new ActionServletException("atencao.filtrar_informar_um_filtro");
		}
		
		// Gerencia Regional
		String parametroGerencia = null;
		if ( filtroParcelamento.getIdGerencia() != null && filtroParcelamento.getIdGerencia().toString() != "" ){
			FiltroGerenciaRegional filtro = new FiltroGerenciaRegional();
			filtro.adicionarParametro( new ParametroSimples( FiltroGerenciaRegional.ID, filtroParcelamento.getIdGerencia() ) );
			Collection<GerenciaRegional> colGerencia = fachada.pesquisar( filtro, GerenciaRegional.class.getName() );
			
			if ( colGerencia != null && colGerencia.size() > 0 ){
				GerenciaRegional gerencia = (GerenciaRegional) colGerencia.iterator().next();
				
				parametroGerencia = new String();
				parametroGerencia = "Gerência Regional - " + filtroParcelamento.getIdGerencia() + " " + gerencia.getNome() + " ";
			}
		}
		
		// Unidade Organizacional
		String parametroUnidadeOrganizacional = null;
		if ( filtroParcelamento.getIdUnidadeOrganizacional() != null && !filtroParcelamento.getIdUnidadeOrganizacional().toString().equals("") ){
			
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro( new ParametroSimples( 
					FiltroUnidadeOrganizacional.ID, filtroParcelamento.getIdUnidadeOrganizacional() ) );
			
			Collection<UnidadeOrganizacional> colUnidade = fachada.pesquisar( filtro, UnidadeOrganizacional.class.getName() );
			
			if ( colUnidade != null && colUnidade.size() > 0 ){
				UnidadeOrganizacional unidade = (UnidadeOrganizacional) colUnidade.iterator().next();
				
				parametroUnidadeOrganizacional = new String();
				parametroUnidadeOrganizacional = "Unidade Organizacional - " + filtroParcelamento.getIdUnidadeOrganizacional() + " " + unidade.getDescricao() + " ";
			}
		}
		
		// Unidade Negocio
		String parametroUnidadeNegocio = null;
		if ( filtroParcelamento.getIdUnidadeNegocio() != null && filtroParcelamento.getIdUnidadeNegocio().toString() != "" ){
			FiltroUnidadeNegocio filtro = new FiltroUnidadeNegocio();
			filtro.adicionarParametro( new ParametroSimples( FiltroUnidadeNegocio.ID, filtroParcelamento.getIdUnidadeNegocio() ) );
			Collection<UnidadeNegocio> colUnidadeNegocio = fachada.pesquisar( filtro, UnidadeNegocio.class.getName() );
			
			if ( colUnidadeNegocio != null && colUnidadeNegocio.size() > 0 ){
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) colUnidadeNegocio.iterator().next();
				
				parametroUnidadeNegocio = new String();
				
				parametroUnidadeNegocio = "Unidade de Negócio - " + filtroParcelamento.getIdUnidadeNegocio() + " " + unidadeNegocio.getNome() + " ";
			}
		}
		
		// Elo
		String parametroElo = null;
		if ( filtroParcelamento.getIdElo() != null && filtroParcelamento.getIdElo().toString() != "" ){
			FiltroLocalidade filtro = new FiltroLocalidade();
			filtro.adicionarParametro( new ParametroSimples( FiltroLocalidade.ID_ELO, filtroParcelamento.getIdElo() ) );
			Collection<Localidade> colElo = fachada.pesquisar( filtro, Localidade.class.getName() );
			
			if ( colElo != null && colElo.size() > 0 ){
				Localidade elo = (Localidade) colElo.iterator().next();
				
				parametroElo = new String();
				
				parametroElo = "Elo - " + filtroParcelamento.getIdElo() + " " + elo.getDescricao() + " ";
			}
		}
		
		// Periodo Parcelamento
		String parametroPeriodo = null;
		if ( ( filtroParcelamento.getDataParcelamentoFinal() != null && 
			  !filtroParcelamento.getDataParcelamentoFinal().equals( "" ) ) ||
			 ( filtroParcelamento.getDataParcelamentoInicial() != null && 
			  !filtroParcelamento.getDataParcelamentoInicial().equals( "" ) ) ){
			
			parametroPeriodo = new String();
			
			parametroPeriodo = "Período - de " + 
				Util.formatarData( 
						filtroParcelamento.getDataParcelamentoInicial() ) + 
				" até " + Util.formatarData( 
						filtroParcelamento.getDataParcelamentoFinal() ) + "  ";
		}
		
		// Usuario Responsavel
		String parametroUsuario = null;
		if ( filtroParcelamento.getIdUsuarioResponsavel() != null && filtroParcelamento.getIdUsuarioResponsavel().toString() != "" ){
			FiltroUsuario filtro = new FiltroUsuario();
			filtro.adicionarParametro( new ParametroSimples( FiltroUsuario.ID, filtroParcelamento.getIdUsuarioResponsavel() ) );
			Collection<Usuario> colUsuario = fachada.pesquisar( filtro, Usuario.class.getName() );
			
			if ( colUsuario != null && colUsuario.size() > 0 ){
				Usuario usuario = (Usuario) colUsuario.iterator().next();
				
				parametroUsuario = new String();
				parametroUsuario = "Usuário Responsável - " + filtroParcelamento.getIdUsuarioResponsavel() + " " + usuario.getNomeUsuario() + " ";
			}
		}
		
		String parametroPerfilImovel = null;
		String parametroValor = null;
		if(filtroParcelamento.getColecaoPerfilImovel() !=null 
				&& !filtroParcelamento.getColecaoPerfilImovel().isEmpty()){
			
			Collection idsPerfilImovel = filtroParcelamento.getColecaoPerfilImovel();
			
			Iterator iteratorPerfilImovel = idsPerfilImovel.iterator();
			
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			
			while (iteratorPerfilImovel.hasNext()) {

				Integer idPerfil = (Integer) iteratorPerfilImovel
						.next();

				if (!iteratorPerfilImovel.hasNext()) {

					filtroImovelPerfil
							.adicionarParametro(new ParametroSimples(
									FiltroImovelPerfil.ID, idPerfil));
				} else {

					filtroImovelPerfil
							.adicionarParametro(new ParametroSimples(
									FiltroImovelPerfil.ID, idPerfil,
									ConectorOr.CONECTOR_OR));

				}

			}
			
			Collection colecaoImovelPerfil = fachada.pesquisar(
					filtroImovelPerfil,ImovelPerfil.class.getName());
			
			// Perfil Imovel
			if(colecaoImovelPerfil !=null && !colecaoImovelPerfil.isEmpty()){
				
				parametroPerfilImovel = new String();
				parametroPerfilImovel = "Peril do Imóvel - ";	
				
				Iterator iteratorImovelPerfil = colecaoImovelPerfil.iterator();
				
				while (iteratorImovelPerfil.hasNext()){
				
					ImovelPerfil imovelPerfil = (ImovelPerfil) iteratorImovelPerfil.next();
					
					parametroPerfilImovel += imovelPerfil.getDescricao() + "  , ";
				
				}
				
				parametroPerfilImovel = Util.removerUltimosCaracteres(parametroPerfilImovel,2);
			}
			
			// Valor
			if (form.getValorDebitoInicial()!= null && !form.getValorDebitoInicial().trim().equalsIgnoreCase("") &&
					form.getValorDebitoFinal() != null && !form.getValorDebitoFinal().trim().equalsIgnoreCase("")) {
				
				parametroValor = new String();
				parametroValor = "Valor - de " + form.getValorDebitoInicial() +  " a " + form.getValorDebitoFinal() + "  ";
				
				
			}
		}
		
		String parametroMuncipio = null;
		if(filtroParcelamento.getColecaoMunicipiosAssociados() !=null 
				&& !filtroParcelamento.getColecaoMunicipiosAssociados().isEmpty()){

			Collection idsMunicipios = filtroParcelamento.getColecaoMunicipiosAssociados();

			Iterator iteratorMuncipios = idsMunicipios.iterator();

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			while (iteratorMuncipios.hasNext()) {

				Integer idMuncipio = (Integer) iteratorMuncipios.next();

				if (!iteratorMuncipios.hasNext()) {
					filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMuncipio));
				} else {
					filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMuncipio, ConectorOr.CONECTOR_OR));
				}
			}

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio,Municipio.class.getName());

			// Perfil Imovel
			if(colecaoMunicipio != null && !colecaoMunicipio.isEmpty()){

				parametroMuncipio = new String();
				parametroMuncipio = "Município - ";	

				Iterator iteratorMunicipio = colecaoMunicipio.iterator();

				while (iteratorMunicipio.hasNext()){

					Municipio municipio = (Municipio) iteratorMunicipio.next();

					parametroMuncipio += municipio.getNome() + "  , ";

				}

				parametroMuncipio = Util.removerUltimosCaracteres(parametroMuncipio,2);
			}
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}		
		
		if ( form.getIdVisaoRelatorio().equals( "1" ) ){
			if(colecaoRelacaoParcelamento == null || colecaoRelacaoParcelamento.isEmpty()){
			  throw new ActionServletException("atencao.pesquisa.nenhumresultado");	
			}else{
				  relatorioRelacaoParcelamento.addParametro("colecaoRelacaoParcelamento",colecaoRelacaoParcelamento);
			}
			
			Integer idSituacao = new Integer(form.getIdSituacaoParcelamento());	
			String situacao = null;
			if(idSituacao.equals(ParcelamentoSituacao.NORMAL)){
				situacao = "NORMAL";
			}else{
				situacao = "DESFEITO";
			}
			
			String faixaValores = null;
			if(!valorDebitoInicial.equals("") && !valorDebitoFinal.equals("")){
				faixaValores = Util.formatarMoedaReal(valorInicial) + " a " + Util.formatarMoedaReal(valorFinal);
			}
			
			String periodo = null;
			if(dataParcelamentoInicial != null && dataParcelamentoFinal != null){
				periodo = Util.formatarData(dataParcelamentoInicial) + " a " + Util.formatarData(dataParcelamentoFinal);
			}
			
			String cabecalho = "RELAÇÃO DE PARCELAMENTOS - " + "SITUAÇÃO: " +situacao+ " - MARKETING ATIVO";
			
			//relatorioRelacaoParcelamento.addParametro( "parametros", parametros );
			
			relatorioRelacaoParcelamento.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			relatorioRelacaoParcelamento.addParametro("cabecalho", cabecalho);
			relatorioRelacaoParcelamento.addParametro("faixaValores", faixaValores);
			relatorioRelacaoParcelamento.addParametro("periodo", periodo);
			
			relatorioRelacaoParcelamento.addParametro( "parametrosGerencia", parametroGerencia );
			relatorioRelacaoParcelamento.addParametro( "parametroUnidadeOrganizacional", parametroUnidadeOrganizacional );
			relatorioRelacaoParcelamento.addParametro( "parametroUnidadeNegocio", parametroUnidadeNegocio );
			relatorioRelacaoParcelamento.addParametro( "parametroElo", parametroElo );
			relatorioRelacaoParcelamento.addParametro( "parametroPeriodo", parametroPeriodo );
			relatorioRelacaoParcelamento.addParametro( "parametroUsuario", parametroUsuario );
			relatorioRelacaoParcelamento.addParametro( "parametroPerfilImovel", parametroPerfilImovel );
			relatorioRelacaoParcelamento.addParametro( "parametroValor", parametroValor );
			relatorioRelacaoParcelamento.addParametro( "parametroMuncipio", parametroMuncipio );
			
		} else if ( form.getIdVisaoRelatorio().equals( "2" ) ){
			if(colecaoRelacaoParcelamentoAnalitico == null || colecaoRelacaoParcelamentoAnalitico.isEmpty()){
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");	
			}else{
				relatorioRelacaoParcelamentoAnalitico.addParametro("colecaoRelacaoParcelamentoAnalitico",colecaoRelacaoParcelamentoAnalitico);
				relatorioRelacaoParcelamentoAnalitico.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
				String cabecalho = "RELAÇÃO DE PARCELAMENTOS - ANALÍTICO";
				relatorioRelacaoParcelamentoAnalitico.addParametro("cabecalho", cabecalho);
				
				//relatorioRelacaoParcelamentoAnalitico.addParametro( "parametros", parametros );
				
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametrosGerencia", parametroGerencia );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroUnidadeOrganizacional", parametroUnidadeOrganizacional );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroUnidadeNegocio", parametroUnidadeNegocio );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroElo", parametroElo );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroPeriodo", parametroPeriodo );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroUsuario", parametroUsuario );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroPerfilImovel", parametroPerfilImovel );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroValor", parametroValor );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroMuncipio", parametroMuncipio );
			}
		} else if ( form.getIdVisaoRelatorio().equals( "3" ) ){
			relatorioRelacaoParcelamentoCartaoCredito.addParametro("colecaoRelacaoParcelamentoCartaoCredito",colecaoRelacaoParcelamentoCartaoCredito);
			relatorioRelacaoParcelamentoCartaoCredito.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			String cabecalho = "RELAÇÃO DE PARCELAMENTOS - CARTÃO DE CRÉDITO";
			relatorioRelacaoParcelamentoCartaoCredito.addParametro("cabecalho", cabecalho);
			
			//relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametros", parametros );
			
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametrosGerencia", parametroGerencia );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroUnidadeOrganizacional", parametroUnidadeOrganizacional );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroUnidadeNegocio", parametroUnidadeNegocio );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroElo", parametroElo );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroPeriodo", parametroPeriodo );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroUsuario", parametroUsuario );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroPerfilImovel", parametroPerfilImovel );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroValor", parametroValor );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroMuncipio", parametroMuncipio );
		}
		relatorioRelacaoParcelamento.addParametro("tipoRelatorio", tipoRelatorio);
		try {
			
			if ( form.getIdVisaoRelatorio().equals( "1" ) ){
				retorno = processarExibicaoRelatorio(relatorioRelacaoParcelamento,
						tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);				
			} else if ( form.getIdVisaoRelatorio().equals( "2" ) ){
				retorno = processarExibicaoRelatorio(relatorioRelacaoParcelamentoAnalitico,
						tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);			
			}else if ( form.getIdVisaoRelatorio().equals( "3" ) ){
				retorno = processarExibicaoRelatorio(relatorioRelacaoParcelamentoCartaoCredito,
						tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);			
			}

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}	
        
        return retorno;
	}
}
