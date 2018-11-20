import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.bui.testinternaldb.User;

@Dao
public interface MyDao
{
    @Insert
    public void addUser(User user);

}
