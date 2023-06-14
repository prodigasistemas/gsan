package gcom.api.pagamentocredito;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpStatus;

public class PagamentoCreditoFilter implements Filter {

	private static final String PROD_DOMINIO = "gsan.cosanpa.pa.gov.br";
	private static final String HOMOL_DOMINIO = "homologa.cosanpa.pa.gov.br";
	

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String dominio = extrairDominio(req.getRequestURL().toString());
		
		if(!dominio.equalsIgnoreCase(PROD_DOMINIO) && !dominio.equalsIgnoreCase(HOMOL_DOMINIO)) {
			request.setAttribute("filtro", HttpStatus.SC_FORBIDDEN);
		}
		
		chain.doFilter(request, response);
		
	}

	private String extrairDominio(String url) {
		return url.substring(7, url.indexOf(":", 7));
		
	}

	public void init(FilterConfig arg0) throws ServletException {}

}
