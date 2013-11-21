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
* Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.spcserasa.FiltroNegativadorContrato;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o contrato do negativador
 * 
 * 
 * @author Yara Taciane de Souza
 * @date 18/12/2007
 */
public class InserirContratoNegativadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirContratoNegativadorActionForm inserirContratoNegativadorActionForm = (InserirContratoNegativadorActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
			
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_CONTRATO_NEGATIVADOR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_CONTRATO_NEGATIVADOR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
			
	
		Fachada fachada = Fachada.getInstancia();
		Integer idNegativadorContrato = null;
		Collection collNegativadorContrato = null;
		
		
		// cria o objeto negativador contrato para ser inserido
		NegativadorContrato negativadorContrato = new NegativadorContrato();
		
		
		if (inserirContratoNegativadorActionForm.getIdNegativador() != null	&& !inserirContratoNegativadorActionForm.getIdNegativador().equals("")) {			
			Negativador negativador = new Negativador();
			negativador.setId(new Integer(inserirContratoNegativadorActionForm.getIdNegativador()));			
			negativadorContrato.setNegativador(negativador);
			
		} else {
			throw new ActionServletException("atencao.required", null,"Negativador");
		}
			
		String idNegativador = (String)inserirContratoNegativadorActionForm.getIdNegativador();
		
		if (inserirContratoNegativadorActionForm.getDescricaoEmailEnvioArquivo() != null
				&& !inserirContratoNegativadorActionForm.getDescricaoEmailEnvioArquivo().equals("")) {			
			String descricaoEmailEnvioArquivo = inserirContratoNegativadorActionForm.getDescricaoEmailEnvioArquivo();			
			negativadorContrato.setDescricaoEmailEnvioArquivo(descricaoEmailEnvioArquivo);		
		
		} 
		
		if (inserirContratoNegativadorActionForm.getCodigoConvenio() != null
				&& !inserirContratoNegativadorActionForm.getCodigoConvenio().equals("")) {		
			
			String codigoConvenio = inserirContratoNegativadorActionForm.getCodigoConvenio();			
			negativadorContrato.setCodigoConvenio(codigoConvenio);		
		
		}
		
		if (inserirContratoNegativadorActionForm.getValorContrato() != null
				&& !inserirContratoNegativadorActionForm.getValorContrato()
						.equals("")) {
			
			BigDecimal valorContrato = new BigDecimal(
					inserirContratoNegativadorActionForm.getValorContrato().replace(".", "").replace(",", "."));
			negativadorContrato.setValorContrato(valorContrato);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Valor do Contrato");
		}
		
		if (inserirContratoNegativadorActionForm.getValorTarifaInclusao() != null
				&& !inserirContratoNegativadorActionForm.getValorTarifaInclusao()
						.equals("")) {
			
			BigDecimal valorTarifaInclusao = new BigDecimal(
					inserirContratoNegativadorActionForm.getValorTarifaInclusao().replace(".", "").replace(",", "."));
			negativadorContrato.setValorTarifaInclusao(valorTarifaInclusao);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Valor do Tarifa para Inclusão");
		}
		
		if (inserirContratoNegativadorActionForm.getNumeroPrazoInclusao() != null
				&& !inserirContratoNegativadorActionForm.getNumeroPrazoInclusao()
						.equals("")) {
			
			short numeroPrazoInclusao = Short.parseShort(inserirContratoNegativadorActionForm.getNumeroPrazoInclusao());
			
			negativadorContrato.setNumeroPrazoInclusao(numeroPrazoInclusao);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Prazo Para Negativação");
		}
		
		
		
		// Data Fim anterior a Data Início

		Date dataContratoInicio = null;
		if (inserirContratoNegativadorActionForm.getDataContratoInicio() != null
				&& !inserirContratoNegativadorActionForm.getDataContratoInicio().equals("")) {
			
			String dataContrato = inserirContratoNegativadorActionForm.getDataContratoInicio();
			if (Util.validarDiaMesAno(dataContrato)) {
				throw new ActionServletException(
						"atencao.data.inicio.Contrato.invalida");
			}

			dataContratoInicio = Util.converteStringParaDate(dataContrato);
			
			Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
			
			if(Util.compararData(dataContratoInicio, dataAtualSemHora) == - 1){
				String dataAtual = Util.formatarData(new Date());
				throw new ActionServletException(
						"atencao.data.inicio.nao.superior.data.corrente", null,
						dataAtual);				
				
			}			
			
			negativadorContrato.setDataContratoInicio(dataContratoInicio);
			
		} else {
			throw new ActionServletException("atencao.required", null,
					"Data de Início do Contrato");
		}
		
		
		Date dataContratoFim = null;
		if (inserirContratoNegativadorActionForm.getDataContratoFim() != null
				&& !inserirContratoNegativadorActionForm.getDataContratoFim().equals("")) {
			
			String dataContrato = inserirContratoNegativadorActionForm.getDataContratoFim();
			if (Util.validarDiaMesAno(dataContrato)) {
				throw new ActionServletException(
						"atencao.data.fim.Contrato.invalida");
			}

			dataContratoFim = Util.converteStringParaDate(dataContrato);
			
			//Se data inicio maior que data fim
			if(Util.compararData(dataContratoInicio, dataContratoFim) == 1){
				String dataInicio = Util.formatarData(dataContratoInicio);
				String dataFim = Util.formatarData(dataContratoFim);
				throw new ActionServletException(
						"atencao.data_inicial_maior_data_final",dataInicio,dataFim);
			}
			
			negativadorContrato.setDataContratoFim(dataContratoFim);	
			
		} else {
			throw new ActionServletException("atencao.required", null,
					"Data do Fim do Contrato");
		}
			
		
		
	    FiltroNegativadorContrato filtroNegativadorContrato = new FiltroNegativadorContrato();
		
	    //Verificar existência do número do contrato		
		if (inserirContratoNegativadorActionForm.getNumeroContrato() != null
				&& !inserirContratoNegativadorActionForm.getNumeroContrato().equals("")) {		
			
			String numeroContrato = inserirContratoNegativadorActionForm.getNumeroContrato();	
			
			filtroNegativadorContrato.limparListaParametros();			
			filtroNegativadorContrato.adicionarParametro(new ParametroSimples(FiltroNegativadorContrato.NEGATIVADOR_ID, idNegativador));
			filtroNegativadorContrato.adicionarParametro(new ParametroSimples(FiltroNegativadorContrato.NUMERO_CONTRATO, numeroContrato));				
		
			collNegativadorContrato = null;
			
			collNegativadorContrato = fachada.pesquisar(filtroNegativadorContrato,
					NegativadorContrato.class.getName());			
			
			if(collNegativadorContrato != null && !collNegativadorContrato.isEmpty()){
				
				throw new ActionServletException("atencao.negativador_associado_numero_contrato");				
				
			}
			negativadorContrato.setNumeroContrato(numeroContrato);		
		
		} else {
			throw new ActionServletException("atencao.required", null,
					"numero  do contrato");
		}
		
		
		
		 // Verificar existência de contrato vigente para o negativador		
		 filtroNegativadorContrato.limparListaParametros();
         filtroNegativadorContrato.adicionarParametro(new ParametroSimples(FiltroNegativadorContrato.NEGATIVADOR_ID,idNegativador));
         collNegativadorContrato = fachada.pesquisar(filtroNegativadorContrato, NegativadorContrato.class.getName());
	     NegativadorContrato negativadorContratoBanco = (NegativadorContrato) Util.retonarObjetoDeColecao(collNegativadorContrato);		
		
		if(negativadorContratoBanco != null){
			Date dataEncerramento = negativadorContratoBanco.getDataContratoEncerramento();
			Date dataFimContrato = negativadorContratoBanco.getDataContratoFim();			
			Date dataCorrente = Util.formatarDataSemHora(new Date());
			
			if((Util.compararData(dataFimContrato, dataCorrente) == 0 || Util.compararData(dataFimContrato, dataCorrente) == 1) && dataEncerramento == null ){
				//Existe um contrato em vigencia
				throw new ActionServletException("atencao.contrato_negativador_vigente");
			}
		}			
		
			
		//------------ REGISTRAR TRANSAÇÃO ----------------
		negativadorContrato.setOperacaoEfetuada(operacaoEfetuada);
		negativadorContrato.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorContrato);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		if (negativadorContrato != null) {			
			
			negativadorContrato.setUltimaAlteracao(new Date());
		
			
			idNegativadorContrato = (Integer) fachada.inserir(negativadorContrato);
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}
		

		montarPaginaSucesso(httpServletRequest, "Contrato do Negativador "
				+ idNegativadorContrato + " inserido com sucesso.",
				"Inserir outro Contrato do Negativador",
				"exibirInserirContratoNegativadorAction.do?menu=sim",
				"exibirAtualizarContratoNegativadorAction.do?idRegistroAtualizacao="
				+ idNegativadorContrato, "Atualizar Contrato do Negativador Inserido");
		
				
			
		return retorno;
	}

}
