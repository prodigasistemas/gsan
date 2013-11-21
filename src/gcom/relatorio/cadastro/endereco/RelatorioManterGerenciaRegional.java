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
package gcom.relatorio.cadastro.endereco;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.localidade.RelatorioManterGerenciaRegionalBean;
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
 * @author not attributable
 * @version 1.0
 */

public class RelatorioManterGerenciaRegional extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterGerenciaRegional(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_LOGRADOURO_MANTER);
	}

	@Deprecated
	public RelatorioManterGerenciaRegional() {
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
		FiltroGerenciaRegional filtroGerenciaRegional = (FiltroGerenciaRegional) getParametro("filtroGerenciaRegional");
		GerenciaRegional gerenciaRegionalParametros = (GerenciaRegional) getParametro("gerenciaRegionalParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
						
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();
		Collection gerenciasRegionais = (Collection) fachada.pesquisar(filtroGerenciaRegional,
				GerenciaRegional.class.getName());

		RelatorioManterGerenciaRegionalBean relatorioBean = null;

		// se a coleção de parâmetros da analise não for vazia
		if (gerenciasRegionais != null && !gerenciasRegionais.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator gerenciaRegionalIterator = gerenciasRegionais.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (gerenciaRegionalIterator.hasNext()) {

				GerenciaRegional gerenciaRegional = (GerenciaRegional) gerenciaRegionalIterator.next();			

				relatorioBean = new RelatorioManterGerenciaRegionalBean(
						// Código
						gerenciaRegional.getId().toString(),

						// Nome
						gerenciaRegional.getNome(),

						// Nome Abreviado
						gerenciaRegional.getNomeAbreviado(),

						// Indicador Uso
						gerenciaRegional.getIndicadorUso() == null? "" : gerenciaRegional.getIndicadorUso().toString());

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

		parametros.put("codigo", gerenciaRegionalParametros.getId() == null ? "" : ""	+ gerenciaRegionalParametros.getId());

		parametros.put("nome", gerenciaRegionalParametros.getNome());

		parametros.put("nomeAbreviado", gerenciaRegionalParametros.getNomeAbreviado());
		
		String cnpj = null;
		if(gerenciaRegionalParametros.getCnpjGerenciaRegional() != null && !gerenciaRegionalParametros.getCnpjGerenciaRegional().equals("")){
			cnpj = Util.formatarCnpj(gerenciaRegionalParametros.getCnpjGerenciaRegional());
		}
		
		parametros.put("cnpj", cnpj);
			
		String indicadorUso = "";

		if (gerenciaRegionalParametros.getIndicadorUso() != null
				&& !gerenciaRegionalParametros.getIndicadorUso().equals("")) {
			if (gerenciaRegionalParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativos";
			} else {
				if (gerenciaRegionalParametros.getIndicadorUso().equals(new Short("2"))) {
					indicadorUso = "Inativos";
				}else{
					indicadorUso = "Todos";
				}
			}
		}

		parametros.put("indicadorUso", indicadorUso);
		
		parametros.put("tipo", "R0016");

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_GERENCIA_REGIONAL_MANTER, parametros,
				ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_GERENCIA_REGIONAL,
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
		int retorno = 1;

//		if (getParametro("logradouros") != null
//				&& getParametro("logradouros") instanceof Collection) {
//			retorno = ((Collection) getParametro("logradouros")).size();
//		}

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterGerenciaRegional", this);
	}

}