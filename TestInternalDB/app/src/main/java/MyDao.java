import android.arch.persistence.room.Dao;

import com.example.bui.testinternaldb.User;

@Dao
public interface MyDao
{

    public void addUser(User user);

}
