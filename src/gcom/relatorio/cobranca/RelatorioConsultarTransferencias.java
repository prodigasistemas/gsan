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
import gcom.cobranca.bean.ConsultarTransferenciasDebitoHelper;
import gcom.cobranca.bean.TransferenciasDebitoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioConsultarTransferencias extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioConsultarTransferencias(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_TRANSFERENCIAS_CONSULTAR);
	}
	
	@Deprecated
	public RelatorioConsultarTransferencias() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {


		Collection<TransferenciasDebitoHelper> colecaoContasTransferidas = (Collection<TransferenciasDebitoHelper>) getParametro("colecaoContasTransferidas");
		Collection<TransferenciasDebitoHelper> colecaoDebitosACobrarTransferidos = (Collection<TransferenciasDebitoHelper>) getParametro("colecaoDebitosACobrarTransferidos");
		Collection<TransferenciasDebitoHelper> colecaoCreditosARealizarTransferidos = (Collection<TransferenciasDebitoHelper>) getParametro("colecaoCreditosARealizarTransferidos");
		Collection<TransferenciasDebitoHelper> colecaoGuiasPagamentoTransferidas = (Collection<TransferenciasDebitoHelper>) getParametro("colecaoGuiasPagamentoTransferidas");
		ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper = (ConsultarTransferenciasDebitoHelper) getParametro("consultarTransferenciasDebitoHelper");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		if (colecaoContasTransferidas != null
				&& !colecaoContasTransferidas.isEmpty()) {
			adicionarContas(colecaoContasTransferidas, relatorioBeans);
		}
		
		if (colecaoDebitosACobrarTransferidos != null
				&& !colecaoDebitosACobrarTransferidos.isEmpty()) {
			adicionarDebitosACobrar(colecaoDebitosACobrarTransferidos, relatorioBeans);
		}
		
		if (colecaoCreditosARealizarTransferidos != null
				&& !colecaoCreditosARealizarTransferidos.isEmpty()) {
			adicionarCreditosARealizar(colecaoCreditosARealizarTransferidos, relatorioBeans);
		}
		
		if (colecaoGuiasPagamentoTransferidas != null
				&& !colecaoGuiasPagamentoTransferidas.isEmpty()) {
			adicionarGuiasPagamento(colecaoGuiasPagamentoTransferidas, relatorioBeans);
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if (consultarTransferenciasDebitoHelper.getIdImovelOrigem() != null) {
			parametros.put("idImovelOrigem", consultarTransferenciasDebitoHelper.getIdImovelOrigem().toString());
		} else {
			parametros.put("idImovelOrigem", "");
		}
		
		if (consultarTransferenciasDebitoHelper.getIdImovelDestino() != null) {
			parametros.put("idImovelDestino", consultarTransferenciasDebitoHelper.getIdImovelDestino().toString());
		} else {
			parametros.put("idImovelDestino", "");
		}
		
		if (consultarTransferenciasDebitoHelper.getDataInicial() != null) {
			parametros.put("periodoTransferencia", Util.formatarData(consultarTransferenciasDebitoHelper.getDataInicial()) + " a " + Util.formatarData(consultarTransferenciasDebitoHelper.getDataInicial()));
		} else {
			parametros.put("periodoTransferencia", "");
		}

		if (consultarTransferenciasDebitoHelper.getIdUsuario() != null) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, consultarTransferenciasDebitoHelper.getIdUsuario()));
			
			Collection colecaoUsuarios = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarios);
			
			parametros.put("usuario", usuario.getNomeUsuario());
		} else {
			parametros.put("usuario", "");
		}
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_TRANSFERENCIAS_CONSULTAR,
				parametros, ds, tipoFormatoRelatorio);
		
		// retorna o relatório gerado
		return retorno;
	}

	private void adicionarContas(Collection<TransferenciasDebitoHelper> colecaoContasTransferidas, List relatorioBeans) {
		RelatorioConsultarTransferenciasBean relatorioBean;
		for (TransferenciasDebitoHelper transferenciasDebitoHelper : colecaoContasTransferidas) {
			
			// Faz as validações dos campos necessáriose e formata a String
			// para a forma como irá aparecer no relatório
			
			// Referência da Conta
			String mesAnoConta = "";
			
			if (transferenciasDebitoHelper.getConta() != null && transferenciasDebitoHelper.getConta().getReferencia() > 0) {
				mesAnoConta = Util.formatarAnoMesParaMesAno(transferenciasDebitoHelper.getConta().getReferencia());
			}
			
			// Data Transferência
			String dataTransferencia = "";
			
			if (transferenciasDebitoHelper.getDataTransferencia() != null) {
				dataTransferencia = Util.formatarData(transferenciasDebitoHelper.getDataTransferencia());
			}
			
			// Usuário
			String nomeUsuario = "";
			
			if (transferenciasDebitoHelper.getUsuario() != null) {
				nomeUsuario = transferenciasDebitoHelper.getUsuario().getNomeUsuario();
			}
			
			relatorioBean = new RelatorioConsultarTransferenciasBean(
					
					// Imóvel Origem
					transferenciasDebitoHelper.getImovelOrigem().getId().toString(),
					
					// Imóvel Destino
					transferenciasDebitoHelper.getImovelDestino().getId().toString(),
					
					// Data Transferência
					dataTransferencia,
					
					// Usuário
					nomeUsuario,
					
					// Id da Conta
					transferenciasDebitoHelper.getConta().getId().toString(),
					
					// Mês/Ano da Conta
					mesAnoConta,
					
					// Id do Débito a Cobrar
					null,
					
					// Id da Guia de Pagamento
					null,
					
					// Tipo do Débito
					null,
					
					// Id do Crédito a Realizar
					null,
					
					// Tipo do Crédito
					null);

			// adiciona o bean a coleção
			relatorioBeans.add(relatorioBean);
		}
	}
	
	private void adicionarDebitosACobrar(Collection<TransferenciasDebitoHelper> colecaoDebitosACobrarTransferidos, List relatorioBeans) {
		RelatorioConsultarTransferenciasBean relatorioBean;
		for (TransferenciasDebitoHelper transferenciasDebitoHelper : colecaoDebitosACobrarTransferidos) {
			
			// Faz as validações dos campos necessáriose e formata a String
			// para a forma como irá aparecer no relatório
			
			// Tipo do Débito
			String tipoDebito = "";
			
			if (transferenciasDebitoHelper.getDebitoACobrar() != null && transferenciasDebitoHelper.getDebitoACobrar().getDebitoTipo() != null) {
				tipoDebito = transferenciasDebitoHelper.getDebitoACobrar().getDebitoTipo().getDescricao();
			}
			
			// Data Transferência
			String dataTransferencia = "";
			
			if (transferenciasDebitoHelper.getDataTransferencia() != null) {
				dataTransferencia = Util.formatarData(transferenciasDebitoHelper.getDataTransferencia());
			}
			
			// Usuário
			String nomeUsuario = "";
			
			if (transferenciasDebitoHelper.getUsuario() != null) {
				nomeUsuario = transferenciasDebitoHelper.getUsuario().getNomeUsuario();
			}
			
			relatorioBean = new RelatorioConsultarTransferenciasBean(
					
					// Imóvel Origem
					transferenciasDebitoHelper.getImovelOrigem().getId().toString(),
					
					// Imóvel Destino
					transferenciasDebitoHelper.getImovelDestino().getId().toString(),
					
					// Data Transferência
					dataTransferencia,
					
					// Usuário
					nomeUsuario,
					
					// Id da Conta
					null,
					
					// Mês/Ano da Conta
					null,
					
					// Id do Débito a Cobrar
					transferenciasDebitoHelper.getDebitoACobrar().getId().toString(),
					
					// Id da Guia de Pagamento
					null,
					
					// Tipo do Débito
					tipoDebito,
					
					// Id do Crédito a Realizar
					null,
					
					// Tipo do Crédito
					null);

			// adiciona o bean a coleção
			relatorioBeans.add(relatorioBean);
		}
	}
	
	private void adicionarCreditosARealizar(Collection<TransferenciasDebitoHelper> colecaoCreditosARealizarTransferidos, List relatorioBeans) {
		RelatorioConsultarTransferenciasBean relatorioBean;
		for (TransferenciasDebitoHelper transferenciasDebitoHelper : colecaoCreditosARealizarTransferidos) {
			
			// Faz as validações dos campos necessáriose e formata a String
			// para a forma como irá aparecer no relatório
			
			// Tipo do Crédito
			String tipoCredito = "";
			
			if (transferenciasDebitoHelper.getCreditoARealizar() != null && transferenciasDebitoHelper.getCreditoARealizar().getCreditoTipo() != null) {
				tipoCredito = transferenciasDebitoHelper.getCreditoARealizar().getCreditoTipo().getDescricao();
			}
			
			// Data Transferência
			String dataTransferencia = "";
			
			if (transferenciasDebitoHelper.getDataTransferencia() != null) {
				dataTransferencia = Util.formatarData(transferenciasDebitoHelper.getDataTransferencia());
			}
			
			// Usuário
			String nomeUsuario = "";
			
			if (transferenciasDebitoHelper.getUsuario() != null) {
				nomeUsuario = transferenciasDebitoHelper.getUsuario().getNomeUsuario();
			}
			
			relatorioBean = new RelatorioConsultarTransferenciasBean(
					
					// Imóvel Origem
					transferenciasDebitoHelper.getImovelOrigem().getId().toString(),
					
					// Imóvel Destino
					transferenciasDebitoHelper.getImovelDestino().getId().toString(),
					
					// Data Transferência
					dataTransferencia,
					
					// Usuário
					nomeUsuario,
					
					// Id da Conta
					null,
					
					// Mês/Ano da Conta
					null,
					
					// Id do Débito a Cobrar
					null,
					
					// Id da Guia de Pagamento
					null,
					
					// Tipo do Débito
					null,
					
					// Id do Crédito a Realizar
					transferenciasDebitoHelper.getCreditoARealizar().getId().toString(),
					
					// Tipo do Crédito
					tipoCredito);

			// adiciona o bean a coleção
			relatorioBeans.add(relatorioBean);
		}
	}
	
	private void adicionarGuiasPagamento(Collection<TransferenciasDebitoHelper> colecaoGuiasPagamentoTransferidas, List relatorioBeans) {
		RelatorioConsultarTransferenciasBean relatorioBean;
		for (TransferenciasDebitoHelper transferenciasDebitoHelper : colecaoGuiasPagamentoTransferidas) {
			
			// Faz as validações dos campos necessáriose e formata a String
			// para a forma como irá aparecer no relatório
			
			// Tipo do Débito
			String tipoDebito = "";
			
			if (transferenciasDebitoHelper.getGuiaPagamento() != null && transferenciasDebitoHelper.getGuiaPagamento().getDebitoTipo() != null) {
				tipoDebito = transferenciasDebitoHelper.getGuiaPagamento().getDebitoTipo().getDescricao();
			}
			
			// Data Transferência
			String dataTransferencia = "";
			
			if (transferenciasDebitoHelper.getDataTransferencia() != null) {
				dataTransferencia = Util.formatarData(transferenciasDebitoHelper.getDataTransferencia());
			}
			
			// Usuário
			String nomeUsuario = "";
			
			if (transferenciasDebitoHelper.getUsuario() != null) {
				nomeUsuario = transferenciasDebitoHelper.getUsuario().getNomeUsuario();
			}
			
			relatorioBean = new RelatorioConsultarTransferenciasBean(
					
					// Imóvel Origem
					transferenciasDebitoHelper.getImovelOrigem().getId().toString(),
					
					// Imóvel Destino
					transferenciasDebitoHelper.getImovelDestino().getId().toString(),
					
					// Data Transferência
					dataTransferencia,
					
					// Usuário
					nomeUsuario,
					
					// Id da Conta
					null,
					
					// Mês/Ano da Conta
					null,
					
					// Id do Débito a Cobrar
					null,
					
					// Id da Guia de Pagamento
					transferenciasDebitoHelper.getGuiaPagamento().getId().toString(),
					
					// Tipo do Débito
					tipoDebito,
					
					// Id do Crédito a Realizar
					null,
					
					// Tipo do Crédito
					null);

			// adiciona o bean a coleção
			relatorioBeans.add(relatorioBean);
		}
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarTransferencias", this);
	}
}