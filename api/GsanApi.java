package gcom.api;

import gcom.api.relatorio.ReportFormat;
import gcom.gui.ActionServletException;
import gcom.util.IoUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GsanApi {

	private String url;
	private Gson gson;
	private String token;

	public GsanApi() {
	}

	public GsanApi(String url) {
		this.url = url;
		this.gson = new Gson();
		this.token = "Bearer bfIOYzIV9yqueZNBskx89RluCJ6bjRBtLc-Rp4vwLMs";
	}

	public void invoke(Object objeto) throws IOException, Exception {
		ClientResponse response = enviarJson(objeto);

		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntityInputStream()));
		StringBuilder builder = montarRetorno(reader);
		if (builder.length() == 0 || response.getStatus() != 200) {
			throw new Exception("Erro ao acessar servico");
		}

		JsonReturn jsonRetorno = gson.fromJson(builder.toString(), JsonReturn.class);
		if (jsonRetorno.temErro()) {
			throw new Exception("Erro no retorno: " + jsonRetorno.getErro());
		}
	}

	public void download(final String url, final String name, HttpServletResponse response) {
		try {
			File file = salvar(url, name);

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

	private StringBuilder montarRetorno(BufferedReader reader) throws IOException {
		StringBuilder builder = new StringBuilder();
		String linha = null;
		while ((linha = reader.readLine()) != null) {
			builder.append(linha);
		}
		return builder;
	}

	private ClientResponse enviarJson(Object objeto) {
		String json = gson.toJson(objeto);

		Client client = Client.create();

		WebResource webResource = client.resource(url);

		return webResource.header("Authorization", token).type("application/json").post(ClientResponse.class, json);
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
}