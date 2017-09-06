package gcom.relatorio.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ReportUtil {
	
	private static int HEADER = 1;
	private static int GROUP = 2;
	private static int TOTALIZER = 3;

	public List<ReportField> headerFieldsFromClass(Class t) {
		return fieldsFromClass(t, HEADER);
	}
	
	public List<ReportField> groupFieldsFromClass(Class t) {
		return fieldsFromClass(t, GROUP);
	}
	
	public List<ReportField> totalizerFieldsFromClass(Class t) {
		return fieldsFromClass(t, TOTALIZER);
	}
	
	public List<ReportField> fieldsFromClass(Class t, int fieldType) {
		Field[] campos = t.getDeclaredFields();
		List<ReportField> fields = new ArrayList<ReportField>();

		for (Field field : campos) {
			if (field.isAnnotationPresent(ReportElementType.class)) {
				ReportElementType campo = field.getAnnotation(ReportElementType.class);

				if (fieldType == HEADER && campo.header()) {
					fields.add(new ReportField(field.getName(), campo.description(), campo.align(), campo.type()));
				} else if (fieldType == GROUP && campo.group()) {
					fields.add(new ReportField(field.getName(), campo.description()));
				} else if (fieldType == TOTALIZER && campo.totalizer()) {
					fields.add(new ReportField(field.getName()));
				}
			}
		}

		return fields;
	}

	public void invokeReport(ReportDTO report, String url) throws IOException, Exception {
		Gson gson = new Gson();

		String json = gson.toJson(report);

		Client client = Client.create();

		WebResource webResource = client.resource(url);

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, json);

		InputStream input = response.getEntityInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		StringBuilder builder = new StringBuilder();
		String linha = null;

		while ((linha = reader.readLine()) != null) {
			builder.append(linha);
		}

		if (builder.length() == 0 || response.getStatus() != 200) {
			throw new Exception("Erro ao acessar servico");
		}

		ReportJsonReturn jsonRetorno = gson.fromJson(builder.toString(), ReportJsonReturn.class);

		if (jsonRetorno.temErro()) {
			throw new Exception("Erro no retorno: " + jsonRetorno.getErro());
		}
	}
}
