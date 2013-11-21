/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.micromedicao;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
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
 * Action utilizado para inserir uma resolução de diretoria no banco
 * 
 * [UC0217] Inserir Resolução de Diretoria Permite inserir uma
 * 
 * @author Rafael Corrêa
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
		
		// Recupera os imóveis selecionados pelo usuário
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
				
				// Verifica se já existe uma RA com esse tipo de especificação para este imóvel, caso exista descarta o imóvel
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
					
					// [SB0006] - Obtém Divisão de Esgoto
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
					
					if(unidadeDestino == null){
						
						fachada.inserirRegistroAtendimento(
								
								// Indicador Atendimento OnLine
								new Short("1"), 
							
								// Data Atendimento / Hora Atendimento
								dataAtendimento, horaAtendimento,
							
								// Tempo Espera Inicial / Final
								null, null, 
							
								// Meio Solicitação / Solicitação Tipo Especificação
								idMeioSolicitacao, solicitacaoTipoEspecificacao.getId(), 
							
								// Data Prevista / Observação
								Util.formatarData(definirDataPrevistaUnidadeDestinoEspecificacaoHelper.getDataPrevista()), null,
							
								// Imóvel / Descrição do Local da Ocorrência / Solicitação Tipo
								imovel.getId(), null, solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId(),
							
								// Coleção de Endereços / Ponto Referência Local Ocorrência
								colecaoEnderecos, null, 
							
								// Bairro Área
								null,
									
								// Localidade		
								imovel.getLocalidade().getId(), 
							
								// Setor Comercial
								imovel.getSetorComercial().getId(), 
									
								// Quadra		
								imovel.getQuadra().getId(),
							
								// Divisão Esgoto / Local Ocorrência
								null, null, 
							
								// Pavimento Rua / Pavimento Calçada
								imovel.getPavimentoRua().getId(), imovel.getPavimentoCalcada().getId(),
							
								// Unidade Atendimento / Usuário Logado
								unidadeAtendimento.getId(), usuarioLogado.getId(),
							
								// Cliente / Ponto Referência Solicitante
								null, null, 
							
								// Nome Solicitante / Novo Solicitante
								null, false,
							
								// Unidade Solicitante / Funcionário
								unidadeAtendimento.getId(), null, 
							
								// Coleção Telefones / Coleção Endereços Solicitante
								null, null, 
							
								// Unidade Destino / Parecer Unidade Destino
								null, null, 
							
								// Serviço Tipo / Número RA Manual / RA Gerado / Observação OS Fiscalização
								solicitacaoTipoEspecificacao.getServicoTipo().getId(), null, null,null,null,ConstantesSistema.NAO, null, 
								null, null, observacao,null, null, null,null,null);
					
					}else{
					
					fachada.inserirRegistroAtendimento(
						
							// Indicador Atendimento OnLine
							new Short("1"), 
						
							// Data Atendimento / Hora Atendimento
							dataAtendimento, horaAtendimento,
						
							// Tempo Espera Inicial / Final
							null, null, 
						
							// Meio Solicitação / Solicitação Tipo Especificação
							idMeioSolicitacao, solicitacaoTipoEspecificacao.getId(), 
						
							// Data Prevista / Observação
							Util.formatarData(definirDataPrevistaUnidadeDestinoEspecificacaoHelper.getDataPrevista()), null,
						
							// Imóvel / Descrição do Local da Ocorrência / Solicitação Tipo
							imovel.getId(), null, solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId(),
						
							// Coleção de Endereços / Ponto Referência Local Ocorrência
							colecaoEnderecos, null, 
						
							// Bairro Área
							null,
								
							// Localidade		
							imovel.getLocalidade().getId(), 
						
							// Setor Comercial
							imovel.getSetorComercial().getId(), 
								
							// Quadra		
							imovel.getQuadra().getId(),
						
							// Divisão Esgoto / Local Ocorrência
							null, null, 
						
							// Pavimento Rua / Pavimento Calçada
							imovel.getPavimentoRua().getId(), imovel.getPavimentoCalcada().getId(),
						
							// Unidade Atendimento / Usuário Logado
							unidadeAtendimento.getId(), usuarioLogado.getId(),
						
							// Cliente / Ponto Referência Solicitante
							null, null, 
						
							// Nome Solicitante / Novo Solicitante
							null, false,
						
							// Unidade Solicitante / Funcionário
							unidadeAtendimento.getId(), null, 
						
							// Coleção Telefones / Coleção Endereços Solicitante
							null, null, 
						
							// Unidade Destino / Parecer Unidade Destino
							unidadeDestino.getId(),	null, 
						
							// Serviço Tipo / Número RA Manual / RA Gerado / Observação OS Fiscalização
							solicitacaoTipoEspecificacao.getServicoTipo().getId(), null, null,null,null,ConstantesSistema.NAO, null, null, null, observacao,
							null,null, null,null,null);
				
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

		// Monta a página de sucesso de acordo com o padrão do sistema.
		montarPaginaSucessoComVoltarJavascript(httpServletRequest, qtde
				+ " Registro(s) Atendimento(s) incluído(s) com sucesso.",
				"Efetuar Outra Análise de Exceções e Consumo",
				"exibirFiltrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos&menu=sim",
				"exibirFiltrarOrdemServicoAction.do?menu=sim",
				"Manter Ordem de Serviço",
				"Voltar",
				"javascript:history.back();");

		return retorno;

	}

}