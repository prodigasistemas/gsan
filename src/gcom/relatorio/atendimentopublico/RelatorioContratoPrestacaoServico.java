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
package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.bean.ContratoPrestacaoServicoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioContratoPrestacaoServico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioContratoPrestacaoServico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTRATO_PRESTACAO_SERVICO);
	}
	
	@Deprecated
	public RelatorioContratoPrestacaoServico() {
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

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer idImovel = (Integer) getParametro("idImovel");

		Integer idCliente = (Integer) getParametro("idCliente");

		// valor de retorno
		byte[] retorno = null;
		
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioContratoPrestacaoServicoBean relatorioBean = null;
		
		Collection colecaoContratoPrestacaoServicoHelper = fachada.obterDadosContratoPrestacaoServico(idImovel, idCliente);


		// se a coleção de parâmetros da analise não for vazia
		if (colecaoContratoPrestacaoServicoHelper != null
				&& !colecaoContratoPrestacaoServicoHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoContratoPrestacaoServicoHelperIterator = colecaoContratoPrestacaoServicoHelper
					.iterator();
			
			Date dataCorrente = new Date();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoContratoPrestacaoServicoHelperIterator.hasNext()) {

				ContratoPrestacaoServicoHelper contratoPrestacaoServicoHelper = (ContratoPrestacaoServicoHelper) colecaoContratoPrestacaoServicoHelperIterator
						.next();
				
				// Faz as validações dos campos necessários e formata a String
				// para a forma como irá aparecer no relatório
				
				// Nome Cliente
				String nomeCliente = "";
				if (contratoPrestacaoServicoHelper.getCliente().getNome() != null) {
					nomeCliente = contratoPrestacaoServicoHelper.getCliente().getNome(); 
				}
				
				// Nome Localidade
				String nomeLocalidade = "";
				if (contratoPrestacaoServicoHelper.getNomeLocalidade() != null) {
					nomeLocalidade = contratoPrestacaoServicoHelper.getNomeLocalidade(); 
				}
				
				// Nome Responsável
				String nomeResponsavel = "";
				if (contratoPrestacaoServicoHelper.getClienteResponsavel().getNome() != null) {
					nomeResponsavel = contratoPrestacaoServicoHelper.getClienteResponsavel().getNome(); 
				}
				
				// CPF Responsável
				String cpfResponsavel = "";
				if (contratoPrestacaoServicoHelper.getClienteResponsavel().getCpf() != null) {
					cpfResponsavel = contratoPrestacaoServicoHelper.getClienteResponsavel().getCpfFormatado(); 
				}
				
				// RG Responsável
				String rgResponsavel = "";
				if (contratoPrestacaoServicoHelper.getClienteResponsavel().getRg() != null) {
					rgResponsavel = contratoPrestacaoServicoHelper.getClienteResponsavel().getRg(); 
				}
				
				// CPF Cliente
				String cpfCliente = "";
				if (contratoPrestacaoServicoHelper.getCliente().getCpf() != null) {
					cpfCliente = contratoPrestacaoServicoHelper.getCliente().getCpfFormatado(); 
				}
				
				// RG Cliente
				String rgCliente = "";
				if (contratoPrestacaoServicoHelper.getCliente().getRg() != null) {
					rgCliente = contratoPrestacaoServicoHelper.getCliente().getRg(); 
				}
				
				// Consumo Mínimo
				String consumoMinimo = "";
				if (contratoPrestacaoServicoHelper.getConsumoMinimo() != null) {
					consumoMinimo = contratoPrestacaoServicoHelper.getConsumoMinimo().toString(); 
				}
				
				String anoCorrente = "" + Util.getAno(dataCorrente);
				
//				 Pega a Data Atual formatada da seguinte forma: dd de mês(por
				// extenso) de aaaa
				// Ex: 23 de maio de 1985
				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale
						.getDefault());
				String dataCorrenteFormatada = df.format(new Date());
				
				
				// Dados da 1ª página
				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
						
						// Indicador Pessoa Física
						"1",
						
						// Número Página
						"1",
						
						// Número Contrato
						idImovel.toString() + anoCorrente,
						
						// Nome Cliente
						nomeCliente,
						
						// Nome Localidade
						nomeLocalidade,
						
						// Nome Responsável
						nomeResponsavel,
						
						// CPF Responsável
						cpfResponsavel,
						
						// RG Responsável
						rgResponsavel,
						
						// CPF Cliente
						cpfCliente,
						
						// RG Cliente
						rgCliente,
						
						// Endereço Cliente
						contratoPrestacaoServicoHelper.getEnderecoCliente(),
						
						// Id Imóvel
						idImovel.toString(),
						
						// Endereço Imóvel
						contratoPrestacaoServicoHelper.getEnderecoImovel(),
						
						// Categoria
						contratoPrestacaoServicoHelper.getCategoria(),
						
						// Consumo Mínimo
						consumoMinimo,
						
						// Data Corrente
						"",
				
						// Município
						"");
				
				relatorioBeans.add(relatorioBean);
				
				// Dados da 2ª página
				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
						
						// Indicador Pessoa Física
						"1",
						
						// Número Página
						"2",
						
						// Número Contrato
						"",
						
						// Nome Cliente
						"",
						
						// Nome Unidade Negócio
						nomeLocalidade,
						
						// Nome Responsável
						"",
						
						// CPF Responsável
						"",
						
						// RG Responsável
						"",
						
						// CPF Cliente
						"",
						
						// RG Cliente
						"",
						
						// Endereço Cliente
						"",
						
						// Id Imóvel
						"",
						
						// Endereço Imóvel
						"",
						
						// Categoria
						"",
						
						// Consumo Mínimo
						"",
						
						// Data Corrente
						dataCorrenteFormatada,
						
						// Município
						contratoPrestacaoServicoHelper.getNomeMunicipio());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
				
//				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
//						
//						// Indicador Pessoa Física
//						"1",
//						
//						// Número Página
//						"2");

				// adiciona o bean a coleção
//				relatorioBeans.add(relatorioBean);
				
//				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
//						
//						// Indicador Pessoa Física
//						"2",
//						
//						// Número Página
//						"1");
//
//				// adiciona o bean a coleção
//				relatorioBeans.add(relatorioBean);
//				
//				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
//						
//						// Indicador Pessoa Física
//						"2",
//						
//						// Número Página
//						"2");
//
//				// adiciona o bean a coleção
//				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTRATO_PRESTACAO_SERVICO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_RESOLUCAO_DIRETORIA,
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
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterResolucaoDiretoria", this);
	}
}