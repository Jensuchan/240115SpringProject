package com.myweb.www.config;


import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
 
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebConfig3 implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		// Spring MVC 프로젝트 설정을 위해 작성하는 클래스의 객체를 생성한다.
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletConfiguration.class);
		
		// 요청 발생 시 요청을 처리하는 서블릿을 DispatcherServlet으로 설정해준다.
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
		
		// 부가 설정
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
		// Bean을 정의하는 클래스를 지정한다
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootConfig.class);
		
		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);
		
		// 파라미터 인코딩 설정
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");
	}
}
	
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		// TODO Auto-generated method stub
//		return new Class[] {RootConfig.class};
//	}
//	
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		// TODO Auto-generated method stub
//		return new Class[] {ServletConfiguration.class};
//	}
//	
//	@Override
//	protected String[] getServletMappings() {
//		// TODO Auto-generated method stub
//		return new String[] {"/"};
//	}
	
	
	//EncodingFilter 설정
//	@Override
//	protected Filter[] getServletFilters() {
//		// filter 설정
//		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
//		encoding.setEncoding("UTF-8");
//		encoding.setForceEncoding(true); //외부로 나가는 데이터도 인코딩 설정(요청에 응답)
//		return new Filter[] {encoding};
//	}
//
//	@Override
//	protected void customizeRegistration(Dynamic registration) {
//		// 그외 기타 사용자 설정
//		// multipartConfig 설정
//		// 사용자 지정 Exception 처리 설정
//		super.customizeRegistration(registration);
//	}
	
	


