package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MutterDAO;
import model.GetMutterListLogic;
import model.Mutter;
import model.User;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    String mutterNumber = request.getParameter("mutterNumber");
	    System.out.println(mutterNumber);

	    MutterDAO dao = new MutterDAO();
	    dao.delete(mutterNumber);

	 // つぶやきリストを取得して、リクエストスコープに保存
	    GetMutterListLogic getMutterListLogic =
	        new GetMutterListLogic();
	    List<Mutter> mutterList = getMutterListLogic.execute();

	    System.out.println("OK");
	    System.out.println(mutterList.size());

	    if(mutterList == null) {
	    	System.out.println("NULL");
	    }



	    request.setAttribute("mutterList", mutterList);

	    // ログインしているか確認するため
	    // セッションスコープからユーザー情報を取得
	    HttpSession session = request.getSession();
	    User loginUser = (User) session.getAttribute("account");

	    if (loginUser == null) { // ログインしていない
	    // リダイレクト
	      response.sendRedirect("/Web/login.jsp");
	    } else { // ログイン済み
	    // フォワード
	      RequestDispatcher dispatcher = request.
	          getRequestDispatcher("/WEB-INF/jsp/main.jsp");
	      dispatcher.forward(request, response);
	    }

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    String text = request.getParameter("edit");
	    String mutterNumber = request.getParameter("mutterNumber");
	    System.out.println(mutterNumber);
	    System.out.println(text);
	    if (text != null && text.length() != 0) {
	    MutterDAO dao = new MutterDAO();
	    dao.edit(mutterNumber,text);
	    }
	 // つぶやきリストを取得して、リクエストスコープに保存
	    GetMutterListLogic getMutterListLogic =
	        new GetMutterListLogic();
	    List<Mutter> mutterList = getMutterListLogic.execute();

	    System.out.println("OK");
	    System.out.println(mutterList.size());

	    if(mutterList == null) {
	    	System.out.println("NULL");
	    }



	    request.setAttribute("mutterList", mutterList);

	    // ログインしているか確認するため
	    // セッションスコープからユーザー情報を取得
	    HttpSession session = request.getSession();
	    User loginUser = (User) session.getAttribute("account");

	    if (loginUser == null) { // ログインしていない
	    // リダイレクト
	      response.sendRedirect("/Web/login.jsp");
	    } else { // ログイン済み
	    // フォワード
	      RequestDispatcher dispatcher = request.
	          getRequestDispatcher("/WEB-INF/jsp/main.jsp");
	      dispatcher.forward(request, response);
	    }
	}

}
