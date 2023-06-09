package com.servelet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.SessionFactoryProvider;

/**
 * Servlet implementation class DeleteServelet
 */
public class DeleteServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public DeleteServelet() {
        super();
    }
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int noteid=Integer.parseInt(request.getParameter("id").trim());
			Session session=SessionFactoryProvider.getSessionFactory().openSession();
			Transaction tx=session.beginTransaction();
			Note note=(Note)session.get(Note.class,noteid);
			session.remove(note);
			tx.commit();
			session.close();
			response.sendRedirect("all_notes.jsp");
		} catch (Exception e) {
		
		}
	}

}
