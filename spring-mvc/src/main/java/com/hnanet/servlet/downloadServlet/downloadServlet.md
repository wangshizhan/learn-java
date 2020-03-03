## 文件下载
* 分析：
    1. 超链接指向的资源如果能够被浏览器解析，则在浏览器中展示，如果不能解析，则弹出下载提示框。不满足需求
    2. 任何资源都必须弹出下载提示框
    3. 使用响应头设置资源的打开方式：
        * content-disposition:attachment;filename=xxx

* 步骤：
    1. 定义页面，编辑超链接href属性，指向Servlet，传递资源名称filename
    2. 定义Servlet
        1. 获取文件名称
        2. 使用字节输入流加载文件进内存
        3. 指定response的响应头： content-disposition:attachment;filename=xxx
        4. 将数据写出到response输出流

* 中文文件问题
    * 解决思路：
        1. 获取客户端使用的浏览器版本信息
        2. 根据不同的版本信息，设置filename的编码方式不同