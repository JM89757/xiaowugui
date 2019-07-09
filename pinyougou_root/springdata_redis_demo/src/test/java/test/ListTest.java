package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class ListTest {
	
	@Autowired
	private RedisTemplate redisTemplate;
	

	@Test
	public void testSetValue1(){
		redisTemplate.boundListOps("list1").rightPush("A");
		redisTemplate.boundListOps("list1").rightPush("B");
		redisTemplate.boundListOps("list1").rightPush("C");
	}
	
	
	@Test
	public void testGetValue1(){
		List list = redisTemplate.boundListOps("list1").range(0, 10);
		System.out.println(list);
	}
	
	@Test
	public void delete(){
		redisTemplate.delete("list1");
	}
	
	
	@Test
	public void testSetValue2(){
		redisTemplate.boundListOps("list2").leftPush("A");
		redisTemplate.boundListOps("list2").leftPush("B");
		redisTemplate.boundListOps("list2").leftPush("C");
	}
	

	@Test
	public void testGetValue2(){
		List list = redisTemplate.boundListOps("list2").range(0, 10);
		System.out.println(list);
	}
	
	
	@Test
	public void removeValue(){
		redisTemplate.boundListOps("list1").remove(0, "A");
	}

}
