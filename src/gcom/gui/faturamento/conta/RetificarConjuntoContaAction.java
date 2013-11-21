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

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.Processo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.RetificarConjuntoContaConsumosHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetificarConjuntoContaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirManterConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        SistemaParametro sistemaParametro = 
			this.getFachada().pesquisarParametrosDoSistema();
        
        RetificarConjuntoContaActionForm retificarConjuntoContaActionForm = (RetificarConjuntoContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
    	/*
		 * RECEBIMENTO DOS PARÂMETROS PARA QUANDO O RETIFICAR CONJUNTO DE CONTAS TIVER SIDO
		 * CHAMADO PELO MANTER CONTA
		 */
    	//Contas selecionadas pelo usuário
        String identificadoresConta = retificarConjuntoContaActionForm.getContaSelected();
        
        //TODAS AS CONTAS
        Collection<Conta> colecaoContaImovel = (Collection) sessao.getAttribute("colecaoContaImovel");
        
        
        /*
         * RECEBIMENTO DOS PARÂMETROS PARA QUANDO O RETIFICAR CONJUNTO DE CONTAS TIVER SIDO
         * CHAMADO PELO MANTER CONTA DE UM CONJUNTO DE IMÓVEIS
         */
        Collection colecaoImovel = (Collection) sessao.getAttribute("colecaoImovel");
        
        
        
        //RECEBENDO OS DADOS PARA RETIFICAÇÃO DA(S) CONTA(S)
        
        //DATA DE VENCIMENTO
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        
        Date dataVencimentoConta;
        
        
        try{
        	dataVencimentoConta = formatoData.parse(retificarConjuntoContaActionForm.getVencimentoConta());
        	
        	// -------------------------------------------------------------------------------------------
			// Alterado por :  Hugo Leonardo - data : 19/08/2010 
			// Analista :  Aryed Lins.
        	// [FS0007] - Validar data de vencimento.		
			// -------------------------------------------------------------------------------------------
    				
			// if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
	        	
	        // Calendar data1985 = new GregorianCalendar(1985, 1, 1);
	        // Caso data corrente seja posterior a data corrente mais a quantidade de dias parametro.
			Date dataCorrente = new Date();	
			
			Integer diasAdicionais = 0;
        	
        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
        	}
			
			Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dataCorrente, diasAdicionais.intValue());
			// Date dataUltimoDiaMes = Util.obterUltimaDataMes(Util.getMes(dataCorrente), Util.getAno(dataCorrente));
		    // if(Util.compararData(dataVencimentoConta, data1985.getTime()) == -1){
			//		throw new ActionServletException("atencao.data_vencimento_menor_1985");
			// }
				
			// E o usuário não tenha permissão especial.	
			boolean temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao = 
				this.getFachada().verificarPermissaoEspecial(
					PermissaoEspecial.RETIFICAR_DATA_VENCIMENTO_ALEM_PRAZO_PADRAO,
					usuarioLogado);
			
			// 1 se a dataVencimentoConta for maior que a dataCorrenteComDias.
			if(Util.compararData(dataVencimentoConta, dataCorrenteComDias) == 1 && 
				temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao == false){
				
				throw new ActionServletException("atencao.necessario_permissao_especial_para_data_vencimento_posterior_permitido");
			}
			
			Calendar data1985 = new GregorianCalendar(1985, 1, 1);
			if(Util.compararData(dataVencimentoConta, data1985.getTime()) == -1){
				throw new ActionServletException("atencao.data_vencimento_menor_1985");
			}
				
			// if(!temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao && 
			//		Util.compararData(dataVencimentoConta, dataUltimoDiaMes) == 1){
			//						
			//		dataVencimentoConta = dataUltimoDiaMes;
			// }
			// }
			// -------------------------------------------------------------------------------------------
        } catch (ParseException ex) {
        	dataVencimentoConta = null;
        }
        
        //LIGACAO_SITUACAO_AGUA
        FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
        
        filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, new 
        Integer(retificarConjuntoContaActionForm.getLigacaoAguaSituacaoID())));
        
        Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
        
        LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
        
        Integer consumoAgua = 0;
        if (retificarConjuntoContaActionForm.getConsumoAgua() != null && 
        	!retificarConjuntoContaActionForm.getConsumoAgua().equals("")){
        	
        	consumoAgua = new Integer(retificarConjuntoContaActionForm.getConsumoAgua());
        }
        
        //LIGACAO_SITUACAO_ESGOTO
        FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
        
        filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, new 
        Integer(retificarConjuntoContaActionForm.getLigacaoEsgotoSituacaoID())));
        
        Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
        
        LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
        
        Integer consumoEsgoto = 0;
        if (retificarConjuntoContaActionForm.getConsumoEsgoto() != null && 
        	!retificarConjuntoContaActionForm.getConsumoEsgoto().equals("")){
        	
        	consumoEsgoto = new Integer(retificarConjuntoContaActionForm.getConsumoEsgoto());
        }
        
        //CONTA_MOTIVO_REVISAO
        ContaMotivoRetificacao contaMotivoRetificacao = new ContaMotivoRetificacao();
        contaMotivoRetificacao.setId(new Integer(retificarConjuntoContaActionForm.getMotivoRetificacaoID()));
        
        Short indicadorCategoriaEconomiaConta = null;
        
        if (retificarConjuntoContaActionForm.getIndicadorCategoriaEconomiaConta() != null && 
        	retificarConjuntoContaActionForm.getIndicadorCategoriaEconomiaConta().equals(ConstantesSistema.NAO.toString())) {
        	indicadorCategoriaEconomiaConta = ConstantesSistema.NAO;
        } else {
        	indicadorCategoriaEconomiaConta = ConstantesSistema.SIM;
        }
        
        
        //RETIFICANDO AS CONTAS A PARTIR DO CASO DE USO MANTER CONTA
        if (colecaoContaImovel != null){
        	
        	fachada.retificarConjuntoConta(colecaoContaImovel, identificadoresConta, ligacaoAguaSituacao, consumoAgua,
        	ligacaoEsgotoSituacao, consumoEsgoto, dataVencimentoConta, contaMotivoRetificacao, indicadorCategoriaEconomiaConta, usuarioLogado);
        	
        	//Realizar um reload na tela de manter conta
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        }
        
        //RETIFICANDO AS CONTAS A PARTIR DO CASO DE USO MANTER CONTA DE UM CONJUNTO DE IMÓVEIS
        else if (colecaoImovel != null){
        	
        	RetificarConjuntoContaConsumosHelper helper = 
        	this.carregarRetificarConjuntoContaConsumosHelper(sessao);
        	helper.setColecaoImovel(colecaoImovel);
        	helper.setDataVencimentoContaRetificacao(dataVencimentoConta);
        	helper.setContaMotivoRetificacao(contaMotivoRetificacao);
        	helper.setLigacaoAguaSituacao(ligacaoAguaSituacao);
        	helper.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
        	helper.setConsumoAgua(consumoAgua);
        	helper.setVolumeEsgoto(consumoEsgoto);
        	helper.setIndicadorCategoriaEconomiaConta(indicadorCategoriaEconomiaConta);
        	
        	//adicionado por Vivianne Sousa - 04/08/2009
        	Map parametros = new HashMap();
        	parametros.put("helper",helper);
        	Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
             		Processo.RETIFICAR_CONJUNTO_CONTAS_CONSUMO,
             		this.getUsuarioLogado(httpServletRequest));
        	
        	
        	sessao.removeAttribute("anoMes");
            sessao.removeAttribute("anoMesFim");
            sessao.removeAttribute("dataVencimentoContaInicial");
            sessao.removeAttribute("dataVencimentoContaFinal");
            sessao.removeAttribute("indicadorContaPaga");
            
            //Realizar um reload na tela de manter conta
        	httpServletRequest.setAttribute("reloadPageManterConjuntoConta", "OK");
        	
        }
        
        return retorno;
	}
	
	
	private RetificarConjuntoContaConsumosHelper carregarRetificarConjuntoContaConsumosHelper(HttpSession sessao){
		
		RetificarConjuntoContaConsumosHelper helper = new RetificarConjuntoContaConsumosHelper();
		
		if(sessao.getAttribute("anoMes") != null){
    		helper.setAnoMes((Integer)sessao.getAttribute("anoMes"));
    	}
    	
    	if(sessao.getAttribute("anoMesFim") != null){
    		helper.setAnoMesFim((Integer)sessao.getAttribute("anoMesFim"));	
    	}
    	
    	if (sessao.getAttribute("dataVencimentoContaInicial") != null){
			
			helper.setDataVencimentoContaInicio((Date) sessao.getAttribute("dataVencimentoContaInicial"));
		}
		
		if (sessao.getAttribute("dataVencimentoContaFinal") != null){
			
			helper.setDataVencimentoContaFim((Date) sessao.getAttribute("dataVencimentoContaFinal"));
		}
		
		if (sessao.getAttribute("indicadorContaPaga") != null){
			
			helper.setIndicadorContaPaga((String) sessao.getAttribute("indicadorContaPaga"));
		}
		
		if (sessao.getAttribute("idGrupoFaturamento") != null){
			
			helper.setIdGrupoFaturamento((Integer) sessao.getAttribute("idGrupoFaturamento"));
		}
    	
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		helper.setUsuarioLogado(usuarioLogado);
		
        if(sessao.getAttribute("codigoClienteSuperior") != null){
            
        	helper.setCodigoClienteSuperior(new Integer( (String) sessao.getAttribute("codigoClienteSuperior")));
        }    		
   	
    	if(helper.getCodigoClienteSuperior() == null && sessao.getAttribute("codigoCliente") != null){
			
    		helper.setCodigoCliente(new Integer( (String) sessao.getAttribute("codigoCliente")));   
		}
    	
    	return helper;
	}
}
