package trash;

import model.Group;

import java.util.ArrayList;

public class HasGroups {

    private ArrayList<Group> groups;

    public HasGroups() {
        this.groups = new ArrayList<Group>();
    }

    public void setGroups(ArrayList<String> groupNames) {

        for (int i = 0; i < groupNames.size(); i++) {
            String currentName = groupNames.get(i);
            Group g = new Group(currentName);
            groups.add(g);
        }
    }

    public ArrayList<String> getGroupsAsStrings() {
        ArrayList<String> result = new ArrayList<>();

        for (Group g: groups) {
            result.add(g.getName());
        }

        return result;
    }


    public ArrayList<Group> getGroups() {
        return groups;
    }

}
