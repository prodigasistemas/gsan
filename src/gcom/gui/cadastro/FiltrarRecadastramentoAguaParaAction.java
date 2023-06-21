package gcom.gui.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.cliente.CadastroAguaPara;
import gcom.cadastro.imovel.bean.ConsultarImovelRegistroAtendimentoHelper;
import gcom.cadastro.imovel.bean.ConsultarRecadastramentoAguaParaHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class FiltrarRecadastramentoAguaParaAction extends GcomAction {
	
	@Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarRecadastramentoAguaPara"); 
		
        Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
        
        if(!getFachada().verificarPermissaoRecadastramentoAguaPara(usuarioLogado))
        	throw new ActionServletException("atencao.usuario_sem_permissao_recadastramento_bolsa_agua"); 
        
        HttpSession sessao = httpServletRequest.getSession(false);

        DadosRecadastramentoAguaParaActionForm form = (DadosRecadastramentoAguaParaActionForm) actionForm;   
        
        if(form.getIdImovel().equals(""))
        	form.setIdImovel("0");
        	
        Integer situacaoInteger = form.getSituacao();
        Integer idImovel = Integer.parseInt(form.getIdImovel());
        
        if(situacaoInteger.equals(0) && idImovel.equals(0))
        	throw new ActionServletException("atencao.selecionar_filtro_agua_para"); 
        
        if(!situacaoInteger.equals(0) && !idImovel.equals(0))
        	form.setImoveis(getFachada().pesquisarRecadastramentoAguaParaMatriculaSituacao(idImovel,situacaoInteger));
        else if(!situacaoInteger.equals(0))
            form.setImoveis(getFachada().pesquisarRecadastramentoAguaParaSituacao(situacaoInteger));       
        else if(!idImovel.equals(0))
        	form.setImoveis(getFachada().pesquisarRecadastramentoAguaParaMatricula(idImovel));
        
    	Collection colecaoConsultarRecadastramentoAguaParaHelper  = null;
		
		if(form.getImoveis() != null &&
				!form.getImoveis() .isEmpty()){
			
			Iterator iteratorColecaoRecadastramento = form.getImoveis().iterator();
			
			colecaoConsultarRecadastramentoAguaParaHelper = new ArrayList();
			
			while (iteratorColecaoRecadastramento.hasNext()) {
				CadastroAguaPara cadastroAguaPara = (CadastroAguaPara) iteratorColecaoRecadastramento.next();
				
				ConsultarRecadastramentoAguaParaHelper consultarRecadastramentoAguaParaHelper = new ConsultarRecadastramentoAguaParaHelper();

				//id cadastro água pará
				if(cadastroAguaPara != null  && cadastroAguaPara.getId() != null ){
					consultarRecadastramentoAguaParaHelper.setId(cadastroAguaPara.getId());
				}
				
				//cadastro cpf
				if(cadastroAguaPara != null  && cadastroAguaPara.getCpf() != null ){
					consultarRecadastramentoAguaParaHelper.setCpf(cadastroAguaPara.getCpf());
				}
				
				//cadastro rg
				if(cadastroAguaPara != null  && cadastroAguaPara.getRg() != null ){
					consultarRecadastramentoAguaParaHelper.setRg(cadastroAguaPara.getRg());
				}
				
				//cadastro numero nis
				if(cadastroAguaPara != null  && cadastroAguaPara.getNumeroNIS() != null ){
					consultarRecadastramentoAguaParaHelper.setNumeroNIS(cadastroAguaPara.getNumeroNIS());
				}
				
				//cadastro id imovel
				if(cadastroAguaPara != null  && cadastroAguaPara.getImovel().getId() != null ){
					consultarRecadastramentoAguaParaHelper.setIdImovel(cadastroAguaPara.getImovel().getId());
				}
				
				//cadastro nome
				if(cadastroAguaPara != null  && cadastroAguaPara.getNome() != null ){
					consultarRecadastramentoAguaParaHelper.setNome(cadastroAguaPara.getNome());
				}
				
				//cadastro situacao
				if(cadastroAguaPara != null  && cadastroAguaPara.getSituacao() != null ){
					if(cadastroAguaPara.getSituacao()==CadastroAguaPara.PENDENTE) {
					    consultarRecadastramentoAguaParaHelper.setSituacao("Pendente");
					} else if(cadastroAguaPara.getSituacao()==CadastroAguaPara.RECUSADO) {
						consultarRecadastramentoAguaParaHelper.setSituacao("Negado");
					} else if(cadastroAguaPara.getSituacao()==CadastroAguaPara.ACEITO) {
						consultarRecadastramentoAguaParaHelper.setSituacao("Aceito");
				    }
				}
				
				//cadastro telefone
				if(cadastroAguaPara != null  && cadastroAguaPara.getTelefone() != null ){
					consultarRecadastramentoAguaParaHelper.setTelefone(cadastroAguaPara.getTelefone());
				}
							
				colecaoConsultarRecadastramentoAguaParaHelper.add(consultarRecadastramentoAguaParaHelper);
			}
		}
			sessao.setAttribute("colecaoConsultarRecadastramentoAguaParaHelper", colecaoConsultarRecadastramentoAguaParaHelper);
      
        return retorno;
    }

}
