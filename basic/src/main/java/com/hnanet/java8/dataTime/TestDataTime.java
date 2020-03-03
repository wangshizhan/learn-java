package com.hnanet.java8.dataTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQuery;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import static java.time.temporal.TemporalAdjusters.lastInMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * Java 8 之前关于时间和日期的API的缺陷
 * 	1. Java的 java.util.Date  和 java.util.Calendar 类易用性差，不支持时区，而且他们都不是线程安全的；
 * 	2. 用于格式化日期的类 DateFormat 被放在 java.text 包中，它是一个抽象类，所以我们需要实例化一个SimpleDateFormat 对象来处理日期格式化，并且 DateFormat 也是非线程安全，这意味着如果你在多线程程序中调用同一个 DateFormat 对象，会得到意想不到的结果。
 * 	3. 对日期的计算方式繁琐，而且容易出错，因为月份是从0开始的，从Calendar 中获取的月份需要加一才能表示当前月份。
 *
 *  在 JDBC 中，使用 Java8 的日期 LocalDate、LocalDateTime，则必须要求数据库驱动的版本不能低于4.2
 *  在实际开发过程中建议使用 LocalDate，包括存储、操作、业务规则的解读
 *  应该尽量避免使用 ChronoLocalDate，除非需要将程序的输入或者输出本地化
 */
public class TestDataTime {

    /**
     *  1、LocalDate
     *      可以通过 LocalDate 的静态方法 of() 创建一个实例
     *      也可以调用静态方法 now() 来获取当前日期
     *  LocalDate 类表示一个具体的日期，但不包含具体时间，也不包含时区信息
     */
    @Test
    public void testLocalDate() {
        LocalDate localDate = LocalDate.of(2017, 1, 4);     // 初始化一个日期：2017-01-04
        int year = localDate.getYear();                     // 年份：2017
        Month month = localDate.getMonth();                 // 月份：JANUARY
        int dayOfMonth = localDate.getDayOfMonth();         // 月份中的第几天：4
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();     // 一周的第几天：WEDNESDAY
        int length = localDate.lengthOfMonth();             // 月份的天数：31
        boolean leapYear = localDate.isLeapYear();          // 是否为闰年：false
        LocalDate now = LocalDate.now();
        System.out.println("localDate: " + localDate);
    }

    /**
     *  2、LocalTime
     *      可以通过 LocalTime 的静态方法 of() 创建一个实例
     *  LocalTime 和 LocalDate 类似，他们之间的区别在于 LocalDate 不包含具体时间，而 LocalTime 包含具体时间
     */
    @Test
    public void testLocalTime() {
        // 初始化一个时间：17:23:52
        LocalTime localTime = LocalTime.of(17, 23, 52);
        int hour = localTime.getHour();                     // 时：17
        int minute = localTime.getMinute();                 // 分：23
        int second = localTime.getSecond();                 // 秒：52
        System.out.println("LocalTime: " + localTime);
    }

    /**
     *  3、LocalDateTime
     *      可以通过 of() 方法直接创建
     *      可以调用 LocalDate 的 atTime() 方法或 LocalTime 的 atDate() 方法将 LocalDate 或 LocalTime 合并成一个 LocalDateTime
     *  LocalDateTime 类是 LocalDate 和 LocalTime 的结合体
     *  LocalDateTime 也提供用于向 LocalDate 和 LocalTime 的转化
     */
    @Test
    public void testLocalDateTime() {
        LocalDateTime ldt1 = LocalDateTime.of(2017, Month.JANUARY, 4, 17, 23, 52);
        LocalDate localDate = LocalDate.of(2017, Month.JANUARY, 4);
        LocalTime localTime = LocalTime.of(17, 23, 52);
        LocalDateTime ldt2 = localDate.atTime(localTime);
        LocalDateTime ldt3 = localTime.atDate(localDate);
        LocalDate date = ldt1.toLocalDate();
        LocalTime time = ldt1.toLocalTime();
        System.out.println("LocalDateTime: " + ldt1);
        System.out.println("LocalDateTime: " + ldt2);
        System.out.println("LocalDateTime: " + ldt3);
        System.out.println("LocalDate: " + date);
        System.out.println("LocalTime: " + time);
    }

    /**
     *  4、Instant
     *      可以使用 now() 方法创建
     *      可以通过 ofEpochSecond 方法创建
     *  比较常见的用法就是在新老日期键进行转换，Java 8为旧日期类型全部添加了toInstant()方法，然后将Instant实例转换为新日期类型
     *  Instant 用于表示一个时间戳（或者说是一个时间点），它与我们常使用的 System.currentTimeMillis() 有些类似
     *  Instant 可以精确到纳秒，System.currentTimeMillis() 方法只精确到毫秒。
     *  Instant源码，它的内部使用了两个常量
     *      seconds 表示从 1970-01-01 00:00:00 开始到现在的秒数
     *      nanos 表示纳秒部分（nanos的值不会超过999,999,999）
     */
    @Test
    public void testInstant(){
        int seconds = 120;
        int nanos = 100000;
        Instant instant = Instant.ofEpochSecond(seconds, nanos);
        Instant instant1 =  Instant.now();
        System.out.println("从 1970-01-01 00:00:00 开始后 "+seconds+" 秒的 "+nanos+" 纳秒 的时刻"+instant);
        System.out.println("instant.now: "+instant1);


        // 旧日期转换为新日期
        java.util.Date date = new java.util.Date();
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Shanghai"));
        System.out.println(dateTime);

        // 新日期转换为旧日期
        java.util.Date now2 = java.util.Date.from(Instant.now());
        System.out.println(now2);

    }


    /**
     *  5、Duration
     *      可以通过 Duration.between() 方法创建 Duration 对象
     *      可以通过 of() 方法创建，该方法接受一个时间段长度，和一个时间单位作为参数
     *  Duration 表示一个时间段
     *  Duration 包含两部分：seconds 表示秒，nanos 表示纳秒
     */
    @Test
    public void testDuration(){
        LocalDateTime from = LocalDateTime.of(2017, Month.JANUARY, 5, 10, 7, 0);    // 2017-01-05 10:07:00
        LocalDateTime to = LocalDateTime.of(2017, Month.FEBRUARY, 5, 10, 7, 0);     // 2017-02-05 10:07:00
        Duration duration = Duration.between(from, to);     // 表示从 2017-01-05 10:07:00 到 2017-02-05 10:07:00 这段时间
        long days = duration.toDays();              // 这段时间的总天数
        long hours = duration.toHours();            // 这段时间的小时数
        long minutes = duration.toMinutes();        // 这段时间的分钟数
        long seconds = duration.getSeconds();       // 这段时间的秒数
        long milliSeconds = duration.toMillis();    // 这段时间的毫秒数
        long nanoSeconds = duration.toNanos();      // 这段时间的纳秒数

        Duration duration1 = Duration.of(5, ChronoUnit.DAYS);       // 5天
        Duration duration2 = Duration.of(1000, ChronoUnit.MILLIS);  // 1000毫秒

        System.out.println("Duration: "+duration);
        System.out.println("Duration: "+duration1);
        System.out.println("Duration: "+duration2);
    }

    /**
     *  6、Period
     *      可以通过 of() 方法创建
     *      可以通过 between() 方法创建
     *  Period 在概念上和 Duration 类似，Period 是以 年月日 来衡量一个时间段
     */
    @Test
    public void testPeriod(){
        Period period = Period.of(2, 3, 6);

        Period period2 = Period.between(
                LocalDate.of(2017, 1, 5),
                LocalDate.of(2017, 2, 5));
        System.out.println("period: "+period);
        System.out.println("period: "+period2);
    }

    /**
     *  7、日期的操作
     *  可以利用 TemporalQuery 进行复杂的查询
     */
    @Test
    public void testOperator(){
        LocalDate date = LocalDate.of(2017, 1, 8);          // 2017-01-05

        LocalDate date1 = date.withYear(2016);              // 修改为 2016-01-05
        LocalDate date2 = date.withMonth(2);                // 修改为 2017-02-05
        LocalDate date3 = date.withDayOfMonth(1);           // 修改为 2017-01-01

        LocalDate date4 = date.plusYears(1);                // 增加一年 2018-01-05
        LocalDate date5 = date.minusMonths(2);              // 减少两个月 2016-11-05
        LocalDate date6 = date.plus(5, ChronoUnit.DAYS);    // 增加5天 2017-01-10

        // TemporalAdjusters 类中包含了很多静态方法可以直接使用
        LocalDate date7 = date.with(nextOrSame(DayOfWeek.SUNDAY));      // 返回下一个距离当前时间最近的星期日，若本日是星期日则返回本日
        LocalDate date9 = date.with(lastInMonth(DayOfWeek.SATURDAY));   // 返回本月最后一个星期六

        System.out.println(date);


        LocalDate date8 = LocalDate.of(2017, 1, 5);
        // 给定一个日期，计算该日期的下一个工作日
        LocalDate localDate = date8.with(temporal -> {
            // 当前日期
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

            // 正常情况下，每次增加一天
            int dayToAdd = 1;

            // 如果是星期五，增加三天
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            }

            // 如果是星期六，增加两天
            if (dayOfWeek == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }

            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(localDate);
    }

    /**
     *  8、DateTimeFormatter 格式化日期
     *  Java 8 的日期类有一个 format() 方法用于将日期格式化为字符串，该方法接收一个 DateTimeFormatter 类型参数
     */
    @Test
    public void testDateTimeFormatter(){

        LocalDateTime dateTime = LocalDateTime.now();
        String strDate1 = dateTime.format(DateTimeFormatter.BASIC_ISO_DATE);    // 20170105
        String strDate2 = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);    // 2017-01-05
        String strDate3 = dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);    // 14:20:16.998
        String strDate4 = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));   // 2017-01-05
        String strDate5 = dateTime.format(DateTimeFormatter.ofPattern("今天是：YYYY 年 MM 月 dd日 E", Locale.CHINESE));

        // 日期类也支持将一个字符串解析成一个日期对象
        String strDate6 = "2017-01-05";
        String strDate7 = "2017-01-05 12:30:05";
        LocalDate date = LocalDate.parse(strDate6, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime dateTime1 = LocalDateTime.parse(strDate7, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println("LocalDateTime: "+ strDate5);
        System.out.println("LocalDateTime: "+ dateTime1);
    }

    /**
     * 9、JDBC，H2 数据库的方式
     * @throws SQLException
     */
    @Test
    public void testH2Database() throws SQLException{
        // 使用H2嵌入式内存数据库
        String url = "jdbc:h2:mem:test";
        try (Connection connection = DriverManager.getConnection(url)) {
            //创建表
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE person(id INT PRIMARY KEY,name VARCHAR(255),birthday DATETIME)");
            //插入数据
            PreparedStatement p = connection.prepareStatement("INSERT INTO person VALUES(?,?,?)");
            p.setInt(1, 1);
            p.setString(2, "zhang3");
            p.setObject(3, LocalDate.of(1993, 5, 25).atTime(LocalTime.of(13,10,2)));
            p.executeUpdate();
            //查询数据
            ResultSet rs = statement.executeQuery("SELECT *FROM person WHERE id=1");
            rs.next();
            Person person = new Person();
            person.setId(rs.getInt(1));
            person.setName(rs.getNString(2));
            person.setBirthday(rs.getObject(3, LocalDateTime.class));
            System.out.println("数据库中:" + person);
        }
    }

    /**
     *  10.1、ZoneId
     *  新的时区类 java.time.ZoneId 是原有的 java.util.TimeZone 类的替代品
     *      可以通过 ZoneId.of() 方法创建，接收一个“区域/城市”的字符串作为参数,通过 getAvailableZoneIds() 方法获取所有合法的“区域/城市”字符串
     *      可以通过 ZoneId.systemDefault() 获取系统默认时区
     *  老的时区类 TimeZone 有 toZoneId() 转换方法
     *  有了ZoneId，我们就 可以将一个LocalDate、LocalTime或LocalDateTime对象转化为ZonedDateTime对象
     *      ZonedDateTime 对象由两部分构成，LocalDateTime 和 ZoneId
     *          可以通过 now() 方法创建
     *          可以通过 of() 方法创建
     *
     *  10.2、ZoneOffset
     *  ZoneOffset 是以当前时间和世界标准时间（UTC）/格林威治时间（GMT）的偏差来计算
     */
    @Test
    public void testZone(){
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
        ZoneId systemZoneId = ZoneId.systemDefault();

        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        ZoneId oldToNewZoneId = TimeZone.getDefault().toZoneId();

        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, shanghaiZoneId);

        ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
        LocalDateTime localDateTime2 = LocalDateTime.now();
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime2, zoneOffset);

        System.out.println("systemZoneId: "+systemZoneId);
        System.out.println("ZonedDateTime: "+zonedDateTime);
        System.out.println("OffsetDateTime: "+offsetDateTime);
    }

    @Data
    class Person{
        int id;
        String name;
        LocalDateTime birthday;
    }
}
