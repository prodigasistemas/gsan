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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Fernanda Paiva
 * @created 11 de Julho de 2005
 */
public class RelatorioFaturasAgrupadas extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioFaturasAgrupadas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FATURAS_AGRUPADAS);
	}

	@Deprecated
	public RelatorioFaturasAgrupadas() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param localidades
	 *            Description of the Parameter
	 * @param localidadeParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Fachada fachada = Fachada.getInstancia();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer anoMes = (Integer) getParametro("anoMes");
		Cliente cliente = (Cliente) getParametro("cliente");
		Collection<Integer> idsClientes = (Collection<Integer>) getParametro("idsClientes");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		
		Integer qtdFaturasSelecionadas = fachada
		.pesquisarDadosRelatorioFaturasAgrupadasCount(anoMes, cliente, idsClientes);
		
		if(qtdFaturasSelecionadas == null || qtdFaturasSelecionadas.intValue() == 0){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,"");
		}
		
		Collection colecaoFaturasAgrupadasRelatorio = fachada
		.pesquisarDadosRelatorioFaturasAgrupadas(anoMes, cliente,
				idsClientes);
		
		Iterator iColecaoRelatorioFaturasAgrupadas = colecaoFaturasAgrupadasRelatorio.iterator();
		
		Collection colecaoRelatorioFaturasAgrupadas = new ArrayList();
		
		while (iColecaoRelatorioFaturasAgrupadas.hasNext()){
			
			RelatorioFaturasAgrupadasBean faturaItem = (RelatorioFaturasAgrupadasBean) iColecaoRelatorioFaturasAgrupadas.next();
			
			if (faturaItem.getIndicadorContaHist() == 2){
				
				colecaoRelatorioFaturasAgrupadas.add(faturaItem);
				
			} else if (faturaItem.getIndicadorContaHist() == 1){
				
				fachada.atualizarIndicadorContaHistorico(faturaItem.getIdFaturaItem());
			}
			
		}

//		relatorioBeans.addAll(colecaoFaturasAgrupadasRelatorio);
		relatorioBeans.addAll(colecaoRelatorioFaturasAgrupadas);
		
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		FiltroBairro filtroBairro = new FiltroBairro();
		filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.ID,
				sistemaParametro.getBairro().getId()));

		Collection colecaoBairros = fachada.pesquisar(filtroBairro,
				Bairro.class.getName());

		Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairros);

		String endereco = formatarEndereco(sistemaParametro, bairro);

		String telefone = "(" + bairro.getMunicipio().getDdd() + ") "
				+ sistemaParametro.getNumeroTelefone();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("nomeAbreviadoEmpresa", sistemaParametro
				.getNomeAbreviadoEmpresa());

		parametros.put("nomeEmpresa", sistemaParametro.getNomeEmpresa());

		parametros.put("enderecoEmpresa", endereco);

		parametros.put("cgc", Util.formatarCnpj(sistemaParametro
				.getCnpjEmpresa()));

		parametros.put("inscricaoEstadual", sistemaParametro
				.getInscricaoEstadual());

		parametros.put("telefoneEmpresa", telefone);

		parametros.put("telefoneContato", sistemaParametro
				.getNumero0800Empresa());

		BigDecimal percentualAliquota = fachada.pesquisarPercentualAliquota();

		parametros.put("percentualRetencao", percentualAliquota);

		String indicadorTotalizador = (String) getParametro("indicadorTotalizador");

		parametros.put("indicadorTotalizador", indicadorTotalizador);

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if (sistemaParametro.getCodigoEmpresaFebraban().equals(
				SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)) {

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_FATURAS_AGRUPADAS_COSANPA,
					parametros, ds, tipoFormatoRelatorio);

		} else if (indicadorTotalizador != null
				&& indicadorTotalizador.equalsIgnoreCase("2")) {

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_FATURAS_AGRUPADAS_SINTETICO,
					parametros, ds, tipoFormatoRelatorio);
		} else {

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_FATURAS_AGRUPADAS,
					parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_FATURAS_AGRUPADAS,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	private String formatarEndereco(SistemaParametro sistemaParametro,
			Bairro bairro) {
		String retorno = "";

		Fachada fachada = Fachada.getInstancia();

		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro
				.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		filtroLogradouro
				.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID, sistemaParametro.getLogradouro().getId()));

		Collection colecaoLogradouros = fachada.pesquisar(filtroLogradouro,
				Logradouro.class.getName());

		Logradouro logradouro = (Logradouro) Util
				.retonarObjetoDeColecao(colecaoLogradouros);

		retorno = logradouro.getDescricaoFormatada();

		if (sistemaParametro.getNumeroImovel() != null) {
			retorno = retorno + ", " + sistemaParametro.getNumeroImovel();
		}

		retorno = retorno + " - " + bairro.getNome();

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {

		Fachada fachada = Fachada.getInstancia();

		Integer anoMes = (Integer) getParametro("anoMes");
		Cliente cliente = (Cliente) getParametro("cliente");
		Collection<Integer> idsClientes = (Collection<Integer>) getParametro("idsClientes");

		return fachada.pesquisarDadosRelatorioFaturasAgrupadasCount(anoMes,
				cliente, idsClientes);

	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFaturasAgrupadas", this);
	}

}