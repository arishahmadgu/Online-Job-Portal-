package com.jobportal.servlet;

import com.jobportal.dao.JobDAO;
import com.jobportal.model.Job;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class PostJobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String title = request.getParameter("title");
        String company = request.getParameter("company");
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String skills = request.getParameter("skills");

        Job job = new Job(title, company, location, description, skills);
        JobDAO dao = new JobDAO();

        if (dao.addJob(job)) {
            response.sendRedirect("manage-jobs.html");
        } else {
            response.sendRedirect("post-job.html?error=1");
        }
    }
}
