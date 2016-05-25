package edu.sdsu.cs645.client;

import edu.sdsu.cs645.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import java.util.*;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Notepad implements EntryPoint {
  private Label status;
    private RichTextArea notepad;
    
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  
  private final NotepadServiceAsync notepadService = GWT.create(NotepadService.class);

 
  public void onModuleLoad() {
      status = new Label();
      status.getElement().setId("status");
      FlowPanel loginPanel = addLoginPanel();
      RootPanel.get().add(new HTML("<h1>Online Notepad</h1>"));
      RootPanel.get().add(loginPanel);
      RootPanel.get ().add(status);
     }
    
    private FlowPanel addLoginPanel(){
        final Label passwordLabel = new Label("Password:");
        final PasswordTextBox password = new PasswordTextBox();
        
        final Button loginButton = new Button("Login");
        final Button clearButton = new Button("Clear");
        clearButton.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent e){
            password.setText("");
            }
        });
        
        loginButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent e) {
                validateLogin(password.getText());
            }
        });

        FlowPanel mainPanel = new FlowPanel();
        FlowPanel buttonPanel = new FlowPanel();
        
        mainPanel.add(passwordLabel);
        mainPanel.add(password);
        mainPanel.getElement().setId("main");
        
        buttonPanel.add(clearButton);
        buttonPanel.add(loginButton);
        buttonPanel.setStyleName("buttonPanel");
        
        mainPanel.add(buttonPanel);
        return mainPanel;
    }
    private void buildNotepadPanel() {
        FlowPanel main = new FlowPanel ();
        main.add(new HTML("<h1>Online Notepad</h1>"));
        main.add(new HTML("<h2> Write Your Notes Here </h2>"));
        main.getElement().setId("padMain");
        notepad = new RichTextArea();
        notepad.getElement().setId("pad");
        
        main.add(notepad);
        main.add(makeButtonPanel());
        RootPanel.get().clear();
        RootPanel.get().add(main);
        RootPanel.get().add(status);
        //load panel from server
    }
    
   
    private void validateLogin (String password) {
        AsyncCallback callback = new  AsyncCallback () {
            public void onSuccess (Object result) {
                String answer = (String) result;
                if(answer.equals("OK")) {
                    status.setText("OK");
                    buildNotepadPanel();
                    load();
                                    }
                else
                    status.setText("Invalid Password");
                
            }
            public void onFailure(Throwable err) {
                status.setText("Failed " + err);
                err.printStackTrace();
            
            }
        };
        notepadService.validateLogin(password, callback);
    }
   
    private void load () {
        AsyncCallback callback = new AsyncCallback () {
            public void onSuccess(Object result) {
                notepad.setHTML((String) result);
                status.setText("Data Loaded!");
            }
            public void onFailure(Throwable err) {
                status.setText("Error, could not retrieve date");
            }
        };
        notepadService.load(callback);
        
    }
    
    private void save (String contents) {
        AsyncCallback callback = new  AsyncCallback () {
            public void onSuccess (Object result) {
                String answer = (String) result;
                if(answer.equals("SUCCESS")) {
                    status.setText("Data Saved!");
                    
                }
                else
                    status.setText("ERROR, data not saved");
                
            }
            public void onFailure(Throwable err) {
                status.setText("Failed " + err);
                err.printStackTrace();
                
            }
        };
        notepadService.save(contents, callback);
    }
    
    private FlowPanel makeButtonPanel() {
        final Button load = new Button("Load");
        final Button save = new Button ("Save");
        final Button clear = new Button ("Clear");
       

        
        
        clear.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent e) {
                notepad.setHTML("");
            }
        });
        
        save.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent e) {
                save(notepad.getHTML());
                
            }
        });
        
        load.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent e) {
                load();
                
            }
        });
        
        
        FlowPanel buttonPanel = new FlowPanel();
        buttonPanel.add(clear);
        buttonPanel.add(load);
        buttonPanel.add(save);
       
        
        return buttonPanel;
        
    }
    private native void doAlert(String s)
    /*-{ alert(s); }-*/;
    
}
