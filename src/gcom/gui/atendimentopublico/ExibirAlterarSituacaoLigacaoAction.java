package gcom.gui.atendimentopublico;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Quadra;import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ConectorAnd;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class ExibirAlterarSituacaoLigacaoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,			ActionForm actionForm, HttpServletRequest httpServletRequest,			HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("alterarSituacaoLigacao");
		AlterarSituacaoLigacaoActionForm form = (AlterarSituacaoLigacaoActionForm) actionForm;		Fachada fachada = Fachada.getInstancia();		HttpSession sessao = httpServletRequest.getSession(false);		String indicadorRedeAgua = null;		String indicadorRedeEsgoto = null;		String idOrdemServico = null;
		idOrdemServico = form.getIdOrdemServico();
		this.pesquisarSelectObrigatorio(httpServletRequest, form, indicadorRedeAgua, indicadorRedeEsgoto);
		OrdemServico ordemServico = null;
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));			if (ordemServico != null) {				boolean  veioEncerrarOS = false;				fachada.validarOrdemServicoAlterarSituacaoLigacao(ordemServico, veioEncerrarOS);
				form.setIdOrdemServico(idOrdemServico);				form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());				sessao.setAttribute("ordemServico", ordemServico);
				Imovel imovel = ordemServico.getImovel();				String matriculaImovel = imovel.getId().toString();				form.setMatriculaImovel("" + matriculaImovel);
				sessao.setAttribute("imovel", ordemServico.getImovel());
				if (imovel != null) {					form.setMatriculaImovel(imovel.getId().toString());
					String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId());					form.setInscricaoImovel(inscricaoImovel);
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();					form.setSituacaoLigacaoAguaAtual(situacaoLigacaoAgua);
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();					form.setSituacaoLigacaoEsgotoAtual(situacaoLigacaoEsgoto);

					IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());					indicadorRedeAgua = integracao.getIndicadorRedeAgua().toString();					indicadorRedeEsgoto = integracao.getIndicadorRedeEsgoto().toString();					this.pesquisarSelectObrigatorio(httpServletRequest, form, indicadorRedeAgua, indicadorRedeEsgoto);
					this.pesquisarCliente(form, new Integer(matriculaImovel));				}			} else {				httpServletRequest.setAttribute("OrdemServicoInexistente", true);				form.setIdOrdemServico("");				form.setNomeOrdemServico("ORDEM DE SERVIÇO INEXISTENTE");			}		}		return retorno;	}
	private void pesquisarCliente(AlterarSituacaoLigacaoActionForm form, Integer matriculaImovel) {		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, matriculaImovel));		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO,				new Integer(ClienteRelacaoTipo.USUARIO)));		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel.iterator().next();			Cliente cliente = clienteImovel.getCliente();			String documento = "";
			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {				documento = cliente.getCpfFormatado();			} else {				documento = cliente.getCnpjFormatado();			}
			form.setClienteUsuario(cliente.getNome());			form.setCpfCnpjCliente(documento);		} else {			throw new ActionServletException("atencao.naocadastrado", null, "Cliente");		}	}
	private void pesquisarSelectObrigatorio(HttpServletRequest httpServletRequest, AlterarSituacaoLigacaoActionForm form, 
			String indicadorRedeAgua, String indicadorRedeEsgoto) {
		Fachada fachada = Fachada.getInstancia();		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();		
		httpServletRequest.setAttribute("colecaoLigacaoAguaSituacao", new ArrayList());		httpServletRequest.setAttribute("colecaoLigacaoEsgotoSituacao",new ArrayList());
		if (form.getIndicadorTipoLigacao() != null) {			if (form.getIndicadorTipoLigacao().equals("1")	|| form.getIndicadorTipoLigacao().equals("3")) {				if (indicadorRedeAgua != null) {
					Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = null;
					httpServletRequest.setAttribute("comboLigacaoAgua", "sim");
										if (indicadorRedeAgua.equalsIgnoreCase("" + Quadra.SEM_REDE)) {						filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(								FiltroLigacaoAguaSituacao.ID, LigacaoAguaSituacao.POTENCIAL));						colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());					}					if (indicadorRedeAgua.equalsIgnoreCase("" + Quadra.COM_REDE)) {
						filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(								FiltroLigacaoAguaSituacao.ID, LigacaoAguaSituacao.FACTIVEL));
						filtroLigacaoAguaSituacao.adicionarParametro( new ParametroSimples(
								FiltroLigacaoAguaSituacao.INDICADOR_FATURAMENTO, ConstantesSistema.NAO ));												colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
					}					if (indicadorRedeAgua.equalsIgnoreCase(""+ Quadra.REDE_PARCIAL)) {						filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID,								LigacaoAguaSituacao.POTENCIAL, ConectorOr.CONECTOR_OR ));						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID,								LigacaoAguaSituacao.FACTIVEL, ConectorAnd.CONECTOR_AND, 2));
						filtroLigacaoAguaSituacao.adicionarParametro( new ParametroSimples(
								FiltroLigacaoAguaSituacao.INDICADOR_FATURAMENTO, ConstantesSistema.NAO ) );													colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());					}
					
										if (colecaoLigacaoAguaSituacao == null || colecaoLigacaoAguaSituacao.isEmpty()) {						throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",								null, "Tabela Ligacao Agua Situacao ");					}					
					httpServletRequest.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);				}			}

			if (form.getIndicadorTipoLigacao().equals("2") || form.getIndicadorTipoLigacao().equals("3")) {				if (indicadorRedeEsgoto != null) {
					
					Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = null;
					httpServletRequest.setAttribute("comboLigacaoEsgoto", "sim");
										if (indicadorRedeEsgoto.equalsIgnoreCase(""	+ Quadra.SEM_REDE)) {
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(								FiltroLigacaoEsgotoSituacao.ID, LigacaoEsgotoSituacao.POTENCIAL));						colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
					}
					if (indicadorRedeEsgoto.equalsIgnoreCase("" + Quadra.COM_REDE)) {						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(								FiltroLigacaoEsgotoSituacao.ID,	LigacaoEsgotoSituacao.FACTIVEL));
						filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.INDICADORFATURAMENTOSITUACAO, ConstantesSistema.NAO ) );													colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());					}
					if (indicadorRedeEsgoto.equalsIgnoreCase("" + Quadra.REDE_PARCIAL)) {						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.ID, LigacaoEsgotoSituacao.POTENCIAL,								ConectorOr.CONECTOR_OR ));						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(								FiltroLigacaoEsgotoSituacao.ID, LigacaoEsgotoSituacao.FACTIVEL,								ConectorAnd.CONECTOR_AND, 2));
						filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.INDICADORFATURAMENTOSITUACAO,
								ConstantesSistema.NAO));						colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
					}
										if (colecaoLigacaoEsgotoSituacao == null || colecaoLigacaoEsgotoSituacao.isEmpty()) {						throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",								null, "Ligacao Esgoto Situacao");					}					
					httpServletRequest.setAttribute("colecaoLigacaoEsgotoSituacao", colecaoLigacaoEsgotoSituacao);				}			}		}	}}
