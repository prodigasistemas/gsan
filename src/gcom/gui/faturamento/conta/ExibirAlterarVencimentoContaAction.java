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

import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


public class ExibirAlterarVencimentoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAlterarVencimentoConta");
        
        //Instância do formulário que está sendo utilizado
        AlterarVencimentoContaActionForm alterarVencimentoContaActionForm = (AlterarVencimentoContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Carregando o identificador das contas selecionadas
        String contaSelected = httpServletRequest.getParameter("conta");
        
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		

    	//FiltroConta filtroConta = new FiltroConta();
    	//filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel.id");
    	//String idConta  = contaSelected.split("-")[0];
		// filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL_ID);
		// filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, idConta));
    
		// Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName());
    	
    	//Conta contaSelecao = fachada.pesquisarContaRetificacao(new Integer(idConta));
    	
    	String idImovel = httpServletRequest.getParameter("idImovel");
    	
		/*
		 * Colocado por Ana Maria em 22/04/2009				
		 */
		if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
			Boolean imovelBloqueado = fachada.verificarImovelPerfilBloqueado(new Integer(httpServletRequest.getParameter("idImovel")));
			if(imovelBloqueado){						
                throw new ActionServletException(
                        "atencao.perfil_imovel_nao_permite_operacao");
			}
		}
    	
    	//Conta contaSelecao = (Conta) colecaoConta.iterator().next();
    	
    	Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
    	
    	//Contas selecionadas pelo usuário
        String[] arrayIdentificadores = contaSelected.split(",");
        Collection idsConta = new ArrayList();
        
    	for (int i = 0; i < arrayIdentificadores.length; i++) {
    		String dadosConta = arrayIdentificadores[i];
			String[] idContaArray = dadosConta.split("-");
			Integer idConta = new Integer (idContaArray[0]);
			idsConta.add(idConta);
    	}	
        
    	// Alterado por: Hugo Leonardo 
    	// Data: 10/08/2010
    	// Analista: Aryed Lins
    	
    	//E o usuário não tenha permissão especial.	
		boolean temPermissaoParaAlterarVencimentoJaAlterado = 
			fachada.verificarPermissaoEspecial(PermissaoEspecial.ALTERAR_VENCIMENTO_JA_ALTERADO,
				usuario);	
		
		if(!temPermissaoParaAlterarVencimentoJaAlterado 
				&& sistemaParametro.getIndicadorLimiteAlteracaoVencimento().equals(ConstantesSistema.SIM)){
			
			fachada.verificarQuantidadeAlteracoesVencimentoConta(idsConta);
		}
    	
    	//[FS0018] Verificar ocorrência de conta(s) sem revisão ou em revisão por ação do usuário
		//Vivianne Sousa 11/05/2007
        Collection contasRevisaoAcaoUsuario = fachada.obterContasNaoEmRevisaoOuEmRevisaoPorAcaoUsuario(idsConta);
        
        if (contasRevisaoAcaoUsuario != null && !contasRevisaoAcaoUsuario.isEmpty()){
        	
    		// -----------------------------------------------------------
    		// Verificar permissão especial
    		boolean temPermissaoAlterarVencimentoSemRa = fachada.verificarPermissaoAlterarVencimentoSemRa(usuario);
    		// -----------------------------------------------------------
    		if(!temPermissaoAlterarVencimentoSemRa){
              //[FS0001] - Verificar Existência de RA
              fachada.verificarExistenciaRegistroAtendimento(
            		  new Integer(idImovel), "atencao.conta_existencia_registro_atendimento",
            		  EspecificacaoTipoValidacao.ALTERACAO_CONTA); 
    		}
        }
        
        //Carregar a data corrente do sistema
        //====================================
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        //Ultimo dia do mês corrente.        
        Date ultimaDataMes = Util.obterUltimaDataMes(Util.getMes(dataCorrente.getTime()), Util.getAno(dataCorrente.getTime()));        
        httpServletRequest.setAttribute("ultimaDataMes", formatoData.format(ultimaDataMes));
       
    	httpServletRequest.setAttribute("indicadorCalculaVencimento", sistemaParametro.getIndicadorCalculaVencimento().toString());
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData
        .format(dataCorrente.getTime()));
        
        //Data Corrente + 60 dias
        dataCorrente.add(Calendar.DATE, 60);
        httpServletRequest.setAttribute("dataAtual60", formatoData
        .format(dataCorrente.getTime()));
        
        
    	//-------------------------------------------------------------------------------------------
		// Alterado por :  Yara Taciane  - data : 17/07/2008 
		// Analista :  Denys Guimarães
		//-------------------------------------------------------------------------------------------	
        if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
        	Date dtCorrente = new Date();
        	
        	Integer diasAdicionais = 0;
        	
        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
        	}
        	
			Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dtCorrente, diasAdicionais.intValue());
        	alterarVencimentoContaActionForm.setDataVencimento(Util.formatarData(dataCorrenteComDias));
        }	
        //--------------------------------------------------------------------------------------------
        
        
        alterarVencimentoContaActionForm.setContaSelected(contaSelected);
        
        return retorno;
    }

}

