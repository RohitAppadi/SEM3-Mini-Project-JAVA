#SEM3-Mini-Project-JAVA: Library Fine Calculator 
This is my 3rd Semester Mini project that can calculate the accumated fine of an issued book after the due date has been reached which is 1 week and the days the book has not been returned will be added with 1 rupee each day until the day the book has been returned.
The project has been kept simple with the basic Mysql Database connection and TOMCAT 8080 server, we programmed this using JAVA EE on Eclipse.
The project is set to Dynamic Web and JAVA EE perspective, and required .jar files have been dowloaded in the set paths.
Moving on we create a package com.library.fine and we store our class DBConnection, servlet FineCalculatorServlet.java.
We create a class DBConnection.java to establish the connection to our MYSql database and before we do this, its crucial to know that the program backend is the basic MYSql database. The reason why we chose MYSql is due to my profieciency in the CRUD structures and also keeping the program light weight since our stoarage of data is mainly structured strings.
We create a SERVLET file FineCalculatorServlet.java and the reason why we chose to create a servlet instead of a java file because the definition of a servlet explains that "servlet is basically a java class that runs on web servers like TOMCAT and can handle HTTP requests and resposes" which our project directly inclines to. A java file normally cannot accept request and send out HTTP requests and is maily used in static webpage.
After creating out Servlet we start with out index.html, our frontend for our webpage.
The flow is simple- Data is stored in MYSQL, the connection is established on DBConnection.java, the webserver logic and the main program that calculates the fine is the FineCalculatorServlet.java and our frontend is the index.html.
We start our TOMCAT server in the servers tab and we run the project on TOMCAT server.
#TechStack
##Backend
1.JAVA EE
2.Servlet
3.MYSql
##Frontend
1.HTML
##Servers
1.Apache TOMCAT 11
##Application
1.Eclipse IDE
