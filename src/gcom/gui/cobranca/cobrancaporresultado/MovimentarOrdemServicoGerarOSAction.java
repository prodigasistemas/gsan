package gcom.gui.cobranca.cobrancaporresultado;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.EmpresaCobrancaConta;
import gcom.cobranca.FiltroEmpresaCobrancaConta;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por validar os dados informados na página de Gerar OS do processo 
 * de movimentar ordem de serviço de cobrança por resultado.
 *
 * @author Mariana Victor
 * @date 10/05/2011
 */
public class MovimentarOrdemServicoGerarOSAction extends GcomAction {
   
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
        ActionForward retorno = actionMapping.findForward("movimentarOrdemServicoAction");

        //Cria uma instância da sessão 
        HttpSession sessao = httpServletRequest.getSession(false);

        MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

        
		 if ((httpServletRequest.getAttribute("confirmacao") != null
					&& (httpServletRequest.getAttribute("confirmacao")).toString().equalsIgnoreCase("true"))
					|| (httpServletRequest.getAttribute("confirmado") != null
							&& (httpServletRequest.getAttribute("confirmado")).toString().equalsIgnoreCase("ok"))) {

			
			Iterator iterator = form.getColecaoImovel().iterator();
			
			while(iterator.hasNext()) {
				Imovel imovel = (Imovel) iterator.next();
				
				this.geraOrdemServico(new Integer(form.getIdTipoServico()),
										new Integer(form.getIdEmpresa()),
										imovel.getId(),
										usuarioLogado); 
			}
			
			httpServletRequest.setAttribute("tipoMovimentacao", "Gerada(s)");

			httpServletRequest.removeAttribute("confirmacao");
			
			httpServletRequest.setAttribute("concluir", "true");
			
		} else if (httpServletRequest.getParameter("concluir") != null
			&& (httpServletRequest.getParameter("concluir")).toString().equalsIgnoreCase("true")) {
        	
        	String mensagem = validarForm(form, sessao);
			
			if (mensagem != null && !mensagem.equals("")) {
				throw new ActionServletException(
						mensagem);
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

    public String validarForm(MovimentarOrdemServicoActionForm form, HttpSession sessao) {
    	
    	boolean algumParametroInformado = false;
    	
    	if (form.getIdTipoServico() == null
    			|| form.getIdTipoServico().equals("")
    			|| form.getIdTipoServico().equals("-1")) {
    		return "atencao.informe.servico_tipo";
    	}
    	
    	if (form.getIdsCategoria() != null
    			&& form.getIdsCategoria().length != 0
    			&& !(form.getIdsCategoria().length == 1
    					&& form.getIdsCategoria()[0].equals("-1"))) {
    		
    		algumParametroInformado = true;
    	}
    	
    	if (form.getIdsImovelPerfil() != null
    			&& form.getIdsImovelPerfil().length != 0
    			&& !(form.getIdsImovelPerfil().length == 1
    					&& form.getIdsImovelPerfil()[0].equals("-1"))) {
    		
    		algumParametroInformado = true;
    	}
    	
    	if (form.getIdsLigacaoAguaSituacao() != null
    			&& form.getIdsLigacaoAguaSituacao().length != 0
    			&& !(form.getIdsLigacaoAguaSituacao().length == 1
    					&& form.getIdsLigacaoAguaSituacao()[0].equals("-1"))) {
    		
    		algumParametroInformado = true;
    	}
    	
    	if (form.getValorMinimo() != null
    			&& !form.getValorMinimo().equals("")
    			&& !(Util.formatarMoedaRealparaBigDecimal(form.getValorMinimo())).equals(BigDecimal.ZERO)) {

        	if (form.getValorMaximo() == null
        			|| form.getValorMaximo().equals("")
        			|| (Util.formatarMoedaRealparaBigDecimal(form.getValorMaximo())).equals(BigDecimal.ZERO)) {
        		return "atencao.informe.valor_minimo";
        		
        	} else if ((Util.formatarMoedaRealparaBigDecimal(form.getValorMinimo()))
        			.compareTo(Util.formatarMoedaRealparaBigDecimal(form.getValorMaximo())) == 1){
        		return "atencao.gerar_os.valor_minimo.menor.valor_maximo";
        	}
        	
    		algumParametroInformado = true;
    	}
		
		List<Integer> numerosImoveisPesquisar = new ArrayList();
		
		for (int i = 0; i < form.getMatriculasImoveis().length; i++) {
			if (form.getMatriculasImoveis()[i] != null && !form.getMatriculasImoveis()[i].equals("")) {
				numerosImoveisPesquisar.add(new Integer(form.getMatriculasImoveis()[i]));
			}
		}
		
		if (numerosImoveisPesquisar == null || numerosImoveisPesquisar.isEmpty()) {
			MovimentarOrdemServicoGerarOSHelper helper = this.montarHelper(form);
			
			Collection<Integer> colecaoIdsImoveis = Fachada.getInstancia().pesquisarIdsImoveis(helper);
			
			if (colecaoIdsImoveis != null && !colecaoIdsImoveis.isEmpty()) {
				Iterator iterator = colecaoIdsImoveis.iterator();
				Collection colecaoImovel = new ArrayList();
				
				while(iterator.hasNext()) {
					Integer idImovel = (Integer) iterator.next();
					
					Imovel imovel = new Imovel();
					imovel.setId(idImovel);
					
					colecaoImovel.add(imovel);
				}
				
				form.setColecaoImovel(colecaoImovel);
	    		algumParametroInformado = true;
				
			} else {
				return "atencao.imoveis.filtro.nao_relacionados.ao_comando";
			}
		}
		
		if (numerosImoveisPesquisar != null && !numerosImoveisPesquisar.isEmpty()) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimplesIn(
					FiltroImovel.ID, numerosImoveisPesquisar));
			
			Collection colecaoImovel = Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
			
			if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

				FiltroEmpresaCobrancaConta filtroEmpresaCobrancaConta = new FiltroEmpresaCobrancaConta();
				filtroEmpresaCobrancaConta.adicionarParametro(new ParametroSimplesIn(
						FiltroEmpresaCobrancaConta.IMOVEL_ID, numerosImoveisPesquisar));
				filtroEmpresaCobrancaConta.adicionarParametro(new ParametroSimples(
						FiltroEmpresaCobrancaConta.COMANDO_EMPRESA_COBRANCA_CONTA_ID, form.getIdComandoContaCobranca()));
				
				Collection colecaoEmpresaCobrancaConta = Fachada.getInstancia().pesquisar(filtroEmpresaCobrancaConta, EmpresaCobrancaConta.class.getName());

				if (colecaoEmpresaCobrancaConta != null && !colecaoEmpresaCobrancaConta.isEmpty()) {
					form.setColecaoImovel(colecaoImovel);
		    		algumParametroInformado = true;
				} else {
					return "atencao.imoveis.nao_relacionados.ao_comando";
				}
				
			} else {
				return "atencao.imoveis.inexistentes";
			}
		}
		
		if (!algumParametroInformado) {
			return "atencao.filtro.nenhum_parametro_informado";
		}
    	
    	return null;
    }
    
    private MovimentarOrdemServicoGerarOSHelper montarHelper(MovimentarOrdemServicoActionForm form){
    	MovimentarOrdemServicoGerarOSHelper helper = new MovimentarOrdemServicoGerarOSHelper();

    	helper.setIdComandoContaCobranca(new Integer(form.getIdComandoContaCobranca()));
    	
    	helper.setIdsCategoria(form.getIdsCategoria());
    	
    	helper.setIdsImovelPerfil(form.getIdsImovelPerfil());
    	
    	helper.setIdsLigacaoAguaSituacao(form.getIdsLigacaoAguaSituacao());

    	if (form.getValorMinimo() != null && !form.getValorMinimo().equals("")) {
    		helper.setValorMinimo(Util.formatarMoedaRealparaBigDecimal(form.getValorMinimo()));
    	}
    	if (form.getValorMaximo() != null && !form.getValorMaximo().equals("")) {
    		helper.setValorMaximo(Util.formatarMoedaRealparaBigDecimal(form.getValorMaximo()));
    	}
    	
    	return helper;
    }

	
	private Integer geraOrdemServico(Integer idTipoServico, Integer idEmpresa, Integer idImovel, Usuario usuarioLogado) {
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
		
		retorno = fachada.gerarOrdemServico(
				ordemServico, usuarioLogado, Funcionalidade.MOVIMENTAR_ORDENS_DE_SERVICO_DE_COBRANCA_POR_RESULTADO);
		
		return retorno;
	}

}
