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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */

public class RelatorioAnaliticoFaturamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAnaliticoFaturamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALITICO_FATURAMENTO);
	}
	
	@Deprecated
	public RelatorioAnaliticoFaturamento() {
		super(null, "");
	}
	
	private Collection<RelatorioAnaliticoFaturamentoBean> inicializarBeanRelatorio(
			Collection colecaoAnaliticoRelatorio) {
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<RelatorioAnaliticoFaturamentoBean> retorno = new ArrayList<RelatorioAnaliticoFaturamentoBean>();

		BigDecimal valorTotalGeral= BigDecimal.ZERO;
		BigDecimal valorTotal = BigDecimal.ZERO;
		BigDecimal valorAgua = BigDecimal.ZERO;
		BigDecimal valorEsgoto = BigDecimal.ZERO;
		BigDecimal valorDebito = BigDecimal.ZERO;
		BigDecimal valorCredito = BigDecimal.ZERO;
		Iterator iter = colecaoAnaliticoRelatorio.iterator();
		
		while (iter.hasNext()) {
			
			RelatorioAnaliticoFaturamentoHelper rel = (RelatorioAnaliticoFaturamentoHelper) iter.next();

			valorAgua = BigDecimal.ZERO;
			valorEsgoto = BigDecimal.ZERO;
			valorDebito = BigDecimal.ZERO;
			valorCredito = BigDecimal.ZERO;
			
			valorAgua = rel.getValorAgua();
			valorEsgoto = rel.getValorEsgoto();
			valorDebito = rel.getDebitos();
			valorCredito = rel.getValorCreditos();
			
			valorTotal = BigDecimal.ZERO;
			valorTotal = valorTotal.add(valorAgua);
			valorTotal = valorTotal.add(valorEsgoto);
			valorTotal = valorTotal.add(valorDebito);
			valorTotal = valorTotal.subtract(valorCredito);
			
			valorTotalGeral = valorTotalGeral.add(valorTotal);
			
			
			String codigoBarra = fachada.obterRepresentacaoNumericaCodigoBarra(3,
						valorTotal,rel.getIdLocalidade(),rel.getIdImovel(),
						rel.getMesAnoFaturamento(),new Integer(rel.getDigitoVerificador()), null,
						null,null,null,null,null,null);
			
			
			RelatorioAnaliticoFaturamentoBean relatorioAnaliticoFaturamentoBean = 
					new RelatorioAnaliticoFaturamentoBean(rel.getIdImovel()+ "",
							rel.getConsumoAgua() != null ? new BigDecimal(rel.getConsumoAgua()) : BigDecimal.ZERO, 
							rel.getConsumoRateioAgua() != null ? new BigDecimal(rel.getConsumoRateioAgua()) : BigDecimal.ZERO, 
							rel.getValorAgua() != null ? rel.getValorAgua() : BigDecimal.ZERO, 
							rel.getConsumoEsgoto() != null ? new BigDecimal(rel.getConsumoEsgoto())  : BigDecimal.ZERO, 
							rel.getConsumoRateioEsgoto() != null ? new BigDecimal(rel.getConsumoRateioEsgoto()) : BigDecimal.ZERO, 
							rel.getValorEsgoto() != null ? rel.getValorEsgoto() : BigDecimal.ZERO,
							rel.getDebitos() != null ? rel.getDebitos() : BigDecimal.ZERO, 
							rel.getValorCreditos() != null ? rel.getValorCreditos() : BigDecimal.ZERO, 
							valorTotal != null ? valorTotal  : BigDecimal.ZERO,
							valorTotalGeral != null ? valorTotalGeral + "" : "0", 
							rel.getCodigoSetorComercial() != null 
							? Util.adicionarZerosEsquedaNumero(3,rel.getCodigoSetorComercial() + "") : "0",
							rel.getInscricao() != null ? rel.getInscricao() + "" : "0",
							rel.getIdLocalidade() != null 
							? Util.adicionarZerosEsquedaNumero(3,rel.getIdLocalidade() + "") : "0",
							Util.formatarCodigoBarra(codigoBarra), rel.getIdDescricaoLocalidade(), rel.getIdDescricaoUnidadeNegocio(),
							rel.getNomeCliente());
				
			retorno.add(relatorioAnaliticoFaturamentoBean);
			
		}

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		String mesAno = (String) getParametro("mesAno");
		String idGrupoFaturamento = (String) getParametro("idGrupoFaturamento");
		String vencimento = (String) getParametro("vencimento");
		Collection colecaoAnaliticoFaturamento = (Collection) getParametro("colecaoAnaliticoFaturamento");
				
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno",mesAno);
		parametros.put("vencimento", vencimento);
		parametros.put("idGrupoFaturamento",idGrupoFaturamento);
		parametros.put("tipoFormatoRelatorio", "R0593");
		
		Collection dadosRelatorio = colecaoAnaliticoFaturamento;

		Collection<RelatorioAnaliticoFaturamentoBean> colecaoBean = this
						.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ANALITICO_FATURAMENTO, 
				parametros, ds,	tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.ANALITICO_FATURAMENTO,
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
//		Integer mesAnoInteger = (Integer) getParametro("mesAnoInteger");
//		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
//		Collection idEsferaPoder = (Collection) getParametro("colecaoIdEsferaPoder");
		
		
		return 1;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnaliticoFaturamento", this);
	}
	
}
