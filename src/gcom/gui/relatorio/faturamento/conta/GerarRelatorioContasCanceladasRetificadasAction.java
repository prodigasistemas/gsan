package gcom.gui.relatorio.faturamento.conta;

import gcom.gui.ActionServletException;
import gcom.gui.relatorio.faturamento.RelatorioContasCanceladasRetificadasActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.conta.RelatorioContasCanceladas;
import gcom.relatorio.faturamento.conta.RelatorioContasCanceladasRetificadasHelper;
import gcom.relatorio.faturamento.conta.RelatorioContasRetificadas;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioContasCanceladasRetificadasAction extends ExibidorProcessamentoTarefaRelatorio {

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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("telaSucesso");

        RelatorioContasCanceladasRetificadasActionForm form = (RelatorioContasCanceladasRetificadasActionForm) actionForm;
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        Usuario usuarioLogado = (Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado");
        
        sessao.removeAttribute("indicadorAtualizar");
        
        String mesAno = (String)form.getMesAno();
        String unidadeNegocio = (String) form.getIdUnidadeNegocio();
        
        boolean parametroInformado = false;
        
        RelatorioContasCanceladasRetificadasHelper helper = new RelatorioContasCanceladasRetificadasHelper();
        
        //recebe valores
        String anoMes = form.getMesAno();
        String localidade = form.getLocalidadeFiltro();
        String[] motivo = form.getIdMotivo();
        String[] categoria = form.getIdCategoria();
        String[] perfil = form.getIdPerfil();
        String[] esfera = form.getIdEsferaPoder();
        String responsavel = form.getResponsavelFiltro();
        String valor = form.getValor();
        String relatorioTipo = form.getRelatorioTipo();
        String ordenacaoTipo = form.getOrdenacaoTipo();
        String periodoInicial = form.getPeriodoInicial();
        String periodoFinal = form.getPeriodoFinal();
        String grupo = form.getGrupo();
        String idGerenciaRegional = form.getIdGerenciaRegional();
        
        if (!"".equals(periodoInicial)) {
        	Date data = Util.converteStringParaDate(periodoInicial);
			if (data == null) {
				throw new ActionServletException("atencao.data.inicial.invalida");
			}
        }
        
        if (!"".equals(periodoFinal)) {
			Date data = Util.converteStringParaDate(periodoFinal);			
			if (data == null) {
				throw new ActionServletException("atencao.data.final.invalida");
			}
        }
    	if (periodoInicial != null && !periodoInicial.trim().equals("")
				&& periodoFinal != null && !periodoFinal.trim().equals("")) {
			if ((Util.converteStringParaDate(periodoInicial)).compareTo(Util
					.converteStringParaDate(periodoFinal)) > 0) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido");
			}
		}
		
        
        //mesAno
        if(mesAno != null && !mesAno.equals("")){
        	parametroInformado = true;
        	helper.setMesAno(mesAno);
        }
        
        if(periodoInicial != null && !periodoInicial.equals("")){
        	parametroInformado = true;
        	helper.setPeriodoInicial(periodoInicial);
        }
        
        if(periodoFinal != null && !periodoFinal.equals("")){
        	parametroInformado = true;
        	helper.setPeriodoFinal(periodoFinal);
        }
        
        if(relatorioTipo != null && !relatorioTipo.equals("")){
        	parametroInformado = true;
        	
        	if(relatorioTipo.equals(ConstantesSistema.SINTETICO+"")){
        		helper.setRelatorioTipo("SINTETICO");	
        	}else if(relatorioTipo.equals(ConstantesSistema.ANALITICO+"")){
        		helper.setRelatorioTipo("ANALITICO");	
        	}
        }
        //localidade
        if(localidade != null && !localidade.equals("")){
        	parametroInformado = true;
        	helper.setIdLocalidade(localidade);
        }
        //motivo
        if(motivo != null && motivo.length > 0 && !motivo[0].equals("-1")){
        	parametroInformado = true;
        	helper.setMotivoArray(motivo);
        }
        
        if(unidadeNegocio != null && !unidadeNegocio.equals("") && !unidadeNegocio.equals("-1")){
        	parametroInformado = true;
        	helper.setUnidadeNegocio(unidadeNegocio);
        }else{
        	helper.setUnidadeNegocio("");
        }
        	
        
        //categoria
        if(categoria != null && categoria.length > 0 && !categoria[0].equals("-1")){
        	parametroInformado = true;
        	helper.setCategoriaArray(categoria);
        }
        //perfil do imovel
        if(perfil != null && perfil.length > 0 && !perfil[0].equals("-1")){
        	parametroInformado = true;
        	helper.setPerfilArray(perfil);
        }
        //esfera
        if(esfera != null && esfera.length > 0 && !esfera[0].equals("-1")){
        	parametroInformado = true;
        	helper.setEsferaArray(esfera);
        }
        //responsavel
        if(responsavel != null && !responsavel.equals("")){
        	
        	
        	FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, responsavel));

			// Retorna o usuário
			Collection colecaoPesquisa = 
				this.getFachada().pesquisar(filtroUsuario,
					Usuario.class.getName());
			
			if(colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				parametroInformado = true;
				helper.setIdResponsavel(usuario.getId().toString());
			}


        }
        //valor
        if(valor != null && !valor.equals("")){
        	parametroInformado = true;
        	helper.setValor(valor);
        }

        if(ordenacaoTipo != null && !ordenacaoTipo.equals("")){
        	parametroInformado = true;
        	helper.setOrdenacaoTipo(ordenacaoTipo);
        }

        if(grupo != null && !grupo.equals("")){
        	
        	helper.setGrupo(grupo);
        }

        if(idGerenciaRegional != null && !idGerenciaRegional.equals("")&&
        		!idGerenciaRegional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	helper.setIdGerenciaRegional(idGerenciaRegional);
        }
        
        
        String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

        if(parametroInformado){
        	//chamar metodo de filtragem
        	try {
        		/**
        		 * Inlcuir Opção de Contas Prescritas no relatório de Contas Canceladas/Retidas
        		 * 
        		 * @author Wellington Rocha
        		 * @date 04/06/2012*/
        		if(form.getTipoConta().equals("1") || form.getTipoConta().equals("3") ){
            		 
        			RelatorioContasCanceladas relatorioContasCanceladas = 
        				new RelatorioContasCanceladas(usuarioLogado);

        			helper.setTipoConta(form.getTipoConta());
     				relatorioContasCanceladas.addParametro("mesAno", anoMes);
     				relatorioContasCanceladas.addParametro("relatorioTipo",helper.getRelatorioTipo());
     				relatorioContasCanceladas.addParametro("ordenacaoTipo",helper.getOrdenacaoTipo());
     				relatorioContasCanceladas.addParametro("relatorioContasCanceladasRetificadasHelper",helper);
     				

     				if (tipoRelatorio == null) {
     					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
     				}

     				relatorioContasCanceladas.addParametro("tipoFormatoRelatorio",
     						Integer.parseInt(tipoRelatorio));

            		retorno = processarExibicaoRelatorio(relatorioContasCanceladas,
    						tipoRelatorio, httpServletRequest, httpServletResponse,
    						actionMapping);
        		}else{
        			RelatorioContasRetificadas relatorioContasRetificadas = 
        				new RelatorioContasRetificadas(usuarioLogado);
        			helper.setTipoConta(form.getTipoConta());
     				relatorioContasRetificadas.addParametro("mesAno", anoMes);
     				relatorioContasRetificadas.addParametro("relatorioTipo",helper.getRelatorioTipo());
     				relatorioContasRetificadas.addParametro("ordenacaoTipo",helper.getOrdenacaoTipo());
     				relatorioContasRetificadas.addParametro("relatorioContasCanceladasRetificadasHelper",helper);
     				
     				if (tipoRelatorio == null) {
     					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
     				}

     				relatorioContasRetificadas.addParametro("tipoFormatoRelatorio",
     						Integer.parseInt(tipoRelatorio));

            		retorno = processarExibicaoRelatorio(relatorioContasRetificadas,
    						tipoRelatorio, httpServletRequest, httpServletResponse,
    						actionMapping);
        		 }

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
        	
        }else{
        	throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado");
        }
        
		
        return retorno;
    }

}
