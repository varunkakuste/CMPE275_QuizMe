<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawSeriesChart);

    function drawSeriesChart() {
      
      var data = google.visualization.arrayToDataTable([ 
                                        				['Quiz Name', 'Max Score', 'Quiz Count', 'User'],
                                                        <c:forEach items="${globalDashBoardList}" var="entry">
                                                            [ '${entry.quizName}', ${entry.maxScore}, ${entry.quizCount}, '${entry.userName}' ], 
                                                        </c:forEach> ['Sample', 20, 10, 'Sample User'] ]);

      var options = {
    	title: 'Statistics of popular quizzes and top users: ',
    	hAxis: {title: 'Max Score'},
        vAxis: {title: 'Quiz Count'},
        bubble: {textStyle: {fontSize: 11}}
      };

      var chart = new google.visualization.BubbleChart(document.getElementById('series_chart_div'));
      chart.draw(data, options);
    }
    </script>
  </head>
  <body>
    <div id="series_chart_div" style="width: 900px; height: 500px;"></div>
  </body>
</html>