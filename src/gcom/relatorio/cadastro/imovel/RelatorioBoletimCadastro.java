package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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
 * classe responsável por criar os boletim de cadastro do txt
 * 
 * @author Rafael Pinto
 * @created 21/01/2008
 */
public class RelatorioBoletimCadastro extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioBoletimCadastro(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_CADASTRO);
	}

	@Deprecated
	public RelatorioBoletimCadastro() {
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
		
		System.out.println("*************************************");
		System.out.println("ENTROU NO EXECUTAR RELATORIO DE BOLETIM DE CADASTRO");
		System.out.println("*************************************");

		// valor de retorno
		byte[] retorno = null;

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		String nomeArquivo = "RELATORIO_"+((String) getParametro("nomeArquivo"));
		String nomeArquvioZip = nomeArquivo+".zip";
		
		final int quantidadeRegistros = 1000;

		List colecaoBoletimCadastro = 
			(ArrayList) getParametro("colecaoBoletimCadastro");
		
		if(colecaoBoletimCadastro != null && !colecaoBoletimCadastro.isEmpty()){
			
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
			
			int totalContas = colecaoBoletimCadastro.size();
			int quantidadeContas = 0;
			int parte = 1;

			while (!flagTerminou) {

				// coleção de beans do relatório
				List relatorioBeans = new ArrayList();
				
				for (int i = 0; i < quantidadeRegistros; i++) {
					
					int index = quantidadeContas;
					
					RelatorioBoletimCadastroBean relatorioBoletimCadastroBean = 
						(RelatorioBoletimCadastroBean) colecaoBoletimCadastro.get(index);
					
					relatorioBeans.add(relatorioBoletimCadastroBean);
					
					quantidadeContas++;
					
					if(quantidadeContas == totalContas){
						break;
					}
					
				}

				if(quantidadeContas == totalContas){
					flagTerminou = true;
				}
				
				// Parâmetros do relatório
				Map parametros = new HashMap();
				
				// adiciona os parâmetros do relatório
				// adiciona o laudo da análise
				
				parametros.put("imagem", sistemaParametro.getImagemRelatorio());

				// cria uma instância do dataSource do relatório
				RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

				System.out.println("************************************");
				System.out.println("GERA RELATORIO BOLETIM CADASTRO (Parte "+parte+") - PDF");
				System.out.println("************************************");

				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_BOLETIM_CADASTRO,
						parametros, ds, tipoFormatoRelatorio);

				// ------------------------------------
				// Grava o relatório no sistema
				try {

					System.out.println("*****************************************");
					System.out.println("COLOCA NO ZIP O RELATORIO BOLETIM CADASTRO (Parte "+parte+")");
					System.out.println("*****************************************");

					File leitura = new File(nomeArquivo+" Parte-"+parte+".pdf");
					
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

			relatorio.addParametro("titulo","RELATÓRIO DE BOLETIM DE CADASTRO");
			relatorio.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
			relatorio.addParametro("nomeArquivo", nomeArquvioZip);
			
			byte[] relatorioGerado = (byte[]) relatorio.executar();

			//nomeArquivo;
			persistirRelatorioConcluido(relatorioGerado, 
				Relatorio.RELATORIO_BOLETIM_CADASTRO,
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
		AgendadorTarefas.agendarTarefa("RelatorioBoletimCadastro", this);

	}

}
