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
package gcom.gui.faturamento.conta;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.FiltroMotivoRetificacaoConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.text.SimpleDateFormat;
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
 * Retificação de um conjunto de contas 
 *
 * @author Raphael Rossiter
 * @date 05/06/2009
 */
public class ExibirRetificarConjuntoContaAction extends GcomAction {

	 public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	    	ActionForward retorno = actionMapping.findForward("exibirRetificarConjuntoConta");
	        
	        RetificarConjuntoContaActionForm retificarConjuntoContaActionForm = (RetificarConjuntoContaActionForm) actionForm;
	        
	        retificarConjuntoContaActionForm.setMotivoRetificacaoID(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
	        
	        Fachada fachada = Fachada.getInstancia();
	        
	        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
	        
	        // -------------------------------------------------------------------------------------------
			// Alterado por :  Hugo Leonardo - data : 02/07/2010 
			// Analista :  Fabiola Araujo.
	        // [SB0008] Retificar um Conjunto de Contas
			//-------------------------------------------------------------------------------------------
	        
	        if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
	        	Date dtCorrente = new Date();	
	        	
	        	Integer diasAdicionais = 0;
	        	
	        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
	        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
	        	}
	        	
				Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dtCorrente, diasAdicionais.intValue());
	        	retificarConjuntoContaActionForm.setVencimentoConta(Util.formatarData(dataCorrenteComDias));
	        }else{
	        	retificarConjuntoContaActionForm.setVencimentoConta("");
	        }
	        // -------------------------------------------------------------------------------------------
	        
	        retificarConjuntoContaActionForm.setLigacaoAguaSituacaoID(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
	        retificarConjuntoContaActionForm.setConsumoAgua("");
	        retificarConjuntoContaActionForm.setLigacaoEsgotoSituacaoID(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
	        retificarConjuntoContaActionForm.setConsumoEsgoto("");
	        retificarConjuntoContaActionForm.setIndicadorCategoriaEconomiaConta("" + ConstantesSistema.SIM);

	        
	        
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	       
	        
	        /*
	         * RECEBIMENTO DOS PARÂMETROS PARA QUANDO O RETIFICAR CONJUNTO DE CONTAS TIVER SIDO
	         * CHAMADO PELO MANTER CONTA
	         */
	        this.recebimentoParametrosManterConta(httpServletRequest, sessao, 
	        retificarConjuntoContaActionForm, fachada);
	        
	        
	        /*
	         * RECEBIMENTO DOS PARÂMETROS PARA QUANDO O RETIFICAR CONJUNTO DE CONTAS TIVER SIDO
	         * CHAMADO PELO MANTER CONTA DE UM CONJUNTO DE IMÓVEIS
	         */
	        this.recebimentoParametrosManterContaConjuntoImoveis(httpServletRequest, sessao);
	        
	        
	        
	        /*CARREGAMENTO INICIAL DO FORMULÁRIO
	        ========================================================================================== */
	        
	        //Motivo da Retificação (Carregar coleção) e remover as coleções que ainda estão na sessão
	        Collection colecaoMotivoRetificacaoConta;
	        
	        if (sessao.getAttribute("colecaoMotivoRetificacaoConta") == null) {
	        	
	        	
	        	FiltroMotivoRetificacaoConta filtroMotivoRetificacaoConta = new FiltroMotivoRetificacaoConta(
	        			FiltroMotivoRetificacaoConta.DESCRICAO_MOTIVO_RETIFICACAO_CONTA);

	        	filtroMotivoRetificacaoConta.adicionarParametro(new ParametroSimples(
	        			FiltroMotivoRetificacaoConta.INDICADOR_USO,
	                    ConstantesSistema.INDICADOR_USO_ATIVO));

	        	colecaoMotivoRetificacaoConta = fachada.pesquisar(filtroMotivoRetificacaoConta,
	        			ContaMotivoRetificacao.class.getName());

	            if (colecaoMotivoRetificacaoConta == null
	                    || colecaoMotivoRetificacaoConta.isEmpty()) {
	                throw new ActionServletException(
	                        "atencao.pesquisa.nenhum_registro_tabela", null,
	                        "MOTIVO_RETIFICACAO_CONTA");
	            } else {
	                sessao.setAttribute("colecaoMotivoRetificacaoConta",
	                		colecaoMotivoRetificacaoConta);
	            }
	        }
	        
	        
	        Collection colecaoSituacaoLigacaoAgua, colecaoSituacaoLigacaoEsgoto;
	        
	        //Situação Ligação Água (Carregar coleção)
	        if (sessao.getAttribute("colecaoSituacaoLigacaoAgua") == null) {

	        	FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(
	        			FiltroLigacaoAguaSituacao.DESCRICAO);

	        	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
	        			FiltroLigacaoAguaSituacao.INDICADOR_USO,
	                    ConstantesSistema.INDICADOR_USO_ATIVO));

	        	colecaoSituacaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAguaSituacao,
	        			LigacaoAguaSituacao.class.getName());

	            if (colecaoSituacaoLigacaoAgua == null
	                    || colecaoSituacaoLigacaoAgua.isEmpty()) {
	                throw new ActionServletException(
	                        "atencao.pesquisa.nenhum_registro_tabela", null,
	                        "LIGACAO_AGUA_SITUACAO");
	            } else {
	                sessao.setAttribute("colecaoLigacaoAguaSituacao",
	                		colecaoSituacaoLigacaoAgua);
	            }
	        }
	        
	        
	        //Situação Ligação Esgoto (Carregar coleção)
	        if (sessao.getAttribute("colecaoSituacaoLigacaoEsgoto") == null) {

	        	FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(
	        			FiltroLigacaoEsgotoSituacao.DESCRICAO);

	        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
	        			FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
	                    ConstantesSistema.INDICADOR_USO_ATIVO));

	        	colecaoSituacaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao,
	        			LigacaoEsgotoSituacao.class.getName());

	            if (colecaoSituacaoLigacaoEsgoto == null
	                    || colecaoSituacaoLigacaoEsgoto.isEmpty()) {
	                throw new ActionServletException(
	                        "atencao.pesquisa.nenhum_registro_tabela", null,
	                        "LIGACAO_ESGOTO_SITUACAO");
	            } else {
	                sessao.setAttribute("colecaoLigacaoEsgotoSituacao",
	                		colecaoSituacaoLigacaoEsgoto);
	            }
	        }
	        
	        
			//Carregar a data corrente do sistema
	        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	        Calendar dataCorrente = new GregorianCalendar();
	        
	        //Ultimo dia do mês corrente.        
	        Date ultimaDataMes = Util.obterUltimaDataMes(Util.getMes(dataCorrente.getTime()), Util.getAno(dataCorrente.getTime()));        
	        httpServletRequest.setAttribute("ultimaDataMes", formatoData.format(ultimaDataMes));
	        
	        httpServletRequest.setAttribute("indicadorCalculaVencimento", sistemaParametro.getIndicadorCalculaVencimento());
	        
	        //Data Corrente
	        httpServletRequest.setAttribute("dataAtual", formatoData
	        .format(dataCorrente.getTime()));
	        
	        //Data Corrente + 60 dias
	        dataCorrente.add(Calendar.DATE, 60);
	        httpServletRequest.setAttribute("dataAtual60", formatoData
	        .format(dataCorrente.getTime()));
	        
	        /*
	         * Costante que informa o ano limite para o campo anoMesReferencia da conta
	         */
	        httpServletRequest.setAttribute("anoLimite", ConstantesSistema.ANO_LIMITE);
	        
	        //========================================================================================
	        
	        
	        httpServletRequest.setAttribute("nomeCampo", "vencimentoConta");
	        
	        
	        return retorno;
	 }
	 
	 
	 /**
	 * RECEBIMENTO DOS PARÂMETROS PARA QUANDO O RETIFICAR CONJUNTO DE CONTAS TIVER SIDO
	 * CHAMADO PELO MANTER CONTA
	 *
	 * @author Raphael Rossiter
	 * @date 05/06/2009
	 *
	 * @param httpServletRequest
	 * @param sessao
	 * @param retificarConjuntoContaActionForm
	 * @param fachada
	 */
	private void recebimentoParametrosManterConta(HttpServletRequest httpServletRequest,
			 HttpSession sessao, RetificarConjuntoContaActionForm retificarConjuntoContaActionForm, 
			 Fachada fachada){
		 
		 //Imóvel
		 String idImovel = httpServletRequest.getParameter("idImovel");
		 
		 if (idImovel != null && !idImovel.equals("")){
			 
			 //Carregando o identificador das contas selecionadas
		     String contaSelected = httpServletRequest.getParameter("contaRetificarConjunto");
		        
		     retificarConjuntoContaActionForm.setContaSelected(contaSelected);
		     
		     //Vefirica existência do Imovel Condominio
		     FiltroImovel filtroImovel = new FiltroImovel();
		     filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelCondominio");
		        
		     filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel)));
		        
		     Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		     Imovel imovel = (Imovel) colecaoImovel.iterator().next();
		        
		     //Indicador imovel condominio
		     if(imovel.getImovelCondominio() != null){			   
		    	 httpServletRequest.setAttribute("idImovelCondominio", imovel.getImovelCondominio().getId());
		     }
		     
		     /* Bruno Barros
	           05 de Janeiro de 2009
                                                              
             [FS0026] - Verificar a permissão especial para exibir as contas 
                        pagas.
                        
              . Verificar se as contas selecionadas para o imóvel se encontram 
              pagas, através do identificador da conta CNTA_ID nas tabelas 
              ARRECADACAO.PAGAMENTO. Caso o usuário possua permissão especial, 
              as contas identificadas nas condições serão apresentadas e 
              poderão ser retificadas e atualizadas, de acordo com processos 
              já existentes. Caso contrário, serão apenas apresentadas as 
              contas sem possibilitar que o usuário atualize ou retifique as 
              contas pagas.                                             
          */            
          
		     boolean usuarioPodeAtualizarRetificarContasPagas = fachada.verificarPermissaoEspecial( 
		    		 PermissaoEspecial.ATUALIZAR_RETIFICAR_CONTAS_PAGAS, ( Usuario ) sessao.getAttribute(
		    				 Usuario.USUARIO_LOGADO ) );
          
		     if ( !usuarioPodeAtualizarRetificarContasPagas ){
              
		    	 //Verifica se houve seleção de contas
		    	 String[] arrayIdentificadores = null;
              
		    	 if (contaSelected != null) {
		    		 arrayIdentificadores = contaSelected.split(",");
		    	 }                
              
		    	 for (int index = 0; index < arrayIdentificadores.length; index++) {
		    		 String dadosConta = arrayIdentificadores[index];
                  
		    		 String[] idUltimaAlteracao = dadosConta.split("-");
                  
		    		 Pagamento pagamento = fachada.pesquisarPagamentoDeConta( Integer.parseInt( idUltimaAlteracao[0] ) );
                  
		    		 if ( pagamento != null ){
                      
		    			 throw new ActionServletException(
                         "atencao.necessario_permissao_especial_para_atualizar_retificar_contas_pagas", null,
                         "MOTIVO_RETIFICACAO_CONTA");
		    		 }                    
		    	 }
		     }
		 }
	 }
	
	
	
	/**
	 * RECEBIMENTO DOS PARÂMETROS PARA QUANDO O RETIFICAR CONJUNTO DE CONTAS TIVER SIDO
	 * CHAMADO PELO MANTER CONTA DE UM CONJUNTO DE IMÓVEIS
	 *
	 * @author Raphael Rossiter
	 * @date 05/06/2009
	 *
	 * @param httpServletRequest
	 * @param sessao
	 */
	private void recebimentoParametrosManterContaConjuntoImoveis(HttpServletRequest httpServletRequest,
			 HttpSession sessao){
		
		Integer anoMes = null;
		Integer anoMesFim = null;
		Date dataVencimentoContaInicio = null;
		Date dataVencimentoContaFim = null;
		String indicadorContaPaga = null;
		Integer idGrupoFaturamento = null;
		String codigoCliente = null;
		
		
		if(httpServletRequest.getParameter("mesAno") != null){
    		anoMes = Util.formatarMesAnoComBarraParaAnoMes(httpServletRequest.getParameter("mesAno"));	
    		sessao.setAttribute("anoMes", anoMes);
    	}
    	
    	if(httpServletRequest.getParameter("mesAnoFim") != null){
    		anoMesFim = Util.formatarMesAnoComBarraParaAnoMes(httpServletRequest.getParameter("mesAnoFim"));	
    		sessao.setAttribute("anoMesFim", anoMesFim);
    	}
    	
		if (httpServletRequest.getParameter("dataVencimentoContaInicial") != null){
			
			dataVencimentoContaInicio = Util.converteStringParaDate(httpServletRequest.getParameter("dataVencimentoContaInicial"));
			sessao.setAttribute("dataVencimentoContaInicial", dataVencimentoContaInicio);
		}
		
		if (httpServletRequest.getParameter("dataVencimentoContaFinal") != null){
			
			dataVencimentoContaFim = Util.converteStringParaDate(httpServletRequest.getParameter("dataVencimentoContaFinal"));
			sessao.setAttribute("dataVencimentoContaFinal", dataVencimentoContaFim);
		}
		
		if (httpServletRequest.getParameter("indicadorContaPaga") != null){
			
			indicadorContaPaga = httpServletRequest.getParameter("indicadorContaPaga");
			sessao.setAttribute("indicadorContaPaga", indicadorContaPaga);
		}
		
		if (httpServletRequest.getParameter("idGrupoFaturamento") != null){
			
			idGrupoFaturamento = new Integer((String) httpServletRequest.getParameter("idGrupoFaturamento"));
			sessao.setAttribute("idGrupoFaturamento", idGrupoFaturamento);
		}
		
		if (httpServletRequest.getParameter("codigoCliente") != null){
			
			codigoCliente = (String) httpServletRequest.getParameter("codigoCliente");
			sessao.setAttribute("codigoCliente", codigoCliente);
		}
	}
}
