package gcom.gui.micromedicao;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1057] Inserir Consumo Anormalidade e Ação
 * 
 * 
 * @author Rodrigo Cabral
 * @date 29/09/2010
 */
public class ExibirInserirConsumoAnormalidadeAcaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("inserirConsumoAnormalidadeAcao");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirConsumoAnormalidadeAcaoActionForm form = (InserirConsumoAnormalidadeAcaoActionForm) actionForm;


		//coleção anormalidade consumo
		FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
		filtroConsumoAnormalidade
				.setCampoOrderBy(FiltroConsumoAnormalidade.ID);
		
		Collection colecaoConsumoAnormalidade = fachada.pesquisar(
				filtroConsumoAnormalidade,
				ConsumoAnormalidade.class.getName());
		sessao.setAttribute("colecaoConsumoAnormalidade",
				colecaoConsumoAnormalidade);
		
		
		//Coleção Categoria
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		filtroCategoria
				.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		
		Collection colecaoCategoria = fachada.pesquisar(
				filtroCategoria,
				Categoria.class.getName());
		sessao.setAttribute("colecaoCategoria",
				colecaoCategoria);
		
		//Coleção Perfil do Imovel
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil
				.setCampoOrderBy(FiltroImovelPerfil.ID);
		
		Collection colecaoImovelPerfil = fachada.pesquisar(
				filtroImovelPerfil,
				ImovelPerfil.class.getName());
		sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		
		//coleção anormalidade leitura consumo
	   
		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo
				.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);
		
		Collection colecaoLeituraAnormalidadeConsumo = fachada.pesquisar(
				filtroLeituraAnormalidadeConsumo,
				LeituraAnormalidadeConsumo.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeConsumo", colecaoLeituraAnormalidadeConsumo);

		
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		//
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("2")|| objetoConsulta.trim().equals("3"))  ) {

			// Faz a consulta de Localidade
			this.pesquisarServicoTipo(form,objetoConsulta, httpServletRequest);
			
		}
		
		//coleção tipo de solicitação
		   
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO, "1"));
		
		filtroSolicitacaoTipo
				.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);
		
		filtroSolicitacaoTipo.adicionarParametro(
    			new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA,
    					ConstantesSistema.INDICADOR_USO_DESATIVO));
		filtroSolicitacaoTipo.adicionarParametro(
    			new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO,
    					ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoSolicitacaoTipo = fachada.pesquisar(
				filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());

		
		sessao.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacaoTipo);
		
		
		//coleção tipo de solicitação especificação para o 1º mês
		   
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao1 = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao1
		.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
		
		filtroSolicitacaoTipoEspecificacao1.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, "1"));

		if (form.getSolicitacaoTipoMes1() != null && !form.getSolicitacaoTipoMes1().equals("-1")){
		filtroSolicitacaoTipoEspecificacao1.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, form.getSolicitacaoTipoMes1()));	
		}		

		Collection colecaoSolicitacaoTipoEspecificacaoMes1 = fachada.pesquisar(
				filtroSolicitacaoTipoEspecificacao1, 
				SolicitacaoTipoEspecificacao.class.getName());
		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacaoMes1", colecaoSolicitacaoTipoEspecificacaoMes1);

		
		//coleção tipo de solicitação especificação para o 2º mês
		   
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao2 = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao2
		.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
		
		filtroSolicitacaoTipoEspecificacao2.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, "1"));

		if (form.getSolicitacaoTipoMes2() != null && !form.getSolicitacaoTipoMes2().equals("-1")){
		filtroSolicitacaoTipoEspecificacao2.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, form.getSolicitacaoTipoMes2()));	
		}		

		Collection colecaoSolicitacaoTipoEspecificacaoMes2 = fachada.pesquisar(
				filtroSolicitacaoTipoEspecificacao2, 
				SolicitacaoTipoEspecificacao.class.getName());
		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacaoMes2", colecaoSolicitacaoTipoEspecificacaoMes2);

		
		//coleção tipo de solicitação especificação para o 3º mês
		   
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao3 = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao3
		.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
		
		filtroSolicitacaoTipoEspecificacao3.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, "1"));

		if (form.getSolicitacaoTipoMes3() != null && !form.getSolicitacaoTipoMes3().equals("-1")){
		filtroSolicitacaoTipoEspecificacao3.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, form.getSolicitacaoTipoMes3()));	
		}		

		Collection colecaoSolicitacaoTipoEspecificacaoMes3 = fachada.pesquisar(
				filtroSolicitacaoTipoEspecificacao3, 
				SolicitacaoTipoEspecificacao.class.getName());
		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacaoMes3", colecaoSolicitacaoTipoEspecificacaoMes3);
		
		
		// devolve o mapeamento de retorno
		return retorno;
	}
	
	//Pesquisar Tipo de Serviço para o Mes 1,2 e 3

	private void pesquisarServicoTipo(InserirConsumoAnormalidadeAcaoActionForm form,
			String objetoConsulta, HttpServletRequest httpServletRequest) {

			Object local = null;
			
			if(objetoConsulta.trim().equals("1")){
				local = form.getIdServicoTipoMes1();
				
			}else if(objetoConsulta.trim().equals("2")){
				local = form.getIdServicoTipoMes2();
				
			}else if(objetoConsulta.trim().equals("3")){
				local = form.getIdServicoTipoMes3();
				
			}
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(
				new ParametroSimples(FiltroServicoTipo.ID,local));
			
			// Recupera Tipo de Serviço
			Collection colecaoServicoTipo = 
				this.getFachada().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
		
			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {

				ServicoTipo servicoTipo = 
					(ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
				
				if(objetoConsulta.trim().equals("1")){
					form.setIdServicoTipoMes1(servicoTipo.getId().toString());
					form.setDesServicoTipoMes1(servicoTipo.getDescricao());
				
				}
				
				if(objetoConsulta.trim().equals("2")){
					form.setIdServicoTipoMes2(servicoTipo.getId().toString());
					form.setDesServicoTipoMes2(servicoTipo.getDescricao());
				}
				
				if(objetoConsulta.trim().equals("3")){
					form.setIdServicoTipoMes3(servicoTipo.getId().toString());
					form.setDesServicoTipoMes3(servicoTipo.getDescricao());
				}
				

			} else {
				if(objetoConsulta.trim().equals("1")){
					form.setIdServicoTipoMes1(null);
					form.setDesServicoTipoMes1("Tipo de serviço inexistente");
					httpServletRequest.setAttribute("servicoTipoInexistente","true");
		
				}if(objetoConsulta.trim().equals("2")){
					form.setIdServicoTipoMes2(null);
					form.setDesServicoTipoMes2("Tipo de serviço inexistente");
					httpServletRequest.setAttribute("servicoTipoInexistente","true");
					
				}if(objetoConsulta.trim().equals("3")){
					form.setIdServicoTipoMes3(null);
					form.setDesServicoTipoMes3("Tipo de serviço inexistente");
					httpServletRequest.setAttribute("servicoTipoInexistente","true");
					
				}
				
			
			}
		}
	
	
	}
