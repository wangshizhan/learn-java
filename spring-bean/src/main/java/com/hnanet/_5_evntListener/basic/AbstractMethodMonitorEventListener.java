package com.hnanet._5_evntListener.basic;

public class AbstractMethodMonitorEventListener implements MethodMonitorEventListener{
    @Override
    public void onMethodBegin(MethodMonitorEvent event) {
        /**
         * 记录方法开始执行时的时间
         */
        event.timestamp = System.currentTimeMillis();
        System.out.println("AbstractMethodMonitorEventListener#onMethodBegin()");
    }

    @Override
    public void onMethodEnd(MethodMonitorEvent event) {
        /**
         * 计算方法耗时
         */
        long duration = System.currentTimeMillis() - event.timestamp;
        System.out.println("AbstractMethodMonitorEventListener#onMethodEnd()");
        System.out.println("耗时：" + duration);
    }
}
