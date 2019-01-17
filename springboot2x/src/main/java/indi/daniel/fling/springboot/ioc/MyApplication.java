package indi.daniel.fling.springboot.ioc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:/ioc.properties"}, ignoreResourceNotFound = true)
@ComponentScan(lazyInit = true)
//@ImportResource(value = { "classpath:/application-context.xml" })
public class MyApplication {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplication.class);

		System.out.println(System.getProperty("clothes.color"));

		Human human = (Human)ctx.getBean("human");
		human.sayHello();
		ctx.getBeanFactory().destroyBean(human);
	}


}

