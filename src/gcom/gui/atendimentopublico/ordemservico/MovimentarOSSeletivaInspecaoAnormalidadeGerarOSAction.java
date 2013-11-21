package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
 * 
 * Action responsável por validar os dados informados na página de Gerar OS do processo 
 * de movimentar ordem de serviço
 * 
 * @author Vivianne Sousa
 * @since 12/07/2011
 */
public class MovimentarOSSeletivaInspecaoAnormalidadeGerarOSAction extends GcomAction {
   
    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a página de definir acesso do grupo 
        ActionForward retorno = actionMapping.findForward("movimentarOSSeletivaInspecaoAnormalidadeAction");

        //Cria uma instância da sessão 
        HttpSession sessao = httpServletRequest.getSession(false);

        MovimentarOSSeletivaInspecaoAnormalidadeActionForm form = (MovimentarOSSeletivaInspecaoAnormalidadeActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

        
		 if ((httpServletRequest.getAttribute("confirmacao") != null
			&& (httpServletRequest.getAttribute("confirmacao")).toString().equalsIgnoreCase("true"))
			|| (httpServletRequest.getAttribute("confirmado") != null
			&& (httpServletRequest.getAttribute("confirmado")).toString().equalsIgnoreCase("ok"))) {

			
			Iterator iterator = form.getColecaoImovel().iterator();
			
			while(iterator.hasNext()) {
				Integer idImovel = (Integer) iterator.next();
				
				this.geraOrdemServico(new Integer(form.getIdTipoServico()),
										new Integer(form.getIdEmpresa()),
										idImovel,
										usuarioLogado,
										new Integer(form.getIdComando())); 
			}
			
			httpServletRequest.setAttribute("tipoMovimentacao", "Gerada(s)");

			httpServletRequest.removeAttribute("confirmacao");
			
			httpServletRequest.setAttribute("concluir", "true");
			
		} else if (httpServletRequest.getParameter("concluir") != null
			&& (httpServletRequest.getParameter("concluir")).toString().equalsIgnoreCase("true")) {
        	
        	String mensagem = validarForm(form);
			
			if (mensagem != null && !mensagem.equals("")) {
				throw new ActionServletException(mensagem);
			}
			
			httpServletRequest.setAttribute("destino", "2");
			sessao.setAttribute("destino", "2");
		
			// Monta a página de confirmação do wizard para perguntar se
			// o usuário quer confirmar o encerramento do comando 
			// mesmo sem ter sido enviado para a empresa contratada
			return montarPaginaConfirmacaoWizard(
					"atencao.serao.geradas.ordens.servico",
					httpServletRequest, actionMapping, ((Integer) form.getColecaoImovel().size()).toString());
			
        }

        //Retorna o mapemaneto na variável "retorno"
        return retorno;
    }

    public String validarForm(MovimentarOSSeletivaInspecaoAnormalidadeActionForm form) {
    	
    	if (form.getIdTipoServico() == null
    			|| form.getIdTipoServico().equals("")
    			|| form.getIdTipoServico().equals("-1")) {
    		return "atencao.informe.servico_tipo";
    	}
    	
		List<Integer> numerosImoveisPesquisar = new ArrayList();
		
		for (int i = 0; i < form.getMatriculasImoveis().length; i++) {
			if (form.getMatriculasImoveis()[i] != null && !form.getMatriculasImoveis()[i].equals("")) {
				numerosImoveisPesquisar.add(new Integer(form.getMatriculasImoveis()[i]));
			}
		}
		
		if (numerosImoveisPesquisar == null || numerosImoveisPesquisar.isEmpty()) {
			return "atencao.filtro.nenhum_imovel_informado";
			
		}else{
			
			verificaSeImovelFazParteComando(numerosImoveisPesquisar,form.getIdComando());
			
			Collection colecaoImovel = new ArrayList(numerosImoveisPesquisar);
			
			form.setColecaoImovel(colecaoImovel);
			
		}
		    	
    	return null;
    }

	
	private Integer geraOrdemServico(Integer idTipoServico, Integer idEmpresa, 
			Integer idImovel, Usuario usuarioLogado, Integer idComando) {
		OrdemServico ordemServico = null;
		ServicoTipo servicoTipo = null;
		Empresa empresa = null;
		Integer retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		// [UC0430] - Gerar Ordem de Servico
		servicoTipo = new ServicoTipo();
		
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.ID,idTipoServico ));
		filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade(
				FiltroServicoTipo.SERVICO_TIPO_REFERENCIA);
		Collection colecaoServTipo = Fachada.getInstancia().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
		servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServTipo); 
		
		ordemServico = new OrdemServico();
		ordemServico.setServicoTipo(servicoTipo);
		
		Imovel imovel = new Imovel();
		imovel.setId(idImovel);
		ordemServico.setImovel(imovel);
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
		Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.empresa_nao_encontra", null, idEmpresa.toString());
		}
		
		empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
		
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		
		// Recupera a Unidade Organizacional da Empresa
		unidadeOrganizacional.setEmpresa(empresa);
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.EMPRESA, empresa));
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = 
			fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
			
			//[FS0011]-Verificar existência de mais de uma unidade correspondente à empresa
			if(colecaoUnidadeOrganizacional.size() > 1){
				throw new ActionServletException("atencao.unidade_organizacional_dupla_correspondente_empresa");
			}
			
			unidadeOrganizacional = colecaoUnidadeOrganizacional.iterator().next();
		}else {
			throw new ActionServletException("atencao.unidade_organizacional_nao_encontrada_empresa", null, empresa.getDescricao());
		}
		
		ordemServico.setUnidadeAtual(unidadeOrganizacional);
		
		ComandoOrdemSeletiva comandoOrdemSeletiva = new ComandoOrdemSeletiva();
		comandoOrdemSeletiva.setId(idComando);
		ordemServico.setComandoOrdemSeletiva(comandoOrdemSeletiva);
		
		retorno = fachada.gerarOrdemServico(ordemServico, usuarioLogado, Funcionalidade.MOVIMENTAR_OS_SELETIVA_INSPECAO_ANORMALIDADE);
		
		return retorno;
	}

	
	 //[FS0003] – Verificar se imóvel faz parte do comando
    public String verificaSeImovelFazParteComando(List<Integer> numerosImoveisPesquisar,String idComandoOrdemSeletiva) {
    	String retorno = null;
    			
    	String imovelNaoFazParteComando = Fachada.getInstancia().retornaImovelNaoFazParteComando(
    			new Integer (idComandoOrdemSeletiva),numerosImoveisPesquisar);
    	
    	if(!imovelNaoFazParteComando.equalsIgnoreCase("")){
    		throw new ActionServletException("atencao.imoveis.nao_contido_comando",null,imovelNaoFazParteComando);
    	}
    	
    	return retorno;
    }
}
