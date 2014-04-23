package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
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
 * Description of the Class
 * 
 * @author compesa
 * @created 1 de Julho de 2004
 */
public class AtualizarBairroAction extends GcomAction {
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
        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        BairroActionForm bairroActionForm = (BairroActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
		
        /*
		 * [UC0107] Registrar Transação
		 * 
		 */
        
//		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_BAIRRO_ATUALIZAR,
//				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//
//		Operacao operacao = new Operacao();
//		operacao.setId(Operacao.OPERACAO_BAIRRO_ATUALIZAR);
//
//		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transação


        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        Bairro bairro = (Bairro) sessao.getAttribute("bairro");

        String idMunicipio = (String) bairroActionForm.getIdMunicipio();

        Municipio municipio = null;

        if (idMunicipio != null && !idMunicipio.equals("")) {
            FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.ID, idMunicipio));
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection municipios = fachada.pesquisar(filtroMunicipio,
                    Municipio.class.getName());

            if (municipios != null && !municipios.isEmpty()) {
                //O municipio foi encontrado
                Iterator municipioIterator = municipios.iterator();

                municipio = (Municipio) municipioIterator.next();

            } else {
                throw new ActionServletException(
                        "atencao.pesquisa_inexistente", null, "Município");
            }

        }

        int codigoBairro = 0;

        String codigoBairroPesquisar = (String) bairroActionForm
                .getCodigoBairro();

       // FiltroBairro filtroBairro = new FiltroBairro();

        codigoBairro = Integer.parseInt(codigoBairroPesquisar);

        Integer codigoBairroPrefeitura = null;
        if (bairroActionForm.getCodigoBairroPrefeitura() != null
                && !bairroActionForm.getCodigoBairroPrefeitura()
                        .equalsIgnoreCase("")) {
            codigoBairroPrefeitura = new Integer(bairroActionForm
                    .getCodigoBairroPrefeitura());
        }

        Short indicadorDeUso = new Short(bairroActionForm.getIndicadorUso());

        
        //Verifica se o nome do bairro é diferente do atual, caso sim, verifica se ja existe o bairro na base  
        if (!bairro.getNome().equalsIgnoreCase(bairroActionForm.getNomeBairro())){
        	FiltroBairro filtroBairroExistente = new FiltroBairro();
    		filtroBairroExistente.adicionarParametro(new ParametroSimples(
    				FiltroBairro.MUNICIPIO_ID, bairroActionForm.getIdMunicipio()));
    		filtroBairroExistente.adicionarParametro(new ParametroSimples(
    				FiltroBairro.NOME, bairroActionForm.getNomeBairro()));
    		filtroBairroExistente
    				.adicionarCaminhoParaCarregamentoEntidade("municipio");
    		Collection collectionBairro = (Collection) fachada.pesquisar(
    				filtroBairroExistente, Bairro.class.getName());

    		if (collectionBairro != null && !collectionBairro.isEmpty()) {
    			throw new ActionServletException(
    					"atencao.bairro_existente_municipio");
    		}
        }
     
        Collection colecaoBairroArea = null;
		
		if (sessao.getAttribute("colecaoBairroArea") != null){
			colecaoBairroArea = (Collection)sessao.getAttribute("colecaoBairroArea");
		}
        if (colecaoBairroArea == null || colecaoBairroArea.isEmpty()){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Áreas do Bairro");
		}
        
        //seta os campos para serem atualizados
        bairro.setMunicipio(municipio);
        bairro.setCodigo(codigoBairro);
        bairro.setNome(bairroActionForm.getNomeBairro());
        bairro.setCodigoBairroPrefeitura(codigoBairroPrefeitura);
        bairro.setIndicadorUso(indicadorDeUso);

        //regitrando operacao
//		bairro.setOperacaoEfetuada(operacaoEfetuada);
//		bairro.adicionarUsuario(Usuario.USUARIO_TESTE,	UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//		registradorOperacao.registrarOperacao(bairro);
        
		fachada.atualizarBairro(bairro,colecaoBairroArea,
				(Collection)sessao.getAttribute("colecaoBairroAreaRemover"),usuarioLogado);

        montarPaginaSucesso(httpServletRequest, "Bairro de código "
                + bairro.getCodigo() + " do município "+ bairro.getMunicipio().getNome() + " atualizado com sucesso.",
                "Realizar outra Manutenção de Bairro",
                "exibirFiltrarBairroAction.do?menu=sim");

        return retorno;
    }

}
