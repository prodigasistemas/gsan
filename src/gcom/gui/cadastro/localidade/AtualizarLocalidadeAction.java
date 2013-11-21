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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.LocalidadeClasse;
import gcom.cadastro.localidade.LocalidadePorte;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para a atualização do logradouro
 * 
 * @author Sávio Luiz
* @date   30/06/2006 
 */

public class AtualizarLocalidadeAction extends GcomAction {

    //Obtém a instância da fachada
    Fachada fachada = Fachada.getInstancia();

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        AtualizarLocalidadeActionForm atualizarLocalidadeActionForm = (AtualizarLocalidadeActionForm) actionForm;
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        //------------ REGISTRAR TRANSAÇÃO ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LOCALIDADE_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_LOCALIDADE_ATUALIZAR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------

        String localidadeID = atualizarLocalidadeActionForm
                .getLocalidadeID();
        String localidadeNome = atualizarLocalidadeActionForm
                .getLocalidadeNome();
        Collection colecaoEnderecos = (Collection) sessao
                .getAttribute("colecaoEnderecos");
        String telefone = atualizarLocalidadeActionForm.getTelefone();
        String ramal = atualizarLocalidadeActionForm.getRamal();
        String fax = atualizarLocalidadeActionForm.getFax();
        String email = atualizarLocalidadeActionForm.getEmail();
        String icms = atualizarLocalidadeActionForm.getIcms();
        String centroCusto = atualizarLocalidadeActionForm.getCentroCusto();
        String informatizada = atualizarLocalidadeActionForm.getInformatizada();
        String gerenteLocalidade = atualizarLocalidadeActionForm.getGerenteLocalidade();
        String sede = atualizarLocalidadeActionForm.getSede();
        
        String menorConsumo = atualizarLocalidadeActionForm
                .getMenorConsumo();
        String eloID = atualizarLocalidadeActionForm.getEloID();
        //String gerenciaID = atualizarLocalidadeActionForm.getGerenciaID();
        String idUnidadeNegocio = atualizarLocalidadeActionForm.getIdUnidadeNegocio();        
        String classeID = atualizarLocalidadeActionForm.getClasseID();
        String porteID = atualizarLocalidadeActionForm.getPorteID();
        String indicadorUso = atualizarLocalidadeActionForm.getIndicadorUso();
        String hidrometroLocalArmazenagem = atualizarLocalidadeActionForm.getHidrometroLocalArmazenagem();
        String centroCustoEsgoto = atualizarLocalidadeActionForm.getCentroCustoEsgoto();
        String municipio = atualizarLocalidadeActionForm.getMunicipio();
        Localidade localidadeAlterar = (Localidade) sessao.getAttribute("localidadeManter");
        Collection colecaoPesquisa = null;

        //O id da localidade é obrigatório.
        if (localidadeID == null || localidadeID.equalsIgnoreCase("")) {
            throw new ActionServletException(
            		"atencao.required",null,"Código");
        }

        //O nome da localidade é obrigatório.
        if (localidadeNome == null || localidadeNome.equalsIgnoreCase("")) {
            throw new ActionServletException(
            		"atencao.required",null,"Nome");
        }

        //O endereço da localidade é obrigatório.
        /*if (colecaoEnderecos == null || colecaoEnderecos.isEmpty()) {
            throw new ActionServletException(
                    "atencao.endereco_localidade_nao_informado");
        } else {
            localidadeAlterar = (Localidade) Util
                    .retonarObjetoDeColecao(colecaoEnderecos);
            localidadeAlterar.setId(new Integer(localidadeID));
            localidadeAlterar.setDescricao(localidadeNome);
        }*/
        
        if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
        	localidadeAlterar = (Localidade) Util
            .retonarObjetoDeColecao(colecaoEnderecos);
        }
        
        localidadeAlterar.setId(new Integer(localidadeID));
        localidadeAlterar.setDescricao(localidadeNome);

        //O telefone é obrigatório caso o ramal tenha sido informado.
        if (ramal != null && !ramal.equalsIgnoreCase("")) {
            if (telefone == null || telefone.equalsIgnoreCase("")) {
                throw new ActionServletException(
                        "atencao.telefone_localidade_nao_informado");
            }
            else if (telefone.length() < 7){
            	throw new ActionServletException(
                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Telefone");
            }
        }
        localidadeAlterar.setRamalfone(ramal);
        
        //indicador bloqueio
        localidadeAlterar.setIndicadorBloqueio(new Short(atualizarLocalidadeActionForm.getIndicadorBloqueio()));
        
        //Telefone.
        if (telefone != null && !telefone.equalsIgnoreCase("")) {
        	if (telefone.length() < 7){
        		throw new ActionServletException(
                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Telefone");
        	}
        }
        localidadeAlterar.setFone(telefone);
        
        //Fax.
        if (fax != null && !fax.equalsIgnoreCase("")) {
        	if (fax.length() < 7){
        		throw new ActionServletException(
                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Fax");
        	}
        }
        localidadeAlterar.setFax(fax);

        //E-mail.
        //if (email != null && !email.equalsIgnoreCase("")) {
            localidadeAlterar.setEmail(email);
        //}

        //Menor Consumo.
            
          
        if (menorConsumo != null && !menorConsumo.equalsIgnoreCase("")) {
            localidadeAlterar.setConsumoGrandeUsuario(Integer
                    .parseInt(menorConsumo));
        }else{
        	 localidadeAlterar.setConsumoGrandeUsuario(Integer
                     .parseInt("0"));
        }

        //ICMS
        if (icms != null && !icms.equalsIgnoreCase("")) {
            localidadeAlterar.setCodigoICMS(Integer
                    .parseInt(icms));
        }
        
        //Centro de Custo
        if (centroCusto != null && !centroCusto.equalsIgnoreCase("")) {
            localidadeAlterar.setCodigoCentroCusto(centroCusto);
        }
        
        //Centro de Custo Esgoto
        if (centroCustoEsgoto != null && !centroCustoEsgoto.equalsIgnoreCase("")) {
            localidadeAlterar.setCodigoCentroCustoEsgoto(centroCustoEsgoto);
        }
        
        //Elo.
        Localidade localidadeElo = new Localidade();
        if (eloID != null && !eloID.equalsIgnoreCase("") && !eloID.equalsIgnoreCase("-1")) {

            FiltroLocalidade filtroLocalidadeElo = new FiltroLocalidade();

            filtroLocalidadeElo.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, eloID));

            if (!eloID.equalsIgnoreCase(localidadeID)) {
                filtroLocalidadeElo.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
            }

            //Retorna localidade - Elo
            colecaoPesquisa = fachada.pesquisar(filtroLocalidadeElo,
                    Localidade.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //O código do Elo não existe na tabela Localidade
                throw new ActionServletException(
                        "atencao.pesquisa_elo_nao_inexistente");
            }

            localidadeElo = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);

			if (localidadeElo.getId().intValue() != localidadeElo
			        .getLocalidade().getId().intValue()) {
			    //A localidade escolhida não é um Elo
			    throw new ActionServletException(
			            "atencao.localidade_nao_e_elo");
			}
			localidadeAlterar.setLocalidade(localidadeElo);
        }
        else{
        	localidadeElo.setId(new Integer(localidadeID));
        	localidadeAlterar.setLocalidade(localidadeElo);
        }

        //Gerência Regional
        if (idUnidadeNegocio == null 
        		|| idUnidadeNegocio.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	//Informe Gerência Regional
        	throw new ActionServletException("atencao.required",null,"Unidade Negócio");
        }
		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
		unidadeNegocio.setId(new Integer(idUnidadeNegocio));
		localidadeAlterar.setUnidadeNegocio(unidadeNegocio);

        //Classe
        if (classeID == null 
        		|| classeID.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	//Informe Classe
        	throw new ActionServletException("atencao.required",null,"Classe");
        }
		LocalidadeClasse classe = new LocalidadeClasse();
		  classe.setId(new Integer(classeID));
		  localidadeAlterar.setLocalidadeClasse(classe);
        
        //Porte
        if (porteID == null 
        		|| porteID.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	// Informe Porte
        	throw new ActionServletException("atencao.required",null,"Porte");
        }
		LocalidadePorte porte = new LocalidadePorte();
		porte.setId(new Integer(porteID));
		localidadeAlterar.setLocalidadePorte(porte);
        
        ///Local Armazenagem Hidrometro
        if (hidrometroLocalArmazenagem != null 
        		&& !hidrometroLocalArmazenagem.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	HidrometroLocalArmazenagem hidrometroLocalArmazenagemID = new HidrometroLocalArmazenagem();
        	hidrometroLocalArmazenagemID.setId(new Integer(hidrometroLocalArmazenagem));
            localidadeAlterar.setHidrometroLocalArmazenagem(hidrometroLocalArmazenagemID);
        }else{
        	localidadeAlterar.setHidrometroLocalArmazenagem(null);
        }
        
        //Informatizada
        if (informatizada == null 
        		|| informatizada.equals("")){

        	// Informatizada
        	throw new ActionServletException("atencao.required",null,"Informatizada");
        }
		localidadeAlterar.setIndicadorLocalidadeInformatizada(new Short(informatizada));

        if(gerenteLocalidade != null && !gerenteLocalidade.equals("")){
        	Cliente cliente = new Cliente();
        	cliente.setId(new Integer(gerenteLocalidade));
        	
			Integer clienteFuncionario = fachada.verificarClienteSelecionadoFuncionario(new Integer(gerenteLocalidade));
			
			if(clienteFuncionario == null){
				throw new ActionServletException("atencao.cliente_selecionado_nao_e_funcionario");
			}
        	
        	localidadeAlterar.setCliente(cliente);
        }
        
//      Sede
        if (sede == null 
        		|| sede.equals("")){

      
        	throw new ActionServletException("atencao.required",null,"Sede");
        
        }else{ 
        	
        	if ( atualizarLocalidadeActionForm.getSede() != null && 
        			atualizarLocalidadeActionForm.getSede().equals("1")){
            	
            	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
            	
//            	filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, 
//            			inserirLocalidadeActionForm.getLocalidadeID()));
            	
            	boolean jaExisteSede = false;
            	
            	Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());
            	
            	if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
            		
            		Iterator colecaoLocalidadeIterator = colecaoLocalidade.iterator();
                	
                	Localidade localidade = null;
                	
                	String localidadeSede = "";
                	
                	while ( colecaoLocalidadeIterator.hasNext() && jaExisteSede == false ){
                		
                		localidade = (Localidade) colecaoLocalidadeIterator.next();
                		
                		if ( localidade.getIndicadorLocalidadeSede() == 1){
                			
                			localidadeSede = ""+localidade.getId();
                			
                			jaExisteSede = true;
                			
                		}
                		
                	}
                	
                	if (jaExisteSede){
                		
                		throw new ActionServletException(
                                "atencao.ja_existe_localidade_sede", null, localidadeSede);
                		
                	}else{
                		
                		localidadeAlterar.setIndicadorLocalidadeSede(new Short(sede));
                		
                	}
            		
            	}
            	
            }else{
            	
            	localidadeAlterar.setIndicadorLocalidadeSede(new Short(sede));
            	
            }
        	
        }

        //Município
        localidadeAlterar.setMunicipio(null);
        if(municipio != null && !municipio.equals("")){
        	FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
        	filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, municipio));
        	Collection colecaoMunicipio =  fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
        	
        	//Caso exista o município digitado pelo usuário
        	if(colecaoMunicipio != null && !colecaoMunicipio.isEmpty()){
        		Municipio objMunicipio = (Municipio)colecaoMunicipio.iterator().next();
        		localidadeAlterar.setMunicipio(objMunicipio);
        	}else{
        		throw new ActionServletException("atencao.municipio.inexistente");
        	}
        }
        
        //Indicador de Uso
        localidadeAlterar.setIndicadorUso(new Short(indicadorUso));

    	FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
    	filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,idUnidadeNegocio));
    	filtroUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.GERENCIA);
    	filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
                FiltroUnidadeNegocio.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
                UnidadeNegocio.class.getName());

        UnidadeNegocio unidadeNegocioGerencia = (UnidadeNegocio) colecaoUnidadeNegocio.iterator().next(); 
        
        if(unidadeNegocioGerencia.getGerenciaRegional() != null){
        	GerenciaRegional gerenciaRegional = new GerenciaRegional();
        	gerenciaRegional.setId(unidadeNegocioGerencia.getGerenciaRegional().getId());
        	localidadeAlterar.setGerenciaRegional(gerenciaRegional);
        }
        
        //Ultima alteração
        //localidadeAlterar.setUltimaAlteracao(new Date());

        
        //------------ REGISTRAR TRANSAÇÃO ----------------
        localidadeAlterar.setOperacaoEfetuada(operacaoEfetuada);
        localidadeAlterar.adicionarUsuario(usuario, 
        		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        registradorOperacao.registrarOperacao(localidadeAlterar);
        //------------ REGISTRAR TRANSAÇÃO ----------------  
        
        fachada.atualizarLocalidade(localidadeAlterar);

        montarPaginaSucesso(httpServletRequest, "Localidade de código  "
                + localidadeAlterar.getId() + "  atualizada com sucesso.",
                "Realizar outra Manutenção de Localidade",
                "exibirFiltrarLocalidadeAction.do?desfazer=S");
        
        
        sessao.removeAttribute("localidadeManter");
        sessao.removeAttribute("colecaoLocalidade");
        sessao.removeAttribute("colecaoEnderecos");
        sessao.removeAttribute("colecaoGerenciaRegional");
        sessao.removeAttribute("colecaoClasse");
        sessao.removeAttribute("colecaoPorte");
        sessao.removeAttribute("colecaoHidrometroLocalArmazenagem");
        
        
        sessao.removeAttribute("tipoPesquisaRetorno");

       /* //Limpando o formulario
        atualizarLocalidadeActionForm.setEloID("");
        atualizarLocalidadeActionForm.setEmail("");
        atualizarLocalidadeActionForm.setFax("");
        atualizarLocalidadeActionForm.setLocalidadeID("");
        atualizarLocalidadeActionForm.setLocalidadeNome("");
        atualizarLocalidadeActionForm.setMenorConsumo("");
        atualizarLocalidadeActionForm.setRamal("");
        atualizarLocalidadeActionForm.setTelefone("");

        // Campos do tipo lista no formulário
        atualizarLocalidadeActionForm.setClasseID("");
        atualizarLocalidadeActionForm.setGerenciaID("");
        atualizarLocalidadeActionForm.setPorteID("");
        */
        //devolve o mapeamento de retorno
        return retorno;

    }
}
