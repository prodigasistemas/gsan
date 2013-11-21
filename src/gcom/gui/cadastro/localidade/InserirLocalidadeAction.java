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
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirLocalidadeAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        InserirLocalidadeActionForm inserirLocalidadeActionForm = (InserirLocalidadeActionForm) actionForm;
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //------------ REGISTRAR TRANSAÇÃO ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LOCALIDADE_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_LOCALIDADE_INSERIR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------
        
        String localidadeID = inserirLocalidadeActionForm.getLocalidadeID();
        String localidadeNome = inserirLocalidadeActionForm.getLocalidadeNome();
        Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
        String telefone = inserirLocalidadeActionForm.getTelefone();
        String ramal = inserirLocalidadeActionForm.getRamal();
        String fax = inserirLocalidadeActionForm.getFax();
        String email = inserirLocalidadeActionForm.getEmail();
        String menorConsumo = inserirLocalidadeActionForm.getMenorConsumo();
        String icms = inserirLocalidadeActionForm.getIcms();
        String centroCusto = inserirLocalidadeActionForm.getCentroCusto();
        String centroCustoEsgoto = inserirLocalidadeActionForm.getCentroCustoEsgoto();
        
        String eloID = inserirLocalidadeActionForm.getEloID();
        //String gerenciaID = inserirLocalidadeActionForm.getGerenciaID();
        String idUnidadeNegocio = inserirLocalidadeActionForm.getIdUnidadeNegocio();
        String classeID = inserirLocalidadeActionForm.getClasseID();
        String porteID = inserirLocalidadeActionForm.getPorteID();
        String informatizada = inserirLocalidadeActionForm.getInformatizada();
        String gerenteLocalidade = inserirLocalidadeActionForm.getGerenteLocalidade();
        String hidrometroLocalArmazenagem = inserirLocalidadeActionForm.getHidrometroLocalArmazenagem();
        //Inidicador se a localidade é a sede da empresa
        String sede = inserirLocalidadeActionForm.getSede();
        String municipio = inserirLocalidadeActionForm.getMunicipio();
        Localidade localidadeInserir = new Localidade();
        Collection colecaoPesquisa = null;

        sessao.removeAttribute("tipoPesquisaRetorno");
        //O código da localidade é obrigatório.
        if (localidadeID == null || localidadeID.equalsIgnoreCase("")) {
            throw new ActionServletException(
            		"atencao.required",null,"Código");
        }

        //O nome da localidade é obrigatório.
        if (localidadeNome == null || localidadeNome.equalsIgnoreCase("")) {
            throw new ActionServletException(
            		"atencao.required",null,"Nome");
        }
        
        if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
        	localidadeInserir = (Localidade) Util
            .retonarObjetoDeColecao(colecaoEnderecos);
        } 

        localidadeInserir.setId(new Integer(localidadeID));
        localidadeInserir.setDescricao(localidadeNome);
        
        
        //O telefone é obrigatório caso o ramal tenha sido informado.
        if (ramal != null && !ramal.equalsIgnoreCase("")) {
            localidadeInserir.setRamalfone(ramal);
            if (telefone == null || telefone.equalsIgnoreCase("")) {
                throw new ActionServletException(
                        "atencao.telefone_localidade_nao_informado");
            }
            else if (telefone.length() < 7){
            	throw new ActionServletException(
                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Telefone");
            }
        }

        //Telefone.
        if (telefone != null && !telefone.equalsIgnoreCase("")) {
        	if (telefone.length() < 7){
        		throw new ActionServletException(
                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Telefone");
        	}
        	else{
        		localidadeInserir.setFone(telefone);
        	}
        }

        //Fax.
        if (fax != null && !fax.equalsIgnoreCase("")) {
        	if (fax.length() < 7){
        		throw new ActionServletException(
                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Fax");
        	}
        	else{
        		localidadeInserir.setFax(fax);
        	}
        }

        //E-mail.
        if (email != null && !email.equalsIgnoreCase("")) {
            localidadeInserir.setEmail(email);
        }

        //Menor Consumo.
        if (menorConsumo != null && !menorConsumo.equalsIgnoreCase("")) {
            localidadeInserir.setConsumoGrandeUsuario(Integer
                    .parseInt(menorConsumo));
        }

        //ICMS
        if (icms != null && !icms.equalsIgnoreCase("")) {
            localidadeInserir.setCodigoICMS(Integer
                    .parseInt(icms));
        }
        
        //Centro Custo
        if (centroCusto != null && !centroCusto.equalsIgnoreCase("")) {
            localidadeInserir.setCodigoCentroCusto(centroCusto);
        }
        
        //Centro Custo de Esgoto
        if (centroCustoEsgoto != null && !centroCustoEsgoto.equalsIgnoreCase("")) {
            localidadeInserir.setCodigoCentroCustoEsgoto(centroCustoEsgoto);
        }
        
        
        
        //Gerência Regional
        if (idUnidadeNegocio == null 
        		|| idUnidadeNegocio.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	//Informe Gerência Regional
        	throw new ActionServletException("atencao.required",null,"Unidade de Negócio");
        }else{        	
        	UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
            unidadeNegocio.setId(new Integer(idUnidadeNegocio));
            localidadeInserir.setUnidadeNegocio(unidadeNegocio);	
        }
        

        //Elo.
        Localidade localidadeElo = new Localidade();
        if (eloID != null
                && !eloID.equalsIgnoreCase(String
                        .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

            FiltroLocalidade filtroLocalidadeElo = new FiltroLocalidade();

            filtroLocalidadeElo.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID_UNIDADE_NEGOCIO, localidadeInserir
                            .getUnidadeNegocio().getId()));

            filtroLocalidadeElo.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, eloID));

            filtroLocalidadeElo.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna localidade - Elo
            colecaoPesquisa = fachada.pesquisar(filtroLocalidadeElo,
                    Localidade.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //O código do Elo não existe na tabela Localidade
                throw new ActionServletException(
                        "atencao.pesquisa_elo_nao_inexistente");
            } else {
                localidadeElo = (Localidade) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                if (localidadeElo.getId().intValue() != localidadeElo
                        .getLocalidade().getId().intValue()) {
                    //A localidade escolhida não é um Elo
                    throw new ActionServletException(
                            "atencao.localidade_nao_e_elo");
                } else {
                    localidadeInserir.setLocalidade(localidadeElo);
                }
            }
        }
        else{
        	localidadeInserir.setLocalidade(localidadeInserir);
        }

        //Classe
        if (classeID == null 
        		|| classeID.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	//Informe Classe
        	throw new ActionServletException("atencao.required",null,"Classe");
        }else{  
        	 LocalidadeClasse classe = new LocalidadeClasse();
             classe.setId(new Integer(classeID));
             localidadeInserir.setLocalidadeClasse(classe);
        }
        
        //Porte
        if (porteID == null 
        		|| porteID.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	// Informe Porte
        	throw new ActionServletException("atencao.required",null,"Porte");
        }else{  
        	LocalidadePorte porte = new LocalidadePorte();
            porte.setId(new Integer(porteID));
            localidadeInserir.setLocalidadePorte(porte);
        }
        
        //Local Armazenagem Hidrometro
        if (hidrometroLocalArmazenagem != null 
        		&& hidrometroLocalArmazenagem.equals("")){
        	HidrometroLocalArmazenagem hidrometroLocalArmazenagemID = new HidrometroLocalArmazenagem();
        	hidrometroLocalArmazenagemID.setId(new Integer(hidrometroLocalArmazenagem));
            localidadeInserir.setHidrometroLocalArmazenagem(hidrometroLocalArmazenagemID);
        }
        
        //Seta o Gerencia Regional de acordo com a Unidade de Negocio
    	FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
    	filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,idUnidadeNegocio));
    	filtroUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.GERENCIA);
    	filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
                FiltroUnidadeNegocio.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
                UnidadeNegocio.class.getName());

        UnidadeNegocio unidadeNegocio = (UnidadeNegocio) colecaoUnidadeNegocio.iterator().next(); 
        
        if(unidadeNegocio.getGerenciaRegional() != null){
        	GerenciaRegional gerenciaRegional = new GerenciaRegional();
        	gerenciaRegional.setId(unidadeNegocio.getGerenciaRegional().getId());
        	
			if (gerenteLocalidade != null && !gerenteLocalidade.equals("")){
			
				Integer clienteFuncionario = fachada.verificarClienteSelecionadoFuncionario(new Integer(gerenteLocalidade));
				
				if(clienteFuncionario == null){
					throw new ActionServletException("atencao.cliente_selecionado_nao_e_funcionario");
				}
			}
        	
        	localidadeInserir.setGerenciaRegional(gerenciaRegional);
        }
        
        //Informatizada
        if (informatizada == null 
        		|| informatizada.equals("")){

        	// Informatizada
        	throw new ActionServletException("atencao.required",null,"Informatizada");
        }else{  
            localidadeInserir.setIndicadorLocalidadeInformatizada(new Short(informatizada));
        }

        if(gerenteLocalidade != null && !gerenteLocalidade.equals("")){
        	Cliente cliente = new Cliente();
        	cliente.setId(new Integer(gerenteLocalidade));
        	
        	localidadeInserir.setCliente(cliente);
        }
        
        //Sede
        if (sede == null 
        		|| sede.equals("")){

      
        	throw new ActionServletException("atencao.required",null,"Sede");
        
        }else{ 
        	
        	if ( inserirLocalidadeActionForm.getSede() != null && 
            		inserirLocalidadeActionForm.getSede().equals("1")){
            	
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
                		
                		localidadeInserir.setIndicadorLocalidadeSede(new Short(sede));
                		
                	}
            		
            	}
            	
            }else{
            	
            	localidadeInserir.setIndicadorLocalidadeSede(new Short(sede));
            	
            }
        	
        }
        
        //Município Principal
        if(municipio != null && !municipio.equals("")){
        	FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
        	filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, municipio));
        	

            Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
                    Municipio.class.getName());
            
            if(colecaoMunicipio != null && !colecaoMunicipio.isEmpty()){
            	Municipio objMunicipio = (Municipio) colecaoMunicipio.iterator().next(); 
            	localidadeInserir.setMunicipio(objMunicipio);
            }else{
            	throw new ActionServletException("atencao.municipio.inexistente");
            }
        }
        
        //Indicador de Uso
        localidadeInserir
                .setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

        //Ultima alteração
        localidadeInserir.setUltimaAlteracao(new Date());

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, localidadeInserir.getId()));

        //Verificar existência da Localidade
        colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class
                .getName());

        if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
            //Localidade já existe
            throw new ActionServletException(
                    "atencao.pesquisa_localidade_ja_cadastrada", null, localidadeID);
        } else {
        	Integer idLocalidade = null;
            
            //------------ REGISTRAR TRANSAÇÃO ----------------
        	localidadeInserir.setOperacaoEfetuada(operacaoEfetuada);
        	localidadeInserir.adicionarUsuario(usuario, 
            		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
            registradorOperacao.registrarOperacao(localidadeInserir);
            //------------ REGISTRAR TRANSAÇÃO ----------------  
            idLocalidade = fachada.inserirLocalidadeRetorno(localidadeInserir);

            montarPaginaSucesso(httpServletRequest, "Localidade de código  "
                    + localidadeInserir.getId().intValue()
                    + " inserida com sucesso.", "Inserir outra Localidade",
                    "exibirInserirLocalidadeAction.do?menu=sim",
                    "exibirAtualizarLocalidadeAction.do?idRegistroInseridoAtualizar="
					+ idLocalidade,
					"Atualizar Localidade Inserida");

        }

        sessao.removeAttribute("colecaoEnderecos");

        //devolve o mapeamento de retorno
        return retorno;
    }

}
