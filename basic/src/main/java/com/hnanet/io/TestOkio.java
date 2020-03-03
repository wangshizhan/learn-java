package com.hnanet.io;

import okio.*;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Paths;

public class TestOkio {
    @Test
    public void TestOkio() throws IOException {
        String srcFile = Utils.getResourcePath()+"io/test.txt";
        String destFile = Utils.getResourcePath()+"io/test2.txt";
        // Okio.buffer(Okio.sink(Paths.get(destFile))).writeAll(Okio.buffer(Okio.source(Paths.get(srcFile))));

        BufferedSource source = Okio.buffer(Okio.source(new File(Utils.getResourcePath()+"io/test.txt")));
        // 创建 buffer
        Buffer buffer = new Buffer();
        // 读数据到 buffer（buffer视角，buffer 是内，文件是外）
        source.read(buffer, 1024);
        // 读数据到内存（内存视角，内存是内，buffer 是外）
        System.out.println(buffer.readUtf8Line());
        Sink sink = Okio.sink(new File(Utils.getResourcePath()+"io/test2.txt"));
        Okio.buffer(sink).writeAll(source);

    }
}
