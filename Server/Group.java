package Server;

import java.io.IOException;
import java.util.ArrayList;

public class Group {
    public String name;
    private ArrayList<ServerUser> users = null;

    public Group(String name) {
        users = new ArrayList<>();
        this.name = name;
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
}
