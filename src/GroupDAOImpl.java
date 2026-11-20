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
public class GroupDAOImpl implements GroupDAO{
    private final List<Group> groups = new ArrayList<>();
    private static final Path DATA_FILE = Paths.get("dbg.dat");
    
    public GroupDAOImpl(){
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
                    if(!(loaded.get(i) instanceof Group)){
                        return false;
                    }
                }
                
                this.groups.clear();
                
                for (int i=0; i<loaded.size(); i++){
                    this.groups.add((Group) loaded.get(i));
                }
                
                return true;
            } catch (Exception e){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean saveToFile(){
        Path temp = DATA_FILE.resolveSibling("dbg.dat.tmp");
        
        try{
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(temp));
            out.writeObject(this.groups);
            
            Files.move(temp, DATA_FILE, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean addGroup(Group newGroup){
        int index = getIndex(newGroup.getGroupId());
        
        if(index == -1){
            this.groups.add(newGroup);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }
    
    public List<Group> getAllGroups(){
        return this.groups;
    }
    
    public List<Group> searchGroups(String keyword){
        List<Group> results = new ArrayList<>();
        String search = keyword.toLowerCase();
        
        for(int i=0; i<this.groups.size(); i++){
            if(this.groups.get(i).getGroupId().toLowerCase().contains(search) || 
                    this.groups.get(i).getGroupName().toLowerCase().contains(search)) {
                results.add(this.groups.get(i));
            }
        }
        
        return results;
    }
    
    public boolean updateGroup(String phoneNumber, Group updatedGroup){
        int index = getIndex(updatedGroup.getGroupId());
        
        if(index > -1){
            this.groups.get(index).setGroupName(updatedGroup.getGroupName());
            saveToFile();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean deleteGroup(String groupId){
        int index = getIndex(groupId);
        
        if(index > -1){
            this.groups.remove(index);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }
    
    public int getIndex(String groupId){
        for(int i=0; i<this.groups.size(); i++){
            if(this.groups.get(i).getGroupId().equals(groupId)){
                return i;
            }
        }
        
        return -1;
    }
}