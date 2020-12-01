package gcom.api.ordemservico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gcom.api.ordemservico.dto.OrdemServicoDTO;
import gcom.api.ordemservico.dto.UsuarioDTO;
import gcom.fachada.Fachada;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.seguranca.acesso.usuario.Usuario;

public class OrdemServicoAPI extends HttpServlet {

	private static final long serialVersionUID = 4409487621679625139L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameterMap().containsKey("usuario"))
			validarLogin(request, response);

		if (request.getParameterMap().containsKey("hidrometro"))
			validarHidrometro(getRequestParameter(request, "hidrometro"), response);

		if (request.getRequestURI().contains("programadas"))
			pesquisarOrdensServicoProgramadas(response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getRequestURI().contains("encerrar"))
			encerrarOrdemServico(request, response);
	}

	private void encerrarOrdemServico(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String json = converterJson(request);

			Gson gson = new Gson();
			OrdemServicoDTO dto = gson.fromJson(json.toString(), OrdemServicoDTO.class);

//			ProcessarRequisicaoOrdemServicoBO.getInstancia().execute(dto);
			
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

	private void pesquisarOrdensServicoProgramadas(HttpServletResponse response) {
		List<OrdemServicoDTO> dto = Fachada.getInstancia().pesquisarOrdensServicoProgramadas();

		try {
			response.getOutputStream().print(toJson(dto));
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

	private void validarLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String login = getRequestParameter(request, "login");
			String senha = getRequestParameter(request, "senha");

			Usuario usuario = Fachada.getInstancia().validarUsuario(login, senha);

			if (usuario != null) {
				UsuarioDTO dto = new UsuarioDTO(
						usuario.getId(), 
						usuario.getNomeUsuario(), 
						usuario.getUnidadeOrganizacional().getId(),
						usuario.getUnidadeOrganizacional().getDescricao());
				
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

	private void validarHidrometro(String numero, HttpServletResponse response) {
		Hidrometro hidrometro = Fachada.getInstancia().pesquisarHidrometroNumeroSituacao(numero, HidrometroSituacao.DISPONIVEL);

		if (hidrometro != null)
			response.setStatus(HttpServletResponse.SC_OK);
		else
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

	private String getRequestParameter(HttpServletRequest request, String name) {
		String param = request.getParameter(name);
		return !param.isEmpty() ? param : getInitParameter(name);
	}

	private String converterJson(HttpServletRequest request) throws IOException {
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
}
