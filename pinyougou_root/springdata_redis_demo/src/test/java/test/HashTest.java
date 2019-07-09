package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class HashTest {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void testSetValue(){
		redisTemplate.boundHashOps("hash").put("a", "A");
		redisTemplate.boundHashOps("hash").put("b", "B");
		redisTemplate.boundHashOps("hash").put("c", "C");
		redisTemplate.boundHashOps("hash").put("d", "D");
		
	}

	@Test
	public void testGetKes(){
		Set keys = redisTemplate.boundHashOps("hash").keys();
		System.out.println(keys);
	}

	@Test
	public void testGetValues(){
		List list = redisTemplate.boundHashOps("hash").values();
		System.out.println(list);
	}

	@Test
	public void searchValueByKey(){
		String str = (String) redisTemplate.boundHashOps("hash").get("b");
		System.out.println(str);
	}

	@Test
	public void removeValue(){
		redisTemplate.boundHashOps("hash").delete("c");
	}
	
}
