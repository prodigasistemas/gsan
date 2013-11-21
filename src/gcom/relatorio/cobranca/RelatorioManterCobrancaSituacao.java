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
package gcom.relatorio.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterCobrancaSituacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterCobrancaSituacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_SITUACAO_COBRANCA_MANTER);
	}
	
	@Deprecated
	public RelatorioManterCobrancaSituacao() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroCobrancaSituacao filtroCobrancaSituacao = (FiltroCobrancaSituacao) getParametro("filtroCobrancaSituacao");
		CobrancaSituacao cobrancaSituacaoParametros = (CobrancaSituacao) getParametro("cobrancaSituacaoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		filtroCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterCobrancaSituacaoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoCobrancaSituacao = fachada.pesquisar(filtroCobrancaSituacao,
				CobrancaSituacao.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoCobrancaSituacao != null && !colecaoCobrancaSituacao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator cobrancaSituacaoIterator = colecaoCobrancaSituacao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (cobrancaSituacaoIterator.hasNext()) {

				CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) cobrancaSituacaoIterator.next();

				
				String indicadorUso = "";
				
				if(cobrancaSituacao.getIndicadorUso().equals(ConstantesSistema.SIM)){
					indicadorUso = "ATIVO";
				} else {
					indicadorUso = "INATIVO";
				}
				String indicadorExigenciaAdvogado = "";
				
				if (cobrancaSituacao.getIndicadorExigenciaAdvogado() != null){
					if(cobrancaSituacao.getIndicadorExigenciaAdvogado().equals(ConstantesSistema.SIM)){
					indicadorExigenciaAdvogado = "SIM";
				} else {
					indicadorExigenciaAdvogado = "NÃO";
				}
				
				}
				String indicadorBloqueioParcelamento = "";
				if (cobrancaSituacao.getIndicadorExigenciaAdvogado() != null){
				if(cobrancaSituacao.getIndicadorBloqueioParcelamento().equals(ConstantesSistema.SIM)){
					indicadorBloqueioParcelamento = "SIM";
				} else {
					indicadorBloqueioParcelamento = "NÃO";
				}
				}
				
				String contaMotivoRevisao = "";
				
				if (cobrancaSituacao.getContaMotivoRevisao() != null) {
					contaMotivoRevisao = cobrancaSituacao.getContaMotivoRevisao().getDescricaoMotivoRevisaoConta();
				}
				
				
				relatorioBean = new RelatorioManterCobrancaSituacaoBean(
						// CODIGO
						cobrancaSituacao.getId().toString(), 
						
						// Descrição
						cobrancaSituacao.getDescricao(), 
						
						//Motivo de revisao da conta
						contaMotivoRevisao,
						
						//motivo da situacao especial de faturamento
						indicadorExigenciaAdvogado,
						
						//Bloqueio de Parcelamento
						indicadorBloqueioParcelamento,
						
						//Indicador de Uso
						indicadorUso);
						
						
						
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("id",
				cobrancaSituacaoParametros.getId() == null ? "" : ""
						+ cobrancaSituacaoParametros.getId());
		
		
		if (cobrancaSituacaoParametros.getDescricao() != null){
			parametros.put("descricao", cobrancaSituacaoParametros.getDescricao());
		} else {
			parametros.put("descricao", "");
		}
		
		if (cobrancaSituacaoParametros.getContaMotivoRevisao() != null) {
			parametros.put("contaMotivoRevisao", cobrancaSituacaoParametros.getContaMotivoRevisao().getDescricaoMotivoRevisaoConta());
		} else {
			parametros.put("contaMotivoRevisao", "");	
		}
		
		
		String indicadorExigenciaAdvogado = "";

		if (cobrancaSituacaoParametros.getIndicadorExigenciaAdvogado() != null && !cobrancaSituacaoParametros.getIndicadorExigenciaAdvogado().equals("")) {
			if (cobrancaSituacaoParametros.getIndicadorExigenciaAdvogado().equals(new Short("1"))) {
				indicadorExigenciaAdvogado = "Sim";
			} else if (cobrancaSituacaoParametros.getIndicadorExigenciaAdvogado().equals(new Short("2"))) {
				indicadorExigenciaAdvogado = "Não";
			}
		}

		parametros.put("indicadorExigenciaAdvogado", indicadorExigenciaAdvogado);
		
		String indicadorBloqueioParcelamento = "";

		if (cobrancaSituacaoParametros.getIndicadorBloqueioParcelamento() != null && !cobrancaSituacaoParametros.getIndicadorBloqueioParcelamento().equals("")) {
			if (cobrancaSituacaoParametros.getIndicadorBloqueioParcelamento().equals(new Short("1"))) {
				indicadorBloqueioParcelamento = "Sim";
			} else if (cobrancaSituacaoParametros.getIndicadorBloqueioParcelamento().equals(new Short("2"))) {
				indicadorBloqueioParcelamento = "Não";
			}
		}

		parametros.put("indicadorBloqueioParcelamento", indicadorBloqueioParcelamento);
		
		String indicadorUso = "";

		if (cobrancaSituacaoParametros.getIndicadorUso() != null && !cobrancaSituacaoParametros.getIndicadorUso().equals("")) {
			if (cobrancaSituacaoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (cobrancaSituacaoParametros.getIndicadorUso().equals(new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_SITUACAO_COBRANCA_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLocalidade) getParametro("filtroLocalidade"),
//				Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterCobrancaSituacao", this);
	}

}