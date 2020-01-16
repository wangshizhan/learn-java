package com.hnanet._5_evntListener.basic;

import java.util.EventListener;

/**
 * 定义事件监听接口
 */
public interface MethodMonitorEventListener extends EventListener {
    /**
     * 处理方法执行之前发布的事件
     * @param event
     */
    public void onMethodBegin(MethodMonitorEvent event);

    /**
     * 处理方法结束时发布的事件
     * @param event
     */
    public void onMethodEnd(MethodMonitorEvent event);
}
