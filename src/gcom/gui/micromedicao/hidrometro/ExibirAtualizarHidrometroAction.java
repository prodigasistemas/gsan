package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.FiltroHidrometroRelojoaria;
import gcom.micromedicao.hidrometro.FiltroHidrometroTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroRelojoaria;
import gcom.micromedicao.hidrometro.HidrometroTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
 * @created 13 de Setembro de 2005
 */
public class ExibirAtualizarHidrometroAction extends GcomAction {
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

        ActionForward retorno = actionMapping
                .findForward("atualizarHidrometro");

        //Obtém o action form
        AtualizarHidrometroActionForm atualizarHidrometroActionForm = (AtualizarHidrometroActionForm) actionForm;

        Collection colecaoHidrometroCapacidade = null;

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        String idHidrometro = httpServletRequest
                .getParameter("idRegistroAtualizacao");
        if (idHidrometro == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				idHidrometro = (String) sessao.getAttribute("idHidrometro");
			}else{
				idHidrometro = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}

        //Obtém a facahda
        Fachada fachada = Fachada.getInstancia();
        Hidrometro hidrometro = null;

        //Verifica se os objetos estão na sessão
        if (idHidrometro != null && !idHidrometro.equals("")) {

            FiltroHidrometro filtroHidrometro = new FiltroHidrometro();

            filtroHidrometro.adicionarParametro(new ParametroSimples(
                    FiltroHidrometro.ID, idHidrometro));
            
            filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroRelojoaria");

            Collection hidrometros = fachada.pesquisar(filtroHidrometro,
                    Hidrometro.class.getName());

            if (hidrometros != null && !hidrometros.isEmpty()) {
            	hidrometro = (Hidrometro) ((List) hidrometros)
                        .get(0);
                sessao.setAttribute("hidrometro", hidrometro);
                atualizarHidrometroActionForm
                        .setNumeroHidrometro(formatarResultado(hidrometro
                                .getNumero()));
                atualizarHidrometroActionForm.setAnoFabricacao(formatarResultado(""
                        + hidrometro.getAnoFabricacao()));
                SimpleDateFormat dataFormatoAtual = new SimpleDateFormat(
                        "dd/MM/yyyy");
                //joga em dataInicial a parte da data
                String dataAquisicao = dataFormatoAtual.format(hidrometro
                        .getDataAquisicao());

                atualizarHidrometroActionForm
                        .setDataAquisicao(formatarResultado(dataAquisicao));
                atualizarHidrometroActionForm
                        .setIdHidrometroCapacidade(formatarResultado(""
                                + hidrometro.getHidrometroCapacidade().getId()));
                atualizarHidrometroActionForm
                        .setIdHidrometroClasseMetrologica(formatarResultado(""
                                + hidrometro.getHidrometroClasseMetrologica()
                                        .getId()));
                atualizarHidrometroActionForm
                        .setIdHidrometroDiametro(formatarResultado(""
                                + hidrometro.getHidrometroDiametro().getId()));
                atualizarHidrometroActionForm.setIdHidrometroMarca(formatarResultado(""
                        + hidrometro.getHidrometroMarca().getId()));
                atualizarHidrometroActionForm.setIdHidrometroTipo(formatarResultado(""
                        + hidrometro.getHidrometroTipo().getId()));
                atualizarHidrometroActionForm
                        .setIndicadorMacromedidor(formatarResultado(""
                                + hidrometro.getIndicadorMacromedidor()));
                atualizarHidrometroActionForm
                        .setIdNumeroDigitosLeitura(formatarResultado(""
                                + hidrometro.getNumeroDigitosLeitura()));
                
                if(hidrometro.getHidrometroRelojoaria() != null && 
                		!hidrometro.getHidrometroRelojoaria().equals("")){
                  atualizarHidrometroActionForm.setIdHidrometroRelojoaria(formatarResultado(""
                         + hidrometro.getHidrometroRelojoaria().getId()));
                }else{
                	atualizarHidrometroActionForm.setIdHidrometroRelojoaria("");
                }
                
                atualizarHidrometroActionForm
                	.setVazaoTransicao(""+ Util.formatarMoedaReal(hidrometro.getVazaoTransicao()))  ;
            
                atualizarHidrometroActionForm
            		.setVazaoNominal(""+ Util.formatarMoedaReal(hidrometro.getVazaoNominal()));

                atualizarHidrometroActionForm
            		.setVazaoMinima(""+  Util.formatarMoedaReal(hidrometro.getVazaoMinima())) ;
                
                atualizarHidrometroActionForm
                	.setNotaFiscal( formatarResultado(""+ hidrometro.getNotaFiscal() ) ) ;
                
                atualizarHidrometroActionForm
                	.setTempoGarantiaAnos( formatarResultado( "" +  hidrometro.getTempoGarantiaAnos() ) ) ;
            
                
                // Imóvel em que o hidrômetro está instalado
                String idImovel = fachada.pesquisarImovelHidrometroInstalado(hidrometro.getId());
                
                atualizarHidrometroActionForm.setIdImovel(idImovel);
            }
            else
            {
            	throw new ActionServletException("atencao.atualizacao.timestamp");
            }

            //Filtro de hidrômetro classe metrológica para obter todas as
            // classes metrológicas ativas
            FiltroHidrometroClasseMetrologica filtroHidrometroClasseMetrologica = new FiltroHidrometroClasseMetrologica();

            filtroHidrometroClasseMetrologica
                    .adicionarParametro(new ParametroSimples(
                            FiltroHidrometroClasseMetrologica.INDICADOR_USO,
                            ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroClasseMetrologica
                    .setCampoOrderBy(FiltroHidrometroClasseMetrologica.DESCRICAO);

            //Pesquisa a coleção de classe metrológica
            Collection colecaoHidrometroClasseMetrologica = fachada.pesquisar(
                    filtroHidrometroClasseMetrologica,
                    HidrometroClasseMetrologica.class.getName());

            //Filtro de hidrômetro marca para obter todas as marcas de
            // hidrômetros ativas
            FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

            filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
                    FiltroHidrometroMarca.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroMarca
                    .setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);

            //Pesquisa a coleção de hidrômetro marca
            Collection colecaoHidrometroMarca = fachada.pesquisar(
                    filtroHidrometroMarca, HidrometroMarca.class.getName());

            //Filtro de hidrômetro diâmetro para obter todos os diâmetros de
            // hidrômetros ativos
            FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

            filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
                    FiltroHidrometroDiametro.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroDiametro
                    .setCampoOrderBy(FiltroHidrometroDiametro.NUMERO_ORDEM);

            //Pesquisa a coleção de hidrômetro capacidade
            Collection colecaoHidrometroDiametro = fachada.pesquisar(
                    filtroHidrometroDiametro, HidrometroDiametro.class
                            .getName());

            //Filtro de hidrômetro capacidade para obter todos as capacidade de
            // hidrômetros ativas
            FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

            filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
                    FiltroHidrometroCapacidade.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroCapacidade
                    .setCampoOrderBy(FiltroHidrometroCapacidade.NUMERO_ORDEM);

            //Pesquisa a coleção de hidrômetro capacidade
            colecaoHidrometroCapacidade = fachada.pesquisar(
                    filtroHidrometroCapacidade, HidrometroCapacidade.class
                            .getName());

            //Filtro de hidrômetro tipo para obter todos os tipos de
            // hidrômetros ativos
            FiltroHidrometroTipo filtroHidrometroTipo = new FiltroHidrometroTipo();

            filtroHidrometroTipo.adicionarParametro(new ParametroSimples(
                    FiltroHidrometroTipo.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroTipo
                    .setCampoOrderBy(FiltroHidrometroTipo.DESCRICAO);

            //Pesquisa a coleção de hidrômetro tipo
            Collection colecaoHidrometroTipo = fachada.pesquisar(
                    filtroHidrometroTipo, HidrometroTipo.class.getName());
            
            // Filtro de hidrômetro relojoaria para obter todos os hidrômetros relojoaria ativos
            FiltroHidrometroRelojoaria filtroHidrometroRelojoaria = new FiltroHidrometroRelojoaria();

            filtroHidrometroRelojoaria.adicionarParametro(new ParametroSimples(
                    FiltroHidrometroRelojoaria.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroHidrometroRelojoaria
                    .setCampoOrderBy(FiltroHidrometroRelojoaria.DESCRICAO);

            //Pesquisa a coleção de hidrômetro tipo
            Collection colecaoHidrometroRelojoaria = fachada.pesquisar(
                    filtroHidrometroRelojoaria, HidrometroRelojoaria.class.getName());

            //Envia as coleções na sessão
            sessao.setAttribute("colecaoHidrometroClasseMetrologica",
                    colecaoHidrometroClasseMetrologica);
            sessao.setAttribute("colecaoHidrometroMarca",
                    colecaoHidrometroMarca);
            sessao.setAttribute("colecaoHidrometroDiametro",
                    colecaoHidrometroDiametro);
            sessao.setAttribute("colecaoHidrometroCapacidade",
                    colecaoHidrometroCapacidade);
            sessao.setAttribute("colecaoHidrometroTipo", colecaoHidrometroTipo);
            sessao.setAttribute("colecaoHidrometroRelojoaria", colecaoHidrometroRelojoaria);
        }

        //Filtro de hidrômetro capacidade para obter capacidade de hidrômetro
        // de acordo com o id
        FiltroHidrometroCapacidade filtroHidrometroCapacidadeNumeroDigitos = new FiltroHidrometroCapacidade();

        //Verifica se a coleção de hidrometro capacidade é diferente de null
        if (colecaoHidrometroCapacidade != null
                && !colecaoHidrometroCapacidade.isEmpty()) {

            //Obtém o primeiro objeto da collection

        	HidrometroCapacidade hidrometroCapacidade = null;
        	if(hidrometro != null && hidrometro.getHidrometroCapacidade() != null){
        		hidrometroCapacidade = (HidrometroCapacidade) hidrometro.getHidrometroCapacidade();
        	}else{
        		Iterator colecaoHidrometroCapacidadeIterator = colecaoHidrometroCapacidade.iterator();
        		hidrometroCapacidade = (HidrometroCapacidade)colecaoHidrometroCapacidadeIterator.next();
        	}

            //Filtra pelo primeiro objeto da collection
            filtroHidrometroCapacidadeNumeroDigitos
                    .adicionarParametro(new ParametroSimples(
                            FiltroHidrometroCapacidade.ID, hidrometroCapacidade
                                    .getId()));
        } else {
            //Filtra pelo id selecionado no combobox
            filtroHidrometroCapacidadeNumeroDigitos
                    .adicionarParametro(new ParametroSimples(
                            FiltroHidrometroCapacidade.ID, new Integer(
                                    atualizarHidrometroActionForm
                                            .getIdHidrometroCapacidade())));

        }

        //Pesquisa o número de dígitos de acordo com o filtro
        Collection colecaoHidrometroCapacidadeNumeroDigitos = fachada
                .pesquisar(filtroHidrometroCapacidadeNumeroDigitos,
                        HidrometroCapacidade.class.getName());

        //Retorna o objeto pesquisado
        HidrometroCapacidade hidrometroCapacidadeNumeroDigitos = (HidrometroCapacidade) Util
                .retonarObjetoDeColecao(colecaoHidrometroCapacidadeNumeroDigitos);

        if(hidrometroCapacidadeNumeroDigitos != null && !hidrometroCapacidadeNumeroDigitos.equals(""))
        {
        	//Obtém as leituras
        	Integer leituraMinimo = new Integer(hidrometroCapacidadeNumeroDigitos
                .getLeituraMinimo().toString());
        	Integer leituraMaximo = new Integer(hidrometroCapacidadeNumeroDigitos
                .getLeituraMaximo().toString());
        	//Obtém a quantidade da diferença
        	int quantidade = (leituraMaximo.intValue() - leituraMinimo.intValue()) + 1;
        	Collection colecaoIntervalo = new ArrayList();

            //Adiciona a quantidade da diferença na coleção
            for (int i = 0; i < quantidade; i++) {
            	Hidrometro hidrometroDigitos = new Hidrometro();
            	Integer numero = leituraMinimo.intValue() + i;
            	hidrometroDigitos.setNumeroDigitosLeitura(new Short(numero+ ""));
                colecaoIntervalo.add(hidrometroDigitos);
            }
            // Envia pela sessão
            sessao.setAttribute("colecaoIntervalo", colecaoIntervalo);
        }
        
        // caso ainda não tenha sido setado o nome campo(na primeira vez)
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", "manter");
		}
		
		atualizarHidrometroActionForm.setIdHidrometro(idHidrometro);
		return retorno;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param parametro
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    private String formatarResultado(String parametro) {
        if (parametro != null && !parametro.trim().equals("")) {
            if (parametro.equals("null")) {
                return "";
            } else {
                return parametro.trim();
            }
        } else {
            return "";
        }
    }

}
