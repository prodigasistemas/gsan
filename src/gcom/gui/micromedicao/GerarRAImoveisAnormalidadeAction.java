package gcom.gui.micromedicao;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.RABuilder;
import gcom.atendimentopublico.registroatendimento.RADadosGeraisHelper;
import gcom.atendimentopublico.registroatendimento.RALocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.RASolicitanteHelper;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para inserir uma resolu��o de diretoria no banco
 * 
 * [UC0217] Inserir Resolu��o de Diretoria Permite inserir uma
 * 
 * @author Rafael Corr�a
 * @since 08/06/2008
 */
public class GerarRAImoveisAnormalidadeAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		GerarRAImoveisAnormalidadeActionForm gerarRAImoveisAnormalidadeActionForm = (GerarRAImoveisAnormalidadeActionForm) actionForm;
		
		// Recupera os im�veis selecionados pelo usu�rio
		Collection<Imovel> colecaoImoveisGerarOS = (Collection) sessao.getAttribute("colecaoImoveisGerarOS");
		HashMap<Integer, String> colecaoObservacaoOS = (HashMap<Integer, String>) sessao.getAttribute("colecaoObservacaoOS");
		
		int qtde = 0;
		

		if (colecaoImoveisGerarOS != null && !colecaoImoveisGerarOS.isEmpty()) {
			
			Integer idMeioSolicitacao = MeioSolicitacao.INTERNO;
			
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO);
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipoEspecificacao()));
			
			Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			
			Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
			UnidadeOrganizacional unidadeAtendimento = fachada.pesquisarUnidadeUsuario(usuarioLogado.getId());
			
			DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacaoHelper = fachada
					.definirDataPrevistaUnidadeDestinoEspecificacao(new Date(),
							solicitacaoTipoEspecificacao.getId()); 
			
			for (Imovel imovel : colecaoImoveisGerarOS) {
				
				// Verifica se j� existe uma RA com esse tipo de especifica��o para este im�vel, caso exista descarta o im�vel
				FiltroRegistroAtendimento filtroRA = new FiltroRegistroAtendimento();
				filtroRA.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.IMOVEL_ID, imovel.getId()));
				filtroRA.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID_SOLICITACAO_TIPO_ESPECIFICACAO, solicitacaoTipoEspecificacao.getId()));
				filtroRA.adicionarParametro(new ParametroNulo(FiltroRegistroAtendimento.ID_ATENDIMENTO_MOTIVO_ENCERRAMENTO));
				
				Collection colecaoRA = fachada.pesquisar(filtroRA, RegistroAtendimento.class.getName());
				String observacao = colecaoObservacaoOS.get(imovel.getId());
				
				if (colecaoRA == null || colecaoRA.isEmpty()) {
					
					Date dataAtual = new Date();
					String dataAtendimento = Util.formatarData(dataAtual);
					String horaAtendimento = Util.formatarHoraSemData(dataAtual);

					Collection colecaoEnderecos = new ArrayList();
				
					Imovel imovelEndereco = fachada.pesquisarImovelParaEndereco(imovel.getId());
				
					colecaoEnderecos.add(imovelEndereco);
				
					qtde++;
					
					//Obter a unidade Destino
					ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto = fachada
					.habilitarGeograficoDivisaoEsgoto(new Integer(gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipo()));
					
					// [SB0006] - Obt�m Divis�o de Esgoto
					DivisaoEsgoto divisaoEsgoto = fachada.obterDivisaoEsgoto(
							imovel.getQuadra().getId(),
							habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());

					UnidadeOrganizacional unidadeDestino = null;
					
					if (divisaoEsgoto != null) {
						
						unidadeDestino = fachada.definirUnidadeDestinoDivisaoEsgoto(
								new Integer(gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipoEspecificacao()), 
								divisaoEsgoto.getId(),
								habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto(),
								imovel.getLocalidade().getId(), 
								new Integer(gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipo()));
					}
					
					if ( unidadeDestino == null || unidadeDestino.equals("") ) {
					
						unidadeDestino = fachada.definirUnidadeDestinoLocalidade(
								new Integer(gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipoEspecificacao()),
								imovel.getLocalidade().getId(),
								new Integer(gerarRAImoveisAnormalidadeActionForm.getSolicitacaoTipo()),
								habilitaGeograficoDivisaoEsgoto.isSolicitacaoTipoRelativoAreaEsgoto());
					}
					
					RADadosGeraisHelper raDadosGerais = RABuilder.buildRADadosGerais(new Short("1"), idMeioSolicitacao, 
							solicitacaoTipoEspecificacao.getId(), definirDataPrevistaUnidadeDestinoEspecificacaoHelper.getDataPrevista(), 
							solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId(), unidadeAtendimento.getId(), usuarioLogado.getId(), observacao);

					RASolicitanteHelper raSolicitante = RABuilder.buildRASolicitante(false, unidadeAtendimento);
					raSolicitante.setIdServicoTipo(solicitacaoTipoEspecificacao.getId());

					if(unidadeDestino == null){
						
						RALocalOcorrenciaHelper raLocalOcorrencia = RABuilder.buildRALocalOcorrencia(imovel, colecaoEnderecos, ConstantesSistema.NAO, null);
						
						
						fachada.inserirRegistroAtendimento(raDadosGerais, raLocalOcorrencia, raSolicitante);
					
					}else{
					
						RALocalOcorrenciaHelper raLocalOcorrencia = RABuilder.buildRALocalOcorrencia(imovel, colecaoEnderecos, ConstantesSistema.NAO, unidadeDestino.getId());
					
						fachada.inserirRegistroAtendimento(raDadosGerais, raLocalOcorrencia, raSolicitante);
				
					}
					
					
				}else{
					fachada.verificarExistenciaRAImovelMesmaEspecificacao(imovel.getId(), solicitacaoTipoEspecificacao.getId());
				}
				
				
				if(observacao != null){
					Iterator<RegistroAtendimento> iterator = colecaoRA.iterator();
					StringBuilder sb = new StringBuilder();
					RegistroAtendimento ra = null;
					
					while (iterator.hasNext()){
						ra = iterator.next();
						OrdemServico os = fachada.pesquisarOrdemServicoRegistroAtendimento(ra);
						
						/*if(os.getObservacao() != null){
							sb.append(os.getObservacao());
							sb.append("; ");
						}*/
						
						sb.append(observacao);
						os.setObservacao(sb.toString());
						
						fachada.atualizar(os);
						qtde++;
					}
					
					
				}
			}
		}

		// Monta a p�gina de sucesso de acordo com o padr�o do sistema.
		montarPaginaSucessoComVoltarJavascript(httpServletRequest, qtde
				+ " Registro(s) Atendimento(s) inclu�do(s) com sucesso.",
				"Efetuar Outra An�lise de Exce��es e Consumo",
				"exibirFiltrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos&menu=sim",
				"exibirFiltrarOrdemServicoAction.do?menu=sim",
				"Manter Ordem de Servi�o",
				"Voltar",
				"javascript:history.back();");

		return retorno;

	}

}
