package br.com.rodrigo.OFP.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.rodrigo.OFP.repository.security.UsuarioUserDetailsService;



@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioUserDetailsService usuarioUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//primeiro bloqueio depois liberar
		http.authorizeRequests()
	    .antMatchers("/usuarios/editarAdmin/**").hasRole("ADMIN")
	    .antMatchers("/usuarios/lista/").hasRole("ADMIN")
	    .antMatchers("/role/**").hasRole("ADMIN")
	    .antMatchers("/usuarios/cadastro").permitAll()
	    .antMatchers("/usuarios/").permitAll()
	    .antMatchers("/resources/**").permitAll()
	    .antMatchers("/bandeira/**").authenticated()
	    .antMatchers("/cartao-credito/**").authenticated()
	    .antMatchers("/categoriaDespesa/**").authenticated()
	    .antMatchers("/categoriaReceita/**").authenticated()
	    .antMatchers("/conta/**").authenticated()
	    .antMatchers("/extrato/**").authenticated()
	    .antMatchers("/receita/**").authenticated()
	    .antMatchers("/subCategoriaDespesa/**").authenticated()
	    .antMatchers("/subCategoriaReceita/**").authenticated()
	    .anyRequest().authenticated()
//	    .antMatchers("/url-magica-maluca-qpuioywkbliughOKJNKJQ872tyqewvtf71367").permitAll()
//	    .antMatchers("/").permitAll()
	    .and().formLogin().loginPage("/login").permitAll()
	    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    .logoutSuccessUrl("/");
//		.anyRequest().permitAll();
	} 
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}