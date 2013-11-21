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
package gcom.gui.cobranca.contratoparcelamento;



import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ConectorAnd;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
 */
public class FiltrarContratoParcelamentoClienteAction extends GcomAction {
	
	private HttpSession sessao;
	private Fachada fachada;
	
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

		ActionForward retorno = actionMapping.findForward("retornoPesquisa");
		sessao = httpServletRequest.getSession(false);
		fachada = Fachada.getInstancia();
		FiltrarContratoParcelamentoClienteActionForm filtrarContratoParcelamentoActionForm = (FiltrarContratoParcelamentoClienteActionForm) actionForm;
		
		String idContratoParcelamento = filtrarContratoParcelamentoActionForm.getIdContratoParcelamento();
		String idContratoAnterior = filtrarContratoParcelamentoActionForm.getIdContratoAnterior();
		String periodoNegociacaoInicial = filtrarContratoParcelamentoActionForm.getPeriodoNegociacaoInicial();
		String periodoNegociacaoFinal = filtrarContratoParcelamentoActionForm.getPeriodoNegociacaoFinal();
		String periodoImplantacaoInicial = filtrarContratoParcelamentoActionForm.getPeriodoImplantacaoInicial();
		String periodoImplantacaoFinal = filtrarContratoParcelamentoActionForm.getPeriodoImplantacaoFinal();
		String situacaoPagamento = filtrarContratoParcelamentoActionForm.getSituacaoPagamento();
		String situacaoCancelamento = filtrarContratoParcelamentoActionForm.getSituacaoCancelamento();
		String periodoCancelamentoInicial = filtrarContratoParcelamentoActionForm.getPeriodoCancelamentoInicial();
		String periodoCancelamentoFinal = filtrarContratoParcelamentoActionForm.getPeriodoCancelamentoFinal();
		String[] colecaoContratoMotivoCancelamento = filtrarContratoParcelamentoActionForm.getColecaoContratoMotivoCancelamento();
		String idClienteContrato = null;
		String idUsuarioResponsavel = null;
		
		if (filtrarContratoParcelamentoActionForm.getLoginUsuario() != null 
				 && !filtrarContratoParcelamentoActionForm.getLoginUsuario().trim().equals("")){
			 
		 	FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, 
					filtrarContratoParcelamentoActionForm.getLoginUsuario()));
			Collection colecaoUsuario = fachada.pesquisar(
					filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				idUsuarioResponsavel = usuario.getId().toString();
			} 
			
		}
		 
		 //Recupera campo AutoCompleteCliente
		 if (filtrarContratoParcelamentoActionForm.getClienteAutocomplete() != null && !"".equals(filtrarContratoParcelamentoActionForm.getClienteAutocomplete())
					&& filtrarContratoParcelamentoActionForm.getClienteAutocomplete().contains("-")){
			 idClienteContrato = filtrarContratoParcelamentoActionForm.getClienteAutocomplete().split(" - ")[0].trim();
		}
		
		
		boolean peloMenosUmParametroInformado = false;
		//FiltroContratoParcelamento filtroContratoParcelamento = new FiltroContratoParcelamento();
		FiltroContratoParcelamentoCliente filtroContratoParcelamentoCliente = new FiltroContratoParcelamentoCliente();
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.contratoAnterior");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.parcelamentoSituacao");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.usuarioResponsavel");
		
		//Validando os campos do tipo Data
		
		validarDatas(periodoNegociacaoInicial,periodoNegociacaoFinal,"Periodo de Negociação");
		validarDatas(periodoImplantacaoInicial,periodoImplantacaoFinal,"Periodo de Implantação");
		validarDatas(periodoCancelamentoInicial,periodoCancelamentoFinal,"Periodo de Cancelamento");
		
		
		//Efetuando as filtragens requeridas
		
		//Caso o número de contrato for informado, desconsiderar os demais filtros
		if(idContratoParcelamento != null && !idContratoParcelamento.equals("")){
			peloMenosUmParametroInformado = true;
			filtroContratoParcelamentoCliente.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamentoCliente.NUMERO_CONTRATO,idContratoParcelamento));
			filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.INDICADOR_CLIENTE_SUPERIOR,"1",ConectorOr.CONECTOR_OR,2));
			filtroContratoParcelamentoCliente.adicionarParametro(new ParametroNulo(FiltroContratoParcelamentoCliente.CLIENTE_SUPERIOR));
			filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.INDICADOR_CLIENTE_SUPERIOR,"1",ConectorOr.CONECTOR_OR,2));
			filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.INDICADOR_CLIENTE_SUPERIOR,"2"));
		}
		else{
			
			//Número do contrato anterior
			if(idContratoAnterior != null && !idContratoAnterior.equals("")){
				peloMenosUmParametroInformado = true;
				filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.NUMERO_ANTERIOR,idContratoAnterior));
			}
			
			//Cliente do Contrato
			filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.INDICADOR_CLIENTE_SUPERIOR,"1",ConectorOr.CONECTOR_OR,2));
			filtroContratoParcelamentoCliente.adicionarParametro(new ParametroNulo(FiltroContratoParcelamentoCliente.CLIENTE_SUPERIOR));
			filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.INDICADOR_CLIENTE_SUPERIOR,"1",ConectorOr.CONECTOR_OR,2));
			filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.INDICADOR_CLIENTE_SUPERIOR,"2"));
			if(idClienteContrato != null && !idClienteContrato.equals("")){
				peloMenosUmParametroInformado = true;
				
				filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.ID_CLIENTE,idClienteContrato));
				
				/*Collection<ContratoParcelamentoCliente> lista = fachada.pesquisar(filtroContratoParcelamentoCliente,ContratoParcelamento.class.getName());
				Collection<String> listaIDs = new ArrayList();
				
				//pegar a lista de IDs
				Iterator it = lista.iterator();
				while(it.hasNext()){
					ContratoParcelamentoCliente cpc = (ContratoParcelamentoCliente) it.next();
					listaIDs.add(cpc.getId().toString());
				}
				
				filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimplesIn(FiltroContratoParcelamento.ID,listaIDs));*/
				
				
			}
		
			//Usuário Responsável
			if(idUsuarioResponsavel != null && !idUsuarioResponsavel.equals("")){
				peloMenosUmParametroInformado = true;
				filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.USUARIO_RESPONSAVEL_ID,idUsuarioResponsavel));
			}
			
			//Período de negociação do contrato - Data inicial
			if(periodoNegociacaoInicial != null && !periodoNegociacaoInicial.equals("")){
				peloMenosUmParametroInformado = true;
				Date periodoNegociacaoInicialDate = Util.converteStringParaDate(periodoNegociacaoInicial);
				filtroContratoParcelamentoCliente.adicionarParametro(new MaiorQue(FiltroContratoParcelamentoCliente.DATA_CONTRATO,periodoNegociacaoInicialDate));
			}
			
			//Período de negociação do contrato - Data final
			if(periodoNegociacaoFinal != null && !periodoNegociacaoFinal.equals("")){
				peloMenosUmParametroInformado = true;
				Date periodoNegociacaoFinalDate = Util.converteStringParaDate(periodoNegociacaoFinal);
				filtroContratoParcelamentoCliente.adicionarParametro(new MenorQue(FiltroContratoParcelamentoCliente.DATA_CONTRATO,periodoNegociacaoFinalDate));
			}
			
			//Período de implantação do contrato - Data inicial
			if(periodoImplantacaoInicial != null && !periodoImplantacaoInicial.equals("")){
				peloMenosUmParametroInformado = true;
				Date periodoImplantacaoInicialDate = Util.converteStringParaDate(periodoImplantacaoInicial);
				Timestamp timeStampPeriodoImplantacaoInicialDate = new Timestamp(periodoImplantacaoInicialDate.getTime());
				filtroContratoParcelamentoCliente.adicionarParametro(new MaiorQue(FiltroContratoParcelamentoCliente.DATA_IMPLANTACAO,timeStampPeriodoImplantacaoInicialDate));
			}
			
			//Período de implantação do contrato - Data FINAL
			if(periodoImplantacaoFinal != null && !periodoImplantacaoFinal.equals("")){
				peloMenosUmParametroInformado = true;
				Date periodoImplantacaoFinalDate = Util.converteStringParaDate(periodoImplantacaoFinal);
				
				//Setando o timestamp para 23:59:59:59
				Calendar cal = Calendar.getInstance();
				cal.setTime(periodoImplantacaoFinalDate);
				cal.set(Calendar.HOUR_OF_DAY, 23);          
				cal.set(Calendar.MINUTE, 59);                 
				cal.set(Calendar.SECOND, 59);                
				cal.set(Calendar.MILLISECOND, 59);
				Timestamp timeStampPeriodoImplantacaoFinalDate = new Timestamp(cal.getTime().getTime());
				filtroContratoParcelamentoCliente.adicionarParametro(new MenorQue(FiltroContratoParcelamentoCliente.DATA_IMPLANTACAO,timeStampPeriodoImplantacaoFinalDate));
			}
			
			//Situação de pagamento do contrato
			if(situacaoPagamento != null && !situacaoPagamento.equals("")){
				int situacaoP = new Integer(situacaoPagamento).intValue();

				switch(situacaoP){
					//Pendentes
					case 1:
						peloMenosUmParametroInformado = true;
						filtroContratoParcelamentoCliente.adicionarParametro(new ParametroNaoNulo(FiltroContratoParcelamentoCliente.VALOR_A_COBRAR,ConectorAnd.CONECTOR_AND,2));
						filtroContratoParcelamentoCliente.adicionarParametro(new MaiorQue(FiltroContratoParcelamentoCliente.VALOR_A_COBRAR,0));
						break;
					//Pagos
					case 2:
						peloMenosUmParametroInformado = true;
						filtroContratoParcelamentoCliente.adicionarParametro(new ParametroNaoNulo(FiltroContratoParcelamentoCliente.VALOR_A_COBRAR,ConectorAnd.CONECTOR_AND,2));
						filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.VALOR_A_COBRAR,0));
						break;
				}
			}
			
			//Situação de cancelamento do contrato
			if(situacaoCancelamento != null && !situacaoCancelamento.equals("")){
				int situacaoC = new Integer(situacaoCancelamento).intValue();
				
				switch(situacaoC){
					//Não Cancelados
					case 1:
						peloMenosUmParametroInformado = true;
						filtroContratoParcelamentoCliente.adicionarParametro(new ParametroNulo(FiltroContratoParcelamentoCliente.MOTIVO_DESFAZER));
						break;
					//Cancelados
					case 2:
						peloMenosUmParametroInformado = true;
						filtroContratoParcelamentoCliente.adicionarParametro(new ParametroNaoNulo(FiltroContratoParcelamentoCliente.MOTIVO_DESFAZER));
						break;
				}
				
			}
			
			//Período de cancelamento do contrato - Data inicial
			if(periodoCancelamentoInicial != null && !periodoCancelamentoInicial.equals("")){
				peloMenosUmParametroInformado = true;
				Date periodoCancelamentoInicialDate = Util.converteStringParaDate(periodoCancelamentoInicial);
				Timestamp timeStampPeriodoCancelamentoInicialDate = new Timestamp(periodoCancelamentoInicialDate.getTime());
				filtroContratoParcelamentoCliente.adicionarParametro(new MaiorQue(FiltroContratoParcelamentoCliente.DATA_CANCELAMENTO,timeStampPeriodoCancelamentoInicialDate));
			}
			
			//Período de cancelamento do contrato - Data final
			if(periodoCancelamentoFinal != null && !periodoCancelamentoFinal.equals("")){
				peloMenosUmParametroInformado = true;
				Date periodoCancelamentoFinalDate = Util.converteStringParaDate(periodoCancelamentoFinal);
				
				//Setando o timestamp para 23:59:59:59
				Calendar cal = Calendar.getInstance();
				cal.setTime(periodoCancelamentoFinalDate);
				cal.set(Calendar.HOUR_OF_DAY, 23);          
				cal.set(Calendar.MINUTE, 59);                 
				cal.set(Calendar.SECOND, 59);                
				cal.set(Calendar.MILLISECOND, 59);
				Timestamp timeStampPeriodoCancelamentoFinalDate = new Timestamp(cal.getTime().getTime());
				filtroContratoParcelamentoCliente.adicionarParametro(new MenorQue(FiltroContratoParcelamentoCliente.DATA_CANCELAMENTO,timeStampPeriodoCancelamentoFinalDate));
			}
			
			
			//Motivo cancelamento do contrato
			if(colecaoContratoMotivoCancelamento != null && colecaoContratoMotivoCancelamento.length != 0){
				peloMenosUmParametroInformado = true;
				for(int i = 0; i < colecaoContratoMotivoCancelamento.length; i++){
					if(i == 0)
						filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.MOTIVO_DESFAZER,colecaoContratoMotivoCancelamento[i],ConectorOr.CONECTOR_OR,colecaoContratoMotivoCancelamento.length));
					else
						filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.MOTIVO_DESFAZER,colecaoContratoMotivoCancelamento[i]));
				}
				
			}

		}
		
		//Ordem
		filtroContratoParcelamentoCliente.setCampoOrderBy(FiltroContratoParcelamentoCliente.ID_CLIENTE+" ASC",FiltroContratoParcelamentoCliente.DATA_CONTRATO+" DESC");
		
		
		//Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		//Pesquisa dos resultados
		Collection<ContratoParcelamentoCliente> collectionContratoParcelamentoCliente = fachada.pesquisar(filtroContratoParcelamentoCliente,ContratoParcelamentoCliente.class.getName());
		
		
		//Validações do resultado
		
		//Nenhum registro encontrado
		if (collectionContratoParcelamentoCliente == null || collectionContratoParcelamentoCliente.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "contrato de parcelamento");
		}
		
		//Muitos registros encontrados
		if (collectionContratoParcelamentoCliente.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// 
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} 
		
		// Coloca a coleção na sessão
		sessao.setAttribute("filtroContratoParcelamentoCliente", filtroContratoParcelamentoCliente);
		
		//Pegando os respectivos clientes
		
		/*Iterator iterator = collectionContratoParcelamento.iterator();
		Collection<ContratoParcelamentoCliente> listaContratosParcelamentoCliente = new ArrayList<ContratoParcelamentoCliente>();
		FiltroContratoParcelamentoCliente filtroCPCliente = new FiltroContratoParcelamentoCliente();
		while(iterator.hasNext()){
			ContratoParcelamento cp = (ContratoParcelamento) iterator.next();
			filtroCPCliente.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.ID_CONTRATO,cp.getId()));
			ArrayList<ContratoParcelamentoCliente> listaContratos = new ArrayList<ContratoParcelamentoCliente>(fachada.pesquisar(filtroCPCliente,ContratoParcelamentoCliente.class.getName()));
			if(listaContratos.size() == 1)
				listaContratosParcelamentoCliente.add(listaContratos.get(0));
			else{
				for(int i = 0;i < listaContratos.size();i++){
					if(listaContratos.get(i).getIndicadorClienteSuperior().toString().equals("1"))
						listaContratosParcelamentoCliente.add(listaContratos.get(i));
				}
			}
		}
		
		sessao.setAttribute("collectionContratoParcelamentoCliente", listaContratosParcelamentoCliente);*/
		
		return retorno;
	}
	
	private void validarDatas(String dataInicial, String dataFinal, String campo){

		if (dataInicial != null && !"".equals(dataInicial)) {
			
			Date data = Util.converteStringParaDate(dataInicial);
			
			if (data == null) {
				throw new ActionServletException("atencao.data.inicial.invalida");
			}
		}
		
		if (dataInicial != null && !"".equals(dataFinal)) {
			
			Date data = Util.converteStringParaDate(dataFinal);
			
			if (data == null) {
				throw new ActionServletException("atencao.data.final.invalida");
			}
		}
		
		if (dataInicial != null && !"".equals(dataInicial) && dataInicial != null && !"".equals(dataFinal)) {
			
			Date dataInicialDate = Util.converteStringParaDate(dataInicial);
			Date dataFinalDate = Util.converteStringParaDate(dataFinal);

			if (dataInicialDate.getTime() > dataFinalDate.getTime()) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido", null, Util
								.formatarData(new Date()));
			}
		}
		
	}

}
