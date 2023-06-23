package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import model.dao.ConsoleDAO;
import model.dao.ConsoleDB;
import model.dao.VideogiocoDAO;
import model.dao.VideogiocoDB;

@WebServlet("/RicercaServlet")
public class RicercaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideogiocoDAO vDAO = new VideogiocoDB();
	private ConsoleDAO cDAO = new ConsoleDB();
	private ArrayList<String> listV = new ArrayList<String>();
	private ArrayList<String> listC = new ArrayList<String>(); 

	public RicercaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("input");
		ArrayList<String> result = dammiProdotti(input);
		response.setContentType("application/json");
		JSONObject json;
		JSONArray list = new JSONArray();
		if(result != null) {
			Iterator<String> iterator = result.iterator();

			while(iterator.hasNext()) {
				json = new JSONObject();
				json.accumulate("name", iterator.next());
				list.put(json);
			}

			System.out.println("lista: " +list.toString());
			response.getWriter().append(list.toString());	

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private ArrayList<String> dammiProdotti(String input){
		listC = cDAO.doRetrieveLikeModello(input);
		listV = vDAO.doRetrieveLike(input);
		
		if(listC==null && listV==null)
			return new ArrayList<String>();
		else if(listC==null && !listV.isEmpty())
		{
			return listV;
		}
		else if(listV==null && !listC.isEmpty())
		{
			return listC;
		}
		else
			return new ArrayList<String>();
	}

}
