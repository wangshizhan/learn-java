部署项目的方式：
1. 直接将项目放到webapps目录下即可。
    * /hello：项目的访问路径-->虚拟目录
    * 简化部署：将项目打成一个war包，再将war包放置到webapps目录下。
    * war包会自动解压缩
2. 配置conf/server.xml文件
    在<Host>标签体中配置
    <Context docBase="D:\hello" path="/hehe" />
    * docBase:项目存放的路径
    * path：虚拟目录
3. 在conf\Catalina\localhost创建任意名称的xml文件。在文件中编写
    <Context docBase="D:\hello" />
    * 虚拟目录就是xml文件名称