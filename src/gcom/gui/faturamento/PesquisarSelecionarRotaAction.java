package gcom.gui.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class PesquisarSelecionarRotaAction extends GcomAction {

    Collection colecaoPesquisa;

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirSelecionarRota");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Formulário de pesquisa
        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //Criando o filtro e o objeto de retorno da pesquisa de rota
        FiltroRota filtroRota = new FiltroRota();
        
        filtroRota.setCampoOrderBy(FiltroRota.FATURAMENTO_GRUPO_ID, 
        		FiltroRota.GERENCIA_REGIONAL_ID,
        		FiltroRota.LOCALIDADE_ID,
        		FiltroRota.SETOR_COMERCIAL_CODIGO,
        		FiltroRota.ID_ROTA);
        
        //filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.UNIDADE_NEGOCIO_NOME_ABREVIADO);
        filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.UNIDADE_NEGOCIO);
        
        //Objetos que serão retornados pelo hibernate.
        filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade.gerenciaRegional");
        
        Collection colecaoRota = null;

        //Validando os campos do formulario
        boolean peloMenosUmParametroInformado = false;

        // Grupo de Faturamento
        if (pesquisarActionForm.get("idGrupoFaturamento") != null) {

            String grupoFaturamentoJSP = (String) pesquisarActionForm
                    .get("idGrupoFaturamento");

            if (!grupoFaturamentoJSP.equalsIgnoreCase(String
                    .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
                peloMenosUmParametroInformado = true;

                filtroRota.adicionarParametro(new ParametroSimples(
                        FiltroRota.FATURAMENTO_GRUPO_ID, grupoFaturamentoJSP));
            }
        }

        //Gerência Regional
        if (pesquisarActionForm.get("idGerenciaRegional") != null) {

            String gerenciaRegionalJSP = (String) pesquisarActionForm
                    .get("idGerenciaRegional");

            if (!gerenciaRegionalJSP.equalsIgnoreCase(String
                    .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
                peloMenosUmParametroInformado = true;

                filtroRota.adicionarParametro(new ParametroSimples(
                        FiltroRota.GERENCIA_REGIONAL_ID, gerenciaRegionalJSP));
            }
        }

        // Localidade
        String localidadeJSP = null;
        if (pesquisarActionForm.get("idLocalidade") != null) {

            localidadeJSP = (String) pesquisarActionForm.get("idLocalidade");

            if (!localidadeJSP.equalsIgnoreCase(String.valueOf(""))) {

                pesquisarLocalidade(localidadeJSP, fachada);

                peloMenosUmParametroInformado = true;

                filtroRota.adicionarParametro(new ParametroSimples(
                        FiltroRota.LOCALIDADE_ID, localidadeJSP));
            }
        }

        // Setor comercial da localidade escolhida
        String setorComercialJSP = null;
        if (pesquisarActionForm.get("numeroSetorComercial") != null) {

            setorComercialJSP = (String) pesquisarActionForm
                    .get("numeroSetorComercial");

            if (!setorComercialJSP.equalsIgnoreCase(String.valueOf(""))) {

                if (localidadeJSP == null || localidadeJSP.equalsIgnoreCase("")) {
                    throw new ActionServletException(
                            "atencao.localidade_nao_informada");
                }

                pesquisarSetorComercial(localidadeJSP, setorComercialJSP,
                        fachada);

                peloMenosUmParametroInformado = true;

                filtroRota.adicionarParametro(new ParametroSimples(
                        FiltroRota.SETOR_COMERCIAL_CODIGO, setorComercialJSP));
            }
        }
        
        
        //Rota
        String rotaJSP = null;
        if (pesquisarActionForm.get("idRota") != null) {

        	rotaJSP = (String) pesquisarActionForm.get("idRota");

            if (!rotaJSP.equalsIgnoreCase(String.valueOf(""))) {
            	
            	if (setorComercialJSP == null || setorComercialJSP.equalsIgnoreCase("")) {
                    throw new ActionServletException(
                            "atencao.setor_comercial_nao_informado");
                }

                peloMenosUmParametroInformado = true;

                filtroRota.adicionarParametro(new ParametroSimples(
                        FiltroRota.CODIGO_ROTA, rotaJSP));
            }
        }

        
        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }

        //Retorna a(s) rota(s)
        colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());

        if (colecaoRota == null || colecaoRota.isEmpty()) {
            //Nenhuma rota cadastrada de acordo com os parâmetros passados
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "rota");
        } else if (colecaoRota.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
            //O limite de registros foi ultrapassado
            throw new ActionServletException("atencao.pesquisa.muitosregistros");
        } else {
        	
        	//Limpa o formulário
        	pesquisarActionForm.set("idGrupoFaturamento", String
                    .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
        	pesquisarActionForm.set("idGerenciaRegional", String
                    .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
        	pesquisarActionForm.set("idLocalidade", "");
        	pesquisarActionForm.set("descricaoLocalidade", "");
        	pesquisarActionForm.set("numeroSetorComercial", "");
        	pesquisarActionForm.set("descricaoSetorComercial", "");
        	pesquisarActionForm.set("idRota", "");
        	pesquisarActionForm.set("descricaoRota", "");
        	
            sessao.setAttribute("colecaoRotasSelecionadas", colecaoRota);
        }
        
        
        httpServletRequest.setAttribute("nomeCampo", "botaoSelecionar");

        //devolve o mapeamento de retorno
        return retorno;
    }

    /**
     * Pesquisa de localidade
     * 
     * @param localidadeID
     * @param fachada
     */
    private void pesquisarLocalidade(String localidadeID, Fachada fachada) {

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, localidadeID));

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class
                .getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            throw new ActionServletException(
                    "atencao.pesquisa.localidade_inexistente");
        }
    }

    /**
     * Pesquisa de setor comercial
     * 
     * @param localidadeID
     * @param setorComercialCD
     * @param fachada
     */
    private void pesquisarSetorComercial(String localidadeID,
            String setorComercialCD, Fachada fachada) {

        FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

        filtroSetorComercial.adicionarParametro(new ParametroSimples(
                FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

        filtroSetorComercial.adicionarParametro(new ParametroSimples(
                FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

        filtroSetorComercial.adicionarParametro(new ParametroSimples(
                FiltroSetorComercial.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                SetorComercial.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            throw new ActionServletException(
                    "atencao.processo.setorComercialNaoCadastrada");
        }
    }

}
