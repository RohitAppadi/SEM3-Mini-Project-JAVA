package com.library.fine;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@WebServlet("/FineCalculatorServlet")
public class FineCalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String bookSerial = request.getParameter("serialNo");

        if (bookSerial == null || bookSerial.trim().isEmpty()) {
            out.println("<h3>Please enter a valid Book Serial Number!</h3>");
            return;
        }
        String query = "select issue_date, issued_by, return_date FROM books WHERE book_serial_no = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, bookSerial);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String issuedBy = rs.getString("issued_by");
                Date issueDt = rs.getDate("issue_date");
                Date returnDt = rs.getDate("return_date");
                LocalDate issueDate = issueDt.toLocalDate();
                LocalDate returnDate = returnDt.toLocalDate();
                LocalDate currentDate = LocalDate.now();
                long daysUsed = ChronoUnit.DAYS.between(issueDate, currentDate);
                long delayDays = ChronoUnit.DAYS.between(returnDate, currentDate);
                double fine = 0;
                if (delayDays > 0) {
                    fine = delayDays * 1.0; // ₹1 per day late
                }
                out.println("<div style='font-family:Verdana; text-align:center; margin-top:40px;'>");
                out.println("<h2>Library Fine Details</h2>");
                out.println("<p><b>Issued By:</b> " + issuedBy + "</p>");
                out.println("<p><b>Total Days Since Issue:</b> " + daysUsed + " days</p>");
                out.println("<p><b>Due Date:</b> " + returnDate + "</p>");
                out.println("<h3>Your Fine: ₹" + fine + "</h3>");
                if (fine == 0) {
                    out.println("<p style='color:green;'>Returned on time! No fine applied.</p>");
                } else {
                    out.println("<p style='color:red;'>You are late by " + delayDays + " days.</p>");
                }
                out.println("</div>");

            } else {
                out.println("<h3>No book found with Serial Number: " + bookSerial + "</h3>");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            out.println("<h3>Error connecting to Database:</h3>");
            out.println("<pre>" + e.getMessage() + "</pre>");
        }
    }
}
