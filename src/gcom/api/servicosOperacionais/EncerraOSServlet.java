package gcom.api.servicosOperacionais;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.ArquivoRetornoAplicativoExecucaoOSHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.FachadaException;
import gcom.util.Util;

@SuppressWarnings("serial")
public class EncerraOSServlet extends HttpServlet {

	private static Gson gson;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getOutputStream().print("{\"id\":1, \"Nome\":\"Marcelo\"}");
		resp.setStatus(HttpServletResponse.SC_OK);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		encerramentoOS(req, resp);
		
		resp.setStatus(HttpServletResponse.SC_OK);
		

		
	}
	
	@SuppressWarnings("unused")
	private static void encerramentoOS(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		Fachada fachada = Fachada.getInstancia();
		JsonObject json = montarRetorno(req);
		
		// Aqui estou setando os valores no Helper generico
		ArquivoRetornoAplicativoExecucaoOSHelper araeOSH = gson.fromJson(json, ArquivoRetornoAplicativoExecucaoOSHelper.class);
		
		criarObjetosHelperEncerramento(araeOSH, fachada);
		
		
	}
	
	private static void criarObjetosHelperEncerramento(ArquivoRetornoAplicativoExecucaoOSHelper araeOSH, Fachada fachada) {		
		// Aqui entra a classe que monta o helper
		
		
	}
	
	
	@SuppressWarnings("unused")
	private void envocaMetdodo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// Monta a lista com os métodos declarados na classe
		Method[] met = getClass().getDeclaredMethods();
		
		// Monta o objeto Json com os campos passados na requisição 
		JsonObject jo = montarRetorno(req);
		
		String nomeMetodo = "";
		
		for (Method method : met) {
			
			nomeMetodo = jo.get("operacao").toString();
		//	nomeMetodo = identificadorAcao(req, resp);
			
			if (method.getName().equals(nomeMetodo)) {
				try {
					method = EncerraOSServlet.class.getDeclaredMethod(nomeMetodo, HttpServletRequest.class, HttpServletResponse.class);
					method.invoke(null, req, resp);
					
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private String identificadorAcao(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	
			try {
				return  obtemIdentificador(req);
			} catch (Exception e) {
				resp.sendError(400, e.getMessage());
				return null;
			}
			

	}
	
	/*
	 * Obtem o identificador do recurso que se deseja
	 */
	private String obtemIdentificador(HttpServletRequest req) throws Exception {
		String requestUri = req.getRequestURI();
		
		String[] pedacosDaUri = requestUri.replaceFirst("/", "").split("/");
		boolean contextoEncontrado = false;
		
		for (String contexto : pedacosDaUri) {
			if (contexto.equals("encerrar")) {
				contextoEncontrado = true;
				continue;
			}
			if (contextoEncontrado) {
				try {
					return URLDecoder.decode(contexto, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					return URLDecoder.decode(contexto);
				}
			}
		}
		throw new Exception("Recurso sem identificador");
		
	}

	
	private static JsonObject montarRetorno(HttpServletRequest req) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String linha = null;
		while ((linha = reader.readLine()) != null) {
			builder.append(linha);
		}
		
		JsonParser jp = new JsonParser();
		return  jp.parse(builder.toString()).getAsJsonObject();
	}	
}
