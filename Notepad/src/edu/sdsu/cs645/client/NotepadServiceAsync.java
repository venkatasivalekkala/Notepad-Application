package edu.sdsu.cs645.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.*;

/**
 * The async counterpart of <code>NotepadService</code>.
 */
public interface NotepadServiceAsync {
  void notepadServer(String input, AsyncCallback<String> callback)
      throws IllegalArgumentException;
    
    void validateLogin(String s, AsyncCallback<String> callback)
    throws IllegalArgumentException;

    void save(String s, AsyncCallback<String> callback)
    throws IllegalArgumentException;

    void load(AsyncCallback<String> callback)
    throws IllegalArgumentException;
    
    
    void loadRbTree(AsyncCallback<TreeMap> callback)
    throws IllegalArgumentException;
}
