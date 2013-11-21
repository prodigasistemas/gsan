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
package gcom.gui.faturamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *  AdicionarFaturaClienteResponsavelContaPopupAction
 * 
 * @author Flávio Leonardo
 * @created 25/04/2006
 */
public class AdicionarFaturaClienteResponsavelContaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAdicionar");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Instância do formulário que está sendo utilizado
		FiltrarFaturaClienteResponsavelActionForm form = (FiltrarFaturaClienteResponsavelActionForm) actionForm;
		
		String mesAnoReferencia = form.getMesAno();
		String idImovel = form.getImovelId();

		//verificar existencia do imovel
		Imovel imovelEncontrado = this.pesquisarImovel(idImovel);
		if(imovelEncontrado == null){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		ContaGeral contaGeral = new ContaGeral();
		
		//[FS0006] Verificar existencia de conta(conta ou conta_historico)
		Conta contaEncontrada = pesquisarConta(idImovel, mesAnoReferencia);
		Integer numeroConsumo = new Integer("0");
		
		BigDecimal valorConta = BigDecimal.ZERO;
		if(contaEncontrada != null){
			contaGeral.setId(contaEncontrada.getId());
			contaGeral.setConta(contaEncontrada);
			valorConta = contaEncontrada.getValorTotal();
			
			if(contaEncontrada.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.CORTADO)
					|| contaEncontrada.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.LIGADO)){
				numeroConsumo = contaEncontrada.getConsumoAgua();
			}else{
				numeroConsumo = contaEncontrada.getConsumoEsgoto();
			}
			
			verificarSituacaoConta(contaEncontrada);
		}else{
			ContaHistorico contaHistoricoEncontrada = pesquisarContaHistorico(idImovel, mesAnoReferencia);
			if(contaHistoricoEncontrada != null){
				contaGeral.setId(contaHistoricoEncontrada.getId());
				contaGeral.setContaHistorico(contaHistoricoEncontrada);
				valorConta = contaHistoricoEncontrada.getValorTotal();
				
				if(contaHistoricoEncontrada.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.CORTADO)
						|| contaHistoricoEncontrada.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.LIGADO)){
					numeroConsumo = contaHistoricoEncontrada.getConsumoAgua();
				}else{
					numeroConsumo = contaHistoricoEncontrada.getConsumoEsgoto();
				}
				
				verificarSituacaoConta(contaHistoricoEncontrada);
			}else{
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
		}
		
		FaturaItem faturaItem = new FaturaItem();

		faturaItem.setImovel(imovelEncontrado);
		
		verificarClienteDaFatura(imovelEncontrado, form);
		
		faturaItem.setValorConta(valorConta);
		
		faturaItem.setContaGeral(contaGeral);
		
		faturaItem.setImovel(imovelEncontrado);
		
		faturaItem.setNumeroConsumo(numeroConsumo);
		
		Collection<FaturaItem> colecaoFaturasItem = (Collection)sessao.getAttribute("colecaoFaturaItem");
		
		Fatura fatura = null;
		
		if(!colecaoFaturasItem.isEmpty()){
			Iterator iterator = colecaoFaturasItem.iterator();
			while(iterator.hasNext()){
				FaturaItem faturaItem2 = (FaturaItem)iterator.next();
				fatura = faturaItem2.getFatura();
				if(faturaItem2.getImovel().getId().equals(faturaItem.getImovel().getId())){
					throw new ActionServletException("atencao.fatura.cliente.responsavel.ja.existe.cota");
				}
			}
		}
		faturaItem.setFatura(fatura);
		colecaoFaturasItem.add(faturaItem);
		
		sessao.setAttribute("colecaoFaturaItem", colecaoFaturasItem);
		
		form.setImovelId("");
		form.setInscricao("");
		
		httpServletRequest.setAttribute("reloadPage",1);
		
		return retorno;
	}
	
	private Imovel pesquisarImovel(String idImovel){
		
		//Cria a variável que vai armazenar o cliente pesquisado
		Imovel imovelEncontrado = null;
		
		//Cria uma instância da fachada 
		Fachada fachada = Fachada.getInstancia();
		
		//Pesquisa o cliente informado pelo usuário no sistema 
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
		Collection<Imovel> colecaoImovel =  fachada.pesquisar(filtroImovel, Imovel.class.getName());
		
		//Caso exista o cliente no sistema 
		//Retorna para o usuário o cliente retornado pela pesquisa
		//Caso contrário retorna um objeto nulo 
		if(colecaoImovel != null && !colecaoImovel.isEmpty()){
			imovelEncontrado =(Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
		}
		
		//Retorna o cliente encontrado ou nulo se não existir 
		return imovelEncontrado;
	}
	
	private Conta pesquisarConta(String idImovel, String mesAnoReferencia){
		Conta contaEncontrada = null;
		
		FiltroConta filtroConta = new FiltroConta();
		
		filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID, idImovel));
		filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.REFERENCIA, 
				Util.formatarMesAnoComBarraParaAnoMes(mesAnoReferencia)));
		filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel");
		
		Collection colecaoConta = Fachada.getInstancia().pesquisar(filtroConta, Conta.class.getName());
		
		if(!colecaoConta.isEmpty()){
			contaEncontrada = (Conta)colecaoConta.iterator().next();
		}
		
		return contaEncontrada;
	}
	
	private ContaHistorico pesquisarContaHistorico(String idImovel, String mesAnoReferencia){
		ContaHistorico contaEncontrada = null;
		
		FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();
		
		filtroContaHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.IMOVEL_ID, idImovel));
		filtroContaHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.ANO_MES_REFERENCIA, 
				Util.formatarMesAnoComBarraParaAnoMes(mesAnoReferencia)));
		filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
		
		Collection colecaoContaHistorico = Fachada.getInstancia().pesquisar(filtroContaHistorico, ContaHistorico.class.getName());
		
		if(!colecaoContaHistorico.isEmpty()){
			contaEncontrada = (ContaHistorico)colecaoContaHistorico.iterator().next();
		}
		
		return contaEncontrada;
	}
	
	private void verificarSituacaoConta(Conta conta){
		
		if(!conta.getDebitoCreditoSituacaoAtual().getId().equals(new Integer(0))
				&& !conta.getDebitoCreditoSituacaoAtual().getId().equals(new Integer(1))
				&& !conta.getDebitoCreditoSituacaoAtual().getId().equals(new Integer(2))){
			
			FiltroDebitoCreditoSituacao filtro = new FiltroDebitoCreditoSituacao();
			filtro.adicionarParametro(new ParametroSimples(FiltroDebitoCreditoSituacao.ID, 
					conta.getDebitoCreditoSituacaoAtual().getId()));
			
			Collection colecao = Fachada.getInstancia().pesquisar(filtro, DebitoCreditoSituacao.class.getName());
			
			DebitoCreditoSituacao debitoCreditoSituacao = (DebitoCreditoSituacao)Util.retonarObjetoDeColecao(colecao);
			
			throw new ActionServletException("atencao.fatura.cliente.responsavel.situacao",null,debitoCreditoSituacao.getDescricaoAbreviada());
			
		}
			
	}

	private void verificarSituacaoConta(ContaHistorico contaHistorico){
		
		if(!contaHistorico.getDebitoCreditoSituacaoAtual().getId().equals(new Integer(0))
				&& !contaHistorico.getDebitoCreditoSituacaoAtual().getId().equals(new Integer(1))
				&& !contaHistorico.getDebitoCreditoSituacaoAtual().getId().equals(new Integer(2))){
			
			FiltroDebitoCreditoSituacao filtro = new FiltroDebitoCreditoSituacao();
			filtro.adicionarParametro(new ParametroSimples(FiltroDebitoCreditoSituacao.ID, 
					contaHistorico.getDebitoCreditoSituacaoAtual().getId()));
			
			Collection colecao = Fachada.getInstancia().pesquisar(filtro, DebitoCreditoSituacao.class.getName());
			
			DebitoCreditoSituacao debitoCreditoSituacao = (DebitoCreditoSituacao)Util.retonarObjetoDeColecao(colecao);
			
			throw new ActionServletException("atencao.fatura.cliente.responsavel.situacao",null,debitoCreditoSituacao.getDescricaoAbreviada());
			
		}
		
	}
	
	private void verificarClienteDaFatura(Imovel imovel, FiltrarFaturaClienteResponsavelActionForm form){
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, form.getClienteId()));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.RESPONSAVEL));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovel.getId()));
		
		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		
		if(colecaoClienteImovel.isEmpty()){
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getClienteId()));
			Collection colCliente = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colCliente);
			throw new ActionServletException("atencao.fatura.cliente.nao.tem.ligacao.imovel", null,cliente.getNome());
		}
		
	}
}
