package gcom.gui.seguranca.acesso.transacao;

import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeDependencia;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAcao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.email.ErroEmailException;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarOperacaoEfetuadaAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ErroEmailException {

		ActionForward retorno = actionMapping.findForward("filtrar");
		FiltrarOperacaoEfetuadaActionForm form = (FiltrarOperacaoEfetuadaActionForm) actionForm;
		

		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.setAttribute("acao", form.getAcao());
		sessao.setAttribute("usuarioAlteracao", null);
		sessao.setAttribute("mostrarLogin",true);

		FiltroUsuarioAcao filtroUsuarioAcao = new FiltroUsuarioAcao();
		filtroUsuarioAcao.adicionarParametro(
			new ParametroSimplesDiferenteDe(
				FiltroUsuarioAcao.INDICADO_USO, ConstantesSistema.NAO));

		Collection coll =
			this.getFachada().pesquisar(filtroUsuarioAcao,UsuarioAcao.class.getSimpleName());
		form.setCollUsuarioAcao(coll);
		
		if (form.getCollUsuarioAcao() == null || form.getCollUsuarioAcao().isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null," A Tabela Usuario Ação está ");
		}

		Object valorSessao = sessao.getAttribute("idFuncionalidade");
	
		if (!equalValorSessaoToForm(form.getIdFuncionalidade(), valorSessao) || 
				(httpServletRequest.getParameter("funcionalidadeLimpa") != null &&
				  httpServletRequest.getParameter("funcionalidadeLimpa").equals("1"))){
			
			sessao.removeAttribute("idFuncionalidade");
			sessao.removeAttribute("argumentos");
			sessao.removeAttribute("nomeOperacaoSelecionada");
		}
		valorSessao = sessao.getAttribute("idUsuario"); 
		if (!equalValorSessaoToForm(form.getIdUsuario(), valorSessao) || 
				(httpServletRequest.getParameter("usuarioLimpo") != null &&
				  httpServletRequest.getParameter("usuarioLimpo").equals("1"))){
			
			sessao.removeAttribute("idUsuario");
			sessao.removeAttribute("nomeUsuario");
		}
		
		valorSessao = sessao.getAttribute("dataInicial");
		if (!equalValorSessaoToForm(form.getDataInicial(), valorSessao) ||
			!equalValorSessaoToForm(form.getDataFinal(), sessao.getAttribute("dataFinal")) ||
				(httpServletRequest.getParameter("dataLimpa") != null &&
				  httpServletRequest.getParameter("dataLimpa").equals("1"))){
			
			sessao.removeAttribute("dataInicial");
			sessao.removeAttribute("dataFinal");
		}
		
		valorSessao = sessao.getAttribute("horaInicial");
		if (!equalValorSessaoToForm(form.getHoraInicial(), valorSessao) ||
			!equalValorSessaoToForm(form.getHoraFinal(), sessao.getAttribute("horaFinal")) ||
				(httpServletRequest.getParameter("horaLimpa") != null &&
				  httpServletRequest.getParameter("horaLimpa").equals("1"))){
			
			sessao.removeAttribute("horaInicial");
			sessao.removeAttribute("horaFinal");
		}		
		if (httpServletRequest.getParameter("argumentoLimpo") != null &&
				  httpServletRequest.getParameter("argumentoLimpo").equals("1")){
			
			sessao.removeAttribute("argumentos");
		}		
		
		if (sessao.getAttribute("idFuncionalidade") != null) {
			form.setIdFuncionalidade(String.valueOf((Integer)sessao.getAttribute("idFuncionalidade")));
		}	 
		if (sessao.getAttribute("idUsuario") != null) {
			form.setIdUsuario((String)sessao.getAttribute("idUsuario"));
		}	

		Date dataAtual = new Date();
		if (sessao.getAttribute("dataInicial") != null){
			String data = Util.formatarData((Date) sessao.getAttribute("dataInicial"));
			form.setDataInicial(data);			
		} else {
			form.setDataInicial(Util.formatarData(Util.adicionarNumeroDiasDeUmaData(dataAtual, -30)));
		}
		
		if (sessao.getAttribute("dataFinal") != null){
			String data = Util.formatarData((Date) sessao.getAttribute("dataFinal"));
			form.setDataFinal(data);		
		} else {
			form.setDataFinal(Util.formatarData(dataAtual));
		}

		if (sessao.getAttribute("horaInicial") != null){
			String hora = Util.formatarHoraSemSegundos((Date) sessao.getAttribute("horaInicial"));
			form.setHoraInicial(hora);			
		}

		if (sessao.getAttribute("horaFinal") != null){
			String hora = Util.formatarHoraSemSegundos((Date) sessao.getAttribute("horaFinal"));
			form.setHoraFinal(hora);						
		}
		
		if (sessao.getAttribute("argumentos") != null){
			Hashtable<String,String> argumentos = (Hashtable<String,String>) sessao.getAttribute("argumentos");
			if (argumentos != null && argumentos.size() > 0 ){
				for(Enumeration e = argumentos.keys(); e.hasMoreElements();) {
					String idArgumento = (String) e.nextElement();
					String valorArgumento = argumentos.get(idArgumento);
					form.setIdArgumentoPesquisa(idArgumento);
					form.setValorArgumentoPesquisa(valorArgumento);
				}
			}			
		}
		
		// se tem id da operacao entao pega o nome da operacao
		if (!"".equals(form.getIdFuncionalidade()) && form.getIdFuncionalidade() != null) {

			Integer idFuncionalidade = new Integer(form.getIdFuncionalidade());

			FiltroFuncionalidade filtroFunc = new FiltroFuncionalidade();
			filtroFunc.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.OPERACOES);
			filtroFunc.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS);

			// coloca parametro no filtro
			filtroFunc.adicionarParametro(
				new ParametroSimples(
					FiltroFuncionalidade.ID, 
					idFuncionalidade));
			
			filtroFunc.adicionarParametro(
				new ParametroSimples(
					FiltroFuncionalidade.OPERACOES_INDICADOR_REGISTRA_TRANSACAO, 
					ConstantesSistema.SIM));

			Vector operacoes = new Vector();
			
			// pesquisa
			coll = 
				this.getFachada().pesquisar(filtroFunc,
					Funcionalidade.class.getName());
			
			if (coll != null && !coll.isEmpty()) {
				Funcionalidade funcionalidade = (Funcionalidade) coll.iterator().next();
			
				form.setNomeFuncionalidade(funcionalidade.getDescricao());
				
				// enviando nomeFuncionalidade para a tela de resultado de consultas
				sessao.setAttribute("nomeFuncionalidade", funcionalidade.getDescricao());
				
				form.setIdFuncionalidade(funcionalidade.getId().toString());
				form.setFuncionalidadeNaoEncontrada("false");				
				
				// verificando se tem operacao de pesquisa
				if (funcionalidade.getOperacoes() != null){
					for (Iterator iterator = funcionalidade.getOperacoes().iterator(); 
						iterator.hasNext();) {
						
						Operacao operacao = (Operacao) iterator.next();
							
							if (operacao.getArgumentoPesquisa() != null){
								
								FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
								filtroTabelaColuna.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaColuna.TABELA);
								
								filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.ID,
										operacao.getArgumentoPesquisa().getId()));
								
								Collection colTabCol = 
									this.getFachada().pesquisar(filtroTabelaColuna,
										TabelaColuna.class.getSimpleName());
								
								Iterator iterTabCol = colTabCol.iterator();
								if (iterTabCol.hasNext()){
									operacao.setArgumentoPesquisa((TabelaColuna) iterTabCol.next());
								}
								
								form.setIdArgumentoPesquisa(operacao.getArgumentoPesquisa().getId().toString());	
								form.setNomeArgumentoPesquisa(operacao.getArgumentoPesquisa().getDescricaoColuna());

								operacao.setDescricao(operacao.getDescricao().toUpperCase());
								operacoes.add(operacao);

								// enviar descricao do argumento para tela de resultado da consulta
								sessao.setAttribute("descricaoArgumento", operacao.getArgumentoPesquisa().getDescricaoColuna());
								
								form.setNomeTabela(operacao.getArgumentoPesquisa().getTabela().getNomeTabela());
							}

							
							if (operacao.getIdOperacaoPesquisa() != null){
								form.setUrl(operacao.getIdOperacaoPesquisa().getCaminhoUrl());
							}							
					}			
				}
				
				Collection dependencias = funcionalidade.getFuncionalidadeDependencias();
				
				if (dependencias != null) {
				
					for (Iterator iter = dependencias.iterator(); iter.hasNext();) {
						FuncionalidadeDependencia funcDepend = (FuncionalidadeDependencia) iter.next();

						FiltroFuncionalidade filtroFunc2 = new FiltroFuncionalidade();
						filtroFunc2.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.OPERACOES);

						// coloca parametro no filtro
						filtroFunc2.adicionarParametro(new ParametroSimples(
								FiltroOperacao.ID, funcDepend.getFuncionalidadeDependencia().getId()));
						
						coll = Fachada.getInstancia().pesquisar(filtroFunc2,
								Funcionalidade.class.getSimpleName());
						
						Funcionalidade func =  (Funcionalidade) Util.retonarObjetoDeColecao(coll);
						// verificando se tem operacao de pesquisa
						if (func.getOperacoes() != null){
							for (Iterator iterator = func.getOperacoes().iterator(); 
								iterator.hasNext();) {
								
								Operacao operacao = (Operacao) iterator.next();

									
								if (operacao.getArgumentoPesquisa() != null){
									
									FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
									filtroTabelaColuna.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaColuna.TABELA);
									filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.ID,
											operacao.getArgumentoPesquisa().getId()));
									
									Collection colTabCol = 
										this.getFachada().pesquisar(filtroTabelaColuna,
											TabelaColuna.class.getSimpleName());
									
									Iterator iterTabCol = colTabCol.iterator();
									
									if (iterTabCol.hasNext()){
										operacao.setArgumentoPesquisa((TabelaColuna) iterTabCol.next());
									}

									form.setNomeTabela(operacao.getArgumentoPesquisa().getTabela().getNomeTabela());
									operacao.setDescricao(operacao.getDescricao().toUpperCase());										
									operacoes.add(operacao);
									
									if (form.getIdArgumentoPesquisa() != null && form.getIdArgumentoPesquisa().equals("")){
										form.setIdArgumentoPesquisa(operacao.getArgumentoPesquisa().getId().toString());	
										form.setNomeArgumentoPesquisa(operacao.getArgumentoPesquisa().getDescricaoColuna());
									}
								}								
							}			
						}
						
					}
				}
				
				sessao.setAttribute("operacoes", operacoes);

			} else {
				
				this.setarPametros(form,sessao);
				
				throw new ActionServletException(
					"atencao.funcionalidade_nao_registra_transacao", 
					null,
					form.getIdFuncionalidade());

			}
		} else {
			
			
			this.setarPametros(form,sessao);
		}	
		
		//Unidade de Negocio
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,
				UnidadeNegocio.class.getName());

		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Unidade Negocio");
		}

		// se tem id do usuario entao pega o nome do usuario
		if (!"".equals(form.getIdUsuario()) && form.getIdUsuario() != null) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			// coloca parametro no filtro
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, form.getIdUsuario()));

			// pesquisa
			coll = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getName());
			if (coll != null && !coll.isEmpty()) {
				// O cliente foi encontrado
				// inserirImovelFiltrarActionForm.set("idCliente", ((Cliente)
				// ((List) clientes).get(0)).getId().toString());
				form.setNomeUsuario(((Usuario) ((List) coll).get(0))
						.getNomeUsuario());
				
				sessao.setAttribute("nomeUsuario", form.getNomeUsuario());
				
				form.setUsuarioNaoEncontrada("false");
			} else {
				form.setUsuarioNaoEncontrada("true");
				form.setNomeUsuario("");
			}
		} else {
			form.setNomeUsuario("");
		}
		
		// valor default do nome do argumento de pesquisa
		if (form.getNomeArgumentoPesquisa() == null || form.getNomeArgumentoPesquisa().equals("")){
			form.setNomeArgumentoPesquisa("Matrícula Imóvel");			
		}
		
		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		
		return retorno;
	}

	private boolean equalValorSessaoToForm(String valorForm, Object valorSessao){
		if (valorForm == null && valorSessao == null){
			return true;
		}
		if (valorForm != null){
			if (valorSessao == null){
				return false;
			}
			String valorSessaoStr = valorSessao.toString();
			return valorForm.equals(valorSessaoStr);
		}
		return false;						
	}
	
	private void setarPametros(FiltrarOperacaoEfetuadaActionForm form,HttpSession sessao){
		
		form.setNomeFuncionalidade("");
		form.setNomeArgumentoPesquisa("");
		form.setIdArgumentoPesquisa("");
		form.setOperacoes(null);
		sessao.setAttribute("operacoes", null);

		// 2.4
		Collection coll = this.getFachada().getTabelaColunaPertencenteOperacao();
		form.setCollTabelaColunaSemOperacao(coll);

		// 2.1
		form.setDesabilitaArgumentoPesquisa("true");

		// 2.2
		form.setDesabilitaInformacoesDisponiveis("true");
		// 2.3
		form.setIdTabelaColunaSemOperacao("");
		
		// remove descricao do argumento da sessao 
		sessao.removeAttribute("descricaoArgumento");
		// remove nomeFuncionalidade da sessao
		sessao.removeAttribute("nomeFuncionalidade");
	}

}

