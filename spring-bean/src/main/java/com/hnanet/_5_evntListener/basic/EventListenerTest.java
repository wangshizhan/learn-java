package com.hnanet._5_evntListener.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EventListenerTest {

    private List<MethodMonitorEventListener > listeners = new ArrayList<MethodMonitorEventListener>();

    public void methodMonitor() throws InterruptedException {
        MethodMonitorEvent eventObject = new MethodMonitorEvent(this);
        /**
         * 发布 MethodMonitorEvent 事件
         */
        publishEvent("begin", eventObject);
        /**
         * 模拟方法执行：休眠5秒钟
         */
        TimeUnit.SECONDS.sleep(5);
        /**
         * 发布 MethodMonitorEvent 事件
         */
        publishEvent("end", eventObject);
    }

    private void publishEvent(String status, MethodMonitorEvent event) {
        /**
         * 避免在事件处理期间，监听器被移除，这里为了安全做一个复制操作
         */
        List<MethodMonitorEventListener> copyListeners = new ArrayList<MethodMonitorEventListener>(listeners);
        for (MethodMonitorEventListener listener : copyListeners) {
            if ("begin".equals(status)) {
                listener.onMethodBegin(event);
            } else {
                listener.onMethodEnd(event);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EventListenerTest publisher = new EventListenerTest();
        publisher.addEventListener(new AbstractMethodMonitorEventListener());
        publisher.methodMonitor();
        publisher.removeAllListeners();
    }

    public void addEventListener(MethodMonitorEventListener listener) {
        listeners.add(listener);
    }

    public void removeEventListener(MethodMonitorEventListener listener) {
        listeners.remove(listener);
    }

    public void removeAllListeners() {
        listeners.clear();
    }
}