package Server;

import java.io.IOException;
import java.util.ArrayList;

public class Group {
    public String name;
    private Integer hash;
    private User owner = null;
    private ArrayList<ServerUser> users = null;

    public Group(String name, User user) {
        users = new ArrayList<>();
        this.name = name;
        this.hash = name.hashCode();
        this.owner = user;
    }

    public String getOwnerName() {
        return owner.username;
    }

    public void addUserCon(ServerUser u) {
        users.add(u);
    }

    public void removeUserCon(ServerUser u) {
        users.remove(u);
    }

    public void sendMsgToAll(String msg) {
        for (ServerUser user : users) {
            user.sendMsg(msg);
        }
    }

    public void closeGroup() {
        for (ServerUser user : users) {
            Server.removeUserFromGroup(user);
        }
    }

    @Override
    public String toString() {
        return name + ":" + owner.username;
    }
}
