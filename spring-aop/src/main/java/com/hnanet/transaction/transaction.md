
事务控制概述
要么都完成,要么都不完成
一个业务的成功： 调用的service是执行成功的，意味着service中调用的所有的dao是执行成功的。 事务应该在Service层统一控制。
编程式事务控制
自己动手控制事务，就叫编程式事务控制,细粒度的事务控制，可以对指定的方法，指定的方法的某几行添加事务控制
Jdbc代码 connection.setAutoCommite(false);//设置手动控制事务
Hibernate代码 Session,beginTransaction();//开启事务
声明式事务概述
Spring提供了对事务的管理，这就叫声明式事务管理，只需要在配置文件中配置即可，解耦，核心是基于AOP，粗粒度的事务控制，只能给整个方法应用事务，不能对方法的某几行应用事务（aop拦截的是方法）
Spring声明式事务管理器类
Jdbc技术：DataSourceTransactionManager
Hibernate技术：HibernateTransactionManager
Jar包
1.引入Spring-aop相关4个文件
2.引入aop名称空间
3.引入tx名称空间
Xml方式实现
<!-- 1.配置事务管理器类 -->
<bean id="txManager"
class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
<property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 2 配置事务增强(如何管理事务?) -->
<tx:advice id="txAdvice" transaction-manager="txManager">
<tx:attributes>
<tx:method name="find*" read-only="false" />
		<tx:method name=" *" /><!-- 其他使用默认值 -->
</tx:attributes>
</tx:advice>
<!-- 3 Aop配置： 拦截哪些方法(切入点表表达式) + 应用上面的事务增强配置 -->
<aop:config>
<aop:pointcut expression="execution(* service.*.*(..))" id="pt" />
<aop:advisor advice-ref="txAdvice" pointcut-ref="pt" />
</aop:config>
注解方式实现
1.引入相关jar文件
2.applicationContext.xml/bean.xml中指定注解方式实现声明式事务管理以及应用的事务管理器类
3.在需要调价事务的地方,方法上，写上@Transactional
@Transactional注解
1.应用事务的注解
2.定义到方法上：当前方法应用spring的声明式事务
3.定义到类上：当前类的所有方法都应用spring声明式事务管理
4.定义到父类上：当执行父类的方法时应用时
<!-- 配置事务管理器 -->
<!-- 启用事务注解 -->
事务的属性
readOnly = false, // 只读属性,帮助数据库引擎优化
timeout = -1, // 事务的超时时间不限制,单位是秒,指定强制回滚事务
				之前事务可以占用的时间
noRollbackFor = ArithmeticException.class, // 遇到数学异常不回滚,默认情况下对所有异
								常进行回滚
isolation = Isolation.DEFAULT, // 事务的隔离级别，数据库的默认
propagation = Propagation.REQUIRED // 事务的传播行
事务传播行为
Propagation.REQUIRED
@Transactional(propagation=Propagation.REQUIRED) 
	指定当前的方法必须在事务的环境下执行；
如果当前运行的方法，已经存在事务， 就会加入当前的事务；
@Transactional(propagation= Propagation.REQUIRED_NEW)
指定当前的方法必须在事务的环境下执行；
如果当前运行的方法，已经存在事务： 事务会挂起； 会始终开启一个新的事务，执行完后； 刚才挂起的事务才继续运行。