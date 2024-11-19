package Servlets;

import eu.bitwalker.useragentutils.UserAgent;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import webservices.RegistroService;
import webservices.RegistroServiceService;

@WebFilter(filterName = "SVRegistroAccesoFiltro", urlPatterns = {"/*"})
public class SVRegistroAccesoFiltro implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public SVRegistroAccesoFiltro() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SVRegistroAccesoFiltro:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SVRegistroAccesoFiltro:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Convertir a HttpServletRequest para poder acceder a los metodos especificos
        HttpServletRequest httpRequest = (HttpServletRequest) request;   
        
        // Capturar la direccion IP
        String direccionIP = request.getRemoteAddr();
        if (httpRequest.getHeader("X-Forwarded-For") != null) {
            direccionIP = httpRequest.getHeader("X-Forwarded-For");
        }
        
        // Capturar la URL de la solicitud
        String url = httpRequest.getRequestURL().toString();
        
        // Capturar el User-Agent para identificar el navegador y el sistema operativo
        String userAgent = httpRequest.getHeader("User-Agent");
        String navegador = "Desconocido";
        String sistemaOperativo = "Desconocido";
        
        if (userAgent != null) {
            UserAgent agent = UserAgent.parseUserAgentString(userAgent);
            navegador = agent.getBrowser().getName();
            sistemaOperativo = agent.getOperatingSystem().getName();
        }
        
        // Capturar fecha actual
        Date fechaAcceso = new Date();
        
        // Almacenar los datos en la base de datos
        almacenarAcceso(direccionIP, url, navegador, sistemaOperativo, fechaAcceso);
        
        // Continuar con el procesamiento de la solicitud
        chain.doFilter(request, response);
    }
    
    private void almacenarAcceso(String direccionIP, String url, String navegador, String sistemaOperativo, Date fechaAcceso) {
        RegistroServiceService serviceR = new RegistroServiceService();
        RegistroService service = serviceR.getRegistroServicePort();
        
        try {
            service.guardarRegistroAcceso(
                    direccionIP, 
                    url, 
                    navegador, 
                    sistemaOperativo, 
                    fechaAcceso);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("SVRegistroAccesoFiltro:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SVRegistroAccesoFiltro()");
        }
        StringBuffer sb = new StringBuffer("SVRegistroAccesoFiltro(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
