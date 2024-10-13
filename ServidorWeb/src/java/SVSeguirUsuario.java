import com.google.gson.Gson;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/SVSeguirUsuario"})
public class SVSeguirUsuario extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SVSeguirUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SVSeguirUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("application/json");
        String seguidor = request.getParameter("seguidor");

        Fabrica f = Fabrica.getInstance();
        IControlador i = f.getControlador();
        List<String> nicknamesUsuarios = i.getUsuariosSinEste(seguidor);

        // Convertimos la lista de usuarios a formato JSON
        Gson gson = new Gson();
        String json = gson.toJson(nicknamesUsuarios);

        // Enviamos la respuesta JSON al cliente
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el valor del combo box después de que se envíe el formulario
        String Seguidor = request.getParameter("Seguidor");
        String Seguido = request.getParameter("Seguido");
        if((Seguidor!=null)&&(!Seguidor.isEmpty())&&(Seguido!=null)&&(!Seguido.isEmpty())){
             Fabrica f = Fabrica.getInstance();
             IControlador i = f.getControlador();
             i.setSeguidorSeguido(Seguidor, Seguido);
        }
        if(Seguidor!=null){
            request.setAttribute("Seguidor", Seguidor);
            request.getRequestDispatcher("SeguirUsuario.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
