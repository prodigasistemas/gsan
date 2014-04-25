package gcom.interceptor;

import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.Filtro;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Classe que representa o objeto de transaço,
 * 
 * objeto transação é o objeto que ao ser atualizado compra com o da base pra
 * ver se vc ta usando o objeto mais novo e que registra das alterações entre um
 * objeto e outro
 * 
 * date 16/01/2006
 * 
 * @author thiago
 */
public abstract class ObjetoTransacao extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	/** operacao realizada pelo usuario */
	private OperacaoEfetuada operacaoEfetuada = null;

	private Collection usuarioAcaoUsuario = new HashSet();
	
	// Utilizada para classes com chaves compostas
	private Integer id2;	

	/**
	 * @return Retorna o campo id2.
	 */
	public Integer getId2() {
		return id2;
	}

	/**
	 * @param id2 O id2 a ser setado.
	 */
	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public OperacaoEfetuada getOperacaoEfetuada() {
		return operacaoEfetuada;
	}

	public void setOperacaoEfetuada(int idOperacao) {
		Operacao operacao = new Operacao();
		operacao.setId(idOperacao);
		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		this.operacaoEfetuada = operacaoEfetuada;
		
	}

	public void setOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada) {
		this.operacaoEfetuada = operacaoEfetuada;
	}

	public void adicionarUsuario(Usuario usuario, UsuarioAcao usuarioAcao) {
		for (Iterator iter = this.usuarioAcaoUsuario.iterator(); iter.hasNext();) {
			UsuarioAcaoUsuarioHelper UAUHExistente = (UsuarioAcaoUsuarioHelper) iter.next();
			if (UAUHExistente.getUsuario() != null && UAUHExistente.getUsuario().equals(usuario)
				&& UAUHExistente.getUsuarioAcao() != null && UAUHExistente.getUsuarioAcao().equals(usuarioAcao)){
				return;
			}
			
		}
		this.usuarioAcaoUsuario.add(new UsuarioAcaoUsuarioHelper(usuario,
				usuarioAcao));
	}

	public Collection getUsuarioAcaoUsuarioHelp() {
		return usuarioAcaoUsuario;
	}

	public void setUsuarioAcaoUsuarioHelp(Collection usuarioAcaoUsuario) {
		this.usuarioAcaoUsuario = usuarioAcaoUsuario;
	}

	/**
	 * Retorna a data da ultima alteração do objeto
	 * 
	 * @return Date
	 */
	public abstract Date getUltimaAlteracao();

	/**
	 * Armazena a data da ultima alteração
	 * 
	 * @param ultimaAlteracao
	 *            date
	 */
	public abstract void setUltimaAlteracao(Date ultimaAlteracao);

	/**
	 * Método que retorna o filtro com as chaves primarias preenchidas
	 * 
	 * @return
	 */
	public abstract Filtro retornaFiltro();
	
	/**
	 * Retorna a coleção de atributos que serão registrados, ou seja,
	 * todos os atributos que contem uma Annotation @ControleAlteracao
	 * @return um array com os nomes dos atributos
	 */
	public String[] retornarAtributosSelecionadosRegistro(){
		ArrayList<String> atributos = new ArrayList<String>();
		Class classe = getClass();
		buscarCamposComControleAlteracao(classe, atributos, "");
		
		return atributos.toArray(new String[0]);	
	}
	
	
	
	private void buscarCamposComControleAlteracao(Class classe, ArrayList<String> atributos, String caminhoAtual){
		Annotation anot[] = classe.getAnnotations();
		if (temControleAlteracao(anot) != null){
			Field[] campos = classe.getDeclaredFields();
			for (int i = 0; i < campos.length; i++) {
				Annotation anotCampo = temControleAlteracao(campos[i].getAnnotations());
				if (anotCampo != null){
					Integer idOperacao = null;
					if (operacaoEfetuada != null && operacaoEfetuada.getOperacao() != null){
						idOperacao = operacaoEfetuada.getOperacao().getId();
					}
					int[] funcionalidades = ((ControleAlteracao)anotCampo).funcionalidade();
					if (idOperacao != null) {						
						for (int j = 0; j < funcionalidades.length; j++) {
							if (funcionalidades[j] == idOperacao.intValue()){				
									atributos.add(campos[i].getName());						
							}						
						}
					} else {
						atributos.add(campos[i].getName());
					}
					
				}
			}			
		}		
	}
	
	/**
	 * Retorna o retorno da chamada do get referente ao atributo passado
	 * Pode haver itens conjugados. Por exemplo: imovel.id  para o caso de
	 *   getImovel().getId())
	 * Caso algum item do conjugado esteja nulo, o retorno será vazio
	 * @param atributo 
	 * @return
	 */
	public Object get(String atributo){

		StringTokenizer st = new StringTokenizer(atributo, ".");
		String nomeMetodo = null;
		// este atual servirá para os casos conjugados 
		Object atual = this;
		while (st.hasMoreElements()) {
			String at = (String) st.nextElement();
			at = at.substring(0, 1).toUpperCase() + at.substring(1, at.length());
			nomeMetodo = "get"+at;
			
			try {
				// invocando o metodo do objeto atual para pegar o retorno
				Method metodo = atual.getClass().getMethod(nomeMetodo, (Class[]) null);
				atual = metodo.invoke(atual,(Object[]) null);
				if (atual == null){				
					return null;
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// casos em que foi colocado um cmainho de atributos inexistente
				return null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		return atual;
	}

	public Class getTipoAtributo(String atributo){		
		StringTokenizer st = new StringTokenizer(atributo, ".");
		String nomeMetodo = null;
		// este atual servirá para os casos conjugados 
		Object atual = this;
		Class retorno = this.getClass();
		while (st.hasMoreElements()) {
			String at = (String) st.nextElement();
			at = at.substring(0, 1).toUpperCase() + at.substring(1, at.length());
			nomeMetodo = "get"+at;
			
			try {
				// invocando o metodo do objeto atual para pegar o retorno
				Method metodo = atual.getClass().getMethod(nomeMetodo, (Class[]) null);
				retorno = atual.getClass();
				atual = metodo.invoke(atual,(Object[]) null);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// casos em que foi colocado um cmainho de atributos inexistente
				atual = "";
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		return retorno;
	}

	/**
	 * Setar o valor de um atributo da classe
	 * @param atributo nome do atributo 
	 * @param tipo tipo do atributo
	 * @param valor valor a ser atribuido
	 */
	public void set(String atributo, Class tipo, Object valor){
		String nomeMetodo = null;
		atributo = atributo.substring(0, 1).toUpperCase() + atributo.substring(1, atributo.length());
		nomeMetodo = "set"+atributo;
		
		try {
			// invocando o metodo do objeto atual para pegar o retorno
			Class[] tipos = {tipo};
			Method metodo = this.getClass().getMethod(nomeMetodo, tipos);
			Object[] args = {valor};
			metodo.invoke(this,args);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna a coleção de atributos que serão usados como 
	 * na consulta de operação efetuada num resumo de dados  
	 * @return
	 */
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
			return null;		
	}
	
	/**
	 * Retorna a coleção de labels referentes aos atributos que serão 
	 * usados na consulta de operação efetuada no resumo de dados 
	 * @return
	 */
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
			return null;		
	}

	public Filtro retornaFiltroRegistroOperacao(){
		return retornaFiltroRegistroOperacao(retornaFiltro());
	}

	/**
	 * Retorna um filtro que será usado no registro da operacao
	 * A diferença deste filtro é que contem como itens de carregamento
	 * os campos que foram definidos para controle de alteracao
	 * 
	 * @param filtro
	 * @see gcom.interceptor.ControleAlteracao
	 * @return
	 */
	public Filtro retornaFiltroRegistroOperacao(Filtro filtro){
		Class classe = getClass();
		Annotation anot[] = classe.getAnnotations();
		if (temControleAlteracao(anot) != null){
			Field[] campos = classe.getDeclaredFields();
			for (int i = 0; i < campos.length; i++) {
				Annotation anotCampo = temControleAlteracao(campos[i].getAnnotations());
				if (anotCampo != null){					
					filtro.adicionarCaminhoParaCarregamentoEntidade(((ControleAlteracao)anotCampo).value());
				}
			}			
		}
		filtro.setInitializeLazy(true);
		return filtro;
	}
		
	/**
	 * Retorna a anotação do tipo ControleAlteracao contida no conjunto de 
	 * anotações que foi passado, caso exista. Caso não, retorna null.
	 * @author Francisco do Nascimento 
	 * @param anot
	 * @return Anotação do tipo ControleAlteracao ou null
	 */
	private Annotation temControleAlteracao(Annotation[] anot){
		if (anot != null){
			for (int i = 0; i < anot.length; i++) {
				if (anot[i] instanceof ControleAlteracao){
					return anot[i];
				}
			}			
		}
		return null;
	}
	
	/**
	 * verifica se esta classe contém alguma anotação do tipo ControleAlteracao
	 * @author Francisco do Nascimento
	 * @return Existe ou não
	 */
	public boolean temControleAlteracao(){
		return temControleAlteracao(this.getClass().getAnnotations()) != null;
	}

	/**
	 * Identificador utilizado para os casos de alteração de entidades, cujo procedimento
	 * utilizado foi a remoção(alguns casos nem remove) seguida de uma inclusão para operacionalizar uma alteração.
	 * Para efeito de registro de operação, era necessário entender como uma alteração
	 * Daí, este atributo irá guardar o id do objeto antigo no objeto novo.
	 * @author Francisco do Nascimento
	 */
	private Object idAntigo;
	
	public Object getIdAntigo() {
		return idAntigo;
	}

	public void setIdAntigo(Object idAntigo) {
		this.idAntigo = idAntigo;
	}

	/**
	 * Em algumas situações, ocorre erro de dar um get numa propriedade que não foi carregada
	 * com a sessão ativa do hibernate. Para solucionar isto, foi criado um atributo no filtro
	 * para determinar se, ao pesquisar algum objeto, deseja que sejam inicializadas as propriedades
	 * lazies. Caso esteja true este atributo no filtro, para cada objeto retornado da pesquisa, é chamado
	 * este método. 
	 * A implementação deste método deve conter chamadas aos get's das propriedades que estão lazies 
	 * (Collection e outros objetos do sistema)
	 * Na classe gcom.faturamento.conta.Conta está implementado.
	 *  
	 * @author Francisco do Nascimento
	 * @see gcom.util.filtro.Filtro
	 *
	 */
	public void initializeLazy(){
		
	}
	
	public void initilizarCollectionLazy(Collection colecao){
		if (colecao != null){
			for (Iterator iter = colecao.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof ObjetoTransacao){
					((ObjetoTransacao)element).initializeLazy();	
				}							
			}			
		}		
	}
	
	/**
	 * Este método define como será a descrição deste objeto no momento
	 * de registrar a transacao.
	 * Normalmente, há um getDescricao na classe, por isso, foi definido como 
	 * default o getDescricao. Caso seja diferente, deve ser sobreescrito na classe.
	 * @return String com a descricao do objeto
	 * @author Francisco Nascimento
	 */
	public String getDescricaoParaRegistroTransacao(){
		Object retorno =  get("descricao");
		if (retorno == null){
			retorno = "";
		}
		return (String) retorno; 
	}
	
}
