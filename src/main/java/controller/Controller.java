package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/Controller", "/header"} )
public class Controller extends HttpServlet {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The evento. */
	JavaBeans evento = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		//teste de recebimento de requisições
		//System.out.println(action);
		if (action.equals("/header")) {
			evento(request, response);
		} else if (action.equals("/header")) {
			adicionarEvento(request, response);
		} else if (action.equals("/header")) {
			listarEvento(request, response);
		} else if (action.equals("/header")) {
			editarEvento(request, response);
		} else if (action.equals("/header")) {
			removerEvento(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	/**
	 * Eventos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar evemtos
	protected void evento(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarEvento();
		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("evento", lista);
		RequestDispatcher rd = request.getRequestDispatcher("header.component.html");
		rd.forward(request, response);

		// teste de recebimento da lista
		// for (int i = 0; i < lista.size(); i++) {
		// System.out.println(lista.get(i).getIdev());
		// System.out.println(lista.get(i).getNome());
		// System.out.println(lista.get(i).getEndereco());
		// System.out.println(lista.get(i).getImagem());
		// }

	}

	/**
	 * Adicionar evento.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo evento
	protected void adicionarEvento(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de recebimento de dados do formulário
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("Endereco"));
		// System.out.println(request.getParameter("Imagem"));
		// setar as variaveis JavaBeans
		evento.setNome(request.getParameter("nome"));
		evento.setEndereco(request.getParameter("endereco"));
		evento.setImagem(request.getParameter("imagem"));
		// invocar o metodo inserirEvento passando o objeto evento
		dao.adicionarEvento(evento);
		// redirecionar para o documento dashboard.html
		response.sendRedirect("dashboard");
	}

	/**
	 * Listar evento.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar evento
	protected void listarEvento(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimendo do id do contato que sera editado
		String idev = request.getParameter("idev");
		// System.out.println(idev);

		// Setar a variavel JavaBeans
		evento.setIdev(idev);
		// Executar o método selecionarContato (DAO)
		dao.selecionarEvento(evento);
		// teste de recebimento
		// System.out.println(evento.getIdev());
		// System.out.println(evento.getNome());
		// System.out.println(evento.getEndereco());
		// System.out.println(evento.getImagem());

		// setar os atributos do formulario com o conteudo do JavaBeans
		request.setAttribute("idev", evento.getIdev());
		request.setAttribute("nome", evento.getNome());
		request.setAttribute("Endereco", evento.getEndereco());
		request.setAttribute("Imagem", evento.getImagem());
		// encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
		;
	}

	/**
	 * Editar evento.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarEvento(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de recebimento
		// System.out.println(request.getParameter("idev"));
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("endereco"));
		// System.out.println(request.getParameter("imagem"));

		// setar as variáveis JavaBeans
		evento.setIdev(request.getParameter("idev"));
		evento.setNome(request.getParameter("nome"));
		evento.setEndereco(request.getParameter("endereco"));
		evento.setImagem(request.getParameter("imagem"));
		// executar o método alterar evento
		dao.alterarEvento(evento);
		// redirecionar para o documento dashboard (atualizando as alteracoes)
		response.sendRedirect("dashboard");
	}

	/**
	 * Remover evento.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// remover um evento
	protected void removerEvento(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id do evento a ser excluido (validador.js)
		String idev = request.getParameter("idev");
		// teste de recebimento
		// System.out.println(idev);
		evento.setIdev(idev);
		// executar o método deletarEvento (DAO) passando o objeto evento
		dao.deletarEvento(evento);
		// redirecionar para o documento dashboard (atualizando as alteracoes)
		response.sendRedirect("dashboard");
	}

	
	

}
