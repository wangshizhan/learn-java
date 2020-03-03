package com.hnanet.io;

import org.junit.Test;

import javax.naming.InterruptedNamingException;
import java.io.*;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class TestIO {
    @Test
    public void testBufferedInputStream() throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream( Utils.getResourcePath("io/test.txt") ));
        byte[] buffer = new byte[8192];
        int size = 0;
        StringBuilder stringBuilder=new StringBuilder();
        while ((size = inputStream.read(buffer))!=-1){
            stringBuilder.append(new String(buffer,0,size,"UTF-8"));
        }
        System.out.println(stringBuilder);
        inputStream.close();
    }

    @Test
    public void testBufferedReader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Utils.getResourcePath("io/test.txt")));
        StringBuilder stringBuilder = new StringBuilder();
        String data;
        while ( (data = bufferedReader.readLine()) != null ){
            stringBuilder.append(data+System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    /**
     * File 类
     * @throws IOException
     */
    @Test
    public void testFiles() throws IOException{
        // 1、获取文件属性
        Path path = Paths.get(Utils.getResourcePath()+"io/test.txt");
        System.out.println(Files.getLastModifiedTime(path));
        System.out.println(Files.size(path));
        System.out.println(Files.isSymbolicLink(path));
        System.out.println(Files.isDirectory(path));
        System.out.println(Files.readAttributes(path, "*"));

        // 2.1、遍历目录，筛选文件
        File sourceFile = new File(Utils.getResourcePath("io"));
        File destinationFile = new File(Utils.getResourcePath()+"io_new/secondFolder");
        if(!destinationFile.exists()){
            destinationFile.mkdirs();
        }
        // 筛选出当前文件夹下后缀为 .txt 的文件
        File[] files = sourceFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.getName().endsWith(".txt") && file.isFile()){
                    return true;
                }else{
                    return false;
                }
            }
        });

        List<File> fileList = new ArrayList<File>();
        getFiles(fileList,sourceFile);
        for (File file : fileList) {
            System.out.println(file.getParent()+" "+ file.getName());
        }
        // 2.2、遍历目录，筛选文件
        Path startingDir = Paths.get(Utils.getResourcePath("io"));
        List<Path> result = new LinkedList<Path>();
        Files.walkFileTree(startingDir, new FindJavaVisitor(result));
        for (Path path1 :result) {
            System.out.println("result = " + path1.toAbsolutePath());
        }
    }
    public class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List<Path> result;
        public FindJavaVisitor(List<Path> result){
            this.result = result;
        }
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
            if(file.toString().endsWith(".txt")){
                result.add(file.toAbsolutePath());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    /**
     * 获取当前目录及其子目录下，后缀为 ".txt" 的文件
     * @param fileList
     * @param file
     */
    public void getFiles(List<File> fileList, File file){
        // 如果是目录，则输出目录名称，然后遍历目录下的文件；否则输出文件名称
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for(File child : files) {
                getFiles(fileList,child);
            }
        } else {
            if(file.getName().endsWith(".txt")){
                fileList.add(file);
            }
        }
    }

    //  读取控制台输入
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一个字符");
        char c = (char) bufferedReader.read();
        System.out.println("你输入的字符为: "+c);
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一行字符");
        String str = bufferedReader2.readLine();
        System.out.println("你输入的字符为: " + str);

        Scanner s = new Scanner(System.in);
        System.out.println("请输入字符串：");
        while (true) {
            String line = s.nextLine();
            if (line.equals("exit")) break;
            System.out.println(">>>" + line);
        }
    }

    // 二进制文件的写入和读取
    @Test
    public void testBinary() throws IOException {
        String source = Utils.getResourcePath()+"io/testBinary.txt";
        byte[] bytes = {12,21,34,11,21};
        OutputStream outputStream = new FileOutputStream(source);
        // 写入二进制文件，直接打开会出现乱码
        outputStream.write(bytes);
        outputStream.close();

        InputStream inputStream = new FileInputStream(source);
        int c;
        // 读取写入的二进制文件，输出字节数组
        while ((c = inputStream.read()) != -1) {
            System.out.print(c +System.lineSeparator());
        }
    }


    /**
     * 文本文件的写入和读取
     * 默认使用 UTF8 编码
     * @throws IOException
     */
    @Test
    public void testFileText_defalt() throws IOException {
        String source_defalut = Utils.getResourcePath()+"io/testTextFile_default.txt";
        createTestFile(source_defalut);
        FileWriter fileWriter = new FileWriter(source_defalut,true);
        fileWriter.write("Hello，world！\n欢迎来到 java 世界\n");
        fileWriter.write("不会覆盖文件原本的内容\n");
        fileWriter.append("并不是追加一行内容，不要被方法名迷惑\n");
        fileWriter.append(null);
        System.out.println("文件的默认编码为" + fileWriter.getEncoding());
        fileWriter.flush();
        fileWriter.close();

        // bufferedReader 方式读取
        System.out.println();
        System.out.println("bufferedReader 方式读取");
        FileReader fileReader = new FileReader(source_defalut);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        fileReader.close();
        bufferedReader.close();

        // fileReader 方式读取
        System.out.println();
        System.out.println("fileReader 方式读取");
        FileReader fileReader2 = new FileReader(source_defalut);
        int c;
        while ((c = fileReader2.read()) != -1) {
            System.out.print((char) c);
        }
        System.out.println();
        fileReader2.close();
    }

    /**
     * 文件读写
     * 使用字节流和字符流的转换类 InputStreamReader 和 OutputStreamWriter 可以指定文件的编码
     * 使用 Buffer 相关的类来读取文件的每一行
     * @throws IOException
     */
    @Test
    public void testFileText_GBK() throws IOException {
        String source_gbk = Utils.getResourcePath()+"io/testTextFile_gbk.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(source_gbk);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");
        outputStreamWriter.write("Hello，world！\n欢迎来到 java 世界\n");
        outputStreamWriter.append("另外一行内容");
        outputStreamWriter.flush();
        System.out.println("文件的编码为" + outputStreamWriter.getEncoding());
        outputStreamWriter.close();
        fileOutputStream.close();


        InputStream inputStream = new FileInputStream(source_gbk);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"GBK");
        BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader);
        String st;
        while ((st = bufferedReader1.readLine()) != null) {
            System.out.println(st);
        }
        bufferedReader1.close();
        inputStreamReader.close();
        inputStream.close();

    }

    /**
     * 读取指定行
     */
    @Test
    public void testReadLine() throws IOException{
        String source_defalut = Utils.getResourcePath()+"io/testReadLine.txt";
        createTestFile(source_defalut);
        int line0 = 0 ;
        int line3 = 3;
        // 对于小文件
        String lessFile = Files.readAllLines(Paths.get(source_defalut)).get(line0);
        // 对于大文件
        String bigFile;
        try (Stream<String> lines = Files.lines(Paths.get(source_defalut))) {
            bigFile = lines.skip(line3).findFirst().get();
        }
        System.out.println("第0行: " +lessFile);
        System.out.println("第3行: " +bigFile);
    }

    /**
     * 修改
     */
    @Test
    public void testReplaceText() throws IOException {

        String source_defalut = Utils.getResourcePath()+"io/testReplaceText.txt";
        createTestFile(source_defalut);
        BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(new FileInputStream(source_defalut),"UTF8"));
        BufferedWriter bufferedWriter  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(source_defalut+"_new"),"UTF8"));
        String line ;
        String keyOld = "达";
        String keyNew = "阿西吧";
        StringBuilder stringBuilder = new StringBuilder();
        while (null!=(line=bufferedReader.readLine())) {
            if(line.contains(keyOld)){
                line=line.replaceAll(keyOld,keyNew);
            }
            stringBuilder.append(line+System.lineSeparator());
        }
        bufferedWriter.write(stringBuilder.substring(0,stringBuilder.length()-System.lineSeparator().length()));

        bufferedWriter.close();
        bufferedReader.close();

    }

    /**
     * Path 类
     * @throws IOException
     */
    @Test
    public void testPaths() throws IOException {
        // 1、File和Path之间的转换，File和URI之间的转换
        File file = new File(Utils.getResourcePath()+"io/test.txt");
        Path path = file.toPath();
        path.toFile();
        URI uri = file.toURI();
        System.out.println( "file: "+file.getName());
        System.out.println( "path: "+path.getFileName());
        System.out.println( "uri: "+uri.getPath());

        //	2、获取Path的相关信息
        Path path2 = FileSystems.getDefault().getPath(Utils.getResourcePath()+"io/test.txt");
        System.out.println("文件名：" + path2.getFileName());
        System.out.println("名称元素的数量：" + path2.getNameCount());
        System.out.println("父路径：" + path2.getParent());
        System.out.println("根路径：" + path2.getRoot());
        System.out.println("是否是绝对路径：" + path2.isAbsolute());
        //startsWith()方法的参数既可以是字符串也可以是Path对象
        System.out.println("是否是以为给定的路径 /Users 开始：" + path2.startsWith("/Users") );
        System.out.println("该路径的字符串形式：" + path2.toString());

        // 	3、移除Path中的冗余项
        Path currentDir = Paths.get(".");
        System.out.println(currentDir.toAbsolutePath());
        Path currentDir2 = Paths.get("./target");
        System.out.println("原始路径格式："+currentDir2.toAbsolutePath());
        System.out.println("执行normalize（）方法之后："+currentDir2.toAbsolutePath().normalize());
        System.out.println("执行toRealPath()方法之后："+currentDir2.toRealPath());

        Path currentDir3 = Paths.get("..");
        System.out.println("原始路径格式："+currentDir3.toAbsolutePath());
        System.out.println("执行normalize（）方法之后："+currentDir3.toAbsolutePath().normalize());
        System.out.println("执行toRealPath()方法之后："+currentDir3.toRealPath());
    }

    private static void copyFolder(File srcFile, File destFile) throws IOException {
        if (srcFile.isDirectory()) {
            File newFolder = new File(destFile, srcFile.getName());
            newFolder.mkdirs();

            //获取该File对象下的所有文件或者文件夹File对象
            File[] fileArray = srcFile.listFiles();
            for (File file : fileArray) {
                //递归，继续判断
                copyFolder(file, newFolder);
            }

        } else {
            File newFile = new File(destFile, srcFile.getName());
            copyFile(srcFile, newFile);
        }
    }

    public static void copyFile(File file, File newFile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos =
                new BufferedOutputStream(new FileOutputStream(newFile));

        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }
        bis.close();
        bos.close();
    }

    public void createTestFile(String source_defalut) throws IOException{
        StringBuilder data = new StringBuilder();
        data.append("哈根达斯").append(System.lineSeparator());
        data.append("哈根达斯,哈根达斯").append(System.lineSeparator());
        data.append("哈根达斯,哈根达斯,哈根达斯").append(System.lineSeparator());
        data.append("哈根达斯,哈根达斯,哈根达斯,哈根达斯").append(System.lineSeparator());
        data.append("哈根达斯,哈根达斯,哈根达斯,哈根达斯,哈根达斯").append(System.lineSeparator());
        data.append("哈根达斯,哈根达斯,哈根达斯,哈根达斯,哈根达斯,哈根达斯").append(System.lineSeparator());
        data.append("哈根达斯,哈根达斯,哈根达斯,哈根达斯,哈根达斯,哈根达斯,哈根达斯").append(System.lineSeparator());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(source_defalut,false));
        bufferedWriter.write(String.valueOf(data));
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
