package com.servelet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.SessionFactoryProvider;

/**
 * Servlet implementation class UpdateServelet
 */
public class UpdateServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int noteid=Integer.parseInt(request.getParameter("id").trim());
			String title=request.getParameter("title").trim();
			String content=request.getParameter("content").trim();
			Session session=SessionFactoryProvider.getSessionFactory().openSession();
			Transaction tx=session.beginTransaction();
			Note note=(Note)session.get(Note.class,noteid);
			note.setTitle(title);
			note.setContent(content);
			note.setAddedDate(new Date());
			tx.commit();
			session.close();
			response.sendRedirect("all_notes.jsp");
		} catch (Exception e) {
		
		}	
	}

}
