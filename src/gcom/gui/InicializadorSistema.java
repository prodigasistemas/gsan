package gcom.gui;


import gcom.util.ConstantesAplicacao;
import gcom.util.HibernateUtil;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TimeZone;

import javax.servlet.http.HttpServlet;

/**
 * Servlet Class
 * 
 * @web.servlet name="InicializadorSistema" 
 * 				display-name="Name for InicializadorSistema" 
 * 				description="Description for InicializadorSistema"
 * @web.servlet-mapping url-pattern="/InicializadorSistema"
 * @web.servlet-init-param name="A parameter" value="A value"
 */

public class InicializadorSistema extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public InicializadorSistema() {
		super();

		HibernateUtil.inicializarSessionFactory();

		TimeZone.setDefault(TimeZone.getTimeZone("America/Belem"));

		if(getTipo().equalsIgnoreCase("Batch")){
			AgendadorTarefas.initAgendador();
		}
	}

	
	private String getTipo() {
        Properties propriedades = new Properties();
        InputStream stream;

        String tipo = "Online";
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            stream = classLoader.getResourceAsStream("version.properties");
            if (stream == null) {
                stream = ConstantesAplicacao.class.getClassLoader().getResourceAsStream("version.properties");
            }
            if (stream == null) {
                stream = ConstantesAplicacao.class.getResourceAsStream("version.properties");
            }

            propriedades.load(stream);

            String gsanTipo = propriedades.getProperty("gsan.tipo").trim();

            if(gsanTipo != null){
            	tipo = gsanTipo;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tipo;
    }
}
