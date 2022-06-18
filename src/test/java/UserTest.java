
import com.application.dao.UserDao;
import com.application.entity.User;
import com.application.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Author chenLiang
 * @Date 2022/5/30 11:14
 */

public class UserTest {

   @Test
    public void test(){
       SqlSession session = MybatisUtil.getSqlSession();
       User user = session.getMapper(UserDao.class).selectByPrimaryKey(1);
       System.out.println(user);
       session.close();
   }
}
