<%@page import="webservices.DataTypes.DtDatosUsuarioSinPw"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="webservices.RankingService"%>
<%@page import="webservices.RankingServiceService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ranking de usuarios</title>
        
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/variablesGlobales.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/clasesAuxiliares.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/nav.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/rankingUsuarios.css"/>
    </head>
    
        <%
            RankingServiceService rankingWS = new RankingServiceService();
            RankingService rankingPort = rankingWS.getRankingServicePort();
            
            List<Object> listUsuariosObj = rankingPort.getUsuariosOrdenadosPorRanking(0).getColeccion();
            List<DtDatosUsuarioSinPw> listDataUsuarios = new ArrayList();
            for (Object o : listUsuariosObj) {
                listDataUsuarios.add((DtDatosUsuarioSinPw) o);
            }
            
        %>
    <body class="bodyContainer">
        <jsp:include page="../headerIndex.jsp"/>
        <%@ include file="../WEB-INF/jspf/Nav.jspf" %>

        <main>
            <h1 class="textAligned">Ranking de usuarios</h1>
            <table class="tableRankings">
                <tr class="tableRowHeaders">
                    <th>Usuario</th>
                    <th>Seguidores</th>
                    <th>Tipo de usuario</th>
                </tr>

                <% for (DtDatosUsuarioSinPw dtu : listDataUsuarios) { %>

                <tr class="tableRowData">
                    <td>
                        <a href="/ServidorWeb/SVConsultaPerfilUsuario?usuario-Consultar=<%= dtu.getNicknameUsuario() %>">
                            <%= dtu.getNicknameUsuario() %>
                            <i class="bi bi-box-arrow-up-right"></i>
                        </a>
                    </td>
                    <td><%= dtu.getNicknamesSeguidores().size() %></td>
                    <td><%= dtu.getTipoDeUsuario() %></td>
                </tr>

                <% } %>
            </table>
        </main>
    </body>
</html>
