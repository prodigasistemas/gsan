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
package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesInterfaceGSAN;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

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
public class RelatorioAnormalidadeLeituraPeriodo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioAnormalidadeLeituraPeriodo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANORMALIDADE_LEITURA_PERIODO);
	}

	@Deprecated
	public RelatorioAnormalidadeLeituraPeriodo() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		List<RelatorioAnormalidadeLeituraPeriodoBean> relatorioBeans = executarConsultaRelatoriosBean();
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_PESQUISA_NENHUM_RESULTADO);
		}

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ANORMALIDADE_LEITURA_PERIODO,
				criarParametros() , ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ANORMALIDADE_LEITURA_PERIODO,this.getIdFuncionalidadeIniciada());
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException(ConstantesInterfaceGSAN.ERRO_GSAN_ERRO_GRAVAR_RELATORIO_SISTEMA, e);
		}
		
		return retorno;
	}

	/**
	 * O método cria os parametros necessários a geração do relatorio.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametros() {
		FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro = (FiltrarRelatorioAnormalidadeLeituraPeriodoHelper) getParametro("filtroRelatorio");

		FiltroLeituraAnormalidade filtroConsumoAnormalidade = new FiltroLeituraAnormalidade();				
		filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID,
				filtro.getAnormalidadeLeitura()));
		filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<LeituraAnormalidade> colecaoAnormalidadeleitura = Fachada.getInstancia().pesquisar(filtroConsumoAnormalidade, LeituraAnormalidade.class.getName());

		LeituraAnormalidade anormalidade = colecaoAnormalidadeleitura.iterator().next();

		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().pesquisarParametrosDoSistema().getImagemRelatorio());
		parametros.put("filtroPeriodoLeitura", Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferenciaInicial()) + " - " + Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferenciaFinal()));
		parametros.put("filtroAnormalidade", anormalidade.getId() + " - " + anormalidade.getDescricao());

		return parametros;
	}

	/**
	 * Esse método tem a lógica para realizar a consulta referente ao relatorio
	 * e a partir do resultado obtido criar os beans.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private List<RelatorioAnormalidadeLeituraPeriodoBean> executarConsultaRelatoriosBean() {

		FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro = (FiltrarRelatorioAnormalidadeLeituraPeriodoHelper) getParametro("filtroRelatorio");

		return (List<RelatorioAnormalidadeLeituraPeriodoBean>)Fachada.getInstancia().pesquisarRelatorioAnormalidadeLeituraPeriodo(filtro);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		Collection<Object[]> colecaoDados = Fachada.getInstancia().pesquisarTotalRegistrosRelatorioAnormalidadeLeituraPeriodo(
				(FiltrarRelatorioAnormalidadeLeituraPeriodoHelper) getParametro("filtroRelatorio"));	
		
		if(Util.isVazioOrNulo(colecaoDados)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_PESQUISA_NENHUM_RESULTADO);
		}
		
		return colecaoDados.size();
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnormalidadeLeituraPeriodo", this);

	}

}