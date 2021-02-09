package gcom.api.ordemservico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gcom.api.ordemservico.bo.RequisicaoEncerrarOrdemServico;
import gcom.api.ordemservico.bo.RequisicaoFotosOrdemServico;
import gcom.api.ordemservico.dto.FotoDTO;
import gcom.api.ordemservico.dto.OrdemServicoDTO;
import gcom.api.ordemservico.dto.UsuarioDTO;
import gcom.api.ordemservico.response.OrdemServicoResponse;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.usuario.Usuario;

public class OrdemServicoAPI extends HttpServlet {

	private static final long serialVersionUID = 4409487621679625139L;

	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private Map<String, String> resposta = new HashMap<String, String>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		if (verificarRequisicao("usuario"))
			validarLogin();

		if (verificarRequisicao("programadas"))
			pesquisarProgramadas();
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		if (verificarRequisicao("encerrar"))
			encerrar();
		
		if (verificarRequisicao("fotos"))
			salvarFotos();
	}

	private void encerrar() throws IOException {
		try {
			String json = obterJson();
			OrdemServicoDTO dto = new Gson().fromJson(json, OrdemServicoDTO.class);
			
			resposta = new RequisicaoEncerrarOrdemServico(dto).processar();
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		enviarResposta();
	}
	
	private void salvarFotos() throws IOException {
		try {
			String json = obterJson();
			Type listaConvertida = new TypeToken<List<FotoDTO>>() {}.getType();
			List<FotoDTO> listaDTO = new Gson().fromJson(json, listaConvertida);

			new RequisicaoFotosOrdemServico(listaDTO).processar();

			response.setStatus(HttpServletResponse.SC_OK);
			
		} catch (Exception e) {

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		enviarResposta();
	}
	
	private void pesquisarProgramadas() {
		try {
			Integer funcionarioId = Integer.valueOf(obterParametro("funcionarioId"));
			
			List<OrdemServicoDTO> dto = Fachada.getInstancia().pesquisarOrdensServicoProgramadas(funcionarioId);

			response.getOutputStream().print(toJson(dto));
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

	private void validarLogin() {
		try {
			String login = obterParametro("login");
			String senha = obterParametro("senha");

			Usuario usuario = Fachada.getInstancia().validarUsuario(login, senha);

			if (usuario != null) {
				UsuarioDTO dto = new UsuarioDTO(
						usuario.getId(),
						usuario.getNomeUsuario(),
						usuario.getUnidadeOrganizacional().getId(),
						usuario.getUnidadeOrganizacional().getDescricao(),
						usuario.getFuncionario().getId());

				response.getOutputStream().print(toJson(dto));
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	
	}

	private String obterParametro(String nome) {
		String parametro = request.getParameter(nome);
		return !parametro.isEmpty() ? parametro : getInitParameter(nome);
	}

	private String obterJson() throws IOException {
		StringBuilder json = new StringBuilder();

		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

		String linha = null;
		while ((linha = reader.readLine()) != null) {
			json.append(linha);
		}
		return json.toString();
	}

	private String toJson(Object objeto) {
		Gson gson = new Gson();
		return gson.toJson(objeto);
	}

	private boolean verificarRequisicao(String url) {
		return request.getRequestURI().contains(url);
	}
	
	private void enviarResposta() {
		try {	
			
			OrdemServicoResponse ordemServicoResponse = new OrdemServicoResponse();
			int status = -1;

			if (!resposta.containsKey("msg")) {
				ordemServicoResponse.setMensagem("");
				ordemServicoResponse.setEncerrada(true);
				status = HttpServletResponse.SC_OK;

			} else {
				ordemServicoResponse.setMensagem(resposta.get("msg"));
				ordemServicoResponse.setEncerrada(false);
				status = HttpServletResponse.SC_ACCEPTED;
			}

			response.getOutputStream().print(toJson(ordemServicoResponse));
			response.setStatus(status);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
}
