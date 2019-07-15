package gcom.cadastro.arquivo;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

public class GeradorRegistroLoginUsuario {
	private Collection<Usuario> lista;

	public GeradorRegistroLoginUsuario(Collection<Usuario> lista) {
		this.lista = lista;
	}

	public StringBuilder build() {
		StringBuilder linha = new StringBuilder();

		for (Usuario usuario : lista) {
			linha.append("21");

			linha.append(Util.completaString(usuario.getNomeUsuario(), 50));
			linha.append(Util.completaString(usuario.getLogin(), 11));
			linha.append(Util.completaString(usuario.getSenha(), 40));
			linha.append(System.getProperty("line.separator"));
		}

		return linha;
	}
}
