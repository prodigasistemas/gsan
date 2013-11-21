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

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

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
 * @author Sávio Luiz
 * @version 1.0
 */

public class RelatorioRegistrarLeiturasAnormalidades extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioRegistrarLeiturasAnormalidades(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_REGISTRAR_LEITURAS_ANORMALIDADES);
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

	public Object executar() throws TarefaException {

		// public byte[] gerarRelatorioRegistrarLeiturasAnormalidades(
		// Collection<MedicaoHistorico> colecaoMedicaoHistoricoRelatorio,
		// String idFaturamento, String anoMesReferencia)
		// throws RelatorioVazioException {

		Collection colecaoMedicaoHistoricoRelatorio = (Collection) getParametro("colecaoMedicaoHistoricoRelatorio");
		Integer idFaturamento = (Integer) getParametro("idFaturamento");
		String anoMesReferencia = (String) getParametro("anoMesLeitura");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String nomeArquivo = (String) getParametro("nomeArquivo");

		Fachada fachada = Fachada.getInstancia();
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioRegistrarLeiturasAnormalidadesBean relatorioBean = null;

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoMedicaoHistoricoRelatorio != null
				&& !colecaoMedicaoHistoricoRelatorio.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator medicaoHistoricoRelatorioIterator = colecaoMedicaoHistoricoRelatorio
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (medicaoHistoricoRelatorioIterator.hasNext()) {

				MedicaoHistorico medicaoHistrico = (MedicaoHistorico) medicaoHistoricoRelatorioIterator
						.next();

				String matriculaFuncionario = ""
						+ medicaoHistrico.getFuncionario().getId();
				String matriculaImovel = ""
						+ medicaoHistrico.getImovel().getId();
				String inscricaoImovel = ""
						+ medicaoHistrico.getImovel().getInscricaoFormatada();
				String tipoMedicao = ""
						+ medicaoHistrico.getMedicaoTipo().getId();
				String dataLeitura = medicaoHistrico
						.getDataLeituraParaRegistrar();
				String dataLeituraFormatada = dataLeitura.substring(0, 2) + "/"
						+ dataLeitura.substring(2, 4) + "/"
						+ dataLeitura.substring(4, 8);
				String codigoAnormalidade = "";
				if (medicaoHistrico.getLeituraAnormalidadeInformada() != null
						&& !medicaoHistrico.getLeituraAnormalidadeInformada()
								.equals("")) {
					codigoAnormalidade = ""
							+ medicaoHistrico.getLeituraAnormalidadeInformada()
									.getId();
				}else{
					codigoAnormalidade = "0";
				}
				// indicador confirmação leitura
				String indicadorConfirmacaoLeitura = medicaoHistrico
						.getIndicadorConfirmacaoLeitura();
				String errosTxt = medicaoHistrico.getGerarRelatorio();
				String leituraHidrometro = ""
						+ medicaoHistrico.getLeituraAtualInformada();

				relatorioBean = new RelatorioRegistrarLeiturasAnormalidadesBean(
						matriculaFuncionario, matriculaImovel, inscricaoImovel,
						tipoMedicao, dataLeituraFormatada, codigoAnormalidade,
						indicadorConfirmacaoLeitura, errosTxt,
						leituraHidrometro);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("idFaturamentoGrupo", ""+idFaturamento);

		String formatarAnoMesReferencia = anoMesReferencia.substring(4, 6)
				+ "/" + anoMesReferencia.substring(0, 4);

		parametros.put("mesAnoReferencia", formatarAnoMesReferencia);
		
		parametros.put("nomeArquivo", nomeArquivo);

		//cria uma instância do dataSource do relatório
		if ( relatorioBeans.size() > 0) {
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_REGISTRAR_LEITURAS_ANORMALIDADES,
					parametros, ds, tipoFormatoRelatorio);
		} else {

			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			
		}
		
		

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void agendarTarefaBatch() {
	}

}