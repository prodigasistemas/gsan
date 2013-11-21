/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gerador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class Gerador {

	public static void main(String arq[]) throws Exception {
		Vector v = new Vector();
		v.add("id");
		v.add("nome");
		v.add("nomeAbreviado");
		v.add("cpf");
		v.add("rg");
		v.add("dataEmissaoRg");
		v.add("dataNascimento");
		v.add("cnpj");
		v.add("email");
		v.add("indicadorUso");
		v.add("ultimaAlteracao");
		v.add("orgaoExpedidorRg");
		v.add("cliente");
		v.add("pessoaSexo");
		v.add("profissao");
		v.add("unidadeFederacao");
		v.add("clienteTipo");
		v.add("ramoAtividade");
		v.add("clienteEnderecos");
		v.add("clienteFones");
		v.add("clienteEndereco");
		
		String casoUso = "Cliente";
		String diretorio = "c:\\template";
		
		//consultajsp(v, casoUso);
		
		new Gerador().processar(v, diretorio, casoUso);
	}
	
	
	public void processar(Collection atributos, String diretorio, String casoUso) {
		try {
			StringBuffer consultaAction = consultaAction(atributos, casoUso);
			escreverArquivo (consultaAction,diretorio,"Consulta"+casoUso+"Action");
			
			StringBuffer consultaActionForm = consultaActionForm(atributos, casoUso);
			escreverArquivo (consultaActionForm,diretorio,"Consulta"+casoUso+"ActionForm");
			
			StringBuffer atualizarAction = atualizarAction(atributos, casoUso);
			escreverArquivo (atualizarAction,diretorio,"Atualizar"+casoUso+"Action");
			
			StringBuffer atualizarActionForm = atualizarActionForm(atributos, casoUso);
			escreverArquivo (atualizarActionForm,diretorio,"Atualizar"+casoUso+"ActionForm");
			
			StringBuffer consultajsp = consultajsp(atributos, casoUso);
			escreverArquivo (consultajsp,diretorio,"jsp");
			
			//StringBuffer consultaAction = //(atributos, casoUso);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void escreverArquivo (StringBuffer sb, String diretorio, String arquivo) throws IOException{
		if (!new File(diretorio).exists()) {
			new File(diretorio).mkdirs();
		}		
		FileOutputStream output = new FileOutputStream(new File(diretorio,arquivo + ".java"));
		try {
			output.write(sb.toString().getBytes());
		} finally {
			if (output != null)
				output.close();
		}
		
	}

/*
	public static void processar (Collection atribuitos, String casoUso, String template) throws IOException {
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("c:\\template"));
		cfg.setObjectWrapper(new DefaultObjectWrapper()); 


		Map root = new HashMap();
		root.put("casoUso","Cliente");

		Template temp = cfg.getTemplate("exemplo.tpl");

		Writer out = new OutputStreamWriter(System.out);
		temp.process(root, out);
		out.flush();
		
		out.close();

	}
*/

	public StringBuffer consultaAction(Collection atributos, String casoUso) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("package gcom.gui.cadastro.tarifasocial;");sb.append("\n");sb.append("\n");

		sb.append("import gcom.cadastro.cliente.FiltroClienteImovel;");sb.append("\n");
		sb.append("import gcom.fachada.Fachada;");sb.append("\n");
		sb.append("import gcom.gui.ControladorConsultaGcomAction;");sb.append("\n");
		sb.append("import gcom.gui.ControladorConsultaGcomActionForm;");sb.append("\n");
		sb.append("import gcom.gui.ControladorGcomAction;");sb.append("\n");
		sb.append("import gcom.gui.ControladorGcomActionForm;");sb.append("\n");
		sb.append("import gcom.util.filtro.Filtro;");sb.append("\n");
		sb.append("import gcom.util.filtro.ParametroSimples;");sb.append("\n");sb.append("\n");

		sb.append("import java.util.Collection;");sb.append("\n");sb.append("\n");

		sb.append("public class Consulta"+casoUso+"Action extends ControladorConsultaGcomAction  {");sb.append("\n");sb.append("\n");

		sb.append("	public static final String CASO_USO = Consulta"+casoUso+"Action.class.getSimpleName()  + \".do\";");sb.append("\n");sb.append("\n");

		sb.append("	public static final String ACAO_EXIBIR = CASO_USO + \"?\" + ControladorGcomAction.PARAMETRO_ACAO + \"=\" + ControladorGcomAction.PARAMETRO_ACAO_EXIBIR;");sb.append("\n");sb.append("\n");

		sb.append("	public static final String ACAO_PROCESSAR = CASO_USO + \"?\" + ControladorGcomAction.PARAMETRO_ACAO + \"=\" + ControladorGcomAction.PARAMETRO_ACAO_PROCESSAR;");sb.append("\n");sb.append("\n");

		sb.append("	public Collection pesquisarObjetoSistema(Filtro filtro ) throws Exception{ ");sb.append("\n");sb.append("\n");

		sb.append("			Filtro"+casoUso+" filtro"+casoUso+" = (Filtro"+casoUso+") filtro;");sb.append("\n");sb.append("\n");

		sb.append("			Collection  coll = Fachada.getInstancia().pesquisar"+casoUso+"(filtro"+casoUso+");");sb.append("\n");sb.append("\n");

		sb.append("			return coll; ");sb.append("\n");sb.append("\n");
		sb.append("		} ");sb.append("\n");sb.append("\n");


		sb.append("	public void carregarColecao(ControladorConsultaGcomActionForm actionForm) { ");sb.append("\n");sb.append("\n");

		sb.append("	} ");sb.append("\n");sb.append("\n");

		sb.append("	public Filtro gerarFiltro(ControladorConsultaGcomActionForm actionForm) { ");sb.append("\n");sb.append("\n");
		sb.append("		Consulta"+casoUso+"ActionForm form = (Consulta"+casoUso+"ActionForm) actionForm; 	");sb.append("\n");sb.append("\n");
		sb.append("        Filtro"+casoUso+" filtro"+casoUso+" = new Filtro"+casoUso+"();");sb.append("\n");sb.append("\n");
		        
		sb.append("        //Objetos que serão retornados pelo Hibernate através do clienteImovel ");sb.append("\n");sb.append("\n");
		Iterator it = atributos.iterator();
		while (it.hasNext()) {
			String atributo = it.next().toString();

		sb.append("        if (form.get"+getPrimeiroMaiusculo(atributo)+"() != null && !\"\".equals(form.get"+getPrimeiroMaiusculo(atributo)+"())) {");sb.append("\n");
		sb.append("            filtro"+casoUso+".adicionarParametro(new ParametroSimples(\""+atributo+"\", form.get"+getPrimeiroMaiusculo(atributo)+"())); ");sb.append("\n");
		sb.append("        }");sb.append("\n");
		}
		sb.append("	return filtro"+casoUso+";");sb.append("\n");sb.append("\n");
		sb.append("	}");sb.append("\n");sb.append("\n");
		sb.append("}");sb.append("\n");sb.append("\n");		
		
		System.out.print(sb.toString());
		return sb;
	}

	public  StringBuffer consultaActionForm(Collection atributos, String casoUso) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("package gcom.gui.cadastro.tarifasocial;");sb.append("\n");sb.append("\n");

		sb.append("import gcom.gui.ControladorConsultaGcomActionForm;");sb.append("\n");sb.append("\n");

		sb.append("import javax.servlet.http.HttpServletRequest;");sb.append("\n");sb.append("\n");

		sb.append("import org.apache.struts.action.ActionMapping;");sb.append("\n");sb.append("\n");
		
		sb.append("public class Consulta" + casoUso + "ActionForm extends ControladorConsultaGcomActionForm {");sb.append("\n");

		Iterator it = atributos.iterator();
		while (it.hasNext()) {
			String atributo = it.next().toString();
			sb.append("		private String " + atributo + ";");sb.append("\n");
		}

		sb.append("\n");

		it = atributos.iterator();
		while (it.hasNext()) {
			String atributo = it.next().toString();
			sb.append("		public String get"+getPrimeiroMaiusculo(atributo)+"() {");sb.append("\n");
			sb.append("			return "+atributo+";");sb.append("\n");
			sb.append("		}");sb.append("\n");
			sb.append("		public void set"+getPrimeiroMaiusculo(atributo)+"(String "+atributo+") {");sb.append("\n");
			sb.append("			this."+atributo+" = "+atributo+";");sb.append("\n");
			sb.append("		}");sb.append("\n");
			sb.append("		");sb.append("\n");
		}
		sb.append("		public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {");sb.append("\n");

		it = atributos.iterator();
		while (it.hasNext()) {
			String atributo = it.next().toString();
			sb.append("		this." + atributo + " = \"\";");sb.append("\n");
		}

		sb.append("		}");sb.append("\n");

		sb.append("}");sb.append("\n");
		
		System.out.print(sb.toString());
		return sb;
	}

	public StringBuffer atualizarAction(Collection atributos, String casoUso) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("package gcom.gui.cadastro.tarifasocial;");sb.append("\n");sb.append("\n");

		sb.append("import gcom.cadastro.cliente.FiltroClienteImovel;");sb.append("\n");
		sb.append("import gcom.fachada.Fachada;");sb.append("\n");
		sb.append("import gcom.gui.ControladorConsultaGcomAction;");sb.append("\n");
		sb.append("import gcom.gui.ControladorConsultaGcomActionForm;");sb.append("\n");
		sb.append("import gcom.gui.ControladorGcomAction;");sb.append("\n");
		sb.append("import gcom.gui.ControladorGcomActionForm;");sb.append("\n");
		sb.append("import gcom.util.filtro.Filtro;");sb.append("\n");
		sb.append("import gcom.util.filtro.ParametroSimples;");sb.append("\n");sb.append("\n");

		sb.append("import java.util.Collection;");sb.append("\n");sb.append("\n");

		sb.append("public class Atualizar"+casoUso+"Action extends ControladorAlteracaoGcomAction  {");sb.append("\n");sb.append("\n");

		sb.append("	public static final String CASO_USO = Atualizar"+casoUso+"Action.class.getSimpleName()  + \".do\";");sb.append("\n");sb.append("\n");

		sb.append("	public static final String ACAO_EXIBIR = CASO_USO + \"?\" + ControladorGcomAction.PARAMETRO_ACAO + \"=\" + ControladorGcomAction.PARAMETRO_ACAO_EXIBIR;");sb.append("\n");sb.append("\n");

		sb.append("	public static final String ACAO_PROCESSAR = CASO_USO + \"?\" + ControladorGcomAction.PARAMETRO_ACAO + \"=\" + ControladorGcomAction.PARAMETRO_ACAO_PROCESSAR;");sb.append("\n");sb.append("\n");
		
		sb.append("	public void atualizarObjeto(Object obj, HttpServletRequest request, ControladorAlteracaoGcomActionForm actionForm) throws Exception { ");sb.append("\n");sb.append("\n");

		sb.append("			Atualizar"+casoUso+"ActionForm form = (Atualizar"+casoUso+"ActionForm) actionForm;");sb.append("\n");sb.append("\n");

		sb.append("			" + casoUso + " objeto = (" + casoUso + ") obj;");sb.append("\n");sb.append("\n");
		
		sb.append("			Fachada.getInstancia().atualizar" + casoUso + "(objeto);");sb.append("\n");sb.append("\n");

		//sb.append("			return coll; ");sb.append("\n");sb.append("\n");
		sb.append("		} ");sb.append("\n");sb.append("\n");


		sb.append("	public Object consultarObjetoSistema(String[] chavesPrimarias, HttpServletRequest request, ControladorAlteracaoGcomActionForm actionForm) throws Exception { ");sb.append("\n");sb.append("\n");

		sb.append("			Atualizar"+casoUso+"ActionForm form = (Atualizar"+casoUso+"ActionForm) actionForm;");sb.append("\n");sb.append("\n");

		sb.append("			Filtro" + casoUso + " filtro = new Filtro" + casoUso + "()");sb.append("\n");sb.append("\n");
		
		sb.append("			// criar os filtros a partir da chave primaria ");sb.append("\n");sb.append("\n");

		sb.append("			" + casoUso + " objeto = Fachada.getInstancia().consultar" + casoUso + "(objeto);");sb.append("\n");sb.append("\n");

		//sb.append("			return coll; ");sb.append("\n");sb.append("\n");
		sb.append("		return objeto;  ");sb.append("\n");sb.append("\n");

		sb.append("	} ");sb.append("\n");sb.append("\n");


		sb.append("	public void carregarColecao(ControladorConsultaGcomActionForm actionForm) { ");sb.append("\n");sb.append("\n");
		sb.append("			Atualizar"+casoUso+"ActionForm form = (Atualizar"+casoUso+"ActionForm) actionForm;");sb.append("\n");sb.append("\n");

		sb.append("	} ");sb.append("\n");sb.append("\n");
		

		sb.append("	public Object gerarObject(ControladorAlteracaoGcomActionForm actionForm, HttpServletRequest request) { ");sb.append("\n");sb.append("\n");
		sb.append("			Atualizar"+casoUso+"ActionForm form = (Atualizar"+casoUso+"ActionForm) actionForm;");sb.append("\n");sb.append("\n");
		sb.append("			" + casoUso + " obj = new " + casoUso + "();");sb.append("\n");sb.append("\n");
		Iterator it = atributos.iterator();
		while (it.hasNext()) {
			String atributo = it.next().toString();
			sb.append("			if (!\"\".equals(form.get"+getPrimeiroMaiusculo(atributo)+"())) "); sb.append("\n");
			sb.append("			obj.set"+getPrimeiroMaiusculo(atributo)+"(form.get"+getPrimeiroMaiusculo(atributo)+"()); "); sb.append("\n");
			sb.append("\n");
		}
		
		sb.append("return obj;");sb.append("\n");
		sb.append("	} ");sb.append("\n");sb.append("\n");


		sb.append("	public void montarFormulario(Object obj, ControladorAlteracaoGcomActionForm actionForm) { ");sb.append("\n");sb.append("\n");
		sb.append("			Atualizar"+casoUso+"ActionForm form = (Atualizar"+casoUso+"ActionForm) actionForm;");sb.append("\n");sb.append("\n");
		sb.append("			if (obj instanceof "+casoUso+") { ");sb.append("\n");sb.append("\n");
		sb.append("			"+casoUso+" vo = ("+casoUso+") obj; ");sb.append("\n");sb.append("\n");
 
		it = atributos.iterator();
		while (it.hasNext()) {
			String atributo = it.next().toString();

			sb.append("			if (vo.get"+getPrimeiroMaiusculo(atributo)+"() != null)  "); sb.append("\n");
			sb.append("			form.set"+getPrimeiroMaiusculo(atributo)+"(vo.get"+getPrimeiroMaiusculo(atributo)+"() + \"\");"); sb.append("\n");
			sb.append("\n");
		}

		sb.append("//return obj;");
		sb.append("		} ");sb.append("\n");sb.append("\n");
		sb.append("	} ");sb.append("\n");sb.append("\n");

		System.out.print(sb.toString());
		return sb;
	}

	public StringBuffer atualizarActionForm(Collection atributos, String casoUso) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("package gcom.gui.cadastro.tarifasocial;");sb.append("\n");sb.append("\n");

		sb.append("import gcom.gui.ControladorConsultaGcomActionForm;");sb.append("\n");sb.append("\n");

		sb.append("import javax.servlet.http.HttpServletRequest;");sb.append("\n");sb.append("\n");

		sb.append("import org.apache.struts.action.ActionMapping;");sb.append("\n");sb.append("\n");
		
		sb.append("public class Atualizar" + casoUso + "ActionForm extends ControladorConsultaGcomActionForm {");sb.append("\n");

		Iterator it = atributos.iterator();
		while (it.hasNext()) {
			String atributo = it.next().toString();
			sb.append("		private String " + atributo + ";");sb.append("\n");
		}

		sb.append("\n");

		it = atributos.iterator();
		while (it.hasNext()) {
			String atributo = it.next().toString();
			sb.append("		public String get"+getPrimeiroMaiusculo(atributo)+"() {");sb.append("\n");
			sb.append("			return "+atributo+";");sb.append("\n");
			sb.append("		}");sb.append("\n");
			sb.append("		public void set"+getPrimeiroMaiusculo(atributo)+"(String "+atributo+") {");sb.append("\n");
			sb.append("			this."+atributo+" = "+atributo+";");sb.append("\n");
			sb.append("		}");sb.append("\n");
			sb.append("		");sb.append("\n");
		}
		sb.append("		public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {");sb.append("\n");

		it = atributos.iterator();
		while (it.hasNext()) {
			String atributo = it.next().toString();
			sb.append("		this." + atributo + " = \"\";");sb.append("\n");
		}

		sb.append("		}");sb.append("\n");

		sb.append("}");sb.append("\n");
		
		System.out.print(sb.toString());
		return sb;
	}
	
	public StringBuffer consultajsp(Collection atributos, String casoUso) throws IOException {
		StringBuffer sb = new StringBuffer();

		Iterator it = atributos.iterator();
		while (it.hasNext()) {
			String atributo = it.next().toString();
			sb.append("		<logic:present name=\""+atributo+"\" scope=\"request\">");sb.append("\n");
			sb.append("			<input type=\"text\" name=\""+atributo+"\" />");sb.append("\n");
			sb.append("		</logic:present>");sb.append("\n");sb.append("\n");

			sb.append("		<logic:notPresent name=\""+atributo+"\" scope=\"request\">");sb.append("\n");
			sb.append("			<html:text property=\""+atributo+"\" name=\"Consulta" + casoUso + "ActionForm\"/>");sb.append("\n");
			sb.append("		</logic:notPresent> ");sb.append("\n");
		}
		System.out.print(sb.toString());
		return sb;
	}

	private String getPrimeiroMaiusculo(String atribuito) {
		String retorno = atribuito.substring(0,1).toUpperCase() + atribuito.substring(1,atribuito.length()); 
		return retorno;
	}
}