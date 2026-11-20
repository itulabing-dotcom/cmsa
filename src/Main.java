/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Angeline Tulabing
 */
public class Main {
    public static void main(String[] args){
        FrmMain frmMain = new FrmMain();
        frmMain.setVisible(true);
        
        /**
        ContactDAOImpl contactDAOImpl = new ContactDAOImpl();
        
        // add
        Contact contact = new Contact("0920500118", "Fernando", "Enad");
        if(contactDAOImpl.addContact(contact) == true){
            System.out.println("Save success");
        } else {
            System.out.println("Save error");
        }
        
        // get all contacts
        for(int i=0; i<contactDAOImpl.getAllContacts().size(); i++){
            System.out.println(contactDAOImpl.getAllContacts().get(i).getPhoneNumber());
        }
        
        // search
        for(int i=0; i<contactDAOImpl.searchContacts("Fernan").size(); i++){
            System.out.println(contactDAOImpl.getAllContacts().get(i).getFirstname());
        }
        
        // update
        Contact updateContact = new Contact("0920500118", "Fernan", "Dane");
        if(contactDAOImpl.updateContact("09205001182",updateContact) == true){
            System.out.println("Update success");
        } else {
            System.out.println("Update error");
        }
        
        for(int i=0; i<contactDAOImpl.getAllContacts().size(); i++){
            System.out.println(contactDAOImpl.getAllContacts().get(i).getFirstname());
        }
        
        // delete
        if(contactDAOImpl.deleteContact("0920500118") == true){
            System.out.println("Delete success");
        } else {
            System.out.println("Delete error");
        }
        
        for(int i=0; i<contactDAOImpl.getAllContacts().size(); i++){
            System.out.println(contactDAOImpl.getAllContacts().get(i).getFirstname());
        }
        */
    }
}