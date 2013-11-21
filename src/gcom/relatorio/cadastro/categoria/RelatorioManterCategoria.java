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
package gcom.relatorio.cadastro.categoria;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de categoria manter de água
 * 
 * @author Rafael Corrêa / Rômulo Aurélio
 * @created 18 de Julho de 2006
 */
public class RelatorioManterCategoria extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterCategoria(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CATEGORIA_MANTER);
	}
	
	@Deprecated
	public RelatorioManterCategoria() {
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

		FiltroCategoria filtroCategoria = (FiltroCategoria) getParametro("filtroCategoria");
		Categoria categoriaParametros = (Categoria) getParametro("categoriaParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterCategoriaBean relatorioBean = null;

		filtroCategoria.limparCamposOrderBy();
		filtroCategoria.setCampoOrderBy(FiltroCategoria.CODIGO);

		filtroCategoria.setConsultaSemLimites(true);

		Collection colecaoCategorias = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoCategoriasIterator = colecaoCategorias.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoCategoriasIterator.hasNext()) {

				Categoria categoria = (Categoria) colecaoCategoriasIterator
						.next();
				
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório
				
				String consumoMinimo = "";
				
				if (categoria.getConsumoMinimo() != null) {
					consumoMinimo = categoria.getConsumoMinimo().toString();
				}
				
				String consumoAlto = "";
				
				if (categoria.getConsumoAlto() != null) {
					consumoAlto = categoria.getConsumoAlto().toString();
				}
				
				String consumoBaixo = "";
				
				if (categoria.getMediaBaixoConsumo() != null) {
					consumoBaixo = categoria.getMediaBaixoConsumo().toString();
				}
				
				String consumoEstouro = "";
				
				if (categoria.getConsumoEstouro() != null) {
					consumoEstouro = categoria.getConsumoEstouro().toString();
				}
				
				String fatorMultiplicativoAlto = "";
				
				if (categoria.getVezesMediaAltoConsumo() != null) {
					fatorMultiplicativoAlto = Util.formatarMoedaReal(categoria
							.getVezesMediaAltoConsumo());
				}
				
				String fatorMultiplicativoEstouro = "";
				
				if (categoria.getVezesMediaEstouro() != null) {
					fatorMultiplicativoEstouro = Util.formatarMoedaReal(categoria
							.getVezesMediaEstouro());
				}
				
				String percentualBaixoConsumo = "";
				
				if (categoria.getPorcentagemMediaBaixoConsumo() != null) {
					fatorMultiplicativoEstouro = Util.formatarMoedaReal(categoria
							.getPorcentagemMediaBaixoConsumo());
				}

				relatorioBean = new RelatorioManterCategoriaBean(

						// Código
						categoria.getId().toString(),

						// Descrição
						categoria.getDescricao(),

						// Descrição Abreviada
						categoria.getDescricaoAbreviada(),

						// Consumo Mínimo
						consumoMinimo,

						// Consumo Referência Alto
						consumoAlto,

						// Consumo Referência Baixo
						consumoBaixo,

						// Consumo Referência Estouro
						consumoEstouro,

						// Fator Multiplicativo Alto
						fatorMultiplicativoAlto,

						// Fator Multiplicativo Estouro
						fatorMultiplicativoEstouro,

						// Percentual Baixo Consumo
						percentualBaixoConsumo,

						// Indicador de Uso
						categoria.getIndicadorUso().toString());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		// Imagem do Relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// Código
		if (categoriaParametros.getId() != null) {
			parametros.put("codigo", categoriaParametros.getId());
		} else {
			parametros.put("codigo", "");
		}

		// Descrição
		parametros.put("descricao", categoriaParametros.getDescricao());

		// Indicador Uso
		String indicadorUso = "";

		if (categoriaParametros.getIndicadorUso() != null
				&& !categoriaParametros.getIndicadorUso().equals("")) {
			if (categoriaParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (categoriaParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CATEGORIA_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_CATEGORIA,
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

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroCategoria) getParametro("filtroCategoria"),
				Categoria.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterCategoria", this);
	}
}