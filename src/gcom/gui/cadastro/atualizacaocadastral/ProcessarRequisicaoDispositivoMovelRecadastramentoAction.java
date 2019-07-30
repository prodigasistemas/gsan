package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

public class ProcessarRequisicaoDispositivoMovelRecadastramentoAction extends GcomAction {

	private static final byte RESPOSTA_OK = '*';
	private static final char RESPOSTA_ERRO = '#';
	private static final char RESPOSTA_INCONSISTENCIA = '!';
	
	private static final int ATUALIZAR_MOVIMENTO = 1;

	private Logger logger = Logger.getLogger(ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		InputStream in = null;
		OutputStream out = null;

		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			logger.error("Erro: " + e.getMessage());
		}

		try {
			in = request.getInputStream();
			DataInputStream din = new DataInputStream(in);

			int pacote = din.readByte();
			switch (pacote) {

			case ATUALIZAR_MOVIMENTO:
				this.atualizarCadastro(din, response, out);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();

			logger.error("Erro: " + e.getMessage());
			response.setContentLength(1);
			try {
				out.write(RESPOSTA_ERRO);
				out.flush();
			} catch (IOException e1) {
				logger.error("Erro: " + e.getMessage());
			}
		} finally {
			fecharInput(in);
			fecharOutput(out);
		}
		return null;
	}

	private void atualizarCadastro(DataInputStream data, HttpServletResponse response, OutputStream out) throws IOException {
		try {
			logger.info("Iniciando atualização online de cadastro...");
			
			InputStreamReader reader = new InputStreamReader(data);
			BufferedReader buffer = new BufferedReader(reader);
			
			AtualizacaoCadastral atualizacao = Fachada.getInstancia().carregarImovelAtualizacaoCadastral(buffer, new ArrayList<String>());

			if (atualizacao.getTotalImoveisComErro() == 0) {
				response.setContentLength(1);
				out.write(RESPOSTA_OK);
				out.flush();
				
				logger.info("Fim da atualização online de cadastro.");
			} else {
				response.setContentLength(1);
				out.write(RESPOSTA_INCONSISTENCIA);
				out.flush();
				
				logger.info("Não foi possível atualizar o cadastro do imóvel " + atualizacao.getImovelAtual().getMatricula() 
						+ " com inconsistências: " + atualizacao.getImoveisComErro().get(0).getMensagensErro());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao atualizar cadastro");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	private void fecharOutput(OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void fecharInput(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
