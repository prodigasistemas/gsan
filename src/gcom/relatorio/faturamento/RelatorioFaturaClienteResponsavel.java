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
import gcom.faturamento.bean.FaturaClienteResponsavelHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioPadraoBatch;
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
 * classe responsável por criar as faturas do cliente responsavel apartir do txt
 * 
 * @author Rafael Pinto
 * 
 * @created 16/08/2007
 */
public class RelatorioFaturaClienteResponsavel extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioFaturaClienteResponsavel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FATURA_CLIENTE_RESPONSAVEL);
	}

	@Deprecated
	public RelatorioFaturaClienteResponsavel() {
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
		
		System.out.println("**********************************************");
		System.out.println("ENTROU NO EXECUTAR RELAT. DE FATURA CLI. RESP.");
		System.out.println("**********************************************");

		// valor de retorno
		byte[] retorno = null;

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeArquivo = "RELATORIO_"+((String) getParametro("nomeArquivo"));
		String nomeArquvioZip = nomeArquivo+".zip";
		
		
		RelatorioFaturaClienteResponsavelBean relatorioFaturaClienteResponsavelBean = null;
		
		final int quantidadeRegistros = 1000;

		List colecaoFaturaClienteResponsavelHelper = 
			(ArrayList) getParametro("colecaoFaturaClienteResponsavelHelper");
		
		if(colecaoFaturaClienteResponsavelHelper != null && !colecaoFaturaClienteResponsavelHelper.isEmpty()){
			
			ZipOutputStream zos = null;
			File compactadoTipo = new File(nomeArquvioZip);
			
			try {
				zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo zip", e);
			}

			Fachada fachada = Fachada.getInstancia();
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			boolean flagTerminou = false;
			
			int totalFaturas = colecaoFaturaClienteResponsavelHelper.size();
			int quantidadeFaturas = 0;
			int parte = 1;

			while (!flagTerminou) {

				// coleção de beans do relatório
				List relatorioBeans = new ArrayList();
				
				FaturaClienteResponsavelHelper faturaClienteResponsavelHelperPrimeiraFatura = null;
				FaturaClienteResponsavelHelper faturaClienteResponsavelHelperSegundaFatura = null;
				
				for (int i = 0; i < quantidadeRegistros; i++) {
					
					int index = quantidadeFaturas;
					
					FaturaClienteResponsavelHelper faturaClienteResponsavelHelper = 
						(FaturaClienteResponsavelHelper) colecaoFaturaClienteResponsavelHelper.get(index);
					
					if(faturaClienteResponsavelHelperPrimeiraFatura == null){
						faturaClienteResponsavelHelperPrimeiraFatura = faturaClienteResponsavelHelper;
					}else{
						faturaClienteResponsavelHelperSegundaFatura = faturaClienteResponsavelHelper;
					}
					
					if(faturaClienteResponsavelHelperPrimeiraFatura != null && faturaClienteResponsavelHelperSegundaFatura != null){
						
						relatorioFaturaClienteResponsavelBean = new RelatorioFaturaClienteResponsavelBean(
								faturaClienteResponsavelHelperPrimeiraFatura,
								faturaClienteResponsavelHelperSegundaFatura,
								""+quantidadeFaturas);
						
						relatorioBeans.add(relatorioFaturaClienteResponsavelBean);
						
						faturaClienteResponsavelHelperPrimeiraFatura = null;
						faturaClienteResponsavelHelperSegundaFatura = null;
					}
					
					quantidadeFaturas++;
					
					if(quantidadeFaturas == totalFaturas){
						break;
					}
					
				}
				
				//Caso tenha sobrado apenas uma fatura
				if(faturaClienteResponsavelHelperPrimeiraFatura != null){
					
					faturaClienteResponsavelHelperSegundaFatura = new FaturaClienteResponsavelHelper();
					faturaClienteResponsavelHelperSegundaFatura.setNumeroFatura(null);
					
					relatorioFaturaClienteResponsavelBean = 
						new RelatorioFaturaClienteResponsavelBean(
							faturaClienteResponsavelHelperPrimeiraFatura,
							faturaClienteResponsavelHelperSegundaFatura,
							""+quantidadeFaturas);
					
					relatorioBeans.add(relatorioFaturaClienteResponsavelBean);
				}


				if(quantidadeFaturas == totalFaturas){
					flagTerminou = true;
				}
				
				// Parâmetros do relatório
				Map parametros = new HashMap();
				
				// adiciona os parâmetros do relatório
				// adiciona o laudo da análise
				
				parametros.put("imagem", sistemaParametro.getImagemRelatorio());

				// cria uma instância do dataSource do relatório
				RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

				System.out.println("***************************************************");
				System.out.println("GERA REL. FATURA CLI. RESP. (Parte "+parte+") - PDF");
				System.out.println("***************************************************");

				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_FATURA_CLIENTE_RESPONSAVEL,
						parametros, ds, tipoFormatoRelatorio);

				// ------------------------------------
				// Grava o relatório no sistema
				try {

					System.out.println("********************************************************");
					System.out.println("COLOCA NO ZIP O REL. FATURA CLI. RESP. (Parte "+parte+")");
					System.out.println("********************************************************");

					File leitura = new File(nomeArquivo+"Parte-"+parte+".pdf");
					
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
			
			}
			try {
				System.out.println("***********************************");
				System.out.println("FINALIZA O ZIP REL. FAT. CLI. RESP.");
				System.out.println("***********************************");

				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao fechar o zip do relatorio", e);
			}
		}
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {

			RelatorioPadraoBatch relatorio = 
				new RelatorioPadraoBatch(Usuario.USUARIO_BATCH);

			relatorio.addParametro("titulo","RELATÓRIO DE FATURAS CLIENTE RESPONSÁVEL");
			relatorio.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
			relatorio.addParametro("nomeArquivo", nomeArquvioZip);
			
			byte[] relatorioGerado = (byte[]) relatorio.executar();
			
			persistirRelatorioConcluido(relatorioGerado, Relatorio.FATURA_CLIENTE_RESPONSAVEL,
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
		AgendadorTarefas.agendarTarefa("RelatorioFaturaClienteResponsavel", this);

	}

}