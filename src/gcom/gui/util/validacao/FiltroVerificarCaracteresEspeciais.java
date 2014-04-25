package gcom.gui.util.validacao;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Filtro que procura em todos os requests do sistema se algum caracter invalido
 * está sendo passado
 * 
 * @author Rodrigo Silveira
 */
public class FiltroVerificarCaracteresEspeciais extends HttpServlet
		implements
			Filter {
	private FilterConfig filterConfig;
	private static final long serialVersionUID = 1L;
	public void init(FilterConfig filterConfig) {
		setFilterConfig(filterConfig);
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		try {

			HttpServletRequest requisicao = (HttpServletRequest) request;

			Enumeration enumeration = requisicao.getParameterNames();

			Pattern titleFinder = Pattern
					.compile("[a-zA-Z0-9/.]*((\\-)?)[a-zA-Z0-9/.]*");

			boolean achouCaracterEspecial = false;

			while (enumeration.hasMoreElements()) {

				String valor = requisicao.getParameter((String) enumeration
						.nextElement());

				System.out.println(valor);
				Matcher m = titleFinder.matcher(valor);
				if (!m.matches()) {
					achouCaracterEspecial = true;

					RequestDispatcher rd = filterConfig
							.getServletContext()
							.getRequestDispatcher(
									"/jsp/util/erro_caracteres_especiais_invalidos.jsp");
					rd.forward(request, response);
					break;

				}

			}

			if (!achouCaracterEspecial) {
				filterChain.doFilter(request, response);
			}

		} catch (ServletException sx) {
			throw sx;
		} catch (IOException iox) {
			throw iox;
		}
	}

	public void destroy() {
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
}
