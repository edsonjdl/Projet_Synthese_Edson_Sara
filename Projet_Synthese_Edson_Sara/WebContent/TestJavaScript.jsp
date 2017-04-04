<%-- 
    Document   : TestJavaScript
    Created on : 2017-03-10, 11:12:00
    Author     : 1695625
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
   </head>
    <body>
        <link rel="stylesheet" href="../amcharts/style.css"
              type="text/css">
        <script src="../amcharts/amcharts.js" type="text/javascript"></script>
        <script src="../amcharts/serial.js" type="text/javascript"></script>
        <script src="../amcharts/amstock.js" type="text/javascript"></script>
        
        

        <%--affichage(${info.ouverture}, ${info.max}, ${info.min}, ${info.fermeture}, ${info.volFin});
        affichage(1, 2, 3, 4, 5);
        ajouteDonnee(${info.ouverture}, ${info.max}, ${info.min}, ${info.fermeture}, ${info.volFin});
        afficherListe();
        
        <script type="text/javascript" src="Test.js"></script>
        
           <input type = "button" value = "Generer" onclick="generateChartData();"/>        
        <input type = "button" value = "Affichage" onclick="afficherListe();"/>
        --%>
        <script>
            AmCharts.ready(function () {
                document.writeln("Test ");
                generateChartData();
                createStockChart();
            });

            var chart;
            var chartData = [];
            //var chartData = {};
            var newPanel;
            var stockPanel;
            var j = 0;

            function generateChartData() {
                var firstDate = new Date();
                firstDate.setHours(0, 0, 0, 0);
                firstDate.setDate(firstDate.getDate() - 2000);

                //var values = {};
                
            <c:forEach var="item" items="${maListe}"  varStatus="loop">
                var newDate = new Date(firstDate);
                newDate.setDate(newDate.getDate() + ${loop.index});

                var open = ${item.ouverture};
                var close = '${item.fermeture}';
                var low = '${item.min}';
                var high = '${item.max}';
                var volume = '${item.volFin}';

                chartData[${loop.index}] = ({
                    date: newDate,
                    open: open,
                    close: close,
                    high: high,
                    low: low,
                    volume: volume
                });
                                
                document.writeln("Cotisation " + ": " + chartData[${loop.index}].open);
                j++;
            </c:forEach>
               
                }
            

        </script>
 
        
    </body>
</html>
