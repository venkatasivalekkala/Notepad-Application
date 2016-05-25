package edu.sdsu.cs645.server;

import edu.sdsu.cs645.client.NotepadService;
import edu.sdsu.cs645.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.io.*;
import java.util.*;


/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class NotepadServiceImpl extends RemoteServiceServlet implements
    NotepadService {

  public String notepadServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid. 
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);

    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   * 
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
        }
        public String validateLogin(String s) throws IllegalArgumentException {
 
        if(s.trim().equals("sp2016")) return "OK";
        else return "NOT OK";
        }
        
public synchronized String save(String s)
        throws IllegalArgumentException {
            
            String path = getServletContext().getRealPath("/");
            String filename = path + "/data.txt";
            try {
                PrintWriter out = new PrintWriter(
                    new FileWriter(filename));
                    s = s.replaceAll("\r\n|\n","<br />");
                    out.print(s);
                    out.close();
                    
                }
                catch(Exception e) {
                    return "Sorry , error " + e;
                    
                }
                return "SUCCESS";
            
        }
        
        public String load() {
            String path = getServletContext().getRealPath("/");
            String filename = path + "/data.txt";
            String  answer = "";
            try {
                BufferedReader in = new BufferedReader(
                    new FileReader(filename));
                String line;
                while ((line = in.readLine()) != null)
                    answer += line;
                in.close();
                
            }
            catch(Exception e) {
                return "Sorry , error " + e;
                
            }
            return answer;

        }
        public TreeMap loadRbTree() {
            TreeMap t= new TreeMap();
            t.put ("Uno", "One");
            t.put("Dos", "Two");
            return t ;
        }
}
