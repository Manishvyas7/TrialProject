package TrialProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GeneralCheckUpServlet")
public class GeneralCheckUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String mail = request.getParameter("email");
		String date =  request.getParameter("dob");
		String time = request.getParameter("time");
		String phone = request.getParameter("phone");
		String add =  request.getParameter("address");
		String comm = request.getParameter("comments");
		RequestDispatcher dispatcher = null;
		Connection connect = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3309/acnovatetraining?useSSL=false","root","Manish@2004");
			PreparedStatement pst = connect.prepareStatement("insert into patients(Name,EmailId,MobileNo,MeetingDate,meeting_time,Symptoms)values(?,?,?,?,?,?)");
			pst.setString(1,name);
			pst.setString(2,mail);
			pst.setString(3,phone);
			pst.setString(4,date);
			pst.setString(5,time);
			pst.setString(6,comm);
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if (rowCount > 0) {
				request.setAttribute ("status","succcess");
			}
			request.setAttribute ("status","failed");
		dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}