/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestao de Servicos de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

package gcom.gui;


import gcom.util.ConstantesAplicacao;
import gcom.util.HibernateUtil;
import gcom.util.agendadortarefas.AgendadorTarefas;

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

	public String getTipo(){
		String tipo = "Online";
		String gsanTipo = ConstantesAplicacao.get("gsan.tipo");
		if(gsanTipo != null){
			tipo = gsanTipo;
		}
		
		return tipo;
	}
}