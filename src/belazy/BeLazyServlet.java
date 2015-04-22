package belazy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;

@SuppressWarnings("serial")
public class BeLazyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String rankStr = req.getParameter("rank");
		String gpaTotalStr = req.getParameter("gpaTotal");
		String gpaSemesterStr = req.getParameter("gpaSemester");
		if (checkInput(rankStr, gpaTotalStr, gpaSemesterStr))
		{
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			Entity student = new Entity("Student");
			student.setProperty("rank", Integer.parseInt(rankStr));
			student.setProperty("gpaTotal", Double.parseDouble(gpaTotalStr));
			student.setProperty("gpaSemester", Double.parseDouble(gpaSemesterStr));
			datastore.put(student);
			resp.getWriter().println("Response recorded.");
		}
		else
		{
			resp.getWriter().println("Your input was invalid, please go back and try again.");
		}
	}
	
	public boolean checkInput(String rankStr, String gpaTotalStr, String gpaSemesterStr)
	{
		try {
			int rank = Integer.parseInt(rankStr);
			double gpaTotal = Double.parseDouble(gpaTotalStr);
			double gpaSemester = Double.parseDouble(gpaSemesterStr);
			if (rank < 1 || rank > 1400 || gpaTotal < 0 || gpaTotal > 5 || gpaSemester < 0 || gpaSemester > 5)
			{
				return false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	
}
