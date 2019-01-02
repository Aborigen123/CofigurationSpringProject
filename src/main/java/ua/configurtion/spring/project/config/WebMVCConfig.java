package ua.configurtion.spring.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc//Щоб могли включити анотації контроллера н-д:(@GetMapping)(або анотація яка дозволяє підтримувати інші анотації)
@ComponentScan(basePackages= {"ua.configurtion.spring.project.controller"})//вказуємо в якому паке будуть лежати наші контроллери
public class WebMVCConfig extends WebMvcConfigurerAdapter {	 //Клас який буде відповідати за viewResolver який буде шукати view (file) за запитами контроллера

	 //переписуємо дефолтний метод з alt + shift + s -> Ovveride/Implement Methods -> configureDefaultServletHandling
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) { // Для того щоб ми могли міняти метод н-д(щоб наші файли ресурсів читалися без 404 помилки) якщо ми не пропишемо то не зможемо читати н-д наші стилі
	configurer.enable();//вказуємо що будомо міняти наш метод
	}
	
	@Bean
public ViewResolver viewResolver() {	//Створимо bean який буде шукати jsp file у папці view
InternalResourceViewResolver resolver = new InternalResourceViewResolver();	

resolver.setPrefix("WEB-INF/views/");// передаємо наш префік - суфікс і клас який буде обробляти нашу jsp сторінку тобто всі теги буде компілювати в html код
resolver.setSuffix(".jsp");//суфікс
resolver.setViewClass(JstlView.class);//клас  який буде відповідальний за рендерінг наших jsp в html

return resolver;
}
	 //переписуємо дефолтний метод з alt + shift + s -> Ovveride/Implement Methods -> addResourceHandlers
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {//все що будемо тягнути з ресурсіів щоб нам дозволило
		registry.addResourceHandler("/resources/**")
		.addResourceLocations("/resources/");
	}
	
	
	
}
