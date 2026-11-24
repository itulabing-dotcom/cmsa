/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Angeline Tulabing
 */
public class ContactDAOImpl implements ContactDAO{
    private final List<Contact> contacts = new ArrayList<>();
    private static final Path DATA_FILE = Paths.get("db.dat");
    
    public ContactDAOImpl(){
        loadFromFile();
    }
    
    public boolean loadFromFile(){
        if(Files.exists(DATA_FILE)){
            try{
                ObjectInputStream in = new ObjectInputStream(Files.newInputStream(DATA_FILE));
                Object stored = in.readObject();
                
                if(!(stored instanceof ArrayList<?>)){
                    return false;
                }
                
                ArrayList<?> loaded = (ArrayList<?>) stored;
                
                for(int i=0; i<loaded.size(); i++){
                    if(!(loaded.get(i) instanceof Contact)){
                        return false;
                    }
                }
                
                this.contacts.clear();
                
                for (int i=0; i<loaded.size(); i++){
                    this.contacts.add((Contact) loaded.get(i));
                }
                
                return true;
            } catch (Exception e){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean saveToFile(){
        Path temp = DATA_FILE.resolveSibling("db.dat.tmp");
        
        try{
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(temp));
            out.writeObject(this.contacts);
            
            Files.move(temp, DATA_FILE, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean addContact(Contact newContact){
        int index = getIndex(newContact.getPhoneNumber());
        
        if(index == -1){
            this.contacts.add(newContact);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }
    
    public List<Contact> getAllContacts(){
        return this.contacts;
    }
    
    public List<Contact> searchContacts(String keyword){
        List<Contact> results = new ArrayList<>();
        String search = keyword.toLowerCase();
        
        for(int i=0; i<this.contacts.size(); i++){
            if(this.contacts.get(i).getPhoneNumber().toLowerCase().contains(search) || 
                    this.contacts.get(i).getFirstname().toLowerCase().contains(search) || 
                    this.contacts.get(i).getLastname().toLowerCase().contains(search) ||
                    this.contacts.get(i).getGroupId().toLowerCase().contains(search)){
                results.add(this.contacts.get(i));
            }
        }
        
        return results;
    }
    
    public boolean updateContact(String phoneNumber, Contact updatedContact){
        int index = getIndex(updatedContact.getPhoneNumber());
        
        if(index > -1){
            this.contacts.get(index).setFirstname(updatedContact.getFirstname());
            this.contacts.get(index).setLastname(updatedContact.getLastname());
            this.contacts.get(index).setGroupId(updatedContact.getGroupId());
            saveToFile();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean deleteContact(String phoneNumber){
        int index = getIndex(phoneNumber);
        
        if(index > -1){
            this.contacts.remove(index);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }
    
    public int getIndex(String phoneNumber){
        for(int i=0; i<this.contacts.size(); i++){
            if(this.contacts.get(i).getPhoneNumber().equals(phoneNumber)){
                return i;
            }
        }
        
        return -1;
    }
}