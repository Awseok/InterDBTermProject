import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerManager
 */
@WebServlet("/ServerManager?opcode=FOODMATERIAL")
public class ServerManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context; 
	private RequestDispatcher dispatcher;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerManager() {
        super();
        // TODO Auto-generated constructor stub
        initS();
    }

    private void initS()
    {
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String opcode = request.getParameter("opcode");
		
		if(opcode==null || opcode.length() <= 0)
			System.out.println("null");
		try{
			OperationCode.valueOf(opcode);
		} catch (IllegalArgumentException e){
		}
		
		int opcodeInt = OperationCode.valueOf(opcode).ordinal();
		
    	context = this.getServletContext();  	
		switch(opcodeInt)
		{
		case 0:
			break;
		case 1:
			break;
		case 2:
			dispatcher = context.getRequestDispatcher("/FoodMatController?opcode=search");
			dispatcher.forward(request, response);
			break;
		case 3:
			dispatcher = context.getRequestDispatcher("/FoodMatController");
			dispatcher.forward(request, response);
			break;
		case 4:
			break;
		case 5:
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
