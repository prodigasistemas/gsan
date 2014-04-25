package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.NegativacaoImoveis;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegItem;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativacaoImoveis;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorMovimentoRegItem;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Thiago Silva Toscano de Brito
 * @date 22/12/2007
 */
public class ExibirExcluirNegativacaoOnLineAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirExcluirNegativacaoOnLine");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		ExcluirNegativacaoOnLineActionForm form = (ExcluirNegativacaoOnLineActionForm) actionForm;
		form.setDataHoje(Util.formatarData(new Date()));
		form.setDataEnvio(Util.formatarData(new Date()));


		FiltroNegativador fn = new FiltroNegativador();
		fn.adicionarCaminhoParaCarregamentoEntidade("cliente");
		String[] ordem = {"cliente.nome"};
		fn.setCampoOrderBy(ordem);

		Collection collection = Fachada.getInstancia().pesquisar(fn,Negativador.class.getName());
		form.setCollNegativador(collection);

		String idImovel =  form.getIdImovel();
		
		// Verifica se o id do imovel não está cadastrado
		if (form.getNegativador() != null && !form.getNegativador().trim().equals("")) {
		
			FiltroNegativadorExclusaoMotivo fnem = new FiltroNegativadorExclusaoMotivo();
			fnem.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.NEGATIVADOR_ID,form.getNegativador()));
			fnem.setCampoOrderBy("codigoExclusaoMotivo");
			Collection colecaoNegativadorExclusaoMotivo = fachada.pesquisar(fnem, NegativadorExclusaoMotivo.class.getName());
			form.setCollMotivoExclusao(colecaoNegativadorExclusaoMotivo);
		}


		// Verifica se o id do imovel não está cadastrado
		if (idImovel != null && !idImovel.trim().equals("")) {

			// Filtro para descobrir id do Imovel
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CEP);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.UNIDADE_FEDERACAO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.ENDERECO_REFERENCIA);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TIPO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.COBRANCA_SITUACAO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TITULO);

			Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());

			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				form.setIdImovel(""); 
				form.setInscricaoImovel( "IMOVEL INEXISTENTE" );
				httpServletRequest.setAttribute("existeImovel","exception");
				httpServletRequest.setAttribute("nomeCampo","idImovel");

				getSessao(httpServletRequest).setAttribute("negativadorMovimentoReg", null);
				getSessao(httpServletRequest).setAttribute("imovel", null);
				getSessao(httpServletRequest).setAttribute("itensConta", null);
				getSessao(httpServletRequest).setAttribute("itensGuiaPagamento", null);

			}else{
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

				if (!form.getIdImovel().equals(form.getIdImovelAnterior())  || 
						!form.getNegativador().equals(form.getNegativadorAnterior()) ) {

//					form.getIdImovelAnterior() == null || form.getIdImovelAnterior().equals("") || 
					form.setIdUsuario("");
					form.setMotivoExclusao("");
					form.setDataExclusao("");
					form.setUsuarioNaoEncontrada("");
					form.setNomeUsuario("");

					getSessao(httpServletRequest).setAttribute("negativadorMovimentoReg", null);
					getSessao(httpServletRequest).setAttribute("imovel", null);
					getSessao(httpServletRequest).setAttribute("itensConta", null);
					getSessao(httpServletRequest).setAttribute("itensGuiaPagamento", null);
					getSessao(httpServletRequest).setAttribute("situacaoNegativacao", null);

					form.setNegativadorAnterior(form.getNegativador());
					form.setIdImovelAnterior(imovel.getId().toString());
					form.setIdImovel(imovel.getId().toString());
					String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId()); 
					form.setInscricaoImovel(inscricaoImovel);
					httpServletRequest.setAttribute("existeImovel","true");
					httpServletRequest.setAttribute("nomeCampo","idImovel");
	
					// caso tenha passado o negativador
					if (form.getNegativador() != null && !form.getNegativador().equals("")) {
						
						// caso nao tenha negativacao para o negativador selecionado
						fn = new FiltroNegativador();
						fn.adicionarParametro(new ParametroSimples(FiltroNegativador.ID,form.getNegativador()));
						fn.adicionarCaminhoParaCarregamentoEntidade("cliente");
						Negativador negativador = (Negativador) Util.retonarObjetoDeColecao(fachada.pesquisar(fn, Negativador.class.getName()));
						if (negativador == null) {
							getSessao(httpServletRequest).setAttribute("negativadorMovimentoReg", null);
							getSessao(httpServletRequest).setAttribute("imovel", null);
							getSessao(httpServletRequest).setAttribute("itensConta", null);
							getSessao(httpServletRequest).setAttribute("itensGuiaPagamento", null);
							getSessao(httpServletRequest).setAttribute("situacaoNegativacao", null);
							
							throw new ActionServletException("atencao.negativador.nao.selecionado");
						}
	
//						FiltroNegativacaoImoveis fni = new FiltroNegativacaoImoveis();
//						fni.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.NEGATIVADOR_ID,form.getNegativador()));
//						fni.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID,imovel.getId()));
//						fni.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.INDICADOR_EXCLUIDO,"2"));
//						Collection colecaoNegativacao = fachada.pesquisar(fni, NegativacaoImoveis.class.getName());
						
						Negativador negativ = new Negativador();
						negativ.setId(new Integer(form.getNegativador()));
						
						Collection colecaoNegativacao = fachada.pesquisarImovelNegativado(imovel,negativ);
						
						if (colecaoNegativacao == null || colecaoNegativacao.isEmpty()) {
							String[] parametros = {negativador.getCliente().getNome()};
							getSessao(httpServletRequest).setAttribute("negativadorMovimentoReg", null);
							getSessao(httpServletRequest).setAttribute("imovel", null);
							getSessao(httpServletRequest).setAttribute("itensConta", null);
							getSessao(httpServletRequest).setAttribute("itensGuiaPagamento", null);
							getSessao(httpServletRequest).setAttribute("situacaoNegativacao", null);
							
							throw new ActionServletException("atencao.nao.ha.negativacao.para.imovel.selecionado",null,parametros);
						}
						
						
//						..................................................................................................................................						
//						alteração - 05/03/2008 [início]
//						..................................................................................................................................									
						Collection coll = fachada.pesquisarNegatiacaoParaImovel(imovel, negativador);
											     
						if (coll == null || coll.size() == 0) {
							String[] parametros = {negativador.getCliente().getNome()};																
							getSessao(httpServletRequest).setAttribute("negativadorMovimentoReg", null);
						    getSessao(httpServletRequest).setAttribute("imovel", null);
							getSessao(httpServletRequest).setAttribute("itensConta", null);
							getSessao(httpServletRequest).setAttribute("itensGuiaPagamento", null);
							getSessao(httpServletRequest).setAttribute("situacaoNegativacao", null);
																						
							throw new ActionServletException("atencao.negativacao_para_imovel_sem.retorno_ou_nao_aceita",null,parametros);
						} 						
//						..................................................................................................................................						
//						alteração - 05/03/2008 [fim]
//						..................................................................................................................................									
						
						else {
						// caso tenha carregue
//
//							FiltroNegativadorMovimentoReg fnmr = new FiltroNegativadorMovimentoReg();
//							fnmr.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoReg.IMOVEL_ID,imovel.getId()));
//							fnmr.adicionarParametro(new ParametroNulo(FiltroNegativadorMovimentoReg.CODIGO_EXCLUSAO_TIPO));
//							fnmr.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoReg.NEGATIVADOR_ID,negativador.getId()));
//							fnmr.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoReg.NEGATIVADOR_MOVIMENTO_CODIGOMOVIMENTO,"1"));
//							fnmr.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoReg.NEGATIVADOR);
//							fnmr.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoReg.CLIENTE);
//							fnmr.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoReg.NEGATIVADOR_MOVIMENTO_NEGATIVACAO_COMANDO);
//							
//							NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg)Util.retonarObjetoDeColecao(fachada.pesquisar(fnmr, NegativadorMovimentoReg.class.getName()));
						
							NegativadorMovimentoReg negativadorMovimentoReg = fachada.pesquisarNegativadorMovimentoRegInclusao(imovel, negativador);
							
							if (negativadorMovimentoReg == null ) {
								String[] parametros = {negativador.getCliente().getNome()};
								
								getSessao(httpServletRequest).setAttribute("negativadorMovimentoReg", null);
								getSessao(httpServletRequest).setAttribute("imovel", null);
								getSessao(httpServletRequest).setAttribute("itensConta", null);
								getSessao(httpServletRequest).setAttribute("itensGuiaPagamento", null);
								getSessao(httpServletRequest).setAttribute("situacaoNegativacao", null);
								
								throw new ActionServletException("atencao.nao.ha.negativacao.para.imovel.selecionado",null,parametros);
							} 
							

							FiltroNegativadorMovimentoRegItem fnmri = new FiltroNegativadorMovimentoRegItem();
							fnmri.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,negativadorMovimentoReg.getId()));
							fnmri.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.DOCUMENTO_TIPO_ID,DocumentoTipo.CONTA));
							fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.CONTA_GERAL_CONTA_HISTORICO);
							fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.CONTA_GERAL_CONTA);
							fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO);
							fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_HISTORICO);
							fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.COBRANCA_DEBITO_SITUACAO);
							Collection itensConta = fachada.pesquisar(fnmri, NegativadorMovimentoRegItem.class.getName());

							fnmri = new FiltroNegativadorMovimentoRegItem();
							fnmri.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,negativadorMovimentoReg.getId()));
							fnmri.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.DOCUMENTO_TIPO_ID,DocumentoTipo.GUIA_PAGAMENTO));
							fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.CONTA_GERAL_CONTA_HISTORICO);
							fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.CONTA_GERAL_CONTA);
							fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO);
							fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_HISTORICO);
							fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.COBRANCA_DEBITO_SITUACAO);
							Collection itensGuiaPagamento = fachada.pesquisar(fnmri, NegativadorMovimentoRegItem.class.getName());

							
							FiltroNegativacaoImoveis fnii = new FiltroNegativacaoImoveis();
//							fnii.adicionarParametro(new ParametroNaoNulo("dataConfirmacao"));
							fnii.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID,imovel.getId()));
							fnii.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.NEGATIVACAO_COMANDO_ID,negativadorMovimentoReg.getNegativadorMovimento().getNegativacaoComando().getId()));
							NegativacaoImoveis ni = (NegativacaoImoveis) Util.retonarObjetoDeColecao(fachada.pesquisar(fnii, NegativacaoImoveis.class.getName()));
							if (ni != null && ni.getDataConfirmacao() != null) {
								getSessao(httpServletRequest).setAttribute("situacaoNegativacao", "Confirmado");
							} else {
								getSessao(httpServletRequest).setAttribute("situacaoNegativacao", "Não Confirmado");
							}
							getSessao(httpServletRequest).setAttribute("negativadorMovimentoReg", negativadorMovimentoReg);
							getSessao(httpServletRequest).setAttribute("imovel", imovel);
							getSessao(httpServletRequest).setAttribute("itensConta", itensConta);
							getSessao(httpServletRequest).setAttribute("itensGuiaPagamento", itensGuiaPagamento);

							form.setDataEnvio(Util.formatarData(negativadorMovimentoReg.getNegativadorMovimento().getDataEnvio()));
						}

					} else {
						// caso nao tenha passado o negativador
						// levanta excecao pq eh obrigatorio
						getSessao(httpServletRequest).setAttribute("negativadorMovimentoReg", null);
						getSessao(httpServletRequest).setAttribute("imovel", null);
						getSessao(httpServletRequest).setAttribute("itensConta", null);
						getSessao(httpServletRequest).setAttribute("itensGuiaPagamento", null);
						getSessao(httpServletRequest).setAttribute("situacaoNegativacao", null);
						
						throw new ActionServletException("atencao.negativador.nao.selecionado");
					}
				}	
			}
		} else {
		// não foi selecionado o imovel	
			form.setIdUsuario("");
			form.setMotivoExclusao("");
			form.setDataExclusao("");
			form.setUsuarioNaoEncontrada("");
			form.setNomeUsuario("");

			getSessao(httpServletRequest).setAttribute("negativadorMovimentoReg", null);
			getSessao(httpServletRequest).setAttribute("imovel", null);
			getSessao(httpServletRequest).setAttribute("itensConta", null);
			getSessao(httpServletRequest).setAttribute("itensGuiaPagamento", null);
			getSessao(httpServletRequest).setAttribute("situacaoNegativacao", null);
		}

		if (!"".equals(form.getIdUsuario()) && form.getIdUsuario() != null) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			// coloca parametro no filtro
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.ID, form.getIdUsuario()));

			// pesquisa
			Collection coll = Fachada.getInstancia().pesquisar(filtroUsuario,
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

		form.setDataExclusao(Util.formatarData(new Date()));
		
		
		
		return retorno;
	}
}
