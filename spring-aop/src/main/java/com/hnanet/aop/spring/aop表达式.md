## 1.1 execution()
```text

```
//匹配任何名称以 find 开头而且只有一个 Long 参数的方法
@Pointcut("execution(* *..find*(Long))")
public void execution1() {
}

//匹配任何名称以 find 开头的而且第一个参数为 Long 类型的方法
@Pointcut("execution(* *..find*(Long,..))")
public void execution2() {
}

execution(<修饰符>? <返回值类型>  <方法>(<参数列表>) <异常>?)
带?的，是可选项目，其他的为必选项
/**
 * execution(<修饰符>? <返回值类型> <方法>(<参数列表>) <异常>?)
 * 如下，
 * 匹配 修饰符为 public，
 * 返回值类型为任意类型，
 * 方法为 com.test.service包中 以Service结尾的类的所以方法，
 * 参数列表为任意参数，
 * 异常为java.lang.IllegalAccessException
 * 注意，如果指定了异常，那么只会匹配 throws 了 指定异常的方法！！！
 */
@Pointcut("execution(public * com.test.service.*Service.*(..) throws java.lang.IllegalAccessException)")
public void execution() {
}

## 1.2 within()
/**
 * @within 匹配类级别
 * 非 Spring环境下,要求的 annotation 的 RetentionPolicy 级别为 CLASS
 * 如下，匹配 标注有 @Service注解 的类 的所有方法
 */
@Pointcut("@within(org.springframework.stereotype.Service)")
public void within(){}

within()
// 匹配com.test包及子包下所有类的方法
@Pointcut("within(com.test..*)"
public void matchPackage(){}

## 1.3 args()
```text

```
/**
 * @args 匹配参数级别
 * 如下，匹配 某方法的参数 所属的类 标注有 authority注解 的方法
 * 即，被拦截的方法的参数中，有的参数所属的类 标注有 authority注解
 */
@Pointcut("@args(com.test.authority)")
public void args(){}

//匹配任何 只有一个Long参数 的方法
@Pointcut("args(Long)")
public void args1() {
}

//匹配第一个参数为 Long 类型的方法
@Pointcut("args(Long,..)")
public void args2() {
}

## 1.4 this和target

## 1.5 @within

## 1.6 @annotation
@annotation()
```text

```
/**
 * @annotation 匹配方法级别
 * 如下，匹配 标注有 @ToExcel注解 的方法
 */
@Pointcut("@annotation(com.demo.security.ToExcel)")
public void annotation(){}

## 1.7 @args

## 1.8 @DeclareParents
## 1.9 perthis 和 pertarget
/**
 * @target 匹配类级别
 * 非 Spring环境下,要求的 annotation 的 RetentionPolicy 级别为 RUNTIME
 * 如下，匹配 标注有 @Service注解 的类 的所有方法
 */
@Pointcut("@target(org.springframework.stereotype.Service)")
public void target(){}

## @Pointcut()
```text
    作用： 定义一个切入点
    @Pointcut()注解的value参数： 一个切面表达
```

## @Before()
```text

```
作用： 被打上 @Before 注解的方法，会在目标方法执行之前执行
@Before()注解的value参数： 除了是一个切面表达式之外，还可以是一个定义好的织入点

## @After()
```text

```
作用： 被打上 @After 注解的方法，会在目标方法执行之后执行,不管目标方法是否成功执行或抛出异常
@After()注解的value参数： 除了是一个切面表达式之外，还可以是一个定义好的织入点

## @Around()
```text

```
作用： 被打上 @After 注解的方法，会将目标方法“包围”起来， 在目标方法执行前后做一些操作
@Around()注解的value参数： 除了是一个切面表达式之外，还可以是一个定义好的织入点

## @AfterReturning()
```text

```
作用： 此注解与@After注解作用一样，不同之出在于它多了一个 returning参数，可以用来获取目标方法返回值，并作为入参带入方法中
@AfterReturning()注解的value参数： 除了是一个切面表达式之外，还可以是一个定义好的织入点

## @AfterThrowing()
作用： 此注解不同于@After注解，它只有在目标方法抛出异常之后才会执行
@AfterThrowing()注解的value参数： 除了是一个切面表达式之外，还可以是一个定义好的织入点