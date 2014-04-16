package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0763] Gerar Arquivo Texto de Ligacoes com Hidrometro - CAERN 
 * @author Tiago Moreno
 * @date 10/04/2008
 * 
 * @param ArquivoTextoLigacoesHidrometroHelper
 * 
 * @return
 * @throws ControladorException
 */

public class GerarArquivoTextoLigacoesHidrometroAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Form
		GerarArquivoTextoLigacoesHidrometroActionForm form = 
			(GerarArquivoTextoLigacoesHidrometroActionForm) actionForm;
		
		ArquivoTextoLigacoesHidrometroHelper objetoArquivoTexto = 
			new ArquivoTextoLigacoesHidrometroHelper();
		
		// Verifica se pelo menos 1 campo foi preenchido
		boolean peloMenosUm = false;
		
		// Gerência Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			objetoArquivoTexto.setGerenciaRegional(new Integer(form.getGerenciaRegional()));
			peloMenosUm = true;
		}
		
		// Unidade de Negocio
		if (form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			objetoArquivoTexto.setUnidadeNegocio(new Integer(form.getUnidadeNegocio()));
			peloMenosUm = true;
		}
			
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") ) {
				
			objetoArquivoTexto.setLocalidadeInicial(new Integer(form.getLocalidadeInicial()));
			peloMenosUm = true;
		}
		
		// Setor Comercial Inicial
		if (form.getSetorComercialInicial() != null && 
			!form.getSetorComercialInicial().equals("") ) {
				
			objetoArquivoTexto.setSetorComercialInicial(new Integer(form.getSetorComercialInicial()));
			peloMenosUm = true;
		}

		// Rota Inicial
		if (form.getRotaInicial() != null && 
			!form.getRotaInicial().equals("") ) {
				
			objetoArquivoTexto.setRotaInicial(new Integer(form.getRotaInicial()));
			peloMenosUm = true;
		}

		// Sequencial Rota Inicial
		if (form.getSequencialRotaInicial() != null && 
			!form.getSequencialRotaInicial().equals("") ) {
				
			objetoArquivoTexto.setSequencialRotalInicial(new Integer(form.getSequencialRotaInicial()));
			peloMenosUm = true;
		}

		// Localidade Final
		if (form.getLocalidadeFinal() != null && 
			!form.getLocalidadeFinal().equals("") ) {
				
			objetoArquivoTexto.setLocalidadeFinal(new Integer(form.getLocalidadeFinal()));
			peloMenosUm = true;
		}

		// Setor Comercial Final
		if (form.getSetorComercialFinal() != null && 
			!form.getSetorComercialFinal().equals("") ) {
				
			objetoArquivoTexto.setSetorComercialFinal(new Integer(form.getSetorComercialFinal()));
			peloMenosUm = true;
		}
		
		// Rota Final
		if (form.getRotaFinal() != null && 
			!form.getRotaFinal().equals("") ) {
				
			objetoArquivoTexto.setRotaFinal(new Integer(form.getRotaFinal()));
			peloMenosUm = true;
		}
		
		// Sequencial Rota Final
		if (form.getSequencialRotaFinal() != null && 
			!form.getSequencialRotaFinal().equals("") ) {
				
			objetoArquivoTexto.setSequencialRotalFinal(new Integer(form.getSequencialRotaFinal()));
			peloMenosUm = true;
		}			
		
		//verifica se pelo menos 1 campo foi preenchido no formulário
		if (!peloMenosUm){
			throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado");
		}
		
		//chama o controlador para gerar o arquivo texto
		Fachada.getInstancia().gerarArquivoTextoLigacoesHidrometro(objetoArquivoTexto);
		
		montarPaginaSucesso(httpServletRequest, "Arquivo de Texto de Ligacoes com Hidrometro gerado com sucesso.",
				"Gerar outro Arquivo de Texto de Ligacoes com Hidrometro",
				"exibirGerarArquivoTextoLigacoesHidrometroAction.do?menu=sim" );
		

		return retorno;
	}
	
}
