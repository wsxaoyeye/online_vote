//package com.two.vote.config;//package com.two.vote.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//
//import javax.sql.DataSource;
//
//@Controller
//@EnableWebSecurity   //开启MVC security 安全支持
//public class MySecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//    /**
//     * @用户认证
//     * @重写 configure(AuthenticationManagerBuilder auth)
//     * @链式编程
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        /**
//         * @内存身份验证
//         * @设置密码编译器：BCryptPasswordEncoder，作为参数传入 nMemoryAuthentication()
//         */
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        auth.inMemoryAuthentication().passwordEncoder(encoder)
//                .withUser("wsx").password(encoder.encode("123456")).roles("admin","common")
//                .and()
//                .withUser("user1").password(encoder.encode("123456")).roles("common")
//                .and()
//                .withUser("user2").password(encoder.encode("123456")).roles("common")
//                .and()
//                .withUser("user3").password(encoder.encode("123456")).roles("common");
//
//    }
//
//    /**
//     * @访问控制
//     * @重写 configure(HttpSecurity http)
//     * @链式编程
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                // 需要对首页和static文件夹下静态资源进行统一放行
//                .antMatchers("/","/static/**","/bootstrap.min.css").permitAll()
//                .antMatchers("/student/getAll").hasRole("common")
//                .antMatchers("/**").hasRole("admin")
//
//                .anyRequest().authenticated() //匹配已经登录认证的用户
//        ;
//
//        //设置默认权限的登陆界面,访问权限不足自动访问
//        http.formLogin()
//                .loginPage("/login").permitAll()   //自定义login界面的跳转请求
//                .usernameParameter("username").passwordParameter("password") //默认为：username  password
//                .defaultSuccessUrl("/") //默认登录成功后走的路径
//                .failureUrl("/loginError") //失败路径，在登录界面打出错误信息，error要和页面一致
//        ;
//    }
//}
