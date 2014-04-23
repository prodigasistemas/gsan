package gcom.gui.cadastro.cliente;

import java.util.Collection;
import java.util.Iterator;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

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
public class InserirClienteNomeTipoAction extends GcomAction {

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

        ActionForward retorno = null;

        DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

        // Verifica se o usuário escolheu algum tipo de pessoa para que seja
        // mostrada a página de pessoa física ou de jurídica, se nada estiver 
        // especificado a página selecionada será a de pessoa física
        Short tipoPessoa = (Short) clienteActionForm.get("tipoPessoa");
        String nome = clienteActionForm.get("nome").toString();

        
		String indicadorExibicaoNomeConta = null;

		Short indicadorUsoNomeFantasiaConta = null;

		if (clienteActionForm.get("indicadorExibicaoNomeConta") != null) {

			indicadorExibicaoNomeConta = (String) clienteActionForm.get(
					"indicadorExibicaoNomeConta").toString();

			if (indicadorExibicaoNomeConta
					.equals(Cliente.INDICADOR_NOME_FANTASIA.toString())) {

				indicadorUsoNomeFantasiaConta = 1;
			} else {
				indicadorUsoNomeFantasiaConta = 2;
			}

		}

        Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

        //Verifica qual é o próximo passo para a execução do processo
        String destinoPagina = httpServletRequest.getParameter("destino");

        
		String tipoPessoaForm =  tipoPessoa.toString() ; 
        
        filtroClienteTipo.adicionarParametro(new ParametroSimples(
                FiltroClienteTipo.ID, tipoPessoaForm));
        tipoPessoa = ((ClienteTipo) fachada.pesquisar(filtroClienteTipo,
                ClienteTipo.class.getName()).iterator().next())
                .getIndicadorPessoaFisicaJuridica();

        // Verifica o destino porque se o usuário tentar concluir o processo
        // nesta página, não é necessário verificar a tela de confirmação
        if (destinoPagina != null && !destinoPagina.trim().equals("")) {

    		/**
    		 * Autor: Mariana Victor
    		 * Data:  04/01/2010
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
    		 * Data:  04/01/2010
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
            if (tipoPessoa != null ){
            	if( tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA)) {
            
	            	clienteActionForm.set("indicadorPessoaFisicaJuridica", ""
	    					+ ClienteTipo.INDICADOR_PESSOA_JURIDICA);
	
	    			// Vai para Pessoa Juridica mas tem dados existentes em pessoa fisica
	    			String cpf = (String) clienteActionForm.get("cpf");
	    			String rg = (String) clienteActionForm.get("rg");
	    			String dataEmissao = (String) clienteActionForm.get("dataEmissao");
	    			Integer idOrgaoExpedidor = (Integer) clienteActionForm.get("idOrgaoExpedidor");
	    			Integer idUnidadeFederacao = (Integer) clienteActionForm.get("idUnidadeFederacao");
	    			String dataNascimento = (String) clienteActionForm.get("dataNascimento");
	    			Integer idProfissao = (Integer) clienteActionForm.get("idProfissao");
	    			Integer idPessoaSexo = (Integer) clienteActionForm.get("idPessoaSexo");
	
	            	// Vai para Pessoa Juridica mas tem dados existentes em pessoa fisica
	                if ((idPessoaSexo != null && idPessoaSexo != ConstantesSistema.NUMERO_NAO_INFORMADO)
	    					|| (cpf != null && !cpf.trim().equalsIgnoreCase(""))
	    					|| (rg != null && !rg.trim().equalsIgnoreCase(""))
	    					|| (dataEmissao != null && !dataEmissao.trim().equalsIgnoreCase(""))
	    					|| (dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase(""))
	    					|| (idOrgaoExpedidor != null && idOrgaoExpedidor != ConstantesSistema.NUMERO_NAO_INFORMADO)
	    					|| (idUnidadeFederacao != null && idUnidadeFederacao != ConstantesSistema.NUMERO_NAO_INFORMADO)
	    					|| (idProfissao != null && idProfissao != ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	                    return montarPaginaConfirmacaoWizard(
	                            "confirmacao.processo.cliente.dependencia.pessoa_juridica",
	                            httpServletRequest, actionMapping);
	                }
	            }else if (tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
	            	// Vai para Pessoa Fisica mas tem dados existentes em pessoa juridica
	    			clienteActionForm.set("indicadorPessoaFisicaJuridica", ""
	    					+ ClienteTipo.INDICADOR_PESSOA_FISICA);
	            	String cnpj = (String) clienteActionForm.get("cnpj");
	    			Integer idRamoAtividade = (Integer) clienteActionForm.get("idRamoAtividade");
	    			String codigoClienteResponsavel = (String) clienteActionForm.get("codigoClienteResponsavel");
	
	    			if ((cnpj != null && !cnpj.trim().equalsIgnoreCase(""))
	    					|| (idRamoAtividade != null && idRamoAtividade != ConstantesSistema.NUMERO_NAO_INFORMADO)
	    					|| (codigoClienteResponsavel != null && !codigoClienteResponsavel
	    							.trim().equalsIgnoreCase(""))) {
	                    return montarPaginaConfirmacaoWizard(
	                            "confirmacao.processo.cliente.dependencia.pessoa_fisica",
	                            httpServletRequest, actionMapping);
	                }
	            }	
            }
        }

        return retorno;
    }
}
