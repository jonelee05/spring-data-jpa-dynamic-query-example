package mytest;

import com.alibaba.fastjson.JSONArray;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lq on 2018/3/6.
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class test {
    @Autowired
    private UserRepository repo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    @Before
    public void init() throws ParseException {
        User userJohn = new User();
        userJohn.setFirstName("Jon");
        userJohn.setLastName("King");
        userJohn.setEmail("john@king.com");
        userJohn.setAge(21);
        userJohn.setCreateTime(dateFormat.parse("2018-03-07 15:00:00"));
        repo.save(userJohn);

        User userTom = new User();
        userTom.setFirstName("Tomi");
        userTom.setLastName("King");
        userTom.setEmail("tom@king.com");
        userTom.setAge(22);
        userTom.setCreateTime(dateFormat.parse("2018-03-07 16:00:00"));
        repo.save(userTom);

        User userJone = new User();
        userJone.setFirstName("Jone");
        userJone.setLastName("Lee");
        userJone.setEmail("Jone@lee.com");
        userJone.setAge(23);
        userJone.setCreateTime(dateFormat.parse("2018-03-07 17:00:00"));
        repo.save(userJone);
    }

    @Test
    public void t() throws ParseException {
        List<User> users = repo.findAll();
        System.out.println("-----" + JSONArray.toJSONString(users));

        QUser user = QUser.user;
        BooleanExpression lastName = user.lastName.eq("King");
        System.out.println("-----" + JSONArray.toJSONString(repo.findAll(lastName)));

        BooleanExpression createTime = user.createTime.after(dateFormat.parse("2018-03-07 15:00:00"));
        System.out.println("-----" + JSONArray.toJSONString(repo.findAll(lastName.and(createTime))));
    }
}