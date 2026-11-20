/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
import java.util.List;
/**
 *
 * @author Angeline Tulabing
 */
public interface GroupDAO {
    public boolean addGroup(Group newGroup);
    public List<Group> getAllGroups();
    public List<Group> searchGroups(String keyword);
    public boolean updateGroup(String groupId, Group updatedGroup);
    public boolean deleteGroup(String groupId);
}
