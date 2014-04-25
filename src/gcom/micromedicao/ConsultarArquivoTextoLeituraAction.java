package gcom.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.ConsultarArquivoTextoLeituraActionForm;
import gcom.micromedicao.bean.ConsultarArquivoTextoRoteiroEmpresaHelper;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório e Thiago Nascimento
 * @date 05/08/2006
 */
public class ConsultarArquivoTextoLeituraAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Serviço
	 * 
	 * [UC0437] Pesquisar Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Thiago Tenório e Thiago Nascimento 
	 * @date 31/07/2006 
	 * 
	 * Yara T. Souza  
	 * 19/12/2008
	 * 
	 * 
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
			ActionForward retorno = actionMapping
					.findForward("consultarArquivoTextoLeitura");
	
			HttpSession sessao = httpServletRequest.getSession(false);
	
			ConsultarArquivoTextoLeituraActionForm consultarArquivoTextoLeituraActionForm = (ConsultarArquivoTextoLeituraActionForm) actionForm;
	
			ConsultarArquivoTextoRoteiroEmpresaHelper consultarArquivoTextoRoteiroEmpresaHelper = new ConsultarArquivoTextoRoteiroEmpresaHelper();
			
			boolean peloMenosUmParametroInformado = false;
			
			/**
			 * CRC775
			 * autor: Yara T. Souza
			 * data: 19/12/2008
			 * Comentei todos os objetos que antes era setados na sessão e deixei apenas no FORM.
			 */
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	
			sessao.removeAttribute("permissao");
			if (usuarioLogado.getEmpresa().getIndicadorEmpresaPrincipal().equals(
					new Short("1"))) {
				sessao.setAttribute("permissao", "1");
			} else {
				sessao.setAttribute("permissao", "2");
			}
			
			//EMPRESA
			String empresaID = consultarArquivoTextoLeituraActionForm.getEmpresaID();
			
			if (empresaID != null && !empresaID.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
				peloMenosUmParametroInformado = true;
	
				consultarArquivoTextoRoteiroEmpresaHelper.setIdEmpresa(empresaID);
			}
			
			//SERVICO_TIPO_CELULAR
			String servicoTipoCelular = consultarArquivoTextoLeituraActionForm.getServicoTipoCelular();
			
			if(servicoTipoCelular != null && !servicoTipoCelular.trim().equalsIgnoreCase("")){
				
				peloMenosUmParametroInformado = true;
				
				consultarArquivoTextoRoteiroEmpresaHelper.setIdServicoTipoCelular(servicoTipoCelular);
			}
	
			//FATURAMENTO_GRUPO
			String grupoFaturamentoID = consultarArquivoTextoLeituraActionForm.getGrupoFaturamentoID();
			
			if (grupoFaturamentoID != null && !grupoFaturamentoID.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO) && 
				!grupoFaturamentoID.equals("-1") && !grupoFaturamentoID.equals("")) {
	
				peloMenosUmParametroInformado = true;
	
				consultarArquivoTextoRoteiroEmpresaHelper.setIdFaturamentoGrupo(grupoFaturamentoID);
	
			}
	
			//ANO MES REFERENCIA
			String mesAno = consultarArquivoTextoLeituraActionForm.getMesAno();
			
			if (mesAno != null && !mesAno.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
				
				consultarArquivoTextoRoteiroEmpresaHelper.setAnoMesReferencia((Util.formatarMesAnoComBarraParaAnoMes(mesAno)).toString());
			}
	
			//SITUACAO_TRANSMISSAO_LEITURA
			String situaTransmLeitura = consultarArquivoTextoLeituraActionForm.getSituaTransmLeitura();
			
			if (situaTransmLeitura != null && !situaTransmLeitura.trim().equalsIgnoreCase("")) {
	
				peloMenosUmParametroInformado = true;
	
				consultarArquivoTextoRoteiroEmpresaHelper.setIdSituacaoTransmissaoLeitura(situaTransmLeitura);
			}
			
			// filtro por localidade
			String localidadeID = consultarArquivoTextoLeituraActionForm.getIdLocalidade();
			
			if(localidadeID != null && !localidadeID.trim().equalsIgnoreCase(
			"" + ConstantesSistema.NUMERO_NAO_INFORMADO) && !localidadeID.equals("")){
				
				peloMenosUmParametroInformado = true;
				
				consultarArquivoTextoRoteiroEmpresaHelper.setIdLocalidade(localidadeID);
			}
	
			//LEITURISTA
			String leiturista = consultarArquivoTextoLeituraActionForm.getLeituristaID();
			
			if (leiturista != null && !leiturista.equals("") && !leiturista.equals("-1")) {
	
				peloMenosUmParametroInformado = true;
				
				consultarArquivoTextoRoteiroEmpresaHelper.setIdLeiturista(leiturista);
	
			}
	
			// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}
			
			
			Fachada fachada = Fachada.getInstancia();
			
			//1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
			/*Integer totalRegistros = fachada.filtrarArquivoTextoRoteiroEmpresaCount
			(consultarArquivoTextoRoteiroEmpresaHelper);*/
			
			//2º Passo - Chamar a função de Paginação passando o total de registros
			//retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
			
			/*
			 * 3º Passo - Obter a coleção da consulta que aparecerá na tela passando 
			 * o numero de paginas da pesquisa que está no request
			 */
			Collection colecaoArquivoTextoRoteiroEmpresa = fachada.filtrarArquivoTextoRoteiroEmpresaParaPaginacao(
			consultarArquivoTextoRoteiroEmpresaHelper, ((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")));
	
			sessao.setAttribute("colecaoArquivoTextoRoteiroEmpresa", colecaoArquivoTextoRoteiroEmpresa);
			
			if(!colecaoArquivoTextoRoteiroEmpresa.isEmpty()){
				sessao.setAttribute("achou","1");
				sessao.setAttribute("qtdArquivos", colecaoArquivoTextoRoteiroEmpresa.size());
			}else{
				sessao.setAttribute("achou","2");
				sessao.setAttribute("qtdArquivos", new Integer(0));
			}
			
			if ( fachada.verificarPermissaoEspecial( PermissaoEspecial.GERAR_ARQUIVO_TEXTO_IMOVEIS_NAO_ENVIADOS, usuarioLogado ) ){
				httpServletRequest.setAttribute( "permissaoRegerarArquivoImoveisNaoEnviados", "SIM" );
			} else {
				httpServletRequest.setAttribute( "permissaoRegerarArquivoImoveisNaoEnviados", "NAO" );
			}			
			
			sessao.removeAttribute("grupoFaturamentoID");
	
			return retorno;
	}
}
