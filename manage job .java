package com.jobportal;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ManageJobsServlet")
public class ManageJobsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Database connection details
    private String url = "jdbc:mysql://localhost:3306/job_portal";
    private String username = "root";
    private String password = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        List<Job> jobList = new ArrayList<>();

        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect
            Connection con = DriverManager.getConnection(url, username, password);

            // Query
            String sql = "SELECT * FROM jobs ORDER BY id DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Loop through results
            while (rs.next()) {
                Job job = new Job();

                job.setId(rs.getInt("id"));
                job.setTitle(rs.getString("title"));
                job.setCompany(rs.getString("company"));
                job.setLocation(rs.getString("location"));
                job.setDescription(rs.getString("description"));
                job.setSkills(rs.getString("skills"));

                jobList.add(job);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Send list to JSP
        request.setAttribute("jobList", jobList);

        RequestDispatcher rd = request.getRequestDispatcher("manage-jobs.jsp");
        rd.forward(request, response);
    }
}
