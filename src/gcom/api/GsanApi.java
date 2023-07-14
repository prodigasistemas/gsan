package gcom.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import gcom.api.relatorio.ReportFormat;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.seguranca.SegurancaParametro;
import gcom.util.IoUtil;

public class GsanApi {

	private String url;
	private Gson gson;

	public GsanApi() {
	}

	public GsanApi(String url) {
		this.url = url;
		this.gson = new Gson();
	}

	public void invoke(Object objeto) throws IOException, Exception {
		ClientResponse response = enviarJson(objeto);

		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
		StringBuilder builder = montarRetorno(reader);
		if (builder.length() == 0 || (response.getStatus() != 200 && response.getStatus() != 201)) {
			throw new Exception("Erro ao acessar servico");
		}

		JsonReturn jsonRetorno = gson.fromJson(builder.toString(), JsonReturn.class);
		if (jsonRetorno.temErro()) {
			throw new Exception("Erro no retorno: " + jsonRetorno.getErro());
		}
	}

	public void download(final String nome, HttpServletResponse response) {
		try {
			File file = salvar(nome);

			response.setContentType(ReportFormat.PDF.getContentType());
			response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());

			ServletOutputStream sos = response.getOutputStream();
			sos.write(IoUtil.getBytesFromFile(file));
			sos.flush();
			sos.close();

			file.delete();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_relatorio");
		}
	}

	public File salvar(final String nome) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new URL(url + nome).openStream());
		FileOutputStream out = new FileOutputStream(nome);

		final byte data[] = new byte[1024];
		int count;
		while ((count = in.read(data, 0, 1024)) != -1) {
			out.write(data, 0, count);
		}

		in.close();
		out.close();

		return new File(nome);
	}

	public StringBuilder montarRetorno(BufferedReader reader) throws IOException {
		StringBuilder builder = new StringBuilder();
		String linha = null;
		while ((linha = reader.readLine()) != null) {
			builder.append(linha);
		}
		return builder;
	}

	public ClientResponse enviarJson(Object objeto) throws Exception {
		String json = gson.toJson(objeto);

		Client client = Client.create();

		WebResource webResource = client.resource(url);

		return webResource.header("Authorization", autenticar()).type("application/json").post(ClientResponse.class, json);
	}

	private File salvar(final String url, final String name) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new URL(url + name).openStream());
		FileOutputStream out = new FileOutputStream(name);

		final byte data[] = new byte[1024];
		int count;
		while ((count = in.read(data, 0, 1024)) != -1) {
			out.write(data, 0, count);
		}

		in.close();
		out.close();

		return new File(name);
	}

	private String autenticar() throws Exception {
		
		String caminhoApi = Fachada.getInstancia()
				.getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_BASIC_AUTH.toString());

		Object[] credenciais = Fachada.getInstancia().obterCredenciaisOauth();

		String client_id = (String) credenciais[0];
		String client_pass = (String) credenciais[1];
		String authStr = client_id + ":" + client_pass;

		String basic = new String(Base64.encodeBase64(new String(authStr).getBytes("UTF-8")));

		BufferedReader in = new BufferedReader(new InputStreamReader(requisitarToken(caminhoApi, basic)));
		StringBuilder builder = montarRetorno(in);

		TokenDto tokenDto = gson.fromJson(builder.toString(), TokenDto.class);

		return tokenDto.getToken_type() + " " + tokenDto.getAccess_token();
	}
	
	private InputStream requisitarToken(String caminho, String basic) throws Exception {
		
		URL url = new URL(caminho);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization", "Basic " + basic);
		
		return connection.getInputStream();
	}

}