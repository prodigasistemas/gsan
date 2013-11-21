package gcom.gui.faturamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC0972] Gerar TXT das Contas dos Projetos Especiais
 * 
 * @author Hugo Amorim, Anderson Italo
 * @since 14/12/2009, 29/01/2010
 *
 */
public class GerarTxtContasProjetosEspeciaisAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = getSessao(httpServletRequest);
		

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		GerarTxtContasProjetosEspeciaisForm gerarTxtContasProjetosEspeciaisForm = (GerarTxtContasProjetosEspeciaisForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Integer mesAno = Util.formatarMesAnoComBarraParaAnoMes(gerarTxtContasProjetosEspeciaisForm.getMesAno());
		
		
		if(mesAno.compareTo(sistemaParametro.getAnoMesFaturamento())>0){
			throw new ActionServletException("atencao.faturamento.mes_ano_deve_ser_menor_ou_igual");
		}
		
		Cliente cliente = fachada.pesquisarClienteDigitado(new Integer(gerarTxtContasProjetosEspeciaisForm.getIdCliente()));
		
		if (cliente == null){
			throw new ActionServletException(
					"atencao.cliente.inexistente", null, "cliente");
		}
		
		if (cliente.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)){
			throw new ActionServletException(
					"atencao.cliente.inativo", null, cliente.getNome());
		}
		
		int count =  fachada.countTxtContasProjetosEspeciais(Util.formatarMesAnoComBarraParaAnoMes(gerarTxtContasProjetosEspeciaisForm.getMesAno()).toString(),
				new Integer(gerarTxtContasProjetosEspeciaisForm.getIdCliente()));
		
		if(count==0){
			throw new ActionServletException("atencao.nao_existe_dados_geracao_arquivo_texto");
		}
		
		fachada.gerarTxtContasProjetosEspeciais(
				Util.formatarMesAnoComBarraParaAnoMes(gerarTxtContasProjetosEspeciaisForm.getMesAno()).toString(),
				new Integer(gerarTxtContasProjetosEspeciaisForm.getIdCliente()),
				usuario);
		
		//montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Arquivo Texto Contas dos Projetos Especiais Enviado para Processamento", 
			"Voltar",
			"exibirGerarTxtContasProjetosEspeciaisAction.do");
			
		return retorno;
	}
}
