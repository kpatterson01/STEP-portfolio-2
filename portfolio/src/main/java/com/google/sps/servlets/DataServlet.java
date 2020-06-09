// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.sps.servlets;

import com.google.sps.data.CommentData;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Servlet that returns comment data from DatastoreService*/
@WebServlet("/comment")
public class DataServlet extends HttpServlet {
  
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Query query = new Query("CommentData").addSort("timestamp", SortDirection.DESCENDING);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery results = datastore.prepare(query);

        List<CommentData> commentData = new ArrayList<>();
        
        for (Entity entity : results.asIterable()) {
            String firstName = (String) entity.getProperty("firstName");
            String lastName = (String) entity.getProperty("lastName");
            String years = (String) entity.getProperty("years");
            String relation = (String) entity.getProperty("relation");
            String commentText = (String) entity.getProperty("commentText");
            long timestamp = (long) entity.getProperty("timestamp");
        
            CommentData comment = new CommentData(firstName, lastName, years, relation, commentText, timestamp);
            commentData.add(comment); 
        }

        Gson gson = new Gson();

        response.setContentType("application/json;");
        response.getWriter().println(gson.toJson(commentData));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String years = request.getParameter("years");
        String relation = request.getParameter("relation");
        String commentText = request.getParameter("comment-input");
        long timestamp = System.currentTimeMillis();

        Entity taskEntity = new Entity("CommentData");
        taskEntity.setProperty("firstName", firstName);
        taskEntity.setProperty("lastName", lastName);
        taskEntity.setProperty("years", years);
        taskEntity.setProperty("relation", relation);
        taskEntity.setProperty("commentText", commentText);
        taskEntity.setProperty("timestamp", timestamp);

        //Adds data to DataService allowing data to be accessed after server stops
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(taskEntity);

        // Redirect back to the HTML page.
        response.sendRedirect("/comments.html");
    }
}