package gcom.api.servicosOperacionais;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jmx.snmp.Enumerated;

import gcom.api.servicosOperacionais.DTO.ProgramadasDTO;
import gcom.api.servicosOperacionais.DTO.UsuarioDTO;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.micromedicao.ProcessarRequisicaoAplicativoExecucaoOSAction;
import gcom.micromedicao.ArquivoRetornoAplicativoExecucaoOSHelper;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.FachadaException;
import gcom.util.Util;

@SuppressWarnings("serial")
public class EncerraOSServlet extends HttpServlet {

	@PathParam(value = "usuario")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getRequestURI().contains("usuario"))
			getUsuario(Integer.valueOf(getRequestParameter(req, "usuario")), req, resp);
		
		if (req.getRequestURI().contains("programadas"))
			getProgramadas(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		encerramentoOS(req, resp);
		resp.setStatus(HttpServletResponse.SC_OK);
	}
	
	@SuppressWarnings("unused")
	private static void encerramentoOS(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		Gson gson = new Gson();
		JsonObject json = montarRetorno(req);
		
		// Aqui estou setando os valores no Helper generico
		ArquivoRetornoAplicativoExecucaoOSHelper araeOSH = gson.fromJson(json, ArquivoRetornoAplicativoExecucaoOSHelper.class);
		
		ProcessarRequisicaoAplicativoExecucaoOSAction pros = ProcessarRequisicaoAplicativoExecucaoOSAction.getInstance();
		pros.executeEncerramento(araeOSH);;
		
		
	}

	private void getProgramadas(HttpServletRequest req, HttpServletResponse resp) {
		
		Collection<ProgramadasDTO> dto = Fachada.getInstancia().recuperaOSProgramacao();
		Gson gson = new Gson();
		
		try {
			String jo = gson.toJson(dto);
			resp.getOutputStream().print(jo);
			resp.setStatus(HttpServletResponse.SC_OK);
			
		} catch (IOException e) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
		}
		
	}

	private void getUsuario(int id, HttpServletRequest req, HttpServletResponse resp) {
		
		UsuarioDTO usuario = Fachada.getInstancia().pesquisarUsuario(id);
		Gson gson = new Gson();
		
		try {
			resp.getOutputStream().print(gson.toJson(usuario));
			resp.setStatus(HttpServletResponse.SC_OK);
			
		} catch (IOException e) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
		}
		
	}
	
    protected String getRequestParameter(HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        return !param.isEmpty() ? param : getInitParameter(name);
    }
    
	@SuppressWarnings("unused")
	private void envocaMetdodo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// Monta a lista com os métodos declarados na classe
		Method[] met = getClass().getDeclaredMethods();
		
		// Monta o objeto Json com os campos passados na requisição 
		JsonObject jo = montarRetorno(req);
		
		String nomeMetodo = "";
		
		for (Method method : met) {
			
			nomeMetodo = identificadorAcao(req, resp);
			
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
