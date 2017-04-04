<%-- 
    Document   : stockMultipleDataSets_3
    Created on : Mar 22, 2017, 7:03:42 PM
    Author     : edson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>amStock Example</title>
        <link rel="stylesheet" href="../amcharts/style.css"	type="text/css">

        <script src="../amcharts/amcharts.js" type="text/javascript"></script>
        <script src="../amcharts/serial.js" type="text/javascript"></script>
        <script src="../amcharts/amstock.js" type="text/javascript"></script>

        <script>
            AmCharts.ready(function () {
                generateChartData();
                createStockChart();
            });

            var chartData1 = [];
            var chartData2 = [];
            var chartData3 = [];
            var chartData4 = [];

            
            function generateChartData() {
            //    var firstDate = new Date();
            //    firstDate.setDate(firstDate.getDate() - 1000);
            //    firstDate.setHours(0, 0, 0, 0);
            
                var firstDate = new Date(2017, 2, 27);
                firstDate.setDate(firstDate.getDate() - 493);
                firstDate.setHours(0, 0, 0, 0);

            <c:forEach var="item" items="${maListe}"  varStatus="loop">
                var newDate = new Date(firstDate);
                newDate.setDate(newDate.getDate() + ${loop.index});

                var open = ${item.ouverture};
                var close = '${item.fermeture}';
                var low = '${item.min}';
                var high = '${item.max}';
                var volume = '${item.volFin}';
                var signal = '${item.signal}';

                chartData1[${loop.index}] = ({
                    date: newDate,
                    open: open,
                    close: close,
                    high: high,
                    low: low,
                    volume: volume,
                    signal: signal
                });

                var i = ${loop.index};
                //var a2 = Math.round(Math.random() * (100 + i)) + 200 + i;
                var a2 = ${item.mmRapide.valeur}; 
                //var b2 = Math.round(Math.random() * (1000 + i)) + 600 + i * 2;
                var b2 = '${item.volFin}';

                //var a3 = Math.round(Math.random() * (100 + i)) + 200;
                var a3 = ${item.mmLente.valeur};
                //var b3 = Math.round(Math.random() * (1000 + i)) + 600 + i * 2;
                var b3 = '${item.volFin}';

                var a4 = Math.round(Math.random() * (100 + i)) + 200 + i;
                //var b4 = Math.round(Math.random() * (100 + i)) + 600 + i;
                var b4 = '${item.volFin}';

                chartData2.push({
                    date: newDate,
                    value: a2,
                    volume: b2
                });
                chartData3.push({
                    date: newDate,
                    value: a3,
                    volume: b3
                });
                chartData4.push({
                    date: newDate,
                    value: a4,
                    volume: b4
                });
                
            </c:forEach>

                ///////////////////////////////////

            }


            function createStockChart() {
                var chart = new AmCharts.AmStockChart();

                // DATASETS //////////////////////////////////////////
                // create data sets first
                var dataSet1 = new AmCharts.DataSet();
                dataSet1.title = "Cotisations";
                dataSet1.fieldMappings = [{
                        fromField: "open",
                        toField: "open"
                    }, {
                        fromField: "close",
                        toField: "close"
                    }, {
                        fromField: "high",
                        toField: "high"
                    }, {
                        fromField: "low",
                        toField: "low"
                    }, {
                        fromField: "volume",
                        toField: "volume"
                    }, {
                        fromField: "value",
                        toField: "value"
                        }, {
                        fromField: "signal",
                        toField: "signal"
                    }];
                dataSet1.color = "#7f8da9";
                dataSet1.dataProvider = chartData1;
                dataSet1.categoryField = "date";

                var dataSet2 = new AmCharts.DataSet();
                dataSet2.title = "Moyenne Mobile Rapide";
                dataSet2.fieldMappings = [{
                        fromField: "value",
                        toField: "value"
                    }, {
                        fromField: "volume",
                        toField: "volume"
                    }];
                dataSet2.dataProvider = chartData2;
                dataSet2.categoryField = "date";

                var dataSet3 = new AmCharts.DataSet();
                dataSet3.title = "Moyenne Mobile Lente";
                dataSet3.fieldMappings = [{
                        fromField: "value",
                        toField: "value"
                    }, {
                        fromField: "volume",
                        toField: "volume"
                    }];
                dataSet3.dataProvider = chartData3;
                dataSet3.categoryField = "date";

                var dataSet4 = new AmCharts.DataSet();
                dataSet4.title = "fourth data set";
                dataSet4.fieldMappings = [{
                        fromField: "value",
                        toField: "value"
                    }, {
                        fromField: "volume",
                        toField: "volume"
                    }];
                dataSet4.dataProvider = chartData4;
                dataSet4.categoryField = "date";

                // set data sets to the chart
                chart.dataSets = [dataSet1, dataSet2, dataSet3, dataSet4];

                // PANELS ///////////////////////////////////////////
                // first stock panel
                var stockPanel1 = new AmCharts.StockPanel();
                stockPanel1.showCategoryAxis = false;
                stockPanel1.title = "Value";
                stockPanel1.percentHeight = 70;

                // graph of first stock panel
                var graph1 = new AmCharts.StockGraph();
                graph1.valueField = "value";
                graph1.comparable = true;
                graph1.compareField = "value";
                //graph1.bullet = "round";
                graph1.bullet = "none";
                graph1.bulletBorderColor = "#FFFFFF";
                graph1.bulletBorderAlpha = 1;
                //graph1.balloonText = "[[title]]:<b>[[value]]</b>";
                graph1.compareGraphBalloonText = "[[title]]:<b>[[value]]</b>";
                //graph1.compareGraphBullet = "round";
                graph1.compareGraphBullet = "none";
                graph1.compareGraphType = "smoothedLine";
                graph1.compareGraphBulletBorderColor = "#FFFFFF";
                graph1.compareGraphBulletBorderAlpha = 1;
                /////////////////////////////////////
                graph1.type = "candlestick";
                graph1.openField = "open";
                graph1.closeField = "close";
                graph1.highField = "high";
                graph1.lowField = "low";
                graph1.valueField = "close";
                graph1.lineColor = "#7f8da9";
                graph1.fillColors = "#7f8da9";
                graph1.negativeLineColor = "#db4c3c";
                graph1.negativeFillColors = "#db4c3c";
                graph1.fillAlphas = 1;
                graph1.balloonText = "open:<b>[[open]]</b><br>close:<b>[[close]]</b><br>low:<b>[[low]]</b><br>high:<b>[[high]]</b><br>signal:<b>[[signal]]</b>";
                graph1.useDataSetColors = false;
                //graph1.proCandlesticks = true;



                stockPanel1.addStockGraph(graph1);

                // create stock legend
                var stockLegend1 = new AmCharts.StockLegend();
                stockLegend1.periodValueTextComparing = "[[percents.value.close]]%";
                stockLegend1.periodValueTextRegular = "[[value.close]]";
                stockPanel1.stockLegend = stockLegend1;


                // second stock panel
                var stockPanel2 = new AmCharts.StockPanel();
                stockPanel2.title = "Volume";
                stockPanel2.percentHeight = 30;
                var graph2 = new AmCharts.StockGraph();
                graph2.valueField = "volume";
                graph2.type = "column";
                graph2.showBalloon = false;
                graph2.fillAlphas = 1;
                stockPanel2.addStockGraph(graph2);

                var stockLegend2 = new AmCharts.StockLegend();
                stockLegend2.periodValueTextRegular = "[[value.close]]";
                stockPanel2.stockLegend = stockLegend2;

                // set panels to the chart
                chart.panels = [stockPanel1, stockPanel2];


                // OTHER SETTINGS ////////////////////////////////////
                var sbsettings = new AmCharts.ChartScrollbarSettings();
                sbsettings.graph = graph1;
                sbsettings.updateOnReleaseOnly = false;
                chart.chartScrollbarSettings = sbsettings;

                // CURSOR
                var cursorSettings = new AmCharts.ChartCursorSettings();
                cursorSettings.valueBalloonsEnabled = true;
                chart.chartCursorSettings = cursorSettings;


                // PERIOD SELECTOR ///////////////////////////////////
                var periodSelector = new AmCharts.PeriodSelector();
                periodSelector.position = "left";
                periodSelector.hideOutOfScopePeriods=true;
                periodSelector.periods = [{
                        period: "DD",
                        count: 30,
                        label: "1 month"
                    }, {
                        period: "DD",
                        selected: true,
                        count: 90,
                        label: "3 months"
                    }, 
                    {
                        period: "YYYY",
                        count: 1,
                        label: "1 year"
                    }, {
                        period: "YTD",
                        label: "YTD"
                    }, {
                        period: "MAX",
                        label: "MAX"
                    }];
                chart.periodSelector = periodSelector;


                // DATA SET SELECTOR
                var dataSetSelector = new AmCharts.DataSetSelector();
                dataSetSelector.position = "left";
                chart.dataSetSelector = dataSetSelector;


                // EVENTS - Créer un type Event qui sera generé selon une condition (prototype)
                
                
                var e0 = {
                    date: new Date(2010, 8, 19),
                    type: "sign",
                    backgroundColor: "#85CDE6",
                    graph: graph1,
                    text: "S",
                    description: "This is description of an event"
                };
                var e1 = {
                    date: new Date(2010, 10, 19),
                    type: "flag",
                    backgroundColor: "#FFFFFF",
                    backgroundAlpha: 0.5,
                    graph: graph1,
                    text: "F",
                    description: "Some longer\ntext can also\n be added"
                };
                var e2 = {
                    date: new Date(2010, 11, 10),
                    showOnAxis: true,
                    backgroundColor: "#85CDE6",
                    type: "pin",
                    text: "X",
                    graph: graph1,
                    description: "This is description of an event"
                };
                var e3 = {
                    date: new Date(2010, 11, 26),
                    showOnAxis: true,
                    backgroundColor: "#85CDE6",
                    type: "pin",
                    text: "Z",
                    graph: graph1,
                    description: "This is description of an event"
                };
                var e4 = {
                    date: new Date(2011, 0, 3),
                    type: "sign",
                    backgroundColor: "#85CDE6",
                    graph: graph1,
                    text: "U",
                    description: "This is description of an event"
                };
                var e5 = {
                    date: new Date(2011, 1, 6),
                    type: "sign",
                    graph: graph1,
                    text: "D",
                    description: "This is description of an event"
                };
                var e6 = {
                    date: new Date(2011, 3, 5),
                    type: "sign",
                    graph: graph1,
                    text: "L",
                    description: "This is description of an event"
                };
                var e7 = {
                    date: new Date(2011, 3, 5),
                    type: "sign",
                    graph: graph1,
                    text: "R",
                    description: "This is description of an event"
                };
                var e8 = {
                    date: new Date(2011, 5, 15),
                    type: "arrowUp",
                    backgroundColor: "#00CC00",
                    graph: graph1,
                    description: "This is description of an event"
                };
                var e9 = {
                    date: new Date(2011, 6, 25),
                    type: "arrowDown",
                    backgroundColor: "#CC0000",
                    graph: graph1,
                    description: "This is description of an event"
                };
                var e10 = {
                    date: new Date(2011, 8, 1),
                    type: "text",
                    graph: graph1,
                    text: "Longer text can\nalso be displayed",
                    description: "This is description of an event"
                };
                        
            // dataSet1.stockEvents = [e0, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10];
                //var evenements = [e0, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10];
   
               var evenements = [];
               
               
var arrayLength = chartData1.length;

for (var i = 0; i < arrayLength; i++) {
    
    		    if(chartData1[i].signal === "achat"){                   
                    evenements.push({
                    date: chartData1[i].date,
                    type: "sign",
                    backgroundColor: "#00CC00",
                    graph: graph1,
                    text: "A",
                    description: "This is description of an event"
                    });
                } else if(chartData1[i].signal === "vente"){
                    
                    evenements.push({
                    date: chartData1[i].date,
                    type: "sign",
                    backgroundColor: "#CC0000",
                    graph: graph1,
                    text: "V",
                    description: "This is description of an event"
                });
            }
}



dataSet1.stockEvents = evenements;

                chart.write('chartdiv');
            }
        </script>
    </head>
    <body style="background-color:#FFFFFF">
        <div id="chartdiv" style="width:100%; height:600px;"></div>
    </body>

</html>
