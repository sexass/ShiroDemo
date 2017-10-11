package demo.shiro.dao;


import demo.shiro.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * userDao
 *
 * @author 郭友文
 * @create 2017-10-11 15:21
 **/
public interface IUserDao extends JpaRepository<UserModel,Long> {
    @Query("from UserModel u where u.userName=:userName")
    UserModel findByUserName(@Param(value = "userName") String userName);
}
