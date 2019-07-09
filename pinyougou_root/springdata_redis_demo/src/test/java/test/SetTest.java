package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class SetTest {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void setValue(){
		redisTemplate.boundSetOps("name").add("大王");
		redisTemplate.boundSetOps("name").add("小王");
		redisTemplate.boundSetOps("name").add("王炸");
	}
	
	@Test
	public void getValue(){
		Set set = redisTemplate.boundSetOps("name").members();
		System.out.println(set);		
	}
	
	@Test
	public void removeValue(){
		redisTemplate.boundSetOps("name").remove("王炸");
	}
	
	@Test
	public void delete(){
		redisTemplate.delete("name");
	}

}
