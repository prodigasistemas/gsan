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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.integracao.webservice.spc.ConsultaWebServiceTest;
import gcom.seguranca.AtributoGrupo;
import gcom.seguranca.ConsultaCdl;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class InserirClienteAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Pega o form do cliente
		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

		// Código do cliente que será inserido
		Integer codigoCliente = null;
		
		// Pega a coleção de endereços do cliente
		Collection colecaoEnderecos = (Collection) sessao
				.getAttribute("colecaoEnderecos");

		// Pega a coleção de telefones do cliente
		Collection colecaoFones = (Collection) sessao
				.getAttribute("colecaoClienteFone");
		// Cria o objeto do cliente para ser inserido
		String nome = ((String) clienteActionForm.get("nome")).toUpperCase();
		String nomeAbreviado = ((String) clienteActionForm.get("nomeAbreviado")).toUpperCase();
		String rg = (String) clienteActionForm.get("rg");
		String cpf = (String) clienteActionForm.get("cpf");
		String dataEmissao = (String) clienteActionForm.get("dataEmissao");
		String dataNascimento = (String) clienteActionForm
				.get("dataNascimento");
		String cnpj = (String) clienteActionForm.get("cnpj");
		String email = (String) clienteActionForm.get("email");
		
		Short  indicadorUsoNomeFantasiaConta = ConstantesSistema.NAO;
        
        if(clienteActionForm.get("indicadorExibicaoNomeConta") != null && !clienteActionForm.get("indicadorExibicaoNomeConta").toString().equals("")){
        	
        	String indicadorExibicaoNomeConta = null;
        	indicadorExibicaoNomeConta = (String) clienteActionForm.get("indicadorExibicaoNomeConta").toString();
        	
        	if(indicadorExibicaoNomeConta.equals(Cliente.INDICADOR_NOME_FANTASIA.toString())){
        		indicadorUsoNomeFantasiaConta = ConstantesSistema.SIM;
        	}
        }

		OrgaoExpedidorRg orgaoExpedidorRg = null;

		if (clienteActionForm.get("idOrgaoExpedidor") != null
				&& ((Integer) clienteActionForm.get("idOrgaoExpedidor"))
						.intValue() > 0) {

			orgaoExpedidorRg = new OrgaoExpedidorRg();

			orgaoExpedidorRg.setId((Integer) clienteActionForm
					.get("idOrgaoExpedidor"));

		}

		PessoaSexo pessoaSexo = null;

		if (clienteActionForm.get("idPessoaSexo") != null
				&& ((Integer) clienteActionForm.get("idPessoaSexo")).intValue() > 0) {

			pessoaSexo = new PessoaSexo();

			pessoaSexo.setId((Integer) clienteActionForm.get("idPessoaSexo"));

		}

		Profissao profissao = null;

		if (clienteActionForm.get("idProfissao") != null
				&& ((Integer) clienteActionForm.get("idProfissao")).intValue() > 0) {

			profissao = new Profissao();

			profissao.setId((Integer) clienteActionForm.get("idProfissao"));
		}

		UnidadeFederacao unidadeFederacao = null;

		if (clienteActionForm.get("idUnidadeFederacao") != null
				&& ((Integer) clienteActionForm.get("idUnidadeFederacao"))
						.intValue() > 0) {

			unidadeFederacao = new UnidadeFederacao();

			unidadeFederacao.setId((Integer) clienteActionForm
					.get("idUnidadeFederacao"));
		}

		// Seta o clienteTipo
		ClienteTipo clienteTipo = new ClienteTipo();
		
//		Short tipoPessoa = (Short)  clienteActionForm.get("tipoPessoa");
//		String tipoPessoaForm =  tipoPessoa.toString() ; 

		clienteTipo.setId(new Integer(((Short) clienteActionForm
				.get("tipoPessoa")).intValue()));

		// Faz a verificação se o tipo do cliente é pessoa jurídica e se o cnpj
		// foi preenchido
		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

		filtroClienteTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteTipo.ID, clienteTipo.getId().toString()));

		Short tipoPessoa = ((ClienteTipo) fachada.pesquisar(filtroClienteTipo,
				ClienteTipo.class.getName()).iterator().next())
				.getIndicadorPessoaFisicaJuridica();

		if (tipoPessoa != null) {
			if (tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA)) {
				// Não é mais obrigatório
				// Roberta Costa - Alterado em 09/08/2006
				/*
				 * if (cnpj == null || cnpj.trim().equalsIgnoreCase("")) { //O
				 * CNPJ é obrigatório caso o tipo de pessoa seja jurídica throw
				 * new ActionServletException( "atencao.informe_campo", null,
				 * "CNPJ"); }
				 */
			} else if (tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
				if (clienteActionForm.get("idPessoaSexo").equals(
						ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					// O Sexo é obrigatório caso o tipo de pessoa seja física
					throw new ActionServletException("atencao.informe_campo",
							null, "Sexo");
				}
			}
		}

		// Verifica se pelo menos um endereço de correspondência foi informado
		if (colecaoEnderecos == null || colecaoEnderecos.isEmpty()) {
			throw new ActionServletException("atencao.informe_campo", null,
					"Endereço(s) do Cliente");
		}

		/**
		 * Autor: Mariana Victor
		 * Data:  27/12/2010
		 * RM_3320 - [FS0016] Verificar Duplicidade de cliente
		 */
		if (this.getSistemaParametro().getIndicadorDuplicidadeCliente().toString()
				.equals(ConstantesSistema.SIM.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_CLIENTE_COM_MESMO_NOME_E_ENDERECO));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
				filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.NOME, nome.toUpperCase()));

				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente");
				
				Collection<ClienteEndereco> colecaoClienteEndereco = fachada.pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());
				
				if (colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()){
					Iterator iterator = colecaoClienteEndereco.iterator();
					
					while (iterator.hasNext()) {
						ClienteEndereco clienteEnderecoIterator = (ClienteEndereco) iterator.next();
						
						Iterator iteratorEnderecos = colecaoEnderecos.iterator();
						while (iteratorEnderecos.hasNext()) {
							ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos
									.next();
							
							if (clienteEndereco.getEnderecoFormatado().equals(
									clienteEnderecoIterator.getEnderecoFormatado())) {
								throw new ActionServletException("atencao.duplicidade.cliente", null,
									"Cliente");
							}
						}
					}
				}	
				
			}
			
		}
		
		/**
		 * Autor: Mariana Victor
		 * Data:  28/12/2010
		 * RM_3320 - [FS0017] Verificar Nome de Cliente com menos de 10 posições
		 */
		if (this.getSistemaParametro().getIndicadorNomeMenorDez().toString()
				.equals(ConstantesSistema.NAO.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_NOMES_COM_MENOS_DE_10_CARACTERES));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				String nomeFormatado= nome.replaceAll(" ", "");
				if (nomeFormatado.length() < 10) {
					throw new ActionServletException("atencao.nome.cliente.menos.dez.posicoes",
							null, "Nome do Cliente");
				}
			}
			
		}

		/**
		 * Autor: Mariana Victor
		 * Data:  28/12/2010
		 * RM_3320 - [FS0018] Verificar Nome de Cliente com Descrição Genérica
		 */
		if (this.getSistemaParametro().getIndicadorNomeClienteGenerico().toString()
				.equals(ConstantesSistema.NAO.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_NOME_CLIENTE_GENERICO));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				FiltroDescricaoGenerica filtroDescricaoGenerica = new FiltroDescricaoGenerica();
				Collection colecaoDescricaoGenerica = fachada.pesquisar(filtroDescricaoGenerica, DescricaoGenerica.class.getName());
				
				if (colecaoDescricaoGenerica != null || !colecaoDescricaoGenerica.isEmpty()) {
					String nomeFormatado= nome.replaceAll(" ", "");
					Iterator iteratorDescricaoGenerica = colecaoDescricaoGenerica.iterator();
					
					while (iteratorDescricaoGenerica.hasNext()) {
						DescricaoGenerica descricaoGenerica = (DescricaoGenerica) iteratorDescricaoGenerica.next();
						String nomeGenerico = descricaoGenerica.getNomeGenerico();
						String nomeGenericoFormatado = nomeGenerico.replaceAll(" ", "");
						
						if (nomeGenerico.equalsIgnoreCase(nome)
								|| nomeGenericoFormatado.equalsIgnoreCase(nome)
								|| nomeGenerico.equalsIgnoreCase(nomeFormatado)
								|| nomeGenericoFormatado.equalsIgnoreCase(nomeFormatado)) {
							throw new ActionServletException("atencao.nome.cliente.descricao.generica",
									null, "Nome do Cliente");		
						}
					}
					
				}
				
			}
			
		}
		
		RamoAtividade ramoAtividade = null;

		// Caso o ramo ra
		if (clienteActionForm.get("idRamoAtividade") != null
				&& ((Integer) clienteActionForm.get("idRamoAtividade"))
						.intValue() > 0) {

			ramoAtividade = new RamoAtividade();

			ramoAtividade.setId((Integer) clienteActionForm
					.get("idRamoAtividade"));
		}

		Cliente clienteResponsavel = null;

		if (clienteActionForm.get("codigoClienteResponsavel") != null
				&& !((String) clienteActionForm.get("codigoClienteResponsavel"))
						.trim().equalsIgnoreCase("")) {
			// Cria o objeto do cliente responsável
			clienteResponsavel = new Cliente();

			clienteResponsavel.setId(new Integer((String) clienteActionForm
					.get("codigoClienteResponsavel")));

		}

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		ConsultaCdl clienteCadastradoNaReceita = new ConsultaCdl();
		try {
			
			if(cpf != null && cpf.equals("")){
				cpf = null;
			}

			if(cnpj != null && cnpj.equals("")){
				cnpj = null;
			}
			
			Cliente cliente = 
				new Cliente(
					nome,
					nomeAbreviado,
					cpf,
					rg,
					dataEmissao != null && !dataEmissao.trim().equalsIgnoreCase("") ? formatoData.parse(dataEmissao): null,
					dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase("") ? formatoData.parse(dataNascimento) : null, 
					cnpj, 
					email,
					ConstantesSistema.INDICADOR_USO_ATIVO, 
					new Date(),
					orgaoExpedidorRg, 
					clienteResponsavel, 
					pessoaSexo,
					profissao, 
					unidadeFederacao, 
					clienteTipo,
					indicadorUsoNomeFantasiaConta,
					ramoAtividade);

			// Insere o indicador para Cobranca Acrescimos
			cliente.setIndicadorAcrescimos(new Short("1"));
            
             if (clienteActionForm.get("diaVencimento") != null
                    && !(clienteActionForm.get("diaVencimento").equals(""))){
                String diaVencimento = (String)clienteActionForm.get("diaVencimento"); 
                cliente.setDataVencimento( new Short(diaVencimento));
            }else{
                cliente.setDataVencimento(null);
            }
            
			// Nome da Mãe
			if (clienteActionForm.get("nomeMae") != null
					&& (!(clienteActionForm.get("nomeMae").equals("")))) {
				cliente.setNomeMae(((String) clienteActionForm.get("nomeMae")).toUpperCase());
			}
			
			if (clienteActionForm.get("indicadorGeraFaturaAntecipada") != null && !clienteActionForm.get("indicadorGeraFaturaAntecipada").equals("")) {
				cliente.setIndicadorGeraFaturaAntecipada(new Short((String) clienteActionForm.get("indicadorGeraFaturaAntecipada")));
			} else {
				cliente.setIndicadorGeraFaturaAntecipada(ConstantesSistema.NAO);
			}
			
			if (clienteActionForm.get("diaVencimento") != null && !(clienteActionForm.get("diaVencimento").equals("")) && 
			   (clienteActionForm.get("indicadorVencimentoMesSeguinte") != null && !clienteActionForm.get("indicadorVencimentoMesSeguinte").equals(""))) {
				cliente.setIndicadorVencimentoMesSeguinte(new Short((String) clienteActionForm.get("indicadorVencimentoMesSeguinte")));
			} else {
				cliente.setIndicadorVencimentoMesSeguinte(ConstantesSistema.NAO);
			}
			
			//Insere o Indicador de Negativação do Cliente
			if (clienteActionForm.get("indicadorPermiteNegativacao") != null
					&& clienteActionForm.get("indicadorPermiteNegativacao").equals(ConstantesSistema.SIM.toString())){
				
				cliente.setIndicadorPermiteNegativacao(ConstantesSistema.NAO);
			} else {
				cliente.setIndicadorPermiteNegativacao(ConstantesSistema.SIM);
			}
			
			//*************************************************************************
			// Autor: Ivan Sergio
			// Data: 06/08/2009
			// CRC2103
			// Verifica se a funcionalidade esta sendo executada dentro de um popup
			//*************************************************************************
			if (sessao.getAttribute("POPUP") != null) {
				if (sessao.getAttribute("POPUP").equals("true")) {
					if (sessao.getAttribute("idImovel") == null) {
						cliente.setId2(-1);
						colecaoEnderecos = this.setaId2ClienteEnderecos(colecaoEnderecos, -1);
						colecaoFones = this.setaId2ClienteFones(colecaoFones, -1);
					} else {
						Integer idImovel = new Integer(sessao.getAttribute("idImovel").toString());
						cliente.setId2(idImovel);
						colecaoEnderecos = this.setaId2ClienteEnderecos(colecaoEnderecos, idImovel);
						colecaoFones = this.setaId2ClienteFones(colecaoFones, idImovel);
					}
					
					if (sessao.getAttribute("idClienteRelacaoTipo") != null) {
						Integer idClienteRelacaoTipo =
							new Integer(sessao.getAttribute("idClienteRelacaoTipo").toString());
						
						Integer idAtributoGrupo = null;
						switch (idClienteRelacaoTipo) {
						case 1:
							idAtributoGrupo = AtributoGrupo.ATRIBUTOS_DO_PROPRIETARIO;
							break;
						case 2:
							idAtributoGrupo = AtributoGrupo.ATRIBUTOS_DO_USUARIO;
							break;
						}
						
						if (idAtributoGrupo != null) {
							AtributoGrupo atributoGrupo = new AtributoGrupo();
							atributoGrupo.setId(idAtributoGrupo);
							
							OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
							operacaoEfetuada.setAtributoGrupo(atributoGrupo);
							
							cliente.setOperacaoEfetuada(operacaoEfetuada);
						}
					}
				}
			}
			//*************************************************************************
			
			/**
			 * Autor: Rodrigo Cabral
			 * Data: 20/10/2010
			 * CRC4476
			 */
			String confirmado = null;
			if ( httpServletRequest.getParameter("confirmado") != null  ) {
				confirmado = httpServletRequest.getParameter("confirmado");
			}
			
			Short indicadorConsultaDocumentoReceita = this.getSistemaParametro().getIndicadorConsultaDocumentoReceita();
			if(cpf == null || cnpj == null){
				indicadorConsultaDocumentoReceita = ConstantesSistema.NAO;
			}
			
			if (confirmado == null && 
				indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())){
				
				ConsultaWebServiceTest consultaWebService = new ConsultaWebServiceTest();
				
				try {
					if (cpf != null){
						clienteCadastradoNaReceita = consultaWebService.consultarPessoaFisica(nome,cpf);
						System.out.println("CONSULTA SPC INSERIR CLIENTE CPF: "+cpf);
					}else if (cnpj != null){
						clienteCadastradoNaReceita = consultaWebService.consultaPessoaJuridica(nome,cnpj);
						System.out.println("CONSULTA SPC INSERIR CLIENTE CNPJ: "+cnpj);
					}
				} catch (Exception e) {
					clienteCadastradoNaReceita.setMensagemRetorno("Erro ao consultar o CDL.");
				}
				
				if(clienteCadastradoNaReceita.getNomeCliente() != null && 
					!clienteCadastradoNaReceita.getNomeCliente().equals("NOME NAO CADASTRADO") &&
					!clienteCadastradoNaReceita.getNomeCliente().equals("EMPRESA NAO CADASTRADA") ){
						
					System.out.println("NOME RETORNADO CDL "+cpf+":"+clienteCadastradoNaReceita.getNomeCliente());
					
					clienteActionForm.set("nomeClienteReceitaFederal" , clienteCadastradoNaReceita.getNomeCliente());
					
				}else{
					clienteCadastradoNaReceita.setNomeCliente(null);
					clienteCadastradoNaReceita.setMensagemRetorno("Erro ao consultar o CDL.");
				}
				
				sessao.setAttribute("clienteCadastradoNaReceita", clienteCadastradoNaReceita);
			}
			
			short codigoAcao = ConstantesSistema.NUMERO_NAO_INFORMADO;
			boolean ehParaInserirImovel = true;
			
			//Caso o spc esteja fora, não realizar acao de atualizacao do cliente e dos dados do spc
			if(clienteCadastradoNaReceita != null &&
				clienteCadastradoNaReceita.getMensagemRetorno() != null){
				
				ehParaInserirImovel = false;
				retorno = this.montaTelaAtencao(actionMapping,
						httpServletRequest,
						"atencao.cliente_nao_foi_inserido_spc_fora",
						false);
				
			}

			
			if ( confirmado == null && 
				clienteCadastradoNaReceita.getNomeCliente() != null &&
				!clienteCadastradoNaReceita.getNomeCliente().equals(nome) ) {
				
				httpServletRequest.setAttribute("nomeBotao1", "Aceitar");
				httpServletRequest.setAttribute("nomeBotao3", "Rejeitar");
		
				
				return montarPaginaConfirmacaoWizard("atencao.confirmacao_nome_receita_federal",
							httpServletRequest, actionMapping, 
							nome, clienteCadastradoNaReceita.getNomeCliente());
					
			}else if(confirmado == null && 
				clienteCadastradoNaReceita.getNomeCliente() != null &&
				clienteCadastradoNaReceita.getNomeCliente().equals(nome)){
				clienteCadastradoNaReceita = 
					(ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");

				codigoAcao = 3;
				clienteCadastradoNaReceita.setCodigoAcaoOperador(codigoAcao);
			
			}else if ( confirmado != null && confirmado.trim().equalsIgnoreCase("ok") ) {
				
				clienteCadastradoNaReceita = 
					(ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");
				
				cliente.setNome(clienteCadastradoNaReceita.getNomeCliente());
				
				if (clienteCadastradoNaReceita.getNomeMae() != null){
					cliente.setNomeMae(clienteCadastradoNaReceita.getNomeMae());
				}
				
				if (clienteCadastradoNaReceita.getDataNascimento() != null){
					cliente.setDataNascimento(clienteCadastradoNaReceita.getDataNascimento());
				}
				
				codigoAcao = 1;
				clienteCadastradoNaReceita.setCodigoAcaoOperador(codigoAcao);
				
				
			} else if((clienteCadastradoNaReceita.getMensagemRetorno() == null || 
					clienteCadastradoNaReceita.getMensagemRetorno().equals("")) && 
					(confirmado != null)){
				
				clienteCadastradoNaReceita = 
					(ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");
				
				codigoAcao = 2;
				clienteCadastradoNaReceita.setCodigoAcaoOperador(codigoAcao);
				ehParaInserirImovel = false;
				
				httpServletRequest.setAttribute("naoExibirBotaoVoltarTelaAtencao",true);
				reportarErros(httpServletRequest, "atencao.cliente_nao_foi_inserido");
				
				//retorno = actionMapping.findForward("telaAtencao");
				retorno = actionMapping.findForward("telaAtencao");
				
			} 
			/**
			 * fim
			 */
			
			// Insere o cliente
			if(ehParaInserirImovel){
				codigoCliente = 
					this.getFachada().inserirCliente(cliente,
						colecaoFones, 
						colecaoEnderecos, 
						usuario);
			}
			
			Cliente clienteAux = null;
			
			if(codigoCliente != null ){
				clienteAux = new Cliente();
				clienteAux.setId(codigoCliente);
			}
		
			if (confirmado != null ||
				(clienteCadastradoNaReceita.getCodigoAcaoOperador() != null && clienteCadastradoNaReceita.getCodigoAcaoOperador() == 3)
				){
				
				ConsultaCdl clienteCadastradoNaReceitaAtualiza = 
					(ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");
				
				clienteCadastradoNaReceitaAtualiza.setCodigoCliente(clienteAux);
				clienteCadastradoNaReceitaAtualiza.setUsuario(usuario);
				clienteCadastradoNaReceitaAtualiza.setCpfUsuario(usuario.getCpf());
				clienteCadastradoNaReceitaAtualiza.setLoginUsuario(usuario.getLogin());
				clienteCadastradoNaReceitaAtualiza.setUltimaAlteracao(new Date());

				this.getFachada().inserir(clienteCadastradoNaReceitaAtualiza);
			}
			
			// limpa a sessão
			sessao.removeAttribute("clienteCadastradoNaReceita");
			sessao.removeAttribute("colecaoClienteFone");
			sessao.removeAttribute("colecaoEnderecos");
			sessao.removeAttribute("foneTipos");
			sessao.removeAttribute("municipios");
			sessao.removeAttribute("colecaoResponsavelSuperiores");
			sessao.removeAttribute("InserirEnderecoActionForm");
			sessao.removeAttribute("ClienteActionForm");
			sessao.removeAttribute("tipoPesquisaRetorno");

		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}

		// Verifica se a funcionalidade esta sendo executada dentro de um popup
		boolean exibirTelaSucesso = true;
		if (sessao.getAttribute("POPUP") != null) {
			if (sessao.getAttribute("POPUP").equals("true")) {
				// Verifica o action de retorno
				// action = inserirClienteNomeTipo
				retorno = actionMapping.findForward("inserirClientePopUp");
				//codigoCliente = 1;
				sessao.setAttribute("codigoCliente", codigoCliente);
				sessao.setAttribute("nomeCliente", nome);
				httpServletRequest.setAttribute("colecaoTipoPessoa", null);
				exibirTelaSucesso = false;
			}
		}
		
		if (exibirTelaSucesso) {
			
			
			// Monta a página de sucesso
			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				
				String mensagemSucesso = "Cliente de código "+ codigoCliente + " inserido com sucesso.";
				
				montarPaginaSucesso(httpServletRequest, mensagemSucesso,
						"Inserir outro Cliente", "exibirInserirClienteAction.do",
						"exibirAtualizarClienteAction.do?idRegistroAtualizacao="
								+ codigoCliente, "Atualizar Cliente Inserido");
			}else{
				retorno = actionMapping.findForward("telaAtencao");
			}
		}
		
		return retorno;
	}
	
	/***
	 * @author Ivan Sergio
	 * @date: 11/08/2009
	 * 
	 * @param colecaoEnderecos
	 * @param id2
	 * @return
	 */
	private Collection setaId2ClienteEnderecos(Collection colecaoEnderecos, Integer id2) {
		Collection retorno = null;
		
		if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
			retorno = new ArrayList();
			Iterator iColecaoEnderecos = colecaoEnderecos.iterator();
			
			while (iColecaoEnderecos.hasNext()) {
				ClienteEndereco endereco = (ClienteEndereco) iColecaoEnderecos.next();
				endereco.setId2(id2);
				retorno.add(endereco);
			}
		}
		
		return retorno;
	}
	
	/**
	 * @author Ivan Sergio
	 * @date: 11/08/2009
	 * 
	 * @param colecaoFones
	 * @param id2
	 * @return
	 */
	private Collection setaId2ClienteFones(Collection colecaoFones, Integer id2) {
		Collection retorno = null;
		
		if (colecaoFones != null && !colecaoFones.isEmpty()) {
			retorno = new ArrayList();
			Iterator iColecaoFones = colecaoFones.iterator();
			
			while (iColecaoFones.hasNext()) {
				ClienteFone fone = (ClienteFone) iColecaoFones.next();
				fone.setId2(id2);
				retorno.add(fone);
			}
		}
		
		return retorno;
	}
	
	/**
	 * @author Rafael Pinto
	 * @date: 09/01/2011
	 * 
	 * @param actionMapping ActionMapping
	 * @param httpServletRequest httpServletRequest
	 * @return ActionForward
	 */
	private ActionForward montaTelaAtencao(ActionMapping actionMapping, 
		HttpServletRequest httpServletRequest,String chave,
		boolean naoExibirBotaoVoltarTelaAtencao){
				
		httpServletRequest.setAttribute("naoExibirBotaoVoltarTelaAtencao",naoExibirBotaoVoltarTelaAtencao);
		reportarErros(httpServletRequest, chave);
		
		return actionMapping.findForward("telaAtencao");
	}
	
	
}
