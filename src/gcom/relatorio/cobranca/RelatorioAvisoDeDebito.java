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

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ReavisoDeDebitoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.ZipUtil;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * classe responsável por criar as contas apartir do txt
 * 
 * @author Flávio Leonardo
 * @created 27/06/2007
 */
public class RelatorioAvisoDeDebito extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioAvisoDeDebito(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTA_TIPO_2);
	}

	@Deprecated
	public RelatorioAvisoDeDebito() {
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

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		RelatorioReavisoDeDebitoBean relatorioReavisoDeDebitoBean = null;

		List colecaoEmitirReavisoDeDebitoHelper = 
			(ArrayList) getParametro("colecaoEmitirReavisoDeDebitoHelper");
		
		String nomeArquivo = "RELATORIO"+((String) getParametro("nomeArquivo"));
		final int quantidadeRegistros = 1000;
		int parte = 1;
		
		
		if(colecaoEmitirReavisoDeDebitoHelper != null && !colecaoEmitirReavisoDeDebitoHelper.isEmpty()){
			
			ZipOutputStream zos = null;
			File compactadoTipo = new File(nomeArquivo+".zip");
			
			try {
				zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo zip", e);
			}
			
			boolean flagTerminou = false;
			
			int totalContas = colecaoEmitirReavisoDeDebitoHelper.size();
			int quantidadeContas = 0;

			ReavisoDeDebitoHelper emitirReavisoDeDebitoHelperPrimeiraConta = null;
			ReavisoDeDebitoHelper emitirReavisoDeDebitoHelperSegundaConta = null;
			while (!flagTerminou) {
				
				// coleção de beans do relatório
				List relatorioBeans = new ArrayList();				
				
				for (int i = 0; i < quantidadeRegistros; i++) {
					int index = quantidadeContas;
					ReavisoDeDebitoHelper emitirContaTipo2Helper = (ReavisoDeDebitoHelper) colecaoEmitirReavisoDeDebitoHelper.get(index);
					
					if(emitirReavisoDeDebitoHelperPrimeiraConta == null){
						emitirReavisoDeDebitoHelperPrimeiraConta = emitirContaTipo2Helper;
					}else{
						emitirReavisoDeDebitoHelperSegundaConta = emitirContaTipo2Helper;
					}
					
					if(emitirReavisoDeDebitoHelperPrimeiraConta != null && emitirReavisoDeDebitoHelperSegundaConta != null){
						
						relatorioReavisoDeDebitoBean = 
							new RelatorioReavisoDeDebitoBean(emitirReavisoDeDebitoHelperPrimeiraConta,
									emitirReavisoDeDebitoHelperSegundaConta);
						
						relatorioBeans.add(relatorioReavisoDeDebitoBean);
						
						emitirReavisoDeDebitoHelperPrimeiraConta = null;
						emitirReavisoDeDebitoHelperSegundaConta = null;
					}
					
					quantidadeContas++;
					
					if(quantidadeContas == totalContas){
						break;
					}
					emitirContaTipo2Helper = null;
				}
			
			
			//Caso tenha sobrado apenas uma conta
			if(emitirReavisoDeDebitoHelperPrimeiraConta != null){
				
				emitirReavisoDeDebitoHelperSegundaConta = new ReavisoDeDebitoHelper();
				
				relatorioReavisoDeDebitoBean = 
					new RelatorioReavisoDeDebitoBean(emitirReavisoDeDebitoHelperPrimeiraConta,
							emitirReavisoDeDebitoHelperSegundaConta);
				
				relatorioBeans.add(relatorioReavisoDeDebitoBean);

			}
			if(quantidadeContas == totalContas){
				flagTerminou = true;
			}
			
		
		Fachada fachada = Fachada.getInstancia();

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		System.out.println("******************************************");
		System.out.println("GERA RELATORIO AVISO CORTE (Parte "+parte+") - PDF");
		System.out.println("*******************************************");
		
		retorno = gerarRelatorio(ConstantesRelatorios.REAVISO_DE_DEBITO,
				parametros, ds, tipoFormatoRelatorio);
		
		

	//		 Grava o relatório no sistema
			try {
	
				System.out.println("***********************************************");
				System.out.println("COLOCA NO ZIP O RELATORIO AVISO CORTE (Parte "+parte+")");
				System.out.println("***********************************************");
	
				File leitura = new File(nomeArquivo+"-Parte-"+parte+".pdf");
				
				FileOutputStream out = new FileOutputStream(leitura.getAbsolutePath());
				
				out.write(retorno);
				out.close();
	
				parte++;
	
				ZipUtil.adicionarArquivo(zos, leitura);
				
				leitura.delete();
				
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no diretorio", e);
			}
			
			// limpamos a coleção de beans do relatório
			relatorioBeans.clear();
			relatorioBeans = null;			
		}
		try {
			System.out.println("**************");
			System.out.println("FINALIZA O ZIP");
			System.out.println("**************");
	
			zos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao fechar o zip do relatorio", e);
		}
	}
//	 ------------------------------------
	// Grava o relatório no sistema
	try {
		persistirRelatorioConcluido(retorno, Relatorio.AVISO_DEBITO,
				idFuncionalidadeIniciada);
	} catch (ControladorException e) {
		e.printStackTrace();
		throw new TarefaException("Erro ao gravar relatório no sistema", e);
	}

		// retorna o relatório gerado
		return retorno;
	
}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContaTipo2", this);

	}

}