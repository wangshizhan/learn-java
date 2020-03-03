package com.hnanet.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import  sun.nio.fs.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class IOX{
    String name;
    Long avg;
    Long sum;
    int times;
}
public class CopyIO {

    @Test
    public void testCopyIO() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException, InterruptedException {
        String sourcePath = Utils.getResourcePath("io/test.txt");
        Map<String,List<Long>> maps = new HashMap<>();
        Class<?> clazz = Class.forName("com.hnanet.io.CopyIO");
        StopWatch sw = new StopWatch();

        List<Method> lili = new ArrayList<Method>();
        for (Method method:clazz.getMethods()) {
            if(!method.getName().startsWith("copyBy")) continue;
            maps.put(method.getName(),new ArrayList<>());
            lili.add(method);
        }
        Collections.shuffle(lili);
        int runTimes = 20;
        while(runTimes > 0){
            for(int i=0; i<lili.size(); i++){
                sw.reset();
                sw.start();
                long t1 = System.currentTimeMillis();
                lili.get(i).invoke(clazz.newInstance(), sourcePath, Utils.getResourcePath()+"io/test_"+lili.get(i).getName()+".txt");
                sw.stop();
                maps.get(lili.get(i).getName()).add(sw.getNanoTime());
                System.gc();
                Thread.sleep(50);
                Runtime.getRuntime().gc();
                Thread.sleep(50);
            }
            runTimes--;
        }
        List<IOX> listIOX = new ArrayList<IOX>();
        for (Map.Entry<String, List<Long>> entry : maps.entrySet()) {
            long los = 0;
            for (long lo : entry.getValue()) {
                los += lo;
            }
            listIOX.add(new IOX().builder().name(entry.getKey()).times(entry.getValue().size()).avg(los/(entry.getValue().size()*1000*1000)).sum(los/(1000*1000)).build());
        }
        Collections.sort(listIOX, new Comparator<IOX>() {
            @Override
            public int compare(IOX o1, IOX o2) {
                return (int) (o1.avg-o2.avg);
            }
        });
        for (IOX iox :listIOX) {
            System.out.println(iox);
        }
    }
    /**
     * 1、字节流读写复制文件
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByStream(String sourcePath,String destinationPath) throws IOException {

        InputStream inputStream = new FileInputStream(sourcePath);
        OutputStream outputStream = new FileOutputStream(destinationPath);
        byte[] buffer = new byte[8192];
        int size = 0;
        while((size = inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,size);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
    }
    /**
     * 2、字节缓冲流读写复制文件
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByBufferStream(String sourcePath,String destinationPath) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourcePath));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationPath));
        byte[] buffer = new byte[8192];
        int size = 0;
        while((size = inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,size);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
    }

    /**
     * 3、字符流读写文件
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByReader(String sourcePath,String destinationPath) throws IOException {
        FileReader fileReader =new FileReader(sourcePath);
        FileWriter fileWriter =new FileWriter(destinationPath,false);
        char[] chars = new char[8192];
        int size = 0;
        while ((size = fileReader.read(chars)) != -1) {
            fileWriter.write(chars, 0, size);
            fileWriter.flush();
        }
        fileReader.close();
        fileWriter.close();

    }

    /**
     * 4、字符缓冲流读写文件
     * * 一行一行读取，会多出一个字节
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByBufferReader(String sourcePath,String destinationPath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destinationPath,false));

// 文件大的话内存占用比较大
//        String data;
//        StringBuilder stringBuilder = new StringBuilder();
//        while((data = bufferedReader.readLine()) != null){
//            stringBuilder.append(System.lineSeparator());
//            stringBuilder.append(data);
//        }
//        stringBuilder.delete(0,System.lineSeparator().length());
//
//        bufferedWriter.write(stringBuilder.toString());
//        bufferedWriter.flush();

// 最后会多一个字符
//        String line = "";
//        while ((line = bufferedReader.readLine()) != null) {
//            bufferedWriter.write(line);
//            bufferedWriter.newLine();
//            bufferedWriter.flush();
//        }

        char[] buffer = new char[8192];
        int size = 0;
        while ((size = bufferedReader.read(buffer)) != -1) {
            bufferedWriter.write(buffer,0,size);
        }
        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
    }


    /**
     * 5.1、使用管道 Channel 复制文件
     * * 可以使用 FileInputStream、FileOutputStream 以及既用于读也用于写的 RandomAccessFile 产生 Channel 对象
     * * 一旦要用从缓冲器中读取数据的话，那么就要调用缓冲器的flip()方法，让它做好让别人读取字节的准备。
     * * 写完数据以后就要调用缓存器的 clear() 方法对所有的内部的指针重新安排，以便缓冲器在另一个 read() 操作期间能够做好接受数据的准备。然后数据就会从源文件中源源不断的读到了目标文件中。
     *
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByFileChannel(String sourcePath,String destinationPath) throws IOException {
//        FileChannel fileInChannel = new FileInputStream(Utils.getResourcePath("io/test.txt")).getChannel();
//        FileChannel fileOutChannel = new FileOutputStream(Utils.getResourcePath()+"io/test_testFileChannel.txt").getChannel();

        FileChannel fileInChannel = new RandomAccessFile(sourcePath,"r").getChannel();
        FileChannel fileOutChannel = new RandomAccessFile(destinationPath,"rw").getChannel();
        // 为缓冲器进行初始化大小
        ByteBuffer byteBuffer =ByteBuffer.allocate(8192);
        while(fileInChannel.read(byteBuffer)!=-1){
            // 做好让人读的准备
            byteBuffer.flip();
            fileOutChannel.write(byteBuffer);
            // 清除数据
            byteBuffer.clear();
        }
        fileOutChannel.close();
        fileInChannel.close();
    }

    /**
     * 5.2、使用管道 Channel 复制文件
     * * 直接将两个通道进行相连
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByFileChannelSimple(String sourcePath,String destinationPath) throws IOException {
        // 获取读通道
        FileChannel fileInChannel = new FileInputStream(sourcePath).getChannel();
        // 获取写通道
        FileChannel fileOutChannel = new FileOutputStream(destinationPath).getChannel();

        //fileOutChannel.transferFrom(fileInChannel,0,fileInChannel.size());
        fileInChannel.transferTo(0,fileInChannel.size(),fileOutChannel);
    }

    /**
     * 6、使用 Files 类复制文件
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByFiles(String sourcePath,String destinationPath) throws IOException {
        // 获取读通道
        Path sPath      = Paths.get(sourcePath);
        Path dPath = Paths.get(destinationPath);
        Files.copy(sPath,dPath, StandardCopyOption.REPLACE_EXISTING);
    }
    /**
     * 7.1、利用 Okio 插件
     */
    public void copyByOkioSimple(String sourcePath,String destinationPath) throws IOException {
        Okio.buffer(Okio.sink(Paths.get(destinationPath))).writeAll(Okio.buffer(Okio.source(Paths.get(sourcePath))));
    }
    /**
     * 7.2、利用 Okio 插件
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByOkio(String sourcePath,String destinationPath) throws IOException {
        BufferedSource source = Okio.buffer(Okio.source(new File(sourcePath)));
        Sink sink = Okio.sink(new File(destinationPath));
        Okio.buffer(sink).writeAll(source);
    }
    /**
     * 8、利用 FileUtils
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByFileUtils(String sourcePath,String destinationPath) throws IOException {
        FileUtils.copyFile(new File(sourcePath), new File(destinationPath));
    }

    /**
     * 9、利用 IO 重定向
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByIORedirect(String sourcePath,String destinationPath) throws IOException {
        FileInputStream in = new FileInputStream(sourcePath);
        PrintStream out = new PrintStream(destinationPath);
        PrintStream oldPrintStream = System.out;
        System.setIn(in);
        System.setOut(out);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            System.out.print(sc.nextLine());
        }
        System.setOut(oldPrintStream);
    }

    /**
     * 10、利用内存映射
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    public void copyByMapper(String sourcePath,String destinationPath) throws IOException {
        FileInputStream fis = new FileInputStream(sourcePath);
        FileOutputStream fos = new FileOutputStream(destinationPath);
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();
        MappedByteBuffer mbb = fisChannel.map(FileChannel.MapMode.READ_ONLY, 0, fisChannel.size());
        fosChannel.write(mbb);
        fis.close();
        fos.close();
    }
}
