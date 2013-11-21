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
package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroOperacaoEfetuada;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAlteracao;
import gcom.seguranca.acesso.usuario.UsuarioAlteracao;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.FiltroTabelaLinhaColunaAlteracao;
import gcom.seguranca.transacao.TabelaLinhaColunaAlteracao;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarOperacaoEfetuadaAction   extends GcomAction {
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
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirOperacaoEfetuada");

        ConsultarOperacaoEfetuadaActionForm form = (ConsultarOperacaoEfetuadaActionForm) actionForm;  

        HttpSession sessao = httpServletRequest.getSession(false);
        
        HashMap resumoDados = null;
        
        FiltroOperacaoEfetuada filtroOperacaoEfetuada = new FiltroOperacaoEfetuada();
        filtroOperacaoEfetuada.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoEfetuada.OPERACAO);
        filtroOperacaoEfetuada.adicionarParametro(new ParametroSimples(FiltroOperacaoEfetuada.ID, new Integer(form.getIdOperacaoEfetuada())));

        Collection coll = Fachada.getInstancia().pesquisar(filtroOperacaoEfetuada, OperacaoEfetuada.class.getSimpleName());
        	        	
        if (coll != null && !coll.isEmpty()) { 
        	
        	OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) coll.iterator().next();
        	
        	FiltroOperacao filtroOperacao = new FiltroOperacao();
			filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(
					FiltroOperacao.ARGUMENTO_PESQUISA);
			filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(
					FiltroOperacao.ARGUMENTO_PESQUISA_TABELA);
			filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID,operacaoEfetuada.
					getOperacao().getId()));
			
			Collection collOperacao = Fachada.getInstancia().pesquisar(filtroOperacao,
					Operacao.class.getSimpleName());
			
			Operacao operacao = (Operacao) collOperacao.iterator().next();
			operacaoEfetuada.setOperacao(operacao);
			
			if (operacao.getArgumentoPesquisa() != null) {
				sessao.setAttribute("descricaoArgumento", operacao.getArgumentoPesquisa().getDescricaoColuna());
			} else {
				sessao.setAttribute("descricaoArgumento", "");
			}
        	
        	sessao.setAttribute("operacaoEfetuada", operacaoEfetuada);
        	
            FiltroUsuarioAlteracao filtroUsuarioAlteracao = new FiltroUsuarioAlteracao();
            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.OPERACAO_EFETUADA);
//            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.OPERACAO);
            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_ACAO);
//            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO);
            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_TIPO);
            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_FUNCIONARIO);
            filtroUsuarioAlteracao.adicionarParametro(new ParametroSimples(FiltroUsuarioAlteracao.OPERACAO_EFETUADA_ID, new Integer(form.getIdOperacaoEfetuada())));
            coll = Fachada.getInstancia().pesquisar(filtroUsuarioAlteracao, UsuarioAlteracao.class.getName());
                     
            sessao.setAttribute("usuarioAlteracao", coll);

            FiltroTabelaLinhaColunaAlteracao filtroTabelaLinhaColunaAlteracao = new FiltroTabelaLinhaColunaAlteracao();            
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_COLUNA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_LINHA_ALTERACAO);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO);
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA_ID, new Integer(form.getIdOperacaoEfetuada())));
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO_ID, AlteracaoTipo.ALTERACAO));
            coll = Fachada.getInstancia().pesquisar(filtroTabelaLinhaColunaAlteracao, TabelaLinhaColunaAlteracao.class.getName());
            //sessao.setAttribute("tabelaLinhaColunaAlteracao", coll);
            if (coll != null && !coll.isEmpty()) {
            	resumoDados = consultarResumoInformacoesItemAnalisado(operacaoEfetuada);
            	//sessao.setAttribute("tabelaLinhaColunaAlteracao", coll);
            } else {
            	sessao.removeAttribute("tabelaLinhaColunaAlteracao");
            }

            filtroTabelaLinhaColunaAlteracao = new FiltroTabelaLinhaColunaAlteracao();            
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_COLUNA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_LINHA_ALTERACAO);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO);
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA_ID, new Integer(form.getIdOperacaoEfetuada())));
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO_ID, AlteracaoTipo.EXCLUSAO));
            
            Collection exclusoes = Fachada.getInstancia().pesquisar(filtroTabelaLinhaColunaAlteracao, TabelaLinhaColunaAlteracao.class.getName()); 
            
            if (coll != null && !coll.isEmpty()) {
            	if (resumoDados == null){
            		resumoDados = consultarResumoInformacoesItemAnalisado(operacaoEfetuada);
            	}
            	//sessao.setAttribute("tabelaLinhaAlteracaoExcluida", coll);
            } else {
            	sessao.removeAttribute("tabelaLinhaAlteracaoExcluida");
            }

            filtroTabelaLinhaColunaAlteracao = new FiltroTabelaLinhaColunaAlteracao();
            filtroTabelaLinhaColunaAlteracao.setConsultaSemLimites(true);
            filtroTabelaLinhaColunaAlteracao.setCampoOrderBy("ultimaAlteracao");
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_COLUNA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_LINHA_ALTERACAO);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO);
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA_ID, new Integer(form.getIdOperacaoEfetuada())));
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO_ID, AlteracaoTipo.INCLUSAO));
            
            Collection inclusoes = Fachada.getInstancia().pesquisar(filtroTabelaLinhaColunaAlteracao, TabelaLinhaColunaAlteracao.class.getName()); 
            
            Collection juncaoInclRemov = juntarInclusaoExclusaoMesmoTipo(inclusoes, exclusoes);
//            coll = juntarInclusaoExclusaoMesmoTipo(coll, exclusoes);
            coll.addAll(juncaoInclRemov);
//            coll.addAll(inclusoes); 
//            coll.addAll(exclusoes);
            
            getFachada().ordenarTabelaLinhaColunaAlteracao(coll, operacao.getId());
            
//            Collections.sort((List)coll, new Comparador());
       
            if (coll != null && !coll.isEmpty()) {
            	if (resumoDados == null){
            		resumoDados = consultarResumoInformacoesItemAnalisado(operacaoEfetuada);
            	}
            	sessao.setAttribute("tabelaLinhaAlteracaoIncluidas", coll);
            } else {
            	sessao.removeAttribute("tabelaLinhaAlteracaoIncluidas");
            }
                        
            sessao.setAttribute("resumoDados", resumoDados);
        }
        return retorno;
    }
    
    private HashMap consultarResumoInformacoesItemAnalisado(OperacaoEfetuada operacaoEfetuada){
    	
		HashMap resumo = new HashMap();
		
		String dadosAdicionais = operacaoEfetuada.getDadosAdicionais();		
		if (dadosAdicionais != null){
			
			StringTokenizer stk = new StringTokenizer(dadosAdicionais,"$");
			while (stk.hasMoreElements()) {
				
				String element = (String) stk.nextElement();
				int ind = element.indexOf(":");
				
				if (ind != -1){
					String label = element.substring(0,ind);
					String valor = element.substring(ind + 1, element.length());
					resumo.put(label,valor);
				}
				
			}
			
		}
		
//    	FiltroTabelaLinhaAlteracao filtro = new FiltroTabelaLinhaAlteracao();
//		filtro.adicionarParametro(
//			new ParametroSimples(FiltroTabelaLinhaAlteracao.OPERACAO_EFETUADA_ID, operacaoEfetuada.getId()));
//		filtro.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaAlteracao.INDICADOR_PRINCIPAL, 
//			TabelaLinhaAlteracao.INDICADOR_TABELA_LINHA_ALTERACAO_PRINCIPAL));
//		
//        Collection coll = fachada.pesquisar(filtro, TabelaLinhaAlteracao.class.getName());
//        if (coll != null && !coll.isEmpty()){
//        	TabelaLinhaAlteracao alteracao = (TabelaLinhaAlteracao) Util.retonarObjetoDeColecao(coll);
//    		resumo = fachada.consultarResumoInformacoesOperacaoEfetuada(operacaoEfetuada, 
//    				alteracao.getId1());        	
//        }
        return resumo;
    	
    }
    
    /**
     * No sistema, quando se tem alterações em coleções, o que está sendo feito é exclusão de todos e inclusão dos novos, 
     * então para ocultar tal ação, esse método faz um join das remoções e inclusoes, afim de aparentar ter 
     * sido uma alteração.
     * @param inclusoes
     * @param remocoes
     * @return
     */
    @SuppressWarnings("unchecked")
	private Collection juntarInclusaoExclusaoMesmoTipo(Collection inclusoes, Collection remocoes){
    	HashMap juncoes = new HashMap();
    	ArrayList linhasJuntadas = new ArrayList();
    	// percorrer as linhas de inclusões e identificar as remoções associadas a estas inclusões
    	// a relação entre elas é o id da coluna alterada, e a igualdade entre os seus conteúdos
    	// além de identificar se esta coluna é primary key
    	// caso seja identificada a associação, guarda-se no Map 
    	//		idTabelaLinhaAlteracao de inclusão -> idTabelaLinhaAlteracao da remoção 
    	for (Iterator iter = inclusoes.iterator(); iter.hasNext();) {
			TabelaLinhaColunaAlteracao tlcaIncl = (TabelaLinhaColunaAlteracao) iter.next();
			for (Iterator iterator = remocoes.iterator(); iterator.hasNext();) {
				TabelaLinhaColunaAlteracao tlcaRem = (TabelaLinhaColunaAlteracao) iterator.next();
				if (tlcaIncl.getTabelaColuna().getId().equals(tlcaRem.getTabelaColuna().getId()) && 
					tlcaIncl.getTabelaColuna().getIndicadorPrimaryKey().intValue() == new Integer("1") && 
					tlcaIncl.getConteudoColunaAtual().equalsIgnoreCase(tlcaRem.getConteudoColunaAnterior())){
					juncoes.put(tlcaIncl.getTabelaLinhaAlteracao().getId(), tlcaRem.getTabelaLinhaAlteracao().getId());
				}
			}		
		}
    	
    	// para cada junção montada no laço anterior, vamos setar o valor anterior na linha de inclusão 
    	// com o valor anterior da linha de remoção, fazendo assim parece q a remoção e a inclusão
    	// fosse uma alteração
    	for (Iterator iter = juncoes.keySet().iterator(); iter.hasNext();) {
    		Integer idTLAIncl = (Integer) iter.next();
			Integer idTLARem = (Integer) juncoes.get(idTLAIncl);
			Collection linhasJuntadasAux = new ArrayList();
			boolean houveAlgumaAlteracao = false;
			for (Iterator iter2 = inclusoes.iterator(); iter2.hasNext();) {
				TabelaLinhaColunaAlteracao tlcaIncl = (TabelaLinhaColunaAlteracao) iter2.next();
				if (!tlcaIncl.getTabelaLinhaAlteracao().getId().equals(idTLAIncl)){
					continue;
				}

				for (Iterator iter3 = remocoes.iterator(); iter3.hasNext();) {
					TabelaLinhaColunaAlteracao tlcaRem = (TabelaLinhaColunaAlteracao) iter3.next();
					if (tlcaRem.getTabelaLinhaAlteracao().getId().equals(idTLARem) && 
						tlcaIncl.getTabelaColuna().getId().equals(tlcaRem.getTabelaColuna().getId())){
					
						// caso a linha alteração seja da chake primária, sempre acrescentar 
						if (tlcaIncl.getTabelaColuna().getIndicadorPrimaryKey().intValue() == new Integer("1")
							|| !tlcaIncl.getConteudoColunaAtual().equalsIgnoreCase(tlcaRem.getConteudoColunaAnterior())){
							tlcaIncl.setConteudoColunaAnterior(tlcaRem.getConteudoColunaAnterior());
							
							AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
							alteracaoTipo.setId(AlteracaoTipo.ALTERACAO);
							alteracaoTipo.setDescricao("Alteração");
											
							tlcaIncl.getTabelaLinhaAlteracao().setAlteracaoTipo(alteracaoTipo);
							linhasJuntadasAux.add(tlcaIncl);
						}
						
						// para cada idem juntado, removemos os respectivos da coleção de inclusão e da de remoção						
						iter2.remove();
						iter3.remove();
						
						// como foi efetuada uma remoção e uma inclusão, não tinha como saber se os valores foram modificados
						// ou seja, pode acontecer de os conteúdos permanecerem o mesmo, neste caso não vamos exibir
						// esta flag 'houveAlgumaAlteracao' servirá para identificar se houve algum campo que foi modificado
						// e neste caso o conjunto de linhas (linha de identificação e linhas alteradas) serão 
						// adicionadas na coleção final de alterações.
						if (!tlcaIncl.getConteudoColunaAtual().equalsIgnoreCase(tlcaRem.getConteudoColunaAnterior())){
							houveAlgumaAlteracao = true;
						}						
					}
				}
				
			}
			if (houveAlgumaAlteracao){
				linhasJuntadas.addAll(linhasJuntadasAux);	
			}							
		}
    	// estas adições serão as linhas que efetivamente foram de inclusão, ou seja, não havia exclusão associada a eles
    	// da mesma forma, serao adicionadas as linhas de remoção que não tinha inclusões associadas.
    	linhasJuntadas.addAll(inclusoes);    	
    	linhasJuntadas.addAll(remocoes);
    	
    	//Ordenadando pelo Id da tabela para agrupar os itens atualizados 
//    	Collections.sort(linhasJuntadas, new Comparador());
    	return linhasJuntadas;
    }
    
    // Ordenador pelo Id da tabela para agrupar os itens atualizados
//    class Comparador implements Comparator {
//        public int compare(Object obj1, Object obj2){
//        	TabelaLinhaColunaAlteracao tlca1 = (TabelaLinhaColunaAlteracao) obj1;
//        	TabelaLinhaColunaAlteracao tlca2 = (TabelaLinhaColunaAlteracao) obj2;
//        	if (obj1 instanceof TabelaLinhaColunaAlteracao && obj2 instanceof TabelaLinhaColunaAlteracao){
//        		
//                int i2 = tlca1.getTabelaLinhaAlteracao().getId().intValue();
//                int i1 = tlca2.getTabelaLinhaAlteracao().getId().intValue();
//                int dif = Math.abs(i1) - Math.abs(i2);
//                if (dif == 0){
//                	
//                	i1 = tlca1.getTabelaColuna().getIndicadorPrimaryKey().intValue();
//                    i2 = tlca2.getTabelaColuna().getIndicadorPrimaryKey().intValue();
//                    dif = Math.abs(i1) - Math.abs(i2);
//                    if (dif == 0){
//                    	dif = tlca1.getTabelaColuna().getDescricaoColuna().compareTo(
//                    		tlca2.getTabelaColuna().getDescricaoColuna());                    	
//                    }
//                }
//                return dif;
//        	} else {
//        		return 0;
//        	}
//        }
//    }

}