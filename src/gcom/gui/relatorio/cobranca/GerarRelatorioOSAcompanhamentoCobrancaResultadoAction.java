package gcom.gui.relatorio.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioOSAcompanhamentoCobrancaResultadoAction extends
ExibidorProcessamentoTarefaRelatorio{

	
	/**
	 * 
	 * 
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * @author Hugo Azevedo 
	 * @date 02/07/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obtém a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm relatorioActionForm = (GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm) actionForm;

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");		
		TarefaRelatorio relatorio = null;
		
		
		//Recuperando os dados do formulário
		String comando = relatorioActionForm.getComando();
		String[] categoriaImovel = relatorioActionForm.getCategoriaImovel();
		String[] perfilImovel = relatorioActionForm.getPerfilImovel();
		String[] gerenciaRegional = relatorioActionForm.getGerenciaRegional();
		String[] unidadeNegocio = relatorioActionForm.getUnidadeNegocio();
		String idLocalidadeInicial = relatorioActionForm.getIdLocalidadeInicial();
		String idLocalidadeFinal = relatorioActionForm.getIdLocalidadeFinal();
		String idSetorComercialInicial = relatorioActionForm.getIdSetorComercialInicial();
		String idSetorComercialFinal = relatorioActionForm.getIdSetorComercialFinal();
		String idQuadraInicial = relatorioActionForm.getIdQuadraInicial();
		String idQuadraFinal = relatorioActionForm.getIdQuadraFinal();
		String tipoServico = relatorioActionForm.getTipoServico(); 
		
		String descLocalidadeInicial = relatorioActionForm.getDescricaoLocalidadeInicial();
		String descLocalidadeFinal = relatorioActionForm.getDescricaoLocalidadeFinal();
		
		//Validar o formulário para campos vazios
        /*if(!relatorioActionForm.validarCamposVazios()){
        	throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
        }*/
		
        //Localidade Inicial e Final
        if( idLocalidadeInicial.equals("")){
        	if(!idLocalidadeFinal.equals("")){
        		throw new ActionServletException(
					"atencao.informe.localidade_inicial");
        	}
        }
        if( idLocalidadeFinal.equals("")){
        	if(!idLocalidadeInicial.equals("")){
        		throw new ActionServletException(
					"atencao.informe.localidade_final");
        	}
        }
        
        
        //Setor Comercial inicial e final
        if( idSetorComercialInicial.equals("")){
        	if(!idSetorComercialFinal.equals("")){
        		throw new ActionServletException(
					"atencao.informe.setor_comercial_inicial");
        	}
        }
        
        if( idSetorComercialFinal.equals("")){
        	if(!idSetorComercialInicial.equals("")){
        		throw new ActionServletException(
					"atencao.informe.setor_comercial_final");
        	}
        }
        
        //Quadra inicial e final
        if( idQuadraInicial.equals("")){
        	if(!idQuadraFinal.equals("")){
        		throw new ActionServletException(
					"atencao.informe.quadra_inicial");
        	}
        }
        
        if( idQuadraFinal.equals("")){
        	if(!idQuadraInicial.equals("")){
        		throw new ActionServletException(
					"atencao.informe.quadra_final");
        	}
        }
        
        
		//Recuperando as ordem de servico especificadas
		Collection colecaoImovelOS = fachada.obterColecaoImovelOSCobrancaResultado(
																categoriaImovel,
																perfilImovel,
																gerenciaRegional,
																unidadeNegocio,
																idLocalidadeInicial,
																idLocalidadeFinal,
																idSetorComercialInicial,
																idSetorComercialFinal,
																idQuadraInicial,
																idQuadraFinal,
																tipoServico,
																comando
															);
		
		
		
		//Nenhum parâmetro retornado
		if(colecaoImovelOS == null || colecaoImovelOS.isEmpty()){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "relatório de acompanhamento");
		}
		
		relatorio = new RelatorioGerarRelatorioOSAcompanhamentoCobrancaResultado(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		relatorio.addParametro("categoriaImovel",categoriaImovel);
		relatorio.addParametro("perfilImovel",perfilImovel);
		relatorio.addParametro("gerenciaRegional",gerenciaRegional);
		relatorio.addParametro("unidadeNegocio",unidadeNegocio);
		relatorio.addParametro("descLocalidadeInicial",descLocalidadeInicial);
		relatorio.addParametro("descLocalidadeFinal",descLocalidadeFinal);
		relatorio.addParametro("idSetorComercialInicial",idSetorComercialInicial);
		relatorio.addParametro("idSetorComercialFinal",idSetorComercialFinal);
		relatorio.addParametro("idQuadraInicial",idQuadraInicial);
		relatorio.addParametro("idQuadraFinal",idQuadraFinal);
		
		
		relatorio.addParametro("colecaoImovelOS",colecaoImovelOS);
		relatorio.addParametro("tipoServico",tipoServico);
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		
		return retorno;
	}
}
