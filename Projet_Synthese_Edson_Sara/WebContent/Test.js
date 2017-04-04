/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function testing() {
    if ("c" + "a" + "t" === "cat") {
        document.writeln("Same");
    } else {
        document.writeln("Not same");
    }
}
    
    function affichage(ouv, max, min, ferm, vol){
        document.writeln("ouv: " + ouv);
        document.writeln("max: " + max);
        document.writeln("min: " + min);
        document.writeln("ferm: " + ferm);
        document.writeln("vol: " + vol);
        
    }
    
    
                AmCharts.ready(function () {
                generateChartData();
                createStockChart();
            });

            var chart;
            var chartData = [];
            var newPanel;
            var stockPanel;
            var index = 0;
            
            function ajouteDonnee(ouv, max, min, ferm, vol){
                index++;
                
                      var open=ouv;
                    var close=ferm;
                    var low=min;
                    var high=max;
                    var volume=vol;
                    
                    chartData[index] = ({
                        //date: newDate,
                        open: open,
                        close: close,
                        high: high,
                        low: low,
                        volume: volume
                    });
                
            }
            
            
            function afficherListe(){
                
               for (var i = 0; i < 100; i++) {                  
                   document.writeln("Cotisation" + i + ": " + chartData[i].open);
               }
                
            }

            function generateChartData2(i, ouv, max, min, ferm, vol) {
                var firstDate = new Date();
                firstDate.setHours(0, 0, 0, 0);
                firstDate.setDate(firstDate.getDate() - 2000);
                    var newDate = new Date(firstDate);
                    newDate.setDate(newDate.getDate() + i);
                    
                    
                    var open=ouv;
                    var close=ferm;
                    var low=min;
                    var high=max;
                    var volume=vol;
                    
                    chartData[i] = ({
                        date: newDate,
                        open: open,
                        close: close,
                        high: high,
                        low: low,
                        volume: volume
                    });
                
            }
            
            //////////////////////////////////////////////////////////////
            


            function generateChartData() {
                
                           var chart;
            var chartData = [];
            var newPanel;
            var stockPanel;
                
                var firstDate = new Date();
                firstDate.setHours(0, 0, 0, 0);
                firstDate.setDate(firstDate.getDate() - 2000);

                for (var i = 0; i < 2000; i++) {
                    var newDate = new Date(firstDate);

                    newDate.setDate(newDate.getDate() + i);

                    var open = Math.round(Math.random() * (30) + 100);
                    var close = open + Math.round(Math.random() * (15) - Math.random() * 10);

                    var low;
                    if (open < close) {
                        low = open - Math.round(Math.random() * 5);
                    } else {
                        low = close - Math.round(Math.random() * 5);
                    }

                    var high;
                    if (open < close) {
                        high = close + Math.round(Math.random() * 5);
                    } else {
                        high = open + Math.round(Math.random() * 5);
                    }

                    var volume = Math.round(Math.random() * (1000 + i)) + 100 + i;
                    chartData[i] = ({
                        date: newDate,
                        open: open,
                        close: close,
                        high: high,
                        low: low,
                        volume: volume
                    });
                    document.writeln("Cotisation" + i + ": " + chartData[i].open);
                }
            }

            
            ////////////////////////////////////////////////////////////

            function createStockChart() {
                chart = new AmCharts.AmStockChart();

                chart.balloon.horizontalPadding = 13;

                // DATASET //////////////////////////////////////////
                var dataSet = new AmCharts.DataSet();
                dataSet.fieldMappings = [{
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
                    }];
                dataSet.color = "#7f8da9";
                dataSet.dataProvider = chartData;
                dataSet.categoryField = "date";

                chart.dataSets = [dataSet];

                // PANELS ///////////////////////////////////////////
                stockPanel = new AmCharts.StockPanel();
                stockPanel.title = "Value";

                // graph of first stock panel
                var graph = new AmCharts.StockGraph();
                graph.type = "candlestick";
                graph.openField = "open";
                graph.closeField = "close";
                graph.highField = "high";
                graph.lowField = "low";
                graph.valueField = "close";
                graph.lineColor = "#7f8da9";
                graph.fillColors = "#7f8da9";
                graph.negativeLineColor = "#db4c3c";
                graph.negativeFillColors = "#db4c3c";
                graph.fillAlphas = 1;
                graph.balloonText = "open:<b>[[open]]</b><br>close:<b>[[close]]</b><br>low:<b>[[low]]</b><br>high:<b>[[high]]</b>";
                graph.useDataSetColors = false;
                stockPanel.addStockGraph(graph);

                var stockLegend = new AmCharts.StockLegend();
                stockLegend.markerType = "none";
                stockLegend.markerSize = 0;
                stockLegend.valueTextRegular = undefined;
                stockLegend.valueWidth = 250;
                stockPanel.stockLegend = stockLegend;

                chart.panels = [stockPanel];


                // OTHER SETTINGS ////////////////////////////////////
                var sbsettings = new AmCharts.ChartScrollbarSettings();
                sbsettings.graph = graph;
                sbsettings.graphType = "line";
                sbsettings.usePeriod = "WW";
                chart.chartScrollbarSettings = sbsettings;

                // Enable pan events
                var panelsSettings = new AmCharts.PanelsSettings();
                panelsSettings.panEventsEnabled = true;
                chart.panelsSettings = panelsSettings;

                // CURSOR
                var cursorSettings = new AmCharts.ChartCursorSettings();
                cursorSettings.valueBalloonsEnabled = true;
                cursorSettings.fullWidth = true;
                cursorSettings.cursorAlpha = 0.1;
                chart.chartCursorSettings = cursorSettings;

                // PERIOD SELECTOR ///////////////////////////////////
                var periodSelector = new AmCharts.PeriodSelector();
                periodSelector.position = "bottom";
                periodSelector.periods = [{
                        period: "DD",
                        count: 10,
                        label: "10 days"
                    }, {
                        period: "MM",
                        selected: true,
                        count: 1,
                        label: "1 month"
                    }, {
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


                chart.write('chartdiv');
            }



            function addPanel() {
                newPanel = new AmCharts.StockPanel();
                newPanel.allowTurningOff = true;
                newPanel.title = "Volume";
                newPanel.showCategoryAxis = false;

                var graph = new AmCharts.StockGraph();
                graph.valueField = "volume";
                graph.fillAlphas = 0.15;
                newPanel.addStockGraph(graph);

                var legend = new AmCharts.StockLegend();
                legend.markerType = "none";
                legend.markerSize = 0;
                newPanel.stockLegend = legend;

                chart.addPanelAt(newPanel, 1);
                chart.validateNow();

                document.getElementById("addPanelButton").disabled = true;
                document.getElementById("removePanelButton").disabled = false;
            }

            function removePanel() {
                chart.removePanel(newPanel);
                chart.validateNow();

                document.getElementById("addPanelButton").disabled = false;
                document.getElementById("removePanelButton").disabled = true;
            }

