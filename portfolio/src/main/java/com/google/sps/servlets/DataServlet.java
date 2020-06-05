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
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Servlet that returns a random comment data*/
@WebServlet("/comment")
public class DataServlet extends HttpServlet {

  //private ArrayList<String> comments;
  public CommentData comments = new CommentData();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
   /* comments = new ArrayList<>();
    comments.add("Comment number 1 from Server!");
    comments.add("Comment number 2 from server!");
    comments.add("Comment number 3 from server!");
    String json = convertToJsonUsingGson(comments);*/
    
    // Convert the server stats to JSON
    // Send the JSON as the response
    response.setContentType("application/json;");
    String json = new Gson().toJson(comments);
    response.getWriter().println(json);
  }

  /**
   * Converts a ServerStats instance into a JSON string using the Gson library. Note: We first added
   * the Gson library dependency to pom.xml.
   */
  /*private String convertToJsonUsingGson(ArrayList comments) {
    Gson gson = new Gson();
    String json = gson.toJson(comments);
    return json;
  }*/

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String firstName = request.getParameter("firstname");
    String lastName = request.getParameter("lastname");
    String years = request.getParameter("years");
    String relation = request.getParameter("relation");
    String comment = request.getParameter("comment-input");
    
    comments.logComments(firstName, lastName, years, relation, comment); 



    // Redirect back to the HTML page.
    response.sendRedirect("/comments.html");

    // Respond with the result.
    response.setContentType("text/html;");
    response.getWriter().println("Hello");
  }

  private String getComment(HttpServletRequest request) {
    // Get the input from the form.
    String userComment = request.getParameter("firstname");

    return userComment;
  }
}