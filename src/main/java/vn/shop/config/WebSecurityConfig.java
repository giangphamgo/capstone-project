package vn.fs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vn.fs.service.UserDetailService;

/**
 * @author DongTHD
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;




	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(appConfig.passwordEncoder());
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(appConfig.authenticationProvider());
    }
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		// Admin page
		http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/register","/confirmOtpRegister","/productDetail").permitAll();
		// If you are not logged in, you will be redirected to the /login page.
		http.authorizeRequests().antMatchers("/checkout").access("hasRole('ROLE_USER')");
		
		http.authorizeRequests()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginProcessingUrl("/doLogin")
			.loginPage("/login")
			.defaultSuccessUrl("/?login_success")
			.successHandler(new SuccessHandler()).failureUrl("/login?error=true")
			.failureUrl("/login?error=true")
			.permitAll()
			.and()
		.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout_success")
			.permitAll();
			
		 // remember-me
		http.rememberMe()
			.rememberMeParameter("remember");




		// Thêm một lớp Filter kiểm tra jwt
		http.addFilterBefore(appConfig.authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);


	}

}
