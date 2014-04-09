package gcom.gui.cobranca;


import java.util.Collection;

import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarCobrancaSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCobrancaSituacaoActionForm atualizarCobrancaSituacaoActionForm = (AtualizarCobrancaSituacaoActionForm) actionForm;

		CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) sessao.getAttribute("atualizarCobrancaSituacao");
		
		Collection colecaoPesquisa = null;
		
		if(atualizarCobrancaSituacaoActionForm.getCodigo()!= null 
				&& !atualizarCobrancaSituacaoActionForm.getCodigo().equals("")){
			cobrancaSituacao.setId(new Integer(atualizarCobrancaSituacaoActionForm.getCodigo()));
		}else{
			cobrancaSituacao.setId(null);
		}
		
		String codigo = atualizarCobrancaSituacaoActionForm.getCodigo();		
        String descricao = atualizarCobrancaSituacaoActionForm.getDescricao();
        Short indicadorUso = atualizarCobrancaSituacaoActionForm.getIndicadorUso();
        String contaMotivoRevisao = atualizarCobrancaSituacaoActionForm.getContaMotivoRevisao();
        Short indicadorBloqueioParcelamento = atualizarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento();
        Short indicadorExigenciaAdvogado = atualizarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado();
        Short indicadorBloqueioRetirada = atualizarCobrancaSituacaoActionForm.getIndicadorBloqueioRetirada();
        String profissao = atualizarCobrancaSituacaoActionForm.getProfissao();
        String ramoAtividade = atualizarCobrancaSituacaoActionForm.getRamoAtividade();
        Short indicadorBloqueioInclusao = atualizarCobrancaSituacaoActionForm.getIndicadorBloqueioInclusao();
        Short indicadorSelecaoApenasComPermissao = atualizarCobrancaSituacaoActionForm.getIndicadorSelecaoApenasComPermissao();
        Integer indicadorPrescricaoImoveisParticulares = atualizarCobrancaSituacaoActionForm.getIndicadorPrescricaoImoveisParticulares();
        Integer indicadorNaoIncluirImoveisEmCobrancaResultado = atualizarCobrancaSituacaoActionForm.getIndicadorNaoIncluirImoveisEmCobrancaResultado();
        
        cobrancaSituacao.setDescricao(descricao);
        
        
		//Descricao
        FiltroCobrancaSituacao filtroCobSit= new FiltroCobrancaSituacao();
        filtroCobSit.adicionarParametro(
				new ParametroSimples(FiltroCobrancaSituacao.DESCRICAO, descricao));
        filtroCobSit.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCobrancaSituacao.ID, cobrancaSituacao.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroCobSit, CobrancaSituacao.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		
		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
		filtroCobrancaSituacao.adicionarParametro(
					new ParametroSimples(FiltroCobrancaSituacao.ID, codigo));
		filtroCobrancaSituacao.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCobrancaSituacao.ID, cobrancaSituacao.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigo+"");
		}
        
        
        
        //Motivo de Revisão da Conta
		if(contaMotivoRevisao != null && !contaMotivoRevisao.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			ContaMotivoRevisao contaMotivo = new ContaMotivoRevisao();
			contaMotivo.setId(new Integer(atualizarCobrancaSituacaoActionForm
					.getContaMotivoRevisao()));
			
			cobrancaSituacao.setContaMotivoRevisao(contaMotivo);
			
		} else {
			cobrancaSituacao.setContaMotivoRevisao(null);
		}
		
		//Profissao
		if(profissao != null && !profissao.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			Profissao prof = new Profissao();
			prof.setId(new Integer(atualizarCobrancaSituacaoActionForm
					.getProfissao()));
			
			cobrancaSituacao.setProfissao(prof);
			
		} else {
			cobrancaSituacao.setProfissao(null);
		}
		
		//Ramo Atividade
		if(ramoAtividade != null && !ramoAtividade.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			RamoAtividade ramoAtiv = new RamoAtividade();
			ramoAtiv.setId(new Integer(atualizarCobrancaSituacaoActionForm
					.getRamoAtividade()));
			
			cobrancaSituacao.setRamoAtividade(ramoAtiv);
			
		} else {
			cobrancaSituacao.setRamoAtividade(null);
		}
		
		//Exige advogado
		if(indicadorExigenciaAdvogado != null && 
			!indicadorExigenciaAdvogado.equals("")){
			
			cobrancaSituacao.setIndicadorExigenciaAdvogado(atualizarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado());
			
			if ( indicadorExigenciaAdvogado == 1 ) {
				//Profissao
				if(profissao != null && 
					profissao.equals("-1")){
					
					throw new ActionServletException("atencao.required", null,
					"Profissão");
				}
				//Ramo Atividade
				if(ramoAtividade != null && 
					ramoAtividade.equals("-1")){
					
					throw new ActionServletException("atencao.required", null,
					"Ramo Atividade");
				} 
			}
		}
		
		
		cobrancaSituacao.setIndicadorUso(indicadorUso);
		cobrancaSituacao.setIndicadorBloqueioParcelamento(indicadorBloqueioParcelamento);
		cobrancaSituacao.setIndicadorExigenciaAdvogado(indicadorExigenciaAdvogado);
		cobrancaSituacao.setIndicadorBloqueioRetirada(indicadorBloqueioRetirada);
		cobrancaSituacao.setIndicadorBloqueioInclusao(indicadorBloqueioInclusao);
		cobrancaSituacao.setIndicadorSelecaoApenasComPermissao(indicadorSelecaoApenasComPermissao);

		if(indicadorPrescricaoImoveisParticulares != null){
			cobrancaSituacao.setIndicadorPrescricaoImoveisParticulares(indicadorPrescricaoImoveisParticulares);
		}
		if(indicadorNaoIncluirImoveisEmCobrancaResultado != null){
			cobrancaSituacao.setIndicadorNaoIncluirImoveisEmCobrancaResultado(indicadorNaoIncluirImoveisEmCobrancaResultado);
		}
		
		fachada.atualizar(cobrancaSituacao);

		
		montarPaginaSucesso(httpServletRequest, "Situação de Cobrança "
				+ descricao + " atualizado com sucesso.",
				"Realizar outra Manutenção de Situação de Cobrança",
				"exibirFiltrarCobrancaSituacaoAction.do?menu=sim");        
        
		return retorno;
	}
}
