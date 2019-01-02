package ua.configurtion.spring.project.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer  {// Головний клас який буде запускати нашу програму(spring, читати конфігурації) для того 
	//щоб сказати що він головний треба  implements WebApplicationInitializer
	//Іменной цей клас буде відповідати за обробку всіх запитів url будуть оброблятися деспетчерм сервелетів(нижче запишемо),буде створюватися наш контейнер де буду створюватися всі bean зі звязками і всі інші налаштування що будуть лежати в пакеті config будуть оброблятися, Цей клас буде відповідати за повноцінну збірку контейнера зі всіма настройками
	

	/* (non-Javadoc)
	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	
	AnnotationConfigWebApplicationContext webContext =	//Створюємо екземпляр класу який буде відповідати за читання конфігурацій по анотаціях
	   new AnnotationConfigWebApplicationContext();//Іменной цей клас буде відповідати за обробку всіх запитів url будуть оброблятися деспетчерм сервелетів(нижче запишемо),буде створюватися наш контейнер де буду створюватися всі bean зі звязками і всі інші налаштування що будуть лежати в пакеті config будуть оброблятися, Цей клас буде відповідати за повноцінну збірку контейнера зі всіма настройками

	webContext.setServletContext(servletContext);
	webContext.setConfigLocation("ua.configurtion.spring.project.config");//клас де будуть всі налаштування і шукати буде за @Configuration
    servletContext.addListener(new ContextLoaderListener(webContext));//відповідає за читання анотацій(@Service)
	
  ServletRegistration.Dynamic reg = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(webContext));//Описуємо Диспетчер сервелет повератиме jsp файл і керувати url
	reg.setLoadOnStartup(1);//ставимо пріорітет
	reg.addMapping("/");//за яким посиланням повинен стартувати
	}
	
}
