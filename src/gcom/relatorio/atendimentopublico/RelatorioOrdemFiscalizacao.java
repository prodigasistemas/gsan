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

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioPadraoBatch;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
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
 * @author Raphael Rossiter
 * @data 04/01/2008
 */
public class RelatorioOrdemFiscalizacao extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioOrdemFiscalizacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ORDEM_FISCALIZACAO);
	}

	@Deprecated
	public RelatorioOrdemFiscalizacao() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		System.out.println("********************************************");
		System.out.println("ENTROU NO EXECUTAR RELATORIO ORDEM DE FISCALIZACAO ");
		System.out.println("********************************************");

		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeArquivo = (String) getParametro("nomeArquivo");
		String nomeArquivoZip = nomeArquivo+".zip";
		
		//Quantidade de ordens de fiscalização que serão geradas por arquivo compactado 
		final int quantidadeRegistros = 1000;

		List colecaoOrdemFiscalizacao = (ArrayList) getParametro("colecaoOrdemFiscalizacao");
		
		
		if(colecaoOrdemFiscalizacao != null && !colecaoOrdemFiscalizacao.isEmpty()){

			ZipOutputStream zos = null;
			File compactadoTipo = new File(nomeArquivoZip);
			
			try {
				zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo zip", e);
			}

			Fachada fachada = Fachada.getInstancia();
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			boolean flagTerminou = false;
			
			//Quantidade total de ordens que serão geradas
			int totalOrdemFiscalizacao = colecaoOrdemFiscalizacao.size();
			
			int quantidadeOrdemFiscalizacao = 0;
			int parte = 1;

			while (!flagTerminou) {

				List relatorioBeans = new ArrayList();

				for (int i = 0; i < quantidadeRegistros; i++) {
					
					int index = quantidadeOrdemFiscalizacao;
					
					RelatorioOrdemFiscalizacaoBean relatorioOrdemFiscalizacaoBean = 
					(RelatorioOrdemFiscalizacaoBean) colecaoOrdemFiscalizacao.get(index);

					relatorioBeans.add(relatorioOrdemFiscalizacaoBean);
						
					quantidadeOrdemFiscalizacao++;
					
					if(quantidadeOrdemFiscalizacao == totalOrdemFiscalizacao){
						break;
					}
				}
				
				if(quantidadeOrdemFiscalizacao == totalOrdemFiscalizacao){
					flagTerminou = true;
				}
				
				// Parâmetros do relatório
				Map parametros = new HashMap();
				
				parametros.put("imagem", sistemaParametro.getImagemRelatorio());
				parametros.put("nomeCompletoEmpresa", sistemaParametro.getNomeEmpresa());
				parametros.put("nomeEmpresa", sistemaParametro.getNomeAbreviadoEmpresa());
				parametros.put("cnpjEmpresa", Util.formatarCnpj(sistemaParametro.getCnpjEmpresa()));
				parametros.put("inscricaoEmpresa", "");

				// cria uma instância do dataSource do relatório
				RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

				System.out.println("******************************************");
				System.out.println("GERA RELATORIO ORDEM FISCALIZACAO (Parte "+parte+") - PDF");
				System.out.println("*******************************************");

				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ORDEM_FISCALIZACAO,
				parametros, ds, tipoFormatoRelatorio);
				
				// ------------------------------------
				// Grava o relatório no sistema
				try {

					System.out.println("***********************************************");
					System.out.println("COLOCA NO ZIP O RELATORIO ORDEM FISCALIZACAO (Parte "+parte+")");
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
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			
			RelatorioPadraoBatch relatorio = 
				new RelatorioPadraoBatch(Usuario.USUARIO_BATCH);

			relatorio.addParametro("titulo","RELATÓRIO ORDEM FISCALIZAÇÃO");
			relatorio.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
			relatorio.addParametro("nomeArquivo", nomeArquivoZip);
			
			byte[] relatorioGerado = (byte[]) relatorio.executar();
			
			
			persistirRelatorioConcluido(relatorioGerado, Relatorio.RELATORIO_ORDEM_FISCALIZACAO,
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
		
		List colecaoOrdemFiscalizacao = (ArrayList) getParametro("colecaoOrdemFiscalizacao");
		
		retorno = colecaoOrdemFiscalizacao.size();

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioOrdemFiscalizacao", this);

	}

}