package edu.sdsu.cs645.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.*;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("notepad")
public interface NotepadService extends RemoteService {
    String notepadServer(String name) throws IllegalArgumentException;
    String validateLogin(String s) throws IllegalArgumentException;
    String save(String s) throws IllegalArgumentException;
    String load() throws IllegalArgumentException;
    TreeMap loadRbTree() throws IllegalArgumentException;
}
