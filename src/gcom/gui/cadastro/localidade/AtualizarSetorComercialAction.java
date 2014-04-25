package gcom.gui.cadastro.localidade;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarSetorComercialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a sessão
        HttpSession sessao = this.getSessao(httpServletRequest);
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        PesquisarAtualizarSetorComercialActionForm form = 
        	(PesquisarAtualizarSetorComercialActionForm) actionForm;
        
        // ------------ REGISTRAR TRANSAÇÃO ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SETOR_COMERCIAL_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_SETOR_COMERCIAL_ATUALIZAR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        
        //------------ REGISTRAR TRANSAÇÃO ----------------

        String setorComercialID = form.getSetorComercialID();
        String localidadeID = form.getLocalidadeID();
        String setorComercialCD = form.getSetorComercialCD();
        String setorComercialNome = form.getSetorComercialNome();
        String municipioID = form.getMunicipioID();
        String indicadorUso = form.getIndicadorUso();
        String indicadorSetorAlternativo = form.getIndicadorSetorAlternativo();

        if (setorComercialID == null || setorComercialID.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.setor_comercial_nao_informado");
        } else if (localidadeID == null || localidadeID.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.localidade_nao_informada");
        } else if (setorComercialCD == null
                || setorComercialCD.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.codigo_setor_comercial_nao_informado");
        } else if (municipioID == null || municipioID.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.municipio_nao_informado");
        } else if (setorComercialNome == null
                || setorComercialNome.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.nome_setor_comercial_nao_informado");
        } else if (indicadorUso == null || indicadorUso.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.indicador_uso_nao_informado");
        } else if (indicadorSetorAlternativo == null || indicadorSetorAlternativo.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.setor_alternativo_nao_informado");
        }

        else {
        	
        	//Verificar existencia de Quadra associada
        	FiltroQuadra filtroQuadra = new FiltroQuadra();
        	filtroQuadra.adicionarParametro( new ParametroSimples(
        			FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));
        	
        	Collection setorComercialAssociadoQuadra = this.getFachada()
        		.pesquisar( filtroQuadra, Quadra.class.getName() );
	        	
        	if ( setorComercialAssociadoQuadra != null && !setorComercialAssociadoQuadra.isEmpty() &&
        			new Short(indicadorSetorAlternativo).equals(ConstantesSistema.INDICADOR_USO_ATIVO ) ) {
        		
        				throw new ActionServletException("atencao.setor_associado_quadra_nao_alternativo");
	        }
        	//Verificar a existencia de Rota associada
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro( new ParametroSimples ( FiltroRota.SETOR_COMERCIAL_ID,
        			setorComercialID));
        	
        	Collection setorComercialAssociadoRota = this.getFachada().pesquisar( filtroRota, Rota.class.getName() );

        	Iterator iteratorComercialAssociadoRota = setorComercialAssociadoRota.iterator();
        	Rota rota = null;
        
        	while ( iteratorComercialAssociadoRota.hasNext() ) {
        	
        		rota = (Rota) iteratorComercialAssociadoRota.next();
	            
        		if ( new Short(indicadorSetorAlternativo).equals( ConstantesSistema.INDICADOR_USO_DESATIVO )&&
	        				rota.getIndicadorRotaAlternativa().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ) {
	        		
	        				throw new ActionServletException("atencao.setor_associado_rota_alternativa");
		        }
        	}
        	
        	//Validando os dados informados pelo usuário.
            Municipio municipioNovo = (Municipio) pesquisarObjeto(municipioID,3);
            
            if (municipioNovo == null) {
                throw new ActionServletException(
                        "atencao.pesquisa.municipio_inexistente");
            }

            Short indicadorUsoNovo = new Short(indicadorUso);

            SetorComercial setorComercialAtual = 
            	(SetorComercial) sessao.getAttribute("setorComercialManter");

            if (setorComercialAtual == null) {
                //Setor comercial nao encontrado
                throw new ActionServletException("atencao.processo.setorComercialNaoCadastrada");
            }
			setorComercialAtual.setDescricao(setorComercialNome);
			setorComercialAtual.setMunicipio(municipioNovo);
			setorComercialAtual.setIndicadorUso(indicadorUsoNovo);
			setorComercialAtual.setIndicadorBloqueio(new Short(form.getIndicadorBloqueio()));
			setorComercialAtual.setIndicadorSetorAlternativo(new Short(indicadorSetorAlternativo));
			
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			setorComercialAtual.setOperacaoEfetuada(operacaoEfetuada);
			setorComercialAtual.adicionarUsuario(usuario, 
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(setorComercialAtual);
			//------------ REGISTRAR TRANSAÇÃO ----------------  
			
			Collection colecaoFontes = (Collection)
				this.getSessao(httpServletRequest).getAttribute("colecaoFonteCaptacao");

			this.getFachada().atualizarSetorComercial(setorComercialAtual,colecaoFontes);
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeID));
			
			Collection colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
			
			Localidade localidade = (Localidade) colecaoLocalidade.iterator().next();

			montarPaginaSucesso(httpServletRequest,
			        "Setor Comercial de código  " + 
			        setorComercialAtual.getCodigo() + 
			        "  da localidade "+ localidade.getId() + " - " + localidade.getDescricao().toUpperCase() +" atualizado com sucesso.",
			        " Realizar outra Manutenção de Setor Comercial",
			        "exibirFiltrarSetorComercialAction.do?menu=sim");
        }

        //devolve o mapeamento de retorno
        return retorno;
    }

    /**
     * 
     * @param objetoPesquisa
     * @param objetoPai
     * @param tipoObjeto
     * @return
     * @throws RemoteException
     * @throws ErroRepositorioException
     */

    private Object pesquisarObjeto(String objetoPesquisa, int tipoObjeto) {

        Object retorno = null;
        Collection colecaoPesquisa = null;

        switch (tipoObjeto) {
        //Setor Comercial
        case 2:

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID, objetoPesquisa));

            colecaoPesquisa = this.getFachada().pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
                retorno = Util.retonarObjetoDeColecao(colecaoPesquisa);
            }

            break;

        case 3:

            FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.ID, objetoPesquisa));

            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = this.getFachada().pesquisar(filtroMunicipio,
                    Municipio.class.getName());

            if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
                retorno = Util.retonarObjetoDeColecao(colecaoPesquisa);
            }

            break;

        default:
            break;
        }

        return retorno;
    }

}
