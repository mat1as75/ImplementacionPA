import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet("/SVPublicarLista")
public class SVPublicarLista extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PuvlicarLista</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PuvlicarLista at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Obtener el nickname desde la solicitud GET
    String nickname = request.getParameter("nickname");
    
    Fabrica f = Fabrica.getInstance();
    IControlador i = f.getControlador();

    // Simulamos la obtención de listas privadas basadas en el nickname
    List<String> listasPrivadas = i.listasCreadasEstadoPrivadoTrue(nickname);
    
    // Configurar la respuesta en formato HTML
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    // Enviar la respuesta como HTML (listas privadas)
    out.println("<ul>");
    for (String lista : listasPrivadas) {
        out.println("<li class='selectable' onclick='handlePrivateListSelection(\"" + lista + "\")'>" + lista + "</li>");
    }
    out.println("</ul>");

    out.close();
}
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nicknameCliente = request.getParameter("nickname"); 
        String listaPrivada = request.getParameter("listaPrivada");
        Fabrica f = Fabrica.getInstance();
        IControlador controlador = f.getControlador();
        
        if ((nicknameCliente != null) &&(listaPrivada != null)) {
            controlador.setPrivadafalse(nicknameCliente, listaPrivada);
                    // Establecer un mensaje
            String mensaje = nicknameCliente+" ahora no tiene lista privada "+listaPrivada;
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("PublicarLista.jsp").forward(request, response);

        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
