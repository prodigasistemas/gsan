package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarImovelProgramaEspecialAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();		

		AtualizarImovelProgramaEspecialActionForm form = (AtualizarImovelProgramaEspecialActionForm) actionForm;
		
		
		if(form.getDescricaoDocumentos()!=null
				&& form.getDescricaoDocumentos().length()>200){
			throw new ActionServletException("atencao.execedeu_limit_observacao","Observação","200");
		}
		
	
		FiltroImovelProgramaEspecial filtroImovelProgramaEspecial = new FiltroImovelProgramaEspecial();
		
		filtroImovelProgramaEspecial.adicionarParametro(new ParametroSimples(FiltroImovelProgramaEspecial.ID,new Integer(form.getId())));
		
		filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.IMOVEL);
		filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.LOCALIDADE);
		filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.SETOR_COMERCIAL);
		filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.QUADRA);
		
		ImovelProgramaEspecial imovelProgramaEspecial = (ImovelProgramaEspecial) fachada.pesquisar(filtroImovelProgramaEspecial,ImovelProgramaEspecial.class.getName()).iterator().next();
		
		if(form.getDescricaoDocumentos()!=null 
				&& !form.getDescricaoDocumentos().equals("")){
			imovelProgramaEspecial.setDescricaoDocumentos(form.getDescricaoDocumentos());
		}
		
		if(form.getNumeroBolsaFamilia()!=null 
				&& !form.getNumeroBolsaFamilia().equals("")){
			imovelProgramaEspecial.setNumeroBolsaFamilia(form.getNumeroBolsaFamilia());
		}
		
		
		if(form.getDataApresentacaoDocumentos()!=null 
				&& !form.getDataApresentacaoDocumentos().equals("")){
			Date dataApresentacao = Util.converteStringParaDate(form.getDataApresentacaoDocumentos());			
			imovelProgramaEspecial.setDataApresentacaoDocumentos(dataApresentacao);
		}

		
		Date dataAtual = new Date();
		
		if(Util.compararData(imovelProgramaEspecial.getDataApresentacaoDocumentos(), dataAtual)==1){
			throw new ActionServletException("atencao.data.apresentacao.documentos.invalida");
		}
		
		imovelProgramaEspecial.setUltimaAlteracao(dataAtual);
		
		fachada.atualizar(imovelProgramaEspecial);
		
		montarPaginaSucesso(httpServletRequest,
				"Matrícula do imóvel "+imovelProgramaEspecial.getImovel().getId() + " alterada com sucesso no programa especial.",
				"Realizar outra Manutenção de Imóvel no programa.",
				"exibirFiltrarImovelProgramaEspecialAction.do?menu=sim&atualizar=sim");		
		
		return retorno;
	}
}
