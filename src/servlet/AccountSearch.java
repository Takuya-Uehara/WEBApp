package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.DAOException;
import model.User;

@WebServlet("/AccountSearch")
public class AccountSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public AccountSearch() {
        super();

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		User user = new User();
		user.setId(id);
		user.setPass(pass);


	AccountDAO ad = new AccountDAO();
	User returnAb ;
	try {
		returnAb = ad.findAccount(user);

		if(returnAb != null) {
		HttpSession session = request.getSession();
		session.setAttribute("account",returnAb);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp");
		rd.forward(request,response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		}

	} catch (DAOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

	}

}
