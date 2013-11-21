
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
package gcom.relatorio.cobranca.spcserasa;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativadorResultadoSimulacao;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane
 * @created 16 de maio de 2008
 * @version 1.0
 */

public class RelatorioNegativadorResultadoSimulacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterNegativadorExclusaoMotivo object
	 */
	public RelatorioNegativadorResultadoSimulacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_NEGATIVADOR_EXCLUSAO_MOTIVO);
	}

	@Deprecated
	public RelatorioNegativadorResultadoSimulacao() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param NegativadorExclusaoMotivo Parametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os parâmetros que serão utilizados no relatório
		
		NegativadorResultadoSimulacao parametrosNegativadorResultadoSimulacao = (NegativadorResultadoSimulacao) getParametro("parametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioNegativadorResultadoSimulacaoBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necessários para
		// a impressão do relatório	
	//	filtro.adicionarCaminhoParaCarregamentoEntidade("negativacaoComando");
	//	filtro.setConsultaSemLimites(true);

		// Nova consulta para trazer objeto completo
//		Collection<NegativadorResultadoSimulacao> colecaoNovos = fachada.pesquisar(filtro, NegativadorResultadoSimulacao.class
//				.getName());

		Collection<NegativadorResultadoSimulacao> colecaoNovos = fachada.pesquisarNegativadorResultadoSimulacao(parametrosNegativadorResultadoSimulacao.getId());
		
		if (colecaoNovos != null && !colecaoNovos.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			for (NegativadorResultadoSimulacao negativadorResultadoSimulacao : colecaoNovos) {
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				String idComando = "";				
				if (negativadorResultadoSimulacao.getNegativacaoComando().getId() != null ) {
					idComando = negativadorResultadoSimulacao.getNegativacaoComando().getId().toString();
				}
				
										
				String descricaoTitulo = "";
				NegativacaoCriterio nCriterio = fachada.pesquisarNegativacaoCriterio(new Integer(idComando));
				if(nCriterio != null){
					descricaoTitulo = nCriterio.getDescricaoTitulo();
				}
			
				
				
				// Descrição
				String sequencial = "";				
				if (negativadorResultadoSimulacao.getId() != null ) {
					sequencial = negativadorResultadoSimulacao.getId().toString();
				}
				
				// idImovel
				String idImovel = "";				
				if (negativadorResultadoSimulacao.getImovel().getId() != null) {
					idImovel = negativadorResultadoSimulacao.getImovel().getId().toString();
				}			
				
				//numero cpf
				String numeroCpf = "";				
				if (negativadorResultadoSimulacao.getNumeroCpf() != null) {					
					numeroCpf = Util.formatarCpf(negativadorResultadoSimulacao.getNumeroCpf());
				}
				
				//numero cnpj
				String numeroCnpj = "";				
				if (negativadorResultadoSimulacao.getNumeroCnpj() != null) {					
					numeroCnpj = Util.formatarCnpj(negativadorResultadoSimulacao.getNumeroCnpj());
				}
				
				

				// valorDebito
				BigDecimal valorDebito = null;				
				if (negativadorResultadoSimulacao.getValorDebito() != null) {					
					valorDebito = negativadorResultadoSimulacao.getValorDebito();
				}

				// Inicializa o construtor constituído dos campos
				// necessários para a impressão do relatorio
				relatorioBean = new RelatorioNegativadorResultadoSimulacaoBean(
						
						// ID
						idComando,
						
						descricaoTitulo,

						// Descrição
						sequencial,

						//idImovel
						idImovel,

						//valorDebito
						valorDebito,					
					
						// numero Cpf
						numeroCpf,
						
						// numero numeroCnpj
						numeroCnpj);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("idComando", parametrosNegativadorResultadoSimulacao.getId() == -1 ? ""
				: "" + parametrosNegativadorResultadoSimulacao.getId());	


		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_NEGATIVADOR_RESULTADO_SIMULACAO, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RESULTADO_SIMULACAO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroNegativadorExclusaoMotivo) getParametro("filtroNegativadorResultadoSimulacao"),
//				NegativadorResultadoSimulacao.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioNegativadorResultadoSimulacao", this);
	}

}
