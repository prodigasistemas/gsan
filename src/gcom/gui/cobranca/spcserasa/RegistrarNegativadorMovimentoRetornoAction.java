package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorMovimento;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

public class RegistrarNegativadorMovimentoRetornoAction extends GcomAction {
	
	private static Logger logger = Logger.getLogger(ControladorFaturamento.class);

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		/*
		 * Passos do Processo
		 * 
		 * 1 - Chama método para Validar Arquivo
		 * 2 - Retorna Tela de Informações do Arquivo para o usuário
		 * 3 - Insere Processo Batch
		 * 4 - Chama Registrar Movimento Retorno
		 * 5 - Busca o Arquivo na pasta bin do servidor
		 * 6 - Chama Registrar Movimento Retorno e SubFluxos
		 */
		ActionForward retorno = actionMapping.findForward("registrarNegativadorMovimentoRetornoResumo");
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		Negativador negativador = null;
		String numeroSequencialArquivo = "";
		int contadorRegistro = 0;

		try {
			String nomeItem = "";
			String idNegativador = null;
			
			DiskFileUpload upload = new DiskFileUpload();
			List itens = upload.parseRequest(httpServletRequest);

			FileItem item = null;

			StringBuilder stringBuilderTxt = new StringBuilder();

			int quantidadeRegistros = 0;

			for (int i = 0; i < itens.size(); i++) {
				item = (FileItem) itens.get(i);
				
				if (item.getFieldName().equals("idNegativador")) {
					idNegativador = item.getString();
					negativador = this.pesquisarNegativador(fachada, idNegativador);
				}

				if (!item.isFormField()) {
					nomeItem = "REG_SPC_SERASA_" + Util.formatarDataAAAAMMDD(new Date());

					if (idNegativador != null) {
						quantidadeRegistros = this.escreverArquivo(negativador, nomeItem, item, stringBuilderTxt, quantidadeRegistros);
					}
				}
			}

			if (nomeItem.equals("")) {
				throw new ActionServletException("atencao.arquivo_negativador_movimento_retorno_inexistente");
			}

			if (quantidadeRegistros != 0) {
				Object[] retornoValidadacao = fachada.validarArquivoMovimentoRetorno(stringBuilderTxt, negativador);
				
				NegativadorMovimento negativadorMovimento = (NegativadorMovimento) retornoValidadacao[1];
				logger.info("Movimento de Retorno do Negativador: " + negativadorMovimento.getId());

				Collection colecaoRegistrosLidos = (Collection) retornoValidadacao[0];
				Iterator iteratorColecaoRegistroLidos = colecaoRegistrosLidos.iterator();
				
				while (iteratorColecaoRegistroLidos.hasNext()) {
					String registro = (String) iteratorColecaoRegistroLidos.next();
					
					contadorRegistro = contadorRegistro + 1;
					
					if (contadorRegistro == 1) {
						if (Negativador.NEGATIVADOR_SPC.intValue() == negativador.getId().intValue()) {
							numeroSequencialArquivo = registro.substring(18, 25);
						} else if (Negativador.NEGATIVADOR_SERASA.intValue() == negativador.getId().intValue()) {
							numeroSequencialArquivo = registro.substring(120, 125);
						}
					}
				}
			} else {
				throw new ActionServletException("atencao.arquivo_sem_dados", null, nomeItem);
			}
		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (NumberFormatException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.sistema", e);
		}

		this.setarInformacoesRetorno(sessao, negativador, numeroSequencialArquivo, contadorRegistro);

		Usuario usuario = this.getUsuarioLogado(httpServletRequest);
		fachada.inserirProcessoRegistrarNegativadorMovimentoRetorno(usuario);

		return retorno;
	}

	private int escreverArquivo(Negativador negativador, String nomeItem, FileItem item, StringBuilder stringBuilderTxt, int quantidadeRegistros)
			throws IOException, FileNotFoundException {
		InputStreamReader reader = new InputStreamReader(item.getInputStream());
		BufferedReader buffer = new BufferedReader(reader);

		File arquivo = new File(nomeItem);
		FileOutputStream out = new FileOutputStream(arquivo);
		PrintWriter writer = new PrintWriter(out);

		boolean eof = false;
		while (!eof) {
			String linha = buffer.readLine();

			if (linha != null) {
				writer.println(linha);
			}

			this.verificarErroHeaderTrailler(negativador, linha);

			if (linha != null && linha.length() > 0) {
				stringBuilderTxt.append(linha);
				stringBuilderTxt.append("\n");
				quantidadeRegistros = quantidadeRegistros + 1;
			} else {
				break;
			}
		}

		buffer.close();
		reader.close();
		item.getInputStream().close();
		writer.flush();
		writer.close();
		return quantidadeRegistros;
	}

	private void setarInformacoesRetorno(HttpSession sessao, Negativador negativador, String numeroSequencialArquivo, int contadorRegistro) {
		sessao.setAttribute("nomeNegativador", negativador.getCliente().getNome());
		sessao.setAttribute("dataProcessamento", Util.formatarData(new Date()));
		sessao.setAttribute("horaProcessamento", Util.formatarHoraSemData(new Date()));
		sessao.setAttribute("numeroSequencialArquivo", numeroSequencialArquivo);
		sessao.setAttribute("numeroRegistros", contadorRegistro);
	}

	private void verificarErroHeaderTrailler(Negativador negativador, String linha) {
		if (negativador.getId().equals(Negativador.NEGATIVADOR_SPC)) {
			if (linha != null && linha.length() >= 334
					&& (linha.substring(0, 2).equals("00")
							|| linha.substring(0, 2).equals("99"))) {

				String codigoRetorno = linha.substring(324, 334);
				if (codigoRetorno != null && !codigoRetorno.equals("0000000000")) {
					throw new ActionServletException("atencao.header_trailler_com_erro");
				}
			}
		} else if (negativador.getId().equals(Negativador.NEGATIVADOR_SERASA)) {
			if (linha != null && linha.length() >= 593
					&& (linha.substring(0, 1).equals("0")
							|| linha.substring(0, 1).equals("9"))) {

				String codigoRetorno = linha.substring(533, 593);
				if (codigoRetorno != null && !codigoRetorno.trim().equals("")) {
					throw new ActionServletException("atencao.header_trailler_com_erro");
				}
			}
		}
	}

	private Negativador pesquisarNegativador(Fachada fachada, String idNegativador) {
		Negativador negativador;
		FiltroNegativador filtroNegativador = new FiltroNegativador();
		filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.ID, idNegativador));
		filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoNegativador = fachada.pesquisar(filtroNegativador, Negativador.class.getName());
		negativador = (Negativador) Util.retonarObjetoDeColecao(colecaoNegativador);
		return negativador;
	}

}
