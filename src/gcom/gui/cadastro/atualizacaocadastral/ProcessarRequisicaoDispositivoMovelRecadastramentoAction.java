package gcom.gui.cadastro.atualizacaocadastral;

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

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

public class ProcessarRequisicaoDispositivoMovelRecadastramentoAction extends GcomAction {

	private static final byte RESPOSTA_OK = '*';
	private static final byte RESPOSTA_ERRO = '#';
	private static final String RESPOSTA_INCONSISTENCIA = "!";

	private static final int ATUALIZAR_CADASTRO = 1;

	private Logger logger = Logger.getLogger(ProcessarRequisicaoDispositivoMovelRecadastramentoAction.class);

	private HttpServletResponse response;
	private InputStream in;
	private OutputStream out;

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		this.response = response;

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

			case ATUALIZAR_CADASTRO:
				this.atualizarCadastro(din, response, out);
				break;
			}

		} catch (Exception e) {
			logger.error("Erro: " + e.getMessage());
			escreverResposta(RESPOSTA_ERRO);
		} finally {
			fecharStream();
		}
		return null;
	}

	private void atualizarCadastro(DataInputStream data, HttpServletResponse response, OutputStream out)
			throws IOException {
		try {
			logger.info("Iniciando atualização online de cadastro...");

			InputStreamReader reader = new InputStreamReader(data);
			BufferedReader buffer = new BufferedReader(reader);

			AtualizacaoCadastral atualizacao = Fachada.getInstancia().carregarImovelAtualizacaoCadastral(buffer,
					new ArrayList<String>());

			if (atualizacao.comErro()) {
				escreverResposta(RESPOSTA_ERRO);
				
			} else if (atualizacao.getTotalImoveisComInconsistencia() == 0) {
				escreverResposta(RESPOSTA_OK);
				logger.info("Fim da atualização online de cadastro.");

			} else {
				String resposta = RESPOSTA_INCONSISTENCIA + Util.removerCaractereEspecial(atualizacao.getImoveisComInconsistencia().get(0).getMensagensErro().toString());

				escreverResposta(resposta);

				logger.warn("Não foi possível atualizar o cadastro do imóvel "
						+ atualizacao.getImovelAtual().getMatricula() + " com inconsistências: "
						+ atualizacao.getImoveisComInconsistencia().get(0).getMensagensErro());
			}
		} catch (Exception e) {
			escreverResposta(RESPOSTA_ERRO);
			logger.error("Erro ao atualizar cadastro: " + e.getMessage());
		}
	}

	private void escreverResposta(byte resposta) {
		try {
			response.setContentLength(1);
			out.write(resposta);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void escreverResposta(String resposta) {
		try {
			response.setContentLength(resposta.length());
			out.write(resposta.getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void fecharStream() {
		try {
			if (in != null)
				in.close();

			if (out != null)
				out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
